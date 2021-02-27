import java.util.Scanner;

public class Part1
{
    static String NormalText;
    static String[][] NormalTextBlock = new String[4][4];


    static String Key;
    static String[][] KeyBlock = new String[4][4];
    static int RoundKeyCounter = 0;
    static String[][] RoundKeyBlock = new String[4][4];

    static String CipherText = "";
    static String[][] CipherTextBlock = new String[4][4];

    static String[][] lookupTable = {
            {"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76"},
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
            {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"}, };

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static String[] StringSplitter(String string ,int size) {

        String[] stringArray = new String[size];

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
    public static String[][] SubBytes(String[][] block)
    {
        for (int i = 0; i < block.length; i++)
        {
            for (int j = 0; j < block[0].length; j++)
            {
                int lookupRow = Character.getNumericValue(block[i][j].charAt(0));
                int lookupCol = Character.getNumericValue(block[i][j].charAt(1));

                block[i][j] = lookupTable[lookupRow][lookupCol];
            }
        }

        return block;
    }
    //------------------------------------------------------------------------------------------------------------------
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
    public static String[][] ShiftRows(String[][] block)
    {
        block[1] = SingleShiftLeft(block[1]);

        block[2] = SingleShiftLeft(block[2]);
        block[2] = SingleShiftLeft(block[2]);

        block[3] = SingleShiftLeft(block[3]);
        block[3] = SingleShiftLeft(block[3]);
        block[3] = SingleShiftLeft(block[3]);

        return block;
    }
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static String MixColumnMultiplication(String[][] hexBlock , int[][] RijndaelBlock , int row , int col)
    {
        String hexResult = "";
        long[] values = new long[4];

        for (int i = 0; i < values.length; i++)
        {
            switch (RijndaelBlock[row][i])
            {
                case 1:

                    values[i] = Long.parseLong(hexBlock[i][col], 16);
                    break;

                case 2:
                    values[i] = Long.parseLong(hexBlock[i][col],16) * 2;

                    if(values[i] >= 256)
                    {
                        values[i] = values[i] ^ 27; //27 is 11011 is 1B
                    }
                    break;

                case 3:
                    values[i] = Long.parseLong(hexBlock[i][col],16) * 2;
                    values[i] = values[i] ^ Long.parseLong(hexBlock[i][col],16);

                    if(values[i] >= 256)
                    {
                        values[i] = values[i] ^ 27; //27 is 11011 is 1B
                    }
                    break;
            }
        }

        hexResult = Long.toHexString(values[0] ^ values[1] ^ values[2] ^ values[3]);

        if(hexResult.length() > 2)
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
    public static String[][] MixColumns(String[][] baseHexBlock)
    {
        String[][] newHexBlock = new String[4][4];
        int[][] RijndaelGaloisField = {
                {02, 03, 01, 01},
                {01, 02, 03, 01},
                {01, 01, 02, 03},
                {03, 01, 01, 02}
        };

        for (int i = 0; i < newHexBlock.length; i++)
        {
            for (int j = 0; j < newHexBlock[0].length; j++)
            {
                newHexBlock[i][j] = MixColumnMultiplication(baseHexBlock ,RijndaelGaloisField , i , j);
            }
        }

        return newHexBlock;
    }
    //------------------------------------------------------------------------------------------------------------------
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
    public static void RoundKeyGenerator()
    {
        String[] Ws = new String[8];

        for (int col = 0; col < RoundKeyBlock.length; col++)
        {
            Ws[col] = RoundKeyBlock[0][col] + RoundKeyBlock[1][col] + RoundKeyBlock[2][col] + RoundKeyBlock[3][col];
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
        for (int i = 0; i < 4 ; i++)
        {
            separatedHexes = StringSplitter(Ws[i+4] , 4);

            for (int k = 0; k < RoundKeyBlock[0].length; k++)
            {
                RoundKeyBlock[k][i] = separatedHexes[k];
            }
        }
        RoundKeyCounter++;
    }
    //------------------------------------------------------------------------------------------------------------------
    public static String[][] AddRoundKey(String[][] block)
    {
        for (int i = 0; i < block.length; i++)
        {
            for (int j = 0; j < block[0].length; j++)
            {
                long decResult = Long.parseLong(block[i][j],16) ^ Long.parseLong(RoundKeyBlock[i][j],16);
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
        String continueChar = "";
        Key = scan.next();
        NormalText = scan.next();

        KeyBlock = BuildBlock(Key);
        NormalTextBlock = BuildBlock(NormalText);

        //initial round

        System.out.println("Initial RoundKey");
        DebugArray2D(RoundKeyBlock);
        System.out.println("Initial PlainText");
        DebugArray2D(NormalTextBlock);

        RoundKeyBlock = KeyBlock;
        NormalTextBlock = AddRoundKey(NormalTextBlock);

        System.out.println("RoundKey for Round " + RoundKeyCounter);
        DebugArray2D(RoundKeyBlock);
        System.out.println("PlainText After AddRound for Round " + RoundKeyCounter);
        DebugArray2D(NormalTextBlock);

        System.out.println("Enter the character 'a' to Continue");
        while(!continueChar.equals("a"))
        {
            continueChar = scan.next();
        }
        continueChar = "";

        //round 1-9
        for (int i = 1; i < 10; i++)
        {
            NormalTextBlock = SubBytes(NormalTextBlock);

            System.out.println("PlainText After SubBytes for Round " + (RoundKeyCounter+1));
            DebugArray2D(NormalTextBlock);

            NormalTextBlock = ShiftRows(NormalTextBlock);

            System.out.println("PlainText After ShiftRows for Round " + (RoundKeyCounter+1));
            DebugArray2D(NormalTextBlock);

            NormalTextBlock = MixColumns(NormalTextBlock);

            System.out.println("PlainText After MixColumn for Round " + (RoundKeyCounter+1));
            DebugArray2D(NormalTextBlock);

            RoundKeyGenerator();
            NormalTextBlock = AddRoundKey(NormalTextBlock);

            System.out.println("RoundKey for Round " + RoundKeyCounter);
            DebugArray2D(RoundKeyBlock);
            System.out.println("PlainText After AddRound for Round " + RoundKeyCounter);
            DebugArray2D(NormalTextBlock);

            //System.out.println("Round " + RoundKeyCounter);
            //DebugArray2D(NormalTextBlock);

            System.out.println("Enter the character 'a' to Continue");
            while(!continueChar.equals("a"))
            {
                continueChar = scan.next();
            }
            continueChar = "";
        }

        //round 10
        NormalTextBlock = SubBytes(NormalTextBlock);

        System.out.println("PlainText After SubBytes for Round " + (RoundKeyCounter+1));
        DebugArray2D(NormalTextBlock);

        NormalTextBlock = ShiftRows(NormalTextBlock);

        System.out.println("PlainText After ShiftRows for Round " + (RoundKeyCounter+1));
        DebugArray2D(NormalTextBlock);

        RoundKeyGenerator();
        NormalTextBlock = AddRoundKey(NormalTextBlock);
        CipherTextBlock = NormalTextBlock;

        System.out.println("RoundKey for Round " + RoundKeyCounter);
        DebugArray2D(RoundKeyBlock);
        System.out.println("PlainText After AddRound for Round " + RoundKeyCounter);
        DebugArray2D(NormalTextBlock);

        //System.out.println("Round " + RoundKeyCounter);
        //DebugArray2D(ChiperTextBlock);

        for (int i = 0; i < CipherTextBlock.length ; i++)
        {
            for (int j = 0; j < CipherTextBlock[0].length; j++)
            {
                CipherText = CipherText + CipherTextBlock[j][i];
            }
        }
        System.out.println(CipherText);
    }
}

//test case 1: AES explanation PDF
//key : 5468617473206D79204B756E67204675
//text : 54776F204F6E65204E696E652054776F
//cipher : 29C3505F571420F6402299B31A02D73A

//test case 2: Project Desc
//key : c0000000000000000000000000000000
//text : 00000000000000000000000000000000
//cipher : 4bc3f883450c113c64ca42e1112a9e87

//test case 3: from TA
//key : 00000000000000000000000000000000
//text : f34481ec3cc627bacd5dc3fb08f273e6
//cipher : 0336763e966d92595a567cc9ce537f5e

//test case 4: from TA (Round 8 needed 2 zeroes to make the RoundKey Tester)
//key : b6364ac4e1de1e285eaf144a2415f7a0
//text : 00000000000000000000000000000000
//cipher : 5d9b05578fc944b3cf1ccf0e746cd581

//test case 5: Animation Tutorial : https://www.youtube.com/watch?v=gP4PqVGudtg
//key : 2b7e151628aed2a6abf7158809cf4f3c
//text : 3243f6a8885a308d313198a2e0370734
//cipher : 3925841d....
