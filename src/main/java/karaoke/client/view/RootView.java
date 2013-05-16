package karaoke.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.*;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 18.04.13
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public class RootView extends Composite {

    private Label message = new Label();
    private SimplePanel body = new SimplePanel();
    private Button setWork;
    private Button setEditor;

    RootView() {
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.add(message);
        mainPanel.add(body);
        setWork = new Button("SetWorkView");
        mainPanel.add(setWork);
        setEditor = new Button("SetEditorView");
        mainPanel.add(setEditor);
        initWidget(mainPanel);
    }

    public HasClickHandlers getButtonWork() {
        return setWork;
    }

    public HasClickHandlers getButtonEditor() {
        return setEditor;
    }

    public HasText getMessage() {
        return message;
    }

    public void setBody(Widget widget) {
        this.body.setWidget(widget);
    }

    public Widget getViewWidget() {
        return this;
    }
}
