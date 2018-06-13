package ht.ihsi.rgph.mobile.utilities;

import android.content.Context;

import java.io.Serializable;

import ht.ihsi.rgph.mobile.constant.Constant;
import ht.ihsi.rgph.mobile.models.BaseModel;

/**
 * Created by JeanFritz on 4/13/2016.
 */
public class ContrainteReponse implements Serializable
{
    //region ATTRIBUT
    private int nombreCaratere = -1;
    private int caractereAccepte = -1;
    private int nbreValeurMinimal = -1;
    private int nombreCaratereMaximal = -1;
    private int nombreCaratereMinimal = -1;
    private int different = -1;
    private Boolean obligatoire = true;
    private Boolean annee = false;
    private int fixedCategory = -1;

    private int minimum = -1;
    private int maximum = -1;
    //endregion

    //region GET / SET
    public int getNombreCaratere() {
        return nombreCaratere;
    }

    public void setNombreCaratere(int nombreCaratere) {
        this.nombreCaratere = nombreCaratere;
    }

    public int getCaractereAccepte() {
        return caractereAccepte;
    }

    public void setCaractereAccepte(int caractereAccepte) {
        this.caractereAccepte = caractereAccepte;
    }

    public int getNbreValeurMinimal() {
        return nbreValeurMinimal;
    }

    public void setNbreValeurMinimal(int nbreValeurMinimal) {
        this.nbreValeurMinimal = nbreValeurMinimal;
    }

    public int getNombreCaratereMaximal() {
        return nombreCaratereMaximal;
    }

    public void setNombreCaratereMaximal(int nombreCaratereMaximal) {
        this.nombreCaratereMaximal = nombreCaratereMaximal;
    }

    public int getNombreCaratereMinimal() {
        return nombreCaratereMinimal;
    }

    public void setNombreCaratereMinimal(int nombreCaratereMinimal) {
        this.nombreCaratereMinimal = nombreCaratereMinimal;
        if (nombreCaratereMinimal > -1) this.nombreCaratereMaximal = nombreCaratereMinimal;
    }

    public int getDifferent() {
        return different;
    }

    public void setDifferent(int different) {
        this.different = different;
    }

    public Boolean getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(Boolean obligatoire) {
        this.obligatoire = obligatoire;
    }

    public Boolean getAnnee() {
        return annee;
    }

    public void setAnnee(Boolean annee) {
        this.annee = annee;
    }

    public int getFixedCategory() {
        return fixedCategory;
    }

    public void setFixedCategory(int fixedCategory) {
        this.fixedCategory = fixedCategory;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public String MessageNombreValeurMinimal(){
        return "Repons ou a pa dwe pi piti ke " + this.nbreValeurMinimal;
    }

    public String MessageNombreCaractereMinimal()
    {
        String e = "karatè";
        if (this.caractereAccepte == Constant.CHIFFRE) e = "chif";
        if (this.caractereAccepte == Constant.LETTRE) e = "lèt";

        return "Repons ou a dwe genyen " + this.nombreCaratereMinimal + " " + e + " pou pi piti";

    }

    public String MessageNombreCaractere()
    {
        String e = "karatè";
            if (this.caractereAccepte == Constant.CHIFFRE) e = "chif";
            if (this.caractereAccepte == Constant.LETTRE) e = "lèt";

            return "Repons ou a dwe genyen " + this.nombreCaratere + " " + e;
    }
    //endregion

}
