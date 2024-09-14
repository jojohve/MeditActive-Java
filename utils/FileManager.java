package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static List<String[]> readCSV(String fileName) {
        List<String[]> data = new ArrayList<>();
        try (InputStream is = FileManager.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new FileNotFoundException("File non trovato nel JAR: " + fileName);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = br.readLine()) != null) {
                    data.add(line.split(";"));
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }
        return data;
    }

    public static void writeCSV(String filePath, List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] line : data) {
                bw.write(String.join(";", line));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Errore nella scrittura del file: " + e.getMessage());
        }
    }
}