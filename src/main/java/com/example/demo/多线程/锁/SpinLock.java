package com.example.demo.多线程.锁;

import com.example.demo.多线程.Thread.DemoThreadOne;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Data
public class SpinLock {
    // 原子性引用，多线程下，对对象的修改是原子性操作，保证数据正确性
    private AtomicReference<Thread> cas = new AtomicReference<Thread>();

    /**
     * 获取锁
     */
    public void lock(CountDownLatch countDownLatch) {
        Thread current = Thread.currentThread();
        // 利用CAS
        long beginTime = System.currentTimeMillis();
        while (!cas.compareAndSet(null, current)) {
            long nowTime = System.currentTimeMillis();

            log.info("wait to get the lock ,you has wait for "+(nowTime-beginTime)/1000 +"s");
            if ((nowTime-beginTime)/1000 >2){
                // 停止当前线程
                Thread.currentThread().interrupt();
                countDownLatch.countDown();
                log.info("is isInterrupted : " + Thread.currentThread().isInterrupted()+"。");
            }
        }
    }

    /**
     * 释放锁
     */
    public void unlock() {
        Thread current = Thread.currentThread();
        cas.compareAndSet(current, null);
        log.info(Thread.currentThread().getName()+" has release lock");
    }


    @Test
    public void spinLockTest(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        final CountDownLatch countDownLatch = new CountDownLatch(2);// 参数为需要执行countDown的次数
        int count = 0;
        final SpinLock spinLock = new SpinLock();
        for (int i = 0 ; i < 2 ; i++){
            executorService.execute(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    spinLock.lock(countDownLatch);
                    System.out.println(Thread.currentThread().getName()+"\t开始运行");
                    Thread.currentThread().sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"\t结束运行");
                    spinLock.unlock();
                    countDownLatch.countDown();// 每次执行一次就把count 减一
                }
            });

        }
        try {
            countDownLatch.await();//调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }


    @Test
    public void test02() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread( ()->{
            countDownLatch.countDown();
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();// 挂起主线程

    }

}



