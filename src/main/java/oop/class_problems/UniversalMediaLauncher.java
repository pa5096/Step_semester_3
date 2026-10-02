package oop.class_problems;

interface Playable {
    String play();
    String play(int fromSecond);
    String pause();
}

abstract class MediaFile {
    private static int counter = 1000;
    private final String fileId;

    public MediaFile() {
        counter++;
        this.fileId = "MF-" + counter;
    }

    public String getFileId() {
        return fileId;
    }

    public abstract String getFormatInfo();
}

class AudioFile extends MediaFile implements Playable {
    private String title;

    public AudioFile(String title) {
        super();
        this.title = title;
    }

    @Override
    public String getFormatInfo() {
        return "Audio file, ID: " + getFileId();
    }

    @Override
    public String play() {
        return "Playing audio: " + title;
    }

    @Override
    public String play(int fromSecond) {
        int minutes = fromSecond / 60;
        int seconds = fromSecond % 60;
        String formattedTime = String.format("%d:%02d", minutes, seconds);
        return "Playing audio: " + title + " from " + formattedTime;
    }

    @Override
    public String pause() {
        return "Paused audio: " + title;
    }
}

class Podcast implements Playable {
    private String showName;
    private int episodeNumber;

    public Podcast(String showName, int episodeNumber) {
        this.showName = showName;
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String play() {
        return "Streaming episode " + episodeNumber + " of " + showName;
    }

    @Override
    public String play(int fromSecond) {
        return "Streaming episode " + episodeNumber + " of " + showName + " from " + fromSecond + "s";
    }

    @Override
    public String pause() {
        return "Paused podcast: " + showName;
    }
}

public class UniversalMediaLauncher {
    public static void launchAll(Playable[] items) {
        for (Playable item : items) {
            System.out.println(item.play());
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test 1: AudioFile Play Overloads & Format Info ---");
        AudioFile a = new AudioFile("Morning Jazz");
        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.getFormatInfo());

        System.out.println("\n--- Test 2: Podcast Streaming ---");
        Podcast p = new Podcast("Tech Talk", 12);
        System.out.println(p.play());

        System.out.println("\n--- Test 3: Upcasting & Polymorphic Batch Launch ---");
        Playable ref = a; // Upcasting AudioFile to Playable interface
        launchAll(new Playable[]{ref, p});
    }
}