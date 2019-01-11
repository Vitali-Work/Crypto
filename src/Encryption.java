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
}
