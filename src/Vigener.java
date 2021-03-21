public class Vigener {

    private static final String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void start(String string, String key) {
        System.out.println("\n");
        System.out.println("Vigener encryption method: ");
        System.out.println(Encrypt(string, key));
        System.out.println("decryption: ");
        System.out.println(Decrypt(Encrypt(string, key), key));
        System.out.println("\n");
    }

    private static String GenerateSecretKey(String StringForWork, String key) {

        StringBuilder resultKey = new StringBuilder();

        String withoutSpacesString = StringForWork.replaceAll("\\s+", "");

        for (int i = 0; i < StringForWork.length(); i++) {
            if (i >= key.length()) {
                int CharIndex = (i - key.length()) % withoutSpacesString.length();
                resultKey.append(withoutSpacesString.charAt(CharIndex));
            }
            else {
                resultKey.append(key.charAt(i));
            }
        }

        return resultKey.toString();

    }

    private static int GetIndexFromAlphabet(char symbol) {
        return Alphabet.indexOf(symbol);
    }

    private static char GetCharFromAlphabet(int ind) {
        return Alphabet.charAt(ind);
    }

    private static String Encrypt(String toEncrypt, String key) {

        StringBuilder resultText = new StringBuilder();

        int AlphabetLength = Alphabet.length();

        String newKey = GenerateSecretKey(toEncrypt, key);

        for (int i = 0; i < toEncrypt.length(); i++) {
            int p = GetIndexFromAlphabet(toEncrypt.charAt(i));
            int k = GetIndexFromAlphabet(newKey.charAt(i));
            int charIndex = (p + k) % AlphabetLength;
            resultText.append(GetCharFromAlphabet(charIndex));
        }

        return resultText.toString();

    }

    private static String Decrypt(String toDecrypt, String key) {

        StringBuilder resultText = new StringBuilder();

        int AlphabetLength = Alphabet.length();

        String NewKey = "";

        for (int i = 0; i < toDecrypt.length(); i++){
            int c = GetIndexFromAlphabet(toDecrypt.charAt(i));   //Получение индекса символа шифротекста
            int k;
            if (i >= key.length()) {
                k = GetIndexFromAlphabet(resultText.charAt((i - key.length())));
            }
            else {
                k = GetIndexFromAlphabet(key.charAt(i));
            }
            NewKey += GetCharFromAlphabet(k);
            int charIndex = (c - k + AlphabetLength) % AlphabetLength;
            resultText.append(GetCharFromAlphabet(charIndex));
        }

        return resultText.toString();

    }

}
