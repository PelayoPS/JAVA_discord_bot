package util.logs;

/**
 * The LogConsole class is responsible for logging events to the console.
 * It implements the Logger interface with a generic type of String.
 */
public class LogConsole implements Logger<String>{

    /**
     * Logs the given code and message to the console.
     * The codeMsg parameter is formed by CODE | msg.
     * The code options are INFO, LOG, and ERROR.
     * 
     * @param codeMsg the code and message to be logged
     */
    @Override
    public void logEvent(String codeMsg) {
        String[] parts = codeMsg.split("\\|");
        String code = parts[0].trim();
        String msg = parts[1].trim();
        switch (code) {
            case "INFO":
                System.out.println("\u001B[34m[INFO] \u001B[0m" + msg);
                break;
            case "LOG":
                System.out.println("\u001B[32m[LOG] \u001B[0m" + msg);
                break;
            case "ERROR":
                System.out.println("\u001B[31m[ERROR] \u001B[0m" + msg);
                break;
            default:
                System.out.println("\u001B[31m[ERROR] \u001B[0m" + "Invalid code");
                break;
        }
    }
}
