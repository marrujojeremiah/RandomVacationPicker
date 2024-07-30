import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class VacationPicker {
    public static void main(String[] args) {
        List<String> cities = new ArrayList<>();

        // Read CSV file and populate cities list
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

        // Create a Random object to generate random numbers
        Random rand = new Random();
        // Array to hold the selected vacation destinations
        String[] selectedDestinations = new String[3];

        // Loop to pick 3 unique destinations
        for (int i = 0; i < 3; i++) {
            int randomIndex;
            String randomCity;

            // Ensure the same city is not picked more than once
            do {
                randomIndex = rand.nextInt(cities.size());
                randomCity = cities.get(randomIndex);
            } while (isAlreadyPicked(selectedDestinations, randomCity));

            selectedDestinations[i] = randomCity;
        }

        // Print out the top 3 vacation destinations
        System.out.println("Top 3 vacation destinations:");
        for (int i = 0; i < selectedDestinations.length; i++) {
            System.out.println((i + 1) + ". " + selectedDestinations[i]);
        }
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
}
