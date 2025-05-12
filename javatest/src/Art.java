class Art {
    Art() {
        System.out.println("Art Constructor");
    }
}
class Drawing extends Art {
    Drawing() {
        System.out.println("Drawing Constructor");
    }
}

class Cartoon extends Drawing {
    Cartoon() {
        super();
        System.out.println("Cartoon Constructor");
    }
    public static void main(String args[]) {
        Cartoon c = new Cartoon();
    }
}