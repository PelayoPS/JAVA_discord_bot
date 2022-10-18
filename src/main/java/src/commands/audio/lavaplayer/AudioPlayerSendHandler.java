package src.commands.audio.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;

import java.nio.ByteBuffer;

public class AudioPlayerSendHandler implements AudioSendHandler {
    private final AudioPlayer audioPlayer;
    private final ByteBuffer buffer;
    private final MutableAudioFrame frame;

    /**
     * Creates an AudioPlayerSendHandler.
     * this class is used to send audio to discord
     * the audio player is the one that is used to play the audio
     * the buffer is used to store the audio data
     * the frame is used to store the audio data in the buffer
     * @param audioPlayer the audio player
     */
    public AudioPlayerSendHandler(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        this.buffer = ByteBuffer.allocate(1024);//recommended size is 1024
        this.frame = new MutableAudioFrame();
        this.frame.setBuffer(buffer);
    }

    /**
     * @return true if the audio player can provide audio
     */
    @Override
    public boolean canProvide() {
        return this.audioPlayer.provide(this.frame);
    }

    /**
     * @return the audio data
     */
    @Override
    public ByteBuffer provide20MsAudio() {
        return this.buffer.flip();
    }

    /**
     * @return true
     */
    @Override
    public boolean isOpus() {
        return true;
    }
}