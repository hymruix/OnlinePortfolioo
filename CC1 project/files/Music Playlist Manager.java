package musicplaylistmanager;

/**
 * Music Playlist Manager with Circular Doubly Linked List
 * Supports adding, removing, navigating, and looping through songs.
 */
import java.util.Scanner;

public class MusicPlaylistManager {

    // Node class representing a song in the playlist
    private class Node {
        String song;
        Node next, prev;

        Node(String song) {
            this.song = song;
        }
    }

    private Node current; // Pointer to the currently playing song
    private int size = 0; // Number of songs in the playlist

    // Add a song to the playlist
    public void addSong(String song) {
        Node newNode = new Node(song);
        if (current == null) { // First song in the playlist
            current = newNode;
            current.next = current;
            current.prev = current;
        } else { // Add to the end of the playlist
            Node tail = current.prev;
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = current;
            current.prev = newNode;
        }
        size++;
        System.out.println("Added song: " + song);
    }

    // Remove a song from the playlist
    public void removeSong(String song) {
        if (size == 0) {
            System.out.println("Playlist is empty!");
            return;
        }

        Node temp = current;
        for (int i = 0; i < size; i++) {
            if (temp.song.equals(song)) {
                if (size == 1) { // Only one song in the playlist
                    current = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                    if (temp == current) {
                        current = temp.next; // Update current if the removed song was playing
                    }
                }
                size--;
                System.out.println("Removed song: " + song);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Song not found in playlist: " + song);
    }

    // Move to the next song
    public void nextSong() {
        if (current == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        current = current.next;
        System.out.println("Now playing: " + current.song);
    }

    // Move to the previous song
    public void previousSong() {
        if (current == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        current = current.prev;
        System.out.println("Now playing: " + current.song);
    }

    // Display the current song
    public void displayCurrentSong() {
        if (current == null) {
            System.out.println("Playlist is empty!");
        } else {
            System.out.println("Current song: " + current.song);
        }
    }

    // Display the entire playlist
    public void displayPlaylist() {
        if (size == 0) {
            System.out.println("Playlist is empty!");
            return;
        }
        System.out.println("Playlist:");
        Node temp = current;
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + temp.song);
            temp = temp.next;
        }
    }

    // Main method for user interaction
    public static void main(String[] args) {
        MusicPlaylistManager playlist = new MusicPlaylistManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to the Music Playlist Manager!");
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Song");
            System.out.println("2. Remove Song");
            System.out.println("3. Next Song");
            System.out.println("4. Previous Song");
            System.out.println("5. Display Current Song");
            System.out.println("6. Display Playlist");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter song name to add: ");
                    String songToAdd = scanner.nextLine();
                    playlist.addSong(songToAdd);
                    break;
                case 2:
                    System.out.print("Enter song name to remove: ");
                    String songToRemove = scanner.nextLine();
                    playlist.removeSong(songToRemove);
                    break;
                case 3:
                    playlist.nextSong();
                    break;
                case 4:
                    playlist.previousSong();
                    break;
                case 5:
                    playlist.displayCurrentSong();
                    break;
                case 6:
                    playlist.displayPlaylist();
                    break;
                case 7:
                    System.out.println("Exiting Music Playlist Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }
}

