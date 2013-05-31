package karaoke.client.view;

import com.google.gwt.user.client.ui.*;
import karaoke.client.service.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 30.05.13
 * Time: 18:21
 * To change this template use File | Settings | File Templates.
 */
public class SongsListView extends Composite {

    private ListBox songsListBox;
//    private Button editSongsButton;

    public SongsListView() {
        FlexTable body = new FlexTable();

        Label listBoxLabel = new Label(Constants.LIST_BOX_SELECT_SONG_LABEL);
        body.setWidget(0, 0, listBoxLabel);
        body.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        songsListBox = new ListBox(false);
        songsListBox.setWidth(Constants.SONGS_LIST_WIDTH);
        songsListBox.setVisibleItemCount(20);
        body.setWidget(1, 0, songsListBox);

//        editSongsButton = new Button(Constants.OPEN_EDITOR_VIEW_BUTTON);
//        body.setWidget(2, 0, editSongsButton);

        DecoratorPanel decPanel = new DecoratorPanel();
        decPanel.setWidget(body);

        initWidget(decPanel);
    }

    public Widget getViewWidget() {
        return this;
    }

//    public Button getEditSongsButton() {
//        return editSongsButton;
//    }

    public ListBox getSongsListBox() {
        return songsListBox;
    }
}
