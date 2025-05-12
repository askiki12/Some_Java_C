public class kobe{
    int sum;
    kobe() {
// calling the second constructor
        this(5, 2);
    }
    // second constructor
    kobe(int arg1, int arg2) {
        // add two value
        this.sum = arg1 + arg2;
    }
    void display() {
        System.out.println("Sum is: " + sum);
    }
    // main class
    public static void main(String[] args) {
// call the first constructor
        kobe obj = new kobe();
// call display method
        obj.display();
        System.out.println(obj.sum);
    }
}
