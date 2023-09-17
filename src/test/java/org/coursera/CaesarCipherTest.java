package org.coursera;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaesarCipherTest {
    CaesarCipher cipher;

    @Nested
    class keyValidation {
        @DisplayName("Throws exception when key is less than or equal to 0")
        @Test
        void instantiationWithOneKeyLesserOrEqualToZero() {
            RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
                cipher = new CaesarCipher(-5);
            });
            assertEquals("This is not a valid encryption key: -5", exception.getMessage());
        }

        @DisplayName("Throws exception when key is 26 or multiple of 26")
        @Test
        void instantiationWithOneKey26OrMultipleOr26() {
            RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
                cipher = new CaesarCipher(52);
            });
            assertEquals("This is not a valid encryption key: 52", exception.getMessage());
        }
        @DisplayName("Throws exception when one of the key is less than or equal to 0")
        @Test
        void instantiationWithTwoKeysLesserOrEqualToZero() {
            RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
                cipher = new CaesarCipher(0, 30);
            });
            assertEquals("This is not a valid encryption key: 0", exception.getMessage());
        }

        @DisplayName("Throws exception when one of the keys is 26 or multiple of 26")
        @Test
        void instantiationWithTwoKeys26OrMultipleOr26() {
            RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
                cipher = new CaesarCipher(26, 36);
            });
            assertEquals("This is not a valid encryption key: 26", exception.getMessage());
        }
    }

    @Nested
    class EncryptionWithOneKey {
        @DisplayName("Key is lesser than 26")
        @Test
        void encryptWithKeyLesserThan26() {
            cipher = new CaesarCipher(12);
            assertEquals("Rdqq OMWQ ur kag omz pqodkbf ftue!", cipher.encrypt("Free CAKE if you can decrypt this!"));
        }

        @DisplayName("Key is greater than 26")
        @Test
        void encryptWithKeyGreaterThan26() {
            cipher = new CaesarCipher(31);
            assertEquals("Ymj hfpj nx f QNJ.", cipher.encrypt("The cake is a LIE."));
        }

        @DisplayName("Long string")
        @Test
        void encryptWithOneKeyTestString() {
            cipher = new CaesarCipher(17);
            assertEquals("Wle Armr Wrtkj: Kyv crexlrxv nrj zezkzrccp trccvu Frb rwkvi re frb kivv kyrk jkffu flkjzuv Xfjczex'j fwwztv.", cipher.encrypt("Fun Java Facts: The language was initially called Oak after an oak tree that stood outside Gosling's office."));
        }
    }

    @Nested
    class EncryptionWithTwoKeys {
        @DisplayName("Keys are lesser than 26")
        @Test
        void encryptWithKeyLesserThan26() {
            cipher = new CaesarCipher(12, 25);
            assertEquals("Rqqd BMJQ ue xat bmm cqbdxbs sthe!", cipher.encrypt("Free CAKE if you can decrypt this!"));
        }

        @DisplayName("Key greater than 26")
        @Test
        void encryptGreaterThan26() {
            cipher = new CaesarCipher(31, 45);
            assertEquals("Yaj htpx bx f QBJ.", cipher.encrypt("The cake is a LIE."));
        }


        @DisplayName("Long string with no E")
        @Test
        void encryptLongString() {
            cipher = new CaesarCipher(12, 5);
            assertEquals("Tn, pt daz bmsf m xtxquuau yaimd? U abz yfzd latp rqmaawe, ggy gmsmsm ux tgyeymspnzl.", cipher.encrypt("Hi, do you want a lollipop today? I own many good flavors, but banana is outstanding."));
        }

    }
}
