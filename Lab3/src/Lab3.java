import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Lab3 {
    public static void main(String[] args) {
        try {
            System.out.println("------ TASK 1 ------");
            System.out.println("\nWorking with latin letters: ");
            task1("src/task1English.txt");
            System.out.println("\nWorking with cyrillic letters: ");
            task1("src/task1Ukrainian.txt");
            System.out.println("\n\n\n");
            System.out.println("------ TASK 2 -------");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter text: ");

            task2(scanner.nextLine());
        } catch(Exception exception) {
            System.out.println(exception.toString());
        }
    }

    private static void task1(String filename) throws IOException {
        var file = readDataFromFile(filename);
        var tokensByLine = file.stream()
                .map(Lab3::parseTokensFromString)
                .collect(Collectors.toList());

        var tokensBySentence = rearrangeTokensToFormListOfSentences(tokensByLine);
        var sentences = joinTokens(tokensBySentence);
        System.out.println("\n---File contains: ");
        sentences.forEach(System.out::println);
        var allDates = new ArrayList<Date>();
        var allDateTokens = new ArrayList<String>();
        sentences.forEach(s -> {
            var datesInSentence = findDate(s, allDateTokens);
            if (!datesInSentence.isEmpty()) {
                sentences.set(sentences.indexOf(s), s.toUpperCase());
            }
            allDates.addAll(datesInSentence);
        });
        var minDate = Collections.min(allDates);
        var maxDate = Collections.max(allDates);
        var middleDate = getMiddleDate(minDate, maxDate);
        var formatter = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("\nDates are in range [" + formatter.format(minDate) + ";" +

                formatter.format(maxDate) + "]");

        System.out.println("Mid range data: " + formatter.format(middleDate));
        var sentencesWithChangedDate = new ArrayList<String>();
        sentences.forEach(s -> allDateTokens.forEach(t -> {
            var newString = s.replaceAll(t, formatter.format(middleDate));
            if (!sentencesWithChangedDate.contains(newString)) {
                if (!newString.equals(s)) {
                    sentencesWithChangedDate.add(newString);
                }
                else if (sentences.contains(s)) {
                    if (findDate(newString, new ArrayList<>()).isEmpty()) {
                        sentencesWithChangedDate.add(newString);
                    }
                }
            }
        }));
        System.out.println("\n---All dates was changed to mid range date and all sentences with dates are upper-case: ");

        sentencesWithChangedDate.forEach(System.out::println);
    }

    private static List<String> readDataFromFile(String filename) throws IOException {
        var list = new ArrayList<String>();
        var file = new File(filename);
        var scanner = new Scanner(new FileReader(file));
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        return list;
    }
    private static List<String> parseTokensFromString(String line) {
        var scanner = new Scanner(line);
        var tokenList = new ArrayList<String>();
        while(scanner.hasNext()) {
            tokenList.add(scanner.next());
        }
        return tokenList;
    }
    private static List<List<String>> rearrangeTokensToFormListOfSentences(List<List<String>> tokens) {
        var sentences = new ArrayList<List<String>>();
        var allTokens = tokens.stream().flatMap(List::stream).collect(Collectors.toList());
        var queue = new LinkedList<String>();
        var sentenceEnders = Arrays.asList(".", "!", "?", "!?");
        sentenceEnders.forEach(suffix -> {
            for (var token: allTokens) {
                queue.add(token);
                if (token.endsWith(suffix)) {
                    sentences.add(new ArrayList<>(queue));

                    queue.clear();
                }
            }
        });

        return sentences;
    }
    private static List<String> joinTokens(List<List<String>> tokens) {
        var list = new ArrayList<String>();

        for (var sentence: tokens) {
            list.add(String.join(" ", sentence));
        }

        return list;
    }
    private static List<Date> findDate(String sentence, List<String> dateTokens) {
        var dateList = new ArrayList<Date>();
        var tokens = parseTokensFromString(sentence);
        var formatters = new ArrayList<SimpleDateFormat>();
        formatters.add(new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH));
        formatters.add(new SimpleDateFormat("dd-MM-yyyy"));
        formatters.add(new SimpleDateFormat("dd/MM/yyyy"));
        formatters.add(new SimpleDateFormat("dd.MM.yyyy"));
        formatters.forEach(formatter -> tokens.forEach(x -> {
            try {
                var date = formatter.parse(x);
                dateList.add(date);
                dateTokens.add(formatter.format(date).toUpperCase());
            } catch (ParseException ignored) {}
        }));

        return dateList;
    }

    private static Date getMiddleDate(Date from, Date to) {
        return new Date((from.getTime() + to.getTime())/2);
    }

    public static String task2(String data) {
        System.out.println("All last letters of each word are now capitalized: ");
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(data);
        var newString = data;

        while (matcher.find()) {
            var word = matcher.group();
            String newWord = word.substring(0, word.length() - 1)
                    + word.substring((word.length()-1)).toUpperCase();
            newString = newString.replaceAll("\\b" + word + "\\b", newWord);
        }

        System.out.println(newString);

        return newString;
    }
}

