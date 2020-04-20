/**
 * Пулл потоков с фиксированным количеством потоков
 */
package ru.sbt.course.ExecutionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FixedThreadPool implements ThreadPool {

    protected ConcurrentLinkedQueue<Runnable> taskQueue;
    protected Runnable finalTask;
    protected List<Thread> threads;
    Object locker = new Object();

    private int filedTaskCount = 0;
    private int completedTaskCount = 0;
    private volatile boolean isFinished = false;
    private volatile boolean isFinishing;

    public int getFiledTaskCount() {
        int result;
        synchronized (locker) {
            result = filedTaskCount;
        }
        return result;
    }

    public int getCompletedTaskCount() {
        int result;
        synchronized (locker) {
            result = completedTaskCount;
        }
        return result;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public int getTaskQueueSize() {
        return taskQueue.size();
    }


    protected class TaskThread extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    if (taskQueue.size() > 0 && !isFinishing) {
                        taskQueue.poll().run();
                        synchronized (locker) {
                            completedTaskCount++;
                        }
                    } else {

                        stopOtherTreads();
                        finalTask.run();
                        isFinished = true;
                        interrupt();
                    }
                    sleep(0);
                } catch (InterruptedException e) {
                    interrupt();
                } catch (IllegalArgumentException e) {
                    synchronized (locker) {
                        filedTaskCount++;
                    }
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public FixedThreadPool(int countPool) {

        taskQueue = new ConcurrentLinkedQueue<>();
        threads = new ArrayList<>();
        for (int i = 0; i < countPool; i++)
            threads.add(new TaskThread());
    }

    @Override
    public void start() {
        for (Thread t : threads)
            t.start();
    }

    @Override
    public void execute(Runnable finalTask, Runnable... task) {
        for (Runnable t : task)
            taskQueue.add(t);
        this.finalTask = finalTask;
    }

    @Override
    public void stop_threads() {
        isFinishing = true;
        for (Thread t : threads) {
            t.interrupt();
            try {
                t.join();
            } catch (InterruptedException e) {

            }
        }
        isFinished = true;
    }

    public void stopOtherTreads() throws InterruptedException {
        isFinishing = true;
        for (Thread t : threads) {
            if (!Thread.currentThread().equals(t)) {
                t.interrupt();
                t.join();
            }

        }

    }

}
