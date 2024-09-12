package controllers;

import models.Goal;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Goals {
    @SuppressWarnings("unused")
    private static final String filePath = "csv/obiettivi.csv";

    static List<Goal> loadGoalsFromFile(String filePath) {
        List<Goal> goals = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                if (fields.length == 6) {
                    try {
                        int id = Integer.parseInt(fields[0]);
                        String nome = fields[1];
                        String descrizione = fields[2];
                        String tipologia = fields[3];
                        int durata = Integer.parseInt(fields[4]);
                        String disponibilità = fields[5];

                        goals.add(new Goal(id, nome, descrizione, tipologia, durata, disponibilità));
                    } catch (NumberFormatException e) {
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
        return goals;
    }

    public static List<Goal> readGoalsFromFile(String filePath) {
        List<Goal> goals = new ArrayList<>();
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
                    String descrizione = values[2];
                    String tipologia = values[3];
                    int durata = Integer.parseInt(values[4]);
                    String disponibilità = values[5];

                    if (durata < 0) {
                        System.out.println("Durata negativa in riga: " + line);
                        continue;
                    }

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

    public static void printGoals(List<Goal> goals) {
        for (Goal goal : goals) {
            System.out.println(goal);
        }
    }

    public static void deleteGoal(List<Goal> goals, int i) {
        if (i >= 0 && i < goals.size()) {
            Goal removedGoal = goals.remove(i);
            removedGoal.setDisponibilità("SI");
            System.out.println("Obiettivo '" + removedGoal.getNome() + "' eliminato con successo.");
        } else {
            System.out.println("Indice non valido. Nessun obiettivo eliminato.");
        }
    }

    public static void exportGoalsToCSV(List<Goal> goals, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("ID;Nome;Descrizione;Tipologia;Durata\n");

            for (Goal goal : goals) {
                writer.append(goal.getId() + ";");
                writer.append(goal.getNome() + ";");
                writer.append(goal.getDescrizione() + ";");
                writer.append(goal.getTipologia() + ";");
                writer.append(goal.getDurata() + "\n");
            }

            System.out.println("Esportazione completata con successo in " + filePath);
        } catch (IOException e) {
            System.out.println("Errore durante l'esportazione del file: " + e.getMessage());
        }
    }
}