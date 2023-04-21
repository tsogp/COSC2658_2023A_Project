public class SecretKey {
    private String[] correctKey;
    private int counter;

    public SecretKey() {
        // for the real test, your program will not know this
        correctKey = new String []{"TMRMTTITMIMRTRIT",
                "ITRIMIIRMMITRMRI",
                "MRMRMMIMIRRRTITT",
                "MMIITTIIITITRIRM",
                "MMMITTTRRMRTTRII",
                "RITTRRRITRTRRTMT",
                "RRMMRRMMRRRTRMMT",
                "RMIRMRTMTMRMTIIM",
                "MIMRIMRIMMMTMRII",
                "MMMRRRMTRTTIMMII",
                "RIMRMRMTTMMTMITI",
                "TTIITIIIRTMITTMR",
                "RRMRRIMIRRRTTMRI",
                "IRTMMMTTMIIIRRRI",
                "MMMMMRIRMIMIIRRI",
                "ITTRTMRMTMMIMRRR",
                "MTMITIRRIRIRMMMM",
                "IMTRRRTRIIMTRRTI",
                "TTRTTRRMRTRMMIMR",
                "IRRMTIMRMTMRMTMM",
                "TITTTTTTMMMRMRRM",
                "MTIITIIMMIITMTIR",
                "RMIRITRIMTIRRTTI",
                "TIRITMMMMMIRRMRI",
                "MIMRTTRRMTMMIMRM",
                "MITMIRMTIMMMMMRR",
                "RMRITITTRRMMMRMM",
                "TIRMMIITRMRTMRIR",
                "IMTIMTRRMTMIITTR",
                "TITTMTITIRIIIMRI",
                "ITMTTIITIMTMITIR",
                "IRMIITRIMRTRTMTT",
                "MMTTITTMRIMTRRMM",
                "RTRMMMTMRMTTTIII",
                "MMMRTRTRRTMRITTR",
                "TTIMMIRMTIMMTRMI",
                "MTMIMTTIIIIIITIR",
                "TRMTIIRMIMIRMIMR",
                "RRIRTMTTRTMMMRRM",
                "RTRRRTRMMMTRMMMR",
                "MTIRIMIIITRIIIMT",
                "MRTMTRTMTTTIIMTT",
                "IMRRRMMIMIIRTTMR",
                "IRTMTTIRIIMIIIRT",
                "RTMRRRRIIIMRIRTM",
                "RIMMMTIIRMTRTRRT",
                "TMRIRMRIIRMRTRTI",
                "MITRTIMMRTTIMIRT",
                "RMIMIIIIITTIMMTI",
                "MRTRTTMTMRRRITTM",
                "RMIMRMMIIRMIMRRR",
                "RRIMRRMRTTMMIIMR",
                "MTMRTTTTIMIIRRIM",
                "ITRMTTRRTITMTTMR",
                "TMMMMMRTITRRTMTT",
                "IRMIIIRIRTTRTIIR",
                "TTTTRTRIRMMTITTT",
                "TTMTMRRTIITMIRII",
                "TRRTRIIIRTIRIMTT",
                "MRMRRMTITTIIRMMM",
                "TRTRRRRRMMITMMMR",
                "TTRMIRMMMMRTRRMT",
                "RRIIIITTRRIIMRMM",
                "TITMITTMRIIIMMIT",
                "TTTRIMRTMRIIRMIM",
                "TITMTIMRTTRIRMIT",
                "RTIRMRTTMMRRTRII",
                "IRRRIMMTIIRTIITI",
                "MIRMRMTMTRMMRTMT",
                "MRRMMTIRMRIRIMTI",
                "TMRTMRRIMMTIRIRT",
                "RMTTRIIRTRRRMRTI",
                "RMMTRRITITRRTMTI",
                "RRMMITIRRMTRRIRT",
                "ITTIIMIMMRITRRTI",
                "IITMMITRMMIRTTMT",
                "RTRIRIIIMTIMTTTT",
                "RMTMMTTMRRRTRIRR",
                "MRMMRRMTITTTMIIM",
                "MTMRTRIIRIMRMRMR",
                "IMTIIRTTTIRRTTRI",
                "TIMTMRRTTRTMTRRM",
                "RRMIRMMITMTITITI",
                "TMRMTMRTMIIMMITM",
                "MTIIMIIIRIMITTTI",
                "IRITTMMMMRTIIITI",
                "MRTIIIMMMMRTRITT",
                "TITTMRTTTTTMIMRM",
                "TMMMITRRITTIRRRI",
                "RIIMIRTRRMRITITR",
                "RRRIRTMTIMRTIIRI",
                "ITIRIIRTMTMTIRII",
                "IMRIRIRMRRTITRRI",
                "TIRRIMMTRMITTMMI",
                "TITRTTIMIMRITMRI",
                "RMTTIMTIMMMMTIMM",
                "TTRTTTMTIRRTTTMI",
                "TMTMMMIMRIIRMIMR",
                "RIIRTTITRRITMRRT",
                "RIIIMRTTRRMIITRI",};
        counter = 0;
    }

    public int guess(String guessedKey) {
        double average = 0;
        int superCounter = 0;
            counter++;
            System.out.println("Number of guesses: " + counter + " " + guessedKey);
            // validation
            if (guessedKey.length() != correctKey[0].length()) {
                return -1;
            }
            int matched = 0;
            for (int i = 0; i < guessedKey.length(); i++) {
                char c = guessedKey.charAt(i);
                if (c != 'R' && c != 'M' && c != 'I' && c != 'T') {
                    return -1;
                }
                if (c == correctKey[0].charAt(i)) {
                    matched++;
                }
            }
            if (matched == 16) {
                System.out.println("Number of guesses: " + counter);
                average += counter;
            }
            return matched;
        System.out.println("Average guess: " + average);
        return 0;
    }

    public static void main(String[] args) {
        new SecretKeyGuesser().start();
    }
}
