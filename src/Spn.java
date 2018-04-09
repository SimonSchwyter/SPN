public class Spn {
    private String text ="";
    private final int[][] bitpermut = new int[][]{
        {0,1,2,3,4},{4,3,2,1,0}
    };

    public Spn(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }


    private String runSBox(){
        return "";
    }
    private String runSBoxInverted(){return "";}
    public String runBitpermutation(){
        String newText= "";
        for(int i=0; i < this.text.length(); i++){
            newText += this.text.toCharArray()[bitpermut[1][i]];
        }
        return newText;
    }
}
