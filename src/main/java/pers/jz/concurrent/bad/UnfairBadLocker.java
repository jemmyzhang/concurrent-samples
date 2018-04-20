package pers.jz.concurrent.bad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Jemmy Zhang on 2018/4/19.
 */
public class UnfairBadLocker {

    private MonitorObject monitorObject = new MonitorObject();

    private boolean isLocked;

    private Thread lockingThread;

    private List<MonitorObject> waitingList = new ArrayList<MonitorObject>();

    public void lock() throws InterruptedException {
        synchronized (this) {
            synchronized (monitorObject) {
                while (isLocked) {
                    try {
                        monitorObject.wait();
                    } catch (InterruptedException e) {
                        waitingList.remove(monitorObject);
                        throw e;
                    }
                }
                waitingList.remove(monitorObject);
                lockingThread = Thread.currentThread();
                isLocked = true;
            }
        }
    }

    public void unlock(){
        synchronized (this) {
            Thread thread = Thread.currentThread();
            if (!Objects.equals(thread, lockingThread)) {
                throw new IllegalMonitorStateException("Calling thread has not locked this lock");
            }
            isLocked = false;
            lockingThread = null;
            synchronized (monitorObject) {
                monitorObject.notify();
            }
        }
    }

    public static class MonitorObject {
    }

    public static void main(String[] args){

    }
}
