package com.example.demo.多线程.Thread;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;

import java.util.Iterator;
import java.util.Locale;

@Slf4j(topic = "Thread-One")
public class DemoThreadOne extends Thread{
    @Override
    public void run() {
        /*log.info("马上进入睡眠，时间3s");
        try {
            sleep(1000*3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("睡眠结束");*/
        log.info(this.getName().toLowerCase(Locale.ROOT));
    }
}
