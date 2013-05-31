package karaoke.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import karaoke.client.MainEventBus;
import karaoke.client.bean.SongBean;
import karaoke.client.view.EditButtonsView;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 31.05.13
 * Time: 0:46
 * To change this template use File | Settings | File Templates.
 */
@Presenter(view = EditButtonsView.class)
public class EditButtonsPresenter  extends BasePresenter<EditButtonsView, MainEventBus> {

    private SongBean song;

    @Override
    public void bind() {
        view.getAddButton().addClickHandler(new AddButtonClickHandler());
        view.getEditButton().addClickHandler(new EditButtonClickHandler());
        view.getDeleteButton().addClickHandler(new DeleteButtonClickHandler());
        view.getAddButton().setEnabled(true);
        view.getEditButton().setEnabled(false);
        view.getDeleteButton().setEnabled(false);
    }

    /////////////////////////////////////////
    // Events handlers
    /////////////////////////////////////////

    public void onShowEditor() {
        eventBus.changeLeftBottomWidget(view.getViewWidget());
    }

    public void onShowPlayer() {
        eventBus.changeLeftBottomWidget(null);
    }

    public void onAddSong() {
        eventBus.changeLeftBottomWidget(null);
    }

    public void onSelectSong(SongBean inSong) {
        song = inSong;
        view.getEditButton().setEnabled(true);
        view.getDeleteButton().setEnabled(true);
    }

    public void onEndOfEditing() {
        view.getEditButton().setEnabled(false);
        view.getDeleteButton().setEnabled(false);
        eventBus.changeLeftBottomWidget(view.getViewWidget());
    }

//    public void onCancelEditing() {
//        eventBus.changeLeftBottomWidget(view.getViewWidget());
//    }

    public void onEditSong(SongBean inSong) {
        eventBus.changeLeftBottomWidget(null);
    }

    /////////////////////////////////////////
    // Functions
    /////////////////////////////////////////

    /////////////////////////////////////////
    // Objects handlers
    /////////////////////////////////////////

    private class AddButtonClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            eventBus.addSong();
        }
    }

    private class EditButtonClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            eventBus.editSong(song);
        }
    }

    private class DeleteButtonClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            eventBus.deleteSong(song);
        }
    }
}
