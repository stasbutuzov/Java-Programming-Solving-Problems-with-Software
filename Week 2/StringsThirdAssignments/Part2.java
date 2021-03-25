/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 *
 * @author stasbutuzov
 * @version Mar 20, 2021
 */

public class Part2 {
    public static int countCodon(String stringa, String stringb) {
        int index = stringb.indexOf(stringa);
        int count = 0;
        while (index != -1) {
            count += 1;
            stringb = stringb.substring(index + stringa.length());
            index = stringb.indexOf(stringa);
        }
        return count;
    }

    public static float cgRatio(String dna) {
        int c_count = countCodon("C", dna);
        int g_count = countCodon("G", dna);
        float cg_ratio = (float) (c_count + g_count) / dna.length();
        return cg_ratio;
    }

    public static void testCgRatio() {
        System.out.println("Test cgRatio method.");
        String dna = "ATGCCATAG";
        System.out.println("Testing string of DNA: " + dna + " \nThe ratio of C’s and G’s in DNA:");
        System.out.printf("%.7f", cgRatio(dna));
        System.out.println("\n–––");

        dna = "ATGGTAGTAGTTGCC";
        System.out.println("Testing string of DNA: " + dna + " \nThe ratio of C’s and G’s in DNA:");
        System.out.printf("%.7f", cgRatio(dna));
        System.out.println("\n–––");

        dna = "CTGCTG";
        System.out.println("Testing string of DNA: " + dna + " \nThe ratio of C’s and G’s in DNA:");
        System.out.printf("%.7f", cgRatio(dna));
        System.out.println("\n–––");
    }

    public static int countCTG(String dna) {
        String codon = "CTG";
        int index = 0;
        int count = 0;
        while (index != -1) {
            index = dna.indexOf(codon, index);
            if (index != -1) {
                count += 1;
                index += codon.length();
            }
        }
        return count;
    }

    public static void testCountCTG() {
        System.out.println("\nTest countCTG method.");
        String dna = "CTGTGACTGGTACTG";
        System.out.println("Testing string of DNA: " + dna + "\nThe codon of CTG occurs " + countCTG(dna) + " times in DNA");
        System.out.println("–––");

        dna = "ATGGTAxxxGTTGCC";
        System.out.println("Testing string of DNA: " + dna + "\nThe codon of CTG occurs " + countCTG(dna) + " times in DNA");
        System.out.println("–––");

        dna = "CTGCTG";
        System.out.println("Testing string of DNA: " + dna + "\nThe codon of CTG occurs " + countCTG(dna) + " times in DNA");
    }

    public static void main(String[] args) {
        Part2 test = new Part2();
        test.testCgRatio();
        test.testCountCTG();
    }
}