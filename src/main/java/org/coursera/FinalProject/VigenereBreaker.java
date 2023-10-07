package org.coursera.FinalProject;

import edu.duke.FileResource;

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
        int aKey;
        int[] keys = new int[keyLength];
        for(int k =0;k<keyLength;k++){
           aKey = CaesarCracker.getKey(sliceString(encrypted,k,keyLength));
           keys[k] = aKey;
        }
        for(int i = 0; i < keyLength; i++) {
            System.out.println(keys[i]);
        }
        return keys;
    }

    public String breakVigenere() {
        FileResource resource = new FileResource();
        return resource.asString();
    }

    public static void main(String[] args) {
        VigenereBreaker breaker = new VigenereBreaker();
        String message = breaker.breakVigenere();
        int[] keys = breaker.tryKeyLength(message, 4, 'e');
        VigenereCipher cipher = new VigenereCipher(keys);
        System.out.println(cipher.decrypt(message));
    }

}
