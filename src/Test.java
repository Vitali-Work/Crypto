import java.util.Date;

public class Test {
    public static void main(String[] args){

        long num = 0xf012f298;

        long n1 = num & 0xff;
        long n2 = (num >> 8) & 0xff;
        long n3 = (num >> 16) & 0xff;
        long n4 = (num >> 24) & 0xff;

        printValue(num);
        printValue(n1);
        printValue(n2);
        printValue(n3);
        printValue(n4);

        /*
        Date date = new Date();
        long d1 = date.getTime();
        System.out.println("example Y: " + Example.y);
        System.out.println("encrypt Y: " + Encryption.Encrypt(Example.X, Example.Key));
        System.out.println("-----------------");
        Date date2 = new Date();
        long d2 = date2.getTime();
    */
    }

    public static void printValue(long l) {

        String s = Long.toBinaryString(l);
        while (s.length() % 32 != 0) {
            s = "0" + s;
        }
        String[] arrStr = Converter.Split(s, 8);
        String s1 = "";
        for (int i = 0; i<8; i++){
            s1 = s1 + arrStr[i] + " ";
        }
        System.out.println(s1 + "   " + Long.toHexString(l));
    }



}
