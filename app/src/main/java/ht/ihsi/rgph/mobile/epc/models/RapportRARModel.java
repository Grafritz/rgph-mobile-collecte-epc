package ht.ihsi.rgph.mobile.epc.models;

/**
 * Created by JFDuverseau on 3/17/2017.
 */
public class RapportRARModel {

    //region ATTRIBUTS
    private Long rapportId;
    private Long batimentId;
    private Long logeId;
    private Long menageId;
    private Long emigreId;
    private Long decesId;
    private Long individuId;
    private String rapportModuleName;
    private String codeQuestionStop;
    private Short visiteNumber;
    private Short raisonActionId;
    private String autreRaisonAction;
    private Boolean isFieldAllFilled;
    private String dateDebutCollecte;
    private String dateFinCollecte;
    private Integer dureeSaisie;
    private Boolean isContreEnqueteMade;
    private String codeAgentRecenceur;
    //endregion

    //region CONSTRUCTEURS
    public RapportRARModel() {
        BlankData();
    }

    private void BlankData() {
        this.rapportId = Long.valueOf(0);
        this.batimentId = Long.valueOf(0);
        this.logeId = Long.valueOf(0);
        this.menageId = Long.valueOf(0);
        this.emigreId = Long.valueOf(0);
        this.decesId = Long.valueOf(0);
        this.individuId = Long.valueOf(0);
    }
    //endregion

    //region LogementModel getters and setters
    public Long getRapportId() {
        return rapportId;
    }

    public void setRapportId(Long rapportId) {
        this.rapportId = rapportId;
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

    public Long getEmigreId() {
        return emigreId;
    }

    public void setEmigreId(Long emigreId) {
        this.emigreId = emigreId;
    }

    public Long getDecesId() {
        return decesId;
    }

    public void setDecesId(Long decesId) {
        this.decesId = decesId;
    }

    public Long getIndividuId() {
        return individuId;
    }

    public void setIndividuId(Long individuId) {
        this.individuId = individuId;
    }

    public String getRapportModuleName() {
        return rapportModuleName;
    }

    public void setRapportModuleName(String rapportModuleName) {
        this.rapportModuleName = rapportModuleName;
    }

    public String getCodeQuestionStop() {
        return codeQuestionStop;
    }

    public void setCodeQuestionStop(String codeQuestionStop) {
        this.codeQuestionStop = codeQuestionStop;
    }

    public Short getVisiteNumber() {
        return visiteNumber;
    }

    public void setVisiteNumber(Short visiteNumber) {
        this.visiteNumber = visiteNumber;
    }

    public Short getRaisonActionId() {
        return raisonActionId;
    }

    public void setRaisonActionId(Short raisonActionId) {
        this.raisonActionId = raisonActionId;
    }

    public String getAutreRaisonAction() {
        return autreRaisonAction;
    }

    public void setAutreRaisonAction(String autreRaisonAction) {
        this.autreRaisonAction = autreRaisonAction;
    }

    public Boolean getIsFieldAllFilled() {
        return isFieldAllFilled;
    }

    public void setIsFieldAllFilled(Boolean isFieldAllFilled) {
        this.isFieldAllFilled = isFieldAllFilled;
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
    //endregion



}
