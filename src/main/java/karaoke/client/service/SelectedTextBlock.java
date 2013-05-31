package karaoke.client.service;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 19.04.13
 * Time: 9:40
 * To change this template use File | Settings | File Templates.
 */
public class SelectedTextBlock implements IsSerializable {

    private int firstSymbol;
    private int lastSymbol;
    private int timeStart;
    private int timeStop;

    public SelectedTextBlock(int firstSymbol, int lastSymbol, int timeStart, int timeStop){
        this.firstSymbol = firstSymbol;
        this.lastSymbol = lastSymbol;
        this.timeStart = timeStart;
        this.timeStop = timeStop;
    }

    public SelectedTextBlock(){}

    public void setFirstSymbol(int firstSymbol) {
        this.firstSymbol = firstSymbol;
    }

    public void setLastSymbol(int lastSymbol) {
        this.lastSymbol = lastSymbol;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeStop(int timeStop) {
        this.timeStop = timeStop;
    }

    public int getFirstSymbol() {
        return firstSymbol;
    }

    public int getLastSymbol() {
        return lastSymbol;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public int getTimeStop() {
        return timeStop;
    }
}
