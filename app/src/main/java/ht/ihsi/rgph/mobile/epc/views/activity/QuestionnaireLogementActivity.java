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
import ht.ihsi.rgph.mobile.epc.models.MenageModel;
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
import ht.ihsi.rgph.mobile.epc.views.decorator.SimpleDividerItemDecoration;

/**
 * Created by JFDuverseau on 11/24/2016.
 */
public class QuestionnaireLogementActivity extends BaseActivity implements Serializable, View.OnClickListener, TextView.OnEditorActionListener , AdapterView.OnItemSelectedListener{
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

    private TextView tvHeaderOne;
    private TextView tvHeaderTwo;

    private String headerFormOne;
    private String headerFormTwo;

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
    Spinner sp_JourIndividu;
    Spinner sp_MoisIndividu;
    Spinner sp_AnneeIndividu;
    Spinner sp_AgeIndividu;


    ContrainteReponse contrainte = new ContrainteReponse();
    public static List<TempInfoQuestion> tempInfoQuestions;

    //region LogementModel PopUp
    LogementModel logementM_OBJ = null;
    public static int CounterForIndividu_LogCol = 1;
    public static int CounterForMenage_LogInd = 1;
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
            // .|. \\ QF.getDateInfo(this);
            init(Constant.FORM_DATA_MANAGER);
            init(Constant.QUERY_RECORD_MANAGER);
            init(Constant.CU_RECORD_MANAGER);

            Intent intent= getIntent();

            headerFormOne = intent.getStringExtra(Constant.PARAM_FORM_HEADER_ONE).toString();
            headerFormTwo = intent.getStringExtra(Constant.PARAM_FORM_HEADER_TWO).toString();
            //int numOrdre = intent.getIntExtra(Constant.PARAM_NUMERO_ORDRE_LOG_INDIVIDUEL, 0);
            //if( numOrdre > 0 ) {
            //    QuestionnaireBatimentActivity.CounterForLogeCollectif = numOrdre;
            //}

            tvHeaderOne=(TextView) this.findViewById(R.id.header1);
            tvHeaderOne.setText(headerFormOne.toUpperCase());
            tvHeaderTwo=(TextView) this.findViewById(R.id.header2);
            tvHeaderTwo.setText( Html.fromHtml(headerFormTwo) );


            tv_GrandTitre = (TextView) this.findViewById(R.id.tv_GrandTitre);
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
            QF.context = QuestionnaireLogementActivity.this;
            //QF.dateDebutCollecte = QF.dateDebutCollecte;
            GetFieldValuesQuestionInfo(QF);

            if (QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL) {
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
            }
            //mHomeKeyLocker = new HomeKeyLocker();
            //mHomeKeyLocker.lock(this);
            QF.context = QuestionnaireLogementActivity.this;
        }catch (Exception ex){
            ToastUtility.LogCat(this, "Exception: onCreate(): " + ex.getMessage() + "" + ex.toString());
            ex.printStackTrace();
        }
    }//

    //region EVENTS CONTROLS
    @Override
    protected void onResume() {
        try {
            if (QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL) {
                if (QF.getNomChamps().equalsIgnoreCase(Constant.CallFormulaireMenage)) {
                    int nbre_TotalMenage = 0;
                    if (QF.getLogementModel().getQlin5NbreTotalMenage() != null) {
                        nbre_TotalMenage = QF.getLogementModel().getQlin5NbreTotalMenage();
                    }
                    if (QF.getLogementModel().getQlin4IsHaveIndividuDepense() != null &&
                            QF.getLogementModel().getQlin4IsHaveIndividuDepense() == Constant.REPONS_NON_2) {
                        nbre_TotalMenage = 1;
                    }
                    // On verifie s'il existe de menage dans le logement Individuel
                    if (nbre_TotalMenage > 0) {
                        long nbreTotalMenage_DejaSave = queryRecordMngr.countMenageByLog(QF.getLogementModel().getLogeId());
                        if ( CounterForMenage_LogInd >= nbre_TotalMenage) {
                            ShowListInformations(Constant.LOJ_ENDIVIDYEL, (int) nbreTotalMenage_DejaSave);
                        } else {
                            // On selectionne le Logement qui n'a pas un statut FINI
                            MenageModel menM = null;
                            do {
                                CounterForMenage_LogInd += 1;
                                menM = queryRecordMngr.searchMenage_ByNoOrdre_ByIdLogement(CounterForMenage_LogInd, QF.getLogementModel().getLogeId());
                            }
                            while (menM == null && CounterForMenage_LogInd < nbre_TotalMenage && nbreTotalMenage_DejaSave > 0);

                            if ( menM != null ) {
                                this.SetFieldMenage(menM, nbre_TotalMenage, Constant.ACTION_MOFIDIER);

                                message = "Kontinye pran enfòmasyon sou Menaj " + menM.getQm1NoOrdre() + " an";
                                ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                            } else {
                                if (nbre_TotalMenage == nbreTotalMenage_DejaSave) {
                                    // On lui permet de voir la liste des personnes deja enregistrer.
                                    // On Passe a la question suivante
                                    Suivant_Click();
                                } else {
                                    Precedent_Click(QF);
                                }
                            }
                        }
                    }
                }
            }
        }catch (ManagerException ex) {
            message = "Erreur:";
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ToastUtility.LogCat("ManagerException: onResume :" + ex.toString());
            ex.printStackTrace();
        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: onResume :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.toString());
            ex.printStackTrace();
        }
        ToastUtility.LogCat(this, "on Resume : ");
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
                        QF.CheckValueBefore(queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Logement, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                                ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                                , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                                , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                                , et_Jour, sp_Mois2 , et_Annee );
                        // Enregistrement des informations
                        QF.SaveInfoDefinitivement(cuRecordMngr, false);

                        // On met le boucle ici pour les individu
                        // AddLogement_EnBoucle();
                        if( QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_COLLECTIF ) {
                            QuestionnaireBatimentActivity.CounterForLogeCollectif += 1;
                        }else{
                            QuestionnaireBatimentActivity.CounterForLogeEndividyel += 1;
                        }
                        QF.context = QuestionnaireLogementActivity.this;
                        finishAfter=true;
                        this.ShowRapport_RAR( Constant.STATUT_PA_FIN_RANPLI_22);
                        //finish();
                    }catch (TextEmptyException ex) {
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
        }catch (TextEmptyException ex) {
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
        try {
            switch (item.getItemId()) {
                case R.id.btn_actionbar_SaveAndQuit:
                    try {
                        QF.CheckValueBefore(queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Logement, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                                ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                                , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                                , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                                , et_Jour, sp_Mois2 , et_Annee );
                        // Enregistrement des informations
                        QF.SaveInfoDefinitivement(cuRecordMngr, false);

                        // On met le boucle ici pour les individu
                        // AddLogement_EnBoucle();
                        if( QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_COLLECTIF ) {
                            QuestionnaireBatimentActivity.CounterForLogeCollectif += 1;
                        }else{
                            QuestionnaireBatimentActivity.CounterForLogeEndividyel += 1;
                        }
                        finishAfter=true;
                        QF.context = QuestionnaireLogementActivity.this;
                        this.ShowRapport_RAR( Constant.STATUT_PA_FIN_RANPLI_22);
                        //finish();
                    }catch (TextEmptyException ex) {
                        Tools.AlertDialogMsg(this, ex.getMessage());
                        ToastUtility.LogCat("TextEmptyException: onContextItemSelected() :" + ex.getMessage());
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
        ToastUtility.LogCat("D", "INSIDE onEditorAction(Logement) | actionId:" + actionId);
        if( actionId == Constant.imeActionId_EtReponse_6){
            ToastUtility.LogCat("D", "INSIDE onEditorAction(Logement if()) | actionId:" + actionId);
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
            if (typeQuestion == Constant.TYPE_QUESTION_CHOIX_1 || typeQuestion == Constant.TYPE_QUESTION_DEUX_CHOIX_4
                    || typeQuestion == Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15 ) {
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
                                ToastUtility.LogCat("W", "INSIDE onItemSelected() / [+]  IsLoadPossibiliteReponse_ChildAgain = TRUE / codeParent>>" + reponseModel.getCodeUniqueReponse() + " / codeQuestionSplit>>" + reponseModel.getCodeQuestion() + " / codeReponseParent>>" + reponseModel.getCodeReponse());
                                QF.Load_PossibiliteReponse_Child(this, formDataMngr,  sp_Reponse2, reponseModel.getCodeUniqueReponse());
                            }
                        } else {
                            ToastUtility.LogCat("E", "INSIDE onItemSelected() / [-]  IsLoadPossibiliteReponse_ChildAgain = FALSE");
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
                    ToastUtility.LogCat(this, "sp_Reponse2 - getCodeUniqueReponse: " + reponseModel.getCodeUniqueReponse());
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
                            ToastUtility.LogCat("[+] CALL ON onItemSelected ( QF.Load_CommuneByIdDept(sp_Reponse2, keyValueDept.getKey()); )");
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
                            ToastUtility.LogCat("[+] CALL ON onItemSelected ( QF.Load_Vqse_ByIdCommune(sp_Reponse3, communeModel.getComId()); )");
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
            String typeLogement=" Enfividyèl ";
            if ( QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_COLLECTIF ) {
                typeLogement=" Kolektif ";
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("" + getString(R.string.msg_Eske_Ou_Vle_Kite___) + " Modil Lojman "+ typeLogement +" la vre.")
                    .setCancelable(false)
                    .setPositiveButton(""+ getString(R.string.label_Non), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
                    .setNegativeButton("" + getString(R.string.label_WI), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            QuestionnaireLogementActivity.super.onKeyDown(_keyCode, _event);
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

            /* Dialog pupUp Formulaire liste individu dans le menage */
            SetReponseValue_DataBase(qf);

            /* Add Individu dans Logement */
            //AddIndividu_LogementCollectif();

            /* Add Menage dans Logement individuel */
            AddMenage_LogementIndividuel();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//
    //endregion

    //region  Add Logement | AddMenage | AddIndividu | PopUp List_Logement
    public void AddLogement_EnBoucle(){
        try{
           Show_Form_Add_Logement();
        }catch (Exception ex) {
            ToastUtility.LogCat("Exception: ShowPopUp_AddLogement :" + ex.getMessage() +" / " + ex.toString());
            Tools.AlertDialogMsg(this, ex.getMessage() +"\n"+ ex.toString());
            ex.printStackTrace();
        }
    }

    private void Show_Form_Add_Logement() {
        try{
            ///*******************************************////
            //      Gestionnaire de statut du Logement      //
            ///*******************************************////
            int typeLogement = Constant.LOJ_KOLEKTIF;
            if ( QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL ) {
                typeLogement = Constant.LOJ_ENDIVIDYEL;
            }
            int statut = QF.SetStatutLogement(queryRecordMngr, cuRecordMngr, typeLogement);
            // On fait appel au Rapport de l'agent recenseur
            //this.ShowRapport_RAR(statut);

            boolean isShowRapport = false;

            //region FORMULAIRE_LOGEMENT_INDIVIDUEL
            if( QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL ){
                //region LOGEMENT INDIVIDUEL
                int NbreLogeEndividyel = QF.getBatimentModel().getQb4NbreLogeIndividuel();
                // On verifie s'il existe de logement Individuel
                if( NbreLogeEndividyel > 0 ) {
                    long nbreLogement_DejaSave = queryRecordMngr.countLogByBatAndType(QF.getBatimentModel().getBatimentId(), Constant.LOJ_ENDIVIDYEL);
                    int nbr_ou_NoOrdre = ((int) nbreLogement_DejaSave + 1);
                    if (NbreLogeEndividyel == nbreLogement_DejaSave) {
                        // On lui permet de voir la liste des personnes deja enregistrer.
                        /* Calcul Statut du Logement individuel en generale */
                        //region PAUSE
                        /**/if( QuestionnaireBatimentActivity.CounterForLogeEndividyel >= NbreLogeEndividyel  ) {
                            finish();
                        }else{
                            // On selectionne le Logement qui n'a pas un statut FINI
                            LogementModel logM = null;
                            do{
                                logM = queryRecordMngr.searchLogementByNoOrdreByTypeLogByIdBatiment( QuestionnaireBatimentActivity.CounterForLogeEndividyel, Constant.LOJ_ENDIVIDYEL, QF.getBatimentModel().getBatimentId() );
                                QuestionnaireBatimentActivity.CounterForLogeEndividyel += 1;
                            }while (logM == null && QuestionnaireBatimentActivity.CounterForLogeEndividyel <= NbreLogeEndividyel );

                            if ( logM != null ) {
                                //region "logementM_OBJ"
                                String dateDebutCollect = Tools.getDateString_MMddyyyy_HHmmss_a();
                                logementM_OBJ = new LogementModel();
                                LogementModel.cuRecordMngr = cuRecordMngr;
                                LogementModel.formDataMngr = formDataMngr;
                                logementM_OBJ.setDateDebutCollecte(dateDebutCollect.toString()) ;
                                logementM_OBJ = logM;
                                // Objet Batimtnent
                                logementM_OBJ.setBatimentId(QF.getBatimentModel().getBatimentId());
                                logementM_OBJ.setObjBatiment(QF.getBatimentModel());

                                ModuleModel moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL, Constant.ACTIF);
                                QF = new QuestionnaireFormulaireUtility(moduleModel, logementM_OBJ, Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL, formDataMngr);
                                QF.context = QuestionnaireLogementActivity.this;
                                if(logementM_OBJ!=null && logementM_OBJ.getDateDebutCollecte()!=null && !logementM_OBJ.getDateDebutCollecte().equalsIgnoreCase("") ) {
                                    dateDebutCollect = logementM_OBJ.getDateDebutCollecte();
                                    QF.setDateDebutCollecte(dateDebutCollect);
                                }else{
                                    QF.setDateDebutCollecte(dateDebutCollect);
                                }
                                GetFieldValuesQuestionInfo(QF);
                                headerFormOne = "MODIFYE LOJMAN ENDIVIDYèl " + logementM_OBJ.getQlin1NumeroOrdre() + "/" + NbreLogeEndividyel;
                                headerFormTwo = "Batiman " + QF.getBatimentModel().getBatimentId() + " | EPC:" + QF.getBatimentModel().getQepc();

                                tvHeaderOne.setText(headerFormOne.toUpperCase());
                                tvHeaderTwo.setText(headerFormTwo.toUpperCase());
                                tempInfoQuestions = new ArrayList<TempInfoQuestion>();
                                toolbar.setTitle(headerFormOne.toUpperCase());


                                message = "Kontinye pran enfòmasyon sou Lojman kolektif " + logementM_OBJ.getQlin1NumeroOrdre() + " la";
                                ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                                //Tools.AlertDialogMsg(this, message, "S");
                                //endregion
                            } else {
                                if ( NbreLogeEndividyel == nbreLogement_DejaSave ) {
                                    // On lui permet de voir la liste des personnes deja enregistrer.
                                    // On Passe a la question suivante
                                    Suivant_Click();
                                } else {
                                    Precedent_Click(QF);
                                }
                            }
                        }/**/
                        //endregion
                        finish();
                    }else{
                        String dateDebutCollect = Tools.getDateString_MMddyyyy_HHmmss_a();
                        logementM_OBJ = new LogementModel();
                        LogementModel.cuRecordMngr = cuRecordMngr;
                        LogementModel.formDataMngr = formDataMngr;
                        logementM_OBJ.setSdeId(QF.getBatimentModel().getSdeId());
                        logementM_OBJ.setQlCategLogement((short) Constant.LOJ_ENDIVIDYEL);
                        logementM_OBJ.setQlin1NumeroOrdre((short)  nbr_ou_NoOrdre);
                        logementM_OBJ.setDateDebutCollecte(dateDebutCollect.toString()) ;
                        // Objet Batimtnent
                        logementM_OBJ.setBatimentId(QF.getBatimentModel().getBatimentId());
                        logementM_OBJ.setObjBatiment(QF.getBatimentModel());

                        ModuleModel moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL, Constant.ACTIF);
                        QF = new QuestionnaireFormulaireUtility( moduleModel, logementM_OBJ, Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL, formDataMngr);
                        QF.context = QuestionnaireLogementActivity.this;
                        if(logementM_OBJ!=null && logementM_OBJ.getDateDebutCollecte()!=null && !logementM_OBJ.getDateDebutCollecte().equalsIgnoreCase("") ) {
                            dateDebutCollect = logementM_OBJ.getDateDebutCollecte();
                            QF.setDateDebutCollecte(dateDebutCollect);
                        }else{
                            QF.setDateDebutCollecte(dateDebutCollect);
                        }
                        GetFieldValuesQuestionInfo(QF);
                        headerFormOne = "LOJMAN ENDIVIDYèl " + nbr_ou_NoOrdre +"/" + NbreLogeEndividyel;
                        headerFormTwo = "Batiman " + QF.getBatimentModel().getBatimentId() + " | EPC:" + QF.getBatimentModel().getQepc();

                        tvHeaderOne.setText(headerFormOne.toUpperCase());
                        tvHeaderTwo.setText(headerFormTwo.toUpperCase());
                        tempInfoQuestions = new ArrayList<TempInfoQuestion>();
                        toolbar.setTitle(headerFormOne.toUpperCase());

                        int ouDejaAntre = nbr_ou_NoOrdre - 1;

                        message = "Kòmanse pran enfòmasyon sou lojman endividyèl " + nbr_ou_NoOrdre + " a \n [ Ou deja antre "+ ouDejaAntre + " / " + NbreLogeEndividyel + "]";
                        ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                        //Tools.AlertDialogMsg(this, message, "S");
                    }
                }
                //endregion
            }
            //endregion
        }catch (ManagerException ex) {
            message = "Erreur:";
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ToastUtility.LogCat("ManagerException: Show_Form_Add_Logement :" + ex.toString());
        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: Show_Form_Add_Logement :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.toString());
            ex.printStackTrace();
        }

    }

    private void AddMenage_LogementIndividuel() {
        try{
           if ( QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL ) {
               if (QF.getNomChamps().equalsIgnoreCase(Constant.CallFormulaireMenage)) {
                   // ON enregistre d'abord le Logement
                   QF.SaveInfoDefinitivement(cuRecordMngr, false);

                   int nbre_TotalMenage = 0;
                   if( QF.getLogementModel().getQlin5NbreTotalMenage() != null ){
                       nbre_TotalMenage = QF.getLogementModel().getQlin5NbreTotalMenage();
                   }
                   if ( QF.getLogementModel().getQlin4IsHaveIndividuDepense() != null &&
                           QF.getLogementModel().getQlin4IsHaveIndividuDepense() == Constant.REPONS_NON_2 ){
                       nbre_TotalMenage = 1;
                   }
                   // On verifie s'il existe de menage dans le logement Individuel
                   if( nbre_TotalMenage > 0 ) {
                       long nbreTotalMenage_DejaSave = queryRecordMngr.countMenageByLog(QF.getLogementModel().getLogeId());
                       int nbr_ou_NoOrdre = ((int) nbreTotalMenage_DejaSave + 1);
                       if (nbre_TotalMenage == nbreTotalMenage_DejaSave) {
                           /*/ Ici on doit Affiche le formulaire du premier menage qui n'est pas encore fini
                            // Et qui est soit remplit Totalement ou pas */
                           if( CounterForMenage_LogInd >= nbre_TotalMenage ) {
                               // On lui permet de voir la liste des Menages deja enregistrer.
                               ShowListInformations(Constant.LOJ_ENDIVIDYEL, (int) nbreTotalMenage_DejaSave);
                           }else {
                               int NoOrdreMen = 0;
                               // On selectionne le Menage qui n'a pas un statut FINI
                               MenageModel menM = null;
                               do {
                                   NoOrdreMen += 1;
                                   CounterForMenage_LogInd = NoOrdreMen;
                                   menM = queryRecordMngr.searchMenage_ByNoOrdre_ByIdLogement(NoOrdreMen, QF.getLogementModel().getLogeId());
                               }
                               while ( menM == null && CounterForMenage_LogInd < nbre_TotalMenage );

                               if ( menM != null) {
                                   this.SetFieldMenage(menM, nbre_TotalMenage, Constant.ACTION_MOFIDIER);

                                   message = "Kontinye pran enfòmasyon sou Menaj " + menM.getQm1NoOrdre() + " an";
                                   ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                                   //Tools.AlertDialogMsg(this, message, "S");
                               } else {
                                   if ( nbre_TotalMenage == nbreTotalMenage_DejaSave ) {
                                       // On lui permet de voir la liste des personnes deja enregistrer.
                                       ShowListInformations(Constant.LOJ_ENDIVIDYEL, (int) nbreTotalMenage_DejaSave);
                                       // On Passe a la question suivante
                                       //Suivant_Click();
                                   } else {
                                       Precedent_Click(QF);
                                   }
                               }
                           }
                       }else{
                           String dateDebutCollect = Tools.getDateString_MMddyyyy_HHmmss_a();
                           MenageModel menageModel = new MenageModel();
                           MenageModel.queryRecordMngr = queryRecordMngr;
                           menageModel.setSdeId(QF.getLogementModel().getSdeId());
                           menageModel.setQm1NoOrdre((short) nbr_ou_NoOrdre);
                           menageModel.setDateDebutCollecte(dateDebutCollect) ;

                           this.SetFieldMenage(menageModel, nbre_TotalMenage, Constant.ACTION_MOFIDIER);

                           message = "Kòmanse pran enfòmasyon sou Menaj " + nbr_ou_NoOrdre + " la";
                           ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                           //Tools.AlertDialogMsg(this, message, "S");
                       }
                   }
               }
            }
        }catch (TextEmptyException  ex) {
            Tools.AlertDialogMsg(this, ex.getMessage());
            ToastUtility.LogCat("TextEmptyException: AddIndividu_LogementCollectif :", ex);
        }catch (ManagerException ex) {
            message = "Erreur:";
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ToastUtility.LogCat("ManagerException: AddIndividu_LogementCollectif :", ex);
        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: AddIndividu_LogementCollectif :", ex);
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
            /*if( QF.getLogementModel() != null && QF.getLogementModel().getQlCategLogement() != null &&
                    QF.getLogementModel().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL ){
                individuM_OBJ.setMenageId(QF.getMenageModel().getMenageId());
                individuM_OBJ.setObjMenage(QF.getMenageModel());
            }*/

            String actionStr = "NOUVO MOUN ";
            if ( actions == Constant.ACTION_MOFIDIER ){
                actionStr = " MODIFYE MOUN ";
            }

            ModuleModel moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_INDIVIDUS, Constant.ACTIF);
            Intent intent = new Intent(this, QuestionnaireIndividuActivity.class);
            QFD = new QuestionnaireFormulaireUtility( moduleModel, individuM_OBJ, Constant.FORMULAIRE_INDIVIDUS, formDataMngr);

            if(individuM_OBJ!=null && individuM_OBJ.getDateDebutCollecte()!=null && !individuM_OBJ.getDateDebutCollecte().equalsIgnoreCase("") ) {
                dateDebutCollect = individuM_OBJ.getDateDebutCollecte();
                QFD.setDateDebutCollecte(dateDebutCollect);
            }else{
                QFD.setDateDebutCollecte(dateDebutCollect);
            }
            intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QFD);
            intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, actionStr + individuM_OBJ.getQ1NoOrdre() +"/" + nbreTotalIndividus);
            intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, " Lojman Kolektif " +  QF.getLogementModel().getQlin1NumeroOrdre()
                    + " | Batiman " +  QF.getLogementModel().getBatimentId() + " | EPC: " +  QF.getBatimentModel().getQepc()  );
            startActivity(intent);

        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: SetFieldIndividus :", ex);
            Tools.AlertDialogMsg(this, message +"\n"+ ex.toString());
            ex.printStackTrace();
        }
    }

    private void SetFieldMenage(MenageModel indM, int nbre_TotalMenage, int actions) {
        try{
            short numOrdre =1;
            String dateDebutCollect = Tools.getDateString_MMddyyyy_HHmmss_a();
            MenageModel menageM_OBJ = new MenageModel();
            IndividuModel.queryRecordMngr = queryRecordMngr;
            menageM_OBJ.setSdeId(QF.getLogementModel().getSdeId());
            //menageM_OBJ.setQp1NoOrdre((short) nbr_ou_NoOrdre);
            menageM_OBJ.setDateDebutCollecte(dateDebutCollect) ;
            menageM_OBJ = indM;
            //numOrdre += (int)menageM_OBJ.getQm1NoOrdre();
            //QuestionnaireMenageActivity.CounterForEmigre = numOrdre;
            // Objet Batimtnent
            menageM_OBJ.setBatimentId(QF.getBatimentModel().getBatimentId());
            menageM_OBJ.setObjBatiment(QF.getBatimentModel());
            // Objet Logement
            menageM_OBJ.setLogeId(QF.getLogementModel().getLogeId());
            menageM_OBJ.setObjLogement(QF.getLogementModel());

            String actionStr = "NOUVO MENAJ ";
            if ( actions == Constant.ACTION_MOFIDIER ){
                actionStr = " MODIFYE MENAJ ";
            }

            ModuleModel moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_MENAGE, Constant.ACTIF);
            Intent intent = new Intent(this, QuestionnaireMenageActivity.class);
            QFD = new QuestionnaireFormulaireUtility( moduleModel, menageM_OBJ, Constant.FORMULAIRE_MENAGE, formDataMngr);

            if(menageM_OBJ!=null && menageM_OBJ.getDateDebutCollecte()!=null && !menageM_OBJ.getDateDebutCollecte().equalsIgnoreCase("") ) {
                dateDebutCollect = menageM_OBJ.getDateDebutCollecte();
                QFD.setDateDebutCollecte(dateDebutCollect);
            }else{
                QFD.setDateDebutCollecte(dateDebutCollect);
            }
            intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QFD);
            intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, actionStr + menageM_OBJ.getQm1NoOrdre() +"/" + nbre_TotalMenage);
            intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, " Lojman Endividèl " +  QF.getLogementModel().getQlin1NumeroOrdre()
                    + " | Batiman " +  QF.getLogementModel().getBatimentId() + " | EPC: " +  QF.getBatimentModel().getQepc()  );
            startActivity(intent);

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
                    QF.SetButtonLabel(QuestionnaireLogementActivity.this, codeReponseRecyclerView.getCodeReponse(), QF, btn_Suivant);
                }
                message = questionReponseModel.toString();
                ToastUtility.ToastMessage(QuestionnaireLogementActivity.this, message, Constant.GravityCenter);
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
                ToastUtility.ToastMessage(QuestionnaireLogementActivity.this, message, Constant.GravityCenter);
                Suivant_Click();
            }
        };
    }
    //endregion

    //region "METHODES - Goto_Question Suivante / Precedent"
    public void Suivant_Click() {
        try {
            QF.context = QuestionnaireLogementActivity.this;
            QF.CheckValueBefore( queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Logement, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                    ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                    , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                    , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                    , et_Jour, sp_Mois2 , et_Annee );
            if (QF.getqSuivant().toString().equalsIgnoreCase(Constant.FIN)) {
                // Enregistrement des informations
                QF.SaveInfoDefinitivement(cuRecordMngr, true );

                // On met le boucle ici pour les individu
                // // AddLogement_EnBoucle();
                int typeLogement=Constant.LOJ_ENDIVIDYEL;
                if ( QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_COLLECTIF ) {
                    typeLogement=Constant.LOJ_KOLEKTIF;
                }
                int statut = QF.SetStatutLogement(queryRecordMngr, cuRecordMngr, typeLogement);
                // On fait appel au Rapport de l'agent recenseur
                QF.context = QuestionnaireLogementActivity.this;
                this.ShowRapport_RAR(statut);
            } else {
                if (JumpToNextQuestion) {
                    qPrecedent = QF.getCodeQuestion();
                    Goto_QuestionSuivante(QF);
                }
            }


            /*InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            ToastUtility.LogCat("LOGEMENT TypeQuestion: " + QF.getTypeQuestion());
            if( QF.getTypeQuestion() != Constant.TYPE_QUESTION_SAISIE_2 ){
                ToastUtility.LogCat("LOGEMENT TypeQuestion: hideSoftInputFromInputMethod" );
                inputMethodManager.hideSoftInputFromInputMethod(getWindow().getDecorView().getRootView().getWindowToken(), 0);
            }else{
                ToastUtility.LogCat("LOGEMENT TypeQuestion: toggleSoftInput - SHOW_FORCED" );
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
            QF.context = QuestionnaireLogementActivity.this;
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
                finish();
            }else {
                Remove_QuestionEncours(QF, true);
                JumpToMenu = true;
                JumpToNextQuestion = true;
                //txt_Reponse.Enabled = true;
            }
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
                                    QF.CheckValueBefore(queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Logement, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                                            ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                                            , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                                            , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                                            , et_Jour, sp_Mois2 , et_Annee );
                                    // Enregistrement des informations
                                    QF.SaveInfoDefinitivement(cuRecordMngr, false);

                                    ShowRapport_RAR(Constant.STATUT_PA_FIN_RANPLI_22);
                                    //finish();
                                } catch (TextEmptyException ex) {
                                    Tools.AlertDialogMsg(QuestionnaireLogementActivity.this, ex.getMessage());
                                    ToastUtility.LogCat("TextEmptyException: CreateMenuContext() :" + ex.getMessage());
                                } catch (ManagerException ex) {
                                    ToastUtility.LogCat("Exception: CreateMenuContext() :" + ex.getMessage() + " / toString: " + ex.toString());
                                    ToastUtility.ToastMessage(QuestionnaireLogementActivity.this, ex.getMessage());
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
                        , et_Jour, sp_Mois2 , et_Annee );
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
                et_Reponse.setText(Constant.STRING_VIDE);
                et_Gason.setText(Constant.STRING_VIDE);
                et_Fi.setText(Constant.STRING_VIDE);
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
            ToastUtility.LogCat(this, "ERROR:" + ex.toString());
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
        aBuilder.setPositiveButton(getString(R.string.label_Non),
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
        //
        aBuilder.setNegativeButton(getString(R.string.label_WI) + ", " + getString(R.string.label_Quit),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }

        );
        aBuilder.show();
    }
    //endregion

    //region LISTE INFOS
    public void ShowListInformations(int typeLogement, int Nbre_TotalElement){
        try{
            infoUser = Tools.SharedPreferences(this);
            if (infoUser != null && infoUser.getProfileId() != null) {
                profilId = infoUser.getProfileId();
            }
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.individus_form);
            dialog.setCancelable(false);
            LinearLayout LL_ListeView = (LinearLayout) dialog.findViewById(R.id.LL_ListeView);
            LinearLayout LL_FormulaireAdd = (LinearLayout) dialog.findViewById(R.id.LL_FormulaireAdd);
            LL_FormulaireAdd.setVisibility(View.VISIBLE);
            LL_ListeView.setVisibility(View.GONE);

            tv_NumeroIndividu = (TextView) dialog.findViewById(R.id.tv_NumeroIndividu);
            TextView tv_GrandTitreInd = (TextView) dialog.findViewById(R.id.tv_GrandTitre);

            // On lui permet de voir la liste des personnes deja enregistrer.
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
            recyclerView.setHasFixedSize(true);
            String lojmantypeSTR = "";
            if( typeLogement == Constant.LOJ_ENDIVIDYEL ){
                // Liste des Menages par Logement Individuel
                //  initialize the adapter
                CounterForMenage_LogInd = 0;
                mAdapter = new DisplayListAdapter(this, targetList, Constant.LIST_MODULE_MENAGE_LIST_ONLY);
                mAdapter.setOnMenuItemClickListener(null);
                //inject the adapter into the recycle view
                recyclerView.setAdapter(mAdapter);
                //dialog.setTitle("Menaj ki nan Lojman Endividyèl sa [" + Nbre_TotalElement + "]");
                //message = "<b> Lojman Endividyèl " + QF.getLogementModel().getQlin1NumeroOrdre() + "</b>";
                lojmantypeSTR = "List Menaj pou Lojman Endividyèl "+ QF.getLogementModel().getQlin1NumeroOrdre();
                tv_GrandTitreInd.setText(Html.fromHtml("Ou antre <b>[" + Nbre_TotalElement + "/"+  QF.getLogementModel().getQlin5NbreTotalMenage() +"] Menaj</b> pou Lojman Endividyèl "+ QF.getLogementModel().getQlin1NumeroOrdre() +" sa" ));
                //tv_GrandTitreInd.setText(Html.fromHtml("" + message));
                targetList = queryRecordMngr.searchListMenage_ByLogement(QF.getLogementModel().getLogeId());
            }

            dialog.setTitle( lojmantypeSTR);

            mAdapter.setFilter(targetList);

            //EVENTS
            // Buttons btnQuitter
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
            // Buttons btnContinuer
            Button btnContinuer = (Button) dialog.findViewById(R.id.btnContinuer);
            btnContinuer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    Suivant_Click();
                }
            });
            dialog.show();
        } catch (Exception ex) {
            message = "Erreur :";
            ToastUtility.LogCat("Exception: ShowListInformation :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
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
                //LinearLayout_messageChangerdeModule.setVisibility(View.GONE);
                tv_messageChangerdeModule = (TextView) dialog.findViewById(R.id.tv_messageChangerdeModule);

                //if( finishAfter ){
                //    LinearLayout_messageChangerdeModule.setVisibility(View.VISIBLE);
                //}

                if (QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_COLLECTIF) {
                    message = String.format(getString(R.string.label_msgInfoRapport), " Lojman Kolektif ", " Batiman " + QF.getBatimentModel().getBatimentId());
                } else if (QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL) {
                    message = String.format(getString(R.string.label_msgInfoRapport), " Lojman Endividyèl ", " Batiman " + QF.getBatimentModel().getBatimentId());
                }
                tv_messageChangerdeModule.setText(Html.fromHtml("" + message));

                dialog.setTitle("" + this.getString(R.string.Rappot_Agent_Resenceur));

                //Shared_Preferences sharedPreferences = null;
                //sharedPreferences = Tools.SharedPreferences(this);

                String typeLogementSTR = " Endividyèl";
                if (QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_COLLECTIF) {
                    typeLogementSTR = " Kolektif";
                }
                TextView tv_GrandTitreRap = (TextView) dialog.findViewById(R.id.tv_GrandTitre);
                message = "" + "<b>Rapò sou Lojman " + typeLogementSTR + "</b>";
                if (QF.getLogementModel().getLogeId() != null) {
                    message += "" + "<b> " + QF.getLogementModel().getQlin1NumeroOrdre() + "</b>";
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
                            ToastUtility.LogCat("Exception :-: sp_Rezon:onItemSelected(): getMessage: ", ex);
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
                            Shared_Preferences sharedPreferences = Tools.SharedPreferences(QuestionnaireLogementActivity.this);
                            QF.CheckValueBefore_RapportRAR(sp_Rezon, et_LotRezon, sharedPreferences);
                            //QF.SaveInfoDefinitivement(cuRecordMngr, false);
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            //if( finishAfter ){
                            //    finish();
                            //}else {
                            //finish();//Suivant_Click();
                            // On met le boucle ici pour les logements
                            AddLogement_EnBoucle();
                            //}
                        } catch (TextEmptyException ex) {
                            Tools.AlertDialogMsg(QuestionnaireLogementActivity.this, ex.getMessage());
                            ToastUtility.LogCat("TextEmptyException: ShowRapport_RAR() :", ex);
                        } catch (Exception ex) {
                            ToastUtility.LogCat("Exception: ShowRapport_RAR() :", ex);
                            ex.printStackTrace();
                        }
                    }
                });
                //endregion

                //region Buttons btnContinuerEtChangerdeModule
                btnContinuerEtChangerdeModule = (Button) dialog.findViewById(R.id.btnContinuerEtChangerdeModule);
                LinearLayout_messageChangerdeModule.setVisibility(View.VISIBLE);
                if (QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL) {
                    if (QuestionnaireBatimentActivity.CounterForLogeEndividyel >= QF.getBatimentModel().getQb4NbreLogeIndividuel()) {
                        LinearLayout_messageChangerdeModule.setVisibility(View.GONE);
                    }
                }
                btnContinuerEtChangerdeModule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Shared_Preferences sharedPreferences = Tools.SharedPreferences(QuestionnaireLogementActivity.this);
                            QF.CheckValueBefore_RapportRAR(sp_Rezon, et_LotRezon, sharedPreferences);
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            if (QF.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL) {
                                QuestionnaireBatimentActivity.CounterForLogeEndividyel = QF.getBatimentModel().getQb4NbreLogeIndividuel() + 1;
                                finishAfter = true;
                            }

                            if (finishAfter) {
                                finish();
                            } else {
                                // On met le boucle ici pour les logements
                                AddLogement_EnBoucle();
                            }
                        } catch (TextEmptyException ex) {
                            Tools.AlertDialogMsg(QuestionnaireLogementActivity.this, ex.getMessage());
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
}
