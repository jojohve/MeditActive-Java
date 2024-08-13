package controller;

import java.util.Scanner;

public class actions {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int result = -1;

        do {
            System.out.println("Scegli il numero");
            try {
                String command = scanner.nextLine();
                result = Integer.parseInt(command);

                switch (result) {
                    case 1:
                        System.out.println("ehi amico,hai selezionato 1");
                        break;
                    case 2:
                        System.out.println("ehi amico,hai selezionato 2");
                        break;
                    case 3:
                        System.out.println("ehi amico,hai selezionato 3");
                        break;
                    case 4:
                        System.out.println("ehi amico,hai selezionato 4");
                        break;
                    case 5:
                        System.out.println("ehi amico,hai selezionato 5");
                        break;
                    case 0:
                        System.out.println("ehi amico,hai selezionato 0, addio");
                        break;
                    default:
                        System.out.println("Scegli un altro numero");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("devi scegliere un numero");
            }
        } while (result != 0);
        scanner.close();
    }
}