package karaoke.client.view;

import com.google.gwt.user.client.ui.*;
import karaoke.client.service.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 30.05.13
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class SongsEditorView extends Composite {

    private FlexTable body;
    private TextBox nameField;
    private TextArea textField;
    private Button saveButton;
    private Button updateButton;
    private Button cancelButton;

    public SongsEditorView() {
        body = new FlexTable();

        HorizontalPanel hPanelTop = new HorizontalPanel();
        body.setWidget(0, 0, hPanelTop);

        Label songNameLabel = new Label(Constants.NAME_FIELD_LABEL);
        hPanelTop.add(songNameLabel);

        nameField = new TextBox();
        hPanelTop.add(nameField);

        textField = new TextArea();
        textField.setVisibleLines(30);
        textField.setWidth(Constants.TEXT_FIELD_WIDTH);
        body.setWidget(1, 0, textField);

        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setSpacing(5);
        body.setWidget(2, 0, hPanel);

        saveButton = new Button(Constants.SAVE_BUTTON);
        hPanel.add(saveButton);

        updateButton = new Button(Constants.UPDATE_BUTTON);
        hPanel.add(updateButton);

        cancelButton = new Button(Constants.CANCEL_BUTTON);
        hPanel.add(cancelButton);

        DecoratorPanel decPanel = new DecoratorPanel();
        decPanel.setWidget(body);

        initWidget(decPanel);
    }

    public Widget getViewWidget() {
        return this;
    }

    public FlexTable getMainPanel() {
        return body;
    }

    public TextBox getNameField() {
        return nameField;
    }

    public TextArea getTextField() {
        return textField;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
