public final class Encryption {

    public static String Encrypt(String Massage, String Key) {

        String[] tmpVar = Converter.Split(Massage, 4);
        String a = tmpVar[0];
        String b = tmpVar[1];
        String c = tmpVar[2];
        String d = tmpVar[3];
        String e = "";

        String[] key = Converter.getK(Key);

        String tmp = "";

        for (int i = 1; i <= 8; i++) {

            //1
            b = Converter.PlusVKrujke(b, Converter.RotHi(Converter.PlusVKvadrate(a, key[7 * i - 6 - 1]), 5));

            //2
            c = Converter.PlusVKrujke(c, Converter.RotHi(Converter.PlusVKvadrate(d, key[7 * i - 5 - 1]), 21));

            //3
            a = Converter.MinusVKvadrate(a, Converter.RotHi(Converter.PlusVKvadrate(b, key[7 * i - 4 - 1]), 13));

            //4
            e = Converter.PlusVKrujke(Converter.RotHi(Converter.PlusVKvadrate(Converter.Reverse(Converter.PlusVKvadrate(b, c)), key[7 * i - 3 - 1]), 21), Converter.I32(i));

            //5
            b = Converter.Reverse(Converter.PlusVKvadrate(b, e));

            //6
            c = Converter.MinusVKvadrate(c, e);

            //7
            d = Converter.Reverse(Converter.PlusVKvadrate(d, Converter.RotHi(Converter.PlusVKvadrate(c, key[7 * i - 2 - 1]), 13)));

            //8
            b = Converter.PlusVKrujke(b, Converter.RotHi(Converter.PlusVKvadrate(a, key[7 * i - 1 - 1]), 21));

            //9
            c = Converter.PlusVKrujke(c, Converter.RotHi(Converter.PlusVKvadrate(d, key[7 * i - 1]), 5));

            //10
            tmp = b;
            b = a;
            a = tmp;

            //11
            tmp = c;
            c = d;
            d = tmp;

            //12
            tmp = b;
            b = c;
            c = tmp;

        }
        return b + d + a + c;
    }

    public static String EncryptV2 (String message, String masterKey) {

        long a, b, c, d, e, tmp;

        String[] arr = Utils.split(message, 4);
        a = Long.parseLong(arr[0], 16);
        b = Long.parseLong(arr[1], 16);
        c = Long.parseLong(arr[2], 16);
        d = Long.parseLong(arr[3], 16);

        long[] key = Utils.getKey(masterKey);

        for (int i = 1; i <= 8; i++) {

            //1
            b = Utils.xor(b, Utils.rotHi5(Utils.sumModuleN(a, key[7 * i - 6 - 1])));
            //System.out.println(" 1  :  " + getStrFromLong(a) +" "+ getStrFromLong(b) +" "+ getStrFromLong(c) +" "+ getStrFromLong(d));

            //2
            c = Utils.xor(c, Utils.rotHi21(Utils.sumModuleN(d, key[7 * i - 5 - 1])));
            //System.out.println(" 2  :  " + getStrFromLong(a) +" "+ getStrFromLong(b) +" "+ getStrFromLong(c) +" "+ getStrFromLong(d));

            //3
            a = Utils.difModuleN(a, Utils.rotHi13(Utils.sumModuleN(b, key[7 * i - 4 - 1])));
            //System.out.println(" 3  :  " + getStrFromLong(a) +" "+ getStrFromLong(b) +" "+ getStrFromLong(c) +" "+ getStrFromLong(d));

            //4
            e = Utils.xor(Utils.rotHi21(Utils.sumModuleN(b, c, key[7 * i - 3 - 1])), Utils.sumModuleN(Utils.reverse(i), 0));
            //System.out.println(" 4  :  " + getStrFromLong(a) +" "+ getStrFromLong(b) +" "+ getStrFromLong(c) +" "+ getStrFromLong(d) + " "+ getStrFromLong(e));

            //5
            b = Utils.sumModuleN(b, e);
            //System.out.println(" 5  :  " + getStrFromLong(a) +" "+ getStrFromLong(b) +" "+ getStrFromLong(c) +" "+ getStrFromLong(d));

            //6
            c = Utils.difModuleN(c, e);
            //System.out.println(" 6  :  " + getStrFromLong(a) +" "+ getStrFromLong(b) +" "+ getStrFromLong(c) +" "+ getStrFromLong(d));

            //7
            d = Utils.sumModuleN(d, Utils.rotHi13(Utils.sumModuleN(c, key[7 * i - 2 - 1])));
            //System.out.println(" 7  :  " + getStrFromLong(a) +" "+ getStrFromLong(b) +" "+ getStrFromLong(c) +" "+ getStrFromLong(d));

            //8
            b = Utils.xor(b, Utils.rotHi21(Utils.sumModuleN(a, key[7 * i - 1 - 1])));
            //System.out.println(" 8  :  " + getStrFromLong(a) +" "+ getStrFromLong(b) +" "+ getStrFromLong(c) +" "+ getStrFromLong(d));

            //9
            c = Utils.xor(c, Utils.rotHi5(Utils.sumModuleN(d, key[7 * i - 1])));
            //System.out.println(" 9  :  " + getStrFromLong(a) +" "+ getStrFromLong(b) +" "+ getStrFromLong(c) +" "+ getStrFromLong(d));

            //10
            tmp = b;
            b = a;
            a = tmp;
            //System.out.println("10  :  " + getStrFromLong(a) +" "+ getStrFromLong(b) +" "+ getStrFromLong(c) +" "+ getStrFromLong(d));

            //11
            tmp = c;
            c = d;
            d = tmp;
            //System.out.println("11  :  " + getStrFromLong(a) +" "+ getStrFromLong(b) +" "+ getStrFromLong(c) +" "+ getStrFromLong(d));

            //12
            tmp = b;
            b = c;
            c = tmp;
            //System.out.println("12  :  " + getStrFromLong(a) +" "+ getStrFromLong(b) +" "+ getStrFromLong(c) +" "+ getStrFromLong(d));
        }


        return  getStrFromLong(b) + getStrFromLong(d) + getStrFromLong(a) + getStrFromLong(c);

    }

    private static String getStrFromLong (long num){
        return Utils.extendString(Long.toHexString(num), 8);
    }

}
