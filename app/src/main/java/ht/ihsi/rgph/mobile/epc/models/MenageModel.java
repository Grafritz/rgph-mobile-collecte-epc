package ht.ihsi.rgph.mobile.epc.models;

import ht.ihsi.rgph.mobile.epc.backend.entities.MenageDao;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.epc.managers.QueryRecordMngr;
import ht.ihsi.rgph.mobile.epc.utilities.Tools;

/**
 * Created by ajordany on 3/21/2016.
 */
public class MenageModel extends BaseModel{

    //region ATTIBUTS
    private Long menageId;
    private Long logeId;
    private Long batimentId;
    private String sdeId;
    private Short qm1NoOrdre;
    private Short qm2ModeJouissance;
    private Short qm3ModeObtentionLoge;
    private Short qm4_1ModeAprovEauABoire;
    private Short qm4_2ModeAprovEauAUsageCourant;
    private Short qm5SrcEnergieCuisson1;
    private Short qm5SrcEnergieCuisson2;
    private Short qm6TypeEclairage;
    private Short qm7ModeEvacDechet;
    private Short qm8EndroitBesoinPhysiologique;
    private Integer qm9NbreRadio1;
    private Integer qm9NbreTelevision2;
    private Integer qm9NbreRefrigerateur3;
    private Integer qm9NbreFouElectrique4;
    private Integer qm9NbreOrdinateur5;
    private Integer qm9NbreMotoBicyclette6;
    private Integer qm9NbreVoitureMachine7;
    private Integer qm9NbreBateau8;
    private Integer qm9NbrePanneauGeneratrice9;
    private Integer qm9NbreMilletChevalBourique10;
    private Integer qm9NbreBoeufVache11;
    private Integer qm9NbreCochonCabrit12;
    private Integer qm9NbreBeteVolaille13;
    private Short qm10AvoirPersDomestique;
    private Short qm10TotalDomestiqueFille;
    private Short qm10TotalDomestiqueGarcon;
    private Integer qm11TotalIndividuVivant;
    private Short qn1Emigration;
    private Short qn1NbreEmigre;
    private Short qd1Deces;
    private Short qd1NbreDecede;
    private Short statut;
    private Boolean isValidated;
    private String dateDebutCollecte;
    private String dateFinCollecte;
    private Integer dureeSaisie;
    private Boolean isFieldAllFilled;
    private Boolean isContreEnqueteMade;
    private String codeAgentRecenceur;
    private Boolean isVerified=false;
    //endregion

    //region VARIABLE SYSTEME
    private String qm4ModeAprobEauBoireEtUsageCourant;
    private String qm5SrcEnergieCuison1Et2;
    private String qm9NbrAppareilsEtAnimaux;
    private String qm10TotalDomestiqueGaconEtFille;
    private String qm11CallFormListeIndividu;
    private String callFormulaireEmigre;
    private String callFormulaireDeces;
    private String callFormulaireIndividuMenage;
    //endregion

    //region MODEL OBJET
    public static QueryRecordMngr queryRecordMngr;
    private BatimentModel objBatiment;
    private LogementModel objLogement;
    //endregion

    //region CONSTRUCTEURS
    public MenageModel() {
        BlankData();
    }

    private void BlankData() {
        menageId = Long.valueOf(0);
        batimentId = Long.valueOf(0);
        logeId = Long.valueOf(0);
        qn1NbreEmigre = 0;
        qd1NbreDecede = 0;
        qm11TotalIndividuVivant = 0;
        qm10TotalDomestiqueGarcon = 0;
        qm10TotalDomestiqueFille = 0;
    }
    //endregion

    //region MenageModel getters and setters
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

    public Short getQm1NoOrdre() {
        return qm1NoOrdre;
    }

    public void setQm1NoOrdre(Short qm1NoOrdre) {
        this.qm1NoOrdre = qm1NoOrdre;
    }

    public Short getQm2ModeJouissance() {
        return qm2ModeJouissance;
    }

    public void setQm2ModeJouissance(Short qm2ModeJouissance) {
        this.qm2ModeJouissance = qm2ModeJouissance;
    }

    public Short getQm3ModeObtentionLoge() {
        return qm3ModeObtentionLoge;
    }

    public void setQm3ModeObtentionLoge(Short qm3ModeObtentionLoge) {
        this.qm3ModeObtentionLoge = qm3ModeObtentionLoge;
    }

    public Short getQm4_1ModeAprovEauABoire() {
        return qm4_1ModeAprovEauABoire;
    }

    public void setQm4_1ModeAprovEauABoire(Short qm4_1ModeAprovEauABoire) {
        this.qm4_1ModeAprovEauABoire = qm4_1ModeAprovEauABoire;
    }

    public Short getQm4_2ModeAprovEauAUsageCourant() {
        return qm4_2ModeAprovEauAUsageCourant;
    }

    public void setQm4_2ModeAprovEauAUsageCourant(Short qm4_2ModeAprovEauAUsageCourant) {
        this.qm4_2ModeAprovEauAUsageCourant = qm4_2ModeAprovEauAUsageCourant;
    }

    public Short getQm5SrcEnergieCuisson1() {
        return qm5SrcEnergieCuisson1;
    }

    public void setQm5SrcEnergieCuisson1(Short qm5SrcEnergieCuisson1) {
        this.qm5SrcEnergieCuisson1 = qm5SrcEnergieCuisson1;
    }

    public Short getQm5SrcEnergieCuisson2() {
        return qm5SrcEnergieCuisson2;
    }

    public void setQm5SrcEnergieCuisson2(Short qm5SrcEnergieCuisson2) {
        this.qm5SrcEnergieCuisson2 = qm5SrcEnergieCuisson2;
    }

    public Short getQm6TypeEclairage() {
        return qm6TypeEclairage;
    }

    public void setQm6TypeEclairage(Short qm6TypeEclairage) {
        this.qm6TypeEclairage = qm6TypeEclairage;
    }

    public Short getQm7ModeEvacDechet() {
        return qm7ModeEvacDechet;
    }

    public void setQm7ModeEvacDechet(Short qm7ModeEvacDechet) {
        this.qm7ModeEvacDechet = qm7ModeEvacDechet;
    }

    public Short getQm8EndroitBesoinPhysiologique() {
        return qm8EndroitBesoinPhysiologique;
    }

    public void setQm8EndroitBesoinPhysiologique(Short qm8EndroitBesoinPhysiologique) {
        this.qm8EndroitBesoinPhysiologique = qm8EndroitBesoinPhysiologique;
    }

    public Integer getQm9NbreRadio1() {
        return qm9NbreRadio1;
    }

    public void setQm9NbreRadio1(Integer qm9NbreRadio1) {
        this.qm9NbreRadio1 = qm9NbreRadio1;
    }

    public Integer getQm9NbreTelevision2() {
        return qm9NbreTelevision2;
    }

    public void setQm9NbreTelevision2(Integer qm9NbreTelevision2) {
        this.qm9NbreTelevision2 = qm9NbreTelevision2;
    }

    public Integer getQm9NbreRefrigerateur3() {
        return qm9NbreRefrigerateur3;
    }

    public void setQm9NbreRefrigerateur3(Integer qm9NbreRefrigerateur3) {
        this.qm9NbreRefrigerateur3 = qm9NbreRefrigerateur3;
    }

    public Integer getQm9NbreFouElectrique4() {
        return qm9NbreFouElectrique4;
    }

    public void setQm9NbreFouElectrique4(Integer qm9NbreFouElectrique4) {
        this.qm9NbreFouElectrique4 = qm9NbreFouElectrique4;
    }

    public Integer getQm9NbreOrdinateur5() {
        return qm9NbreOrdinateur5;
    }

    public void setQm9NbreOrdinateur5(Integer qm9NbreOrdinateur5) {
        this.qm9NbreOrdinateur5 = qm9NbreOrdinateur5;
    }

    public Integer getQm9NbreMotoBicyclette6() {
        return qm9NbreMotoBicyclette6;
    }

    public void setQm9NbreMotoBicyclette6(Integer qm9NbreMotoBicyclette6) {
        this.qm9NbreMotoBicyclette6 = qm9NbreMotoBicyclette6;
    }

    public Integer getQm9NbreVoitureMachine7() {
        return qm9NbreVoitureMachine7;
    }

    public void setQm9NbreVoitureMachine7(Integer qm9NbreVoitureMachine7) {
        this.qm9NbreVoitureMachine7 = qm9NbreVoitureMachine7;
    }

    public Integer getQm9NbreBateau8() {
        return qm9NbreBateau8;
    }

    public void setQm9NbreBateau8(Integer qm9NbreBateau8) {
        this.qm9NbreBateau8 = qm9NbreBateau8;
    }

    public Integer getQm9NbrePanneauGeneratrice9() {
        return qm9NbrePanneauGeneratrice9;
    }

    public void setQm9NbrePanneauGeneratrice9(Integer qm9NbrePanneauGeneratrice9) {
        this.qm9NbrePanneauGeneratrice9 = qm9NbrePanneauGeneratrice9;
    }

    public Integer getQm9NbreMilletChevalBourique10() {
        return qm9NbreMilletChevalBourique10;
    }

    public void setQm9NbreMilletChevalBourique10(Integer qm9NbreMilletChevalBourique10) {
        this.qm9NbreMilletChevalBourique10 = qm9NbreMilletChevalBourique10;
    }

    public Integer getQm9NbreBoeufVache11() {
        return qm9NbreBoeufVache11;
    }

    public void setQm9NbreBoeufVache11(Integer qm9NbreBoeufVache11) {
        this.qm9NbreBoeufVache11 = qm9NbreBoeufVache11;
    }

    public Integer getQm9NbreCochonCabrit12() {
        return qm9NbreCochonCabrit12;
    }

    public void setQm9NbreCochonCabrit12(Integer qm9NbreCochonCabrit12) {
        this.qm9NbreCochonCabrit12 = qm9NbreCochonCabrit12;
    }

    public Integer getQm9NbreBeteVolaille13() {
        return qm9NbreBeteVolaille13;
    }

    public void setQm9NbreBeteVolaille13(Integer qm9NbreBeteVolaille13) {
        this.qm9NbreBeteVolaille13 = qm9NbreBeteVolaille13;
    }

    public Short getQm10AvoirPersDomestique() {
        return qm10AvoirPersDomestique;
    }

    public void setQm10AvoirPersDomestique(Short qm10AvoirPersDomestique) {
        this.qm10AvoirPersDomestique = qm10AvoirPersDomestique;
    }

    public Short getQm10TotalDomestiqueFille() {
        return qm10TotalDomestiqueFille;
    }

    public void setQm10TotalDomestiqueFille(Short qm10TotalDomestiqueFille) {
        this.qm10TotalDomestiqueFille = qm10TotalDomestiqueFille;
    }

    public Short getQm10TotalDomestiqueGarcon() {
        return qm10TotalDomestiqueGarcon;
    }

    public void setQm10TotalDomestiqueGarcon(Short qm10TotalDomestiqueGarcon) {
        this.qm10TotalDomestiqueGarcon = qm10TotalDomestiqueGarcon;
    }

    public Integer getQm11TotalIndividuVivant() {
        return qm11TotalIndividuVivant;
    }

    public void setQm11TotalIndividuVivant(Integer qm11TotalIndividuVivant) {
        this.qm11TotalIndividuVivant = qm11TotalIndividuVivant;
    }

    public Short getQn1Emigration() {
        return qn1Emigration;
    }

    public void setQn1Emigration(Short qn1Emigration) {
        this.qn1Emigration = qn1Emigration;
    }

    public Short getQn1NbreEmigre() {
        return qn1NbreEmigre;
    }

    public void setQn1NbreEmigre(Short qn1NbreEmigre) {
        this.qn1NbreEmigre = qn1NbreEmigre;
    }

    public Short getQd1Deces() {
        return qd1Deces;
    }

    public void setQd1Deces(Short qd1Deces) {
        this.qd1Deces = qd1Deces;
    }

    public Short getQd1NbreDecede() {
        return qd1NbreDecede;
    }

    public void setQd1NbreDecede(Short qd1NbreDecede) {
        this.qd1NbreDecede = qd1NbreDecede;
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

    public String getCodeAgentRecenceur() {
        return codeAgentRecenceur;
    }

    public void setCodeAgentRecenceur(String codeAgentRecenceur) {
        this.codeAgentRecenceur = codeAgentRecenceur;
    }
    //endregion

    // region |-| GETTER SETTER SYSTEME |-|
    public String getQm4ModeAprobEauBoireEtUsageCourant() {// "00-00"
        return qm4_1ModeAprovEauABoire + "-" + qm4_2ModeAprovEauAUsageCourant;
    }
    public void setQm4ModeAprobEauBoireEtUsageCourant(String value) {
        try {
            String[] AprobEauBoireEtUsageCourant = value.split("-"); // 00-00
            String ModeAprovEauABoire  = AprobEauBoireEtUsageCourant[0]; // Mode Aprov Eau A Boire
            String ModeAprovEauAUsageCourant = AprobEauBoireEtUsageCourant[1];  // Mode Aprov Eau A Usage Courant
            qm4_1ModeAprovEauABoire = Short.valueOf(ModeAprovEauABoire);
            qm4_2ModeAprovEauAUsageCourant = Short.valueOf(ModeAprovEauAUsageCourant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getQm5SrcEnergieCuison1Et2() {// "00-00"
        return qm5SrcEnergieCuisson1 + "-" + qm5SrcEnergieCuisson2;
    }
    public void setQm5SrcEnergieCuison1Et2(String value) {
        try {
            String[] SrcEnergieCuison1Et2 = value.split("-"); // 00-00
            String SrcEnergieCuisson1  = SrcEnergieCuison1Et2[0]; // Src Energie Cuisson 1
            String SrcEnergieCuisson2 = SrcEnergieCuison1Et2[1];  // Src Energie Cuisson 2
            qm5SrcEnergieCuisson1 = Short.valueOf(SrcEnergieCuisson1);
            qm5SrcEnergieCuisson2 = Short.valueOf(SrcEnergieCuisson2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getQm9NbrAppareilsEtAnimaux() {// "00-00"
        String result = qm9NbreRadio1 + "-" + qm9NbreTelevision2
                + "-" + qm9NbreRefrigerateur3 + "-" + qm9NbreFouElectrique4
                + "-" + qm9NbreOrdinateur5 + "-" + qm9NbreMotoBicyclette6
                + "-" + qm9NbreVoitureMachine7 + "-" + qm9NbreBateau8
                + "-" + qm9NbrePanneauGeneratrice9 + "-" + qm9NbreMilletChevalBourique10
                + "-" + qm9NbreBoeufVache11 + "-" + qm9NbreCochonCabrit12 + "-" + qm9NbreBeteVolaille13;
        return result;
    }
    public void setQm9NbrAppareilsEtAnimaux(String value) {
        try {
            String[] nbrItems = value.split("-");
            String nbrItem  = nbrItems[0]; //
            qm9NbreRadio1 = Integer.valueOf(nbrItem);

            nbrItem  = nbrItems[1]; //
            qm9NbreTelevision2 = Integer.valueOf(nbrItem);

            nbrItem  = nbrItems[2]; //
            qm9NbreRefrigerateur3 = Integer.valueOf(nbrItem);

            nbrItem  = nbrItems[3]; //
            qm9NbreFouElectrique4 = Integer.valueOf(nbrItem);

            nbrItem  = nbrItems[4]; //
            qm9NbreOrdinateur5 = Integer.valueOf(nbrItem);

            nbrItem  = nbrItems[5]; //
            qm9NbreMotoBicyclette6 = Integer.valueOf(nbrItem);

            nbrItem  = nbrItems[6]; //
            qm9NbreVoitureMachine7 = Integer.valueOf(nbrItem);

            nbrItem  = nbrItems[7]; //
            qm9NbreBateau8 = Integer.valueOf(nbrItem);

            nbrItem  = nbrItems[8]; //
            qm9NbrePanneauGeneratrice9 = Integer.valueOf(nbrItem);

            nbrItem  = nbrItems[9]; //
            qm9NbreMilletChevalBourique10 = Integer.valueOf(nbrItem);

            nbrItem  = nbrItems[10]; //
            qm9NbreBoeufVache11 = Integer.valueOf(nbrItem);

            nbrItem  = nbrItems[11]; //
            qm9NbreCochonCabrit12 = Integer.valueOf(nbrItem);

            nbrItem  = nbrItems[12]; //
            qm9NbreBeteVolaille13 = Integer.valueOf(nbrItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getQm10TotalDomestiqueGaconEtFille() {// "00-00"
        return  qm10TotalDomestiqueGarcon + "-" + qm10TotalDomestiqueFille;
    }
    public void setQm10TotalDomestiqueGaconEtFille(String value) {
        try {
            String[] nbrItems = value.split("-");
            String nbrItem  = nbrItems[0]; //
            qm10TotalDomestiqueGarcon = Short.valueOf(nbrItem);
            nbrItem  = nbrItems[1]; //
            qm10TotalDomestiqueFille = Short.valueOf(nbrItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getQm11CallFormListeIndividu() {
        return qm11CallFormListeIndividu;
    }
    public void setQm11CallFormListeIndividu(String qm11CallFormListeIndividu) {
        this.qm11CallFormListeIndividu = qm11CallFormListeIndividu;
    }
    public String getCallFormulaireEmigre() {
        return callFormulaireEmigre;
    }
    public void setCallFormulaireEmigre(String callFormulaireEmigre) {
        this.callFormulaireEmigre = callFormulaireEmigre;
    }
    public String getCallFormulaireDeces() {
        return callFormulaireDeces;
    }
    public void setCallFormulaireDeces(String callFormulaireDeces) {
        this.callFormulaireDeces = callFormulaireDeces;
    }
    public String getCallFormulaireIndividuMenage() {
        return callFormulaireIndividuMenage;
    }
    public void setCallFormulaireIndividuMenage(String callFormulaireIndividuMenage) {
        this.callFormulaireIndividuMenage = callFormulaireIndividuMenage;
    }
    //endregion

    //region Model getters and setters
    public BatimentModel getObjBatiment() {
        return objBatiment;
    }

    public void setObjBatiment(BatimentModel objBatiment) {
        this.objBatiment = objBatiment;
    }

    public LogementModel getObjLogement() {
        return objLogement;
    }

    public void setObjLogement(LogementModel objLogement) {
        this.objLogement = objLogement;
    }
//endregion

    //region METHODES
    public static String Check_ContrainteSautChampsValeur( String nomChamps, MenageModel menageModel, Long iDKeys, Object dataBase)  throws TextEmptyException {
        try{
            String QSuivant = "";

            // Qm10 Total Domestique Garcon et Total Domestique Fille
            if (nomChamps.equalsIgnoreCase(Constant.Qm10TotalDomestiqueGaconEtFille)) {
                if(menageModel.getQm10TotalDomestiqueGarcon()!=null && menageModel.getQm10TotalDomestiqueFille()!=null
                        && menageModel.getQm10TotalDomestiqueGarcon() <= 0 && menageModel.getQm10TotalDomestiqueFille() <= 0){
                    throw new TextEmptyException("Kantite domestik gason oubyen kantite fi pa ka zero...");
                }
            }
            if (nomChamps.equalsIgnoreCase(MenageDao.Properties.Qm11TotalIndividuVivant.columnName)) {
                long Nbre_Individu_DejaSave=0;
                if( menageModel.getQm11TotalIndividuVivant()!=null && menageModel.getQm11TotalIndividuVivant() <= 0 ){
                    QSuivant = "N1";
                }
                if ( menageModel.getMenageId()!=null && menageModel.getMenageId() > 0 ) {
                    Nbre_Individu_DejaSave = queryRecordMngr.countIndByMenage(menageModel.getMenageId());
                }
                if(menageModel.getQm11TotalIndividuVivant()!=null && menageModel.getQm11TotalIndividuVivant() < Nbre_Individu_DejaSave ){
                    throw new TextEmptyException("Ou paka retire nan kantite ke ou te mete a. "
                            + "\n  paske ou gentan anregistre [" + Nbre_Individu_DejaSave + "] moun deja pou menaj sa.");
                }
            }

            //M5.1	AK KI SA YO PLIS SEVI POU KWIT MANJE ?  (Bay tip ki pi sèvi a anvan)
            if (nomChamps.equalsIgnoreCase( MenageDao.Properties.Qm5SrcEnergieCuisson2.columnName )) {
                //if (nomChamps.equalsIgnoreCase( Constant.Qm5SrcEnergieCuison1Et2 )) {
            /* Vérifier que source 1 ≠ source 2 .
                Si source 1 = source 2, afficher un message d'erreur et permettre à l'Agent Recenseur de  corriger pour pouvoir continuer.
                Permettre à l’Agent Recenseur d’inscrire « 00 » dans les autres cases vides s’il s’agit d’une seule source d’énergie pour la cuisson. */
                if ( menageModel.getQm5SrcEnergieCuisson1()!=null && menageModel.getQm5SrcEnergieCuisson1() == 99 ){
                    throw new TextEmptyException("Repons ou Chwazi pou 1e sous la pa bon [" + Tools.getString_Reponse("99", MenageDao.Properties.Qm5SrcEnergieCuisson1.columnName) + "] .");
                }
                if ( menageModel.getQm5SrcEnergieCuisson1()!=null && menageModel.getQm5SrcEnergieCuisson2()!=null
                        && menageModel.getQm5SrcEnergieCuisson1() == menageModel.getQm5SrcEnergieCuisson2() ){
                    throw new TextEmptyException("Paka gen de menm sous deneji pou fè manje. Si se yon sèl sous ki genyen, "
                            + " Chwazi [" + Tools.getString_Reponse("99", MenageDao.Properties.Qm5SrcEnergieCuisson1.columnName) + "] pou dezyèm sous la."
                            + "\n \n Ou te chwazi : " + Tools.getString_Reponse(""+ menageModel.getQm5SrcEnergieCuisson1(), MenageDao.Properties.Qm5SrcEnergieCuisson1.columnName));
                }
            }

            // EMIGRER
           if (nomChamps.equalsIgnoreCase(MenageDao.Properties.Qn1NbreEmigre.columnName)) {
                if( menageModel.getMenageId()!=null &&  menageModel.getMenageId() > 0 &&  menageModel.getQn1NbreEmigre()!=null ) {
                    long nbreEmigreFilleSave = queryRecordMngr.countEmigrerByMenage(menageModel.getMenageId());
                    if ( nbreEmigreFilleSave > menageModel.getQn1NbreEmigre()) {
                        throw new TextEmptyException("Ou gentan antre " + nbreEmigreFilleSave + " Emigre."
                                + "\n \n Ou ka ajoute men ou paka retire sou kantite sa a.");
                    }
                }
            }
             /*if (nomChamps.equalsIgnoreCase(MenageDao.Properties.Qn1NbreEmigreGarcon.columnName)) {
                if( menageModel.getQn1NbreEmigreFille() <= 0 && menageModel.getQn1NbreEmigreGarcon() <= 0){
                    throw new TextEmptyException("Kantite gason oubyen kantite fi Emigre pa ka zero...");
                }
                if( menageModel.getMenageId() > 0) {
                    long nbreEmigreGarconSave = queryRecordMngr.countEmigrerByMenageBySexe(menageModel.getMenageId(), Constant.HOMME_1);
                    if ( nbreEmigreGarconSave > menageModel.getQn1NbreEmigreGarcon()) {
                        throw new TextEmptyException("Ou gentan antre " + nbreEmigreGarconSave + " gason ki Emigre."
                                + "\n \n Ou ka ajoute men ou paka retire sou kantite sa a.");
                    }
                }
            }*/

            // DECES
            if (nomChamps.equalsIgnoreCase(MenageDao.Properties.Qd1NbreDecede.columnName)) {
                if( menageModel.getMenageId() > 0 &&  menageModel.getQd1NbreDecede()!=null ) {
                    long nbreDecesFilleSave = queryRecordMngr.countDecesByMenage(menageModel.getMenageId());
                    if ( nbreDecesFilleSave > menageModel.getQd1NbreDecede()) {
                        throw new TextEmptyException("Ou gentan antre " + nbreDecesFilleSave + " moun ki mouri."
                                + "\n \n Ou ka ajoute men ou paka retire sou kantite sa a.");
                    }
                }
            }
            /*if (nomChamps.equalsIgnoreCase(MenageDao.Properties.Qd1NbreDecedeGarcon.columnName)) {
                if( menageModel.getQd1NbreDecedeFille() <= 0 && menageModel.getQd1NbreDecedeGarcon() <= 0){
                    throw new TextEmptyException("Kantite gason ou kantite fi ki mouri pa ka zero...");
                }
                if( menageModel.getMenageId() > 0) {
                    long nbreDecesGarconSave = queryRecordMngr.countDecesByMenageBySexe(menageModel.getMenageId(), Constant.HOMME_1);
                    if ( nbreDecesGarconSave > menageModel.getQd1NbreDecedeGarcon()) {
                        throw new TextEmptyException("Ou gentan antre " + nbreDecesGarconSave + " Gason ki mouri."
                                + "\n \n Ou ka ajoute men ou paka retire sou kantite sa a.");
                    }
                }
            }*/
            return QSuivant;
        } catch (Exception ex){
            throw ex;
        }
    }
    //endregion
}
