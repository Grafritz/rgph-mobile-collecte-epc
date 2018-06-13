package ht.ihsi.rgph.mobile.models;

/**
 * Created by ajordany on 3/21/2016.
 */
public class ModuleModel extends BaseModel {

    private String codeModule;
    private String nomModule;
    private int typeModule;
    private String description;
    private boolean estActif;

    public ModuleModel() {
    }

    public ModuleModel(String codeModule, String nomModule, Integer typeModule, Boolean estActif) {
        this.codeModule = codeModule;
        this.nomModule = nomModule;
        this.typeModule = typeModule;
        this.estActif = estActif;
    }

    //region ModuleModel getters and setters

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }

    public int getTypeModule() {
        return typeModule;
    }

    public void setTypeModule(int typeModule) {
        this.typeModule = typeModule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEstActif() {
        return estActif;
    }

    public void setEstActif(boolean estActif) {
        this.estActif = estActif;
    }

    //endregion
}
