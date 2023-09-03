package coursera.assignement1;

import edu.duke.FileResource;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        key = validate(key);
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) +
                                 alphabet.substring(0, key);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                if (Character.isUpperCase(currChar)) {
                    encrypted.setCharAt(i, Character.toUpperCase(newChar));
                } else {
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
        }
        return encrypted.toString();
    }

    public String encryptTwoKeys(String input, int keyOne, int keyTwo) {
        keyOne = validate(keyOne);
        keyTwo = validate(keyTwo);
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabetOne = alphabet.substring(keyOne)+
                alphabet.substring(0,keyOne);
        String shiftedAlphabetTwo = alphabet.substring(keyTwo)+
                alphabet.substring(0, keyTwo);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            char newChar;
            if (idx != -1) {
                if (i % 2 == 0) {
                    newChar = shiftedAlphabetOne.charAt(idx);
                } else {
                    newChar = shiftedAlphabetTwo.charAt(idx);
                }

                if (Character.isUpperCase(currChar)) {
                    encrypted.setCharAt(i, Character.toUpperCase(newChar));
                } else {
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
        }
        return encrypted.toString();
    }

    private int validate(int key) {
        if ( key % 26 != 0 && key != 13  && key > 0) {
            return( key < 26 ) ? key : (key % 26);
        }
        throw new RuntimeException("This is not a valid encryption key: " + key);
    }

    public static void main(String[] args) {
    }
}

