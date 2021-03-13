/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 *
 * @author stasbutuzov
 * @version Mar 13, 2021
 */

public class Part1 {
    public static int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 3);
            }
        }
        return dna.length();
    }

    public static void testFindStopCodon() {
        System.out.println("\nTest FindStopCodon method.");
        String dna = "AAATATGAAATAGTAATTTTGATTTTTT";
        System.out.println("Testing string of DNA: " + dna);

        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            System.out.println("No start codon");
        } else {
            System.out.println("Start codon ATG has index: " + startIndex);
        }

        int stopIndex = findStopCodon(dna, startIndex, "TAA");
        if (stopIndex == dna.length()) {
            System.out.println("No stop codon");
        } else {
            System.out.println("Stop codon TAA has index: " + stopIndex);
        }

        stopIndex = findStopCodon(dna, startIndex, "TAG");
        if (stopIndex == dna.length()) {
            System.out.println("No stop codon");
        } else {
            System.out.println("Stop codon TAG has index: " + stopIndex);
        }

        stopIndex = findStopCodon(dna, startIndex, "TGA");
        if (stopIndex == dna.length()) {
            System.out.println("No stop codon");
        } else {
            System.out.println("Stop codon TGA has index: " + stopIndex);
        }
    }

    public static String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = Math.min(Math.min(taaIndex, tgaIndex), tagIndex);
        if (minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public static void testFindGene() {
        System.out.println("\nTest FindGene method.");
        String dna = "AAATATCAAATAGTAAATAA";
        System.out.println("Testing string of DNA: " + dna);
        System.out.println("Valid gene is " + findGene(dna));
        System.out.println("–––");

        dna = "AAATATGAAATAGTGGTTTTGGAAAGT";
        System.out.println("Testing string of DNA: " + dna);
        System.out.println("Valid gene is " + findGene(dna));
        System.out.println("–––");

        dna = "AAATATGAAATGATAATTTTGATAGAAATAG";
        System.out.println("Testing string of DNA: " + dna);
        System.out.println("Valid gene is " + findGene(dna));
        System.out.println("–––");

        dna = "AAATATGTTTGTATGTGTAAAATTG";
        System.out.println("Testing string of DNA: " + dna);
        System.out.println("Valid gene is " + findGene(dna));
        System.out.println("–––");

        dna = "xxxATGxxxTAAxxxATGxxxTAGxxxTGA";
        System.out.println("Testing string of DNA: " + dna);
        System.out.println("Valid gene is " + findGene(dna));
    }

    public static void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currGene = findGene(dna);
            if (currGene.isEmpty()) {
                break;
            }
            System.out.println(currGene);
            dna = dna.substring(startIndex + currGene.length());
        }
    }

    public static void testPrintAllGenes() {
        System.out.println("\nTest PrintAllGenes method.");
        String dna = "xxxATGxxxTAAxxxATGxxxTAGxxxTGA";
        System.out.println("Found genes:");
        printAllGenes(dna);
    }

    public static void main(String[] args) {
        Part1 test = new Part1();
        test.testFindStopCodon();
        test.testFindGene();
        test.testPrintAllGenes();
    }
}