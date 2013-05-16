package karaoke.client.service;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 16.05.13
 * Time: 21:50
 * To change this template use File | Settings | File Templates.
 */
public class Song {

    private static int numOfItems = 0;
    private String text;
    private ArrayList<SelectedTextBlock> timings = new ArrayList<SelectedTextBlock>();
    private String name;

    public Song(String songName, String songText)
    {
        name = songName;
        text = songText;
    }

    public void addTiming(SelectedTextBlock textBlock)
    {
        timings.add(textBlock);
    }
}
