package ht.ihsi.rgph.mobile.models;

import ht.ihsi.rgph.mobile.backend.entities.EmigreDao;
import ht.ihsi.rgph.mobile.backend.entities.MenageDao;
import ht.ihsi.rgph.mobile.constant.Constant;
import ht.ihsi.rgph.mobile.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.managers.QueryRecordMngr;
import ht.ihsi.rgph.mobile.utilities.Tools;

/**
 * Created by ajordany on 3/21/2016.
 */
public class EmigreModel extends BaseModel {

    private Long emigreId;
    private Long menageId;
    private Long logeId;
    private Long batimentId;
    private String sdeId;
    private Short qn1numeroOrdre;
    private String qn2aNomComplet;
    private Short qn2bSexe;
    private String qn2cAgeAuMomentDepart;
    private Short qn2dVivantToujours;
    private Short qn2eDernierPaysResidence;
    private Short statut;
    private Boolean isFieldAllFilled;
    private String dateDebutCollecte;
    private String dateFinCollecte;
    private Integer dureeSaisie;
    private String codeAgentRecenceur;
    private Boolean isVerified=false;

    //region MODEL OBJET
    public static QueryRecordMngr queryRecordMngr;
    private BatimentModel objBatiment;
    private LogementModel objLogement;
    private MenageModel objMenage;
    //endregion

    //region CONSTRUCTEURS
    public EmigreModel() {
        BlankData();
    }

    private void BlankData() {
        emigreId = Long.valueOf(0);
        batimentId = Long.valueOf(0);
        logeId = Long.valueOf(0);
        menageId = Long.valueOf(0);
    }
    //endregion

    //region EmigreModel getters and setters
    public Long getEmigreId() {
        return emigreId;
    }

    public void setEmigreId(Long emigreId) {
        this.emigreId = emigreId;
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

    public Short getQn1numeroOrdre() {
        return qn1numeroOrdre;
    }

    public void setQn1numeroOrdre(Short qn1numeroOrdre) {
        this.qn1numeroOrdre = qn1numeroOrdre;
    }

    public String getQn2aNomComplet() {
        return qn2aNomComplet;
    }

    public void setQn2aNomComplet(String qn2aNomComplet) {
        this.qn2aNomComplet = qn2aNomComplet;
    }

    public Short getQn2bSexe() {
        return qn2bSexe;
    }

    public void setQn2bSexe(Short qn2bSexe) {
        this.qn2bSexe = qn2bSexe;
    }

    public String getQn2cAgeAuMomentDepart() {
        return qn2cAgeAuMomentDepart;
    }

    public void setQn2cAgeAuMomentDepart(String qn2cAgeAuMomentDepart) {
        this.qn2cAgeAuMomentDepart = qn2cAgeAuMomentDepart;
    }

    public Short getQn2dVivantToujours() {
        return qn2dVivantToujours;
    }

    public void setQn2dVivantToujours(Short qn2dVivantToujours) {
        this.qn2dVivantToujours = qn2dVivantToujours;
    }

    public Short getQn2eDernierPaysResidence() {
        return qn2eDernierPaysResidence;
    }

    public void setQn2eDernierPaysResidence(Short qn2eDernierPaysResidence) {
        this.qn2eDernierPaysResidence = qn2eDernierPaysResidence;
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
    public static String Check_ContrainteSautChampsValeur( String nomChamps, EmigreModel emigreModel, Long iDKeys, Object dataBase)  throws TextEmptyException {
        try{
            String QSuivant = "";
            //N2D - KI LAJ {0} TE GENYEN LÈ LI TAP KITE PEYI A? [  ]
            if (nomChamps.equalsIgnoreCase(EmigreDao.Properties.Qn2cAgeAuMomentDepart.columnName)) {
            /* Si l'âge est connu, prévoir une liste déroulante d'âges allant de 000 (pour les moins d'un an) à 120.
            Si l'âge n'est pas connu, permettre à l'Agent Recenseur d'inscrire 999 */
                if ( emigreModel.getQn2cAgeAuMomentDepart() != null
                        && Integer.parseInt(emigreModel.getQn2cAgeAuMomentDepart()) != Constant.AGE_PA_KONNEN_999ANS
                        && Integer.parseInt(emigreModel.getQn2cAgeAuMomentDepart()) > Constant.AGE_120ANS ){
                    throw new TextEmptyException("Atansyon verifye laj ou antre a. Limit laj la dwe " + Constant.AGE_120ANS + " ane ");
                }
            }
            return QSuivant;
        } catch (Exception ex){
            throw ex;
        }
    }
    //endregion
}

