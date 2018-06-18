package ht.ihsi.rgph.mobile.epc.utilities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Spinner;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.epc.backend.entities.BatimentDao;
//import ht.ihsi.rgph.mobile.epc.backend.entities.DecesDao;
//import ht.ihsi.rgph.mobile.epc.backend.entities.EmigreDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.IndividuDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.LogementDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.MenageDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.PersonnelDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.RapportRARDao;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.epc.mappers.ModelMapper;
import ht.ihsi.rgph.mobile.epc.models.KeyValueModel;
import ht.ihsi.rgph.mobile.epc.models.PersonnelModel;

/**
 * Created by JFDuverseau on 5/2/2016.
 */
public class Tools
{
    public static void AlertDialogMsg(Context context, String msg){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(context);
        String titleMsg = context.getResources().getString(R.string.msg_title_succesInformation);
        aBuilder.setTitle(titleMsg);
        aBuilder.setMessage(msg);
        aBuilder.setCancelable(false);
        aBuilder.setIcon(R.mipmap.ic_launcher);

        //set Positive Button 1
        aBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }//
        );
        aBuilder.show();
    }//
    //
    public static void AlertDialogMsg(Context context, String msg, String E_or_S){
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(context);
        String titleMsg = context.getResources().getString(R.string.msg_title_succesInformation);
        if (E_or_S.equalsIgnoreCase("")) E_or_S = "E";
        if (E_or_S.equalsIgnoreCase("E")) titleMsg = context.getResources().getString(R.string.msg_title_erreurInformation);

        aBuilder.setTitle(titleMsg);
        aBuilder.setMessage(msg);
        aBuilder.setCancelable(false);
        aBuilder.setIcon(R.mipmap.ic_launcher);

        //set Positive Button 1
        aBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }//
        );
        //
        /*aBuilder.setOnCancelListener(
                new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {

                    }
                }
        );*/
        aBuilder.show();
    }//

    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public static Shared_Preferences SharedPreferences(Context context){ return new Shared_Preferences(context); }

    public static void StoreInfoPresonnel_PreferenceManager(Context context,  PersonnelModel personnelModel){
        ModelMapper.MapToPreferences( context, personnelModel);
    }

    public static boolean CheckPrefIsUseConnected(Context context) {
        return SharedPreferences(context).getIsConnected();
    }//

    //region REPONSE STRING QUESTION
    public static String getString_Reponse(String codeReponse,  String nomChamps) {
        try{
            String result="";
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            //BATIMENT
            if (nomChamps.equalsIgnoreCase(BatimentDao.Properties.Qb1Etat.columnName)) {
                keyValueModels.add(new KeyValueModel("1", "1. Li fin bati"));
                keyValueModels.add(new KeyValueModel("2", "2. L'ap bati "));
                keyValueModels.add(new KeyValueModel("3", "3. L'ap repare "));
                keyValueModels.add(new KeyValueModel("4", "4. Li delabre "));
                keyValueModels.add(new KeyValueModel("5", "5. Pa konnen, paske li pa sou je"));
            }else if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.IsFieldAllFilled.columnName)){
                keyValueModels.add(new KeyValueModel("1", "Wi"));
                keyValueModels.add(new KeyValueModel("0", "Non"));
                keyValueModels.add(new KeyValueModel("true", "Wi"));
                keyValueModels.add(new KeyValueModel("false", "Non"));
            }else if (nomChamps.equalsIgnoreCase(BatimentDao.Properties.Qb3StatutOccupation.columnName)){
                keyValueModels.add(new KeyValueModel("1", "1.Okipe"));
                keyValueModels.add(new KeyValueModel("2", "2.Toujou vid"));
            }else if (nomChamps.equalsIgnoreCase(BatimentDao.Properties.Qb2Type.columnName)){
                keyValueModels.add(new KeyValueModel("1", "1. Kay atè ….. ale nan B4"));
                keyValueModels.add(new KeyValueModel("2", "2. Todi …… ale nan B4"));
                keyValueModels.add(new KeyValueModel("3", "3.  Joupa …… ale nan B4"));
                keyValueModels.add(new KeyValueModel("4", "4.  Kay, ki fèt ak siman/woch oswa bwa, ki pou kont li"));
                keyValueModels.add(new KeyValueModel("5", "5.  Kay, ki fèt ak siman/woch oswa bwa, ki kole ak lot (townhouse)"));
                keyValueModels.add(new KeyValueModel("6", "6.  Kay estyl gingerbread"));
                keyValueModels.add(new KeyValueModel("7", "7.  Edifis"));
                keyValueModels.add(new KeyValueModel("8", "8.  Anga …… ale nan B4"));
                keyValueModels.add(new KeyValueModel("9", "9.  Abri pwovizwa …… ale nan B4"));
                keyValueModels.add(new KeyValueModel("10", "10.  Lòt kalite"));
                // LOGEMENT //
            }else if (nomChamps.equalsIgnoreCase(LogementDao.Properties.Qlin2StatutOccupation.columnName)){
                keyValueModels.add(new KeyValueModel("1", "1. Okipe toutan epi moun yo la"));
                keyValueModels.add(new KeyValueModel("2", "2. Okipe toutan, men moun yo pa la"));
                keyValueModels.add(new KeyValueModel("3", "3. Okipe yon lè konsa"));
                keyValueModels.add(new KeyValueModel("4", "4. Pa okipe"));
            }else if (nomChamps.equalsIgnoreCase(LogementDao.Properties.Qlin3TypeLogement.columnName)){
                keyValueModels.add(new KeyValueModel("1", "1. Apatman"));
                keyValueModels.add(new KeyValueModel("2", "2. Pyès (pyès kay)"));
                keyValueModels.add(new KeyValueModel("3", "3. Lòt"));
            }else if (nomChamps.equalsIgnoreCase(LogementDao.Properties.Qlin4IsHaveIndividuDepense.columnName)){
                keyValueModels.add(new KeyValueModel("1", "1- Wi"));
                keyValueModels.add(new KeyValueModel("2", "2- Non"));
                // INDIVIDU //
            }else if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Q12StatutMatrimonial.columnName)){
                keyValueModels.add(new KeyValueModel("1", "1. Selibatè"));
                keyValueModels.add(new KeyValueModel("2", "2. Marye"));
                keyValueModels.add(new KeyValueModel("3", "3. Plase"));
                keyValueModels.add(new KeyValueModel("4", "4. Viv avèk"));
                keyValueModels.add(new KeyValueModel("5", "5. Vèv (Mari /oswa Madanm nan mouri)"));
                keyValueModels.add(new KeyValueModel("6", "6. Kite, Separe apre maryaj"));
                keyValueModels.add(new KeyValueModel("7", "7. Kite, Separe apre plasay"));
                keyValueModels.add(new KeyValueModel("8", "8. Divòse"));
                keyValueModels.add(new KeyValueModel("9", "9. Lòt"));
            }else if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Q5HabiteDansMenage.columnName)){
                keyValueModels.add(new KeyValueModel("1", "1- Wi li toujou la, se la li dómi, manje ak tout lót moun ki nan lojman an"));
                keyValueModels.add(new KeyValueModel("2", "2- Wi li toujou la, men li okipe zafè l ak manjel a pa .....   fini la a pou moun sa a"));
                keyValueModels.add(new KeyValueModel("3", "3- Wi li toujou la, men li fë yon deplase pou mwens pase 6 mwa"));
                keyValueModels.add(new KeyValueModel("4", "4- Wi, men li deplase pou plis pase 6 mwa ....... fini la a pou moun sa a"));
                keyValueModels.add(new KeyValueModel("5", "5- Se yon ti moun ki fèk fèt "));
                keyValueModels.add(new KeyValueModel("6", "6- Se yon moun ki gran moun anpil"));
                keyValueModels.add(new KeyValueModel("7", "7- Li fèk vini men li gen entansyon rete la a"));
                keyValueModels.add(new KeyValueModel("8", "8- Li fèk vini li gen entansyon rete la a, men lap  okipe zafè l ak manje l a pa  .....   fini la a pou moun sa a"));
                keyValueModels.add(new KeyValueModel("9", "9- Li nan kay la pou mwens ke 6 mwa  .... fini la a pou moun sa a"));
            }else if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Q9LienDeParente.columnName)){
                keyValueModels.add(new KeyValueModel("1", "01- Chèf menaj la"));
                keyValueModels.add(new KeyValueModel("2", "02- Mari / Madanm"));
                keyValueModels.add(new KeyValueModel("3", "03- Pitit gason / Piti fi"));
                keyValueModels.add(new KeyValueModel("4", "04- Frè / sè "));
                keyValueModels.add(new KeyValueModel("5", "05- Neve / Nyès"));
                keyValueModels.add(new KeyValueModel("6", "06. Papa / Manman"));
                keyValueModels.add(new KeyValueModel("7", "07- Pitit Pitit gason / Pitit Pitit fi"));
                keyValueModels.add(new KeyValueModel("8", "08- Bòpè / Bèlmè mari a oswa madanm nan"));
                keyValueModels.add(new KeyValueModel("9", "09- Bofis / Bèlfi"));
                keyValueModels.add(new KeyValueModel("10", "10- Lòt paran"));
                keyValueModels.add(new KeyValueModel("11", "11- Moun kap travay nan kay la ki touche pou sa "));
                keyValueModels.add(new KeyValueModel("12", "12- Moun kap travay nan kay la san touche "));
                keyValueModels.add(new KeyValueModel("13", "13- Timoun ki rete ak nou (Restavèk)"));
                keyValueModels.add(new KeyValueModel("14", "14- Lòt moun ki pa gen oken n lyen ak chèf menaj la "));
            }else if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp4Sexe .columnName)){
                keyValueModels.add(new KeyValueModel("1", "Yon Gason"));
                keyValueModels.add(new KeyValueModel("2", "Yon Fi"));
            /*}else if (nomChamps.equalsIgnoreCase(EmigreDao.Properties.Qn2bSexe.columnName)){
                keyValueModels.add(new KeyValueModel("1", "Gason"));
                keyValueModels.add(new KeyValueModel("2", "Fi"));*/
            /*}else if (nomChamps.equalsIgnoreCase(EmigreDao.Properties.Qn2bResidenceActuelle.columnName)){
                keyValueModels.add(new KeyValueModel("1", "Ap viv nan peyi letranje"));
                keyValueModels.add(new KeyValueModel("2", "Ap viv an Ayiti"));
                keyValueModels.add(new KeyValueModel("3", "Non, li Mouri"));
                keyValueModels.add(new KeyValueModel("4", "Pa konnen"));*/
            /*}else if (nomChamps.equalsIgnoreCase(EmigreDao.Properties.Qn2eDernierPaysResidence.columnName)){
                keyValueModels.add(new KeyValueModel("1", "01. Etazini"));
                keyValueModels.add(new KeyValueModel("2", "02. Repiblik Domikèn"));
                keyValueModels.add(new KeyValueModel("3", "03. Kanada"));
                keyValueModels.add(new KeyValueModel("4", "04. Lafrans"));
                keyValueModels.add(new KeyValueModel("5", "05. Gwadloup"));
                keyValueModels.add(new KeyValueModel("6", "06. Matinik"));
                keyValueModels.add(new KeyValueModel("7", "07. Antiy fransèz"));
                keyValueModels.add(new KeyValueModel("8", "08. Baamas"));
                keyValueModels.add(new KeyValueModel("9", "09. Chili"));
                keyValueModels.add(new KeyValueModel("10", "10. Brezil"));
                keyValueModels.add(new KeyValueModel("11", "11. Ajantin"));
                keyValueModels.add(new KeyValueModel("12", "12. Sirinam"));
                keyValueModels.add(new KeyValueModel("13", "13. Venezwela"));
                keyValueModels.add(new KeyValueModel("14", "14. Turk e Kaykos"));
                keyValueModels.add(new KeyValueModel("15", "15. Lòt"));
                keyValueModels.add(new KeyValueModel("16", "16. Pa konnen"));*/
           /* }else if (nomChamps.equalsIgnoreCase(DecesDao.Properties.Qd2aSexe.columnName)){
                keyValueModels.add(new KeyValueModel("1", "Gason"));
                keyValueModels.add(new KeyValueModel("2", "Fi"));*/
          /*  }else if (nomChamps.equalsIgnoreCase(DecesDao.Properties.Qd2c1CirconstanceDeces.columnName)){
                keyValueModels.add(new KeyValueModel("1", "1. Pandan li  ansent "));
                keyValueModels.add(new KeyValueModel("2", "2. Pandan li tap akouche"));
                keyValueModels.add(new KeyValueModel("3", "3. Karannde (42) jou (anviwon yon mwa edmi) aprè gwosès oswa aprè akouchman an"));
                keyValueModels.add(new KeyValueModel("4", "4. Lòt "));
                keyValueModels.add(new KeyValueModel("5", "5. Pa konnen"));*/
           /* }else if (nomChamps.equalsIgnoreCase(DecesDao.Properties.Qd2c2CauseDeces.columnName)){
                keyValueModels.add(new KeyValueModel("1", "1. Emoraji"));
                keyValueModels.add(new KeyValueModel("2", "2. Enfeksyon"));
                keyValueModels.add(new KeyValueModel("3", "3. Eklanmsi (Tansyon)"));
                keyValueModels.add(new KeyValueModel("4", "4. Konplikasyon pandan lap akouche"));
                keyValueModels.add(new KeyValueModel("5", "5. Fos kouch"));
                keyValueModels.add(new KeyValueModel("6", "6. Avotman ki fèt nan move kondisyon"));
                keyValueModels.add(new KeyValueModel("7", "7. Lòt"));
                keyValueModels.add(new KeyValueModel("8", "8. Pa konnen"));*/
            }else if (nomChamps.equalsIgnoreCase(PersonnelDao.Properties.ProfileId.columnName)) {
                keyValueModels.add(new KeyValueModel("" + Constant.PRIVILEGE_DEVELOPPEUR, "Devlopè"));
                keyValueModels.add(new KeyValueModel("" + Constant.PRIVILEGE_ASTIC, "ACTIC"));
                keyValueModels.add(new KeyValueModel("" + Constant.PRIVILEGE_SUPERVISEUR, "Sipèvizè"));
                keyValueModels.add(new KeyValueModel("" + Constant.PRIVILEGE_AGENT, "Ajan"));
            }else if (nomChamps.equalsIgnoreCase(PersonnelDao.Properties.EstActif.columnName)){
                keyValueModels.add(new KeyValueModel("1", "Aktif"));
                keyValueModels.add(new KeyValueModel("0", "Pa aktif"));
            }else if (nomChamps.equalsIgnoreCase(RapportRARDao.Properties.RaisonActionId.columnName)){
                keyValueModels.add(new KeyValueModel("1", "Ranpli nèt / depi nan batiman rive nan manb menaj la"));
                keyValueModels.add(new KeyValueModel("2", "Ranpli nèt / Batiman vid"));
                keyValueModels.add(new KeyValueModel("3", "Ranpli nèt / Lojman vid"));

                //* tous objets  ,  bâtiment vide (confirmé)  ,  logement vide (confirmé) *//
                keyValueModels.add(new KeyValueModel("4", "Ranpli nèt depi nan premye entèvyou a"));
                //* (abandon), (refus) *//*
                keyValueModels.add(new KeyValueModel("5", "Refus converti"));
                //* (interruption avec rendez-vous)	,  (absence répondants avec rendez-vous)  ,  (logement occupé, occupants absents)	 *//
                keyValueModels.add(new KeyValueModel("6", "Randevou ou retou pwograme/fèt/fini"));

                keyValueModels.add(new KeyValueModel("7", "Moun ki tap reponn nan pa vle kontinye"));
                keyValueModels.add(new KeyValueModel("8", "Moun ki tap reponn nan kanpe, men gen randevou"));
                keyValueModels.add(new KeyValueModel("9", "Lojman an okipe, men moun yo pa la"));
                keyValueModels.add(new KeyValueModel("10", "Lòt. Presize poukisa..."));

                keyValueModels.add(new KeyValueModel("11", "\"Refus non converti\" / Kesyonè a pa fini (NRP)"));
                keyValueModels.add(new KeyValueModel("12", "Moun ki pou reponn nan pa janm la/disponib pou AR la fin ranpli kesyonè a"));
                keyValueModels.add(new KeyValueModel("13", "Lojman an okipe, men moun yo pa la"));
                keyValueModels.add(new KeyValueModel("14", "Lòt. Presize poukisa..."));

                keyValueModels.add(new KeyValueModel("15", "Pa gen mwayen obsève batiman an / pa gen enfòmasyon sou lojman an"));
                keyValueModels.add(new KeyValueModel("16", "Moun yo refize reponn nètalkole"));
                keyValueModels.add(new KeyValueModel("17", "Moun ki pou reponn nan pa la/ pa disponib men gen yon randevou"));
                keyValueModels.add(new KeyValueModel("18", "Moun ki pou reponn nan pa la oubyen li pa disponib"));
                keyValueModels.add(new KeyValueModel("19", "Lòt. Presize poukisa..."));

                keyValueModels.add(new KeyValueModel("20", "Pa janm gen mwayen obsève batiman an"));
                keyValueModels.add(new KeyValueModel("21", "\"Refus non converti\" / Non-réponse totale (NRT)"));
                keyValueModels.add(new KeyValueModel("22", "Moun ki pou reponn nan pa janm la/disponib pou AR la ranpli kesyonè a"));
                                /* (absence, indisponbilité, avec rendez-vous)  ,  (absence, indisponibilité) */
                keyValueModels.add(new KeyValueModel("23", "Lòt. Presize poukisa..."));

                keyValueModels.add(new KeyValueModel("25", "Lojman Ranpli"));
                keyValueModels.add(new KeyValueModel("26", "Menaj Ranpli"));
                keyValueModels.add(new KeyValueModel("27", "Emigre Ranpli"));
                keyValueModels.add(new KeyValueModel("28", "Moun mouri Ranpli"));
                keyValueModels.add(new KeyValueModel("29", "Moun Ranpli"));


            }

            if ( keyValueModels != null  && keyValueModels.size()>0){
                for(KeyValueModel key : keyValueModels){
                    if( key.getKey().equalsIgnoreCase(codeReponse)){
                        result = key.getValue();
                        break;
                    }
                }
            }
            return result;
        }catch (Exception ex) {
            ToastUtility.LogCat("Exception-getString_B2Batiment("+ codeReponse +"): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
            ex.printStackTrace();
        }
        return "";
    }

    public static String getStringStatut(short moduleStatut) {
        String result = "";
        if ( moduleStatut == Constant.STATUT_MODULE_KI_FINI_1 ) {
            return result = " Ki Fini";
        }else if ( moduleStatut == Constant.STATUT_MODULE_KI_MAL_RANPLI_2 ) {
            return result = " Ki Mal Rampli";
        }else if ( moduleStatut == Constant.STATUT_MODULE_KI_PA_FINI_3 ) {
            return result = " Ki Pa Fini";
        }
        return result;
    }


    //endregion

    //region CONTRAINTES


    public static void SetErrorField(Context context, EditText editText, String message) {
        editText.setError(message);
        editText.requestFocus();
        ToastUtility.LogCat(message);
        ToastUtility.ToastMessage(context, message);
        AlertDialogMsg(context, message);
    }
    public static void SetErrorField(Context context, Spinner spinner, String message) {
        //spinner.setError(message);
        spinner.requestFocus();
        ToastUtility.LogCat(message);
        ToastUtility.ToastMessage(context, message);
        AlertDialogMsg(context, message);
    }

    public static void Check_DataField_CHIFFRE_LETTRE(Context context, int caractereAccepte, EditText EditTextRep) throws TextEmptyException {
        try{
            String EditText_Reponse = EditTextRep.getText().toString();
            Pattern pattern;// = Pattern.compile("^[A-Za-z0-9]");
            Matcher matcher;// = pattern.matcher(EditText_Reponse);
            if (!EditText_Reponse.trim().equals("")){
                if ( caractereAccepte == Constant.CHIFFRE){
                    pattern = Pattern.compile("^([0-9]+)$");
                    matcher = pattern.matcher(EditText_Reponse);
                    if (!matcher.matches()){
                        EditTextRep.setError(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Chif_Selman));
                        EditTextRep.requestFocus();
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Chif_Selman));
                    }
                }

                if ( caractereAccepte == Constant.CHIFFRE_LETTRE){
                    pattern = Pattern.compile("^([A-Za-z0-9]*)$");
                    matcher = pattern.matcher(EditText_Reponse);
                    if (!matcher.matches()){
                        EditTextRep.setError(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Let_Oubye_Chif_Selman));
                        EditTextRep.requestFocus();
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Let_Oubye_Chif_Selman));
                    }
                }

                if ( caractereAccepte == Constant.LETTRE){
                    pattern = Pattern.compile("^([A-Za-z\\s]*)$");
                    matcher = pattern.matcher(EditText_Reponse);
                    //Match match = Regex.Match(_Txt_Reponse.Text, @"^([A-Za-z\s]*)$", RegexOptions.IgnoreCase);
                    if (!matcher.matches()){
                        EditTextRep.setError(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Let_Selman));
                        EditTextRep.requestFocus();
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Let_Selman));
                    }
                }
            }
        }catch (Exception ex){
            ToastUtility.LogCat( "Exception :-: Check_DataField_CHIFFRE_LETTRE(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
            throw ex;
        }
    }//

    public static void Check_Constraint_NombreCaratereMinimal(int caractereAccepte, int nombreCaratereMinimal, EditText EditTextRep) throws TextEmptyException {
        try {
            String message ="";
            EditTextRep.setError(null);
            int etReponse_Length = EditTextRep.getText().toString().length();
            String etReponse_Text = EditTextRep.getText().toString();

            String e = "karatè";
            if ( caractereAccepte == Constant.CHIFFRE) e = "chif";
            if ( caractereAccepte == Constant.LETTRE) e = "lèt";

            if ( nombreCaratereMinimal != etReponse_Length) {
                message = "Repons ou a dwe genyen " + nombreCaratereMinimal + " " + e + " pou pi piti";
                EditTextRep.setError(message);
                EditTextRep.requestFocus();
                throw new TextEmptyException(message);
            }

            /*if (contrainte.getNombreCaratere() > -1 && contrainte.getNombreCaratere() != etReponse_Length) {
                et_Reponse.setError(contrainte.MessageNombreCaractere());
                et_Reponse.requestFocus();
                throw new TextEmptyException(contrainte.MessageNombreCaractere());
            }
            if (contrainte.getNbreValeurMinimal() > -1 && Integer.parseInt(etReponse_Text) < contrainte.getNbreValeurMinimal()) {
                et_Reponse.setError(contrainte.MessageNombreValeurMinimal());
                et_Reponse.requestFocus();
                throw new TextEmptyException(contrainte.MessageNombreValeurMinimal());
            }*/
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception :-: Check_Constraint_NombreCaratereMinimal(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
            throw ex;
        }
    }//
    //endregion

    //region [ PERMISSION ]
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean CheckPermission(final Context context){
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if( currentAPIVersion >= android.os.Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    android.app.AlertDialog.Builder alertBuilder = new android.app.AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    android.app.AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 124;
    public static boolean CheckPermission_Location(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    android.app.AlertDialog.Builder alertBuilder = new android.app.AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("Location permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                        }
                    });
                    android.app.AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                }
                return false;

            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    //endregion

    //region [ DATE ]

    public static boolean IsLeapYear(int year) {
        if ((year % 4 == 0) && year % 100 != 0){
            return true;// " is a leap year."
        }else if ((year % 4 == 0) && (year % 100 == 0) && (year % 400 == 0)){
            return true;// " is a leap year."
        } else {
            return false;// " is NOT a leap year."
        }
    }
    public static void  getDateInfo(Context context) {
        Calendar mydate = new GregorianCalendar();//new Date();
        Calendar now = Calendar.getInstance();
        String mystring = new Date().toString();//"01-27-2010";
        Date thedate = new Date();//null;
        try {
            //thedate = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).parse(mystring);
            //mydate.setTime(thedate);

            //mydate.set(Calendar.YEAR, 2009);
            //mydate.set(Calendar.MONTH,Calendar.FEBRUARY);
            //mydate.set(Calendar.DAY_OF_MONTH, 25);
            //mydate.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
            //mydate.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
            //mydate.set(Calendar.SECOND, now.get(Calendar.SECOND));
            //breakdown
            ToastUtility.LogCat( "getDateInfo() - mydate -> " + mydate.toString());
            ToastUtility.LogCat( "getDateInfo() - YEAR   -> " + mydate.get(Calendar.YEAR));
            ToastUtility.LogCat( "getDateInfo() - MONTH  -> " + mydate.get(Calendar.MONTH));
            ToastUtility.LogCat( "getDateInfo() - DAY_OF_MONTH    -> " + (1 + mydate.get(Calendar.DAY_OF_MONTH)));
            ToastUtility.LogCat( "getDateInfo() - ------------------ ");
            ToastUtility.LogCat( "getDateInfo() - DAY_OF_WEEK    -> " + mydate.get(Calendar.DAY_OF_WEEK));
            ToastUtility.LogCat( "getDateInfo() - HOUR   -> " + mydate.get(Calendar.HOUR));
            ToastUtility.LogCat( "getDateInfo() - minute -> " + mydate.get(Calendar.MINUTE));
            ToastUtility.LogCat( "getDateInfo() - second -> " + mydate.get(Calendar.SECOND));
            ToastUtility.LogCat( "getDateInfo() - milli  -> " + mydate.get(Calendar.MILLISECOND));
            ToastUtility.LogCat( "getDateInfo() - ampm   -> " + mydate.get(Calendar.AM_PM));
            ToastUtility.LogCat( "getDateInfo() - hod    -> " + mydate.get(Calendar.HOUR_OF_DAY));
        } catch (Exception e) {
            ToastUtility.LogCat( "getDateInfo() - Exception    -> ", e);
            e.printStackTrace();
        }
    }

    public static String  getFormatDateString_MM_dd_yyyy_HHmmss_a() {
        return "MM/dd/yyyy HH:mm:ss a";
    }
    public static String  getFormatDateString_dd_MM_yyyy_HHmmss_a() {
        return "dd/MM/yyyy HH:mm:ss a";
    }

    public static String getDateString_MMddyyyy_HHmmss_a() {
        //Calendar mydate = new GregorianCalendar();
        String dateFormated = "";
        try {
            //  MM/dd/yyyy HH:mm:ss
        /*int MONTH = (1 + mydate.get(Calendar.MONTH));
        dateFormated = "" + MONTH + "/" + mydate.get(Calendar.DAY_OF_MONTH) + "/" + mydate.get(Calendar.YEAR) + " "
                + mydate.get(Calendar.HOUR) + ":" + mydate.get(Calendar.MINUTE) + ":" + mydate.get(Calendar.SECOND) + "";*/
            DateTime mydate = new DateTime(System.currentTimeMillis());
            dateFormated = mydate.toString(getFormatDateString_MM_dd_yyyy_HHmmss_a());

            ToastUtility.LogCat("dateFormated    -> " + dateFormated);
        } catch (Exception e) {
            ToastUtility.LogCat("getDateString_MMddyyyy_HHmmss_a() - Exception    -> ", e);
        }
        return dateFormated;
    }

    public static String getDateString_ddMMyyyy_HHmmss_a() {
        String dateFormated = "";
        try {
            DateTime mydate = new DateTime(System.currentTimeMillis());
            dateFormated = mydate.toString(getFormatDateString_dd_MM_yyyy_HHmmss_a());

            ToastUtility.LogCat("dateFormated    -> " + dateFormated);
        } catch (Exception e) {
            ToastUtility.LogCat("getDateString_ddMMyyyy_HHmmss_a() - Exception    -> ", e);
        }
        return dateFormated;
    }

    public static String  getDateString_MMddyyyy_HHmmss_2() {

        String dateFormated = String.format("Current date time is: %s at this current time zone: %s", DateTime.now(DateTimeZone.forID("America/New_York")).toString("MM/dd/yyyy HH:mm:ss a"), DateTime.now(DateTimeZone.forID("America/New_York")).getZone().toString());
        //ToastUtility.LogCat("JODA 1: " + dateFormated);

        DateTime mydate = new DateTime(System.currentTimeMillis());
        ToastUtility.LogCat( "JODA 2: " + mydate.toString("MM/dd/yyyy HH:mm:ss a"));

        dateFormated = "JODA 3: " +  mydate.getMonthOfYear() + "/" +  mydate.getDayOfMonth() + "/" + mydate.getYear() + " "
                + (mydate.getHourOfDay()+1) + ":" + mydate.getMinuteOfHour() + ":" + mydate.getSecondOfMinute();
        ToastUtility.LogCat( dateFormated);

        //ToastUtility.LogCat( "JODA 4: " + new DateTime(System.currentTimeMillis()).toString("MM/dd/yyyy HH:mm:ss a"));

        /*try {
            //  MM/dd/yyyy HH:mm:ss
            int MONTH = (1 + mydate.get(Calendar.MONTH));
            dateFormated = "" + MONTH + "/" + mydate.get(Calendar.DAY_OF_MONTH) + "/" + mydate.get(Calendar.YEAR) + " "
                    + mydate.get(Calendar.HOUR) + ":" + mydate.get(Calendar.MINUTE) + ":" + mydate.get(Calendar.SECOND) + "";

            ToastUtility.LogCat( "dateFormated    -> " + dateFormated);
        } catch (Exception e) {
            ToastUtility.LogCat( "getDateString_MMddyyyy_HHmmss_a() - Exception    -> ", e);
        }*/
        return dateFormated;
    }
    //endregion
}
