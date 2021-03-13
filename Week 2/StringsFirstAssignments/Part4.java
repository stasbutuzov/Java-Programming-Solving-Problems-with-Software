import edu.duke.*;

/**
 * Resources: https://www.dukelearntoprogram.com//course2/index.php
 *
 * @author stasbutuzov
 * @version Mar 13, 2021
 */

public class Part4 {
    public static void youtubeLinks() {
        URLResource dukeurl = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String word : dukeurl.lines()) {
            int line = word.toLowerCase().indexOf("youtube.com");
            if (line != -1) {
                int quotestart = word.lastIndexOf("\"", line);
                int quotestop = word.indexOf("\"", line + 1);
                System.out.println(word.substring(quotestart + 1, quotestop));
            }
        }
    }

    public static void main(String[] args) {
        Part4 urls = new Part4();
        urls.youtubeLinks();
    }
}