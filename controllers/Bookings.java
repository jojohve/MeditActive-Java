package controllers;

import models.Booking;
import models.Goal;
import models.User;
import utils.FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bookings {
    private static List<Booking> bookings = new ArrayList<>();

    public static void loadBookingsFromFile() {
        List<String[]> data = FileManager.readCSV("csv/prenotazioni.csv");
        for (String[] row : data) {
            try {
                Date dataInizio = new SimpleDateFormat("yyyy-MM-dd").parse(row[3]);
                Date dataFine = new SimpleDateFormat("yyyy-MM-dd").parse(row[4]);
                bookings.add(new Booking(Integer.parseInt(row[0]), Integer.parseInt(row[1]), Integer.parseInt(row[2]),
                        dataInizio, dataFine));
            } catch (ParseException e) {
                System.out.println("Errore di parsing della data: " + e.getMessage());
            }
        }
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

    public static void printBookings() {
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public static List<Booking> getBookings() {
        return bookings;
    }

    public static void addBooking(Booking newBooking) {
        Goal selectedGoal = Goals.getGoalById(newBooking.getIdCorso());
        User selectedUser = Users.getUserById(newBooking.getIdUtente());
    
        if (selectedGoal != null && selectedGoal.getDisponibilita().equals("SI") && selectedUser != null) {
            int newBookingId = bookings.size() + 1;
            Booking bookingWithNewId = new Booking(newBookingId, newBooking.getIdCorso(), newBooking.getIdUtente(),
                    newBooking.getDataInizio(), newBooking.getDataFine());
            bookings.add(bookingWithNewId);
    
            selectedGoal.setDisponibilita("NO");
    
            System.out.println("Prenotazione creata e obiettivo aggiornato con successo!");
        } else {
            if (selectedGoal == null) {
                System.out.println("Obiettivo non trovato.");
            } else if (!selectedGoal.getDisponibilita().equals("SI")) {
                System.out.println("Obiettivo non disponibile.");
            }
            if (selectedUser == null) {
                System.out.println("Utente non trovato.");
            }
        }
    }
}