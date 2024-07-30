import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class VacationPicker {

    public static void main(String[] args) {
        // Display the welcome message
        displayWelcomeMessage();

        // Wait for the user to press Enter
        promptEnterKey();

        // Step 1: Initialize an empty list to store city and state pairs
        List<String> cities = new ArrayList<>();

        // Step 2: Read data from the CSV file and populate the list
        try {
            Reader reader = new FileReader("src/main/resources/us_cities.csv");
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            for (CSVRecord record : csvParser) {
                String city = record.get("City");
                String state = record.get("State");
                cities.add(city + ", " + state);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Step 3: Initialize a Random object to generate random numbers
        Random rand = new Random();
        // Step 4: Array to hold the selected vacation destinations
        String[] selectedDestinations = new String[3];

        // Step 5: Loop to pick 3 unique destinations
        for (int i = 0; i < 3; i++) {
            int randomIndex;
            String randomCity;

            // Step 6: Ensure the same city is not picked more than once
            do {
                randomIndex = rand.nextInt(cities.size());
                randomCity = cities.get(randomIndex);
            } while (isAlreadyPicked(selectedDestinations, randomCity));

            selectedDestinations[i] = randomCity;
        }

        // Step 7: Print out the top 3 vacation destinations
        System.out.println("Your top 3 vacation destinations:");
        for (int i = 0; i < selectedDestinations.length; i++) {
            System.out.println((i + 1) + ". " + selectedDestinations[i]);
        }


    }

    // Method to display the welcome message
    private static void displayWelcomeMessage() {
        System.out.println("Welcome! Having trouble picking a vacation destination? Let me help!");
        System.out.println("Pick a number between 1 & 3. Then run the program and whatever number you picked is where you HAVE to go!");
    }

    // Method to prompt the user to press Enter
    private static void promptEnterKey() {
        System.out.println("Press Enter to see the top 3 vacation destinations...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    // Method to check if a city is already picked
    private static boolean isAlreadyPicked(String[] pickedCities, String city) {
        for (String pickedCity : pickedCities) {
            if (city != null && city.equals(pickedCity)) {
                return true;
            }
        }
        return false;
    }
}
