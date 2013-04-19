package karaoke.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import karaoke.client.SelectedTextBlock;

import java.util.List;

public class WorkView extends Composite {

    TextArea textArea;
    Label label;
//    final RichTextArea textArea;
//    RichTextArea.Formatter formatter;
    VerticalPanel vpanel;
    private int counter = 0;
    Timer timer;
    List<SelectedTextBlock> timings;
    int currentWord = 0;
    int cnt = 0;
    int time = 0;

    public WorkView() {

        vpanel = new VerticalPanel();
        vpanel.setSpacing(5);

        textArea = new TextArea();
        textArea.ensureDebugId("cwBasicText-textarea");
        textArea.setVisibleLines(25);
        vpanel.add(new HTML("<br><br>" + "Selected"));
        vpanel.add(createTextExample(textArea, true));

//        textArea = new TextBox();
//        textArea.setText("Some not long Text!");
//        formatter = textArea.getFormatter();
        Button button = new Button("Run");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                textArea.setFocus(true);
                runTimer();
//                setSelectionRange(textArea.getElement(), 10, 5);
//                RichTextArea.Formatter f = textArea.getFormatter();
//                f.setBackColor("green");
//                setSelectionRange(textArea.getElement(), 0, 0);
//                textArea.setFocus(true);
            }
        });
        vpanel.add(textArea);
        vpanel.add(button);

        initWidget(vpanel);
    }

    public Widget getViewWidget() {
        return this;
    }

    public void setText(String string, List<SelectedTextBlock> timings){
        this.timings = timings;
        textArea.setText(string);
        textArea.setFocus(true);
//        runTimer();
    }

    /**
     * Create a TextBox example that includes the text box and an optional handler
     * that updates a Label with the currently selected text.
     *
     * @param textBox the text box to handle
     * @param addSelection add handlers to update label
     * @return the Label that will be updated
     */
    private HorizontalPanel createTextExample(final TextBoxBase textBox, boolean addSelection) {
        // Add the text box and label to a panel
        HorizontalPanel hPanel = new HorizontalPanel();
        hPanel.setSpacing(4);
        hPanel.add(textBox);

        // Add handlers
        if (addSelection) {
            // Create the new label
            label = new Label("Selected" + ": 0, 0");

            // Add a KeyUpHandler
            textBox.addKeyUpHandler(new KeyUpHandler() {
                public void onKeyUp(KeyUpEvent event) {
                    updateSelectionLabel();
                }
            });

            // Add a ClickHandler
            textBox.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    updateSelectionLabel();
                }
            });

            // Add the label to the box
            hPanel.add(label);
        }

        // Return the panel
        return hPanel;
    }

    /**
     * Update the text in one of the selection labels.
     */
    private void updateSelectionLabel() {
        label.setText("Selected" + ": " + textArea.getCursorPos() + ", " + textArea.getSelectionLength());
    }

    public void runTimer() {
        timer = new Timer() {
            public void run() {
//                updateSelection();
                time++;
                updSelection();
                updateSelectionLabel();
            }
        };
        timer.scheduleRepeating(1);
    }

    private void updateSelection(){
        textArea.setSelectionRange(counter, 1);
        counter+=1;
    }

    private void updSelection(){
        if((time >= timings.get(cnt).getTimeStart()) && (time <= timings.get(cnt).getTimeStop())) {
            textArea.setSelectionRange(timings.get(cnt).getFirstSymbol(), timings.get(cnt).getLastSymbol()-timings.get(cnt).getFirstSymbol());
        }
        if(time == timings.get(cnt).getTimeStart()){

        }
    }

//    private void updateSelection(){
////        if(counter>=10){
//            setSelectionRange(textArea.getElement(), 0, 20);
//            formatter.setBackColor("green");
//            setSelectionRange(textArea.getElement(), 0, 0);
////        }
//
//        if(counter<textArea.getText().length()-20){
////            setSelectionRange(textArea.getElement(), counter, 10);
//        setSelectionRange(textArea.getElement(), 0, 20);
//            formatter.setBackColor("yellow");
//            setSelectionRange(textArea.getElement(), 0, 0);
////            textArea.setFocus(true);
////            counter+=20;
//        }
//        else{
//            setSelectionRange(textArea.getElement(), 0, 0);
//            timer.cancel();
//        }
//    }

//    public native void setSelectionRange(Element elem, int pos, int length) /*-{
//        try {
//            var selection = null, range2 = null;
//            var iframeWindow = elem.contentWindow;
//            var iframeDocument = iframeWindow.document;
//
//            selection = iframeWindow.getSelection();
//            range2 = selection.getRangeAt(0);
//
//            //create new range
//            var range = iframeDocument.createRange();
//            range.setStart(selection.anchorNode, pos);
//            range.setEnd(selection.anchorNode, pos+length);
//
//            //remove the old range and add the newly created range
//            if (selection.removeRange) { // Firefox, Opera, IE after version 9
//                selection.removeRange(range2);
//            } else {
//                if (selection.removeAllRanges) { // Safari, Google Chrome
//                    selection.removeAllRanges();
//                }
//            }
//            selection.addRange(range);
//        } catch (e) {
//            $wnd.alert(e);
//        }
//    }-*/;
}
