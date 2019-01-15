import java.util.Date;

public class Test {
    public static void main(String[] args){

        System.out.println("example Y: " + Example.y);
        System.out.println("encrypt Y: " + Encryption.Encrypt(Example.X, Example.Key));
        System.out.println("encrypt Y: " + Encryption.EncryptV2(Example.X, Example.Key));


        System.out.println(Long.parseLong("b194bac8", 16));
        System.out.println(Long.parseLong("c8ba94b1", 16));

        System.out.println(Long.toHexString(Utils.cyclicShift(Long.parseLong("b194bac8", 16), 1)));

        /*
        System.out.println(Long.toHexString(Utils.getKey(Example.Key)[0]));
        long num = 0x11223344;

        long n1 = num & 0xff;
        long n2 = (num >> 8) & 0xff;
        long n3 = (num >> 16) & 0xff;
        long n4 = (num >> 24) & 0xff;

        printValue(num);
        printValue(n1);
        printValue(n2);
        printValue(n3);
        printValue(n4);

        long n5 = (n1 << 24) | (n2 << 16) | (n3 << 8) | n4;
        printValue(n5);

        System.out.println(Long.toHexString(Utils.rotHi(0xb194bac8, 1)));


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
