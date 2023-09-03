package coursera.assignement1;

import edu.duke.FileResource;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key)+
                alphabet.substring(0,key);
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

    public static void main(String[] args) {
        CaesarCipher cypher = new CaesarCipher();
        int key = 3;
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("Message : " + message);

        System.out.println("Encrypted : " + cypher.encrypt(message, 15));
        System.out.println("EncryptedTwo : " + cypher.encryptTwoKeys(message, 3, 21));
    }
}
