package karaoke.client.view;

import com.google.gwt.user.client.ui.*;
import karaoke.client.service.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 31.05.13
 * Time: 0:40
 * To change this template use File | Settings | File Templates.
 */
public class EditButtonsView extends Composite {

    private Button addButton;
    private Button deleteButton;
    private Button editButton;

    public EditButtonsView() {
        FlexTable body = new FlexTable();

        VerticalPanel vPanel = new VerticalPanel();
        vPanel.setSpacing(5);
        body.setWidget(0, 0, vPanel);

        addButton = new Button(Constants.ADD_SONG_BUTTON);
        addButton.setWidth(Constants.BUTTON_WIDTH);
        vPanel.add(addButton);

        deleteButton = new Button(Constants.DELETE_SONG_BUTTON);
        deleteButton.setWidth(Constants.BUTTON_WIDTH);
        vPanel.add(deleteButton);

        editButton = new Button(Constants.EDIT_SONG_BUTTON);
        editButton.setWidth(Constants.BUTTON_WIDTH);
        vPanel.add(editButton);

        initWidget(body);
    }

    public Widget getViewWidget() {
        return this;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getEditButton() {
        return editButton;
    }
}
