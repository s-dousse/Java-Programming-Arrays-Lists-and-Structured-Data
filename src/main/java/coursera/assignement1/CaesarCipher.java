package coursera.assignement1;

public class CaesarCipher {

    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";;
    private String shiftedAlphabetOne;
    private String  shiftedAlphabetTwo;

    public CaesarCipher(int key) {
        shiftedAlphabetOne = shiftBy(validate(key));
    }

    public CaesarCipher(int keyOne, int keyTwo) {
        shiftedAlphabetOne = shiftBy(validate(keyOne));
        shiftedAlphabetTwo = shiftBy(validate(keyTwo));
    }

    public String encrypt(String input) {
        StringBuilder encryptedMsg = new StringBuilder(input);
        for(int i = 0; i < encryptedMsg.length(); i++) {
            char currChar = encryptedMsg.charAt(i);
            int idx = ALPHABET.indexOf(Character.toUpperCase(currChar));
            char newChar;
            if (idx != -1){
                if (shiftedAlphabetTwo == null ) {
                    newChar = shiftedAlphabetOne.charAt(idx);
                } else {
                    newChar = (i % 2 == 0) ? shiftedAlphabetOne.charAt(idx) : shiftedAlphabetTwo.charAt(idx);
                }
                setNewCharacter(currChar, newChar, encryptedMsg, i );
            }
        }
        return encryptedMsg.toString();
    }

    private void setNewCharacter(char currChar, char newChar, StringBuilder encryptedMsg, int i ) {
       char ch = Character.isUpperCase(currChar) ? Character.toUpperCase(newChar) : Character.toLowerCase(newChar);
       encryptedMsg.setCharAt(i, ch);
    }

    private int validate(int key) {
        if ( key % 26 != 0 && key != 13  && key >= 0) {
            return( key < 26 ) ? key : (key % 26);
        }
        throw new RuntimeException("This is not a valid encryption key: " + key);
    }

    private String shiftBy(int key) {
        return ALPHABET.substring(key) + ALPHABET.substring(0, key);
    }

    public static void main(String[] args) {}
}

