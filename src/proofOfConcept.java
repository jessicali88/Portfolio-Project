import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Proof of concept for a fitness tracker component that tracks the weight
 * progression of weight lifting exercises.
 *
 * @author Jessica Li
 *
 */
public class proofOfConcept {

    /**
     * Returns the PR maximum weight in {@code this}.
     *
     * @return integer that is the maximum value in the {@code this}
     * @requires <pre>
     * this is not empty
     * </pre>
     * @ensures this = #this
     */
    public int findPR() {
        int max = 0;
        FitnessTracker copy = this.newInstance();
        copy.transferFrom(this);
        while (this.length() != 0) {
            int temp = this.removeLast();
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        // SimpleReader in = new SimpleReader1L();
        // SimpleWriter out = new SimpleWriter1L();

        // //get and read input file
        // out.print("Please put the name of the exercise: ");
        // String exerciseName = in.nextLine();

        // //create output folder
        // out.print("Please put what weight you lifted: ");
        // int weight = in.nextInteger();

        // //initialize map
        // Map<String, Integer> wordMap = new Map1L<String, Integer>();

        //create deadlift object
        FitnessTracker deadlift = new FitnessTracker1L();

        //add weights to the deadlift collection
        deadlift.addWeight(105);
        deadlift.addWeight(115);
        deadlift.addWeight(135);
        deadlift.addWeight(125);
        //find the pr for deadlifts (135)
        int deadliftPR = deadlift.findPR();
        //find the first entry in the deadlift collection (105)
        int deadliftFirst = deadlift.findFirst();
        //find the current progress for deadlifts (125 - 105)
        int deadliftProgress = deadlift.getCurrentProgress();

    }
}

/**
 * Counts the number of each word in a inputed text file. Outputs a table in an
 * HTML file with words listed in alphabetical order and their respective
 * counts. Case insensitive order.
 *
 * @author Jessica Li
 *
 */
public final class WordCounter {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private WordCounter() {
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code startPos}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code startPos}
     * @requires 0 <= startPos < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[startPos, startPos + |nextWordOrSeparator|)  and
     * if entries(text[startPos, startPos + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (startPos + |nextWordOrSeparator| = |text|  or
     *    entries(text[startPos, startPos + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (startPos + |nextWordOrSeparator| = |text|  or
     *    entries(text[startPos, startPos + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int i = position;
        //if it is a separator
        boolean isSeparator = separators.contains(text.charAt(i));
        //while you haven't reached the end, check that it's the same type as before
        while (i < text.length()
                && separators.contains(text.charAt(i)) == isSeparator) {
            i++;
        }
        //return the substring of where you started until the type changes
        return text.substring(position, i);
    }

    /**
     * Inputs a text file and stores each unique word in the given {@code Map}.
     *
     * @param fileName
     *            the name of the input file
     * @param wordMap
     *            map with the words as the keys and the counts as the values
     * @replaces termMap
     * @requires <pre>
     * [file named fileName exists but is not open]
     * </pre>
     * @ensures [wordMap contains word -> count mapping from file fileName]
     */
    public static void getWordMap(String fileName,
            Map<String, Integer> wordMap) {
        assert fileName != null : "Violation of: fileName is not null";
        assert wordMap != null : "Violation of: wordMap is not null";

        //create separator set; words are separated by spaces and punctuation
        Set<Character> separatorSet = new Set1L<>();
        separatorSet.add(' ');
        separatorSet.add('.');
        separatorSet.add(',');
        separatorSet.add('-');
        separatorSet.add(';');
        separatorSet.add(':');
        separatorSet.add('?');
        separatorSet.add('!');

        //read in file
        SimpleReader in = new SimpleReader1L(fileName);

        int position = 0;
        int count = 0;
        //read the entire file to create all keys
        while (!in.atEOS()) {
            //read line by line
            String line = in.nextLine();
            while (position < line.length()) {
                String wordOrSep = nextWordOrSeparator(line, position,
                        separatorSet);
                //if it's a word (no separators), and not already in, add to map
                if (!separatorSet.contains(wordOrSep.charAt(0))
                        && !wordMap.hasKey(wordOrSep)) {
                    wordMap.add(wordOrSep, count);
                }
                //increment to get to the next word
                position += wordOrSep.length();
            }
            //reset to beginning of line
            position = 0;
        }

        in.close();
    }

    /**
     * Inputs a text file and updates the given {@code Map} with counts.
     *
     * @param fileName
     *            the name of the input file
     * @param wordMap
     *            map with the words as the keys and the counts as the values
     * @replaces termMap
     * @requires <pre>
     * [file named fileName exists but is not open]
     * </pre>
     * @ensures [values of the map are the number of occurrences for each word]
     */
    public static void countWords(String fileName,
            Map<String, Integer> wordMap) {
        assert fileName != null : "Violation of: fileName is not null";
        assert wordMap != null : "Violation of: termMap is not null";

        //create separators set
        Set<Character> separatorSet = new Set1L<>();
        separatorSet.add(' ');
        separatorSet.add('.');
        separatorSet.add(',');
        separatorSet.add('-');
        separatorSet.add(';');
        separatorSet.add(':');
        separatorSet.add('?');
        separatorSet.add('!');

        //read in file
        SimpleReader in = new SimpleReader1L(fileName);
        int position = 0;
        while (!in.atEOS()) {
            //read line by line
            String line = in.nextLine();
            while (position < line.length()) {
                String wordOrSep = nextWordOrSeparator(line, position,
                        separatorSet);
                //match word to existing key and increment count
                if (wordMap.hasKey(wordOrSep)) {
                    wordMap.replaceValue(wordOrSep,
                            wordMap.value(wordOrSep) + 1);
                }
                //increment to get to the next word
                position += wordOrSep.length();
            }
            //reset to beginning of line
            position = 0;
        }

        in.close();
    }

    /**
     * Creates HTML output of index which contains table of all words and counts
     * in alphabetical order.
     *
     * @param wordMap
     *            map containing words and counts
     * @param outputFileName
     *            the {@code String} of the name of the output file
     * @param inputFileName
     *            the {@code String} of the name of the input file to read
     * @requires <pre>
     * wordMap, file names are not empty and
     * </pre>
     * @ensures a web page of a table of words sorted in alphabetical order with
     *          their corresponding counts. The title includes the input file
     *          name.
     */
    public static void outputFile(Map<String, Integer> wordMap,
            String outputFileName, String inputFileName) {
        assert wordMap != null : "Violation of: termMap is not null";

        //create writer to print to index in folder
        SimpleWriter printToOutFile = new SimpleWriter1L(outputFileName);
        //create opening tags for HTML file
        printToOutFile.println("<html>");
        printToOutFile.println("<head>");
        printToOutFile.println(
                "<title>Words Counted in " + inputFileName + "</title>");
        printToOutFile.println("</head>");
        printToOutFile.println("<body>");
        printToOutFile
                .println("<h2>Words Counted in " + inputFileName + "</h2>");
        printToOutFile.println("<hr/>");
        printToOutFile.println("<main>");
        printToOutFile.println("<table border=1>");
        printToOutFile.println("<tbody>");
        printToOutFile.println("<tr>");
        printToOutFile.println("<th>Words</th>");
        printToOutFile.println("<th>Counts</th>");
        printToOutFile.println("</tr>");

        //create queue of sorted words
        Queue<String> sortedQ = new Queue1L<>();
        for (Map.Pair<String, Integer> pair : wordMap) {
            sortedQ.enqueue(pair.key());
        }
        sortedQ.sort(String.CASE_INSENSITIVE_ORDER);

        //iterate through each term in queue (already in alphabetical order)
        while (sortedQ.length() > 0) {
            for (Map.Pair<String, Integer> pair : wordMap) {
                //match the next alphabetical word to the map and output the pair
                if (pair.key().equals(sortedQ.front())) {
                    printToOutFile.println("<tr>");
                    printToOutFile.println("<td>" + pair.key() + "</td>");
                    printToOutFile.println("<td>" + pair.value() + "</td>");
                    printToOutFile.println("</tr>");
                }
            }
            //remove the first term (move on to next in alphabetical order)
            sortedQ.dequeue();

        }

        //closing tags
        printToOutFile.println("</tbody>");
        printToOutFile.println("</table>");
        printToOutFile.println("</main>");
        printToOutFile.println("</body>");
        printToOutFile.println("</html>");
        printToOutFile.close();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //get and read input file
        out.print("Please name the input file: ");
        String inputFileName = in.nextLine();

        //create output folder
        out.print("Please name the output file: ");
        String outputFileName = in.nextLine();

        //initialize map
        Map<String, Integer> wordMap = new Map1L<String, Integer>();
        //create a map with all the words in the file
        getWordMap(inputFileName, wordMap);
        //update map with counts of words
        countWords(inputFileName, wordMap);

        //output file with table
        outputFile(wordMap, outputFileName, inputFileName);

        //close streams
        in.close();
        out.close();
    }

}

//test
//input file: https://cse22x1.engineering.osu.edu/2231/web-sw2/assignments/projects/count-words/data/gettysburg.txt
//output: gettysburg.html
