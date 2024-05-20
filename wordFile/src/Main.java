import java.io.*;
import java.util.*;

//основная реализация
public class Main {
    public static void main(String[] args) {
        String readFile = "text.txt";

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(readFile))))
        {
            Map<String, Integer> wordCount = new HashMap<>();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                for (String word : line.toLowerCase().split("[ ,.\\n\\r\\t]+")) {
                    if(!word.isEmpty() && word.length()>1) {
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
//                        System.out.println(word + ": " + wordCount.get(word));
                    }
                }
            }

            List<String> sortedWords = new ArrayList<>(wordCount.keySet());
            Collections.sort(sortedWords);

            System.out.println("Sorted list of words alphabetically <word : count_repeat>:");
            for (String word : sortedWords) {
                System.out.println(word + ": " + wordCount.get(word));
            }

            int maxValueOfWords = 0;
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                if (entry.getValue() > maxValueOfWords) {
                    maxValueOfWords = entry.getValue();
                }
            }

            for (String word : wordCount.keySet()) {
                if (wordCount.get(word) == maxValueOfWords) {
                    System.out.println("Word with max repeat value is>> "+word + ": " + maxValueOfWords);
                }
            }

//            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
//                if (entry.getValue() == maxValueOfWords) {
//                    System.out.println("Word with max repeat value is>> "+entry.getKey() + ": " + maxValueOfWords);
//                }
//            }

        } catch (IOException e) {
            System.out.println("Что-то пошло не так при чтении файла");
        }

    }

}