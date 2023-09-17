package org.coursera;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordFrequenciesTest {
    WordFrequencies wordFreqs;

    @BeforeEach
    public void setUp() {
        wordFreqs = new WordFrequencies();
    }

    @Nested
    class getSanitisedString{
        @DisplayName("Sanitizes one line: removes all punctuation and turn all characters to lowercase")
        @Test
            void getSanitisedListTestOneLine() {
                String fileName = "src/test/java/org/coursera/resources/testwordfreqs.txt";
                assertEquals("this is a test yes a test of a test test", wordFreqs.getSanitisedList(fileName).get(0));
            }

        @DisplayName("Sanitizes small paragraph: removes all punctuation and turn all characters to lowercase")
        @Test
        void getSanitisedListTestMultipleLines() {
            String fileName = "src/main/java/org/coursera/resources/macbethSmall.txt";
            List<String> result =  wordFreqs.getSanitisedList(fileName);

            List<String> referenceList = new ArrayList<>();
            try (Stream<String> stream = Files.lines(Paths.get("src/test/java/org/coursera/resources/macbethResult.txt"));) {
                referenceList = stream
                        .map(str -> str.replaceAll("\\p{Punct}", ""))
                        .map(String::toLowerCase)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }

//            for (String actualStr : result) {
//                for (String expectedStr : referenceList) {
//                    assertEquals(expectedStr, actualStr);
//                }
//            }

            for (int i = 0; i < referenceList.size(); i++) {
                assertEquals(referenceList.get(i), result.get(i));
            }
        }
    }
}
