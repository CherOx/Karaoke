package karaoke.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import karaoke.client.service.SelectedTextBlock;
import karaoke.client.service.Song;
import karaoke.client.service.TextField;
//import org.apache.xpath.operations.String;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 25.04.13
 * Time: 20:43
 * To change this template use File | Settings | File Templates.
 */
public class EditorView extends Composite {

    private VerticalPanel vpanel;
    private ListBox songsListBox;
//    private TextField textField;
    private TextArea textArea;
    private TextBox nameField;
    private Button saveButton;
    private Button updateButton;
//    private TextBox beginTextBox;
//    private TextBox endTextBox;
    private Button newSongButton;
    private Button editSongButton;
    private Song song;

    public EditorView() {
        vpanel = new VerticalPanel();
        vpanel.setSpacing(5);

        songsListBox = new ListBox(false);
        songsListBox.ensureDebugId("cwListBox-multiBox");
        songsListBox.setWidth("11em");
        songsListBox.setVisibleItemCount(10);
        VerticalPanel listBoxPanel = new VerticalPanel();
        listBoxPanel.setSpacing(4);
        listBoxPanel.add(new HTML("Select song"));
        listBoxPanel.add(songsListBox);
        vpanel.add(listBoxPanel);

//        textField = new TextField();
//        vpanel.add(textField);
        textArea = new TextArea();
        vpanel.add(textArea);

//        beginTextBox = new TextBox();
//        endTextBox = new TextBox();
//        Button saveTextButton = new Button("Save Text");
//        saveTextButton.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                SaveText();
//            }
//        });
//        Button saveTimeButton = new Button("Save Time");
//        saveTimeButton.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//                SaveTime();
//            }
//        });

        newSongButton = new Button("Add new song");
        vpanel.add(newSongButton);

        editSongButton = new Button("Edit song");
        vpanel.add(editSongButton);

        nameField = new TextBox();
        vpanel.add(nameField);

        saveButton = new Button("Save");
        vpanel.add(saveButton);

        updateButton = new Button("Update");
        vpanel.add(updateButton);

//        vpanel.add(beginTextBox);
//        vpanel.add(endTextBox);
//        vpanel.add(saveTextButton);
//        vpanel.add(saveTimeButton);

        initWidget(vpanel);
    }

    public Widget getViewWidget() {
        return this;
    }

    public ListBox getSongsListBox() {
        return songsListBox;
    }

//    public TextField getTextField() {
//        return textField;
//    }
    public TextArea getTextArea() {
        return textArea;
    }

    public TextBox getNameField() {
        return nameField;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public Button getEditSongButton() {
        return editSongButton;
    }

    public Button getNewSongButton()
    {
        return newSongButton;
    }

//    private void SaveText() {
//        song = new Song("Там высоко", textField.getText());
//        Window.alert("Song 'Там высоко' saved");
//    }

//    private void SaveTime() {
//        song.addTiming(new SelectedTextBlock(textField.getSelectionBegin(), textField.getSelectionEnd(), Integer.parseInt(beginTextBox.getText()), Integer.parseInt(endTextBox.getText())));
//    }
}
