package org.coursera;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodonCountTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    CodonCount codonCount;
    String dna;

    @BeforeEach
    public void setup() {
        codonCount = new CodonCount();
        Path path = Paths.get("src/test/java/org/coursera/resources/DNAStrand.txt");
        try {
            dna = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Nested
    class CodonCountStartingAtZero {
        @BeforeEach
        void beforeEach() { codonCount.buildCodonMap(0, dna); }

        @DisplayName("Returns the most common codon")
        @Test
        void getMostCommonCodonTestStartAtZero() {
            assertEquals("TCA with count 2", codonCount.getMostCommonCodon());
        }

        @DisplayName("Print all codon with count withing range")
        @Test
        void printCodonCountsTestStartAtZero() {
            codonCount.getMostCommonCodon();
            codonCount.printCodonCounts(1, 5);
            assertEquals("CGT\t1\n" + "TCA\t2\n" + "AGT\t1", outputStreamCaptor.toString().trim());
        }
    }

    @Nested
    class CodonCountStartingAtOne {
        @BeforeEach
        void beforeEach() {
            codonCount.buildCodonMap(1, dna);
            String count = codonCount.getMostCommonCodon();
        }

        @DisplayName("Returns the most common codon")
        @Test
        void getMostCommonCodonTest() {
            assertEquals("CAA with count 2", codonCount.getMostCommonCodon());
        }

        @DisplayName("Print all codon with count withing range")
        @Test
        void printCodonCountsTest() {
            codonCount.printCodonCounts(1,5);
            assertEquals("CAA\t2\n" + "GTT\t2", outputStreamCaptor.toString().trim());
        }
    }


    @Nested
    class CodonCountStartingAtTwo {
        @BeforeEach
        void beforeEach() {
            codonCount.buildCodonMap(2, dna);
        }

        @DisplayName("Returns the most common codon")
        @Test
        void getMostCommonCodonTest() {
            assertEquals("TTC with count 2", codonCount.getMostCommonCodon());
        }

        @DisplayName("Print all codon with count withing range")
        @Test
        void printCodonCountsTest() {
            codonCount.getMostCommonCodon();
            codonCount.printCodonCounts(1,5);
            assertEquals("TTC\t2\n" + "AAG\t1", outputStreamCaptor.toString().trim());
        }
    }

}
