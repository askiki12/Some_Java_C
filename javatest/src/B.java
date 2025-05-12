public class B extends A {
    public char j = 'x';
    final double k = 5;
    static int e = 321;
    int m = 2;
    void show() {
        System.out.println(i + " " + j + " " + k + " " + e+ " " + m);
    }

    void showA() {
        System.out.println(super.j + " " + super.k + " " + super.e);
    }

    public static void main(String[] args) {
        B b = new B();
        b.show();
        b.showA();
    }
}
