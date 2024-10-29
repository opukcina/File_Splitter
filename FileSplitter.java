package pm;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama file (misalnya: file.txt): ");
        String fileName = scanner.nextLine();

        System.out.print("Masukkan jumlah baris per potongan: ");
        int linesPerChunk = scanner.nextInt();

        Queue<String> fileChunks = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder chunk = new StringBuilder();
            String line;
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                chunk.append(line).append("\n");
                lineCount++;

                if (lineCount == linesPerChunk) {
                    fileChunks.add(chunk.toString());
                    chunk.setLength(0);
                    lineCount = 0;
                }
            }

            if (chunk.length() > 0) {
                fileChunks.add(chunk.toString());
            }

            int partNumber = 1;
            System.out.println("\nPotongan file:");
            while (!fileChunks.isEmpty()) {
                System.out.println("Potongan " + partNumber + ":");
                System.out.println(fileChunks.poll());
                System.out.println("==========");
                partNumber++;
            }

        } catch (IOException e) {
            System.out.println("Error saat membaca file: " + e.getMessage());
        }

        scanner.close();
    }
}

