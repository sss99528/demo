package com.example.demo.日志;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Marker;

import java.util.Locale;

@Slf4j(topic = "Demo")
public class Demo {
    @Test
    public void test01(){
        /**
         * 14:34:16.450 [main] INFO com.example.demo.日志.Demo - ch.qos.logback.classic.Logger
         * 14:34:16.453 [main] INFO com.example.demo.日志.Demo - app
         * 14:34:16.453 [main] INFO com.example.demo.日志.Demo - app
         */
        log.info(log.getClass().getName());
        log.info(log.getClass().getClassLoader().getName().toLowerCase(Locale.SIMPLIFIED_CHINESE));
        log.info(this.getClass().getClassLoader().getName().toLowerCase(Locale.SIMPLIFIED_CHINESE));
    }


}
