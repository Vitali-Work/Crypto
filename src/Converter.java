public final class Converter {

    public static String toBinString(String HexStr){

        /*
          Метод конвертирует HEX-строку в BIN-строку
        Входные данные:
          String HexStr - HEX-строка
        Выходные данные:
          String BinStr - BIN-строка
          10FF  -->  0001000011111111
        Дополнительно:
          Каждый символ HEX-строки конвертируется в 4 символа BIN-строки
        */

        String tmpBin = "";
        char[] HexChar;
        String BinStr = "";
        HexChar = HexStr.toCharArray();
        for (char ch : HexChar){
            tmpBin = Integer.toBinaryString(Integer.parseInt(Character.toString(ch), 16));
            while (tmpBin.length() < 4){
                tmpBin = "0" + tmpBin;
            }
            BinStr = BinStr + tmpBin;
        }
        return BinStr;
    }

    public static String toBinString(long number){
        /*
          Метод конвертирует LONG-число в BIN-строку
        Входные данные:
          long number - LONG-число
        Выходные данные:
          String BinStr - BIN-строка
          4859  -->  0001001011111011
        Дополнительно:
          Количество символов в BIN-строке будет кратно 8
        */

        String BinStr = "";
        BinStr = Long.toBinaryString(number);
        while (BinStr.length() % 8 != 0){
            BinStr = "0" + BinStr;
        }
        return BinStr;
    }

    public static String toHexString(String BinStr){
        /*
          Метод конвертирует BIN-строку в HEX-строку
        Входные данные:
          String BinStr - BIN-строка
        Выходные данные:
          String HexStr - HEX-строка
          11000111010110011101010100101111  -->  C759D52F
        Дополнительно:
          Количество символов в HEX-строке будет 8
        */

        String HexStr = "";

        for(int i = 0; i<BinStr.length(); i = i + 4){
            String tmpHex = Integer.toHexString(Integer.parseInt(BinStr.substring(i, i+4), 2));
            HexStr = HexStr + tmpHex;
        }
        return HexStr.toUpperCase();
    }

    public static String toHexString(long number){

        String HexStr = Long.toHexString(number);

        while (HexStr.length() % 2 != 0){
            HexStr = "0" + HexStr;
        }

        return HexStr.toUpperCase();
    }


    public static String[] Split (String Str, int Parts){

        //определяем сколько будет частей строки
        int CharInPart = Str.length() / Parts;

        //создаем пустой массив по количеству частей
        String[] rez = new String[Parts];

        int n = 0;  //Объявляем счетчик для массива

        //копируем по несколько символов в новые строки и заносим их в массив
        for(int i = 0; i<Str.length(); i = i + CharInPart){

            int StartIndex = i;
            int EndIndex = i+CharInPart;
            rez[n] = Str.substring(StartIndex, EndIndex);
            n++;
        }

        return rez;
    }

    public static String Concatenate (String[] Arr){

        String rez = "";
        for (String s : Arr){
            rez += s;
        }
        return rez;
    }


    public static String[] getK (String StrKEY){

        String[] arr = Converter.Split(Converter.toBinString(StrKEY), 8);
        String[] rez = new String[56];

        for (int i = 0; i<8; i++){
            for (int j = 0; j<7; j++){
                rez[j*8+i] = arr[i];
            }
        }

        for (int i = 0; i<56; i++){
            rez[i] = toHexString(rez[i]);
        }

        return rez;
    }

    static final String[][] H = {
            {"B1", "94", "BA", "C8", "0A", "08", "F5", "3B", "36", "6D", "00", "8E", "58", "4A", "5D", "E4"},
            {"85", "04", "FA", "9D", "1B", "B6", "C7", "AC", "25", "2E", "72", "C2", "02", "FD", "CE", "0D"},
            {"5B", "E3", "D6", "12", "17", "B9", "61", "81", "FE", "67", "86", "AD", "71", "6B", "89", "0B"},
            {"5C", "B0", "C0", "FF", "33", "C3", "56", "B8", "35", "C4", "05", "AE", "D8", "E0", "7F", "99"},
            {"E1", "2B", "DC", "1A", "E2", "82", "57", "EC", "70", "3F", "CC", "F0", "95", "EE", "8D", "F1"},
            {"C1", "AB", "76", "38", "9F", "E6", "78", "CA", "F7", "C6", "F8", "60", "D5", "BB", "9C", "4F"},
            {"F3", "3C", "65", "7B", "63", "7C", "30", "6A", "DD", "4E", "A7", "79", "9E", "B2", "3D", "31"},
            {"3E", "98", "B5", "6E", "27", "D3", "BC", "CF", "59", "1E", "18", "1F", "4C", "5A", "B7", "93"},
            {"E9", "DE", "E7", "2C", "8F", "0C", "0F", "A6", "2D", "DB", "49", "F4", "6F", "73", "96", "47"},
            {"06", "07", "53", "16", "ED", "24", "7A", "37", "39", "CB", "A3", "83", "03", "A9", "8B", "F6"},
            {"92", "BD", "9B", "1C", "E5", "D1", "41", "01", "54", "45", "FB", "C9", "5E", "4D", "0E", "F2"},
            {"68", "20", "80", "AA", "22", "7D", "64", "2F", "26", "87", "F9", "34", "90", "40", "55", "11"},
            {"BE", "32", "97", "13", "43", "FC", "9A", "48", "A0", "2A", "88", "5F", "19", "4B", "09", "A1"},
            {"7E", "CD", "A4", "D0", "15", "44", "AF", "8C", "A5", "84", "50", "BF", "66", "D2", "E8", "8A"},
            {"A2", "D7", "46", "52", "42", "A8", "DF", "B3", "69", "74", "C5", "51", "EB", "23", "29", "21"},
            {"D4", "EF", "D9", "B4", "3A", "62", "28", "75", "91", "14", "10", "EA", "77", "6C", "DA", "1D"}
    };

    public static String getValueH (String Str){

        String[] numberStr = Converter.Split(Str, 2);
        int Row = Integer.parseInt(numberStr[0], 16);
        int Col = Integer.parseInt(numberStr[1], 16);
        return Converter.H[Row][Col];
    }

    public static String getStrH (String Str){

        String r = "";
        String[] arr = Converter.Split(Str, 4);
        for (String s : arr){
            r += Converter.getValueH(s);
        };
        return r;
    }


    public static String RotHi (String StrHex, int r){

        /*
        String[] tmpH = Split(StrHex, 4);

        StrHex = "";
        for (String s : tmpH){
            StrHex += getValueH(s);
        }
        */


        //объявляем переменные
        String[] rez = new String[32];
        int newIndex;

        //конвертируем строку в двоичный массив
        String[] BinArr = Converter.Split(Converter.toBinString(StrHex), 32);

        //проходим по всем элементам массива и вычисляем их новые индексы
        for (int i = 0; i<32; i++){
            if ((i-r)<0){
                newIndex = 32 + i - r;
            }
            else {
                newIndex = i-r;
            }
            rez[newIndex] = BinArr[i];
        }


        //return Reverse(Converter.toHexString(Converter.Concatenate(rez)));
        return Converter.toHexString(Converter.Concatenate(rez));
    }

    public static String PlusVKrujke (String u, String v){

        String uBin = Converter.toBinString(u);
        String vBin = Converter.toBinString(v);

        int n = uBin.length();

        String[] uArr = Converter.Split(uBin, n);
        String[] vArr = Converter.Split(vBin, n);
        String[] wArr = new String[n];

        for (int i=0; i<n; i++){
            int _u = Integer.parseInt(uArr[i], 2);
            int _v = Integer.parseInt(vArr[i], 2);
            int _w = (_u + _v) % 2;

            wArr[i] = Integer.toString(_w);
        }

        return Converter.toHexString(Converter.Concatenate(wArr));

    }

    public static String Reverse (String HexStr){

        String rez = "";
        String[] parts = Split(HexStr, HexStr.length() / 2);

        for (String tmp : parts){
            rez = tmp + rez;
        }
        return rez;
    }

    public static long toDec (String HexStr){

        return Long.parseUnsignedLong(Reverse(HexStr), 16);

    }

    public static String PlusVKvadrate (String u, String v) {



        //определяем сколько бит в числах
        int n = u.length() / 2;

        //выполняем вычисления

        long ul = toDec(u);
        long vl = toDec(v);

        long sum = ul + vl;
        int pow = 8*n;
        long tmp = sum % (long)Math.pow(2, pow);

        //конвертим результат обратно в хекс
        String rez = toHexString(tmp);

        //дописываем 0 в начале, чтобы на выходе было столькоже бит, сколько и на входе
        while (rez.length() < u.length() ){
            rez = "0" + rez;
        }

        return rez;

    }

    public static String MinusVKvadrate (String u, String v) {

        //определяем сколько бит в числах
        int n = u.length() / 2;

        //выполняем вычисления
        long sum = toDec(u) - toDec(v);
        int pow = 8*n;
        long tmp = sum % (long)Math.pow(2, pow);

        //конвертим результат обратно в хекс
        String rez = toHexString(tmp);
        if (rez.length() > 8) {
            rez = rez.substring(8, 16);
        }


        //дописываем 0 в начале, чтобы на выходе было столькоже бит, сколько и на входе
        while (rez.length() < u.length() ){
            rez = "0" + rez;
        }

        return Reverse(rez);

    }

    public static String I32 (long i) {


        String rez = toBinString(i);

        //дописываем 0 в начале, чтобы на выходе было столькоже бит, сколько и на входе
        while (rez.length() % 32 != 0  ){
            rez = "0" + rez;
        }

        return Reverse(toHexString(rez));
    }

    public static String sigma1 (String u) {

        String[] tmpU = Split(u, 4);

        String key = tmpU[0] + tmpU[1];
        String message = PlusVKrujke(tmpU[2], tmpU[3]);

        return PlusVKrujke(PlusVKrujke(Encryption.Encrypt(message, key), tmpU[2]), tmpU[3]);

    }


    public static String sigma2 (String u) {

        String[] tmpU = Split(u, 4);

        String tmpSigma = sigma1(u);
        String odin128 = "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";

        String key1 = tmpSigma + tmpU[3];
        String key2 = PlusVKrujke(tmpSigma, odin128) + tmpU[2];

        return PlusVKrujke(Encryption.Encrypt(tmpU[0], key1), tmpU[0]) + PlusVKrujke(Encryption.Encrypt(tmpU[1], key2), tmpU[1]);

    }




}
