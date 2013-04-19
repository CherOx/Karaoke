package karaoke.client;

/**
 * Created with IntelliJ IDEA.
 * User: Василий
 * Date: 19.04.13
 * Time: 9:40
 * To change this template use File | Settings | File Templates.
 */
public class SelectedTextBlock {

    private int firstSymbol;
    private int lastSymbol;
    private int timeStart;
    private int timeStop;

    public SelectedTextBlock(int fs, int ls, int tstart, int tstop){
        firstSymbol = fs;
        lastSymbol = ls;
        timeStart = tstart;
        timeStop = tstop;
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
