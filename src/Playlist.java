import java.util.Objects;

/**
 * This class contains details about a single song in a
 * playlist and also some other details
 */
public class Playlist {

    // name of the artist of the song
    private String artist;
    // title of the song
    private String title;
    // duration of the song
    private int duration;

    private int timeEntered; // the time when this song enqueued
    private int timeExit; // the time when this song dequeued

    // constructor method to initialize of all values
    public Playlist(String artist, String title, int duration) {
        this.artist = artist;
        this.title = title;
        this.duration = duration;
    }

    ///////////////////////////////////////////////
    ///             Getter methods             ///
    /////////////////////////////////////////////

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    // the waiting time is the time difference between
    // enqueueing time and dequeueing time
    public int getWaitingTime() {
        return timeExit-timeEntered;
    }

    ////////////////////////////////////////////////
    ///             Setter Methods              ///
    //////////////////////////////////////////////

    public void setTimeEntered(int timeEntered) {
        this.timeEntered = timeEntered;
    }

    public void setTimeExit(int timeExit) {
        this.timeExit = timeExit;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return duration == playlist.duration &&
                Objects.equals(artist, playlist.artist) &&
                Objects.equals(title, playlist.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artist, title, duration);
    }

    @Override
    public String toString() {
        return "("+artist+","+ title +","+duration+")";
    }
}
