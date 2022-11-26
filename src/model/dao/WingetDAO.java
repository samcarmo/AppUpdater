package model.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author samue
 */
public class WingetDAO {

    public String command;
    public String info;

    public void runCommand(String command) throws Exception {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", command);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
                info = line;
            }
        } catch (IOException ex) {
            Logger.getLogger(WingetDAO.class.getName()).log(Level.SEVERE, null, ex);
            info = "Error";
        }
    }

    public String getUpdate() throws Exception {
        command = "winget upgrade all";
        this.runCommand(command);
        return info;
    }

    public String getOutdated() throws Exception {
        command = "winget upgrade";
        this.runCommand(command);
        return info;
    }

    public String getAppList() throws Exception {
        command = "winget export -o apps.json";
        this.runCommand(command);
        return info;
    }

}
