import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//основная реализация
public class Main_per {
    public static void main(String[] args) {
        String readFile = "text.txt";
        int countWord=0;
        double percentelCount=0.0;

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
                countWord = wordCount.get(word);
                percentelCount = (countWord / (double) wordCount.size()) * 100;
//                System.out.println(word + ": " + wordCount.get(word));
                System.out.println(word + ": " + countWord + " (" + String.format("%.2f", percentelCount) + "%)");
            }

            int maxValueOfWords = 0;
            double maxpercentelCount = 0.0;
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                if (entry.getValue() > maxValueOfWords) {
                    maxValueOfWords = entry.getValue();
                    maxpercentelCount = (maxValueOfWords/ (double) wordCount.size()) * 100;
                }
            }

            for (String word : wordCount.keySet()) {
                if (wordCount.get(word) == maxValueOfWords) {
//                    System.out.println("Word with max repeat value is>> "+word + ": " + maxValueOfWords);
                    System.out.println("Word with max repeat value is>> "+word + ": " + maxValueOfWords + " (" + String.format("%.2f", maxpercentelCount) + "%)");
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