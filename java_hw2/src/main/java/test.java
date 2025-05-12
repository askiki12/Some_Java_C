import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class test {
    public static String cardMaster(String cards) {
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

    public static boolean IPmatch(String IP, String subnet) {
        /** WRITE YOUR CODE HERE **/
        int[] nums1 = {0,0,0,0};
        int[] nums2 = {0,0,0,0};
        int i = 0;
        //找到掩码
        int tem = subnet.indexOf('/');
        String ytem = subnet.substring(tem + 1);
        int intytemall = Integer.parseInt(ytem);
        int intytem = intytemall;
        //转为二进制
        String[] parts = IP.split("\\.");
        for(String part:parts){
            if(intytem>=8){
                nums1[i] = Integer.parseInt(part);
                i++;
            }
            else{
                int res = Integer.parseInt(part);
                int ki = 8 - intytem;
                res = res>>ki;
                res = res<<ki;
                nums1[i] = res;
                i++;
            }
            intytem-=8;
            if(intytem<=0){
                break;
            }
        }
        //检验相同
        i = 0;
        intytem = intytemall;
        String[] othparts = subnet.split("[./]");
        for(String othpart:othparts){
            if(intytem>=8){
                nums2[i] = Integer.parseInt(othpart);
                i++;
            }
            else{
                int res = Integer.parseInt(othpart);
                int ki = 8 - intytem;
                res = res>>ki;
                res = res<<ki;
                nums2[i] = res;
                i++;
            }
            intytem-=8;
            if(intytem<=0){
                break;
            }
        }
        i = 0;
        while (i<4){
            if(nums1[i]!=nums2[i]){
                return false;
            }
            i++;
        }
        return true;
    }
    public static void main(String[] args){
//        String str = "Hello, world! Hello, everyone!";
//        String replacedStr = str.replaceAll("Hello", "Hi");// 输出: "Hi, world! Hi, everyone!"
////        System.out.printf(replacedStr);
//        StringBuilder sb = new StringBuilder("Hello");
//        sb.insert(0, '!'); // 在位置5插入字符'!'
//        sb.insert(6, " world"); // 在位置6插入字符串" world"
//        System.out.println(sb.toString()); // 输出: "Hello world!"
//        String str = cardMaster("36TA");
//        System.out.println(str);
//        int[][][] ten = [[[1, 2, 3],[4, 5, 6]],[[7, 8, 9],[10, 11, 12]]];
        if(IPmatch("192.168.3.100","192.168.2.0/23")){
            System.out.println("nihao");
        }
    }
}
