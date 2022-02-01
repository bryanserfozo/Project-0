package com.revature.loggingSingleton;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggingSingleton {

    private static final String FILE = "log.txt";
    private static StringBuffer sb;
    private BufferedWriter fileWriter;

    private boolean writeToFile = true;
    private boolean writeToConsole = false;


    private static LoggingSingleton logger;

    private LoggingSingleton(){
        try{
            this.fileWriter = new BufferedWriter(new FileWriter(FILE, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static LoggingSingleton getLogger(){
        if(logger == null) logger = new LoggingSingleton();
        return logger;
    }


    public void debug(String message){
        sb = new StringBuffer();
        sb.append("DEBUG - ");
        sb.append(LocalDateTime.now());
        sb.append(" - ");
        sb.append(message);
        if(writeToFile){
            try{
                fileWriter.write(sb.toString());
                fileWriter.newLine();
                fileWriter.flush();
            } catch(IOException e){
                e.printStackTrace();
            }
        }

        if(writeToConsole){
            System.out.println(sb.toString());
        }
    }


    public void info(String message){
        sb = new StringBuffer();
        sb.append("INFO - ");
        sb.append(LocalDateTime.now());
        sb.append(" - ");
        sb.append(message);
        if(writeToFile){
            try{
                fileWriter.write(sb.toString());
                fileWriter.newLine();
                fileWriter.flush();
            } catch(IOException e){
                e.printStackTrace();
            }
        }

        if(writeToConsole){
            System.out.println(sb.toString());
        }
    }


    public void warn(String message){
        sb = new StringBuffer();
        sb.append("WARN - ");
        sb.append(LocalDateTime.now());
        sb.append(" - ");
        sb.append(message);
        if(writeToFile){
            try{
                fileWriter.write(sb.toString());
                fileWriter.newLine();
                fileWriter.flush();
            } catch(IOException e){
                e.printStackTrace();
            }
        }

        if(writeToConsole){
            System.out.println(sb.toString());
        }
    }

    public void setWriteToFile(boolean val){
        this.writeToFile = val;
    }

    public void setWriteToConsole(boolean val){
        this.writeToConsole = val;
    }

}