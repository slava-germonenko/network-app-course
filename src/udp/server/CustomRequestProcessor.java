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
        if(paramA == null) return "Error: invalid parameter A!";
        Double paramB = getParamValue(request, paramBPattern);
        if(paramB == null) return "Error: invalid parameter B!";
        Double paramC = getParamValue(request, paramCPattern);
        if(paramC == null) return "Error: invalid parameter C!";

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
        return paramA + paramB + paramC;
    }
}
