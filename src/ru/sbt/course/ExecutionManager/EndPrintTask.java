package ru.sbt.course.ExecutionManager;

/**
 * Финальная задача.
 */

public class EndPrintTask implements Runnable {
    @Override
    public void run() {
        System.out.println("EndPrintTask: все завершено! из " + Thread.currentThread().getName());
    }
}
