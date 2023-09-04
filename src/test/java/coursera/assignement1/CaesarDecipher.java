package coursera.assignement1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaesarDecipherTest {
    CaesarDecipher decipher;

    @Nested
    class DecryptionWithOneKey {
        @DisplayName("Key is lesser than 26")
        @Test
        void encryptWithKeyLesserThan26() {
            decipher = new CaesarDecipher("Rdqq OMWQ ur kag omz pqodkbf ftue!");
            assertEquals("Free CAKE if you can decrypt this!", decipher.decryptWithOneKey());
        }

        @DisplayName("Key is greater than 26")
        @Test
        void encryptWithKeyGreaterThan26() {
            decipher = new CaesarDecipher("Ymj hfpj nx f QNJ.");
            assertEquals("The cake is a LIE.", decipher.decryptWithOneKey());
        }
    }

    @Nested
    class DecryptionWithTwoKeys {
        @DisplayName("Short String with Es")
        @Test
        void encryptWithKeyLesserThan26() {
            decipher = new CaesarDecipher("Roqb ZMHQ uc var zmk aqzdvbq qtfe ybepmdq, tq nbxfqsq uk var!");
            assertEquals("Free CAKE if you can decrypt this message, we believe in you!", decipher.decryptWithTwoKeys());
        }

        @DisplayName("Long String with Es")
        @Test
        void encryptWithKeyGreaterThan26() {
            decipher = new CaesarDecipher("Eq qabpsi, yi mk qlb jibxfrd oslq afxe vsrv xfkbv hfwdyfwb lr jlv e rfgb pyotompi txvqc afxe bzbvvski. qlbvb tmip fb ZEHI ekh MZI GOIXQ!!");
            assertEquals("At twelve, be in the meeting room with your tiger disguise on for a nice surprise party with everyone. there will be CAKE and ICE CREAM!!", decipher.decryptWithTwoKeys());
        }

        // TODO: figure out how to break the encryption without E, next most common letter is A, or look for patterns
        @Disabled
        @DisplayName("Long string with no Es")
        @Test
        void decryptLongStringWithNoEs() {
            decipher = new CaesarDecipher("Tn, pt daz bmsf m xtxquuau yaimd? U abz yfzd latp rqmaawe, ggy gmsmsm ux tgyeymspnzl.");
            assertEquals("Hi, do you want a lollipop today? I own many good flavors, but banana is outstanding.", decipher.decryptWithTwoKeys());
        }
    }
}
