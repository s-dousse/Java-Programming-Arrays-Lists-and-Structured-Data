package coursera.assignement1;

class CaesarDecipher {

    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int INDEX_OF_E = 4;
    private String encryptedMsg;
    public CaesarDecipher(String encryptedMsg) {
        this.encryptedMsg = encryptedMsg;
    }

    private int[] countLetters(String message) {
        int[] counters = new int[ALPHABET.length()];

        for (int i = 0; i < message.length(); i++) {
            char ch = Character.toUpperCase(message.charAt(i));
            int indexCh = ALPHABET.indexOf(ch);
            if (indexCh != -1) { counters[indexCh]++; }
        }
        return counters;
    }

    private int maxIndex(int[] counters) {
        int maxIndex = 0;

        for (int i=0; i < counters.length; i++) {
            if (counters[i] > counters[maxIndex]) { maxIndex = i; }
        }
        return maxIndex;
    }

    private String halfOfString(String message, int start){
        String result = new String();

        for (int k = start; k< message.length();k += 2) {
            result += message.charAt(k);
        }
        return result;
    }

    private int getDecryptionKey(String s) {
        int[] counters = countLetters(s);
        int maxDex = maxIndex(counters);
        int dKey = maxDex - INDEX_OF_E;

        if (maxDex < INDEX_OF_E) {
            dKey = 26 - (INDEX_OF_E -maxDex);
        }
        return dKey;
    }

    public String decryptWithOneKey() {
        int key = getDecryptionKey(this.encryptedMsg);

        CaesarCipher cc = new CaesarCipher((26 - key));
        String decrypted = cc.encrypt(this.encryptedMsg);
        return decrypted;
    }

    public String decryptWithTwoKeys() {
        StringBuilder strEven = new StringBuilder(halfOfString(encryptedMsg, 0));
        StringBuilder strOdd = new StringBuilder(halfOfString(encryptedMsg, 1));
        int keyOne = getDecryptionKey(strEven.toString());
        int keyTwo = getDecryptionKey(strOdd.toString());

        CaesarCipher cc = new CaesarCipher((26 - keyOne), (26 - keyTwo));
        return cc.encrypt(encryptedMsg);
    }

    public static void main(String[] args) {}
}
