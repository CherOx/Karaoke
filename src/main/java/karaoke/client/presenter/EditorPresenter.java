package karaoke.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import karaoke.client.MainEventBus;
import karaoke.client.SongServiceAsync;
import karaoke.client.bean.SongBean;
import karaoke.client.service.SelectedTextBlock;
import karaoke.client.view.EditorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 25.04.13
 * Time: 20:43
 * To change this template use File | Settings | File Templates.
 */
@Presenter(view = EditorView.class)
public class EditorPresenter extends BasePresenter<EditorView, MainEventBus> {

    private SongBean song;
    private List<SongBean> songs;
//    private List<SelectedTextBlock> timings;

    @Inject
    private SongServiceAsync service;

    @Override
    public void bind()
    {
        view.getSongsListBox().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                eventBus.selectSong(songs.get(view.getSongsListBox().getSelectedIndex()));
            }
        });
        view.getNewSongButton().addClickHandler(new NewSongClickHandler());
        view.getEditSongButton().addClickHandler(new EditSongClickHandler());
        view.getSaveButton().addClickHandler(new SaveClickHandler());
        view.getUpdateButton().addClickHandler(new UpdateSongClickHandler());
        view.getNameField().setVisible(false);
        view.getTextArea().setVisible(false);
        view.getSaveButton().setVisible(false);
        view.getUpdateButton().setVisible(false);
        view.getEditSongButton().setEnabled(false);
    }

    private void ShowAddDialog(boolean visible)
    {
        view.getNameField().setText("");
        view.getTextArea().setText("");
        view.getNameField().setVisible(visible);
        view.getTextArea().setVisible(visible);
        view.getSaveButton().setVisible(visible);
    }

    private void ShowEditDialog(boolean visible)
    {
        if(visible)
        {
            view.getNameField().setText(song.getName());
            view.getTextArea().setText(song.getText());
            ExtractTimings(song);
        }
        else
        {
            view.getNameField().setText("");
            view.getTextArea().setText("");
        }
        view.getNameField().setVisible(visible);
        view.getTextArea().setVisible(visible);
        view.getUpdateButton().setVisible(visible);
        view.getEditSongButton().setEnabled(false);
    }

    public void onStart() {
        RefreshSongsList();
        eventBus.changeBody(view.getViewWidget());
    }

    public void onShowEditor()
    {
        RefreshSongsList();
        eventBus.changeBody(view.getViewWidget());
    }

    public void onSaveSong(SongBean newSong) {
        service.createSong( newSong, new AsyncCallback<Void>() {
            public void onFailure( Throwable caught ) {
                Window.alert("Error during creating new song!");
            }
            public void onSuccess( Void result ) {
                Window.alert("Song created!");
                ShowAddDialog(false);
                RefreshSongsList();
            }
        });
    }

    public void onUpdateSong(SongBean newSong) {
        service.updateSong( newSong, new AsyncCallback<Void>() {
            public void onFailure( Throwable caught ) {
                Window.alert("Error during updating new song!");
            }
            public void onSuccess( Void result ) {
                Window.alert("Song updated!");
                ShowEditDialog(false);
                RefreshSongsList();
            }
        });
    }

    public void onSelectSong(SongBean selectedSong)
    {
        song = selectedSong;
        view.getEditSongButton().setEnabled(true);

//        Window.alert("Song selected");
        StringBuilder str = new StringBuilder();
        for(int i=0;i<song.getTimings().size();i++)
        {
            StringBuilder s = new StringBuilder();
            s.append(song.getTimings().get(i).getFirstSymbol());
            s.append(" ");
            s.append(song.getTimings().get(i).getLastSymbol());
            s.append(" ");
            s.append(song.getTimings().get(i).getTimeStart());
            s.append(" ");
            s.append(song.getTimings().get(i).getTimeStop());
//            Window.alert(s.toString());
            str.append(s.toString());
            str.append("\n");
        }
//        Window.alert(str.toString());
        view.getTempTextArea().setText(str.toString());
    }

    private void RefreshSongsList()
    {
        view.getSongsListBox().clear();
        service.getSongs(new AsyncCallback<List<SongBean>>() {
            public void onFailure(Throwable caught)
            {
                Window.alert("Error during the song's list reading!");
            }
            public void onSuccess(List<SongBean> result) {
                SetSongs(result);
            }
        });
    }

    private void SetSongs(List<SongBean> songs) {
        this.songs = songs;
        int nbSongs = songs.size();
        for ( int i = 0; i < nbSongs; i++ ) {
            DisplaySong(songs.get(i));
        }
    }

    private void DisplaySong(SongBean song) {
        ListBox table = view.getSongsListBox();
        table.addItem(song.getName());
    }

    private void ExtractTimings(SongBean inSong)
    {
        StringBuilder str = new StringBuilder();
        int start = 0;
        int begSymbol, endSymbol;
        for(int i=0; i<inSong.getTimings().size(); i++)
        {
            begSymbol = inSong.getTimings().get(i).getFirstSymbol();
            endSymbol = inSong.getTimings().get(i).getLastSymbol();
            str.append(inSong.getText(), start, begSymbol);
            str.append("[=");
            str.append(inSong.getTimings().get(i).getTimeStart());
            str.append("=");
            str.append(inSong.getText(), begSymbol, endSymbol+1);
            str.append("=");
            str.append(inSong.getTimings().get(i).getTimeStop());
            str.append("=]");
            start = endSymbol+1;
        }
        str.append(inSong.getText(), start, inSong.getText().length());
        view.getTextArea().setText(str.toString());
    }

    private boolean MakeSong(SongBean inSong)
    {
        List<SelectedTextBlock> stbList = new ArrayList<SelectedTextBlock>();
        List<String> str = new ArrayList<String>();

        String[] arr = view.getTextArea().getText().split("[\\[\\]]");
        for (int i = 0; i < arr.length; i++) {
            String[] arr2 = arr[i].split("=");
            for (int j = 0; j < arr2.length; j++) {
                str.add(arr2[j]);
            }
        }

        boolean isTimeStart = true;
        StringBuilder sb = new StringBuilder();
        SelectedTextBlock stb = new SelectedTextBlock();
        for(String s : str)
        {
            if(!s.isEmpty())
            {
                try
                {
                    int time = Integer.parseInt(s);
                    if(isTimeStart)
                    {
                        stb = new SelectedTextBlock();
                        stb.setTimeStart(time);
                        stb.setFirstSymbol(sb.length());
                    }
                    else
                    {
                        stb.setTimeStop(time);
                        stb.setLastSymbol(sb.length()-1);
                        stbList.add(stb);
                        stb = new SelectedTextBlock();
                    }
                    isTimeStart = !isTimeStart;
                }
                catch (Exception e)
                {
                    sb.append(s);
                }
            }
        }
//        for(SelectedTextBlock sss : stbList)
//        {
//            StringBuilder s = new StringBuilder();
//            s.append(sss.getFirstSymbol());
//            s.append(" ");
//            s.append(sss.getLastSymbol());
//            s.append(" ");
//            s.append(sss.getTimeStart());
//            s.append(" ");
//            s.append(sss.getTimeStop());
//            Window.alert(s.toString());
//        }
        inSong.setTimings(stbList);
        inSong.setText(sb.toString());
        inSong.setName(view.getNameField().getText());

//        for(SelectedTextBlock sss : inSong.getTimings())
//        {
//            StringBuilder s = new StringBuilder();
//            s.append(sss.getFirstSymbol());
//            s.append(" ");
//            s.append(sss.getLastSymbol());
//            s.append(" ");
//            s.append(sss.getTimeStart());
//            s.append(" ");
//            s.append(sss.getTimeStop());
//            Window.alert(s.toString());
//        }

        return isTimeStart;
    }

    class NewSongClickHandler implements ClickHandler {
        public void onClick(ClickEvent event) {
            ShowAddDialog(true);
        }
    }

    class EditSongClickHandler implements ClickHandler {
        public void onClick(ClickEvent event) {
            ShowEditDialog(true);
        }
    }

    class UpdateSongClickHandler implements ClickHandler {
        public void onClick(ClickEvent event) {
//            song = new SongBean();
            if(MakeSong(song))
            {
                eventBus.updateSong(song);
            }
            else
            {
                Window.alert("There are some errors in timings!");
            }
        }
    }

    class SaveClickHandler implements ClickHandler {
        public void onClick( ClickEvent event ) {
            song = new SongBean();
            song.setName(view.getNameField().getText());
            song.setText(view.getTextArea().getText());
            ArrayList<SelectedTextBlock> timings = new ArrayList<SelectedTextBlock>();
            timings.add(new SelectedTextBlock(0, 0, 0, 0));
            song.setTimings(timings);
            eventBus.saveSong(song);
        }
    }

}
