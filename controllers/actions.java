package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import models.Booking;
import models.Goal;
import models.User;

public class actions {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void start(String[] args) {
        List<User> users = null;
        List<Goal> goals = null;
        List<Booking> bookings = null;

        try {
            // Leggi i file CSV e carica i dati
            users = readUsersFromFile("csv/utenti.csv");
            goals = readGoalsFromFile("csv/obiettivi.csv");
            bookings = readBookingsFromFile("csv/prenotazioni.csv");

        } catch (IOException e) {
            System.err.println("Errore durante la lettura dei file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

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
                            try {
                                Users.printUsers(users);
                                System.out.println("Digita ID Obiettivo");
                                int idObiettivo = Integer.parseInt(scanner.nextLine());
                                System.out.println("Digita ID Utente");
                                int idUtente = Integer.parseInt(scanner.nextLine());

                                Goal selectedGoal = null;
                                for (Goal goal : goals) {
                                    if (goal.getId() == idObiettivo) {
                                        selectedGoal = goal;
                                        break;
                                    }
                                }

                                if (selectedGoal != null && selectedGoal.getDisponibilità().equals("SI")) {
                                    int newBookingId = bookings.size() + 1;

                                    Booking newBooking = new Booking(newBookingId, idObiettivo, idUtente, null, null);
                                    bookings.add(newBooking);

                                    selectedGoal.setDisponibilità("NO");

                                    System.out.println("Prenotazione creata e obiettivo aggiornato con successo!");
                                } else {
                                    System.out.println("Obiettivo non trovato o non disponibile.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Devi inserire un numero valido.");
                            }
                            break;

                        case 3:
                            System.out.println("Inserisci il numero dell'obiettivo da eliminare (1 per il primo, 2 per il secondo, ecc.):");
                            try {
                                int i = Integer.parseInt(scanner.nextLine()) - 1;
                                Goals.deleteGoal(goals, i);

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
                            String date = new SimpleDateFormat("dd_MM_yyyy").format(new Date());
                            String fileName = "obiettivi_" + date + ".csv";
                            String filePath = "csv/" + fileName;
                            Goals.exportGoalsToCSV(goals, filePath);
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

    private static List<User> readUsersFromFile(String filePath) throws IOException {
        List<User> users = new ArrayList<>();
        try (InputStream is = actions.class.getClassLoader().getResourceAsStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    int id = Integer.parseInt(values[0]);
                    String nome = values[1];
                    String cognome = values[2];
                    Date dataDiNascita = null;
                    try {
                        dataDiNascita = DATE_FORMAT.parse(values[3]);
                    } catch (ParseException e) {
                        System.err.println("Formato data non valido: " + values[3]);
                    }
                    String indirizzo = values[4];
                    String documentoId = values[5];
                    users.add(new User(id, nome, cognome, dataDiNascita, indirizzo, documentoId));
                }
            }
        }
        return users;
    }

    private static List<Goal> readGoalsFromFile(String filePath) throws IOException {
        List<Goal> goals = new ArrayList<>();
        try (InputStream is = actions.class.getClassLoader().getResourceAsStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    int id = Integer.parseInt(values[0]);
                    String nome = values[1];
                    String descrizione = values[2];
                    String tipologia = values[3];
                    int durata = Integer.parseInt(values[4]);
                    String disponibilità = values[5];
                    goals.add(new Goal(id, nome, descrizione, tipologia, durata, disponibilità));
                }
            }
        }
        return goals;
    }

    private static List<Booking> readBookingsFromFile(String filePath) throws IOException {
        List<Booking> bookings = new ArrayList<>();
        try (InputStream is = actions.class.getClassLoader().getResourceAsStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) {
                    int id = Integer.parseInt(values[0]);
                    int idObiettivo = Integer.parseInt(values[1]);
                    int idUtente = Integer.parseInt(values[2]);
                    Date dataInizio = null;
                    Date dataFine = null;
                    try {
                        dataInizio = DATE_FORMAT.parse(values[3]);
                        dataFine = DATE_FORMAT.parse(values[4]);
                    } catch (ParseException e) {
                        System.err.println("Formato data non valido: " + values[3] + " o " + values[4]);
                    }
                    bookings.add(new Booking(id, idObiettivo, idUtente, dataInizio, dataFine));
                }
            }
        }
        return bookings;
    }
}