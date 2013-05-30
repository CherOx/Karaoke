package karaoke.client.bean;

import com.google.gwt.user.client.rpc.IsSerializable;
import karaoke.client.service.SelectedTextBlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 28.05.13
 * Time: 12:41
 * To change this template use File | Settings | File Templates.
 */
public class SongBean implements IsSerializable {

    static int currId = 0;

    private String text = null;
    private List<SelectedTextBlock> timings = null;
    private String name = null;
    private int id;

    public SongBean()
    {
        id = currId++;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<SelectedTextBlock> getTimings() {
        return timings;
    }

    public void setTimings(List<SelectedTextBlock> timings) {
        this.timings = timings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public SongBean(String songName, String songText)
//    {
//        name = songName;
//        text = songText;
//    }

//    public void addTiming(SelectedTextBlock textBlock)
//    {
//        timings.add(textBlock);
//    }
}
