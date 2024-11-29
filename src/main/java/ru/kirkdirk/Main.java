package ru.kirkdirk;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.*;

public class Main {
     /**
     * Количество дверей
     */
    static int countDoors;
    /**
     * Количество розыгрышей
     */
    static int raffle;
    /**
     * Генератор случайности
     */
    static Random random;
    /**
     * Мапа для результатов розыгрыша c заменой первоначального выбора
     */
    static Map<Integer, String> resultsSelectionChange;
    /**
     * Мапа для результата без замены первоначального выбора
      */
    static Map<Integer, String> resultsWithoutChaging;

    public static void main(String[] args) {

        countDoors = 3;
        raffle = 1000;
        random = new Random();
        resultsSelectionChange = new HashMap<>();
        resultsWithoutChaging = new HashMap<>();

        double [] array = new double[raffle]; // для проверки common.math

        System.out.println("\n Парадокс Монти Холла: " +
                countDoors + " двери и " +
                raffle + " розыгрышей\n");


        for (int round = 0; round < raffle; round++) {
//          Определяем:
//          - выигрышную дверь,
//          - дверь первого выбора,
//          - открытую дверь
            int winDoor = random.nextInt(countDoors); // дверь, за которой приз
            int choice1Door = random.nextInt(countDoors); // первый выбор двери
            int openDoor = -1;
            int choice2Door = -1;
            for (int i = 0; i < countDoors; i++) {
                if (i != winDoor && i != choice1Door) {
                    openDoor = i;
                }
            }

            array[round] = openDoor;// для проверки common.math

//          Определяем дверь второго выбора при смене первоначального выбора
            for (int i = 0; i < countDoors; i++) {
                if (i != openDoor && i != choice1Door) {
                    choice2Door = i;
                }
            }
            // Проверяем и записываем в HashMap'ы результаты раунда
            if (winDoor == choice2Door) {
                resultsSelectionChange.put(round, "win");
            } else {
                resultsSelectionChange.put(round, "lose");
            }
            if (winDoor == choice1Door) {
                resultsWithoutChaging.put(round, "win");
            } else {
                resultsWithoutChaging.put(round, "lose");
            }
        }

        DescriptiveStatistics ds = new DescriptiveStatistics(array);
        System.out.println("Сумма элементов array = " + ds.getSum());

        // Выводим результаты розыгрыша в 1000 раундов
        int countWinSC = Collections.frequency(new ArrayList<String>(
                resultsSelectionChange.values()), "win");
        int countLoseSC = Collections.frequency(new ArrayList<String>(
                resultsSelectionChange.values()), "lose");
        System.out.println("\nПри смене первоначального выбора: \n Количество win: "
                + countWinSC + "\n Количество lose: " + countLoseSC);

        int countWinWC = Collections.frequency(new ArrayList<String>(
                resultsWithoutChaging.values()), "win");
        int countLoseWC = Collections.frequency(new ArrayList<String>(
                resultsWithoutChaging.values()), "lose");
        System.out.println("\nБез смены первоначального выбора: \n Количество win: "
                + countWinWC + "\n Количество lose: " + countLoseWC);
    }
}