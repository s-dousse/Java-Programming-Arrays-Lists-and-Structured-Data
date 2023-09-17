package org.coursera;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<String> myFrequencies;

    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFrequencies = new ArrayList<>();
    }

    private void findUnique() {
        myWords.clear();
        myFrequencies.clear();

        // String fileName = "src/main/java/org/coursera/resources/macbethSmall.txt";
        // list = getListOfWord(fileName);
        // do stuff with list
    }

    public List<String> getSanitisedList(String fileName) {
        List<String> list = new ArrayList<>();
        StringBuilder allWords = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            //1. remove punctuation
            //2. convert all content to lower case
            //3. convert it into a List
            list = stream
                    .map(str -> str.replaceAll("\\p{Punct}", ""))
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {}
}
