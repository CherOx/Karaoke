package karaoke.client.service;

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

//    /**
//     * Update the text in one of the selection labels.
//     */
//    private void updateSelectionLabel() {
//        label.setText("Selected" + ": " + textArea.getCursorPos() + ", " + textArea.getSelectionLength());
//    }

//    public void RunTimer() {
//        timer = new Timer() {
//            public void run() {
////                updateSelection();
//                updSelection();
////                updateSelectionLabel();
//                timeLabel.setText("Time: " + Integer.toString(time));
//                time+=20;
//            }
//        };
//        timer.scheduleRepeating(20);
//    }

//    private void updSelection(){
////        if((time >= timings.get(cnt).getTimeStart()) && (time <= timings.get(cnt).getTimeStop())) {
////            textArea.setSelectionRange(timings.get(cnt).getFirstSymbol(), timings.get(cnt).getLastSymbol()-timings.get(cnt).getFirstSymbol());
////        }
//        if(cnt<=timings.size()){
//            if(time == timings.get(cnt).getTimeStart()){
//                textArea.setSelectionRange(timings.get(cnt).getFirstSymbol(), timings.get(cnt).getLastSymbol()-timings.get(cnt).getFirstSymbol());
//            }
//            if(time == timings.get(cnt).getTimeStop()){
//                textArea.setSelectionRange(0, 0);
//                cnt++;
//            }
//        }
//    }

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
