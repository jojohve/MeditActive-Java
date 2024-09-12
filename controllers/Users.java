package controllers;

import models.User;
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
    private static final String filePath = "csv/utenti.csv";

    static List<User> loadUsersFromFile(String filePath) {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length == 6) {
                    try {
                        int id = Integer.parseInt(fields[0]);
                        String nome = fields[1];
                        String cognome = fields[2];
                        Date dataDiNascita = sdf.parse(fields[3]);
                        String indirizzo = fields[4];
                        String documentoId = fields[5];

                        users.add(new User(id, nome, cognome, dataDiNascita, indirizzo, documentoId));
                    } catch (NumberFormatException | ParseException e) {
                        System.out.println("Errore nel parsing della riga: " + line);
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Formato della riga non valido: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<User> readUsersFromFile(String filePath) {
        List<User> users = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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
                    Date dataDiNascita = sdf.parse(values[3]);
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

    public static void printUsers(List<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static void addUser() {
        try (Scanner scanner = new Scanner(System.in)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Inserisci ID:");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.println("Inserisci Nome:");
            String nome = scanner.nextLine();

            System.out.println("Inserisci Cognome:");
            String cognome = scanner.nextLine();

            System.out.println("Inserisci Data di Nascita (dd/MM/yyyy):");
            Date dataDiNascita = sdf.parse(scanner.nextLine());

            System.out.println("Inserisci Indirizzo:");
            String indirizzo = scanner.nextLine();

            System.out.println("Inserisci Documento ID:");
            String documentoId = scanner.nextLine();

            User newUser = new User(id, nome, cognome, dataDiNascita, indirizzo, documentoId);

            try (FileWriter fw = new FileWriter(filePath, true)) {
                fw.write(String.format("%d;%s;%s;%s;%s;%s%n",
                        newUser.getId(),
                        newUser.getNome(),
                        newUser.getCognome(),
                        sdf.format(newUser.getDataDiNascita()),
                        newUser.getIndirizzo(),
                        newUser.getDocumentoId()));
                System.out.println("Utente aggiunto con successo.");
            } catch (IOException e) {
                System.out.println("Errore durante l'aggiunta dell'utente: " + e.getMessage());
            }
        } catch (ParseException e) {
            System.out.println("Errore nel formato della data.");
        }
    }
}