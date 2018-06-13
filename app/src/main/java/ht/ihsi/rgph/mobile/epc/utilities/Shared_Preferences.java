package ht.ihsi.rgph.mobile.epc.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ht.ihsi.rgph.mobile.epc.backend.entities.PersonnelDao;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.models.BaseModel;

/**
 * Created by JFDuverseau on 5/3/2016.
 */
public class Shared_Preferences extends BaseModel
{

    //region ATTRIBUTS
    private Long persId;
    private String sdeId;
    private String nom;
    private String prenom;
    private String sexe;
    private String nomUtilisateur;
    private String motDePasse;
    private String email;
    private String deptId;
    private String comId;
    private String vqseId;
    private String zone;
    private String codeDsitri;
    private Boolean estActif;
    private Integer ProfileId;
    private int Qlin2StatutOccupation;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    //endregion

    //region CONSTRUCTEURS
    public Shared_Preferences(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }
    //endregion

    //region GETTER & SETTER
    public Long getPersId() {
        persId = this.sharedPreferences.getLong(PersonnelDao.Properties.PersId.columnName, 0);
        return persId;
    }

    public void setPersId(Long persId) {
        this.persId = persId;
        editor.putLong(PersonnelDao.Properties.PersId.columnName, persId).commit();
    }

    public String getSdeId() {
        sdeId = this.sharedPreferences.getString( PersonnelDao.Properties.SdeId.columnName , "");
        return sdeId;
    }

    public void setSdeId(String sdeId) {
        this.sdeId = sdeId;
        editor.putString(PersonnelDao.Properties.SdeId.columnName , sdeId).commit();
    }

    public String getNom() {
        nom = this.sharedPreferences.getString(PersonnelDao.Properties.Nom.columnName  , "");
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
        editor.putString(PersonnelDao.Properties.Nom.columnName , nom).commit();
    }

    public String getPrenom() {
        prenom = this.sharedPreferences.getString(PersonnelDao.Properties.Prenom.columnName , "");
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
        editor.putString(PersonnelDao.Properties.Prenom.columnName , prenom).commit();
    }

    public String getPrenomEtNom() {
        return this.sharedPreferences.getString(Constant.prefPrenomEtNom, "");
    }

    public void setPrenomEtNom() {
        editor.putString(Constant.prefPrenomEtNom, this.prenom + " " + this.nom.toUpperCase()).commit();
    }

    public String getSexe() {
        sexe = this.sharedPreferences.getString(PersonnelDao.Properties.Sexe.columnName , "");
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
        editor.putString(PersonnelDao.Properties.Sexe.columnName , sexe).commit();
    }

    public String getNomUtilisateur() {
        nomUtilisateur = this.sharedPreferences.getString( PersonnelDao.Properties.NomUtilisateur.columnName , "");
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
        editor.putString(PersonnelDao.Properties.NomUtilisateur.columnName , nomUtilisateur).commit();
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        email = this.sharedPreferences.getString(PersonnelDao.Properties.Email.columnName , "");
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        editor.putString(PersonnelDao.Properties.Email.columnName , email).commit();
    }

    public String getDeptId() {
        deptId = this.sharedPreferences.getString( PersonnelDao.Properties.DeptId.columnName , "");
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
        editor.putString(PersonnelDao.Properties.DeptId.columnName , deptId).commit();
    }

    public String getComId() {
        comId = this.sharedPreferences.getString(PersonnelDao.Properties.ComId.columnName , "");
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
        editor.putString(PersonnelDao.Properties.ComId.columnName , comId).commit();
    }

    public String getVqseId() {
        vqseId = this.sharedPreferences.getString(PersonnelDao.Properties.VqseId.columnName , "");
        return vqseId;
    }

    public void setVqseId(String vqseId) {
        this.vqseId = vqseId;
        editor.putString(PersonnelDao.Properties.VqseId.columnName , vqseId).commit();
    }

    public String getZone() {
        zone = this.sharedPreferences.getString(PersonnelDao.Properties.Zone.columnName , "");
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
        editor.putString(PersonnelDao.Properties.Zone.columnName, zone).commit();
    }

    public String getCodeDsitri() {
        codeDsitri = this.sharedPreferences.getString(PersonnelDao.Properties.CodeDistrict.columnName, "");
        return codeDsitri;
    }

    public void setCodeDsitri(String codeDsitri) {
        this.codeDsitri = codeDsitri;
        editor.putString(PersonnelDao.Properties.CodeDistrict.columnName, codeDsitri).commit();
    }


    public Boolean getEstActif() {
        estActif = this.sharedPreferences.getBoolean(Constant.prefEstActif, false);
        return estActif;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
        editor.putBoolean(Constant.prefEstActif, estActif).commit();
    }

    public Integer getProfileId() {
        ProfileId = this.sharedPreferences.getInt(Constant.prefProfileId, 0);
        return ProfileId;
    }

    public void setProfileId(Integer profileId) {
        ProfileId = profileId;
        editor.putInt(Constant.prefProfileId, ProfileId).commit();
    }

    public Boolean getIsConnected() {
        return this.sharedPreferences.getBoolean(Constant.prefIsConnected, false);
    }

    public void setIsConnected(Boolean IsConnected) {
        editor.putBoolean(Constant.prefIsConnected, IsConnected).commit();
    }

    public String getprefLastLogin() {
        return this.sharedPreferences.getString(Constant.prefLastLogin, "");
    }

    public void setprefLastLogin(String prefLastLogin) {
        editor.putString(Constant.prefLastLogin, prefLastLogin).commit();
    }
//endregion

//region "LOGEMENT INDIVIDUEL"
    public int getQlin2StatutOccupation() {
        Qlin2StatutOccupation = this.sharedPreferences.getInt(Constant.Qlin2StatutOccupation, 1);
        return Qlin2StatutOccupation;
    }

    public void setQlin2StatutOccupation(int qlin2StatutOccupation) {
        Qlin2StatutOccupation = qlin2StatutOccupation;
        editor.putInt(Constant.Qlin2StatutOccupation, qlin2StatutOccupation).commit();
    }
    public int getVariablePref(String variablePref) {
        return this.sharedPreferences.getInt(Constant.Qlin2StatutOccupation, 1);
    }

    public void setVariablePref(String variablePref, int valeur) {
        editor.putInt(variablePref, valeur).commit();
    }
//endregion
}
