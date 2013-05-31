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

//    static private final String[] NAMES = { "Good song", "Better song", "The best song", "Imagine" };
//    static private final String[] TEXTES = { "In the beginning\nthere was silence", "and darkness, all across the earth.", "Then came the wind, and a hole in the sky.", "Thunder and lightning keep crushing down," };
    static private final String[] NAMES = { "Там Высоко", "The Show Must Go On", "Still Got The Blues", "Back In Black" };
    static private final String[] TEXTES = {
            "Не ведьма, не колдунья\n" +
            "Ко мне явилась в дом,\n" +
            "Не в пору полнолунья,\n" +
            "А летним, ясным днем...\n" +
            "\n" +
            "\"Обычно на рассвете\n" +
            "Я прихожу, во сне,\n" +
            "Но все не так на этот раз...\" -\n" +
            "Она сказала мне\n" +
            "\n" +
            "\"Усталость, ненависть и боль,\n" +
            "Безумья темный страх...\n" +
            "Ты держишь целый ад земной\n" +
            "Как небо, на плечах!\n" +
            "\n" +
            "Любой из вас безумен -\n" +
            "В любви и на войне,\n" +
            "Но жизнь - не звук, чтоб обрывать...\", -\n" +
            "Она сказала мне\n" +
            "\n" +
            "... Там, высоко - нет никого\n" +
            "Там также одиноко, как и здесь\n" +
            "Там, высоко - бег облаков\n" +
            "К погасшей много лет назад звезде\n" +
            "\n" +
            "\"Пока ты жив, не умирай,\n" +
            "На этот мир взгляни -\n" +
            "У многих здесь душа мертва,\n" +
            "Они мертвы внутри!\n" +
            "\n" +
            "Но ходят и смеются,\n" +
            "Не зная, что их нет...\n" +
            "Не торопи свой смертный час\", -\n" +
            "Она пропела мне\n" +
            "\n" +
            "\"Сбежать от жизни можно\n" +
            "От смерти - никогда.\n" +
            "Сама жизнь крылья сложит\n" +
            "И я вернусь сюда...\"\n" +
            "\n" +
            "Не ведьма, не колдунья\n" +
            "Явилась в дом ко мне,\n" +
            "А летним днем испить воды\n" +
            "Зашла случайно смерть",

            "Empty spaces - what are we living for\n" +
            "Abandoned places - I guess we know the score\n" +
            "On and on, does anybody know what we are looking for...\n" +
            "Another hero, another mindless crime\n" +
            "Behind the curtain, in the pantomime\n" +
            "Hold the line, does anybody want to take it anymore\n" +
            "The show must go on\n" +
            "The show must go on, yeah\n" +
            "Inside my heart is breaking\n" +
            "My make-up may be flaking\n" +
            "But my smile still stays on\n" +
            "\n" +
            "Whatever happens, I'll leave it all to chance\n" +
            "Another heartache, another failed romance\n" +
            "On and on, does anybody know what we are living for ?\n" +
            "I guess I'm learning (I'm learning learning learning)\n" +
            "I must be warmer now\n" +
            "I'll soon be turning (turning turning turning)\n" +
            "Round the corner now\n" +
            "Outside the dawn is breaking\n" +
            "But inside in the dark I'm aching to be free\n" +
            "The show must go on\n" +
            "The show must go on, yeah yeah\n" +
            "Ooh, inside my heart is breaking\n" +
            "My make-up may be flaking\n" +
            "But my smile still stays on\n" +
            "\n" +
            "Yeah yeah, whoa wo oh oh\n" +
            "\n" +
            "My soul is painted like the wings of butterflies\n" +
            "Fairytales of yesterday will grow but never die\n" +
            "I can fly - my friends\n" +
            "The show must go on (go on, go on, go on) yeah yeah\n" +
            "The show must go on (go on, go on, go on)\n" +
            "I'll face it with a grin\n" +
            "I'm never giving in\n" +
            "On - with the show\n" +
            "\n" +
            "Ooh, I'll top the bill, I'll overkill\n" +
            "I have to find the will to carry on\n" +
            "On with the show\n" +
            "On with the show\n" +
            "The show - the show must go on\n" +
            "Go on, go on, go on, go on, go on\n" +
            "Go on, go on, go on, go on, go on\n" +
            "Go on, go on, go on, go on, go on\n" +
            "Go on, go on, go on, go on, go on\n" +
            "Go on, go on",

            "Used to be so easy \n" +
            "To give my heart away \n" +
            "But I found out the hard way \n" +
            "There's the price you have to pay \n" +
            "I found out that love was no friend of mine \n" +
            "I should have known time after time \n" +
            "\n" +
            "So long, it was so long ago \n" +
            "But I've still got the blues for you \n" +
            "\n" +
            "Used to be so easy \n" +
            "To fall in love again \n" +
            "But I found out the hard way \n" +
            "It's a road that leads to pain \n" +
            "I found that love \n" +
            "Was more than just a game \n" +
            "You're playin' to win \n" +
            "But you lose just the same \n" +
            "\n" +
            "So long, it was so long ago \n" +
            "But I've still got the blues for you \n" +
            "\n" +
            "So many years \n" +
            "Since I've seen your face \n" +
            "Here in my heart, there's an empty space \n" +
            "Where you used to be \n" +
            "\n" +
            "So long, it was so long ago \n" +
            "But I've still got the blues for you \n" +
            "\n" +
            "Though the days come and go \n" +
            "There's one thing I know \n" +
            "I've still got the blues for you",

            "Back in black \n" +
            "I hit the sack \n" +
            "I've been too long I'm glad to be back [I bet you know I'm...] \n" +
            "Yes, I'm let loose \n" +
            "From the noose \n" +
            "That's kept me hanging about \n" +
            "I've been looking at the sky \n" +
            "'Cause it's gettin' me high \n" +
            "Forget the hearse 'cause I never die \n" +
            "I got nine lives \n" +
            "Cat's eyes \n" +
            "Abusin' every one of them and running wild \n" +
            "\n" +
            "CHORUS: \n" +
            "'Cause I'm back \n" +
            "Yes, I'm back \n" +
            "Well, I'm back \n" +
            "Yes, I'm back \n" +
            "Well, I'm back, back \n" +
            "(Well) I'm back in black \n" +
            "Yes, I'm back in black \n" +
            "\n" +
            "Back in the back \n" +
            "Of a Cadillac \n" +
            "Number one with a bullet, I'm a power pack \n" +
            "Yes, I'm in a bang \n" +
            "With a gang \n" +
            "They've got to catch me if they want me to hang \n" +
            "Cause I'm back on the track \n" +
            "And I'm beatin' the flack \n" +
            "Nobody's gonna get me on another rap \n" +
            "So look at me now \n" +
            "I'm just makin' my play \n" +
            "Don't try to push your luck, just get out of my way \n" +
            "\n" +
            "CHORUS \n" +
            "\n" +
            "Well, I'm back, Yes I'm back \n" +
            "Well, I'm back, Yes I'm back \n" +
            "Well, I'm back, back \n" +
            "Well I'm back in black \n" +
            "Yes I'm back in black \n" +
            "\n" +
            "hooo yeah \n" +
            "Ohh yeah \n" +
            "Yes I am \n" +
            "Oooh yeah, yeah Oh yeah \n" +
            "Back in now \n" +
            "Well I'm back, I'm back \n" +
            "Back, I'm back \n" +
            "Back, I'm back \n" +
            "Back, I'm back \n" +
            "Back, I'm back \n" +
            "Back \n" +
            "Back in black \n" +
            "Yes I'm back in black \n" +
            "\n" +
            "Out of the sight"
    };

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
            for (int j = 0; j < 30; ++j)
            {
                timings.add(new SelectedTextBlock(j*20, j*20+20-1, (j)*1000, (j+1)*1000));
            }
            song.setTimings(timings);

            songs.add( song );
        }
    }

    public List<SongBean> getSongs()
    {
        return songs;
    }

    public void deleteSong( SongBean song )
    {
        for(SongBean s : songs)
        {
            if(s.getId() == song.getId())
            {
                songs.remove(s);
                break;
            }
        }
    }

    public void createSong( SongBean song )
    {
        songs.add(song);
    }

    public void updateSong( SongBean song )
    {
        for(SongBean s : songs)
        {
            if(s.getId() == song.getId())
            {
                s.setName(song.getName());
                s.setText(song.getText());
                s.setTimings(song.getTimings());
                break;
            }
        }
    }
}
