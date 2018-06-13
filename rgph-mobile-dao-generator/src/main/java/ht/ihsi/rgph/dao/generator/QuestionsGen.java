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
        entity.addStringProperty("qrgph").columnName("qrgph");
        entity.addShortProperty("qb1Etat").columnName("qb1Etat");
        entity.addShortProperty("qb2Type").columnName("qb2Type");
        entity.addShortProperty("qb3NombreEtage").columnName("qb3NombreEtage");
        entity.addShortProperty("qb4MateriauMur").columnName("qb4MateriauMur");
        entity.addShortProperty("qb5MateriauToit").columnName("qb5MateriauToit");
        entity.addShortProperty("qb6StatutOccupation").columnName("qb6StatutOccupation");
        entity.addShortProperty("qb7Utilisation1").columnName("qb7Utilisation1");
        entity.addShortProperty("qb7Utilisation2").columnName("qb7Utilisation2");
        entity.addShortProperty("qb8NbreLogeCollectif").columnName("qb8NbreLogeCollectif");
        entity.addShortProperty("qb8NbreLogeIndividuel").columnName("qb8NbreLogeIndividuel");
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
        entity.addShortProperty("qlc1TypeLogement").columnName("qlc1TypeLogement");
        entity.addShortProperty("qlc2bTotalGarcon").columnName("qlc2bTotalGarcon");
        entity.addShortProperty("qlc2bTotalFille").columnName("qlc2bTotalFille");
        entity.addShortProperty("qlcTotalIndividus").columnName("qlcTotalIndividus");
        entity.addShortProperty("qlin2StatutOccupation").columnName("qlin2StatutOccupation");
        entity.addShortProperty("qlin3ExistenceLogement").columnName("qlin3ExistenceLogement");
        entity.addShortProperty("qlin4TypeLogement").columnName("qlin4TypeLogement");
        entity.addShortProperty("qlin5MateriauSol").columnName("qlin5MateriauSol");
        entity.addShortProperty("qlin6NombrePiece").columnName("qlin6NombrePiece");
        entity.addShortProperty("qlin7NbreChambreACoucher").columnName("qlin7NbreChambreACoucher");
        entity.addShortProperty("qlin8NbreIndividuDepense").columnName("qlin8NbreIndividuDepense");
        entity.addShortProperty("qlin9NbreTotalMenage").columnName("qlin9NbreTotalMenage");
        entity.addShortProperty("statut").columnName("statut");
        entity.addBooleanProperty("isValidated").columnName("isValidated");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
        entity.addIntProperty("dureeSaisie").columnName("dureeSaisie");
        entity.addBooleanProperty("isFieldAllFilled").columnName("isFieldAllFilled");
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
        entity.addShortProperty("qm2ModeJouissance").columnName("qm2ModeJouissance");
        entity.addShortProperty("qm3ModeObtentionLoge").columnName("qm3ModeObtentionLoge");
        entity.addShortProperty("qm4_1ModeAprovEauABoire").columnName("qm4_1ModeAprovEauABoire");
        entity.addShortProperty("qm4_2ModeAprovEauAUsageCourant").columnName("qm4_2ModeAprovEauAUsageCourant");
        entity.addShortProperty("qm5SrcEnergieCuisson1").columnName("qm5SrcEnergieCuisson1");
        entity.addShortProperty("qm5SrcEnergieCuisson2").columnName("qm5SrcEnergieCuisson2");
        entity.addShortProperty("qm6TypeEclairage").columnName("qm6TypeEclairage");
        entity.addShortProperty("qm7ModeEvacDechet").columnName("qm7ModeEvacDechet");
        entity.addShortProperty("qm8EndroitBesoinPhysiologique").columnName("qm8EndroitBesoinPhysiologique");
        entity.addIntProperty("qm9NbreRadio1").columnName("qm9NbreRadio1");
        entity.addIntProperty("qm9NbreTelevision2").columnName("qm9NbreTelevision2");
        entity.addIntProperty("qm9NbreRefrigerateur3").columnName("qm9NbreRefrigerateur3");
        entity.addIntProperty("qm9NbreFouElectrique4").columnName("qm9NbreFouElectrique4");
        entity.addIntProperty("qm9NbreOrdinateur5").columnName("qm9NbreOrdinateur5");
        entity.addIntProperty("qm9NbreMotoBicyclette6").columnName("qm9NbreMotoBicyclette6");
        entity.addIntProperty("qm9NbreVoitureMachine7").columnName("qm9NbreVoitureMachine7");
        entity.addIntProperty("qm9NbreBateau8").columnName("qm9NbreBateau8");
        entity.addIntProperty("qm9NbrePanneauGeneratrice9").columnName("qm9NbrePanneauGeneratrice9");
        entity.addIntProperty("qm9NbreMilletChevalBourique10").columnName("qm9NbreMilletChevalBourique10");
        entity.addIntProperty("qm9NbreBoeufVache11").columnName("qm9NbreBoeufVache11");
        entity.addIntProperty("qm9NbreCochonCabrit12").columnName("qm9NbreCochonCabrit12");
        entity.addIntProperty("qm9NbreBeteVolaille13").columnName("qm9NbreBeteVolaille13");
        entity.addShortProperty("qm10AvoirPersDomestique").columnName("qm10AvoirPersDomestique");
        entity.addShortProperty("qm10TotalDomestiqueFille").columnName("qm10TotalDomestiqueFille");
        entity.addShortProperty("qm10TotalDomestiqueGarcon").columnName("qm10TotalDomestiqueGarcon");
        entity.addIntProperty("qm11TotalIndividuVivant").columnName("qm11TotalIndividuVivant");
        entity.addShortProperty("qn1Emigration").columnName("qn1Emigration");
        entity.addShortProperty("qn1NbreEmigre").columnName("qn1NbreEmigre");
        entity.addShortProperty("qd1Deces").columnName("qd1Deces");
        entity.addShortProperty("qd1NbreDecede").columnName("qd1NbreDecede");
        entity.addShortProperty("statut").columnName("statut");
        entity.addBooleanProperty("isValidated").columnName("isValidated");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
        entity.addIntProperty("dureeSaisie").columnName("dureeSaisie");
        entity.addBooleanProperty("isFieldAllFilled").columnName("isFieldAllFilled");
        entity.addBooleanProperty("isContreEnqueteMade").columnName("isContreEnqueteMade");
        entity.addStringProperty("codeAgentRecenceur").columnName("codeAgentRecenceur");
        entity.addBooleanProperty("isVerified").columnName("isVerified");
    }

    public static void createEmigreEntity(Entity entity){
        entity.addLongProperty("emigreId").columnName("emigreId").primaryKey().autoincrement();
        entity.addLongProperty("menageId").columnName("menageId");
        entity.addLongProperty("logeId").columnName("logeId");
        entity.addLongProperty("batimentId").columnName("batimentId");
        entity.addStringProperty("sdeId").columnName("sdeId");
        entity.addShortProperty("qn1numeroOrdre").columnName("qn1numeroOrdre");
        entity.addStringProperty("qn2aNomComplet").columnName("qn2aNomComplet");
        entity.addShortProperty("qn2bSexe").columnName("qn2bSexe");
        entity.addStringProperty("qn2cAgeAuMomentDepart").columnName("qn2cAgeAuMomentDepart");
        entity.addShortProperty("qn2dVivantToujours").columnName("qn2dVivantToujours");
        entity.addShortProperty("qn2eDernierPaysResidence").columnName("qn2eDernierPaysResidence");
        entity.addShortProperty("statut").columnName("statut");
        entity.addBooleanProperty("isFieldAllFilled").columnName("isFieldAllFilled");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
        entity.addIntProperty("dureeSaisie").columnName("dureeSaisie");
        entity.addStringProperty("codeAgentRecenceur").columnName("codeAgentRecenceur");
        entity.addBooleanProperty("isVerified").columnName("isVerified");
    }

    public static void createDecesEntity(Entity entity){
        entity.addLongProperty("decesId").columnName("decesId").primaryKey().autoincrement();
        entity.addLongProperty("menageId").columnName("menageId");
        entity.addLongProperty("logeId").columnName("logeId");
        entity.addLongProperty("batimentId").columnName("batimentId");
        entity.addStringProperty("sdeId").columnName("sdeId");
        entity.addShortProperty("qd2NoOrdre").columnName("qd2NoOrdre");
        entity.addShortProperty("qd2aSexe").columnName("qd2aSexe");
        entity.addStringProperty("qd2bAgeDecede").columnName("qd2bAgeDecede");
        entity.addShortProperty("qd2c1CirconstanceDeces").columnName("qd2c1CirconstanceDeces");
        entity.addShortProperty("qd2c2CauseDeces").columnName("qd2c2CauseDeces");
        entity.addShortProperty("statut").columnName("statut");
        entity.addBooleanProperty("isFieldAllFilled").columnName("isFieldAllFilled");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
        entity.addIntProperty("dureeSaisie").columnName("dureeSaisie");
        entity.addBooleanProperty("isContreEnqueteMade").columnName("isContreEnqueteMade");
        entity.addStringProperty("codeAgentRecenceur").columnName("codeAgentRecenceur");
        entity.addBooleanProperty("isVerified").columnName("isVerified");
    }

    public static void createIndividuEntity(Entity entity){
        //region "KARAKTERISTIK MOUN NAN -|- POU TOUT MANM MENAJ LA -|- KARAKTERISTIK DEMOGRAFIK"
        entity.addLongProperty("individuId").columnName("individuId").primaryKey().autoincrement();
        entity.addLongProperty("menageId").columnName("menageId");
        entity.addLongProperty("logeId").columnName("logeId");
        entity.addLongProperty("batimentId").columnName("batimentId");
        entity.addStringProperty("sdeId").columnName("sdeId");
        entity.addShortProperty("q1NoOrdre").columnName("q1NoOrdre");
        entity.addStringProperty("qp2APrenom").columnName("qp2APrenom");
        entity.addStringProperty("qp2BNom").columnName("qp2BNom");
        entity.addShortProperty("qp3LienDeParente").columnName("qp3LienDeParente");
        entity.addShortProperty("qp3HabiteDansMenage").columnName("qp3HabiteDansMenage");
        entity.addShortProperty("qp4Sexe").columnName("qp4Sexe");
        entity.addShortProperty("qp5DateNaissanceJour").columnName("qp5DateNaissanceJour");
        entity.addShortProperty("qp5DateNaissanceMois").columnName("qp5DateNaissanceMois");
        entity.addIntProperty("Qp5DateNaissanceAnnee").columnName("Qp5DateNaissanceAnnee");
        entity.addShortProperty("qp5bAge").columnName("qp5bAge");
        entity.addShortProperty("qp6religion").columnName("qp6religion");
        entity.addStringProperty("qp6AutreReligion").columnName("qp6AutreReligion");
        entity.addShortProperty("qp7Nationalite").columnName("qp7Nationalite");
        entity.addStringProperty("qp7PaysNationalite").columnName("qp7PaysNationalite");
        entity.addShortProperty("qp8MereEncoreVivante").columnName("qp8MereEncoreVivante");
        entity.addShortProperty("qp9EstPlusAge").columnName("qp9EstPlusAge");
        entity.addShortProperty("qp10LieuNaissance").columnName("qp10LieuNaissance");
        entity.addStringProperty("qp10CommuneNaissance").columnName("qp10CommuneNaissance");
        entity.addStringProperty("qp10VqseNaissance").columnName("qp10VqseNaissance");
        entity.addStringProperty("qp10PaysNaissance").columnName("qp10PaysNaissance");
        entity.addShortProperty("qp11PeriodeResidence").columnName("qp11PeriodeResidence");
        entity.addShortProperty("qp12DomicileAvantRecensement").columnName("qp12DomicileAvantRecensement");
        entity.addStringProperty("qp12CommuneDomicileAvantRecensement").columnName("qp12CommuneDomicileAvantRecensement");
        entity.addStringProperty("qp12VqseDomicileAvantRecensement").columnName("qp12VqseDomicileAvantRecensement");
        entity.addStringProperty("qp12PaysDomicileAvantRecensement").columnName("qp12PaysDomicileAvantRecensement");
        //endregion

        //region "KARAKTERISTIK MOUN NAN -|- EDIKASYON -|- POU MOUN KI GEN 3 LANE OSWA PLIS"
        entity.addShortProperty("qe1EstAlphabetise").columnName("qe1EstAlphabetise");
        entity.addShortProperty("qe2FreqentationScolaireOuUniv").columnName("qe2FreqentationScolaireOuUniv");
        entity.addShortProperty("qe3typeEcoleOuUniv").columnName("qe3typeEcoleOuUniv");
        entity.addShortProperty("qe4aNiveauEtude").columnName("qe4aNiveauEtude");
        entity.addStringProperty("qe4bDerniereClasseOUAneEtude").columnName("qe4bDerniereClasseOUAneEtude");
        entity.addShortProperty("qe5DiplomeUniversitaire").columnName("qe5DiplomeUniversitaire");
        entity.addStringProperty("qe6DomaineEtudeUniversitaire").columnName("qe6DomaineEtudeUniversitaire");
        //endregion

        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON NAN ZAFE WE"
        entity.addShortProperty("qaf1HandicapVoir").columnName("qaf1HandicapVoir");
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON NAN ZAFE TANDE"
        entity.addShortProperty("qaf2HandicapEntendre").columnName("qaf2HandicapEntendre");
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON NAN ZAFE MACHE (BOUJE)"
        entity.addShortProperty("qaf3HandicapMarcher").columnName("qaf3HandicapMarcher");
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON KOYITIV"
        entity.addShortProperty("qaf4HandicapSouvenir").columnName("qaf4HandicapSouvenir");
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- OKIPE TET LI"
        entity.addShortProperty("qaf5HandicapPourSeSoigner").columnName("qaf5HandicapPourSeSoigner");
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON KOMINIKATIF"
        entity.addShortProperty("qaf6HandicapCommuniquer").columnName("qaf6HandicapCommuniquer");
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- NOUVEL TEKNOLOJI NAN KOMINIKASYON(POU MOUN KI GEN 10 LANE OSWA PLIS) -|- POSESYON TELEFON SELILE"
        entity.addShortProperty("qt1PossessionTelCellulaire").columnName("qt1PossessionTelCellulaire");
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- NOUVEL TEKNOLOJI NAN KOMINIKASYON(POU MOUN KI GEN 10 LANE OSWA PLIS) -|- ITILIZASYON  ENTENET AK AKSE "
        entity.addShortProperty("qt2UtilisationInternet").columnName("qt2UtilisationInternet");
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- MIGRASYON : RETOUNEN VIN VIV AN AYITI(POU MOUN KI GEN 10 LANE OSWA PLIS) -|- TAN LI FE LI TAP VIV NAN LOT PEYI A"
        entity.addShortProperty("qem1DejaVivreAutrePays").columnName("qem1DejaVivreAutrePays");
        entity.addStringProperty("qem1AutrePays").columnName("qem1AutrePays");
        //endregion
                //**
        //region "KARAKTERISTIK MOUN NAN -|- MIGRASYON : RETOUNEN VIN VIV AN AYITI(POU MOUN KI GEN 10 LANE OSWA PLIS) -|- DAT RETOU A"
        entity.addShortProperty("qem2MoisRetour").columnName("qem2MoisRetour");
        entity.addIntProperty("qem2AnneeRetour").columnName("qem2AnneeRetour");
        //endregion
        //region "KARAKTERISTIK MOUN NAN  -|- Pou moun ki genyen dis (10) lane epi plis -|- ESTATI MATRIMONYAL"
        entity.addShortProperty("qsm1StatutMatrimonial").columnName("qsm1StatutMatrimonial");
        //endregion
        //**
        //region "KARAKTERISTIK MOUN NAN  -|- Pou moun ki genyen dis (10) lane epi plis -|- Aktivite Ekonomik"
        entity.addShortProperty("qa1ActEconomiqueDerniereSemaine").columnName("qa1ActEconomiqueDerniereSemaine");
        entity.addShortProperty("qa2ActAvoirDemele1").columnName("qa2ActAvoirDemele1");
        entity.addShortProperty("qa2ActDomestique2").columnName("qa2ActDomestique2");
        entity.addShortProperty("qa2ActCultivateur3").columnName("qa2ActCultivateur3");
        entity.addShortProperty("qa2ActAiderParent4").columnName("qa2ActAiderParent4");
        entity.addShortProperty("qa2ActAutre5").columnName("qa2ActAutre5");
        entity.addShortProperty("qa3StatutEmploie").columnName("qa3StatutEmploie");
        entity.addShortProperty("qa4SecteurInstitutionnel").columnName("qa4SecteurInstitutionnel");
        entity.addStringProperty("qa5TypeBienProduitParEntreprise").columnName("qa5TypeBienProduitParEntreprise");
        entity.addStringProperty("qa5PreciserTypeBienProduitParEntreprise").columnName("qa5PreciserTypeBienProduitParEntreprise");
        entity.addShortProperty("qa6LieuActDerniereSemaine").columnName("qa6LieuActDerniereSemaine");
        entity.addShortProperty("qa7FoncTravail").columnName("qa7FoncTravail");
        entity.addShortProperty("qa8EntreprendreDemarcheTravail").columnName("qa8EntreprendreDemarcheTravail");
        entity.addShortProperty("qa9VouloirTravailler").columnName("qa9VouloirTravailler");
        entity.addShortProperty("qa10DisponibilitePourTravail").columnName("qa10DisponibilitePourTravail");
        entity.addShortProperty("qa11RecevoirTransfertArgent").columnName("qa11RecevoirTransfertArgent");
        //endregion
        //**
        //region "FEGONDITE -|- POU FANM KI GEN 13 LANE OSWA PLIS -|- "
        entity.addIntProperty("qf1aNbreEnfantNeVivantM").columnName("qf1aNbreEnfantNeVivantM");
        entity.addIntProperty("qf1bNbreEnfantNeVivantF").columnName("qf1bNbreEnfantNeVivantF");
        entity.addIntProperty("qf2aNbreEnfantVivantM").columnName("qf2aNbreEnfantVivantM");
        entity.addIntProperty("qf2bNbreEnfantVivantF").columnName("qf2bNbreEnfantVivantF");
        entity.addShortProperty("qf3DernierEnfantJour").columnName("qf3DernierEnfantJour");
        entity.addShortProperty("qf3DernierEnfantMois").columnName("qf3DernierEnfantMois");
        entity.addIntProperty("qf3DernierEnfantAnnee").columnName("qf3DernierEnfantAnnee");
        entity.addShortProperty("qf4DENeVivantVit").columnName("qf4DENeVivantVit");
        entity.addShortProperty("statut").columnName("statut");
        entity.addBooleanProperty("isFieldAllFilled").columnName("isFieldAllFilled");
        entity.addStringProperty("dateDebutCollecte").columnName("dateDebutCollecte");
        entity.addStringProperty("dateFinCollecte").columnName("dateFinCollecte");
        entity.addIntProperty("dureeSaisie").columnName("dureeSaisie");
        entity.addBooleanProperty("isContreEnqueteMade").columnName("isContreEnqueteMade");
        entity.addStringProperty("codeAgentRecenceur").columnName("codeAgentRecenceur");
        //endregion
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
