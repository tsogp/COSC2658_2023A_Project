
public class SecretKeyGuesser {

    // Generate new SecretKey object to access the guess() method
    // As we are not allowed to modify the SecretKey class's object -> we marked it as final
    private final SecretKey key;
    private String str;

    // Number of corrected key
    private int corrected = 0;

    // The number of occurrence of each key
    private final int[] occurrence;

    // Constructor
    public SecretKeyGuesser() {
        this.key = new SecretKey();
        this.str = "R".repeat(16);
        this.occurrence = new int[4];

        // Create an array of string where each index will be a key repeating 16 times
        final String[] basicKeys = {"R".repeat(16), "M".repeat(16), "I".repeat(16), "T".repeat(16)};

        for (int i = 0; i < 4; i++) {
            occurrence[i] = key.guess(basicKeys[i]);

            // Return true if the key is in 'basicKeys'
            if (occurrence[i] == 16) {
                secretKey(basicKeys[i]);
                return;
            }

            // Find the most occurrence
            if (occurrence[i] > corrected) {
                corrected = occurrence[i];
            }
        }
    }

    // Return true if the key is found
    private boolean isFound() {
        return this.corrected == 16;
    }

    // Print out the secret key
    private void secretKey(String key) {
        System.out.println("I found the secret key. It is " + key);
    }

    public void start() {

        // Find the key with largest, 2nd largest, least and 2nd least occurrence
        int mostOccurrence, secondOccurrence, thirdOccurrence, leastOccurrence;
        if (occurrence[0] >= occurrence[1]) {
            mostOccurrence = 0;
            secondOccurrence = 1;
            thirdOccurrence = 0;
            leastOccurrence = 1;
        } else {
            mostOccurrence = 1;
            secondOccurrence = 0;
            thirdOccurrence = 1;
            leastOccurrence = 0;
        }

        // find max and second max algorithm
        for (int i = 2; i <= 3; i++) {
            if (occurrence[i] < occurrence[leastOccurrence]) {
                thirdOccurrence = leastOccurrence;
                leastOccurrence = i;
            } else if (occurrence[i] < occurrence[thirdOccurrence]) {
                thirdOccurrence = i;
            }

            if (occurrence[i] >= occurrence[mostOccurrence]) {
                secondOccurrence = mostOccurrence;
                mostOccurrence = i;
            } else if (occurrence[i] >= occurrence[secondOccurrence]) {
                secondOccurrence = i;
            }
        }

        if (mostOccurrence == 0) {
            str = "R".repeat(16);
        } else if (mostOccurrence == 1) {
            str = "M".repeat(16);
        } else if (mostOccurrence == 2) {
            str = "I".repeat(16);
        } else {
            str = "T".repeat(16);
        }

        int firstIndex = ((occurrence[leastOccurrence] == 0) && (occurrence[thirdOccurrence] == 0)) ? secondOccurrence : leastOccurrence;
        // new modified approach
        int[] tmpArr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};


        for (int j = 1; j <= occurrence[firstIndex]; j++) {
            for (int k = 15; k >= 0; k--) {
                char[] current = str.toCharArray();
                if (current[k] != charOf(mostOccurrence) || tmpArr[k] != 0) {
                    continue;
                }

                current[k] = charOf(firstIndex);
                int temp = key.guess(String.valueOf(current));
                if (temp > corrected) {
                    str = String.valueOf(current);
                    corrected = temp;

                    if (isFound()) {
                        secretKey(str);
                        return;
                    }
                    break;
                } else if (temp < corrected) {
                    tmpArr[k] = 1;
                } else {
                    tmpArr[k] = 2;
                }
            }
        }


        for (int k = 15; k >= 0; k--) {
            char[] current = str.toCharArray();
            if (current[k] != charOf(mostOccurrence) || tmpArr[k] == 1) {
                continue;
            }

            current[k] = charOf(thirdOccurrence);
            int temp = key.guess(String.valueOf(current));
            if (temp > corrected) {
                str = String.valueOf(current);
                corrected = temp;

                if (isFound()) {
                    secretKey(str);
                    return;
                }
            } else if (temp < corrected) {
                tmpArr[k] = 1;
            } else {
                current[k] = charOf(secondOccurrence);
                str = String.valueOf(current);
                corrected++;
            }
        }

        key.guess(str);
        secretKey(str);
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
}

