package karaoke.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import karaoke.client.MainEventBus;
import karaoke.client.SongService;
import karaoke.client.SongServiceAsync;
import karaoke.client.bean.SongBean;
import karaoke.client.service.SelectedTextBlock;
import karaoke.client.view.WorkView;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 19.03.13
 * Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
@Presenter(view = WorkView.class)
public class WorkPresenter extends BasePresenter<WorkView, MainEventBus> {

//    String string = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\n Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
//   int[] timings = {100,500,1000,100,500,1000,100,500,1000,100,500,1000,100,500,1000,100,500,1000,100,500,1000};
//    String str2 =  "{100-500}Lorem ipsum {600-1000}dolor sit amet, {1001-2000}consectetur adipisicing elit, {3000-5000}sed do eiusmod tempor {5001-5500}incididunt ut labore et dolore magna aliqua.\n Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\n Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
//    SelectedTextBlock[] stb = new SelectedTextBlock[4]{new SelectedTextBlock(1,10,10,1000), };
//    ArrayList<SelectedTextBlock> list = new ArrayList<SelectedTextBlock>();

    protected List<SongBean> songs;

    @Inject
    private SongServiceAsync service;

    @Override
    public void bind()
    {
        view.getSongListBox().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                eventBus.selectSong(songs.get(view.getSongListBox().getSelectedIndex()));
            }
        });
    }

    public void onStart() {
//        list.add(new SelectedTextBlock(0,0,-1,0));
//        list.add(new SelectedTextBlock(0,5,0,1000));
//        list.add(new SelectedTextBlock(6,11,2000,2500));
//        list.add(new SelectedTextBlock(12,17,2520,3000));
//        list.add(new SelectedTextBlock(18,21,3020,3500));
//        list.add(new SelectedTextBlock(22,26,4000,5000));
//        list.add(new SelectedTextBlock(28,39,6000,7000));
//        list.add(new SelectedTextBlock(40,51,7020,7500));
//        list.add(new SelectedTextBlock(52,56,8000,9000));
//        view.setText(string, list);
        service.getSongs(new AsyncCallback<List<SongBean>>() {

            public void onFailure(Throwable caught)
            {
                Window.alert("Failure");
            }

            public void onSuccess(List<SongBean> result) {
                setSongs( result );
                eventBus.changeBody(view.getViewWidget());
            }

        });
//        setSongs(service.getSongs());
//        eventBus.changeBody(view.getViewWidget());
    }

    void setSongs(List<SongBean> songs) {
        this.songs = songs;
        int nbSongs = songs.size();
        for ( int i = 0; i < nbSongs; i++ ) {
            displaySong(songs.get(i));
        }
    }

    void displaySong( SongBean song ) {
        ListBox table = view.getSongListBox();
        table.addItem(song.getName());
    }

    public void onShowWork()
    {
        eventBus.changeBody(view.getViewWidget());
    }

    public void onSelectSong(SongBean song)
    {
        TextArea textArea = view.getTextArea();
        textArea.setText("");
        textArea.setText(song.getText());
    }
}
