package project;

public class SecretKeyGuesser {
    private SecretKey key;
    private String str;
    private int corrected = 0;
    private int[] occ;

    private boolean isFound() {
        return this.corrected == 16;
    }
    private void foundKey(String key) {
        System.out.println("I found the secret key. It is " + key);
    }

    public SecretKeyGuesser() {
        this.key = new SecretKey();
        this.str = "RRRRRRRRRRRRRRRR";
        this.occ = new int[4];

        final String[] basicKeys = {"RRRRRRRRRRRRRRRR", "MMMMMMMMMMMMMMMM", "IIIIIIIIIIIIIIII", "TTTTTTTTTTTTTTTT"};
        for (int i = 0; i < 4; i++) {
            occ[i] = key.guess(basicKeys[i]);
            if (occ[i] == 16) {
                foundKey(basicKeys[i]);
                return;
            }

            if (occ[i] > corrected) {
                corrected = occ[i];
            }
        }
    }

    public void start() {
        int largest_occ = 0, sec_occ = 0, min = 100, sec_min = 100;
        if (occ[0]>= occ[1])  {
            largest_occ = 0;
            sec_occ = 1;
            sec_min = 0;
            min = 1;
        } else {
            largest_occ = 1;
            sec_occ = 0;
            sec_min = 1;
            min = 0;
        }

        for(int i = 2; i<=3;i++){
            if(occ[i] < occ[min]){
                sec_min = min;
                min = i;
            } else if (occ[i] < occ[sec_min]) {
                sec_min = i;
            }

            if(occ[i] >= occ[largest_occ]){
                sec_occ = largest_occ;
                largest_occ = i;
            } else if (occ[i] >= occ[sec_occ]) {
                sec_occ = i;
            }
        }

        if (largest_occ == 0){
            str = "RRRRRRRRRRRRRRRR";
        } else if (largest_occ == 1) {
            str = "MMMMMMMMMMMMMMMM";
        } else if (largest_occ == 2) {
            str = "IIIIIIIIIIIIIIII";
        } else {
            str = "TTTTTTTTTTTTTTTT";
        }

        int firstIndex = (corrected == 15) ? sec_occ : sec_min;
        // new modified approach
        int[] tmp_arr = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        for (int j = 1; j <= occ[firstIndex]; j++) {
            for (int k = 15; k >= 0; k--) {
                char[] curr = str.toCharArray();
                if (curr[k] != charOf(largest_occ) || tmp_arr[k] != 0){
                    continue;
                }

                curr[k] = charOf(firstIndex);
                int temp = key.guess(String.valueOf(curr));
                if (temp > corrected){
                    str = String.valueOf(curr);
                    corrected = temp;

                    if (isFound()) {
                        foundKey(str);
                        return;
                    }
                    break;
                } else if (temp < corrected){
                    tmp_arr[k] = 1;
                } else {
                    tmp_arr[k] = 2;
                }
            }
        }

        for (int k = 15; k >= 0; k--) {
            char[] curr = str.toCharArray();
            if (curr[k] != charOf(largest_occ) || tmp_arr[k] == 1){
                continue;
            }

            curr[k] = charOf(min);
            int temp = key.guess(String.valueOf(curr));
            if (temp > corrected){
                str = String.valueOf(curr);
                corrected = temp;

                if (isFound()) {
                    foundKey(str);
                    return;
                }
            } else if(temp < corrected){
                tmp_arr[k] = 1;
            } else if (temp == corrected) {
                curr[k] = charOf(sec_occ);
                str = String.valueOf(curr);
                corrected++;
            }
        }

        key.guess(str);
        foundKey(str);
    }

    static int order(char c) {
        if (c == 'R') {
            return 0;
        } else if (c == 'M') {
            return 1;
        } else if (c == 'I') {
            return 2;
        }
        return 3;
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