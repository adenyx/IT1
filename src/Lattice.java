import java.util.Scanner;

public class Lattice {

    private static char[][] outArray;

    public static void start(String string) {
        Scanner ScannerInt2 = new Scanner(System.in);
        System.out.print("Input matrix size for Lattice method: ");
        boolean flag = true;

        do {
            int n;
            n = ScannerInt2.nextInt();

            if (n % 2 == 0 && n * n >= string.length()) {

                flag = false;

                int[][] MatrixForLattice = new int[n][n];
                MatrixForLattice = input(n);

                System.out.println("Rotating Lattice encryption method: ");
                System.out.println(Encrypt(string, n, MatrixForLattice));
                System.out.println("decryption: ");
                System.out.println(Decrypt(Encrypt(string, n, MatrixForLattice), n, MatrixForLattice));
            } else {
                System.out.println("Incorrect input!");
            }
        } while(flag);
    }

    private static int[][] rotateMatrix (int[][] matrix) {
        int SIDE = matrix.length;
        int[][] resultMatrix = new int[SIDE][SIDE];

        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                resultMatrix[i][j] = matrix[SIDE - j - 1][i];
            }
        }
        return resultMatrix;
    }

    private static char[][] rotateMatrix (char[][] matrix) {
        int SIDE = matrix.length;
        char[][] resultMatrix = new char[SIDE][SIDE];

        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                resultMatrix[i][j] = matrix[SIDE - j - 1][i];
            }
        }
        return resultMatrix;
    }

    private static int[][] input(int key) {
        int boolLattice[][] = new int[key][key];
        int numbLattice[][] = new int[key][key];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < key; i++)
            for (int j = 0; j < key; j++)
            {
                boolLattice[i][j] = 0 ;
                numbLattice[i][j] = 0 ;
            }

        for (int turnCount = 0; turnCount < 4 ; turnCount++) {
            int number = 1 ;
            for (int i = 0; i < key / 2; i++)
                for (int j = 0; j < key / 2; j++) {
                    numbLattice[i][j] = number;
                    number++ ;
                }
            numbLattice = rotateMatrix(numbLattice);
        }

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < key; j++) {
                System.out.print(numbLattice[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < (key/2)*(key/2); i++) {
            boolean flag = true;
            while (flag){
                System.out.println("Input hole coordinates for " + (i+1) + " (0-3):");
                System.out.print("x: ");
                int x = scanner.nextInt();
                System.out.print("y: ");
                int y = scanner.nextInt() ;
                if (numbLattice[x][y] == (i + 1)){
                    boolLattice[x][y] = 1;
                    flag = false;
                }
                else
                    System.out.println("Incorrect Data Input");
            }
        }
        return boolLattice;
    }

    private static String Encrypt(String toEncrypt, int key, int[][] lattice){

        outArray = new char[key][key];

        for (int i = 0; i < key ; i++)
            for(int j = 0 ; j < key ; j++)
                outArray[i][j] = (char) ((int)(Math.random() * 26) + 'A') ;

        int index = 0;

        for (int turnCount = 0; turnCount < 4; turnCount++){
            if (index <= toEncrypt.length())
            {
                for (int i = 0; i < key; i++)
                    for (int j = 0; j < key; j++)
                    {
                        if (lattice[i][j] == 1)
                        {
                            if (index < toEncrypt.length()) {
                                outArray[i][j] = toEncrypt.charAt(index);
                            }
                            if (index == toEncrypt.length()) {
                                outArray[i][j] = '0';
                            }
                            index++ ;
                        }
                    }
            }
            lattice = rotateMatrix(lattice);
        }

        String resultText = "";
        for (int i = 0 ; i < key; i++)
            for (int j = 0 ; j < key; j++)
                resultText = resultText + outArray[i][j];

        return resultText;
    }


    private static String Decrypt(String toDecrypt, int key, int[][] lattice){

        outArray = new char[key][key];

        try {
            int index = 0;
            for (int i = 0; i < key; i++) {
                for (int j = 0; j < key && index < toDecrypt.length(); j++) {
                    outArray[i][j] = toDecrypt.charAt(index);
                    index++;
                    if (index == 3)
                        index = 0;
                }
            }
        }catch(StringIndexOutOfBoundsException e){

        }

        String resultText = "";

        int flag = 0;

        for (int turnCount = 0; turnCount < 4; turnCount++){
            for (int i = 0; i < key; i++)
                for (int j = 0; j < key; j++){
                    if (lattice[i][j] == 1 && resultText.length() < toDecrypt.length()){
                        resultText = resultText + outArray[i][j];
                    }
                }
            lattice = rotateMatrix(lattice);
        }

        resultText = resultText.trim();

        return resultText;
    }

}
