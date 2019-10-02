import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    //method to check if a given integer is a cyclic number
    static boolean isCyclic(long N) {

        //handle if user entered not an integer
        if(N == Long.MIN_VALUE) return false;

        //Count number of digits
        //Check for numbers with only repeated digits
        long number = N;
        int count = 0;
        int digit = (int)number % 10;
        boolean repeatedDigits = true;
        while (number > 0) {
            count++;
            if (number % 10 != digit)
                repeatedDigits = false;
            number /= 10;
        }

        //Numbers with only repeated digits are not considered cyclic numbers
        if(repeatedDigits == true)
            return false;

        //Repeated cyclic numbers are not considered cyclic numbers
        if(count % 2 == 0) {
            long halfPower = (int) Math.pow(10, count / 2);
            long firstHalf = N % halfPower;
            long secondHalf = N / halfPower;
            if (firstHalf == firstHalf && isCyclic(firstHalf))
                return false;
        }

        number = N;
        while(true) {
            long rem = number % 10;
            long div = number / 10;
            number = (int)(Math.pow(10, count - 1)) * rem + div;
            if (number == N)
                break;
            if (number % N != 0)
                return false;
        }
        return true;
    }

    public static Long tryParse(String text) {
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            System.out.println("You entered not an integer");
            return Long.MIN_VALUE;
        }
    }

    public static void main(String[] args) throws IOException
    {
        System.out.print("Input a number: ");
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        String numberString = reader.readLine();

        long numberInteger = tryParse(numberString);
        if (isCyclic(numberInteger) )
            System.out.println("It's magic!");
        else if (numberInteger != Long.MIN_VALUE)
            System.out.println("It's not magic :(");
}
}
