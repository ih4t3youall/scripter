package ar.com.sourcesistemas.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Terminal {

    public int executeInTerminal(String filePath){
        if (System.getProperty("os.name").equals("Linux")){
            return executeLinux(filePath);
        }else {
           return executeMacOs(filePath);
        }
    }

    public int executeLinux(String filePath){
        String command = "gnome-terminal -- bash -c \"vim "+filePath+"\" ";

        try {
            final String[] wrappedCommand;
            wrappedCommand = new String[]{"osascript",
                    "-e", "tell application \"Terminal\" to activate",
                    "-e", "tell application \"Terminal\" to do script \"" + command + ";exit\""};
            Process process = new ProcessBuilder(command)
                    .redirectErrorStream(true)
                    .start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // Your superior logging approach here
                }
            }
            process.wait();
            return process.waitFor();
        }catch(Exception e){
        }
        return -1;

    }

    public int executeMacOs(String filePath){
        try {
            final String[] wrappedCommand;
            String command = "vim "+filePath;
            wrappedCommand = new String[]{"osascript",
                    "-e", "tell application \"Terminal\" to activate",
                    "-e", "tell application \"Terminal\" to do script \"" + command + ";exit\""};
            Process process = new ProcessBuilder(wrappedCommand)
                    .redirectErrorStream(true)
                    .start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // Your superior logging approach here
                }
            }
            process.wait();
            return process.waitFor();
        }catch(Exception e){
        }
        return -1;


    }

}
