package ht.ihsi.rgph.mobile.epc.models;

import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by ajordany on 3/21/2016.
 */
public class QuestionReponseModel extends BaseModel{

    /** Not-null value. */
    private String codeQuestion;
    /** Not-null value. */
    private String codeUniqueReponse;
    /** Not-null value. */
    private String codeReponse;
    private String libelleReponse;
    private Boolean estEnfant;
    private Boolean avoirEnfant;
    private String codeParent;
    private String qPrecedent;
    private String qSuivant;

    //region CONTROLS
    public RadioButton radioButton;
    public TextView tReponse;
    //endregion

    public QuestionReponseModel() {
    }

    public QuestionReponseModel(String codeQuestion, String codeUniqueReponse, String codeReponse, String libelleReponse) {
        this.codeQuestion = codeQuestion;
        this.codeUniqueReponse = codeUniqueReponse;
        this.codeReponse = codeReponse;
        this.libelleReponse = libelleReponse;
        this.estEnfant = false;
        this.avoirEnfant = false;
        this.codeParent = "";
        this.qPrecedent = "";
        this.qSuivant = "";
    }
    //region QuestionReponseModel getters and setters
    /** Not-null value. */
    public String getCodeQuestion() {
        return codeQuestion;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCodeQuestion(String codeQuestion) {
        this.codeQuestion = codeQuestion;
    }

    /** Not-null value. */
    public String getCodeUniqueReponse() {
        return codeUniqueReponse;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCodeUniqueReponse(String codeUniqueReponse) {
        this.codeUniqueReponse = codeUniqueReponse;
    }

    /** Not-null value. */
    public String getCodeReponse() {
        return codeReponse;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCodeReponse(String codeReponse) {
        this.codeReponse = codeReponse;
    }

    public String getLibelleReponse() {
        return libelleReponse;
    }

    public void setLibelleReponse(String libelleReponse) {
        this.libelleReponse = libelleReponse;
    }

    public Boolean getEstEnfant() {
        return estEnfant;
    }

    public void setEstEnfant(Boolean estEnfant) {
        this.estEnfant = estEnfant;
    }

    public Boolean getAvoirEnfant() {
        return avoirEnfant;
    }

    public void setAvoirEnfant(Boolean avoirEnfant) {
        this.avoirEnfant = avoirEnfant;
    }

    public String getCodeParent() {
        return codeParent;
    }

    public void setCodeParent(String codeParent) {
        this.codeParent = codeParent;
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

    //endregion


    @Override
    public String toString() {
        return this.libelleReponse.toString();
    }
}
