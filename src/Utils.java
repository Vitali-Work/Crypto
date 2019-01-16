public final class Utils {



    public static long sumModuleN(long u, long v){

        u = reverse(u);
        v = reverse(v);

        long pow = (long)Math.pow(2, 32);

        return reverse((u + v) % pow);

    }

    public static long sumModuleN(long u, long v, long w){

        u = reverse(u);
        v = reverse(v);
        w = reverse(w);

        long pow = (long)Math.pow(2, 32);

        return reverse((u + v + w) % pow);
    }


    public static long difModuleN(long u, long v){

        u = reverse(u);
        v = reverse(v);

        long pow = (long)Math.pow(2, 32);
        long a = (u - v) % pow;

        //откинуть первые ffffffff
        String s = Long.toHexString(a);
        if (s.length() > 8) {
            s = s.substring(8, 16);
        }

        return reverse(a);

    }

    public static long xor(long u, long v) {

        //u и v - числа до 4 байта
        return u^v;

    }

    public static String xor(String u, String v) {

        //на входе  : две строки длиной от 0 до 16 байт
        //на выходе : строка 16 байт

        // 128 бит = 16 байт

        //1. расширяем строки до 16 байт
        //2. делим строки на байты
        //3. ксорим байты попартно, расширяем результат до 2 символов если надо, складываем в конечную строку

        u = extendString(u, 16);
        v = extendString(v, 16);

        String[] strU = split(u, 16);
        String[] strV = split(v, 16);

        String str = "";

        for (int i = 0; i<16; i++){

            str = str + extendString(Long.toHexString(xor(Long.parseLong(strU[i], 16), Long.parseLong(strV[i], 16))), 2);

        }

        return str;

    }


    public static long H[] = {
                0xB1,0x94,0xBA,0xC8,0x0A,0x08,0xF5,0x3B,0x36,0x6D,0x00,0x8E,0x58,0x4A,0x5D,0xE4,
                0x85,0x04,0xFA,0x9D,0x1B,0xB6,0xC7,0xAC,0x25,0x2E,0x72,0xC2,0x02,0xFD,0xCE,0x0D,
                0x5B,0xE3,0xD6,0x12,0x17,0xB9,0x61,0x81,0xFE,0x67,0x86,0xAD,0x71,0x6B,0x89,0x0B,
                0x5C,0xB0,0xC0,0xFF,0x33,0xC3,0x56,0xB8,0x35,0xC4,0x05,0xAE,0xD8,0xE0,0x7F,0x99,
                0xE1,0x2B,0xDC,0x1A,0xE2,0x82,0x57,0xEC,0x70,0x3F,0xCC,0xF0,0x95,0xEE,0x8D,0xF1,
                0xC1,0xAB,0x76,0x38,0x9F,0xE6,0x78,0xCA,0xF7,0xC6,0xF8,0x60,0xD5,0xBB,0x9C,0x4F,
                0xF3,0x3C,0x65,0x7B,0x63,0x7C,0x30,0x6A,0xDD,0x4E,0xA7,0x79,0x9E,0xB2,0x3D,0x31,
                0x3E,0x98,0xB5,0x6E,0x27,0xD3,0xBC,0xCF,0x59,0x1E,0x18,0x1F,0x4C,0x5A,0xB7,0x93,
                0xE9,0xDE,0xE7,0x2C,0x8F,0x0C,0x0F,0xA6,0x2D,0xDB,0x49,0xF4,0x6F,0x73,0x96,0x47,
                0x06,0x07,0x53,0x16,0xED,0x24,0x7A,0x37,0x39,0xCB,0xA3,0x83,0x03,0xA9,0x8B,0xF6,
                0x92,0xBD,0x9B,0x1C,0xE5,0xD1,0x41,0x01,0x54,0x45,0xFB,0xC9,0x5E,0x4D,0x0E,0xF2,
                0x68,0x20,0x80,0xAA,0x22,0x7D,0x64,0x2F,0x26,0x87,0xF9,0x34,0x90,0x40,0x55,0x11,
                0xBE,0x32,0x97,0x13,0x43,0xFC,0x9A,0x48,0xA0,0x2A,0x88,0x5F,0x19,0x4B,0x09,0xA1,
                0x7E,0xCD,0xA4,0xD0,0x15,0x44,0xAF,0x8C,0xA5,0x84,0x50,0xBF,0x66,0xD2,0xE8,0x8A,
                0xA2,0xD7,0x46,0x52,0x42,0xA8,0xDF,0xB3,0x69,0x74,0xC5,0x51,0xEB,0x23,0x29,0x21,
                0xD4,0xEF,0xD9,0xB4,0x3A,0x62,0x28,0x75,0x91,0x14,0x10,0xEA,0x77,0x6C,0xDA,0x1D,
    };

    public static long getH(long h) {

        //Метод получает новое значение для h из таблицы подстановки Н

        if (h <= 16L) {         //например, H(0D) = H[0][D] = H[16*0 + D]

            return H[(int)h];
        }
        else {
            char[] chars =  Long.toHexString(h).toCharArray();
            int row = Integer.parseInt(Character.toString(chars[0]), 16);
            int col = Integer.parseInt(Character.toString(chars[1]), 16);

            return H[16 * row + col];
        }
    }

    public static long getSubstitution (long num){

        //Метод возвращает значение с применением подстановки из таблицы Н

                                        //num = 0x11223344
        long n4 = num & 0xff;           //n4  = 0x44
        long n3 = (num >> 8) & 0xff;    //n3  = 0x33
        long n2 = (num >> 16) & 0xff;   //n2  = 0x22
        long n1 = (num >> 24) & 0xff;   //n1  = 0x11
                                        //rez = 0x44332211
        n4 = getH(n4);
        n3 = getH(n3);
        n2 = getH(n2);
        n1 = getH(n1);

        return  (n1 << 24) | (n2 << 16) | (n3 << 8) | n4;

    }

    public static String[] split (String Str, int Parts){

        // Метод разделеет строку Str на массив строк, количество которых равно Parts

        int CharInPart = Str.length() / Parts;
        String[] rez = new String[Parts];
        int n = 0;
        for(int i = 0; i<Str.length(); i = i + CharInPart){

            int StartIndex = i;
            int EndIndex = i+CharInPart;
            rez[n] = Str.substring(StartIndex, EndIndex);
            n++;
        }

        return rez;
    }

    public static String concatenate (String[] Arr){

        //Метод склеивает массив строк Arr в одну строку rez

        String rez = "";
        for (String s : Arr){
            rez += s;
        }
        return rez;
    }

    public static long reverse (long num){

        //Метод изменяет порядок байт в числе на противоположный

                                        //num = 0x11223344
        long n4 = num & 0xff;           //n4  = 0x44
        long n3 = (num >> 8) & 0xff;    //n3  = 0x33
        long n2 = (num >> 16) & 0xff;   //n2  = 0x22
        long n1 = (num >> 24) & 0xff;   //n1  = 0x11
                                        //rez = 0x44332211

        return (n4 << 24) | (n3 << 16) | (n2 << 8) | n1;

    }

    public static String extendString (String str, int сharacters){

        //Метод дописывает в начало строки str символы "0", чтобы количество символов строки было кратно сharacters

        while (str.length() % сharacters != 0){
            str = "0" + str;
        }
        return str;
    }

    public static String extendStringEnd (String str, int сharacters){

        //Метод дописывает в конец строки str символы "0", чтобы количество символов строки было кратно сharacters

        while (str.length() % сharacters != 0){
            str = str + "0";
        }
        return str;
    }


    public static long cyclicShift(long num, int r){

        //Метод производит циклический сдвиг влево на r позиций

        String[] rez = new String[32];
        int newIndex;

        String[] BinArr = split(extendString(Long.toBinaryString(reverse(num)), 32), 32);


        //проходим по всем элементам массива и вычисляем их новые индексы
        //записываем в массив rez элементы на их новые (вычисленные) места
        for (int i = 0; i<32; i++){
            if ((i-r)<0){
                newIndex = 32 + i - r;
            }
            else {
                newIndex = i-r;
            }
            rez[newIndex] = BinArr[i];
        }

        return reverse(Long.parseLong(concatenate(rez), 2));
    }

    public static long rotHi (long num, int r){

        //Циклический сдвиг влево с предварительной подстановкой из таблицы Н

        num = getSubstitution(num);
        return cyclicShift(num, r);
    }

    public static long rotHi5 (long num){

        //Циклический сдвиг влево с предварительной подстановкой из таблицы Н на 5 позиций

        return rotHi(num, 5);
    }

    public static long rotHi13 (long num){

        //Циклический сдвиг влево с предварительной подстановкой из таблицы Н на 13 позиций

        return rotHi(num, 13);
    }

    public static long rotHi21 (long num){

        //Циклический сдвиг влево с предварительной подстановкой из таблицы Н на 21 позицию

        return rotHi(num, 21);
    }

    public static long[] getKey (String masterKey){

        String[] strKey = split(masterKey, 8);

        long[] key = new long[56];
        key[0] = key[8]  = key[16] = key[24] = key[32] = key[40] = key[48] = Long.parseLong(strKey[0], 16);
        key[1] = key[9]  = key[17] = key[25] = key[33] = key[41] = key[49] = Long.parseLong(strKey[1], 16);
        key[2] = key[10] = key[18] = key[26] = key[34] = key[42] = key[50] = Long.parseLong(strKey[2], 16);
        key[3] = key[11] = key[19] = key[27] = key[35] = key[43] = key[51] = Long.parseLong(strKey[3], 16);
        key[4] = key[12] = key[20] = key[28] = key[36] = key[44] = key[52] = Long.parseLong(strKey[4], 16);
        key[5] = key[13] = key[21] = key[29] = key[37] = key[45] = key[53] = Long.parseLong(strKey[5], 16);
        key[6] = key[14] = key[22] = key[30] = key[38] = key[46] = key[54] = Long.parseLong(strKey[6], 16);
        key[7] = key[15] = key[23] = key[31] = key[39] = key[47] = key[55] = Long.parseLong(strKey[7], 16);

        return key;
    }

}
