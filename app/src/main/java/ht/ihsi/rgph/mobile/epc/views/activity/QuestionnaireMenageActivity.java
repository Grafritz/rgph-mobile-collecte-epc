package ht.ihsi.rgph.mobile.epc.views.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import ht.ihsi.rgph.mobile.epc.models.AncienMembreModel;
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
    EditText et_02NonIndividu;
    EditText et_03SiyatiIndividu;
    Spinner sp_04Sexe;
    Spinner sp_09LienDeParente;
    TextView tv_NumeroIndividu, tv_06MembreMenageDepuisQuand;
    long ID_INDIVIDU = 0;
    private List<RowDataListModel> targetList = new ArrayList<RowDataListModel>();
    private RecyclerView recyclerView;
    private DisplayListAdapter mAdapter;
    EditText et_JourIndividu;
    Spinner sp_MoisIndividu;
    EditText et_06DateMembreMenageJour, et_AnneeIndividu, et_08AgeIndividu, et_06DateMembreMenageAnnee;
    Spinner sp_06MembreMenageDepuisQuand, sp_06DateMembreMenageMois, sp_07DateNaissanceMois, sp_05HabiteDansMenage;
    Spinner sp_11NiveauEtude, sp_12StatutMatrimonial, sp_10Nationalite, sp_10PaysNationalite;
    ScrollView scrollView2;

    TextView tv_06DateMembreMenage, tv_07DateNaissance, tv_09LienDeParente, tv_DateMounNanfet;
    TextView tv_10Nationalite, tv_11NiveauEtude, tv_10PaysNationalite, tv_12StatutMatrimonial;
    EditText et_07DateNaissanceJour, et_07DateNaissanceAnnee;
    RelativeLayout RL_06MembreMenageDepuisQuand, RL_09LienDeParente, RL_11NiveauEtude, RL_12StatutMatrimonial;
    RelativeLayout RL_10Nationalite, RL_10PaysNationalite;
    LinearLayout LL_AllField_Suite, LL_06DateMembreMenage, LL_07DateNaissance, LL_DateMounNanfet, LL_08LajMounNan;

    //region [ Ancien Membre ]
    EditText et_Qp2APrenom, et_Qp2BNom, et_Q7DateQuitterMenageJour, et_Q7DateQuitterMenageAnnee, et_Q7bDateMouriJour, et_Q7bDateMouriAnnee ;
    EditText et_Q8DateNaissanceJour, et_Q8DateNaissanceAnnee, et_Q9AgeAncienMembre ;
    Spinner sp_Qp4Sexe, sp_Q5EstMortOuQuitter, sp_Q6HabiteDansMenage, sp_Q7DateQuitterMenageMois, sp_Q7bDateMouriMois, sp_Q8DateNaissanceMois ;
    Spinner sp_Q10LienDeParente, sp_Q11Nationalite, sp_Q11PaysNationalite, sp_Q12NiveauEtude, sp_Q13StatutMatrimonial ;
    TextView tv_Q7DateQuitterMenage, tv_Q7bDateMouri, tv_Q8DateNaissance, tv_Q10LienDeParente, tv_Q11Nationalite, tv_Q11PaysNationalite ;
    TextView tv_Q12NiveauEtude, tv_Q13StatutMatrimonial ;
    LinearLayout LL_Q7DateQuitterMenage, LL_Q7bDateMouri, LL_Q8DateNaissance, LL_Q9AgeAncienMembre ;
    RelativeLayout RL_Q10LienDeParente, RL_Q11Nationalite, RL_Q11PaysNationalite, RL_Q12NiveauEtude, RL_Q13StatutMatrimonial ;
    //endregion

    ContrainteReponse contrainte = new ContrainteReponse();
    public static List<TempInfoQuestion> tempInfoQuestions;
    public int NoOrdreIndividu = 1, NoOrdreAncienMembre = 1;

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
    public void ShowPopUp_AddIndividus() {
        try {
            if (QF.getTbl_TableName() == Constant.FORMULAIRE_MENAGE) {
                //region [ Qm11CallFormListeIndividu ]
                if (QF.getNomChamps().equalsIgnoreCase(Constant.Qm11CallFormListeIndividu)) {
                    // Save Formulaire D'abord
                    QF.SaveInfoDefinitivement(cuRecordMngr, false);

                    int Nbre_TotalIndividu = QF.getMenageModel().getQm2TotalIndividuVivant();

                    if (Nbre_TotalIndividu > 0) {
                        // On Affiche le popUp pour la saisie des individus
                        dialog = new Dialog(this);
                        dialog.setContentView(R.layout.individus_form);
                        dialog.setCancelable(false);
                        scrollView2 = (ScrollView) dialog.findViewById(R.id.scrollView2);
                        //LinearLayout LLGrandTitre = (LinearLayout) dialog.findViewById(R.id.LLGrandTitre);
                        //LLGrandTitre.setVisibility(View.GONE);
                        LinearLayout LL_ListeView = (LinearLayout) dialog.findViewById(R.id.LL_ListeView);
                        LinearLayout LL_FormulaireAdd = (LinearLayout) dialog.findViewById(R.id.LL_FormulaireAdd);
                        tv_NumeroIndividu = (TextView) dialog.findViewById(R.id.tv_NumeroIndividu);
                        LL_AllField_Suite = (LinearLayout) dialog.findViewById(R.id.LL_AllField_Suite);

                        //region [ FORM INDIVIDU ]
                        LL_FormulaireAdd.setVisibility(View.VISIBLE);
                        LL_ListeView.setVisibility(View.GONE);

                        et_02NonIndividu = (EditText) dialog.findViewById(R.id.et_NonIndividu);
                        //et_02NonIndividu.addTextChangedListener(TextChanged_NonListener);
                        et_03SiyatiIndividu = (EditText) dialog.findViewById(R.id.et_SiyatiIndividu);

                        // 04
                        sp_04Sexe = (Spinner) dialog.findViewById(R.id.sp_Sexe);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_04Sexe, "MP2.4");

                        // 05 Habite Dans Menage
                        sp_05HabiteDansMenage = (Spinner) dialog.findViewById(R.id.sp_05HabiteDansMenage);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_05HabiteDansMenage, "MP2.5");
                        sp_05HabiteDansMenage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                //region TEST POUR LE CHARGEMENT
                                try {
                                    QuestionReponseModel questionReponseModel = null;
                                    questionReponseModel = ((QuestionReponseModel) sp_05HabiteDansMenage.getSelectedItem());
                                    LL_AllField_Suite.setVisibility(View.GONE);

                                    if (questionReponseModel != null) {
                                        if (!questionReponseModel.getQSuivant().trim().equalsIgnoreCase(Constant.FIN)) {
                                            LL_AllField_Suite.setVisibility(View.VISIBLE);
                                        }
                                    }
                                } catch (Exception ex) {
                                    ToastUtility.LogCat("Exception :-: sp_05HabiteDansMenage:onItemSelected(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
                                    ex.printStackTrace();
                                }
                                //endregion
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        // 06 A
                        tv_06MembreMenageDepuisQuand = (TextView) dialog.findViewById(R.id.tv_06MembreMenageDepuisQuand);
                        RL_06MembreMenageDepuisQuand = (RelativeLayout) dialog.findViewById(R.id.RL_06MembreMenageDepuisQuand);
                        sp_06MembreMenageDepuisQuand = (Spinner) dialog.findViewById(R.id.sp_06MembreMenageDepuisQuand);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_06MembreMenageDepuisQuand, "MP2.6.A");
                        sp_06MembreMenageDepuisQuand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                //region TEST POUR LE CHARGEMENT
                                try {
                                    QuestionReponseModel questionReponseModel = null;
                                    questionReponseModel = ((QuestionReponseModel) sp_06MembreMenageDepuisQuand.getSelectedItem());
                                    tv_06DateMembreMenage.setVisibility(View.GONE);
                                    LL_06DateMembreMenage.setVisibility(View.GONE);
                                    if (questionReponseModel != null) {
                                        QF.VisibleOrHide_06DateMembreMenage(questionReponseModel.getCodeReponse()
                                                , tv_06DateMembreMenage, LL_06DateMembreMenage);
                                    }
                                } catch (Exception ex) {
                                    ToastUtility.LogCat("Exception :-: sp_06MembreMenageDepuisQuand:onItemSelected(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
                                    ex.printStackTrace();
                                }
                                //endregion
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        // 06 B
                        tv_06DateMembreMenage = (TextView) dialog.findViewById(R.id.tv_06DateMembreMenage);
                        LL_06DateMembreMenage = (LinearLayout) dialog.findViewById(R.id.LL_06DateMembreMenage);
                        tv_06DateMembreMenage.setVisibility(View.GONE);
                        LL_06DateMembreMenage.setVisibility(View.GONE);
                        // 06.B
                        et_06DateMembreMenageJour = (EditText) dialog.findViewById(R.id.et_06DateMembreMenageJour);
                        sp_06DateMembreMenageMois = (Spinner) dialog.findViewById(R.id.sp_06DateMembreMenageMois);
                        QF.Load_Mois(this, sp_06DateMembreMenageMois);
                        et_06DateMembreMenageAnnee = (EditText) dialog.findViewById(R.id.et_06DateMembreMenageAnnee);

                        // 07
                        tv_07DateNaissance = (TextView) dialog.findViewById(R.id.tv_07DateNaissance);
                        LL_07DateNaissance = (LinearLayout) dialog.findViewById(R.id.LL_07DateNaissance);
                        et_07DateNaissanceJour = (EditText) dialog.findViewById(R.id.et_07DateNaissanceJour);
                        sp_07DateNaissanceMois = (Spinner) dialog.findViewById(R.id.sp_07DateNaissanceMois);
                        QF.Load_Mois(this, sp_07DateNaissanceMois);
                        et_07DateNaissanceAnnee = (EditText) dialog.findViewById(R.id.et_07DateNaissanceAnnee);

                        // 08
                        LL_08LajMounNan = (LinearLayout) dialog.findViewById(R.id.LL_08LajMounNan);
                        et_08AgeIndividu = (EditText) dialog.findViewById(R.id.et_08AgeIndividu);
                        et_08AgeIndividu.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                String ageStr = et_08AgeIndividu.getText().toString();
                                int age = 0;
                                if (!TextUtils.isEmpty(ageStr)) {
                                    age = Integer.parseInt(ageStr);
                                }
                                QF.VisibleOrHide_11NiveauEtude_12StatutMatrimonial(age
                                        , tv_11NiveauEtude, RL_11NiveauEtude
                                        , tv_12StatutMatrimonial, RL_12StatutMatrimonial);
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });

                        // 09
                        tv_09LienDeParente = (TextView) dialog.findViewById(R.id.tv_09LienDeParente);
                        RL_09LienDeParente = (RelativeLayout) dialog.findViewById(R.id.RL_09LienDeParente);
                        sp_09LienDeParente = (Spinner) dialog.findViewById(R.id.sp_09LienDeParente);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_09LienDeParente, "MP2.9");

                        // 10 A
                        tv_10Nationalite = (TextView) dialog.findViewById(R.id.tv_10Nationalite);
                        RL_10Nationalite = (RelativeLayout) dialog.findViewById(R.id.RL_10Nationalite);
                        sp_10Nationalite = (Spinner) dialog.findViewById(R.id.sp_10Nationalite);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_10Nationalite, "MP2.10");
                        sp_10Nationalite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                //region TEST POUR LE CHARGEMENT
                                try {
                                    QuestionReponseModel questionReponseModel = null;
                                    questionReponseModel = ((QuestionReponseModel) sp_10Nationalite.getSelectedItem());
                                    tv_10PaysNationalite.setVisibility(View.GONE);
                                    RL_10PaysNationalite.setVisibility(View.GONE);

                                    if (questionReponseModel != null) {
                                        if ( questionReponseModel.getCodeReponse().trim().equalsIgnoreCase("" + Constant.R02_Etranje)
                                                ||  questionReponseModel.getCodeReponse().trim().equalsIgnoreCase("" + Constant.R03_Ayisyen_ak_Etranje)) {
                                            tv_10PaysNationalite.setVisibility(View.VISIBLE);
                                            RL_10PaysNationalite.setVisibility(View.VISIBLE);
                                        }
                                    }
                                } catch (Exception ex) {
                                    ToastUtility.LogCat("Exception :-: sp_10Nationalite:onItemSelected(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
                                    ex.printStackTrace();
                                }
                                //endregion
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        // 10 B
                        tv_10PaysNationalite = (TextView) dialog.findViewById(R.id.tv_10PaysNationalite);
                        RL_10PaysNationalite = (RelativeLayout) dialog.findViewById(R.id.RL_10PaysNationalite);
                        tv_10PaysNationalite.setVisibility(View.GONE);
                        RL_10PaysNationalite.setVisibility(View.GONE);
                        sp_10PaysNationalite = (Spinner) dialog.findViewById(R.id.sp_10PaysNationalite);
                        QF.Load_Pays(formDataMngr, sp_10PaysNationalite);

                        // 11
                        tv_11NiveauEtude = (TextView) dialog.findViewById(R.id.tv_11NiveauEtude);
                        RL_11NiveauEtude = (RelativeLayout) dialog.findViewById(R.id.RL_11NiveauEtude);
                        sp_11NiveauEtude = (Spinner) dialog.findViewById(R.id.sp_11NiveauEtude);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_11NiveauEtude, "MP2.11");

                        // 12
                        tv_12StatutMatrimonial = (TextView) dialog.findViewById(R.id.tv_12StatutMatrimonial);
                        RL_12StatutMatrimonial = (RelativeLayout) dialog.findViewById(R.id.RL_12StatutMatrimonial);
                        sp_12StatutMatrimonial = (Spinner) dialog.findViewById(R.id.sp_12StatutMatrimonial);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_12StatutMatrimonial, "MP2.12");

                        tv_NumeroIndividu.setText("Moun #" + NoOrdreIndividu);

                        // On recherche les Individus par numero d'ordre et par IdMenage
                        ID_INDIVIDU = 0;
                        IndividuModel individuModel = QF.Get_Set_Individu_IfExist(queryRecordMngr, dialog, ID_INDIVIDU, NoOrdreIndividu, Nbre_TotalIndividu
                                , tv_NumeroIndividu, et_02NonIndividu, et_03SiyatiIndividu, sp_04Sexe
                                , sp_05HabiteDansMenage, sp_06MembreMenageDepuisQuand
                                , tv_06DateMembreMenage, LL_06DateMembreMenage
                                , et_06DateMembreMenageJour, sp_06DateMembreMenageMois, et_06DateMembreMenageAnnee
                                , et_07DateNaissanceJour, sp_07DateNaissanceMois, et_07DateNaissanceAnnee
                                , et_08AgeIndividu, tv_11NiveauEtude, RL_11NiveauEtude, tv_12StatutMatrimonial, RL_12StatutMatrimonial
                                , sp_09LienDeParente, sp_10Nationalite, tv_10PaysNationalite, RL_10PaysNationalite
                                , sp_10PaysNationalite, sp_11NiveauEtude, sp_12StatutMatrimonial);
                        if (individuModel != null && individuModel.getIndividuId() != null) {
                            ID_INDIVIDU = individuModel.getIndividuId();
                        }
                        //endregion

                        //region [ EVENTS BUTTON btnQuitter ]
                        Button btnQuitter = (Button) dialog.findViewById(R.id.btnQuitter);
                        btnQuitter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (NoOrdreIndividu > 1) {
                                    int Nbre_TotalIndividu = QF.getMenageModel().getQm2TotalIndividuVivant();
                                    NoOrdreIndividu -= 1;
                                    IndividuModel individuModel = QF.Get_Set_Individu_IfExist(queryRecordMngr, dialog, ID_INDIVIDU, NoOrdreIndividu, Nbre_TotalIndividu
                                            , tv_NumeroIndividu, et_02NonIndividu, et_03SiyatiIndividu, sp_04Sexe
                                            , sp_05HabiteDansMenage, sp_06MembreMenageDepuisQuand
                                            , tv_06DateMembreMenage, LL_06DateMembreMenage
                                            , et_06DateMembreMenageJour, sp_06DateMembreMenageMois, et_06DateMembreMenageAnnee
                                            , et_07DateNaissanceJour, sp_07DateNaissanceMois, et_07DateNaissanceAnnee
                                            , et_08AgeIndividu, tv_11NiveauEtude, RL_11NiveauEtude, tv_12StatutMatrimonial, RL_12StatutMatrimonial
                                            , sp_09LienDeParente, sp_10Nationalite, tv_10PaysNationalite, RL_10PaysNationalite
                                            , sp_10PaysNationalite, sp_11NiveauEtude, sp_12StatutMatrimonial);
                                    if (individuModel != null && individuModel.getIndividuId() != null) {
                                        ID_INDIVIDU = individuModel.getIndividuId();
                                    }
                                } else {
                                    if (dialog != null) {
                                        dialog.dismiss();
                                    }
                                    // On va a la question suivante
                                    Precedent_Click(QF);
                                }
                            }
                        });
                        //endregion

                        //region [ EVENTS BUTTON btnContinuer ]
                        Button btnContinuer = (Button) dialog.findViewById(R.id.btnContinuer);
                        btnContinuer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                QF.context = QuestionnaireMenageActivity.this;
                                try {
                                    int Nbre_TotalIndividu_Declarer = QF.getMenageModel().getQm2TotalIndividuVivant();
                                    // On recherche l'individu par le numero d'ordre
                                    //long NbreIndividu_DejaSave = queryRecordMngr.countIndByMenage(QF.getMenageModel().getMenageId());

                                    if (NoOrdreIndividu <= Nbre_TotalIndividu_Declarer) {
                                        IndividuModel indModel = QF.CheckIndividu_ValueBefore_AndSave(queryRecordMngr, cuRecordMngr
                                                , ID_INDIVIDU, NoOrdreIndividu, et_02NonIndividu, et_03SiyatiIndividu
                                                , sp_04Sexe, sp_05HabiteDansMenage
                                                , sp_06MembreMenageDepuisQuand, et_06DateMembreMenageJour, sp_06DateMembreMenageMois, et_06DateMembreMenageAnnee
                                                , et_07DateNaissanceJour, sp_07DateNaissanceMois, et_07DateNaissanceAnnee
                                                , et_08AgeIndividu, sp_09LienDeParente
                                                , sp_10Nationalite, sp_10PaysNationalite, sp_11NiveauEtude, sp_12StatutMatrimonial);

                                        //NbreIndividu_DejaSave = queryRecordMngr.countIndByMenage(QF.getMenageModel().getMenageId());
                                        NoOrdreIndividu = (NoOrdreIndividu + 1);

                                        if ( NoOrdreIndividu > Nbre_TotalIndividu_Declarer) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }
                                            NoOrdreIndividu=Nbre_TotalIndividu_Declarer;
                                            Suivant_Click();
                                        }
                                        et_02NonIndividu.setText(null);
                                        et_03SiyatiIndividu.setText(null);
                                        sp_04Sexe.setSelection(0);
                                        sp_05HabiteDansMenage.setSelection(0);

                                        sp_06MembreMenageDepuisQuand.setSelection(0);
                                        et_06DateMembreMenageJour.setText("");
                                        sp_06DateMembreMenageMois.setSelection(0);
                                        et_06DateMembreMenageAnnee.setText("");

                                        et_07DateNaissanceJour.setText("");
                                        sp_07DateNaissanceMois.setSelection(0);
                                        et_07DateNaissanceAnnee.setText("");

                                        et_08AgeIndividu.setText("");
                                        sp_09LienDeParente.setSelection(0);
                                        sp_10Nationalite.setSelection(0);
                                        sp_10PaysNationalite.setSelection(0);
                                        sp_11NiveauEtude.setSelection(0);
                                        sp_12StatutMatrimonial.setSelection(0);

                                        et_02NonIndividu.requestFocus();
                                        //ID_INDIVIDU = 0;

                                        // On recherche les Individus par numero d'ordre et par IdMenage
                                        IndividuModel individuModel = QF.Get_Set_Individu_IfExist(queryRecordMngr, dialog, ID_INDIVIDU, NoOrdreIndividu, Nbre_TotalIndividu_Declarer
                                                , tv_NumeroIndividu, et_02NonIndividu, et_03SiyatiIndividu, sp_04Sexe
                                                , sp_05HabiteDansMenage, sp_06MembreMenageDepuisQuand
                                                , tv_06DateMembreMenage, LL_06DateMembreMenage
                                                , et_06DateMembreMenageJour, sp_06DateMembreMenageMois, et_06DateMembreMenageAnnee
                                                , et_07DateNaissanceJour, sp_07DateNaissanceMois, et_07DateNaissanceAnnee
                                                , et_08AgeIndividu, tv_11NiveauEtude, RL_11NiveauEtude, tv_12StatutMatrimonial, RL_12StatutMatrimonial
                                                , sp_09LienDeParente, sp_10Nationalite, tv_10PaysNationalite, RL_10PaysNationalite
                                                , sp_10PaysNationalite, sp_11NiveauEtude, sp_12StatutMatrimonial);

                                        if (individuModel != null && individuModel.getIndividuId() != null) {
                                            ID_INDIVIDU = individuModel.getIndividuId();
                                        }
                                    } else if (NoOrdreIndividu == Nbre_TotalIndividu_Declarer) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                        Suivant_Click();
                                    }
                                } catch (TextEmptyException ex) {
                                    Tools.AlertDialogMsg(QuestionnaireMenageActivity.this, ex.getMessage());
                                    ToastUtility.LogCat("TextEmptyException: btnQuitter.setOnClickListener() :" + ex.getMessage() + " \n " + ex.toString());
                                } catch (Exception ex) {
                                    ToastUtility.ToastMessage(QuestionnaireMenageActivity.this, ex.getMessage() + " \n " + ex.toString());
                                    ToastUtility.LogCat("Exception: btnQuitter.setOnClickListener() :" + ex.getMessage() + " \n " + ex.toString());
                                }
                            }
                        });
                        //endregion

                        dialog.show();
                    }
                }
                //endregion

                //region [ CallFormListeAncienMembreMenage ]
                if ( QF.getNomChamps().equalsIgnoreCase(Constant.CallFormListeAncienMembreMenage) ) {
                    // Save Formulaire D'abord
                    QF.SaveInfoDefinitivement(cuRecordMngr, false);

                    int Nbre_TotalAncienMembre = QF.getMenageModel().getQm22TotalAncienMembre();

                    if (Nbre_TotalAncienMembre > 0) {
                        // On Affiche le popUp pour la saisie des individus
                        dialog = new Dialog(this);
                        dialog.setContentView(R.layout.individus_form_ancien_membre);
                        dialog.setCancelable(false);
                        scrollView2 = (ScrollView) dialog.findViewById(R.id.scrollView2);
                        LinearLayout LL_ListeView = (LinearLayout) dialog.findViewById(R.id.LL_ListeView);
                        LinearLayout LL_FormulaireAdd = (LinearLayout) dialog.findViewById(R.id.LL_FormulaireAdd);
                        tv_NumeroIndividu = (TextView) dialog.findViewById(R.id.tv_NumeroIndividu);
                        LL_AllField_Suite = (LinearLayout) dialog.findViewById(R.id.LL_AllField_Suite);

                        //region [ FORM INDIVIDU ]
                        LL_FormulaireAdd.setVisibility(View.VISIBLE);
                        LL_ListeView.setVisibility(View.GONE);

                        et_Qp2APrenom = (EditText) dialog.findViewById(R.id.et_Qp2APrenom);
                        //et_02NonIndividu.addTextChangedListener(TextChanged_NonListener);
                        et_Qp2BNom = (EditText) dialog.findViewById(R.id.et_Qp2BNom);

                        // 04
                        sp_Qp4Sexe = (Spinner) dialog.findViewById(R.id.sp_Qp4Sexe);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_Qp4Sexe, "M2.2.2.4");

                        // 05 Est Mort Ou Quitter
                        sp_Q5EstMortOuQuitter = (Spinner) dialog.findViewById(R.id.sp_Q5EstMortOuQuitter);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_Q5EstMortOuQuitter, "M2.2.2.5");
                        sp_Q5EstMortOuQuitter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                //region TEST POUR LE CHARGEMENT
                                try {
                                    QuestionReponseModel questionReponseModel = null;
                                    questionReponseModel = ((QuestionReponseModel) sp_Q5EstMortOuQuitter.getSelectedItem());
                                    tv_Q7DateQuitterMenage.setVisibility(View.GONE);
                                    LL_Q7DateQuitterMenage.setVisibility(View.GONE);
                                    tv_Q7bDateMouri.setVisibility(View.GONE);
                                    LL_Q7bDateMouri.setVisibility(View.GONE);

                                    if (questionReponseModel != null) {
                                        QF.VisibleOrHide_EstMortOuQuitter(questionReponseModel.getCodeReponse()
                                                , tv_Q7DateQuitterMenage,  LL_Q7DateQuitterMenage
                                                , tv_Q7bDateMouri,  LL_Q7bDateMouri );
                                    }
                                } catch (Exception ex) {
                                    ToastUtility.LogCat("Exception :-: sp_Q5EstMortOuQuitter:onItemSelected(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
                                    ex.printStackTrace();
                                }
                                //endregion
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        // 06 Habite Dans Menage
                        sp_Q6HabiteDansMenage = (Spinner) dialog.findViewById(R.id.sp_Q6HabiteDansMenage);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_Q6HabiteDansMenage, "M2.2.2.6");
                        sp_Q6HabiteDansMenage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                //region TEST POUR LE CHARGEMENT
                                try {
                                    QuestionReponseModel questionReponseModel = null;
                                    questionReponseModel = ((QuestionReponseModel) sp_Q6HabiteDansMenage.getSelectedItem());
                                    LL_AllField_Suite.setVisibility(View.GONE);

                                    if (questionReponseModel != null) {
                                        if (!questionReponseModel.getQSuivant().trim().equalsIgnoreCase(Constant.FIN)) {
                                            LL_AllField_Suite.setVisibility(View.VISIBLE);
                                        }
                                    }
                                } catch (Exception ex) {
                                    ToastUtility.LogCat("Exception :-: sp_Q6HabiteDansMenage:onItemSelected(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
                                    ex.printStackTrace();
                                }
                                //endregion
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        // 07 A - Date Quitter Menage
                        tv_Q7DateQuitterMenage = (TextView) dialog.findViewById(R.id.tv_Q7DateQuitterMenage);
                        LL_Q7DateQuitterMenage = (LinearLayout) dialog.findViewById(R.id.LL_Q7DateQuitterMenage);
                        tv_Q7DateQuitterMenage.setVisibility(View.GONE);
                        LL_Q7DateQuitterMenage.setVisibility(View.GONE);
                        et_Q7DateQuitterMenageJour = (EditText) dialog.findViewById(R.id.et_Q7DateQuitterMenageJour);
                        sp_Q7DateQuitterMenageMois = (Spinner) dialog.findViewById(R.id.sp_Q7DateQuitterMenageMois);
                        QF.Load_Mois(this, sp_Q7DateQuitterMenageMois);
                        et_Q7DateQuitterMenageAnnee = (EditText) dialog.findViewById(R.id.et_Q7DateQuitterMenageAnnee);

                        // 07 B - Date Mouri
                        tv_Q7bDateMouri = (TextView) dialog.findViewById(R.id.tv_Q7bDateMouri);
                        LL_Q7bDateMouri = (LinearLayout) dialog.findViewById(R.id.LL_Q7bDateMouri);
                        tv_Q7bDateMouri.setVisibility(View.GONE);
                        LL_Q7bDateMouri.setVisibility(View.GONE);
                        et_Q7bDateMouriJour = (EditText) dialog.findViewById(R.id.et_Q7bDateMouriJour);
                        sp_Q7bDateMouriMois = (Spinner) dialog.findViewById(R.id.sp_Q7bDateMouriMois);
                        QF.Load_Mois(this, sp_Q7bDateMouriMois);
                        et_Q7bDateMouriAnnee = (EditText) dialog.findViewById(R.id.et_Q7bDateMouriAnnee);

                        // 098 Date Naissance
                        tv_Q8DateNaissance = (TextView) dialog.findViewById(R.id.tv_Q8DateNaissance);
                        LL_Q8DateNaissance = (LinearLayout) dialog.findViewById(R.id.LL_Q8DateNaissance);
                        et_Q8DateNaissanceJour = (EditText) dialog.findViewById(R.id.et_Q8DateNaissanceJour);
                        sp_Q8DateNaissanceMois = (Spinner) dialog.findViewById(R.id.sp_Q8DateNaissanceMois);
                        QF.Load_Mois(this, sp_Q8DateNaissanceMois);
                        et_Q8DateNaissanceAnnee = (EditText) dialog.findViewById(R.id.et_Q8DateNaissanceAnnee);

                        // 09 Age Ancien Membre
                        LL_Q9AgeAncienMembre = (LinearLayout) dialog.findViewById(R.id.LL_Q9AgeAncienMembre);
                        et_Q9AgeAncienMembre = (EditText) dialog.findViewById(R.id.et_Q9AgeAncienMembre);
                        et_Q9AgeAncienMembre.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                String ageStr = et_Q9AgeAncienMembre.getText().toString();
                                int age = 0;
                                if (!TextUtils.isEmpty(ageStr)) {
                                    age = Integer.parseInt(ageStr);
                                }
                                QF.VisibleOrHide_11NiveauEtude_12StatutMatrimonial(age
                                        , tv_Q12NiveauEtude, RL_Q12NiveauEtude
                                        , tv_Q13StatutMatrimonial, RL_Q13StatutMatrimonial);
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });

                        // 10 Lien De Parente
                        tv_Q10LienDeParente = (TextView) dialog.findViewById(R.id.tv_Q10LienDeParente);
                        RL_Q10LienDeParente = (RelativeLayout) dialog.findViewById(R.id.RL_Q10LienDeParente);
                        sp_Q10LienDeParente = (Spinner) dialog.findViewById(R.id.sp_Q10LienDeParente);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_Q10LienDeParente, "M2.2.2.10");

                        // 11 Nationalite
                        tv_Q11Nationalite = (TextView) dialog.findViewById(R.id.tv_Q11Nationalite);
                        RL_Q11Nationalite = (RelativeLayout) dialog.findViewById(R.id.RL_Q11Nationalite);
                        sp_Q11Nationalite = (Spinner) dialog.findViewById(R.id.sp_Q11Nationalite);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_Q11Nationalite, "M2.2.2.11");
                        sp_Q11Nationalite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                //region TEST POUR LE CHARGEMENT
                                try {
                                    QuestionReponseModel questionReponseModel = null;
                                    questionReponseModel = ((QuestionReponseModel) sp_Q11Nationalite.getSelectedItem());
                                    tv_Q11PaysNationalite.setVisibility(View.GONE);
                                    RL_Q11PaysNationalite.setVisibility(View.GONE);

                                    if (questionReponseModel != null) {
                                        if ( questionReponseModel.getCodeReponse().trim().equalsIgnoreCase("" + Constant.R02_Etranje)
                                                ||  questionReponseModel.getCodeReponse().trim().equalsIgnoreCase("" + Constant.R03_Ayisyen_ak_Etranje)) {
                                            tv_Q11PaysNationalite.setVisibility(View.VISIBLE);
                                            RL_Q11PaysNationalite.setVisibility(View.VISIBLE);
                                        }
                                    }
                                } catch (Exception ex) {
                                    ToastUtility.LogCat("Exception :-: sp_10Nationalite:onItemSelected(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
                                    ex.printStackTrace();
                                }
                                //endregion
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        // 11 Pays Nationalite
                        tv_Q11PaysNationalite = (TextView) dialog.findViewById(R.id.tv_Q11PaysNationalite);
                        RL_Q11PaysNationalite = (RelativeLayout) dialog.findViewById(R.id.RL_Q11PaysNationalite);
                        tv_Q11PaysNationalite.setVisibility(View.GONE);
                        RL_Q11PaysNationalite.setVisibility(View.GONE);
                        sp_Q11PaysNationalite = (Spinner) dialog.findViewById(R.id.sp_Q11PaysNationalite);
                        QF.Load_Pays(formDataMngr, sp_Q11PaysNationalite);

                        // 12 Niveau Etude
                        tv_Q12NiveauEtude = (TextView) dialog.findViewById(R.id.tv_Q12NiveauEtude);
                        RL_Q12NiveauEtude = (RelativeLayout) dialog.findViewById(R.id.RL_Q12NiveauEtude);
                        sp_Q12NiveauEtude = (Spinner) dialog.findViewById(R.id.sp_Q12NiveauEtude);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_Q12NiveauEtude, "M2.2.2.12");

                        // 12
                        tv_Q13StatutMatrimonial = (TextView) dialog.findViewById(R.id.tv_Q13StatutMatrimonial);
                        RL_Q13StatutMatrimonial = (RelativeLayout) dialog.findViewById(R.id.RL_Q13StatutMatrimonial);
                        sp_Q13StatutMatrimonial = (Spinner) dialog.findViewById(R.id.sp_Q13StatutMatrimonial);
                        QF.Load_PossibiliteReponse(this, formDataMngr, sp_Q13StatutMatrimonial, "M2.2.2.13");

                        tv_NumeroIndividu.setText("Ansyen Manm #" + NoOrdreAncienMembre);

                        // On recherche les Individus par numero d'ordre et par IdMenage
                        ID_INDIVIDU = 0;
                        AncienMembreModel ancienMembreModel = QF.Get_Set_AncienMembre_IfExist(queryRecordMngr, dialog, ID_INDIVIDU, NoOrdreAncienMembre, Nbre_TotalAncienMembre
                                , tv_NumeroIndividu, et_Qp2APrenom, et_Qp2BNom, sp_Qp4Sexe
                                , sp_Q5EstMortOuQuitter , sp_Q6HabiteDansMenage
                                , tv_Q7DateQuitterMenage, LL_Q7DateQuitterMenage
                                , et_Q7DateQuitterMenageJour, sp_Q7DateQuitterMenageMois, et_Q7DateQuitterMenageAnnee
                                , tv_Q7bDateMouri, LL_Q7bDateMouri
                                , et_Q7bDateMouriJour, sp_Q7bDateMouriMois, et_Q7bDateMouriAnnee
                                , et_Q8DateNaissanceJour, sp_Q8DateNaissanceMois,  et_Q8DateNaissanceAnnee
                                , et_Q9AgeAncienMembre , tv_Q12NiveauEtude,  RL_Q12NiveauEtude ,  tv_12StatutMatrimonial,  RL_12StatutMatrimonial
                                , sp_Q10LienDeParente ,  sp_Q11Nationalite,  tv_Q11PaysNationalite,  RL_Q11PaysNationalite
                                , sp_Q11PaysNationalite,  sp_Q12NiveauEtude,  sp_Q13StatutMatrimonial);

                        if (ancienMembreModel != null && ancienMembreModel.getAncienMembreId() != null) {
                            ID_INDIVIDU = ancienMembreModel.getAncienMembreId();
                        }
                        //endregion

                        //region [ EVENTS BUTTON btnQuitter ]
                        Button btnQuitter = (Button) dialog.findViewById(R.id.btnQuitter);
                        btnQuitter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if ( NoOrdreAncienMembre > 1 ) {
                                    int Nbre_TotalAncienMembre = QF.getMenageModel().getQm22TotalAncienMembre();
                                    NoOrdreAncienMembre -= 1;
                                    AncienMembreModel ancienMembreModel1 = QF.Get_Set_AncienMembre_IfExist(queryRecordMngr, dialog, ID_INDIVIDU, NoOrdreAncienMembre, Nbre_TotalAncienMembre
                                            , tv_NumeroIndividu, et_02NonIndividu, et_03SiyatiIndividu, sp_04Sexe
                                            , sp_Q5EstMortOuQuitter , sp_Q6HabiteDansMenage
                                            , tv_Q7DateQuitterMenage, LL_Q7DateQuitterMenage
                                            , et_Q7DateQuitterMenageJour, sp_Q7DateQuitterMenageMois, et_Q7DateQuitterMenageAnnee
                                            , tv_Q7bDateMouri, LL_Q7bDateMouri
                                            , et_Q7bDateMouriJour, sp_Q7bDateMouriMois, et_Q7bDateMouriAnnee
                                            , et_Q8DateNaissanceJour, sp_Q8DateNaissanceMois,  et_Q8DateNaissanceAnnee
                                            , et_Q9AgeAncienMembre , tv_Q12NiveauEtude,  RL_Q12NiveauEtude ,  tv_12StatutMatrimonial,  RL_12StatutMatrimonial
                                            , sp_Q10LienDeParente ,  sp_Q11Nationalite,  tv_Q11PaysNationalite,  RL_Q11PaysNationalite
                                            , sp_Q11PaysNationalite,  sp_Q12NiveauEtude,  sp_Q13StatutMatrimonial);

                                    if (ancienMembreModel1 != null && ancienMembreModel1.getAncienMembreId() != null) {
                                        ID_INDIVIDU = ancienMembreModel1.getAncienMembreId();
                                    }
                                } else {
                                    if (dialog != null) {
                                        dialog.dismiss();
                                    }
                                    // On va a la question suivante
                                    Precedent_Click(QF);
                                }
                            }
                        });
                        //endregion

                        //region [ EVENTS BUTTON btnContinuer ]
                        Button btnContinuer = (Button) dialog.findViewById(R.id.btnContinuer);
                        btnContinuer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                QF.context = QuestionnaireMenageActivity.this;
                                try {
                                    int Nbre_TotalAncienMembre_Declarer = QF.getMenageModel().getQm22TotalAncienMembre();
                                    // On recherche l'individu par le numero d'ordre
                                    //long NbreIndividu_DejaSave = queryRecordMngr.countIndByMenage(QF.getMenageModel().getMenageId());

                                    if ( NoOrdreAncienMembre <= Nbre_TotalAncienMembre_Declarer ) {
                                        AncienMembreModel indModel = QF.CheckAncienMembre_ValueBefore_AndSave(queryRecordMngr, cuRecordMngr
                                                , ID_INDIVIDU, NoOrdreAncienMembre,  et_Qp2APrenom, et_Qp2BNom, sp_Qp4Sexe
                                                , sp_Q5EstMortOuQuitter , sp_Q6HabiteDansMenage
                                                , et_Q7DateQuitterMenageJour, sp_Q7DateQuitterMenageMois, et_Q7DateQuitterMenageAnnee
                                                , et_Q7bDateMouriJour, sp_Q7bDateMouriMois, et_Q7bDateMouriAnnee
                                                , et_Q8DateNaissanceJour, sp_Q8DateNaissanceMois,  et_Q8DateNaissanceAnnee
                                                , et_Q9AgeAncienMembre, sp_Q10LienDeParente
                                                , sp_Q11Nationalite, sp_Q11PaysNationalite, sp_Q12NiveauEtude, sp_Q13StatutMatrimonial);

                                        NoOrdreAncienMembre = (NoOrdreAncienMembre + 1);

                                        if ( NoOrdreAncienMembre > Nbre_TotalAncienMembre_Declarer) {
                                            if (dialog != null) {
                                                dialog.dismiss();
                                            }
                                            NoOrdreAncienMembre=Nbre_TotalAncienMembre_Declarer;
                                            Suivant_Click();
                                        }
                                        et_Qp2APrenom.setText(null);
                                        et_Qp2BNom.setText(null);
                                        sp_Qp4Sexe.setSelection(0);
                                        sp_Q5EstMortOuQuitter.setSelection(0);
                                        sp_Q6HabiteDansMenage.setSelection(0);

                                        et_Q7DateQuitterMenageJour.setText("");
                                        sp_Q7DateQuitterMenageMois.setSelection(0);
                                        et_Q7DateQuitterMenageAnnee.setText("");

                                        et_Q7bDateMouriJour.setText("");
                                        sp_Q7bDateMouriMois.setSelection(0);
                                        et_Q7bDateMouriAnnee.setText("");

                                        et_Q8DateNaissanceJour.setText("");
                                        sp_Q8DateNaissanceMois.setSelection(0);
                                        et_Q8DateNaissanceAnnee.setText("");

                                        et_Q9AgeAncienMembre.setText("");
                                        sp_Q10LienDeParente.setSelection(0);
                                        sp_Q11Nationalite.setSelection(0);
                                        sp_Q11PaysNationalite.setSelection(0);
                                        sp_Q12NiveauEtude.setSelection(0);
                                        sp_Q13StatutMatrimonial.setSelection(0);

                                        et_02NonIndividu.requestFocus();
                                        //ID_INDIVIDU = 0;

                                        // On recherche les Individus par numero d'ordre et par IdMenage
                                        AncienMembreModel individuModel = QF.Get_Set_AncienMembre_IfExist(queryRecordMngr, dialog, ID_INDIVIDU, NoOrdreAncienMembre, Nbre_TotalAncienMembre_Declarer
                                                , tv_NumeroIndividu, et_Qp2APrenom, et_Qp2BNom, sp_Qp4Sexe
                                                , sp_Q5EstMortOuQuitter , sp_Q6HabiteDansMenage
                                                , tv_Q7DateQuitterMenage, LL_Q7DateQuitterMenage
                                                , et_Q7DateQuitterMenageJour, sp_Q7DateQuitterMenageMois, et_Q7DateQuitterMenageAnnee
                                                , tv_Q7bDateMouri, LL_Q7bDateMouri
                                                , et_Q7bDateMouriJour, sp_Q7bDateMouriMois, et_Q7bDateMouriAnnee
                                                , et_Q8DateNaissanceJour, sp_Q8DateNaissanceMois,  et_Q8DateNaissanceAnnee
                                                , et_Q9AgeAncienMembre , tv_Q12NiveauEtude,  RL_Q12NiveauEtude ,  tv_12StatutMatrimonial,  RL_12StatutMatrimonial
                                                , sp_Q10LienDeParente ,  sp_Q11Nationalite,  tv_Q11PaysNationalite,  RL_Q11PaysNationalite
                                                , sp_Q11PaysNationalite,  sp_Q12NiveauEtude,  sp_Q13StatutMatrimonial);

                                        if (individuModel != null && individuModel.getAncienMembreId() != null) {
                                            ID_INDIVIDU = individuModel.getAncienMembreId();
                                        }
                                    } else if ( NoOrdreAncienMembre == Nbre_TotalAncienMembre_Declarer ) {
                                        if (dialog != null) {
                                            dialog.dismiss();
                                        }
                                        Suivant_Click();
                                    }
                                } catch (TextEmptyException ex) {
                                    Tools.AlertDialogMsg(QuestionnaireMenageActivity.this, ex.getMessage());
                                    ToastUtility.LogCat("TextEmptyException: btnQuitter.setOnClickListener() :" + ex.getMessage() + " \n " + ex.toString());
                                } catch (Exception ex) {
                                    ToastUtility.ToastMessage(QuestionnaireMenageActivity.this, ex.getMessage() + " \n " + ex.toString());
                                    ToastUtility.LogCat("Exception: btnQuitter.setOnClickListener() :" + ex.getMessage() + " \n " + ex.toString());
                                }
                            }
                        });
                        //endregion

                        dialog.show();
                    }
                }
                //endregion
            }
        } catch (TextEmptyException ex) {
            Tools.AlertDialogMsg(this, ex.getMessage());
            ToastUtility.LogCat("TextEmptyException: Suivant_Click :" + ex.getMessage());
        } catch (ManagerException ex) {
            message = "Erreur au niveau du Manager";
            Tools.AlertDialogMsg(this, message + "\n" + ex.getMessage());
            ToastUtility.LogCat("ManagerException: Suivant_Click :" + ex.getMessage());
        } catch (Exception ex) {
            message = "Erreur au niveau de la sauvegarde";
            ToastUtility.LogCat("Exception: Suivant_Click :" + message + " / " + ex.toString());
            Tools.AlertDialogMsg(this, message + "\n" + ex.getMessage());
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
                            NoOrdreIndividu=1; // Reinitialisation du nombre d'individu
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
                    NoOrdreIndividu=1; // Reinitialisation du nombre d'individu
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
            TextView tv_GrandTitreInd = (TextView) dialog.findViewById(R.id.tv_grandtitre);

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

                TextView tv_GrandTitreRap = (TextView) dialog.findViewById(R.id.tv_grandtitre);
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
                            //int statut = QF.SetStatutMenage(queryRecordMngr, cuRecordMngr);
                            //if( statut == Constant.STATUT_RANPLI_NET_11 ){
                            //    ShowRapport_RAR_Final();
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

