package coursera.assignement1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaesarCipherTest {
    CaesarCipher cipher;

    @BeforeEach
    public void setUp() { cipher = new CaesarCipher(); }

    @Nested
    class EncryptionWithOneKey {
        @DisplayName("Key is lesser than 26")
        @Test
        void encryptWithKeyLesserThan26() {
            assertEquals("Rdqq OMWQ ur kag omz pqodkbf ftue!", cipher.encrypt("Free CAKE if you can decrypt this!", 12));
        }

        @DisplayName("Key is greater than 26")
        @Test
        void encryptWithKeyGreaterThan26() {
            assertEquals("Ymj hfpj nx f QNJ.", cipher.encrypt("The cake is a LIE.", 31));
        }

        @DisplayName("Throws exception when key less than or equal to 0")
        @Test
        void encryptWithNegativeKey() {
            RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
                cipher.encrypt("Try to break the Caesar Cipher", -5);
            });
            assertEquals("This is not a valid encryption key: -5", exception.getMessage());
        }

        @DisplayName("Throws exception when key is 26 or multiple of 26")
        @Test
        void encryptWithKey26OrMultipleOf26() {
            RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
                cipher.encrypt("That's disappointing...Life’s batter with cake.", 52);
            });
            assertEquals("This is not a valid encryption key: 52", exception.getMessage());
        }
    }

    @Nested
    class EncryptionWithTwoKeys {
        @DisplayName("Keys are lesser than 26")
        @Test
        void encryptWithKeyLesserThan26() {
            assertEquals("Rqqd BMJQ ue xat bmm cqbdxbs sthe!", cipher.encryptTwoKeys("Free CAKE if you can decrypt this!", 12, 25));
        }

        @DisplayName("Key greater than 26")
        @Test
        void encryptWithKeyGreaterThan26() {
            assertEquals("Yaj htpx bx f QBJ.", cipher.encryptTwoKeys("The cake is a LIE.", 31, 45));
        }

        @DisplayName("Throws exception when one of the key less than or equal to 0")
        @Test
        void encryptWithNegativeKey() {
            RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
                cipher.encryptTwoKeys("Try to break the Caesar Cipher", 0, 30);
            });
            assertEquals("This is not a valid encryption key: 0", exception.getMessage());
        }

        @DisplayName("Throws exception when one of the keys is 26 or multiple of 26")
        @Test
        void encryptWithKey26OrMultipleOf26() {
            RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
                cipher.encryptTwoKeys("That's disappointing...Life’s batter with cake.", 26, 36);
            });
            assertEquals("This is not a valid encryption key: 26", exception.getMessage());
        }
    }
}
