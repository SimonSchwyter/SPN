public class Spn {
    private String text ="";
    private final int[][] bitpermut = new int[][]{
        {0,1,2,3,4},{4,3,2,1,0}
    };
    private String[] keys;
    public Spn(String text, String baseKey, int keyCount) {
        this.text = text;
        this.keys = calcKeys(baseKey, keyCount);

    }

    public String encript(){
        /*for(int i =0; i < this.keys.length(); i++){

        }*/
        return "";
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
            System.out.println(keys[i]);
            block++;
        }
        return keys;
    }

    public String getText() {
        return text;
    }


    private String runSBox(){
        return "";
    }
    private String runSBoxInverted(){return "";}
    private String runBitpermutation(){
        String newText= "";
        for(int i=0; i < this.text.length(); i++){
            newText += this.text.toCharArray()[bitpermut[1][i]];
        }
        return newText;
    }
}
