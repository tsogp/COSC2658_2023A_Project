package project;

public class SecretKeyGuesser {
    public void start() {

        SecretKey key = new SecretKey();

        String str = "RRRRRRRRRRRRRRRR";
        int corrected = 0;

        while (corrected != 16) {
            int[] occ = new int[4];

            occ[0] = corrected = key.guess("RRRRRRRRRRRRRRRR");
            if(corrected == 16){
                System.out.println("I found the secret key. It is RRRRRRRRRRRRRRRR");
                return;
            }
            occ[1] = corrected = key.guess("MMMMMMMMMMMMMMMM");
            if(corrected == 16){
                System.out.println("I found the secret key. It is MMMMMMMMMMMMMMMM");
                return;
            }
            occ[2] = corrected = key.guess("IIIIIIIIIIIIIIII");
            if(corrected == 16){
                System.out.println("I found the secret key. It is IIIIIIIIIIIIIIII");
                return;
            }
            occ[3] = corrected = key.guess("TTTTTTTTTTTTTTTT");
            if(corrected == 16){
                System.out.println("I found the secret key. It is TTTTTTTTTTTTTTTT");
                return;
            }


            int largest_occ = 0, sec_occ = 0, min = 100 , sec_min = 100;

            if(occ[0]>= occ[1]){
                largest_occ = 0;
                sec_occ = 1;
                sec_min = 0;
                min = 1;
            }else{
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



            if(largest_occ == 0){
                str = "RRRRRRRRRRRRRRRR";
            } else if (largest_occ == 1) {
                str = "MMMMMMMMMMMMMMMM";
            }else if (largest_occ == 2) {
                str = "IIIIIIIIIIIIIIII";
            }else {
                str = "TTTTTTTTTTTTTTTT";
            }

            corrected = occ[largest_occ];

//            for (int i = 0; i < 4; i ++){
//                if(i == largest_occ){
//                    continue;
//                }
//
//                for(int j = 1; j <= occ[i]; j++){
//                    for(int k = 15; k >=0; k-- ){
//                        char[] curr = str.toCharArray();
//                        if(curr[k] != charOf(largest_occ)){
//                            continue;
//                        }
//                        curr[k] = charOf(i);
//                        int temp = key.guess(String.valueOf(curr));
//                        if(temp > corrected){
//                            str = String.valueOf(curr);
//                            corrected = temp;
//                            break;
//                        }
//                    }
//                }
//            }

            //new modified approach
            int[] tmp_arr = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

            for(int j = 1; j <= occ[sec_min]; j++){
                for(int k = 15; k >=0; k-- ){
                    char[] curr = str.toCharArray();
                    if(curr[k] != charOf(largest_occ) || tmp_arr[k] != 0){
                        continue;
                    }
                    curr[k] = charOf(sec_min);
                    int temp = key.guess(String.valueOf(curr));
                    if(temp > corrected){
                        str = String.valueOf(curr);
                        corrected = temp;
                        break;
                    }else if(temp < corrected){
                        tmp_arr[k] = 1;
                    }else {
                        tmp_arr[k] = 2;
                    }
                }
            }

            //for(int j = 1; j <= occ[min]; j ++){
                for(int k = 15; k >=0; k-- ){
                    char[] curr = str.toCharArray();
                    if(curr[k] != charOf(largest_occ) || tmp_arr[k] == 1){
                        continue;
                    }
                    curr[k] = charOf(min);
                    int temp = key.guess(String.valueOf(curr));
                    if(temp > corrected){
                        str = String.valueOf(curr);
                        corrected = temp;
                        if(corrected == 16){
                            System.out.println("I found the secret key. It is " + str);
                            return;
                        }
//                        if(j != occ[min]){
//                            break;
//                        }
                    }else if(temp < corrected){
                        tmp_arr[k] = 1;
                    } else if (temp == corrected) {
                        curr[k] = charOf(sec_occ);
                        str = String.valueOf(curr);
                        corrected++;
                    }
                }
            //}

        }
        key.guess(str);
        //System.out.println("Number of guesses: " + key.counter);
        System.out.println("I found the secret key. It is " + str);
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

    // return the next value in 'RMIT' order, that is
    // R < M < I < T
    public String next(String current) {
        char[] curr = current.toCharArray();
        for (int i = curr.length - 1; i >=0; i--) {
            if (order(curr[i]) < 3) {
                // increase this one and stop
                curr[i] = charOf(order(curr[i]) + 1);
                break;
            }
            curr[i] = 'R';
        }
        return String.valueOf(curr);
    }
}
