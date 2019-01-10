public class Encrypt {
    public static void main(String[] args){

        String[] tmpVar = Converter.Split(Example.X, 4);
        String a = tmpVar[0];
        String b = tmpVar[1];
        String c = tmpVar[2];
        String d = tmpVar[3];
        String e = "";

        String[] key = Converter.getK(Example.Key);

        String tmp = "";

        for (int i = 1; i <= 8; i++ ){

            System.out.println("----Round " + i + "----");

            //1
            b = Converter.PlusVKrujke(b, Converter.RotHi(Converter.PlusVKvadrate(a, key[7*i-6-1]), 5 ));
            PrintValue(1, a, b, c, d);

            //2
            c = Converter.PlusVKrujke(c, Converter.RotHi(Converter.PlusVKvadrate(d, key[7*i-5-1]), 21));
            PrintValue(2, a, b, c, d);

            //3
            a = Converter.MinusVKvadrate(a, Converter.RotHi(Converter.PlusVKvadrate(b, key[7*i-4-1]), 13));
            PrintValue(3, a, b, c, d);

            //4
            e = Converter.PlusVKrujke(Converter.RotHi(Converter.PlusVKvadrate(b, Converter.PlusVKvadrate(c, key[7*i-3-1])), 21), Converter.I32(i));
            PrintValue(4, a, b, c, d);

            //5
            b = Converter.PlusVKvadrate(b, e);
            PrintValue(5, a, b, c, d);

            //6
            c = Converter.MinusVKvadrate(c, e);
            PrintValue(6, a, b, c, d);

            //7
            d = Converter.PlusVKvadrate(d, Converter.RotHi(Converter.PlusVKvadrate(c, key[7*i-2-1]), 13));
            PrintValue(7, a, b, c, d);

            //8
            b = Converter.PlusVKrujke(b, Converter.RotHi(Converter.PlusVKvadrate(a, key[7*i-1-1]), 21));
            PrintValue(8, a, b, c, d);

            //9
            c = Converter.PlusVKrujke(c, Converter.RotHi(Converter.PlusVKvadrate(d, key[7*i-1]), 5));
            PrintValue(9, a, b, c, d);

            //10
            tmp = b;
            b = a;
            a = tmp;
            PrintValue(10, a, b, c, d);

            //11
            tmp = c;
            c = d;
            d = tmp;
            PrintValue(11, a, b, c, d);

            //12
            tmp = b;
            b = c;
            c = tmp;
            PrintValue(12, a, b, c, d);
        }

        String Y = b + d + a + c;

        System.out.println("-----------------");
        System.out.println("example Y: " + Example.y);
        System.out.println("encrypt Y: " + Y);

    }

    static void PrintValue(int i, String a, String b, String c, String d){

        if (i<10) {
            System.out.println(" " + i + ": " + a + " " + b + " " + c + " " + d);
        }
        else {
            System.out.println(i + ": " + a + " " + b + " " + c + " " + d);
        }


    }

}
