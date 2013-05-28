package karaoke.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import karaoke.client.bean.SongBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 28.05.13
 * Time: 12:50
 * To change this template use File | Settings | File Templates.
 */
public interface SongServiceAsync {

    public void getSongs( AsyncCallback<List<SongBean>> callback );

    public void deleteSong( SongBean song, AsyncCallback<Void> callback );

    public void createSong( SongBean song, AsyncCallback<Void> callback );

    public void updateSong( SongBean song, AsyncCallback<Void> callback );
}
