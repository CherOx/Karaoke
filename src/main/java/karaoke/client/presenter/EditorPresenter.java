package karaoke.client.presenter;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import karaoke.client.MainEventBus;
import karaoke.client.view.EditorView;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 25.04.13
 * Time: 20:43
 * To change this template use File | Settings | File Templates.
 */
@Presenter(view = EditorView.class)
public class EditorPresenter extends BasePresenter<EditorView, MainEventBus> {

    public void onStart() {
        eventBus.changeBody(view.getViewWidget());
    }
}
