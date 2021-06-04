package com.example.demo.jvm.oopKlass;

import org.junit.jupiter.api.Test;

import javax.crypto.interfaces.PBEKey;


public class Demo extends Children{


    /**
     * todo 一个类的装载顺序以及多重继承情况下，调用的关系
     *
     * 总结： 在方法区创建一个instanceKlass 用来描述一个类型所具有的特征==》 Class 对象。
     * 然后里面有个superClass属性指向其超类，以及这个类对应的方法表。
     * 只包含本身定义的方法，不包含从父类继承来的，如果调用到父类继承来的方法，
     * 会在运行中通过superclass 这个属性来依次向上级追溯，找到最近的实现，就来调用
     */
    @Test
    public void test01() {
        Class<? extends Children> aClass = new Children().getClass();
        System.out.println(aClass.getSuperclass().getName().toString());
        this.say();
    }

    @Test
    public void test02() throws NoSuchMethodException {

        System.out.println(new Demo().getClass().getMethod("test01").getClass().getSuperclass().getName().toString());//java.lang.reflect.Executable 这个类的类加载器，是null

        System.out.println(this.getClass().getClassLoader().toString());//jdk.internal.loader.ClassLoaders$AppClassLoader@1f89ab83 当前类使用的是AppClassLoader 应用类加载器
    }

    @Test
    public  void test03(){
        StringBuffer sb = new StringBuffer("111");
        appendStr(sb);
        System.out.println(sb);//111123
        changeStr(sb);//这里传递过去的是 引用的copy 并没有改变实例的值，因此不会改变
        System.out.println(sb);//111123

    }
    public void appendStr(StringBuffer sb){
        sb.append("123");
    }
    public void changeStr(StringBuffer sb){
        sb = new StringBuffer("123");
        System.out.println(sb);//123
    }



}
