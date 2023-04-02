package AES;

public class AES_Encrypt {

    static String[][] SBox = {
            {"63","7C","77","7B","F2","6B","6F","C5","30","01","67","2B","FE","D7","AB","76" },
            {"CA","82","C9","7D","FA","59","47","F0","AD","D4","A2","AF","9C","A4","72","C0" },
            {"B7","FD","93","26","36","3F","F7","CC","34","A5","E5","F1","71","D8","31","15" },
            {"04","C7","23","C3","18","96","05","9A","07","12","80","E2","EB","27","B2","75" },
            {"09","83","2C","1A","1B","6E","5A","A0","52","3B","D6","B3","29","E3","2F","84" },
            {"53","D1","00","ED","20","FC","B1","5B","6A","CB","BE","39","4A","4C","58","CF" },
            {"D0","EF","AA","FB","43","4D","33","85","45","F9","02","7F","50","3C","9F","A8" },
            {"51","A3","40","8F","92","9D","38","F5","BC","B6","DA","21","10","FF","F3","D2" },
            {"CD","0C","13","EC","5F","97","44","17","C4","A7","7E","3D","64","5D","19","73" },
            {"60","81","4F","DC","22","2A","90","88","46","EE","B8","14","DE","5E","0B","DB" },
            {"E0","32","3A","0A","49","06","24","5C","C2","D3","AC","62","91","95","E4","79" },
            {"E7","C8","37","6D","8D","D5","4E","A9","6C","56","F4","EA","65","7A","AE","08" },
            {"BA","78","25","2E","1C","A6","B4","C6","E8","DD","74","1F","4B","BD","8B","8A" },
            {"70","3E","B5","66","48","03","F6","0E","61","35","57","B9","86","C1","1D","9E" },
            {"E1","F8","98","11","69","D9","8E","94","9B","1E","87","E9","CE","55","28","DF" },
            {"8C","A1","89","0D","BF","E6","42","68","41","99","2D","0F","B0","54","BB","16" }
    };

    static int[][] mixCols = {
            {2,3,1,1},
            {1,2,3,1},
            {1,1,2,3},
            {3,1,1,2},
    };

    static int rowLength = 4, colLength = 4;
    static String[][] State = new String[rowLength][colLength];
    static String[] Key_Words = new String[44];

    public AES_Encrypt(String Plain, String Key){
        int pointer = 0;
        Key_Words = new AES_Key(Key).getWords();
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                State[j][i] = Plain.substring(pointer,pointer+2);
                pointer += 2;
            }
        }

        addRoundKey(0);
        for (int i = 1; i <= 10; i++) {
            subBytes();
            shiftRows();
            if (i != 10)
                mixColumns();
            addRoundKey(i);
        }

        for (int i = 0; i < rowLength; i++)
            for (int j = 0; j < colLength; j++)
                System.out.print(State[j][i]);
        System.out.println();
    }
    public void addRoundKey(int numberOfRound){
        String[][] subKey = new String[rowLength][colLength];
        for (int i = 0; i < colLength; i++) {
            for (int j = 0; j < rowLength; j++) {
                subKey[j][i] = Key_Words[numberOfRound*4 + i].substring(j*2,j*2 +2);
            }
        }

        String newValue;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                newValue = Integer.toString(Integer.parseInt(State[i][j],16)^Integer.parseInt(subKey[i][j],16),16).toUpperCase();
                newValue = (newValue.length() == 1) ? "0" + newValue : newValue;
                State[i][j] = newValue;
            }
        }
    }
    public void subBytes(){
        int row, col;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                row = Integer.parseInt(Character.toString(State[i][j].charAt(0)),16);
                col = Integer.parseInt(Character.toString(State[i][j].charAt(1)),16);
                State[i][j] = SBox[row][col];
            }
        }

    }
    public void shiftRows() {
        String temp;

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < i; j++) {
                temp = State[i][0];

                for (int k = 1; k < rowLength; k++)
                    State[i][k-1] = State[i][k];

                State[i][3] = temp;
            }
        }
    }
    public void mixColumns() {
        int temp = 0;
        String[][] tempState = new String[rowLength][colLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                for (int k = 0; k < colLength; k++) {
                    temp ^= Integer.parseInt(multiplyTwoNum(mixCols[i][k],State[k][j]),16);
                }
                tempState[i][j] = Integer.toString(temp,16).toUpperCase();
                temp = 0;
            }
        }
        State = tempState;
    }
    public String XFx(String Fx){
        int temp = Integer.parseInt(Fx, 16);
        Fx = Integer.toString(temp, 2);

        while(Fx.length() < 8) Fx = "0" + Fx;

        if(Fx.charAt(0) == '0') {
            Fx = Fx.substring(1,Fx.length()) + "0";
            return Integer.toString(Integer.parseInt(Fx,2), 16).toUpperCase();
        }
        Fx = Fx.substring(1,Fx.length()) + "0";
        return Integer.toString(Integer.parseInt(Fx,2)^Integer.parseInt("00011011",2),16).toUpperCase();
    }
    public String multiplyTwoNum(int mixColsNum, String hexNum){
        if (mixColsNum == 1)
            return hexNum;
        else if (mixColsNum == 2){
            return XFx(hexNum);
        }
        return Integer.toString(Integer.parseInt(XFx(hexNum),16)^Integer.parseInt(hexNum,16),16).toUpperCase();
    }
}
