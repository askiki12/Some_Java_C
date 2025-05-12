import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public boolean strCombination(String longStr, String[] shortStrs) {
        /**
         * example:
         *  input:
         *      123equals123   [123, equals]
         *  output:
         *      true
         * **/
        /** WRITE YOUR CODE HERE **/
        for(String temp:shortStrs){
            while(longStr.contains(temp)){
                longStr = longStr.replaceAll(temp,"");
            }
        }
        if(longStr.isEmpty()){
            return true;
        }
        return false;
    }

//    public String cardMaster(String cards) {
//        // Return the sorted playing cards.
//        /**
//         * example:
//         *  input:
//         *      36TA
//         *  output:
//         *      AT63
//         * **/
//        /** WRITE YOUR CODE HERE **/
//        String thesequence  = new String("2AKQJT9876543");
//        String newstr = new String("");
//        StringBuilder sb = new StringBuilder(newstr);
//        int thelen = cards.length();
//        int ind = 0;
//        for(char elm:cards.toCharArray()){
//            if(newstr.isEmpty()){
//                sb.insert(0,elm);
//                ind++;
//            }
//            int kiki = ind-1;
//            while(kiki>=0){
//                char char1 = sb.charAt(kiki);
//                if(thesequence.indexOf(char1)>=thesequence.indexOf(elm)){
//                    sb.insert(kiki+1,elm);
//                    ind++;
//                    break;
//                }
//                kiki--;
//            }
//            if(kiki == -1){
//                sb.insert(0,elm);
//                ind++;
//            }
//        }
//        return sb.toString();
//    }
    public String cardMaster(String cards) {
        // Return the sorted playing cards.
        /**
         * example:
         *  input:
         *      36TA
         *  output:
         *      AT63
         * **/
        /** WRITE YOUR CODE HERE **/
        String sequence = "2AKQJT9876543";
        StringBuilder sb = new StringBuilder();
        for (char elm : cards.toCharArray()) {
            int kiki = sb.length() - 1;
            while (kiki >= 0) {
                char char1 = sb.charAt(kiki);
                if (sequence.indexOf(char1) <= sequence.indexOf(elm)) {
                    sb.insert(kiki + 1, elm);
                    break;
                }
                kiki--;
            }
            if (kiki == -1) {
                sb.insert(0, elm);
            }
        }
        return sb.toString();
    }

    public int[][] TensorUnfold(int[][][] tensor) {
        /**
         * example:
         *  input:
         *   [
         *    [[1, 2, 3],
         *     [4, 5, 6]],
         *    [[7, 8, 9],
         *     [10, 11, 12]]
         *   ]
         *  output:
         *   [[1, 2, 3, 7, 8, 9],
         *    [4, 5, 6, 10, 11, 12]]
         * **/
        /** WRITE YOUR CODE HERE **/
        int m = tensor.length;
        int n = tensor[0].length;
        int z = tensor[0][0].length;
        int[][] unfoldedMatrix = new int[n][m * z];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < z; k++) {
                    unfoldedMatrix[j][i * z + k] = tensor[i][j][k];
                }
            }
        }
        return unfoldedMatrix;
    }

    public boolean IPmatch(String IP, String subnet) {
        /** WRITE YOUR CODE HERE **/
        int[] nums = {0,0,0,0};
        int i = 0;
        //找到掩码
        int tem = subnet.indexOf('/');
        String ytem = subnet.substring(tem + 1);
        int intytem = Integer.parseInt(ytem);
        //转为二进制
        String[] parts = IP.split("\\.");
        for(String part:parts){
            if(intytem>=8){
                nums[i] = Integer.parseInt(part);
                i++;
            }
            else{
                int res = Integer.parseInt(part);
                String binaryString = Integer.toBinaryString(res);
                while (binaryString.length() < 8) {
                    binaryString = "0" + binaryString;
                }
                String newst = binaryString.substring(0,intytem);
                nums[i] = Integer.parseInt(newst);
                i++;
            }
            intytem-=8;
            if(intytem<=0){
                break;
            }
        }
        //检验相同
        i = 0;
        String[] othparts = subnet.split("[./]");
        for(String othpart:othparts){
            if(i==3){
                return true;
            }
            if(nums[i]!=Integer.parseInt(othpart)){
                return false;
            }
            i++;
        }
        return true;
    }

    public List<String> quordleCheat(List<Character> have, List<Character> haveNot, Map<Integer,Character> rightIn) {
        /**
         * example:
         *  input:
         *   have: [a, f], haveNot: [w, i], rightIn: {(4:r)}
         *  output:
         *   [afear, after, facer, fader, fager, faker, farer, fator, favor, fchar, feuar, flear, Rafer, Safar, safer, sofar, unfar]
         * **/
        /** WRITE YOUR CODE HERE **/
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
         String word;
         while ((word = br.readLine()) != null) {
             if (word.length() != 5) {
                 continue;
             }
             // Check if the word meets all the criteria
             if (meetsCriteria(word, have, haveNot, rightIn)) {
                 result.add(word);
             }
         }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    private boolean meetsCriteria(String word, List<Character> have, List<Character> haveNot, Map<Integer, Character> rightIn) {
        for (Map.Entry<Integer, Character> entry : rightIn.entrySet()) {
            if (word.charAt(entry.getKey()) != entry.getValue()) {
                return false;
            }
        }
        for (char c : have) {
            if (!word.contains(String.valueOf(c))) {
                return false;
            }
        }
        for (char c : haveNot) {
            if (word.contains(String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }
}
