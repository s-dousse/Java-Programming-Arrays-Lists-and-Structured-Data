package org.coursera;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordFrequenciesTest {
    WordFrequencies wordFreqs;

    @Nested
    class getSanitisedList {
        @DisplayName("Sanitizes one line")
        @Test
            void getSanitisedListTestOneLine() {
                wordFreqs = new WordFrequencies("src/test/java/org/coursera/resources/testwordfreqs.txt");
                List<String> actualList = wordFreqs.getSanitisedList();
                List<String> expectedList = Arrays.asList("this", "is", "a", "test", "yes", "a", "test", "of", "a", "test", "test");

                for (int i = 0; i < expectedList.size(); i++) {
                    assertEquals(expectedList.get(i), actualList.get(i));
                }
            }

        @DisplayName("Sanitizes small paragraph")
        @Test
        void getSanitisedListTestMultipleLines() {
            wordFreqs = new WordFrequencies("src/test/java/org/coursera/resources/macbethSmall.txt");
            List<String> actualList =  wordFreqs.getSanitisedList();

            String str = null;
            try {
                str = Files.readString(Paths.get("src/test/java/org/coursera/resources/macbethResult.txt"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            List<String> expectedList = Arrays.asList(str.split("\\W+"));

            for (int i = 0; i < expectedList.size(); i++) {
                assertEquals(expectedList.get(i), actualList.get(i));
            }
        }
    }

    @Nested
    class findUnique {
        @DisplayName("Find unique words in one line")
        @Test
        void findUniqueTestOneLine() {
            wordFreqs = new WordFrequencies("src/test/java/org/coursera/resources/testwordfreqs.txt");
            wordFreqs.findUnique();
            assertEquals(6, wordFreqs.getMyWords().size());
        }

        @DisplayName("Find unique words in small paragraph")
        @Test
        void findUniqueTestSmallParagraph() {
            wordFreqs = new WordFrequencies("src/test/java/org/coursera/resources/macbethSmall.txt");
            wordFreqs.findUnique();
            assertEquals(82, wordFreqs.getMyWords().size());
        }
    }
}
