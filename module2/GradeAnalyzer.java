import java.io.*;
import java.util.ArrayList;

public class GradeAnalyzer {

    public static void main(String[] args) {
        // Step 1: read scores from file
        ArrayList<Integer> scores = readScores("scores.txt");
        // Step 2: calculate statistics
        double avg = calculateAverage(scores);
        // Step 3: write and print report

        int high = Integer.MIN_VALUE; // starts lower than any possible score;
        int low = Integer.MAX_VALUE;

        for (int score: scores) {
            if (score > high) high = score;
            if (score < low) low = score;
        }

        writeReport(scores, avg, high, low, "report.txt");

    }

    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        // your code here
        ArrayList<Integer> scores = new ArrayList<>();

        // FileReader is necessary to read the file character by character, but it is painfully slow
        // BufferedReader helps us read chunks of text at a time
        // We feed FileReader to BufferedReader so it can read the file itself chunks at a time
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            // While the reader is not reading a blank line
            while ((line = reader.readLine()) != null){
                // We trim the white space before and after the scores
                line = line.trim();

                // After the trim if the line is empty, exit this iteration of the while loop and start the next while loop with the next line
                if (line.isEmpty()){
                    continue;
                }

                try {
                    int score = Integer.parseInt(line);
                    scores.add(score);
                } catch (NumberFormatException e) {
                    // Warn against invalid formats instead of crashing
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("could not read file: " + filename);
        }
        return scores;
    }

    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        // your code here
        // Block against if the scores is a blank list
        if (scores.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        // For every entry in scores, we add them up
        for (int score: scores) {
            total += score;
        }

        return total / scores.size();
    }

    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile) {
        // Block against if there are no valid scores, otherwise high and low would print weird values
        if (scores.isEmpty()) {
            String message = "Grade Analysis Report\n"
                           + "No valid scores found. Nothing to report.\n";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(message);
            } catch (IOException e) {
                System.out.println("Could not write file: " + outputFile);
            }
            System.out.print(message);
            return;   // stop here since there is nothing else to add up
        }

        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        for (int score:scores) {
            if (score >= 90) countA++;
            else if (score >= 80) countB++;
            else if (score >= 70) countC++;
            else if (score >= 60) countD++;
            else countF++;
        }
        // Round the average to 2 decimals by multiplying by 100, rounding to a whole number, then dividing back
        double roundedAvg = Math.round(avg * 100.0) / 100.0;

        // Build the report as one big string, we glue the text and the numbers together with +
        String report = "";
        report += "Grade Analysis Report\n";
        report += "Total scores processed: " + scores.size() + "\n";
        report += "Average score: " + roundedAvg + "\n";
        report += "Highest score: " + high + "\n";
        report += "Lowest score: " + low + "\n";
        report += "\nGrade distribution:\n";
        report += "  A (90-100): " + countA + "\n";
        report += "  B (80-89): " + countB + "\n";
        report += "  C (70-79): " + countC + "\n";
        report += "  D (60-69): " + countD + "\n";
        report += "  F (below 60): " + countF + "\n";

        // We write the report to the output file, the try-with-resources closes it for us when done
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(report);
        } catch (IOException e) {
            System.out.println("Could not write file: " + outputFile);
        }

        // We print the same report to the terminal too
        System.out.print(report);
    }
}
