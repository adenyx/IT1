public class Rails {

    public static void start(String string, int key) {
        System.out.println("\n");
        System.out.println("Rails encryption method: ");
        System.out.println(Encrypt(string, key));
        System.out.println("decryption: ");
        System.out.println(Decrypt(Encrypt(string, key), key));
        System.out.println("\n");
    }

    private static String Encrypt(String toEncrypt, int key){

        if (key < 1) return null;

        if (key == 1) return toEncrypt;

        char[][] out = new char[key][toEncrypt.length()];

        boolean directionDown = false;
        int row = 0, col = 0;

        for (int i = 0; i < key; i++){
            for (int j = 0; j < toEncrypt.length(); j++) {
                out[i][j] = '\n';
            }
        }

        for (int i = 0; i < toEncrypt.length(); i++){
            if (row == 0 || row == key-1)
                directionDown = !directionDown;
            out[row][col++] = toEncrypt.charAt(i);
            if (directionDown)
                row++;
            else
                row--;
        }

        String resultText = "";
        for (int i = 0; i < key; i++)
            for (int j = 0; j < toEncrypt.length(); j++){
                if (out[i][j] != '\n')
                    resultText = resultText.concat(String.valueOf(out[i][j]));
            }

        return resultText;
    }


    private static String Decrypt(String toDecrypt, int key){
        if (key < 1) return null;

        if (key == 1) return toDecrypt;

        char[][] out = new char[key][toDecrypt.length()];

        boolean directionDown = false;
        int row = 0, col = 0;

        for (int i = 0; i < toDecrypt.length(); i++){
            if (row == 0 || row == key-1)
                directionDown = !directionDown;
            out[row][col++] = '&';
            if (directionDown)
                row++;
            else
                row--;
        }

        int ind = 0;
        for (int i = 0; i < key; i++)
        {
            for (int j = 0; j < toDecrypt.length(); j++) {
                if (out[i][j] == '&')
                    out[i][j] = toDecrypt.charAt(ind++);
            }
        }

        String resultText = "";

        row = 0;
        col = 0;

        for (int i = 0; i < toDecrypt.length(); i++)
        {
            if (row == 0)
                directionDown = true;
            if (row == key - 1)
                directionDown = false;
            resultText = resultText.concat(String.valueOf(out[row][col++]));
            if (directionDown)
                row++;
            else
                row--;
        }

        return resultText;
    }

}
