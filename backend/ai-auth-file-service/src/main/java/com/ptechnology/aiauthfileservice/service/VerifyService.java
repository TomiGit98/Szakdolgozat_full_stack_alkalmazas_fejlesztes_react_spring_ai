package com.ptechnology.aiauthfileservice.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class VerifyService {

    public String runPythonScript(String arg1, String arg2) throws IOException, InterruptedException {

        File fileToDelete = new File("files/representations_vgg_face.pkl");

        boolean success = false;
        if (fileToDelete.exists()) {
             success = fileToDelete.delete();
        }
        System.out.println("Töröltem: " + success);


        String[] command = {
                "python",
                "main2.py",
                arg1,
                arg2
        };
        System.out.println("Command: " + command.toString());

        Process p = Runtime.getRuntime().exec(command);
        p.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        boolean kiir = false;

        String id = "-1";

        String line = "";
        while(true) {
            try {
                if(!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(line.contains("identity")){
                kiir = true;
            }
            if(kiir == true && line.indexOf('/') != -1 && line.indexOf('_') != -1){
                //System.out.println(line.substring(line.indexOf('/')+1, line.indexOf('_')));
                System.out.println(line);
                id = line.substring(line.indexOf('/')+1, line.indexOf('_'));
            }
        }
        System.out.println("Done");
        return id;
    }
}
