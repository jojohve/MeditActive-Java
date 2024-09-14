package controllers;

import models.User;
import utils.FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Users {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static List<User> users = new ArrayList<>();

    public static void loadUsersFromFile() {
        List<String[]> data = FileManager.readCSV("csv/utenti.csv");
        for (String[] row : data) {
            try {
                Date dataDiNascita = new SimpleDateFormat("yyyy-MM-dd").parse(row[3]);
                users.add(new User(Integer.parseInt(row[0]), row[1], row[2], dataDiNascita, row[4], row[5]));
            } catch (ParseException e) {
                System.out.println("Errore di parsing della data: " + e.getMessage());
            }
        }
    }

    public static List<User> readUsersFromFile(String filePath) {
        List<User> users = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                
                if (values.length < 6) {
                    System.out.println("Riga mal formattata: " + line);
                    continue;
                }

                try {
                    int id = Integer.parseInt(values[0]);
                    String nome = values[1];
                    String cognome = values[2];
                    Date dataDiNascita = dateFormat.parse(values[3]);
                    String indirizzo = values[4];
                    String documentoId = values[5];

                    User user = new User(id, nome, cognome, dataDiNascita, indirizzo, documentoId);
                    users.add(user);
                } catch (NumberFormatException e) {
                    System.out.println("Errore di formattazione in riga: " + line);
                } catch (ParseException e) {
                    System.out.println("Errore di parsing della data in riga: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file: " + e.getMessage());
        }
        return users;
    }    

    public static void printUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static void addUser(User newUser) {
        users.add(newUser);
        System.out.println("Utente aggiunto con successo.");
    }

    public static void addUser() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci ID:");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Inserisci Nome:");
        String nome = scanner.nextLine();

        System.out.println("Inserisci Cognome:");
        String cognome = scanner.nextLine();

        System.out.println("Inserisci Data di Nascita (yyyy-MM-dd):");
        Date dataDiNascita = null;
        try {
            dataDiNascita = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Formato data non valido.");
            return;
        }

        System.out.println("Inserisci Indirizzo:");
        String indirizzo = scanner.nextLine();

        System.out.println("Inserisci Documento ID:");
        String documentoId = scanner.nextLine();

        addUser(new User(id, nome, cognome, dataDiNascita, indirizzo, documentoId));
    }

    public static void deleteUser(int userId) {
        User toRemove = null;
        for (User user : users) {
            if (user.getId() == userId) {
                toRemove = user;
                break;
            }
        }

        if (toRemove != null) {
            users.remove(toRemove);
            System.out.println("Utente eliminato con successo.");
        } else {
            System.out.println("Utente non trovato.");
        }
    }

    public static void exportUsersToCSV(String exportFilePath) {
        try (FileWriter writer = new FileWriter(exportFilePath)) {
            writer.append("ID;Nome;Cognome;Data di Nascita;Indirizzo;Documento ID\n");

            for (User user : users) {
                writer.append(user.getId() + ";");
                writer.append(user.getNome() + ";");
                writer.append(user.getCognome() + ";");
                writer.append(dateFormat.format(user.getDataDiNascita()) + ";");
                writer.append(user.getIndirizzo() + ";");
                writer.append(user.getDocumentoId() + "\n");
            }

            System.out.println("Esportazione completata con successo in " + exportFilePath);
        } catch (IOException e) {
            System.out.println("Errore durante l'esportazione del file: " + e.getMessage());
        }
    }

    public static List<User> getUsers() {
        return users;
    }

    public static User getUserById(int idUtente) {
        for (User user : users) {
            if (user.getId() == idUtente) {
                return user;
            }
        }
        return null;
    }
}