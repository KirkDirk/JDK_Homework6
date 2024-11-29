package ru.kirkdirk;

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
    static Map<Integer, String> resultsWithoutChaging;

    public static void main(String[] args) {

        countDoors = 3;
        raffle = 1000;
        random = new Random();
        resultsSelectionChange = new HashMap<>();
        resultsWithoutChaging = new HashMap<>();

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
//          Определяем дверь второго выбора
            for (int i = 0; i < countDoors; i++) {
                if (i != openDoor && i != choice1Door) {
                    choice2Door = i;
                }
            }
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
        int countWinSC = Collections.frequency(new ArrayList<String>(resultsSelectionChange.values()), "win");
        System.out.println("Количество win при смене выбора: " + countWinSC);

        int countWinWC = Collections.frequency(new ArrayList<String>(resultsWithoutChaging.values()), "win");
        System.out.println("Количество win без смены выбора: " + countWinWC);

    }


}