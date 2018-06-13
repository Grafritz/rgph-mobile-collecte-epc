package ht.ihsi.rgph.mobile.models;

/**
 * Created by ajordany on 3/21/2016.
 */
public class DomainEtudeModel extends BaseModel{
    private String Code;
    private String NomDomaine;

    public DomainEtudeModel() {
    }

    //region DomainEtudeModel getters and setters

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getNomDomaine() {
        return NomDomaine;
    }

    public void setNomDomaine(String nomDomaine) {
        NomDomaine = nomDomaine;
    }

    //endregion
}
