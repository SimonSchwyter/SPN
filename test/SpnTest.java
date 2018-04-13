import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpnTest {

    private String[][] sBox;
    private String key;

    @Before
    public void setUp() {
        sBox = new String[][]{{"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"},
                {"1110", "0100", "1101", "0001", "0010", "1111", "1011", "1000", "0011", "1010", "0110", "1100", "0101", "1001", "0000", "0111"}};
        key = "0001" + "0001" + "0010" + "1000" + "1000" + "1100" + "0000" +  "0000";
    }

    /*
    @Test
    public void encrypt() {
        String text = "0001001010001111";
        Spn spn = new Spn(key, 4, sBox, 4);
        assertEquals(spn.encrypt(text), "1010111010110100");
    }*/

    @Test
    public void decrypt() {
        String cypherText = "1010111010110100";
        Spn spn = new Spn(key, 4, sBox, 4);
        assertEquals(spn.decrypt(cypherText), "0001001010001111");
    }

    @Test
    public void xor() {
        Spn spn = new Spn(key, 4, sBox, 4);
        String result = spn.xor("111101010110", "000110101111");
        assertEquals(result, "111011111001");
    }

    @Test
    public void encrypt() {
    }

    @Test
    public void intToBinary() {
        Spn spn = new Spn(key, 4, sBox, 4);
        assertEquals(spn.intToBinary(2), "0010");
    }

    @Test
    public void e() {
    }
}