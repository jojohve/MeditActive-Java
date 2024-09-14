package controllers;

import models.Goal;
import utils.FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Goals {
    private static List<Goal> goals = new ArrayList<>();

    public static void loadGoalsFromFile() {
        List<String[]> data = FileManager.readCSV("csv/obiettivi.csv");
        for (String[] row : data) {
            try {
                if (row.length >= 6) {
                    int id = Integer.parseInt(row[0]);
                    String nome = row[1];
                    String descrizione = row[2];
                    String tipologia = row[3];
                    int durata = Integer.parseInt(row[4]);
                    String disponibilità = row[5];
    
                    goals.add(new Goal(id, nome, descrizione, tipologia, durata, disponibilità));
                } else {
                    System.out.println("Riga mal formattata: " + String.join(", ", row));
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore di formattazione numerica: " + e.getMessage());
            }
        }
    }

    public static List<Goal> readGoalsFromFile(String filePath) {
        List<Goal> goals = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length < 6) {
                   // System.out.println("Riga mal formattata: " + line);
                    continue;
                }

                try {
                    int id = Integer.parseInt(values[0]);
                    String nome = values[1];
                    String descrizione = values[2];
                    String tipologia = values[3];
                    int durata = Integer.parseInt(values[4]);
                    String disponibilità = values[5];

                    Goal goal = new Goal(id, nome, descrizione, tipologia, durata, disponibilità);
                    goals.add(goal);
                } catch (NumberFormatException e) {
                    System.out.println("Errore di formattazione in riga: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file: " + e.getMessage());
        }
        return goals;
    }

    public static void printGoals() {
        for (Goal goal : goals) {
            System.out.println(goal);
        }
    }

    public static void deleteGoal(int index) {
        if (index >= 0 && index < goals.size()) {
            goals.remove(index);
            System.out.println("Obiettivo eliminato con successo.");
        } else {
            System.out.println("Indice obiettivo non valido.");
        }
    }    

    public static void exportGoalsToCSV(String exportFilePath) {
        try (FileWriter writer = new FileWriter(exportFilePath)) {
            writer.append("ID;Nome;Descrizione;Tipologia;Durata;Disponibilità\n");

            for (Goal goal : goals) {
                writer.append(goal.getId() + ";");
                writer.append(goal.getNome() + ";");
                writer.append(goal.getDescrizione() + ";");
                writer.append(goal.getTipologia() + ";");
                writer.append(goal.getDurata() + ";");
                writer.append(goal.getDisponibilita() + "\n");
            }

            System.out.println("Esportazione completata con successo in " + exportFilePath);
        } catch (IOException e) {
            System.out.println("Errore durante l'esportazione del file: " + e.getMessage());
        }
    }

    public static List<Goal> getGoals() {
        return goals;
    }

    public static Goal getGoalById(int id) {
        for (Goal goal : goals) {
            if (goal.getId() == id) {
                return goal;
            }
        }
        return null;
    }    
}