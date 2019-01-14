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


        String s = getString("0", 128);
        String tmph1 = "";
        String tmph2 = "";
        for (int i = 0; i<16; i++){
            tmph1 += Converter.H[0][i];
            tmph2 += Converter.H[1][i];
        }
        String h = tmph1 + tmph2;

        String Y = Converter.sigma2(getString("0", 128) + Converter.PlusVKrujke(s, Converter.sigma1(h+h)) +  Converter.sigma2(h+h));

        System.out.println(Y);

    }

    public static String getString (String s, int n){
        String tmp = "";
        for (int i = 0; i<n; i++){
            tmp += s;
        }
        return Converter.toHexString(tmp);
    }

}
