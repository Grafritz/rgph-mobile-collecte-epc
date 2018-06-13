package ht.ihsi.rgph.mobile.epc.models;

/**
 * Created by ajordany on 3/21/2016.
 */
public class KeyValueModel extends BaseModel{

    private String key;
    private String value;
    private String otherValue;

    public KeyValueModel(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public KeyValueModel(String key, String value, String otherValue) {
        this.key = key;
        this.value = value;
        this.otherValue = otherValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }

    @Override
    public String toString() {
        //return "[" + this.key.toString() +"] - "+ this.value.toString();
        return  this.value.toString();
    }
}
