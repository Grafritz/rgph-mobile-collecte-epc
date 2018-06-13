package ht.ihsi.rgph.mobile.epc.backend.entities;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "tbl_module".
 */
public class Module {

    /** Not-null value. */
    private String codeModule;
    private String nomModule;
    private Integer typeModule;
    private String description;
    private Boolean estActif;

    public Module() {
    }

    public Module(String codeModule, String nomModule, Integer typeModule, String description, Boolean estActif) {
        this.codeModule = codeModule;
        this.nomModule = nomModule;
        this.typeModule = typeModule;
        this.description = description;
        this.estActif = estActif;
    }

    /** Not-null value. */
    public String getCodeModule() {
        return codeModule;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }

    public Integer getTypeModule() {
        return typeModule;
    }

    public void setTypeModule(Integer typeModule) {
        this.typeModule = typeModule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEstActif() {
        return estActif;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

}