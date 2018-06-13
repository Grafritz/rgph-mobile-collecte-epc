package ht.ihsi.rgph.mobile.epc.models;

import java.io.Serializable;

/**
 * Created by JFDuverseau on 11/22/2016.
 */
public class QuestionnaireModel implements Serializable {

       //region VARIABLES
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

    //region OBJECT
    private BatimentModel batimentModel;
    private LogementModel logementModel;
    private MenageModel menageModel;
    //endregion
    ///////////////////////////////////
    public String GrandTitre;
    public String LibelleQuestion;
    public String DetailsQuestion;
    public String DetailsCategorie;
    public String SousDetailsCategorie;
    public int TypeEvenement;

    private Object model;
    //endregion

    //region CONSTRUCTEURS
    public QuestionnaireModel() {
    }

    //endregion

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

    //region getters and setters
    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

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

    public void setMenageModel(MenageModel menageModel) {
        this.menageModel = menageModel;
    }

    //endregion
}
