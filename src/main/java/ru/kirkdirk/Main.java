package ru.kirkdirk;

import java.util.*;

public class Main {
    static Random random;
    static Map<Integer, Boolean> results1;       // Статистика для игрока, не меняющего свой выбор.
    static Map<Integer, Boolean> results2;       // Статистика для игрока, изменяющего свой выбор.
    static int doorsNumber;                      // Количество дверей.
    static int attempts;                         // Количество попыток.

    /**
     * Количество дверей
     */
    static int countDoors;
    /**
     * Количество розыгрышей
     */
    static int raffle;
    /**
     * Переменная для выбора двери
     */
    static Random choice;
    /**
     * Мапа для результатов розыгрыша c заменой первоначального выбора
     */
    static Map<Integer, String> resultsSelectionChange;
    static Map<Integer, String> resultsWithoutChaging;

    public static void main(String[] args) {

        random = new Random();
        results1 = new HashMap<>();
        results2 = new HashMap<>();
        doorsNumber = 3; // код работает только для 3-х дверей, так что
                        // вместо переменной можно было использовать просто 3
        attempts = 1000;

        countDoors = 3;
        raffle = 1000;
        choice = new Random();
        resultsSelectionChange = new HashMap<>();
        resultsWithoutChaging = new HashMap<>();

        System.out.println(" Парадокс Монти Холла: " +
                doorsNumber + " двери и " +
                raffle + " розыгрышей\n");


        for (int round = 0; round < raffle; round++) {
//          Определяем:
//          - выигрышную дверь,
//          - дверь первого выбора,
//          - открытую дверь
            int winDoor = random.nextInt(countDoors)+1; // дверь, за которой приз
            int choice1Door = random.nextInt(countDoors)+1; // первый выбор двери
            int openDoor = -1;
            int choice2Door = -1;
            for (int i = 1; i < countDoors+1; i++) {
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