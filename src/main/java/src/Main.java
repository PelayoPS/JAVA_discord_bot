package src;

import javax.security.auth.login.LoginException;

public class Main {
    //bot variable to be accesible in other classes
    public static DiscordBot bot;

    /**
     * Main method, used to start the bot
     * @param args not used
     */
    public static void main(String[] args) {
        try {
            bot = new DiscordBot();//creates a new instance of the bot
        } catch (LoginException e) {
            System.out.println("ERROR: Provided bot token is invalid");// prints the error message
        }
    }
}


