package ht.ihsi.rgph.mobile.epc.views.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.exceptions.ManagerException;
import ht.ihsi.rgph.mobile.epc.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.epc.models.BatimentModel;
import ht.ihsi.rgph.mobile.epc.models.CommuneModel;
import ht.ihsi.rgph.mobile.epc.models.IndividuModel;
import ht.ihsi.rgph.mobile.epc.models.KeyValueModel;
import ht.ihsi.rgph.mobile.epc.models.LogementModel;
import ht.ihsi.rgph.mobile.epc.models.ModuleModel;
import ht.ihsi.rgph.mobile.epc.models.QuestionReponseModel;
import ht.ihsi.rgph.mobile.epc.models.RowDataListModel;
import ht.ihsi.rgph.mobile.epc.models.VqseModel;
import ht.ihsi.rgph.mobile.epc.services.GPSService;
import ht.ihsi.rgph.mobile.epc.utilities.ContrainteReponse;
import ht.ihsi.rgph.mobile.epc.utilities.FieldMapperUtils;
import ht.ihsi.rgph.mobile.epc.utilities.HomeKeyLocker;
import ht.ihsi.rgph.mobile.epc.utilities.QuestionnaireFormulaireUtility;
import ht.ihsi.rgph.mobile.epc.utilities.Shared_Preferences;
import ht.ihsi.rgph.mobile.epc.utilities.TempInfoQuestion;
import ht.ihsi.rgph.mobile.epc.utilities.ToastUtility;
import ht.ihsi.rgph.mobile.epc.utilities.Tools;
import ht.ihsi.rgph.mobile.epc.views.adapters.DisplayListAdapter;
import ht.ihsi.rgph.mobile.epc.views.adapters.RadioListAdapter;
import ht.ihsi.rgph.mobile.epc.views.adapters.RadioListAdapterKeyValue;
import ht.ihsi.rgph.mobile.epc.views.decorator.SimpleDividerItemDecoration;

public class QuestionnaireBatimentActivity extends BaseActivity implements Serializable, View.OnClickListener, TextView.OnEditorActionListener , AdapterView.OnItemSelectedListener{//, View.OnLongClickListener {
    //region ATTRIBUTS
    private String title;
    private String qPrecedent;
    private int typeQuestion;
    //private Intent intent;
    private QuestionnaireFormulaireUtility QF, QFD;
    private TextView tv_GrandTitre;

    private TextView tv_DetailsCategorie;
    private TextView tv_SousDetailsCategorie;
    private TextView tv_LibeleQuestion;
    private TextView tv_DetailQuestion;
    private TextView tv_SectionCommune;
    private EditText et_Reponse;
    private Spinner sp_Reponse;
    private RelativeLayout RelativeLayout_Reponse;
    private Spinner sp_Reponse2;
    private RelativeLayout RelativeLayout_Reponse2;
    private Spinner sp_Reponse3;
    private RelativeLayout RelativeLayout_Reponse3;
    private Spinner sp_Jour;
    private Spinner sp_Mois;
    private Spinner sp_Annee;
    private RelativeLayout RL_Jour;
    private LinearLayout LinearLDate;

    LinearLayout LL_GaconEtFille, LL_AppareilEtAnimaux;
    TextView tv_Reponse, tv_Gason, tv_Fi;
    EditText et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet;
    EditText et_VwatiMachin, et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok;

    private Button btn_Precedent;
    private Button btn_Suivant;
    private LinearLayout LinearLDate_SaisieJJ_SelectMM_SaisieAAAA;
    private EditText et_Jour;
    private Spinner sp_Mois2;
    private EditText et_Annee;

    private HomeKeyLocker mHomeKeyLocker;

    Boolean JumpToMenu = true;
    Boolean JumpToNextQuestion = true;
    private MenuItem btn_actionbar_SaveAndQuit;
    private MenuItem btn_actionbar_Quitter;

    private TextView tvHeaderOne, tvHeaderOneLog;
    private TextView tvHeaderTwo, tvHeaderTwoLog;

    private String headerFormOne, headerFormOneLog;
    private String headerFormTwo, headerFormTwoLog;

    private RowDataListModel rowDada;
    public Dialog dialog, dialogFB, dialogIL, dialogLogNotFinish;
    EditText et_NonIndividu;
    EditText et_SiyatiIndividu;
    Spinner sp_Sexe;
    Spinner sp_RelasyonMounNan;
    TextView tv_NumeroIndividu;
    long ID_INDIVIDU = 0;
    private List<RowDataListModel> targetList = new ArrayList<RowDataListModel>();
    private RecyclerView recyclerView;


    ContrainteReponse contrainte = new ContrainteReponse();
    public static List<TempInfoQuestion> tempInfoQuestions;
    public static boolean IsActivityLogeClose = false;
    public static int CounterForLogeEndividyel = 1;
    public static int CounterForLogeCollectif = 1;

    //region LogementModel PopUp
    LogementModel logementM_OBJ = null;
    //endregion

    //region Form Batiment
    ScrollView scrollView2;
    TextView tv_GrandTitre2, tv_SousTitre2, tv_Adresse,tv_Bitasyon, tv_Lokalite, tv_Commune;//, tv_Departement, tv_Vqse;
    //Spinner sp_Departement, sp_Commune, sp_Vqse, sp_Zone;
    EditText et_Adresse , et_Bitasyon, et_Lokalite, et_EPC, et_Latitude, et_Longitude;
    Button Btn_GetGPS, btnQuitter_Bat;
    //RelativeLayout RL_Departement, RL_Commune, RL_Vqse;
    Shared_Preferences sharedPreferences = null;
    String sdeCode="";
    String recCode="";

    //endregion

//endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_v2);

        toolbar =  (Toolbar)findViewById(R.id.my_toolbar);
        toolbar.setTitle(Html.fromHtml("<b>" + getString(R.string.app_name) + "</b>"));
        setSupportActionBar(toolbar);
        try {
            tempInfoQuestions = new ArrayList<TempInfoQuestion>();
            //QF.getDateInfo(this);
            init(Constant.FORM_DATA_MANAGER);
            init(Constant.QUERY_RECORD_MANAGER);
            init(Constant.CU_RECORD_MANAGER);

            Intent intent= getIntent();

            headerFormOne = intent.getStringExtra(Constant.PARAM_FORM_HEADER_ONE).toString();
            headerFormTwo = intent.getStringExtra(Constant.PARAM_FORM_HEADER_TWO).toString();

            tvHeaderOne=(TextView) this.findViewById(R.id.header1);
            tvHeaderOne.setText(headerFormOne.toUpperCase());

            toolbar.setTitle(Html.fromHtml(headerFormOne.toUpperCase()));

            tvHeaderTwo=(TextView) this.findViewById(R.id.header2);
            tvHeaderTwo.setText( Html.fromHtml(headerFormTwo) );


            tv_GrandTitre = (TextView) this.findViewById(R.id.tv_grandtitre);
            tv_DetailsCategorie = (TextView) this.findViewById(R.id.tv_DetailsCategorie);
            tv_SousDetailsCategorie = (TextView) this.findViewById(R.id.tv_SousDetailsCategorie);
            tv_LibeleQuestion = (TextView) this.findViewById(R.id.tv_LibeleQuestion);
            tv_DetailQuestion = (TextView) this.findViewById(R.id.tv_DetailQuestion);
            tv_Commune = (TextView) this.findViewById(R.id.tv_Commune);
            tv_SectionCommune = (TextView) this.findViewById(R.id.tv_SectionCommune);

            recyclerViewReponse = (RecyclerView) findViewById(R.id.recyclerViewReponse);
            LL_RecyclerView = (LinearLayout) this.findViewById(R.id.LL_RecyclerView);
            et_Reponse = (EditText) this.findViewById(R.id.et_Reponse);
            et_Reponse.setOnEditorActionListener(this);

            sp_Reponse = (Spinner) this.findViewById(R.id.sp_Reponse);
            RelativeLayout_Reponse = (RelativeLayout) this.findViewById(R.id.RelativeLayout_Reponse);
            sp_Reponse.setOnItemSelectedListener(this);

            sp_Reponse2 = (Spinner) this.findViewById(R.id.sp_Reponse2);
            RelativeLayout_Reponse2 = (RelativeLayout) this.findViewById(R.id.RelativeLayout_Reponse2);
            sp_Reponse2.setOnItemSelectedListener(this);

            sp_Reponse3 = (Spinner) this.findViewById(R.id.sp_Reponse3);
            RelativeLayout_Reponse3 = (RelativeLayout) this.findViewById(R.id.RelativeLayout_Reponse3);

            sp_Jour = (Spinner) this.findViewById(R.id.sp_Jour);
            sp_Mois = (Spinner) this.findViewById(R.id.sp_Mois);
            sp_Annee = (Spinner) this.findViewById(R.id.sp_Annee);
            RL_Jour = (RelativeLayout) this.findViewById(R.id.RL_Jour);
            LinearLDate = (LinearLayout) this.findViewById(R.id.LinearLDate);

            tv_Reponse = (TextView) this.findViewById(R.id.tv_Reponse);
            LL_GaconEtFille = (LinearLayout) this.findViewById(R.id.LL_GaconEtFille);
            tv_Gason = (TextView) this.findViewById(R.id.tv_Gason);
            et_Gason = (EditText) this.findViewById(R.id.et_Gason);
            tv_Fi = (TextView) this.findViewById(R.id.tv_Fi);
            et_Fi = (EditText) this.findViewById(R.id.et_Fi);
            LL_AppareilEtAnimaux = (LinearLayout) this.findViewById(R.id.LL_AppareilEtAnimaux);
            et_ApareyRadyo = (EditText) this.findViewById(R.id.et_ApareyRadyo);
            et_Televizyon = (EditText) this.findViewById(R.id.et_Televizyon);
            et_FrijideFrize = (EditText) this.findViewById(R.id.et_FrijideFrize);
            et_FouElektrikFouAkGaz = (EditText) this.findViewById(R.id.et_FouElektrikFouAkGaz);
            et_OdinatePCLaptopTabletNimerik = (EditText) this.findViewById(R.id.et_OdinatePCLaptopTabletNimerik);
            et_BisikletMotosiklet = (EditText) this.findViewById(R.id.et_BisikletMotosiklet);
            et_VwatiMachin = (EditText) this.findViewById(R.id.et_VwatiMachin);
            et_KannotBato = (EditText) this.findViewById(R.id.et_KannotBato);
            et_InvetePanoSoleJeneratrisDelko = (EditText) this.findViewById(R.id.et_InvetePanoSoleJeneratrisDelko);
            et_MiletChwalBourik = (EditText) this.findViewById(R.id.et_MiletChwalBourik);
            et_BefVach = (EditText) this.findViewById(R.id.et_BefVach);
            et_KochonKabrit = (EditText) this.findViewById(R.id.et_KochonKabrit);
            et_BetVolayPoulKok = (EditText) this.findViewById(R.id.et_BetVolayPoulKok);

            LinearLDate_SaisieJJ_SelectMM_SaisieAAAA = (LinearLayout) this.findViewById(R.id.LinearLDate_SaisieJJ_SelectMM_SaisieAAAA);
            et_Jour = (EditText) this.findViewById(R.id.et_Jour);
            sp_Mois2 = (Spinner) this.findViewById(R.id.sp_Mois2);
            et_Annee = (EditText) this.findViewById(R.id.et_Annee);


            btn_Precedent = (Button) this.findViewById(R.id.btn_Precedent);
            btn_Precedent.setOnClickListener(this);

            btn_Suivant = (Button) this.findViewById(R.id.btn_Suivant);
            btn_Suivant.setOnClickListener(this);

            RelativeLayout RL_Questionnaire = (RelativeLayout) this.findViewById(R.id.RL_Questionnaire);
            registerForContextMenu(RL_Questionnaire);
            //ScrollView scrollView2 = (ScrollView) this.findViewById(R.id.scrollView2);
            //registerForContextMenu(scrollView2);
            //RL_Questionnaire.setOnLongClickListener(this);

            // LOAD FIRST QUESTION
            //QF = SessionUtility.getCurrentQuestionnaireFormulaireUtility();
            //QF = new QuestionnaireFormulaireUtility();
            //QF = (QuestionnaireFormulaireUtility) intent.getSerializableExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE);

            //toolbar.setTitle(title);
            //toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            //QF.formDataMngr = formDataMngr;
            //QF.queryRecordMngr = queryRecordMngr;


            //QF = SessionUtility.getCurrentQuestionnaireFormulaireUtility();
            //QF = new QuestionnaireFormulaireUtility();
            QF = new QuestionnaireFormulaireUtility();
            QF = (QuestionnaireFormulaireUtility) intent.getSerializableExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE);
            ///QF = (QuestionnaireFormulaireUtility) QM.getModel();
            QF.context = QuestionnaireBatimentActivity.this;
            GetFieldValuesQuestionInfo(QF);

            ShowFormulaireBatiment();
            //QF.context = QuestionnaireBatimentActivity.this;
            /*if (QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL) {
                LogementModel log = (LogementModel) QF.getData();
                if (log.getQlin2StatutOccupation() != null && log.getQlin2StatutOccupation() == Constant.LOJMAN_OKIPE_TOUTAN_MEN_MOUN_YO_PA_LA_2) {
                    if(QF.getDataBase() != null){
                        log = (LogementModel)QF.getDataBase();
                    }
                    int NbrTentative = ( log.getNbrTentative() != null ? log.getNbrTentative() : 1);
                    if (NbrTentative < 3) {
                        ToastUtility.ToastMessage(this, "Tantativ: " + NbrTentative, Constant.GravityCenter);
                    } else if (NbrTentative == 3) {
                        ToastUtility.ToastMessage(this, "Dènye Tantativ: " + NbrTentative, Constant.GravityCenter);
                    }
                }
            }*/
            //mHomeKeyLocker = new HomeKeyLocker();
            //mHomeKeyLocker.lock(this);
            LL_RecyclerView.setEnabled(false);
        }catch (Exception ex){
            ToastUtility.LogCat(this, "Exception: onCreate(): ", ex);
            ex.printStackTrace();
        }
    }//

    //region PopUp FICHE BATIMENT
    private void ShowFormulaireBatiment() {
        try {
            dialogFB = new Dialog(this);
            dialogFB.setContentView(R.layout.batiment_form);
            dialogFB.setCancelable(false);
            scrollView2 = (ScrollView) dialogFB.findViewById(R.id.scrollView2);

            QF.context = QuestionnaireBatimentActivity.this;

            tv_GrandTitre2 = (TextView) dialogFB.findViewById(R.id.tv_grandtitre);
            tv_SousTitre2 = (TextView) dialogFB.findViewById(R.id.tv_SousTitre);

            et_Latitude = (EditText) dialogFB.findViewById(R.id.et_Latitude);
            et_Longitude = (EditText) dialogFB.findViewById(R.id.et_Longitude);
            //Btn_GetGPS = (Button) dialog.findViewById(R.id.Btn_GetGPS);
            //btnQuitter_Bat = (Button) dialog.findViewById(R.id.btnQuitter_Bat);

            tv_Adresse = (TextView) dialogFB.findViewById(R.id.tv_Adresse);
            et_Adresse = (EditText) dialogFB.findViewById(R.id.et_Adresse);

            tv_Bitasyon = (TextView) dialogFB.findViewById(R.id.tv_Bitasyon);
            et_Bitasyon = (EditText) dialogFB.findViewById(R.id.et_Bitasyon);
            tv_Lokalite = (TextView) dialogFB.findViewById(R.id.tv_Lokalite);
            et_Lokalite = (EditText) dialogFB.findViewById(R.id.et_Lokalite);

            tv_Adresse.setVisibility(View.GONE);
            et_Adresse.setVisibility(View.GONE);
            tv_Bitasyon.setVisibility(View.GONE);
            et_Bitasyon.setVisibility(View.GONE);
            tv_Lokalite.setVisibility(View.GONE);
            et_Lokalite.setVisibility(View.GONE);

            //et_RtEC = (EditText) dialogFB.findViewById(R.id.et_RtEC);

            //region setOnEditorActionListener et_EPC
            et_EPC = (EditText) dialogFB.findViewById(R.id.et_RGPH);
            et_EPC.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    //ToastUtility.LogCat("D", "INSIDE setOnEditorActionListener() | actionId:" + actionId);
                    if( actionId == Constant.imeActionId_EtReponse_6){
                        SetDataBatimentFirst();
                        return true;
                    }
                    return false;
                }
            });
            //endregion

            sharedPreferences = Tools.SharedPreferences(this);
            if ( sharedPreferences != null ) {
                sdeCode = sharedPreferences.getSdeId();

                if (sharedPreferences.getZone().equalsIgnoreCase(""+Constant.ZONE_URBAIN_1)) {
                    tv_Adresse.setVisibility(View.VISIBLE);
                    et_Adresse.setVisibility(View.VISIBLE);
                } else if (sharedPreferences.getZone().equalsIgnoreCase(""+Constant.ZONE_RURAL_2)) {
                    tv_Bitasyon.setVisibility(View.VISIBLE);
                    et_Bitasyon.setVisibility(View.VISIBLE);
                    tv_Lokalite.setVisibility(View.VISIBLE);
                    et_Lokalite.setVisibility(View.VISIBLE);
                }
            }
            //String noRecStr = "0";
            long noRecInt =0;
            if( QF.getBatimentModel() != null &&  QF.getBatimentModel().getQrec() != null ){
                recCode = ""+QF.getBatimentModel().getQrec();
                noRecInt = (QF.getBatimentModel().getBatimentId() != null ?  QF.getBatimentModel().getBatimentId() : 0 );
                QF.setCodeAgentRecenseur(QF.getBatimentModel().getCodeAgentRecenceur());
            }else{
                noRecInt = queryRecordMngr.countBatimentBySDE(sdeCode) + 1;
                recCode = ""+noRecInt;
                if (noRecInt >= 0 && noRecInt<=9) {
                    recCode = "00"+noRecInt;
                } else if (noRecInt >= 10 && noRecInt<=99) {
                    recCode = "0"+noRecInt;
                } else{
                    recCode = ""+noRecInt;
                }
                //et_RtEC.setText( recCode );
            }

            dialogFB.setTitle(Html.fromHtml("LOKALIZASYON <b>BATIMAN " + noRecInt + "</b> | REC: " + recCode) );

            //message = "<b> Menage " + QF.getMenageModel().getQm1NoOrdre() + "</b>";
            //tv_GrandTitreInd.setText(Html.fromHtml("" + message));

            //region Buttons Btn_GetGPS
            Button Btn_GetGPS = (Button) dialogFB.findViewById(R.id.Btn_GetGPS);
            Btn_GetGPS.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    getServiceGPS();
                }
            });
            //endregion

            //region Buttons btnQuitter
            Button btnQuitter_Bat = (Button) dialogFB.findViewById(R.id.btnQuitter_Bat);
            btnQuitter_Bat.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (dialogFB != null) {
                        dialogFB.dismiss();
                    }
                    // On ferme l'activity
                    finish();
                }
            });
            //endregion

            //region Buttons btnContinuer
            Button btnContinuer = (Button) dialogFB.findViewById(R.id.btnContinuer);
            btnContinuer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SetDataBatimentFirst();
                }
            });
            //endregion

            dialogFB.show();

            if ( sharedPreferences != null ){
                //tv_KodSeksyonEnimerasyon.setText(sharedPreferences.getSdeId());
                //et_KodSeksyonEnimerasyon.setEnabled(false);
                //DepartementModel departementModel = formDataMngr.getDepartementById(sharedPreferences.getDeptId());
                //CommuneModel communeModel = formDataMngr.getCommuneById(sharedPreferences.getComId());
                //VqseModel vqseModel = formDataMngr.getVqseById(sharedPreferences.getVqseId());

                sdeCode = sharedPreferences.getSdeId();
                //String localisationString = "" + departementModel.getDeptNom() + " <b>|</b> " + communeModel.getComNom() + " <b>|</b> " + vqseModel.getVqseNom()
                //        + "<b>" + ( sharedPreferences.getZone().equalsIgnoreCase(""+Constant.ZONE_URBAIN_1)  ?  "<br >Zòn Iben " : "<br >Zòn Riral" ) +"</b>"; ;

                //tv_SousTitre2.setText(Html.fromHtml("" + localisationString));
                tv_SousTitre2.setText(Html.fromHtml("" + headerFormTwo + "<br />LOKALIZASYON <b>BATIMAN " + noRecInt + "</b> | REC: " + recCode));
            }

            if( QF.getBatimentModel() != null &&  QF.getBatimentModel().getQrec() != null &&  QF.getBatimentModel().getQepc() != null ){//&&  QF.getBatimentModel().getBatimentId() > 0 ) {

                //noRecInt = (QF.getBatimentModel().getBatimentId() != null ?  QF.getBatimentModel().getBatimentId() : 0 );
                //dialogFB.setTitle("LOKALIZASYON BATIMAN " + noRecInt + " | REC: " + recCode);

                recCode = QF.getBatimentModel().getQrec();
                et_EPC.setText(QF.getBatimentModel().getQepc());
                et_Latitude.setText(QF.getBatimentModel().getLatitude());
                et_Longitude.setText(QF.getBatimentModel().getLongitude());

                if (QF.getBatimentModel().getZone() == Constant.ZONE_URBAIN_1) {
                    tv_Adresse.setVisibility(View.VISIBLE);
                    et_Adresse.setVisibility(View.VISIBLE);
                    et_Adresse.setText(QF.getBatimentModel().getQadresse());

                    tv_Bitasyon.setVisibility(View.GONE);
                    et_Bitasyon.setVisibility(View.GONE);
                    tv_Lokalite.setVisibility(View.GONE);
                    et_Lokalite.setVisibility(View.GONE);

                } else if (QF.getBatimentModel().getZone() == Constant.ZONE_RURAL_2) {
                    tv_Adresse.setVisibility(View.GONE);
                    et_Adresse.setVisibility(View.GONE);
                    tv_Bitasyon.setVisibility(View.VISIBLE);
                    et_Bitasyon.setVisibility(View.VISIBLE);
                    tv_Lokalite.setVisibility(View.VISIBLE);
                    et_Lokalite.setVisibility(View.VISIBLE);

                    et_Bitasyon.setText(QF.getBatimentModel().getQhabitation());
                    et_Lokalite.setText(QF.getBatimentModel().getQlocalite());
                }
            }else{
                //int noRec = queryRecordMngr.countBatimentBySDE(sdeCode) + 1;
                //et_RtEC.setText( et_RtEC );
            }
            // Rendre readOnly les champs
            if( QF.TypeEvenement == Constant.ACTION_AFFICHER ){
                et_Bitasyon.setEnabled(false);
                et_Lokalite.setEnabled(false);
                et_Adresse.setEnabled(false);
                et_Latitude.setEnabled(false);
                et_Longitude.setEnabled(false);
                et_EPC.setEnabled(false);
                Btn_GetGPS.setEnabled(false);

            }
        } catch (Exception ex) {
            message = "Erreur :";
            ToastUtility.LogCat("Exception: ShowFormulaireBatiment :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void SetDataBatimentFirst() {
        String departementId="", communeId="", vqseId="", zoneId="", kodDistriSipevisyon="", codeAgentRecenceur="";
        try {
            QF.context = QuestionnaireBatimentActivity.this;
            if ( sharedPreferences != null ){
                zoneId = sharedPreferences.getZone();
                departementId = sharedPreferences.getDeptId();
                communeId = sharedPreferences.getComId();
                vqseId = sharedPreferences.getVqseId();
                sdeCode = sharedPreferences.getSdeId();
                kodDistriSipevisyon = sharedPreferences.getCodeDsitri();
                codeAgentRecenceur = sharedPreferences.getNomUtilisateur();
            }
            QF.CheckValueBefore_Batiment( queryRecordMngr //, cuRecordMngr, Constant.SetValueTempInfoQuestion_Batiment
                    , departementId, communeId, vqseId, zoneId, sdeCode, kodDistriSipevisyon
                    , et_Adresse, et_Bitasyon, et_Lokalite, recCode, et_EPC
                    , et_Latitude, et_Longitude, codeAgentRecenceur );

            //QF.SaveInfoDefinitivement(cuRecordMngr, false);
            if (dialogFB != null) {
                dialogFB.dismiss();
            }
            //Suivant_Click();
        }catch (TextEmptyException  ex) {
            Tools.AlertDialogMsg( QuestionnaireBatimentActivity.this, ex.getMessage());
            ToastUtility.LogCat("TextEmptyException: ShowFormulaireBatiment() :" + ex.getMessage());
            //}catch (ManagerException ex) {
            //    ToastUtility.LogCat("Exception: ShowFormulaireBatiment() :" + ex.getMessage() + " / toString: " + ex.toString());
            //    ToastUtility.ToastMessage(QuestionnaireBatimentActivity.this, ex.getMessage());
        }catch (Exception ex) {
            ToastUtility.LogCat("Exception: ShowFormulaireBatiment() :" + ex.getMessage() + " / toString: " + ex.toString());
            ex.printStackTrace();
        }
    }
    // endregion

    //region GET DATA : GetFieldValuesQuestionInfo- ...
    private void GetFieldValuesQuestionInfo(QuestionnaireFormulaireUtility qf) {
        try {
            QuestionnaireFormulaireUtility.SetFieldValuesQuestionInfo( this, formDataMngr,  qf
                    , tv_DetailsCategorie , tv_SousDetailsCategorie , tv_DetailQuestion , tv_GrandTitre , toolbar
                    , tv_LibeleQuestion , tv_Commune , tv_SectionCommune
                    , recyclerViewReponse, LL_RecyclerView, radioListAdapter,  getItemClickListener(), radioListAdapterKeyValue,  getItemClickListenerKeyValue()
                    , et_Reponse , sp_Reponse , RelativeLayout_Reponse , sp_Reponse2 , RelativeLayout_Reponse2 , sp_Reponse3 , RelativeLayout_Reponse3
                    , LinearLDate , RL_Jour , sp_Jour , sp_Mois , sp_Annee
                    , LL_GaconEtFille, LL_AppareilEtAnimaux,tv_Reponse, tv_Gason, tv_Fi,et_Gason, et_Fi
                    , et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                    , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok, btn_Precedent, btn_Suivant
                    , LinearLDate_SaisieJJ_SelectMM_SaisieAAAA, et_Jour, sp_Mois2 , et_Annee);

            //QF.Load_PossibiliteReponse(this, formDataMngr, recyclerViewReponse, radioListAdapter,  getItemClickListener());
            /* Dialog pupUp Formulaire liste individu dans le menage */
            SetReponseValue_DataBase(qf);

            /* Add Logement Col et Ind. dans Batiment */
            ShowPopUp_AddLogement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//
    //endregion

    //region EVENTS CONTROLS
    @Override
    protected void onResume() {
        try {
            if(IsActivityLogeClose) {
                if (QF.getTbl_TableName() == Constant.FORMULAIRE_BATIMENT) {
                    if (QF.getNomChamps().equalsIgnoreCase(Constant.CallFormulaireLogementEndividyel)) {
                        //region [ Call Formulaire Logement Endividyel ]
                        int NbreLogeEndividyel = QF.getBatimentModel().getQb4NbreLogeIndividuel();
                        // On verifie s'il existe de logement Individuel
                        if (NbreLogeEndividyel > 0) {
                            long nbreLogement_DejaSave = queryRecordMngr.countLogByBatAndType(QF.getBatimentModel().getBatimentId(), Constant.LOJ_ENDIVIDYEL);
                            if (CounterForLogeEndividyel >= NbreLogeEndividyel) {
                                ShowListInformationsLogement(Constant.LOJ_ENDIVIDYEL, (int) nbreLogement_DejaSave);
                            } else {
                                // On selectionne le Logement qui n'a pas un statut FINI
                                /*LogementModel logM = null;
                                do {
                                    CounterForLogeEndividyel += 1;
                                    logM = queryRecordMngr.searchLogementByNoOrdreByTypeLogByIdBatiment(CounterForLogeEndividyel, Constant.LOJ_ENDIVIDYEL, QF.getBatimentModel().getBatimentId());
                                }
                                while (logM == null && CounterForLogeEndividyel <= NbreLogeEndividyel && nbreLogement_DejaSave > 0);

                                if (logM != null) {
                                    this.SetFieldLogement(logM, NbreLogeEndividyel, Constant.LOJ_ENDIVIDYEL, Constant.ACTION_MOFIDIER);

                                    message = "Kontinye pran enfòmasyon sou Lojman Endividyèl " + logementM_OBJ.getQlin1NumeroOrdre() + " la";
                                    ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                                } else {*/
                                    if (NbreLogeEndividyel == nbreLogement_DejaSave) {
                                        // On Passe a la question suivante
                                        Suivant_Click();
                                    } else {
                                        //CounterForLogeEndividyel -= 1;
                                        Precedent_Click(QF);
                                    }
                                //}
                            }
                        }//endregion
                    }
                }
            }
        } catch (Exception ex) {
            message = "Erreur:" + ex.getMessage();
            ToastUtility.LogCat("Exception: onResume :" + message + " / " + ex.toString());
            Tools.AlertDialogMsg(this, message + "\n" + ex.toString());
            ex.printStackTrace();
        }
        super.onResume();
    }
    //
    @Override
    public void onClick(View v) {
        et_Reponse.setEnabled(true);
        sp_Reponse.setEnabled(true);
        sp_Reponse2.setEnabled(true);
        sp_Reponse3.setEnabled(true);
        sp_Jour.setEnabled(true);
        sp_Mois.setEnabled(true);
        sp_Annee.setEnabled(true);

        switch (v.getId()) {
            case R.id.btn_Suivant:
                Suivant_Click();
                break;
            case R.id.btn_Precedent:
                Precedent_Click(QF);
                break;
            default:
                break;
        }
    }//
    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater= getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_action_questionnaire, menu);

        btn_actionbar_SaveAndQuit = menu.findItem(R.id.btn_actionbar_SaveAndQuit);
        btn_actionbar_Quitter = menu.findItem(R.id.btn_actionbar_Quitter);

        if(QF.TypeEvenement == Constant.ACTION_AFFICHER) {
            btn_actionbar_SaveAndQuit.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }
    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try{
            switch (item.getItemId()){
                case R.id.btn_actionbar_SaveAndQuit:
                    try {
                        QF.CheckValueBefore(queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Batiment, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                                ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                                , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                                , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                                , et_Jour, sp_Mois2 , et_Annee
                        );
                        // Enregistrement des informations
                        QF.SaveInfoDefinitivement(cuRecordMngr, false);

                        this.ShowRapport_RAR(  Constant.STATUT_PA_FIN_RANPLI_22);
                        //finish();
                    }catch (TextEmptyException  ex) {
                        Tools.AlertDialogMsg(this, ex.getMessage());
                        ToastUtility.LogCat("TextEmptyException: onOptionsItemSelected() :" + ex.getMessage());
                    }
                    break;
                case R.id.btn_actionbar_Quitter:
                    ShowAlertDialogQuitter();
                    break;
                default:
                    break;
            }
        }catch (TextEmptyException  ex) {
            Tools.AlertDialogMsg(this, ex.getMessage());
            ToastUtility.LogCat("TextEmptyException: onOptionsItemSelected() :" + ex.getMessage());
        }catch (ManagerException ex) {
            ToastUtility.LogCat("Exception: onOptionsItemSelected() :" + ex.getMessage() + " / toString: " + ex.toString());
            ToastUtility.ToastMessage(this, ex.getMessage());
        }catch (Exception ex) {
            ToastUtility.LogCat("Exception: onOptionsItemSelected() :" + ex.getMessage() + " / toString: " + ex.toString());
            ex.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }
    //
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.RL_Questionnaire) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_action_questionnaire, menu);

            btn_actionbar_SaveAndQuit = menu.findItem(R.id.btn_actionbar_SaveAndQuit);
            btn_actionbar_Quitter = menu.findItem(R.id.btn_actionbar_Quitter);

            if(QF.TypeEvenement == Constant.ACTION_AFFICHER) {
                btn_actionbar_SaveAndQuit.setVisible(false);
            }
        }
    }
    //
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        try {
            switch (item.getItemId()) {
                case R.id.btn_actionbar_SaveAndQuit:
                    try {
                        QF.CheckValueBefore(queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Batiment, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                                ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                                , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                                , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                                , et_Jour, sp_Mois2 , et_Annee
                        );
                        // Enregistrement des informations
                        QF.SaveInfoDefinitivement(cuRecordMngr, false);

                        this.ShowRapport_RAR( Constant.STATUT_PA_FIN_RANPLI_22);
                        //finish();
                    } catch (TextEmptyException ex) {
                        Tools.AlertDialogMsg(this, ex.getMessage());
                        ToastUtility.LogCat("TextEmptyException: onContextItemSelected() :" + ex.getMessage());
                    }
                    break;
                case R.id.btn_actionbar_Quitter:
                    ShowAlertDialogQuitter();
                    break;
                default:
                    break;
            }
        } catch (TextEmptyException ex) {
            Tools.AlertDialogMsg(this, ex.getMessage());
            ToastUtility.LogCat("TextEmptyException: onContextItemSelected() :" + ex.getMessage());
        } catch (ManagerException ex) {
            ToastUtility.LogCat("Exception: onOptionsItemSelected() :" + ex.getMessage() + " / toString: " + ex.toString());
            ToastUtility.ToastMessage(this, ex.getMessage());
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception: onContextItemSelected() :" + ex.getMessage() + " / toString: " + ex.toString());
            ex.printStackTrace();
        }
        return super.onContextItemSelected(item);
    }
    //
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if( actionId == R.integer.imeActionId_EtReponse_6){
            Suivant_Click();
            return true;
        }
        return false;
    }//
    //
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            typeQuestion = QF.getTypeQuestion();
            // On selecting a spinner item
            QuestionReponseModel reponseModel = null;
            if (typeQuestion == Constant.TYPE_QUESTION_CHOIX_1
                    || typeQuestion == Constant.TYPE_QUESTION_DEUX_CHOIX_4
                    || typeQuestion == Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15
                    || typeQuestion == Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_LISTE_BOX_LIER_21 ) {
                reponseModel = ((QuestionReponseModel) sp_Reponse.getSelectedItem());
                if (!QF.codeReponseParentLast.equals(reponseModel.getCodeUniqueReponse())) {
                    QF.IsLoadPossibiliteReponse_ChildAgain = true;
                }
            }
            if (QF.getEstSautReponse()) {// SI LE SAUT DEPEND DE LA REPONSE
                if (!reponseModel.getCodeReponse().trim().equalsIgnoreCase("")) {
                    if (!reponseModel.getQSuivant().equalsIgnoreCase(""))
                        QF.setqSuivant(reponseModel.getQSuivant());
                    //QF.SetButtonLabel(this, reponseModel.getCodeReponse(), QF, btn_Suivant);
                }
            }
            if (reponseModel != null) {
                if (parent.getId() == R.id.sp_Reponse) {
                    QF.SetButtonLabel(this, reponseModel.getCodeReponse(), QF, btn_Suivant);

                    if (reponseModel.getAvoirEnfant()) {
                        // VERIFIER S'IL EXISTE DES SOUS REPONSES ( EXEMPLE UTILISATION 1 ET UTILISATION 2 )
                        if (!reponseModel.getCodeUniqueReponse().trim().equalsIgnoreCase("")) {
                            sp_Reponse2.setVisibility(View.VISIBLE);
                            RelativeLayout_Reponse2.setVisibility(View.VISIBLE);
                            tv_Commune.setVisibility(View.VISIBLE);
                            tv_Commune.setText("...");
                            if (QF.getTbl_TableName() == Constant.FORMULAIRE_BATIMENT) {
                                tv_Commune.setText("Di ki de sa yo plis fè...");
                            }

                            if (QF.IsLoadPossibiliteReponse_ChildAgain) {
                                if (QF.getTbl_TableName() == Constant.FORMULAIRE_INDIVIDUS) {
                                    // Pou E4A	Ki pi wo nivo {0} te rive nan lekol / inivesite?
                                    if (QF.getNomChamps().equalsIgnoreCase(Constant.Qe4ANiveauEtudeETClasse)) {//
                                        tv_Reponse.setVisibility(View.VISIBLE);
                                        tv_Reponse.setText(getString(R.string.label_E4B_Nivo));
                                        tv_Commune.setText(getString(R.string.label_E4B_KlasAneDetid));
                                    }
                                }
                                //ToastUtility.LogCat("W", "INSIDE onItemSelected() / [+]  IsLoadPossibiliteReponse_ChildAgain = TRUE / codeParent>>" + reponseModel.getCodeUniqueReponse() + " / codeQuestionSplit>>" + reponseModel.getCodeQuestion() + " / codeReponseParent>>" + reponseModel.getCodeReponse());
                                QF.Load_PossibiliteReponse_Child(this, formDataMngr, sp_Reponse2, reponseModel.getCodeUniqueReponse());
                            }
                        } else {
                            //ToastUtility.LogCat("E", "INSIDE onItemSelected() / [-]  IsLoadPossibiliteReponse_ChildAgain = FALSE");
                            sp_Reponse2.setVisibility(View.GONE);
                            RelativeLayout_Reponse2.setVisibility(View.GONE);
                            tv_Commune.setVisibility(View.GONE);
                        }
                    } else {
                        sp_Reponse2.setVisibility(View.GONE);
                        RelativeLayout_Reponse2.setVisibility(View.GONE);
                        tv_Commune.setVisibility(View.GONE);
                    }
                }

            }
            // TEST POUR LE CHARGEMENT DES COMMUNES
            KeyValueModel keyValueDept = null;
            if (typeQuestion == Constant.TYPE_QUESTION_CHOIX_COMMUNE_6) {
                keyValueDept = ((KeyValueModel) sp_Reponse.getSelectedItem());
                if (!QF.idDeptLast.equals(keyValueDept.getKey())) {
                    QF.IsLoadCommuneAgain = true;
                }
            }
            if (keyValueDept != null) {
                if (!keyValueDept.getKey().trim().equalsIgnoreCase("")) {
                    if (parent.getId() == R.id.sp_Reponse) {
                        sp_Reponse2.setVisibility(View.VISIBLE);
                        RelativeLayout_Reponse2.setVisibility(View.VISIBLE);
                        tv_Commune.setVisibility(View.VISIBLE);
                        tv_Commune.setText("Komin");
                        if (QF.IsLoadCommuneAgain) {
                            ToastUtility.LogCat("[+] CALL ON onItemSelected ( QF.Load_CommuneByIdDept(sp_Reponse2, keyValueDept.getKey()); )");
                            QF.Load_CommuneByIdDept(formDataMngr, sp_Reponse2, keyValueDept.getKey());
                            QF.Load_ClearSpinner(sp_Reponse3, Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7);
                            //ToastUtility.LogCat(this, "sp_Reponse - getKey: " + keyValueDept.getKey());
                        }
                    }
                }
            }
            // TEST POUR LE CHARGEMENT DES VQSE
            KeyValueModel dept2 = null;
            CommuneModel communeModel = null;
            if (typeQuestion == Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7) {
                dept2 = ((KeyValueModel) sp_Reponse.getSelectedItem());
                if (!QF.idDeptLast.equals(dept2.getKey())) {
                    QF.IsLoadCommuneAgain = true;
                }
                if (sp_Reponse2.getSelectedItem() != null) {
                    communeModel = ((CommuneModel) sp_Reponse2.getSelectedItem());
                    if (!QF.idComLast.equals(communeModel.getComId())) {
                        QF.IsLoadVqseAgain = true;
                    }
                }
            }

            if (dept2 != null) {
                if (!dept2.getKey().trim().equalsIgnoreCase("")) {
                    if (parent.getId() == R.id.sp_Reponse) {
                        sp_Reponse2.setVisibility(View.VISIBLE);
                        RelativeLayout_Reponse2.setVisibility(View.VISIBLE);
                        tv_Commune.setVisibility(View.VISIBLE);
                        tv_Commune.setText("Komin");
                        if (QF.IsLoadCommuneAgain) {
                            ToastUtility.LogCat("[+] CALL ON onItemSelected ( QF.Load_CommuneByIdDept(sp_Reponse2, keyValueDept.getKey()); )");
                            QF.Load_CommuneByIdDept(formDataMngr, sp_Reponse2, dept2.getKey());
                            QF.Load_ClearSpinner(sp_Reponse3, Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7);
                            //ToastUtility.LogCat(this, "sp_Reponse - getKey: " + keyValueDept.getKey());
                        }
                    }
                }
            }
            if (communeModel != null) {
                if (!communeModel.getComId().trim().equalsIgnoreCase("")) {
                    if (parent.getId() == R.id.sp_Reponse2) {
                        sp_Reponse3.setVisibility(View.VISIBLE);
                        RelativeLayout_Reponse3.setVisibility(View.VISIBLE);
                        tv_SectionCommune.setVisibility(View.VISIBLE);
                        tv_SectionCommune.setText("Seksyon Kominal");
                        if (QF.IsLoadVqseAgain) {
                            ToastUtility.LogCat("[+] CALL ON onItemSelected ( QF.Load_Vqse_ByIdCommune(sp_Reponse3, communeModel.getComId()); )");
                            QF.Load_Vqse_ByIdCommune(formDataMngr, sp_Reponse3, communeModel.getComId());
                        }
                    }
                }
            }
            // CHANGEMENT DE QUESTION AUTOMATIQUEENT POUR LES QUESTIONS
            QuestionReponseModel reponseModel2 = null;
            if (parent.getId() == R.id.sp_Reponse) {
                if (typeQuestion == Constant.TYPE_QUESTION_DEUX_CHOIX_4
                        || typeQuestion == Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15) {
                    reponseModel = ((QuestionReponseModel) sp_Reponse.getSelectedItem());
                    if (reponseModel != null) {
                        if (!reponseModel.getCodeUniqueReponse().trim().equalsIgnoreCase("")) {
                            if (sp_Reponse2.getVisibility() == View.GONE) {
                                if (!isRetour) {
                                    //Suivant_Click();
                                } else {
                                    isRetour = false;
                                    btn_Suivant.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                }
            }

            if (parent.getId() == R.id.sp_Reponse2) {
                if (typeQuestion == Constant.TYPE_QUESTION_DEUX_CHOIX_4
                        || typeQuestion == Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15) {
                    reponseModel2 = ((QuestionReponseModel) sp_Reponse2.getSelectedItem());
                    if (reponseModel2 != null) {
                        if (!reponseModel2.getCodeReponse().trim().equalsIgnoreCase("")) {
                            QF.SetButtonLabel(this, reponseModel2.getCodeReponse(), QF, btn_Suivant);

                            if (!isRetour) {
                                //Suivant_Click();
                            } else {
                                isRetour = false;
                                btn_Suivant.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
                //ToastUtility.LogCat(this, "sp_Reponse2 - getCodeUniqueReponse: " + reponseModel.getCodeUniqueReponse());
            }

            VqseModel vqse = null;
            if (parent.getId() == R.id.sp_Reponse3) {
                if (typeQuestion == Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7) {
                    vqse = ((VqseModel) sp_Reponse3.getSelectedItem());
                    if (vqse != null) {
                        if (!vqse.getVqseId().trim().equalsIgnoreCase("")) {
                            if (!isRetour) {
                                //Suivant_Click();
                            } else {
                                isRetour = false;
                                btn_Suivant.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception: onItemSelected() :" + ex.getMessage() + " / toString: " + ex.toString());
            ex.printStackTrace();
        }
    }//
    //
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }//

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            final int _keyCode=keyCode;
            final KeyEvent _event=event;
            //e = event;
            //k = keyCode;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("" + getString(R.string.msg_Eske_Ou_Vle_Kite___) + " Modil Batiman an vre.")
                    .setCancelable(false)
                    .setPositiveButton(""+ getString(R.string.label_Non), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
                    .setNegativeButton("" + getString(R.string.label_WI), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            QuestionnaireBatimentActivity.super.onKeyDown(_keyCode, _event);
                            //finish();
                            CreateMenuContext();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        }else  if (keyCode == KeyEvent.KEYCODE_HOME){
           return true;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }//

    //endregion

    //region EVENTS REPONSE
    public RadioListAdapter.OnItemClickListener getItemClickListener(){
       return new RadioListAdapter.OnItemClickListener(){
           @Override
           public void onItemClick(QuestionReponseModel questionReponseModel) {
               codeReponseRecyclerView = questionReponseModel;
               RadioListAdapter radioListAdapter = (RadioListAdapter) recyclerViewReponse.getAdapter();
               radioListAdapter.setReponseModelSelectionner(questionReponseModel);

               if (codeReponseRecyclerView != null) {
                   if (QF.getEstSautReponse()) {// SI LE SAUT DEPEND DE LA REPONSE
                       if (!codeReponseRecyclerView.getCodeReponse().trim().equalsIgnoreCase("")) {
                           if (!codeReponseRecyclerView.getQSuivant().equalsIgnoreCase(""))
                               QF.setqSuivant(codeReponseRecyclerView.getQSuivant());
                       }
                   }

                   QF.SetButtonLabel(QuestionnaireBatimentActivity.this, codeReponseRecyclerView.getCodeReponse(), QF, btn_Suivant);
               }
               if(QF.TypeEvenement == Constant.ACTION_AFFICHER) {
                   message = getString(R.string.msg_Autorisation_BoutonContinuer);
                   ToastUtility.ToastMessage(QuestionnaireBatimentActivity.this, message, Constant.GravityCenter);
               }else {
                   message = questionReponseModel.toString();
                   ToastUtility.ToastMessage(QuestionnaireBatimentActivity.this, message, Constant.GravityCenter);
                   Suivant_Click();
               }
           }
       };
   }

    public RadioListAdapterKeyValue.OnItemClickListenerKeyValue getItemClickListenerKeyValue(){
        return new RadioListAdapterKeyValue.OnItemClickListenerKeyValue(){
            @Override
            public void onItemClick(KeyValueModel keyValueModel) {
                codeReponseKeyValueModel = keyValueModel;
                if(QF.TypeEvenement == Constant.ACTION_AFFICHER) {
                    message = getString(R.string.msg_Autorisation_BoutonContinuer);
                    ToastUtility.ToastMessage(QuestionnaireBatimentActivity.this, message, Constant.GravityCenter);
                }else {
                    message = keyValueModel.toString();
                    ToastUtility.ToastMessage(QuestionnaireBatimentActivity.this, message, Constant.GravityCenter);
                    Suivant_Click();
                }
            }
        };
    }

    private DisplayListAdapter.OnItemClickListener GetOnItemClickListener(){
        return new DisplayListAdapter.OnItemClickListener() {
            @Override
            public void onItemModuleClick( RowDataListModel row ) {
                LogementModel logementModel = (LogementModel)  row.getModel();
                //message = "NumeroOrdre: "+ logementModel.getQlin1NumeroOrdre();
                //ToastUtility.ToastMessage(QuestionnaireBatimentActivity.this, message, Constant.GravityCenter);
                goToForm(row, logementModel.getQlCategLogement(), Constant.ACTION_MOFIDIER);
            }
        };
    }
    //endregion

    //region "METHODES - Goto_Question Suivante / Precedent"
    public void Suivant_Click() {
        try {
            if(QF.TypeEvenement == Constant.ACTION_AFFICHER ){

            }

            isRetour = false;
            QF.context = QuestionnaireBatimentActivity.this;
            QF.CheckValueBefore( queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Batiment, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                    ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                    , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                    , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                    , et_Jour, sp_Mois2 , et_Annee
            );
            if (QF.getqSuivant().toString().equalsIgnoreCase(Constant.FIN)) {
                // Enregistrement des informations
                QF.SaveInfoDefinitivement(cuRecordMngr, true );
                ///*******************************************////
                //      Gestionnaire de statut du Batiment      //
                ///*******************************************////
               int statut = QF.SetStatutBatiment(queryRecordMngr, cuRecordMngr);
                // Calcul Statut du batiment en generale
                //finish();
                // On fait appel au Rapport de l'agent recenseur
                this.ShowRapport_RAR(statut);
                //if (QF.dialog != null) {
                //    QF.dialog.dismiss();
                //}
                //finish();
            } else {
                if (JumpToNextQuestion) {
                    qPrecedent = QF.getCodeQuestion();
                    Goto_QuestionSuivante(QF);
                }
            }

            /*InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            ToastUtility.LogCat("BATIMENT TypeQuestion: " + QF.getTypeQuestion());
            if( QF.getTypeQuestion() != Constant.TYPE_QUESTION_SAISIE_2 ){
                ToastUtility.LogCat("BATIMENT TypeQuestion: hideSoftInputFromInputMethod" );
                inputMethodManager.hideSoftInputFromInputMethod(getWindow().getDecorView().getRootView().getWindowToken(), 0);
            }else{
                ToastUtility.LogCat("BATIMENT TypeQuestion: toggleSoftInput - SHOW_FORCED" );
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }*/
        }catch (TextEmptyException  ex) {
            Tools.AlertDialogMsg(this, ex.getMessage());
            ToastUtility.LogCat("TextEmptyException: Suivant_Click() :" + ex.getMessage());
        }catch (ManagerException ex) {
            message = "Erreur :";
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ToastUtility.LogCat("ManagerException: Suivant_Click() :" + ex.getMessage());
        } catch (Exception ex) {
            message = "Erreur :";
            ToastUtility.LogCat("Exception: Suivant_Click() :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }//
    //
    public void Goto_QuestionSuivante(QuestionnaireFormulaireUtility QF){
            Goto_Question(QF.getqSuivant(), QF);
    }//
    //
    public void Goto_QuestionPrecedente(QuestionnaireFormulaireUtility QF){
        Goto_Question(qPrecedent, QF);
    }//
    //
    public void Goto_Question(String codeQuestion, QuestionnaireFormulaireUtility QF ) {
        try {
            //QF.setFormDataMngr(formDataMngr);
            QF.Goto_QuestionSuivante( formDataMngr, codeQuestion);
            QF.context = QuestionnaireBatimentActivity.this;
            GetFieldValuesQuestionInfo(QF);
        } catch (Exception ex){
            ToastUtility.LogCat("Exception-Goto_Question() : onClick : " + ex.getMessage() + " / toString:" + ex.toString());
            throw ex;
        }
    }//
    //
    private void Precedent_Click( QuestionnaireFormulaireUtility QF ){
        try{
            if (QF.getqPrecedent().toString().equalsIgnoreCase(Constant.DEBUT)) {
                //finish();
                ShowFormulaireBatiment();
            }else{
                Remove_QuestionEncours(QF, true);
                JumpToMenu = true;
                JumpToNextQuestion = true;
                //txt_Reponse.Enabled = true;
            }
            isRetour = true;
        }catch (Exception ex){
            Tools.AlertDialogMsg(this, ex.getMessage()+"\n"+ex.toString(), "E");
        }

    }

    private void Remove_QuestionEncours(QuestionnaireFormulaireUtility QF, Boolean IsGoBack){
        try{
            if (tempInfoQuestions != null){
                TempInfoQuestion temp = tempInfoQuestions.get(tempInfoQuestions.size() - 1);
                if (tempInfoQuestions.size() > 0){
                    qPrecedent = temp.getCodeQuestion().toString();
                }
            }
            if (IsGoBack){
                //QF.Goto_QuestionPrecedente(qPrecedent);
                Goto_QuestionPrecedente(QF);
            }

            if (tempInfoQuestions.size() > 0){
                TempInfoQuestion temp = tempInfoQuestions.get(tempInfoQuestions.size() - 1);
                SetReponseValue(QF, temp);
                String FieldName = temp.getNomChamps().toString();
                String FieldValue = temp.getCodeUniqueReponse().toString();

                //SetKey_Value(FieldName, "");

                tempInfoQuestions.remove(tempInfoQuestions.size() - 1);
            }
        }catch (Exception ex){
            throw ex;
        }
    }//

    private void  CreateMenuContext() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setIcon(R.mipmap.ic_launcher);

        String label_SaveAndQuit = getResources().getString(R.string.label_SaveAndQuit);
        String label_QuitNotSave = getResources().getString(R.string.label_QuitNotSave);

        ad.setItems(new String[]{label_SaveAndQuit, label_QuitNotSave},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:// label_SaveAndQuit
                                try {
                                    QF.CheckValueBefore(queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Batiment, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                                            ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                                            , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                                            , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                                            , et_Jour, sp_Mois2 , et_Annee );
                                    // Enregistrement des informations
                                    QF.SaveInfoDefinitivement(cuRecordMngr, false);

                                    ShowRapport_RAR(Constant.STATUT_PA_FIN_RANPLI_22);
                                    //finish();
                                } catch (TextEmptyException ex) {
                                    Tools.AlertDialogMsg(QuestionnaireBatimentActivity.this, ex.getMessage());
                                    ToastUtility.LogCat("TextEmptyException: CreateMenuContext() :" + ex.getMessage());
                                } catch (ManagerException ex) {
                                    ToastUtility.LogCat("Exception: CreateMenuContext() :" + ex.getMessage() + " / toString: " + ex.toString());
                                    ToastUtility.ToastMessage(QuestionnaireBatimentActivity.this, ex.getMessage());
                                } catch (Exception ex) {
                                    ToastUtility.LogCat("Exception: CreateMenuContext() :" + ex.getMessage() + " / toString: " + ex.toString());
                                    ex.printStackTrace();
                                }
                                break;
                            case 1:// label_QuitNotSave
                                ShowAlertDialogQuitter();
                                break;
                            default:
                                break;
                        }
                    }

                });//
        //
        ad.show();

    }//
    //endregion

    //region METHODES SET VALUE IN CONTROLS
    public void SetReponseValue(QuestionnaireFormulaireUtility QF, TempInfoQuestion tempInfoQuestion){
        try{
            if (tempInfoQuestion != null){
                QF.SetValueTemp_InViewControl(  formDataMngr, tempInfoQuestion.getCodeQuestion(), tempInfoQuestion.getCodeUniqueReponse(),
                        et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                        ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                        , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                        , recyclerViewReponse
                        , et_Jour, sp_Mois2 , et_Annee);
            }
        }catch (Exception ex){
            ToastUtility.LogCat(this, "ERROR - INSIDE SetReponseValue():" + ex.toString());
            ToastUtility.ToastMessage(this, "ERROR:" + ex.getMessage());
        }
    }//

    public void SetReponseValue_DataBase(QuestionnaireFormulaireUtility QF) {
        try{
            String codeReponse = "";
            if (QF.getDataBase() != null){
                //if (QF.getTbl_TableName() == Constant.FORMULAIRE_BATIMENT){
                    codeReponse = FieldMapperUtils.getFieldValue(QF.getDataBase(), QF.getNomChamps());
                    QF.SetValueTemp_InViewControl( formDataMngr,  QF.getCodeQuestion(), codeReponse,
                            et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                            ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                            , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                            , recyclerViewReponse
                            , et_Jour, sp_Mois2 , et_Annee );
                //}
            }else{
                if (QF.getDataBase() == null){
                    et_Jour.setText(Constant.STRING_VIDE);
                    et_Annee.setText(Constant.STRING_VIDE);
                    et_Reponse.setText(Constant.STRING_VIDE);
                    et_Gason.setText(Constant.STRING_VIDE);
                    et_Fi.setText(Constant.STRING_VIDE);
                    et_ApareyRadyo.setText(Constant.STRING_VIDE);
                    et_Televizyon.setText(Constant.STRING_VIDE);
                    et_FrijideFrize.setText(Constant.STRING_VIDE);
                    et_FouElektrikFouAkGaz.setText(Constant.STRING_VIDE);
                    et_OdinatePCLaptopTabletNimerik.setText(Constant.STRING_VIDE);
                    et_BisikletMotosiklet.setText(Constant.STRING_VIDE);
                    et_VwatiMachin.setText(Constant.STRING_VIDE);
                    et_KannotBato.setText(Constant.STRING_VIDE);
                    et_InvetePanoSoleJeneratrisDelko.setText(Constant.STRING_VIDE);
                    et_MiletChwalBourik.setText(Constant.STRING_VIDE);
                    et_BefVach.setText(Constant.STRING_VIDE);
                    et_KochonKabrit.setText(Constant.STRING_VIDE);
                    et_BetVolayPoulKok.setText(Constant.STRING_VIDE);
                }

                if ( QF.getTbl_TableName() == Constant.FORMULAIRE_INDIVIDUS ){
                    if (QF.getNomChamps().equalsIgnoreCase(Constant.Qp5bAge) ){
                        IndividuModel Ind = (IndividuModel)QF.getData();
                        //if (Ind.getQp5AnneeNaissance() != 999){
                            //int Age = DateTime.now().year() - Ind.getQp5AnneeNaissance();
                            //txt_Reponse.Text = Age.ToString();
                        //}
                    }
                }
            }
            //getOnBindViewHolderListener();
        }catch (Exception ex){
            ToastUtility.LogCat(this, ex);
            Tools.AlertDialogMsg(this, "ERROR:" + ex.toString(), "E");
        }
    }//
    //endregion

    //region "Show Alert Dialog Quitter"
    private void ShowAlertDialogQuitter() {
        message = getString(R.string.msg_QuitterQuestionnaire);
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        String titleMsg = getResources().getString(R.string.msg_title_succesInformation);

        aBuilder.setTitle(titleMsg);
        aBuilder.setMessage(message);
        aBuilder.setCancelable(false);
        aBuilder.setPositiveButton(getString(R.string.label_WI) + ", " + getString(R.string.label_Quit),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }
        );
        //
        aBuilder.setNegativeButton(getString(R.string.label_Non),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        //finish();
                    }
                }

        );
        aBuilder.show();
    }
    //endregion

    //region PopUp Add Individus
    public void ShowPopUp_AddIndividus(){
        try{
            /*if ( QF.getTbl_TableName() == Constant.FORMULAIRE_MENAGE ) {
                if (QF.getNomChamps().equalsIgnoreCase(Constant.Qm11CallFormListeIndividu)) {
                    // Save Formulaire D'abord
                    QF.SaveInfoDefinitivement(cuRecordMngr, false);
                    //QF.queryRecordMngr = queryRecordMngr;
                    long Nbre_Individu_DejaSave = queryRecordMngr.countIndByMenage(QF.getMenageModel().getMenageId());
                    int nbrInd_NoOrdre = ((int) Nbre_Individu_DejaSave + 1);
                    int Nbre_TotalIndividu = QF.getMenageModel().getQm11TotalIndividuVivant();

                    if ( Nbre_TotalIndividu <= 0 ){
                        message = "Ou pat di gen moun kap viv nan Menaj sa.";
                        message += "\n ou pap kapab ajoute moun ? \n Tounen nan menaj la pouw ka di konbyen moun ki genyen !";
                        Tools.AlertDialogMsg(this, message, "E");
                        ToastUtility.ToastMessage(this, message);
                    }else{
                        // On Affiche le popUp pour la saisie des individus
                        dialog = new Dialog(this);
                        dialog.setContentView(R.layout.individus_form);
                        dialog.setCancelable(false);
                        LinearLayout LL_ListeView = (LinearLayout) dialog.findViewById(R.id.LL_ListeView);
                        LinearLayout LL_FormulaireAdd = (LinearLayout) dialog.findViewById(R.id.LL_FormulaireAdd);
                        tv_NumeroIndividu = (TextView) dialog.findViewById(R.id.tv_NumeroIndividu);
                        TextView tv_GrandTitreInd = (TextView) dialog.findViewById(R.id.tv_GrandTitre);

                        message = "" + "<b>Menaj " + QF.getMenageModel().getQm1NoOrdre() + "</b>";
                        tv_GrandTitreInd.setText(Html.fromHtml("" + message));

                        if (Nbre_TotalIndividu == Nbre_Individu_DejaSave) {
                            // On lui permet de voir la liste des personnes deja enregistrer.
                            dialog.setTitle("Moun ki nan menaj sa [" + Nbre_TotalIndividu + "]");
                            LL_FormulaireAdd.setVisibility(View.GONE);
                            LL_ListeView.setVisibility(View.VISIBLE);

                            tv_NumeroIndividu.setVisibility(View.GONE);

                            //initialize the recycle view
                            recyclerView = (RecyclerView)  dialog.findViewById(R.id.recycler_view);

                            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
                            // Attach the layout manager to the recycler view
                            gridLayoutManager.setAutoMeasureEnabled(true);
                            recyclerView.setLayoutManager(gridLayoutManager);
                            recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
                            //recyclerView.setItemAnimator(new SlideInUpAnimator());
                            recyclerView.setHasFixedSize(true);
                            //  initialize the adapter
                            mAdapter = new DisplayListAdapter(this, targetList, Constant.LIST_MODULE_INDIVIDU_LIST_ONLY);
                            mAdapter.setOnMenuItemClickListener(null);
                            //inject the adapter into the recycle view
                            recyclerView.setAdapter(mAdapter);

                            targetList = queryRecordMngr.searchListIndividu_ByMenage(QF.getMenageModel().getMenageId());
                            mAdapter.setFilter(targetList);
                            //new AsynDisplayDataList_IndividuTask().execute();
                            *//*if (dialog != null) {
                                dialog.dismiss();
                            }
                            Suivant_Click();*//*
                        }else{
                            dialog.setTitle("Ajoute Moun nan menaj sa [" + nbrInd_NoOrdre + " / " + Nbre_TotalIndividu + "]");
                            LL_FormulaireAdd.setVisibility(View.VISIBLE);
                            LL_ListeView.setVisibility(View.GONE);

                            et_02NonIndividu = (EditText) dialog.findViewById(R.id.et_02NonIndividu);
                            et_03SiyatiIndividu = (EditText) dialog.findViewById(R.id.et_03SiyatiIndividu);
                            sp_04Sexe = (Spinner) dialog.findViewById(R.id.sp_04Sexe);
                            QF.Load_Sexe(this, sp_04Sexe);

                            sp_09LienDeParente = (Spinner) dialog.findViewById(R.id.sp_09LienDeParente);
                            QF.Load_Relation(this, sp_09LienDeParente);

                            sp_JourIndividu = (Spinner) dialog.findViewById(R.id.sp_JourIndividu);
                            QF.Load_Jour(this, sp_JourIndividu);

                            sp_MoisIndividu = (Spinner) dialog.findViewById(R.id.sp_MoisIndividu);
                            QF.Load_Mois(this, sp_MoisIndividu);

                            sp_AnneeIndividu = (Spinner) dialog.findViewById(R.id.sp_AnneeIndividu);
                            QF.Load_Annee(this, sp_AnneeIndividu);

                            sp_JourIndividu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TEST POUR LE CHARGEMENT DES COMMUNES
                                    QF.CheckAgeDialog(sp_JourIndividu, sp_MoisIndividu, sp_AnneeIndividu, sp_AgeIndividu);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                            sp_MoisIndividu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TEST POUR LE CHARGEMENT DES COMMUNES
                                    QF.CheckAgeDialog(sp_JourIndividu, sp_MoisIndividu, sp_AnneeIndividu, sp_AgeIndividu);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                            sp_AnneeIndividu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    // TEST POUR LE CHARGEMENT DES COMMUNES
                                    QF.CheckAgeDialog(sp_JourIndividu, sp_MoisIndividu, sp_AnneeIndividu, sp_AgeIndividu);
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                            sp_AgeIndividu = (Spinner) dialog.findViewById(R.id.sp_AgeIndividu);
                            QF.Load_Age(this, sp_AgeIndividu);

                            tv_NumeroIndividu.setText("Moun " + nbrInd_NoOrdre);

                            // On recherche les Individus par numero d'ordre et par IdMenage
                            ID_INDIVIDU = 0;
                            QF.Get_Set_Individu_IfExist(queryRecordMngr, dialog, ID_INDIVIDU, nbrInd_NoOrdre, Nbre_TotalIndividu, tv_NumeroIndividu, et_02NonIndividu, et_03SiyatiIndividu, sp_04Sexe, sp_09LienDeParente, sp_05HabiteDansMenage);
                        }

                        Button btnQuitter = (Button) dialog.findViewById(R.id.btnQuitter);
                        btnQuitter.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                if (dialog != null) {
                                    dialog.dismiss();
                                }
                                // On va a la question suivante
                                Precedent_Click(QF);
                            }
                        });
                        final int _NoOrdre = nbrInd_NoOrdre;

                        // Buttons
                        Button btnContinuer = (Button) dialog.findViewById(R.id.btnContinuer);
                        btnContinuer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                QF.context = QuestionnaireBatimentActivity.this;
                                try {
                                    int Nbre_TotalIndividu_Declarer = QF.getMenageModel().getQm11TotalIndividuVivant();
                                    long Nbre_Individu_DejaSave = queryRecordMngr.countIndByMenage(QF.getMenageModel().getMenageId());
                                    int NoOrdreIndividu = ((int) Nbre_Individu_DejaSave + 1);

                                    if( Nbre_Individu_DejaSave < Nbre_TotalIndividu_Declarer ) {
                                        IndividuModel indModel = QF.CheckIndividu_ValueBefore_AndSave( queryRecordMngr, cuRecordMngr, ID_INDIVIDU, NoOrdreIndividu, et_02NonIndividu, et_03SiyatiIndividu, sp_04Sexe, sp_09LienDeParente, sp_JourIndividu, sp_MoisIndividu, sp_AnneeIndividu, sp_AgeIndividu);
                                        Nbre_Individu_DejaSave = queryRecordMngr.countIndByMenage(QF.getMenageModel().getMenageId());
                                        NoOrdreIndividu = ((int) Nbre_Individu_DejaSave + 1);
                                    }

                                    if( Nbre_Individu_DejaSave < Nbre_TotalIndividu_Declarer ){
                                        et_02NonIndividu.setText(null);
                                        et_03SiyatiIndividu.setText(null);
                                        sp_04Sexe.setSelection(0);
                                        sp_09LienDeParente.setSelection(0);
                                        sp_JourIndividu.setSelection(0);
                                        sp_MoisIndividu.setSelection(0);
                                        sp_AnneeIndividu.setSelection(0);
                                        sp_AgeIndividu.setSelection(0);
                                        et_02NonIndividu.requestFocus();
                                        ID_INDIVIDU = 0;

                                        // On recherche les Individus par numero d'ordre et par IdMenage
                                        QF.Get_Set_Individu_IfExist(queryRecordMngr,  dialog, ID_INDIVIDU, NoOrdreIndividu, Nbre_TotalIndividu_Declarer, tv_NumeroIndividu, et_02NonIndividu, et_03SiyatiIndividu, sp_04Sexe, sp_09LienDeParente);

                                    }else if( Nbre_Individu_DejaSave == Nbre_TotalIndividu_Declarer ){
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                        Suivant_Click();
                                    }
                                } catch (TextEmptyException ex) {
                                    Tools.AlertDialogMsg(QuestionnaireBatimentActivity.this, ex.getMessage());
                                    ToastUtility.LogCat("TextEmptyException: btnQuitter.setOnClickListener() :" + ex.getMessage()+" \n "+ ex.toString());
                                } catch (Exception ex) {
                                    ToastUtility.ToastMessage(QuestionnaireBatimentActivity.this, ex.getMessage()+" \n "+ ex.toString());
                                    ToastUtility.LogCat("Exception: btnQuitter.setOnClickListener() :" + ex.getMessage()+" \n "+ ex.toString());
                                }
                            }
                        });
                        dialog.show();
                    }
                }
            }*/
        //}catch (TextEmptyException  ex) {
        //    Tools.AlertDialogMsg(this, ex.getMessage());
        //    ToastUtility.LogCat("TextEmptyException: Suivant_Click :" + ex.getMessage());
        //}catch (ManagerException ex) {
        //    message = "Erreur au niveau du Manager";
        //    Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
        //    ToastUtility.LogCat("ManagerException: Suivant_Click :" + ex.getMessage());
        } catch (Exception ex) {
            message = "Erreur au niveau de la sauvegarde";
            ToastUtility.LogCat("Exception: Suivant_Click :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }
    //endregion

    //region PopUp Add Logement
    public void ShowPopUp_AddLogement(){
        try{
            if ( QF.getTbl_TableName() == Constant.FORMULAIRE_BATIMENT ) {
                if (QF.getNomChamps().equalsIgnoreCase(Constant.CallFormulaireLogementCollectif)) {
                    // ON enregistre d'abord le batiment
                    QF.SaveInfoDefinitivement(cuRecordMngr, false);
                    Show_Form_Add_or_List_Logement(Constant.LOJ_KOLEKTIF);
                }else if (QF.getNomChamps().equalsIgnoreCase(Constant.CallFormulaireLogementEndividyel)){
                    // ON enregistre d'abord le batiment
                    QF.SaveInfoDefinitivement(cuRecordMngr, false);
                    Show_Form_Add_or_List_Logement(Constant.LOJ_ENDIVIDYEL);
                }
            }
        }catch (TextEmptyException  ex) {
            Tools.AlertDialogMsg(this, ex.getMessage());
            ToastUtility.LogCat("TextEmptyException: ShowPopUp_AddLogement :" + ex.getMessage());
        }catch (ManagerException ex) {
            message = "Erreur:";
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ToastUtility.LogCat("ManagerException: ShowPopUp_AddLogement :" + ex.toString());
        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: ShowPopUp_AddLogement :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.toString());
            ex.printStackTrace();
        }
    }

    private void Show_Form_Add_or_List_Logement(int typeLogement) {
        try{
            if(typeLogement == Constant.LOJ_ENDIVIDYEL){
                int NbreLogeEndividyel_Declarer = 0, NbrTotalLogement_NotFinish=0, NbrTotalLogement_Finish=0;
                List<RowDataListModel> ListAll_Logement_NotFinish=null, ListAll_Logement_Finish=null;
                if( QF.getBatimentModel().getQb4NbreLogeIndividuel() != null ) {
                    NbreLogeEndividyel_Declarer = QF.getBatimentModel().getQb4NbreLogeIndividuel();
                }
                // On verifie s'il existe de logement Individuel
                if( NbreLogeEndividyel_Declarer > 0 ) {
                    long nbreLogement_DejaSave = queryRecordMngr.countLogByBatAndType(QF.getBatimentModel().getBatimentId(), Constant.LOJ_ENDIVIDYEL );
                    // On recherche le nombre de logement non fini
                    ListAll_Logement_NotFinish = queryRecordMngr.SearchAll_Logement_NotFinish(QF.getBatimentModel().getBatimentId(), Constant.LOJ_ENDIVIDYEL );
                    if( ListAll_Logement_NotFinish!=null && ListAll_Logement_NotFinish.size()>0) {
                        NbrTotalLogement_NotFinish = ListAll_Logement_NotFinish.size();
                    }
                    // On recherche le nombre de logement fini
                    ListAll_Logement_Finish = queryRecordMngr.SearchAll_Logement_Finish(QF.getBatimentModel().getBatimentId(), Constant.LOJ_ENDIVIDYEL );
                    if( ListAll_Logement_Finish!=null && ListAll_Logement_Finish.size()>0) {
                        NbrTotalLogement_Finish = ListAll_Logement_Finish.size();
                    }

                    int nbr_ou_NoOrdre = ((int) nbreLogement_DejaSave + 1);

                    if ( NbreLogeEndividyel_Declarer == nbreLogement_DejaSave ) {
                        // On lui permet de voir la liste des personnes deja enregistrer.
                        if( CounterForLogeEndividyel >= NbreLogeEndividyel_Declarer  ) {
                            if( NbrTotalLogement_Finish == NbreLogeEndividyel_Declarer ) {
                                // Ici on doit Afficher le formulaire du premier logement qui n'est pas encore fini
                                // Et qui est soit remplit Totalement ou pas
                                ShowListInformationsLogement( Constant.LOJ_ENDIVIDYEL, (int) nbreLogement_DejaSave);
                            }else{
                                // Bay moun nan yon list pou li chwazi ak ki lojman ke li vle komanse
                                // Si gen plis ke 2 lojman ki pako fini.
                                Show_ListLogement_NotFinish(ListAll_Logement_NotFinish, Constant.LOJ_ENDIVIDYEL,  (int) nbreLogement_DejaSave);
                            }
                        }else{
                            // Bay moun nan yon list pou li chwazi ak ki lojman ke li vle komanse
                            // Si gen plis ke 2 lojman ki pako fini.
                            //Show_ListLogement_NotFinish(ListAll_Logement_NotFinish, Constant.LOJ_ENDIVIDYEL,  (int) nbreLogement_DejaSave);
                            if( NbrTotalLogement_Finish == NbreLogeEndividyel_Declarer ) {
                                // Ici on doit Afficher le formulaire du premier logement qui n'est pas encore fini
                                // Et qui est soit remplit Totalement ou pas
                                ShowListInformationsLogement( Constant.LOJ_ENDIVIDYEL, (int) nbreLogement_DejaSave);
                            }else{
                                // Bay moun nan yon list pou li chwazi ak ki lojman ke li vle komanse
                                // Si gen plis ke 2 lojman ki pako fini.
                                Show_ListLogement_NotFinish(ListAll_Logement_NotFinish, Constant.LOJ_ENDIVIDYEL,  (int) nbreLogement_DejaSave);
                            }
                        }
                    }else{
                        if( NbrTotalLogement_NotFinish >=1 ){
                            // Bay moun nan yon list pou li chwazi ak ki lojman ke li vle komanse
                            // Si gen plis ke 2 lojman ki pako fini.
                            Show_ListLogement_NotFinish(ListAll_Logement_NotFinish, Constant.LOJ_ENDIVIDYEL,  (int) nbreLogement_DejaSave);
                        }else{

                            AddNewLogement(Constant.LOJ_ENDIVIDYEL, nbr_ou_NoOrdre, NbreLogeEndividyel_Declarer);
                        }
                    }
                }
            }
        }catch (ManagerException ex) {
            message = "Erreur:";
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ToastUtility.LogCat("ManagerException: Show_Form_Add_or_List_Logement :" + ex.toString());
        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: Show_Form_Add_or_List_Logement :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.toString());
            ex.printStackTrace();
        }

    }

    private void AddNewLogement(int CategLogement_Col_Ou_End , int nbr_ou_NoOrdre, int NbreLog_Declarer) {
        try{
            ToastUtility.ToastMessage(this, "Kòmanse Pran Enfomasyon sou LOJMAN ENDIVIDYèl " + nbr_ou_NoOrdre + " an", Constant.GravityCenter);

            logementM_OBJ = new LogementModel();
            LogementModel.cuRecordMngr = cuRecordMngr;
            LogementModel.formDataMngr = formDataMngr;
            logementM_OBJ.setSdeId(QF.getBatimentModel().getSdeId());
            logementM_OBJ.setQlCategLogement((short) CategLogement_Col_Ou_End);
            logementM_OBJ.setQlin1NumeroOrdre((short)  nbr_ou_NoOrdre);
            logementM_OBJ.setDateDebutCollecte(Tools.getDateString_MMddyyyy_HHmmss_a()) ;
            QuestionnaireBatimentActivity.CounterForLogeEndividyel = nbr_ou_NoOrdre;

            this.SetFieldLogement(logementM_OBJ, NbreLog_Declarer, CategLogement_Col_Ou_End, Constant.ACTION_NOUVEAU);
        }catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: AddNewLogement :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.toString());
            ex.printStackTrace();
        }
    }

    private void SetFieldLogement(LogementModel logM, int nbreTotalLoge, int catLogement, int actions) {
        try{
            String dateDebutCollect = Tools.getDateString_MMddyyyy_HHmmss_a();

            DateTime getDateDebutCollecte;
            logementM_OBJ = new LogementModel();
            LogementModel.cuRecordMngr = cuRecordMngr;
            LogementModel.formDataMngr = formDataMngr;
            logementM_OBJ = logM;
            // Objet Batimtnent
            logementM_OBJ.setBatimentId(QF.getBatimentModel().getBatimentId());
            logementM_OBJ.setObjBatiment(QF.getBatimentModel());

            String typeLogementStr =" LOJMAN ENDIVIDYèl ";
            int FORMULAIRE_LOGEMENT = Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL;

            if( catLogement == Constant.LOJ_KOLEKTIF ){
                typeLogementStr = " LOJMAN KOLEKTIF ";
                FORMULAIRE_LOGEMENT = Constant.FORMULAIRE_LOGEMENT_COLLECTIF;
            }
            String actionStr = "NOUVO ";
            if ( actions == Constant.ACTION_MOFIDIER ){
                actionStr = " MODIFYE ";
            }

            ModuleModel moduleModel = formDataMngr.checkModule( FORMULAIRE_LOGEMENT, Constant.ACTIF);
            QFD = new QuestionnaireFormulaireUtility( moduleModel, logementM_OBJ, FORMULAIRE_LOGEMENT, formDataMngr);

            if(logementM_OBJ!=null && logementM_OBJ.getDateDebutCollecte()!=null && !logementM_OBJ.getDateDebutCollecte().equalsIgnoreCase("") ) {
                QFD.setDateDebutCollecte(logementM_OBJ.getDateDebutCollecte());
            }else{
                QFD.setDateDebutCollecte(dateDebutCollect);
            }

            Intent intent = new Intent(this, QuestionnaireLogementActivity.class);
            intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QFD);
            intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, actionStr + typeLogementStr + logementM_OBJ.getQlin1NumeroOrdre() +"/" + nbreTotalLoge);
            intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, "Batiman " + QF.getBatimentModel().getBatimentId() + " | EPC:" + QF.getBatimentModel().getQepc());
            startActivity(intent);

        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat(this, "Exception: SetFieldLogement : ", ex);
            Tools.AlertDialogMsg(this, message +"\n"+ ex.toString());
            ex.printStackTrace();
        }
    }

    //endregion

    //region "OPTIONS METHODES"
    public void goToForm(RowDataListModel row, int CategLogement, int typeEvenement){
        try {
            ModuleModel moduleModel = null;
            QuestionnaireFormulaireUtility QF = null;
            short numOrdre =1;
            //String dateString = Tools.getDateString_MMddyyyy_HHmmss_a();
           if( CategLogement == Constant.LOJ_KOLEKTIF ){
                moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_LOGEMENT_COLLECTIF, Constant.ACTIF);
                if( row != null && moduleModel != null ){
                    LogementModel logementModel = (LogementModel) row.getModel();
                    BatimentModel batimentModel = queryRecordMngr.getBatById(logementModel.getBatimentId());
                    logementModel.setObjBatiment(batimentModel);
                    numOrdre += (int)logementModel.getQlin1NumeroOrdre();
                    QuestionnaireBatimentActivity.CounterForLogeCollectif = numOrdre;//

                    QF = new QuestionnaireFormulaireUtility( moduleModel, logementModel, Constant.FORMULAIRE_LOGEMENT_COLLECTIF, formDataMngr);
                    QF.TypeEvenement = typeEvenement;
                    if(logementModel!=null && logementModel.getDateDebutCollecte()!=null && !logementModel.getDateDebutCollecte().equalsIgnoreCase("") ) {
                        QF.setDateDebutCollecte(logementModel.getDateDebutCollecte());
                    }else{
                        QF.setDateDebutCollecte(dateString);
                    }
                    intent = new Intent(this, QuestionnaireLogementActivity.class);
                    intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QF);
                    intent.putExtra(Constant.PARAM_NUMERO_ORDRE_LOG_INDIVIDUEL, "" + numOrdre );
                    intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, "MODIFYE LOJMAN KOLEKTIF " + logementModel.getQlin1NumeroOrdre() );
                    intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, "Batiman " + batimentModel.getBatimentId() + " | REC: " +batimentModel.getQrec());
                    startActivity(intent);
                }
            }else if( CategLogement == Constant.LOJ_ENDIVIDYEL ){
                moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL, Constant.ACTIF);
                if( row != null && moduleModel != null ){
                    LogementModel logementModel = (LogementModel) row.getModel();
                    BatimentModel batimentModel = queryRecordMngr.getBatById(logementModel.getBatimentId());
                    logementModel.setObjBatiment(batimentModel);
                    numOrdre += (int)logementModel.getQlin1NumeroOrdre();
                    QuestionnaireBatimentActivity.CounterForLogeEndividyel = numOrdre;

                    QF = new QuestionnaireFormulaireUtility( moduleModel, logementModel, Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL, formDataMngr);
                    QF.TypeEvenement = typeEvenement;
                    if(logementModel!=null && logementModel.getDateDebutCollecte()!=null && !logementModel.getDateDebutCollecte().equalsIgnoreCase("") ) {
                        QF.setDateDebutCollecte(logementModel.getDateDebutCollecte());
                    }else{
                        QF.setDateDebutCollecte(dateString);
                    }
                    intent = new Intent(this, QuestionnaireLogementActivity.class);
                    intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QF);
                    intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, "MODIFYE LOJMAN ENDIVIDYEL " + logementModel.getQlin1NumeroOrdre() );
                    intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, "Batiman " + batimentModel.getBatimentId() + " | REC: " +batimentModel.getQrec() );
                    startActivity(intent);
                }
            }

            if (dialogLogNotFinish != null) {
                dialogLogNotFinish.dismiss();
            }
        }catch (ManagerException e) {
            ToastUtility.LogCat(this, "ManagerException:goToForm() - getMessage:" + e.getMessage() + " / toString:" + e.toString());
            e.printStackTrace();
        }catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception:goToForm() - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }
    //endregion

    //region LISTE INFOS
    public void ShowListInformationsLogement(int typeLogement, int Nbre_TotalElement){
        try{
            infoUser = Tools.SharedPreferences(this);
            if (infoUser != null && infoUser.getProfileId() != null) {
                profilId = infoUser.getProfileId();
            }
            dialogIL = new Dialog(this);
            dialogIL.setContentView(R.layout.individus_form);
            dialogIL.setCancelable(false);
            LinearLayout LL_ListeView = (LinearLayout) dialogIL.findViewById(R.id.LL_ListeView);
            LinearLayout LL_FormulaireAdd = (LinearLayout) dialogIL.findViewById(R.id.LL_FormulaireAdd);
            LL_FormulaireAdd.setVisibility(View.VISIBLE);
            LL_ListeView.setVisibility(View.GONE);

            tv_NumeroIndividu = (TextView) dialogIL.findViewById(R.id.tv_NumeroIndividu);
            TextView tv_GrandTitreInd = (TextView) dialogIL.findViewById(R.id.tv_grandtitre);

            // On lui permet de voir la liste des personnes deja enregistrer.
            LL_FormulaireAdd.setVisibility(View.GONE);
            LL_ListeView.setVisibility(View.VISIBLE);
            tv_NumeroIndividu.setVisibility(View.GONE);

            //initialize the recycle view
            recyclerView = (RecyclerView)  dialogIL.findViewById(R.id.recycler_view);

            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            // Attach the layout manager to the recycler view
            gridLayoutManager.setAutoMeasureEnabled(true);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
            recyclerView.setHasFixedSize(true);
            //  initialize the adapter
            DisplayListAdapter mAdapter = new DisplayListAdapter(this, targetList, Constant.LIST_MODULE_LOGEMENT_LIST_ONLY);
            mAdapter.setOnMenuItemClickListener(null);
            //inject the adapter into the recycle view
            recyclerView.setAdapter(mAdapter);
            String lojmantypeSTR = "";
            if( typeLogement == Constant.LOJ_ENDIVIDYEL ){
                lojmantypeSTR = "List Lojman Endividyèl pou Batiman "+ QF.getBatimentModel().getBatimentId();
                tv_GrandTitreInd.setText(Html.fromHtml("Ou antre <b>[" + Nbre_TotalElement +  "/"+  QF.getBatimentModel().getQb4NbreLogeIndividuel() +"] Lojman Endividyèl</b> pou <b>Batiman "+ QF.getBatimentModel().getBatimentId() +"</b> sa" ));
                CounterForLogeEndividyel=0;
            }

            dialogIL.setTitle( lojmantypeSTR);
            targetList = queryRecordMngr.searchListLogement_ByBatiment_ByCategLogement(QF.getBatimentModel().getBatimentId(), typeLogement);
            if( targetList!=null ) {
                mAdapter.setFilter(targetList);
            }
            //EVENTS
            // Buttons btnQuitter
            Button btnQuitter = (Button) dialogIL.findViewById(R.id.btnQuitter);
            btnQuitter.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (dialogIL != null) {
                        dialogIL.dismiss();
                    }
                    // On va a la question suivante
                    Precedent_Click(QF);
                }
            });
            // Buttons btnContinuer
            Button btnContinuer = (Button) dialogIL.findViewById(R.id.btnContinuer);
            btnContinuer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( dialogIL != null ) {
                        dialogIL.dismiss();
                    }
                    Suivant_Click();
                }
            });
            dialogIL.show();
        } catch (Exception ex) {
            message = "Erreur :";
            ToastUtility.LogCat("Exception: ShowListInformationsLogement :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }


    //region LISTE LOGEMENT NOT FINISH
    public void Show_ListLogement_NotFinish(List<RowDataListModel> ListAll_Logement_NotFinish, final int typeLogement, int Nbre_TotalElement){
        try{
            infoUser = Tools.SharedPreferences(this);
            if (infoUser != null && infoUser.getProfileId() != null) {
                profilId = infoUser.getProfileId();
            }
            dialogLogNotFinish = new Dialog(this);
            dialogLogNotFinish.setContentView(R.layout.individus_form);
            dialogLogNotFinish.setCancelable(false);
            ScrollView scrollView2 = (ScrollView) dialogLogNotFinish.findViewById(R.id.scrollView2);
            LinearLayout LL_ListeView = (LinearLayout) dialogLogNotFinish.findViewById(R.id.LL_ListeView);
            LinearLayout LL_FormulaireAdd = (LinearLayout) dialogLogNotFinish.findViewById(R.id.LL_FormulaireAdd);
            LL_FormulaireAdd.setVisibility(View.VISIBLE);
            LL_ListeView.setVisibility(View.GONE);

            tv_NumeroIndividu = (TextView) dialogLogNotFinish.findViewById(R.id.tv_NumeroIndividu);
            TextView tv_GrandTitreInd = (TextView) dialogLogNotFinish.findViewById(R.id.tv_grandtitre);

            // On lui permet de voir la liste des personnes deja enregistrer.
            LL_FormulaireAdd.setVisibility(View.GONE);
            LL_ListeView.setVisibility(View.VISIBLE);
            tv_NumeroIndividu.setVisibility(View.GONE);

            //initialize the recycle view
            recyclerView = (RecyclerView)  dialogLogNotFinish.findViewById(R.id.recycler_view);

            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            // Attach the layout manager to the recycler view
            gridLayoutManager.setAutoMeasureEnabled(true);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
            recyclerView.setHasFixedSize(true);

            // initialize the adapter
            DisplayListAdapter mAdapter = new DisplayListAdapter(this, ListAll_Logement_NotFinish, Constant.LIST_MODULE_LOGEMENT_NOT_FINISH_LIST_ONLY);
            mAdapter.setOnItemClickListener(GetOnItemClickListener());
            //inject the adapter into the recycle view
            recyclerView.setAdapter(mAdapter);

            final int Nbre_TotalElement_Declare =  QF.getBatimentModel().getQb4NbreLogeIndividuel();
            int Nbre_Total_NotFinish =0, nbr_ou_NoOrdreSuivant=1;
            String lojmantypeSTR = "";

            //targetList = queryRecordMngr.searchListLogement_ByBatiment_ByCategLogement(QF.getBatimentModel().getBatimentId(), typeLogement);
            if( ListAll_Logement_NotFinish!=null ) {
                Nbre_Total_NotFinish=ListAll_Logement_NotFinish.size();
                mAdapter.setFilter(ListAll_Logement_NotFinish);
            }

            if( typeLogement == Constant.LOJ_ENDIVIDYEL ){
                lojmantypeSTR = "Lojman Endividyèl Ki poko fini [" + Nbre_Total_NotFinish +  "/"+  Nbre_TotalElement_Declare +"].";
                message ="" + lojmantypeSTR;
                message +="\nChwazi lojman kew vle kòmanse a!";

                tv_GrandTitreInd.setText(message);
                CounterForLogeEndividyel=0;
            }
            dialogLogNotFinish.setTitle( lojmantypeSTR);
            nbr_ou_NoOrdreSuivant += Nbre_TotalElement;

            //EVENTS
            //region Buttons btnQuitter
            Button btnQuitter = (Button) dialogLogNotFinish.findViewById(R.id.btnQuitter);
            btnQuitter.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (dialogLogNotFinish != null) {
                        dialogLogNotFinish.dismiss();
                    }
                    // On va a la question suivante
                    Precedent_Click(QF);
                }
            });//endregion

            //region Buttons btnContinuer
            Button btnContinuer = (Button) dialogLogNotFinish.findViewById(R.id.btnContinuer);
            final int finalNbr_ou_NoOrdreSuivant = nbr_ou_NoOrdreSuivant;
            btnContinuer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogLogNotFinish != null) {
                        dialogLogNotFinish.dismiss();
                    }
                    //Suivant_Click();

                    AddNewLogement(typeLogement, finalNbr_ou_NoOrdreSuivant, Nbre_TotalElement_Declare);
                }
            });//endregion

            btnContinuer.setVisibility(View.GONE);
            if( Nbre_TotalElement_Declare > Nbre_TotalElement ){
                btnContinuer.setVisibility(View.VISIBLE);
                btnContinuer.setText("Kòmanse ak #"+ (Nbre_TotalElement+1));
            }

            dialogLogNotFinish.show();
        } catch (Exception ex) {
            message = "Erreur :";
            ToastUtility.LogCat("Exception: ShowListInformationsLogement :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }
    //endregion
    //endregion

    //region PopUp ShowRapport_RAR
    public void ShowRapport_RAR( int statut) {
        try {
            if( QF.TypeEvenement != Constant.ACTION_AFFICHER ) {
                dialog = new Dialog(this);
                dialog.setContentView(R.layout.rapport_rar);
                dialog.setCancelable(false);
                ScrollView scrollView2 = (ScrollView) dialog.findViewById(R.id.scrollView2);

                //TextView tv_GrandTitre2 = (TextView) dialog.findViewById(R.id.tv_GrandTitre);
                //tv_GrandTitre2.setVisibility(View.GONE);

                sp_Rezon = (Spinner) dialog.findViewById(R.id.sp_Rezon);

                LL_LotRezon = (LinearLayout) dialog.findViewById(R.id.LL_LotRezon);
                tv_LotRezon = (TextView) dialog.findViewById(R.id.tv_LotRezon);
                et_LotRezon = (EditText) dialog.findViewById(R.id.et_LotRezon);

                LinearLayout_messageChangerdeModule = (LinearLayout) dialog.findViewById(R.id.LinearLayout_messageChangerdeModule);
                LinearLayout_messageChangerdeModule.setVisibility(View.GONE);
                tv_messageChangerdeModule = (TextView) dialog.findViewById(R.id.tv_messageChangerdeModule);
                //if( finishAfter ){
                //    LinearLayout_messageChangerdeModule.setVisibility(View.VISIBLE);
                //}

                dialog.setTitle("" + this.getString(R.string.Rappot_Agent_Resenceur));

                TextView tv_GrandTitreRap = (TextView) dialog.findViewById(R.id.tv_grandtitre);
                message = "" + "<b>Rapò sou Batiman </b>";
                if (QF.getBatimentModel().getBatimentId() != null && QF.getBatimentModel().getQrec() != null) {
                    message += "" + "<b> " + QF.getBatimentModel().getBatimentId() + " </b>";
                }
                tv_GrandTitreRap.setText(Html.fromHtml("" + message));

                //region sp_Rezon
                QF.Load_RaisonRAR(this, QF, sp_Rezon, statut);

                sp_Rezon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            // TEST POUR LE CHARGEMENT DES COMMUNES
                            KeyValueModel keyValueRezon = null;
                            if (parent.getId() == R.id.sp_Rezon) {
                                keyValueRezon = ((KeyValueModel) sp_Rezon.getSelectedItem());
                            }
                            LL_LotRezon.setVisibility(View.GONE);
                            if (keyValueRezon != null) {
                                if (!keyValueRezon.getKey().trim().equalsIgnoreCase("")) {
                                    if (parent.getId() == R.id.sp_Rezon) {
                                        if (keyValueRezon.getKey().equalsIgnoreCase("" + Constant.PRECISEZ_10) ||
                                                keyValueRezon.getKey().equalsIgnoreCase("" + Constant.PRECISEZ_14) ||
                                                keyValueRezon.getKey().equalsIgnoreCase("" + Constant.PRECISEZ_19) ||
                                                keyValueRezon.getKey().equalsIgnoreCase("" + Constant.PRECISEZ_23)) {
                                            LL_LotRezon.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            ToastUtility.LogCat("Exception :-: sp_Rezon:onItemSelected(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                //endregion

                //region Buttons btnQuitter
                Button btnQuitter = (Button) dialog.findViewById(R.id.btnQuitter);
                btnQuitter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        // On va a la question suivante
                        //Precedent_Click(QF);
                    }
                });
                //endregion

                //region Buttons btnContinuer
                Button btnContinuer = (Button) dialog.findViewById(R.id.btnContinuer);
                btnContinuer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Shared_Preferences sharedPreferences = Tools.SharedPreferences(QuestionnaireBatimentActivity.this);
                            QF.CheckValueBefore_RapportRAR(sp_Rezon, et_LotRezon, sharedPreferences);

                            //QF.SaveInfoDefinitivement(cuRecordMngr, false);
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            finish();//Suivant_Click();
                        } catch (TextEmptyException ex) {
                            Tools.AlertDialogMsg(QuestionnaireBatimentActivity.this, ex.getMessage());
                            ToastUtility.LogCat("TextEmptyException: ShowRapport_RAR() :" + ex.getMessage());
                            //}catch (ManagerException ex) {
                            //    ToastUtility.LogCat("Exception: ShowFormulaireBatiment() :" + ex.getMessage() + " / toString: " + ex.toString());
                            //    ToastUtility.ToastMessage(context, ex.getMessage());
                        } catch (Exception ex) {
                            ToastUtility.LogCat("Exception: ShowRapport_RAR() :" + ex.getMessage() + " / toString: " + ex.toString());
                            ex.printStackTrace();
                        }
                    }
                });
                //endregion

                //region Buttons btnContinuerEtChangerdeModule
                btnContinuerEtChangerdeModule = (Button) dialog.findViewById(R.id.btnContinuerEtChangerdeModule);
                btnContinuerEtChangerdeModule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Shared_Preferences sharedPreferences = Tools.SharedPreferences(QuestionnaireBatimentActivity.this);
                            QF.CheckValueBefore_RapportRAR(sp_Rezon, et_LotRezon, sharedPreferences);
                            if (dialog != null) {
                                dialog.dismiss();
                            }

                            if (finishAfter) {
                                finish();
                            } else {
                                finish();
                            }
                        } catch (TextEmptyException ex) {
                            Tools.AlertDialogMsg(QuestionnaireBatimentActivity.this, ex.getMessage());
                            ToastUtility.LogCat("TextEmptyException: ShowRapport_RAR() :" + ex.getMessage());
                        } catch (Exception ex) {
                            ToastUtility.LogCat("Exception: ShowRapport_RAR() :" + ex.getMessage() + " / toString: " + ex.toString());
                            ex.printStackTrace();
                        }
                    }
                });
                //endregion

                dialog.show();
            }
        } catch (Exception ex) {
            String message = "Erreur :";
            ToastUtility.LogCat("Exception: ShowRapport_RAR :" + message +" / " + ex.toString());
            //Tools.AlertDialogMsg(context, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }
    // endregion

    //region LOCALISATION GPS
    private void getServiceGPS() {
        try{
            GPSService gpsService = new GPSService(this);
            gpsService.getLocation();
            if (gpsService.isLocationAvailable == false) {
                // Here you can ask the user to try again, using return; for that
                ToastUtility.ToastMessage(this, getString(R.string.msg_Localisation_Non_Disponnible), Constant.GravityCenter);
            } else {
                // Getting location co-ordinates
                double latitude = gpsService.getLatitude();
                double longitude = gpsService.getLongitude();
                //String locationAddress = gpsService.getLocationAddress();

                message = "Latitude:" + latitude + " | Longitude: " + longitude;// + "\n locationAddress" + locationAddress;
                ToastUtility.LogCat("W", message);
                ToastUtility.ToastMessage(this, message);

                et_Latitude.setText("" + latitude );
                et_Longitude.setText("" + longitude);
                //et_Adresse.setText("" + locationAddress);
                //et_AdrBat_HabitationAncienNom.setText("Address: " + locationAddress);
            }
            // make sure you close the gps after using it. Save user's battery power
            //gpsService.closeGPS();
        }catch (Exception ex) {
            ToastUtility.LogCat("Exception-getServiceGPS(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
            ex.printStackTrace();
        }
    }
    //endregion

}// End Class
