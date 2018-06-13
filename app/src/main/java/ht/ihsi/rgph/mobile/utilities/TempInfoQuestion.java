package ht.ihsi.rgph.mobile.utilities;

/**
 * Created by JFDuverseau on 4/27/2016.
 */
public class TempInfoQuestion
{

    //region " ATTRIBUT "
    private String codeQuestion;
    private String nomChamps;
    private String codeUniqueReponse;
    private String codeReponse;
    //endregion

    //region "  GET SET  "
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

    public String getCodeUniqueReponse() {
        return codeUniqueReponse;
    }

    public void setCodeUniqueReponse(String codeUniqueReponse) {
        this.codeUniqueReponse = codeUniqueReponse;
    }

    public String getCodeReponse() {
        return codeReponse;
    }

    public void setCodeReponse(String codeReponse) {
        this.codeReponse = codeReponse;
    }

    //endregion
}
