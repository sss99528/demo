package com.example.demo.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessDemo {

    public static void main(String[] args) throws IOException {
//        String[] command = new String[]{"ping qq.com","ping baidu.com"};

        String command_pre = "ping ";
        String split = " ";
        String value = " baidu.com";
        String command = command_pre + split + value;
//        Process exec = Runtime.getRuntime().exec("ping baidu.com");
        Process exec = Runtime.getRuntime().exec(command);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String tempLine =null;
        while ( (tempLine = bufferedReader.readLine())!=null){
            sb.append(tempLine);
        }
        System.out.println(sb.toString());

    }

}
