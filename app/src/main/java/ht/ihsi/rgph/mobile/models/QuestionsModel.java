package ht.ihsi.rgph.mobile.models;

/**
 * Created by ajordany on 3/21/2016.
 */
public class QuestionsModel extends BaseModel{

    private String codeQuestion;
    private String libelle;
    private String detailsQuestion;
    private String indicationsQuestion;
    private String codeCategorie;
    private String nomChamps;
    private int typeQuestion;
    private int caratereAccepte;
    private int nbreValeurMinimal;
    private int nbreCaratereMaximal;
    private String contrainteSautChampsValeur;
    private boolean estSautReponse;
    private String qPrecedent;
    private String qSuivant;

    public QuestionsModel() {
    }

    //region QuestionsModel getter and setters

    public String getCodeQuestion() {
        return codeQuestion;
    }

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
        return indicationsQuestion;
    }

    public void setIndicationsQuestion(String indicationsQuestion) {
        this.indicationsQuestion = indicationsQuestion;
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
    }

    public int getNbreValeurMinimal() {
        return nbreValeurMinimal;
    }

    public void setNbreValeurMinimal(int nbreValeurMinimal) {
        this.nbreValeurMinimal = nbreValeurMinimal;
    }

    public int getNbreCaratereMaximal() {
        return nbreCaratereMaximal;
    }

    public void setNbreCaratereMaximal(int nbreCaratereMaximal) {
        this.nbreCaratereMaximal = nbreCaratereMaximal;
    }

    public String getContrainteSautChampsValeur() {
        return contrainteSautChampsValeur;
    }

    public void setContrainteSautChampsValeur(String contrainteSautChampsValeur) {
        this.contrainteSautChampsValeur = contrainteSautChampsValeur;
    }

    public boolean isEstSautReponse() {
        return estSautReponse;
    }

    public void setEstSautReponse(boolean estSautReponse) {
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
}
