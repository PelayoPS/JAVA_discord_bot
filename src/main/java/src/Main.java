package src;

import io.github.cdimascio.dotenv.Dotenv;
import src.gui.gui.MainWindow;

import java.awt.*;
import java.util.Scanner;

public class Main {
    //bot variable to be accessible in other classes
    public static DiscordBot bot;

    /**
     * Main method, used to start the bot
     * @param args not used
     */
    public static void main(String[] args) {
        //starts the gui setup
        try {
            Dotenv.load();
            //if no exception was raised we can continue
            initializeBot(args);
        } catch (Exception e) {//only runs the gui setup if the .env is not found
            e.printStackTrace();
            initializeGui();
            //after creating the .env with the gui setup we can continue
            initializeBot(args);
        }
    }

    /**
     * Initializes the bot
     * @param args not used
     */
    private static void initializeBot(String[] args) {
        /*
         * asks the user if they want to use the dev or prod token
         */
        if (args.length > 0 && args[0].equals("dev")) {
            bot = new DiscordBot(false);
        } else {
            System.out.println("Do you want to use the dev or prod token? (dev/prod)");
            Scanner scanner = new Scanner(System.in);
            String token = scanner.nextLine();
            switch (token) {
                case "dev" -> bot = new DiscordBot(false);
                case "prod" -> bot = new DiscordBot(true);
                default -> {
                    System.out.println("Invalid token, please use only dev or prod");
                    main(args);
                }
            }
        }

    }

    /**
     * Initializes the gui setup
     */
    private static void initializeGui() {
        EventQueue.invokeLater(() -> {
            try {
                MainWindow window = new MainWindow();
                window.getFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}


