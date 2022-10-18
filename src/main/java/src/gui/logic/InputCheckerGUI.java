package src.gui.logic;

public class InputCheckerGUI {
    @SuppressWarnings("UnusedReturnValue")
    public static boolean checkProdToken(String input) {
        //TODO: check if the token is valid
        /*
         * creates a regex so the input has the format:
         * TOKENPROD=**************************.******.**************************************
         * where * is any character
         */
        String regex = "TOKENPROD=\\*{26}\\.\\*{5}\\.\\*{38}";
        return input.matches(regex);
    }

    public static boolean checkID(String input) {
        //TODO: check if the id is valid
        /*
         * creates a regex so the input has the format:
         * ID=******************
         * where * is any character
         */
        String regex = "ID=\\*{18}";
        return input.matches(regex);
    }
}

