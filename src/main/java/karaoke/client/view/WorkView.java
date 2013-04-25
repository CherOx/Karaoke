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
    Label timeLabel;
//    final RichTextArea richTextArea;
//    RichTextArea.Formatter formatter;
    VerticalPanel vpanel;
    private int counter = 0;
    Timer timer;
    List<SelectedTextBlock> timings;
//    int currentWord = 0;
    int cnt = 0;
    int time = 0;

    public WorkView() {

        vpanel = new VerticalPanel();
        vpanel.setSpacing(5);

        textArea = new TextArea();
        textArea.ensureDebugId("cwBasicText-textarea");
        textArea.setVisibleLines(10);
        textArea.setWidth("800px");
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
//                richTextArea.getElement().setId("rta");
//                updateSelection();

//                setSelectionRange(richTextArea.getElement(), 5, 15);
//                formatter.setBackColor("green");
//                setSelectionRange(richTextArea.getElement(), 0, 0);
//                formatter.selectAll();
//                formatter.removeFormat();
//                setSelectionRange(richTextArea.getElement(), 0, 0);
//                setSelectionRange(richTextArea.getElement(), 11, 20);
//                formatter.setBackColor("yellow");
//                setSelectionRange(richTextArea.getElement(), 0, 0);
//                richTextArea.setFocus(true);
//
                updateSelectionLabel();
            }
        });

        timeLabel = new Label("Time: ");

//        richTextArea = new RichTextArea();
//        formatter = richTextArea.getFormatter();

        vpanel.add(textArea);
        vpanel.add(button);
        vpanel.add(timeLabel);

//        vpanel.add(richTextArea);

        initWidget(vpanel);
    }

    public Widget getViewWidget() {
        return this;
    }

    public void setText(String string, List<SelectedTextBlock> timings){
        this.timings = timings;
        textArea.setText(string);
//        richTextArea.setText(string);
//        textArea.setFocus(true);
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
                updSelection();
                updateSelectionLabel();
                timeLabel.setText("Time: " + Integer.toString(time));
                time+=20;
            }
        };
        timer.scheduleRepeating(20);
    }

//    private void updateSelection(){
//
////        setSelectionRange(richTextArea.getElement(), 5, 15);
////        formatter.setBackColor("green");
////        setSelectionRange(richTextArea.getElement(), 0, 0);
////        formatter.selectAll();
////        formatter.removeFormat();
////        setSelectionRange(richTextArea.getElement(), 11, 20);
////        formatter.setBackColor("yellow");
////        setSelectionRange(richTextArea.getElement(), 0, 0);
////        richTextArea.setFocus(true);
//    }

    private void updSelection(){
//        if((time >= timings.get(cnt).getTimeStart()) && (time <= timings.get(cnt).getTimeStop())) {
//            textArea.setSelectionRange(timings.get(cnt).getFirstSymbol(), timings.get(cnt).getLastSymbol()-timings.get(cnt).getFirstSymbol());
//        }
        if(cnt<=timings.size()){
            if(time == timings.get(cnt).getTimeStart()){
                textArea.setSelectionRange(timings.get(cnt).getFirstSymbol(), timings.get(cnt).getLastSymbol()-timings.get(cnt).getFirstSymbol());
            }
            if(time == timings.get(cnt).getTimeStop()){
                textArea.setSelectionRange(0, 0);
                cnt++;
            }
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
//            var sel = null, range2 = null;
//            $wnd.alert("setSelectionRange");
//            var iframeWindow = elem.contentWindow;
//            $wnd.alert("iframeWindow");
//            var iframeDocument = iframeWindow.document;
//            $wnd.alert("iframeDocument");
//
//            sel = iframeWindow.getSelection();
//            $wnd.alert("sel");
//            range2 = sel.getRangeAt(0);
//            $wnd.alert("range2");
//            var range = iframeDocument.createRange();
//            $wnd.alert("range");
//            range.setStart(sel.anchorNode, pos);
//            range.setEnd(sel.anchorNode, pos + length);
//            $wnd.alert(range.toString());
//
//            if (sel.removeRange) { // Firefox, Opera, IE after version 9
//                sel.removeRange(range2);
//                $wnd.alert("removeRange");
//            } else {
//                if (sel.removeAllRanges) { // Safari, Google Chrome
//                    sel.removeAllRanges();
//                    $wnd.alert("removeAllRanges");
//                }
//            }
//
//            sel.addRange(range);
//            $wnd.alert("addRange");
//        } catch (e) {
//            $wnd.alert(e);
//        }
////        var selection = iframeWindow.setSelectionRange();
////        var target = document.getElementsByClassName('gwt-RichTextArea').getElementsByTagName('body')[0];
////        var rng = document.createRange();
////        $wnd.alert(target.toString());
////        rng.selectNode( target );
////        var sel = window.getSelection();
////        sel.removeAllRanges();
////        sel.addRange( rng );
//    }-*/;

//    public native void setSelectionRange(Element elem, int pos, int length) /*-{
//        try {
//            var selection = null, range2 = null;
//            var iframeWindow = elem.contentWindow;
//            $wnd.alert("iframeWindow");
//            var iframeDocument = iframeWindow.document;
//            $wnd.alert("iframeDocument");
//
//            selection = iframeWindow.getSelection();
//            $wnd.alert("selection");
//            range2 = selection.getRangeAt(0);
//            $wnd.alert("range2");
//
//            //create new range
//            var range = iframeDocument.createRange();
//            $wnd.alert("range");
//            range.setStart(selection.anchorNode, pos);
//            $wnd.alert("setStart");
//            range.setEnd(selection.anchorNode, pos+length);
//            $wnd.alert("setEnd");
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
