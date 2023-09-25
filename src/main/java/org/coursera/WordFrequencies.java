package org.coursera;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFrequencies;
    private String fileName;

    public WordFrequencies(String fileName) {
        myWords = new ArrayList<>();
        myFrequencies = new ArrayList<>();
        this.fileName = fileName;
    }

    public void findUnique() {
        myWords.clear();
        myFrequencies.clear();
        List<String> list = getSanitisedList();

        for (String word : list) {
            int index = myWords.indexOf(word);
            if ( index == -1) {
                myWords.add(word);
                myFrequencies.add(1);
            } else {
                int wordFreq = myFrequencies.get(index);
                myFrequencies.set(index, wordFreq + 1);
            }
        }
    }

    public List<String> getSanitisedList() {
        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(this.fileName))) {
            // assigns the list variable to the result of the following stream pipeline.
            // 1. remove punctuation
            // 2. convert all content to lower case
            // 3. splits each word in the stream into a list of words, using space character as delimiter
            // 4. flattens the stream of lists of words into a single stream of words = remove nested data structures
            // 5. remove empty strings (need to match lambda expression)
            // 6. remove leading and trailing whitespace
            // 7. collects the stream of strings into a list

            list = stream
                    .map(str -> str.replaceAll("\\p{Punct}", ""))
                    .map(String::toLowerCase)
                    .map(w -> w.split(" "))
                    .flatMap(Arrays::stream)
                    .filter(s -> !s.isEmpty())
                    .map(String::strip)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int findIndexOfMax() {
        int max = myFrequencies.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFrequencies.size(); k++){
            if (myFrequencies.get(k) > max){
                max = myFrequencies.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public List<String> getMyWords() {
        return this.myWords;
    }

    public List<Integer> getMyFreqs() {
        return this.myFrequencies;
    }

    public static void main(String[] args) {}
}
