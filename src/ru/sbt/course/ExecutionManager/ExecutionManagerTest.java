package ru.sbt.course.ExecutionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Тест класса ExecutionManager
 * Задание 2 по уроку 13 СБТ.
 *
 * @author Hin
 * @version 1.0 20/04/2020
 */

public class ExecutionManagerTest {

    static private final int numberOfTask = 50;

    static public void main(String[] args) throws InterruptedException {

        System.out.println("Тест ExecutionManager");
        Runnable[] tasks = new Runnable[numberOfTask];
        for (int i = 0; i < tasks.length; i++)
            tasks[i] = new SummCounterWithExc(i + 1);

        ExecutionManager executionManager = new ExecutionManagerImp();

        Context con = executionManager.execute(new EndPrintTask(), tasks);

        List<String> reportList = new ArrayList<>();

        int tmp = -1;
        while (!con.isFinished()) {
            if (tmp != con.getCompletedTaskCount()) {
                tmp = con.getCompletedTaskCount();
                reportList.add("Завершено = " + tmp + "; исключений = " + con.getFailedTaskCount() +
                        "; прервано = " + con.getInterruptedTaskCount());
            }
            Thread.currentThread().yield();
        }
        reportList.add("Завершено = " + con.getCompletedTaskCount() + "; исключений = " + con.getFailedTaskCount() +
                "; прервано = " + con.getInterruptedTaskCount());

        System.out.println();
        System.out.println("Отчет");
        for (String line : reportList)
            System.out.println(line);
        if (numberOfTask == con.getCompletedTaskCount() + con.getFailedTaskCount() + con.getInterruptedTaskCount())
            System.out.println("Число задач " + numberOfTask + " = " + con.getCompletedTaskCount() + "+" +
                    con.getFailedTaskCount() + "+" + con.getInterruptedTaskCount() + " Test-OK");
        else
            System.out.println("Test-filed");
        System.out.println();


        executionManager = new ExecutionManagerImp();
        con = executionManager.execute(new EndPrintTask(), tasks);
        reportList.clear();
        tmp = -1;
        while (!con.isFinished()) {
            if (tmp != con.getCompletedTaskCount()) {
                tmp = con.getCompletedTaskCount();
                reportList.add("Завершено = " + tmp + "; исключений = " + con.getFailedTaskCount() +
                        "; прервано = " + con.getInterruptedTaskCount());
                if (tmp > 20) con.interrupt();
            }
            Thread.currentThread().yield();
        }
        reportList.add("Завершено = " + con.getCompletedTaskCount() + "; исключений = " + con.getFailedTaskCount() +
                "; прервано = " + con.getInterruptedTaskCount());


        System.out.println();
        System.out.println("Отчет с прерыванем");
        for (String line : reportList)
            System.out.println(line);
        if (numberOfTask == con.getCompletedTaskCount() + con.getFailedTaskCount() + con.getInterruptedTaskCount())
            System.out.println("Число задач " + numberOfTask + " = " + con.getCompletedTaskCount() + "+" +
                    con.getFailedTaskCount() + "+" + con.getInterruptedTaskCount() + " Test-OK");
        else
            System.out.println("Test-filed");

    }
}
