package com.napier.sem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class MarkdownGenerator {
    private static final String REPORTS_DIRECTORY = "/tmp/reports/";
    public static void generateLanguageSpeakersReport(Map<String, Integer> languageSpeakers) {
        if (languageSpeakers == null || languageSpeakers.isEmpty()) {
            System.out.println("No language speakers data available.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("# Language Speakers Report\n\n");
        sb.append("| Language | Number of Speakers |\n");
        sb.append("| -------- | ------------------ |\n");
        for (Map.Entry<String, Integer> entry : languageSpeakers.entrySet()) {
            sb.append("| ").append(entry.getKey()).append(" | ").append(entry.getValue()).append(" |\n");
        }

        saveReportToFile(sb.toString(), "Language_Speakers_Report.md");
    }

    private static void saveReportToFile(String content, String filename) {
        try {

            File directory = new File(REPORTS_DIRECTORY);
            // Check if the directory exists
            if (!directory.exists()) {
                // If not, create directory
                directory.mkdirs();
            }

            // Create a BufferedWriter object to write to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(REPORTS_DIRECTORY + filename)));
            writer.write(content);
            writer.close();
            System.out.println("\n\nReport generated: " + REPORTS_DIRECTORY + filename);
        } catch (IOException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
}
