package ru.sbt.course.ExecutionManager;

/**
 * ExecutionManager - интерфейс управления пулом потоков
 * Задание 2 урока 13 СБТ (Java memory model).
 *
 * @author Hin
 * @version 1.0 17/04/2020
 */

public interface ExecutionManager {
    /**
     * execute
     * @param callback - задача, которая выполняется после окончания задач в пуле потоков
     * @param tasks - массив параллельно выполняемых задач в пуле потоков
     * @return - интнрфейс получения информации о выполнении задач
     */
    Context execute(Runnable callback, Runnable... tasks);
}
