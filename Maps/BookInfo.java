package Maps;

import java.util.List;

/**
 * Created by yxiaocheng1997 on 4/15/17.
 */
public class BookInfo {
    private String libName;
    private String callno;
    private String title;
    /* List of all the possible floors in the building. */
    private List<String> floors;
    private int floor;
    private int shelfno;
    private int shelf;
    private int colno;
    private int col;
    private int x_tl;
    private int y_tl;
    private int length;
    private int width;

    BookInfo(String lib, String callnum, String bookname, List<String> flrs, int fl,
             int shno, int sh, int cno, int c, int x, int y, int len, int wid) {
        libName = lib;
        callno = callnum;
        title = bookname;
        floors = flrs;
        floor = fl;
        shelfno = shno;
        shelf = sh;
        colno = cno;
        col = c;
        x_tl = x;
        y_tl = y;
        length = len;
        width = wid;
    }

    public String getLibName() {
        return libName;
    }

    public String getCallno() {
        return callno;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getFloors(){
        return floors;
    }

    public int[] getParams(){
        return new int[] {floor, shelfno, shelf, colno, col};
    }

    public int[] getSectionLocation(){
        return new int[] {x_tl, y_tl, length, width};
    }







}