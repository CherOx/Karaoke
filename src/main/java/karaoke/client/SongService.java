package karaoke.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import karaoke.client.bean.SongBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 28.05.13
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
@RemoteServiceRelativePath( "songService" )
public interface SongService extends RemoteService {

    public List<SongBean> getSongs();

    public void deleteSong( SongBean song );

    public void createSong( SongBean song );

    public void updateSong( SongBean song );

}
