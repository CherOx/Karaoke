package karaoke.client.presenter;

import karaoke.client.MainEventBus;
import karaoke.client.SelectedTextBlock;
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

    String string = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\n Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    int[] timings = {100, 500, 1000,100,500,1000,100,500,1000,100,500,1000,100,500,1000,100,500,1000,100,500,1000};
    String str2 =  "{100-500}Lorem ipsum {600-1000}dolor sit amet, {1001-2000}consectetur adipisicing elit, {3000-5000}sed do eiusmod tempor {5001-5500}incididunt ut labore et dolore magna aliqua.\n Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\n Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
//    SelectedTextBlock[] stb = new SelectedTextBlock[4]{new SelectedTextBlock(1,10,10,1000), };
    ArrayList<SelectedTextBlock> list = new ArrayList<SelectedTextBlock>();

    public void onStart() {
        list.add(new SelectedTextBlock(0,0,0,0));
        list.add(new SelectedTextBlock(1,10,10,1000));
        list.add(new SelectedTextBlock(11,25,2000,2500));
        list.add(new SelectedTextBlock(30,50,2501,5000));
        eventBus.changeBody(view.getViewWidget());
        view.setText(str2, list);
    }

}
