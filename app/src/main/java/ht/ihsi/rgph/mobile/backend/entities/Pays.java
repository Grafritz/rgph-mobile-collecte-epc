package ht.ihsi.rgph.mobile.backend.entities;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "tbl_pays".
 */
public class Pays {

    /** Not-null value. */
    private String CodePays;
    private String NomPays;

    public Pays() {
    }

    public Pays(String CodePays, String NomPays) {
        this.CodePays = CodePays;
        this.NomPays = NomPays;
    }

    /** Not-null value. */
    public String getCodePays() {
        return CodePays;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCodePays(String CodePays) {
        this.CodePays = CodePays;
    }

    public String getNomPays() {
        return NomPays;
    }

    public void setNomPays(String NomPays) {
        this.NomPays = NomPays;
    }

}
