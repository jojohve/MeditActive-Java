package controllers;

import java.util.List;
import java.util.Scanner;

import models.Booking;
import models.Goal;
import models.User;
import controllers.Bookings;
import controllers.Goals;
import controllers.Users;

public class actions {
    public static void main(String[] args) {
        List<User> users = Users.readUsersFromFile("csv\\utenti.csv");
        List<Goal> goals = Goals.readGoalsFromFile("csv\\obiettivi.csv");
        List<Booking> bookings = Bookings.readBookingsFromFile("csv\\prenotazioni.csv");

        Scanner scanner = new Scanner(System.in);
        int result = -1;

        do {
            System.out.println("Scegli il numero:");
            System.out.println("1- Stampa di tutti gli obiettivi:");
            System.out.println("2- Decidi un obiettivo esistente:");
            System.out.println("3- Rimuovi un obiettivo:");
            System.out.println("4- Aggiungi un nuovo utente:");
            System.out.println("5- Esporta un file con gli obiettivi disponibili:");
            System.out.println("0- Esci:");

            try {
                if (scanner.hasNextLine()) {
                    String command = scanner.nextLine().trim();
                    result = Integer.parseInt(command);

                    switch (result) {
                        case 1:
                            System.out.println("Stampa di tutti gli obiettivi:");
                            Goals.printGoals(goals);
                            break;

                        case 2:
                            System.out.println("Decidi un obiettivo esistente:");
                            break;

                        case 3:
                            System.out.println("Rimuovi un obiettivo:");
                            break;

                        case 4:
                            System.out.println("Aggiungi un nuovo utente:");
                            Users.addUser();
                            break;

                        case 5:
                            System.out.println("Esporta un file con gli obiettivi disponibili:");
                            break;

                        case 0:
                            System.out.println("Sei davvero sicuro di voler uscire? (SI/NO)");
                            String confirm = scanner.nextLine().trim().toUpperCase();
                            if (confirm.equals("NO")) {
                                System.out.println("Ritorniamo al menu principale.");
                                result = -1;
                            } else if (confirm.equals("SI")) {
                                System.out.println("Addio!");
                                result = 0;
                            } else {
                                System.out.println("Risposta non valida. Per favore, inserisci 'SI' o 'NO'.");
                            }
                            break;

                        default:
                            System.out.println("Scegli un altro numero");
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Devi scegliere un numero");
            }
        } while (result != 0);

        scanner.close();
    }
}