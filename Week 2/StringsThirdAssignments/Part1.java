import edu.duke.*;

/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 *
 * @author stasbutuzov
 * @version Mar 18, 2021
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

    public static StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        while (true) {
            String currGene = findGene(dna);
            if (currGene.isEmpty()) {
                break;
            }
            geneList.add(currGene);
            dna = dna.substring(dna.indexOf(currGene) + currGene.length());
        }
        return geneList;
    }

    public static void testGetAllGenes() {
        System.out.println("\nTest getAllGenes method.");
        String dna = "xxxATGxxxTAAxxxATGxxxTAGxxxTGA";
        System.out.println("Testing string of DNA: " + dna + " \nFound genes:");
        StorageResource genes = getAllGenes(dna);
        for (String g : genes.data()) {
            System.out.println(g);
        }
        System.out.println("–––");

        dna = "AAATATGAAATGATAATTTTGATAGAAATAG";
        System.out.println("Testing string of DNA: " + dna + " \nFound genes:");
        genes = getAllGenes(dna);
        for (String g : genes.data()) {
            System.out.println(g);
        }
    }

    public static void main(String[] args) {
        Part1 test = new Part1();
        test.testGetAllGenes();
    }
}