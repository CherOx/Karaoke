package karaoke.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import karaoke.client.service.SelectedTextBlock;
import karaoke.client.service.Song;
import karaoke.client.service.TextField;

import java.util.ArrayList;
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
    private ListBox listBox;
    private TextField textField;
    private TextBox beginTextBox;
    private TextBox endTextBox;
    private Song song;

    public EditorView() {
        vpanel = new VerticalPanel();
        vpanel.setSpacing(5);

        listBox = new ListBox(false);
        listBox.ensureDebugId("cwListBox-multiBox");
        listBox.setWidth("11em");
        listBox.setVisibleItemCount(10);
        VerticalPanel listBoxPanel = new VerticalPanel();
        listBoxPanel.setSpacing(4);
        listBoxPanel.add(new HTML("Select song"));
        listBoxPanel.add(listBox);
        vpanel.add(listBoxPanel);

        textField = new TextField();
        beginTextBox = new TextBox();
        endTextBox = new TextBox();
        Button saveTextButton = new Button("Save Text");
        saveTextButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                SaveText();
            }
        });
        Button saveTimeButton = new Button("Save Time");
        saveTimeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                SaveTime();
            }
        });

        vpanel.add(textField);
        vpanel.add(beginTextBox);
        vpanel.add(endTextBox);
        vpanel.add(saveTextButton);
        vpanel.add(saveTimeButton);

        initWidget(vpanel);
    }

    public Widget getViewWidget() {
        return this;
    }

    private void SaveText() {
        song = new Song("Там высоко", textField.getText());
        Window.alert("Song 'Там высоко' saved");
    }

    private void SaveTime() {
        song.addTiming(new SelectedTextBlock(textField.getSelectionBegin(), textField.getSelectionEnd(), Integer.parseInt(beginTextBox.getText()), Integer.parseInt(endTextBox.getText())));
    }
}
