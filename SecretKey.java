package project;

public class SecretKey {
    private String correctKey;
    private int counter;

    public SecretKey() {
        // for the real test, your program will not know this
        correctKey = "RRRRRRRRRRRRRRRI";
        counter = 0;
    }

    public int guess(String guessedKey) {
        counter++;
        System.out.println("Number of guesses: " + counter + " " + guessedKey);
        // validation
        if (guessedKey.length() != correctKey.length()) {
            return -1;
        }
        int matched = 0;
        for (int i = 0; i < guessedKey.length(); i++) {
            char c = guessedKey.charAt(i);
            if (c != 'R' && c != 'M' && c != 'I' && c != 'T') {
                return -1;
            }
            if (c == correctKey.charAt(i)) {
                matched++;
            }
        }
        if (matched == 16) {
            System.out.println("Number of guesses: " + counter);
        }
        return matched;
    }

    public static void main(String[] args) {
        new SecretKeyGuesser().start();
    }
}
