package com.example.demo.文件;

import org.junit.jupiter.api.Test;

import java.io.File;

public class Demo {
    @Test
    public void test(){

        File file = new File("C:\\Users\\剑来\\IdeaProjects\\demo\\src\\main\\java\\com\\example\\demo\\文件\\世界.txt");
        String name = file.getName();
        System.out.println(name);
    }


}
