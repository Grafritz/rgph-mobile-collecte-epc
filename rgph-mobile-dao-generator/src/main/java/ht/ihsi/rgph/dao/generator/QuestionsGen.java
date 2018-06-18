package ht.ihsi.rgph.dao.generator;

import de.greenrobot.daogenerator.Entity;

/**
 * Created by ajordany on 3/16/2016.
 */
public class QuestionsGen {

    //region DYNAMIC QUESTION

    public static void createPersonnelEntity(Entity entity){
        entity.addLongProperty("persId").columnName("persId").primaryKey();
        entity.addStringProperty("sdeId").columnName("sdeId");
        entity.addStringProperty("nom").columnName("nom");
        entity.addStringProperty("prenom").columnName("prenom");
        entity.addStringProperty("sexe").columnName("sexe");
        entity.addStringProperty("nomUtilisateur").columnName("nomUtilisateur");
        entity.addStringProperty("motDePasse").columnName("motDePasse");
        entity.addStringProperty("email").columnName("email");
        entity.addStringProperty("deptId").columnName("deptId");
        entity.addStringProperty("comId").columnName("comId");
        entity.addStringProperty("vqseId").columnName("vqseId");
        entity.addStringProperty("zone").columnName("zone");
        entity.addStringProperty("codeDistrict").columnName("codeDistrict");
        entity.addBooleanProperty("estActif").columnName("estActif");
        entity.addIntProperty("ProfileId").columnName("ProfileId");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
    }

    public static void createCategorieQuestionEntity(Entity entity){
        entity.addStringProperty("codeCategorie").columnName("codeCategorie").unique().notNull();
        entity.addStringProperty("categorieQuestion").columnName("categorieQuestion").notNull();
        entity.addStringProperty("detailsCategorie").columnName("detailsCategorie");
        entity.addStringProperty("sousDetailsCategorie").columnName("sousDetailsCategorie");
    }

    public static void createQuestionsEntity(Entity entity){
        entity.addStringProperty("codeQuestion").columnName("codeQuestion").unique().notNull();
        entity.addStringProperty("libelle").columnName("libelle");
        entity.addStringProperty("detailsQuestion").columnName("detailsQuestion");
        entity.addStringProperty("IndicationsQuestion").columnName("IndicationsQuestion");
        entity.addStringProperty("codeCategorie").columnName("codeCategorie");
        entity.addStringProperty("nomChamps").columnName("nomChamps");
        entity.addIntProperty("typeQuestion").columnName("typeQuestion");
        entity.addIntProperty("caratereAccepte").columnName("caratereAccepte");
        entity.addIntProperty("nbreValeurMinimal").columnName("nbreValeurMinimal");
        entity.addIntProperty("nbreCaratereMaximal").columnName("nbreCaratereMaximal");
        entity.addStringProperty("contrainteSautChampsValeur").columnName("contrainteSautChampsValeur");
        entity.addBooleanProperty("estSautReponse").columnName("estSautReponse");
        entity.addStringProperty("qPrecedent").columnName("qPrecedent");
        entity.addStringProperty("qSuivant").columnName("qSuivant");
    }

    /*public static void createReponseEntity(Entity entity){
        entity.addStringProperty("codeUniqueReponse").columnName("codeUniqueReponse").index().notNull();
        entity.addStringProperty("codeReponse").columnName("codeReponse").index();
        entity.addStringProperty("libelleReponse").columnName("libelleReponse");
    }*/

    public static void createQuestionReponseEntity(Entity entity){
        entity.addStringProperty("codeQuestion").columnName("codeQuestion").index().notNull();
        entity.addStringProperty("codeUniqueReponse").columnName("codeUniqueReponse").index().notNull();
        entity.addStringProperty("codeReponse").columnName("codeReponse").index().notNull();
        entity.addStringProperty("libelleReponse").columnName("libelleReponse");
        entity.addBooleanProperty("estEnfant").columnName("estEnfant");
        entity.addBooleanProperty("avoirEnfant").columnName("avoirEnfant");
        entity.addStringProperty("codeParent").columnName("codeParent");
        entity.addStringProperty("qPrecedent").columnName("qPrecedent");
        entity.addStringProperty("qSuivant").columnName("qSuivant");
    }

    public static void createModuleEntity(Entity entity){
        entity.addStringProperty("codeModule").columnName("codeModule").unique().index().notNull();
        entity.addStringProperty("nomModule").columnName("nomModule");
        entity.addIntProperty("typeModule").columnName("typeModule");
        entity.addStringProperty("description").columnName("description");
        entity.addBooleanProperty("estActif").columnName("estActif");
    }

    public static void createQuestionModuleEntity(Entity entity){
        entity.addStringProperty("ordre").columnName("ordre");
        entity.addStringProperty("codeModule").columnName("codeModule").index().notNull();
        entity.addStringProperty("codeQuestion").columnName("codeQuestion").index().notNull();
        entity.addBooleanProperty("estDebut").columnName("estDebut");
    }

    public static void createDepartementEntity(Entity entity){
        entity.addStringProperty("DeptId").columnName("DeptId").unique().notNull();
        entity.addStringProperty("DeptNom").columnName("DeptNom");
    }

    public static void createCommuneEntity(Entity entity){
        entity.addStringProperty("ComId").columnName("ComId").unique().notNull();
        entity.addStringProperty("ComNom").columnName("ComNom");
        entity.addStringProperty("DeptId").columnName("DeptId").notNull();
    }

    public static void createVqseEntity(Entity entity){
        entity.addStringProperty("VqseId").columnName("VqseId").unique().notNull();
        entity.addStringProperty("VqseNom").columnName("VqseNom");
        entity.addStringProperty("ComId").columnName("ComId").notNull();
    }

    public static void createDomainEtudeEntity(Entity entity){
        entity.addStringProperty("Code").columnName("Code").unique().notNull();
        entity.addStringProperty("NomDomaine").columnName("NomDomaine");
    }

    public static void createPaysEntity(Entity entity){
        entity.addStringProperty("CodePays").columnName("CodePays").unique().notNull();
        entity.addStringProperty("NomPays").columnName("NomPays");
    }


    //endregion

    //region COLLECTE OBJECT
    public static void createBatimentEntity(Entity entity){
        entity.addLongProperty("batimentId").columnName("batimentId").primaryKey().autoincrement();
        entity.addStringProperty("deptId").columnName("deptId");
        entity.addStringProperty("comId").columnName("comId");
        entity.addStringProperty("vqseId").columnName("vqseId");
        entity.addStringProperty("sdeId").columnName("sdeId");
        entity.addShortProperty("zone").columnName("zone");
        entity.addStringProperty("disctrictId").columnName("disctrictId");
        entity.addStringProperty("qhabitation").columnName("qhabitation");
        entity.addStringProperty("qlocalite").columnName("qlocalite");
        entity.addStringProperty("qadresse").columnName("qadresse");
        entity.addStringProperty("qrec").columnName("qrec");
        entity.addStringProperty("qepc").columnName("qepc");
        entity.addShortProperty("qb1Etat").columnName("qb1Etat");
        entity.addShortProperty("qb2Type").columnName("qb2Type");
        entity.addShortProperty("qb3StatutOccupation").columnName("qb3StatutOccupation");
        entity.addShortProperty("qb4NbreLogeIndividuel").columnName("qb4NbreLogeIndividuel");
        entity.addShortProperty("statut").columnName("statut");
        entity.addStringProperty("dateEnvoi").columnName("dateEnvoi");
        entity.addBooleanProperty("isValidated").columnName("isValidated");
        entity.addBooleanProperty("isSynchroToAppSup").columnName("isSynchroToAppSup");
        entity.addBooleanProperty("isSynchroToCentrale").columnName("isSynchroToCentrale");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
        entity.addIntProperty("dureeSaisie").columnName("dureeSaisie");
        entity.addBooleanProperty("isFieldAllFilled").columnName("isFieldAllFilled");
        entity.addBooleanProperty("isContreEnqueteMade").columnName("isContreEnqueteMade");
        entity.addStringProperty("latitude").columnName("latitude");
        entity.addStringProperty("longitude").columnName("longitude");
        entity.addStringProperty("codeAgentRecenceur").columnName("codeAgentRecenceur");
        entity.addBooleanProperty("isVerified").columnName("isVerified");
    }

    public static void createLogementEntity(Entity entity){
        entity.addLongProperty("logeId").columnName("logeId").primaryKey().autoincrement();
        entity.addLongProperty("batimentId").columnName("batimentId");
        entity.addStringProperty("sdeId").columnName("sdeId");
        entity.addShortProperty("qlCategLogement").columnName("qlCategLogement");
        entity.addShortProperty("qlin1NumeroOrdre").columnName("qlin1NumeroOrdre");
        entity.addShortProperty("qlin2StatutOccupation").columnName("qlin2StatutOccupation");
        entity.addShortProperty("qlin3TypeLogement").columnName("qlin3TypeLogement");
        entity.addShortProperty("qlin4IsHaveIndividuDepense").columnName("qlin4IsHaveIndividuDepense");
        entity.addShortProperty("qlin5NbreTotalMenage").columnName("qlin5NbreTotalMenage");
        entity.addShortProperty("statut").columnName("statut");
        entity.addBooleanProperty("isValidated").columnName("isValidated");
        entity.addBooleanProperty("isFieldAllFilled").columnName("isFieldAllFilled");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
        entity.addIntProperty("dureeSaisie").columnName("dureeSaisie");
        entity.addBooleanProperty("isContreEnqueteMade").columnName("isContreEnqueteMade");
        entity.addShortProperty("nbrTentative").columnName("nbrTentative");
        entity.addStringProperty("codeAgentRecenceur").columnName("codeAgentRecenceur");
        entity.addBooleanProperty("isVerified").columnName("isVerified");
    }

    public static void createMenageEntity(Entity entity){
        entity.addLongProperty("menageId").columnName("menageId").primaryKey().autoincrement();
        entity.addLongProperty("logeId").columnName("logeId");
        entity.addLongProperty("batimentId").columnName("batimentId");
        entity.addStringProperty("sdeId").columnName("sdeId");
        entity.addShortProperty("qm1NoOrdre").columnName("qm1NoOrdre");
        entity.addShortProperty("qm2TotalIndividuVivant").columnName("qm2TotalIndividuVivant");
        entity.addShortProperty("qm22IsHaveAncienMembre").columnName("qm22IsHaveAncienMembre");
        entity.addShortProperty("qm22TotalAncienMembre").columnName("qm22TotalAncienMembre");
        entity.addShortProperty("statut").columnName("statut");
        entity.addBooleanProperty("isValidated").columnName("isValidated");
        entity.addBooleanProperty("isFieldAllFilled").columnName("isFieldAllFilled");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
        entity.addIntProperty("dureeSaisie").columnName("dureeSaisie");
        entity.addBooleanProperty("isContreEnqueteMade").columnName("isContreEnqueteMade");
        entity.addStringProperty("codeAgentRecenceur").columnName("codeAgentRecenceur");
        entity.addBooleanProperty("isVerified").columnName("isVerified");
    }

    public static void createAncienMembreEntity(Entity entity){
        entity.addLongProperty("ancienMembreId").columnName("ancienMembreId").primaryKey().autoincrement();
        entity.addLongProperty("menageId").columnName("menageId");
        entity.addLongProperty("logeId").columnName("logeId");
        entity.addLongProperty("batimentId").columnName("batimentId");
        entity.addStringProperty("sdeId").columnName("sdeId");
        entity.addShortProperty("q1NoOrdre").columnName("q1NoOrdre");
        entity.addStringProperty("qp2APrenom").columnName("qp2APrenom");
        entity.addStringProperty("qp2BNom").columnName("qp2BNom");
        entity.addShortProperty("qp4Sexe").columnName("qp4Sexe");
        entity.addShortProperty("q5EstMortOuQuitter").columnName("q5EstMortOuQuitter");
        entity.addShortProperty("q6HabiteDansMenage").columnName("q6HabiteDansMenage");
        entity.addShortProperty("q7DateQuitterMenageJour").columnName("q7DateQuitterMenageJour");
        entity.addShortProperty("q7DateQuitterMenageMois").columnName("q7DateQuitterMenageMois");
        entity.addIntProperty("q7DateQuitterMenageAnnee").columnName("q7DateQuitterMenageAnnee");
        entity.addShortProperty("q7bDateMouriJour").columnName("q7bDateMouriJour");
        entity.addShortProperty("q7bDateMouriMois").columnName("q7bDateMouriMois");
        entity.addIntProperty("q7bDateMouriAnnee").columnName("q7bDateMouriAnnee");
        entity.addShortProperty("q8DateNaissanceJour").columnName("q8DateNaissanceJour");
        entity.addShortProperty("q8DateNaissanceMois").columnName("q8DateNaissanceMois");
        entity.addIntProperty("q8DateNaissanceAnnee").columnName("q8DateNaissanceAnnee");
        entity.addIntProperty("q9Age").columnName("q9Age");
        entity.addShortProperty("q10LienDeParente").columnName("q10LienDeParente");
        entity.addShortProperty("q11Nationalite").columnName("q11Nationalite");
        entity.addStringProperty("q11PaysNationalite").columnName("q11PaysNationalite");
        entity.addShortProperty("q12NiveauEtude").columnName("q12NiveauEtude");
        entity.addShortProperty("q12StatutMatrimonial").columnName("q12StatutMatrimonial");
        entity.addShortProperty("statut").columnName("statut");
        entity.addBooleanProperty("isValidated").columnName("isValidated");
        entity.addBooleanProperty("isFieldAllFilled").columnName("isFieldAllFilled");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
        entity.addIntProperty("dureeSaisie").columnName("dureeSaisie");
        entity.addBooleanProperty("isContreEnqueteMade").columnName("isContreEnqueteMade");
        entity.addStringProperty("codeAgentRecenceur").columnName("codeAgentRecenceur");
        entity.addBooleanProperty("isVerified").columnName("isVerified");
    }

    public static void createIndividuEntity(Entity entity){
        entity.addLongProperty("individuId").columnName("individuId").primaryKey().autoincrement();
        entity.addLongProperty("menageId").columnName("menageId");
        entity.addLongProperty("logeId").columnName("logeId");
        entity.addLongProperty("batimentId").columnName("batimentId");
        entity.addStringProperty("sdeId").columnName("sdeId");
        entity.addShortProperty("q1NoOrdre").columnName("q1NoOrdre");
        entity.addStringProperty("qp2APrenom").columnName("qp2APrenom");
        entity.addStringProperty("qp2BNom").columnName("qp2BNom");
        entity.addShortProperty("qp4Sexe").columnName("qp4Sexe");
        entity.addShortProperty("q5HabiteDansMenage").columnName("q5HabiteDansMenage");
        entity.addShortProperty("q6aMembreMenageDepuisQuand").columnName("q6aMembreMenageDepuisQuand");
        entity.addShortProperty("q6bDateMembreMenageJour").columnName("q6bDateMembreMenageJour");
        entity.addShortProperty("q6bDateMembreMenageMois").columnName("q6bDateMembreMenageMois");
        entity.addIntProperty("q6bDateMembreMenageAnnee").columnName("q6bDateMembreMenageAnnee");
        entity.addShortProperty("q7DateNaissanceJour").columnName("q7DateNaissanceJour");
        entity.addShortProperty("q7DateNaissanceMois").columnName("q7DateNaissanceMois");
        entity.addIntProperty("q7DateNaissanceAnnee").columnName("q7DateNaissanceAnnee");
        entity.addShortProperty("q8Age").columnName("q8Age");
        entity.addShortProperty("q9LienDeParente").columnName("q9LienDeParente");
        entity.addShortProperty("q10Nationalite").columnName("q10Nationalite");
        entity.addStringProperty("q10PaysNationalite").columnName("q10PaysNationalite");
        entity.addShortProperty("q11NiveauEtude").columnName("q11NiveauEtude");
        entity.addShortProperty("q12StatutMatrimonial").columnName("q12StatutMatrimonial");
        entity.addShortProperty("statut").columnName("statut");
        entity.addBooleanProperty("isValidated").columnName("isValidated");
        entity.addBooleanProperty("isFieldAllFilled").columnName("isFieldAllFilled");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
        entity.addIntProperty("dureeSaisie").columnName("dureeSaisie");
        entity.addBooleanProperty("isContreEnqueteMade").columnName("isContreEnqueteMade");
        entity.addStringProperty("codeAgentRecenceur").columnName("codeAgentRecenceur");
        entity.addBooleanProperty("isVerified").columnName("isVerified");
    }
    //endregion

    //region "Rapport Agent Recenseur"
    public static void createRapportRAREntity(Entity entity){
        entity.addLongProperty("rapportId").columnName("rapportId").primaryKey().autoincrement();
        entity.addLongProperty("batimentId").columnName("batimentId");
        entity.addLongProperty("logeId").columnName("logeId");
        entity.addLongProperty("menageId").columnName("menageId");
        entity.addLongProperty("emigreId").columnName("emigreId");
        entity.addLongProperty("decesId").columnName("decesId");
        entity.addLongProperty("individuId").columnName("individuId");
        entity.addStringProperty("rapportModuleName").columnName("rapportModuleName");
        entity.addStringProperty("codeQuestionStop").columnName("codeQuestionStop");
        entity.addShortProperty("visiteNumber").columnName("visiteNumber");
        entity.addShortProperty("raisonActionId").columnName("raisonActionId");
        entity.addStringProperty("autreRaisonAction").columnName("autreRaisonAction");
        entity.addBooleanProperty("isFieldAllFilled").columnName("isFieldAllFilled");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
        entity.addIntProperty("dureeSaisie").columnName("dureeSaisie");
        entity.addBooleanProperty("isContreEnqueteMade").columnName("isContreEnqueteMade");
        entity.addStringProperty("codeAgentRecenceur").columnName("codeAgentRecenceur");
    }
    //endregion

    //region "Rapport Final Agent Recenseur"
    public static void createRapportFinalRAREntity(Entity entity){
        entity.addLongProperty("rapportFinalId").columnName("rapportFinalId").primaryKey().autoincrement();
        entity.addLongProperty("batimentId").columnName("batimentId");
        entity.addLongProperty("logeId").columnName("logeId");
        entity.addLongProperty("menageId").columnName("menageId");
        entity.addLongProperty("repondantPrincipalId").columnName("repondantPrincipalId");
        entity.addShortProperty("aE_EsKeGenMounKiEde").columnName("aE_EsKeGenMounKiEde");
        entity.addShortProperty("aE_IsVivreDansMenage").columnName("aE_IsVivreDansMenage");
        entity.addShortProperty("aE_RepondantQuiAideId").columnName("aE_RepondantQuiAideId");
        entity.addShortProperty("f_EsKeGenMounKiEde").columnName("f_EsKeGenMounKiEde");
        entity.addShortProperty("f_IsVivreDansMenage").columnName("f_IsVivreDansMenage");
        entity.addLongProperty("f_RepondantQuiAideId").columnName("f_RepondantQuiAideId");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
        entity.addIntProperty("dureeSaisie").columnName("dureeSaisie");
        entity.addBooleanProperty("isContreEnqueteMade").columnName("isContreEnqueteMade");
        entity.addStringProperty("codeAgentRecenceur").columnName("codeAgentRecenceur");
    }
    //endregion
}
