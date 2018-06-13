package ht.ihsi.rgph.mobile.epc.backend.entities;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "tbl_question".
 */
public class Question {

    /** Not-null value. */
    private String codeQuestion;
    private String libelle;
    private String detailsQuestion;
    private String IndicationsQuestion;
    private String codeCategorie;
    private String nomChamps;
    private Integer typeQuestion;
    private Integer caratereAccepte;
    private Integer nbreValeurMinimal;
    private Integer nbreCaratereMaximal;
    private String contrainteSautChampsValeur;
    private Boolean estSautReponse;
    private String qPrecedent;
    private String qSuivant;

    public Question() {
    }

    public Question(String codeQuestion, String libelle, String detailsQuestion, String IndicationsQuestion, String codeCategorie, String nomChamps, Integer typeQuestion, Integer caratereAccepte, Integer nbreValeurMinimal, Integer nbreCaratereMaximal, String contrainteSautChampsValeur, Boolean estSautReponse, String qPrecedent, String qSuivant) {
        this.codeQuestion = codeQuestion;
        this.libelle = libelle;
        this.detailsQuestion = detailsQuestion;
        this.IndicationsQuestion = IndicationsQuestion;
        this.codeCategorie = codeCategorie;
        this.nomChamps = nomChamps;
        this.typeQuestion = typeQuestion;
        this.caratereAccepte = caratereAccepte;
        this.nbreValeurMinimal = nbreValeurMinimal;
        this.nbreCaratereMaximal = nbreCaratereMaximal;
        this.contrainteSautChampsValeur = contrainteSautChampsValeur;
        this.estSautReponse = estSautReponse;
        this.qPrecedent = qPrecedent;
        this.qSuivant = qSuivant;
    }

    /** Not-null value. */
    public String getCodeQuestion() {
        return codeQuestion;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCodeQuestion(String codeQuestion) {
        this.codeQuestion = codeQuestion;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDetailsQuestion() {
        return detailsQuestion;
    }

    public void setDetailsQuestion(String detailsQuestion) {
        this.detailsQuestion = detailsQuestion;
    }

    public String getIndicationsQuestion() {
        return IndicationsQuestion;
    }

    public void setIndicationsQuestion(String IndicationsQuestion) {
        this.IndicationsQuestion = IndicationsQuestion;
    }

    public String getCodeCategorie() {
        return codeCategorie;
    }

    public void setCodeCategorie(String codeCategorie) {
        this.codeCategorie = codeCategorie;
    }

    public String getNomChamps() {
        return nomChamps;
    }

    public void setNomChamps(String nomChamps) {
        this.nomChamps = nomChamps;
    }

    public Integer getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(Integer typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public Integer getCaratereAccepte() {
        return caratereAccepte;
    }

    public void setCaratereAccepte(Integer caratereAccepte) {
        this.caratereAccepte = caratereAccepte;
    }

    public Integer getNbreValeurMinimal() {
        return nbreValeurMinimal;
    }

    public void setNbreValeurMinimal(Integer nbreValeurMinimal) {
        this.nbreValeurMinimal = nbreValeurMinimal;
    }

    public Integer getNbreCaratereMaximal() {
        return nbreCaratereMaximal;
    }

    public void setNbreCaratereMaximal(Integer nbreCaratereMaximal) {
        this.nbreCaratereMaximal = nbreCaratereMaximal;
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

    public String getQPrecedent() {
        return qPrecedent;
    }

    public void setQPrecedent(String qPrecedent) {
        this.qPrecedent = qPrecedent;
    }

    public String getQSuivant() {
        return qSuivant;
    }

    public void setQSuivant(String qSuivant) {
        this.qSuivant = qSuivant;
    }

}