import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Problem 1
 * */
interface GeometricObject {
    public double getPerimeter();
    public double getArea();
}

class Circle implements GeometricObject {
    // Protected variable
    /** WRITE YOUR CODE HERE **/
    protected double radius;
    // Constructor
    /** WRITE YOUR CODE HERE **/
    Circle(double radius){
        this.radius = radius;
    }

    // Implement toString method
    public String toString() {
        /**（保留一位小数）
         * output: Circle[radius=2.5]
         * */
        /** WRITE YOUR CODE HERE **/
        return String.format("Circle[radius=%.1f]", radius);
    }

    // Implement methods defined in the interface GeometricObject
    @Override
    public double getPerimeter() {
        /** WRITE YOUR CODE HERE **/
        return 2 * Math.PI * radius;
    }
    @Override
    public double getArea() {
        /** WRITE YOUR CODE HERE **/
        return Math.PI * radius * radius;
    }

}

interface Resizable {
    public void resize(int percent);
}

class ResizableCircle extends Circle implements Resizable {
    // Constructor
    /** WRITE YOUR CODE HERE **/
    ResizableCircle(double radius){
        super(radius);
    }

    // Reimplement toString method
    public String toString() {
        /**（保留一位小数）
         * output: ResizableCircle[Circle[radius=2.5]]
         * */
        /** WRITE YOUR CODE HERE **/
        return String.format("ResizableCircle[Circle[radius=%.1f]]", radius);
    }

    // Implement methods defined in the interface Resizable
    @Override
    public void resize(int percent) {
        radius = radius *  (percent / 100.0);
    }
}

/**
 * Problem 2
 * */
class MyDate {
    int year;
    int month;
    int day;

    public MyDate(int day, int month, int year) {
        /* WRITE YOUR CODE HERE **/
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public boolean isLeapYear(){
        /* WRITE YOUR CODE HERE **/
        if(year%400==0||(year % 4 == 0 && year % 100 != 0)){
            return true;
        }
        return false;
    }
    public boolean isValidDate(){
        /* WRITE YOUR CODE HERE **/
        if (year < 1 || month < 1 || month > 12 || day < 1) {
            return false;
        }
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear()) {
            daysInMonth[1] = 29;
        }
        if (day > daysInMonth[month - 1]) {
            return false;
        }
        return true;
    }
    public void setDate(MyDate date){
        /* WRITE YOUR CODE HERE **/
        this.day = date.day;
        this.month = date.month;
        this.year = date.year;
    }
    public String toStringDate(){
        /* WRITE YOUR CODE HERE **/
        return String.format("%d %d %d", day, month, year);
    }

}

/**
 * Problem 3
 * */
class Trie {
    // add members if you need! you can also define a inner class here

    private class TrieNode {
        Character the_word;
        List<TrieNode> child;

        TrieNode() {
            the_word = null;
            child =  new ArrayList<>();
        }
        public Character get_word(){
            return the_word;
        }
        public List<TrieNode> get_child(){
            return child;
        }
        public boolean is_end(){
            return child.isEmpty();
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public boolean search(String s) {
        /** WRITE YOUR CODE HERE **/
        return help_search(s,root);
    }

    public boolean help_search(String s,TrieNode search){
        if(s.isEmpty()) {
            return true;
        }
        for (TrieNode nd : search.child) {
            if (nd.the_word == s.charAt(0)){
                return help_search(s.substring(1),nd);
            }
        }
        return false;
    }

    public void add(String s) {
        TrieNode pos = root;
        int i = 0;
        while (i < s.length()) {
            boolean found = false;
            for (TrieNode nd : pos.child) {
                if (nd.the_word != null && nd.the_word.equals(s.charAt(i))) {
                    found = true;
                    pos = nd;
                    i++;
                    break;
                }
            }
            if (!found) {
                break;
            }
        }
        while (i < s.length()) {
            TrieNode newNode = new TrieNode();
            newNode.the_word = s.charAt(i);
            pos.child.add(newNode);
            pos = newNode;
            i++;
        }
    }


    public List<String> serialize() {
        List<String> result = new ArrayList<>();
        serializeHelper(root, new StringBuilder(), result);
        return result;
    }

    private void serializeHelper(TrieNode node, StringBuilder path, List<String> result) {
        if (node.the_word != null) {
            path.append(node.the_word);
        }
        if (node.is_end()) {
            result.add(path.toString());
        } else {
            for (TrieNode child : node.child) {
                serializeHelper(child, path, result);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }
}

//class Trie {
//    // add members if you need! you can also define a inner class here
//
//    private class Node{
//
//        public boolean isWord; // 是否是某个单词的结束
//
//        public HashMap<Character, Node> next; //到下一个节点的映射
//
//        public Node(boolean isWord){
//            this.isWord = isWord;
//            //初始化字典树
//            next = new HashMap<>();
//        }
//
//        public Node(){
//            this(false);
//        }
//    }
//
//    //根节点
//    private Node root;
//    //Trie单词个数
//    private int size;
//
//    public Trie(){
//        root = new Node();
//        size = 0;
//    }
//
//    public boolean search(String s) {
//        /** WRITE YOUR CODE HERE **/
//        Node cur = root;
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (cur.next.get(c) == null) {
//                return false;
//            } else {
//                cur = cur.next.get(c);
//            }
//        }
//        return cur.isWord;
//    }
//
//    public void add(String s) {
//        /** WRITE YOUR CODE HERE **/
//        Node cur = root;
//        //循环判断新的cur节点是否包含下一个字符到下一个节点的映射
//        for(int i = 0 ; i < s.length() ; i++){
//            //将c当成一个节点插入Trie中
//            char c = s.charAt(i);
//            //判断cur.next是不是已经指向我们要找的c字符相应的节点
//            if(cur.next.get(c) == null){
//                //新建节点
//                cur.next.put(c, new Node());
//            }
//            //否则，就直接走到该节点位置即可
//            cur = cur.next.get(c);
//        }
//        //判断该单词并不表示任何一个单词的结尾
//        if(!cur.isWord) {
//            //确定cur是新的单词
//            cur.isWord = true;
//            size++;
//        }
//    }
//
//    public List<String> serialize() {
//        List<String> result = new ArrayList<>();
//        serializeHelper(root, new StringBuilder(), result);
//        return result;
//    }
//
//    // 帮助序列化的递归函数
//    private void serializeHelper(Node node, StringBuilder path, List<String> result) {
//        if (node.isWord) {
//            result.add(path.toString());
//        }
//        for (Map.Entry<Character, Node> entry : node.next.entrySet()) {
//            path.append(entry.getKey());
//            serializeHelper(entry.getValue(), path, result);
//            path.deleteCharAt(path.length() - 1);
//        }
//    }
//}

/**
 * Problem 4
 * */
public class Solution {
    public String executeMethod(Object victim, String methodName) throws Exception {
        Object result = null;
        /* WRITE YOUR CODE HERE **/
        Method method = victim.getClass().getMethod(methodName);
        result = method.invoke(victim);
        return result == null ? "" : result.toString();
    }

    public List<String> handleAnimals(Object[] animals, String dogActivity, String catActivity, String wolfRecipe) throws Exception {
        List<String> result = new ArrayList<>();
        /* WRITE YOUR CODE HERE **/
        for (Object animal : animals) {
            String category = executeMethod(animal, "getCategory");
            String name = (String) animal.getClass().getField("name").get(animal);
            if (name.contains("666")) {
                name = name.replace("666", "888");
                animal.getClass().getField("name").set(animal, name);
                if ("Dog".equals(category)) {
                    String shout = executeMethod(animal, "shout");
                    if (shout.endsWith("Howl!")) {
                        executeMethod(animal, "kill");
                        executeMethod(animal, wolfRecipe);
                        continue;
                    }
                }
                executeMethod(animal, "kill");
                continue;
            }
            if ("Cat".equals(category)) {
                String r = executeMethod(animal, catActivity);
                result.add(r);
            } else if ("Dog".equals(category)) {
                String shout = executeMethod(animal, "shout");
                if (shout.endsWith("Howl!")) {
                    executeMethod(animal, "kill");
                    executeMethod(animal, wolfRecipe);
                    continue;
                } else {
                    String r = executeMethod(animal, dogActivity);
                    result.add(r);
                }
            }
        }
        return result;
    }
}