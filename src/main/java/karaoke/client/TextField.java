package karaoke.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 25.04.13
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */
public class TextField extends HorizontalPanel {

    private TextArea textArea;
    private Label label;

    public TextField() {
        super();
        // Add the text box and label to a panel
        this.setSpacing(4);
        textArea = new TextArea();
        this.add(textArea);

        // Add handlers
//        if (addSelection) {
            // Create the new label
        label = new Label("Selected" + ": Position - " + 0 + "; Length - " + 0);

            // Add a KeyUpHandler
        textArea.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                updateSelectionLabel();
            }
        });

            // Add a ClickHandler
        textArea.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                updateSelectionLabel();
            }
        });

            // Add the label to the box
        this.add(label);
//        }
    }

    public void updateSelectionLabel() {
        label.setText("Selected" + ": Position - " + textArea.getCursorPos() + "; Length - " + textArea.getSelectionLength());
    }

    public String getText(){
        return textArea.getText();
    }

    public int getSelectionBegin(){
        return textArea.getCursorPos();
    }

    public int getSelectionEnd(){
        return textArea.getCursorPos() + textArea.getSelectionLength();
    }
}
