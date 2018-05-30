package com.sofi.services;

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

    public DockerService() {
    }

    public void getMachine() {
        String[] cmd = {"/bin/sh", "-c", "docker-machine ls"};
        ArrayList<String> output = runCommand(cmd);

        for(String line : output) {
            if (line.contains(dockerName) && line.contains("Running")) {
                System.out.println("The " + dockerName + " docker machine is running!");
                return;
            } else if (line.contains(dockerName)) {
                System.out.println("The " + dockerName + " docker machine is NOT running!");
                return;
            }
        }

        System.out.println("The Docker Machine (" + dockerName + ") listed in your Application.conf could not be found");
    }

    public void listContainers() {
        String[] cmd = { "/bin/sh", "-c", "docker-compose ps"};
        ArrayList<String> output = runCommand(cmd);

        ArrayList<String> containersRunning = new ArrayList<>();
        ArrayList<String> projectContainers = new ArrayList<>(Arrays.asList(containerNames));

        for (String cont : projectContainers) {
            System.out.println(cont);
        }

        if (output.isEmpty()) {
            System.out.println("No containers could be found. You probably need to run the 'eval $(docker-machine env training' " +
                    "command in the terminal window running this program");
            return;
        }

        // If the container is found, it's added to a list (to make sure each container is accounted for)
        for (String line : output) {
            for (String containerName : projectContainers) {
                if (line.contains(containerName) && line.contains("Up")) {
                    System.out.println(containerName + " is running!");
                    containersRunning.add(containerName);
                } else if (line.contains(containerName)) {
                    System.out.println(containerName + " is NOT running!");
                    containersRunning.add(containerName);
                }
            }
        }

        // Now we check to make sure all of the containers have been found
        if (containersRunning.isEmpty()) {
            System.out.println("There are no containers running.  You should run 'docker-compose up' in " + dockerLocation);
        } else {
            for (String container : projectContainers) {
                if (!containersRunning.contains(container)) {
                    System.out.println("The " + container + " is NOT running");
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
