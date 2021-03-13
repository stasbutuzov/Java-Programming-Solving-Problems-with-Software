/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 *
 * @author stasbutuzov
 * @version Mar 13, 2021
 */

public class Part3 {
    public static boolean twoOccurrences(String stringa, String stringb) {
        int first = stringb.indexOf(stringa);
        int second = stringb.indexOf(stringa, first + 1);
        if (second != -1) {
            return true;
        } else {
            return false;
        }
    }

    public static void testing() {
        System.out.println("Test twoOccurrences method:\nTested strings: A story by Abby Long and by");
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println("Tested strings: banana and an");
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println("Tested strings: atttaggatagt and gta");
        System.out.println(twoOccurrences("gta", "atttaggatagt"));
        System.out.println("\nTest lastPart method:\nTested strings: banana and an");
        System.out.println(lastPart("an", "banana"));
        System.out.println("Tested strings: zoo and forest");
        System.out.println(lastPart("zoo", "forest"));
    }

    public static String lastPart(String stringa, String stringb) {
        int first = stringb.indexOf(stringa);
        if (first != -1) {
            String result = stringb.substring(first + stringa.length());
            return result;
        }
        return stringb;
    }

    public static void main(String[] args) {
        Part3 test = new Part3();
        test.testing();
    }
}