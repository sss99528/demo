package com.example.demo.jvm.逃逸分析;

/**
 * -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC
 * -server -Xmx10m -Xms10m -XX:-DoEscapeAnalysis -XX:+PrintGC
 * 逃逸分析来优化jvm
 *
 * definitely：
 *         Java hotspot 分析新创建对象的使用范围，并决定是否在java堆上分配内存的一项技术
 *          开启逃逸分析：-XX:+DoEscapeAnalysis
 *          关闭逃逸分析：-XX:-DoEscapeAnalysis
 *          显示分析结果：-XX:+PrintEscapeAnalysis
 */
public class Demo {


    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long e = System.currentTimeMillis();
        System.out.println(e - b);
    }

    /**
     * 成员变量来进行逃逸分析
     */
    public static void alloc() {
        byte[] b = new byte[2];
        b[0] = 1;
    }


}
