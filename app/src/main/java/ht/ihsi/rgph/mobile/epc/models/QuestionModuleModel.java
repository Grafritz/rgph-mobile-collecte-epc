package ht.ihsi.rgph.mobile.epc.models;

/**
 * Created by ajordany on 3/21/2016.
 */
public class QuestionModuleModel extends BaseModel {

    private String ordre;
    private String codeModule;
    private String codeQuestion;
    private boolean estDebut;

    public QuestionModuleModel() {
    }

    //region QuestionModuleModel getters and setters

    public String getOrdre() {
        return ordre;
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
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

    public boolean isEstDebut() {
        return estDebut;
    }

    public void setEstDebut(boolean estDebut) {
        this.estDebut = estDebut;
    }

    //endregion
}
