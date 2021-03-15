/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 *
 * @author stasbutuzov
 * @version Mar 15, 2021
 */

public class Part2 {
    public static int howMany(String stringa, String stringb) {
        int index = 0;
        int count = 0;
        while (index != -1) {
            index = stringb.indexOf(stringa, index);
            if (index != -1) {
                count += 1;
                index += stringa.length();
            }
        }
        return count;
    }

    public static void testHowMany() {
        System.out.println("Test howMany method.");
        String a = "GAA";
        String b = "ATGAACGAATTGAATC";
        System.out.println("stringa is: " + a + " \nstringb is: " + b);
        System.out.println("The stringa occurs " + howMany(a, b) + " times in stringb.");
        System.out.println("–––");

        a = "ATG";
        b = "AAATATCAAATAGTAAATAA";
        System.out.println("stringa is: " + a + " \nstringb is: " + b);
        System.out.println("The stringa occurs " + howMany(a, b) + " times in stringb.");
        System.out.println("–––");

        a = "GTA";
        b = "xxxGTAxxxTAAxxxGTAxxxTAGxxxTGA";
        System.out.println("stringa is: " + a + " \nstringb is: " + b);
        System.out.println("The stringa occurs " + howMany(a, b) + " times in stringb.");
    }

    public static void main(String[] args) {
        Part2 test = new Part2();
        test.testHowMany();
    }
}