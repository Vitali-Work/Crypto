public final class Hashing {

    /*
    * вычисление ХЕША
    * */

    public static String sigma1 (String _u, String _key){

        String[] u = Utils.split(_u, 4);
        long[] uL = new long[4];
        for (int i = 0; i<4; i++){
            uL[i] = Long.parseLong(u[i], 16);
        }

        //String messageForEncrypt = Long.toHexString();
        String keyForEncrypt = "";

        return "1";

    }


}
