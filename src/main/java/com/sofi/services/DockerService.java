package com.sofi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service("DockerService")
public class DockerService {

    @Value("${docker.machine.name}")
    private String dockerName;

    @Value("${docker.training.location}")
    private String dockerLocation;

    @Value("${docker.container.names}")
    private String[] containerNames;

    private static Logger log = LoggerFactory.getLogger(DockerService.class);

    public DockerService() {
    }

    public void getMachine() {
        String[] cmd = {"/bin/sh", "-c", "docker-machine ls"};
        ArrayList<String> output = runCommand(cmd);

        for(String line : output) {
            if (line.contains(dockerName) && line.contains("Running")) {
                log.info("The {} docker machine is running!", dockerName);
                return;
            } else if (line.contains(dockerName)) {
                log.warn("The {} docker machine is NOT running!", dockerName);
                return;
            }
        }

        log.warn("The Docker Machine (" + dockerName + ") listed in your Application.conf could not be found");
    }

    public void listContainers() {
        String[] cmd = { "/bin/sh", "-c", "docker-compose ps"};
        ArrayList<String> output = runCommand(cmd);

        ArrayList<String> containersRunning = new ArrayList<>();
        ArrayList<String> projectContainers = new ArrayList<>(Arrays.asList(containerNames));

        if (output.isEmpty()) {
            log.warn("No containers could be found. You probably need to run the 'eval $(docker-machine env training' " +
                    "command in the terminal window running this program");
            return;
        }

        // If the container is found, it's added to a list (to make sure each container is accounted for)
        for (String line : output) {
            for (String containerName : projectContainers) {
                if (line.contains(containerName) && line.contains("Up")) {
                    log.info("{} is running!", containerName);
                    containersRunning.add(containerName);
                } else if (line.contains(containerName)) {
                    log.warn("{} is NOT running!", containerName);
                    containersRunning.add(containerName);
                }
            }
        }

        // Now we check to make sure all of the containers have been found
        if (containersRunning.isEmpty()) {
            log.warn("There are no containers running.  You should run 'docker-compose up' in {}", dockerLocation);
        } else {
            for (String container : projectContainers) {
                if (!containersRunning.contains(container)) {
                    log.warn("The {} container is NOT running", container);
                }
            }
        }
    }

    private ArrayList<String> runCommand(String[] cmd) {
        ArrayList<String> result = new ArrayList<>();
        String line;
        Process p;

        try {
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
