package karaoke.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import karaoke.client.MainEventBus;
import karaoke.client.bean.SongBean;
import karaoke.client.service.Constants;
import karaoke.client.service.SelectedTextBlock;
import karaoke.client.view.SongPlayerView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 30.05.13
 * Time: 20:47
 * To change this template use File | Settings | File Templates.
 */
@Presenter(view = SongPlayerView.class)
public class SongPlayerPresenter extends BasePresenter<SongPlayerView, MainEventBus> {

    private List<SelectedTextBlock> timings;
    private Timer timer;
    private int time = 0;
    private int cnt = 0;
    private boolean isWaitForStartSymbol = true;

    @Override
    public void bind() {
        view.getRunButton().addClickHandler(new RunButtonClickHandler());
        view.getStopButton().addClickHandler(new StopButtonClickHandler());
        view.getRunButton().setEnabled(false);
        view.getStopButton().setEnabled(false);
        view.getTextField().setReadOnly(true);

        timer = new TimerHandler();
//        timer.scheduleRepeating(Constants.TIMER_REPEATING_PERIOD);
    }

    /////////////////////////////////////////
    // Events handlers
    /////////////////////////////////////////

    public void onStart() {
        eventBus.changeRightWidget(view.getViewWidget());
    }

    public void onShowPlayer() {
        eventBus.changeRightWidget(view.getViewWidget());
    }

    public void onSelectSong(SongBean inSong) {
        view.getTextField().setText("");
        view.getTextField().setText(inSong.getText());
        timings = inSong.getTimings();
        view.getRunButton().setEnabled(true);
    }

    public void onRunTimer() {
        RunTimer();
    }

    public void onStopTimer() {
        StopTimer();
    }

    public void onEndOfEditing() {
        view.getTextField().setText("");
        timings = null;
        view.getRunButton().setEnabled(false);
    }

    /////////////////////////////////////////
    // Functions
    /////////////////////////////////////////

    public void RunTimer() {
        view.getRunButton().setEnabled(false);
        view.getStopButton().setEnabled(true);
        view.getTextField().setFocus(true);
        timer.scheduleRepeating(Constants.TIMER_REPEATING_PERIOD);
    }

    public void StopTimer() {
        timer.cancel();
        time = 0;
        cnt = 0;
        view.getRunButton().setEnabled(true);
        view.getStopButton().setEnabled(false);
        view.getTimeLabel().setText(Constants.TIME_LABEL + "0");
    }

    private void UpdateSelection() {
        view.getTextField().setFocus(true);
        if (cnt < timings.size()) {
            if ((isWaitForStartSymbol) && (time >= timings.get(cnt).getTimeStart())) {
                view.getTextField().setSelectionRange(timings.get(cnt).getFirstSymbol(), timings.get(cnt).getLastSymbol() - timings.get(cnt).getFirstSymbol() + 1);
            }
            if ((!isWaitForStartSymbol) && (time >= timings.get(cnt).getTimeStop())) {
                view.getTextField().setSelectionRange(0, 0);
                cnt++;
            }
            isWaitForStartSymbol = !isWaitForStartSymbol;
        } else {
//            StopTimer();
            eventBus.stopTimer();
        }
    }

    /////////////////////////////////////////
    // Objects handlers
    /////////////////////////////////////////

    private class RunButtonClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
//            RunTimer();
            eventBus.runTimer();
        }
    }

    private class StopButtonClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
//            StopTimer();
            eventBus.stopTimer();
        }
    }

    private class TimerHandler extends Timer {
        @Override
        public void run() {
            UpdateSelection();
            view.getTimeLabel().setText(Constants.TIME_LABEL + Integer.toString(time));
            time += Constants.TIMER_REPEATING_PERIOD;
        }
    }
}
