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

    @Override
    public void bind()
    {
        view.getButtonWork().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                eventBus.showWork();
            }
        });
        view.getButtonEditor().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                eventBus.showEditor();
            }
        });
    }

    public void onChangeBody(Widget newPage) {
        view.setBody(newPage);
    }

    public void onDisplayMessage(String message) {
        view.getMessage().setText(message);
    }

    public void onStart() {
        view.getMessage().setText("New Message");
    }

}
