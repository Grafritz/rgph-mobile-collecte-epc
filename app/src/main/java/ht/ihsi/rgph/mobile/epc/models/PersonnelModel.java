package ht.ihsi.rgph.mobile.epc.models;

/**
 * Created by ajordany on 3/21/2016.
 */
public class PersonnelModel extends BaseModel{

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
    private String codeDistrict;
    private Boolean estActif;
    private Integer ProfileId;

    private Boolean IsConnected;

    public PersonnelModel(){
        BlankField();
    }
    //region getters and setters
    public void setEstActif(boolean estActif) {
        this.estActif = estActif;
    }

    public void setProfileId(int profileId) {
        ProfileId = profileId;
    }

    public Boolean getIsConnected() {
        return IsConnected;
    }

    public void setIsConnected(Boolean connected) {
        IsConnected = connected;
    }


    public Long getPersId() {
        return persId;
    }

    public void setPersId(Long persId) {
        this.persId = persId;
    }

    public String getSdeId() {
        return sdeId;
    }

    public void setSdeId(String sdeId) {
        this.sdeId = sdeId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getVqseId() {
        return vqseId;
    }

    public void setVqseId(String vqseId) {
        this.vqseId = vqseId;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCodeDistrict() {
        return codeDistrict;
    }

    public void setCodeDistrict(String codeDistri) {
        this.codeDistrict = codeDistri;
    }

    public Boolean getEstActif() {
        return estActif;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public Integer getProfileId() {
        return ProfileId;
    }

    public void setProfileId(Integer ProfileId) {
        this.ProfileId = ProfileId;
    }
    //endregion

    public void BlankField(){
        this.persId = Long.valueOf(0);
        this.sdeId = "";
        this.nom = "";
        this.prenom = "";
        this.sexe = "";
        this.nomUtilisateur = "";
        this.motDePasse = "";
        this.email = "";
        this.deptId = "";
        this.comId = "";
        this.vqseId = "";
        this.zone = "";
        this.codeDistrict = "";
        this.estActif = false;
        this.ProfileId = 0;
        this.IsConnected = false;
    }
}
