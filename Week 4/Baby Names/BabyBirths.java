import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 * Small data set: http://www.dukelearntoprogram.com/course2/data/us_babynames_small.zip
 * Full data set: http://www.dukelearntoprogram.com/course2/data/us_babynames.zip
 * </pre>
 *
 * @author stasbutuzov
 * @version Apr 3, 2021
 */

public class BabyBirths {
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        int totalBoysNames = 0;
        int totalGirlsNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames += 1;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoysNames += 1;
            } else {
                totalGirls += numBorn;
                totalGirlsNames += 1;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Girls = " + totalGirls);
        System.out.println("Boys = " + totalBoys);
        System.out.println("Total names = " + totalNames);
        System.out.println("Total boys names = " + totalBoysNames);
        System.out.println("Total girls names = " + totalGirlsNames);
        System.out.println("–––––––––––––––––––––––––");
    }

    public void testTotalBirths() {
        System.out.println("Test totalBirths method");
        FileResource fr = new FileResource("us_babynames_small/yob2012short.csv"); // small dataset
        //FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob1880.csv"); // full dataset
        totalBirths(fr);
    }

    public int getRank(int year, String name, String gender) {
        int rank = 0;
        //FileResource fr = new FileResource("us_babynames_small/yob" + year + "short.csv"); // small dataset
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv"); // full dataset
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank += 1;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }

    public void testGetRank() {
        System.out.println("Test getRank method");
        int year = 2012;
        String name = "Mason";
        String gender = "M";
        System.out.println("Name: " + "'" + name + "'" +
                " \nYear: " + "'" + year + "'" +
                " \nGender: " + "'" + gender + "'" +
                " \nRank: " + getRank(year, name, gender));
        System.out.println("–––––––––––––––––––––––––");
    }

    public String getName(int year, int rank, String gender) {
        int currentRank = 0;
        //FileResource fr = new FileResource("us_babynames_small/yob" + year + "short.csv"); // small dataset
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv"); // full dataset
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                currentRank += 1;
                if (currentRank == rank) {
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }

    public void testGetName() {
        System.out.println("Test getName method");
        int year = 1991;
        int rank = 1;
        String gender = "M";
        System.out.println("Year: " + "'" + year + "'" +
                " \nRank: " + "'" + rank + "'" +
                " \nGender: " + "'" + gender + "'" +
                " \nName: " + getName(year, rank, gender));
        System.out.println("–––––––––––––––––––––––––");
    }

    public String whatIsNameInYear(String name, int year, int newYear, String gender) {
        String nameInYear = "";
        int rank = getRank(year, name, gender);
        return nameInYear = getName(newYear, rank, gender);
    }

    public void testWhatIsNameInYear() {
        System.out.println("Test whatIsNameInYear method");
        String name = "Isabella";
        int year = 2012;
        int newYear = 2014;
        String gender = "F";
        String pronoun = "";
        if (gender == "F") {
            pronoun = "she";
        } else {
            pronoun = "he";
        }
        System.out.println(name +
                " born in " + year +
                " would be " + whatIsNameInYear(name, year, newYear, gender) +
                " if " + pronoun + " was born in " + newYear + ".");
        System.out.println("–––––––––––––––––––––––––");
    }

    public int yearOfHighestRank(String name, String gender) {
        int yearOfHighestRank = -1;
        int highestRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int currentYear = Integer.parseInt(fileName.substring(3, 7));
            int currentRank = getRank(currentYear, name, gender);
            if ((currentRank != -1) && (highestRank == 0)
                    || (currentRank != -1) && (currentRank < highestRank)) {
                highestRank = currentRank;
                yearOfHighestRank = currentYear;
            }
        }
        return yearOfHighestRank;
    }

    public void testYearOfHighestRank() {
        System.out.println("Test yearOfHighestRank method");
        String name = "Mason";
        String gender = "M";
        System.out.println("Name: " + "'" + name + "'" +
                " \nGender: " + "'" + gender + "'" +
                " \nYear of highest rank: " + yearOfHighestRank(name, gender));
        System.out.println("–––––––––––––––––––––––––");
    }

    public double getAverageRank(String name, String gender) {
        double averageRank = 0.0;
        int totalRank = 0;
        int countFiles = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int currentYear = Integer.parseInt(f.getName().substring(3, 7));
            int currentRank = getRank(currentYear, name, gender);
            if (currentRank != -1) {
                countFiles += 1;
                totalRank += currentRank;
            }
        }
        if (totalRank == 0) {
            return -1;
        }
        averageRank = (double) totalRank / countFiles;
        return averageRank;
    }

    public void testGetAverageRank() {
        System.out.println("Test getAverageRank method");
        String name = "Jacob";
        String gender = "M";
        System.out.printf("Name: " + "'" + name + "'" +
                " \nGender: " + "'" + gender + "'" +
                " \nAverage rank: " + "%.4f", getAverageRank(name, gender));
        System.out.println("\n–––––––––––––––––––––––––");
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirth = 0;
        int rank = 0;
        FileResource fr = new FileResource("us_babynames_small/yob" + year + "short.csv"); // small dataset
        //FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv"); // full dataset
        int currentRank = getRank(year, name, gender);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rank += 1;
                if (rank < currentRank) {
                    totalBirth += Integer.parseInt(rec.get(2));
                }
            }
        }
        return totalBirth;
    }

    public void testGetTotalBirthsRankedHigher() {
        System.out.println("Test getTotalBirthsRankedHigher method");
        int year = 2012;
        String name = "Ethan";
        String gender = "M";
        System.out.println("Year: " + "'" + year + "'" +
                " \nName: " + "'" + name + "'" +
                " \nGender: " + "'" + gender + "'" +
                " \nBirths higher: " + getTotalBirthsRankedHigher(year, name, gender));
        System.out.println("–––––––––––––––––––––––––");
    }

    public static void main(String[] args) {
        BabyBirths test = new BabyBirths();
        Scanner in = new Scanner(System.in);
        System.out.println("Choose a method for testing:");
        System.out.println("Put '1' for call 'testTotalBirths'" +
                "\nPut '2' for call 'testGetRank'" +
                "\nPut '3' for call 'testGetName'" +
                "\nPut '4' for call 'testWhatIsNameInYear'" +
                "\nPut '5' for call 'testYearOfHighestRank'" +
                "\nPut '6' for call 'testGetAverageRank'" +
                "\nPut '7' for call 'testGetTotalBirthsRankedHigher'");
        int choice = in.nextInt();
        if (choice == 1) {
            test.testTotalBirths();
        } else if (choice == 2) {
            test.testGetRank();
        } else if (choice == 3) {
            test.testGetName();
        } else if (choice == 4) {
            test.testWhatIsNameInYear();
        } else if (choice == 5) {
            test.testYearOfHighestRank();
        } else if (choice == 6) {
            test.testGetAverageRank();
        } else if (choice == 7) {
            test.testGetTotalBirthsRankedHigher();
        } else {
            System.out.println("Bad input");
        }
    }
}