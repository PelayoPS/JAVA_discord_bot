package src;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import src.listeners.ListenerManager;
import src.util.commandPattern.CommandManager;
import src.util.commandPattern.Invoker;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class DiscordBot {

    private JDA jda;
    private static Dotenv config = null;

    private CommandManager commandManager;

    private static boolean isProductionEnabled;

    /**
     * Default constructor for the DiscordBot class
     * @throws LoginException if the bot token is invalid
     */
    public DiscordBot(boolean productionEnabled) throws LoginException {
        isProductionEnabled = productionEnabled;
        if (productionEnabled){
            initialize("TOKENPROD");
        } else {
            initialize("TOKENDEV");
        }
    }

    /**
     * Initializes the bot
     * @param token the name of the token in the .env file
     * @throws LoginException if the bot token is invalid
     */
    private void initialize(String token) throws LoginException {
        config = Dotenv.load();//Used to load all the environment variables from the .env file
        jda = JDABuilder.createDefault(config.get(token))//creates a new JDA instance
                .setActivity(Activity.playing("with the code"))//sets the activity
                .setStatus(OnlineStatus.ONLINE)//sets the status
                .enableIntents(EnumSet.allOf(GatewayIntent.class))//for huge bots enable only the intents you need
                .setMemberCachePolicy(MemberCachePolicy.ALL)//enables the member cache
                .enableCache(EnumSet.allOf(CacheFlag.class))//enables all the caches
                .build();//builds the JDA instance
        this.commandManager = new CommandManager(jda);
        jda.getGuilds().get(0).loadMembers();
        updateCommands();//updates the commands
        addCommandListeners();//adds the command listeners
        //console log name of commands loaded
        commandManager.printCommands();
        setActivityAndStatus();//sets the activity and status
        addListeners(jda);//adds the listeners to the bot
    }

    /**
     * Adds all the listeners to the bot
     * @param jda the JDA instance
     */
    private void addListeners(JDA jda) {
        new ListenerManager(this).getListener().forEach(jda::addEventListener);
    }

    /**
     *  Used to get the config instance in other classes
     *  @return the config instance
     */
    public static Dotenv getConfig() {
        return config;
    }

    /**
     * sets the activity and status of the bot
     */
    private void setActivityAndStatus() {
        jda.getPresence().setActivity(Activity.streaming("Follow my author :D","https://www.twitch.tv/pelayo_p_s"));
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
    }

    /**
     * gets the JDA instance
     * to be used in other classes
     * @return the JDA instance
     */
    public JDA getJda() {
        return jda;
    }

    /**
     * updates the commands
     */
    private void updateCommands() {
        jda.updateCommands().addCommands(this.commandManager.getCommandDataAll()).queue();
    }

    /**
     * adds the command listeners
     */
    private void addCommandListeners() {
        jda.addEventListener(new Invoker(this.commandManager));
    }

    /**
     * returns true when the bot is in production mode
     * @return
     */
    public static boolean isProductionEnabled(){
        return isProductionEnabled;
    }

}
