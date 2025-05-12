//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

//public class Main {
//    public static void main(String[] args) {
//        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
//        // to see how IntelliJ IDEA suggests fixing it.
//        System.out.printf("Hello and welcome!\n");
//        double number = 3.1415926;
//        String result = String.format("%.3f", number);
//        //number = Double.parseDouble(str);
//        System.out.println(result);
//
//        for (int i = 1; i <= 5; i++) {
//            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
//            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
//            System.out.println("i = " + i);
//        }
//    }
//}

//package src;
//public class PassBy {
//    public void tryPrimitives(int i, double f, char c, boolean test) {
//        _i += 10;
//        //This is legal, but the new values
//        c = 'z';
//        //won't be seen outside tryPrimitives.
//        f -= 3.0;
//        if (test)
//            test = false;
//        else
//            test = true;
//    }
//
//    public static void main(String[] args) {
//        PassBy p = new PassBy();
//        int ii = 1;
//        double ff = 1.0;
//        char cc = 'a';
//        boolean bb = false;
//        p.tryPrimitives(ii, ff, cc, bb);
//        System.out.println("ii = " + ii + ", ff = " + ff +
//                ", cc = " + cc + ", bb = " + bb);
//        System.out.println(p._i);
//    }
//
//    int _i = 1;
//    char _c = 'm';
//    double _f = 1.0;
//}