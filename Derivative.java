import java.util.*;
import java.math.*;

public class Derivative
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int martabeh = Integer.parseInt(input.nextLine());
        String userInput = input.nextLine();
        String output = "";
        for(; martabeh > 0; martabeh--)
        {
            int numberOfSentence = 1, plusOrNegativeIndex = 0, plusOrNegative = 0;
            //find the Number of sentences
            for (char c : userInput.toCharArray())
                if (c == '+' || c == '-') numberOfSentence++;
            for(; numberOfSentence > 0; numberOfSentence--)
            {
                if(userInput.charAt(0) == '+' || userInput.charAt(0) == '-')
                {
                    if(userInput.charAt(0) == '+') plusOrNegative = 1;
                    else plusOrNegative = -1;
                    userInput = userInput.replace(userInput.substring(0, 1), "");
                }
                int last = 0;
                for(char c : userInput.toCharArray())
                    if(c == '+' || c == '-')
                    {
                        last = userInput.indexOf(c);
                        break;
                    }
                String sentence;
                if(last == 0) sentence = userInput;
                else sentence = userInput.substring(0, last).toString();
                userInput = userInput.replace(sentence, "");
                output += process(sentence, plusOrNegative);
                sentence = "";
            }
            userInput = "" + output;
            output = "";
        }
        System.out.println(userInput);

    }


    public static String process(String sentence, int plusOrNegative)
    {
        int xindex = sentence.indexOf('x');
        String str_coefficient = sentence.substring(0, xindex).toString();
        double Coefficient;
        if(!sentence.substring(0, xindex).toString().equals(""))
            Coefficient = Double.parseDouble(sentence.substring(0, xindex));
        else Coefficient = 1;
        sentence = sentence.replace(str_coefficient + "x^", "").toString();
        int power;

        if(sentence.contains("-"))
        {
            sentence = sentence.replace("-", "");
            power = Integer.parseInt(sentence) * -1;
        }
        else power = Integer.parseInt(sentence);


        String total = "";
        if((plusOrNegative == 1 && power > 0) || plusOrNegative == -1 && power < 0) total += "+";
        else if((plusOrNegative == 1 && power < 0) || (plusOrNegative == -1 && power > 0)) total += "-";

        double newCoefficient = (Coefficient * Math.abs(power));
        if(newCoefficient - (int) newCoefficient == 0)
            total += (int) newCoefficient;
        else
            total += newCoefficient;

        if(power == 0) return "";
        else if(power == 1) return total;

        total += ("x^" + (power - 1));
        return total;
    }
}
