import java.util.Date;

public class Test {
    public static void main(String[] args) {

        long a = Long.parseLong("ff22334455667788", 16);

        System.out.println(Long.toHexString(a));
    }
 /*
       long timeSum1 = 0, timeSum2 = 0;
        String test0 = Example.y;

        if (Integer.parseInt(args[1]) == 1) {

            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                Date date1 = new Date();
                long d1 = date1.getTime();

                String testV1 = Encryption.Encrypt(Example.X, Example.Key);

                Date date2 = new Date();
                long d2 = date2.getTime();

                timeSum1 = timeSum1 + (d2 - d1);
            }

            System.out.println("Encr v1: " + timeSum1 + " / " + args[0] + " = " + timeSum1 / Float.parseFloat(args[0]));

        } else if (Integer.parseInt(args[1]) == 2) {

            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                Date date1 = new Date();
                long d1 = date1.getTime();

                String testV2 = Encryption.EncryptV2(Example.X, Example.Key);

                Date date2 = new Date();
                long d2 = date2.getTime();

                timeSum2 = timeSum2 + (d2 - d1);
            }

            System.out.println("Encr v2: " + timeSum2 + " / " + args[0] + " = " + timeSum2 / Float.parseFloat(args[0]));

        }



        //v1
        String key1 = "E9DEE72C";
        String a1 = "B194BAC8";
        String b1 = "0a08f53b";
        String pvk1 = Converter.PlusVKvadrate(a1, key1);
        String rot1 = Converter.RotHi(pvk1, 5);

        String brez1 = Converter.PlusVKrujke(b1, rot1);

        //v2
        long key2 = Long.parseLong("E9DEE72C", 16);
        long a2 = Long.parseLong("B194BAC8", 16);
        long b2 = Long.parseLong("0a08f53b", 16);
        long sum2 = Utils.sumModuleN(a2, key2);
        long rot2 = Utils.rotHi5(sum2);
        long brez2 = Utils.xor(b2, rot2);

        System.out.println("key1: " + key1);
        System.out.println("key2: " + Long.toHexString(key2));
        System.out.println("a1: " + a1);
        System.out.println("a2: " + Long.toHexString(a2));
        System.out.println("b1: " + b1);
        System.out.println("b2: " + Long.toHexString(b2));
        System.out.println("sumN1: " + pvk1);
        System.out.println("sumN2: " + Long.toHexString(sum2));
        System.out.println("rot1: " + rot1);
        System.out.println("rot2: " + Long.toHexString(rot2));
        System.out.println("brez1: " + brez1);
        System.out.println("brez2: " + Long.toHexString(brez2));


        System.out.println("example Y: " + Example.y);
        System.out.println("encrypt Y: " + Encryption.Encrypt(Example.X, Example.Key));
        System.out.println("encrypt Y: " + Encryption.EncryptV2(Example.X, Example.Key));


        System.out.println(Long.parseLong("b194bac8", 16));
        System.out.println(Long.parseLong("c8ba94b1", 16));

        System.out.println(Long.toHexString(Utils.cyclicShift(Long.parseLong("b194bac8", 16), 1)));


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

    }
    */

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
