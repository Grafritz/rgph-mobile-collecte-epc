package ht.ihsi.rgph.mobile.epc.models;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ht.ihsi.rgph.mobile.epc.backend.entities.AncienMembreDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.IndividuDao;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.exceptions.ManagerException;
import ht.ihsi.rgph.mobile.epc.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.epc.managers.QueryRecordMngr;
import ht.ihsi.rgph.mobile.epc.utilities.ToastUtility;
import ht.ihsi.rgph.mobile.epc.utilities.Tools;

/**
 * Created by JFDuverseau on 6/18/2018.
 */

public class AncienMembreModel  extends BaseModel{

    //region ATTRIBUT
    private Long ancienMembreId;
    private Long menageId;
    private Long logeId;
    private Long batimentId;
    private String sdeId;
    private Short q1NoOrdre;
    private String qp2APrenom;
    private String qp2BNom;
    private Short qp4Sexe;
    private Short q5EstMortOuQuitter;
    private Short q6HabiteDansMenage;
    private Short q7DateQuitterMenageJour;
    private Short q7DateQuitterMenageMois;
    private Integer q7DateQuitterMenageAnnee;
    private Short q7bDateMouriJour;
    private Short q7bDateMouriMois;
    private Integer q7bDateMouriAnnee;
    private Short q8DateNaissanceJour;
    private Short q8DateNaissanceMois;
    private Integer q8DateNaissanceAnnee;
    private Integer q9Age;
    private Short q10LienDeParente;
    private Short q11Nationalite;
    private String q11PaysNationalite;
    private Short q12NiveauEtude;
    private Short q12StatutMatrimonial;
    private Short statut;
    private Boolean isValidated;
    private Boolean isFieldAllFilled;
    private String dateDebutCollecte;
    private String dateFinCollecte;
    private Integer dureeSaisie;
    private Boolean isContreEnqueteMade;
    private String codeAgentRecenceur;
    private Boolean isVerified=false;
//endregion

    //region VARIABLE SYSTEME
    private String q7JourMoisAnneeDateQuitterMenage;
    private String q7bJourMoisAnneeDateMouri;
    private String q8JourMoisAnneeDateNaissance;

    public boolean IsAgeIndividuVerify=true;
//endregion

    //region MODEL OBJET
    private BatimentModel objBatiment;
    private LogementModel objLogement;
    private MenageModel objMenage;
    public static QueryRecordMngr queryRecordMngr;
    //endregion

    public AncienMembreModel() {
        this.ancienMembreId = Long.valueOf(0);
        BlankProperties();
    }

    public AncienMembreModel(long ancienMembreId, short q1NoOrdre, String qp2APrenom, String qp2BNom) {
        this.ancienMembreId = ancienMembreId;
        this.q1NoOrdre = q1NoOrdre;
        this.qp2APrenom = qp2APrenom;
        this.qp2BNom = qp2BNom;
    }

    private void BlankProperties() {
        this.menageId = Long.valueOf(0);
        this.logeId = Long.valueOf(0);
        this.batimentId = Long.valueOf(0);
        this.ancienMembreId = Long.valueOf(0);
        this.menageId = Long.valueOf(0);
        this.logeId = Long.valueOf(0);
        this.batimentId = Long.valueOf(0);
        this.sdeId = "";
        this.q1NoOrdre = 0;
        this.qp2APrenom = "";
        this.qp2BNom = "";
        this.qp4Sexe = 0;
        this.q5EstMortOuQuitter = 0;
        this.q6HabiteDansMenage = 0;
        this.q7DateQuitterMenageJour = 0;
        this.q7DateQuitterMenageMois = 0;
        this.q7DateQuitterMenageAnnee = 0;
        this.q7bDateMouriJour = 0;
        this.q7bDateMouriMois = 0;
        this.q7bDateMouriAnnee = 0;
        this.q8DateNaissanceJour = 0;
        this.q8DateNaissanceMois = 0;
        this.q8DateNaissanceAnnee = 0;
        this.q9Age = 0;
        this.q10LienDeParente = 0;
        this.q11Nationalite = 0;
        this.q11PaysNationalite = "";
        this.q12NiveauEtude = 0;
        this.q12StatutMatrimonial = 0;
        this.statut = Constant.STATUT_MODULE_KI_PA_FINI_3;
        this.isValidated = false;
        this.isFieldAllFilled = false;
        this.dateDebutCollecte = "";
        this.dateFinCollecte = "";
        this.dureeSaisie = 0;
        this.isContreEnqueteMade = false;
        this.codeAgentRecenceur = "";
        this.isVerified = false;
    }

    // region |-| GETTER SETTER SYSTEME |-|
    public String getQ7JourMoisAnneeDateQuitterMenage() { // "MM-dd-yyyy"
        return q7DateQuitterMenageMois + "-" + q7DateQuitterMenageJour + "-" + q7DateQuitterMenageAnnee;
    }
    public void setQ7JourMoisAnneeDateQuitterMenage(String value) {
        try {
            /* Short qpDateNaissanceJour / Short qpDateNaissanceMois / Integer qpDateNaissanceAnnee;*/
            String[] JJMMAAAA = value.split("-"); // MM-dd-yyyy
            String mois  = JJMMAAAA[0]; // Mois
            String jour = JJMMAAAA[1];  // Jour
            String annee = JJMMAAAA[2]; // Annee
            q7DateQuitterMenageMois = Short.valueOf(mois);
            q7DateQuitterMenageJour = Short.valueOf(jour);
            q7DateQuitterMenageAnnee = Integer.valueOf(annee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getQ7bJourMoisAnneeDateMouri() { // "MM-dd-yyyy"
        return q7bDateMouriMois + "-" + q7bDateMouriJour + "-" + q7bDateMouriAnnee;
    }
    public void setQ7bJourMoisAnneeDateMouri(String value) {
        try {
            String[] JJMMAAAA = value.split("-"); // MM-dd-yyyy
            String mois  = JJMMAAAA[0]; // Mois
            String jour = JJMMAAAA[1];  // Jour
            String annee = JJMMAAAA[2]; // Annee
            q7bDateMouriMois = Short.valueOf(mois);
            q7bDateMouriJour = Short.valueOf(jour);
            q7bDateMouriAnnee = Integer.valueOf(annee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getQ8JourMoisAnneeDateNaissance() { // "MM-dd-yyyy"
        return q8DateNaissanceMois + "-" + q8DateNaissanceJour + "-" + q8DateNaissanceAnnee;
    }
    public void setQ8JourMoisAnneeDateNaissance(String value) {
        try {
            String[] JJMMAAAA = value.split("-"); // MM-dd-yyyy
            String mois  = JJMMAAAA[0]; // Mois
            String jour = JJMMAAAA[1];  // Jour
            String annee = JJMMAAAA[2]; // Annee
            q8DateNaissanceMois = Short.valueOf(mois);
            q8DateNaissanceJour = Short.valueOf(jour);
            q8DateNaissanceAnnee = Integer.valueOf(annee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//endregion///////////////////////////////////////////////

    //region IndividuModel getters and setters
    public Long getAncienMembreId() {
        return ancienMembreId;
    }

    public void setAncienMembreId(Long ancienMembreId) {
        this.ancienMembreId = ancienMembreId;
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

    public Short getQ1NoOrdre() {
        return q1NoOrdre;
    }

    public void setQ1NoOrdre(Short q1NoOrdre) {
        this.q1NoOrdre = q1NoOrdre;
    }

    public String getQp2APrenom() {
        return qp2APrenom;
    }

    public void setQp2APrenom(String qp2APrenom) {
        this.qp2APrenom = qp2APrenom;
    }

    public String getQp2BNom() {
        return qp2BNom;
    }

    public void setQp2BNom(String qp2BNom) {
        this.qp2BNom = qp2BNom;
    }

    public Short getQp4Sexe() {
        return qp4Sexe;
    }

    public void setQp4Sexe(Short qp4Sexe) {
        this.qp4Sexe = qp4Sexe;
    }

    public Short getQ5EstMortOuQuitter() {
        return q5EstMortOuQuitter;
    }

    public void setQ5EstMortOuQuitter(Short q5EstMortOuQuitter) {
        this.q5EstMortOuQuitter = q5EstMortOuQuitter;
    }

    public Short getQ6HabiteDansMenage() {
        return q6HabiteDansMenage;
    }

    public void setQ6HabiteDansMenage(Short q6HabiteDansMenage) {
        this.q6HabiteDansMenage = q6HabiteDansMenage;
    }

    public Short getQ7DateQuitterMenageJour() {
        return q7DateQuitterMenageJour;
    }

    public void setQ7DateQuitterMenageJour(Short q7DateQuitterMenageJour) {
        this.q7DateQuitterMenageJour = q7DateQuitterMenageJour;
    }

    public Short getQ7DateQuitterMenageMois() {
        return q7DateQuitterMenageMois;
    }

    public void setQ7DateQuitterMenageMois(Short q7DateQuitterMenageMois) {
        this.q7DateQuitterMenageMois = q7DateQuitterMenageMois;
    }

    public Integer getQ7DateQuitterMenageAnnee() {
        return q7DateQuitterMenageAnnee;
    }

    public void setQ7DateQuitterMenageAnnee(Integer q7DateQuitterMenageAnnee) {
        this.q7DateQuitterMenageAnnee = q7DateQuitterMenageAnnee;
    }

    public Short getQ7bDateMouriJour() {
        return q7bDateMouriJour;
    }

    public void setQ7bDateMouriJour(Short q7bDateMouriJour) {
        this.q7bDateMouriJour = q7bDateMouriJour;
    }

    public Short getQ7bDateMouriMois() {
        return q7bDateMouriMois;
    }

    public void setQ7bDateMouriMois(Short q7bDateMouriMois) {
        this.q7bDateMouriMois = q7bDateMouriMois;
    }

    public Integer getQ7bDateMouriAnnee() {
        return q7bDateMouriAnnee;
    }

    public void setQ7bDateMouriAnnee(Integer q7bDateMouriAnnee) {
        this.q7bDateMouriAnnee = q7bDateMouriAnnee;
    }

    public Short getQ8DateNaissanceJour() {
        return q8DateNaissanceJour;
    }

    public void setQ8DateNaissanceJour(Short q8DateNaissanceJour) {
        this.q8DateNaissanceJour = q8DateNaissanceJour;
    }

    public Short getQ8DateNaissanceMois() {
        return q8DateNaissanceMois;
    }

    public void setQ8DateNaissanceMois(Short q8DateNaissanceMois) {
        this.q8DateNaissanceMois = q8DateNaissanceMois;
    }

    public Integer getQ8DateNaissanceAnnee() {
        return q8DateNaissanceAnnee;
    }

    public void setQ8DateNaissanceAnnee(Integer q8DateNaissanceAnnee) {
        this.q8DateNaissanceAnnee = q8DateNaissanceAnnee;
    }

    public Integer getQ9Age() {
        return q9Age;
    }

    public void setQ9Age(Integer q9Age) {
        this.q9Age = q9Age;
    }

    public Short getQ10LienDeParente() {
        return q10LienDeParente;
    }

    public void setQ10LienDeParente(Short q10LienDeParente) {
        this.q10LienDeParente = q10LienDeParente;
    }

    public Short getQ11Nationalite() {
        return q11Nationalite;
    }

    public void setQ11Nationalite(Short q11Nationalite) {
        this.q11Nationalite = q11Nationalite;
    }

    public String getQ11PaysNationalite() {
        return q11PaysNationalite;
    }

    public void setQ11PaysNationalite(String q11PaysNationalite) {
        this.q11PaysNationalite = q11PaysNationalite;
    }

    public Short getQ12NiveauEtude() {
        return q12NiveauEtude;
    }

    public void setQ12NiveauEtude(Short q12NiveauEtude) {
        this.q12NiveauEtude = q12NiveauEtude;
    }

    public Short getQ12StatutMatrimonial() {
        return q12StatutMatrimonial;
    }

    public void setQ12StatutMatrimonial(Short q12StatutMatrimonial) {
        this.q12StatutMatrimonial = q12StatutMatrimonial;
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

    //region toString
    @Override
    public String toString() {
        return "[" + this.q1NoOrdre.toString() +"] "  + this.qp2APrenom.toString() +" "+ this.qp2BNom.toString().toUpperCase();
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

    public boolean getIsAgeIndividuVerify() {
        return IsAgeIndividuVerify;
    }

    public void setIsAgeIndividuVerify(boolean IsAgeIndividuVerify) {
        this.IsAgeIndividuVerify = IsAgeIndividuVerify;
    }
    //endregion

    //region METHODES
    public static void Check_ContrainteSautChampsValeur(AncienMembreModel individuModel)  throws TextEmptyException {
        try {
            //region P1A-P12.2 "KARAKTERISTIK MOUN NAN -|- POU TOUT MANM MENAJ LA -|- KARAKTERISTIK DEMOGRAFIK"
            //P3	KI RELASYON OSWA KISA {0} YE  POU CHÈF MENAJ LA?
            //if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Q10LienDeParente.columnName)){
                /* Le numéro d’ordre doit être unique.
                    Dans le cas d'un ménage, établir la relation entre P1 et P3, vérifier que si P1=01 alors P3 =01
                    Si P1=01 et que P3 ≥ 02 faites apparaitre un message d’erreur et permettre de vérifier M11 */
            Boolean isMounNanMenajLa = false;
            if( individuModel.getQ6HabiteDansMenage()==1 ||
                    individuModel.getQ6HabiteDansMenage()==3 ||
                    individuModel.getQ6HabiteDansMenage()==5 ||
                    individuModel.getQ6HabiteDansMenage()==6 ||
                    individuModel.getQ6HabiteDansMenage()==7 ) {
                isMounNanMenajLa=true;
            }
            if ( !isMounNanMenajLa && individuModel.getQ1NoOrdre() <= 1) {
                throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye.\n Ou dwe antre Chèf menaj la avan!");
            }

            if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() <= 1 &&
                    individuModel.getQ10LienDeParente() > Constant.Chef_menaj_la_01) {
                throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye. \n Ou dwe antre Chèf menaj la avan!");
            }
            if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() >= 2 &&
                    individuModel.getQ10LienDeParente() == Constant.Chef_menaj_la_01) {
                throw new TextEmptyException("Ou paka chwazi moun sa tou pou se chèf menaj la. \n Ou te antre Chèf menaj la avan!");
            }
            if ( isMounNanMenajLa && individuModel.getQ10LienDeParente() == Constant.Chef_menaj_la_01 &&
                    individuModel.getQ9Age() < Constant.AGE_15ANS) {
                throw new TextEmptyException("Menaj la dwe gen yon sèl chèf epi fòk li genyen " + Constant.AGE_15ANS + " lane aswa plis."
                        + "\n\nSètifye si se " + individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom()
                        + " ki chef menaj lan. ");
            }
            //}
            //P3	KI RELASYON OSWA KISA {0} YE  POU CHÈF MENAJ LA?
            //if ( nomChamps.equalsIgnoreCase(IndividuDao.Properties.Q10LienDeParente.columnName) ) {
            /* Le numéro d’ordre doit être unique.
                Dans le cas d'un ménage, établir la relation entre P1 et P3, vérifier que si P1=01 alors P3 =01
                Si P1=01 et que P3 ≥ 02 faites apparaitre un message d’erreur et permettre de vérifier M11 */
            //if (  isMounNanMenajLa && individuModel.getObjLogement() != null ) {
            // TEST POUR LOGEMENT INDIVIDUEL SEULEMENT
            if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() <= 1 &&
                    individuModel.getQ10LienDeParente() > Constant.Chef_menaj_la_01 ) {
                throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye. \n Ou dwe antre Chèf menaj la avan!");
            }
            if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() >= 2 &&
                    individuModel.getQ10LienDeParente() == Constant.Chef_menaj_la_01 ) {
                throw new TextEmptyException("Ou paka chwazi moun sa tou pou se chèf menaj la. \n Ou te antre Chèf menaj la avan!");
            }
            //}
            //}
            // P4 - ÈSKE {0} SE  ?  [  ]
            //if (nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp4Sexe.columnName)){
            if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() >= 2) {
                // ON RECHERCHE LES INFORMATIONS SUR LE CHEF DU MENAGE DEJA ENREGISTRER
                AncienMembreModel individuCM = null;
                if ( individuModel.getMenageId() != null && individuModel.getMenageId() > 0) {
                    individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getMenageId(), Constant.FORMULAIRE_MENAGE);
                }

                if (individuCM == null) {
                    throw new TextEmptyException("Ou pa te anregistre yon chef menaj. \n Ou te dwe antre Chèf menaj la avan!");
                } else {
                        /*  Si P4= 1 pour P3 = 01 alors P4 = 2 pour P3 = 02
                            Si P4=2  pour P3 = 01 alors P4= 1 pour P3 = 02
                            Dans le cas où le chef de ménage a un conjoint dans le ménage. Le sexe du chef de ménage et celui de son conjoint doit être différent */
                        /* Si P4= 1 pour P3 = 01 alors P4 = 1 pour P3 = 02 , de même si P4=2 pour P3=01 alors P4=2 pour P3=02, afficher un message d’erreur et permettre de vérifier l’information */
                    if (individuCM.getQp4Sexe() == individuModel.getQp4Sexe() &&
                            individuModel.getQ10LienDeParente() == Constant.Mari_Madanm_02) {
                        throw new TextEmptyException("Chef menaj la [ " + individuCM.getQp2BNom() + " " + individuCM.getQp2APrenom() + " ] paka gen menm sèks ak konjwen li  [ "
                                + individuModel.getQp2BNom() + " " + individuModel.getQp2APrenom() + " ].");
                    }
                }
                //}
                //}

                //region Etablir une relation entre l’âge du CM et celui de son (sa) fils/fille (Père/Mère).
                //if (individuModel.getQ1NoOrdre() >= 2) {
               /* // ON RECHERCHE LES INFORMATIONS SUR LE CHEF DU MENAGE DEJA ENREGISTRER
                IndividuModel individuCM = null;
                if (individuModel.getMenageId() != null && individuModel.getMenageId() > 0) {
                    individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getMenageId(), Constant.FORMULAIRE_MENAGE);
                }*/

                if (individuCM == null) {
                    throw new TextEmptyException("Ou pa te anregistre yon chef menaj. \n Ou te dwe antre Chèf menaj la avan!");
                } else {
                    int differenceceAge = 0;
                    String NomCompletCM = individuCM.getQp2APrenom() + " " + individuCM.getQp2BNom();
                    String NomCompletInd = individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom();
                    //#00 Verifye si chef menaj la pi gran ke papa li oswa manman li
                    /*if ( individuCM.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQp5bAge() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQ10LienDeParente() == Constant.Papa_Manman_06
                            && individuCM.getQp5bAge() > individuModel.getQp5bAge() ) {
                        throw new TextEmptyException("Atansyon, verifye laj [ " + NomCompletInd + " ], ou di li genyen [ " + individuModel.getQp5bAge() + " ] ane.\n"
                                + "Li pa dwe pi gran ke Chef Menaj la [ " + NomCompletCM + " ] ki genyen [ " + individuCM.getQp5bAge() + " ] ane." );
                    }*/
                    //#1 Si CM (P3=1) est une femme, la différence d'âge entre ce CM (P3=1) et le (la) fils/fille (P3=03) est >13 ans. Dans le cas contraire voir message
                    //differenceceAge = ( individuModel.getQp5bAge() - individuCM.getQp5bAge() );
                    if ( individuCM.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                            && individuCM.getQp4Sexe() == Constant.FEMME_2 &&
                            individuModel.getQ10LienDeParente() == Constant.Pitit_gason_Ou_Piti_fi_03 ){

                        differenceceAge = ( individuCM.getQ9Age() - individuModel.getQ9Age() );

                        if ( differenceceAge <= Constant.AGE_13ANS) {
                            throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQ9Age() + " ane ) "
                                    +"ak relasyon ke li genyen ak chef menaj la  [ " + NomCompletCM + " ] ( " + individuCM.getQ9Age() + " ane ), \n"
                                    + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_13ANS + " zan");
                        }
                    }
                    //#2 Si CM (P3=1) est un homme, la différence d'âge entre ce CM (P3=1) et le (la) fils/fille (P3=03) est >15 ans
                    if (individuCM.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                            && individuCM.getQp4Sexe() == Constant.HOMME_1
                            && individuModel.getQ10LienDeParente() == Constant.Pitit_gason_Ou_Piti_fi_03) {

                        differenceceAge = ( individuCM.getQ9Age() - individuModel.getQ9Age() );

                        if ( differenceceAge <= Constant.AGE_15ANS ) {
                            throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQ9Age() + " ane ) "
                                    +"ak relasyon ke li genyen ak chef menaj la  [ " + NomCompletCM + " ] ( " + individuCM.getQ9Age() + " ane ), \n"
                                    + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_15ANS + " zan");
                        }
                    }

                    //#3 Si CM (P3=1) est une femme, la différence d'âge entre ce CM (P3=1) et le (la) petit (e) fils/fille (P3=07) est >28 ans
                    if ( individuCM.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                            && individuCM.getQp4Sexe() == Constant.FEMME_2 &&
                            individuModel.getQ10LienDeParente() == Constant.Pitit_Pitit_Gason_Ou_Pitit_Pitit_fi_07 ){

                        differenceceAge = ( individuCM.getQ9Age() - individuModel.getQ9Age() );

                        if( differenceceAge <= Constant.AGE_28ANS) {
                            throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQ9Age() + " ane ) "
                                    +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQ9Age() + " ane ), \n"
                                    + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_28ANS + " zan");
                        }
                    }
                    //#4 Si CM (P3=1) est un homme, la différence d'âge entre ce CM (P3=1) et le (la) petit (e) fils/fille (P3=07)  est >32 ans
                    if ( individuCM.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                            && individuCM.getQp4Sexe() == Constant.HOMME_1 &&
                            individuModel.getQ10LienDeParente() == Constant.Pitit_Pitit_Gason_Ou_Pitit_Pitit_fi_07 ){

                        differenceceAge = ( individuCM.getQ9Age() - individuModel.getQ9Age() );

                        if(  differenceceAge <= Constant.AGE_32ANS) {
                            throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQ9Age() + " ane ) "
                                    +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQ9Age() + " ane ), \n"
                                    + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_32ANS + " zan");
                        }
                    }
                    //#5 Si CM (P3=1) est une femme, la différence d'âge entre le (la) père/mère (P3=06) et ce Chef du Ménage (P3=1)   est >13 ans
                    if ( individuCM.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                            && individuCM.getQp4Sexe() == Constant.FEMME_2
                            && individuModel.getQ10LienDeParente() == Constant.Papa_Manman_06 ){

                        differenceceAge = ( individuModel.getQ9Age() - individuCM.getQ9Age() );

                        if(  differenceceAge <= Constant.AGE_13ANS ) {
                            throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQ9Age() + " ane ) "
                                    +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQ9Age() + " ane ), \n"
                                    + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_13ANS + " zan");
                        }
                    }
                    //#6 Si CM (P3=1) est un homme, la différence d'âge entre le (la) père/mère (P3=06) et ce Chef du Ménage est >15 ans
                    if ( individuCM.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                            && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                            && individuCM.getQp4Sexe() == Constant.HOMME_1
                            && individuModel.getQ10LienDeParente() == Constant.Papa_Manman_06){

                        differenceceAge = ( individuModel.getQ9Age() - individuCM.getQ9Age() );

                        if(  differenceceAge <= Constant.AGE_15ANS ) {
                            throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQ9Age() + " ane ) "
                                    +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQ9Age() + " ane ), \n"
                                    + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_15ANS + " zan");
                        }
                    }
                }

            }
                    /*//#1 Si CM est une femme alors la différence d'âge entre le CM et le (la) fils/fille est >13 ans
                    //#2 Si CM est un  homme alors la différence d'âge entre le CM et le (la) fils/fille est >15 ans
                    //#3 Si CM est une femme alors la différence d'âge entre le CM et le (la) petit (e) fils/fille est >28 ans
                    //#4 Si CM est un homme alors la différence d'âge entre le CM et le (la) petit (e) fils/fille est >32 ans
                    //#5 Si CM est une femme alors la différence d'âge entre le (la) père/mère et le Chef du Ménage est >13 ans
                    //#6 Si CM est un homme alors la différence d'âge entre le (la) père/mère et le Chef du Ménage est >15 ans
                    // (même remarque pour le conjoint et père du conjoint=15 et conjoint et mère du conjoint=13) */

            //endregion

        } catch (Exception ex) {
            throw ex;
        }
    }

    public static String Check_ContrainteSautChampsValeur(String nomChamps, AncienMembreModel individuModel, Long iDKeys, Object dataBase, Boolean ExpulseException)  throws TextEmptyException  {
        try {
            String QSuivant = "";
            //region P1A-P12.2 "KARAKTERISTIK MOUN NAN -|- POU TOUT MANM MENAJ LA -|- KARAKTERISTIK DEMOGRAFIK"
            if ( individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_KOLEKTIF ) {
                if ( nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp2BNom.columnName) ) {
                    individuModel.IsAgeIndividuVerify=true;
                    QSuivant = "P3.1";
                }
            }
            //P3.1	Eske se isit la (....) Toujou rete sa vle di se la li dómi, manje avèk tout lót moun ?
            if ( nomChamps.equalsIgnoreCase(AncienMembreDao.Properties.Q6HabiteDansMenage.columnName) ) {
                individuModel.IsAgeIndividuVerify=true;
                Boolean isMounNanMenajLa = false;
                if( individuModel.getQ6HabiteDansMenage()!=null && individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL){
                    if( individuModel.getQ6HabiteDansMenage()==1 ||
                            individuModel.getQ6HabiteDansMenage()==3 ||
                            individuModel.getQ6HabiteDansMenage()==5 ||
                            individuModel.getQ6HabiteDansMenage()==6 ||
                            individuModel.getQ6HabiteDansMenage()==7) {
                        isMounNanMenajLa=true;
                    }
                }

                if ( !isMounNanMenajLa && individuModel.getQ1NoOrdre() <= 1 &&
                        individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye.\n Ou dwe antre Chèf menaj la avan!");
                }

                if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() <= 1 &&
                        individuModel.getQ10LienDeParente() > Constant.Chef_menaj_la_01 &&
                        individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye. \n Ou dwe antre Chèf menaj la avan!");
                }
                if ( isMounNanMenajLa && individuModel.getQ1NoOrdre() >= 2 &&
                        individuModel.getQ10LienDeParente() == Constant.Chef_menaj_la_01 &&
                        individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    throw new TextEmptyException("Ou paka chwazi moun sa tou pou se chèf menaj la. \n Ou te antre Chèf menaj la avan!");
                }
                if ( isMounNanMenajLa && individuModel.getQ10LienDeParente()!=null &&
                        individuModel.getQ10LienDeParente() == Constant.Chef_menaj_la_01
                        && individuModel.getQ9Age()!=null &&  individuModel.getQ9Age() <= Constant.AGE_15ANS
                        && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                        && individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL ) {
                    throw new TextEmptyException("Menaj la dwe gen yon sèl chèf epi fòk li genyen " + Constant.AGE_15ANS + " lane aswa plis."
                            + "\n\nSètifye si se " + individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom()
                            + " ki chef menaj lan. ");
                }
            }
            //P3	KI RELASYON OSWA KISA {0} YE  POU CHÈF MENAJ LA?
            if ( nomChamps.equalsIgnoreCase(AncienMembreDao.Properties.Q10LienDeParente.columnName) ) {
                individuModel.IsAgeIndividuVerify=true;
            /* Le numéro d’ordre doit être unique.
                Dans le cas d'un ménage, établir la relation entre P1 et P3, vérifier que si P1=01 alors P3 =01
                Si P1=01 et que P3 ≥ 02 faites apparaitre un message d’erreur et permettre de vérifier M11 */
                if ( ExpulseException==true && individuModel.getObjLogement() != null &&
                        individuModel.getObjLogement().getQlCategLogement() != null &&
                        individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    // TEST POUR LOGEMENT INDIVIDUEL SEULEMENT
                    if ( ExpulseException==true && individuModel.getQ1NoOrdre() <= 1 &&
                            individuModel.getQ10LienDeParente() > Constant.Chef_menaj_la_01) {
                        throw new TextEmptyException("Moun sa a se chèf menaj la pou li ye. \n Ou dwe antre Chèf menaj la avan!");
                    }
                    if ( ExpulseException==true && individuModel.getQ1NoOrdre() >= 2 &&
                            individuModel.getQ10LienDeParente() == Constant.Chef_menaj_la_01) {
                        throw new TextEmptyException("Ou paka chwazi moun sa tou pou se chèf menaj la. \n Ou te antre Chèf menaj la avan!");
                    }
                }
            }
            // P4 - ÈSKE {0} SE  ?  [  ]
            if ( nomChamps.equalsIgnoreCase(IndividuDao.Properties.Qp4Sexe.columnName) ) {
                String NomCompletInd = individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom();
                individuModel.IsAgeIndividuVerify=true;

                if ( ExpulseException==true && individuModel.getObjLogement() != null &&
                        individuModel.getObjLogement().getQlCategLogement() != null &&
                        individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    // TEST POUR LOGEMENT INDIVIDUEL SEULEMENT
                    if ( ExpulseException==true && individuModel.getQ1NoOrdre() >= 2) {
                        // ON RECHERCHE LES INFORMATIONS SUR LE CHEF DU MENAGE DEJA ENREGISTRER
                        AncienMembreModel individuCM = null;
                        if ( ExpulseException==true && individuModel.getMenageId() != null && individuModel.getMenageId() > 0) {
                            individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getMenageId(), Constant.FORMULAIRE_MENAGE);
                        } else if ( ExpulseException==true && individuModel.getLogeId() != null && individuModel.getLogeId() > 0) {
                            //individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getLogeId(), Constant.FORMULAIRE_LOGEMENT_COLLECTIF);
                        }

                        if ( ExpulseException==true && individuCM == null) {
                            throw new TextEmptyException("Ou pa te anregistre yon chef menaj. \n Ou te dwe antre Chèf menaj la avan!");
                        } else {
                            String NomCompletCM = individuCM.getQp2APrenom() + " " + individuCM.getQp2BNom();
                        /*  Si P4= 1 pour P3 = 01 alors P4 = 2 pour P3 = 02
                            Si P4=2  pour P3 = 01 alors P4= 1 pour P3 = 02
                            Dans le cas où le chef de ménage a un conjoint dans le ménage. Le sexe du chef de ménage et celui de son conjoint doit être différent */
                        /* Si P4= 1 pour P3 = 01 alors P4 = 1 pour P3 = 02 , de même si P4=2 pour P3=01 alors P4=2 pour P3=02, afficher un message d’erreur et permettre de vérifier l’information */
                            if ( ExpulseException==true && individuCM.getQp4Sexe() == individuModel.getQp4Sexe() &&
                                    individuModel.getQ10LienDeParente() == Constant.Mari_Madanm_02) {
                                throw new TextEmptyException("Chef menaj la [ " + NomCompletCM + " ] paka gen menm sèks ak konjwen li  [ "
                                        + NomCompletInd + " ].");
                            }
                        }
                    }
                }
            }

            //region P5.A - Ki dat {0} te fet ?  [  ]
            // endregion

            //region P5 - KI LAJ {0} GENYEN ? [  ]
            if (nomChamps.equalsIgnoreCase(AncienMembreDao.Properties.Q9Age.columnName)) {
                int annee =0;
                annee = individuModel.getQ8DateNaissanceAnnee();
                Calendar mydate = new GregorianCalendar();
                int anneeSysteme = mydate.get(Calendar.YEAR);
                String NomCompletInd = individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom();

                if ( ExpulseException==true && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS ) { // Si moun nan konn ane , si li pa 9999
                    if ( individuModel.getQ9Age() > Constant.AGE_120ANS ) {
                        individuModel.IsAgeIndividuVerify=true;
                        throw new TextEmptyException("Atansyon verifye laj ou antre a. Limit laj la dwe " + Constant.AGE_120ANS + " ane ");
                    }

                    int ageDiff = ( anneeSysteme - annee );
                    if ( annee != Constant.ANNEE_PA_KONNEN_9999ANS ) { // Si moun nan konn ane , si li pa 9999
                        if( individuModel.IsAgeIndividuVerify ) {
                            if ( individuModel.getQ9Age() != ageDiff) {
                                individuModel.IsAgeIndividuVerify=false;
                                String _message = "Atansyon verifye laj ou antre a ak dènye lè ["+ NomCompletInd +"] te fete anivèsè li.";
                                _message += "\n\nPaske pou systèm nan moun sa ta dwe genyen [ " + ageDiff + " ] ane si li fèt nan ane [ " + annee + " ]";
                                _message +=  "\n\nTandiske w antre [ " + individuModel.getQ9Age() + " ] ane pou moun sa a.";
                                _message += "\n\nSi w vle kite l konsa retouche bouton [Kontinye] a.";
                                throw new TextEmptyException(_message);
                            }
                        }
                    }
                }
                //QF.CheckAgeDialog(sp_JourIndividu, sp_MoisIndividu, sp_AnneeIndividu, sp_AgeIndividu);
            }//endregion

            if ( ExpulseException==true && individuModel.getObjLogement() != null &&
                    individuModel.getObjLogement().getQlCategLogement() != null &&
                    individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                // TEST POUR LOGEMENT INDIVIDUEL SEULEMENT
                //P5 - KI LAJ {0} GENYEN ? [  ]
                if (nomChamps.equalsIgnoreCase(AncienMembreDao.Properties.Q9Age.columnName)) {
                /* Permettre que P3 = « 01 » est toujours attribué au chef de ménage et égal à son numéro d’ordre P1 = « 01 ».


                    Etablir une relation entre P3 et P5.
                    Si P3=01 alors P5≥15 ans
                    Si P.3 = 01 et P.5 ≤ 15 ans, afficher un message d’erreur permettant de vérifier les informations */
                    if ( ExpulseException==true && individuModel.getQ10LienDeParente() == Constant.Chef_menaj_la_01 &&
                            individuModel.getQ9Age() < Constant.AGE_15ANS ) {
                        throw new TextEmptyException("Menaj la dwe gen yon sèl chèf epi fòk li genyen " + Constant.AGE_15ANS + " lane aswa plis."
                                + "\n\nSètifye si se " + individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom()
                                + " ki chef menaj lan. ");
                    }

                    //region Etablir une relation entre l’âge du CM et celui de son (sa) fils/fille (Père/Mère).
                    if (individuModel.getQ1NoOrdre() >= 2) {
                        // ON RECHERCHE LES INFORMATIONS SUR LE CHEF DU MENAGE DEJA ENREGISTRER
                        AncienMembreModel individuCM = null;
                        if (individuModel.getMenageId() != null && individuModel.getMenageId() > 0) {
                            individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getMenageId(), Constant.FORMULAIRE_MENAGE);
                        } //else if (individuModel.getLogeId() != null && individuModel.getLogeId() > 0) {
                        //    individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getLogeId(), Constant.FORMULAIRE_LOGEMENT_COLLECTIF);
                        //}

                        if (individuCM == null) {
                            throw new TextEmptyException("Ou pa te anregistre yon chef menaj. \n Ou te dwe antre Chèf menaj la avan!");
                        } else {
                            int differenceceAge = 0;
                            String NomCompletCM = individuCM.getQp2APrenom() + " " + individuCM.getQp2BNom();
                            String NomCompletInd = individuModel.getQp2APrenom() + " " + individuModel.getQp2BNom();
                            //#1 Si CM (P3=1) est une femme, la différence d'âge entre ce CM (P3=1) et le (la) fils/fille (P3=03) est >13 ans. Dans le cas contraire voir message
                            //differenceceAge = ( individuModel.getQ9Age() - individuCM.getQ9Age() );
                            if ( individuCM.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuCM.getQp4Sexe() == Constant.FEMME_2 &&
                                    individuModel.getQ10LienDeParente() == Constant.Pitit_gason_Ou_Piti_fi_03 ){

                                differenceceAge = ( individuCM.getQ9Age() - individuModel.getQ9Age() );

                                if ( differenceceAge <= Constant.AGE_13ANS) {
                                    throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQ9Age() + " ane ) "
                                            +"ak relasyon ke li genyen ak chef menaj la  [ " + NomCompletCM + " ] ( " + individuCM.getQ9Age() + " ane ), \n"
                                            + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_13ANS + " zan");
                                }
                            }
                            //#2 Si CM (P3=1) est un homme, la différence d'âge entre ce CM (P3=1) et le (la) fils/fille (P3=03) est >15 ans
                            if (individuCM.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuCM.getQp4Sexe() == Constant.HOMME_1
                                    && individuModel.getQ10LienDeParente() == Constant.Pitit_gason_Ou_Piti_fi_03) {

                                differenceceAge = ( individuCM.getQ9Age() - individuModel.getQ9Age() );

                                if ( differenceceAge <= Constant.AGE_15ANS ) {
                                    throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQ9Age() + " ane ) "
                                            +"ak relasyon ke li genyen ak chef menaj la  [ " + NomCompletCM + " ] ( " + individuCM.getQ9Age() + " ane ), \n"
                                            + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_15ANS + " zan");
                                }
                            }
                            //#3 Si CM (P3=1) est une femme, la différence d'âge entre ce CM (P3=1) et le (la) petit (e) fils/fille (P3=07) est >28 ans
                            if ( individuCM.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuCM.getQp4Sexe() == Constant.FEMME_2 &&
                                    individuModel.getQ10LienDeParente() == Constant.Pitit_Pitit_Gason_Ou_Pitit_Pitit_fi_07 ){

                                differenceceAge = ( individuCM.getQ9Age() - individuModel.getQ9Age() );

                                if( differenceceAge <= Constant.AGE_28ANS) {
                                    throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQ9Age() + " ane ) "
                                            +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQ9Age() + " ane ), \n"
                                            + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_28ANS + " zan");
                                }
                            }
                            //#4 Si CM (P3=1) est un homme, la différence d'âge entre ce CM (P3=1) et le (la) petit (e) fils/fille (P3=07)  est >32 ans
                            if ( individuCM.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuCM.getQp4Sexe() == Constant.HOMME_1 &&
                                    individuModel.getQ10LienDeParente() == Constant.Pitit_Pitit_Gason_Ou_Pitit_Pitit_fi_07){

                                differenceceAge = ( individuCM.getQ9Age() - individuModel.getQ9Age() );

                                if(  differenceceAge <= Constant.AGE_32ANS) {
                                    throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQ9Age() + " ane ) "
                                            +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQ9Age() + " ane ), \n"
                                            + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_32ANS + " zan");
                                }
                            }
                            //#5 Si CM (P3=1) est une femme, la différence d'âge entre le (la) père/mère (P3=06) et ce Chef du Ménage (P3=1)   est >13 ans
                            if ( individuCM.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuCM.getQp4Sexe() == Constant.FEMME_2
                                    && individuModel.getQ10LienDeParente() == Constant.Papa_Manman_06 ){

                                differenceceAge = ( individuModel.getQ9Age() - individuCM.getQ9Age() );

                                if(  differenceceAge <= Constant.AGE_13ANS ) {
                                    throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQ9Age() + " ane ) "
                                            +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQ9Age() + " ane ), \n"
                                            + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_13ANS + " zan");
                                }
                            }
                            //#6 Si CM (P3=1) est un homme, la différence d'âge entre le (la) père/mère (P3=06) et ce Chef du Ménage est >15 ans
                            if ( individuCM.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuModel.getQ9Age() != Constant.AGE_PA_KONNEN_999ANS
                                    && individuCM.getQp4Sexe() == Constant.HOMME_1
                                    && individuModel.getQ10LienDeParente() == Constant.Papa_Manman_06){

                                differenceceAge = ( individuModel.getQ9Age() - individuCM.getQ9Age() );

                                if(  differenceceAge <= Constant.AGE_15ANS ) {
                                    throw new TextEmptyException("Tanpri, verifye laj moun sa [ " + NomCompletInd + " ] ( " + individuModel.getQ9Age() + " ane ) "
                                            +"ak relasyon ke li genyen ak chef menaj la [ " + NomCompletCM + " ] ( " + individuCM.getQ9Age() + " ane ), \n"
                                            + "Diferans laj ant de moun sa yo dwe pi piti ke " + Constant.AGE_15ANS + " zan");
                                }
                            }
                        }

                    }
                    //endregion

                }
            }
            //endregion

            //region E1-E8 KARAKTERISTIK MOUN NAN -|- EDIKASYON -|- POU MOUN KI GEN 3 LANE OSWA PLIS */
            //region E4A Ki pi wo nivo {0} te rive nan lekol / inivesite?
            if( nomChamps.equalsIgnoreCase( Constant.Qe4ANiveauEtudeETClasse ) ){
                /* Ki denye klas oswa ki denye lane {0} te pase (reyisi) nan nivo sa a? */
                if(individuModel.getQ9Age() < Constant.AGE_05ANS ){
                    /* KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON NAN ZAFE WE */
                    QSuivant =  Constant.FIN;
                }
            }//endregion
            //endregion

            //region SM1 Pou moun ki genyen dis (10) lane epi plis -|- ESTATI MATRIMONYAL
            if ( nomChamps.equalsIgnoreCase(IndividuDao.Properties.Q12StatutMatrimonial.columnName) ){
                if ( ExpulseException==true && individuModel.getObjLogement() != null &&
                        individuModel.getObjLogement().getQlCategLogement() != null &&
                        individuModel.getObjLogement().getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                    // TEST POUR LOGEMENT INDIVIDUEL SEULEMENT

                      /* TEST POU CM la selman */
                    if ( ExpulseException==true && individuModel.getQ1NoOrdre() == 1 ) {
                        int countNbrMadanmCM = 0;
                        if ( ExpulseException==true && individuModel.getMenageId() != null && individuModel.getMenageId() > 0) {
                            /* Verifye si CM la gen madan  deja */
                            countNbrMadanmCM = GetCountMarie_Ou_Madanm( Constant.Mari_Madanm_02, individuModel.getMenageId(), Constant.FORMULAIRE_MENAGE);

                        } else if ( ExpulseException==true && individuModel.getLogeId() != null && individuModel.getLogeId() > 0) {
                            /* Verifye si CM la gen madan  deja */
                            countNbrMadanmCM = GetCountMarie_Ou_Madanm( Constant.Mari_Madanm_02, individuModel.getLogeId(), Constant.FORMULAIRE_LOGEMENT_COLLECTIF);
                        }
                         /* Anpeche ke yo chwazi stati selibate=1 ak Veuf=5 pou CM la si li gen madanm ou mari */
                        if( countNbrMadanmCM > 0 ){
                            if ( ExpulseException==true
                                    && individuModel.getQ12StatutMatrimonial() == Constant.Selibate_01  ) {
                                throw new TextEmptyException("Atansyon!!! Chèf menaj pa ka Selibatè.  \nPaske li genyen yon konjwen kap viv avèk li nan menaj la.");
                            }
                            if ( ExpulseException==true
                                    && individuModel.getQ12StatutMatrimonial() == Constant.Veuf_05  ) {
                                throw new TextEmptyException("Atansyon!!! Chèf menaj pa ka Vèv \nPaske li genyen yon konjwen kap viv avèk li nan menaj la. ");
                            }
                        }
                    }
                /* Etablir la relation entre P.4, P.3, SM.1 */
                    if ( ExpulseException==true && individuModel.getQ1NoOrdre() >= 2) {
                        // ON RECHERCHE LES INFORMATIONS SUR LE CHEF DU MENAGE DEJA ENREGISTRER
                        AncienMembreModel individuCM = null;
                        if ( ExpulseException==true && individuModel.getMenageId() != null && individuModel.getMenageId() > 0) {
                            individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getMenageId(), Constant.FORMULAIRE_MENAGE);

                        } else if ( ExpulseException==true && individuModel.getLogeId() != null && individuModel.getLogeId() > 0) {
                            //individuCM = GetIndividu(1, Constant.Chef_menaj_la_01, individuModel.getLogeId(), Constant.FORMULAIRE_LOGEMENT_COLLECTIF);
                        }

                        if ( ExpulseException==true && individuCM == null) {
                            throw new TextEmptyException("Ou pa te anregistre yon chef menaj. \n Ou te dwe antre Chèf menaj la avan!");
                        } else {

                        /* Si P.4 = 2 pour P.3 = 01 et SM.1 = 2, 3 ou 4, alors P.4 = 1 pour P.3 =02 en admettant que le conjoint soit dans le ménage
                        Le statut matrimonial du Chef de ménage doit être le même que celui de son conjoint.
                        Afficher un  message d’erreur si SM.1 pour  P.3 = 01 ≠ SM.1 pour P3 = 02 et permettre à l’Agent Recenseur de vérifier et de corriger l’information enregistrée. */
                            if ( ExpulseException==true
                                    && individuCM.getQ12StatutMatrimonial() != individuModel.getQ12StatutMatrimonial()
                                    && individuModel.getQ10LienDeParente() == Constant.Mari_Madanm_02) {
                                throw new TextEmptyException("Chef menaj la fet pou gen menm estati matrimonyal ak konjwen li depi yo tou le de nan menaj la. \n\n"
                                        + "Ou te di Chef menaj la : [ " + Tools.getString_Reponse("" + individuCM.getQ12StatutMatrimonial(), IndividuDao.Properties.Q12StatutMatrimonial.columnName) + " ] ");
                            }
                        }
                    }
                }
            }
            //endregion
            return QSuivant;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private static AncienMembreModel GetIndividu(int noOrdre, int idLienDeParente, long idMenageOuLogement, int Type_FORMULAIRE) {
        try {
            if( Type_FORMULAIRE == Constant.FORMULAIRE_MENAGE ) {
                return queryRecordMngr.searchAncienMembre_ByNoOrdre_ByIdLienDeParente_ByIdMenage(noOrdre, idLienDeParente, idMenageOuLogement);
            }else if( Type_FORMULAIRE == Constant.FORMULAIRE_LOGEMENT_COLLECTIF ) {
                //return queryRecordMngr.searchIndividu_ByNoOrdre_ByIdLienDeParente_ByIdLogement(noOrdre, idLienDeParente, idMenageOuLogement);
            }
            return null;
        } catch (ManagerException e) {
            ToastUtility.LogCat( "ManagerException: GetIndividu() :", e);
            return null;
        }
    }

    private static int GetCountMarie_Ou_Madanm(int idLienDeParente, long idMenageOuLogement, int Type_FORMULAIRE) {
        try {
            if( Type_FORMULAIRE == Constant.FORMULAIRE_MENAGE ) {
                return queryRecordMngr.CountMarie_Ou_Madanm_ByIdLienDeParente_ByIdMenage( idLienDeParente, idMenageOuLogement);
            }else if( Type_FORMULAIRE == Constant.FORMULAIRE_LOGEMENT_COLLECTIF ) {
                return queryRecordMngr.CountMarie_Ou_Madanm_ByIdLienDeParente_ByIdLogement( idLienDeParente, idMenageOuLogement);
            }
            return 0;
        } catch (ManagerException e) {
            ToastUtility.LogCat( "ManagerException: GetCountMarie_Ou_Madanm():", e);
            return 0;
        }
    }

    public static IndividuModel GetIndividu(int noOrdre, long idMenage) {
        try {
            return queryRecordMngr.searchIndividu_ByNoOrdre_ByIdMenage(noOrdre, idMenage,false);
        } catch (ManagerException e) {
            ToastUtility.LogCat("E", "ManagerException: GetIndividu() | getMessage:" + e.getMessage() + " /n toString:" + e.toString());
            return null;
        }
    }
    //endregion
}
