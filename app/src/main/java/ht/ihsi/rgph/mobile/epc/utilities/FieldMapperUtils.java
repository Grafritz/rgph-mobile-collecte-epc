package ht.ihsi.rgph.mobile.epc.utilities;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by ajordany on 5/6/2016.
 */
public class FieldMapperUtils {


    public static String getFieldValue(Object obj, String fieldName){
        if(obj==null || fieldName.isEmpty()){
            return "";
        }
        try{
            Class<?> O = obj.getClass();
            Field field=O.getDeclaredField(transformFieldName(fieldName));
            if(field!=null){
                Method getter = O.getMethod(createGetter(field.getName()));
                return ""+getter.invoke(obj);
            }
        }catch(Exception ex){
            Log.i(ToastUtility.TAG, "Exception:-:getFieldValue(): toString:" + ex.toString());
            ex.printStackTrace();
        }
        return "";
    }


    public static Object mapField(String fieldName,String value, Object obj){
        if(obj==null || fieldName.isEmpty() || value.isEmpty()){
           return obj;
        }
        try{
            Class<?> O = obj.getClass();
            Field field=O.getDeclaredField(transformFieldName(fieldName));
            if(field!=null){
                Class<?> fieldType=field.getType();
                Method setter = O.getMethod(FieldMapperUtils.createSetter(field.getName())
                        , field.getType());
                //setter.invoke(obj, fieldType.cast(value));
                if(short.class.isAssignableFrom(fieldType)){
                    setter.invoke(obj, Short.parseShort(value));
                }else if(Short.class.isAssignableFrom(fieldType)){
                    setter.invoke(obj, Short.parseShort(value));
                }else if(int.class.isAssignableFrom(fieldType)){
                    setter.invoke(obj, Integer.parseInt(value));
                }else if(Integer.class.isAssignableFrom(fieldType)){
                    setter.invoke(obj, Integer.parseInt(value));
                }else if(long.class.isAssignableFrom(fieldType)){
                    setter.invoke(obj, Long.parseLong(value));
                }else if(Long.class.isAssignableFrom(fieldType)){
                    setter.invoke(obj, Long.parseLong(value));
                }else if(BigDecimal.class.isAssignableFrom(fieldType)){
                    setter.invoke(obj, fieldType.cast(value));
                }else if(BigInteger.class.isAssignableFrom(fieldType)){
                    setter.invoke(obj, BigInteger.valueOf(Long.parseLong(value)));
                }else if(String.class.isAssignableFrom(fieldType)){
                    setter.invoke(obj, value);
                }else if(Date.class.isAssignableFrom(fieldType)){
                    setter.invoke(obj, Date.class.cast(value));
                }else if(Timestamp.class.isAssignableFrom(fieldType)) {
                    setter.invoke(obj, Timestamp.class.cast(value));
                }else if(float.class.isAssignableFrom(fieldType)) {
                    setter.invoke(obj, Float.class.cast(value));
                }else if(boolean.class.isAssignableFrom(fieldType)){
                    setter.invoke(obj, Boolean.parseBoolean(value));
                }else if(Boolean.class.isAssignableFrom(fieldType)){
                    setter.invoke(obj, Boolean.parseBoolean(value));
                }else if(Calendar.class.isAssignableFrom(fieldType)){
                    Calendar mydate = new GregorianCalendar();
                    Date thedate = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).parse(value);
                    mydate.setTime(thedate);
                    setter.invoke(obj,  mydate);
                }
            }
        }catch(Exception ex){
            Log.i(ToastUtility.TAG, "Exception:-:mapField(): toString:" + ex.toString());
            ex.printStackTrace();
        }
        return obj;
    }
    private static boolean isFieldExist(Field[] fields, String fieldName) {
        int sizeTab = fields.length;
        for (int i = 0; i < sizeTab; i++) {
            if (fields[i].getName().equals(fieldName) == true) {
                return true;
            }
        }
        return false;
    }

    private static String transformFieldName(String fielName){
        StringBuffer methodeName = new StringBuffer("");
        methodeName.append(fielName.substring(0, 1).toLowerCase());
        methodeName.append(fielName.substring(1));
        return methodeName.toString();
    }

    private static String createSetter(String fielName){
        StringBuffer methodeName = new StringBuffer("set");
        methodeName.append(fielName.substring(0, 1).toUpperCase());
        methodeName.append(fielName.substring(1));
        return methodeName.toString();
    }

    private static String createGetter(String fielName){
        StringBuffer methodeName = new StringBuffer("get");
        methodeName.append(fielName.substring(0, 1).toUpperCase());
        methodeName.append(fielName.substring(1));
        return methodeName.toString();
    }


    public static String GetMapField(String fieldName, Object obj){
        String valeur = "";
        if( obj == null || fieldName.isEmpty() ){
            return valeur;
        }
        try{
            Class<?> O = obj.getClass();
            Field field = O.getDeclaredField(transformFieldName(fieldName));
            if(field != null){
                Class<?> fieldType = field.getType();
                Method getter = O.getMethod(FieldMapperUtils.createGetter(field.getName()), field.getType());
                valeur = getter.toString();
                if(String.class.isAssignableFrom(fieldType)){
                    valeur = getter.getDefaultValue().toString();
                }
            }
        }catch(Exception ex){
            Log.i(ToastUtility.TAG, "Exception:-:GetMapField(): toString:" + ex.toString());
            ex.printStackTrace();
        }
        return valeur;
    }

}
