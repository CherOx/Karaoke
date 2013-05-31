package karaoke.client.view;

import com.google.gwt.user.client.ui.*;
import karaoke.client.service.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 30.05.13
 * Time: 20:37
 * To change this template use File | Settings | File Templates.
 */
public class SongPlayerView extends Composite {

    private TextArea textField;
    private Label timeLabel;
    private Button runButton;
    private Button stopButton;

    public SongPlayerView() {
        FlexTable body = new FlexTable();

        timeLabel = new Label(Constants.TIME_LABEL + "0");
        body.setWidget(0, 0, timeLabel);
        body.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);

        textField = new TextArea();
        textField.setVisibleLines(30);
        textField.setWidth(Constants.TEXT_FIELD_WIDTH);
        body.setWidget(1, 0, textField);

        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setSpacing(5);
        body.setWidget(2, 0, hPanel);

        runButton = new Button(Constants.RUN_BUTTON);
        hPanel.add(runButton);

        stopButton = new Button(Constants.STOP_BUTTON);
        hPanel.add(stopButton);

        DecoratorPanel decPanel = new DecoratorPanel();
        decPanel.setWidget(body);

        initWidget(decPanel);
    }

    public Widget getViewWidget() {
        return this;
    }

    public TextArea getTextField() {
        return textField;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public Button getRunButton() {
        return runButton;
    }

    public Button getStopButton() {
        return stopButton;
    }
}
