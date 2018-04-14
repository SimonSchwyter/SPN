import java.math.BigInteger;

class Spn {
    private final int[][] bitpermut = new int[][]{
        {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},{0,4,8,12,1,5,9,13,2,6,10,14,3,7,11,15}
    };
    private String[][] sBox;
    private String[] keys;
    private int m;

    Spn(String baseKey, int keyCount,String[][] sBox, int m) {
        // this.keys = calcKeys(baseKey, keyCount);
        this.sBox = sBox;
        this.m = m;
    }

    String encrypt(String text){
        String encryptedText = "";
        // Read text into array
        String[] groupedText = new String[text.length() / m];
        for (int i = 0; i < text.length() / m; i++) {
            groupedText[i] = text.substring(i*m, i*m + m);
        }

        for (String partText: groupedText) {

            System.out.println(partText);
        }

        // text = runSBox(text);
        return encryptedText;
    }

    String decrypt(String cypherText) {
        // 1: Decrypt cyphertext with CTR-Mode
        String[] xArray = new String[cypherText.length() / m];

        // Get y-1 from start of string
        String yMinusOne = cypherText.substring(0, m);
        // Remove y-1 from cyphertext
        cypherText = cypherText.substring(m);

        for (int i = 0; i < cypherText.length() / m; i++) {
            String yi = cypherText.substring(i*m, i*m + m);
            String xiOne =  (Integer.toBinaryString(Integer.parseInt(yMinusOne + intToBinary(i), 2) % 2222));
            String xi = xor(xiOne, yi);
            xArray[i] = xi;
        }

        // Generate string from array
        String x = String.join("", xArray);


        // 2: Remove Padding
        char j = x.charAt(x.length());
        // Remove all 0 at the end of the string
        while(j == '0') {
            x = x.substring(0, x.length()-1);
            j = x.charAt(x.length());
        }

        // Further remove the 1 padding
        x = x.substring(0, x.length()-1);

        // 3: Convert bitstring back to text
        byte[] bytes = x.getBytes();
        BigInteger bi = new BigInteger(bytes);
        String s = bi.toString(2);

        return s;
    }

    /**
     * Generates a padded binary from an integer - Padding is taken from length of spn
     * @param x
     * @return
     */
    String intToBinary(int x) {
        return String.format("%" + m + "s", Integer.toBinaryString(x)).replace(' ', '0');
    }

    String E(String text){
        // 1: Foreach key: xor value with ki, apply sbox and bitshift

        return text;
    }

    /**
     * xor Function for two strings - https://stackoverflow.com/questions/5126616/xor-operation-with-two-strings-in-java
     * @param a
     * @param b
     * @return xor value of a and b
     */
    String xor(String a, String b){
        StringBuilder sb = new StringBuilder();
        for(int k=0; k < a.length(); k++)
            sb.append((a.charAt(k) ^ b.charAt(k + (Math.abs(a.length() - b.length()))))) ;
        return sb.toString();
    }

    private String[] invertKeys() {
        String[] newKeys = new String[this.keys.length];
        for(int i =0; i<this.keys.length; i++){
            if(i != 0 && i != this.keys.length-1){
                this.keys[this.keys.length-i] = runBitpermutation(this.keys[this.keys.length-i]);
            }
            newKeys[i] = this.keys[this.keys.length-i];
        }
        return newKeys;
    }

    /**
     * Calculate keys from basekey
     * @params String basekey
     * @return String Array of keys k0, k1, ..., kn-1
     */
    String[] calcKeys(String keybase, int keyCount){
        String[] keys = new String[keyCount + 1];
        for (int i = 0; i < keyCount + 1; i++) {
            keys[i] = keybase.substring(4*i, 4*i + 16);
        }

        return keys;
    }

    private String runSBox(String text){
        String newText="";
        String[] textBlocks = text.split("(?<=\\G....)");
        for(int i=0; i < textBlocks.length;i++){
            for(int j=0; j < textBlocks.length;j++){
                if(textBlocks[i].equals(this.sBox[0][j])){
                    newText += this.sBox[1][j];
                }
            }
        }
        return newText;
    }

    private String runBitpermutation(String text){
        String newText= "";
        for(int i=0; i < text.toCharArray().length; i++){
            newText += text.toCharArray()[bitpermut[1][i]];
        }
        return newText;
    }
}
