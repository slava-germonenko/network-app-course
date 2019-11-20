package udp.server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomRequestProcessor implements IRequestProcessor {
    private Pattern paramAPattern = Pattern.compile("(A=)(\\d+)");
    private Pattern paramBPattern = Pattern.compile("(B=)(\\d+)");
    private Pattern paramCPattern = Pattern.compile("(C=)(\\d+)");

    @Override
    public String execute(String request) {
        Double paramA = getParamValue(request, paramAPattern);
        if(paramA == null) return "Error: failed to parse parameter A, parameters must be numbers!";
        Double paramB = getParamValue(request, paramBPattern);
        if(paramB == null) return "Error: failed to parse parameter B, parameters must be numbers!";
        Double paramC = getParamValue(request, paramCPattern);
        if(paramC == null) return "Error: failed to parse parameter C, parameters must be numbers!";

        if(paramA > paramB || paramB > paramC) return "Error: invalid parameters!";

        return "Result: " + resolve(paramA, paramB, paramC);
    }

    private Double getParamValue(String string,Pattern pattern) {
        Double result = null;

        Matcher matcher = pattern.matcher(string);
        if(matcher.find()) {
            result = tryParse(matcher.group(2));
        }

        return result;
    }

    private Double tryParse(String string) {
        Double value = null;
        try {
            value = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            System.out.println("Failed to parse double: " + string);
        }
        return value;
    }

    private double resolve(Double paramA, Double paramB, Double paramC) {
        double sum1 = 0d;
        double sum2 = 0d;
        for(double n = paramA; n < paramB; n++) {
            sum1 += Math.pow(n, 2 - n);
        }

        for(double n = paramB; n < paramC; n++) {
            sum2 += Math.pow(n - 1, 2);
        }

        return  sum1 = sum2;
    }
}
