package pers.jz.concurrent.bad;

/**
 * @author Jemmy Zhang on 2018/4/24.
 */
public class UnfairLocker {


    private boolean isLocked = false;

    private Thread lockingThread;

    public synchronized void lock() throws InterruptedException {
        if (isLocked) {
            wait();
        }
        isLocked = true;
        lockingThread = Thread.currentThread();
    }

    public synchronized void unlock() throws InterruptedException {
        if (lockingThread != Thread.currentThread()) {
            throw new UnsupportedOperationException();
        }
        isLocked = false;
        lockingThread = null;
    }
}
