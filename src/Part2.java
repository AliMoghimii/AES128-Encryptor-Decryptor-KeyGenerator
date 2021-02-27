import java.util.Scanner;

public class Part2
{
    static String NormalText = "";
    static String[][] NormalTextBlock = new String[4][4];

    static String Key;
    static String[][] KeyBlock = new String[4][4];

    static int RoundKeyCounter = 0;
    static String[][] RoundKeyBlock  = new String[4][4];

    static String CipherText = "";
    static String[][] CipherTextBlock = new String[4][4];

    public static String[][] InvLookupTable =
            {
                    {"52","09","6a","d5","30","36","a5","38","bf","40","a3","9e","81","f3","d7","fb"},
                    {"7c","e3","39","82","9b","2f","ff","87","34","8e","43","44","c4","de","e9","cb"},
                    {"54","7b","94","32","a6","c2","23","3d","ee","4c","95","0b","42","fa","c3","4e"},
                    {"08","2e","a1","66","28","d9","24","b2","76","5b","a2","49","6d","8b","d1","25"},
                    {"72","f8","f6","64","86","68","98","16","d4","a4","5c","cc","5d","65","b6","92"},
                    {"6c","70","48","50","fd","ed","b9","da","5e","15","46","57","a7","8d","9d","84"},
                    {"90","d8","ab","00","8c","bc","d3","0a","f7","e4","58","05","b8","b3","45","06"},
                    {"d0","2c","1e","8f","ca","3f","0f","02","c1","af","bd","03","01","13","8a","6b"},
                    {"3a","91","11","41","4f","67","dc","ea","97","f2","cf","ce","f0","b4","e6","73"},
                    {"96","ac","74","22","e7","ad","35","85","e2","f9","37","e8","1c","75","df","6e"},
                    {"47","f1","1a","71","1d","29","c5","89","6f","b7","62","0e","aa","18","be","1b"},
                    {"fc","56","3e","4b","c6","d2","79","20","9a","db","c0","fe","78","cd","5a","f4"},
                    {"1f","dd","a8","33","88","07","c7","31","b1","12","10","59","27","80","ec","5f"},
                    {"60","51","7f","a9","19","b5","4a","0d","2d","e5","7a","9f","93","c9","9c","ef"},
                    {"a0","e0","3b","4d","ae","2a","f5","b0","c8","eb","bb","3c","83","53","99","61"},
                    {"17","2b","04","7e","ba","77","d6","26","e1","69","14","63","55","21","0c","7d"}
            };

    static String[][] lookupTable =
            {       {"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76"},
                    {"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
                    {"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
                    {"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
                    {"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
                    {"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
                    {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
                    {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
                    {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
                    {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
                    {"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
                    {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
                    {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
                    {"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
                    {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
                    {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"},
            };

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static String[] StringSplitter(String string ,int houses) {

        String[] stringArray = new String[houses];

        int stringIndex = 0;
        for (int stringArrayIndex = 0 ; stringArrayIndex < stringArray.length ; stringArrayIndex++)
        {
            char firstChar  = string.charAt(stringIndex);
            stringIndex++;
            char secondChar = string.charAt(stringIndex);
            stringIndex++;
            stringArray[stringArrayIndex] = new StringBuilder().append(firstChar).append(secondChar).toString();
        }
        return stringArray;
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String[][] BuildBlock(String text)
    {
        String[][] block = new String[4][4];
        String[] textArray = StringSplitter(text , 16);

        int i = 0;
        for(int c = 0; c < 4; c++){
            for(int r = 0; r < 4; r++){
                block[r][c]=textArray[i++];
            }
        }
        return block;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static String[][] InvSubBytes(String[][] block)
    {
        for (int i = 0; i < block.length; i++)
        {
            for (int j = 0; j < block[0].length; j++)
            {
                int lookupRow = Character.getNumericValue(block[i][j].charAt(0));
                int lookupCol = Character.getNumericValue(block[i][j].charAt(1));

                block[i][j] = InvLookupTable[lookupRow][lookupCol];
            }
        }

        return block;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static String[] SingleShiftRight(String[] block)
    {
        String temp;

        temp = block[block.length - 1];
        for(int i = block.length - 1 ; i > 0 ; i--)
        {
            block[i]=block[ i - 1];
        }
        block[0] = temp;

        return block;
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String[] SingleShiftLeft(String[] block)
    {
        String temp;
        temp = block[0];
        for (int i = 0; i < block.length - 1; i++)
        {
            block[i] = block[i + 1];
        }
        block[block.length - 1] = temp;

        return block;
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String[][] InvShiftRows(String[][] block)
    {
        block[1] = SingleShiftRight(block[1]);

        block[2] = SingleShiftRight(block[2]);
        block[2] = SingleShiftRight(block[2]);

        block[3] = SingleShiftRight(block[3]);
        block[3] = SingleShiftRight(block[3]);
        block[3] = SingleShiftRight(block[3]);

        return block;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static long byTwoMultiply(long num)
    {
        if (num < 128)
            return 2*num;
        else
        {
            long res = (2 * num) ^ 27;
            String resString = Long.toHexString(res);
            while(resString.length() > 2)
            {
                StringBuilder hexSb = new StringBuilder(resString);
                hexSb.deleteCharAt(0);
                resString = hexSb.toString();
            }
            return Long.parseLong(resString , 16);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String InvMixColumnMultiplication(String[][] hexBlock , int[][] InverseRijndaelBlock , int row , int col)
    {
        String hexResult = "";
        long[] values = new long[4];

        for (int i = 0; i < values.length; i++)
        {
            switch (InverseRijndaelBlock[row][i])
            {
                case 9:
                    values[i] = byTwoMultiply(Long.parseLong(hexBlock[i][col],16));
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    break;
                //...............................................
                case 11:
                    values[i] = byTwoMultiply(Long.parseLong(hexBlock[i][col],16));
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    break;
                //...............................................
                case 13:
                    values[i] = byTwoMultiply(Long.parseLong(hexBlock[i][col],16));
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    break;
                //...............................................
                case 14:
                    values[i] = byTwoMultiply(Long.parseLong(hexBlock[i][col],16));
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    values[i] = byTwoMultiply(values[i]);
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);
                    values[i] = byTwoMultiply(values[i]);
                    break;
            }
        }
        hexResult = Long.toHexString(values[0] ^ values[1] ^ values[2] ^ values[3]);

        while(hexResult.length() > 2)
        {
            StringBuilder hexSb = new StringBuilder(hexResult);
            hexSb.deleteCharAt(0);
            hexResult = hexSb.toString();
        }

        if(hexResult.length() == 1)
        {
            hexResult =  "0" + hexResult;
        }

        return hexResult;
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String[][] InvMixColumns(String[][] baseHexBlock)
    {
        String[][] newHexBlock = new String[4][4];
        int[][] InverseRijndaelGaloisField = {
                {14, 11, 13, 9},
                {9, 14, 11, 13},
                {13, 9, 14, 11},
                {11, 13, 9, 14}
        };

        for (int i = 0; i < newHexBlock.length; i++)
        {
            for (int j = 0; j < newHexBlock[0].length; j++)
            {
                newHexBlock[i][j] = InvMixColumnMultiplication(baseHexBlock ,InverseRijndaelGaloisField , i , j);
            }
        }

        return newHexBlock;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static String RoundKeyFunction(String W)
    {
        String[] rc = {"1" , "2" , "4" , "8" , "10" , "20" , "40" , "80" , "1B" , "36"};
        String[] separatedHexes = new String[4];
        separatedHexes = StringSplitter(W , 4);

        SingleShiftLeft(separatedHexes);

        for (int i = 0; i < separatedHexes.length; i++)
        {
            int lookupRow = Character.getNumericValue(separatedHexes[i].charAt(0));
            int lookupCol = Character.getNumericValue(separatedHexes[i].charAt(1));

            separatedHexes[i] = lookupTable[lookupRow][lookupCol];

            if(i == 0)
            {
                long rcXorResult = Long.parseLong(separatedHexes[i] , 16) ^ Long.parseLong(rc[RoundKeyCounter] , 16);
                separatedHexes[i] = Long.toHexString(rcXorResult);
            }
        }

        return separatedHexes[0] + separatedHexes[1] + separatedHexes[2] + separatedHexes[3];
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String[][] RoundKeyGenerator(String[][] prevRoundKey)
    {
        String[] Ws = new String[8];

        for (int col = 0; col < prevRoundKey.length; col++)
        {
            Ws[col] = prevRoundKey[0][col] + prevRoundKey[1][col] + prevRoundKey[2][col] + prevRoundKey[3][col];
        }
        Ws[4] = Long.toHexString(Long.parseLong(RoundKeyFunction(Ws[3]),16) ^ Long.parseLong(Ws[0],16));
        while(Ws[4].length() < 8)
        {
            Ws[4] =  "0" + Ws[4];
        }
        Ws[5] = Long.toHexString(Long.parseLong(Ws[4],16) ^ Long.parseLong(Ws[1],16));
        while(Ws[5].length() < 8)
        {
            Ws[5] =  "0" + Ws[5];
        }
        Ws[6] = Long.toHexString(Long.parseLong(Ws[5],16) ^ Long.parseLong(Ws[2],16));
        while(Ws[6].length() < 8)
        {
            Ws[6] =  "0" + Ws[6];
        }
        Ws[7] = Long.toHexString(Long.parseLong(Ws[6],16) ^ Long.parseLong(Ws[3],16));
        while(Ws[7].length() < 8)
        {
            Ws[7] =  "0" + Ws[7];
        }

        String[] separatedHexes = new String[4];
        String[][] finalBlock = new String[4][4];
        for (int i = 0; i < 4 ; i++)
        {
            separatedHexes = StringSplitter(Ws[i+4] , 4);

            for (int k = 0; k < finalBlock[0].length; k++)
            {
                finalBlock[k][i] = separatedHexes[k];
            }
        }
        RoundKeyCounter++;

        return finalBlock;
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String[][] InvAddRoundKey(String[][] block , String[][] currentRoundKey)
    {
        for (int i = 0; i < block.length; i++)
        {
            for (int j = 0; j < block[0].length; j++)
            {
                long decResult = Long.parseLong(block[i][j],16) ^ Long.parseLong(currentRoundKey[i][j],16);
                String strResult = Long.toHexString(decResult);

                if(strResult.length() == 1)
                {
                    strResult =  "0" + strResult;
                }
                block[i][j] = strResult;
            }
        }

        return block;
    }
    //------------------------------------------------------------------------------------------------------------------
    public static void DebugArray(String Array[])
    {
        for (int i = 0; i < Array.length; i++)
        {
            System.out.print(Array[i]);
            System.out.print(",");
        }
        System.out.println();
    }
    //------------------------------------------------------------------------------------------------------------------
    public static void DebugArray2D(String Array[][])
    {
        for (int i = 0; i < Array.length; i++)
        {
            System.out.print("|");
            for (int j = 0; j < Array[0].length; j++)
            {
                System.out.print(Array[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println();
    }
    //------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        Key = scan.next();
        CipherText = scan.next();
        KeyBlock = BuildBlock(Key);
        CipherTextBlock = BuildBlock(CipherText);

        String[][] RoundKeyBlock0 = KeyBlock;
        String[][] RoundKeyBlock1 = RoundKeyGenerator(RoundKeyBlock0);
        String[][] RoundKeyBlock2 = RoundKeyGenerator(RoundKeyBlock1);
        String[][] RoundKeyBlock3 = RoundKeyGenerator(RoundKeyBlock2);
        String[][] RoundKeyBlock4 = RoundKeyGenerator(RoundKeyBlock3);
        String[][] RoundKeyBlock5 = RoundKeyGenerator(RoundKeyBlock4);
        String[][] RoundKeyBlock6 = RoundKeyGenerator(RoundKeyBlock5);
        String[][] RoundKeyBlock7 = RoundKeyGenerator(RoundKeyBlock6);
        String[][] RoundKeyBlock8 = RoundKeyGenerator(RoundKeyBlock7);
        String[][] RoundKeyBlock9 = RoundKeyGenerator(RoundKeyBlock8);
        String[][] RoundKeyBlock10 = RoundKeyGenerator(RoundKeyBlock9);

        //round 1
        CipherTextBlock = InvAddRoundKey(CipherTextBlock , RoundKeyBlock10);
        CipherTextBlock = InvShiftRows(CipherTextBlock);
        CipherTextBlock = InvSubBytes(CipherTextBlock);
        //round 2
        CipherTextBlock = InvAddRoundKey(CipherTextBlock , RoundKeyBlock9);
        CipherTextBlock = InvMixColumns(CipherTextBlock);
        CipherTextBlock = InvShiftRows(CipherTextBlock);
        CipherTextBlock = InvSubBytes(CipherTextBlock);
        //round 3
        CipherTextBlock = InvAddRoundKey(CipherTextBlock , RoundKeyBlock8);
        CipherTextBlock = InvMixColumns(CipherTextBlock);
        CipherTextBlock = InvShiftRows(CipherTextBlock);
        CipherTextBlock = InvSubBytes(CipherTextBlock);
        //round 4
        CipherTextBlock = InvAddRoundKey(CipherTextBlock , RoundKeyBlock7);
        CipherTextBlock = InvMixColumns(CipherTextBlock);
        CipherTextBlock = InvShiftRows(CipherTextBlock);
        CipherTextBlock = InvSubBytes(CipherTextBlock);
        //round 5
        CipherTextBlock = InvAddRoundKey(CipherTextBlock , RoundKeyBlock6);
        CipherTextBlock = InvMixColumns(CipherTextBlock);
        CipherTextBlock = InvShiftRows(CipherTextBlock);
        CipherTextBlock = InvSubBytes(CipherTextBlock);
        //round 6
        CipherTextBlock = InvAddRoundKey(CipherTextBlock , RoundKeyBlock5);
        CipherTextBlock = InvMixColumns(CipherTextBlock);
        CipherTextBlock = InvShiftRows(CipherTextBlock);
        CipherTextBlock = InvSubBytes(CipherTextBlock);
        //round 7
        CipherTextBlock = InvAddRoundKey(CipherTextBlock , RoundKeyBlock4);
        CipherTextBlock = InvMixColumns(CipherTextBlock);
        CipherTextBlock = InvShiftRows(CipherTextBlock);
        CipherTextBlock = InvSubBytes(CipherTextBlock);
        //round 8
        CipherTextBlock = InvAddRoundKey(CipherTextBlock , RoundKeyBlock3);
        CipherTextBlock = InvMixColumns(CipherTextBlock);
        CipherTextBlock = InvShiftRows(CipherTextBlock);
        CipherTextBlock = InvSubBytes(CipherTextBlock);
        //round 9
        CipherTextBlock = InvAddRoundKey(CipherTextBlock , RoundKeyBlock2);
        CipherTextBlock = InvMixColumns(CipherTextBlock);
        CipherTextBlock = InvShiftRows(CipherTextBlock);
        CipherTextBlock = InvSubBytes(CipherTextBlock);
        //round 10
        CipherTextBlock = InvAddRoundKey(CipherTextBlock , RoundKeyBlock1);
        CipherTextBlock = InvMixColumns(CipherTextBlock);
        CipherTextBlock = InvShiftRows(CipherTextBlock);
        CipherTextBlock = InvSubBytes(CipherTextBlock);
        //final round
        CipherTextBlock = InvAddRoundKey(CipherTextBlock , RoundKeyBlock0);

        NormalTextBlock = CipherTextBlock;

        for (int i = 0; i < NormalTextBlock.length ; i++)
        {
            for (int j = 0; j < NormalTextBlock[0].length; j++)
            {
                NormalText = NormalText + NormalTextBlock[j][i];
            }
        }
        System.out.println(NormalText);

    }
}

//test case 1:
//Key :    80000000000000000000000000000000
//Cipher : 0edd33d3c621e546455bd8ba1418bec8
//Plain : 00000000000000000000000000000000