package karaoke.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import karaoke.client.SelectedTextBlock;
import karaoke.client.TextField;

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
    private TextField textField;
    private TextBox beginTextBox;
    private TextBox endTextBox;

    private ArrayList<String> texts = new ArrayList<String>();
    private ArrayList<SelectedTextBlock> timings = new ArrayList<SelectedTextBlock>();

    public EditorView() {
        vpanel = new VerticalPanel();
        vpanel.setSpacing(5);

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
        texts.add(textField.getText());
        Window.alert(textField.getText());
    }

    private void SaveTime() {
        timings.add(new SelectedTextBlock(textField.getSelectionBegin(), textField.getSelectionEnd(), Integer.parseInt(beginTextBox.getText()), Integer.parseInt(endTextBox.getText())));
    }
}
