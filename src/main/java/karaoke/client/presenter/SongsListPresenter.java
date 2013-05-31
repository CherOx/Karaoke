package karaoke.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import karaoke.client.MainEventBus;
import karaoke.client.bean.SongBean;
import karaoke.client.service.Constants;
import karaoke.client.view.SongsListView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 30.05.13
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
@Presenter(view = SongsListView.class)
public class SongsListPresenter extends BasePresenter<SongsListView, MainEventBus> {

    private List<SongBean> songs;

    @Inject
    private karaoke.client.SongServiceAsync service;

    @Override
    public void bind()
    {
        view.getSongsListBox().addClickHandler(new ListBoxClickHandler());
//        view.getEditSongsButton().addClickHandler(new EditSongsButtonHandler());
    }

    /////////////////////////////////////////
    // Events handlers
    /////////////////////////////////////////

    public void onStart() {
        RefreshSongsList();
        eventBus.changeLeftTopWidget(view.getViewWidget());
    }

    public void onEndOfEditing() {
        RefreshSongsList();
        view.getSongsListBox().setEnabled(true);
    }

    public void onRunTimer() {
        view.getSongsListBox().setEnabled(false);
    }

    public void onStopTimer() {
        view.getSongsListBox().setEnabled(true);
    }

    public void onAddSong() {
        view.getSongsListBox().setEnabled(false);
    }

    public void onEditSong(SongBean inSong) {
        view.getSongsListBox().setEnabled(false);
    }

    /////////////////////////////////////////
    // Functions
    /////////////////////////////////////////

    private void RefreshSongsList()
    {
        view.getSongsListBox().clear();
        service.getSongs(new AsyncCallback<List<SongBean>>() {
            public void onFailure(Throwable caught)
            {
                Window.alert(Constants.SONGS_LIST_READING_ERROR);
            }
            public void onSuccess(List<SongBean> result) {
                DisplaySongs(result);
            }
        });
    }

    private void DisplaySongs(List<SongBean> songs) {
        this.songs = songs;
        for(int i = 0; i < songs.size(); i++) {
            view.getSongsListBox().addItem(songs.get(i).getName());
        }
    }

    /////////////////////////////////////////
    // Objects handlers
    /////////////////////////////////////////

    private class ListBoxClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            eventBus.selectSong(songs.get(view.getSongsListBox().getSelectedIndex()));
        }
    }

//    private class EditSongsButtonHandler implements ClickHandler {
//        @Override
//        public void onClick(ClickEvent event) {
//            eventBus.show();
//        }
//    }
}
