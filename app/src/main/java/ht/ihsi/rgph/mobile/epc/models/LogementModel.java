package ht.ihsi.rgph.mobile.epc.models;

import ht.ihsi.rgph.mobile.epc.backend.entities.LogementDao;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.epc.managers.CURecordMngr;
import ht.ihsi.rgph.mobile.epc.managers.FormDataMngr;
import ht.ihsi.rgph.mobile.epc.managers.QueryRecordMngr;
import ht.ihsi.rgph.mobile.epc.utilities.Tools;

/**
 * Created by Jfduvers on 3/21/2016.
 */
public class LogementModel extends BaseModel{

    //region ATTRIBUTS
    private Long logeId;
    private Long batimentId;
    private String sdeId;
    private Short qlCategLogement;
    private Short qlin1NumeroOrdre;
    private Short qlin2StatutOccupation;
    private Short qlin3TypeLogement;
    private Short qlin4IsHaveIndividuDepense;
    private Short qlin5NbreTotalMenage;
    private Short statut;
    private Boolean isValidated;
    private Boolean isFieldAllFilled;
    private String dateDebutCollecte;
    private String dateFinCollecte;
    private Integer dureeSaisie;
    private Boolean isContreEnqueteMade;
    private Short nbrTentative;
    private String codeAgentRecenceur;
    private Boolean isVerified=false;

    //endregion

    //region VARIABLES SYSTEME
    public String qlc1bTotalGarconEtFille;
    public String qlin6NombrePieceETChambreACoucher;
    private String callFormulaireIndividuLojCol;
    private String callFormulaireMenage;
    private BatimentModel objBatiment;
    public static QueryRecordMngr queryRecordMngr;
    public static FormDataMngr formDataMngr;
    public static CURecordMngr cuRecordMngr;
    //endregion

    //region CONSTRUCTEURS
    public LogementModel() {
        BlankData();
    }

    private void BlankData() {
        this.logeId = Long.valueOf(0);
        this.batimentId = Long.valueOf(0);
        this.qlin5NbreTotalMenage = 0;
        objBatiment = null;
    }
    //endregion

    // region |-| GETTER SETTER SYSTEME |-|
    /*public String getQlc1bTotalGarconEtFille() {// "00-00"
        return qlc2bTotalGarcon + "-" + qlc2bTotalFille;
    }

    public void setQlc1bTotalGarconEtFille(String value) {
        try {
            String[] TotalGarconEtFille = value.split("-"); // 00-00
            String TotalGarcon  = TotalGarconEtFille[0]; // Garcon
            String TotalFille = TotalGarconEtFille[1];  // Fille
            qlc2bTotalGarcon = Short.valueOf(TotalGarcon);
            qlc2bTotalFille = Short.valueOf(TotalFille);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    /*public String getQlin6NombrePieceETChambreACoucher() {// "00-00"
        return qlin6NombrePiece + "-" + qlin7NbreChambreACoucher;
    }

    public void setQlin6NombrePieceETChambreACoucher(String value) {
        try {
            String[] Items = value.split("-"); // 00-00
            qlin6NombrePiece = Short.valueOf(Items[0]);
            qlin7NbreChambreACoucher = Short.valueOf(Items[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
//endregion///////////////////////////////////////////////

    //region LogementModel getters and setters
    public Long getLogeId() {
        return logeId;
    }

    public void setLogeId(Long logeId) {
        this.logeId = logeId;
    }

    public Long getBatimentId() {
        return batimentId;
    }

    public void setBatimentId(Long batimentId) {
        this.batimentId = batimentId;
    }

    public String getSdeId() {
        return sdeId;
    }

    public void setSdeId(String sdeId) {
        this.sdeId = sdeId;
    }

    public Short getQlCategLogement() {
        return qlCategLogement;
    }

    public void setQlCategLogement(Short qlCategLogement) {
        this.qlCategLogement = qlCategLogement;
    }

    public Short getQlin1NumeroOrdre() {
        return qlin1NumeroOrdre;
    }

    public void setQlin1NumeroOrdre(Short qlin1NumeroOrdre) {
        this.qlin1NumeroOrdre = qlin1NumeroOrdre;
    }

    public Short getQlin2StatutOccupation() {
        return qlin2StatutOccupation;
    }

    public void setQlin2StatutOccupation(Short qlin2StatutOccupation) {
        this.qlin2StatutOccupation = qlin2StatutOccupation;
    }

    public Short getQlin3TypeLogement() {
        return qlin3TypeLogement;
    }

    public void setQlin3TypeLogement(Short qlin3TypeLogement) {
        this.qlin3TypeLogement = qlin3TypeLogement;
    }

    public Short getQlin4IsHaveIndividuDepense() {
        return qlin4IsHaveIndividuDepense;
    }

    public void setQlin4IsHaveIndividuDepense(Short qlin4IsHaveIndividuDepense) {
        this.qlin4IsHaveIndividuDepense = qlin4IsHaveIndividuDepense;
    }

    public Short getQlin5NbreTotalMenage() {
        return qlin5NbreTotalMenage;
    }

    public void setQlin5NbreTotalMenage(Short qlin5NbreTotalMenage) {
        this.qlin5NbreTotalMenage = qlin5NbreTotalMenage;
    }

    public Short getStatut() {
        return statut;
    }

    public void setStatut(Short statut) {
        this.statut = statut;
    }

    public Boolean getIsValidated() {
        return isValidated;
    }

    public void setIsValidated(Boolean isValidated) {
        this.isValidated = isValidated;
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

    public Short getNbrTentative() {
        return nbrTentative;
    }

    public void setNbrTentative(Short nbrTentative) {
        this.nbrTentative = nbrTentative;
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
    //endregion

    //region Model getters and setters

    public String getCallFormulaireIndividuLojCol() {
        return callFormulaireIndividuLojCol;
    }
    public void setCallFormulaireIndividuLojCol(String callFormulaireIndividuLojCol) {
        this.callFormulaireIndividuLojCol = callFormulaireIndividuLojCol;
    }
    public String getCallFormulaireMenage() {
        return callFormulaireMenage;
    }
    public void setCallFormulaireMenage(String callFormulaireMenage) {
        this.callFormulaireMenage = callFormulaireMenage;
    }

    public BatimentModel getObjBatiment() {
        return objBatiment;
    }

    public void setObjBatiment(BatimentModel objBatiment) {
        this.objBatiment = objBatiment;
    }
    //endregion

    //region METHODES Contrainte Saut Champs Valeur
    public static String Check_ContrainteSautChampsValeur( String nomChamps, LogementModel logementModel, Long iDKeys, Object dataBase)  throws TextEmptyException {
        try{
            String QSuivant = "";

            //region LOGEMENT INDIVIDUEL

            if (nomChamps.equalsIgnoreCase(LogementDao.Properties.Qlin4IsHaveIndividuDepense.columnName)){
                if ( logementModel.getQlin4IsHaveIndividuDepense()!=null
                        && logementModel.getQlin4IsHaveIndividuDepense() == Constant.REPONS_NON_2  ) {
                    logementModel.setQlin5NbreTotalMenage((short) 1);
                }
            }
            //LIN5 - KONBYEN MENAJ* ANTOU KAP VIV NAN LOJMAN SA A SI W METE MENAJ PAW LA LADAN N?
            if (nomChamps.equalsIgnoreCase(LogementDao.Properties.Qlin5NbreTotalMenage.columnName)) {
            /* Si LIN4 = 1,  alors LIN5≥ 2
             * Dans ce cas, si LIN5 ≤ 1, afficher un message d’erreur */
                if (  logementModel.getQlin5NbreTotalMenage()!=null
                        && logementModel.getQlin4IsHaveIndividuDepense()!=null
                        && logementModel.getQlin4IsHaveIndividuDepense() == Constant.REPONS_WI_1
                        && logementModel.getQlin5NbreTotalMenage() <= 1 ){
                    throw new TextEmptyException("Ou paka mete "+logementModel.getQlin5NbreTotalMenage()+" Menaj nan ka sa a pou kantite menaj yo ki genyen antou."
                            + "\n\nPaske lèw fe sòm pa ou a ak lòt yo wap jwenn plis ke  " + +logementModel.getQlin5NbreTotalMenage() + " Menaj.");
                }
            }
            //endregion
            return QSuivant;
        } catch (Exception ex){
            throw ex;
        }
    }
    //endregion


}

