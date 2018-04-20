package pers.jz.concurrent.bad;

/**
 * @author Jemmy Zhang on 2018/4/20.
 */
public class BadUnfairLockerTest {
    public static void main(String[] args) {
        BadUnfairLocker locker = new BadUnfairLocker();
        startThread(locker, "thread1");
        startThread(locker, "thread2");
    }

    private static void startThread(BadUnfairLocker locker, String thread) {
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
