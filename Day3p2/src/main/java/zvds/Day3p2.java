package zvds;

import zvds.reader.TxtReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3p2 {
    public static void main(String[] args) {
        //String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
        String input = TxtReader.readFileToString("E:/Development/Java/adventofcode2024/Day3p2/src/main/resources/mul-input.txt");

        StringBuilder filteredInput = new StringBuilder();
        boolean insideDontBlock = false;
        int index = 0;

        while (index < input.length()) {
            if (!insideDontBlock && input.startsWith("don't()", index)) {
                insideDontBlock = true;
                index += "don't()".length();  // Skip past "don't()"
            }
            else if (insideDontBlock && (input.startsWith("do()", index) || input.startsWith("don't()", index))) {
                insideDontBlock = false;  // Exit the block
            }
            else if (!insideDontBlock) {
                filteredInput.append(input.charAt(index));
                index++;
            } else {
                index++;
            }
        }

        System.out.println("\n" + filteredInput.toString());

        String regex = "mul\\(([1-9]\\d{0,2}),([1-9]\\d{0,2})\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(filteredInput);

        int totalCount = 0;
        while (matcher.find()) {
            System.out.println("\nFound valid mul call: " + matcher.group());
            String mulCall = matcher.group();
            int number1 = Integer.parseInt(mulCall.substring(mulCall.indexOf("(")+1, mulCall.indexOf(",")));
            int number2 = Integer.parseInt(mulCall.substring(mulCall.indexOf(",")+1, mulCall.indexOf(")")));
            totalCount += mul(number1,number2);
            System.out.println(number1 + " * " + number2 +" " + mul(number1,number2));
        }
        System.out.println("Total count: " + totalCount);

    }
    private static int mul(int x, int y){
        return x * y;
    }
}