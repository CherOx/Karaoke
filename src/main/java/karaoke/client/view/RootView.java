package karaoke.client.view;

import com.google.gwt.user.client.ui.*;
import karaoke.client.service.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 18.04.13
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public class RootView extends Composite {

    private FlexTable body = new FlexTable();
    private Label appName = new Label();
    private Button editSongsButton;
    private Button playSongsButton;

    public RootView() {
        body.getFlexCellFormatter().setColSpan(0, 0, 2);
        body.getFlexCellFormatter().setRowSpan(1, 1, 3);
        body.getCellFormatter().setHeight(1, 0, "30px");
        body.getCellFormatter().setHeight(2, 0, "200px");
//        body.setBorderWidth(2);
        body.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
        body.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
//        body.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
//        body.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
        body.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_TOP);
        body.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);
        body.getCellFormatter().setVerticalAlignment(3, 0, HasVerticalAlignment.ALIGN_TOP);

        appName.setText(Constants.APPLICATION_NAME);
        body.setWidget(0, 0, appName);

        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setSpacing(5);
        body.setWidget(1, 0, hPanel);

        editSongsButton = new Button(Constants.OPEN_EDITOR_VIEW_BUTTON);
        hPanel.add(editSongsButton);

        playSongsButton = new Button(Constants.OPEN_PLAYER_VIEW_BUTTON);
        hPanel.add(playSongsButton);

        initWidget(body);
    }

    public Button getEditSongsButton() {
        return editSongsButton;
    }

    public Button getPlaySongsButton() {
        return playSongsButton;
    }

    public void setLeftTopWidget(Widget widget) {
        body.setWidget(2, 0, widget);
    }

    public void setLeftBottomWidget(Widget widget) {
        body.setWidget(3, 0, widget);
    }

    public void setRightWidget(Widget widget) {
        body.setWidget(1, 1, widget);
    }

}
