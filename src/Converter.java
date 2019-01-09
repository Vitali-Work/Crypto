public final class Converter {

    public static String toBinString(String HexStr){

        String BinStr = "";
        char[] HexChar = HexStr.toCharArray();

        for (char ch : HexChar){
            String tmpBin = Integer.toBinaryString(Integer.parseInt(Character.toString(ch), 16));
            while (tmpBin.length() < 4){
                tmpBin = "0" + tmpBin;
            }
            BinStr = BinStr + tmpBin;
        }

        return BinStr;
    }

    public static String toBinString(long number){

        String BinStr = "";
        BinStr = Long.toBinaryString(number);
        while (BinStr.length() % 8 != 0){
            BinStr = "0" + BinStr;
        }
        return BinStr;
    }


    public static String toHexString(String BinStr){

        String HexStr = "";

        for(int i = 0; i<BinStr.length(); i = i + 4){
            String tmpHex = Integer.toHexString(Integer.parseInt(BinStr.substring(i, i+4), 2));
            HexStr = HexStr + tmpHex;
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

        String[] tmp = Converter.Split(Converter.toHexString(Converter.Concatenate(rez)), 4);
        return tmp[3] + tmp[2] + tmp[1] + tmp[0];
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
            int _w = (_u + _v) / 2;

            wArr[i] = Integer.toString(_w);
        }

        return Converter.toHexString(Converter.Concatenate(wArr));

    }


}
