package ht.ihsi.rgph.mobile.epc.backend.entities;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "tbl_batiment".
 */
public class Batiment {

    private Long batimentId;
    private String deptId;
    private String comId;
    private String vqseId;
    private String sdeId;
    private Short zone;
    private String disctrictId;
    private String qhabitation;
    private String qlocalite;
    private String qadresse;
    private String qrec;
    private String qrgph;
    private Short qb1Etat;
    private Short qb2Type;
    private Short qb3NombreEtage;
    private Short qb4MateriauMur;
    private Short qb5MateriauToit;
    private Short qb6StatutOccupation;
    private Short qb7Utilisation1;
    private Short qb7Utilisation2;
    private Short qb8NbreLogeCollectif;
    private Short qb8NbreLogeIndividuel;
    private Short statut;
    private String dateEnvoi;
    private Boolean isValidated;
    private Boolean isSynchroToAppSup;
    private Boolean isSynchroToCentrale;
    private String dateDebutCollecte;
    private String dateFinCollecte;
    private Integer dureeSaisie;
    private Boolean isFieldAllFilled;
    private Boolean isContreEnqueteMade;
    private String latitude;
    private String longitude;
    private String codeAgentRecenceur;
    private Boolean isVerified=false;

    public Batiment() {
    }

    public Batiment(Long batimentId) {
        this.batimentId = batimentId;
    }

    public Batiment(Long batimentId, String deptId, String comId, String vqseId, String sdeId, Short zone, String disctrictId, String qhabitation, String qlocalite, String qadresse, String qrec, String qrgph, Short qb1Etat, Short qb2Type, Short qb3NombreEtage, Short qb4MateriauMur, Short qb5MateriauToit, Short qb6StatutOccupation, Short qb7Utilisation1, Short qb7Utilisation2, Short qb8NbreLogeCollectif, Short qb8NbreLogeIndividuel, Short statut, String dateEnvoi, Boolean isValidated, Boolean isSynchroToAppSup, Boolean isSynchroToCentrale, String dateDebutCollecte, String dateFinCollecte, Integer dureeSaisie, Boolean isFieldAllFilled, Boolean isContreEnqueteMade, String latitude, String longitude, String codeAgentRecenceur, Boolean isVerified) {
        this.batimentId = batimentId;
        this.deptId = deptId;
        this.comId = comId;
        this.vqseId = vqseId;
        this.sdeId = sdeId;
        this.zone = zone;
        this.disctrictId = disctrictId;
        this.qhabitation = qhabitation;
        this.qlocalite = qlocalite;
        this.qadresse = qadresse;
        this.qrec = qrec;
        this.qrgph = qrgph;
        this.qb1Etat = qb1Etat;
        this.qb2Type = qb2Type;
        this.qb3NombreEtage = qb3NombreEtage;
        this.qb4MateriauMur = qb4MateriauMur;
        this.qb5MateriauToit = qb5MateriauToit;
        this.qb6StatutOccupation = qb6StatutOccupation;
        this.qb7Utilisation1 = qb7Utilisation1;
        this.qb7Utilisation2 = qb7Utilisation2;
        this.qb8NbreLogeCollectif = qb8NbreLogeCollectif;
        this.qb8NbreLogeIndividuel = qb8NbreLogeIndividuel;
        this.statut = statut;
        this.dateEnvoi = dateEnvoi;
        this.isValidated = isValidated;
        this.isSynchroToAppSup = isSynchroToAppSup;
        this.isSynchroToCentrale = isSynchroToCentrale;
        this.dateDebutCollecte = dateDebutCollecte;
        this.dateFinCollecte = dateFinCollecte;
        this.dureeSaisie = dureeSaisie;
        this.isFieldAllFilled = isFieldAllFilled;
        this.isContreEnqueteMade = isContreEnqueteMade;
        this.latitude = latitude;
        this.longitude = longitude;
        this.codeAgentRecenceur = codeAgentRecenceur;
        this.isVerified = isVerified;
    }

    public Long getBatimentId() {
        return batimentId;
    }

    public void setBatimentId(Long batimentId) {
        this.batimentId = batimentId;
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

    public String getSdeId() {
        return sdeId;
    }

    public void setSdeId(String sdeId) {
        this.sdeId = sdeId;
    }

    public Short getZone() {
        return zone;
    }

    public void setZone(Short zone) {
        this.zone = zone;
    }

    public String getDisctrictId() {
        return disctrictId;
    }

    public void setDisctrictId(String disctrictId) {
        this.disctrictId = disctrictId;
    }

    public String getQhabitation() {
        return qhabitation;
    }

    public void setQhabitation(String qhabitation) {
        this.qhabitation = qhabitation;
    }

    public String getQlocalite() {
        return qlocalite;
    }

    public void setQlocalite(String qlocalite) {
        this.qlocalite = qlocalite;
    }

    public String getQadresse() {
        return qadresse;
    }

    public void setQadresse(String qadresse) {
        this.qadresse = qadresse;
    }

    public String getQrec() {
        return qrec;
    }

    public void setQrec(String qrec) {
        this.qrec = qrec;
    }

    public String getQrgph() {
        return qrgph;
    }

    public void setQrgph(String qrgph) {
        this.qrgph = qrgph;
    }

    public Short getQb1Etat() {
        return qb1Etat;
    }

    public void setQb1Etat(Short qb1Etat) {
        this.qb1Etat = qb1Etat;
    }

    public Short getQb2Type() {
        return qb2Type;
    }

    public void setQb2Type(Short qb2Type) {
        this.qb2Type = qb2Type;
    }

    public Short getQb3NombreEtage() {
        return qb3NombreEtage;
    }

    public void setQb3NombreEtage(Short qb3NombreEtage) {
        this.qb3NombreEtage = qb3NombreEtage;
    }

    public Short getQb4MateriauMur() {
        return qb4MateriauMur;
    }

    public void setQb4MateriauMur(Short qb4MateriauMur) {
        this.qb4MateriauMur = qb4MateriauMur;
    }

    public Short getQb5MateriauToit() {
        return qb5MateriauToit;
    }

    public void setQb5MateriauToit(Short qb5MateriauToit) {
        this.qb5MateriauToit = qb5MateriauToit;
    }

    public Short getQb6StatutOccupation() {
        return qb6StatutOccupation;
    }

    public void setQb6StatutOccupation(Short qb6StatutOccupation) {
        this.qb6StatutOccupation = qb6StatutOccupation;
    }

    public Short getQb7Utilisation1() {
        return qb7Utilisation1;
    }

    public void setQb7Utilisation1(Short qb7Utilisation1) {
        this.qb7Utilisation1 = qb7Utilisation1;
    }

    public Short getQb7Utilisation2() {
        return qb7Utilisation2;
    }

    public void setQb7Utilisation2(Short qb7Utilisation2) {
        this.qb7Utilisation2 = qb7Utilisation2;
    }

    public Short getQb8NbreLogeCollectif() {
        return qb8NbreLogeCollectif;
    }

    public void setQb8NbreLogeCollectif(Short qb8NbreLogeCollectif) {
        this.qb8NbreLogeCollectif = qb8NbreLogeCollectif;
    }

    public Short getQb8NbreLogeIndividuel() {
        return qb8NbreLogeIndividuel;
    }

    public void setQb8NbreLogeIndividuel(Short qb8NbreLogeIndividuel) {
        this.qb8NbreLogeIndividuel = qb8NbreLogeIndividuel;
    }

    public Short getStatut() {
        return statut;
    }

    public void setStatut(Short statut) {
        this.statut = statut;
    }

    public String getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Boolean getIsValidated() {
        return isValidated;
    }

    public void setIsValidated(Boolean isValidated) {
        this.isValidated = isValidated;
    }

    public Boolean getIsSynchroToAppSup() {
        return isSynchroToAppSup;
    }

    public void setIsSynchroToAppSup(Boolean isSynchroToAppSup) {
        this.isSynchroToAppSup = isSynchroToAppSup;
    }

    public Boolean getIsSynchroToCentrale() {
        return isSynchroToCentrale;
    }

    public void setIsSynchroToCentrale(Boolean isSynchroToCentrale) {
        this.isSynchroToCentrale = isSynchroToCentrale;
    }

    public String getDateDebutCollecte() {
        return dateDebutCollecte;
    }

    public void setDateDebutCollecte(String dateDebutCollecte) {
        this.dateDebutCollecte = dateDebutCollecte;
    }

    public String getDateFinCollecte() {
        return dateFinCollecte;
    }

    public void setDateFinCollecte(String dateFinCollecte) {
        this.dateFinCollecte = dateFinCollecte;
    }

    public Integer getDureeSaisie() {
        return dureeSaisie;
    }

    public void setDureeSaisie(Integer dureeSaisie) {
        this.dureeSaisie = dureeSaisie;
    }

    public Boolean getIsFieldAllFilled() {
        return isFieldAllFilled;
    }

    public void setIsFieldAllFilled(Boolean isFieldAllFilled) {
        this.isFieldAllFilled = isFieldAllFilled;
    }

    public Boolean getIsContreEnqueteMade() {
        return isContreEnqueteMade;
    }

    public void setIsContreEnqueteMade(Boolean isContreEnqueteMade) {
        this.isContreEnqueteMade = isContreEnqueteMade;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCodeAgentRecenceur() {
        return codeAgentRecenceur;
    }

    public void setCodeAgentRecenceur(String codeAgentRecenceur) {
        this.codeAgentRecenceur = codeAgentRecenceur;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

}