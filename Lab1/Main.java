package org.example;

import java.io.*;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void saveGrades (Stream<Map.Entry<String, Float>> grades, String filename) {
        File file = new File(filename);

        try {
            FileWriter fw = (file.exists() ? new FileWriter(file, false) : new FileWriter(file, true));
            grades.forEach((e) -> {
                try {
                    fw.append(e.getKey() + ": " + e.getValue() + "\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            fw.close();
        }
        catch (IOException ex) {
            System.err.println(ex.getCause());
        }
    }

    public static void saveGradesByRarity (Stream<Map.Entry<String, Map.Entry<Float, Float>>> grades, String filename) {
        File file = new File(filename);

        try {
            FileWriter fw = (file.exists() ? new FileWriter(file, false) : new FileWriter(file, true));
            grades.forEach((e) -> {
                try {
                    fw.append(e.getKey() + ": " + e.getValue().getKey() + "\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            fw.close();
        }
        catch (IOException ex) {
            System.err.println(ex.getCause());
        }
    }

    public static Stream<Map.Entry<String, Float>> readFile (String filename) {
        Map<String, Float> data = new HashMap<>();
        try {
            BufferedReader bR = new BufferedReader(new FileReader(new File(filename)));
            String line = null;
            while ((line = bR.readLine()) != null) {
                String[] lData = line.split(": ");
                data.put(lData[0], Float.valueOf(lData[1]));
            }
            bR.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No file found!");
        } catch (IOException ex) {
            System.out.println("Error reading file!");
        }
        return data.entrySet().stream();
    }

    public static void main(String[] args) {
        String[] subjects = new String[]{
                "Zaawansowana inzynieria oprogramowania",
            "Programowanie aplikacji na platformę iOS",
            "Programowanie aplikacji na platforme Android",
            "Zaawansowana programowanie w Javie"};
        Stream<String> stream = Arrays.stream(subjects);
        Stream<String> filtered_stream = stream.filter(e -> e.toLowerCase().contains("zaaw"));
        filtered_stream.forEach(System.out::println);
        Float[] marks = new Float[]{3.0f, 5.0f, 5.0f, 4.5f};
        Stream<Float> mark_stream = Arrays.stream(marks);
        Map<Float, Long> counters = mark_stream.collect(Collectors.groupingBy(Float::floatValue, Collectors.counting()));
        counters.forEach((e, v) -> System.out.println(e + ": " + v));
        // 1.2
        Map<String, Float> grades = new HashMap<>();
        for (int i = 0; i < subjects.length; i++) grades.put(subjects[i], marks[i]);
        Stream<Map.Entry<String, Float>> sorted_grades = grades.entrySet().stream().sorted(Map.Entry.comparingByValue());
        //sorted_grades.forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
        //saveGrades(sorted_grades, "sorted_by_grade.txt");
        Map<String, Map.Entry<Float, Float>> grades_with_rarity = new HashMap<>();
        for (int i = 0; i < subjects.length; i++) {
            Map.Entry<Float, Float> g = new AbstractMap.SimpleEntry<Float, Float>(marks[i], 1.0f / counters.get(marks[i]));
            grades_with_rarity.put(subjects[i], g);
        }
        Stream<Map.Entry<String, Map.Entry<Float, Float>>> sorted_grades2 =
            grades_with_rarity.entrySet().stream().sorted(
                (a, b) -> {
                    return (int)((b.getValue().getValue() - a.getValue().getValue()) * 10.0);
                });
        //sorted_grades2.forEach(e -> System.out.println(e.getKey() + ": " + e.getValue().getKey() + " -> Rarity:" + e.getValue().getValue()));
        saveGradesByRarity(sorted_grades2, "sorted_by_rarity.txt");
        // 1.3
        Stream<Map.Entry<String, Float>> data = readFile("sorted_by_grade.txt");
        System.out.println("Z pliku: ");
        Float sr = 0.0f;
        Long cnt = 0L;
        data.forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });

    }
}
