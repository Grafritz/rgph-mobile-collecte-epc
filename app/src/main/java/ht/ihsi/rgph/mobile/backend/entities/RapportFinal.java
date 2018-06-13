package ht.ihsi.rgph.mobile.backend.entities;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "tbl_rapportfinal".
 */
public class RapportFinal {

    private Long rapportFinalId;
    private Long batimentId;
    private Long logeId;
    private Long menageId;
    private Long repondantPrincipalId;
    private Short aE_EsKeGenMounKiEde;
    private Short aE_IsVivreDansMenage;
    private Short aE_RepondantQuiAideId;
    private Short f_EsKeGenMounKiEde;
    private Short f_IsVivreDansMenage;
    private Long f_RepondantQuiAideId;
    private String dateDebutCollecte;
    private String dateFinCollecte;
    private Integer dureeSaisie;
    private Boolean isContreEnqueteMade;
    private String codeAgentRecenceur;

    public RapportFinal() {
    }

    public RapportFinal(Long rapportFinalId) {
        this.rapportFinalId = rapportFinalId;
    }

    public RapportFinal(Long rapportFinalId, Long batimentId, Long logeId, Long menageId, Long repondantPrincipalId, Short aE_EsKeGenMounKiEde, Short aE_IsVivreDansMenage, Short aE_RepondantQuiAideId, Short f_EsKeGenMounKiEde, Short f_IsVivreDansMenage, Long f_RepondantQuiAideId, String dateDebutCollecte, String dateFinCollecte, Integer dureeSaisie, Boolean isContreEnqueteMade, String codeAgentRecenceur) {
        this.rapportFinalId = rapportFinalId;
        this.batimentId = batimentId;
        this.logeId = logeId;
        this.menageId = menageId;
        this.repondantPrincipalId = repondantPrincipalId;
        this.aE_EsKeGenMounKiEde = aE_EsKeGenMounKiEde;
        this.aE_IsVivreDansMenage = aE_IsVivreDansMenage;
        this.aE_RepondantQuiAideId = aE_RepondantQuiAideId;
        this.f_EsKeGenMounKiEde = f_EsKeGenMounKiEde;
        this.f_IsVivreDansMenage = f_IsVivreDansMenage;
        this.f_RepondantQuiAideId = f_RepondantQuiAideId;
        this.dateDebutCollecte = dateDebutCollecte;
        this.dateFinCollecte = dateFinCollecte;
        this.dureeSaisie = dureeSaisie;
        this.isContreEnqueteMade = isContreEnqueteMade;
        this.codeAgentRecenceur = codeAgentRecenceur;
    }

    public Long getRapportFinalId() {
        return rapportFinalId;
    }

    public void setRapportFinalId(Long rapportFinalId) {
        this.rapportFinalId = rapportFinalId;
    }

    public Long getBatimentId() {
        return batimentId;
    }

    public void setBatimentId(Long batimentId) {
        this.batimentId = batimentId;
    }

    public Long getLogeId() {
        return logeId;
    }

    public void setLogeId(Long logeId) {
        this.logeId = logeId;
    }

    public Long getMenageId() {
        return menageId;
    }

    public void setMenageId(Long menageId) {
        this.menageId = menageId;
    }

    public Long getRepondantPrincipalId() {
        return repondantPrincipalId;
    }

    public void setRepondantPrincipalId(Long repondantPrincipalId) {
        this.repondantPrincipalId = repondantPrincipalId;
    }

    public Short getAE_EsKeGenMounKiEde() {
        return aE_EsKeGenMounKiEde;
    }

    public void setAE_EsKeGenMounKiEde(Short aE_EsKeGenMounKiEde) {
        this.aE_EsKeGenMounKiEde = aE_EsKeGenMounKiEde;
    }

    public Short getAE_IsVivreDansMenage() {
        return aE_IsVivreDansMenage;
    }

    public void setAE_IsVivreDansMenage(Short aE_IsVivreDansMenage) {
        this.aE_IsVivreDansMenage = aE_IsVivreDansMenage;
    }

    public Short getAE_RepondantQuiAideId() {
        return aE_RepondantQuiAideId;
    }

    public void setAE_RepondantQuiAideId(Short aE_RepondantQuiAideId) {
        this.aE_RepondantQuiAideId = aE_RepondantQuiAideId;
    }

    public Short getF_EsKeGenMounKiEde() {
        return f_EsKeGenMounKiEde;
    }

    public void setF_EsKeGenMounKiEde(Short f_EsKeGenMounKiEde) {
        this.f_EsKeGenMounKiEde = f_EsKeGenMounKiEde;
    }

    public Short getF_IsVivreDansMenage() {
        return f_IsVivreDansMenage;
    }

    public void setF_IsVivreDansMenage(Short f_IsVivreDansMenage) {
        this.f_IsVivreDansMenage = f_IsVivreDansMenage;
    }

    public Long getF_RepondantQuiAideId() {
        return f_RepondantQuiAideId;
    }

    public void setF_RepondantQuiAideId(Long f_RepondantQuiAideId) {
        this.f_RepondantQuiAideId = f_RepondantQuiAideId;
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

    public Boolean getIsContreEnqueteMade() {
        return isContreEnqueteMade;
    }

    public void setIsContreEnqueteMade(Boolean isContreEnqueteMade) {
        this.isContreEnqueteMade = isContreEnqueteMade;
    }

    public String getCodeAgentRecenceur() {
        return codeAgentRecenceur;
    }

    public void setCodeAgentRecenceur(String codeAgentRecenceur) {
        this.codeAgentRecenceur = codeAgentRecenceur;
    }

}
