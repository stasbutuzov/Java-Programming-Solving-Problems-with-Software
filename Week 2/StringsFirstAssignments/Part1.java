/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 *
 * @author stasbutuzov
 * @version Mar 13, 2021
 */

public class Part1 {
    public static String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if ((startIndex == -1) || (stopIndex == -1)) {
            return "";
        }
        if ((stopIndex - startIndex) % 3 == 0) {
            return dna.substring(startIndex, stopIndex + 3);
        }
        return "";
    }

    public static void testSimpleGene() {
        System.out.println("1st gene: " + findSimpleGene("ATGGGTTAAGTC"));
        System.out.println("2nd gene: " + findSimpleGene("AATGSGTAGTTASTCG"));
        System.out.println("3rd gene: " + findSimpleGene("AATTSGTAGTTASTCG"));
        System.out.println("4th gene: " + findSimpleGene("AATGSGGTAATCGTTAATCG"));
        System.out.println("5th gene: " + findSimpleGene("AATGSGTAATCGTTAGTCG"));
    }

    public static void main(String[] args) {
        Part1 test = new Part1();
        test.testSimpleGene();
    }
}
