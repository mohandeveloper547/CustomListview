package sample.customlistview.dto;

import java.util.ArrayList;

/**
 * Created by shyam.yammanuru on 4/21/2016.
 */
public class CustomDTO {
    private String listTitle;
    private ArrayList<SimpleDTO> infoList;

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public ArrayList<SimpleDTO> getInfoList() {
        return infoList;
    }

    public void setInfoList(ArrayList<SimpleDTO> infoList) {
        this.infoList = infoList;
    }


}
