package ht.ihsi.rgph.mobile.epc.utilities;

/**
 * Created by JeanFritz on 5/27/2016.
 */
public class TypeSafeConversion {
    public static String TranslateIntToBoolString(int pInteger) {
        switch (pInteger) {
            case 1:
            case -1:
                return "Yes";
            default:
                return "No";
        }
    }

    public static Boolean TranslateIntToBool(int pInteger) {
        switch (pInteger) {
            case 1:
            case -1:
                return true;
            default:
                return false;
        }
    }

    public static Boolean TranslateStringToBool(String pString) {
        switch (pString) {
            case "1":
                return true;
            default:
                return false;
        }
    }

}
