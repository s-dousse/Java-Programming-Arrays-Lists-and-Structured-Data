package org.coursera;

import java.util.HashMap;

public class CodonCount {
    private final int CODON_LENGTH = 3;
    private HashMap<String, Integer> DNA_count;

    public CodonCount() {
        this.DNA_count = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        if(DNA_count != null) { DNA_count.clear(); }
        dna = sanitiseDNAStrand(dna);
        for (int i = start; i < (dna.length() - 2); i += CODON_LENGTH) {
            String codon = dna.substring(i, i + 3);
            if (!DNA_count.containsKey(codon)) {
                DNA_count.put(codon, 1);
            } else {
                DNA_count.put(codon, DNA_count.get(codon) + 1);
            }
        }

    }

    public String getMostCommonCodon() {
        int highest = 0;
        String codon = "";
        for (String codonKey  : DNA_count.keySet()) {
            int codonCount = DNA_count.get(codonKey);
            if (highest < codonCount) {
                highest = codonCount;
                codon = codonKey;
            }
        }

        return String.format("%s with count %s", codon, highest);
    }

    public void printCodonCounts(int start, int end) {
        for (String codonKey  : DNA_count.keySet()) {
            int count = DNA_count.get(codonKey);
            if (count >= start && count <= end) {
                System.out.println(codonKey + "\t" + count);
            }
        }
    }

    private String sanitiseDNAStrand(String dna) {
        return dna.toUpperCase().trim();
    }

    public static void main(String[] args) {}
}
