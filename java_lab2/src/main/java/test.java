import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.List;

public class test {
//    public static void printMatrix(int[][] mat) {
//        /**
//         * example:
//         *  input:
//         *      1 2 3 4
//         *      10 11 12 5
//         *      9 8 7 6
//         *  print:
//         *      1 2 3 4 5 6 7 8 9 10 11 12
//         * **/
//        /** WRITE YOUR CODE HERE **/
//        int len1 = mat.length;
//        int len2 = mat[0].length;
//        int[] nums = new int[len1*len2+100];
//        int k=0;
//        int m=0;
//        while(k< len1 /2){
//            for(int i = k;i<len2-k;i++){
//                nums[m++] = mat[k][i];
//            }
//            for(int i = k+1;i<len1-k;i++){
//                nums[m++] = mat[i][len2-k-1];
//            }
//            for(int i = len1-k-2;i>=k;i--){
//                if(k+1<len1-k){
//                nums[m++] = mat[len1-k-1][i];
//                }
//            }
//            for(int i = len1-k-2;i>k;i--){
//                if(len1-k-1>=k){
//                nums[m++] = mat[i][k];
//                }
//            }
//            k++;
//        }
//        for(int i=0;i<len1*len2;i++){
//            if(i!=len1*len2-1){
//            System.out.print(nums[i]+" ");
//            }
//            else{
//                System.out.print(nums[i]);
//            }
//        }
//    }

    public static void printMatrix(int[][] mat) {
        int len1 = mat.length;
        int len2 = mat[0].length;
        int[] nums = new int[len1 * len2];
        int m = 0;
        int top = 0, bottom = len1 - 1, left = 0, right = len2 - 1;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                nums[m++] = mat[top][i];
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                nums[m++] = mat[i][right];
            }
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    nums[m++] = mat[bottom][i];
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    nums[m++] = mat[i][left];
                }
                left++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != nums.length - 1) {
                System.out.print(nums[i] + " ");
            } else {
                System.out.print(nums[i]);
            }
        }
    }

    public static String hex2Bin(String num) {
        /** WRITE YOUR CODE HERE **/
        String ret = new String();
        StringBuilder nuewtr = new StringBuilder(num);
        String[] hexToBinary = {
                "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111",
                "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"
        };
        String sixnum = "0123456789abcdef";
        int num_len = num.length();
        for(int i = 0;i<num_len;i++){
            char thenum = num.charAt(i);
            if(i!=num_len-1){
                ret = ret + hexToBinary[sixnum.indexOf(thenum)]+" ";
            }
            else{
                ret = ret + hexToBinary[sixnum.indexOf(thenum)];
            }
        }
        return ret;
    }

    public static String strCompression(String str){
        //Returns the compressed string
        /** WRITE YOUR CODE HERE **/
        String ret = new String();
        if(str.isEmpty()) {
            return ret;
        }
        int lenlen = str.length();
        if(lenlen == 1){
            return str;
        }
        int index = 0;
        int i = 1;
        while(index < lenlen-1){
            if(str.charAt(index)==str.charAt(index+1)){
                i++;
                index++;
            }
            else{
                ret = ret + str.charAt(index) + i;
                index++;
                i = 1;
            }
        }
        if(str.charAt(index)==str.charAt(index-1)){;
            ret = ret + str.charAt(index) + i;
        }
        else {
            ret = ret + str.charAt(index) + 1;
        }
        if(ret.length()>=lenlen){
            return str;
        }
        return ret;
    }

    public static int strMatching (String str1, String str2) {
        //Returns the index
        /** WRITE YOUR CODE HERE **/
        int len1 = str1.length();
        int len2 = str2.length();
        for (int i = 0; i < len1-len2; i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                int flag = 1;
                for (int j = 0; j < len2; j++) {
                    if (str1.charAt(i + j) != str2.charAt(j)) {
                        flag = 0;
                    }
                }
                if (flag == 1) {
                    return i;
                }
            }
        }
        return -1; //Delete this line
    }

    private static String getPinyin(char character){
        String pinyinStr = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        if (character > 128) {
            try {
                String[] strings= PinyinHelper.toHanyuPinyinStringArray(character, defaultFormat);
                if (strings.length==0){
                    return "";
                }
                pinyinStr = strings[0];
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        }else{
            return "";
        }
        return pinyinStr;
    }

    public static List<String> kyzhyz(List<String> words) {
        //Return the Chinese phrase with the format "ky,zhyz" or "kyzhyz" in the input.
        /**
         * input:
         *      {"坎勇，最会游走！","科比，这很硬肘","肉包：狂羊，真狠一撞","狂野震撼亚洲！！","不可以。总会有这样的。"，,"堪忧，最后一"}
         * return:
         *      {"坎勇，最会游走","狂羊，真狠一撞","狂野震撼亚洲"}
         * */
        /** WRITE YOUR CODE HERE **/
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (containsKyZhyz(word)) {
                result.add(word);
            }
        }
        return result;
    }

    private static boolean containsKyZhyz(String word){
        StringBuilder pinyinBuilder = new StringBuilder();
        for (char ch : word.toCharArray()) {
            String pinyin = getPinyin(ch);
            if (!pinyin.isEmpty()) {
                pinyinBuilder.append(pinyin.charAt(0));
            }
        }
        String pinyinStr = pinyinBuilder.toString();
        return pinyinStr.contains("kyzhyz") || pinyinStr.contains("ky,zhyz");
    }

    public static void main(String[] args){
        String kiki = new String("kiki is a \nhandsome boy");
        System.out.print(kiki);
    }
}
