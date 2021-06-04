package com.example.demo.设计模式.工厂模式.工厂方法模式;

import com.example.demo.设计模式.工厂模式.简单工厂模式.Course;
import com.example.demo.设计模式.工厂模式.简单工厂模式.JavaCourse;
import com.example.demo.设计模式.工厂模式.简单工厂模式.PythonCourse;

public class PythonCourseFactory implements CourseFactory{


    @Override
    public Course create() {
        return new PythonCourse();
    }
}
