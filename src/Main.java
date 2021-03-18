import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner ScannerInt = new Scanner(System.in);
        Scanner ScannerString = new Scanner(System.in);

        System.out.print("Input your string: ");
        String StringForEncrypt = ScannerInt.nextLine();

        System.out.print("Input your integer key: ");
        int intKey = ScannerInt.nextInt();

        System.out.print("Input your string key: ");
        String stringKey = ScannerString.nextLine();

        Rails.start(StringForEncrypt, intKey);

        Column.start(StringForEncrypt, stringKey);

        Vigener.start(StringForEncrypt, stringKey);

        Lattice.start(StringForEncrypt);
    }
}
