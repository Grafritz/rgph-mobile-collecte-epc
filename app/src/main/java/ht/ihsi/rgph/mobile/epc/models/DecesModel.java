package ht.ihsi.rgph.mobile.epc.models;

import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.epc.managers.QueryRecordMngr;

/**
 * Created by ajordany on 3/21/2016.
 */
public class DecesModel extends BaseModel{

    private Long decesId;
    private Long menageId;
    private Long logeId;
    private Long batimentId;
    private String sdeId;
    private Short qd2NoOrdre;
    private Short qd2aSexe;
    private String qd2bAgeDecede;
    private Short qd2c1CirconstanceDeces;
    private Short qd2c2CauseDeces;
    private Short statut;
    private Boolean isFieldAllFilled;
    private String dateDebutCollecte;
    private String dateFinCollecte;
    private Integer dureeSaisie;
    private Boolean isContreEnqueteMade;
    private String codeAgentRecenceur;
    private Boolean isVerified=false;

    //region MODEL OBJET
    public static QueryRecordMngr queryRecordMngr;
    private BatimentModel objBatiment;
    private LogementModel objLogement;
    private MenageModel objMenage;
    //endregion

    //region CONSTRUCTEURS
    public DecesModel() {
        BlankData();
    }

    private void BlankData() {
        decesId = Long.valueOf(0);
        batimentId = Long.valueOf(0);
        logeId = Long.valueOf(0);
        menageId = Long.valueOf(0);
    }
    //endregion

    //region DecesModel getters and setters
    public Long getDecesId() {
        return decesId;
    }

    public void setDecesId(Long decesId) {
        this.decesId = decesId;
    }

    public Long getMenageId() {
        return menageId;
    }

    public void setMenageId(Long menageId) {
        this.menageId = menageId;
    }

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

    public Short getQd2NoOrdre() {
        return qd2NoOrdre;
    }

    public void setQd2NoOrdre(Short qd2NoOrdre) {
        this.qd2NoOrdre = qd2NoOrdre;
    }

    public Short getQd2aSexe() {
        return qd2aSexe;
    }

    public void setQd2aSexe(Short qd2aSexe) {
        this.qd2aSexe = qd2aSexe;
    }

    public String getQd2bAgeDecede() {
        return qd2bAgeDecede;
    }

    public void setQd2bAgeDecede(String qd2bAgeDecede) {
        this.qd2bAgeDecede = qd2bAgeDecede;
    }

    public Short getQd2c1CirconstanceDeces() {
        return qd2c1CirconstanceDeces;
    }

    public void setQd2c1CirconstanceDeces(Short qd2c1CirconstanceDeces) {
        this.qd2c1CirconstanceDeces = qd2c1CirconstanceDeces;
    }

    public Short getQd2c2CauseDeces() {
        return qd2c2CauseDeces;
    }

    public void setQd2c2CauseDeces(Short qd2c2CauseDeces) {
        this.qd2c2CauseDeces = qd2c2CauseDeces;
    }

    public Short getStatut() {
        return statut;
    }

    public void setStatut(Short statut) {
        this.statut = statut;
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

    //region Model getters and setters
    public MenageModel getObjMenage() {
        return objMenage;
    }

    public void setObjMenage(MenageModel objMenage) {
        this.objMenage = objMenage;
    }

    public LogementModel getObjLogement() {
        return objLogement;
    }

    public void setObjLogement(LogementModel objLogement) {
        this.objLogement = objLogement;
    }

    public BatimentModel getObjBatiment() {
        return objBatiment;
    }

    public void setObjBatiment(BatimentModel objBatiment) {
        this.objBatiment = objBatiment;
    }
    //endregion


    //region METHODES
    public static String Check_ContrainteSautChampsValeur( String nomChamps, DecesModel decesModel, Long iDKeys, Object dataBase)  throws TextEmptyException {
        try{
            String QSuivant = "";
            //D2b	Laj li te mouri
            if (nomChamps.equalsIgnoreCase(Constant.Qd2bAgeDecede)) {

                if ( decesModel.getQd2bAgeDecede() != null
                    && Integer.parseInt(decesModel.getQd2bAgeDecede()) != Constant.AGE_PA_KONNEN_999ANS
                    && Integer.parseInt(decesModel.getQd2bAgeDecede()) > Constant.AGE_120ANS ) {
                    throw new TextEmptyException("Atansyon verifye laj ou antre a. Limit laj la dwe " + Constant.AGE_120ANS + " ane ");
                }
            /* Introduire un saut de contrôle permettant  d’aller automatiquement à la section CP (P.3) si:
            D.2.a =1;
            D.2.a =2 et que l'âge de la personne en D.2.b est< 13 ans ou > 49 ans */
                if ( decesModel.getQd2aSexe() == Constant.HOMME_1 ){
                    QSuivant = Constant.FIN;
                }
                if ( decesModel.getQd2aSexe() == Constant.FEMME_2 ){
                    if ( Short.parseShort( decesModel.getQd2bAgeDecede()) < Constant.AGE_13ANS || Short.parseShort( decesModel.getQd2bAgeDecede()) > Constant.AGE_49ANS  ){
                        QSuivant = Constant.FIN;
                    }
                }
            }
            return QSuivant;
        } catch (Exception ex){
            throw ex;
        }
    }
    //endregion
}

