package controllers;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import models.Booking;
import models.Goal;

public class actions {

    public static void start(String[] args) {
        Users.loadUsersFromFile();
        Goals.loadGoalsFromFile();
        Bookings.loadBookingsFromFile();

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
                            Goals.printGoals();
                            break;

                            case 2:
                            System.out.println("Decidi un obiettivo esistente:");
                            Users.printUsers();
                            System.out.println("Digita ID Obiettivo:");
                            int idObiettivo = Integer.parseInt(scanner.nextLine());
                            System.out.println("Digita ID Utente:");
                            int idUtente = Integer.parseInt(scanner.nextLine());
                        
                            Goal selectedGoal = Goals.getGoalById(idObiettivo);
                        
                            if (selectedGoal != null && selectedGoal.getDisponibilita().equals("SI")) {
                                Booking newBooking = new Booking(Bookings.getBookings().size() + 1, idObiettivo, idUtente, null, null);
                                Bookings.addBooking(newBooking);
                                selectedGoal.setDisponibilita("NO");
                                System.out.println("Prenotazione creata e obiettivo aggiornato con successo!");
                            } else {
                                System.out.println("Obiettivo non trovato o non disponibile.");
                            }
                            break;
                        
                        case 3:
                            System.out.println(
                                    "Inserisci il numero dell'obiettivo da eliminare (1 per il primo, 2 per il secondo, ecc.):");
                            try {
                                int i = Integer.parseInt(scanner.nextLine()) - 1;
                                Goals.deleteGoal(i);
                            } catch (NumberFormatException e) {
                                System.out.println("Devi inserire un numero valido.");
                            }
                            break;

                        case 4:
                            System.out.println("Aggiungi un nuovo utente:");
                            Users.addUser();
                            break;

                        case 5:
                            System.out.println("Esporta un file con gli obiettivi disponibili:");
                            String date = new SimpleDateFormat("dd_MM_yyyy").format(new java.util.Date());
                            String fileName = "obiettivi_" + date + ".csv";
                            String filePath = "csv/" + fileName;
                            Goals.exportGoalsToCSV(filePath);
                            break;

                        case 0:
                            System.out.println("Sei davvero sicuro di voler uscire? (SI/NO)");
                            String confirm = scanner.nextLine().trim().toUpperCase();
                            if (confirm.equals("NO")) {
                                System.out.println("Ritorniamo al menu principale.");
                                result = -1;
                            } else if (confirm.equals("SI")) {
                                System.out.println("A Presto!");
                                result = 0;
                            } else {
                                System.out.println("Risposta non valida. Per favore, inserisci 'SI' o 'NO'.");
                                result = 0;
                            }
                            break;

                        default:
                            System.out.println("Scegli un altro numero");
                            break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Devi scegliere un numero valido.");
            }
        } while (result != 0);

        scanner.close();
    }
}