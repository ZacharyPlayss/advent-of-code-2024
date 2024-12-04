package zvds;

import zvds.reader.TxtReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3p1Main {
    public static void main(String[] args) {
        //String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
        String input = TxtReader.readFileToString("E:/Development/Java/adventofcode2024/Day3p1/src/main/resources/mul-input.txt");
        String regex = "mul\\(([1-9]\\d{0,2}),([1-9]\\d{0,2})\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        int totalCount = 0;
        while (matcher.find()) {
            System.out.println("Found valid mul call: " + matcher.group());
            String mulCall = matcher.group();
            int number1 = Integer.parseInt(mulCall.substring(mulCall.indexOf("(")+1, mulCall.indexOf(",")));
            int number2 = Integer.parseInt(mulCall.substring(mulCall.indexOf(",")+1, mulCall.indexOf(")")));
            totalCount += mul(number1,number2);
            //System.out.println(number1 + " * " + number2 +" " + mul(number1,number2));
        }
        System.out.println("Total count: " + totalCount);
    }
    private static int mul(int x, int y){
        return x * y;
    }
}