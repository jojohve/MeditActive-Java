package controllers;

import models.Goal;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Goals {

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

                    Goal goal = new Goal(id, nome, descrizione, tipologia, durata, disponibilità);
                    goals.add(goal);
                } catch (NumberFormatException e) {
                    System.out.println("Errore di formattazione in riga: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return goals;
    }

    public static void printGoals(List<Goal> goals) {
        for (Goal goal : goals) {
            System.out.println(goal);
        }
    }
}