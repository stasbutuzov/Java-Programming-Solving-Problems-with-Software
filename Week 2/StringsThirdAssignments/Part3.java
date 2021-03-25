import edu.duke.*;

/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 *
 * @author stasbutuzov
 * @version Mar 25, 2021
 */

public class Part3 {
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

    public static int countGenes(String dna) {
        int count = 0;
        while (true) {
            String currGene = findGene(dna);
            if (currGene.isEmpty()) {
                break;
            }
            count += 1;
            dna = dna.substring(dna.indexOf(currGene) + currGene.length());
        }
        return count;
    }

    public static void processGenes(StorageResource sr) {
        int count_longer = 0;
        for (String g : sr.data()) {
            //if (g.length() > 9) {
            if (g.length() > 60) {
                //System.out.println("The string longer than 9 characters: " + "\n" + g);
                //System.out.println("\nThe string longer than 60 characters: " + "\n" + g);
                count_longer += 1;
            }
        }
        //System.out.println("\nNumber of strings longer than 9 characters: " + count_longer);
        System.out.println("\nNumber of strings longer than 60 characters: " + count_longer);

        int count_cg_ratio = 0;
        for (String g : sr.data()) {
            float cgRatio = cgRatio(g);
            if (cgRatio > 0.35) {
                //System.out.println("\nThe string whose C-G-ratio is higher than 0.35: " + "\n" + g);
                count_cg_ratio += 1;
            }
        }
        System.out.println("\nNumber of strings whose C-G-ratio is higher than 0.35: " + count_cg_ratio);

        String longestGene = "";
        for (String g : sr.data()) {
            if (g.length() > longestGene.length()) {
                longestGene = g;
            }
        }
        System.out.println("\nThe longest gene is: " + "\n" + longestGene + "\nit's length is: " + longestGene.length());
        System.out.println("––––––––––––––––––––––––––––––");
    }

    public static void testProcessGenes() {
        System.out.println("Test processGenes method (with DNA string in external file).");
        FileResource fr = new FileResource("brca1line.fa");
        //FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        System.out.println("The number of genes found in file: " + countGenes(dna));
        System.out.println("\nThe codon of CTG appear " + countCTG(dna) + " times in DNA");
    }

    public static void testProcessGenes2() {
        System.out.println("Test processGenes method (with DNA strings to use as test cases).");
        StorageResource sr = new StorageResource();

        String dna = "AAATATGAAAGTATGTTAGTGGTTTTGGAAAATGGTAXXXGATTTGAAGTAG";
        System.out.println("Testing string of DNA: " + dna);
        processGenes(getAllGenes(dna));

        dna = "AAATATCAAATAGTAAATAA";
        System.out.println("Testing string of DNA: " + dna);
        processGenes(getAllGenes(dna));

        dna = "TAAAAAATGAGTTAGATGCCCGCGAAACATGATTAAAAAATGAAACATGATTAA";
        System.out.println("Testing string of DNA: " + dna);
        processGenes(getAllGenes(dna));

        dna = "AAATATGTTTGTATGTGTAAAATTG";
        System.out.println("Testing string of DNA: " + dna);
        processGenes(getAllGenes(dna));

        dna = "xxxATGxxxTAAxxxATGxxxTAGxxxTGA";
        System.out.println("Testing string of DNA: " + dna);
        processGenes(getAllGenes(dna));
    }

    public static void main(String[] args) {
        Part3 test = new Part3();
        test.testProcessGenes();
        //test.testProcessGenes2();
    }
}