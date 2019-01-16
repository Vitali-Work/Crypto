public final class Hashing {

    /*
    * вычисление ХЕША
    * */

    private static String getStringFromChar(char c, int len){
        String rez = "";
        for (int i = 0; i<len; i++){
            rez = rez + c;
        }
        return rez;

    }

    public static String sigma1 (String _u){

        //на входе : строка 64 байта = 512 бит
        //на выходе: строка 16 байт  = 128 бит

        String[] u = Utils.split(_u, 4);

        String messageForEncypt = Utils.xor(u[2], u[3]);
        String keyForEncrypt = u[0] + u[1];
        String encr = Encryption.EncryptV2(messageForEncypt, keyForEncrypt);

        return Utils.xor(Utils.xor(encr, u[2]), u[3]);
    }

    public static String sigma2 (String _u){

        //на входе : строка 64 байта = 512 бит
        //на выходе: строка 32 байта = 256 бит

        String[] u = Utils.split(_u, 4);

        String messageForEncypt1 = u[0];
        String keyForEncrypt1 = sigma1(_u) + u[3];
        String encr1 = Encryption.EncryptV2(messageForEncypt1, keyForEncrypt1);

        String messageForEncypt2 = u[1];
        //String a = getStringFromChar('1', 128);
        String a = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
        String keyForEncrypt2 = Utils.xor(sigma1(_u), a) + u[2];
        String encr2 = Encryption.EncryptV2(messageForEncypt2, keyForEncrypt2);

        return (Utils.xor(encr1, u[0]))+(Utils.xor(encr2, u[1]));
    }

    public static String s = "";
    public static String h = "";
    public static long byteCount = 0;


    public static void getHashStart (){

        s = getStringFromChar('0', 128);
        h = "";
        for (long i = 0; i<32; i++){
            h = h + Utils.extendString(Long.toHexString(Utils.getH(i)), 2);
        }
        byteCount = 0;
    }

    public static void getHashH (byte[] arr){

        byteCount = byteCount + arr.length;
        String x = "";
        for(byte b : arr){

            x = x + Utils.extendString(Integer.toHexString(Byte.toUnsignedInt(b)), 2);

        }
        while (x.length() % 64 != 0){
            x = x + "0";
        }

        System.out.println("getHashH: x= " + x);
        System.out.println("getHashH: sigma1(x+h)= " + sigma1(x + h));
        s = Utils.xor(s, sigma1(x + h));
        System.out.println("getHashH: s= " + s);
        h = sigma2(x + h);
        System.out.println("getHashH: h= " + h);
    }


    private static long iModuleN(long i, int n){

        i = Utils.reverse(i);
        return Utils.reverse((long)(i % Math.pow(2, n)));

    }

    public static String getHashG (){

        //long a = iModuleN(byteCount*8, 128);
        long a = Utils.reverse(256);
        String xLenStr = Utils.extendString(Long.toHexString(a), 32);
        return sigma2(xLenStr + s + h);
    }



}
