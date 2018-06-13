package ht.ihsi.rgph.mobile.epc.models;

import java.io.Serializable;

/**
 * Created by ajordany on 4/7/2016.
 */
public class RowDataListModel implements Serializable{

    private long id;
    private String icon;
    private String title;
    private String desc;
    private boolean isComplete;
    private boolean isModuleMenu;
    private boolean isEmpty;
    private int tableName;

    private Object model;

    public RowDataListModel(){

    }

    public RowDataListModel(String icon, String title, String desc){
        this.icon=icon;
        this.title=title;
        this.desc=desc;
        this.isModuleMenu=true;
        this.isComplete=true;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean isModuleMenu() {
        return isModuleMenu;
    }

    public void setIsModuleMenu(boolean isModuleMenu) {
        this.isModuleMenu = isModuleMenu;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public int getTableName() {
        return tableName;
    }

    public void setTableName(int tableName) {
        this.tableName = tableName;
    }
}
