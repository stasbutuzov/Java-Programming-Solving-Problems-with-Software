/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 *
 * @author stasbutuzov
 * @version Mar 13, 2021
 */

public class Part2 {
    public static String findSimpleGene(String dna, String startCodon, String stopCodon) {
        int startIndex = dna.toUpperCase().indexOf(startCodon);
        int stopIndex = dna.toUpperCase().indexOf(stopCodon, startIndex + 3);
        if ((startIndex == -1) || (stopIndex == -1)) {
            return "";
        }
        if ((stopIndex - startIndex) % 3 == 0) {
            return dna.substring(startIndex, stopIndex + 3);
        }
        return "";
    }

    public static void testSimpleGene() {
        String startCodon = "ATG";
        String stopCodon = "TAA";
        System.out.println("1st gene: " + findSimpleGene("ATGGGTTAAGTC", startCodon, stopCodon));
        System.out.println("2nd gene: " + findSimpleGene("AATGSGTAGTTASTCG", startCodon, stopCodon));
        System.out.println("3rd gene: " + findSimpleGene("AATTSGTAGTTASTCG", startCodon, stopCodon));
        System.out.println("4th gene: " + findSimpleGene("aatgsggtaatcgttaatcg", startCodon, stopCodon));
        System.out.println("5th gene: " + findSimpleGene("AATGSGTAATCGTTAGTCG", startCodon, stopCodon));
    }

    public static void main(String[] args) {
        Part2 test = new Part2();
        test.testSimpleGene();
    }
}