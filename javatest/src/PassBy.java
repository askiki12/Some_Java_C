//import src.PassBy;
class Record
{
    int num;
    String name;
}

public class PassBy {
    public void tryObject(Record r)
    {
        r = new Record();
        r.num = 100;
        r.name = "Fred";
    }
    public static void main(String[] args) {
        PassBy p = new PassBy();
        Record id = new Record();
        id.num = 2;
        id.name = "Barney";
        p.tryObject(id);
        System.out.println(id.name + "  " + id.num);
    }
}
