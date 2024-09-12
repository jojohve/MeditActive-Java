package controllers;

import models.Booking;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bookings {
    @SuppressWarnings("unused")
    private static final String filePath = "csv/prenotazioni.csv";

    static List<Booking> loadBookingsFromFile(String filePath) {
        List<Booking> bookings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length == 5) {
                    try {
                        int id = Integer.parseInt(fields[0]);
                        int idCorso = Integer.parseInt(fields[1]);
                        int idUtente = Integer.parseInt(fields[2]);
                        Date dataInizio = sdf.parse(fields[3]);
                        Date dataFine = sdf.parse(fields[4]);

                        bookings.add(new Booking(id, idCorso, idUtente, dataInizio, dataFine));
                    } catch (NumberFormatException e) {
                        System.out.println("Errore nel parsing della riga (NumberFormatException): " + line);
                        e.printStackTrace();
                    } catch (ParseException e) {
                        System.out.println("Errore nel parsing della data (ParseException): " + line);
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Formato della riga non valido: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public static List<Booking> readBookingsFromFile(String filePath) {
        List<Booking> bookings = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length < 5) {
                    System.out.println("Riga mal formattata: " + line);
                    continue;
                }

                try {
                    int id = Integer.parseInt(values[0]);
                    int idCorso = Integer.parseInt(values[1]);
                    int idUtente = Integer.parseInt(values[2]);
                    Date dataInizio = sdf.parse(values[3]);
                    Date dataFine = sdf.parse(values[4]);

                    Booking booking = new Booking(id, idCorso, idUtente, dataInizio, dataFine);
                    bookings.add(booking);
                } catch (NumberFormatException e) {
                    System.out.println("Errore di formattazione in riga: " + line);
                } catch (ParseException e) {
                    System.out.println("Errore di parsing della data in riga: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file: " + e.getMessage());
        }
        return bookings;
    }

    public static void printBookings(List<Booking> bookings) {
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }
}