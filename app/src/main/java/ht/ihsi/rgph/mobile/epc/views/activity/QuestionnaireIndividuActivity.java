package ht.ihsi.rgph.mobile.epc.views.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.exceptions.ManagerException;
import ht.ihsi.rgph.mobile.epc.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.epc.models.CommuneModel;
import ht.ihsi.rgph.mobile.epc.models.IndividuModel;
import ht.ihsi.rgph.mobile.epc.models.KeyValueModel;
import ht.ihsi.rgph.mobile.epc.models.LogementModel;
import ht.ihsi.rgph.mobile.epc.models.ModuleModel;
import ht.ihsi.rgph.mobile.epc.models.QuestionReponseModel;
import ht.ihsi.rgph.mobile.epc.models.RowDataListModel;
import ht.ihsi.rgph.mobile.epc.models.VqseModel;
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

/**
 * Created by JFDuverseau on 11/24/2016.
 */
public class QuestionnaireIndividuActivity extends BaseActivity implements Serializable, View.OnClickListener, TextView.OnEditorActionListener , AdapterView.OnItemSelectedListener{
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
    private TextView tv_Commune;
    private TextView tv_SectionCommune;
    private EditText et_Reponse;
    private Spinner sp_Reponse;
    private RelativeLayout RelativeLayout_Reponse;
    private Spinner sp_Reponse2;
    private RelativeLayout RelativeLayout_Reponse2;
    private Spinner sp_Reponse3;
    private RelativeLayout RelativeLayout_Reponse3;
    private Spinner sp_Jour;
    private Spinner sp_Mois, sp_MoisLog;
    private Spinner sp_Annee, sp_AnneeLog;
    private RelativeLayout RL_Jour, RL_JourLog;
    private LinearLayout LinearLDate, LinearLDateLog;

    LinearLayout LL_GaconEtFille, LL_AppareilEtAnimaux;
    TextView tv_Reponse, tv_Gason, tv_Fi;
    EditText et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet;
    EditText et_VwatiMachin, et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok;

    private Button btn_Precedent, btn_PrecedentLog;
    private Button btn_Suivant, btn_SuivantLog;
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
    public Dialog dialog;
    EditText et_NonIndividu;
    EditText et_SiyatiIndividu;
    Spinner sp_Sexe;
    Spinner sp_RelasyonMounNan;
    TextView tv_NumeroIndividu;
    long ID_INDIVIDU = 0;
    private List<RowDataListModel> targetList = new ArrayList<RowDataListModel>();
    private RecyclerView recyclerView;
    private DisplayListAdapter mAdapter;
    EditText et_JourIndividu;
    Spinner sp_MoisIndividu;
    EditText et_AnneeIndividu;
    EditText et_AgeIndividu;

    Spinner sp_MounNanMenajLa;
    TextView tv_RelasyonMounNan;
    TextView tv_DateMounNanfet;
    TextView tv_LageMounNan;
    RelativeLayout RL_RelasyonMounNan;
    LinearLayout LL_DateMounNanfet;
    LinearLayout LL_LajMounNan;


    ContrainteReponse contrainte = new ContrainteReponse();
    public static List<TempInfoQuestion> tempInfoQuestions;

    //region LogementModel PopUp
    LogementModel logementM_OBJ = null;
    //endregion

    ScrollView scrollView2;
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
            tvHeaderTwo=(TextView) this.findViewById(R.id.header2);
            tvHeaderTwo.setText( Html.fromHtml(headerFormTwo) );


            tv_GrandTitre = (TextView) this.findViewById(R.id.tv_grandtitre);
            tv_DetailsCategorie = (TextView) this.findViewById(R.id.tv_DetailsCategorie);
            tv_SousDetailsCategorie = (TextView) this.findViewById(R.id.tv_SousDetailsCategorie);
            tv_LibeleQuestion = (TextView) this.findViewById(R.id.tv_LibeleQuestion);
            tv_DetailQuestion = (TextView) this.findViewById(R.id.tv_DetailQuestion);
            tv_Commune = (TextView) this.findViewById(R.id.tv_Commune);
            tv_SectionCommune = (TextView) this.findViewById(R.id.tv_SectionCommune);
//
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
            et_Fi.setOnEditorActionListener(this);
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
            et_BetVolayPoulKok.setOnEditorActionListener(this);

            LinearLDate_SaisieJJ_SelectMM_SaisieAAAA = (LinearLayout) this.findViewById(R.id.LinearLDate_SaisieJJ_SelectMM_SaisieAAAA);
            et_Jour = (EditText) this.findViewById(R.id.et_Jour);
            sp_Mois2 = (Spinner) this.findViewById(R.id.sp_Mois2);
            et_Annee = (EditText) this.findViewById(R.id.et_Annee);


            btn_Precedent = (Button) this.findViewById(R.id.btn_Precedent);
            btn_Precedent.setOnClickListener(this);

            btn_Suivant = (Button) this.findViewById(R.id.btn_Suivant);
            btn_Suivant.setOnClickListener(this);

            RelativeLayout RL_Questionnaire = (RelativeLayout) this.findViewById(R.id.RL_Questionnaire);
            //RL_Questionnaire.setOnLongClickListener(this);
            registerForContextMenu(RL_Questionnaire);

            // LOAD FIRST QUESTION
            //QF = SessionUtility.getCurrentQuestionnaireFormulaireUtility();
            //QF = new QuestionnaireFormulaireUtility();
            //QF = (QuestionnaireFormulaireUtility) intent.getSerializableExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE);

            toolbar.setTitle(headerFormOne);
            //toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            //QF.formDataMngr = formDataMngr;
            //QF.queryRecordMngr = queryRecordMngr;


            //QF = SessionUtility.getCurrentQuestionnaireFormulaireUtility();
            //QF = new QuestionnaireFormulaireUtility();
            QF = new QuestionnaireFormulaireUtility();
            QF = (QuestionnaireFormulaireUtility) intent.getSerializableExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE);
            QF.context = QuestionnaireIndividuActivity.this;
            GetFieldValuesQuestionInfo(QF);

            ShowPopUp_FormIndividus();
            //mHomeKeyLocker = new HomeKeyLocker();
            //mHomeKeyLocker.lock(this);
        }catch (Exception ex){
            ToastUtility.LogCat(this, "Exception: onCreate(): ", ex);
            ex.printStackTrace();
        }
    }//

    //region EVENTS CONTROLS
    @Override
    protected void onResume() {
        ToastUtility.LogCat(this, "onResume : ");
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

        switch (v.getId()){
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
                        QF.CheckValueBefore(queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Individu, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                                ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                                , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                                , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                                , et_Jour, sp_Mois2 , et_Annee );
                        // Enregistrement des informations
                        QF.SaveInfoDefinitivement(cuRecordMngr, false);

                        /**/
                        if( QF.getLogementModel()!=null && QF.getLogementModel().getQlCategLogement()== Constant.LOJ_KOLEKTIF ){
                            QuestionnaireLogementActivity.CounterForIndividu_LogCol  += 1;
                        }else  if( QF.getLogementModel()!=null && QF.getLogementModel().getQlCategLogement()== Constant.LOJ_ENDIVIDYEL ){
                            QuestionnaireMenageActivity.CounterForIndividu += 1;
                        }
                        QF.context = QuestionnaireIndividuActivity.this;
                        finishAfter=true;
                        this.ShowRapport_RAR( Constant.STATUT_PA_FIN_RANPLI_22);
                        //finish();
                    }catch (TextEmptyException ex) {
                        Tools.AlertDialogMsg(this, ex.getMessage());
                        ToastUtility.LogCat("TextEmptyException: onOptionsItemSelected() :", ex);
                    }
                    break;
                case R.id.btn_actionbar_Quitter:
                    ShowAlertDialogQuitter();
                    break;
                default:
                    break;
            }
        }catch (TextEmptyException ex) {
            Tools.AlertDialogMsg(this, ex.getMessage());
            ToastUtility.LogCat("TextEmptyException: onOptionsItemSelected() :", ex);
        }catch (ManagerException ex) {
            ToastUtility.LogCat("Exception: onOptionsItemSelected() :", ex);
            ToastUtility.ToastMessage(this, ex.getMessage());
        }catch (Exception ex) {
            ToastUtility.LogCat("Exception: onOptionsItemSelected() :", ex);
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
        try {
            switch (item.getItemId()) {
                case R.id.btn_actionbar_SaveAndQuit:
                    try {
                        QF.CheckValueBefore(queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Individu, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                                ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                                , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                                , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                                , et_Jour, sp_Mois2 , et_Annee  );
                        // Enregistrement des informations
                        QF.SaveInfoDefinitivement(cuRecordMngr, false);

                        /**/
                        if( QF.getLogementModel()!=null && QF.getLogementModel().getQlCategLogement()== Constant.LOJ_KOLEKTIF ){
                            QuestionnaireLogementActivity.CounterForIndividu_LogCol  += 1;
                        }else  if( QF.getLogementModel()!=null && QF.getLogementModel().getQlCategLogement()== Constant.LOJ_ENDIVIDYEL ){
                            QuestionnaireMenageActivity.CounterForIndividu += 1;
                        }
                        QF.context = QuestionnaireIndividuActivity.this;
                        finishAfter=true;
                        this.ShowRapport_RAR( Constant.STATUT_PA_FIN_RANPLI_22);
                        //finish();
                    }catch (TextEmptyException ex) {
                        Tools.AlertDialogMsg(this, ex.getMessage());
                        ToastUtility.LogCat("TextEmptyException: onContextItemSelected() :", ex);
                    }
                    break;
                case R.id.btn_actionbar_Quitter:
                    ShowAlertDialogQuitter();
                    break;
                default:
                    break;
                    //return super.onContextItemSelected(item);
            }
        }catch (TextEmptyException  ex) {
            Tools.AlertDialogMsg(this, ex.getMessage());
            ToastUtility.LogCat("TextEmptyException: onContextItemSelected :" + ex.getMessage());
        }catch (ManagerException ex) {
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
        if( actionId == Constant.imeActionId_EtReponse_6 ){
            Suivant_Click();
            return  true;
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
            if (QF.getEstSautReponse()){// SI LE SAUT DEPEND DE LA REPONSE
                if (!reponseModel.getCodeReponse().trim().equalsIgnoreCase("")) {
                    if (!reponseModel.getQSuivant().equalsIgnoreCase(""))
                        QF.setqSuivant(reponseModel.getQSuivant());
                }
            }
            if (reponseModel != null) {
                if (parent.getId() == R.id.sp_Reponse) {
                    QF.SetButtonLabel(this, reponseModel.getCodeReponse(), QF, btn_Suivant);
                    //ToastUtility.LogCat(this, "sp_Reponse - getCodeUniqueReponse: " + reponseModel.getCodeUniqueReponse());
                    if (reponseModel.getAvoirEnfant()) {
                        // VERIFIER S'IL EXISTE DES SOUS REPONSES ( EXEMPLE UTILISATION 1 ET UTILISATION 2 )
                        if (!reponseModel.getCodeUniqueReponse().trim().equalsIgnoreCase("")) {
                            sp_Reponse2.setVisibility(View.VISIBLE);
                            RelativeLayout_Reponse2.setVisibility(View.VISIBLE);
                            tv_Commune.setVisibility(View.VISIBLE);
                            tv_Commune.setText("...");

                              if (QF.IsLoadPossibiliteReponse_ChildAgain) {
                                if( QF.getTbl_TableName() == Constant.FORMULAIRE_INDIVIDUS ) {
                                    // Pou E4A	Ki pi wo nivo {0} te rive nan lekol / inivesite?
                                    if (QF.getNomChamps().equalsIgnoreCase(Constant.Qe4ANiveauEtudeETClasse)) {//
                                        tv_Reponse.setVisibility(View.VISIBLE);
                                        tv_Reponse.setText( getString(R.string.label_E4B_Nivo) );
                                        tv_Commune.setText( getString(R.string.label_E4B_KlasAneDetid) );
                                    }
                                }
                                //ToastUtility.LogCat("W", "INSIDE onItemSelected() / [+]  IsLoadPossibiliteReponse_ChildAgain = TRUE / codeParent>>" + reponseModel.getCodeUniqueReponse() + " / codeQuestionSplit>>" + reponseModel.getCodeQuestion() + " / codeReponseParent>>" + reponseModel.getCodeReponse());
                                QF.Load_PossibiliteReponse_Child(this, formDataMngr,  sp_Reponse2, reponseModel.getCodeUniqueReponse());
                            }
                        } else {
                            //ToastUtility.LogCat("E", "INSIDE onItemSelected() / [-]  IsLoadPossibiliteReponse_ChildAgain = FALSE");
                            sp_Reponse2.setVisibility(View.GONE);
                            RelativeLayout_Reponse2.setVisibility(View.GONE);
                            tv_Commune.setVisibility(View.GONE);
                        }
                    }else{
                        sp_Reponse2.setVisibility(View.GONE);
                        RelativeLayout_Reponse2.setVisibility(View.GONE);
                        tv_Commune.setVisibility(View.GONE);
                    }
                }
                if (parent.getId() == R.id.sp_Reponse2) {
                    //ToastUtility.LogCat(this, "sp_Reponse2 - getCodeUniqueReponse: " + reponseModel.getCodeUniqueReponse());
                }

            }
            // TEST POUR LE CHARGEMENT DES COMMUNES
            KeyValueModel keyValueDept = null;
            if (typeQuestion == Constant.TYPE_QUESTION_CHOIX_COMMUNE_6) {
                keyValueDept = ((KeyValueModel) sp_Reponse.getSelectedItem());
                if (!QF.idDeptLast.equals(keyValueDept.getKey())) {
                    QF.IsLoadCommuneAgain = true;
                }
                //QF.Load_ClearSpinner(sp_Reponse2, Constant.TYPE_QUESTION_CHOIX_COMMUNE_6);
                //QF.Load_ClearSpinner(sp_Reponse3, Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7);
            }
            if (keyValueDept != null) {
                if (!keyValueDept.getKey().trim().equalsIgnoreCase("")) {
                    if (parent.getId() == R.id.sp_Reponse) {
                        sp_Reponse2.setVisibility(View.VISIBLE);
                        RelativeLayout_Reponse2.setVisibility(View.VISIBLE);
                        tv_Commune.setVisibility(View.VISIBLE);
                        tv_Commune.setText("Komin");
                        if (QF.IsLoadCommuneAgain) {
                            //ToastUtility.LogCat("[+] CALL ON onItemSelected ( QF.Load_CommuneByIdDept(sp_Reponse2, keyValueDept.getKey()); )");
                            QF.Load_CommuneByIdDept( formDataMngr, sp_Reponse2, keyValueDept.getKey());
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
                            //ToastUtility.LogCat("[+] CALL ON onItemSelected ( QF.Load_CommuneByIdDept(sp_Reponse2, keyValueDept.getKey()); )");
                            QF.Load_CommuneByIdDept( formDataMngr, sp_Reponse2, dept2.getKey());
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
                            //ToastUtility.LogCat("[+] CALL ON onItemSelected ( QF.Load_Vqse_ByIdCommune(sp_Reponse3, communeModel.getComId()); )");
                            QF.Load_Vqse_ByIdCommune( formDataMngr, sp_Reponse3, communeModel.getComId());
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
                        if (!reponseModel2.getCodeUniqueReponse().trim().equalsIgnoreCase("")) {
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
                                Suivant_Click();
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
            builder.setMessage("" + getString(R.string.msg_Eske_Ou_Vle_Kite___) + " Modil Moun nan vre.")
                    .setCancelable(false)
                    .setPositiveButton(""+ getString(R.string.label_Non), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
                    .setNegativeButton("" + getString(R.string.label_WI), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            QuestionnaireIndividuActivity.super.onKeyDown(_keyCode, _event);
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
    }
    //endregion

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

            SetReponseValue_DataBase(qf);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//
    //endregion

    private void ContinuerAvecIndividuMenage_EnBoucle() {
        try{
            String dateDebutCollect = Tools.getDateString_MMddyyyy_HHmmss_a();
            if( QF.getLogementModel().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL ) {
                // Verification si l'individu se trouve dans un logement Collectif
                int NbreTotalIndividus = QF.getMenageModel().getQm2TotalIndividuVivant();
                // On verifie s'il existe d'individu dans le menage
                if (NbreTotalIndividus > 0) {
                    long nbreTotalIndividus_DejaSave = queryRecordMngr.countIndByMenage(QF.getMenageModel().getMenageId());
                    int nbr_ou_NoOrdre = ((int) nbreTotalIndividus_DejaSave + 1);
                    // On verifie s'il existe d'individu deja enregistrer dans le menage
                    if (NbreTotalIndividus == nbreTotalIndividus_DejaSave) {
                        // On lui permet de voir la liste des Individus deja enregistrer.
                        // Et qui est soit remplit Totalement ou pas
                        if (QuestionnaireMenageActivity.CounterForIndividu >= NbreTotalIndividus) {
                            // On verifi si tous les individus sont bien enregistrer....
                            finish();
                        } else { //rend automatique cette partie pour les individus dans les menages. ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff
                            // On selectionne l'Emigre qui n'a pas un statut FINI
                            IndividuModel indM = null;
                            do {
                                indM = queryRecordMngr.searchIndividu_ByNoOrdre_ByIdMenage(QuestionnaireMenageActivity.CounterForIndividu, QF.getMenageModel().getMenageId(),true);
                                QuestionnaireMenageActivity.CounterForIndividu += 1;
                            }
                            while (indM == null && QuestionnaireMenageActivity.CounterForIndividu <= NbreTotalIndividus);

                            if (indM != null) {
                                IndividuModel individuM_OBJ = new IndividuModel();
                                IndividuModel.queryRecordMngr = queryRecordMngr;
                                individuM_OBJ.setSdeId(QF.getLogementModel().getSdeId());
                                individuM_OBJ.setQ1NoOrdre(indM.getQ1NoOrdre());
                                individuM_OBJ.setDateDebutCollecte(dateDebutCollect);
                                individuM_OBJ = indM;

                                this.SetFieldIndividus(individuM_OBJ, NbreTotalIndividus, Constant.ACTION_MOFIDIER);

                                message = "Kontinye pran enfòmasyon sou Moun " + individuM_OBJ.getQ1NoOrdre() + " an";// \n [ Ou deja antre "+ (nbr_ou_NoOrdre-1) + " / " + NbreTotalIndividus + "]";
                                ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                                //Tools.AlertDialogMsg(this, message, "S");
                            }
                        }
                    }else{
                        IndividuModel individuM_OBJ = new IndividuModel();
                        IndividuModel.queryRecordMngr = queryRecordMngr;
                        individuM_OBJ.setSdeId(QF.getLogementModel().getSdeId());
                        individuM_OBJ.setQ1NoOrdre((short) nbr_ou_NoOrdre);
                        individuM_OBJ.setDateDebutCollecte(dateDebutCollect);

                        this.SetFieldIndividus(individuM_OBJ, NbreTotalIndividus, Constant.ACTION_MOFIDIER);

                        message = "Kòmanse pran enfòmasyon sou Moun " + nbr_ou_NoOrdre + " an";// \n [ Ou deja antre "+ (nbr_ou_NoOrdre-1) + " / " + NbreTotalIndividus + "]";
                        ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                        //Tools.AlertDialogMsg(this, message, "S");
                    }
                }
            }
        }catch (ManagerException ex) {
            message = "Erreur:";
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ToastUtility.LogCat("ManagerException: ShowPopUp_AddIndividu :" + ex.toString());
        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: ShowPopUp_AddIndividu :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.toString());
            ex.printStackTrace();
        }
    }

    private void SetFieldIndividus(IndividuModel indM, int nbreTotalIndividus, int actions) {
        try{
            String dateDebutCollect = Tools.getDateString_MMddyyyy_HHmmss_a();
            IndividuModel individuM_OBJ = new IndividuModel();
            IndividuModel.queryRecordMngr = queryRecordMngr;
            individuM_OBJ.setSdeId(QF.getLogementModel().getSdeId());
            //individuM_OBJ.setQp1NoOrdre((short) nbr_ou_NoOrdre);
            individuM_OBJ.setDateDebutCollecte(dateDebutCollect) ;

            individuM_OBJ = indM;
            // Objet Batimtnent
            individuM_OBJ.setBatimentId(QF.getBatimentModel().getBatimentId());
            individuM_OBJ.setObjBatiment(QF.getBatimentModel());
            // Objet Logement
            individuM_OBJ.setLogeId(QF.getLogementModel().getLogeId());
            individuM_OBJ.setObjLogement(QF.getLogementModel());

            // Objet Menage
            individuM_OBJ.setMenageId((long) 0);
            individuM_OBJ.setObjMenage(null);
            if( QF.getLogementModel() != null && QF.getLogementModel().getQlCategLogement() != null &&
                    QF.getLogementModel().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL ){
                individuM_OBJ.setMenageId(QF.getMenageModel().getMenageId());
                individuM_OBJ.setObjMenage(QF.getMenageModel());
            }
            //individuM_OBJ.setMenageId(QF.getMenageModel().getMenageId());
            //individuM_OBJ.setObjMenage(QF.getMenageModel());

            ModuleModel moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_INDIVIDUS, Constant.ACTIF);
            QF = new QuestionnaireFormulaireUtility(moduleModel, individuM_OBJ, Constant.FORMULAIRE_INDIVIDUS, formDataMngr);
            QF.context = QuestionnaireIndividuActivity.this;
            if(individuM_OBJ!=null && individuM_OBJ.getDateDebutCollecte()!=null && !individuM_OBJ.getDateDebutCollecte().equalsIgnoreCase("") ) {
                dateDebutCollect = individuM_OBJ.getDateDebutCollecte();
                QF.setDateDebutCollecte(dateDebutCollect);
            }else{
                QF.setDateDebutCollecte(dateDebutCollect);
            }
            GetFieldValuesQuestionInfo(QF);

            String actionStr = "NOUVO MOUN ";
            if ( actions == Constant.ACTION_MOFIDIER ){
                actionStr = " MODIFYE MOUN ";
            }
            headerFormOne = actionStr + individuM_OBJ.getQ1NoOrdre() +"/" + nbreTotalIndividus;
            headerFormTwo = " Lojman Kolektif " + QF.getLogementModel().getQlin1NumeroOrdre()
                    + " | Batiman " + individuM_OBJ.getBatimentId();

            if( QF.getLogementModel() != null && QF.getLogementModel().getQlCategLogement() != null &&
                    QF.getLogementModel().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL ){
                headerFormOne = actionStr + individuM_OBJ.getQ1NoOrdre() +"/" + nbreTotalIndividus;
                headerFormTwo = " Menaj " + QF.getMenageModel().getQm1NoOrdre()
                        + " | Lojman Endividyèl " + QF.getLogementModel().getQlin1NumeroOrdre()
                        + " | Batiman " + individuM_OBJ.getBatimentId();
            }

            tvHeaderOne.setText(headerFormOne.toUpperCase());
            tvHeaderTwo.setText(headerFormTwo.toUpperCase());
            toolbar.setTitle(headerFormOne.toUpperCase());
            tempInfoQuestions = new ArrayList<TempInfoQuestion>();

            ShowPopUp_FormIndividus();
        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: SetFieldIndividus :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.toString());
            ex.printStackTrace();
        }
    }
    //endregion

    //region EVENTS REPONSE
    public RadioListAdapter.OnItemClickListener getItemClickListener(){
        return new RadioListAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(QuestionReponseModel  questionReponseModel) {
                codeReponseRecyclerView = questionReponseModel;
                if (codeReponseRecyclerView != null) {
                    if (QF.getEstSautReponse()) {// SI LE SAUT DEPEND DE LA REPONSE
                        if (!codeReponseRecyclerView.getCodeReponse().trim().equalsIgnoreCase("")) {
                            if (!codeReponseRecyclerView.getQSuivant().equalsIgnoreCase(""))
                                QF.setqSuivant(codeReponseRecyclerView.getQSuivant());
                        }
                    }
                    QF.SetButtonLabel(QuestionnaireIndividuActivity.this, codeReponseRecyclerView.getCodeReponse(), QF, btn_Suivant);
                }
                message = questionReponseModel.toString();
                ToastUtility.ToastMessage(QuestionnaireIndividuActivity.this, message, Constant.GravityBottom);
                Suivant_Click();
            }
        };
    }

    public RadioListAdapterKeyValue.OnItemClickListenerKeyValue getItemClickListenerKeyValue(){
        return new RadioListAdapterKeyValue.OnItemClickListenerKeyValue(){
            @Override
            public void onItemClick(KeyValueModel keyValueModel) {
                codeReponseKeyValueModel = keyValueModel;
                message = keyValueModel.toString();
                ToastUtility.ToastMessage(QuestionnaireIndividuActivity.this, message, Constant.GravityCenter);
                Suivant_Click();
            }
        };
    }
    //endregion

    //region "METHODES - Goto_Question Suivante / Precedent"
    public void Suivant_Click() {
        try {
            QF.context = QuestionnaireIndividuActivity.this;

            QF.CheckValueBefore( queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Individu, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                    ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                    , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                    , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                    , et_Jour, sp_Mois2 , et_Annee );
            if (QF.getqSuivant().toString().equalsIgnoreCase(Constant.FIN)) {
                // Enregistrement des informations
                QF.SaveInfoDefinitivement(cuRecordMngr, true );

                // On fait appel au Rapport de l'agent recenseur
                if( QF.getLogementModel().getQlCategLogement() == Constant.LOJ_KOLEKTIF ) {
                    int statut = QF.SetStatutLogement(queryRecordMngr, cuRecordMngr, Constant.LOJ_KOLEKTIF);
                    // On fait appel au Rapport de l'agent recenseur
                    QF.context = QuestionnaireIndividuActivity.this;
                    statut=Constant.STATUT_RANPLI_NET_11;
                    this.ShowRapport_RAR(statut);

                }else  if( QF.getLogementModel().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL ) {
                    int statut = QF.SetStatutLogement(queryRecordMngr, cuRecordMngr, Constant.LOJ_ENDIVIDYEL);
                    // On fait appel au Rapport de l'agent recenseur
                    QF.context = QuestionnaireIndividuActivity.this;
                    statut=Constant.STATUT_RANPLI_NET_11;
                    this.ShowRapport_RAR(statut);
                }
                // On met le boucle ici pour les individus dans les Logement Collectif
                //AddIndividu_LCol_EnBoucle();
                // On met le boucle ici pour les individus dans les Menage
                //ContinuerAvecIndividuMenage_EnBoucle();
            } else {
                if (JumpToNextQuestion) {
                     qPrecedent = QF.getCodeQuestion();
                    Goto_QuestionSuivante(QF);
                }
            }

            /*InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            ToastUtility.LogCat("INDIVIDU TypeQuestion: " + QF.getTypeQuestion());
            if( QF.getTypeQuestion() != Constant.TYPE_QUESTION_SAISIE_2 ){
                ToastUtility.LogCat("INDIVIDU TypeQuestion: hideSoftInputFromInputMethod" );
                inputMethodManager.hideSoftInputFromInputMethod(getWindow().getDecorView().getRootView().getWindowToken(), 0);
                inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }else{
                ToastUtility.LogCat("INDIVIDU TypeQuestion: toggleSoftInput - SHOW_FORCED" );
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
            //if (codeQuestion.toString().equalsIgnoreCase("P6")) {
            //    ShowPopUp_FormIndividus();
            //}else {
                //QF.setFormDataMngr(formDataMngr);
                QF.Goto_QuestionSuivante(formDataMngr, codeQuestion);
                QF.context = QuestionnaireIndividuActivity.this;
                GetFieldValuesQuestionInfo(QF);
            //}
        } catch (Exception ex){
            ToastUtility.LogCat("Exception-Goto_Question() : onClick : " , ex);
            throw ex;
        }
    }//
    //
    private void Precedent_Click( QuestionnaireFormulaireUtility QF ) {
        try {
            if (QF.getqPrecedent().toString().equalsIgnoreCase(Constant.DEBUT)) {
                finish();
            } else {
                if( QF.getLogementModel().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL ) {
                    if ( QF.getCodeQuestion().toString().equalsIgnoreCase("P6")) {
                        ShowPopUp_FormIndividus();
                    }else{
                        Remove_QuestionEncours(QF, true);
                        JumpToMenu = true;
                        JumpToNextQuestion = true;
                    }
                }else{
                    Remove_QuestionEncours(QF, true);
                    JumpToMenu = true;
                    JumpToNextQuestion = true;
                }
                //txt_Reponse.Enabled = true;
            }
        } catch (Exception ex) {
            Tools.AlertDialogMsg(this, ex.getMessage() + "\n" + ex.toString(), "E");
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
                Goto_QuestionPrecedente(QF);
            }

            if (tempInfoQuestions.size() > 0){
                TempInfoQuestion temp = tempInfoQuestions.get(tempInfoQuestions.size() - 1);
                SetReponseValue(QF, temp);

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
                                    QF.CheckValueBefore(queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Individu, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                                            ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                                            , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                                            , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                                            , et_Jour, sp_Mois2 , et_Annee );
                                    // Enregistrement des informations
                                    QF.SaveInfoDefinitivement(cuRecordMngr, false);

                                    ShowRapport_RAR(Constant.STATUT_PA_FIN_RANPLI_22);
                                    //finish();
                                } catch (TextEmptyException ex) {
                                    Tools.AlertDialogMsg(QuestionnaireIndividuActivity.this, ex.getMessage());
                                    ToastUtility.LogCat("TextEmptyException: CreateMenuContext() :", ex);
                                } catch (ManagerException ex) {
                                    ToastUtility.LogCat("Exception: CreateMenuContext() :", ex);
                                    ToastUtility.ToastMessage(QuestionnaireIndividuActivity.this, ex.getMessage());
                                } catch (Exception ex) {
                                    ToastUtility.LogCat("Exception: CreateMenuContext() :", ex);
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
                        , et_Jour, sp_Mois2 , et_Annee );
            }
        }catch (Exception ex){
            ToastUtility.LogCat(this, "ERROR - INSIDE SetReponseValue():", ex);
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
                et_Reponse.setText(Constant.STRING_VIDE);
                et_Gason.setText(Constant.STRING_VIDE);
                et_Fi.setText(Constant.STRING_VIDE);
                et_Jour.setText(Constant.STRING_VIDE);
                et_Annee.setText(Constant.STRING_VIDE);
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
        }catch (Exception ex){
            ToastUtility.LogCat(this, "ERROR:", ex);
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

    //region PopUp ShowRapport_RAR
    public void ShowRapport_RAR(int statut) {
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
                tv_messageChangerdeModule = (TextView) dialog.findViewById(R.id.tv_messageChangerdeModule);

                if (QF.getLogementModel().getQlCategLogement() == Constant.LOJ_KOLEKTIF) {
                    message = String.format(getString(R.string.label_msgInfoRapport), "Moun", "Lojman Kolektif " + QF.getLogementModel().getQlin1NumeroOrdre());
                } else if (QF.getLogementModel().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    message = String.format(getString(R.string.label_msgInfoRapport), "Moun", "Menaj " + QF.getMenageModel().getQm1NoOrdre());
                }
                tv_messageChangerdeModule.setText(Html.fromHtml("" + message));

                dialog.setTitle("" + this.getString(R.string.Rappot_Agent_Resenceur));

                Shared_Preferences sharedPreferences = null;
                sharedPreferences = Tools.SharedPreferences(this);
                if (sharedPreferences != null) {
                    //tv_GrandTitre2.setText(sharedPreferences.getPrenomEtNom());
                }

                TextView tv_GrandTitreRap = (TextView) dialog.findViewById(R.id.tv_grandtitre);
                message = "" + "<b>Rapò sou Moun </b>";
                if (QF.getLogementModel().getLogeId() != null) {
                    message += "" + "<b> " + QF.getIndividuModel().getQ1NoOrdre() + "</b>";
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
                            ToastUtility.LogCat("Exception :-: sp_Rezon:onItemSelected():", ex);
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
                        Precedent_Click(QF);
                    }
                });
                //endregion

                //region Buttons btnContinuer
                Button btnContinuer = (Button) dialog.findViewById(R.id.btnContinuer);
                btnContinuer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Shared_Preferences sharedPreferences = Tools.SharedPreferences(QuestionnaireIndividuActivity.this);
                            QF.CheckValueBefore_RapportRAR(sp_Rezon, et_LotRezon, sharedPreferences);
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            //if( finishAfter ){
                            //    finish();
                            //}else {
                            //finish();//Suivant_Click();
                            //}
                            // On met le boucle ici pour les individus dans les Logement Collectif
                            if( QF.getLogementModel().getQlCategLogement() == Constant.LOJ_KOLEKTIF ) {
                                //AddIndividu_LCol_EnBoucle();
                            }else  if( QF.getLogementModel().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL ) {
                                // On met le boucle ici pour les individus dans les Menage
                                ContinuerAvecIndividuMenage_EnBoucle();
                            }
                        } catch (TextEmptyException ex) {
                            Tools.AlertDialogMsg(QuestionnaireIndividuActivity.this, ex.getMessage());
                            ToastUtility.LogCat("TextEmptyException: ShowRapport_RAR() :", ex);
                        } catch (Exception ex) {
                            ToastUtility.LogCat("Exception: ShowRapport_RAR() :" , ex);
                            ex.printStackTrace();
                        }
                    }
                });
                //endregion

                //region Buttons btnContinuerEtChangerdeModule
                btnContinuerEtChangerdeModule = (Button) dialog.findViewById(R.id.btnContinuerEtChangerdeModule);
                LinearLayout_messageChangerdeModule.setVisibility(View.VISIBLE);

                if (QF.getLogementModel() != null && QF.getLogementModel().getQlCategLogement() == Constant.LOJ_KOLEKTIF) {
                   /* if (QuestionnaireLogementActivity.CounterForIndividu_LogCol - 1 >= QF.getLogementModel().getQlcTotalIndividus()) {
                        LinearLayout_messageChangerdeModule.setVisibility(View.GONE);
                    }*/
                } else if (QF.getLogementModel() != null && QF.getLogementModel().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    if (QuestionnaireMenageActivity.CounterForIndividu >= QF.getMenageModel().getQm2TotalIndividuVivant()) {
                        LinearLayout_messageChangerdeModule.setVisibility(View.GONE);
                    }
                }
                btnContinuerEtChangerdeModule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Shared_Preferences sharedPreferences = Tools.SharedPreferences(QuestionnaireIndividuActivity.this);
                            QF.CheckValueBefore_RapportRAR(sp_Rezon, et_LotRezon, sharedPreferences);
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            if (QF.getLogementModel() != null && QF.getLogementModel().getQlCategLogement() == Constant.LOJ_KOLEKTIF) {
                                //QuestionnaireLogementActivity.CounterForIndividu_LogCol = QF.getLogementModel().getQlcTotalIndividus();
                                finishAfter = true;
                            } else if (QF.getLogementModel() != null && QF.getLogementModel().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                                QuestionnaireMenageActivity.CounterForIndividu = QF.getMenageModel().getQm2TotalIndividuVivant();
                                finishAfter = true;
                            }

                            if (finishAfter) {
                                finish();
                            } else {
                                // On met le boucle ici pour les individus dans les Logement Collectif
                                //AddIndividu_LCol_EnBoucle();
                            }
                        } catch (TextEmptyException ex) {
                            Tools.AlertDialogMsg(QuestionnaireIndividuActivity.this, ex.getMessage());
                            ToastUtility.LogCat("TextEmptyException: ShowRapport_RAR() :", ex);
                        } catch (Exception ex) {
                            ToastUtility.LogCat("Exception: ShowRapport_RAR() :", ex);
                            ex.printStackTrace();
                        }
                    }
                });
                //endregion
                dialog.show();
            }
        } catch (Exception ex) {
            String message = "Erreur :";
            ToastUtility.LogCat("Exception: ShowRapport_RAR :", ex);
            //Tools.AlertDialogMsg(context, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }
    // endregion

    //region PopUp Form Individu
    public void ShowPopUp_FormIndividus(){
        try{
            if( QF.getLogementModel().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL ) {
                if (QF.getqPrecedent().toString().equalsIgnoreCase(Constant.DEBUT)
                        || QF.getCodeQuestion().toString().equalsIgnoreCase("P6")) {
                    if (QF.getDataBase() != null) {
                        IndividuModel Ind = (IndividuModel) QF.getDataBase();

                        //if (QF.getNomChamps().equalsIgnoreCase(Constant.Qm11CallFormListeIndividu)) {
                        //long Nbre_Individu_DejaSave = queryRecordMngr.countIndByMenage(QF.getMenageModel().getMenageId());
                        int nbrInd_NoOrdre = Ind.getQ1NoOrdre();//((int) Nbre_Individu_DejaSave + 1);
                        //int Nbre_TotalIndividu = QF.getMenageModel().getQm11TotalIndividuVivant();

                        // On Affiche le popUp pour la saisie des individus
                        dialog = new Dialog(this);
                        dialog.setContentView(R.layout.individus_form);
                        dialog.setCancelable(false);
                        scrollView2 = (ScrollView) dialog.findViewById(R.id.scrollView2);
                        LinearLayout LLGrandTitre = (LinearLayout) dialog.findViewById(R.id.LLGrandTitre);
                        LLGrandTitre.setVisibility(View.GONE);
                        LinearLayout LL_ListeView = (LinearLayout) dialog.findViewById(R.id.LL_ListeView);
                        LinearLayout LL_FormulaireAdd = (LinearLayout) dialog.findViewById(R.id.LL_FormulaireAdd);
                        tv_NumeroIndividu = (TextView) dialog.findViewById(R.id.tv_NumeroIndividu);
                        TextView tv_GrandTitreInd = (TextView) dialog.findViewById(R.id.tv_grandtitre);

                        //dialog.setTitle("Ajoute Moun nan menaj sa [" + nbrInd_NoOrdre + " / " + Nbre_TotalIndividu + "]");
                        tv_GrandTitreInd.setText("Moun " + nbrInd_NoOrdre + "");
                        LL_FormulaireAdd.setVisibility(View.VISIBLE);
                        LL_ListeView.setVisibility(View.GONE);

                        et_NonIndividu = (EditText) dialog.findViewById(R.id.et_NonIndividu);
                        et_SiyatiIndividu = (EditText) dialog.findViewById(R.id.et_SiyatiIndividu);
                        sp_Sexe = (Spinner) dialog.findViewById(R.id.sp_Sexe);
                        QF.Load_Sexe(this, sp_Sexe);

                        sp_RelasyonMounNan = (Spinner) dialog.findViewById(R.id.sp_09LienDeParente);
                        QF.Load_Relation(this, sp_RelasyonMounNan);

                        tv_RelasyonMounNan = (TextView) dialog.findViewById(R.id.tv_09LienDeParente);
                        tv_DateMounNanfet = (TextView) dialog.findViewById(R.id.tv_07DateNaissance);
                        tv_LageMounNan = (TextView) dialog.findViewById(R.id.tv_LageMounNan);
                        RL_RelasyonMounNan = (RelativeLayout) dialog.findViewById(R.id.RL_09LienDeParente);
                        LL_DateMounNanfet = (LinearLayout) dialog.findViewById(R.id.LL_07DateNaissance);
                        LL_LajMounNan = (LinearLayout) dialog.findViewById(R.id.LL_08LajMounNan);

                        et_JourIndividu = (EditText) dialog.findViewById(R.id.et_07DateNaissanceJour);
                        //QF.Load_Jour(this, sp_JourIndividu);

                        sp_MoisIndividu = (Spinner) dialog.findViewById(R.id.sp_07DateNaissanceMois);
                        QF.Load_Mois(this, sp_MoisIndividu);

                        et_AnneeIndividu = (EditText) dialog.findViewById(R.id.et_07DateNaissanceAnnee);
                        //QF.Load_Annee(this, sp_AnneeIndividu);

                        sp_MounNanMenajLa = (Spinner) dialog.findViewById(R.id.sp_05HabiteDansMenage);
                        QF.Load_MounNanMenajLa(this, sp_MounNanMenajLa);

                        et_AgeIndividu = (EditText) dialog.findViewById(R.id.et_08AgeIndividu);
                        //QF.Load_Age(this, sp_AgeIndividu);

                        dialog.setTitle("Kontinye Pran Enfòmasyon sou MOUN #" + nbrInd_NoOrdre);
                        tv_NumeroIndividu.setText("Moun " + nbrInd_NoOrdre + " [ " + Ind.getQp2APrenom() + " " + Ind.getQp2BNom() + " ]");
                        et_NonIndividu.setText(Ind.getQp2APrenom());
                        et_NonIndividu.setEnabled(false);
                        et_SiyatiIndividu.setText(Ind.getQp2BNom());
                        et_SiyatiIndividu.setEnabled(false);
                        QF.setReponse(sp_Sexe, "" + Ind.getQp4Sexe(), Constant.CLASSE_KEY_VALUE_MODEL);
                        sp_Sexe.setEnabled(false);
                        QF.setReponse(sp_RelasyonMounNan, "" + Ind.getQ9LienDeParente(), Constant.CLASSE_KEY_VALUE_MODEL);
                        sp_RelasyonMounNan.setEnabled(false);

                        QF.setReponseDate(et_JourIndividu, sp_MoisIndividu, et_AnneeIndividu, "" + Ind.getQ7JourMoisAnneeDateNaissance());
                        et_JourIndividu.setEnabled(false);
                        sp_MoisIndividu.setEnabled(false);
                        et_AnneeIndividu.setEnabled(false);
                        et_AgeIndividu.setText(""+Ind.getQ8Age());
                        //QF.setReponse(et_08AgeIndividu, "" + Ind.getQp5bAge(), Constant.CLASSE_KEY_VALUE_MODEL);
                        et_AgeIndividu.setEnabled(false);
                        QF.setReponse(sp_MounNanMenajLa, "" + Ind.getQ5HabiteDansMenage(), Constant.CLASSE_KEY_VALUE_MODEL);
                        sp_MounNanMenajLa.setEnabled(false);

                        QF.setqPrecedent("P5");
                        QF.setqSuivant("P6");

                        Button btnQuitter = (Button) dialog.findViewById(R.id.btnQuitter);
                        btnQuitter.setVisibility(View.GONE);
                        btnQuitter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (dialog != null) {
                                    dialog.dismiss();
                                }
                                // On va a la question suivante
                                Precedent_Click(QF);
                            }
                        });

                        // Buttons
                        Button btnContinuer = (Button) dialog.findViewById(R.id.btnContinuer);
                        btnContinuer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                QF.context = QuestionnaireIndividuActivity.this;
                                try {
                                    if (dialog != null) {
                                        dialog.dismiss();
                                    }
                                    //Suivant_Click();
                                    QF.setqPrecedent("P5");
                                    QF.setqSuivant("P6");
                                    Goto_QuestionSuivante(QF);
                                } catch (Exception ex) {
                                    ToastUtility.ToastMessage(QuestionnaireIndividuActivity.this, ex.getMessage() + " \n " + ex.toString());
                                    ToastUtility.LogCat("Exception: btnQuitter.setOnClickListener() :" + ex.getMessage() + " \n " + ex.toString());
                                }
                            }
                        });
                        dialog.show();
                        //}
                    }
                }
            }
       /* }catch (TextEmptyException  ex) {
            Tools.AlertDialogMsg(this, ex.getMessage());
            ToastUtility.LogCat("TextEmptyException: Suivant_Click :" + ex.getMessage());
        }catch (ManagerException ex) {
            message = "Erreur au niveau du Manager";
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ToastUtility.LogCat("ManagerException: Suivant_Click :" + ex.getMessage());*/
        } catch (Exception ex) {
            message = "Erreur au niveau de l'affichage";
            ToastUtility.LogCat("Exception: Suivant_Click :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }
    //endregion
}
