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
//import ht.ihsi.rgph.mobile.epc.models.DecesModel;
//import ht.ihsi.rgph.mobile.epc.models.EmigreModel;
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
 * Created by JFDuverseau on 11/25/2016.
 */
public class QuestionnaireMenageActivity extends BaseActivity implements Serializable, View.OnClickListener, TextView.OnEditorActionListener , AdapterView.OnItemSelectedListener{
    //region ATTRIBUTS
    private String title;
    private String qPrecedent;
    private int typeQuestion;
    //private Intent intent;
    private QuestionnaireFormulaireUtility QF, QFD;
    private TextView tv_GrandTitre, tv_GrandTitreLog;
    private TextView tv_DetailsCategorie, tv_DetailsCategorieLog;
    private TextView tv_SousDetailsCategorie, tv_SousDetailsCategorieLog;
    private TextView tv_LibeleQuestion, tv_LibeleQuestionLog;
    private TextView tv_DetailQuestion, tv_DetailQuestionLog;
    private TextView tv_Commune, tv_CommuneLog;
    private TextView tv_SectionCommune, tv_SectionCommuneLog;
    private EditText et_Reponse, et_ReponseLog;
    private Spinner sp_Reponse, sp_ReponseLog;
    private RelativeLayout RelativeLayout_Reponse, RelativeLayout_ReponseLog;
    private Spinner sp_Reponse2, sp_Reponse2Log;
    private RelativeLayout RelativeLayout_Reponse2, RelativeLayout_Reponse2Log;
    private Spinner sp_Reponse3, sp_Reponse3Log;
    private RelativeLayout RelativeLayout_Reponse3, RelativeLayout_Reponse3Log;
    private Spinner sp_Jour, sp_JourLog;
    private Spinner sp_Mois, sp_MoisLog;
    private Spinner sp_Annee, sp_AnneeLog;
    private RelativeLayout RL_Jour, RL_JourLog;
    private LinearLayout LinearLDate;

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
    ScrollView scrollView2;

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
    MenageModel menageM_OBJ = null;
    public static int CounterForEmigre = 0;
    public static int CounterForDeces = 0;
    public static int CounterForIndividu = 0;
    //endregion

    //region Rapport RAR Final
    Spinner sp_RepondantPrincipal, sp_AE_EsKeGenMounKiEde, sp_AE_IsVivreDansMenage, sp_AE_RepondantQuiAide;
    RelativeLayout RL_F_IsVivreDansMenage, RL_F_RepondantQuiAide;
    TextView tv_AE_IsVivreDansMenage, tv_AE_RepondantQuiAide;

    Spinner  sp_F_EsKeGenMounKiEde, sp_F_IsVivreDansMenage, sp_F_RepondantQuiAide;
    RelativeLayout RL_AE_IsVivreDansMenage, RL_AE_RepondantQuiAide;
    TextView tv_F_IsVivreDansMenage, tv_F_RepondantQuiAide;
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
            QF.context = QuestionnaireMenageActivity.this;
            GetFieldValuesQuestionInfo(QF);
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
        try {
            if (QF.getTbl_TableName() == Constant.FORMULAIRE_MENAGE) {
                if (QF.getNomChamps().equalsIgnoreCase(Constant.CallFormulaireEmigre)) {
                    int NbreTotalEmigre = 0;
                    /*if (QF.getMenageModel().getQn1Emigration() != null && QF.getMenageModel().getQn1Emigration() == Constant.REPONS_WI_1) {
                        if ( QF.getMenageModel().getQn1NbreEmigre() != null && QF.getMenageModel().getQn1NbreEmigre() > 0) {
                            NbreTotalEmigre = QF.getMenageModel().getQn1NbreEmigre();
                        }
                    }*/
                    // On verifie s'il existe d'emigrer dans le menage
                    /*if (NbreTotalEmigre > 0) {
                        long nbreTotalEmigre_DejaSave = queryRecordMngr.countEmigrerByMenage(QF.getMenageModel().getMenageId());
                        if (CounterForEmigre >= NbreTotalEmigre) {
                            ShowListInformations(Constant.FORMULAIRE_EMIGRE, (int) nbreTotalEmigre_DejaSave);
                        } else {
                            // On selectionne l'emigrer qui n'a pas un statut FINI
                            EmigreModel emigM = null;
                            do {
                                CounterForEmigre += 1;
                                emigM = queryRecordMngr.searchEmigre_ByNoOrdre_ByIdMenage(CounterForEmigre,  QF.getMenageModel().getMenageId() );
                            }
                            while (emigM == null && CounterForEmigre < NbreTotalEmigre && nbreTotalEmigre_DejaSave > 0);

                            if (emigM != null) {
                                this.SetFieldEmigre(emigM, NbreTotalEmigre, Constant.ACTION_MOFIDIER);

                                message = "Kontinye pran enfòmasyon sou Emigre " + emigM.getQn1numeroOrdre() + " a";
                                ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                            }else{
                                if (NbreTotalEmigre == nbreTotalEmigre_DejaSave) {
                                    // On Passe a la question suivante
                                    Suivant_Click();
                                } else {
                                    Precedent_Click(QF);
                                }
                            }
                        }

                    }*/
                /*} else if (QF.getNomChamps().equalsIgnoreCase(Constant.CallFormulaireDeces)) {
                    int NbreTotalDeces = 0;
                    if ( QF.getMenageModel().getQd1Deces() != null && QF.getMenageModel().getQd1Deces() == Constant.REPONS_WI_1) {
                        if ( QF.getMenageModel().getQd1NbreDecede() != null && QF.getMenageModel().getQd1NbreDecede() > 0) {
                            NbreTotalDeces = QF.getMenageModel().getQd1NbreDecede();
                        }
                    }
                    // On verifie s'il existe de logement Collectif
                    if (NbreTotalDeces > 0) {
                        long nbreTotalDeces_DejaSave = queryRecordMngr.countDecesByMenage(QF.getMenageModel().getMenageId());
                        int nbr_ou_NoOrdre = ((int) nbreTotalDeces_DejaSave + 1);
                        if (CounterForDeces >= NbreTotalDeces) {
                            ShowListInformations(Constant.FORMULAIRE_DECES, (int) nbreTotalDeces_DejaSave);
                        } else {
                            // On selectionne le deces qui n'a pas un statut FINI
                            DecesModel decesM = null;
                            do {
                                CounterForDeces += 1;
                                decesM = queryRecordMngr.searchDeces_ByNoOrdre_ByIdMenage(CounterForDeces,  QF.getMenageModel().getMenageId() );
                            }
                            while (decesM == null && CounterForDeces < NbreTotalDeces && nbreTotalDeces_DejaSave > 0);

                            if (decesM != null) {
                                this.SetFieldDeces(decesM, NbreTotalDeces, Constant.ACTION_MOFIDIER);

                                message = "Kontinye pran enfòmasyon sou Moun mouri " + decesM.getQd2NoOrdre() + " a";
                                ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                            }else{
                                if (NbreTotalDeces == nbreTotalDeces_DejaSave) {
                                    // On Passe a la question suivante
                                    Suivant_Click();
                                } else {
                                    Precedent_Click(QF);
                                }
                            }
                        }
                    }*/
                } else if (QF.getNomChamps().equalsIgnoreCase(Constant.CallFormulaireIndividuMenage)) {
                    int Nbre_TotalIndividu = 0;
                    if (QF.getMenageModel().getQm2TotalIndividuVivant() != null ) {
                        Nbre_TotalIndividu = QF.getMenageModel().getQm2TotalIndividuVivant();
                    }
                    if( Nbre_TotalIndividu > 0 ){
                        long nbreTotalIndividu_DejaSave = queryRecordMngr.countIndByMenage(QF.getMenageModel().getMenageId());
                        int nbr_ou_NoOrdre = ((int) nbreTotalIndividu_DejaSave + 1);
                        if (CounterForIndividu >= Nbre_TotalIndividu) {
                            ShowListInformations(Constant.FORMULAIRE_INDIVIDUS, (int) nbreTotalIndividu_DejaSave);
                        } else {
                            // On selectionne le deces qui n'a pas un statut FINI
                            IndividuModel indM = null;
                            do {
                                CounterForIndividu += 1;
                                indM = queryRecordMngr.searchIndividu_ByNoOrdre_ByIdMenage(CounterForIndividu,  QF.getMenageModel().getMenageId(), true);
                            }
                            while (indM == null && CounterForIndividu <= Nbre_TotalIndividu && nbreTotalIndividu_DejaSave > 0);

                            if (indM != null) {
                                this.SetFieldIndividu(indM, Nbre_TotalIndividu, Constant.ACTION_MOFIDIER);

                                message = "Kontinye pran enfòmasyon sou Moun " + indM.getQ1NoOrdre() + " a";
                                ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                            }else{
                                if (Nbre_TotalIndividu == nbreTotalIndividu_DejaSave) {
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
        }catch (Exception ex) {
            message = "";
            ToastUtility.LogCat("Exception: onResume :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
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

    //region MENU EVENTS
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

                        QF.CheckValueBefore(queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Menage, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                                ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                                , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                                , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                                , et_Jour, sp_Mois2 , et_Annee );
                        // Enregistrement des informations
                        QF.SaveInfoDefinitivement(cuRecordMngr, false);
                        QuestionnaireLogementActivity.CounterForMenage_LogInd += 1;
                        QF.context = QuestionnaireMenageActivity.this;
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
                        QF.CheckValueBefore(queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Menage, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                                ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                                , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                                , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                                , et_Jour, sp_Mois2 , et_Annee );
                        // Enregistrement des informations
                        QF.SaveInfoDefinitivement(cuRecordMngr, false);

                        QuestionnaireLogementActivity.CounterForMenage_LogInd += 1;
                        QF.context = QuestionnaireMenageActivity.this;
                        finishAfter=true;
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
                    //ToastUtility.LogCat(this, "sp_Reponse - getCodeUniqueReponse: " + reponseModel.getCodeUniqueReponse());
                    QF.SetButtonLabel(this, reponseModel.getCodeReponse(), QF, btn_Suivant);

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
    //endregion

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if( actionId == Constant.imeActionId_EtReponse_6 ){
            Suivant_Click();
            return  true;
        }
        return false;
    }//

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            final int _keyCode=keyCode;
            final KeyEvent _event=event;
            //e = event;
            //k = keyCode;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("" + getString(R.string.msg_Eske_Ou_Vle_Kite___) + " Menaj la vre.")
                    .setCancelable(false)
                    .setPositiveButton(""+ getString(R.string.label_Non), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
                    .setNegativeButton("" + getString(R.string.label_WI), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            QuestionnaireMenageActivity.super.onKeyDown(_keyCode, _event);
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
    public RadioListAdapter.OnItemClickListener getItemClickListener() {
        return new RadioListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(QuestionReponseModel questionReponseModel) {
                codeReponseRecyclerView = questionReponseModel;
                if (codeReponseRecyclerView != null) {
                    if (QF.getEstSautReponse()) {// SI LE SAUT DEPEND DE LA REPONSE
                        if (!codeReponseRecyclerView.getCodeReponse().trim().equalsIgnoreCase("")) {
                            if (!codeReponseRecyclerView.getQSuivant().equalsIgnoreCase(""))
                                QF.setqSuivant(codeReponseRecyclerView.getQSuivant());
                        }
                    }
                    QF.SetButtonLabel(QuestionnaireMenageActivity.this, codeReponseRecyclerView.getCodeReponse(), QF, btn_Suivant);
                }
                message = questionReponseModel.toString();
                ToastUtility.ToastMessage(QuestionnaireMenageActivity.this, message, Constant.GravityBottom);
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
                ToastUtility.ToastMessage(QuestionnaireMenageActivity.this, message, Constant.GravityBottom);
                Suivant_Click();
            }
        };
    }
    //endregion

    //region GET DATA : GetFieldValuesQuestionInfo- ...
    private void GetFieldValuesQuestionInfo(QuestionnaireFormulaireUtility qf) {
        try {
            codeReponseRecyclerView=null; codeReponseKeyValueModel=null;
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

            /* Add Individu dans Menage */
            ShowPopUp_AddIndividus();

            /* Add Individu dans Menage */
            ContinuerAvecIndividu();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//
    //endregion

    //region PopUp Add LISTE Individus DANS MENAGE
    public void ShowPopUp_AddIndividus(){
        try{
            if ( QF.getTbl_TableName() == Constant.FORMULAIRE_MENAGE ) {
                //region [ Qm11CallFormListeIndividu ]
                if (QF.getNomChamps().equalsIgnoreCase(Constant.Qm11CallFormListeIndividu)) {
                    // Save Formulaire D'abord
                    QF.SaveInfoDefinitivement(cuRecordMngr, false);

                    long Nbre_Individu_DejaSave = queryRecordMngr.countIndByMenage(QF.getMenageModel().getMenageId());
                    int nbrInd_NoOrdre = ((int) Nbre_Individu_DejaSave + 1);
                    int Nbre_TotalIndividu = QF.getMenageModel().getQm2TotalIndividuVivant();

                    if ( Nbre_TotalIndividu > 0 ){
                        // On Affiche le popUp pour la saisie des individus
                        //ShowListInformations(Constant.FORMULAIRE_INDIVIDUS,  Nbre_TotalIndividu);

                        dialog = new Dialog(this);
                        dialog.setContentView(R.layout.individus_form);
                        dialog.setCancelable(false);
                        scrollView2 = (ScrollView) dialog.findViewById(R.id.scrollView2);
                        LinearLayout LLGrandTitre = (LinearLayout) dialog.findViewById(R.id.LLGrandTitre);
                        LLGrandTitre.setVisibility(View.GONE);
                        LinearLayout LL_ListeView = (LinearLayout) dialog.findViewById(R.id.LL_ListeView);
                        LinearLayout LL_FormulaireAdd = (LinearLayout) dialog.findViewById(R.id.LL_FormulaireAdd);
                        tv_NumeroIndividu = (TextView) dialog.findViewById(R.id.tv_NumeroIndividu);
                        TextView tv_GrandTitreInd = (TextView) dialog.findViewById(R.id.tv_GrandTitre);

                        //message = "" + "<b>Menaj " + QF.getMenageModel().getQm1NoOrdre() + "</b>";
                        //tv_GrandTitreInd.setText(Html.fromHtml("" + message));

                        if (Nbre_TotalIndividu == Nbre_Individu_DejaSave) {
                            // On lui permet de voir la liste des personnes deja enregistrer.
                            //dialog.setTitle("Moun ki nan menaj sa [" + Nbre_TotalIndividu + "]");
                            LLGrandTitre.setVisibility(View.VISIBLE);
                            tv_GrandTitreInd.setText(Html.fromHtml("Ou antre <b>[" + Nbre_Individu_DejaSave +  "/"+ Nbre_TotalIndividu +"] Moun</b> pou Menaj "+ QF.getMenageModel().getQm1NoOrdre() +" sa"));
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
                            mAdapter = new DisplayListAdapter(this, targetList, Constant.LIST_MODULE_INDIVIDU_LIST_ONLY, profilId, moduleStatut);
                            mAdapter.setOnMenuItemClickListener(null);
                            //inject the adapter into the recycle view
                            recyclerView.setAdapter(mAdapter);
                            targetList = queryRecordMngr.searchListIndividu_ByMenage(QF.getMenageModel().getMenageId());
                            mAdapter.setFilter(targetList);
                            //new AsynDisplayDataList_IndividuTask().execute();
                            /*if (dialog != null) {
                                dialog.dismiss();
                            }
                            Suivant_Click();*/
                        }else{
                            //dialog.setTitle("Ajoute Moun nan menaj sa [" + nbrInd_NoOrdre + " / " + Nbre_TotalIndividu + "]");
                            tv_GrandTitreInd.setText("Ajoute Moun nan menaj sa [" + nbrInd_NoOrdre + " / " + Nbre_TotalIndividu + "]");
                            LL_FormulaireAdd.setVisibility(View.VISIBLE);
                            LL_ListeView.setVisibility(View.GONE);

                            et_NonIndividu = (EditText) dialog.findViewById(R.id.et_NonIndividu);
                            //et_NonIndividu.addTextChangedListener(TextChanged_NonListener);
                            et_SiyatiIndividu = (EditText) dialog.findViewById(R.id.et_SiyatiIndividu);
                            sp_Sexe = (Spinner) dialog.findViewById(R.id.sp_Sexe);
                            QF.Load_Sexe(this, sp_Sexe);

                            sp_RelasyonMounNan = (Spinner) dialog.findViewById(R.id.sp_RelasyonMounNan);
                            QF.Load_Relation(this, sp_RelasyonMounNan);

                            tv_RelasyonMounNan = (TextView) dialog.findViewById(R.id.tv_RelasyonMounNan);
                            tv_DateMounNanfet = (TextView) dialog.findViewById(R.id.tv_DateNaissance);
                            tv_LageMounNan = (TextView) dialog.findViewById(R.id.tv_LageMounNan);
                            RL_RelasyonMounNan = (RelativeLayout) dialog.findViewById(R.id.RL_RelasyonMounNan);
                            LL_DateMounNanfet = (LinearLayout) dialog.findViewById(R.id.LL_DateNaissance);
                            LL_LajMounNan = (LinearLayout) dialog.findViewById(R.id.LL_LajMounNan);

                            et_JourIndividu = (EditText) dialog.findViewById(R.id.et_DateNaissanceJour);
                            //QF.Load_Jour(this, sp_JourIndividu);

                            sp_MoisIndividu = (Spinner) dialog.findViewById(R.id.sp_DateNaissanceMois);
                            QF.Load_Mois(this, sp_MoisIndividu);

                            et_AnneeIndividu = (EditText) dialog.findViewById(R.id.et_DateNaissanceAnnee);
                            //QF.Load_Annee(this, sp_AnneeIndividu);

                            sp_MounNanMenajLa = (Spinner) dialog.findViewById(R.id.sp_MounNanMenajLa);
                            QF.Load_MounNanMenajLa(this, sp_MounNanMenajLa);
                            sp_MounNanMenajLa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    //region TEST POUR LE CHARGEMENT
                                    try{
                                         KeyValueModel keyValueRezon = null;
                                        if (parent.getId() == R.id.sp_MounNanMenajLa) {
                                            keyValueRezon = ((KeyValueModel) sp_MounNanMenajLa.getSelectedItem());
                                        }

                                        tv_RelasyonMounNan.setVisibility(View.VISIBLE);
                                        RL_RelasyonMounNan.setVisibility(View.VISIBLE);
                                        tv_DateMounNanfet.setVisibility(View.VISIBLE);
                                        LL_DateMounNanfet.setVisibility(View.VISIBLE);
                                        tv_LageMounNan.setVisibility(View.VISIBLE);
                                        LL_LajMounNan.setVisibility(View.VISIBLE);

                                        if (keyValueRezon != null){
                                            if( !keyValueRezon.getKey().trim().equalsIgnoreCase("")) {
                                                if (parent.getId() == R.id.sp_MounNanMenajLa) {
                                                    if( keyValueRezon.getKey().equalsIgnoreCase(""+ Constant.WI_LI_TOUJOU_LA_MEN_LI_OKIPE_NAN_ZAFE_MANJE_A_PA_02) ||
                                                            keyValueRezon.getKey().equalsIgnoreCase(""+ Constant.WI_MEN_LI_DEPLASE_POU_PLIS_PASE_6_MWA_04) ||
                                                            keyValueRezon.getKey().equalsIgnoreCase(""+ Constant.LI_FENK_VINI_LI_GEN_LI_GEN_ENTANSYON_RETE_LA_08) ||
                                                            keyValueRezon.getKey().equalsIgnoreCase(""+ Constant.LI_NAN_KAY_LA_POU_MWENS_KE_6_MWA_09) ){
                                                        tv_RelasyonMounNan.setVisibility(View.GONE);
                                                        RL_RelasyonMounNan.setVisibility(View.GONE);
                                                        tv_DateMounNanfet.setVisibility(View.GONE);
                                                        LL_DateMounNanfet.setVisibility(View.GONE);
                                                        tv_LageMounNan.setVisibility(View.GONE);
                                                        LL_LajMounNan.setVisibility(View.GONE);
                                                    }
                                                }
                                            }
                                        }
                                    }catch (Exception ex)        {
                                        ToastUtility.LogCat("Exception :-: sp_MounNanMenajLa:onItemSelected(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
                                        ex.printStackTrace();
                                    }
                                    //endregion
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                            et_AgeIndividu = (EditText) dialog.findViewById(R.id.et_AgeIndividu);
                            //QF.Load_Age(this, et_AgeIndividu);

                            tv_NumeroIndividu.setText("Moun " + nbrInd_NoOrdre);

                            // On recherche les Individus par numero d'ordre et par IdMenage
                            ID_INDIVIDU = 0;
                            QF.SetValue_Individu(queryRecordMngr, dialog, ID_INDIVIDU, nbrInd_NoOrdre, Nbre_TotalIndividu, tv_NumeroIndividu, et_NonIndividu, et_SiyatiIndividu, sp_Sexe, sp_RelasyonMounNan, sp_MounNanMenajLa);
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
                                QF.context = QuestionnaireMenageActivity.this;
                                try {
                                    int Nbre_TotalIndividu_Declarer = QF.getMenageModel().getQm2TotalIndividuVivant();
                                    long Nbre_Individu_DejaSave = queryRecordMngr.countIndByMenage(QF.getMenageModel().getMenageId());
                                    int NoOrdreIndividu = ((int) Nbre_Individu_DejaSave + 1);

                                    if( Nbre_Individu_DejaSave < Nbre_TotalIndividu_Declarer ) {
                                        IndividuModel indModel = QF.CheckIndividu_ValueBefore_AndSave( queryRecordMngr, cuRecordMngr, ID_INDIVIDU, NoOrdreIndividu, et_NonIndividu, et_SiyatiIndividu
                                                , sp_Sexe, sp_RelasyonMounNan, et_JourIndividu, sp_MoisIndividu, et_AnneeIndividu, et_AgeIndividu, sp_MounNanMenajLa);
                                        Nbre_Individu_DejaSave = queryRecordMngr.countIndByMenage(QF.getMenageModel().getMenageId());
                                        NoOrdreIndividu = ((int) Nbre_Individu_DejaSave + 1);
                                    }

                                    if( Nbre_Individu_DejaSave < Nbre_TotalIndividu_Declarer ){
                                        et_NonIndividu.setText(null);
                                        et_SiyatiIndividu.setText(null);
                                        sp_Sexe.setSelection(0);
                                        sp_RelasyonMounNan.setSelection(0);
                                        et_JourIndividu.setText("");
                                        sp_MoisIndividu.setSelection(0);
                                        et_AnneeIndividu.setText("");
                                        et_AgeIndividu.setText("");
                                        et_NonIndividu.requestFocus();
                                        sp_MounNanMenajLa.setSelection(0);
                                        ID_INDIVIDU = 0;

                                        // On recherche les Individus par numero d'ordre et par IdMenage
                                        QF.SetValue_Individu(queryRecordMngr,  dialog, ID_INDIVIDU, NoOrdreIndividu, Nbre_TotalIndividu_Declarer, tv_NumeroIndividu, et_NonIndividu, et_SiyatiIndividu, sp_Sexe, sp_RelasyonMounNan, sp_MounNanMenajLa);

                                    }else if( Nbre_Individu_DejaSave == Nbre_TotalIndividu_Declarer ){
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                        Suivant_Click();
                                    }
                                } catch (TextEmptyException ex) {
                                    Tools.AlertDialogMsg(QuestionnaireMenageActivity.this, ex.getMessage());
                                    ToastUtility.LogCat("TextEmptyException: btnQuitter.setOnClickListener() :" + ex.getMessage()+" \n "+ ex.toString());
                                } catch (Exception ex) {
                                    ToastUtility.ToastMessage(QuestionnaireMenageActivity.this, ex.getMessage()+" \n "+ ex.toString());
                                    ToastUtility.LogCat("Exception: btnQuitter.setOnClickListener() :" + ex.getMessage()+" \n "+ ex.toString());
                                }
                            }
                        });
                        dialog.show();
                    }
                }
                //endregion

                //region [ CallFormListeAncienMembreMenage ]
                if( QF.getNomChamps().equalsIgnoreCase( Constant.CallFormListeAncienMembreMenage ) ) {

                }
                //endregion

            }
        }catch (TextEmptyException  ex) {
            Tools.AlertDialogMsg(this, ex.getMessage());
            ToastUtility.LogCat("TextEmptyException: Suivant_Click :" + ex.getMessage());
        }catch (ManagerException ex) {
            message = "Erreur au niveau du Manager";
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ToastUtility.LogCat("ManagerException: Suivant_Click :" + ex.getMessage());
        } catch (Exception ex) {
            message = "Erreur au niveau de la sauvegarde";
            ToastUtility.LogCat("Exception: Suivant_Click :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }
    //endregion

    //region PopUp Add Emigrer | Deces | Individu | Menage_EnBoucle
    private void AddMenage_EnBoucle() {
        try {
            // Gestionnaire de statut du menage
            QF.SetStatutMenage(queryRecordMngr, cuRecordMngr);

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
                int nbr_ou_NoOrdre = ((int) nbreTotalMenage_DejaSave + 1);
                if (nbre_TotalMenage == nbreTotalMenage_DejaSave) {
                    // On lui permet de voir la liste des personnes deja enregistrer.
                    // Calcul Statut du Menage en generale
                    finish();
                    if( QuestionnaireLogementActivity.CounterForMenage_LogInd >= nbre_TotalMenage  ) {
                        finish();
                    }else{
                        // On selectionne le Logement qui n'a pas un statut FINI
                        MenageModel MenModel = null;
                        do{
                            MenModel = queryRecordMngr.searchMenage_ByNoOrdre_ByIdLogement( QuestionnaireLogementActivity.CounterForMenage_LogInd,  QF.getLogementModel().getLogeId() );
                            QuestionnaireLogementActivity.CounterForMenage_LogInd += 1;
                        }while (MenModel == null && QuestionnaireLogementActivity.CounterForMenage_LogInd <= nbre_TotalMenage );

                        if ( MenModel != null ) {
                            //region "MenageModel"
                            String dateDebutCollect = Tools.getDateString_MMddyyyy_HHmmss_a();
                            menageM_OBJ = new MenageModel();
                            MenageModel.queryRecordMngr = queryRecordMngr;
                            menageM_OBJ.setSdeId(QF.getLogementModel().getSdeId());
                            menageM_OBJ.setQm1NoOrdre((short) nbr_ou_NoOrdre);
                            // POUR BATIMENT
                            menageM_OBJ.setBatimentId(QF.getLogementModel().getBatimentId());
                            menageM_OBJ.setObjBatiment(QF.getBatimentModel());
                            // POUR LOGEMENT
                            menageM_OBJ.setLogeId(QF.getLogementModel().getLogeId());
                            menageM_OBJ.setObjLogement(QF.getLogementModel());
                            menageM_OBJ.setDateDebutCollecte(dateDebutCollect.toString());
                            // Objet Batimtnent
                            menageM_OBJ.setBatimentId(QF.getBatimentModel().getBatimentId());
                            menageM_OBJ.setObjBatiment(QF.getBatimentModel());
                            menageM_OBJ=MenModel;

                            ModuleModel moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_MENAGE, Constant.ACTIF);
                            QF = new QuestionnaireFormulaireUtility(moduleModel, menageM_OBJ, Constant.FORMULAIRE_MENAGE, formDataMngr);
                            QF.context = QuestionnaireMenageActivity.this;
                            if(menageM_OBJ!=null && menageM_OBJ.getDateDebutCollecte()!=null && !menageM_OBJ.getDateDebutCollecte().equalsIgnoreCase("") ) {
                                dateDebutCollect = menageM_OBJ.getDateDebutCollecte();
                                QF.setDateDebutCollecte(dateDebutCollect);
                            }else{
                                QF.setDateDebutCollecte(dateDebutCollect);
                            }
                            GetFieldValuesQuestionInfo(QF);
                            headerFormOne = "MODIFYE MENAJ " + logementM_OBJ.getQlin1NumeroOrdre() + "/" + nbre_TotalMenage;
                            headerFormTwo = " Lojman Endividèl " + QF.getLogementModel().getQlin1NumeroOrdre()
                                    + " | Batiman " + QF.getLogementModel().getBatimentId();

                            tvHeaderOne.setText(headerFormOne.toUpperCase());
                            tvHeaderTwo.setText(headerFormTwo.toUpperCase());
                            tempInfoQuestions = new ArrayList<TempInfoQuestion>();
                            toolbar.setTitle(headerFormOne);

                            message = "Kontinye pran enfòmasyon sou Menaj " + menageM_OBJ.getQm1NoOrdre() + " an";
                            ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                            //Tools.AlertDialogMsg(this, message, "S");
                            //endregion
                        }else {
                            if ( nbre_TotalMenage == nbreTotalMenage_DejaSave ) {
                                //
                                if (QF.getqSuivant().toString().equalsIgnoreCase(Constant.FIN)) {
                                    finish();
                                }else {
                                    // On Passe a la question suivante
                                    Suivant_Click();
                                }
                            } else {
                                Precedent_Click(QF);
                            }
                        }
                    }
                } else {
                    //region menageModel
                    String dateDebutCollect = Tools.getDateString_MMddyyyy_HHmmss_a();
                    MenageModel menageModel = new MenageModel();
                    MenageModel.queryRecordMngr = queryRecordMngr;
                    menageModel.setSdeId(QF.getLogementModel().getSdeId());
                    menageModel.setQm1NoOrdre((short) nbr_ou_NoOrdre);
                    // POUR BATIMENT
                    menageModel.setBatimentId(QF.getLogementModel().getBatimentId());
                    menageModel.setObjBatiment(QF.getBatimentModel());
                    // POUR LOGEMENT
                    menageModel.setLogeId(QF.getLogementModel().getLogeId());
                    menageModel.setObjLogement(QF.getLogementModel());
                    menageModel.setDateDebutCollecte(dateDebutCollect.toString());

                    ModuleModel moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_MENAGE, Constant.ACTIF);
                    QF = new QuestionnaireFormulaireUtility(moduleModel, menageModel, Constant.FORMULAIRE_MENAGE, formDataMngr);
                    QF.context = QuestionnaireMenageActivity.this;
                    if(menageModel!=null && menageModel.getDateDebutCollecte()!=null && !menageModel.getDateDebutCollecte().equalsIgnoreCase("") ) {
                        dateDebutCollect = menageModel.getDateDebutCollecte();
                        QF.setDateDebutCollecte(dateDebutCollect);
                    }else{
                        QF.setDateDebutCollecte(dateDebutCollect);
                    }
                    GetFieldValuesQuestionInfo(QF);
                    headerFormOne = "AJOUTE MENAJ " + nbr_ou_NoOrdre + " /" + nbre_TotalMenage;
                    headerFormTwo = " Lojman Endividèl " + QF.getLogementModel().getQlin1NumeroOrdre()
                            + " | Batiman " + QF.getLogementModel().getBatimentId();// + " | REC: " + QF.getLogementModel().getObjBatiment().getQrec();

                    tvHeaderOne.setText(headerFormOne.toUpperCase());
                    tvHeaderTwo.setText(headerFormTwo.toUpperCase());
                    tempInfoQuestions = new ArrayList<TempInfoQuestion>();
                    toolbar.setTitle(headerFormOne);
                    int ouDejaAntre = nbr_ou_NoOrdre - 1;

                    message = "Kòmanse pran enfòmasyon sou Menaj " + nbr_ou_NoOrdre + " an!";
                    ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                    //Tools.AlertDialogMsg(this, message, "S");
                    //endregion
                }
            }
        } catch (ManagerException ex) {
            message = "Erreur:";
            Tools.AlertDialogMsg(this, message + "\n" + ex.toString());
            ToastUtility.LogCat("ManagerException: AddIndividu_LogementCollectif :" + ex.toString());
        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: AddIndividu_LogementCollectif :" + message + " / " + ex.toString());
            Tools.AlertDialogMsg(this, message + "\n" + ex.toString());
            ex.printStackTrace();
        }
    }

    private void ContinuerAvecIndividu() {
        try{
            if ( QF.getTbl_TableName() == Constant.FORMULAIRE_MENAGE ) {
                if (QF.getNomChamps().equalsIgnoreCase(Constant.CallFormulaireIndividuMenage)) {
                    // Save Formulaire D'abord
                    QF.SaveInfoDefinitivement(cuRecordMngr, false);

                    int Nbre_TotalIndividu = 0;
                    if (QF.getMenageModel() != null && QF.getMenageModel().getQm2TotalIndividuVivant() != null) {
                        Nbre_TotalIndividu = QF.getMenageModel().getQm2TotalIndividuVivant();
                    }
                    // On verifie s'il existe d'individu dans le menage
                    if (Nbre_TotalIndividu > 0) {
                        // On verifie s'il existe d'individu deja enregistrer dans le menage
                        long Nbre_Individu_DejaSave = queryRecordMngr.countIndByMenage(QF.getMenageModel().getMenageId());
                        int nbr_ou_NoOrdre = ((int) Nbre_Individu_DejaSave + 1);
                        if (Nbre_TotalIndividu == Nbre_Individu_DejaSave) {
                           /*/ Ici on doit Afficher le formulaire du premier individu qui n'est pas encore fini
                            // Et qui est soit remplit Totalement ou pas */
                            if (CounterForIndividu >= Nbre_TotalIndividu) {
                                // On lui permet de voir la liste des personnes deja enregistrer dans le menage.
                                ShowListInformations(Constant.FORMULAIRE_INDIVIDUS, Nbre_TotalIndividu);
                            } else {
                                int NoOrdreLog = 0;
                                // On selectionne l'individu qui n'a pas un statut FINI
                                IndividuModel indM = null;
                                do {
                                    NoOrdreLog += 1;
                                    CounterForIndividu = NoOrdreLog;
                                    indM = queryRecordMngr.searchIndividu_ByNoOrdre_ByIdMenage(NoOrdreLog, QF.getMenageModel().getMenageId(), true);
                                }
                                while (indM == null && CounterForIndividu <= Nbre_TotalIndividu);

                                if (indM != null) {
                                    this.SetFieldIndividu(indM, Nbre_TotalIndividu, Constant.ACTION_MOFIDIER);

                                    message = "Kontinye pran enfòmasyon sou Moun " + indM.getQ1NoOrdre() + " la";
                                    ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                                    //Tools.AlertDialogMsg(this, message, "S");
                                }else {
                                    if ( Nbre_TotalIndividu == Nbre_Individu_DejaSave) {
                                        // On lui permet de voir la liste des personnes deja enregistrer.
                                        // On Passe a la question suivante
                                        ShowListInformations(Constant.FORMULAIRE_INDIVIDUS, Nbre_TotalIndividu);
                                        //Suivant_Click();
                                    } else {
                                        Precedent_Click(QF);
                                    }
                                }
                            }
                        }else{
                            String dateDebutCollect = Tools.getDateString_MMddyyyy_HHmmss_a();
                            IndividuModel individuM_OBJ = new IndividuModel();
                            IndividuModel.queryRecordMngr = queryRecordMngr;
                            individuM_OBJ.setSdeId(QF.getLogementModel().getSdeId());
                            individuM_OBJ.setQ1NoOrdre((short) nbr_ou_NoOrdre);
                            individuM_OBJ.setDateDebutCollecte(dateDebutCollect);

                            this.SetFieldIndividu(individuM_OBJ, Nbre_TotalIndividu, Constant.ACTION_MOFIDIER);
                            message = "Kòmanse pran enfòmasyon sou Moun " + nbr_ou_NoOrdre + " an ";
                            ToastUtility.ToastMessage(this, message, Constant.GravityCenter);
                        }
                    }
                }
            }
        }catch (ManagerException ex) {
            message = "Erreur:";
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ToastUtility.LogCat("ManagerException: ContinuerAvecIndividu :" + ex.toString());
        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: ContinuerAvecIndividu :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.toString());
            ex.printStackTrace();
        }
    }//

    private void SetFieldIndividu(IndividuModel individuModel, int nbre_TotalIndividu, int actions) {
        try{
            String dateDebutCollect = Tools.getDateString_MMddyyyy_HHmmss_a();
            IndividuModel IndM_OBJ = new IndividuModel();
            //EmigreModel.queryRecordMngr = queryRecordMngr;
            IndM_OBJ.setSdeId(QF.getLogementModel().getSdeId());
            //IndM_OBJ.setQp1NoOrdre((short) nbr_ou_NoOrdre);
            IndM_OBJ.setDateDebutCollecte(dateDebutCollect.toString()) ;
            IndM_OBJ = individuModel;
            // Objet Batimtnent
            IndM_OBJ.setBatimentId(QF.getBatimentModel().getBatimentId());
            IndM_OBJ.setObjBatiment(QF.getBatimentModel());
            // Objet Logement
            IndM_OBJ.setLogeId(QF.getLogementModel().getLogeId());
            IndM_OBJ.setObjLogement(QF.getLogementModel());
            // Objet Menage
            IndM_OBJ.setMenageId(QF.getMenageModel().getMenageId());
            IndM_OBJ.setObjMenage(QF.getMenageModel());

            String actionStr = "NOUVO MOUN";
            if ( actions == Constant.ACTION_MOFIDIER ){
                actionStr = " MODIFYE MOUN";
            }

            ModuleModel moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_INDIVIDUS, Constant.ACTIF);
            Intent intent = new Intent(this, QuestionnaireIndividuActivity.class);
            QFD = new QuestionnaireFormulaireUtility( moduleModel, IndM_OBJ, Constant.FORMULAIRE_INDIVIDUS, formDataMngr);

            if(IndM_OBJ!=null && IndM_OBJ.getDateDebutCollecte()!=null && !IndM_OBJ.getDateDebutCollecte().equalsIgnoreCase("") ) {
                dateDebutCollect = IndM_OBJ.getDateDebutCollecte();
                QFD.setDateDebutCollecte(dateDebutCollect);
            }else{
                QFD.setDateDebutCollecte(dateDebutCollect);
            }
            intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QFD);
            intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, actionStr + IndM_OBJ.getQ1NoOrdre() +"/" + nbre_TotalIndividu);
            intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, " Menaj " +  QF.getMenageModel().getQm1NoOrdre()
                    + " | Lojman Endividyèl " +  QF.getLogementModel().getQlin1NumeroOrdre()
                    + " | Batiman " +  QF.getLogementModel().getBatimentId());
            startActivity(intent);

        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: SetFieldIndividu :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.toString());
            ex.printStackTrace();
        }
    }
   /* private void SetFieldEmigre(EmigreModel emigreModel, int nbre_TotalMenage, int actions) {
        try{
            String dateDebutCollect = Tools.getDateString_MMddyyyy_HHmmss_a();
            EmigreModel emigreM_OBJ = new EmigreModel();
            //EmigreModel.queryRecordMngr = queryRecordMngr;
            emigreM_OBJ.setSdeId(QF.getLogementModel().getSdeId());
            //emigreM_OBJ.setQp1NoOrdre((short) nbr_ou_NoOrdre);
            emigreM_OBJ.setDateDebutCollecte(dateDebutCollect) ;
            emigreM_OBJ = emigreModel;
            // Objet Batimtnent
            emigreM_OBJ.setBatimentId(QF.getBatimentModel().getBatimentId());
            emigreM_OBJ.setObjBatiment(QF.getBatimentModel());
            // Objet Logement
            emigreM_OBJ.setLogeId(QF.getLogementModel().getLogeId());
            emigreM_OBJ.setObjLogement(QF.getLogementModel());
            // Objet Menage
            emigreM_OBJ.setMenageId(QF.getMenageModel().getMenageId());
            emigreM_OBJ.setObjMenage(QF.getMenageModel());

            String actionStr = "NOUVO EMIGRE";
            if ( actions == Constant.ACTION_MOFIDIER ){
                actionStr = " MODIFYE EMIGRE";
            }

            ModuleModel moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_EMIGRE, Constant.ACTIF);
            Intent intent = new Intent(this, QuestionnaireEmigreActivity.class);
            QFD = new QuestionnaireFormulaireUtility( moduleModel, emigreM_OBJ, Constant.FORMULAIRE_EMIGRE, formDataMngr);

            if(emigreM_OBJ!=null && emigreM_OBJ.getDateDebutCollecte()!=null && !emigreM_OBJ.getDateDebutCollecte().equalsIgnoreCase("") ) {
                dateDebutCollect = emigreM_OBJ.getDateDebutCollecte();
                QFD.setDateDebutCollecte(dateDebutCollect);
            }else{
                QFD.setDateDebutCollecte(dateDebutCollect);
            }
            intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QFD);
            intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, actionStr + emigreM_OBJ.getQn1numeroOrdre() +"/" + nbre_TotalMenage);
            intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, " Menaj " +  QF.getMenageModel().getQm1NoOrdre()
                    + " | Lojman Endividyèl " +  QF.getLogementModel().getQlin1NumeroOrdre()
                    + " | Batiman " +  QF.getLogementModel().getBatimentId());
            startActivity(intent);

        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: SetFieldEmigrer :", ex);
            Tools.AlertDialogMsg(this, message +"\n"+ ex.toString());
            ex.printStackTrace();
        }
    }
    private void SetFieldDeces(DecesModel decesModel, int nbre_TotalMenage, int actions) {
        try{
            String dateDebutCollect = Tools.getDateString_MMddyyyy_HHmmss_a();
            DecesModel decesM_OBJ = new DecesModel();
            //EmigreModel.queryRecordMngr = queryRecordMngr;
            decesM_OBJ.setSdeId(QF.getLogementModel().getSdeId());
            //decesM_OBJ.setQp1NoOrdre((short) nbr_ou_NoOrdre);
            decesM_OBJ.setDateDebutCollecte(dateDebutCollect.toString()) ;
            decesM_OBJ = decesModel;
            // Objet Batimtnent
            decesM_OBJ.setBatimentId(QF.getBatimentModel().getBatimentId());
            decesM_OBJ.setObjBatiment(QF.getBatimentModel());
            // Objet Logement
            decesM_OBJ.setLogeId(QF.getLogementModel().getLogeId());
            decesM_OBJ.setObjLogement(QF.getLogementModel());
            // Objet Menage
            decesM_OBJ.setMenageId(QF.getMenageModel().getMenageId());
            decesM_OBJ.setObjMenage(QF.getMenageModel());

            String actionStr = "NOUVO MOUN MOURI";
            if ( actions == Constant.ACTION_MOFIDIER ){
                actionStr = " MODIFYE MOUN MOURI";
            }

            ModuleModel moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_DECES, Constant.ACTIF);
            Intent intent = new Intent(this, QuestionnaireDecesActivity.class);
            QFD = new QuestionnaireFormulaireUtility( moduleModel, decesM_OBJ, Constant.FORMULAIRE_DECES, formDataMngr);

            if(decesM_OBJ!=null && decesM_OBJ.getDateDebutCollecte()!=null && !decesM_OBJ.getDateDebutCollecte().equalsIgnoreCase("") ) {
                dateDebutCollect = decesM_OBJ.getDateDebutCollecte();
                QFD.setDateDebutCollecte(dateDebutCollect);
            }else{
                QFD.setDateDebutCollecte(dateDebutCollect);
            }
            intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QFD);
            intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, actionStr + decesM_OBJ.getQd2NoOrdre() +"/" + nbre_TotalMenage);
            intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, " Menaj " +  QF.getMenageModel().getQm1NoOrdre()
                    + " | Lojman Endividyèl " +  QF.getLogementModel().getQlin1NumeroOrdre()
                    + " | Batiman " +  QF.getLogementModel().getBatimentId());
            startActivity(intent);

        } catch (Exception ex) {
            message = "Erreur:";
            ToastUtility.LogCat("Exception: SetFieldDeces :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.toString());
            ex.printStackTrace();
        }
    }*/
 //endregion

    //region "METHODES - Goto_Question Suivante / Precedent"
    public void Suivant_Click() {
        try {
            QF.context = QuestionnaireMenageActivity.this;

            QF.CheckValueBefore( queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Menage, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                    ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                    , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                    , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                    , et_Jour, sp_Mois2 , et_Annee );
            if (QF.getqSuivant().toString().equalsIgnoreCase(Constant.FIN)) {
                // Enregistrement des informations
                QF.SaveInfoDefinitivement(cuRecordMngr, true );

                // On met le boucle ici pour les individu
                //AddMenage_EnBoucle();

                int statut = QF.SetStatutMenage(queryRecordMngr, cuRecordMngr);
                // On fait appel au Rapport de l'agent recenseur
                QF.context = QuestionnaireMenageActivity.this;
                this.ShowRapport_RAR(statut);
            } else {
                if (JumpToNextQuestion) {
                    qPrecedent = QF.getCodeQuestion();
                    Goto_QuestionSuivante(QF);
                }
            }
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
            QF.context = QuestionnaireMenageActivity.this;
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
                                    QF.CheckValueBefore(queryRecordMngr, cuRecordMngr, Constant.SetValueTempInfoQuestion_Menage, et_Reponse, sp_Reponse, sp_Reponse2, sp_Reponse3, sp_Jour, sp_Mois, sp_Annee
                                            ,et_Gason, et_Fi, et_ApareyRadyo, et_Televizyon, et_FrijideFrize, et_FouElektrikFouAkGaz, et_OdinatePCLaptopTabletNimerik, et_BisikletMotosiklet, et_VwatiMachin
                                            , et_KannotBato, et_InvetePanoSoleJeneratrisDelko, et_MiletChwalBourik, et_BefVach, et_KochonKabrit, et_BetVolayPoulKok
                                            , recyclerViewReponse, codeReponseRecyclerView, codeReponseKeyValueModel
                                            , et_Jour, sp_Mois2 , et_Annee );
                                    // Enregistrement des informations
                                    QF.SaveInfoDefinitivement(cuRecordMngr, false);

                                    //ShowRapport_RAR(Constant.STATUT_PA_FIN_RANPLI_22);
                                    //finish();
                                } catch (TextEmptyException ex) {
                                    Tools.AlertDialogMsg(QuestionnaireMenageActivity.this, ex.getMessage());
                                    ToastUtility.LogCat("TextEmptyException: CreateMenuContext() :" + ex.getMessage());
                                } catch (ManagerException ex) {
                                    ToastUtility.LogCat("Exception: CreateMenuContext() :" + ex.getMessage() + " / toString: " + ex.toString());
                                    ToastUtility.ToastMessage(QuestionnaireMenageActivity.this, ex.getMessage());
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
                        , et_Jour, sp_Mois2 , et_Annee);
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

    //region LISTE INFOS
    public void ShowListInformations(int typeFormulaire, int Nbre_TotalElement){
        try {
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.individus_form);
            dialog.setCancelable(false);
            LinearLayout LL_ListeView = (LinearLayout) dialog.findViewById(R.id.LL_ListeView);
            //LinearLayout LLGrandTitre = (LinearLayout) dialog.findViewById(R.id.LLGrandTitre);
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
            recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view);

            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            // Attach the layout manager to the recycler view
            gridLayoutManager.setAutoMeasureEnabled(true);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
            recyclerView.setHasFixedSize(true);
            String titleSet = "";

            if( typeFormulaire == Constant.FORMULAIRE_INDIVIDUS ){
                // Liste des Individus par Menage
                //  initialize the adapter
                CounterForIndividu = 1;
                mAdapter = new DisplayListAdapter(this, targetList, Constant.LIST_MODULE_INDIVIDU_LIST_ONLY);
                mAdapter.setOnMenuItemClickListener(null);
                //inject the adapter into the recycle view
                recyclerView.setAdapter(mAdapter);
                //dialog.setTitle(Html.fromHtml("<b> Menage " + QF.getMenageModel().getQm1NoOrdre() + "</b>"));
                titleSet = "List moun pou Menaj "+ QF.getMenageModel().getQm1NoOrdre();
                dialog.setTitle( Html.fromHtml(titleSet));
                message = "Ou antre [" + Nbre_TotalElement + "] moun pou Menaj sa";
                tv_GrandTitreInd.setText(Html.fromHtml("Ou antre <b>[" + Nbre_TotalElement +  "/"+  QF.getMenageModel().getQm2TotalIndividuVivant() +"] Moun</b> pou Menaj "+ QF.getMenageModel().getQm1NoOrdre() +" sa" ));
                targetList = queryRecordMngr.searchListIndividu_ByMenage(QF.getMenageModel().getMenageId());

           /* }else if( typeFormulaire == Constant.FORMULAIRE_EMIGRE ){
                // Liste des Emigres par Menage
                //  initialize the adapter
                CounterForEmigre = 1;
                mAdapter = new DisplayListAdapter(this, targetList, Constant.LIST_MODULE_EMIGRER_LIST_ONLY);
                mAdapter.setOnMenuItemClickListener(null);
                //inject the adapter into the recycle view
                recyclerView.setAdapter(mAdapter);

                titleSet = "List Emigre pou Menaj "+ QF.getMenageModel().getQm1NoOrdre();
                dialog.setTitle( Html.fromHtml(titleSet));
                tv_GrandTitreInd.setText(Html.fromHtml("Ou antre <b>[" + Nbre_TotalElement +  "/"+  QF.getMenageModel().getQn1NbreEmigre() +"] Emigre</b> pou Menaj "+ QF.getMenageModel().getQm1NoOrdre() +" sa" ));
                targetList = queryRecordMngr.searchListEmigreByMenage(QF.getMenageModel().getMenageId());
*/
            /*}else if( typeFormulaire == Constant.FORMULAIRE_DECES ){
                // Liste des Deces par Menage
                //  initialize the adapter
                CounterForDeces = 1;
                //CounterForIndividu = 1;
                mAdapter = new DisplayListAdapter(this, targetList, Constant.LIST_MODULE_DECES_LIST_ONLY);
                mAdapter.setOnMenuItemClickListener(null);
                //inject the adapter into the recycle view
                //inject the adapter into the recycle view
                recyclerView.setAdapter(mAdapter);

                titleSet = "List Moun ki mouri pou Menaj "+ QF.getMenageModel().getQm1NoOrdre();
                dialog.setTitle( Html.fromHtml(titleSet));
                tv_GrandTitreInd.setText(Html.fromHtml("Ou antre <b>[" + Nbre_TotalElement +  "/"+  QF.getMenageModel().getQd1NbreDecede() +"] Moun ki mouri</b> pou Menaj "+ QF.getMenageModel().getQm1NoOrdre() +" sa" ));
                targetList = queryRecordMngr.searchListDecesByMenage(QF.getMenageModel().getMenageId());
                */
            }

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

                message = String.format(getString(R.string.label_msgInfoRapport), " Menaj ", " Lojman Endividyèl " + QF.getLogementModel().getQlin1NumeroOrdre());
                tv_messageChangerdeModule.setText(Html.fromHtml("" + message));

                dialog.setTitle("" + this.getString(R.string.Rappot_Agent_Resenceur));

                //Shared_Preferences sharedPreferences = null;
                //sharedPreferences = Tools.SharedPreferences(this);

                TextView tv_GrandTitreRap = (TextView) dialog.findViewById(R.id.tv_GrandTitre);
                message = "" + "<b>Rapò sou Menaj </b>";
                if (QF.getMenageModel().getMenageId() != null) {
                    message += "" + "<b> " + QF.getMenageModel().getQm1NoOrdre() + "</b>";
                }
                tv_GrandTitreRap.setText(Html.fromHtml("" + message));
                //region sp_Rezon
                QF.Load_RaisonRAR(this, QF, sp_Rezon, statut);

                sp_Rezon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            // TEST POUR LE CHARGEMENT
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
                            Shared_Preferences sharedPreferences = Tools.SharedPreferences(QuestionnaireMenageActivity.this);
                            QF.CheckValueBefore_RapportRAR(sp_Rezon, et_LotRezon, sharedPreferences);
                            //QF.SaveInfoDefinitivement(cuRecordMngr, false);
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            // Gestionnaire de statut du menage
                            int statut = QF.SetStatutMenage(queryRecordMngr, cuRecordMngr);
                            if( statut == Constant.STATUT_RANPLI_NET_11 ){
                                ShowRapport_RAR_Final();
                            }else {
                                // On met le boucle ici pour le menage
                                AddMenage_EnBoucle();
                            }
                        } catch (TextEmptyException ex) {
                            Tools.AlertDialogMsg(QuestionnaireMenageActivity.this, ex.getMessage());
                            ToastUtility.LogCat("TextEmptyException: ShowRapport_RAR() :" + ex.getMessage());
                        } catch (Exception ex) {
                            ToastUtility.LogCat("Exception: ShowRapport_RAR() :" + ex.getMessage() + " / toString: " + ex.toString());
                            ex.printStackTrace();
                        }
                    }
                });
                //endregion

                //region Buttons btnContinuerEtChangerdeModule
                btnContinuerEtChangerdeModule = (Button) dialog.findViewById(R.id.btnContinuerEtChangerdeModule);
                LinearLayout_messageChangerdeModule.setVisibility(View.VISIBLE);
                Short nbre_TotalMenage = 0;
                if (QF.getLogementModel().getQlin5NbreTotalMenage() != null) {
                    nbre_TotalMenage = QF.getLogementModel().getQlin5NbreTotalMenage();
                }
                if (QuestionnaireLogementActivity.CounterForMenage_LogInd - 1 >= nbre_TotalMenage) {
                    LinearLayout_messageChangerdeModule.setVisibility(View.GONE);
                }
                btnContinuerEtChangerdeModule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Shared_Preferences sharedPreferences = Tools.SharedPreferences(QuestionnaireMenageActivity.this);
                            QF.CheckValueBefore_RapportRAR(sp_Rezon, et_LotRezon, sharedPreferences);
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            QuestionnaireLogementActivity.CounterForMenage_LogInd = QF.getLogementModel().getQlin5NbreTotalMenage();
                            finishAfter = true;

                            if (finishAfter) {
                                finish();
                            } else {
                                // Gestionnaire de statut du menage
                                int statut = QF.SetStatutMenage(queryRecordMngr, cuRecordMngr);
                                if( statut == Constant.STATUT_RANPLI_NET_11 ){
                                    ShowRapport_RAR_Final();
                                }else {
                                    // On met le boucle ici pour le menage
                                    AddMenage_EnBoucle();
                                }
                            }
                        } catch (TextEmptyException ex) {
                            Tools.AlertDialogMsg(QuestionnaireMenageActivity.this, ex.getMessage());
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

    //region PopUp ShowRapport_RAR_Final
    public void ShowRapport_RAR_Final() {
        try {
            if( QF.TypeEvenement != Constant.ACTION_AFFICHER ) {
                dialog = new Dialog(this);
                dialog.setContentView(R.layout.rapport_rar_final);
                dialog.setCancelable(false);
                ScrollView scrollView_RAR_Final = (ScrollView) dialog.findViewById(R.id.scrollView_RAR_Final);

                //TextView tv_GrandTitre2 = (TextView) dialog.findViewById(R.id.tv_GrandTitre);
                //tv_GrandTitre2.setVisibility(View.GONE);
                sp_RepondantPrincipal = (Spinner) dialog.findViewById(R.id.sp_RepondantPrincipal);

                sp_AE_EsKeGenMounKiEde = (Spinner) dialog.findViewById(R.id.sp_AE_EsKeGenMounKiEde);
                tv_AE_IsVivreDansMenage = (TextView) dialog.findViewById(R.id.tv_AE_IsVivreDansMenage);
                RL_AE_IsVivreDansMenage = (RelativeLayout) dialog.findViewById(R.id.RL_AE_IsVivreDansMenage);
                sp_AE_IsVivreDansMenage = (Spinner) dialog.findViewById(R.id.sp_AE_IsVivreDansMenage);

                tv_AE_RepondantQuiAide = (TextView) dialog.findViewById(R.id.tv_AE_RepondantQuiAide);
                RL_AE_RepondantQuiAide = (RelativeLayout) dialog.findViewById(R.id.RL_AE_RepondantQuiAide);
                sp_AE_RepondantQuiAide = (Spinner) dialog.findViewById(R.id.sp_AE_RepondantQuiAide);

                sp_F_EsKeGenMounKiEde = (Spinner) dialog.findViewById(R.id.sp_F_EsKeGenMounKiEde);
                tv_F_IsVivreDansMenage = (TextView) dialog.findViewById(R.id.tv_F_IsVivreDansMenage);
                RL_F_IsVivreDansMenage = (RelativeLayout) dialog.findViewById(R.id.RL_F_IsVivreDansMenage);
                sp_F_IsVivreDansMenage = (Spinner) dialog.findViewById(R.id.sp_F_IsVivreDansMenage);

                tv_F_RepondantQuiAide = (TextView) dialog.findViewById(R.id.tv_F_RepondantQuiAide);
                RL_F_RepondantQuiAide = (RelativeLayout) dialog.findViewById(R.id.RL_F_RepondantQuiAide);
                sp_F_RepondantQuiAide = (Spinner) dialog.findViewById(R.id.sp_F_RepondantQuiAide);

                dialog.setTitle("" + this.getString(R.string.Rappot_Final_Agent_Resenceur));

                long menageId = ((QF.getMenageModel().getMenageId()!=null) ? QF.getMenageModel().getMenageId() : 0);//QF.getMenageModel().getMenageId();

                //region EVB11.- sp_RepondantPrincipal
                QF.Load_IndividuMenage(queryRecordMngr, sp_RepondantPrincipal, menageId );
                //endregion

                //region AE.- AKTIVITE EKONOMIK
                //region EVB21.- sp_AE_EsKeGenMounKiEde
                QF.Load_ReponseRapportFinal(QuestionnaireMenageActivity.this, sp_AE_EsKeGenMounKiEde );
                sp_AE_EsKeGenMounKiEde.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            // TEST POUR LE CHARGEMENT
                            KeyValueModel keyValueRezon = null;
                            if (parent.getId() == R.id.sp_AE_EsKeGenMounKiEde) {
                                keyValueRezon = ((KeyValueModel) sp_AE_EsKeGenMounKiEde.getSelectedItem());
                            }
                            tv_AE_IsVivreDansMenage.setVisibility(View.GONE);
                            RL_AE_IsVivreDansMenage.setVisibility(View.GONE);
                            if (keyValueRezon != null) {
                                if (!keyValueRezon.getKey().trim().equalsIgnoreCase("")) {
                                    if (parent.getId() == R.id.sp_AE_EsKeGenMounKiEde) {
                                        if (keyValueRezon.getKey().equalsIgnoreCase("" + Constant.REPONS_WI_1) ) {
                                            tv_AE_IsVivreDansMenage.setVisibility(View.VISIBLE);
                                            RL_AE_IsVivreDansMenage.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            ToastUtility.LogCat("Exception: sp_AE_EsKeGenMounKiEde:onItemSelected(): ", ex);
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                //endregion

                //region EVB22.- sp_AE_IsVivreDansMenage
                QF.Load_ReponseRapportFinal(QuestionnaireMenageActivity.this, sp_AE_IsVivreDansMenage );
                sp_AE_IsVivreDansMenage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            // TEST POUR LE CHARGEMENT
                            KeyValueModel keyValueRezon = null;
                            if (parent.getId() == R.id.sp_AE_IsVivreDansMenage) {
                                keyValueRezon = ((KeyValueModel) sp_AE_IsVivreDansMenage.getSelectedItem());
                            }
                            tv_AE_RepondantQuiAide.setVisibility(View.GONE);
                            RL_AE_RepondantQuiAide.setVisibility(View.GONE);
                            if (keyValueRezon != null) {
                                if (!keyValueRezon.getKey().trim().equalsIgnoreCase("")) {
                                    if (parent.getId() == R.id.sp_AE_IsVivreDansMenage) {
                                        if (keyValueRezon.getKey().equalsIgnoreCase("" + Constant.REPONS_WI_1) ) {
                                            tv_AE_RepondantQuiAide.setVisibility(View.VISIBLE);
                                            RL_AE_RepondantQuiAide.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            ToastUtility.LogCat("Exception: sp_AE_EsKeGenMounKiEde:onItemSelected(): ", ex);
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                //endregion

                //region EVB23.- sp_AE_RepondantQuiAide
                QF.Load_IndividuMenage(queryRecordMngr, sp_AE_RepondantQuiAide, menageId );
                //endregion
                //endregion

                //region F.- FECONDITE
                //region EVB21.- sp_F_EsKeGenMounKiEde
                QF.Load_ReponseRapportFinal(QuestionnaireMenageActivity.this, sp_F_EsKeGenMounKiEde );
                sp_F_EsKeGenMounKiEde.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            // TEST POUR LE CHARGEMENT
                            KeyValueModel keyValueRezon = null;
                            if (parent.getId() == R.id.sp_F_EsKeGenMounKiEde) {
                                keyValueRezon = ((KeyValueModel) sp_F_EsKeGenMounKiEde.getSelectedItem());
                            }
                            tv_F_IsVivreDansMenage.setVisibility(View.GONE);
                            RL_F_IsVivreDansMenage.setVisibility(View.GONE);
                            if (keyValueRezon != null) {
                                if (!keyValueRezon.getKey().trim().equalsIgnoreCase("")) {
                                    if (parent.getId() == R.id.sp_F_EsKeGenMounKiEde) {
                                        if (keyValueRezon.getKey().equalsIgnoreCase("" + Constant.REPONS_WI_1) ) {
                                            tv_F_IsVivreDansMenage.setVisibility(View.VISIBLE);
                                            RL_F_IsVivreDansMenage.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            ToastUtility.LogCat("Exception: sp_F_EsKeGenMounKiEde:onItemSelected(): ", ex);
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                //endregion

                //region EVB22.- sp_F_IsVivreDansMenage
                QF.Load_ReponseRapportFinal(QuestionnaireMenageActivity.this, sp_F_IsVivreDansMenage );
                sp_F_IsVivreDansMenage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            // TEST POUR LE CHARGEMENT
                            KeyValueModel keyValueRezon = null;
                            if (parent.getId() == R.id.sp_F_IsVivreDansMenage) {
                                keyValueRezon = ((KeyValueModel) sp_F_IsVivreDansMenage.getSelectedItem());
                            }
                            tv_F_RepondantQuiAide.setVisibility(View.GONE);
                            RL_F_RepondantQuiAide.setVisibility(View.GONE);
                            if (keyValueRezon != null) {
                                if (!keyValueRezon.getKey().trim().equalsIgnoreCase("")) {
                                    if (parent.getId() == R.id.sp_F_IsVivreDansMenage) {
                                        if (keyValueRezon.getKey().equalsIgnoreCase("" + Constant.REPONS_WI_1) ) {
                                            tv_F_RepondantQuiAide.setVisibility(View.VISIBLE);
                                            RL_F_RepondantQuiAide.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            ToastUtility.LogCat("Exception: sp_F_EsKeGenMounKiEde:onItemSelected(): ", ex);
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                //endregion

                //region EVB23.- sp_F_RepondantQuiAide
                QF.Load_IndividuMenage(queryRecordMngr, sp_F_RepondantQuiAide, menageId );
                //endregion
                //endregion

                //region Buttons btnContinuer
                Button btnContinuer = (Button) dialog.findViewById(R.id.btnContinuer);
                btnContinuer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Shared_Preferences sharedPreferences = Tools.SharedPreferences(QuestionnaireMenageActivity.this);
                            QF.CheckValueBefore_RapportFinalRAR(sp_RepondantPrincipal, sp_AE_EsKeGenMounKiEde, sp_AE_IsVivreDansMenage, sp_AE_RepondantQuiAide
                                    , sp_F_EsKeGenMounKiEde, sp_F_IsVivreDansMenage, sp_F_RepondantQuiAide, sharedPreferences);
                            //QF.SaveInfoDefinitivement(cuRecordMngr, false);
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            //if( finishAfter ){
                            //    finish();
                            //}else {
                            // On met le boucle ici pour le menage
                            AddMenage_EnBoucle();
                            //}
                        } catch (TextEmptyException ex) {
                            Tools.AlertDialogMsg(QuestionnaireMenageActivity.this, ex.getMessage());
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
            ToastUtility.LogCat("Exception: ShowRapport_RAR_Final :" + message, ex.toString());
            //Tools.AlertDialogMsg(context, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }
    // endregion
}

