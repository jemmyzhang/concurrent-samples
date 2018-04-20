package pers.jz.concurrent.bad;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Jemmy Zhang on 2018/4/20.
 */
public class UnfairBadLockerTest {
    public static void main(String[] args) {
        UnfairBadLocker locker = new UnfairBadLocker();
        startThread(locker, "thread1");
        startThread(locker, "thread2");
    }

    private static void startThread(UnfairBadLocker locker, String thread) {
        new Thread(() -> {
            while (true) {
                try {
                    locker.lock();
                    System.out.println(thread + " locked.");
                    Thread.sleep(1000L);
                    locker.unlock();
                    System.out.println(thread + " unlocked.");
                } catch (InterruptedException e) {
                }
            }
        }).start();
    }
}
