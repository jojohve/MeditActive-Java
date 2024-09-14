package controllers;

import models.Booking;
import utils.FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.FileWriter;

public class Bookings {
    private static List<Booking> bookings = new ArrayList<>();

    public static void loadBookingsFromFile() {
        List<String[]> data = FileManager.readCSV("csv/prenotazioni.csv");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (String[] row : data) {
            try {
                if (row.length >= 5) {
                    Date dataInizio = dateFormat.parse(row[3]);
                    Date dataFine = dateFormat.parse(row[4]);
                    bookings.add(
                            new Booking(Integer.parseInt(row[0]), Integer.parseInt(row[1]), Integer.parseInt(row[2]),
                                    dataInizio, dataFine));
                } else {
                    System.out.println("Riga mal formattata: " + String.join(", ", row));
                }
            } catch (ParseException e) {
                System.out.println("Errore di parsing della data: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Errore di formattazione numerica: " + e.getMessage());
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
                    // System.out.println("Riga mal formattata: " + line);
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
                    System.out.println("Errore di formattazione numerica in riga: " + line);
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

    public static void addBooking(int id, int idCorso, int idUtente, Date dataInizio, Date dataFine) {
        Booking newBooking = new Booking(id, idCorso, idUtente, dataInizio, dataFine);
        bookings.add(newBooking);
        System.out.println("Prenotazione aggiunta con successo.");
    }

    public static void deleteBooking(int bookingId) {
        Booking toRemove = null;
        for (Booking booking : bookings) {
            if (booking.getId() == bookingId) {
                toRemove = booking;
                break;
            }
        }

        if (toRemove != null) {
            bookings.remove(toRemove);
            System.out.println("Prenotazione eliminata con successo.");
        } else {
            System.out.println("Prenotazione non trovata.");
        }
    }

    public static void exportBookingsToCSV(String exportFilePath) {
        try (FileWriter writer = new FileWriter(exportFilePath)) {
            writer.append("ID;ID Corso;ID Utente;Data Inizio;Data Fine\n");

            for (Booking booking : bookings) {
                writer.append(booking.getId() + ";");
                writer.append(booking.getIdCorso() + ";");
                writer.append(booking.getIdUtente() + ";");
                writer.append(new SimpleDateFormat("dd/MM/yyyy").format(booking.getDataInizio()) + ";");
                writer.append(new SimpleDateFormat("dd/MM/yyyy").format(booking.getDataFine()) + "\n");
            }

            System.out.println("Esportazione completata con successo in " + exportFilePath);
        } catch (IOException e) {
            System.out.println("Errore durante l'esportazione del file: " + e.getMessage());
        }
    }

    public static List<Booking> getBookings() {
        return bookings;
    }

    public static Booking getBookingById(int id) {
        for (Booking booking : bookings) {
            if (booking.getId() == id) {
                return booking;
            }
        }
        return null;
    }
}