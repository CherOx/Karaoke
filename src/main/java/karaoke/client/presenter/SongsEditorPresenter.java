package karaoke.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import karaoke.client.MainEventBus;
import karaoke.client.SongServiceAsync;
import karaoke.client.bean.SongBean;
import karaoke.client.service.Constants;
import karaoke.client.service.SelectedTextBlock;
import karaoke.client.view.SongsEditorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 30.05.13
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
@Presenter(view = SongsEditorView.class)
public class SongsEditorPresenter extends BasePresenter<SongsEditorView, MainEventBus> {

    SongBean song;

    @Inject
    private SongServiceAsync service;

    @Override
    public void bind() {
        view.getSaveButton().addClickHandler(new SaveButtonClickHandler());
        view.getUpdateButton().addClickHandler(new UpdateButtonClickHandler());
        view.getCancelButton().addClickHandler(new CancelButtonClickHandler());
        HideDialog();
//        view.getMainPanel().setVisible(false);
//        view.getNameField().setEnabled(false);
//        view.getTextField().setEnabled(false);
//        view.getSaveButton().setEnabled(false);
//        view.getCancelButton().setEnabled(false);
    }

    /////////////////////////////////////////
    // Events handlers
    /////////////////////////////////////////

    public void onShowEditor() {
        eventBus.changeRightWidget(view.getViewWidget());
    }

    public void onAddSong() {
        view.getSaveButton().setVisible(true);
        view.getMainPanel().setVisible(true);
    }

    public void onSaveSong(SongBean inSong) {
        service.createSong(inSong, new AsyncCallback<Void>() {
            public void onFailure(Throwable caught) {
                Window.alert(Constants.NEW_SONG_CREATING_ERROR);
            }
            public void onSuccess( Void result ) {
                Window.alert(Constants.NEW_SONG_CREATED);
                eventBus.endOfEditing();
            }
        });
    }

    public void onEndOfEditing() {
        HideDialog();
//        view.getMainPanel().setVisible(false);
//        song = null;
    }

//    public void onCancelEditing() {
//        HideDialog();
////        view.getMainPanel().setVisible(false);
////        song = null;
//    }

    public void onEditSong(SongBean inSong) {
        song = inSong;
        view.getUpdateButton().setVisible(true);
        view.getNameField().setText(inSong.getName());
        view.getTextField().setText(GetTextWithTimings(inSong));
        view.getMainPanel().setVisible(true);
    }

    public void onUpdateSong(SongBean inSong) {
        service.updateSong(inSong, new AsyncCallback<Void>() {
            public void onFailure(Throwable caught) {
                Window.alert(Constants.SONG_UPDATING_ERROR);
            }
            public void onSuccess( Void result ) {
                Window.alert(Constants.SONG_UPDATED);
                eventBus.endOfEditing();
            }
        });
    }

    public void onDeleteSong(SongBean inSong) {
        service.deleteSong(inSong, new AsyncCallback<Void>() {
            public void onFailure(Throwable caught) {
                Window.alert(Constants.SONG_DELETING_ERROR);
            }
            public void onSuccess( Void result ) {
                Window.alert(Constants.SONG_DELETED);
                eventBus.endOfEditing();
            }
        });
    }

    /////////////////////////////////////////
    // Functions
    /////////////////////////////////////////

    private boolean MakeSong() {
        if((view.getNameField().getText().isEmpty()) || (view.getTextField().getText().isEmpty())) {
            Window.alert(Constants.NOT_FILLED_FIELDS_ERROR);
            return false;
        }
        song = new SongBean();
        song.setName(view.getNameField().getText());
        song.setText(view.getTextField().getText());
        ArrayList<SelectedTextBlock> timings = new ArrayList<SelectedTextBlock>();
        timings.add(new SelectedTextBlock(0, 0, 0, 0));
        song.setTimings(timings);
        return true;
    }

    private void HideDialog() {
        song = null;
        view.getNameField().setText("");
        view.getTextField().setText("");
        view.getSaveButton().setVisible(false);
        view.getUpdateButton().setVisible(false);
        view.getMainPanel().setVisible(false);
    }

    private String GetTextWithTimings(SongBean inSong)
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
        return str.toString();
    }

    private boolean RemakeSong(SongBean inSong)
    {
        if((view.getNameField().getText().isEmpty()) || (view.getTextField().getText().isEmpty())) {
            return false;
        }

        List<SelectedTextBlock> stbList = new ArrayList<SelectedTextBlock>();
        List<String> str = new ArrayList<String>();

        String[] arr = view.getTextField().getText().split("[\\[\\]]");
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
        inSong.setTimings(stbList);
        inSong.setText(sb.toString());
        inSong.setName(view.getNameField().getText());

        return isTimeStart;
    }

    /////////////////////////////////////////
    // Objects handlers
    /////////////////////////////////////////

    private class SaveButtonClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            if(MakeSong()) {
                eventBus.saveSong(song);
            }
        }
    }

    private class UpdateButtonClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            if(RemakeSong(song))
            {
                eventBus.updateSong(song);
            }
            else
            {
                Window.alert(Constants.WRONG_TIMINGS_ERROR + "\nor\n" +Constants.NOT_FILLED_FIELDS_ERROR);
            }
        }
    }

    private class CancelButtonClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
//            eventBus.cancelEditing();
            eventBus.endOfEditing();
        }
    }
}
