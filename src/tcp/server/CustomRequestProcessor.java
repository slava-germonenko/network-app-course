package tcp.server;

public class CustomRequestProcessor implements IRequestProcessor {

    @Override
    public String execute(String request) {
        String responseTemplate = "Ticket is ";
        String ticketLenError = "Error: Ticket number length must equal 6!";
        String ticketNumberError = "Error: Ticket number must consist of numbers only!";

        if (request.length() != 6) return ticketLenError;

        Integer left = tryParse(request, 0);
        if (left == null) return ticketNumberError;

        Integer right = tryParse(request, 2);
        if(right == null) return ticketNumberError;

        String result = checkOfLucky(request) ? "lucky!" : "unlucky.";
        return responseTemplate + result;
    }

    private Integer tryParse(String string, int offset) {
        Integer result = null;
        try {
            result = Integer.parseInt(string.substring(offset));
        } catch (NumberFormatException ignored) {}
        return result;
    }

    private boolean checkOfLucky(String string) {
        String[] numbers = string.split("");
        int leftSum = 0;
        for (int i = 0; i <= 2; i++) {
            leftSum += Integer.parseInt(numbers[i]);
        }

        int rightSum = 0;
        for (int i = 3; i <= 5; i++) {
            rightSum += Integer.parseInt(numbers[i]);
        }

        return leftSum == rightSum;
    }
}
