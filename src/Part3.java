import java.math.BigInteger;
import java.util.Scanner;
import java.util.Random;

public class Part3
{
    static int p = 0;
    static int g = 0;
    static int a = 0;
    static int b = 0;
    //------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        p = scan.nextInt();
        g = scan.nextInt();
        a = scan.nextInt();
        b = scan.nextInt();

        BigInteger p_big , g_big , a_big , b_big , ResultA , ResultB , KeyA , KeyB;

        p_big = new BigInteger(Integer.toString(p)); //mod
        g_big = new BigInteger(Integer.toString(g)); //base
        a_big = new BigInteger(Integer.toString(a)); //exponent 1
        b_big = new BigInteger(Integer.toString(b)); //exponent 2

        ResultA = g_big.modPow(a_big, p_big);
        ResultB = g_big.modPow(b_big, p_big);

        KeyA =  ResultB.modPow(a_big, p_big);
        KeyB =  ResultA.modPow(b_big, p_big);

        System.out.print(ResultA.toString());
        System.out.print(" ");
        System.out.print(ResultB.toString());
        System.out.print(" ");
        System.out.print(KeyA.toString()); // OR System.out.println(KeyB.toString());
    }
}