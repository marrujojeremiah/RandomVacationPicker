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

        displayWelcomeMessage();


        promptEnterKey();


        List<String> cities = new ArrayList<>();


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

        Random rand = new Random();

        String[] selectedDestinations = new String[3];


        for (int i = 0; i < 3; i++) {
            int randomIndex;
            String randomCity;


            do {
                randomIndex = rand.nextInt(cities.size());
                randomCity = cities.get(randomIndex);
            } while (isAlreadyPicked(selectedDestinations, randomCity));

            selectedDestinations[i] = randomCity;
        }


        System.out.println("Your top 3 vacation destinations:");
        for (int i = 0; i < selectedDestinations.length; i++) {
            System.out.println((i + 1) + ". " + selectedDestinations[i]);
        }


    }


    private static void displayWelcomeMessage() {
        System.out.println("Welcome! Having trouble picking a vacation destination? Let me help!");
        System.out.println("Pick a number between 1 & 3. Then run the program and whatever number you picked is where you HAVE to go!");
    }


    private static void promptEnterKey() {
        System.out.println("Press Enter to see the top 3 vacation destinations...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }


    private static boolean isAlreadyPicked(String[] pickedCities, String city) {
        for (String pickedCity : pickedCities) {
            if (city != null && city.equals(pickedCity)) {
                return true;
            }
        }
        return false;
    }
}
