package karaoke.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
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

    private List<SongBean> songs;

    private List<SelectedTextBlock> timings;
//    private Label selectionLabel;
    private Timer timer;
    private int time = 0;
    private int cnt = 0;

    @Inject
    private SongServiceAsync service;

    @Override
    public void bind()
    {
//        selectionLabel = view.getSelectionLabel();
        view.getSongListBox().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                eventBus.selectSong(songs.get(view.getSongListBox().getSelectedIndex()));
            }
        });
        // Add a KeyUpHandler
        view.getTextArea().addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                updateSelectionLabel();
            }
        });
        // Add a ClickHandler
        view.getTextArea().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                updateSelectionLabel();
            }
        });
        view.getButtonRun().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                view.getTextArea().setFocus(true);
                runTimer();
                updateSelectionLabel();
            }
        });
    }

    public void onStart() {
        RefreshSongsList();
        eventBus.changeBody(view.getViewWidget());
//        service.getSongs(new AsyncCallback<List<SongBean>>() {
//
//            public void onFailure(Throwable caught)
//            {
//                Window.alert("Error during the song's list reading!");
//            }
//
//            public void onSuccess(List<SongBean> result) {
//                setSongs( result );
//                eventBus.changeBody(view.getViewWidget());
//            }
//
//        });
    }

    private void setSongs(List<SongBean> songs) {
        this.songs = songs;
        int nbSongs = songs.size();
        for ( int i = 0; i < nbSongs; i++ ) {
            displaySong(songs.get(i));
        }
    }

    private void displaySong( SongBean song ) {
        ListBox table = view.getSongListBox();
        table.addItem(song.getName());
    }

    public void onShowWork()
    {
        RefreshSongsList();
        eventBus.changeBody(view.getViewWidget());
    }

    public void onSelectSong(SongBean song)
    {
        TextArea textArea = view.getTextArea();
        textArea.setText("");
        textArea.setText(song.getText());
        timings = song.getTimings();
        view.getTimeLabel().setText(Integer.toString(timings.get(0).getFirstSymbol())+' '+Integer.toString(timings.get(0).getLastSymbol())+' '+Integer.toString(timings.get(0).getTimeStart())+' '+Integer.toString(timings.get(0).getTimeStop()));
    }

    private void RefreshSongsList()
    {
        view.getSongListBox().clear();
        service.getSongs(new AsyncCallback<List<SongBean>>() {

            public void onFailure(Throwable caught)
            {
                Window.alert("Error during the song's list reading!");
            }

            public void onSuccess(List<SongBean> result) {
                setSongs( result );
            }

        });
    }

    /**
     * Update the text in one of the selection labels.
     */
    private void updateSelectionLabel() {
        view.getSelectionLabel().setText("Selected" + ": " + view.getTextArea().getCursorPos() + ", " + view.getTextArea().getSelectionLength());
    }

    public void runTimer() {
        timer = new Timer() {
            public void run() {
                updateSelectionLabel();
                updateSelection();
                view.getTimeLabel().setText("Time: " + Integer.toString(time));
                time+=50;
            }
        };
        timer.scheduleRepeating(50);
    }

    private void updateSelection()
    {
//        if((time >= timings.get(cnt).getTimeStart()) && (time <= timings.get(cnt).getTimeStop())) {
//            textArea.setSelectionRange(timings.get(cnt).getFirstSymbol(), timings.get(cnt).getLastSymbol()-timings.get(cnt).getFirstSymbol());
//        }
        if(cnt<=timings.size()){
            if(time == timings.get(cnt).getTimeStart()){
                view.getTextArea().setSelectionRange(timings.get(cnt).getFirstSymbol(), timings.get(cnt).getLastSymbol()-timings.get(cnt).getFirstSymbol()+1);
            }
            if(time == timings.get(cnt).getTimeStop()){
                view.getTextArea().setSelectionRange(0, 0);
                cnt++;
            }
        }
    }
}
