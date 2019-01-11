import java.util.Date;

public class Test {
    public static void main(String[] args){

        Date date = new Date();
        long d1 = date.getTime();
        System.out.println("example Y: " + Example.y);
        System.out.println("encrypt Y: " + Encryption.Encrypt(Example.X, Example.Key));
        System.out.println("-----------------");
        Date date2 = new Date();
        long d2 = date2.getTime();
        System.out.println(d2-d1);
    }

}
