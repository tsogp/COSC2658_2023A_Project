public class SecretKeyGuesser {
    public void start() {

        // create a new secret key object
        SecretKey key = new SecretKey();

        // arrays of possible values {R, M, I, T}
        String[] possibleValues = new String[]{"R", "M", "I", "T"};

        // guessed
        StringBuilder guessedKeyR = new StringBuilder(possibleValues[0].repeat(16));
        StringBuilder guessedKeyT = new StringBuilder(possibleValues[3].repeat(16));
        // check if the secret key contains only one key
        for (String i : possibleValues) {
            if (key.guess(i.repeat(16)) == 16) {
                System.out.println("I found the secret key. It is " + i.repeat(16));
                return;
            }
        }

        // count the number of each key in the secret key
        int[] numberOfEachKey = {
                key.guess(possibleValues[0].repeat(16)),
                key.guess(possibleValues[1].repeat(16)),
                key.guess(possibleValues[2].repeat(16)),
                key.guess(possibleValues[3].repeat(16))
        };

        // Get the position of each key
        int[][] positionOfEachKey = new int[4][4];
        positionOfEachKey[0] = SecretKeyGuesser.getPositionOfCharacter(guessedKeyT, key, numberOfEachKey, 'R');
        positionOfEachKey[1] = SecretKeyGuesser.getPositionOfCharacter(guessedKeyT, key, numberOfEachKey, 'M');
        positionOfEachKey[2] = SecretKeyGuesser.getPositionOfCharacter(guessedKeyT, key, numberOfEachKey, 'I');
        positionOfEachKey[3] = SecretKeyGuesser.getPositionOfCharacter(guessedKeyR, key, numberOfEachKey, 'T');

        // Assign to a new String according to the index found above
        char[] guessedKey = new char[16];
        for (int i = 0; i < positionOfEachKey.length; i++) {
            for (int j = 0; j < positionOfEachKey[i].length; j++) {
                if (positionOfEachKey[i][j] == 1) {
                    guessedKey[j] = charOf(i);
                }
            }
        }

        if (key.guess(new String(guessedKey)) == 16) {
            System.out.println("The string is: " + new String(guessedKey));
        }
    }

    static char charOf(int order) {
        if (order == 0) {
            return 'R';
        } else if (order == 1) {
            return 'M';
        } else if (order == 2) {
            return 'I';
        }
        return 'T';
    }

    public static int[] getPositionOfCharacter(StringBuilder guessedKey, SecretKey key, int[] numberOfEachKey, char character) {
        int[] position = new int[16];
        char tmp = guessedKey.charAt(0);
        for (int i = 0; i < guessedKey.length(); i++) {
            guessedKey.setCharAt(i, character);
            if (key.guess(String.valueOf(guessedKey)) > numberOfEachKey[3]) {
                position[i]++;
            }
            guessedKey.setCharAt(i, tmp);
        }
        return position;
    }
}
