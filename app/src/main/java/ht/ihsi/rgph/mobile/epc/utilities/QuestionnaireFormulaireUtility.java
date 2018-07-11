package ht.ihsi.rgph.mobile.epc.utilities;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.epc.backend.entities.BatimentDao;
//import ht.ihsi.rgph.mobile.epc.backend.entities.DecesDao;
//import ht.ihsi.rgph.mobile.epc.backend.entities.EmigreDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.IndividuDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.LogementDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.MenageDao;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.exceptions.ManagerException;
import ht.ihsi.rgph.mobile.epc.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.epc.managers.CURecordMngr;
import ht.ihsi.rgph.mobile.epc.managers.CURecordMngrImpl;
import ht.ihsi.rgph.mobile.epc.managers.FormDataMngr;
import ht.ihsi.rgph.mobile.epc.managers.QueryRecordMngr;
import ht.ihsi.rgph.mobile.epc.models.AncienMembreModel;
import ht.ihsi.rgph.mobile.epc.models.BaseModel;
import ht.ihsi.rgph.mobile.epc.models.BatimentModel;
import ht.ihsi.rgph.mobile.epc.models.CategorieQuestionModel;
import ht.ihsi.rgph.mobile.epc.models.CommuneModel;
//import ht.ihsi.rgph.mobile.epc.models.DecesModel;
//import ht.ihsi.rgph.mobile.epc.models.EmigreModel;
import ht.ihsi.rgph.mobile.epc.models.IndividuModel;
import ht.ihsi.rgph.mobile.epc.models.KeyValueModel;
import ht.ihsi.rgph.mobile.epc.models.LogementModel;
import ht.ihsi.rgph.mobile.epc.models.MenageModel;
import ht.ihsi.rgph.mobile.epc.models.ModuleModel;
import ht.ihsi.rgph.mobile.epc.models.QuestionReponseModel;
import ht.ihsi.rgph.mobile.epc.models.QuestionsModel;
import ht.ihsi.rgph.mobile.epc.models.RapportFinalModel;
import ht.ihsi.rgph.mobile.epc.models.RapportRARModel;
import ht.ihsi.rgph.mobile.epc.models.VqseModel;
import ht.ihsi.rgph.mobile.epc.views.activity.QuestionnaireBatimentActivity;
//import ht.ihsi.rgph.mobile.epc.views.activity.QuestionnaireDecesActivity;
//import ht.ihsi.rgph.mobile.epc.views.activity.QuestionnaireEmigreActivity;
import ht.ihsi.rgph.mobile.epc.views.activity.QuestionnaireIndividuActivity;
import ht.ihsi.rgph.mobile.epc.views.activity.QuestionnaireLogementActivity;
import ht.ihsi.rgph.mobile.epc.views.activity.QuestionnaireMenageActivity;
import ht.ihsi.rgph.mobile.epc.views.adapters.RadioListAdapter;
import ht.ihsi.rgph.mobile.epc.views.adapters.RadioListAdapterKeyValue;
import ht.ihsi.rgph.mobile.epc.views.decorator.SimpleDividerItemDecoration;

import static ht.ihsi.rgph.mobile.epc.views.adapters.RadioListAdapter.*;

/**
 * Created by JeanFritz on 4/12/2016.
 */
public class QuestionnaireFormulaireUtility  extends BaseModel //extends AppCompatActivity //
{
    //region ATTRIBUT - QuestionnaireFormulaireUtility
    private Long iDKeys;
    private String sdeId;
    private int tbl_TableName;
    private String codeModule;
    private String codeQuestion;
    private String nomChamps;
    private int typeQuestion;
    private int caratereAccepte;
    private int contrainteQuestion;
    private String contrainteSautChampsValeur;
    private Boolean estSautReponse;
    private String qPrecedent;
    private String qSuivant;

    private Object dataBase = null;
    private Object data = null;
    public QuestionsModel questionsModel;
    public Boolean IsAgeIndividuVerify=true;

    //region OBJECT
    private BatimentModel batimentModel;
    private LogementModel logementModel;
    private MenageModel menageModel;
    private IndividuModel individuModel;
    //private EmigreModel emigreModel;
    //private DecesModel decesModel;
    //endregion
    ///////////////////////////////////
    public String GrandTitre;
    public String LibelleQuestion;
    public String DetailsQuestion;
    public String DetailsCategorie;
    public String SousDetailsCategorie;
    public Context context;
    public int TypeEvenement;
    public String nomUtilisateur;

    public Dialog dialog;

    //////////////////////////////////
    public Object getDataBase() {
        return dataBase;
    }

    public Object getData() {
        return data;
    }

    //public FormDataMngr formDataMngr;
    //public QueryRecordMngr queryRecordMngr;

    ContrainteReponse contrainte = new ContrainteReponse();

    public ContrainteReponse getContrainte() {
        return contrainte;
    }

    public int NbChar;

    public long getDureeSaisie(String dateFinCollecte) {
        // Joda Time
        if( dateFinCollecte.equalsIgnoreCase("") ) {
            dateFinCollecte = Tools.getDateString_MMddyyyy_HHmmss_a();
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(Tools.getFormatDateString_MM_dd_yyyy_HHmmss_a());
        DateTime start = dateTimeFormatter.parseDateTime(dateDebutCollecte);
        DateTime end = dateTimeFormatter.parseDateTime(dateFinCollecte);
        // duration in ms between two instants
        Duration dur = new Duration(start, end);

        ToastUtility.LogCat("W", "dateDebutCollecte:"+ dateDebutCollecte + " | dateFinCollecte():" + dateFinCollecte + "\\ngetDureeSaisie():" + dur.getStandardSeconds() + " Sec");
        return dur.getStandardSeconds();
    }

    public String dateDebutCollecte;

    public String getDateDebutCollecte() {
        return dateDebutCollecte;
    }

    public void setDateDebutCollecte(String dateDebutCollecte) {
        this.dateDebutCollecte = dateDebutCollecte;
    }


    public Boolean IsLoadCommuneAgain = true;
    public String idDeptLast = "";
    public String codeReponseParentLast = "";
    public boolean IsLoadPossibiliteReponse_ChildAgain = true;
    public String idComLast = "";
    public Boolean IsLoadVqseAgain = true;

    private String codeAgentRecenseur;
    //endregion

    //region CONSTRUCTEURS
    public QuestionnaireFormulaireUtility() {
    }

    // POUR BATIMENT
    public QuestionnaireFormulaireUtility(ModuleModel frm, BatimentModel batimentModel, int tbl_TableName, FormDataMngr formDataMngr) {
        try {
            this.tbl_TableName = tbl_TableName;
            this.codeModule = frm.getCodeModule();
            this.batimentModel = batimentModel;

            if (batimentModel != null) {
                if (batimentModel.getBatimentId() > 0) {
                    this.iDKeys = batimentModel.getBatimentId();
                    dataBase = batimentModel;
                    this.batimentModel = batimentModel;
                } else {
                    this.iDKeys = (long) 0;
                }
            } else {
                ToastUtility.LogCat("W", " batimentModel == NULL");
            }
            ToastUtility.LogCat("W", " 3: " + frm.getCodeModule());
           /* if (!frm.isEstActif()) {
                ToastUtility.ToastMessage(context, "Fòmilè a pa aktive... ou pap ka kontinye, ale wè sipèvisè ou.");
            }*/
            if (tbl_TableName == Constant.FORMULAIRE_BATIMENT) {
                data = new BatimentModel();

                if (batimentModel.getBatimentId() > 0) {
                    data = batimentModel;
                }
                this.SetKey_Value(BatimentDao.Properties.SdeId.columnName, batimentModel.getSdeId().toString());
                this.SetKey_Value(BatimentDao.Properties.DateDebutCollecte.columnName, batimentModel.getDateDebutCollecte().toString());
                //dateDebutCollecte = batimentModel.getDateDebutCollecte();
            }
            if (batimentModel.getCallFormulaireLogementCollectif() != null &&
                    batimentModel.getCallFormulaireLogementCollectif().equalsIgnoreCase(Constant.CallFormulaireLogementCollectif)) {

            } else {
                LoadFirst_Question(formDataMngr, this.codeModule);
            }

        } catch (Exception ex) {
            ToastUtility.LogCat("Exception : QuestionnaireFormulaireUtility(BatimentModel) : ", ex);
        }
    }//

    // POUR LOGEMENT
    public QuestionnaireFormulaireUtility(ModuleModel frm, LogementModel logementModel, int tbl_TableName, FormDataMngr formDataMngr) {
        this.tbl_TableName = tbl_TableName;
        this.codeModule = frm.getCodeModule();
        //this.formDataMngr = formDataMngr;
        if (logementModel.getObjBatiment() != null) {
            this.batimentModel = logementModel.getObjBatiment();
            this.batimentModel.setBatimentId(logementModel.getBatimentId());
        }
        this.logementModel = logementModel;
        //ToastUtility.LogCat(context, " logementModel.getBatimentId(): " + logementModel.getBatimentId());
        //ToastUtility.LogCat(context, " getIsQlCategLogement(): " + String.valueOf(logementModel.getQlCategLogement()).toString());
        //ToastUtility.LogCat(context, "QuestionnaireFormulaireUtility() - getDateDebutCollecte:" + logementModel.getDateDebutCollecte());

        dataBase = null;

        if (logementModel.getLogeId() != null && logementModel.getLogeId() > 0) {
            this.iDKeys = logementModel.getLogeId();
            dataBase = logementModel;
            this.logementModel = logementModel;
        } else {
            this.iDKeys = (long) 0;
        }

        if (!frm.isEstActif()) {
            // MessageToShow("Fòmilè a pa aktive... ou pap ka kontinye, ale wè sipèvisè ou.","S");
            // this.Close();
        }
        if (tbl_TableName == Constant.FORMULAIRE_LOGEMENT_COLLECTIF
                || tbl_TableName == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL) {
            data = logementModel;//new LogementModel();
            if (logementModel.getLogeId() != null && logementModel.getLogeId() > 0) {
                data = logementModel;
            }

            // SET KEY - VALUE
            this.SetKey_Value(LogementDao.Properties.QlCategLogement.columnName, "" + logementModel.getQlCategLogement());
            this.SetKey_Value(LogementDao.Properties.Qlin1NumeroOrdre.columnName, "" + logementModel.getQlin1NumeroOrdre());
            this.SetKey_Value(LogementDao.Properties.SdeId.columnName, logementModel.getSdeId().toString());
            this.SetKey_Value(LogementDao.Properties.DateDebutCollecte.columnName, logementModel.getDateDebutCollecte().toString());
        }

        LoadFirst_Question(formDataMngr, this.codeModule);
    }//

    // POUR MENAGE
    public QuestionnaireFormulaireUtility(ModuleModel frm, MenageModel menageModel, int tbl_TableName, FormDataMngr formDataMngr) {
        try {
            this.tbl_TableName = tbl_TableName;
            this.codeModule = frm.getCodeModule();
            this.batimentModel = menageModel.getObjBatiment();
            this.logementModel = menageModel.getObjLogement();

            if (menageModel.getMenageId() > 0) {
                this.iDKeys = menageModel.getMenageId();
                dataBase = menageModel;
                this.menageModel = menageModel;
            } else {
                this.iDKeys = (long) 0;
            }

            if (!frm.isEstActif()) {
                // MessageToShow("Fòmilè a pa aktive... ou pap ka kontinye, ale wè sipèvisè ou.","S");
                // this.Close();
            }
            if (tbl_TableName == Constant.FORMULAIRE_MENAGE) {
                data = new MenageModel();
                if (menageModel.getMenageId() > 0) {
                    data = menageModel;
                }

                // SET KEY - VALUE
                this.SetKey_Value(MenageDao.Properties.MenageId.columnName, String.valueOf(menageModel.getMenageId()).toString());
                this.SetKey_Value(MenageDao.Properties.BatimentId.columnName, String.valueOf(menageModel.getBatimentId()).toString());
                this.SetKey_Value(MenageDao.Properties.LogeId.columnName, String.valueOf(menageModel.getLogeId()).toString());
                this.SetKey_Value(MenageDao.Properties.Qm1NoOrdre.columnName, String.valueOf(menageModel.getQm1NoOrdre()).toString());
                this.SetKey_Value(MenageDao.Properties.SdeId.columnName, menageModel.getSdeId().toString());
                this.SetKey_Value(MenageDao.Properties.DateDebutCollecte.columnName, menageModel.getDateDebutCollecte().toString());
            }
            LoadFirst_Question(formDataMngr, this.codeModule);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception : QuestionnaireFormulaireUtility() : ", ex);
            ex.printStackTrace();
            //throw ex;
        }
    }//

    // POUR INDIVIDU
    public QuestionnaireFormulaireUtility(ModuleModel frm, IndividuModel individuModel, int tbl_TableName, FormDataMngr formDataMngr) {
        try {
            this.tbl_TableName = tbl_TableName;
            this.codeModule = frm.getCodeModule();
            this.batimentModel = individuModel.getObjBatiment();
            this.logementModel = individuModel.getObjLogement();
            this.menageModel = individuModel.getObjMenage();
            this.individuModel = individuModel;

            if (individuModel.getIndividuId() > 0) {
                this.iDKeys = individuModel.getIndividuId();
                dataBase = individuModel;
            } else {
                this.iDKeys = (long) 0;
            }

            if (!frm.isEstActif()) {
                // MessageToShow("Fòmilè a pa aktive... ou pap ka kontinye, ale wè sipèvisè ou.","S");
                // this.Close();
            }
            if (tbl_TableName == Constant.FORMULAIRE_INDIVIDUS) {
                data = new IndividuModel();
                if (individuModel.getIndividuId() > 0) {
                    data = individuModel;
                }

                // SET KEY - VALUE
                SetKey_Value(IndividuDao.Properties.Q1NoOrdre.columnName, String.valueOf(individuModel.getQ1NoOrdre()).toString());
                SetKey_Value(IndividuDao.Properties.BatimentId.columnName, String.valueOf(individuModel.getBatimentId()).toString());
                SetKey_Value(IndividuDao.Properties.LogeId.columnName, String.valueOf(individuModel.getLogeId()).toString());
                SetKey_Value(IndividuDao.Properties.MenageId.columnName, String.valueOf(individuModel.getMenageId()).toString());

                this.SetKey_Value(IndividuDao.Properties.MenageId.columnName, String.valueOf(individuModel.getMenageId()).toString());
                this.SetKey_Value(IndividuDao.Properties.BatimentId.columnName, String.valueOf(individuModel.getBatimentId()).toString());
                this.SetKey_Value(IndividuDao.Properties.LogeId.columnName, String.valueOf(individuModel.getLogeId()).toString());
                this.SetKey_Value(IndividuDao.Properties.Q1NoOrdre.columnName, String.valueOf(individuModel.getQ1NoOrdre()).toString());
                this.SetKey_Value(IndividuDao.Properties.SdeId.columnName, individuModel.getSdeId().toString());

                this.SetKey_Value(IndividuDao.Properties.DateDebutCollecte.columnName, individuModel.getDateDebutCollecte().toString());
            }
            LoadFirst_Question(formDataMngr, this.codeModule);
        } catch (Exception ex) {
            ex.printStackTrace();
            ToastUtility.LogCat("Exception : Constructeur() : ", ex);
            throw ex;
        }
    }//

    //region GET / SET
    public Long getiDKeys() {
        return iDKeys;
    }

    public void setiDKeys(Long iDKeys) {
        this.iDKeys = iDKeys;
    }

    public String getSdeId() {
        return sdeId;
    }

    public void setSdeId(String sdeId) {
        this.sdeId = sdeId;
    }

    public int getTbl_TableName() {
        return tbl_TableName;
    }

    public void setTbl_TableName(int tbl_TableName) {
        this.tbl_TableName = tbl_TableName;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public String getCodeQuestion() {
        return codeQuestion;
    }

    public void setCodeQuestion(String codeQuestion) {
        this.codeQuestion = codeQuestion;
    }

    public String getNomChamps() {
        return nomChamps;
    }

    public void setNomChamps(String nomChamps) {
        this.nomChamps = nomChamps;
    }

    public int getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(int typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public int getCaratereAccepte() {
        return caratereAccepte;
    }

    public void setCaratereAccepte(int caratereAccepte) {
        this.caratereAccepte = caratereAccepte;
        this.contrainteQuestion = caratereAccepte;
    }

    public String getContrainteSautChampsValeur() {
        return contrainteSautChampsValeur;
    }

    public void setContrainteSautChampsValeur(String contrainteSautChampsValeur) {
        this.contrainteSautChampsValeur = contrainteSautChampsValeur;
    }

    public Boolean getEstSautReponse() {
        return estSautReponse;
    }

    public void setEstSautReponse(Boolean estSautReponse) {
        this.estSautReponse = estSautReponse;
    }

    public String getqPrecedent() {
        return qPrecedent;
    }

    public void setqPrecedent(String qPrecedent) {
        this.qPrecedent = qPrecedent;
    }

    public String getqSuivant() {
        return qSuivant;
    }

    public void setqSuivant(String qSuivant) {
        this.qSuivant = qSuivant;
    }
    //endregion

    //region GET - SET OBJET
   /* public FormDataMngr getFormDataMngr() { return formDataMngr; }

    public void setFormDataMngr(FormDataMngr formDataMngr) { this.formDataMngr = formDataMngr; }*/

    public BatimentModel getBatimentModel() {
        return batimentModel;
    }

    public void setBatimentModel(BatimentModel batimentModel) {
        this.batimentModel = batimentModel;
    }

    public LogementModel getLogementModel() {
        return logementModel;
    }

    public void setLogementModel(LogementModel logementModel) {
        this.logementModel = logementModel;
    }

    public MenageModel getMenageModel() {
        return menageModel;
    }
    //public EmigreModel getEmigreModel() {
        //return emigreModel;
    //}
    //public DecesModel getDecesModel() { return decesModel; }

    public IndividuModel getIndividuModel() {
        return individuModel;
    }

    public String getReponseDate(String _jour, String _Mois, String _Annee) {
        //Calendar cal = Calendar.getInstance();
        String dateStr = "";
        int jj = Integer.parseInt(_jour);//28;
        int mois = Integer.parseInt(_Mois);
        int annee = Integer.parseInt(_Annee);
        /*if (jj == 0 || jj == 99) {
            jj = 1;
        }
        if (mois == 0 || mois == 99) {
            mois = 12;
        }*/
        //try {
        dateStr = mois + "-" + jj + "-" + annee;
        //cal.set(Calendar.DAY_OF_MONTH, jj);
        //cal.set(Calendar.MONTH, mois);
        //cal.set(Calendar.YEAR, annee);
        return dateStr;//cal;
    }

    public String getCodeAgentRecenseur() {
        return codeAgentRecenseur;
    }

    public void setCodeAgentRecenseur(String codeAgentRecenseur) {
        this.codeAgentRecenseur = codeAgentRecenseur;
    }
//endregion

    //region "METHODE SET INFO - QuestionnaireFormulaire"
    public void GetQuestionInfo(QuestionsModel Q, FormDataMngr formDataMngr) {
        try {
            //if (Q.getCodeQuestion() == null && Q.getCodeQuestion().equals("")) {
            //    ToastUtility.LogCat(context, "Gen yon ti problèm nan fòmilè a... \n Eseye ankò tanpri");
            //    ToastUtility.ToastMessage(context, "Gen yon ti problèm nan fòmilè a... \n Eseye ankò tanpri");
            //finish();
            //} else {
            this.codeQuestion = Q.getCodeQuestion();
            this.typeQuestion = Q.getTypeQuestion();
            this.caratereAccepte = Q.getCaratereAccepte();
            this.contrainteQuestion = Q.getCaratereAccepte();
            this.estSautReponse = Q.isEstSautReponse();
            this.contrainteSautChampsValeur = Q.getContrainteSautChampsValeur();
            this.nomChamps = Q.getNomChamps();
            this.qPrecedent = Q.getqPrecedent();
            this.qSuivant = Q.getqSuivant();

            this.LibelleQuestion = Q.getLibelle();
            this.DetailsQuestion = Q.getDetailsQuestion();
            if (Constant.FORMULAIRE_MENAGE == this.tbl_TableName) {
                MenageModel M = (MenageModel) data;
                //if (M.getQm15NomChefMenage() != null) {
                //ToastUtility.LogCat(context, "I.getQp2Nom():"+I.getQp2Nom().toString());
                //    LibelleQuestion = Q.getCodeQuestion() + ". " + Q.getLibelle().replace("{0}",  M.getQm15NomChefMenage().toString());
                //} else {
                LibelleQuestion = Q.getCodeQuestion() + ". " + Q.getLibelle();
                //}
            } else if (Constant.FORMULAIRE_INDIVIDUS == this.tbl_TableName) {
                IndividuModel I = (IndividuModel) data;
                if (I.getQp2BNom() != null && I.getQp2APrenom() != null) {
                    // ToastUtility.LogCat(context, "I.getQp2Nom():"+I.getQp2Nom().toString());
                    String nomComplet = "<span style='color:2494F2;'>" + I.getQp2APrenom().toString() + " " + I.getQp2BNom().toString().toUpperCase() +"</span>";
                    LibelleQuestion = Q.getCodeQuestion() + ". " + Q.getLibelle().replace("{0}", nomComplet).replace("(…)", nomComplet);
                    this.DetailsQuestion = Q.getDetailsQuestion().replace("{0}", nomComplet).replace("(…)", nomComplet);
                } else {
                    LibelleQuestion = Q.getCodeQuestion() + ". " + Q.getLibelle();
                }
            } else {
                LibelleQuestion = Q.getCodeQuestion() + ". " + Q.getLibelle();
                LibelleQuestion = LibelleQuestion.replace("BIT.LOC.", "").replace("BREC.", "").replace("BRGPH.", "");
            }

            CategorieQuestionModel categorieQuestionModel = formDataMngr.getCategorieQuestion(Q.getCodeCategorie());
            if (categorieQuestionModel != null) {
                GrandTitre = categorieQuestionModel.getCategorieQuestion();
                DetailsCategorie = categorieQuestionModel.getDetailsCategorie();
                SousDetailsCategorie = categorieQuestionModel.getSousDetailsCategorie();
            }

            // CONTRAINTES REPONSE
            contrainte.setCaractereAccepte(Q.getCaratereAccepte());
            contrainte.setNbreValeurMinimal(Q.getNbreValeurMinimal());

            contrainte.setNombreCaratereMinimal(Q.getNbreCaratereMaximal());
            contrainte.setNombreCaratere(Q.getNbreCaratereMaximal());
            contrainte.setNombreCaratereMaximal(Q.getNbreCaratereMaximal());

            if (Q.getNbreCaratereMaximal() > 0) this.NbChar = contrainte.getNombreCaratere();
            else this.NbChar = 0;
            //}
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception : GetQuestionInfo() : ", ex);
            ex.printStackTrace();
        }
    }
    //endregion

    //region "METHODE QUESTION SUIVANTE ET PRECEDENTE"
    public void LoadFirst_Question(FormDataMngr formDataMngr, String codeModule) {
        try {
            questionsModel = formDataMngr.getFirstQuestionOfModule(codeModule);
            GetQuestionInfo(questionsModel, formDataMngr);
        } catch (ManagerException ex) {
            ToastUtility.LogCat("Exception :LoadFirst_Question() : ", ex);
            ex.printStackTrace();
        }
    }//

    //
    public void Goto_QuestionSuivante(FormDataMngr formDataMngr, String CodeQuestion) {
        try {
            questionsModel = formDataMngr.getNextQuestionByCode(CodeQuestion);
            GetQuestionInfo(questionsModel, formDataMngr);
        } catch (ManagerException ex) {
            ToastUtility.LogCat(context, "ManagerException-Goto_QuestionSuivante() : onClick : ", ex);
            ex.printStackTrace();
        } catch (Exception ex) {
            ToastUtility.LogCat(context, "Exception-Goto_QuestionSuivante() : onClick : ", ex);
            ex.printStackTrace();
        }
    }//
    //endregion

    //region SET REPONSE
    public void setReponse(Spinner sp_reponse, String codeReponse, int classeType) {
        if (classeType == Constant.CLASSE_REPONSE_MODEL) {
            ArrayAdapter<QuestionReponseModel> reponsModel = (ArrayAdapter<QuestionReponseModel>) sp_reponse.getAdapter();
            for (int i = 0; i < reponsModel.getCount(); i++) {
                if (reponsModel.getItem(i).getCodeReponse().equalsIgnoreCase(codeReponse)) {
                    sp_reponse.setSelection(i);
                    break;
                }
            }
        } else if (classeType == Constant.CLASSE_KEY_VALUE_MODEL) {
            ArrayAdapter<KeyValueModel> keyValueModel = (ArrayAdapter<KeyValueModel>) sp_reponse.getAdapter();
            if (keyValueModel != null) {
                for (int i = 0; i < keyValueModel.getCount(); i++) {
                    if (keyValueModel.getItem(i).getKey().equalsIgnoreCase(codeReponse) || keyValueModel.getItem(i).getKey().equalsIgnoreCase("0" + codeReponse)) {
                        sp_reponse.setSelection(i);
                        break;
                    }
                }
            }
        } else if (classeType == Constant.TYPE_QUESTION_CHOIX_COMMUNE_6) {
            ArrayAdapter<CommuneModel> communeModel = (ArrayAdapter<CommuneModel>) sp_reponse.getAdapter();
            if (communeModel != null) {
                for (int i = 0; i < communeModel.getCount(); i++) {
                    if (communeModel.getItem(i).getComId().equalsIgnoreCase(codeReponse)) {
                        //ToastUtility.LogCat("[+] getDeptId>> " + communeModel.getItem(i).getDeptId() + " / getComId>> " + communeModel.getItem(i).getComId() + " / getComNom>> " + communeModel.getItem(i).getComNom());
                        sp_reponse.setSelection(i);
                        break;
                    }
                    //ToastUtility.LogCat("[-] getDeptId>> " + communeModel.getItem(i).getDeptId() + " / getComId>> " + communeModel.getItem(i).getComId() + " / getComNom>> " + communeModel.getItem(i).getComNom());
                }
            }
        } else if (classeType == Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7) {
            ArrayAdapter<VqseModel> vqseModel = (ArrayAdapter<VqseModel>) sp_reponse.getAdapter();
            if (vqseModel != null) {
                for (int i = 0; i < vqseModel.getCount(); i++) {
                    if (vqseModel.getItem(i).getVqseId().equalsIgnoreCase(codeReponse)) {
                        //ToastUtility.LogCat("[+] getVqseId>> " + vqseModel.getItem(i).getVqseId() + " / getVqseNom>> " + vqseModel.getItem(i).getVqseNom());
                        sp_reponse.setSelection(i);
                        break;
                    }
                    //ToastUtility.LogCat("[-] getVqseId>> " + vqseModel.getItem(i).getVqseId() + " / getVqseNom>> " + vqseModel.getItem(i).getVqseNom());
                }
            }
        }

    }//

    private int mSelectedItem = -1;
    public void setReponse(RecyclerView recyclerViewReponse, String codeReponse, int classeType) {
        try {
            if(this.TypeEvenement == Constant.ACTION_AFFICHER) {
                if(codeReponse.equalsIgnoreCase("null")){
                    ToastUtility.ToastMessage(context, "Se sou kesyon [ " + this.codeQuestion + " ] sa Ajan an te rive...", Constant.GravityCenter);
                }
            }
            if (classeType == Constant.CLASSE_REPONSE_MODEL) {
                RadioListAdapter radioListAdapter = (RadioListAdapter) recyclerViewReponse.getAdapter();
                RecyclerView.LayoutManager lmRecyclerView = recyclerViewReponse.getLayoutManager();
                if (radioListAdapter.getReponseModelList() != null) {
                    int position = 1;

                    for (QuestionReponseModel qRM : radioListAdapter.getReponseModelList()) {
                        if (qRM.getCodeReponse().equalsIgnoreCase(codeReponse)) {
                            ToastUtility.LogCat("I", "SetReponse : [+] : getCodeReponse: " + qRM.getCodeReponse() + " / getLibelleReponse: " + qRM.getLibelleReponse() + " codeReponse: " + codeReponse);
                            //recyclerViewReponse.setSelected(true);
                            //mSelectedItem = lmRecyclerView.getPosition(recyclerViewReponse);
                            radioListAdapter.tryMoveSelection(lmRecyclerView, position);
                            //qRM.radioButton.setChecked(position == mSelectedItem);
                            //qRM.radioButton.setChecked(true);
                            //ToastUtility.LogCat("E", "SetReponse : position : " + position + " |  mSelectedItem : " + mSelectedItem);
                            break;
                        }
                        //ToastUtility.LogCat("W", "position : " + position + " |  mSelectedItem : " + mSelectedItem);
                        position++;
                        //ToastUtility.LogCat("I", "SetReponse : [-] : getCodeReponse: " + qRM.getCodeReponse() + " / getLibelleReponse: " + qRM.getLibelleReponse() + " codeReponse: " + codeReponse);
                    }
                }
            }else if (classeType == Constant.CLASSE_KEY_VALUE_MODEL) {
                RadioListAdapterKeyValue reponsModel = (RadioListAdapterKeyValue) recyclerViewReponse.getAdapter();
                RecyclerView.LayoutManager lmRecyclerView = recyclerViewReponse.getLayoutManager();
                if (reponsModel.getKeyValueModelList() != null) {
                    int position = 1;
                    for (KeyValueModel kvm : reponsModel.getKeyValueModelList()) {
                        if (kvm.getKey().equalsIgnoreCase(codeReponse)) {
                            ToastUtility.LogCat("I", "SetReponse : [+] : getKey: " + kvm.getKey() + " / getValue: " + kvm.getValue()  + " codeReponse: " + codeReponse);
                            //recyclerViewReponse.setSelected(true);
                            //mSelectedItem = lmRecyclerView.getPosition(recyclerViewReponse);
                            reponsModel.tryMoveSelection(lmRecyclerView, position);
                            //kvm.radioButton.setChecked(position == mSelectedItem);
                            //kvm.radioButton.setChecked(true);
                            //ToastUtility.LogCat("E", "SetReponse : position : " + position + " |  mSelectedItem : " + mSelectedItem);
                            break;
                        }
                        //ToastUtility.LogCat("W", "position : " + position + " |  mSelectedItem : " + mSelectedItem);
                        position++;
                        //ToastUtility.LogCat("I", "SetReponse : [-] : getKey: " + kvm.getKey() + " / getValue: " + kvm.getValue()  + " codeReponse: " + codeReponse);
                    }
                }
            }
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception: setReponse : toString():", ex);
            ex.printStackTrace();
        }
    }//
    // Start with first item selected

    public void setReponseDate(Spinner sp_Mois, Spinner sp_Annee, String codeReponse) {
        try {
            String[] JJMMAAAA = codeReponse.split("-"); // MM-dd-yyyy
            String mois = JJMMAAAA[0]; // Mois
            String jour = JJMMAAAA[1];  // Jour
            String annee = JJMMAAAA[2]; // Annee
            ArrayAdapter<KeyValueModel> keyMois = (ArrayAdapter<KeyValueModel>) sp_Mois.getAdapter();
            for (int i = 0; i < keyMois.getCount(); i++) {
                if (keyMois.getItem(i).getKey().equalsIgnoreCase(mois) || keyMois.getItem(i).getKey().equalsIgnoreCase("0" + mois)) {
                    sp_Mois.setSelection(i);
                    break;
                }
            }
            ArrayAdapter<KeyValueModel> keyAnnee = (ArrayAdapter<KeyValueModel>) sp_Annee.getAdapter();
            for (int i = 0; i < keyAnnee.getCount(); i++) {
                if (keyAnnee.getItem(i).getKey().equalsIgnoreCase(annee) || keyAnnee.getItem(i).getKey().equalsIgnoreCase("0" + annee)) {
                    sp_Annee.setSelection(i);
                    break;
                }
            }
            /*Calendar mydate = new GregorianCalendar();
            Date thedate = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).parse(codeReponse);
            mydate.setTime(thedate);

            ArrayAdapter<KeyValueModel> keyMois = (ArrayAdapter<KeyValueModel>) sp_Mois.getAdapter();
            int mois = (1 + mydate.get(Calendar.MONTH));
            for (int i = 0; i < keyMois.getCount(); i++) {
                if ( keyMois.getItem(i).getKey().equalsIgnoreCase(String.valueOf(mois)) ) {
                    sp_Mois.setSelection(i);
                    break;
                }
            }
            ArrayAdapter<KeyValueModel> keyAnnee = (ArrayAdapter<KeyValueModel>) sp_Annee.getAdapter();
            for (int i = 0; i < keyAnnee.getCount(); i++) {
                if (keyAnnee.getItem(i).getKey().equalsIgnoreCase(String.valueOf(mydate.get(Calendar.YEAR)))) {
                    sp_Annee.setSelection(i);
                    break;
                }
            }*/
        } catch (Exception ex) {
            ToastUtility.LogCat(context, "Exception: setReponseDate : toString():", ex);
            ex.printStackTrace();
        }
    }//

    public void setReponseDate(Spinner sp_Jour, Spinner sp_Mois, Spinner sp_Annee, String codeReponse) {
        try {
            String[] JJMMAAAA = codeReponse.split("-"); // MM-dd-yyyy
            String mois = JJMMAAAA[0]; // Mois
            String jour = JJMMAAAA[1];  // Jour
            String annee = JJMMAAAA[2]; // Annee
            ArrayAdapter<KeyValueModel> keyJour = (ArrayAdapter<KeyValueModel>) sp_Jour.getAdapter();
            for (int i = 0; i < keyJour.getCount(); i++) {
                if (keyJour.getItem(i).getKey().equalsIgnoreCase(String.valueOf(jour))) {
                    sp_Jour.setSelection(i);
                    break;
                }
            }
            ArrayAdapter<KeyValueModel> keyMois = (ArrayAdapter<KeyValueModel>) sp_Mois.getAdapter();
            for (int i = 0; i < keyMois.getCount(); i++) {
                if (keyMois.getItem(i).getKey().equalsIgnoreCase(mois) || keyMois.getItem(i).getKey().equalsIgnoreCase("0" + mois)) {
                    sp_Mois.setSelection(i);
                    break;
                }
            }
            ArrayAdapter<KeyValueModel> keyAnnee = (ArrayAdapter<KeyValueModel>) sp_Annee.getAdapter();
            for (int i = 0; i < keyAnnee.getCount(); i++) {
                if (keyAnnee.getItem(i).getKey().equalsIgnoreCase(annee) || keyAnnee.getItem(i).getKey().equalsIgnoreCase("0" + annee)) {
                    sp_Annee.setSelection(i);
                    break;
                }
            }
            /*Calendar mydate = new GregorianCalendar();
            Date thedate = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).parse(codeReponse);
            mydate.setTime(thedate);

            ArrayAdapter<KeyValueModel> keyJour = (ArrayAdapter<KeyValueModel>) sp_Jour.getAdapter();
            int jour = (mydate.get(Calendar.DAY_OF_MONTH));
            for (int i = 0; i < keyJour.getCount(); i++) {
                if ( keyJour.getItem(i).getKey().equalsIgnoreCase(String.valueOf(jour)) ) {
                    sp_Jour.setSelection(i);
                    break;
                }
            }
            ArrayAdapter<KeyValueModel> keyMois = (ArrayAdapter<KeyValueModel>) sp_Mois.getAdapter();
            int mois = (1 + mydate.get(Calendar.MONTH));
            for (int i = 0; i < keyMois.getCount(); i++) {
                if ( keyMois.getItem(i).getKey().equalsIgnoreCase(String.valueOf(mois)) ) {
                    sp_Mois.setSelection(i);
                    break;
                }
            }
            ArrayAdapter<KeyValueModel> keyAnnee = (ArrayAdapter<KeyValueModel>) sp_Annee.getAdapter();
            for (int i = 0; i < keyAnnee.getCount(); i++) {
                if (keyAnnee.getItem(i).getKey().equalsIgnoreCase(String.valueOf(mydate.get(Calendar.YEAR)))) {
                    sp_Annee.setSelection(i);
                    break;
                }
            }*/
        } catch (Exception ex) {
            ToastUtility.LogCat(context, "Exception: setReponseDate : toString():", ex);
            ex.printStackTrace();
        }
    }//

    public void setReponseDate(EditText et_Jour, Spinner sp_Mois2, EditText et_Annee, String codeReponse) {
        try {
            String[] JJMMAAAA = codeReponse.split("-"); // MM-dd-yyyy
            String mois = JJMMAAAA[0]; // Mois
            String jour = JJMMAAAA[1];  // Jour
            String annee = JJMMAAAA[2]; // Annee

            et_Jour.setText(jour);
            ArrayAdapter<KeyValueModel> keyMois = (ArrayAdapter<KeyValueModel>) sp_Mois2.getAdapter();
            for (int i = 0; i < keyMois.getCount(); i++) {
                if (keyMois.getItem(i).getKey().equalsIgnoreCase(mois) || keyMois.getItem(i).getKey().equalsIgnoreCase("0" + mois)) {
                    sp_Mois2.setSelection(i);
                    break;
                }
            }
            et_Annee.setText(annee);
        } catch (Exception ex) {
            ToastUtility.LogCat(context, "Exception: setReponseDate : toString():", ex);
            ex.printStackTrace();
        }
    }//

    public void setReponseDate( Spinner sp_Mois2, EditText et_Annee, String codeReponse) {
        try {
            String[] JJMMAAAA = codeReponse.split("-"); // MM-dd-yyyy
            String mois = JJMMAAAA[0]; // Mois
            String jour = JJMMAAAA[1];  // Jour
            String annee = JJMMAAAA[2]; // Annee

            ArrayAdapter<KeyValueModel> keyMois = (ArrayAdapter<KeyValueModel>) sp_Mois2.getAdapter();
            for (int i = 0; i < keyMois.getCount(); i++) {
                if (keyMois.getItem(i).getKey().equalsIgnoreCase(mois) || keyMois.getItem(i).getKey().equalsIgnoreCase("0" + mois)) {
                    sp_Mois2.setSelection(i);
                    break;
                }
            }
            et_Annee.setText(annee);
        } catch (Exception ex) {
            ToastUtility.LogCat(context, "Exception: setReponseDate : toString():", ex);
            ex.printStackTrace();
        }
    }//

    //endregion

    //region METHODES LOAD DATA
    public void Load_PossibiliteReponse(Context context, FormDataMngr formDataMngr, RecyclerView recyclerViewReponse, RadioListAdapter radioListAdapter, OnItemClickListener getItemClickListener) {
        try {
            List<QuestionReponseModel> reponseModelList1 = formDataMngr.searchQuestionReponseByQuestion(this.getCodeQuestion());

            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            //adding codes before you clear your collection
            recyclerViewReponse.getRecycledViewPool().clear();
            // Attach the layout manager to the recycler view
            gridLayoutManager.setAutoMeasureEnabled(true);
            recyclerViewReponse.setLayoutManager(gridLayoutManager);
            recyclerViewReponse.addItemDecoration(new SimpleDividerItemDecoration(context.getResources()));
            //recyclerView.setItemAnimator(new SlideInUpAnimator());
            recyclerViewReponse.setHasFixedSize(true);
            radioListAdapter = new RadioListAdapter(context, this.TypeEvenement, reponseModelList1);

            //if( this.TypeEvenement != Constant.ACTION_AFFICHER ){
                //  initialize the adapter
                radioListAdapter.setOnItemClickListener(getItemClickListener);
            //}

            //radioListAdapter.setOnItemCheckedChangedListener();
            recyclerViewReponse.setAdapter(radioListAdapter);

        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_PossibiliteReponse(): getMessage: ",  ex);
            //Tools.AlertDialogMsg(context, ex.getMessage(), "E");
            ex.printStackTrace();
        }
    }//

    public void Load_PossibiliteReponse(Context context, FormDataMngr formDataMngr, Spinner spinner) {
        try {
            List<QuestionReponseModel> reponseModelList1 = formDataMngr.searchQuestionReponseByQuestion(this.getCodeQuestion());
            ArrayList<QuestionReponseModel> reponseModelList = new ArrayList<QuestionReponseModel>();
            reponseModelList.add(new QuestionReponseModel("", "", "", "- " + context.getResources().getString(R.string.label_Chwazi_yon_Repons).toString() + " - "));
            if (reponseModelList1 != null) {
                reponseModelList.addAll(reponseModelList1);
            }
            // Creating adapter for spinner
            ArrayAdapter<QuestionReponseModel> dataAdapter = new ArrayAdapter<QuestionReponseModel>(context, R.layout.simple_list_item_1, reponseModelList);
            //ArrayAdapter<QuestionReponseModel> dataAdapter = new ArrayAdapter<QuestionReponseModel>(context, android.R.layout.simple_list_item_1, reponseModelList);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_PossibiliteReponse(): getMessage: ", ex);
            Tools.AlertDialogMsg(context, ex.getMessage(), "E");
            ex.printStackTrace();
        }
    }//
    public void Load_PossibiliteReponse(Context context, FormDataMngr formDataMngr, Spinner spinner, String CodeQuestion) {
        try {
            List<QuestionReponseModel> reponseModelList1 = formDataMngr.searchQuestionReponseByQuestion(CodeQuestion);
            ArrayList<QuestionReponseModel> reponseModelList = new ArrayList<QuestionReponseModel>();
            reponseModelList.add(new QuestionReponseModel("", "", "", "- " + context.getResources().getString(R.string.label_Chwazi_yon_Repons).toString() + " - "));
            if (reponseModelList1 != null) {
                reponseModelList.addAll(reponseModelList1);
            }
            // Creating adapter for spinner
            ArrayAdapter<QuestionReponseModel> dataAdapter = new ArrayAdapter<QuestionReponseModel>(context, R.layout.simple_list_item_1, reponseModelList);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_PossibiliteReponse(): getMessage: ", ex);
            Tools.AlertDialogMsg(context, ex.getMessage(), "E");
            ex.printStackTrace();
        }
    }//

    public void Load_PossibiliteReponse_Child(Context context, FormDataMngr formDataMngr, Spinner spinner, String CodeParent) {
        try {
            //ToastUtility.LogCat("INBSIDE - Load_PossibiliteReponse_Child(): CodeQuestion: " + this.getCodeQuestion() + " / CodeParent: " + CodeParent);
            List<QuestionReponseModel> reponseModelList1 = formDataMngr.searchResponsesByQuestion(this.getCodeQuestion(), CodeParent);
            ArrayList<QuestionReponseModel> reponseModelList = new ArrayList<QuestionReponseModel>();
            reponseModelList.add(new QuestionReponseModel("", "", "", "- " + context.getResources().getString(R.string.label_Chwazi_yon_Repons).toString() + " -"));
            //if( reponseModelList1 != null ) {
            reponseModelList.addAll(reponseModelList1);
            //}
            // Creating adapter for spinner
            ArrayAdapter<QuestionReponseModel> dataAdapter = new ArrayAdapter<QuestionReponseModel>(context, R.layout.simple_list_item_1, reponseModelList);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_PossibiliteReponse_Child(): getMessage: ", ex);
            //ToastUtility.LogCat(context, "getMessage: ", ex);
            Tools.AlertDialogMsg(context, ex.getMessage(), "E");
            ex.printStackTrace();
        }
    }//

    public void Load_Age(Context context, Spinner spinner) {
        try {
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_LajMounNan) + " -"));
            keyValueModels.add(new KeyValueModel("0", "- " + context.getResources().getString(R.string.msg_PiPiti_PaseYonLane) + " [000] -"));
            keyValueModels.add(new KeyValueModel("999", " Pa konnen [999]"));
            for (int i = 1; i <= Constant.AGE_120ANS; i++) {
                if (i > 9) {
                    keyValueModels.add(new KeyValueModel("" + i, "" + i + " ane"));
                } else {
                    keyValueModels.add(new KeyValueModel("" + i, "" + i + " ane"));
                }
            }
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//
    public void Load_Age(Context context,  RecyclerView recyclerViewReponse, RadioListAdapterKeyValue radioListAdapter, RadioListAdapterKeyValue.OnItemClickListenerKeyValue getItemClickListener) {
        try {
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            //keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_LajMounNan) + " -"));
            keyValueModels.add(new KeyValueModel("0", "- " + context.getResources().getString(R.string.msg_PiPiti_PaseYonLane) + " [000] -"));
            keyValueModels.add(new KeyValueModel("999", " Pa konnen [999]"));
            for (int i = 1; i <= Constant.AGE_120ANS; i++) {
                if (i > 9) {
                    keyValueModels.add(new KeyValueModel("" + i, "" + i + " ane"));
                } else {
                    keyValueModels.add(new KeyValueModel("" + i, "" + i + " ane"));
                }
            }

            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            //adding codes before you clear your collection
            recyclerViewReponse.getRecycledViewPool().clear();
            // Attach the layout manager to the recycler view
            gridLayoutManager.setAutoMeasureEnabled(true);
            recyclerViewReponse.setLayoutManager(gridLayoutManager);
            recyclerViewReponse.addItemDecoration(new SimpleDividerItemDecoration(context.getResources()));
            //recyclerView.setItemAnimator(new SlideInUpAnimator());
            recyclerViewReponse.setHasFixedSize(true);
            radioListAdapter = new RadioListAdapterKeyValue(context, keyValueModels, View.TEXT_ALIGNMENT_CENTER);
            //  initialize the adapter
            //if( this.TypeEvenement != Constant.ACTION_AFFICHER ){
                radioListAdapter.setOnItemClickListener(getItemClickListener);
            //}
            recyclerViewReponse.setAdapter(radioListAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//
    public void Load_Quantite(Context context, int chiffeDeDepart, int nombreCaratereMaximal, String textAdditionnel, RecyclerView recyclerViewReponse, RadioListAdapterKeyValue radioListAdapter, RadioListAdapterKeyValue.OnItemClickListenerKeyValue getItemClickListener) {
        try {
            String qteChiffre = "";
            if (nombreCaratereMaximal == 9) {
                //nombreCaratereMaximal = 9;
                qteChiffre = "";
            } else if (nombreCaratereMaximal == 99){
                //nombreCaratereMaximal = 99;
                qteChiffre = "0";
            }else  if( nombreCaratereMaximal == 999 ) {
                //nombreCaratereMaximal = 999;
                qteChiffre = "00";
            }else  if( nombreCaratereMaximal == 9999 ) {
                //nombreCaratereMaximal = 9999;
                qteChiffre = "000";
            }

            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            //keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_LajMounNan) + " -"));
            //keyValueModels.add(new KeyValueModel("0", "- " + context.getResources().getString(R.string.msg_PiPiti_PaseYonLane) + " [000] -"));
            //keyValueModels.add(new KeyValueModel("999", " Pa konnen [999]"));
            for (int i = chiffeDeDepart; i <= nombreCaratereMaximal; i++) {
                if (i >= 0 && i<=9) {
                    keyValueModels.add(new KeyValueModel("" + i, ""+ qteChiffre + i + " " + textAdditionnel ));
                } else if (i >= 10 && i<=99) {
                    keyValueModels.add(new KeyValueModel("" + i, "" + i + " " + textAdditionnel ));
                } else if (i >= 100 && i<=999) {
                    keyValueModels.add(new KeyValueModel("" + i, "" + i + " " + textAdditionnel ));
                } else if (i >= 1000 && i<=9999) {
                    keyValueModels.add(new KeyValueModel("" + i, "" + i + " " + textAdditionnel ));
                }
            }

            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            //adding codes before you clear your collection
            recyclerViewReponse.getRecycledViewPool().clear();
            // Attach the layout manager to the recycler view
            gridLayoutManager.setAutoMeasureEnabled(true);
            recyclerViewReponse.setLayoutManager(gridLayoutManager);
            recyclerViewReponse.addItemDecoration(new SimpleDividerItemDecoration(context.getResources()));
            //recyclerView.setItemAnimator(new SlideInUpAnimator());
            recyclerViewReponse.setHasFixedSize(true);
            radioListAdapter = new RadioListAdapterKeyValue(context, keyValueModels, View.TEXT_ALIGNMENT_CENTER);
            //  initialize the adapter
            //if( this.TypeEvenement != Constant.ACTION_AFFICHER ){
                radioListAdapter.setOnItemClickListener(getItemClickListener);
            //}
            recyclerViewReponse.setAdapter(radioListAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_Jour(Context context, Spinner spinner) {
        try {
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Jour) + " -"));
            keyValueModels.add(new KeyValueModel("99", "- " + context.getResources().getString(R.string.msg_Pakonnen) + " [99] -"));
            for (int i = 1; i <= 31; i++) {
                if (i > 9) {
                    keyValueModels.add(new KeyValueModel("" + i, "" + i));
                } else {
                    keyValueModels.add(new KeyValueModel("" + i, "0" + i));
                }
            }
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_Mois(Context context, Spinner spinner) {
        try {
            final KeyValueModel[] keyValueModels = {
                    new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Mois) + " -", "0"),
                    new KeyValueModel("99", "- " + context.getResources().getString(R.string.msg_Pakonnen) + " [ 99 ] -", "31"),
                    new KeyValueModel("1", "Janvye", "31"),
                    new KeyValueModel("2", "Fevriye", "28"),
                    new KeyValueModel("3", "Mas", "31"),
                    new KeyValueModel("4", "Avril", "30"),
                    new KeyValueModel("5", "Me", "31"),
                    new KeyValueModel("6", "Jen", "30"),
                    new KeyValueModel("7", "Jiyè", "31"),
                    new KeyValueModel("8", "Out", "31"),
                    new KeyValueModel("9", "Septanm", "30"),
                    new KeyValueModel("10", "Oktòb", "31"),
                    new KeyValueModel("11", "Novanm", "30"),
                    new KeyValueModel("12", "Desanm", "31"),
            };
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_Annee(Context context, Spinner spinner) {
        try {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Annee) + " -"));
            keyValueModels.add(new KeyValueModel("9999", "- " + context.getResources().getString(R.string.msg_Pakonnen) + " [9999] -"));
            for (int i = year; i >= 1897; i--) {
                keyValueModels.add(new KeyValueModel("" + i, "" + i));
            }
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_Pays(FormDataMngr formDataMngr, Spinner spinner) {
        try {
            List<KeyValueModel> reponseModelList1 = formDataMngr.getAllPays();
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_yon_pays) + " -"));
            keyValueModels.addAll(reponseModelList1);

            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_Pays(): getMessage: ", ex);
            ex.printStackTrace();
        }
    }//
    public void Load_Pays(Context context, FormDataMngr formDataMngr, RecyclerView recyclerViewReponse, RadioListAdapterKeyValue radioListAdapterKeyValue, RadioListAdapterKeyValue.OnItemClickListenerKeyValue getItemClickListener) {
        try {
            List<KeyValueModel> reponseModelList = formDataMngr.getAllPays();
            //ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            //keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_yon_pays) + " -"));
            //keyValueModels.addAll(reponseModelList1);

            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            //adding codes before you clear your collection
            recyclerViewReponse.getRecycledViewPool().clear();
            // Attach the layout manager to the recycler view
            gridLayoutManager.setAutoMeasureEnabled(true);
            recyclerViewReponse.setLayoutManager(gridLayoutManager);
            recyclerViewReponse.addItemDecoration(new SimpleDividerItemDecoration(context.getResources()));
            //recyclerView.setItemAnimator(new SlideInUpAnimator());
            recyclerViewReponse.setHasFixedSize(true);
            radioListAdapterKeyValue = new RadioListAdapterKeyValue(context, reponseModelList);
            //  initialize the adapter
            //if( this.TypeEvenement != Constant.ACTION_AFFICHER ){
                radioListAdapterKeyValue.setOnItemClickListener(getItemClickListener);
            //}
            //  initialize the adapter
            //radioListAdapterKeyValue.setOnItemClickListener(getItemClickListener);
            recyclerViewReponse.setAdapter(radioListAdapterKeyValue);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_Pays(): getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_Departement(FormDataMngr formDataMngr, Spinner spinner) {
        try {
            List<KeyValueModel> reponseModelList1 = formDataMngr.getAllDepartement();
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_yon_Depatman) + " -"));
            keyValueModels.addAll(reponseModelList1);
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_Departement(): getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_CommuneByIdDept(FormDataMngr formDataMngr, Spinner spinner, String idDept) {
        try {
            List<CommuneModel> reponseModelList1 = formDataMngr.getAllCommuneByIdDept(idDept);
            ArrayList<CommuneModel> communeModels = new ArrayList<CommuneModel>();
            communeModels.add(new CommuneModel("", "- " + context.getResources().getString(R.string.label_Chwazi_yon_Komin) + " -", "0"));
            communeModels.addAll(reponseModelList1);

            // Creating adapter for spinner
            ArrayAdapter<CommuneModel> dataAdapter = new ArrayAdapter<CommuneModel>(context, R.layout.simple_list_item_1, communeModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_Departement(): getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_Vqse_ByIdCommune(FormDataMngr formDataMngr, Spinner spinner, String idCommune) {
        try {
            List<VqseModel> vqseModelList = formDataMngr.getAllVqseByIdCom(idCommune);
            ArrayList<VqseModel> vqseModels = new ArrayList<VqseModel>();
            vqseModels.add(new VqseModel("", "- " + context.getResources().getString(R.string.label_Chwazi_yon_Seksyon_kominal) + " -", "0"));
            vqseModels.addAll(vqseModelList);
            // Creating adapter for spinner
            ArrayAdapter<VqseModel> dataAdapter = new ArrayAdapter<VqseModel>(context, R.layout.simple_list_item_1, vqseModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_Departement(): getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_Zone(Spinner spinner) {
        try {
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_yon_Zone) + " -"));
            keyValueModels.add(new KeyValueModel("" + Constant.ZONE_URBAIN_1, " " + context.getResources().getString(R.string.label_Zone_Urbain) + ""));
            keyValueModels.add(new KeyValueModel("" + Constant.ZONE_RURAL_2, " " + context.getResources().getString(R.string.label_Zone_Rural) + ""));
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_TypeFormulaire(): getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_ClearSpinner(Spinner spinner, int classeType) {
        try {
            if (classeType == Constant.CLASSE_REPONSE_MODEL) {
                ArrayList<QuestionReponseModel> reponseModelList = new ArrayList<QuestionReponseModel>();
                reponseModelList.add(new QuestionReponseModel("", "", "", "- " + context.getResources().getString(R.string.label_Chwazi_yon_Repons).toString() + " -"));
                ArrayAdapter<QuestionReponseModel> dataAdapter = new ArrayAdapter<QuestionReponseModel>(context, R.layout.simple_list_item_1, reponseModelList);
                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                spinner.setAdapter(dataAdapter);
            } else if (classeType == Constant.TYPE_QUESTION_CHOIX_COMMUNE_6) {
                ArrayList<CommuneModel> communeModels = new ArrayList<CommuneModel>();
                communeModels.add(new CommuneModel("", "- " + context.getResources().getString(R.string.label_Chwazi_yon_Komin) + " -", "0"));
                // Creating adapter for spinner
                ArrayAdapter<CommuneModel> dataAdapter = new ArrayAdapter<CommuneModel>(context, R.layout.simple_list_item_1, communeModels);
                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                spinner.setAdapter(dataAdapter);
            } else if (classeType == Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7) {
                ArrayList<VqseModel> vqseModels = new ArrayList<VqseModel>();
                vqseModels.add(new VqseModel("", "- " + context.getResources().getString(R.string.label_Chwazi_yon_Seksyon_kominal) + " -", "0"));
                // Creating adapter for spinner
                ArrayAdapter<VqseModel> dataAdapter = new ArrayAdapter<VqseModel>(context, R.layout.simple_list_item_1, vqseModels);
                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                spinner.setAdapter(dataAdapter);
            }
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_ClearSpinner(): getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_DomaineEtude(FormDataMngr formDataMngr, Spinner spinner) {
        try {
            List<KeyValueModel> reponseModelList1 = formDataMngr.getAllDomaineEtude();
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_yon_Domaine_Etude) + " -"));
            keyValueModels.addAll(reponseModelList1);

            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_Departement(): getMessage: ", ex);
            ex.printStackTrace();
        }
    }//
    public void Load_DomaineEtude(Context context, FormDataMngr formDataMngr, RecyclerView recyclerViewReponse, RadioListAdapterKeyValue radioListAdapterKeyValue, RadioListAdapterKeyValue.OnItemClickListenerKeyValue getItemClickListener ) {
        try {
            List<KeyValueModel> keyValueModels = formDataMngr.getAllDomaineEtude();
            //ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            //keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_yon_Domaine_Etude) + " -"));
            //keyValueModels.addAll(reponseModelList1);

            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            //adding codes before you clear your collection
            recyclerViewReponse.getRecycledViewPool().clear();
            // Attach the layout manager to the recycler view
            gridLayoutManager.setAutoMeasureEnabled(true);
            recyclerViewReponse.setLayoutManager(gridLayoutManager);
            recyclerViewReponse.addItemDecoration(new SimpleDividerItemDecoration(context.getResources()));
            //recyclerView.setItemAnimator(new SlideInUpAnimator());
            recyclerViewReponse.setHasFixedSize(true);
            radioListAdapterKeyValue = new RadioListAdapterKeyValue(context, keyValueModels);
            //  initialize the adapter
            //if( this.TypeEvenement != Constant.ACTION_AFFICHER ){
                radioListAdapterKeyValue.setOnItemClickListener(getItemClickListener);
            //}
            //  initialize the adapter
            //radioListAdapterKeyValue.setOnItemClickListener(getItemClickListener);
            recyclerViewReponse.setAdapter(radioListAdapterKeyValue);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_Departement(): getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_Sexe(Context context, Spinner spinner) {
        try {
            final KeyValueModel[] keyValueModels = {
                    new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_seks) + " -"),
                    new KeyValueModel("1", "1. Yon Gason"),
                    new KeyValueModel("2", "2. Yon Fi")
            };
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_Relation(Context context, Spinner spinner) {
        try {
            final KeyValueModel[] keyValueModels = {
                    new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_RelasyonMounNan) + " -"),
                    new KeyValueModel("1", "01- Chèf menaj la "),
                    new KeyValueModel("2", "02- Mari / Madanm "),
                    new KeyValueModel("3", "03- Pitit gason / Piti fi "),
                    new KeyValueModel("4", "04- Frè / sè  "),
                    new KeyValueModel("5", "05- Neve / Nyès "),
                    new KeyValueModel("6", "06. Papa / Manman "),
                    new KeyValueModel("7", "07- Pitit Pitit gason / Pitit Pitit fi "),
                    new KeyValueModel("8", "08- Bòpè / Bèlmè mari a oswa madanm nan "),
                    new KeyValueModel("9", "09- Bofis / Bèlfi "),
                    new KeyValueModel("10", "10- Lòt paran "),
                    new KeyValueModel("11", "11- Moun kap travay ki touche pou sa  "),
                    new KeyValueModel("12", "12- Moun kap travay san touche  "),
                    new KeyValueModel("13", "13- Timoun ki rete ak nou (Restavèk) "),
                    new KeyValueModel("14", "14- Lòt moun ki pa gen oken n lyen ak chèf menaj la  ")
            };
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_MounNanMenajLa(Context context, Spinner spinner) {
        try {
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();
            keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_MounNanMenajLa) + " -"));
            keyValueModels.add(new KeyValueModel("1", "1- Wi li toujou la, se la li dómi, manje ak tout lót moun ki nan lojman an"));
            keyValueModels.add(new KeyValueModel("2", "2- Wi li toujou la, men li okipe zafè l ak manjel a pa .....   fini la a pou moun sa a"));
            keyValueModels.add(new KeyValueModel("3", "3- Wi li toujou la, men li fë yon deplase pou mwens pase 6 mwa"));
            keyValueModels.add(new KeyValueModel("4", "4- Wi, men li deplase pou plis pase 6 mwa ....... fini la a pou moun sa a"));
            keyValueModels.add(new KeyValueModel("5", "5- Se yon ti moun ki fèk fèt "));
            keyValueModels.add(new KeyValueModel("6", "6- Se yon moun ki gran moun anpil"));
            keyValueModels.add(new KeyValueModel("7", "7- Li fèk vini men li gen entansyon rete la a"));
            keyValueModels.add(new KeyValueModel("8", "8- Li fèk vini li gen entansyon rete la a, men lap  okipe zafè l ak manje l a pa  .....   fini la a pou moun sa a"));
            keyValueModels.add(new KeyValueModel("9", "9- Li nan kay la pou mwens ke 6 mwa  .... fini la a pou moun sa a"));

            /*final KeyValueModel[] keyValueModels = {
                    new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_MounNanMenajLa) + " -"),
            };*/
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_RaisonRAR(Context context, QuestionnaireFormulaireUtility QF, Spinner spinner, int statut) {
        try {
            ArrayList<KeyValueModel> keyValueModels = new ArrayList<KeyValueModel>();

            String codeReponse = "";

            if (this.tbl_TableName == Constant.FORMULAIRE_BATIMENT) {
                codeReponse = FieldMapperUtils.getFieldValue(this.batimentModel, this.nomChamps);

            } else if (this.tbl_TableName == Constant.FORMULAIRE_LOGEMENT_COLLECTIF || this.tbl_TableName == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL) {
                //codeReponse = FieldMapperUtils.getFieldValue(this.logementModel, this.nomChamps);

            } else if (this.tbl_TableName == Constant.FORMULAIRE_MENAGE) {
                //codeReponse = FieldMapperUtils.getFieldValue(this.menageModel, this.nomChamps);

            } else if (this.tbl_TableName == Constant.FORMULAIRE_EMIGRE) {
                //codeReponse = FieldMapperUtils.getFieldValue(this.emigreModel, this.nomChamps);

            } else if (this.tbl_TableName == Constant.FORMULAIRE_DECES) {
                //codeReponse = FieldMapperUtils.getFieldValue(this.decesModel, this.nomChamps);

            } else if (this.tbl_TableName == Constant.FORMULAIRE_INDIVIDUS) {
                //codeReponse = FieldMapperUtils.getFieldValue(this.individuModel, this.nomChamps);
            }

            if (statut == Constant.STATUT_RANPLI_NET_11) {// RAPPORT FINAL
                //A.- ENTIÈREMENT REMPLI / A.- RANPLI NÈT
                if (this.TypeEvenement == Constant.ACTION_NOUVEAU) {//if (isRetour == Constant.Oui_1) {
                    // region POUR BATIMENT UNIQUEMENT
                    //C.- PAS REMPLI DU TOUT / C.- PA RANPLI DITOU
                    if (this.nomChamps.equalsIgnoreCase( BatimentDao.Properties.Qb1Etat.columnName )) {
                        keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_Rezon) + " -"));
                        if (codeReponse.equalsIgnoreCase("" + Constant.BATIMAN_Pa_konnen_paske_li_pa_sou_je_5)) {
                            if (this.TypeEvenement == Constant.ACTION_NOUVEAU) {
                                keyValueModels.add(new KeyValueModel("15", "Pa gen mwayen obsève batiman an / pa gen enfòmasyon sou lojman an"));
                                keyValueModels.add(new KeyValueModel("16", "Moun yo refize reponn nètalkole"));
                                keyValueModels.add(new KeyValueModel("17", "Moun ki pou reponn nan pa la/ pa disponib men gen yon randevou"));
                                keyValueModels.add(new KeyValueModel("18", "Moun ki pou reponn nan pa la oubyen li pa disponib"));
                                keyValueModels.add(new KeyValueModel("19", "Lòt. Presize poukisa..."));
                            } else {
                                keyValueModels.add(new KeyValueModel("20", "Pa janm gen mwayen obsève batiman an"));
                                keyValueModels.add(new KeyValueModel("21", "\"Refus non converti\" / Non-réponse totale (NRT)"));
                                keyValueModels.add(new KeyValueModel("22", "Moun ki pou reponn nan pa janm la/disponib pou AR la ranpli kesyonè a"));
                                /* (absence, indisponbilité, avec rendez-vous)  ,  (absence, indisponibilité) */
                                keyValueModels.add(new KeyValueModel("23", "Lòt. Presize poukisa..."));
                            }
                        }//Sinon lap pase a lot kesyon yo...
                    } else {
                        if (this.tbl_TableName == Constant.FORMULAIRE_BATIMENT) {
                            keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_Rezon) + " -"));

                            keyValueModels.add(new KeyValueModel("1", "Ranpli nèt / depi nan batiman rive nan manb menaj la"));
                            keyValueModels.add(new KeyValueModel("2", "Ranpli nèt / Batiman vid"));
                            keyValueModels.add(new KeyValueModel("3", "Ranpli nèt / Lojman vid"));
                        } else if (this.tbl_TableName == Constant.FORMULAIRE_LOGEMENT_COLLECTIF || this.tbl_TableName == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL) {
                            keyValueModels.add(new KeyValueModel("25", "Lojman Ranpli"));

                        } else if (this.tbl_TableName == Constant.FORMULAIRE_MENAGE) {
                            keyValueModels.add(new KeyValueModel("26", "Menaj Ranpli"));

                        } else if (this.tbl_TableName == Constant.FORMULAIRE_EMIGRE) {
                            keyValueModels.add(new KeyValueModel("27", "Emigre Ranpli"));

                        } else if (this.tbl_TableName == Constant.FORMULAIRE_DECES) {
                            keyValueModels.add(new KeyValueModel("28", "Moun mouri Ranpli"));

                        } else if (this.tbl_TableName == Constant.FORMULAIRE_INDIVIDUS) {
                            keyValueModels.add(new KeyValueModel("29", "Moun Ranpli"));

                        }
                    }
                    //endregion
                } else { // Si se yon retour / Update

                    if (this.tbl_TableName == Constant.FORMULAIRE_BATIMENT) {
                        keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_Rezon) + " -"));
                        //* tous objets  ,  bâtiment vide (confirmé)  ,  logement vide (confirmé) *//
                        keyValueModels.add(new KeyValueModel("4", "Ranpli nèt depi nan premye entèvyou a"));
                        //* (abandon), (refus) *//*
                        keyValueModels.add(new KeyValueModel("5", "Refus converti"));
                        //* (interruption avec rendez-vous)	,  (absence répondants avec rendez-vous)  ,  (logement occupé, occupants absents)	 *//
                        keyValueModels.add(new KeyValueModel("6", "Randevou ou retou pwograme/fèt/fini"));

                    } else if (this.tbl_TableName == Constant.FORMULAIRE_LOGEMENT_COLLECTIF
                            || this.tbl_TableName == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL) {
                        keyValueModels.add(new KeyValueModel("25", "Lojman Ranpli"));

                    } else if (this.tbl_TableName == Constant.FORMULAIRE_MENAGE) {
                        keyValueModels.add(new KeyValueModel("26", "Menaj Ranpli"));

                    } else if (this.tbl_TableName == Constant.FORMULAIRE_EMIGRE) {
                        keyValueModels.add(new KeyValueModel("27", "Emigre Ranpli"));

                    } else if (this.tbl_TableName == Constant.FORMULAIRE_DECES) {
                        keyValueModels.add(new KeyValueModel("28", "Moun mouri Ranpli"));

                    } else if (this.tbl_TableName == Constant.FORMULAIRE_INDIVIDUS) {
                        keyValueModels.add(new KeyValueModel("29", "Moun Ranpli"));

                    }
                }
            }

            if (statut == Constant.STATUT_PA_FIN_RANPLI_22) {// STATUT_PA_FIN_RANPLI_22
                //B.- REMPLI PARTIELLEMENT / B. PA FIN RANPLI
                keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_Rezon) + " -"));
                if (this.TypeEvenement == Constant.ACTION_NOUVEAU) {
                    keyValueModels.add(new KeyValueModel("7", "Moun ki tap reponn nan pa vle kontinye"));
                    keyValueModels.add(new KeyValueModel("8", "Moun ki tap reponn nan kanpe, men gen randevou"));
                    keyValueModels.add(new KeyValueModel("9", "Lojman an okipe, men moun yo pa la"));
                    keyValueModels.add(new KeyValueModel("10", "Lòt. Presize poukisa..."));
                } else {
                    keyValueModels.add(new KeyValueModel("11", "\"Refus non converti\" / Kesyonè a pa fini (NRP)"));
                    keyValueModels.add(new KeyValueModel("12", "Moun ki pou reponn nan pa janm la/disponib pou AR la fin ranpli kesyonè a"));
                    keyValueModels.add(new KeyValueModel("13", "Lojman an okipe, men moun yo pa la"));
                    keyValueModels.add(new KeyValueModel("14", "Lòt. Presize poukisa..."));
                }
            }
            if (statut == Constant.STATUT_PA_RANPLI_DITOU_33) {
                if (this.tbl_TableName == Constant.FORMULAIRE_BATIMENT) {
                    keyValueModels.add(new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_Rezon) + " -"));
                    //region C.- PAS REMPLI DU TOUT / C.- PA RANPLI DITOU
                    if (this.TypeEvenement == Constant.ACTION_NOUVEAU) {
                        keyValueModels.add(new KeyValueModel("15", "Pa gen mwayen obsève batiman an / pa gen enfòmasyon sou lojman an"));
                        keyValueModels.add(new KeyValueModel("16", "Moun yo refize reponn nètalkole"));
                        keyValueModels.add(new KeyValueModel("17", "Moun ki pou reponn nan pa la/ pa disponib men gen yon randevou"));
                        keyValueModels.add(new KeyValueModel("18", "Moun ki pou reponn nan pa la oubyen li pa disponib"));
                        keyValueModels.add(new KeyValueModel("19", "Lòt. Presize poukisa..."));
                    } else {
                        keyValueModels.add(new KeyValueModel("20", "Pa janm gen mwayen obsève batiman an"));
                        keyValueModels.add(new KeyValueModel("21", "\"Refus non converti\" / Non-réponse totale (NRT)"));
                        keyValueModels.add(new KeyValueModel("22", "Moun ki pou reponn nan pa janm la/disponib pou AR la ranpli kesyonè a"));
                                /* (absence, indisponbilité, avec rendez-vous)  ,  (absence, indisponibilité) */
                        keyValueModels.add(new KeyValueModel("23", "Lòt. Presize poukisa..."));
                    }
                    //endregion
                }
            }

            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_ReponseRapportFinal(Context context, Spinner spinner) {
        try {
            final KeyValueModel[] keyValueModels = {
                    new KeyValueModel("", "- " + context.getResources().getString(R.string.label_Chwazi_yon_Repons) + " -"),
                    new KeyValueModel("1", "1. Wi"),
                    new KeyValueModel("2", "2. Non")
            };
            // Creating adapter for spinner
            ArrayAdapter<KeyValueModel> dataAdapter = new ArrayAdapter<KeyValueModel>(context, R.layout.simple_list_item_1, keyValueModels);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat( "getMessage: ", ex);
            ex.printStackTrace();
        }
    }//

    public void Load_IndividuMenage(QueryRecordMngr queryRecordMngr, Spinner spinner, long menageId) {
        try {
            List<IndividuModel> individuModels = queryRecordMngr.getAllListIndividu_ByMenage(menageId);
            ArrayList<IndividuModel> individuModelArrayList = new ArrayList<IndividuModel>();
            individuModelArrayList.add(new IndividuModel((long)0, (short) 0, "- " + context.getResources().getString(R.string.label_Chwazi_yon_Repons) + " -",""));
            individuModelArrayList.addAll(individuModels);
            // Creating adapter for spinner
            ArrayAdapter<IndividuModel> dataAdapter = new ArrayAdapter<IndividuModel>(context, R.layout.simple_list_item_1, individuModelArrayList);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Load_IndividuMenage(): getMessage: ", ex);
            ex.printStackTrace();
        }
    }//
    //endregion

    //region Check Contrainte Saut Champs Valeur
    public void Check_ContrainteSautChampsValeur(QueryRecordMngr queryRecordMngr, CURecordMngr cuRecordMngr, String codeReponse) throws TextEmptyException {
        try {
            //ToastUtility.LogCat( "TOP - Check_ContrainteSautChampsValeur");
            //----------------- CONTRAINTE BATIMANT -----------------//
            if (Constant.FORMULAIRE_BATIMENT == this.tbl_TableName) {
                BatimentModel batimentModel = (BatimentModel) this.data;
                BatimentModel.queryRecordMngr = queryRecordMngr;
                String QSuivant_Val = BatimentModel.Check_ContrainteSautChampsValeur(this.nomChamps, batimentModel, this.iDKeys, dataBase, true);
                if (!QSuivant_Val.equalsIgnoreCase("")) {
                    this.qSuivant = QSuivant_Val;
                }
            } else if (Constant.FORMULAIRE_LOGEMENT_COLLECTIF == this.tbl_TableName
                    || Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL == this.tbl_TableName) {
                LogementModel logementModel = (LogementModel) this.data;
                LogementModel.queryRecordMngr = queryRecordMngr;
                //LogementModel.formDataMngr = this.formDataMngr;
                String QSuivant_Val = LogementModel.Check_ContrainteSautChampsValeur(this.nomChamps, logementModel, this.iDKeys, dataBase);
                if (!QSuivant_Val.equalsIgnoreCase("")) {
                    this.qSuivant = QSuivant_Val;
                }
            } else if (Constant.FORMULAIRE_MENAGE == this.tbl_TableName) {
                MenageModel menageModel = (MenageModel) this.data;
                //if ( this.menageModel.getMenageId() != null ) {
                menageModel.setMenageId(this.iDKeys);
                //}
                MenageModel.queryRecordMngr = queryRecordMngr;
                String QSuivant_Val = MenageModel.Check_ContrainteSautChampsValeur(this.nomChamps, menageModel, this.iDKeys, dataBase);
                if (!QSuivant_Val.equalsIgnoreCase("")) {
                    this.qSuivant = QSuivant_Val;
                }
            } else if (Constant.FORMULAIRE_INDIVIDUS == this.tbl_TableName) {
                IndividuModel individuModel = (IndividuModel) this.data;
                IndividuModel.queryRecordMngr = queryRecordMngr;
                individuModel.setObjBatiment(this.batimentModel);
                individuModel.setObjLogement(this.logementModel);
                String QSuivant_Val = IndividuModel.Check_ContrainteSautChampsValeur(this.nomChamps, individuModel, this.iDKeys, dataBase, true);
                if (!QSuivant_Val.equalsIgnoreCase("")) {
                    this.qSuivant = QSuivant_Val;
                }
            }
            //ToastUtility.LogCat( "BOTTOM - Check_ContrainteSautChampsValeur");
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception- Check_ContrainteSautChampsValeur", ex);
            throw ex;
        }
    }//
//endregion

    //region "METHODE VALIDATION CHAMPS"
    public void Check_DataField_EditText(EditText EditTextRep) throws TextEmptyException {
        try {
            //ToastUtility.LogCat( "TOP:INSIDE - Check_DataField_CHIFFRE_LETTRE");
            String EditText_Reponse = EditTextRep.getText().toString();
            Pattern pattern;// = Pattern.compile("^[A-Za-z0-9]");
            Matcher matcher;// = pattern.matcher(EditText_Reponse);
            if (!EditText_Reponse.trim().equals("")) {
                if (contrainte.getCaractereAccepte() == Constant.CHIFFRE) {
                    pattern = Pattern.compile("^([0-9]+)$");
                    matcher = pattern.matcher(EditText_Reponse);
                    if (!matcher.matches()) {
                        EditTextRep.setError(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Chif_Selman));
                        EditTextRep.requestFocus();
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Chif_Selman));
                    }
                }

                if (contrainte.getCaractereAccepte() == Constant.CHIFFRE_LETTRE) {
                    pattern = Pattern.compile("^([A-Za-z0-9]*)$");
                    matcher = pattern.matcher(EditText_Reponse);
                    if (!matcher.matches()) {
                        EditTextRep.setError(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Let_Oubye_Chif_Selman));
                        EditTextRep.requestFocus();
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Let_Oubye_Chif_Selman));
                    }
                }

                if (contrainte.getCaractereAccepte() == Constant.LETTRE) {
                    pattern = Pattern.compile("^([A-Za-z\\s]*)$");
                    matcher = pattern.matcher(EditText_Reponse);
                    //Match match = Regex.Match(_Txt_Reponse.Text, @"^([A-Za-z\s]*)$", RegexOptions.IgnoreCase);
                    if (!matcher.matches()) {
                        EditTextRep.setError(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Let_Selman));
                        EditTextRep.requestFocus();
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Let_Selman));
                    }
                }
            }
            //ToastUtility.LogCat( "TOP:BOTTOM - Check_DataField_CHIFFRE_LETTRE");
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-Check_DataField_CHIFFRE_LETTRE(): getMessage: ", ex);
            throw ex;
        }
    }//

    public IndividuModel CheckIndividu_ValueBefore_AndSave(QueryRecordMngr queryRecordMngr, CURecordMngr cuRecordMngr
            , long ID_INDIVIDU, int nbrInd_NoOrdre
            , EditText et_02NonIndividu, EditText et_03SiyatiIndividu
            ,Spinner sp_04Sexe, Spinner sp_05HabiteDansMenage
            ,Spinner sp_06MembreMenageDepuisQuand, EditText et_06DateMembreMenageJour ,Spinner sp_06DateMembreMenageMois, EditText et_06DateMembreMenageAnnee
            ,EditText et_07DateNaissanceJour, Spinner sp_07DateNaissanceMois, EditText et_07DateNaissanceAnnee
            ,EditText et_08AgeIndividu ,Spinner sp_09LienDeParente
            ,Spinner sp_10Nationalite, Spinner sp_10PaysNationalite, Spinner sp_11NiveauEtude, Spinner sp_12StatutMatrimonial) throws TextEmptyException {
        try {
            String message = "";
            String ValReponse = "";
            QuestionReponseModel questionReponseModel = null;
            KeyValueModel keyValueModel = null;
            et_02NonIndividu.setError(null);
            String _02NonIndividu = et_02NonIndividu.getText().toString();

            et_03SiyatiIndividu.setError(null);
            String _03SiyatiIndividu = et_03SiyatiIndividu.getText().toString();

            if (TextUtils.isEmpty(_02NonIndividu)) {
                message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Non_Moun_Nan);
                et_02NonIndividu.setError(message);
                et_02NonIndividu.requestFocus();
                throw new TextEmptyException(message);
            }

            if (TextUtils.isEmpty(_03SiyatiIndividu)) {
                message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Siyati_Moun_Nan);
                et_03SiyatiIndividu.setError(message);
                et_03SiyatiIndividu.requestFocus();
                throw new TextEmptyException(message);
            }
            questionReponseModel = ((QuestionReponseModel) sp_04Sexe.getSelectedItem());
            String _04Sexe = questionReponseModel.getCodeReponse();
            if (TextUtils.isEmpty(_04Sexe)) {
                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Seks_Moun_nan));
            }
            questionReponseModel = ((QuestionReponseModel) sp_05HabiteDansMenage.getSelectedItem());
            String _05HabiteDansMenage = questionReponseModel.getCodeReponse();
            if (TextUtils.isEmpty(_05HabiteDansMenage)) {
                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Di_Si_MounNanMenajLa));
            }

            String _message = "";
            String _09LienDeParente = "0", _06MembreMenageDepuisQuand = "0";
            String jourMembreMenage = "0", jourNais = "0";
            String moisMembreMenage = "0", moisNais = "0";
            String anneeMembreMenage = "0", anneeNais = "0";
            String _08AgeIndividu = "000";
            String _10Nationalite = "0", _10PaysNationalite = "", _11NiveauEtude = "0", _12StatutMatrimonial = "0";
            Boolean isMounNanMenajLa = false;

            Calendar mydate = new GregorianCalendar();
            int anneeSysteme = mydate.get(Calendar.YEAR);
            int moisSysteme = (1 + mydate.get(Calendar.MONTH));
            int jourSysteme = mydate.get(Calendar.DAY_OF_MONTH);

            if (!questionReponseModel.getQSuivant().equalsIgnoreCase(Constant.FIN)) {
                isMounNanMenajLa = true;
            }

            if (nbrInd_NoOrdre <= 1 && !isMounNanMenajLa) { // si se premye moun nan...
                throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye. \n Ou dwe antre Chèf menaj la avan!");
            }
            if (isMounNanMenajLa) {
                //region [ _06MembreMenageDepuisQuand ]
                questionReponseModel = ((QuestionReponseModel) sp_06MembreMenageDepuisQuand.getSelectedItem());
                _06MembreMenageDepuisQuand = questionReponseModel.getCodeReponse();
                if (TextUtils.isEmpty(_06MembreMenageDepuisQuand)) {
                    message = context.getString(R.string.msg_Reponse_Ou_Dwe);
                    message += " " + context.getString(R.string.label_MembreMenageDepuisQuand);
                    throw new TextEmptyException(message);
                }
                //region [ TEST _06MembreMenageDepuisQuand ]
                //Si repons moun nan pa egal a 1 [ MembreMenageDepuisQuand != 1 ]
                if (!_06MembreMenageDepuisQuand.equalsIgnoreCase("" + Constant.R01_Avan_Resansman)) {
                    et_06DateMembreMenageJour.setError(null);
                    et_06DateMembreMenageAnnee.setError(null);
                    jourMembreMenage = et_06DateMembreMenageJour.getText().toString();
                    KeyValueModel keyValueMoisMembreMenage = ((KeyValueModel) sp_06DateMembreMenageMois.getSelectedItem());
                    moisMembreMenage = ((KeyValueModel) sp_06DateMembreMenageMois.getSelectedItem()).getKey();
                    anneeMembreMenage = et_06DateMembreMenageAnnee.getText().toString();

                    if (TextUtils.isEmpty(jourMembreMenage)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_Jou_Moun_nan_rive_nan_menaj_la);
                        et_06DateMembreMenageJour.setError(_message);
                        et_06DateMembreMenageJour.requestFocus();
                        throw new TextEmptyException(_message);

                    } else if (TextUtils.isEmpty(moisMembreMenage)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Mwa_Moun_nan_rive_nan_menaj_la));

                    } else if (TextUtils.isEmpty(anneeMembreMenage)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_Ane_Moun_nan_rive_nan_menaj_la);
                        et_06DateMembreMenageAnnee.setError(_message);
                        et_06DateMembreMenageAnnee.requestFocus();
                        throw new TextEmptyException(_message);
                    }

                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_06DateMembreMenageJour);
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_06DateMembreMenageAnnee);
                    Tools.Check_Constraint_NombreCaratereMinimal(Constant.CHIFFRE, 4, et_06DateMembreMenageAnnee);

                    int jourInt = Integer.parseInt(jourMembreMenage);
                    if (jourInt <= 0) { // Si moun nan konn jou a li tape 00
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_JouA);
                        et_06DateMembreMenageJour.setError(_message);
                        et_06DateMembreMenageJour.requestFocus();
                        et_06DateMembreMenageJour.selectAll();
                        throw new TextEmptyException(_message);
                    }

                    if (keyValueMoisMembreMenage != null) { // Verifye si jou a pa 99 sa vle di si moun nan konn jou a
                        if (!jourMembreMenage.equalsIgnoreCase("" + Constant.JOUR_PA_KONNEN_99ANS)) { // Si moun nan konn jou a
                            if (jourInt > Integer.parseInt(keyValueMoisMembreMenage.getOtherValue())) { // Verifye si jou a pi gran ke limit jou pou mwa a
                                _message = context.getString(R.string.msg_Reponse_Jou_ou_antre_a_Pa_bon);
                                et_06DateMembreMenageJour.setError(_message);
                                et_06DateMembreMenageJour.requestFocus();
                                et_06DateMembreMenageJour.selectAll();
                                //IsAgeIndividuVerify=true;
                                throw new TextEmptyException(_message);

                            } else if (jourInt == 29) { // Verifye si jou a pi gran ke limit jou pou mwa a
                                if (moisMembreMenage.equalsIgnoreCase("" + Constant.MOIS_FEVRIER_02)) { // Si mwa a se Fevriye=2
                                    if (!anneeMembreMenage.equalsIgnoreCase("" + Constant.ANNEE_PA_KONNEN_9999ANS)) { // Si moun nan konn ane , si li pa 9999
                                        if (!Tools.IsLeapYear(Integer.parseInt(anneeMembreMenage))) { // Verifye si se yon ane bisextil
                                            _message = context.getString(R.string.msg_Reponse_Jou_ou_antre_a_Pa_bon);
                                            et_06DateMembreMenageJour.setError(_message);
                                            et_06DateMembreMenageJour.requestFocus();
                                            et_06DateMembreMenageJour.selectAll();
                                            //IsAgeIndividuVerify=true;
                                            throw new TextEmptyException(_message);
                                        } else {
                                            if (anneeSysteme < Integer.parseInt(anneeMembreMenage)) {
                                                _message = "Atansyon! Atansyon!...\n Dat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                                        + "\nOu paka antre [ " + anneeMembreMenage + " ] pou ane moun nan te rive nan menaj la.";
                                                et_06DateMembreMenageAnnee.setError(_message);
                                                et_06DateMembreMenageAnnee.requestFocus();
                                                et_06DateMembreMenageAnnee.selectAll();
                                                //IsAgeIndividuVerify = true;
                                                throw new TextEmptyException(_message);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!anneeMembreMenage.equalsIgnoreCase("" + Constant.ANNEE_PA_KONNEN_9999ANS)) { // Si moun nan konn ane , si li pa 9999
                        if (anneeSysteme < Integer.parseInt(anneeMembreMenage)) {
                            _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                    + "\nOu paka antre [ " + anneeMembreMenage + " ] pou ane moun nan te rive nan menaj la.";
                            et_06DateMembreMenageAnnee.setError(_message);
                            et_06DateMembreMenageAnnee.requestFocus();
                            et_06DateMembreMenageAnnee.selectAll();
                            throw new TextEmptyException(_message);
                        }
                        if ( anneeSysteme == Integer.parseInt(anneeMembreMenage)) {
                            if ( !moisMembreMenage.equalsIgnoreCase("" + Constant.MOIS_PA_KONNEN_99ANS) ) { // Si moun nan konn ane , si li pa 9999
                                if ( moisSysteme < Integer.parseInt(moisMembreMenage)) {
                                    _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                            + "\nOu paka chwazi mwa sa pou mwa moun nan te rive nan menaj la.";
                                    sp_06DateMembreMenageMois.requestFocus();
                                    throw new TextEmptyException(_message);
                                }
                                if ( moisSysteme == Integer.parseInt(moisMembreMenage) ) {
                                    if ( !jourMembreMenage.equalsIgnoreCase("" + Constant.JOUR_PA_KONNEN_99ANS) ) { // Si moun nan konn jou a
                                        if ( jourSysteme < Integer.parseInt(jourMembreMenage)) {
                                            _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                                    + "\nOu paka antre jou sa pou jou moun nan te rive nan menaj la.";
                                            et_06DateMembreMenageJour.setError(_message);
                                            et_06DateMembreMenageJour.requestFocus();
                                            et_06DateMembreMenageJour.selectAll();
                                            throw new TextEmptyException(_message);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }//
                //endregion

                //endregion

                //region [ _07DateNaissance ]
                et_07DateNaissanceJour.setError(null);
                et_07DateNaissanceAnnee.setError(null);
                et_08AgeIndividu.setError(null);
                jourNais = et_07DateNaissanceJour.getText().toString();
                KeyValueModel keyValueMoisNais = ((KeyValueModel) sp_07DateNaissanceMois.getSelectedItem());
                moisNais = ((KeyValueModel) sp_07DateNaissanceMois.getSelectedItem()).getKey();
                anneeNais = et_07DateNaissanceAnnee.getText().toString();

                if (TextUtils.isEmpty(jourNais)) {
                    _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_JouFet_Moun_nan);
                    et_07DateNaissanceJour.setError(_message);
                    et_07DateNaissanceJour.requestFocus();
                    throw new TextEmptyException(_message);

                } else if (TextUtils.isEmpty(moisNais)) {
                    throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_MwaFet_Moun_nan));

                } else if (TextUtils.isEmpty(anneeNais)) {
                    _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_AneFet_Moun_nan);
                    et_07DateNaissanceAnnee.setError(_message);
                    et_07DateNaissanceAnnee.requestFocus();
                    throw new TextEmptyException(_message);
                }

                Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_07DateNaissanceJour);
                Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_07DateNaissanceAnnee);
                Tools.Check_Constraint_NombreCaratereMinimal(Constant.CHIFFRE, 4, et_07DateNaissanceAnnee);

                int jourInt = Integer.parseInt(jourNais);
                if (jourInt <= 0) { // Si moun nan konn jou a li tape 00
                    _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_JouA);
                    et_07DateNaissanceJour.setError(_message);
                    et_07DateNaissanceJour.requestFocus();
                    et_07DateNaissanceJour.selectAll();
                    throw new TextEmptyException(_message);
                }

                if (keyValueMoisNais != null) { // Verifye si jou a pa 99 sa vle di si moun nan konn jou a
                    if (!jourNais.equalsIgnoreCase("99")) { // Si moun nan konn jou a
                        if (jourInt > Integer.parseInt(keyValueMoisNais.getOtherValue())) { // Verifye si jou a pi gran ke limit jou pou mwa a
                            _message = context.getString(R.string.msg_Reponse_Jou_ou_antre_a_Pa_bon);
                            et_07DateNaissanceJour.setError(_message);
                            et_07DateNaissanceJour.requestFocus();
                            et_07DateNaissanceJour.selectAll();
                            IsAgeIndividuVerify = true;
                            throw new TextEmptyException(_message);

                        } else if (jourInt == 29) { // Verifye si jou a pi gran ke limit jou pou mwa a
                            if (moisNais.equalsIgnoreCase("" + Constant.MOIS_FEVRIER_02)) { // Si mwa a se Fevriye=2
                                if (!anneeNais.equalsIgnoreCase("" + Constant.ANNEE_PA_KONNEN_9999ANS)) { // Si moun nan konn ane , si li pa 9999
                                    if (!Tools.IsLeapYear(Integer.parseInt(anneeNais))) { // Verifye si se yon ane bisextil
                                        _message = context.getString(R.string.msg_Reponse_Jou_ou_antre_a_Pa_bon);
                                        et_07DateNaissanceJour.setError(_message);
                                        et_07DateNaissanceJour.requestFocus();
                                        et_07DateNaissanceJour.selectAll();
                                        IsAgeIndividuVerify = true;
                                        throw new TextEmptyException(_message);
                                    } else {
                                        if (anneeSysteme < Integer.parseInt(anneeNais)) {
                                            _message = "Atansyon! Atansyon!...\n Dat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                                    + "\nOu paka antre [ " + anneeNais + " ] pou ane moun nan fèt.";
                                            et_07DateNaissanceAnnee.setError(_message);
                                            et_07DateNaissanceAnnee.requestFocus();
                                            et_07DateNaissanceAnnee.selectAll();
                                            IsAgeIndividuVerify = true;
                                            throw new TextEmptyException(_message);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if ( !anneeNais.equalsIgnoreCase("" + Constant.ANNEE_PA_KONNEN_9999ANS) ) { // Si moun nan konn ane , si li pa 9999
                    if (anneeSysteme < Integer.parseInt(anneeNais)) {
                        _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                + "\nOu paka antre [ " + anneeNais + " ] pou ane  [ " + _02NonIndividu + " " + _03SiyatiIndividu.toUpperCase() + " ] fèt.";
                        et_07DateNaissanceAnnee.setError(_message);
                        et_07DateNaissanceAnnee.requestFocus();
                        et_07DateNaissanceAnnee.selectAll();
                        IsAgeIndividuVerify = true;
                        throw new TextEmptyException(_message);
                    }
                    if ( anneeSysteme == Integer.parseInt(anneeNais)) {
                        if ( !moisNais.equalsIgnoreCase("" + Constant.MOIS_PA_KONNEN_99ANS) ) { // Si moun nan konn ane , si li pa 9999
                            if ( moisSysteme < Integer.parseInt(moisNais)) {
                                _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                        + "\nOu paka chwazi mwa sa pou mwa moun nan fèt.";
                                sp_07DateNaissanceMois.requestFocus();
                                throw new TextEmptyException(_message);
                            }
                            if ( moisSysteme == Integer.parseInt(moisNais) ) {
                                if ( !jourNais.equalsIgnoreCase("" + Constant.JOUR_PA_KONNEN_99ANS) ) { // Si moun nan konn jou a
                                    if ( jourSysteme < Integer.parseInt(jourNais) ) {
                                        _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                                + "\nOu paka antre jou sa pou jou moun nan fèt.";
                                        et_07DateNaissanceJour.setError(_message);
                                        et_07DateNaissanceJour.requestFocus();
                                        et_07DateNaissanceJour.selectAll();
                                        throw new TextEmptyException(_message);
                                    }
                                }
                            }
                        }
                    }
                }
                //endregion

                //region [ _08AgeIndividu ]
                _08AgeIndividu = et_08AgeIndividu.getText().toString();
                if (TextUtils.isEmpty(_08AgeIndividu)) {
                    message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_Laj_Moun_nan);
                    et_08AgeIndividu.setError(message);
                    et_08AgeIndividu.requestFocus();
                    et_08AgeIndividu.selectAll();
                    IsAgeIndividuVerify = true;
                    throw new TextEmptyException(message);
                }

                Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_08AgeIndividu);

                if (!_08AgeIndividu.equalsIgnoreCase("999")) { // Si moun nan konn ane , si li pa 9999
                    if (Integer.parseInt(_08AgeIndividu) > Constant.AGE_120ANS) {
                        _message = "Atansyon verifye laj ou antre a. Limit laj la dwe " + Constant.AGE_120ANS + " ane ";
                        et_08AgeIndividu.setError(_message);
                        et_08AgeIndividu.requestFocus();
                        et_08AgeIndividu.selectAll();
                        IsAgeIndividuVerify = true;
                        throw new TextEmptyException(_message);
                    }

                    int ageDiff = (anneeSysteme - Integer.parseInt(anneeNais));
                    if (!anneeNais.equalsIgnoreCase("9999")) { // Si moun nan konn ane , si li pa 9999
                        if (IsAgeIndividuVerify) {
                            if (Integer.parseInt(_08AgeIndividu) != ageDiff) {
                                _message = "Atansyon verifye laj ou antre a ak dènye lè [" + _02NonIndividu + "] te fete anivèsè li.";
                                _message += "\n\nPaske pou systèm nan moun sa ta dwe genyen [ " + ageDiff + " ] ane si li fèt nan ane [ " + anneeNais + " ]";
                                _message += "\n\nTandiske w antre [ " + _08AgeIndividu + " ] ane pou moun sa a.";
                                _message += "\n\nSi w vle kite l konsa retouche bouton [Kontinye] a.";
                                et_08AgeIndividu.setError(_message);
                                et_08AgeIndividu.requestFocus();
                                et_08AgeIndividu.selectAll();
                                IsAgeIndividuVerify = false;
                                throw new TextEmptyException(_message);
                            }
                        }
                    }
                }
                //endregion

                //region [ _09LienDeParente ]
                questionReponseModel = ((QuestionReponseModel) sp_09LienDeParente.getSelectedItem());
                _09LienDeParente = questionReponseModel.getCodeReponse();
                if (TextUtils.isEmpty(_09LienDeParente)) {
                    throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Relasyon_Moun_nan));
                }

                if (nbrInd_NoOrdre <= 1 && Short.parseShort(_09LienDeParente) != Constant.Chef_menaj_la_01) { // si se premye moun nan...
                    throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye. \n Ou dwe antre Chèf menaj la avan!");
                }
                if (nbrInd_NoOrdre >= 2 && Short.parseShort(_09LienDeParente) == Constant.Chef_menaj_la_01) {
                    throw new TextEmptyException("Ou paka chwazi moun sa tou pou chèf menaj la. \n Ou te antre Chèf menaj la avan!");
                }
                //endregion

                //region [ _10Nationalite ]
                questionReponseModel = ((QuestionReponseModel) sp_10Nationalite.getSelectedItem());
                _10Nationalite = questionReponseModel.getCodeReponse();
                if (TextUtils.isEmpty(_10Nationalite)) {
                    throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Nasyonalite_Moun_nan));
                }

                if (_10Nationalite.equalsIgnoreCase("" + Constant.R02_Etranje)
                        || _10Nationalite.equalsIgnoreCase("" + Constant.R03_Ayisyen_ak_Etranje)) {
                    keyValueModel = ((KeyValueModel) sp_10PaysNationalite.getSelectedItem());
                    _10PaysNationalite = keyValueModel.getKey();
                    if (TextUtils.isEmpty(_10PaysNationalite)) {
                        message = context.getString(R.string.msg_Reponse_Ou_Dwe);
                        message += " di " + context.getString(R.string.label_PaysNationalite);
                        throw new TextEmptyException(message);
                    }
                }
                //endregion

                //region [ _11NiveauEtude ]
                int age = Integer.parseInt(_08AgeIndividu);
                if (age >= Constant.AGE_03ANS) {
                    questionReponseModel = ((QuestionReponseModel) sp_11NiveauEtude.getSelectedItem());
                    _11NiveauEtude = questionReponseModel.getCodeReponse();
                    if (TextUtils.isEmpty(_11NiveauEtude)) {
                        message = context.getString(R.string.msg_Reponse_Ou_Dwe);
                        message += " di " + context.getString(R.string.label_NiveauEtude);
                        throw new TextEmptyException(message);
                    }
                }
                //endregion

                //region [ _12StatutMatrimonial ]
                if (age >= Constant.AGE_10ANS) {
                    questionReponseModel = ((QuestionReponseModel) sp_12StatutMatrimonial.getSelectedItem());
                    _12StatutMatrimonial = questionReponseModel.getCodeReponse();
                    if (TextUtils.isEmpty(_12StatutMatrimonial)) {
                        message = context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi);
                        message += " " + context.getString(R.string.label_StatutMatrimonial);
                        throw new TextEmptyException(message);
                    }
                }
                //endregion
            }//

            // --- Contrainte lieu au document de specification --- //
            IndividuModel individuModel = new IndividuModel();
            individuModel.setQ1NoOrdre((short) nbrInd_NoOrdre);
            individuModel.setSdeId(this.menageModel.getSdeId());
            individuModel.setBatimentId(this.menageModel.getBatimentId());
            individuModel.setLogeId(this.menageModel.getLogeId());
            individuModel.setMenageId(this.menageModel.getMenageId());
            individuModel.setQp2APrenom(_02NonIndividu);
            individuModel.setQp2BNom(_03SiyatiIndividu);
            individuModel.setQp4Sexe(Short.valueOf(_04Sexe));
            individuModel.setQ5HabiteDansMenage(Short.valueOf(_05HabiteDansMenage));
            individuModel.setQ6aMembreMenageDepuisQuand(Short.valueOf(_06MembreMenageDepuisQuand));
            individuModel.setQ6bDateMembreMenageJour(Short.valueOf(jourMembreMenage));
            individuModel.setQ6bDateMembreMenageMois(Short.valueOf(moisMembreMenage));
            individuModel.setQ6bDateMembreMenageAnnee(Integer.valueOf(anneeMembreMenage));

            individuModel.setQ7DateNaissanceJour(Short.valueOf(jourNais));
            individuModel.setQ7DateNaissanceMois(Short.valueOf(moisNais));
            individuModel.setQ7DateNaissanceAnnee(Integer.valueOf(anneeNais));

            individuModel.setQ8Age(Short.valueOf(_08AgeIndividu));
            individuModel.setQ9LienDeParente(Short.valueOf(_09LienDeParente));
            individuModel.setQp10Nationalite(Short.valueOf(_10Nationalite));
            individuModel.setQp10PaysNationalite(_10PaysNationalite);
            individuModel.setQ11NiveauEtude(Short.valueOf(_11NiveauEtude));
            individuModel.setQ12StatutMatrimonial(Short.valueOf(_12StatutMatrimonial));

            individuModel.setDateDebutCollecte(this.dateDebutCollecte);
            individuModel.setIsFieldAllFilled(true);
            individuModel.setStatut((short) Constant.STATUT_MODULE_KI_FINI_1);

            //anc.setIsContreEnqueteMade(entity.getIsContreEnqueteMade());
            individuModel.setIsValidated(false);
            individuModel.setDateDebutCollecte(this.dateDebutCollecte);

            String setDateCollecte =  Tools.getDateString_MMddyyyy_HHmmss_a();
            individuModel.setDateFinCollecte(setDateCollecte);
            individuModel.setDureeSaisie( (int) getDureeSaisie("") );
            String codeAgentRecenceur = "";
            Shared_Preferences sharedPreferences = Tools.SharedPreferences(this.context);
            if ( sharedPreferences != null ){
                codeAgentRecenceur = sharedPreferences.getNomUtilisateur();
            }
            individuModel.setCodeAgentRecenceur( codeAgentRecenceur );
            individuModel.setIsVerified(false);

            IndividuModel.queryRecordMngr = queryRecordMngr;
            IndividuModel.Check_ContrainteSautChampsValeur(individuModel);
            SaveInfoIndividu(cuRecordMngr, ID_INDIVIDU, individuModel);

            IsAgeIndividuVerify = true;
            return individuModel;
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-CheckIndividu_ValueBefore(): getMessage: ", ex);
            throw ex;
        }
    }


    public AncienMembreModel CheckAncienMembre_ValueBefore_AndSave(QueryRecordMngr queryRecordMngr, CURecordMngr cuRecordMngr
            , long ID_INDIVIDU, int nbrInd_NoOrdre
            , EditText et_Qp2APrenom, EditText et_03SiyatiIndividu , Spinner sp_04Sexe
            , Spinner sp_Q5EstMortOuQuitter , Spinner sp_Q6HabiteDansMenage
            , EditText et_Q7DateQuitterMenageJour ,Spinner sp_Q7DateQuitterMenageMois, EditText et_Q7DateQuitterMenageAnnee
            , EditText et_Q7bDateMouriJour ,Spinner sp_Q7bDateMouriMois, EditText et_Q7bDateMouriAnnee
            ,EditText et_Q8DateNaissanceJour, Spinner sp_Q8DateNaissanceMois, EditText et_Q8DateNaissanceAnnee
            ,EditText et_Q9AgeAncienMembre ,Spinner sp_Q10LienDeParente
            ,Spinner sp_Q11Nationalite, Spinner sp_Q11PaysNationalite, Spinner sp_Q12NiveauEtude, Spinner sp_Q13StatutMatrimonial) throws TextEmptyException {
        try {
            String message = "";
            String ValReponse = "";
            QuestionReponseModel questionReponseModel = null;
            KeyValueModel keyValueModel = null;
            et_Qp2APrenom.setError(null);
            String _02NonIndividu = et_Qp2APrenom.getText().toString();

            et_03SiyatiIndividu.setError(null);
            String _03SiyatiIndividu = et_03SiyatiIndividu.getText().toString();

            if (TextUtils.isEmpty(_02NonIndividu)) {
                message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Non_Moun_Nan);
                et_Qp2APrenom.setError(message);
                et_Qp2APrenom.requestFocus();
                throw new TextEmptyException(message);
            }

            if (TextUtils.isEmpty(_03SiyatiIndividu)) {
                message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Siyati_Moun_Nan);
                et_03SiyatiIndividu.setError(message);
                et_03SiyatiIndividu.requestFocus();
                throw new TextEmptyException(message);
            }
            questionReponseModel = ((QuestionReponseModel) sp_04Sexe.getSelectedItem());
            String _04Sexe = questionReponseModel.getCodeReponse();
            if (TextUtils.isEmpty(_04Sexe)) {
                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Seks_Moun_nan));
            }

            //region [ 05 Est Mort Ou Quitter ]
            questionReponseModel = ((QuestionReponseModel) sp_Q5EstMortOuQuitter.getSelectedItem());
            String _Q5EstMortOuQuitter = questionReponseModel.getCodeReponse();
            if (TextUtils.isEmpty(_Q5EstMortOuQuitter)) {
                sp_Q5EstMortOuQuitter.requestFocus();
                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Di_Si_li_kite_menaj_la_oswa_si_li_mouri));
            }
            //endregion

            //region [ 06 Habite Dans Menage ]
            questionReponseModel = ((QuestionReponseModel) sp_Q6HabiteDansMenage.getSelectedItem());
            String _Q6HabiteDansMenage = questionReponseModel.getCodeReponse();
            if (TextUtils.isEmpty(_Q6HabiteDansMenage)) {
                sp_Q6HabiteDansMenage.requestFocus();
                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Di_Si_MounNanMenajLa));
            }

            String _message = "";
            String _10LienDeParente = "0";
            String jourQuitterMenage = "0",  jourMouri = "0",  jourNais = "0";
            String moisQuitterMenage = "0",  moisMouri = "0",  moisNais = "0";
            String anneeQuitterMenage = "0", anneeMouri = "0", anneeNais = "0";
            String _09AgeAncienMembre = "000";
            String _11Nationalite = "0", _11PaysNationalite = "", _12NiveauEtude = "0", _13StatutMatrimonial = "0";
            Boolean isMounNanMenajLa = false;

            Calendar mydate = new GregorianCalendar();
            int anneeSysteme = mydate.get(Calendar.YEAR);
            int moisSysteme = (1 + mydate.get(Calendar.MONTH));
            int jourSysteme = mydate.get(Calendar.DAY_OF_MONTH);

            if (!questionReponseModel.getQSuivant().equalsIgnoreCase(Constant.FIN)) {
                isMounNanMenajLa = true;
            }

            if (nbrInd_NoOrdre <= 1 && !isMounNanMenajLa) { // si se premye moun nan...
                throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye. \n Ou dwe antre Chèf menaj la avan!");
            }
            //endregion

            if (isMounNanMenajLa) {

                //region [ 07 Est Mort Ou Quitter ]
                et_Q7DateQuitterMenageJour.setError(null);
                et_Q7DateQuitterMenageAnnee.setError(null);
                jourQuitterMenage = et_Q7DateQuitterMenageJour.getText().toString();
                KeyValueModel keyValueMoisQuitterMenage = ((KeyValueModel) sp_Q7DateQuitterMenageMois.getSelectedItem());
                moisQuitterMenage = ((KeyValueModel) sp_Q7DateQuitterMenageMois.getSelectedItem()).getKey();
                anneeQuitterMenage = et_Q7DateQuitterMenageAnnee.getText().toString();

                //region [ 07 EstMortOuQuitter ]
                if( !_Q5EstMortOuQuitter.equalsIgnoreCase( ""+Constant.R03_Li_Mouri ) ){
                    //region [ Si MOUN NAN PA MOURI ]
                        if (TextUtils.isEmpty(jourQuitterMenage)) {
                            _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_Jou_Moun_nan_PatiKite_menaj_la);
                            et_Q7DateQuitterMenageJour.setError(_message);
                            et_Q7DateQuitterMenageJour.requestFocus();
                            throw new TextEmptyException(_message);

                        } else if (TextUtils.isEmpty(moisQuitterMenage)) {
                            throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Mwa_Moun_nan_PatiKite_menaj_la));

                        } else if (TextUtils.isEmpty(anneeQuitterMenage)) {
                            _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_Ane_Moun_nan_PatiKite_menaj_la);
                            et_Q7DateQuitterMenageAnnee.setError(_message);
                            et_Q7DateQuitterMenageAnnee.requestFocus();
                            throw new TextEmptyException(_message);
                        }

                        Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_Q7DateQuitterMenageJour);
                        Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_Q7DateQuitterMenageAnnee);
                        Tools.Check_Constraint_NombreCaratereMinimal(Constant.CHIFFRE, 4, et_Q7DateQuitterMenageAnnee);

                        int jourInt = Integer.parseInt(jourQuitterMenage);
                        if (jourInt <= 0) { // Si moun nan konn jou a li tape 00
                            _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_JouA);
                            et_Q7DateQuitterMenageJour.setError(_message);
                            et_Q7DateQuitterMenageJour.requestFocus();
                            et_Q7DateQuitterMenageJour.selectAll();
                            throw new TextEmptyException(_message);
                        }

                        if ( keyValueMoisQuitterMenage != null ) { // Verifye si jou a pa 99 sa vle di si moun nan konn jou a
                            if ( !jourQuitterMenage.equalsIgnoreCase("99") ) { // Si moun nan konn jou a
                                if ( jourInt > Integer.parseInt(keyValueMoisQuitterMenage.getOtherValue()) ) { // Verifye si jou a pi gran ke limit jou pou mwa a
                                    _message = context.getString(R.string.msg_Reponse_Jou_ou_antre_a_Pa_bon);
                                    et_Q7DateQuitterMenageJour.setError(_message);
                                    et_Q7DateQuitterMenageJour.requestFocus();
                                    et_Q7DateQuitterMenageJour.selectAll();
                                    //IsAgeIndividuVerify=true;
                                    throw new TextEmptyException(_message);

                                } else if (jourInt == 29) { // Verifye si jou a pi gran ke limit jou pou mwa a
                                    if (moisQuitterMenage.equalsIgnoreCase("" + Constant.MOIS_FEVRIER_02)) { // Si mwa a se Fevriye=2
                                        if (!anneeQuitterMenage.equalsIgnoreCase("" + Constant.ANNEE_PA_KONNEN_9999ANS)) { // Si moun nan konn ane , si li pa 9999
                                            if (!Tools.IsLeapYear(Integer.parseInt(anneeQuitterMenage))) { // Verifye si se yon ane bisextil
                                                _message = context.getString(R.string.msg_Reponse_Jou_ou_antre_a_Pa_bon);
                                                et_Q7DateQuitterMenageJour.setError(_message);
                                                et_Q7DateQuitterMenageJour.requestFocus();
                                                et_Q7DateQuitterMenageJour.selectAll();
                                                //IsAgeIndividuVerify=true;
                                                throw new TextEmptyException(_message);
                                            } else {
                                                if (anneeSysteme < Integer.parseInt(anneeQuitterMenage)) {
                                                    _message = "Atansyon! Atansyon!...\n Dat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                                            + "\nOu paka antre [ " + anneeQuitterMenage + " ] pou ane moun nan te pati kite menaj la.";
                                                    et_Q7DateQuitterMenageAnnee.setError(_message);
                                                    et_Q7DateQuitterMenageAnnee.requestFocus();
                                                    et_Q7DateQuitterMenageAnnee.selectAll();
                                                    //IsAgeIndividuVerify = true;
                                                    throw new TextEmptyException(_message);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!anneeQuitterMenage.equalsIgnoreCase("" + Constant.ANNEE_PA_KONNEN_9999ANS)) { // Si moun nan konn ane , si li pa 9999
                            if (anneeSysteme < Integer.parseInt(anneeQuitterMenage)) {
                                _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                        + "\nOu paka antre [ " + anneeQuitterMenage + " ] pou ane moun nan te pati kite menaj la.";
                                et_Q7DateQuitterMenageAnnee.setError(_message);
                                et_Q7DateQuitterMenageAnnee.requestFocus();
                                et_Q7DateQuitterMenageAnnee.selectAll();
                                //IsAgeIndividuVerify=true;
                                throw new TextEmptyException(_message);
                            }
                            if ( anneeSysteme == Integer.parseInt(anneeQuitterMenage)) {
                                if ( !moisQuitterMenage.equalsIgnoreCase("" + Constant.MOIS_PA_KONNEN_99ANS) ) { // Si moun nan konn ane , si li pa 9999
                                    if ( moisSysteme < Integer.parseInt(moisQuitterMenage)) {
                                        _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                                + "\nOu paka chwazi mwa sa pou mwa moun nan te pati kite menaj la.";
                                        sp_Q7DateQuitterMenageMois.requestFocus();
                                        throw new TextEmptyException(_message);
                                    }
                                    if ( moisSysteme == Integer.parseInt(moisQuitterMenage) ) {
                                        if ( !jourQuitterMenage.equalsIgnoreCase("" + Constant.JOUR_PA_KONNEN_99ANS) ) { // Si moun nan konn jou a
                                            if ( jourSysteme < Integer.parseInt(jourQuitterMenage)) {
                                                _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                                        + "\nOu paka antre jou sa pou jou moun nan te pati kite menaj la.";
                                                et_Q7DateQuitterMenageJour.setError(_message);
                                                et_Q7DateQuitterMenageJour.requestFocus();
                                                et_Q7DateQuitterMenageJour.selectAll();
                                                throw new TextEmptyException(_message);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    //endregion
                }
                if( _Q5EstMortOuQuitter.equalsIgnoreCase( ""+Constant.R03_Li_Mouri ) ){
                    //region [ SI MOUN NAN MOURI ]
                    et_Q7bDateMouriJour.setError(null);
                    et_Q7bDateMouriAnnee.setError(null);
                        jourMouri = et_Q7bDateMouriJour.getText().toString();
                        KeyValueModel keyValueMoisMouri = ((KeyValueModel) sp_Q7bDateMouriMois.getSelectedItem());
                        moisMouri = ((KeyValueModel) sp_Q7bDateMouriMois.getSelectedItem()).getKey();
                        anneeMouri = et_Q7bDateMouriAnnee.getText().toString();

                        if (TextUtils.isEmpty(jourMouri)) {
                            _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_Jou_Moun_nan_te_mouri);
                            et_Q7bDateMouriJour.setError(_message);
                            et_Q7bDateMouriJour.requestFocus();
                            throw new TextEmptyException(_message);

                        } else if (TextUtils.isEmpty(moisMouri)) {
                            sp_Q7bDateMouriMois.requestFocus();
                            throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Mwa_Moun_nan_te_mouri));

                        } else if (TextUtils.isEmpty(anneeMouri)) {
                            _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_Ane_Moun_nan_te_mouri);
                            et_Q7bDateMouriAnnee.setError(_message);
                            et_Q7bDateMouriAnnee.requestFocus();
                            throw new TextEmptyException(_message);
                        }

                        Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_Q7bDateMouriJour);
                        Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_Q7bDateMouriAnnee);
                        Tools.Check_Constraint_NombreCaratereMinimal(Constant.CHIFFRE, 4, et_Q7bDateMouriAnnee);

                        int jourInt = Integer.parseInt(jourMouri);
                        if (jourInt <= 0) { // Si moun nan konn jou a li tape 00
                            _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_JouA);
                            et_Q7bDateMouriJour.setError(_message);
                            et_Q7bDateMouriJour.requestFocus();
                            et_Q7bDateMouriJour.selectAll();
                            throw new TextEmptyException(_message);
                        }

                        if (keyValueMoisMouri != null) { // Verifye si jou a pa 99 sa vle di si moun nan konn jou a
                            if (!jourMouri.equalsIgnoreCase("99")) { // Si moun nan konn jou a
                                if (jourInt > Integer.parseInt(keyValueMoisMouri.getOtherValue())) { // Verifye si jou a pi gran ke limit jou pou mwa a
                                    _message = context.getString(R.string.msg_Reponse_Jou_ou_antre_a_Pa_bon);
                                    et_Q7bDateMouriJour.setError(_message);
                                    et_Q7bDateMouriJour.requestFocus();
                                    et_Q7bDateMouriJour.selectAll();
                                    throw new TextEmptyException(_message);

                                } else if (jourInt == 29) { // Verifye si jou a pi gran ke limit jou pou mwa a
                                    if (moisMouri.equalsIgnoreCase("" + Constant.MOIS_FEVRIER_02)) { // Si mwa a se Fevriye=2
                                        if (!anneeMouri.equalsIgnoreCase("" + Constant.ANNEE_PA_KONNEN_9999ANS)) { // Si moun nan konn ane , si li pa 9999
                                            if (!Tools.IsLeapYear(Integer.parseInt(anneeMouri))) { // Verifye si se yon ane bisextil
                                                _message = context.getString(R.string.msg_Reponse_Jou_ou_antre_a_Pa_bon);
                                                et_Q7bDateMouriJour.setError(_message);
                                                et_Q7bDateMouriJour.requestFocus();
                                                et_Q7bDateMouriJour.selectAll();
                                                throw new TextEmptyException(_message);
                                            } else {
                                                if (anneeSysteme < Integer.parseInt(anneeMouri)) {
                                                    _message = "Atansyon! Atansyon!...\n Dat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                                            + "\nOu paka antre [ " + anneeMouri + " ] pou ane moun nan te mouti.";
                                                    et_Q7bDateMouriAnnee.setError(_message);
                                                    et_Q7bDateMouriAnnee.requestFocus();
                                                    et_Q7bDateMouriAnnee.selectAll();
                                                    throw new TextEmptyException(_message);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!anneeMouri.equalsIgnoreCase("" + Constant.ANNEE_PA_KONNEN_9999ANS)) { // Si moun nan konn ane , si li pa 9999
                            if (anneeSysteme < Integer.parseInt(anneeMouri)) {
                                _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                        + "\nOu paka antre [ " + anneeMouri + " ] pou ane moun nan te mouri.";
                                et_Q7bDateMouriAnnee.setError(_message);
                                et_Q7bDateMouriAnnee.requestFocus();
                                et_Q7bDateMouriAnnee.selectAll();
                                throw new TextEmptyException(_message);
                            }
                            if ( anneeSysteme == Integer.parseInt(anneeMouri)) {
                                if ( !moisMouri.equalsIgnoreCase("" + Constant.MOIS_PA_KONNEN_99ANS) ) { // Si moun nan konn ane , si li pa 9999
                                    if ( moisSysteme < Integer.parseInt(moisMouri)) {
                                        _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                                + "\nOu paka chwazi mwa sa pou mwa moun nan te mouri.";
                                        sp_Q7bDateMouriMois.requestFocus();
                                        throw new TextEmptyException(_message);
                                    }
                                    if ( moisSysteme == Integer.parseInt(moisMouri) ) {
                                        if ( !jourMouri.equalsIgnoreCase("" + Constant.JOUR_PA_KONNEN_99ANS) ) { // Si moun nan konn jou a
                                            if ( jourSysteme < Integer.parseInt(jourMouri)) {
                                                _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                                        + "\nOu paka antre jou sa pou jou moun nan te mouri.";
                                                et_Q7bDateMouriJour.setError(_message);
                                                et_Q7bDateMouriJour.requestFocus();
                                                et_Q7bDateMouriJour.selectAll();
                                                throw new TextEmptyException(_message);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    //endregion
                }
                //endregion
                //endregion

                //region [ 08 Date Naissance ]
                et_Q8DateNaissanceJour.setError(null);
                et_Q8DateNaissanceAnnee.setError(null);
                et_Q9AgeAncienMembre.setError(null);
                jourNais = et_Q8DateNaissanceJour.getText().toString();
                KeyValueModel keyValueMoisNais = ((KeyValueModel) sp_Q8DateNaissanceMois.getSelectedItem());
                moisNais = ((KeyValueModel) sp_Q8DateNaissanceMois.getSelectedItem()).getKey();
                anneeNais = et_Q8DateNaissanceAnnee.getText().toString();

                if (TextUtils.isEmpty(jourNais)) {
                    _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_JouFet_Moun_nan);
                    et_Q8DateNaissanceJour.setError(_message);
                    et_Q8DateNaissanceJour.requestFocus();
                    throw new TextEmptyException(_message);

                } else if (TextUtils.isEmpty(moisNais)) {
                    throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_MwaFet_Moun_nan));

                } else if (TextUtils.isEmpty(anneeNais)) {
                    _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_AneFet_Moun_nan);
                    et_Q8DateNaissanceAnnee.setError(_message);
                    et_Q8DateNaissanceAnnee.requestFocus();
                    throw new TextEmptyException(_message);
                }

                Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_Q8DateNaissanceJour);
                Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_Q8DateNaissanceAnnee);
                Tools.Check_Constraint_NombreCaratereMinimal(Constant.CHIFFRE, 4, et_Q8DateNaissanceAnnee);

                int jourInt = Integer.parseInt(jourNais);
                if (jourInt <= 0) { // Si moun nan konn jou a li tape 00
                    _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_JouA);
                    et_Q8DateNaissanceJour.setError(_message);
                    et_Q8DateNaissanceJour.requestFocus();
                    et_Q8DateNaissanceJour.selectAll();
                    throw new TextEmptyException(_message);
                }

                if (keyValueMoisNais != null) { // Verifye si jou a pa 99 sa vle di si moun nan konn jou a
                    if (!jourNais.equalsIgnoreCase("99")) { // Si moun nan konn jou a
                        if (jourInt > Integer.parseInt(keyValueMoisNais.getOtherValue())) { // Verifye si jou a pi gran ke limit jou pou mwa a
                            _message = context.getString(R.string.msg_Reponse_Jou_ou_antre_a_Pa_bon);
                            et_Q8DateNaissanceJour.setError(_message);
                            et_Q8DateNaissanceJour.requestFocus();
                            et_Q8DateNaissanceJour.selectAll();
                            IsAgeIndividuVerify = true;
                            throw new TextEmptyException(_message);

                        } else if (jourInt == 29) { // Verifye si jou a pi gran ke limit jou pou mwa a
                            if (moisNais.equalsIgnoreCase("" + Constant.MOIS_FEVRIER_02)) { // Si mwa a se Fevriye=2
                                if (!anneeNais.equalsIgnoreCase("" + Constant.ANNEE_PA_KONNEN_9999ANS)) { // Si moun nan konn ane , si li pa 9999
                                    if (!Tools.IsLeapYear(Integer.parseInt(anneeNais))) { // Verifye si se yon ane bisextil
                                        _message = context.getString(R.string.msg_Reponse_Jou_ou_antre_a_Pa_bon);
                                        et_Q8DateNaissanceJour.setError(_message);
                                        et_Q8DateNaissanceJour.requestFocus();
                                        et_Q8DateNaissanceJour.selectAll();
                                        IsAgeIndividuVerify = true;
                                        throw new TextEmptyException(_message);
                                    } else {
                                        if (anneeSysteme < Integer.parseInt(anneeNais)) {
                                            _message = "Atansyon! Atansyon!...\n Dat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                                    + "\nOu paka antre [ " + anneeNais + " ] pou ane moun nan fèt.";
                                            et_Q8DateNaissanceAnnee.setError(_message);
                                            et_Q8DateNaissanceAnnee.requestFocus();
                                            et_Q8DateNaissanceAnnee.selectAll();
                                            IsAgeIndividuVerify = true;
                                            throw new TextEmptyException(_message);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (!anneeNais.equalsIgnoreCase("" + Constant.ANNEE_PA_KONNEN_9999ANS)) { // Si moun nan konn ane , si li pa 9999
                    if (anneeSysteme < Integer.parseInt(anneeNais)) {
                        _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                + "\nOu paka antre [ " + anneeNais + " ] pou ane  [ " + _02NonIndividu + " " + _03SiyatiIndividu.toUpperCase() + " ] fèt.";
                        et_Q8DateNaissanceAnnee.setError(_message);
                        et_Q8DateNaissanceAnnee.requestFocus();
                        et_Q8DateNaissanceAnnee.selectAll();
                        IsAgeIndividuVerify = true;
                        throw new TextEmptyException(_message);
                    }
                    if ( anneeSysteme == Integer.parseInt(anneeNais)) {
                        if ( !moisNais.equalsIgnoreCase("" + Constant.MOIS_PA_KONNEN_99ANS) ) { // Si moun nan konn ane , si li pa 9999
                            if ( moisSysteme < Integer.parseInt(moisNais)) {
                                _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                        + "\nOu paka chwazi mwa sa pou mwa moun nan te fèt.";
                                sp_Q8DateNaissanceMois.requestFocus();
                                throw new TextEmptyException(_message);
                            }
                            if ( moisSysteme == Integer.parseInt(moisNais) ) {
                                if ( !jourNais.equalsIgnoreCase("" + Constant.JOUR_PA_KONNEN_99ANS) ) { // Si moun nan konn jou a
                                    if ( jourSysteme < Integer.parseInt(jourNais)) {
                                        _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                                + "\nOu paka antre jou sa pou jou moun nan te fèt.";
                                        et_Q8DateNaissanceJour.setError(_message);
                                        et_Q8DateNaissanceJour.requestFocus();
                                        et_Q8DateNaissanceJour.selectAll();
                                        throw new TextEmptyException(_message);
                                    }
                                }
                            }
                        }
                    }
                }
                //endregion

                //region [ 09 Age Individu ]
                _09AgeAncienMembre = et_Q9AgeAncienMembre.getText().toString();
                if (TextUtils.isEmpty(_09AgeAncienMembre)) {
                    message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_Laj_Moun_nan);
                    et_Q9AgeAncienMembre.setError(message);
                    et_Q9AgeAncienMembre.requestFocus();
                    et_Q9AgeAncienMembre.selectAll();
                    IsAgeIndividuVerify = true;
                    throw new TextEmptyException(message);
                }

                Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_Q9AgeAncienMembre);

                if (!_09AgeAncienMembre.equalsIgnoreCase("999")) { // Si moun nan konn ane , si li pa 9999
                    if (Integer.parseInt(_09AgeAncienMembre) > Constant.AGE_120ANS) {
                        _message = "Atansyon verifye laj ou antre a. Limit laj la dwe " + Constant.AGE_120ANS + " ane ";
                        et_Q9AgeAncienMembre.setError(_message);
                        et_Q9AgeAncienMembre.requestFocus();
                        et_Q9AgeAncienMembre.selectAll();
                        IsAgeIndividuVerify = true;
                        throw new TextEmptyException(_message);
                    }

                    int ageDiff = (anneeSysteme - Integer.parseInt(anneeNais));
                    if (!anneeNais.equalsIgnoreCase("9999")) { // Si moun nan konn ane , si li pa 9999
                        if (IsAgeIndividuVerify) {
                            if (Integer.parseInt(_09AgeAncienMembre) != ageDiff) {
                                _message = "Atansyon verifye laj ou antre a ak dènye lè [" + _02NonIndividu + "] te fete anivèsè li.";
                                _message += "\n\nPaske pou systèm nan moun sa ta dwe genyen [ " + ageDiff + " ] ane si li fèt nan ane [ " + anneeNais + " ]";
                                _message += "\n\nTandiske w antre [ " + _09AgeAncienMembre + " ] ane pou moun sa a.";
                                _message += "\n\nSi w vle kite l konsa retouche bouton [Kontinye] a.";
                                et_Q9AgeAncienMembre.setError(_message);
                                et_Q9AgeAncienMembre.requestFocus();
                                et_Q9AgeAncienMembre.selectAll();
                                IsAgeIndividuVerify = false;
                                throw new TextEmptyException(_message);
                            }
                        }
                    }
                }
                //endregion

                //region [ 10 Lien De Parente ]
                questionReponseModel = ((QuestionReponseModel) sp_Q10LienDeParente.getSelectedItem());
                _10LienDeParente = questionReponseModel.getCodeReponse();
                if (TextUtils.isEmpty(_10LienDeParente)) {
                    throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Relasyon_Moun_nan));
                }

                if (nbrInd_NoOrdre <= 1 && Short.parseShort(_10LienDeParente) != Constant.Chef_menaj_la_01) { // si se premye moun nan...
                    throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye. \n Ou dwe antre Chèf menaj la avan!");
                }
                if (nbrInd_NoOrdre >= 2 && Short.parseShort(_10LienDeParente) == Constant.Chef_menaj_la_01) {
                    throw new TextEmptyException("Ou paka chwazi moun sa tou pou chèf menaj la. \n Ou te antre Chèf menaj la avan!");
                }
                //endregion

                //region [ 11 Nationalite ]
                questionReponseModel = ((QuestionReponseModel) sp_Q11Nationalite.getSelectedItem());
                _11Nationalite = questionReponseModel.getCodeReponse();
                if (TextUtils.isEmpty(_11Nationalite)) {
                    sp_Q11Nationalite.requestFocus();
                    throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Nasyonalite_Moun_nan));
                }

                if (_11Nationalite.equalsIgnoreCase("" + Constant.R02_Etranje)
                        || _11Nationalite.equalsIgnoreCase("" + Constant.R03_Ayisyen_ak_Etranje)) {
                    keyValueModel = ((KeyValueModel) sp_Q11PaysNationalite.getSelectedItem());
                    _11PaysNationalite = keyValueModel.getKey();
                    if (TextUtils.isEmpty(_11PaysNationalite)) {
                        message = context.getString(R.string.msg_Reponse_Ou_Dwe);
                        message += " di " + context.getString(R.string.label_PaysNationalite);
                        sp_Q11PaysNationalite.requestFocus();
                        throw new TextEmptyException(message);
                    }
                }
                //endregion

                //region [ 12 NiveauEtude ]
                int age = Integer.parseInt(_09AgeAncienMembre);
                if (age >= Constant.AGE_03ANS) {
                    questionReponseModel = ((QuestionReponseModel) sp_Q12NiveauEtude.getSelectedItem());
                    _12NiveauEtude = questionReponseModel.getCodeReponse();
                    if (TextUtils.isEmpty(_12NiveauEtude)) {
                        message = context.getString(R.string.msg_Reponse_Ou_Dwe);
                        message += " di " + context.getString(R.string.label_NiveauEtude);
                        sp_Q12NiveauEtude.requestFocus();
                        throw new TextEmptyException(message);
                    }
                }
                //endregion

                //region [ 13 StatutMatrimonial ]
                if (age >= Constant.AGE_10ANS) {
                    questionReponseModel = ((QuestionReponseModel) sp_Q13StatutMatrimonial.getSelectedItem());
                    _13StatutMatrimonial = questionReponseModel.getCodeReponse();
                    if (TextUtils.isEmpty(_13StatutMatrimonial)) {
                        message = context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi);
                        message += " " + context.getString(R.string.label_StatutMatrimonial);
                        sp_Q13StatutMatrimonial.requestFocus();
                        throw new TextEmptyException(message);
                    }
                }
                //endregion
            }//

            // --- Contrainte lieu au document de specification --- //
            AncienMembreModel ancienMembreM = new AncienMembreModel();
            //ancienMembreM.setAncienMembreId(entity.getAncienMembreId());

            ancienMembreM.setQ1NoOrdre((short) nbrInd_NoOrdre);
            ancienMembreM.setSdeId(this.menageModel.getSdeId());
            ancienMembreM.setBatimentId(this.menageModel.getBatimentId());
            ancienMembreM.setLogeId(this.menageModel.getLogeId());
            ancienMembreM.setMenageId(this.menageModel.getMenageId());
            ancienMembreM.setQp2APrenom(_02NonIndividu);
            ancienMembreM.setQp2BNom(_03SiyatiIndividu);
            ancienMembreM.setQp4Sexe(Short.valueOf(_04Sexe));

            ancienMembreM.setQ5EstMortOuQuitter( Short.valueOf(_Q5EstMortOuQuitter) );
            ancienMembreM.setQ6HabiteDansMenage( Short.valueOf(_Q6HabiteDansMenage) );

            ancienMembreM.setQ7DateQuitterMenageJour( Short.valueOf(jourQuitterMenage) );
            ancienMembreM.setQ7DateQuitterMenageMois( Short.valueOf(moisQuitterMenage) );
            ancienMembreM.setQ7DateQuitterMenageAnnee( Integer.valueOf(anneeQuitterMenage) );

            ancienMembreM.setQ7bDateMouriJour( Short.valueOf(jourMouri) );
            ancienMembreM.setQ7bDateMouriMois( Short.valueOf(moisMouri) );
            ancienMembreM.setQ7bDateMouriAnnee( Integer.valueOf(anneeMouri) );

            ancienMembreM.setQ8DateNaissanceJour( Short.valueOf(jourNais) );
            ancienMembreM.setQ8DateNaissanceMois( Short.valueOf(moisNais) );
            ancienMembreM.setQ8DateNaissanceAnnee( Integer.valueOf(anneeNais) );

            ancienMembreM.setQ9Age( Integer.valueOf(_09AgeAncienMembre) );
            ancienMembreM.setQ10LienDeParente( Short.valueOf(_10LienDeParente) );
            ancienMembreM.setQ11Nationalite( Short.valueOf(_11Nationalite) );
            ancienMembreM.setQ11PaysNationalite( _11PaysNationalite );
            ancienMembreM.setQ12NiveauEtude( Short.valueOf(_12NiveauEtude) );
            ancienMembreM.setQ12StatutMatrimonial( Short.valueOf(_13StatutMatrimonial) );

            ancienMembreM.setIsFieldAllFilled(true);
            ancienMembreM.setStatut((short) Constant.STATUT_MODULE_KI_FINI_1);

            //ancienMembreM.setIsContreEnqueteMade(entity.getIsContreEnqueteMade());
            ancienMembreM.setIsValidated(false);
            ancienMembreM.setDateDebutCollecte(this.dateDebutCollecte);

            String setDateCollecte =  Tools.getDateString_MMddyyyy_HHmmss_a();
            ancienMembreM.setDateFinCollecte(setDateCollecte);
            ancienMembreM.setDureeSaisie( (int) getDureeSaisie("") );
            String codeAgentRecenceur = "";
            Shared_Preferences sharedPreferences = Tools.SharedPreferences(this.context);
            if ( sharedPreferences != null ){
                codeAgentRecenceur = sharedPreferences.getNomUtilisateur();
            }
            ancienMembreM.setCodeAgentRecenceur( codeAgentRecenceur );
            ancienMembreM.setIsVerified(false);

            ancienMembreM.queryRecordMngr = queryRecordMngr;
            ancienMembreM.Check_ContrainteSautChampsValeur(ancienMembreM);
            SaveInfoAncienMembre(cuRecordMngr, ID_INDIVIDU, ancienMembreM);

            IsAgeIndividuVerify = true;
            return ancienMembreM;
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-CheckIndividu_ValueBefore(): getMessage: ", ex);
            throw ex;
        }
    }

    public void CheckAgeDialog(Spinner sp_JourIndividu, Spinner sp_MoisIndividu, Spinner sp_AnneeIndividu, Spinner sp_AgeIndividu) {
        try {
            KeyValueModel keyJour = null;
            keyJour = ((KeyValueModel) sp_JourIndividu.getSelectedItem());

            KeyValueModel keyMois = null;
            keyMois = ((KeyValueModel) sp_MoisIndividu.getSelectedItem());
            KeyValueModel keyAnnee = null;
            keyAnnee = ((KeyValueModel) sp_AnneeIndividu.getSelectedItem());

            if (keyAnnee != null) {
                if (!keyAnnee.getKey().equalsIgnoreCase("")) {
                    if (keyAnnee.getKey().trim().equalsIgnoreCase("9999")) {
                        sp_AgeIndividu.setSelection(0);
                    } else {
                        //GRAFRITZ
                        Calendar mydate = new GregorianCalendar();
                        // JOUR
                        int valJour = 0;
                        int valJourSystem = 0;
                        if (keyJour != null) {
                            if (!keyJour.getKey().equalsIgnoreCase("")) {
                                if (!keyJour.getKey().equalsIgnoreCase("99")) {
                                    valJour = Integer.parseInt(keyJour.getKey());
                                    valJourSystem = mydate.get(Calendar.DAY_OF_MONTH);
                                }
                            }
                        }
                        // MOIS
                        int valMois = 0;
                        int valMoisSystem = 0;
                        if (keyMois != null) {
                            if (!keyMois.getKey().trim().equalsIgnoreCase("")) {
                                if (!keyMois.getKey().trim().equalsIgnoreCase("99")) {
                                    valMois = Integer.parseInt(keyMois.getKey());
                                    valMoisSystem = mydate.get(Calendar.MONTH) + 1;
                                }
                            }
                        }
                        int valAnnee = (keyAnnee.getKey().equalsIgnoreCase("") ? 0 : Integer.parseInt(keyAnnee.getKey()));
                        int valAnneeSystem = mydate.get(Calendar.YEAR);
                        // ANNEE
                        int ageSaisie = Integer.parseInt(valAnnee + "" + valMois + "" + valJour);
                        int ageSysteme = Integer.parseInt(valAnneeSystem + "" + valMoisSystem + "" + valJourSystem);
                        int calculAgeInfo = (valAnneeSystem - valAnnee);
                        int calculinfo = (ageSysteme - ageSaisie);
                        ToastUtility.LogCat("calculinfo :" + calculinfo + " / ageSaisie :" + ageSaisie
                                + " / ageSysteme :" + ageSysteme + " / calculAgeInfo :" + calculAgeInfo + " / calculinfo :" + calculinfo);
                        if (calculinfo < 0) {
                            sp_AgeIndividu.setSelection(1);// Moun sa gen pipiti ke yon lane
                        } else {
                            this.setReponse(sp_AgeIndividu, "" + calculAgeInfo, Constant.CLASSE_KEY_VALUE_MODEL);
                        }
                        //QF.getDateInfo(this);
                    }
                }
            }
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception: CheckAgeDialog() :", ex);
            ex.printStackTrace();
        }
    }

    public void CheckValueBefore(QueryRecordMngr queryRecordMngr, CURecordMngr cuRecordMngr, int typeDeSauvegarde, EditText et_Reponse
            , Spinner sp_Reponse, Spinner sp_Reponse2, Spinner sp_Reponse3, Spinner sp_Jour, Spinner sp_Mois, Spinner sp_Annee
            , EditText et_Gason, EditText et_Fi , EditText et_ApareyRadyo, EditText et_Televizyon, EditText et_FrijideFrize
            , EditText et_FouElektrikFouAkGaz, EditText et_OdinatePCLaptopTabletNimerik, EditText et_BisikletMotosiklet, EditText et_VwatiMachin
            , EditText et_KannotBato, EditText et_InvetePanoSoleJeneratrisDelko, EditText et_MiletChwalBourik, EditText et_BefVach, EditText et_KochonKabrit, EditText et_BetVolayPoulKok
            , RecyclerView recyclerViewReponse, QuestionReponseModel codeReponseRecyclerView, KeyValueModel codeReponseKeyValueModel
            , EditText et_Jour, Spinner sp_Mois2, EditText et_Annee
    ) throws TextEmptyException {
        try {
            //ToastUtility.LogCat( "TOP:INSIDE - CheckValueBefore");
            String _message = "";
            String ValReponse1 = "", ValReponse2 = "";
            QuestionReponseModel reponseModel = null, reponseModel2 = null;
            KeyValueModel keyValueModel = null;
            switch ( this.typeQuestion ) {
                //region TYPE_QUESTION_CHOIX_1
                case Constant.TYPE_QUESTION_CHOIX_1:
                    reponseModel = ((QuestionReponseModel) sp_Reponse.getSelectedItem());
                    ValReponse1 = reponseModel.getCodeReponse();
                    //ValReponse1 = codeReponseRecyclerView.getCodeReponse();
                    if (TextUtils.isEmpty(ValReponse1)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons));
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_DEUX_CHOIX_4
                case Constant.TYPE_QUESTION_DEUX_CHOIX_4:
                    reponseModel = ((QuestionReponseModel) sp_Reponse.getSelectedItem());
                    ValReponse1 = reponseModel.getCodeReponse();
                    if (TextUtils.isEmpty(ValReponse1)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons));
                    }
                    // TEST POUR 2ème LISTE DE CHOIX
                    if (sp_Reponse2.getVisibility() == View.VISIBLE) {
                        reponseModel = ((QuestionReponseModel) sp_Reponse2.getSelectedItem());
                        ValReponse1 = reponseModel.getCodeReponse();
                        if (TextUtils.isEmpty(ValReponse1)) {
                            throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_lot_Repons));
                        }
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_PAYS_5
                case Constant.TYPE_QUESTION_CHOIX_PAYS_5:
                    keyValueModel = ((KeyValueModel) sp_Reponse.getSelectedItem());
                    ValReponse1 = keyValueModel.getKey();
                    if (TextUtils.isEmpty(ValReponse1)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Peyi));
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMMUNE_6
                case Constant.TYPE_QUESTION_CHOIX_COMMUNE_6:
                    keyValueModel = ((KeyValueModel) sp_Reponse.getSelectedItem());
                    ValReponse1 = keyValueModel.getKey();
                    if (TextUtils.isEmpty(ValReponse1)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Depatman));
                    }
                    // TEST POUR 2ème LISTE DE CHOIX
                    if (sp_Reponse2.getVisibility() == View.VISIBLE) {
                        CommuneModel communeModel = ((CommuneModel) sp_Reponse2.getSelectedItem());
                        ValReponse1 = communeModel.getComId();
                        if (TextUtils.isEmpty(ValReponse1)) {
                            throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Komin));
                        }
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7
                case Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7:
                    keyValueModel = ((KeyValueModel) sp_Reponse.getSelectedItem());
                    ValReponse1 = keyValueModel.getKey();
                    if (TextUtils.isEmpty(ValReponse1)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Depatman));
                    }
                    // TEST POUR 2ème LISTE DE CHOIX
                    if (sp_Reponse2.getVisibility() == View.VISIBLE) {
                        CommuneModel communeModel = ((CommuneModel) sp_Reponse2.getSelectedItem());
                        ValReponse1 = communeModel.getComId();
                        if (TextUtils.isEmpty(ValReponse1)) {
                            throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Komin));
                        }
                    }
                    // TEST POUR 3ème LISTE DE CHOIX
                    if (sp_Reponse3.getVisibility() == View.VISIBLE) {
                        VqseModel vqseModel = ((VqseModel) sp_Reponse3.getSelectedItem());
                        ValReponse1 = vqseModel.getVqseId();
                        if (TextUtils.isEmpty(ValReponse1)) {
                            throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_SeksyonKominal));
                        }
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_DOMAINE_ETUDE_8
                case Constant.TYPE_QUESTION_CHOIX_DOMAINE_ETUDE_8:
                    keyValueModel = ((KeyValueModel) sp_Reponse.getSelectedItem());
                    ValReponse1 = keyValueModel.getKey();
                    if (TextUtils.isEmpty(ValReponse1)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_DomènEtid));
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_INDIVIDU_9
                case Constant.TYPE_QUESTION_CHOIX_INDIVIDU_9:
                    break;
                //endregion
                //region TYPE_QUESTION_SAISIE_2
                case Constant.TYPE_QUESTION_SAISIE_2:
                    et_Reponse.setError(null);
                    int etReponse_Length = et_Reponse.getText().toString().length();
                    String etReponse_Text = et_Reponse.getText().toString();

                    if (TextUtils.isEmpty(etReponse_Text)) {
                        et_Reponse.setError(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Yon_Repons));
                        et_Reponse.requestFocus();
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Yon_Repons));
                    }
                    if (contrainte.getNbreValeurMinimal() > -1 && Integer.parseInt(etReponse_Text) < contrainte.getNbreValeurMinimal()) {
                        et_Reponse.setError(contrainte.MessageNombreValeurMinimal());
                        et_Reponse.requestFocus();
                        throw new TextEmptyException(contrainte.MessageNombreValeurMinimal());
                    }
                    ValReponse1 = etReponse_Text;
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_MM_AAAA_3
                case Constant.TYPE_QUESTION_DATE_MM_AAAA_3:
                    String mois = ((KeyValueModel) sp_Mois.getSelectedItem()).getKey();
                    String annee = ((KeyValueModel) sp_Annee.getSelectedItem()).getKey();
                    if (TextUtils.isEmpty(mois)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Mwa));
                    } else if (TextUtils.isEmpty(annee)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Ane));
                    }
                    ValReponse1 = getReponseDate("1", mois, annee).toString();
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_JJ_MM_AAAA_11
                case Constant.TYPE_QUESTION_DATE_JJ_MM_AAAA_11:
                    String jour = ((KeyValueModel) sp_Jour.getSelectedItem()).getKey();
                    mois = ((KeyValueModel) sp_Mois.getSelectedItem()).getKey();
                    annee = ((KeyValueModel) sp_Annee.getSelectedItem()).getKey();
                    if (TextUtils.isEmpty(jour)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Jou));
                    } else if (TextUtils.isEmpty(mois)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Mwa));
                    } else if (TextUtils.isEmpty(annee)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Ane));
                    }

                    ValReponse1 = getReponseDate(jour, mois, annee).toString();
                    //ToastUtility.LogCat( "CheckValueBefore() : TYPE_QUESTION_DATE_MM_AAAA_3 >> - ValReponse1: " + ValReponse1);
                    break;
                //endregion
                //region TYPE_QUESTION_NBR_APPAREIL_ET_ANIMAUX_12
                case Constant.TYPE_QUESTION_NBR_APPAREIL_ET_ANIMAUX_12:
                    et_Gason.setError(null);
                    et_Fi.setError(null);
                    String qm9NbreRadio1 = et_ApareyRadyo.getText().toString();
                    String qm9NbreTelevision2 = et_Televizyon.getText().toString();
                    String qm9NbreRefrigerateur3 = et_FrijideFrize.getText().toString();
                    String qm9NbreFouElectrique4 = et_FouElektrikFouAkGaz.getText().toString();
                    String qm9NbreOrdinateur5 = et_OdinatePCLaptopTabletNimerik.getText().toString();
                    String qm9NbreMotoBicyclette6 = et_BisikletMotosiklet.getText().toString();
                    String qm9NbreVoitureMachine7 = et_VwatiMachin.getText().toString();
                    String qm9NbreBateau8 = et_KannotBato.getText().toString();
                    String qm9NbrePanneauGeneratrice9 = et_InvetePanoSoleJeneratrisDelko.getText().toString();
                    String qm9NbreMilletChevalBourique10 = et_MiletChwalBourik.getText().toString();
                    String qm9NbreBoeufVache11 = et_BefVach.getText().toString();
                    String qm9NbreCochonCabrit12 = et_KochonKabrit.getText().toString();
                    String qm9NbreBeteVolaille13 = et_BetVolayPoulKok.getText().toString();
                    //region TYPE_QUESTION_NBR_APPAREIL_ET_ANIMAUX_12
                    if (TextUtils.isEmpty(qm9NbreRadio1)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Kantite) + " " + context.getString(R.string.label_ApareyRadyo);
                        et_ApareyRadyo.setError(_message);
                        et_ApareyRadyo.requestFocus();
                        throw new TextEmptyException(_message);
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_ApareyRadyo);

                    if (TextUtils.isEmpty(qm9NbreTelevision2)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Kantite) + " " + context.getString(R.string.label_Televizyon);
                        et_Televizyon.setError(_message);
                        et_Televizyon.requestFocus();
                        throw new TextEmptyException(_message);
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_Televizyon);

                    if (TextUtils.isEmpty(qm9NbreRefrigerateur3)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Kantite) + " " + context.getString(R.string.label_FrijideFrize);
                        et_FrijideFrize.setError(_message);
                        et_FrijideFrize.requestFocus();
                        throw new TextEmptyException(_message);
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_FrijideFrize);

                    if (TextUtils.isEmpty(qm9NbreFouElectrique4)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Kantite) + " " + context.getString(R.string.label_FouElektrikFouAkGaz);
                        et_FouElektrikFouAkGaz.setError(_message);
                        et_FouElektrikFouAkGaz.requestFocus();
                        throw new TextEmptyException(_message);
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_FouElektrikFouAkGaz);

                    if (TextUtils.isEmpty(qm9NbreOrdinateur5)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Kantite) + " " + context.getString(R.string.label_OdinatePCLaptopTabletNimerik);
                        et_OdinatePCLaptopTabletNimerik.setError(_message);
                        et_OdinatePCLaptopTabletNimerik.requestFocus();
                        throw new TextEmptyException(_message);
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_OdinatePCLaptopTabletNimerik);

                    if (TextUtils.isEmpty(qm9NbreMotoBicyclette6)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Kantite) + " " + context.getString(R.string.label_BisikletMotosiklet);
                        et_BisikletMotosiklet.setError(_message);
                        et_BisikletMotosiklet.requestFocus();
                        throw new TextEmptyException(_message);
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_BisikletMotosiklet);

                    if (TextUtils.isEmpty(qm9NbreVoitureMachine7)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Kantite) + " " + context.getString(R.string.label_VwatiMachin);
                        et_VwatiMachin.setError(_message);
                        et_VwatiMachin.requestFocus();
                        throw new TextEmptyException(_message);
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_VwatiMachin);

                    if (TextUtils.isEmpty(qm9NbreBateau8)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Kantite) + " " + context.getString(R.string.label_KannotBato);
                        et_KannotBato.setError(_message);
                        et_KannotBato.requestFocus();
                        throw new TextEmptyException(_message);
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_KannotBato);

                    if (TextUtils.isEmpty(qm9NbrePanneauGeneratrice9)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Kantite) + " " + context.getString(R.string.label_InvetePanoSoleJeneratrisDelko);
                        et_InvetePanoSoleJeneratrisDelko.setError(_message);
                        et_InvetePanoSoleJeneratrisDelko.requestFocus();
                        throw new TextEmptyException(_message);
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_InvetePanoSoleJeneratrisDelko);

                    if (TextUtils.isEmpty(qm9NbreMilletChevalBourique10)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Kantite) + " " + context.getString(R.string.label_MiletChwalBourik);
                        et_MiletChwalBourik.setError(_message);
                        et_MiletChwalBourik.requestFocus();
                        throw new TextEmptyException(_message);
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_MiletChwalBourik);

                    if (TextUtils.isEmpty(qm9NbreBoeufVache11)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Kantite) + " " + context.getString(R.string.label_BefVach);
                        et_BefVach.setError(_message);
                        et_BefVach.requestFocus();
                        throw new TextEmptyException(_message);
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_BefVach);

                    if (TextUtils.isEmpty(qm9NbreCochonCabrit12)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Kantite) + " " + context.getString(R.string.label_KochonKabrit);
                        et_KochonKabrit.setError(_message);
                        et_KochonKabrit.requestFocus();
                        throw new TextEmptyException(_message);
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_KochonKabrit);

                    if (TextUtils.isEmpty(qm9NbreBeteVolaille13)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Kantite) + " " + context.getString(R.string.label_BetVolayPoulKok);
                        et_BetVolayPoulKok.setError(_message);
                        et_BetVolayPoulKok.requestFocus();
                        throw new TextEmptyException(_message);
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_BetVolayPoulKok);

                    //endregion
                    ValReponse1 = qm9NbreRadio1 + "-" + qm9NbreTelevision2
                            + "-" + qm9NbreRefrigerateur3 + "-" + qm9NbreFouElectrique4
                            + "-" + qm9NbreOrdinateur5 + "-" + qm9NbreMotoBicyclette6
                            + "-" + qm9NbreVoitureMachine7 + "-" + qm9NbreBateau8
                            + "-" + qm9NbrePanneauGeneratrice9 + "-" + qm9NbreMilletChevalBourique10
                            + "-" + qm9NbreBoeufVache11 + "-" + qm9NbreCochonCabrit12
                            + "-" + qm9NbreBeteVolaille13;
                    break;
                //endregion
                //region TYPE_QUESTION_NBR_GARCON_ET_FILLE_13
                case Constant.TYPE_QUESTION_NBR_GARCON_ET_FILLE_13:
                    et_Gason.setError(null);
                    et_Fi.setError(null);
                    //int et_Gason_Length = et_Gason.getText().toString().length();
                    String et_Gason_Text = et_Gason.getText().toString();
                    String et_Fi_Text = et_Fi.getText().toString();

                    if (this.tbl_TableName == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL) {
                        if (this.nomChamps.equalsIgnoreCase(Constant.Qlin6NombrePieceETChambreACoucher)) {
                            if (TextUtils.isEmpty(et_Gason_Text)) {
                                _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Di) + " " + context.getString(R.string.label_KantitePyesAntou);
                                et_Gason.setError(_message);
                                et_Gason.requestFocus();
                                throw new TextEmptyException(_message);
                            }
                            if (TextUtils.isEmpty(et_Fi_Text)) {
                                _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Di) + " " + context.getString(R.string.label_KantiteChanmPouDomi);
                                et_Fi.setError(_message);
                                et_Fi.requestFocus();
                                throw new TextEmptyException(_message);
                            }
                        }
                    } else {

                        if (TextUtils.isEmpty(et_Gason_Text)) {
                            _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Di) + " " + context.getString(R.string.label_Konbyen) + " " + context.getString(R.string.label_Gason);
                            et_Gason.setError(_message);
                            et_Gason.requestFocus();
                            throw new TextEmptyException(_message);
                        }
                        if (TextUtils.isEmpty(et_Fi_Text)) {
                            _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Di) + " " + context.getString(R.string.label_Konbyen) + " " + context.getString(R.string.label_Fi);
                            et_Fi.setError(_message);
                            et_Fi.requestFocus();
                            throw new TextEmptyException(_message);
                        }
                    }
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_Gason);
                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_Fi);

                    ValReponse1 = et_Gason_Text + "-" + et_Fi_Text;
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_14
                case Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_14:
                    reponseModel = ((QuestionReponseModel) sp_Reponse.getSelectedItem());
                    reponseModel2 = ((QuestionReponseModel) sp_Reponse2.getSelectedItem());
                    String reponseVal1 = reponseModel.getCodeReponse();
                    String reponseVal2 = reponseModel2.getCodeReponse();

                    if (this.tbl_TableName == Constant.FORMULAIRE_MENAGE) {
                        if (this.nomChamps.equalsIgnoreCase(Constant.Qm4ModeAprobEauBoireEtUsageCourant)) {
                            if (TextUtils.isEmpty(reponseVal1)) {
                                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons) + " \n Pou "
                                        + context.getString(R.string.label_M4_1DloPouBwe));

                            } else if (TextUtils.isEmpty(reponseVal2)) {
                                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons) + " \n Pou "
                                        + context.getString(R.string.label_M4_2DloPouFeLotIzaj));
                            }
                        }

                        if (this.nomChamps.equalsIgnoreCase(Constant.Qm5SrcEnergieCuison1Et2)) {
                            if (TextUtils.isEmpty(reponseVal1)) {
                                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons) + " \n Pou "
                                        + context.getString(R.string.label_1erSousEnejiPouKwitManje));
                            } else if (TextUtils.isEmpty(reponseVal2)) {
                                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons) + " \n Pou "
                                        + context.getString(R.string.label_2emSousEnejiPouKwitManje));
                            }
                        }
                        ValReponse1 = reponseVal1 + "-" + reponseVal2;
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15
                case Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15:
                    reponseModel = ((QuestionReponseModel) sp_Reponse.getSelectedItem());
                    reponseVal1 = reponseModel.getCodeReponse();
                    reponseVal2 = "0";
                    if (TextUtils.isEmpty(reponseVal1)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons) + " \n Pou "
                                + context.getString(R.string.label_E4B_Nivo));
                    } else if (sp_Reponse2.getVisibility() == View.VISIBLE) {
                        // TEST POUR 2ème LISTE DE CHOIX
                        if (sp_Reponse2.getVisibility() == View.VISIBLE) {
                            reponseModel2 = ((QuestionReponseModel) sp_Reponse2.getSelectedItem());
                            reponseVal2 = reponseModel2.getCodeReponse();

                            if (TextUtils.isEmpty(reponseVal2)) {
                                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons) + " \n Pou "
                                        + context.getString(R.string.label_E4B_KlasAneDetid));
                            }
                        }
                    }
                    ValReponse1 = reponseVal1 + "-" + reponseVal2;
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_AGE_16
                case Constant.TYPE_QUESTION_CHOIX_AGE_16:
                    keyValueModel = ((KeyValueModel) sp_Reponse.getSelectedItem());
                    ValReponse1 = keyValueModel.getKey();
                    if (TextUtils.isEmpty(ValReponse1)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons) + " \n Pou laj la");
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_LISTE_BOX_17
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_17:
                    if (this.TypeEvenement == Constant.ACTION_AFFICHER) {
                        if (recyclerViewReponse != null) {
                            RadioListAdapter radioListAdapter = (RadioListAdapter) recyclerViewReponse.getAdapter();
                            QuestionReponseModel selectedQr = radioListAdapter.getReponseModelSelectionner();
                            if (selectedQr != null) {
                                ToastUtility.LogCat("W", " selectedQr: [+]" + selectedQr.getCodeReponse() + ":" + selectedQr.getLibelleReponse());
                                ValReponse1 = selectedQr.getCodeReponse();
                            } else {
                                ToastUtility.LogCat("w", " selectedQr: [-]");
                                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons));
                            }
                        }
                    }

                    if (codeReponseRecyclerView != null) {
                        ValReponse1 = codeReponseRecyclerView.getCodeReponse();
                        ToastUtility.LogCat("codeReponseRecyclerView != null / ValReponse1:" + ValReponse1);
                        if (TextUtils.isEmpty(ValReponse1)) {
                            throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons));
                        }
                    } else {
                        ToastUtility.LogCat("codeReponseRecyclerView == null");
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_LISTE_BOX_PAYS_18
                //  TYPE_QUESTION_CHOIX_LISTE_BOX_AGE_19
                //  TYPE_QUESTION_CHOIX_LISTE_BOX_DOMAINE_ETUDE_20
                //  TYPE_QUESTION_CHOIX_NUMBER_LISTE_BOX_22
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_PAYS_18:
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_AGE_19:
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_DOMAINE_ETUDE_20:
                case Constant.TYPE_QUESTION_CHOIX_NUMBER_LISTE_BOX_22:
                    if (codeReponseKeyValueModel != null) {
                        ValReponse1 = codeReponseKeyValueModel.getKey();
                        if (TextUtils.isEmpty(ValReponse1)) {
                            throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons));
                        }
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_SAISIE_JOUR_SELECT_MOIS_SAISIE_ANNEE_23
                case Constant.TYPE_QUESTION_DATE_SAISIE_JOUR_SELECT_MOIS_SAISIE_ANNEE_23:
                    et_Jour.setError(null);
                    et_Annee.setError(null);
                    jour = et_Jour.getText().toString();
                    KeyValueModel keyValueMois = ((KeyValueModel) sp_Mois2.getSelectedItem());
                    mois = ((KeyValueModel) sp_Mois2.getSelectedItem()).getKey();
                    annee = et_Annee.getText().toString();
                    Calendar mydate = new GregorianCalendar();
                    int anneeSysteme = mydate.get(Calendar.YEAR);

                    if (TextUtils.isEmpty(jour)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_JouA);
                        et_Jour.setError(_message);
                        et_Jour.requestFocus();
                        throw new TextEmptyException(_message);

                    } else if (TextUtils.isEmpty(mois)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Mwa));

                    } else if (TextUtils.isEmpty(annee)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_AneA);
                        et_Annee.setError(_message);
                        et_Annee.requestFocus();
                        throw new TextEmptyException(_message);
                    }

                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_Jour);
                    //Tools.Check_Constraint_NombreCaratereMinimal(Constant.CHIFFRE, 2, et_Jour);

                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_Annee);
                    Tools.Check_Constraint_NombreCaratereMinimal(Constant.CHIFFRE, 4, et_Annee);

                    int jourInt = Integer.parseInt(jour);
                    if (jourInt <= 0) { // Si moun nan konn jou a li tape 00
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_JouA);
                        et_Jour.setError(_message);
                        et_Jour.requestFocus();
                        et_Jour.selectAll();
                        IsAgeIndividuVerify=true;
                        throw new TextEmptyException(_message);
                    }

                    if (keyValueMois != null) { // Verifye si jou a pa 99 sa vle di si moun nan konn jou a
                        if (!jour.equalsIgnoreCase("99")) { // Si moun nan konn jou a
                            if (jourInt > Integer.parseInt(keyValueMois.getOtherValue())) { // Verifye si jou a pi gran ke limit jou pou mwa a
                                _message = context.getString(R.string.msg_Reponse_Jou_ou_antre_a_Pa_bon);
                                et_Jour.setError(_message);
                                et_Jour.requestFocus();
                                et_Jour.selectAll();
                                IsAgeIndividuVerify=true;
                                throw new TextEmptyException(_message);

                            } else if (jourInt == 29) { // Verifye si jou a pi gran ke limit jou pou mwa a
                                if (mois.equalsIgnoreCase( "" + Constant.MOIS_FEVRIER_02 )) { // Si mwa a se Fevriye=2
                                    if (!annee.equalsIgnoreCase("" + Constant.ANNEE_PA_KONNEN_9999ANS)) { // Si moun nan konn ane , si li pa 9999
                                        if (!Tools.IsLeapYear(Integer.parseInt(annee))) { // Verifye si se yon ane bisextil
                                            _message = context.getString(R.string.msg_Reponse_Jou_ou_antre_a_Pa_bon);
                                            et_Jour.setError(_message);
                                            et_Jour.requestFocus();
                                            et_Jour.selectAll();
                                            IsAgeIndividuVerify=true;
                                            throw new TextEmptyException(_message);
                                        }else{
                                            if(  anneeSysteme < Integer.parseInt(annee) ) {
                                                _message = "Atansyon! Atansyon!...\n Dat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                                        + "\nOu paka antre [ " + annee + " ] pou ane moun nan fèt.";
                                                et_Annee.setError(_message);
                                                et_Annee.requestFocus();
                                                et_Annee.selectAll();
                                                IsAgeIndividuVerify = true;
                                                throw new TextEmptyException(_message);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!annee.equalsIgnoreCase( "" + Constant.ANNEE_PA_KONNEN_9999ANS )) { // Si moun nan konn ane , si li pa 9999
                        if (anneeSysteme < Integer.parseInt(annee)) {
                            _message = "Atansyon! \nDat sistèm nan se :" + Tools.getDateString_ddMMyyyy_HHmmss_a()
                                    + "\nOu paka antre [ " + annee + " ] pou ane moun nan fèt.";
                            et_Annee.setError(_message);
                            et_Annee.requestFocus();
                            et_Annee.selectAll();
                            IsAgeIndividuVerify=true;
                            throw new TextEmptyException(_message);
                        }
                    }
                    ValReponse1 = getReponseDate(jour, mois, annee).toString();
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_SELECT_MOIS_SAISIE_ANNEE_24
                case Constant.TYPE_QUESTION_DATE_SELECT_MOIS_SAISIE_ANNEE_24:
                    et_Annee.setError(null);
                    mois = ((KeyValueModel) sp_Mois2.getSelectedItem()).getKey();
                    annee = et_Annee.getText().toString();

                    if (TextUtils.isEmpty(mois)) {
                        throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Mwa));

                    } else if (TextUtils.isEmpty(annee)) {
                        _message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_AneA);
                        et_Annee.setError(_message);
                        et_Annee.requestFocus();
                        throw new TextEmptyException(_message);
                    }

                    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_Annee);
                    Tools.Check_Constraint_NombreCaratereMinimal(Constant.CHIFFRE, 4, et_Annee);

                    ValReponse1 = getReponseDate("1", mois, annee).toString();
                    break;
                //endregion
            }

            //endregion

            String codeQuestion = this.codeQuestion;
            String CodeReponse = ValReponse1;
            String NomChamps = this.nomChamps;
            // SET KEY - VALUE
            this.SetKey_Value(this.nomChamps, CodeReponse);
            // TEST AU NIVEAU DES CHAMPS
            this.Check_DataField_EditText(et_Reponse);
            // --- Contrainte lieu au document de specification --- //
            this.Check_ContrainteSautChampsValeur(queryRecordMngr, cuRecordMngr, CodeReponse);

            this.SetValueTempInfoQuestion(typeDeSauvegarde, codeQuestion, CodeReponse, NomChamps);
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-CheckValueBefore(): getMessage: ", ex);
            throw ex;
        }
    }//

    public void CheckValueBefore_Batiment(QueryRecordMngr queryRecordMngr //, CURecordMngr cuRecordMngr, int typeDeSauvegarde
            , String departementId, String communeId, String vqseId
            , String zoneId, String kodSeksyonEnimerasyon, String kodDistriSipevisyon
            , EditText et_Adresse, EditText et_Bitasyon, EditText et_Lokalite, String recCode, EditText et_EPC
            , EditText et_Latitude, EditText et_Longitude, String codeAgentRecenceur) throws TextEmptyException {
        try {
            String message = "";

            et_Adresse.setError(null);
            String etAdresse = "";

            et_Latitude.setError(null);
            String etLatitude = "";
            et_Longitude.setError(null);
            String etLongitude = "";

            et_Bitasyon.setError(null);
            String etBitasyon = "";

            et_Lokalite.setError(null);
            String etLokalite = "";

            etLatitude =  et_Latitude.getText().toString();
            if (TextUtils.isEmpty(etLatitude)) {
                message = context.getString(R.string.msg_Reponse_Ou_Dwe) +" "+ context.getString(R.string.label_ResumeGPS) + " " + context.getString(R.string.label_Latitude ) + " la";
                et_Latitude.setError(message);
                et_Latitude.requestFocus();
                throw new TextEmptyException(message);
            }
            etLongitude =  et_Longitude.getText().toString();
            if (TextUtils.isEmpty(etLongitude)) {
                message = context.getString(R.string.msg_Reponse_Ou_Dwe) +" "+ context.getString(R.string.label_ResumeGPS) + " "+ context.getString(R.string.label_Longitude ) + " la";
                et_Longitude.setError(message);
                et_Longitude.requestFocus();
                throw new TextEmptyException(message);
            }

            if (zoneId.equalsIgnoreCase("" + Constant.ZONE_URBAIN_1)) {
                etAdresse = et_Adresse.getText().toString();
                if (TextUtils.isEmpty(etAdresse)) {
                    message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Adresse_la);
                    et_Adresse.setError(message);
                    et_Adresse.requestFocus();
                    throw new TextEmptyException(message);
                }
            } else {
                etBitasyon = et_Bitasyon.getText().toString();
                etLokalite = et_Lokalite.getText().toString();
                if (TextUtils.isEmpty(etBitasyon) || TextUtils.isEmpty(etLokalite)) {
                    message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Bitasyon_an);
                    et_Bitasyon.setError(message);
                    et_Bitasyon.requestFocus();

                    message += " oubyen " + context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_Lokalite_a);
                    et_Lokalite.setError("" + message);
                    //et_Lokalite.requestFocus();

                    throw new TextEmptyException(message);
                }
            }

            //et_REC.setError(null);
            //String etREC = et_REC.getText().toString();
            if (TextUtils.isEmpty(recCode)) {
                message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_REC_La);
                //et_REC.setError(message);
                //et_REC.requestFocus();
                throw new TextEmptyException(message);
            } //else {
            //    Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_REC);
            //    Tools.Check_Constraint_NombreCaratereMinimal(Constant.CHIFFRE, 3, et_REC);
            //}

            et_EPC.setError(null);
            String etEPC = et_EPC.getText().toString();
            if (TextUtils.isEmpty(etEPC)) {
                message = context.getString(R.string.msg_Reponse_Ou_Dwe_Ekri_EPC_La);
                et_EPC.setError(message);
                et_EPC.requestFocus();
                throw new TextEmptyException(message);

            }else if( etEPC.equalsIgnoreCase("000") ){
                message = context.getString(R.string.msg_Reponse_numewo_RGPH_paka_000);
                et_EPC.setError(message);
                et_EPC.requestFocus();
                throw new TextEmptyException(message);

            } else {
                Tools.Check_DataField_CHIFFRE_LETTRE(context, Constant.CHIFFRE, et_EPC);
                Tools.Check_Constraint_NombreCaratereMinimal(Constant.CHIFFRE, 3, et_EPC);
            }

            // --- Contrainte lieu au document de specification --- //
            // SET KEY - VALUE
            this.SetKey_Value(BatimentDao.Properties.DeptId.columnName, departementId);
            this.SetKey_Value(BatimentDao.Properties.ComId.columnName, communeId);
            this.SetKey_Value(BatimentDao.Properties.VqseId.columnName, vqseId);
            this.SetKey_Value(BatimentDao.Properties.Zone.columnName, zoneId);
            this.SetKey_Value(BatimentDao.Properties.Qadresse.columnName, etAdresse);
            this.SetKey_Value(BatimentDao.Properties.Qhabitation.columnName, etBitasyon);
            this.SetKey_Value(BatimentDao.Properties.Qlocalite.columnName, etLokalite);
            this.SetKey_Value(BatimentDao.Properties.Qrec.columnName, recCode);
            this.SetKey_Value(BatimentDao.Properties.Qepc.columnName, etEPC);
            this.SetKey_Value(BatimentDao.Properties.SdeId.columnName, kodSeksyonEnimerasyon);
            this.SetKey_Value(BatimentDao.Properties.DisctrictId.columnName, kodDistriSipevisyon);
            this.SetKey_Value(BatimentDao.Properties.Latitude.columnName, etLatitude);
            this.SetKey_Value(BatimentDao.Properties.Longitude.columnName, etLongitude);
            this.SetKey_Value(BatimentDao.Properties.CodeAgentRecenceur.columnName, codeAgentRecenceur);
            //if (Constant.FORMULAIRE_BATIMENT == this.tbl_TableName){
            BatimentModel batimentModel = (BatimentModel) this.data;
            this.batimentModel = batimentModel;
            BatimentModel.queryRecordMngr = queryRecordMngr;
            //String QSuivant_Val = BatimentModel.Check_ContrainteSautChampsValeur(BatimentDao.Properties.Qrec.columnName, batimentModel, this.iDKeys, dataBase, true);
            //if (!QSuivant_Val.equalsIgnoreCase("")) {
            //    this.qSuivant = QSuivant_Val;
            //}
            //}
            //return individuModel;
            //this.SetKey_Value(this.nomChamps, ValReponse);
            // TEST AU NIVEAU DES CHAMPS
            //this.Check_DataField_CHIFFRE_LETTRE(et_Reponse);
            // --- Contrainte lieu au document de specification --- //
            //this.Check_ContrainteSautChampsValeur( queryRecordMngr,  cuRecordMngr, CodeReponse);

            //this.SetValueTempInfoQuestion(typeDeSauvegarde, codeQuestion, CodeReponse, NomChamps);

        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-CheckValueBefore(): getMessage: ", ex);
            throw ex;
        }
    }//

    public RapportRARModel CheckValueBefore_RapportRAR(Spinner sp_rezon, EditText et_lotRezon, Shared_Preferences sharedPreferences) throws TextEmptyException {
        try {
            String message = "";
            KeyValueModel keyValueModel = null;
            String lotRezon = "", rezonAksyon = "";

            keyValueModel = ((KeyValueModel) sp_rezon.getSelectedItem());
            rezonAksyon = keyValueModel.getKey();
            if (TextUtils.isEmpty(rezonAksyon)) {
                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Rezon));
            }
            et_lotRezon.setError(null);
            lotRezon = et_lotRezon.getText().toString();
            if (rezonAksyon.equalsIgnoreCase("" + Constant.PRECISEZ_10) ||
                    rezonAksyon.equalsIgnoreCase("" + Constant.PRECISEZ_14) ||
                    rezonAksyon.equalsIgnoreCase("" + Constant.PRECISEZ_19) ||
                    rezonAksyon.equalsIgnoreCase("" + Constant.PRECISEZ_23)) {

                if (TextUtils.isEmpty(lotRezon)) {
                    message = context.getString(R.string.msg_Reponse_Ou_Dwe_Antre_YonLotRezon);
                    et_lotRezon.setError(message);
                    et_lotRezon.requestFocus();
                    throw new TextEmptyException(message);
                }
            }
            long numeroOrdre=0, batimentId = Long.valueOf(0), logeId = Long.valueOf(0), menageId = Long.valueOf(0), emigreId = Long.valueOf(0), decesId = Long.valueOf(0), individuId = Long.valueOf(0);

            if (this.tbl_TableName == Constant.FORMULAIRE_BATIMENT) {
                batimentId = (this.batimentModel.getBatimentId() != null ? this.batimentModel.getBatimentId() : Long.valueOf(0));
                numeroOrdre = batimentId;
            }else if (this.tbl_TableName == Constant.FORMULAIRE_LOGEMENT_COLLECTIF || this.tbl_TableName == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL) {
                batimentId = (this.batimentModel.getBatimentId() != null ? this.batimentModel.getBatimentId() : Long.valueOf(0));
                logeId = (this.logementModel.getLogeId() != null ? this.logementModel.getLogeId() : Long.valueOf(0));
                numeroOrdre = (this.logementModel.getQlin1NumeroOrdre() != null ? this.logementModel.getQlin1NumeroOrdre() : Long.valueOf(0));

            } else if (this.tbl_TableName == Constant.FORMULAIRE_MENAGE ) {
                batimentId = (this.batimentModel.getBatimentId() != null ? this.batimentModel.getBatimentId() : Long.valueOf(0));
                logeId = (this.logementModel.getLogeId() != null ? this.logementModel.getLogeId() : Long.valueOf(0));
                menageId = (this.menageModel.getMenageId() != null ? this.menageModel.getMenageId() : Long.valueOf(0));
                numeroOrdre = (this.menageModel.getQm1NoOrdre() != null ? this.menageModel.getQm1NoOrdre() : Long.valueOf(0));

            }else if( this.tbl_TableName == Constant.FORMULAIRE_INDIVIDUS ) {
                batimentId = (this.batimentModel.getBatimentId() != null ? this.batimentModel.getBatimentId() : Long.valueOf(0));
                logeId = (this.logementModel.getLogeId() != null ? this.logementModel.getLogeId() : Long.valueOf(0));
                if( this.logementModel.getQlCategLogement() != null && this.logementModel.getQlCategLogement() == Constant.LOJ_ENDIVIDYEL ){
                    menageId = (this.menageModel.getMenageId() != null ? this.menageModel.getMenageId() : Long.valueOf(0));
                }
                individuId = (this.individuModel.getIndividuId() != null ? this.individuModel.getIndividuId() : Long.valueOf(0));
                numeroOrdre = (this.individuModel.getQ1NoOrdre() != null ? this.individuModel.getQ1NoOrdre() : Long.valueOf(0));
            }
            String codeAgentRecenceur = "";
            //Shared_Preferences sharedPreferences = Tools.SharedPreferences(context);
            if ( sharedPreferences != null ){
                codeAgentRecenceur = sharedPreferences.getNomUtilisateur();
            }
            RapportRARModel rarModel = new RapportRARModel();
            rarModel.setBatimentId(batimentId);
            rarModel.setLogeId( logeId);
            rarModel.setMenageId(menageId);
            rarModel.setEmigreId(emigreId);
            rarModel.setDecesId(decesId);
            rarModel.setIndividuId(individuId);

            rarModel.setRapportModuleName(GetModuleName()+" "+numeroOrdre);
            rarModel.setCodeQuestionStop(this.nomChamps);
            rarModel.setVisiteNumber((short) 0);
            rarModel.setRaisonActionId(Short.valueOf(rezonAksyon));
            rarModel.setAutreRaisonAction(lotRezon);
            rarModel.setIsFieldAllFilled(true);
            String setDateCollecte =  Tools.getDateString_MMddyyyy_HHmmss_a();
            rarModel.setDateDebutCollecte(this.dateDebutCollecte);
            rarModel.setDateFinCollecte(setDateCollecte);

            rarModel.setDureeSaisie((int) getDureeSaisie(""));
            rarModel.setCodeAgentRecenceur(codeAgentRecenceur);
            rarModel.setIsContreEnqueteMade(false);

            CURecordMngr cuRecordMngr=new CURecordMngrImpl(context);
            SaveInfoRapportRAR(cuRecordMngr, rarModel);
            //cuRecordMngr.saveRapportRAR( rarModel);
            return rarModel;
        }catch (TextEmptyException  ex) {
            ToastUtility.LogCat("TextEmptyException-CheckValueBefore_RapportRAR(): getMessage: ", ex);
            throw ex;
        }catch (Exception ex) {
            ToastUtility.LogCat("Exception-CheckValueBefore_RapportRAR(): getMessage: ", ex);
            throw ex;
        }
    }

    public RapportFinalModel CheckValueBefore_RapportFinalRAR(Spinner sp_RepondantPrincipal, Spinner sp_AE_EsKeGenMounKiEde, Spinner sp_AE_IsVivreDansMenage, Spinner sp_AE_RepondantQuiAide
            ,Spinner sp_F_EsKeGenMounKiEde, Spinner sp_F_IsVivreDansMenage, Spinner sp_F_RepondantQuiAide,  Shared_Preferences sharedPreferences)
            throws TextEmptyException, ManagerException {
        try {
            String message = "";
            KeyValueModel keyValueModel = null;
            IndividuModel individuModel = null;
            String ae_EsKeGenMounKiEde = ""+Constant.REPONS_NON_0, ae_IsVivreDansMenage = ""+Constant.REPONS_NON_0, f_EsKeGenMounKiEde = ""+Constant.REPONS_NON_0, f_IsVivreDansMenage = ""+Constant.REPONS_NON_0;
            long repondantPrincipal = 0, ae_RepondantQuiAide = 0, f_RepondantQuiAide = 0;

            individuModel = ((IndividuModel) sp_RepondantPrincipal.getSelectedItem());
            repondantPrincipal = (individuModel.getIndividuId() != null ? individuModel.getIndividuId() : Long.valueOf(0));
            if ( repondantPrincipal <= 0 ) {
                throw new TextEmptyException(context.getString(R.string.msg_Ou_Dwe_Chwazi_Moun_Ki_Reponn_Plis_Kesyon));
            }


            //region AE.- AKTIVITE EKONOMIK
            keyValueModel = ((KeyValueModel) sp_AE_EsKeGenMounKiEde.getSelectedItem());
            ae_EsKeGenMounKiEde = keyValueModel.getKey();
            if (TextUtils.isEmpty(ae_EsKeGenMounKiEde)) {
                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons)
                + " "+ context.getString(R.string.msg_Pou_di_kiyes_ki_ede_repondan)
                + " "+ context.getString(R.string.msg_Nan_seksyon_Aktivite_Ekonomik));
            }

            if( ae_EsKeGenMounKiEde.equalsIgnoreCase(""+Constant.REPONS_WI_1)) {
                keyValueModel = ((KeyValueModel) sp_AE_IsVivreDansMenage.getSelectedItem());
                ae_IsVivreDansMenage = keyValueModel.getKey();
                if (TextUtils.isEmpty(ae_IsVivreDansMenage)) {
                    throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons)
                            + " " + context.getString(R.string.msg_Pou_di_Si_moun_sa_moun_sa_ap_viv_nan_menaj_la)
                            + " " + context.getString(R.string.msg_Nan_seksyon_Aktivite_Ekonomik));
                }
            }
            if( ae_IsVivreDansMenage.equalsIgnoreCase(""+Constant.REPONS_WI_1)) {
                individuModel = ((IndividuModel) sp_AE_RepondantQuiAide.getSelectedItem());
                ae_RepondantQuiAide = (individuModel.getIndividuId() != null ? individuModel.getIndividuId() : Long.valueOf(0));
                if (ae_RepondantQuiAide <= 0) {
                    throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons)
                            + " " + context.getString(R.string.msg_Pou_di_kiyes_ki_ede_repondan)
                            + " " + context.getString(R.string.msg_Nan_seksyon_Aktivite_Ekonomik));
                }
            }
            //endregion

            //region F.- FECONDITE
            keyValueModel = ((KeyValueModel) sp_F_EsKeGenMounKiEde.getSelectedItem());
            f_EsKeGenMounKiEde = keyValueModel.getKey();
            if (TextUtils.isEmpty(f_EsKeGenMounKiEde)) {
                throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons)
                        + " "+ context.getString(R.string.msg_Pou_di_kiyes_ki_ede_repondan)
                        + " "+ context.getString(R.string.msg_Nan_seksyon_Fekondite));
            }

            if( f_EsKeGenMounKiEde.equalsIgnoreCase(""+Constant.REPONS_WI_1)) {
                keyValueModel = ((KeyValueModel) sp_F_IsVivreDansMenage.getSelectedItem());
                f_IsVivreDansMenage = keyValueModel.getKey();
                if (TextUtils.isEmpty(f_IsVivreDansMenage)) {
                    throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons)
                            + " " + context.getString(R.string.msg_Pou_di_Si_moun_sa_moun_sa_ap_viv_nan_menaj_la)
                            + " " + context.getString(R.string.msg_Nan_seksyon_Fekondite));
                }
            }
            if( f_IsVivreDansMenage.equalsIgnoreCase(""+Constant.REPONS_WI_1)) {
                individuModel = ((IndividuModel) sp_F_RepondantQuiAide.getSelectedItem());
                f_RepondantQuiAide = (individuModel.getIndividuId() != null ? individuModel.getIndividuId() : Long.valueOf(0));
                if (f_RepondantQuiAide <= 0) {
                    throw new TextEmptyException(context.getString(R.string.msg_Reponse_Ou_Dwe_Chwazi_Yon_Repons)
                            + " " + context.getString(R.string.msg_Pou_di_kiyes_ki_ede_repondan)
                            + " " + context.getString(R.string.msg_Nan_seksyon_Fekondite));
                }
            }
            //endregion

            long numeroOrdre=0, batimentId = Long.valueOf(0), logeId = Long.valueOf(0), menageId = Long.valueOf(0);

            if (this.tbl_TableName == Constant.FORMULAIRE_MENAGE ) {
                batimentId = (this.batimentModel.getBatimentId() != null ? this.batimentModel.getBatimentId() : Long.valueOf(0));
                logeId = (this.logementModel.getLogeId() != null ? this.logementModel.getLogeId() : Long.valueOf(0));
                menageId = (this.menageModel.getMenageId() != null ? this.menageModel.getMenageId() : Long.valueOf(0));
                numeroOrdre = (this.menageModel.getQm1NoOrdre() != null ? this.menageModel.getQm1NoOrdre() : Long.valueOf(0));
            }
            String codeAgentRecenceur = "";
            //Shared_Preferences sharedPreferences = Tools.SharedPreferences(context);
            if ( sharedPreferences != null ){
                codeAgentRecenceur = sharedPreferences.getNomUtilisateur();
            }
            RapportFinalModel rarModel = new RapportFinalModel();
            rarModel.setBatimentId(batimentId);
            rarModel.setLogeId( logeId);
            rarModel.setMenageId(menageId);
            rarModel.setRepondantPrincipalId(repondantPrincipal);
            rarModel.setAE_EsKeGenMounKiEde(Short.valueOf(ae_EsKeGenMounKiEde));
            rarModel.setAE_IsVivreDansMenage(Short.valueOf(ae_IsVivreDansMenage));
            rarModel.setAE_RepondantQuiAideId((short) ae_RepondantQuiAide);

            rarModel.setF_EsKeGenMounKiEde(Short.valueOf(f_EsKeGenMounKiEde));
            rarModel.setF_IsVivreDansMenage(Short.valueOf(f_IsVivreDansMenage));
            rarModel.setF_RepondantQuiAideId( f_RepondantQuiAide);

            String setDateCollecte =  Tools.getDateString_MMddyyyy_HHmmss_a();
            rarModel.setDateDebutCollecte(this.dateDebutCollecte);
            rarModel.setDateFinCollecte(setDateCollecte);

            rarModel.setDureeSaisie((int) getDureeSaisie(""));
            rarModel.setCodeAgentRecenceur(codeAgentRecenceur);
            rarModel.setIsContreEnqueteMade(false);

            CURecordMngr cuRecordMngr=new CURecordMngrImpl(context);
            rarModel = cuRecordMngr.saveRapportFinal( rarModel);

            return rarModel;
        }catch (TextEmptyException  ex) {
            ToastUtility.LogCat("TextEmptyException-CheckValueBefore_RapportFinalRAR(): getMessage: ", ex);
            throw ex;
        }catch (ManagerException ex) {
            ToastUtility.LogCat("ManagerException-CheckValueBefore_RapportFinalRAR(): getMessage: ", ex);
            throw ex;
        }catch (Exception ex) {
            ToastUtility.LogCat("Exception-CheckValueBefore_RapportFinalRAR(): getMessage: ", ex);
            throw ex;
        }
    }

    private String GetModuleName() {
        if( this.tbl_TableName == Constant.FORMULAIRE_BATIMENT){
            return Constant.MODULE_NAME_BATIMAN;
        }else if( this.tbl_TableName == Constant.FORMULAIRE_LOGEMENT_COLLECTIF ){
            return Constant.MODULE_NAME_LOGMAN_KOLEKTIF;
        }else if( this.tbl_TableName == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL ){
            return Constant.MODULE_NAME_LOGMAN_ENDIVIDYEL;
        }else if( this.tbl_TableName == Constant.FORMULAIRE_MENAGE ){
            return Constant.MODULE_NAME_MENAGE;
        }else if( this.tbl_TableName == Constant.FORMULAIRE_EMIGRE ){
            return Constant.MODULE_NAME_EMIGRE;
        }else if( this.tbl_TableName == Constant.FORMULAIRE_DECES ){
            return Constant.MODULE_NAME_DECES;
        }else if( this.tbl_TableName == Constant.FORMULAIRE_INDIVIDUS ){
            return Constant.MODULE_NAME_INDIVIDU;
        }
        return "";
    }
    //endregion

    //region CHECK VALUES
    public void VisibleOrHide_06DateMembreMenage(String CodeReponse
            ,TextView tv_06DateMembreMenage, LinearLayout LL_06DateMembreMenage) {
        try{
            tv_06DateMembreMenage.setVisibility(View.GONE);
            LL_06DateMembreMenage.setVisibility(View.GONE);
            if ( CodeReponse != null) {
                if ( !CodeReponse.trim().equalsIgnoreCase("" + Constant.R01_Avan_Resansman) ) {
                    tv_06DateMembreMenage.setVisibility(View.VISIBLE);
                    LL_06DateMembreMenage.setVisibility(View.VISIBLE);
                }
            }
        }catch (Exception ex) {
            ToastUtility.LogCat("Exception:VisibleOrHide_06DateMembreMenage", ex);
        }
    }
    public void VisibleOrHide_11NiveauEtude_12StatutMatrimonial(int age
            ,TextView tv_11NiveauEtude, RelativeLayout RL_11NiveauEtude
            ,TextView tv_12StatutMatrimonial, RelativeLayout RL_12StatutMatrimonial) {
        try{
            tv_11NiveauEtude.setVisibility(View.GONE);
            RL_11NiveauEtude.setVisibility(View.GONE);

            tv_12StatutMatrimonial.setVisibility(View.GONE);
            RL_12StatutMatrimonial.setVisibility(View.GONE);

            if( age >= Constant.AGE_03ANS ){
                tv_11NiveauEtude.setVisibility(View.VISIBLE);
                RL_11NiveauEtude.setVisibility(View.VISIBLE);
            }
            if( age > Constant.AGE_10ANS ){
                tv_12StatutMatrimonial.setVisibility(View.VISIBLE);
                RL_12StatutMatrimonial.setVisibility(View.VISIBLE);
            }
        }catch (Exception ex) {
            ToastUtility.LogCat("Exception:VisibleOrHide_06DateMembreMenage", ex);
        }
    }
    public void VisibleOrHide_EstMortOuQuitter(String CodeReponse
            ,TextView tv_Q7DateQuitterMenage, LinearLayout LL_Q7DateQuitterMenage
            ,TextView tv_Q7bDateMouri, LinearLayout LL_Q7bDateMouri ) {
        try{
            if ( !CodeReponse.trim().equalsIgnoreCase(""+Constant.R03_Li_Mouri) ) {
                tv_Q7DateQuitterMenage.setVisibility(View.VISIBLE);
                LL_Q7DateQuitterMenage.setVisibility(View.VISIBLE);
            }
            if ( CodeReponse.trim().equalsIgnoreCase(""+Constant.R03_Li_Mouri) ) {
                tv_Q7bDateMouri.setVisibility(View.VISIBLE);
                LL_Q7bDateMouri.setVisibility(View.VISIBLE);
            }
        }catch (Exception ex) {
            ToastUtility.LogCat("Exception:VisibleOrHide_EstMortOuQuitter", ex);
        }
    }
    //endregion

    //region SET VALUES
    public AncienMembreModel Get_Set_AncienMembre_IfExist(QueryRecordMngr queryRecordMngr, Dialog dialog, long ID_INDIVIDU
            , int nbrInd_NoOrdre, int Nbre_TotalIndividu, TextView tv_NumeroIndividu
            , EditText et_Qp2APrenom, EditText et_Qp2BNom, Spinner sp_Qp4Sexe
            , Spinner sp_Q5EstMortOuQuitter , Spinner sp_Q6HabiteDansMenage
            , TextView tv_Q7DateQuitterMenage, LinearLayout LL_Q7DateQuitterMenage
            , EditText et_Q7DateQuitterMenageJour, Spinner sp_Q7DateQuitterMenageMois, EditText et_Q7DateQuitterMenageAnnee
            , TextView tv_Q7bDateMouri, LinearLayout LL_Q7bDateMouri
            , EditText et_Q7bDateMouriJour, Spinner sp_Q7bDateMouriMois, EditText et_Q7bDateMouriAnnee
            , EditText et_Q8DateNaissanceJour, Spinner sp_Q8DateNaissanceMois, EditText et_Q8DateNaissanceAnnee
            , EditText et_Q9AgeAncienMembre , TextView tv_Q12NiveauEtude, RelativeLayout RL_Q12NiveauEtude , TextView tv_12StatutMatrimonial, RelativeLayout RL_12StatutMatrimonial
            , Spinner sp_Q10LienDeParente , Spinner sp_Q11Nationalite, TextView tv_Q11PaysNationalite, RelativeLayout RL_Q11PaysNationalite
            , Spinner sp_Q11PaysNationalite, Spinner sp_Q12NiveauEtude, Spinner sp_Q13StatutMatrimonial) {

        AncienMembreModel.queryRecordMngr = queryRecordMngr;
        AncienMembreModel ancienMembreModel = AncienMembreModel.GetIndividu(nbrInd_NoOrdre, this.getMenageModel().getMenageId());
        if( ancienMembreModel != null ){
            ID_INDIVIDU = ancienMembreModel.getAncienMembreId();
            nbrInd_NoOrdre = ancienMembreModel.getQ1NoOrdre();
            String msg = " Modifye [ Ansyen Manm #" + ancienMembreModel.getQ1NoOrdre() + " / " + Nbre_TotalIndividu +" ]";
            dialog.setTitle(msg);
            tv_NumeroIndividu.setText(msg);

            et_Qp2APrenom.setText("" + ancienMembreModel.getQp2APrenom());
            et_Qp2BNom.setText("" + ancienMembreModel.getQp2BNom());

            this.setReponse(sp_Qp4Sexe, "" + ancienMembreModel.getQp4Sexe(), Constant.CLASSE_REPONSE_MODEL);

            //region [ 05 Est Mort Ou Quitter ]
            this.setReponse(sp_Q5EstMortOuQuitter, "" + ancienMembreModel.getQ5EstMortOuQuitter(), Constant.CLASSE_REPONSE_MODEL);
            this.VisibleOrHide_EstMortOuQuitter( ""+ancienMembreModel.getQ5EstMortOuQuitter()
                    , tv_Q7DateQuitterMenage,  LL_Q7DateQuitterMenage
                    , tv_Q7bDateMouri,  LL_Q7bDateMouri );
            //endregion

            this.setReponse(sp_Q6HabiteDansMenage, "" + ancienMembreModel.getQ6HabiteDansMenage(), Constant.CLASSE_REPONSE_MODEL);

            this.setReponseDate(et_Q7DateQuitterMenageJour, sp_Q7DateQuitterMenageMois, et_Q7DateQuitterMenageAnnee, "" + ancienMembreModel.getQ7JourMoisAnneeDateQuitterMenage());

            this.setReponseDate(et_Q7bDateMouriJour, sp_Q7bDateMouriMois, et_Q7bDateMouriAnnee, "" + ancienMembreModel.getQ7bJourMoisAnneeDateMouri());

            this.setReponseDate(et_Q8DateNaissanceJour, sp_Q8DateNaissanceMois,et_Q8DateNaissanceAnnee, "" + ancienMembreModel.getQ8JourMoisAnneeDateNaissance());

            et_Q9AgeAncienMembre.setText("" + ancienMembreModel.getQ9Age());
            this.VisibleOrHide_11NiveauEtude_12StatutMatrimonial( ancienMembreModel.getQ9Age()
                    , tv_Q12NiveauEtude, RL_Q12NiveauEtude, tv_12StatutMatrimonial, RL_12StatutMatrimonial );

            this.setReponse(sp_Q10LienDeParente, "" + ancienMembreModel.getQ10LienDeParente(), Constant.CLASSE_REPONSE_MODEL);
            this.setReponse(sp_Q11Nationalite, "" + ancienMembreModel.getQ11Nationalite(), Constant.CLASSE_REPONSE_MODEL);
            if( !ancienMembreModel.getQ11PaysNationalite().trim().equalsIgnoreCase("" + Constant.R01_Avan_Resansman)) {
                tv_Q11PaysNationalite.setVisibility(View.VISIBLE);
                RL_Q11PaysNationalite.setVisibility(View.VISIBLE);
            }
            this.setReponse(sp_Q11Nationalite, "" + ancienMembreModel.getQ11Nationalite(), Constant.CLASSE_REPONSE_MODEL);
            this.setReponse(sp_Q11PaysNationalite, "" + ancienMembreModel.getQ11PaysNationalite(), Constant.CLASSE_KEY_VALUE_MODEL);
            this.setReponse(sp_Q12NiveauEtude, "" + ancienMembreModel.getQ12NiveauEtude(), Constant.CLASSE_REPONSE_MODEL);
            this.setReponse(sp_Q13StatutMatrimonial, "" + ancienMembreModel.getQ12StatutMatrimonial(), Constant.CLASSE_REPONSE_MODEL);

        }else{
            String msg = " [ Ansyen Manm #" + nbrInd_NoOrdre + " / " + Nbre_TotalIndividu +" ]";
            dialog.setTitle(msg);
            tv_NumeroIndividu.setText(msg);
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

            et_Qp2APrenom.requestFocus();
        }
        return ancienMembreModel;
    }

    public IndividuModel Get_Set_Individu_IfExist(QueryRecordMngr queryRecordMngr, Dialog dialog, long ID_INDIVIDU
            , int nbrInd_NoOrdre, int Nbre_TotalIndividu, TextView tv_NumeroIndividu
            , EditText et_02NonIndividu, EditText et_03SiyatiIndividu, Spinner sp_04Sexe
            , Spinner sp_05HabiteDansMenage , Spinner sp_06MembreMenageDepuisQuand
            , TextView tv_06DateMembreMenage, LinearLayout LL_06DateMembreMenage
            , EditText et_06DateMembreMenageJour, Spinner sp_06DateMembreMenageMois, EditText et_06DateMembreMenageAnnee
            , EditText et_07DateNaissanceJour, Spinner sp_07DateNaissanceMois, EditText et_07DateNaissanceAnnee
            , EditText et_08AgeIndividu, TextView tv_11NiveauEtude, RelativeLayout RL_11NiveauEtude , TextView tv_12StatutMatrimonial, RelativeLayout RL_12StatutMatrimonial
            , Spinner sp_09LienDeParente , Spinner sp_10Nationalite, TextView tv_10PaysNationalite, RelativeLayout RL_10PaysNationalite
            , Spinner sp_10PaysNationalite, Spinner sp_11NiveauEtude, Spinner sp_12StatutMatrimonial) {

        IndividuModel.queryRecordMngr = queryRecordMngr;
        IndividuModel individuModel = IndividuModel.GetIndividu(nbrInd_NoOrdre, this.getMenageModel().getMenageId());
        if( individuModel != null ){
            ID_INDIVIDU = individuModel.getIndividuId();
            nbrInd_NoOrdre = individuModel.getQ1NoOrdre();
            String msg = " Modifye [ Moun #" + individuModel.getQ1NoOrdre() + " / " + Nbre_TotalIndividu +" ]";
            dialog.setTitle(msg);
            tv_NumeroIndividu.setText(msg);

            et_02NonIndividu.setText("" + individuModel.getQp2APrenom());
            et_03SiyatiIndividu.setText("" + individuModel.getQp2BNom());

            this.setReponse(sp_04Sexe, "" + individuModel.getQp4Sexe(), Constant.CLASSE_REPONSE_MODEL);
            this.setReponse(sp_05HabiteDansMenage, "" + individuModel.getQ5HabiteDansMenage(), Constant.CLASSE_REPONSE_MODEL);

            this.setReponse(sp_06MembreMenageDepuisQuand, "" + individuModel.getQ6aMembreMenageDepuisQuand(), Constant.CLASSE_REPONSE_MODEL);
            this.VisibleOrHide_06DateMembreMenage( ""+individuModel.getQ6aMembreMenageDepuisQuand(), tv_06DateMembreMenage, LL_06DateMembreMenage);
            this.setReponseDate(et_06DateMembreMenageJour, sp_06DateMembreMenageMois,et_06DateMembreMenageAnnee, "" + individuModel.getQ6bJourMoisAnneeDateMembreMenage());

            this.setReponseDate(et_07DateNaissanceJour, sp_07DateNaissanceMois,et_07DateNaissanceAnnee, "" + individuModel.getQ7JourMoisAnneeDateNaissance());

            et_08AgeIndividu.setText("" + individuModel.getQ8Age());
            this.VisibleOrHide_11NiveauEtude_12StatutMatrimonial( individuModel.getQ8Age()
                    , tv_11NiveauEtude, RL_11NiveauEtude, tv_12StatutMatrimonial, RL_12StatutMatrimonial );

            this.setReponse(sp_09LienDeParente, "" + individuModel.getQ9LienDeParente(), Constant.CLASSE_REPONSE_MODEL);
            this.setReponse(sp_10Nationalite, "" + individuModel.getQp10Nationalite(), Constant.CLASSE_REPONSE_MODEL);
            if( !individuModel.getQp10PaysNationalite().trim().equalsIgnoreCase("" + Constant.R01_Avan_Resansman)) {
                tv_10PaysNationalite.setVisibility(View.VISIBLE);
                RL_10PaysNationalite.setVisibility(View.VISIBLE);
            }
            this.setReponse(sp_10Nationalite, "" + individuModel.getQp10Nationalite(), Constant.CLASSE_REPONSE_MODEL);
            this.setReponse(sp_10PaysNationalite, "" + individuModel.getQp10PaysNationalite(), Constant.CLASSE_KEY_VALUE_MODEL);
            this.setReponse(sp_11NiveauEtude, "" + individuModel.getQ11NiveauEtude(), Constant.CLASSE_REPONSE_MODEL);
            this.setReponse(sp_12StatutMatrimonial, "" + individuModel.getQ12StatutMatrimonial(), Constant.CLASSE_REPONSE_MODEL);

        }else{
            //dialog.setTitle("Ajoute Moun sa nan menaj sa [" + nbrInd_NoOrdre + " / " + Nbre_TotalIndividu + "]");
            ID_INDIVIDU=0;
            String msg = " [ Moun #" + nbrInd_NoOrdre + " / " + Nbre_TotalIndividu +" ]";
            dialog.setTitle(msg);
            tv_NumeroIndividu.setText(msg);
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
        }
        return individuModel;
    }

    public void SetValueTempInfoQuestion(int typeDeSauvegarde, String codeQuestion, String CodeReponse, String NomChamps)  {
        try {
            TempInfoQuestion temp = new TempInfoQuestion();
            temp.setCodeQuestion(codeQuestion);
            temp.setNomChamps(NomChamps);
            temp.setCodeReponse(CodeReponse);
            temp.setCodeUniqueReponse(CodeReponse);

            if( Constant.SetValueTempInfoQuestion_Batiment == typeDeSauvegarde ){
                QuestionnaireBatimentActivity.tempInfoQuestions.add(temp);

            }else if(Constant.SetValueTempInfoQuestion_Logement == typeDeSauvegarde ){
                QuestionnaireLogementActivity.tempInfoQuestions.add(temp);

            }else if(Constant.SetValueTempInfoQuestion_Individu == typeDeSauvegarde ){
                QuestionnaireIndividuActivity.tempInfoQuestions.add(temp);

            }else if(Constant.SetValueTempInfoQuestion_Menage == typeDeSauvegarde ){
                QuestionnaireMenageActivity.tempInfoQuestions.add(temp);

            }else if(Constant.SetValueTempInfoQuestion_Emigre == typeDeSauvegarde ){
                //QuestionnaireEmigreActivity.tempInfoQuestions.add(temp);

            }else if(Constant.SetValueTempInfoQuestion_Deces == typeDeSauvegarde ){
                //QuestionnaireDecesActivity.tempInfoQuestions.add(temp);
            }
        } catch (Exception ex) {
            ToastUtility.LogCat( "Exception-SetValueTempInfoQuestion(): getMessage: ", ex);
            throw ex;
        }
    }//

    public void SetValueTemp_InViewControl( FormDataMngr formDataMngr,  String codeQuestion, String codeReponse,
                                            EditText et_Reponse, Spinner sp_Reponse, Spinner sp_Reponse2, Spinner sp_Reponse3, Spinner sp_Jour, Spinner sp_Mois, Spinner sp_Annee
            , EditText et_Gason, EditText et_Fi , EditText et_ApareyRadyo, EditText et_Televizyon, EditText et_FrijideFrize
            , EditText et_FouElektrikFouAkGaz, EditText et_OdinatePCLaptopTabletNimerik, EditText et_BisikletMotosiklet, EditText et_VwatiMachin
            , EditText et_KannotBato, EditText et_InvetePanoSoleJeneratrisDelko, EditText et_MiletChwalBourik, EditText et_BefVach, EditText et_KochonKabrit, EditText et_BetVolayPoulKok
            , RecyclerView recyclerViewReponse
            , EditText et_Jour, Spinner sp_Mois2, EditText et_Annee
    ) {
        try {
            Pattern pattern;// = Pattern.compile("^[A-Za-z0-9]");
            Matcher matcher;// = pattern.matcher(EditText_Reponse);
            //if (this.dataBase == null){
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

            et_Gason.setText(Constant.STRING_VIDE);
            et_Fi.setText(Constant.STRING_VIDE);

            et_Jour.setText(Constant.STRING_VIDE);
            et_Annee.setText(Constant.STRING_VIDE);
            //}
            switch ( this.typeQuestion ) {
                //region TYPE_QUESTION_CHOIX_1
                case Constant.TYPE_QUESTION_CHOIX_1:
                case Constant.TYPE_QUESTION_CHOIX_INDIVIDU_9:
                    this.setReponse(sp_Reponse, codeReponse, Constant.CLASSE_REPONSE_MODEL);
                    break;
                //endregion
                //region TYPE_QUESTION_SAISIE_2
                case Constant.TYPE_QUESTION_SAISIE_2:
                    if (codeReponse != null) {
                        if (!codeReponse.equalsIgnoreCase("null")) {
                            et_Reponse.setText(codeReponse);
                        }
                    }
                    //if( this.TypeEvenement != Constant.ACTION_AFFICHER)
                    et_Reponse.selectAll();
                /*if (NomChamps == Cls_Individu.QP5Age)
                {
                    Cls_Individu b = (Cls_Individu)data;
                    if (b.Qp5AnneeNaissance != 999)
                    {
                        int Age = DateTime.Now.Year - b.Qp5AnneeNaissance;
                        txt_Reponse.Text = Age.ToString();
                    }
                }*/
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_MM_AAAA_3
                case Constant.TYPE_QUESTION_DATE_MM_AAAA_3:
                    this.setReponseDate(sp_Mois, sp_Annee, codeReponse);
                    break;
                //endregion
                //region TYPE_QUESTION_DEUX_CHOIX_4
                case Constant.TYPE_QUESTION_DEUX_CHOIX_4:
// RECHERCHE DE L'OBJET QUESTION REPONSE PAR LE CODE REPONSE
                    //String codeReponse = codeQuestion.trim() +"-"+ codeReponse.trim();
                    QuestionReponseModel qReponseModel = formDataMngr.getQuestionResponsesByCodeQuestionByCodeReponse(codeQuestion, codeReponse);
                    codeReponse = qReponseModel.getCodeReponse();
                    String codeParent = qReponseModel.getCodeParent();
                    String codeQuestionSplit = "";
                    String codeReponseParent = "";

                    if (!codeParent.trim().equalsIgnoreCase("")) {
                        String[] codeSplit = codeParent.split("-"); // B8.1-29
                        codeQuestionSplit = codeSplit[0];  // B8.1
                        codeReponseParent = codeSplit[1]; // 29
                        this.setReponse(sp_Reponse, codeReponseParent, Constant.CLASSE_REPONSE_MODEL);

                        this.Load_PossibiliteReponse_Child(context, formDataMngr, sp_Reponse2, codeParent);
                        this.codeReponseParentLast = codeParent;
                        this.IsLoadPossibiliteReponse_ChildAgain = false;
                        this.setReponse(sp_Reponse2, codeReponse, Constant.CLASSE_REPONSE_MODEL);
                    } else {
                        this.setReponse(sp_Reponse, codeReponse, Constant.CLASSE_REPONSE_MODEL);
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_PAYS_5
                case Constant.TYPE_QUESTION_CHOIX_PAYS_5:
                    this.setReponse(sp_Reponse, codeReponse, Constant.CLASSE_KEY_VALUE_MODEL);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMMUNE_6
                case Constant.TYPE_QUESTION_CHOIX_COMMUNE_6:
// RECHERCHE DE L'OBJET COMMUNE POUR AVOIR LE CODE DU DEPARTEMENT
                    String idDept = "0";
                    String comId = "0";
                    CommuneModel com = formDataMngr.getCommuneById(codeReponse);
                    if (com != null && com.getDeptId() != null && com.getComId() != null) {
                        idDept = com.getDeptId();
                        comId = com.getComId();
                    }
                    // CHARGEMENT DE TOUS LES COMMUNES PAR DEPT
                    //ToastUtility.LogCat("[+] CALL ON SET (this.Load_CommuneByIdDept(sp_Reponse2, com.getDeptId());)");
                    this.Load_CommuneByIdDept(formDataMngr, sp_Reponse2, idDept);
                    this.idDeptLast = idDept;
                    this.IsLoadCommuneAgain = false;
                    // SELECTION DES ELEMENTS
                    //ToastUtility.LogCat( "getDeptId>> " + com.getDeptId() + " / getComId>> " + com.getComId() + " / getComNom>> " + com.getComNom());
                    this.setReponse(sp_Reponse, idDept, Constant.CLASSE_KEY_VALUE_MODEL);
                    this.setReponse(sp_Reponse2, comId, Constant.TYPE_QUESTION_CHOIX_COMMUNE_6);
 break;
                //endregion
                //region TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7
                case Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7:
// RECHERCHE DE L'OBJET SECTION COMMUNALE POUR AVOIR LE CODE DU DEPARTEMENT AINSI QUE LA COMMUNE
                    // Load VQSE
                    VqseModel vqse = formDataMngr.getVqseById(codeReponse);
                    String vqseId = "0";
                     comId = "0";
                     idDept = "0";
                    if (vqse != null && vqse.getVqseId() != null && vqse.getComId() != null) {
                        vqseId = vqse.getVqseId();
                        comId = vqse.getComId();
                    }
                    // CHARGEMENT DE TOUS LES COMMUNES
                    com = formDataMngr.getCommuneById(comId);
                    if (com != null && com.getDeptId() != null) {
                        idDept = com.getDeptId();
                    }
                    // lOAD Commune BY Dept
                    this.Load_CommuneByIdDept(formDataMngr, sp_Reponse2, idDept);
                    this.idDeptLast = idDept;
                    this.IsLoadCommuneAgain = false;
                    // lOAD VQSE Commune BY Commune
                    this.Load_Vqse_ByIdCommune(formDataMngr, sp_Reponse3, comId);

                    this.idComLast = comId;
                    this.IsLoadVqseAgain = false;
                    // set Departement
                    //ToastUtility.LogCat("W", "set Departement : getDeptId>> " + com.getDeptId() + " / getComId>> " + com.getComId() + " / getComNom>> " + com.getComNom());
                    this.setReponse(sp_Reponse, idDept, Constant.CLASSE_KEY_VALUE_MODEL);
                    // set Commune
                    //ToastUtility.LogCat("W", "set Commune : getDeptId>> " + com.getDeptId() + " / getComId>> " + com.getComId() + " / getComNom>> " + com.getComNom());
                    this.setReponse(sp_Reponse2, comId, Constant.TYPE_QUESTION_CHOIX_COMMUNE_6);
                    // set VQSE
                    //ToastUtility.LogCat("W", "set Vqse : getVqseId>> " + vqse.getVqseId() + " / getvqse>> " + vqse.getVqseNom());
                    this.setReponse(sp_Reponse3, vqseId, Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7);

                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_DOMAINE_ETUDE_8
                case Constant.TYPE_QUESTION_CHOIX_DOMAINE_ETUDE_8:
                    this.setReponse(sp_Reponse, codeReponse, Constant.CLASSE_KEY_VALUE_MODEL);
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_JJ_MM_AAAA_11
                case Constant.TYPE_QUESTION_DATE_JJ_MM_AAAA_11:
                    this.setReponseDate(sp_Jour, sp_Mois, sp_Annee, codeReponse);

                    break;
                //endregion
                //region TYPE_QUESTION_NBR_APPAREIL_ET_ANIMAUX_12
                case Constant.TYPE_QUESTION_NBR_APPAREIL_ET_ANIMAUX_12:
                    if (!codeReponse.trim().equalsIgnoreCase("")) {
                        String[] nbrItems = codeReponse.split("-");
                        et_ApareyRadyo.setText(nbrItems[0]);
                        et_Televizyon.setText(nbrItems[1]);
                        et_FrijideFrize.setText(nbrItems[2]);
                        et_FouElektrikFouAkGaz.setText(nbrItems[3]);
                        et_OdinatePCLaptopTabletNimerik.setText(nbrItems[4]);
                        et_BisikletMotosiklet.setText(nbrItems[5]);
                        et_VwatiMachin.setText(nbrItems[6]);
                        et_KannotBato.setText(nbrItems[7]);
                        et_InvetePanoSoleJeneratrisDelko.setText(nbrItems[8]);
                        et_MiletChwalBourik.setText(nbrItems[9]);
                        et_BefVach.setText(nbrItems[10]);
                        et_KochonKabrit.setText(nbrItems[11]);
                        et_BetVolayPoulKok.setText(nbrItems[12]);
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_NBR_GARCON_ET_FILLE_13
                case Constant.TYPE_QUESTION_NBR_GARCON_ET_FILLE_13:
                    if (!codeReponse.trim().equalsIgnoreCase("")) {
                        et_Gason.setText(Constant.STRING_VIDE);
                        et_Fi.setText(Constant.STRING_VIDE);
                        String[] nbrItems = codeReponse.split("-");

                        pattern = Pattern.compile("^([0-9]+)$");
                        matcher = pattern.matcher(nbrItems[0].toString());
                        if (matcher.matches()) {
                            et_Gason.setText(nbrItems[0]);
                        }
                        matcher = pattern.matcher(nbrItems[1].toString());
                        if (matcher.matches()) {
                            et_Fi.setText(nbrItems[1]);
                        }
                   /* if(  !nbrItems[0].equalsIgnoreCase("") ||  !nbrItems[0].equalsIgnoreCase("nu") || !nbrItems[0].equalsIgnoreCase("null") ) {
                        et_Gason.setText(nbrItems[0]);
                    }
                    if(  !nbrItems[1].equalsIgnoreCase("") ||  !nbrItems[1].equalsIgnoreCase("nu") || !nbrItems[1].equalsIgnoreCase("null") ) {
                        et_Fi.setText(nbrItems[1]);
                    }*/
                        //et_Gason.setText(nbrItems[0]);
                        //et_Fi.setText(nbrItems[1]);
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_14
                case Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_14:
                    if (!codeReponse.trim().equalsIgnoreCase("")) {
                        String[] nbrItems = codeReponse.split("-");

                        this.setReponse(sp_Reponse, "" + nbrItems[0], Constant.CLASSE_REPONSE_MODEL);
                        this.setReponse(sp_Reponse2, "" + nbrItems[1], Constant.CLASSE_REPONSE_MODEL);
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15
                case Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15:
                    if (!codeReponse.trim().equalsIgnoreCase("")) {
                        String[] nbrItems = codeReponse.split("-");
                        String codeReponse1 = nbrItems[0];
                        String codeReponse2 = nbrItems[1];
                        // NAP CHACHE REPONS PARAN AN POU NOU KA JWENN KOD PARAN AN
                         qReponseModel = formDataMngr.getQuestionResponsesByCodeQuestionByCodeReponseIsParent(codeQuestion, codeReponse1);
                        codeReponse = qReponseModel.getCodeReponse();
                         codeParent = qReponseModel.getCodeUniqueReponse();
                        //
                        if (!codeParent.trim().equalsIgnoreCase("")) {
                            this.setReponse(sp_Reponse, codeReponse1, Constant.CLASSE_REPONSE_MODEL);
                            this.Load_PossibiliteReponse_Child(context, formDataMngr, sp_Reponse2, codeParent);
                            this.codeReponseParentLast = codeParent;
                            this.IsLoadPossibiliteReponse_ChildAgain = false;
                            this.setReponse(sp_Reponse2, codeReponse2, Constant.CLASSE_REPONSE_MODEL);
                        } else {
                            this.setReponse(sp_Reponse, codeReponse1, Constant.CLASSE_REPONSE_MODEL);
                        }
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_AGE_16
                case Constant.TYPE_QUESTION_CHOIX_AGE_16:
                    this.setReponse(sp_Reponse, codeReponse, Constant.CLASSE_KEY_VALUE_MODEL);

                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_LISTE_BOX_17
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_17:
                    this.setReponse(recyclerViewReponse, codeReponse, Constant.CLASSE_REPONSE_MODEL);

                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_LISTE_BOX_PAYS_18
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_PAYS_18:
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_AGE_19:
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_DOMAINE_ETUDE_20:
                case Constant.TYPE_QUESTION_CHOIX_NUMBER_LISTE_BOX_22:
                    this.setReponse(recyclerViewReponse, codeReponse, Constant.CLASSE_KEY_VALUE_MODEL);
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_SAISIE_JOUR_SELECT_MOIS_SAISIE_ANNEE_23
                case Constant.TYPE_QUESTION_DATE_SAISIE_JOUR_SELECT_MOIS_SAISIE_ANNEE_23:
                    this.setReponseDate(et_Jour, sp_Mois2, et_Annee, codeReponse);
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_SELECT_MOIS_SAISIE_ANNEE_24
                case Constant.TYPE_QUESTION_DATE_SELECT_MOIS_SAISIE_ANNEE_24:
                    this.setReponseDate(sp_Mois2, et_Annee, codeReponse);
                    break;
                //endregion
            }

            if (this.TypeEvenement == Constant.ACTION_AFFICHER) {
                et_Reponse.setEnabled(false);
                sp_Reponse.setEnabled(false);
                sp_Reponse2.setEnabled(false);
                sp_Reponse3.setEnabled(false);
                sp_Jour.setEnabled(false);
                sp_Mois.setEnabled(false);
                sp_Annee.setEnabled(false);
                et_Jour.setEnabled(false);
                sp_Mois.setEnabled(false);
                et_Annee.setEnabled(false);

                et_ApareyRadyo.setEnabled(false);
                et_Televizyon.setEnabled(false);
                et_FrijideFrize.setEnabled(false);
                et_FouElektrikFouAkGaz.setEnabled(false);
                et_OdinatePCLaptopTabletNimerik.setEnabled(false);
                et_BisikletMotosiklet.setEnabled(false);
                et_VwatiMachin.setEnabled(false);
                et_KannotBato.setEnabled(false);
                et_InvetePanoSoleJeneratrisDelko.setEnabled(false);
                et_MiletChwalBourik.setEnabled(false);
                et_BefVach.setEnabled(false);
                et_KochonKabrit.setEnabled(false);
                et_BetVolayPoulKok.setEnabled(false);
                recyclerViewReponse.setEnabled(false);
            }
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception: SetValueTemp_InViewControl >> ", ex);
            ToastUtility.ToastMessage(context, "ERROR:" + ex.getMessage());
            ex.printStackTrace();
        }
    }//

    private void SetKey_Value( String NomChamps, String reponseValue) {
        try {
            FieldMapperUtils.mapField(NomChamps, reponseValue, this.data);
        } catch (Exception ex) {
            ToastUtility.LogCat( "Exception:INSIDE - SetKey_Value", ex);
            throw ex;
        }
    }

    @SuppressWarnings("deprecation")
    public static  void SetFieldValuesQuestionInfo(Context context, FormDataMngr formDataMngr, QuestionnaireFormulaireUtility qf
            , TextView tv_DetailsCategorie
            , TextView tv_SousDetailsCategorie
            , TextView tv_DetailQuestion
            , TextView tv_GrandTitre
            , Toolbar toolbar
            , TextView tv_LibeleQuestion
            , TextView tv_Commune
            , TextView tv_SectionCommune
            , RecyclerView recyclerViewReponse
            , LinearLayout LL_RecyclerView
            , RadioListAdapter radioListAdapter, OnItemClickListener getItemClickListener
            , RadioListAdapterKeyValue radioListAdapterKeyValue, RadioListAdapterKeyValue.OnItemClickListenerKeyValue getItemClickListenerKeyValue
            , EditText et_Reponse
            , Spinner sp_Reponse
            , RelativeLayout RelativeLayout_Reponse
            , Spinner sp_Reponse2
            , RelativeLayout RelativeLayout_Reponse2
            , Spinner sp_Reponse3
            , RelativeLayout RelativeLayout_Reponse3
            , LinearLayout LinearLDate
            , RelativeLayout RL_Jour
            , Spinner sp_Jour
            , Spinner sp_Mois
            , Spinner sp_Annee
            , LinearLayout LL_GaconEtFille, LinearLayout LL_AppareilEtAnimaux
            , TextView tv_Reponse, TextView tv_Gason, TextView tv_Fi
            , EditText et_Gason, EditText et_Fi , EditText et_ApareyRadyo, EditText et_Televizyon, EditText et_FrijideFrize
            , EditText et_FouElektrikFouAkGaz, EditText et_OdinatePCLaptopTabletNimerik, EditText et_BisikletMotosiklet, EditText et_VwatiMachin
            , EditText et_KannotBato, EditText et_InvetePanoSoleJeneratrisDelko, EditText et_MiletChwalBourik, EditText et_BefVach, EditText et_KochonKabrit
            , EditText et_BetVolayPoulKok
            , Button btn_Precedent
            , Button btn_Suivant
            , LinearLayout LinearLDate_SaisieJJ_SelectMM_SaisieAAAA
            , EditText et_Jour
            , Spinner sp_Mois2
            , EditText et_Annee) {
        try {
            if (qf.getCodeQuestion() == null && qf.getCodeQuestion().equals("")) {
                ToastUtility.ToastMessage(context, "Gen yon ti problèm nan fòmilè a..." + (char) 13 + " Eseye ankò tanpri");
                //finish();
            }
            tv_DetailsCategorie.setVisibility(View.GONE);
            tv_SousDetailsCategorie.setVisibility(View.GONE);
            tv_DetailQuestion.setVisibility(View.GONE);

            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ){
                tv_GrandTitre.setText(Html.fromHtml( qf.GrandTitre, Html.FROM_HTML_OPTION_USE_CSS_COLORS ) );
                tv_DetailsCategorie.setText( Html.fromHtml( qf.DetailsCategorie, Html.FROM_HTML_OPTION_USE_CSS_COLORS  ) );
                tv_SousDetailsCategorie.setText( Html.fromHtml( qf.SousDetailsCategorie, Html.FROM_HTML_OPTION_USE_CSS_COLORS  ) );
                tv_LibeleQuestion.setText( Html.fromHtml( qf.LibelleQuestion, Html.FROM_HTML_OPTION_USE_CSS_COLORS  ) );
                tv_DetailQuestion.setText( Html.fromHtml( qf.DetailsQuestion, Html.FROM_HTML_OPTION_USE_CSS_COLORS  ) );
            }else{
                tv_GrandTitre.setText( Html.fromHtml( qf.GrandTitre ) );
                tv_DetailsCategorie.setText( Html.fromHtml( qf.DetailsCategorie ) );
                tv_SousDetailsCategorie.setText( Html.fromHtml( qf.SousDetailsCategorie ) );
                tv_LibeleQuestion.setText( Html.fromHtml( qf.LibelleQuestion ) );
                tv_DetailQuestion.setText( Html.fromHtml( qf.DetailsQuestion ) );
            }


            if(!qf.DetailsCategorie.toString().trim().equals("")){
                tv_DetailsCategorie.setVisibility(View.VISIBLE);
            }
            if(!qf.SousDetailsCategorie.toString().trim().equals("")){
                tv_SousDetailsCategorie.setVisibility(View.VISIBLE);
            }

            if(!qf.DetailsQuestion.toString().trim().equals("")){
                tv_DetailQuestion.setVisibility(View.VISIBLE);
            }
            if (qf.getqPrecedent().toString().equals(Constant.DEBUT)) {
                btn_Precedent.setVisibility(View.INVISIBLE);
                //btn_Precedent.setText(context.getString(R.string.label_Quit));
                if( qf.getTbl_TableName() == Constant.FORMULAIRE_BATIMENT ){
                    btn_Precedent.setVisibility(View.VISIBLE);
                }
            }else {
                btn_Precedent.setText(context.getString(R.string.label_QuestionPrecedente));
                btn_Precedent.setVisibility(View.VISIBLE);
            }

            btn_Suivant.setVisibility(View.GONE);

            String result = "";

            tv_Commune.setVisibility(View.GONE);
            tv_SectionCommune.setVisibility(View.GONE);
            et_Reponse.setVisibility(View.GONE);
            sp_Reponse.setVisibility(View.GONE);
            RelativeLayout_Reponse.setVisibility(View.GONE);
            sp_Reponse2.setVisibility(View.GONE);
            RelativeLayout_Reponse2.setVisibility(View.GONE);
            sp_Reponse3.setVisibility(View.GONE);
            RelativeLayout_Reponse3.setVisibility(View.GONE);
            LinearLDate.setVisibility(View.GONE);
            RL_Jour.setVisibility(View.GONE);
            LinearLDate_SaisieJJ_SelectMM_SaisieAAAA.setVisibility(View.GONE);

            LL_GaconEtFille.setVisibility(View.GONE);
            LL_AppareilEtAnimaux.setVisibility(View.GONE);
            tv_Reponse.setVisibility(View.GONE);
            LL_RecyclerView.setVisibility(View.GONE);

            et_Reponse.setInputType(InputType.TYPE_CLASS_TEXT);

            ContrainteReponse contrainte = new ContrainteReponse();
            contrainte = qf.getContrainte();
            //typeQuestion = qf.getTypeQuestion();

            // Contrainte Directeur Louinel
            if( qf.getNomChamps().equalsIgnoreCase("") ){

            }
            if( qf.TypeEvenement == Constant.ACTION_NOUVEAU ){
                //btn_Suivant.setVisibility(View.VISIBLE);
            }
            if( qf.TypeEvenement == Constant.ACTION_AFFICHER ){
                btn_Suivant.setVisibility(View.VISIBLE);
            }
            et_Reponse.setFilters(new InputFilter[] {new InputFilter.LengthFilter(500000000)});
            //Afficher le clavier
            //final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            //inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            //inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            //inputMethodManager.hideSoftInputFromInputMethod(getWindow().getDecorView().getRootView().getWindowToken(), 0);

            //final InputMethodManager imm2 = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            //imm.hideSoftInputFromInputMethod(view.getWindowToken(), 0);

            //codeReponseRecyclerView=null; codeReponseKeyValueModel=null;
            switch (qf.getTypeQuestion()) {
                //region TYPE_QUESTION_CHOIX_1
                case Constant.TYPE_QUESTION_CHOIX_1:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();

                    // LISTE DES POSIBILITES DE REPONSES
                    qf.Load_PossibiliteReponse(context, formDataMngr,  sp_Reponse);
                    break;
                //endregion
                //region TYPE_QUESTION_SAISIE_2
                case Constant.TYPE_QUESTION_SAISIE_2:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    if (contrainte.getCaractereAccepte() == Constant.CHIFFRE) {
                        et_Reponse.setInputType(InputType.TYPE_CLASS_NUMBER);
                    }else if (contrainte.getCaractereAccepte() == Constant.CHIFFRE_LETTRE){
                        et_Reponse.setInputType(InputType.TYPE_CLASS_TEXT);
                    }
                    if (contrainte.getNombreCaratereMaximal() > 0 ) {
                        et_Reponse.setFilters(new InputFilter[] {new InputFilter.LengthFilter(contrainte.getNombreCaratereMaximal())});
                    }
                    et_Reponse.setVisibility(View.VISIBLE);
                    et_Reponse.requestFocus();
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_MM_AAAA_3
                case Constant.TYPE_QUESTION_DATE_MM_AAAA_3:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    LinearLDate.setVisibility(View.VISIBLE);
                    qf.Load_Mois(context, sp_Mois);
                    qf.Load_Annee(context, sp_Annee);
                    //et_Reponse.setInputType(InputType.TYPE_CLASS_DATETIME);
                    //sp_Mois.requestFocus();
                    break;
                //endregion
                //region TYPE_QUESTION_DEUX_CHOIX_4
                case Constant.TYPE_QUESTION_DEUX_CHOIX_4:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();
                    // LISTE DES POSIBILITES DE REPONSES
                    qf.Load_PossibiliteReponse(context, formDataMngr,  sp_Reponse);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_PAYS_5
                case Constant.TYPE_QUESTION_CHOIX_PAYS_5:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();

                    // LISTE DES PAYS
                    qf.Load_Pays( formDataMngr, sp_Reponse);
                    //qf.Load_Pays(context, formDataMngr, recyclerViewReponse, radioListAdapterKeyValue,  getItemClickListenerKeyValue);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMMUNE_6
                case Constant.TYPE_QUESTION_CHOIX_COMMUNE_6:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    tv_Reponse.setVisibility(View.VISIBLE);
                    tv_Reponse.setText(context.getString(R.string.label_Depatman));
                    sp_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();
                    tv_Commune.setVisibility(View.VISIBLE);
                    tv_Commune.setText(context.getString(R.string.label_Komin));
                    RelativeLayout_Reponse2.setVisibility(View.VISIBLE);
                    sp_Reponse2.setVisibility(View.VISIBLE);

                    // LISTE DES DEPARTEMENTS
                    qf.Load_Departement( formDataMngr, sp_Reponse);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7
                case Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    tv_Reponse.setVisibility(View.VISIBLE);
                    tv_Reponse.setText(context.getString(R.string.label_Depatman));
                    sp_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();
                    tv_Commune.setVisibility(View.VISIBLE);
                    sp_Reponse2.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse2.setVisibility(View.VISIBLE);
                    tv_SectionCommune.setVisibility(View.VISIBLE);
                    sp_Reponse3.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse3.setVisibility(View.VISIBLE);

                    // LISTE DES DEPARTEMENTS
                    qf.Load_Departement( formDataMngr, sp_Reponse);
                    qf.Load_ClearSpinner(sp_Reponse2, Constant.TYPE_QUESTION_CHOIX_COMMUNE_6);
                    qf.Load_ClearSpinner(sp_Reponse3, Constant.TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_DOMAINE_ETUDE_8
                case Constant.TYPE_QUESTION_CHOIX_DOMAINE_ETUDE_8:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();

                    qf.Load_DomaineEtude( formDataMngr, sp_Reponse);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_INDIVIDU_9
                case Constant.TYPE_QUESTION_CHOIX_INDIVIDU_9:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();

                    if (Constant.FORMULAIRE_EVALUATION_MENAGE == qf.getTbl_TableName()){
                        //Load_IndividuEvaluation(sp_Reponse);
                    }else{
                        //Load_Individu(sp_Reponse);
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_JJ_MM_AAAA_11
                case Constant.TYPE_QUESTION_DATE_JJ_MM_AAAA_11:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    RL_Jour.setVisibility(View.VISIBLE);
                    LinearLDate.setVisibility(View.VISIBLE);
                    qf.Load_Jour(context, sp_Jour);
                    qf.Load_Mois(context, sp_Mois);
                    qf.Load_Annee(context, sp_Annee);
                    //et_Reponse.setInputType(InputType.TYPE_CLASS_DATETIME);
                    //sp_Mois.requestFocus();
                    break;
                //endregion
                //region TYPE_QUESTION_NBR_APPAREIL_ET_ANIMAUX_12
                case Constant.TYPE_QUESTION_NBR_APPAREIL_ET_ANIMAUX_12:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    LL_AppareilEtAnimaux.setVisibility(View.VISIBLE);

                    break;
                //endregion
                //region TYPE_QUESTION_NBR_GARCON_ET_FILLE_13
                case Constant.TYPE_QUESTION_NBR_GARCON_ET_FILLE_13:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    LL_GaconEtFille.setVisibility(View.VISIBLE);

                    if( qf.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_COLLECTIF ){
                        // Verifye si se nan lojman kolekti epi afiche label Gason e fi pou chak chan yo
                        //Pou LC1B	Di konbyen fi, konbyen gason kap viv nan tip lojman ki tcheke yo // Qlc1bTotalGarconEtFille
                        if( qf.getNomChamps().equalsIgnoreCase( Constant.Qlc1bTotalGarconEtFille ) ){//
                            tv_Gason.setText(context.getString(R.string.label_Gason));
                            et_Gason.setHint(context.getString(R.string.label_Gason));
                            tv_Fi.setText(context.getString(R.string.label_Fi));
                            et_Fi.setHint(context.getString(R.string.label_Fi));
                        }
                    }
                    if( qf.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL ){
                        // LIN6	Konbyen pyès lojman sa a genyen? (watè, koulwa ak galeri pa ladann) Et Nan pyès sa yo nan konbyen ki se chanm pou domi sèlman ?
                        // Si pa gen okenn chanm pou moun domi ekri 00
                        if( qf.getNomChamps().equalsIgnoreCase( Constant.Qlin6NombrePieceETChambreACoucher ) ){//
                            tv_Gason.setText(context.getString(R.string.label_KantitePyesAntou) );
                            et_Gason.setHint(context.getString(R.string.label_KantitePyesAntou));
                            tv_Fi.setText(context.getString(R.string.label_KantiteChanmPouDomi));
                            et_Fi.setHint(context.getString(R.string.label_KantiteChanmPouDomi));
                        }
                    }
                    if( qf.getTbl_TableName() == Constant.FORMULAIRE_MENAGE ) {
                        // Verifye si se nan menaj epi afiche label Gason e fi pou chak chan yo
                        //Pou M10.1	Nan 6 mwa ki sot pase la yo, èske menaj la te peye yon moun kom anplwaye pèmanan pou fè travay
                        // (netwaye kay, okipe timoun/granmoun, lave, pase, fè manje, veye kay...) Nan kay pou li ?
                        if (qf.getNomChamps().equalsIgnoreCase( Constant.Qm10TotalDomestiqueGaconEtFille)) {//
                            tv_Gason.setText(context.getString(R.string.label_Konbyen) + " " + context.getString(R.string.label_Gason));
                            et_Gason.setHint(context.getString(R.string.label_Konbyen) + " " + context.getString(R.string.label_Gason));
                            tv_Fi.setText(context.getString(R.string.label_Konbyen) + " " + context.getString(R.string.label_Fi));
                            et_Fi.setHint(context.getString(R.string.label_Konbyen) + " " + context.getString(R.string.label_Fi));
                        }
                    }
                    if( qf.getTbl_TableName() == Constant.FORMULAIRE_INDIVIDUS ) {
                        // Verifye si se nan endividi epi afiche label Gason e fi pou chak chan yo
                        //Pou F2	Konbyen nan pitit sa yo ki vivan toujou?
                        if (qf.getNomChamps().equalsIgnoreCase( Constant.Qf1NbreEnfantNeVivantGarconEtFille )) {
                            tv_Gason.setText("F1A. " + context.getString(R.string.label_Piti) + " " + context.getString(R.string.label_Gason));
                            et_Gason.setHint(context.getString(R.string.label_Piti) + " " + context.getString(R.string.label_Gason));
                            tv_Fi.setText("F1B. " + context.getString(R.string.label_Piti) + " " + context.getString(R.string.label_Fi));
                            et_Fi.setHint(context.getString(R.string.label_Piti) + " " + context.getString(R.string.label_Fi));
                        }
                        //Pou F1	Konbyen pitit antou {0} fe tou vivan?
                        if (qf.getNomChamps().equalsIgnoreCase(Constant.Qf2NbrEnfantVivantGarconEtFille)) {
                            tv_Gason.setText("F2A. " + context.getString(R.string.label_Piti) + " " + context.getString(R.string.label_Gason));
                            et_Gason.setHint(context.getString(R.string.label_Piti) + " " + context.getString(R.string.label_Gason));
                            tv_Fi.setText("F2B. " + context.getString(R.string.label_Piti) + " " + context.getString(R.string.label_Fi));
                            et_Fi.setHint(context.getString(R.string.label_Piti) + " " + context.getString(R.string.label_Fi));
                        }
                    }
                    if (contrainte.getNombreCaratereMaximal() > 0 ) {
                        et_Gason.setFilters(new InputFilter[] {new InputFilter.LengthFilter(contrainte.getNombreCaratereMaximal())});
                        et_Fi.setFilters(new InputFilter[] {new InputFilter.LengthFilter(contrainte.getNombreCaratereMaximal())});
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_14
                case Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_14:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    tv_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();
                    tv_Commune.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse2.setVisibility(View.VISIBLE);
                    sp_Reponse2.setVisibility(View.VISIBLE);

                    if( qf.getTbl_TableName() == Constant.FORMULAIRE_MENAGE ) {
                        // Pou M4	Sa menaj la plis fe pou jwenn dlo pou bwè/...pou fè lót bagay, tankou: pou nou lave, netwaye kay, lave rad, eks.
                        // Si gen plizyè posibilite, bay sa ki pi enpòtan an
                        if (qf.getNomChamps().equalsIgnoreCase(Constant.Qm4ModeAprobEauBoireEtUsageCourant)) {//
                            tv_Reponse.setText( "M4.1 " + context.getString(R.string.label_M4_1DloPouBwe) );
                            tv_Commune.setText( "M4.2 " +  context.getString(R.string.label_M4_2DloPouFeLotIzaj) );

                            // LISTE DES POSIBILITES DE REPONSES 1
                            qf.Load_PossibiliteReponse(context, formDataMngr,  sp_Reponse);
                            // LISTE DES POSIBILITES DE REPONSES 2
                            qf.Load_PossibiliteReponse(context, formDataMngr,  sp_Reponse2);
                        }

                        // Pou M5	Ak ki de (2) prinsipal sous enerji menaj la pi plis sevi pou kwit manje ? (komanse ak sa ki pi enpotan an)
                        if (qf.getNomChamps().equalsIgnoreCase(Constant.Qm5SrcEnergieCuison1Et2)) {//
                            tv_Reponse.setText( context.getString(R.string.label_1erSousEnejiPouKwitManje) );
                            tv_Commune.setText( context.getString(R.string.label_2emSousEnejiPouKwitManje) );

                            // LISTE DES POSIBILITES DE REPONSES 1
                            qf.Load_PossibiliteReponse(context, formDataMngr,  sp_Reponse);
                            // LISTE DES POSIBILITES DE REPONSES 2
                            qf.Load_PossibiliteReponse(context, formDataMngr,  sp_Reponse2);
                        }
                    }
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15
                case Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();

                    if( qf.getTbl_TableName() == Constant.FORMULAIRE_INDIVIDUS ) {
                        // Pou E4A	Ki pi wo nivo {0} te rive nan lekol / inivesite?
                        if (qf.getNomChamps().equalsIgnoreCase(Constant.Qe4ANiveauEtudeETClasse)) {//
                            tv_Reponse.setVisibility(View.VISIBLE);
                            tv_Reponse.setText( context.getString(R.string.label_E4B_Nivo) );
                            tv_Commune.setText( context.getString(R.string.label_E4B_KlasAneDetid) );
                        }
                    }
                    // LISTE DES POSIBILITES DE REPONSES
                    qf.Load_PossibiliteReponse(context, formDataMngr,  sp_Reponse);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_AGE_16
                case Constant.TYPE_QUESTION_CHOIX_AGE_16:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();

                    qf.Load_Age(context, sp_Reponse);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_LISTE_BOX_17
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_17:
                    LL_RecyclerView.setVisibility(View.VISIBLE);

                    // LISTE DES POSIBILITES DE REPONSES
                    qf.Load_PossibiliteReponse(context, formDataMngr, recyclerViewReponse, radioListAdapter,  getItemClickListener);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_LISTE_BOX_PAYS_18
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_PAYS_18:
                    LL_RecyclerView.setVisibility(View.VISIBLE);
                    // LISTE DES PAYS
                    qf.Load_Pays(context, formDataMngr, recyclerViewReponse, radioListAdapterKeyValue,  getItemClickListenerKeyValue);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_LISTE_BOX_AGE_19
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_AGE_19:
                    LL_RecyclerView.setVisibility(View.VISIBLE);

                    qf.Load_Age(context, recyclerViewReponse, radioListAdapterKeyValue,  getItemClickListenerKeyValue);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_LISTE_BOX_DOMAINE_ETUDE_20
                case Constant.TYPE_QUESTION_CHOIX_LISTE_BOX_DOMAINE_ETUDE_20:
                    LL_RecyclerView.setVisibility(View.VISIBLE);

                    qf.Load_DomaineEtude(context, formDataMngr, recyclerViewReponse, radioListAdapterKeyValue,  getItemClickListenerKeyValue);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_COMBO1_ET_LISTE_BOX_LIER_21
                case Constant.TYPE_QUESTION_CHOIX_COMBO1_ET_LISTE_BOX_LIER_21:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    sp_Reponse.setVisibility(View.VISIBLE);
                    RelativeLayout_Reponse.setVisibility(View.VISIBLE);
                    sp_Reponse.requestFocus();

                    // LISTE DES POSIBILITES DE REPONSES
                    qf.Load_PossibiliteReponse(context, formDataMngr,  sp_Reponse);
                    break;
                //endregion
                //region TYPE_QUESTION_CHOIX_NUMBER_LISTE_BOX_22
                case Constant.TYPE_QUESTION_CHOIX_NUMBER_LISTE_BOX_22:
                    LL_RecyclerView.setVisibility(View.VISIBLE);

                    int chiffeDeDepart=1;
                    int nombreLimite = contrainte.getNombreCaratereMaximal();
                    if (nombreLimite == 1) {
                        nombreLimite = 9;
                    } else if (nombreLimite == 2){
                        nombreLimite = 99;
                    }else  if( nombreLimite == 3 ) {
                        nombreLimite = 999;
                    }else  if( nombreLimite == 4 ) {
                        nombreLimite = 9999;
                    }
                    String textAdditionnel="";
                    if( qf.getTbl_TableName() == Constant.FORMULAIRE_BATIMENT ){
                        if (qf.getNomChamps().equalsIgnoreCase( BatimentDao.Properties.Qb4NbreLogeIndividuel.columnName )) {
                            textAdditionnel =" Lojman Endividyèl";
                            BatimentModel batimentModel = (BatimentModel) qf.getData();
                            if( batimentModel!=null){
                                if( batimentModel.getQb4NbreLogeIndividuel()!=null && batimentModel.getQb4NbreLogeIndividuel() > 0 ){
                                    chiffeDeDepart = 0;
                                }
                            }
                        }
                    }else  if( qf.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_COLLECTIF ){
                        /*if (qf.getNomChamps().equalsIgnoreCase( LogementDao.Properties.QlcTotalIndividus.columnName )) {

                            textAdditionnel =" Moun";
                        }*/
                    }else  if( qf.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL ){
                        if (qf.getNomChamps().equalsIgnoreCase( LogementDao.Properties.Qlin5NbreTotalMenage.columnName )) {
                            textAdditionnel =" Menaj";
                            chiffeDeDepart = 2;
                        }
                    }else  if( qf.getTbl_TableName() == Constant.FORMULAIRE_MENAGE ){
                        if (qf.getNomChamps().equalsIgnoreCase( MenageDao.Properties.Qm2TotalIndividuVivant.columnName )) {
                            textAdditionnel =" Moun";
                        }/*else if (qf.getNomChamps().equalsIgnoreCase( MenageDao.Properties.Qn1NbreEmigre.columnName )) {
                            textAdditionnel =" Emigre";
                        }else if (qf.getNomChamps().equalsIgnoreCase( MenageDao.Properties.Qd1NbreDecede.columnName )) {
                            textAdditionnel =" Moun Mouri";
                        }*/
                    }

                    qf.Load_Quantite(context, chiffeDeDepart, nombreLimite, textAdditionnel, recyclerViewReponse, radioListAdapterKeyValue,  getItemClickListenerKeyValue);
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_SAISIE_JOUR_SELECT_MOIS_SAISIE_ANNEE_23
                case Constant.TYPE_QUESTION_DATE_SAISIE_JOUR_SELECT_MOIS_SAISIE_ANNEE_23:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    LinearLDate_SaisieJJ_SelectMM_SaisieAAAA.setVisibility(View.VISIBLE);
                    qf.Load_Mois(context, sp_Mois2);
                    //et_Reponse.setInputType(InputType.TYPE_CLASS_DATETIME);
                    et_Jour.requestFocus();
                    break;
                //endregion
                //region TYPE_QUESTION_DATE_SELECT_MOIS_SAISIE_ANNEE_24
                case Constant.TYPE_QUESTION_DATE_SELECT_MOIS_SAISIE_ANNEE_24:
                    btn_Suivant.setVisibility(View.VISIBLE);
                    LinearLDate_SaisieJJ_SelectMM_SaisieAAAA.setVisibility(View.VISIBLE);
                    qf.Load_Mois(context, sp_Mois2);
                    et_Jour.setVisibility(View.GONE);
                    break;
                //endregion
                default:
            }
            qf.SetButtonLabel(context, "", qf, btn_Suivant);

        } catch (Exception ex) {
            ToastUtility.LogCat( "Exception:INSIDE - SetFieldValuesQuestionInfo", ex);
            ex.printStackTrace();
        }
    }

    public static void SetButtonLabel(Context context, String codeReponse, QuestionnaireFormulaireUtility qf, Button btn_Suivant) {
        String module = "";
        btn_Suivant.setText(context.getString(R.string.label_menu_Kontinye));

        if( qf.getTbl_TableName() == Constant.FORMULAIRE_BATIMENT ) {
            BatimentModel batimentModel = (BatimentModel) qf.data;
            String QSuivant_Val = "";
            try {
                QSuivant_Val = BatimentModel.Check_ContrainteSautChampsValeur(qf.nomChamps, batimentModel, qf.iDKeys, qf.dataBase, false);
            } catch (TextEmptyException e) {
                e.printStackTrace();
            }
            if (!QSuivant_Val.equalsIgnoreCase("")) {
                qf.qSuivant = QSuivant_Val;
            }
        }else if( qf.getTbl_TableName() == Constant.FORMULAIRE_INDIVIDUS ) {
            IndividuModel individuModel = (IndividuModel) qf.data;
            String QSuivant_Val = "";
            //try {
                //QSuivant_Val = IndividuModel.Check_ContrainteSautChampsValeur(qf.nomChamps, individuModel, qf.iDKeys, qf.dataBase, false);
            //} catch (TextEmptyException e) {
            //    e.printStackTrace();
            //}
            if (!QSuivant_Val.equalsIgnoreCase("")) {
                qf.qSuivant = QSuivant_Val;
            }
        }

        if (qf.getqSuivant().toString().equals(Constant.FIN)) {
            btn_Suivant.setText(context.getString(R.string.label_QuestionFINI) );//+" " + module + " ");
        }
        SetButtonLabelforModule(context, codeReponse, qf, btn_Suivant);
    }

    public static void SetButtonLabelforModule(Context context, String codeReponse, QuestionnaireFormulaireUtility qf, Button btn_suivant) {
        /*if( qf.getTbl_TableName() == Constant.FORMULAIRE_BATIMENT ) {
            if (qf.nomChamps.equalsIgnoreCase(BatimentDao.Properties.Qb7Utilisation2.columnName)){
                if ( qf.getBatimentModel().getQb7Utilisation1()!= null && qf.getBatimentModel().getQb7Utilisation1() >= 20 ) {
                    if ( codeReponse.equalsIgnoreCase(""+Constant.PA_GEN_LOT_ITILIZASYON) ) { // SI PA GENYEN YON DEZYEM ITLIZASYON
                        btn_suivant.setText(context.getString(R.string.label_QuestionFINI));

                    }else  if ( Integer.parseInt(codeReponse) >= 20 ) { // SI GEN YON LOT ITLIZASYON
                        btn_suivant.setText(context.getString(R.string.label_QuestionFINI));
                    }
                }
            }else  if (qf.nomChamps.equalsIgnoreCase(BatimentDao.Properties.Qb8NbreLogeIndividuel.columnName)){
                //if ( Integer.parseInt(codeReponse) <= 0 ) {
                //    btn_suivant.setText(context.getString(R.string.label_QuestionFINI));
                //}
            }
        }*/
        if( qf.getTbl_TableName() == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL ) {
            if ( codeReponse.equalsIgnoreCase(""+Constant.PA_GEN_LOT_ITILIZASYON) ) {

            }
        }
    }
    //endregion

    //region "SAVE INFO"queryRecordMngr

    public void SaveInfoRapportRAR(CURecordMngr cuRecordMngr, RapportRARModel rapportRARModel) throws TextEmptyException{
        try {
            RapportRARModel ind = cuRecordMngr.saveRapportRAR( rapportRARModel);
        } catch (ManagerException e) {
            try {
                throw e;
            } catch (ManagerException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void SaveInfoIndividu(CURecordMngr cuRecordMngr, long idIndividu, IndividuModel individuModel) throws TextEmptyException {
        try {
            Shared_Preferences infoUser = Tools.SharedPreferences(context);
            if (infoUser != null && infoUser.getProfileId() != null) {
                nomUtilisateur = infoUser.getNomUtilisateur();
            }
            IndividuModel ind = cuRecordMngr.SaveIndividu(idIndividu, individuModel, Constant.ACTION_MOFIDIER, nomUtilisateur);
        } catch (ManagerException e) {
            try {
                throw e;
            } catch (ManagerException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void SaveInfoAncienMembre(CURecordMngr cuRecordMngr, long idIndividu, AncienMembreModel individuModel) throws TextEmptyException {
        try {
            Shared_Preferences infoUser = Tools.SharedPreferences(context);
            if (infoUser != null && infoUser.getProfileId() != null) {
                nomUtilisateur = infoUser.getNomUtilisateur();
            }
            AncienMembreModel ind = cuRecordMngr.SaveAncienMembre(idIndividu, individuModel, Constant.ACTION_MOFIDIER, nomUtilisateur);
        } catch (ManagerException e) {
            try {
                throw e;
            } catch (ManagerException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void SaveInfoDefinitivement(CURecordMngr cuRecordMngr, boolean isFinFormulaire) throws Exception {
        try {
            String statutFormulaire = GetStatutFormulaire();
            String isFillAllField = Constant.NON_0;
            String dateFinCollecte = "";

            Shared_Preferences infoUser = Tools.SharedPreferences(context);
            if (infoUser != null && infoUser.getProfileId() != null) {
                nomUtilisateur = infoUser.getNomUtilisateur();
            }
            if(isFinFormulaire){
                isFillAllField = Constant.OUI_1;
                dateFinCollecte = Tools.getDateString_MMddyyyy_HHmmss_a();
                if (tbl_TableName == Constant.FORMULAIRE_INDIVIDUS
                        || tbl_TableName == Constant.FORMULAIRE_EMIGRE
                        || tbl_TableName == Constant.FORMULAIRE_DECES)
                {
                    statutFormulaire = "" +  Constant.STATUT_MODULE_KI_FINI_1;
                }
                SetKey_Value( MenageDao.Properties.DateFinCollecte.columnName, "" + dateFinCollecte);
            }else{
                statutFormulaire = "" + Constant.STATUT_MODULE_KI_PA_FINI_3;
            }
            if (tbl_TableName != Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL) {
                //if (this.getqSuivant().toString().equalsIgnoreCase(Constant.FIN)) {
                //    isFillAllField = Constant.OUI_1;
                //}
            }
            this.SetKey_Value( MenageDao.Properties.Statut.columnName, statutFormulaire);
            this.SetKey_Value( MenageDao.Properties.IsFieldAllFilled.columnName, isFillAllField);
            this.SetKey_Value( MenageDao.Properties.DureeSaisie.columnName, "" + getDureeSaisie(dateFinCollecte));

            //if (tbl_TableName == Constant.FORMULAIRE_DECES || tbl_TableName == Constant.FORMULAIRE_EMIGRE){
            //    SetKey_Value("dateFinCollecte", "" + dateFinCollecte);
            //}
            // SAVE BATIMENT
            if (this.tbl_TableName == Constant.FORMULAIRE_BATIMENT) {
                BatimentModel batimentModel = (BatimentModel) this.data;
                this.batimentModel = cuRecordMngr.SaveBatiment(this.iDKeys, batimentModel, this.TypeEvenement, nomUtilisateur );
                this.iDKeys =  this.batimentModel.getBatimentId();
                dataBase = batimentModel;
            }
            // SAVE LOGEMENT
            if ( this.tbl_TableName == Constant.FORMULAIRE_LOGEMENT_COLLECTIF ||
                    this.tbl_TableName == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL ) {
                LogementModel logementModel = (LogementModel) this.data;
                this.logementModel = cuRecordMngr.SaveLogement(this.iDKeys, logementModel, this.TypeEvenement, nomUtilisateur );
                this.iDKeys = this.logementModel.getLogeId();
                dataBase = logementModel;
            }
            // SAVE MENAGE
            if ( this.tbl_TableName == Constant.FORMULAIRE_MENAGE ) {
                MenageModel menageModel = (MenageModel) this.data;
                this.menageModel = cuRecordMngr.SaveMenage(this.iDKeys, menageModel, this.TypeEvenement, nomUtilisateur );
                this.iDKeys = this.menageModel.getMenageId();
                dataBase = menageModel;
            }
            // SAVE EMIGRER
            /*if ( this.tbl_TableName == Constant.FORMULAIRE_EMIGRE ) {
                //ToastUtility.LogCat( "PREPARE TO SAVE FORMULAIRE_EMIGRE" );
                EmigreModel emigreModel = (EmigreModel) this.data;
                this.emigreModel = cuRecordMngr.SaveEmigre(this.iDKeys, emigreModel, this.TypeEvenement, nomUtilisateur );
                this.iDKeys =  this.emigreModel.getEmigreId();
                dataBase = emigreModel;
                //ToastUtility.LogCat( "AFTER Save emigreModel ID:" + emigreModel.getEmigreId() );
            }*/
            // SAVE DECES
           /* if ( this.tbl_TableName == Constant.FORMULAIRE_DECES ) {
                //ToastUtility.LogCat( "PREPARE TO SAVE FORMULAIRE_DECES" );
                DecesModel decesModel = (DecesModel) this.data;
                this.decesModel = cuRecordMngr.SaveDeces(this.iDKeys, decesModel, this.TypeEvenement, nomUtilisateur );
                this.iDKeys =  this.decesModel.getDecesId();
                dataBase = decesModel;
                //ToastUtility.LogCat("AFTER Save decesModel ID:" + decesModel.getDecesId() );
            }*/
            // SAVE INdividu
            if ( this.tbl_TableName == Constant.FORMULAIRE_INDIVIDUS ) {
                //ToastUtility.LogCat( "PREPARE TO SAVE FORMULAIRE_INDIVIDUS" );
                IndividuModel individuModel = (IndividuModel) this.data;
                this.individuModel = cuRecordMngr.SaveIndividu(this.iDKeys, individuModel, this.TypeEvenement, nomUtilisateur );
                this.iDKeys =  this.individuModel.getIndividuId();
                dataBase = individuModel;
                //ToastUtility.LogCat( "AFTER Save individuModel ID:" + individuModel.getIndividuId() );
            }
        }catch (ManagerException ex) {
            ToastUtility.LogCat( "Exception: SaveInfoDefinitivement(): " , ex);
            throw ex;
        }catch (Exception ex) {
            ToastUtility.LogCat( "Exception: SaveInfoDefinitivement(): ", ex );
            throw ex;
        }
    }//

    private String GetStatutFormulaire(){
        try {
            String result = "" + Constant.STATUT_MODULE_KI_PA_FINI_3;
            if (tbl_TableName == Constant.FORMULAIRE_BATIMENT) {
                BatimentModel b = (BatimentModel) this.data;
                if (b.getQb1Etat() != null && b.getQb1Etat() == Constant.BATIMAN_PA_KA_WE_5  ) {
                    return "" + Constant.STATUT_MODULE_KI_FINI_1;
                }else if (b.getQb3StatutOccupation() != null &&  b.getQb3StatutOccupation()== Constant.BATIMAN_OKIPE_1 ) {
                    return "" + Constant.STATUT_MODULE_KI_PA_FINI_3;
                }else if (b.getQb3StatutOccupation() != null &&  b.getQb3StatutOccupation() == Constant.BATIMAN_TOUJOU_VID_2  ) {
                    return "" + Constant.STATUT_MODULE_KI_FINI_1;
                }
            }
            if (tbl_TableName == Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL){
                LogementModel log = (LogementModel)this.data;
                if ( log.getQlin2StatutOccupation() != null && log.getQlin2StatutOccupation() == Constant.LOJMAN_OKIPE_TOUTAN_MEN_MOUN_YO_PA_LA_2){
                    /* Permettre à l'Agent Recenseur d’effectuer au moins trois (3) tentatives avant de mettre fin au questionnaire dans le cas où LIN.2 = 2. */
                    if(this.dataBase != null){
                        log = (LogementModel)this.dataBase;
                    }
                    int NbrTentative = 1;
                    if( log.getNbrTentative() != null && log.getNbrTentative() > 1 )
                        NbrTentative = log.getNbrTentative();

                    if( NbrTentative < 3 ){
                        ToastUtility.ToastMessage( context, "Tantativ: " + NbrTentative, Constant.GravityCenter);
                        NbrTentative = NbrTentative + 1;
                        this.SetKey_Value("NbrTentative", ""+NbrTentative);
                        result = "" + Constant.STATUT_MODULE_KI_PA_FINI_3;
                    }else if( NbrTentative == 3 ){
                        this.SetKey_Value("NbrTentative", "1");
                        ToastUtility.ToastMessage( context, "Dènye Tantativ: " + NbrTentative, Constant.GravityCenter);
                        result = "" + Constant.STATUT_MODULE_KI_FINI_1;
                    }
                    return result;
                }
                /*if ( (log.getQlin2StatutOccupation() != null && log.getQlin2StatutOccupation() == Constant.LOJMAN_OKIPE_TOUTAN_MEN_MOUN_YO_PA_LA_2)
                        || log.getQlin2StatutOccupation() == Constant.LOJMAN_VID_YON_LE_KONSA_3
                        || log.getQlin2StatutOccupation() == Constant.LOJMAN_TOUJOU_VID_4){
                    return "" + Constant.STATUT_MODULE_KI_FINI_1;
                }*/
            }
            if (tbl_TableName == Constant.FORMULAIRE_LOGEMENT_COLLECTIF){
                /*LogementModel b = (LogementModel)this.data;
                if (b.getQlc1TypeLogement() != null && b.getQlc1TypeLogement() > Constant.LCOL_Lazil_oswa_kote_granmoun_rete_oswa_kay_retrèt_6){
                    return "" + Constant.STATUT_MODULE_KI_FINI_1;
                }*/
            }
            if (tbl_TableName == Constant.FORMULAIRE_MENAGE){
                MenageModel b = (MenageModel) this.data;
                //if (b.Qlin3Occupation == ContrainteReponse.LOJMAN_TOUJOU_VID){
                //    return ContrainteReponse.FOMILE_KI_FINI;
                //}
            }
        }catch (Exception ex) {
            ToastUtility.LogCat( "Exception: GetStatutFormulaire(): ", ex);
        }
        return "" + Constant.STATUT_MODULE_KI_PA_FINI_3;
    }//
    //endregion

    public static void  getDateInfo(Context context) {
        Calendar mydate = new GregorianCalendar();
        //Calendar now = Calendar.getInstance();
        //String mystring = new Da6te().toString();//"01-27-2010";
        //Date thedate = new Dat6e();//null;
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

    //region SET STATUT
    public int SetStatutMenage(QueryRecordMngr queryRecordMngr, CURecordMngr cuRecordMngr) {
        int statut = Constant.STATUT_PA_FIN_RANPLI_22;
        try {
            boolean IsStatutMenageFini = true;
            int statutFormulaire = Constant.STATUT_MODULE_KI_FINI_1;
            boolean isFillAllField = true;
            //****************************************//
            // ********* CALCUL POUR EMIGRE **********//
            //****************************************//
            /*int NbreTotalEmigre = 0;
            if (this.getMenageModel().getQn1Emigration() != null &&
                    this.getMenageModel().getQn1Emigration() == Constant.REPONS_WI_1) {
                if (this.getMenageModel().getQn1NbreEmigre() != null && this.getMenageModel().getQn1NbreEmigre() > 0) {
                    NbreTotalEmigre = this.getMenageModel().getQn1NbreEmigre();
                }
            }*/

            /*if (NbreTotalEmigre > 0) {
                long nbreTotalEmigre_FiniEtBienRemplit = queryRecordMngr.countAncienMembre_AllFilled_ByMenage_ByStatus(this.getMenageModel().getMenageId(), statutFormulaire, isFillAllField);
                if (NbreTotalEmigre != nbreTotalEmigre_FiniEtBienRemplit) {
                    IsStatutMenageFini = false;
                }
            }*/
            //****************************************//
            //*********  CALCUL POUR DECES  **********//
            //****************************************//
            /*int NbreTotalDeces = 0;
            if ( this.getMenageModel().getQd1Deces() != null &&
                    this.getMenageModel().getQd1Deces() == Constant.REPONS_WI_1) {
                if (this.getMenageModel().getQd1NbreDecede() != null && this.getMenageModel().getQd1NbreDecede() > 0) {
                    NbreTotalDeces = this.getMenageModel().getQd1NbreDecede();
                }
                if (NbreTotalDeces > 0) {
                    long nbreTotalDeces_FiniEtBienRemplit = queryRecordMngr.countDeces_AllFilled_ByMenage_ByStatus(this.getMenageModel().getMenageId(), statutFormulaire, isFillAllField);
                    if (NbreTotalDeces != nbreTotalDeces_FiniEtBienRemplit) {
                        IsStatutMenageFini = false;
                    }
                }
            }*/
            //****************************************//
            //*******  CALCUL POUR INDIVIDUS  ********//
            //****************************************//
            int Nbre_TotalIndividu = this.getMenageModel().getQm2TotalIndividuVivant();
            if (Nbre_TotalIndividu > 0) {
                long NbreTotalIndividu_FiniEtBienRemplit = queryRecordMngr.countIndividus_AllFilled_ByMenage_ByStatus(this.getMenageModel().getMenageId(), statutFormulaire, isFillAllField);
                if (Nbre_TotalIndividu != NbreTotalIndividu_FiniEtBienRemplit) {
                    IsStatutMenageFini = false;
                }
            }

            // SI TOUT CE PASSE TES BIEN ON PASSE LE STATUT DU MENAGE A FINI\
            if (IsStatutMenageFini) {
                MenageModel menageModel = this.menageModel;
                // SI tous les champs du Menage sont bien remplit
                if (menageModel.getIsFieldAllFilled() != null && menageModel.getIsFieldAllFilled()) {
                    menageModel.setStatut((short) statutFormulaire);
                    this.menageModel = cuRecordMngr.SaveMenage(this.iDKeys, menageModel, this.TypeEvenement, nomUtilisateur );
                    statut = Constant.STATUT_RANPLI_NET_11;
                    this.iDKeys = this.menageModel.getMenageId();
                    ToastUtility.LogCat("AFTER Save menageModel ID:" + menageModel.getMenageId());
                }
            }
            return statut;
        } catch (Exception ex) {
            ToastUtility.LogCat("Exception: SetStatutMenage(): ", ex);
            Tools.AlertDialogMsg(context, ex.getMessage(), "E");
            ex.printStackTrace();
        }
        return statut;
    }

    public int SetStatutLogement(QueryRecordMngr queryRecordMngr, CURecordMngr cuRecordMngr, int typeLogement) {
        int statut = Constant.STATUT_PA_FIN_RANPLI_22;
        try{
            boolean IsStatutLogementFini = true;
           int statutFormulaire = Constant.STATUT_MODULE_KI_FINI_1;
            boolean isFillAllField = true;
            //****************************************************//
            //********** CALCUL POUR LOGEMENT COLLECTIF **********//
            //****************************************************//

            if(typeLogement == Constant.LOJ_KOLEKTIF){
                //region LOGEMENT COLLECTIF
                /*******  CALCUL POUR INDIVIDUS DANS LE LOGEMENT COLLECTIF  ********//*
                int Nbre_TotalIndividu = 0;//this.getLogementModel().getQlcTotalIndividus();
                if( this.getLogementModel().getQlc1TypeLogement() != null && this.getLogementModel().getQlc1TypeLogement() <= Constant.LCOL_Lazil_oswa_kote_granmoun_rete_oswa_kay_retrèt_6 ){
                    if (this.getLogementModel().getQlcTotalIndividus() != null && this.getLogementModel().getQlcTotalIndividus() > 0) {
                        Nbre_TotalIndividu = this.getLogementModel().getQlcTotalIndividus();
                    }
                }
                if (Nbre_TotalIndividu > 0) {
                    // On Calcul le nombre d'individu fini et bien remplit
                    long NbreTotalIndividu_FiniEtBienRemplit = queryRecordMngr.countIndividus_AllFilled_ByLogement_ByStatus( this.getLogementModel().getLogeId(), statutFormulaire, isFillAllField);
                    if ( Nbre_TotalIndividu != NbreTotalIndividu_FiniEtBienRemplit ){
                        IsStatutLogementFini=false;
                    }
                }*/
                //endregion
            }else if(typeLogement == Constant.LOJ_ENDIVIDYEL){
                //region LOGEMENT INDIVIDUEL
                /*******  CALCUL POUR MENAGE DANS LE LOGEMENT  ********/
                int nbre_TotalMenage = 0;
                if( this.getLogementModel().getQlin5NbreTotalMenage() != null ){
                    nbre_TotalMenage = this.getLogementModel().getQlin5NbreTotalMenage();
                }
                if ( this.getLogementModel().getQlin4IsHaveIndividuDepense() != null &&
                        this.getLogementModel().getQlin4IsHaveIndividuDepense() == Constant.REPONS_NON_2 ){
                    nbre_TotalMenage = 1;
                }
                // On verifie s'il existe de menage dans le logement Individuel
                if( nbre_TotalMenage > 0 ) {
                    long nbreTotalMenage_FiniEtBienRemplit = queryRecordMngr.countMenage_AllFilled_ByLogement_ByStatus(this.getLogementModel().getLogeId(), statutFormulaire, isFillAllField);
                    if ( nbre_TotalMenage != nbreTotalMenage_FiniEtBienRemplit ){
                        IsStatutLogementFini=false;
                    }
                }else if( logementModel.getQlin2StatutOccupation() != null && logementModel.getQlin2StatutOccupation() == Constant.LOJMAN_OKIPE_TOUTAN_MEN_MOUN_YO_PA_LA_2
                        && logementModel.getNbrTentative() != null && logementModel.getNbrTentative() < 4 ){
                    IsStatutLogementFini=false;
                    statut = Constant.STATUT_PA_FIN_RANPLI_22;
                }
                //endregion
            }

            // SI TOUT CE PASSE TRES BIEN ON PASSE LE STATUT DU LOGEMENT A FINI\
            if ( IsStatutLogementFini ) {
                LogementModel logementModel = this.logementModel;
                // SI tous les champs du logement sont bien remplit
                if ( logementModel.getIsFieldAllFilled() != null && logementModel.getIsFieldAllFilled() ) {
                    logementModel.setStatut((short) statutFormulaire);
                    this.logementModel = cuRecordMngr.SaveLogement(this.iDKeys, logementModel, this.TypeEvenement, nomUtilisateur );
                    statut = Constant.STATUT_RANPLI_NET_11;
                    if ( typeLogement == Constant.LOJ_KOLEKTIF ) {
                        //if ( this.logementModel.getQlc1TypeLogement() != null && this.logementModel.getQlc1TypeLogement() >= Constant.LCOL_Lazil_oswa_kote_granmoun_rete_oswa_kay_retrèt_6 ) {
                            //statut = Constant.STATUT_MODULE_KI_PA_FINI_3;
                        //}
                    }else{
                        if ( this.logementModel.getQlin2StatutOccupation() != null && this.logementModel.getQlin2StatutOccupation() == Constant.LOJMAN_OKIPE_YON_LE_KONSA_3 ||
                                this.logementModel.getQlin2StatutOccupation() == Constant.LOJMAN_PA_OKIPE_4 ) {
                            statut = Constant.STATUT_PA_FIN_RANPLI_22;
                        }
                    }
                }
            }
            return statut;
        }catch (Exception ex) {
            ToastUtility.LogCat( "Exception: SetStatutLogement(): ", ex);
            Tools.AlertDialogMsg(context, ex.getMessage(), "E");
            ex.printStackTrace();
        }
        return statut;
    }

    public int SetStatutLogementCollectif(QueryRecordMngr queryRecordMngr, CURecordMngr cuRecordMngr) {
        int statut = Constant.STATUT_MODULE_KI_PA_FINI_3;
        try{
            boolean IsStatutLogementFini = true;
            int statutFormulaire = Constant.STATUT_MODULE_KI_FINI_1;
            boolean isFillAllField = true;
            //****************************************************//
            //********** CALCUL POUR LOGEMENT COLLECTIF **********//
            //****************************************************//

            //*******  CALCUL POUR INDIVIDUS DANS LE LOGEMENT COLLECTIF  ********//
            int Nbre_TotalIndividu = 0;
           /* if( this.getLogementModel().getQlc1TypeLogement() != null && this.getLogementModel().getQlc1TypeLogement() <= Constant.LCOL_Lazil_oswa_kote_granmoun_rete_oswa_kay_retrèt_6 ){
                if (this.getLogementModel().getQlcTotalIndividus() != null && this.getLogementModel().getQlcTotalIndividus() > 0) {
                    Nbre_TotalIndividu = this.getLogementModel().getQlcTotalIndividus();
                }
            }*/
            if (Nbre_TotalIndividu > 0) {
                // On Calcul le nombre d'individu fini et bien remplit
                long NbreTotalIndividu_FiniEtBienRemplit = queryRecordMngr.countIndividus_AllFilled_ByLogement_ByStatus( this.getLogementModel().getLogeId(), statutFormulaire, isFillAllField);
                if ( Nbre_TotalIndividu != NbreTotalIndividu_FiniEtBienRemplit ){
                    IsStatutLogementFini=false;
                }
            }
            return statut;
        }catch (Exception ex) {
            ToastUtility.LogCat( "Exception: SetStatutLogementCollectif(): ", ex);
            Tools.AlertDialogMsg(context, ex.getMessage(), "E");
            ex.printStackTrace();
        }
        return statut;
    }

    public int SetStatutBatiment(QueryRecordMngr queryRecordMngr, CURecordMngr cuRecordMngr) {
        int statut = Constant.STATUT_PA_FIN_RANPLI_22;
        try{
            boolean IsStatutBatimentFini = true;
            statut = Constant.STATUT_PA_FIN_RANPLI_22;
            int statutFormulaire = Constant.STATUT_MODULE_KI_FINI_1;
            boolean isFillAllField = true;
            //****************************************************//
            //********** CALCUL POUR LOGEMENT COLLECTIF **********//
            //****************************************************//
            /*int Nbre_TotalLogeCollectif = this.getBatimentModel().getQb8NbreLogeCollectif();
            // On verifie s'il existe de logement Collectif dans le batiment
            if( Nbre_TotalLogeCollectif > 0 ) {
                // On Calcul le nombre de logement Collectif fini et bien remplit
                long NbreTotalLogeCollectif_FiniEtBienRemplit = queryRecordMngr.countLogement_AllFilled_ByBatiment_byTypeLog_ByStatus(this.getBatimentModel().getBatimentId(), Constant.LOJ_KOLEKTIF, statutFormulaire, isFillAllField);
                if ( Nbre_TotalLogeCollectif != NbreTotalLogeCollectif_FiniEtBienRemplit ){
                    IsStatutBatimentFini=false;
                }
            }*/
            int Nbre_TotalLogeIndividuel = this.getBatimentModel().getQb4NbreLogeIndividuel();
            // On verifie s'il existe de logement Individuel dans le batiment
            if( Nbre_TotalLogeIndividuel > 0 ) {
                // On Calcul le nombre de logement Individuel fini et bien remplit
                long NbreTotalLogeIndividuel_FiniEtBienRemplit = queryRecordMngr.countLogement_AllFilled_ByBatiment_byTypeLog_ByStatus(this.getBatimentModel().getBatimentId(), Constant.LOJ_ENDIVIDYEL, statutFormulaire, isFillAllField);
                if ( Nbre_TotalLogeIndividuel != NbreTotalLogeIndividuel_FiniEtBienRemplit ){
                    IsStatutBatimentFini=false;
                }
            }
            // SI TOUT CE PASSE TRES BIEN ON PASSE LE STATUT DU BATIMENT A FINI //
            if ( IsStatutBatimentFini ) {
                BatimentModel batimentModel = this.batimentModel;
                // SI tous les champs du Batiment sont bien remplit
                if (batimentModel.getIsFieldAllFilled() != null && batimentModel.getIsFieldAllFilled() ) {
                    statut = Constant.STATUT_RANPLI_NET_11;
                    if (batimentModel.getQb1Etat() == Constant.BATIMAN_Pa_konnen_paske_li_pa_sou_je_5 ) {
                        statut = Constant.STATUT_PA_RANPLI_DITOU_33;
                        statutFormulaire = Constant.STATUT_MODULE_KI_PA_FINI_3;
                    }else{
                        //statut = Constant.STATUT_PA_RANPLI_DITOU_33;
                    }
                    batimentModel.setStatut((short) statutFormulaire);
                    this.batimentModel = cuRecordMngr.SaveBatiment(this.iDKeys, batimentModel, this.TypeEvenement, nomUtilisateur );
                    //ToastUtility.LogCat( "AFTER Save batiment ID:" + batimentModel.getBatimentId());
                }
            }
            return statut;
        }catch (Exception ex) {
            ToastUtility.LogCat( "Exception: SetStatutBatiment(): ", ex);
            Tools.AlertDialogMsg(context, ex.getMessage(), "E");
            ex.printStackTrace();
        }
        return statut;
    }


    //endregion
}
