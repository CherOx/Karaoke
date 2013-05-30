package karaoke.server;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import karaoke.client.SongService;
import karaoke.client.bean.SongBean;
import karaoke.client.service.SelectedTextBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 28.05.13
 * Time: 12:52
 * To change this template use File | Settings | File Templates.
 */
public class SongServiceImpl extends RemoteServiceServlet implements SongService {

    static private final String[] NAMES = { "Good song", "Better song", "The best song", "Imagine" };
    static private final String[] TEXTES = { "In the beginning\nthere was silence", "and darkness, all across the earth.", "Then came the wind, and a hole in the sky.", "Thunder and lightning keep crushing down," };

    static private int NB_SONGS = 4;

    static List<SongBean> songs = new ArrayList<SongBean>();

    static
    {
        SongBean song = null;
        String name = null;
        String text = null;
        List<SelectedTextBlock> timings = null;
        for ( int i = 0; i < NB_SONGS; i++ )
        {
            song = new SongBean();

            name = NAMES[i % NAMES.length];
            song.setName(name);

            text = TEXTES[i % TEXTES.length];
            song.setText(text);

            timings = new ArrayList<SelectedTextBlock>();
            for (int j = 0; j < 5; ++j)
            {
                timings.add(new SelectedTextBlock(j*6+2, j*6+5+2, (j+1)*1000+50, (j+2)*1000));
            }
            song.setTimings(timings);

            songs.add( song );
        }
    }

    public List<SongBean> getSongs()
    {
//        List<SongBean> songs = new ArrayList<SongBean>();
//        SongBean song = null;
//        String name = null;
//        String text = null;
//        List<SelectedTextBlock> timings = null;
//
////        Random random = new Random();
//
//        for ( int i = 0; i < NB_SONGS; i++ )
//        {
//            song = new SongBean();
//
//            name = NAMES[i % NAMES.length];
//            song.setName(name);
//
//            text = TEXTES[i % TEXTES.length];
//            song.setText(text);
//
//            timings = new ArrayList<SelectedTextBlock>();
//            for (int j = 0; j < 5; ++j)
//            {
//                timings.add(new SelectedTextBlock(j*6, j*6+5, (j+1)*1000+20, (j+2)*1000));
//            }
//            song.setTimings(timings);
//
//            songs.add( song );
//        }

        return songs;
    }

    public void deleteSong( SongBean song )
    {}

    public void createSong( SongBean song )
    {
        songs.add(song);
    }

    public void updateSong( SongBean song )
    {}
}
