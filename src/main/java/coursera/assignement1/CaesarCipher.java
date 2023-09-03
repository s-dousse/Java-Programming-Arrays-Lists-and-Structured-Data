package coursera.assignement1;

public class CaesarCipher {

    private String alphabet;
    private String shiftedAlphabetOne;
    private String  shiftedAlphabetTwo;

    public CaesarCipher(int key) {
        key = validate(key);
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabetOne = alphabet.substring(key) +
                alphabet.substring(0, key);
    }

    public CaesarCipher(int keyOne, int keyTwo) {
        keyOne = validate(keyOne);
        keyTwo = validate(keyTwo);
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabetOne = alphabet.substring(keyOne) +
                alphabet.substring(0, keyOne);
        shiftedAlphabetTwo = alphabet.substring(keyTwo) +
                alphabet.substring(0, keyTwo);
    }

    public String encryptWithOneKey(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if (idx != -1){
                char newChar = shiftedAlphabetOne.charAt(idx);
                if (Character.isUpperCase(currChar)) {
                    encrypted.setCharAt(i, Character.toUpperCase(newChar));
                } else {
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
        }
        return encrypted.toString();
    }

    public String encryptWithTwoKeys(String input) {
        StringBuilder encrypted = new StringBuilder(input);
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
        if ( key % 26 != 0 && key != 13  && key >= 0) {
            return( key < 26 ) ? key : (key % 26);
        }
        throw new RuntimeException("This is not a valid encryption key: " + key);
    }

    public static void main(String[] args) {
    }
}

