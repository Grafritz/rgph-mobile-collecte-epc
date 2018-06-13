package ht.ihsi.rgph.mobile.models;

/**
 * Created by ajordany on 3/21/2016.
 */
public class CategorieQuestionModel extends BaseModel{

    private String codeCategorie;
    private String categorieQuestion;
    private String detailsCategorie;
    private String sousDetailsCategorie;

    public CategorieQuestionModel() {
    }

    //region CategorieQuestionModel setter and getters

    public String getCodeCategorie() {
        return codeCategorie;
    }

    public void setCodeCategorie(String codeCategorie) {
        this.codeCategorie = codeCategorie;
    }

    public String getCategorieQuestion() {
        return categorieQuestion;
    }

    public void setCategorieQuestion(String categorieQuestion) {
        this.categorieQuestion = categorieQuestion;
    }

    public String getDetailsCategorie() {
        return detailsCategorie;
    }

    public void setDetailsCategorie(String detailsCategorie) {
        this.detailsCategorie = detailsCategorie;
    }

    public String getSousDetailsCategorie() {
        return sousDetailsCategorie;
    }

    public void setSousDetailsCategorie(String sousDetailsCategorie) {
        this.sousDetailsCategorie = sousDetailsCategorie;
    }

    //endregion
}
