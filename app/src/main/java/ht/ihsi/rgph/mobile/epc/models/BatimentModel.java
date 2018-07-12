package ht.ihsi.rgph.mobile.epc.models;

import ht.ihsi.rgph.mobile.epc.backend.entities.BatimentDao;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.exceptions.ManagerException;
import ht.ihsi.rgph.mobile.epc.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.epc.managers.QueryRecordMngr;
import ht.ihsi.rgph.mobile.epc.utilities.ToastUtility;
import ht.ihsi.rgph.mobile.epc.utilities.Tools;

/**
 * Created by Jfduvers on 3/21/2016.
 */
public class BatimentModel extends BaseModel {

    //region VARIABLES
    private Long batimentId = Long.valueOf(0);
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
    private String qepc;
    private Short qb1Etat;
    private Short qb2Type;
    private Short qb3StatutOccupation;
    private Short qb4NbreLogeIndividuel;
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

    private String callFormulaireLogementCollectif;
    private String callFormulaireLogementEndividyel;
    public static QueryRecordMngr queryRecordMngr;

    //region CONSTRUCTEURS
    public BatimentModel() {
        BlankData();
    }

    private void BlankData() {
        batimentId = Long.valueOf(0);
        qb4NbreLogeIndividuel = 0;
    }
    //endregion

    //region BatimentModel getters and setters
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

    public String getQepc() {
        return qepc;
    }

    public void setQepc(String qepc) {
        this.qepc = qepc;
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

    public Short getQb3StatutOccupation() {
        return qb3StatutOccupation;
    }

    public void setQb3StatutOccupation(Short qb3StatutOccupation) {
        this.qb3StatutOccupation = qb3StatutOccupation;
    }

    public Short getQb4NbreLogeIndividuel() {
        return qb4NbreLogeIndividuel;
    }

    public void setQb4NbreLogeIndividuel(Short qb4NbreLogeIndividuel) {
        this.qb4NbreLogeIndividuel = qb4NbreLogeIndividuel;
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
    //endregion

    //region 2 getters and setters
    public String getCallFormulaireLogementCollectif() {
        return callFormulaireLogementCollectif;
    }
    public void setCallFormulaireLogementCollectif(String callFormulaireLogementCollectif) {
        this.callFormulaireLogementCollectif = callFormulaireLogementCollectif;
    }
    public String getCallFormulaireLogementEndividyel() {
        return callFormulaireLogementEndividyel;
    }
    public void setCallFormulaireLogementEndividyel(String callFormulaireLogementEndividyel) {
        this.callFormulaireLogementEndividyel = callFormulaireLogementEndividyel;
    }
    //endregion

    //region METHODES
    public static String Check_ContrainteSautChampsValeur(String nomChamps, BatimentModel batimentModel, Long iDKeys, Object dataBase, Boolean ExpulseException)  throws TextEmptyException {
        try{
            String QSuivant = "";
            if (nomChamps.equalsIgnoreCase(BatimentDao.Properties.Qepc.columnName)){
                if( ExpulseException==true &&  batimentModel.getQepc().trim().equalsIgnoreCase("000") ){
                    throw new TextEmptyException("Ou pa ka ekri 000 (Nimewo sa a pa dwe  000).");
                }
            }
            // B1
            if (nomChamps.equalsIgnoreCase(BatimentDao.Properties.Qrec.columnName)){
                if( ExpulseException==true &&  batimentModel.getQrec().trim().equalsIgnoreCase("000") ){
                    throw new TextEmptyException("Ou pa ka ekri 000 (Nimewo sa a pa dwe  000).");
                }

                if (iDKeys <= 0){
                    if( ExpulseException==true &&  IsBatimentExiste_ByRec(batimentModel.getQrec()) ){
                        throw new TextEmptyException("Nimewo REC " + batimentModel.getQrec() + " sa a anrejistre deja. \nNimewo ou ekri a pa bon, gade nimewo ou te ekri avan yo.");
                    }
                }else{
                    if (dataBase != null){
                        BatimentModel b2 = (BatimentModel)dataBase;
                        if ( ExpulseException==true && !b2.getQrec().equalsIgnoreCase( batimentModel.getQrec() ) ) {
                            if ( ExpulseException==true &&  IsBatimentExiste_ByRec(b2.getQrec()) ){
                                throw new TextEmptyException("Nimewo REC " + b2.getQrec() + " sa a anrejistre deja. \nNimewo ou ekri a pa bon, gade nimewo ou te ekri avan yo.");
                            }
                        }
                    }
                }
            }//

            // B1.- Nan ki eta kay la ye?
            if (nomChamps.equalsIgnoreCase(BatimentDao.Properties.Qb1Etat.columnName)){
                if ( ExpulseException==true &&  batimentModel.getQb1Etat() == Constant.R05_Pa_Konnen_Paske_Li_Pa_Sou_Je  ){
                    long NbreLogement_DejaSave = 0;
                    if( ExpulseException==true && batimentModel.getBatimentId() > 0 ){
                        NbreLogement_DejaSave = queryRecordMngr.countLogByBat(batimentModel.getBatimentId());
                        if( ExpulseException==true &&  NbreLogement_DejaSave > 0 ){
                            throw new TextEmptyException("Ou paka chwazi [ "+ Tools.getString_Reponse("" + batimentModel.getQb1Etat(), BatimentDao.Properties.Qb1Etat.columnName) +" ]. "
                                    + "\npaske ou gentan anregistre [" + NbreLogement_DejaSave + "] Lojman pou batiman sa.");
                        }
                    }
                }
            }

            // B3.- Eske kay sa a?
            if (nomChamps.equalsIgnoreCase(BatimentDao.Properties.Qb3StatutOccupation.columnName)){
                if ( ExpulseException==true &&  batimentModel.getQb3StatutOccupation() == Constant.BATIMAN_TOUJOU_VID_2  ){
                    long NbreLogement_DejaSave = 0;
                    if( ExpulseException==true && batimentModel.getBatimentId() > 0 ){
                        NbreLogement_DejaSave = queryRecordMngr.countLogByBat(batimentModel.getBatimentId());
                        if( ExpulseException==true &&  NbreLogement_DejaSave > 0 ){
                            throw new TextEmptyException("Ou paka chwazi [ "+ Tools.getString_Reponse("" + batimentModel.getQb3StatutOccupation(), BatimentDao.Properties.Qb3StatutOccupation.columnName) +" ]. "
                                    + "\npaske ou gentan anregistre [" + NbreLogement_DejaSave + "] Lojman pou batiman sa.");
                        }
                    }
                }
            }
            // B4.- Nan kay sa a konbyen lojman endividyèl ki genyen?
            if (nomChamps.equalsIgnoreCase(BatimentDao.Properties.Qb4NbreLogeIndividuel.columnName)){
                if( ExpulseException==true &&  batimentModel.getBatimentId()!=null && batimentModel.getBatimentId() > 0  ) {
                    long NbreLogement_DejaSave = queryRecordMngr.countLogByBatAndType(batimentModel.getBatimentId(), Constant.LOJ_ENDIVIDYEL);
                    if (ExpulseException == true && batimentModel.getQb4NbreLogeIndividuel() < NbreLogement_DejaSave) {
                        throw new TextEmptyException("Ou paka retire nan kantite ke ou te mete a. "
                                + "\npaske ou gentan anregistre [" + NbreLogement_DejaSave + "] Lojman endividyèl deja pou batiman sa.");
                    }
                }

                if( ExpulseException==true && batimentModel.getQb4NbreLogeIndividuel()!=null &&
                        batimentModel.getQb4NbreLogeIndividuel() <= 0 ){
                    QSuivant = Constant.FIN;
                }
            }//
            return QSuivant;
        } catch (Exception ex){
            throw ex;
        }
    }

    private static boolean IsBatimentExiste_ByRec(String qrec) {
        try {
            return queryRecordMngr.isRecAlreadyExist(qrec);
        } catch (ManagerException e) {
            ToastUtility.LogCat("E", "ManagerException: IsBatimentExiste_ByRec() | getMessage:" + e.getMessage() + " /n toString:" + e.toString());
            return true;
        }
    }


    //endregion
}

