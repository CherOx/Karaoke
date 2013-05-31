package karaoke.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import karaoke.client.MainEventBus;
import karaoke.client.view.RootView;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 18.04.13
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 */
@Presenter(view = RootView.class)
public class RootPresenter extends BasePresenter<RootView, MainEventBus> {

//    @Override
//    public void bind()
//    {
//        view.getButtonWork().addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                eventBus.showWork();
//            }
//        });
//        view.getButtonEditor().addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                eventBus.showEditor();
//            }
//        });
//    }
//
//    public void onChangeBody(Widget newPage) {
//        view.setBody(newPage);
//    }
//
//    public void onDisplayMessage(String message) {
//        view.getMessage().setText(message);
//    }
//
//    public void onStart() {
//        view.getMessage().setText("New Message");
//    }

    @Override
    public void bind() {
        view.getEditSongsButton().addClickHandler(new EditSongsButtonHandler());
        view.getPlaySongsButton().addClickHandler(new PlaySongsButtonHandler());
        view.getPlaySongsButton().setVisible(false);
    }

    /////////////////////////////////////////
    // Events handlers
    /////////////////////////////////////////

    public void onChangeLeftTopWidget(Widget newWidget) {
        view.setLeftTopWidget(newWidget);
    }

    public void onChangeLeftBottomWidget(Widget newWidget) {
        view.setLeftBottomWidget(newWidget);
    }

    public void onChangeRightWidget(Widget newWidget) {
        view.setRightWidget(newWidget);
    }

    public void onRunTimer() {
        view.getEditSongsButton().setEnabled(false);
    }

    public void onStopTimer() {
        view.getEditSongsButton().setEnabled(true);
    }

    /////////////////////////////////////////
    // Objects handlers
    /////////////////////////////////////////

    private class EditSongsButtonHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            eventBus.showEditor();
            view.getPlaySongsButton().setVisible(true);
            view.getEditSongsButton().setVisible(false);
        }
    }

    private class PlaySongsButtonHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            eventBus.showPlayer();
            view.getPlaySongsButton().setVisible(false);
            view.getEditSongsButton().setVisible(true);
        }
    }
}
