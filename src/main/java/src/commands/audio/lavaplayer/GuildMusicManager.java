package src.commands.audio.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {
    public final AudioPlayer audioPlayer;

    public final TrackScheduler scheduler;

    private final AudioPlayerSendHandler sendHandler;

    /**
     * Creates a GuildMusicManager.
     * this class is used to manage the audio player
     * the audio player is the one that is used to play the audio
     * the scheduler is used to schedule the audio(which means queueing the audio)
     * the sendHandler is the listener used to send the audio to discord
     * @param manager
     */
    public GuildMusicManager(AudioPlayerManager manager) {
        this.audioPlayer = manager.createPlayer();
        this.scheduler = new TrackScheduler(this.audioPlayer);
        this.audioPlayer.addListener(this.scheduler);
        this.sendHandler = new AudioPlayerSendHandler(this.audioPlayer);
    }

    /**
     * returns the sendHandler
     * @return
     */
    public AudioPlayerSendHandler getSendHandler() {
        return sendHandler;
    }

    /**
     * changes the volume of the audio player
     */
    public void setVolume(int volume) {
        this.audioPlayer.setVolume(volume);
    }

    /**
     * returns the audio player
     * @return
     */
    public AudioPlayer getAudioPlayer() {
        return this.audioPlayer;
    }
}
