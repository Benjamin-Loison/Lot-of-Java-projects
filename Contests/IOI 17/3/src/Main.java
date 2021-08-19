import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String word = scan.nextLine();
        int length = word.length(), lengthDiv2 = length / 2;
        if(length % 2 == 1)
        {
            word = word.substring(0, lengthDiv2) + word.substring(lengthDiv2 + 1, length);
        }
        for(int i = 0; i < lengthDiv2; i++)
        {
            if(word.charAt(i) != word.charAt(length - i))
            {
                System.out.println("NO");
                scan.close();
                return;
            }
        }
        System.out.println("YES");
        scan.close();
    }
}