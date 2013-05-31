package karaoke.client;

import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.event.EventBus;
import karaoke.client.bean.SongBean;
import karaoke.client.presenter.*;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 19.03.13
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
@Events(startPresenter = RootPresenter.class)
public interface MainEventBus extends EventBus {

//    @Start
//    @Event( handlers = { RootPresenter.class, WorkPresenter.class } )
//    public void start();
//
//    @Event( handlers = { RootPresenter.class } )
//    public void changeBody(Widget widget);
//
//    @Event( handlers = { RootPresenter.class } )
//    public void displayMessage(String string);
//
//    @Event( handlers = { WorkPresenter.class } )
//    public void showWork();
//
//    @Event( handlers = { EditorPresenter.class } )
//    public void showEditor();
//
////    @Event( handlers = { WorkPresenter.class } )
////    public void selectSong( SongBean song );
//    @Event( handlers = { EditorPresenter.class } )
//    public void selectSong( SongBean song );
//
//    @Event( handlers = { EditorPresenter.class } )
//    public void saveSong( SongBean song );
//
//    @Event( handlers = { EditorPresenter.class } )
//    public void updateSong( SongBean song );

    @Start
    @Event(handlers = {SongsListPresenter.class, SongPlayerPresenter.class})
    public void start();

    @Event(handlers = {RootPresenter.class})
    public void changeLeftTopWidget(Widget widget);

    @Event(handlers = {RootPresenter.class})
    public void changeLeftBottomWidget(Widget widget);

    @Event(handlers = {RootPresenter.class})
    public void changeRightWidget(Widget widget);

    @Event(handlers = {SongPlayerPresenter.class, EditButtonsPresenter.class})
    public void selectSong(SongBean song);

    @Event(handlers = {SongPlayerPresenter.class, EditButtonsPresenter.class})
    public void showPlayer();

    @Event(handlers = {SongsEditorPresenter.class, EditButtonsPresenter.class})
    public void showEditor();

    @Event(handlers = {RootPresenter.class, SongPlayerPresenter.class, SongsListPresenter.class})
    public void runTimer();

    @Event(handlers = {RootPresenter.class, SongPlayerPresenter.class, SongsListPresenter.class})
    public void stopTimer();

    @Event(handlers = {SongsEditorPresenter.class, EditButtonsPresenter.class, SongsListPresenter.class, SongsListPresenter.class})
    public void addSong();

    @Event(handlers = {SongsEditorPresenter.class})
    public void saveSong(SongBean song);

    @Event(handlers = {SongsEditorPresenter.class, EditButtonsPresenter.class, SongsListPresenter.class, SongPlayerPresenter.class})
    public void endOfEditing();

    @Event(handlers = {SongsEditorPresenter.class, EditButtonsPresenter.class, SongsListPresenter.class, SongsListPresenter.class})
    public void editSong(SongBean song);

    @Event(handlers = {SongsEditorPresenter.class})
    public void updateSong(SongBean song);

    @Event(handlers = {SongsEditorPresenter.class})
    public void deleteSong(SongBean song);
}
