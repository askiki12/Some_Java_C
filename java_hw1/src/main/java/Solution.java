package Solution.java;
public class Solution {
    public double sumAverageRunningInt(int a, int b) {
        /**
         * example:
         * a = 1, b = 100
         * return: 50.5
         */
        //WRITE YOUR CODE HERE
        int count = 0;
        for (int i = a; i <= b; i++) {
            count += i;
        }
        return count * 1.0 / (b - a);
    }

    public double segLength(double x_1, double y_1, double x_2, double y_2) {
        /**
         * Math.sqrt(number)求平方根
         * example:
         * x_1 = 1.2, y_1 = 0.1
         * x_2 = 0.6, y_1 = 0.8
         * return: 0.922
         */
        //WRITE YOUR CODE HERE
        return 0; //Delete this line
    }

    public int findPrime(int n) {
        /**
         * example:
         * n = 9
         * return: 7
         */
        //WRITE YOUR CODE HERE
        return 0; //Delete this line
    }

    public int fibonacci(int n) {
        /**
         * example:
         * n = 20
         * The first 20 Fibonacci numbers are:
         * 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 6765
         * return: 6765
         */
        //WRITE YOUR CODE HERE
        return 0; //Delete this line
    }

    public String cozaLozaWoza(int n) {
        /**
         * example:
         * n = 11543333
         * return: "Coza2Loza0Woza4"
         */
        //WRITE YOUR CODE HERE
        if (true && false)
            return null; //Delete this line
        return null;
    }

    public int conCalculation(int start, int times) {
        /**
         * example:
         * start = 10; times = 5
         * 10->11->1->2->3->3
         * return: 3
         */
        //WRITE YOUR CODE HERE
        return 0; //Delete this line
    }
}
public class test {
    public int main(){
        double number = 3.1415926;
        String result = String.format("%.3f", number);
        number = Double.parseDouble(str);
        System.out.print(number);
        return 0;
    }

}