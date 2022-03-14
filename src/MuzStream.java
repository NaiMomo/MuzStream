import ca.umontreal.adt.list.ArrayList;
import ca.umontreal.adt.list.List;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is the main class. It's responsible to
 * load playlist from the given file and showing
 * the top-k songs list
 */
public class MuzStream {

    private Scanner sc; // scanner to read songs from file
    private int capacity; // the max capacity of the queue
    private int limit; // the minimum no. of items to maintain in the queue
    private int numberOfFilling; // how many time the file reading operation will happen
    private int topK; // the number of songs to show in top-k list

    private PlaylistQueue queue; //  the queue
    private int timePlay = 0; // the total playtime, this is the sum of durations of all dequeued songs
    private List<Playlist> finished; // list of songs which are already dequeued

    // constructor method to pass the cmd line arguments
    public MuzStream(String requests, int playlistCapacity, int playlistLimit, int numberOfFilling, int k) throws IOException {
        // prints the command
        System.out.printf("java MuzStream %s %d %d %d %d\n",requests,playlistCapacity,playlistLimit,numberOfFilling,k);
        this.sc = new Scanner(new FileReader(requests));
        this.sc.useDelimiter("[\\t\\n]");
        this.capacity = playlistCapacity;
        this.limit = playlistCapacity*playlistLimit/100;
        this.numberOfFilling = numberOfFilling;
        this.topK = k;
        this.queue = new PlaylistQueue();
        this.finished = new ArrayList<>();
    }


    // initialize main operation
    // it reads from file, dequeues songs from queue hits the limit
    // prints the top-k list
    public void startPlaying() {
        int remainingFilling = numberOfFilling;
        while (remainingFilling > 0) {
            readNextRequests(capacity);
            remainingFilling--;
            while (queue.size() > limit) {
                Playlist song = queue.dequeue();
                song.setTimeExit(timePlay);
                timePlay += song.getDuration();
                finished.add(finished.size(), song);
            }
            printTopK();
        }
    }

    // prints the oldest "k" no. of dequeued songs
    private void printTopK() {
        System.out.println("Top-"+topK);
        for (int i = 0; !finished.isEmpty() && i < topK; i++) {
            Playlist song = finished.remove(0); // oldest entry
            System.out.println(song.getArtist()+"\t"+song.getTitle()+"\t"+song.getWaitingTime());
        }
    }

    /**
     * Reads next songs from the requested file
     *
     * @param max capacity of the queue to maintain
     */
    private void readNextRequests(int max) {
        while (sc.hasNextLine() && queue.size() < max) {
            Playlist song = readNextSong(sc);
            queue.enqueue(song);
        }
    }

    // creates the Playlist object for the song read from requested file
    private Playlist readNextSong(Scanner sc) {
        String artist = sc.next();
        String title = sc.next();
        int duration = sc.nextInt();
        Playlist song = new Playlist(artist,title,duration);
        song.setTimeEntered(timePlay);
        if (sc.hasNext()) sc.nextLine();
        return song;
    }

    public static <Int> void main(String[] args) throws Exception {

        // requests playlistCapacity playlistLimit numberOfFillings k
        String requests = args[0];
        int playlistCapacity = Integer.parseInt(args[1]);
        int playlistLimit = Integer.parseInt(args[2]);
        int numberOfFillings = Integer.parseInt(args[3]);
        int k = Integer.parseInt(args[4]);
     

        MuzStream stream = new MuzStream(requests, playlistCapacity,
                playlistLimit, numberOfFillings, k);
        stream.startPlaying();
    }
}
