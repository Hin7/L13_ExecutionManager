package ru.sbt.course.ExecutionManager;
/**
 * SummCounterWithExc - класс, вычисляющий сумму всех чисел от 1 до value.
 * Если сумма делится нацело на 10, выкидывает исключение.
 * Ловит InterruptedException и оборачивает его в свое исключение.
 */

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SummCounterWithExc implements Runnable {
    private int value;

    public SummCounterWithExc(int val) {
        value = val;
    }

    @Override
    public void run() throws IllegalArgumentException {

        int result = 0;
        for (int i = 1; i <= value; i++) {
            result += i;
        }
        // тянем время имитируя длительные вычисления
        // и ловим interrupt снова взводя прерывание
        long delay = System.currentTimeMillis() + 100;
        try {
            while (delay > System.currentTimeMillis()) {
                TimeUnit.MILLISECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        if (result % 10 == 0)
            throw new IllegalArgumentException("Результат делится на 10 (summ " + value + "=" + result + ")! из " +
                    Thread.currentThread().getName());

        System.out.println("Summ " + value + " = " + result + " из " + Thread.currentThread().getName());
    }
}
