package com.sofi.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Service("DockerService")
public class DockerService {

    private String dockerName;

    public DockerService(String dockerName) {
        this.dockerName = dockerName;
    }

    public void getMachine() {
        ArrayList<String> output = runCommand("docker-machine ls");

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
        System.out.println("Listing Containers");

        String command = "docker-machine ls";
        ArrayList<String> output = runCommand(command);

        //System.out.println(output);
    }

    private ArrayList<String> runCommand(String cmd) {
        ArrayList<String> result = new ArrayList<>();
        String line;
        Process p;

        try {
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((line = reader.readLine())!= null) {
                result.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
