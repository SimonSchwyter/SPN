public class Spn {
    private String text ="";
    private final int[][] bitpermut = new int[][]{
        {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15},{0,4,8,12,1,5,9,13,2,6,10,14,3,7,11,15}
    };
    private String[][] sBox;
    private String[] keys;
    public Spn(String text, String baseKey, int keyCount,String[][] sBox) {
        this.text = text;
        this.keys = calcKeys(baseKey, keyCount);
        this.sBox = sBox;
    }

    public String encript(){
        //this.keys = invertKeys();
        String encriptedText="";
        this.text = runSBox();
        System.out.println(this.text);
        for(int i =0; i < this.keys.length; i++){

        }
        return encriptedText;
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

    /**Calculate keys from basekey
     * @params String basekey
     * @return String Array of keys k0, k1, ..., kn-1 */
    private String[] calcKeys(String keybase, int keyCount){
        String[] keys = new String[keyCount+1];
        int block =0;
        for(int i =0; i< keyCount+1; i++){
            String key = "";
            int item =0;
            for(int j=0; j<16; j++){
                if(4*block+j == keybase.toCharArray().length){
                block=0;
                item =0;
                }
                 key += keybase.toCharArray()[4*block+item];
                 item ++;
            }
            keys[i]=key;
            block++;
        }
        return keys;
    }

    public String getText() {
        return text;
    }


    private String runSBox(){
        String newText="";
        String[] textBlocks = this.text.split("(?<=\\G....)");
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
