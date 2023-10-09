package org.coursera.FinalProject;

import edu.duke.FileResource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class VigenereBreaker {
    public String sliceString(String message, int startSlice, int keyLength) {
        StringBuilder slicedMsg = new StringBuilder(message);
        String result = new String();
        for(int i = startSlice; i < slicedMsg.length(); i += keyLength){
            result += slicedMsg.charAt(i);
        }
        return result;
    }

    public int[] tryKeyLength(String encrypted, int keyLength, char mostCommon) {
        CaesarCracker CaesarCracker = new CaesarCracker(mostCommon);
        int key;
        int[] keys = new int[keyLength];
        for(int i = 0; i < keyLength; i++){
           key = CaesarCracker.getKey(sliceString(encrypted, i, keyLength));
           keys[i] = key;
        }
        return keys;
    }

    public int countWords(String message, HashSet dictionary) {
        int wordsCount = 0;
        ArrayList<String> wordsArr = new ArrayList<String>(Arrays.asList(message.split("\\W")));
        for(int i = 0 ; i < wordsArr.size(); i++ ) {
            if (dictionary.contains(wordsArr.get(i).toLowerCase())) { wordsCount++; }
        }
        return wordsCount;
    }

    public String breakForLanguage(String encryptedMsg, HashSet dictionaries) {
        int highestCount = 0;
        int keyLength = 0;
        String decryptedMsg = new String();
        int[] keys = new int[100];
        String solution = new String();
        for (int i = 1; i <= 100; i ++) {
            keys = tryKeyLength(encryptedMsg, i, 'e' );
            VigenereCipher vigenere = new VigenereCipher(keys);
            decryptedMsg = vigenere.decrypt(encryptedMsg);
            int currCount = countWords(decryptedMsg, dictionaries);
            if (currCount > highestCount) {
                highestCount = currCount;
                solution = decryptedMsg;
                keyLength = keys.length;
            }
        }

        System.out.println("Valid word counts: " + highestCount);
        System.out.println("Key Length: " + keyLength);
        System.out.println("Keys : " + keys);
        return solution;
    }

    public HashSet readDictionary(FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String line : fr.lines()) {
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }

    public String breakForLanguageQuizz(String encrypted, HashSet<String> dict) {
        int max = 0;
        int keyReturn[] = new int[100];
        String aMessage = new String();
        String largestDecryption = new String();
        String[] decrypted = new String[100];
        keyReturn = tryKeyLength(encrypted,38,'e');
        VigenereCipher VCipher  = new VigenereCipher(keyReturn) ;
        aMessage = VCipher.decrypt(encrypted);
        //counts is a value returned, no use starting from 0
        int counts = countWords(aMessage, dict);
        if(counts > max){
            max = counts;
            largestDecryption = aMessage;
        }
        System.out.println("Max counts:" + max);
        return largestDecryption;
    }

    public static void main(String[] args) {
        VigenereBreaker breaker = new VigenereBreaker();
        FileResource textMsg = new FileResource("./src/main/java/org/coursera/FinalProject/VigenereMessages/secretmessage2.txt");
        String message = textMsg.asString();

        FileResource fr = new FileResource("./src/main/java/org/coursera/FinalProject/dictionaries/English");
        HashSet dictionary = breaker.readDictionary(fr);
        System.out.println(breaker.breakForLanguage(message, dictionary));
    }

}

