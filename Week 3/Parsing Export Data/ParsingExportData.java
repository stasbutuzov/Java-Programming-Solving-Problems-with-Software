import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 *
 * @author stasbutuzov
 * @version Mar 26, 2021
 */

public class ParsingExportData {
    public static String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String countryRecord = record.get("Country");
            if (countryRecord.contains(country)) {
                return record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "Country " + country + ": NOT FOUND";
    }

    public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    public static int numberOfExporters(CSVParser parser, String exportItem) {
        int number = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                number += 1;
            }
        }
        return number;
    }

    public static void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }

    public static void tester() {
        System.out.println("Testing 'Parsing Export Data'.");
        System.out.println("––––––––––––––––––––––––––––––");
        FileResource fr = new FileResource();
        
        System.out.println("Test countryInfo method");
        CSVParser parser = fr.getCSVParser();
        String country = "Russia";
        System.out.println(countryInfo(parser, country));
        System.out.println("––––––––––––––––––––––––––––––");
        
        System.out.println("Test listExportersTwoProducts method");
        parser = fr.getCSVParser();
        String product1 = "gold";
        String product2 = "diamonds";
        System.out.println("Countries that export " + "'" + product1 + "'" + " and " + "'" + product2 + "'" + ":");
        listExportersTwoProducts(parser, product1, product2);
        System.out.println("––––––––––––––––––––––––––––––");
        
        System.out.println("Test numberOfExporters method");
        parser = fr.getCSVParser();
        String exportItem = "gold";
        System.out.println("Number of countries that export " + "'" + exportItem + "'" + ": " + numberOfExporters(parser, exportItem));
        System.out.println("––––––––––––––––––––––––––––––");
        
        System.out.println("Test bigExporters method");
        parser = fr.getCSVParser();
        String valueAmount = "$400,000,000";
        System.out.println("Countries with export value more than " + valueAmount + ":");
        bigExporters(parser, valueAmount);
    }

    public static void main(String[] args) {
        ParsingExportData test = new ParsingExportData();
        test.tester();
    }
}