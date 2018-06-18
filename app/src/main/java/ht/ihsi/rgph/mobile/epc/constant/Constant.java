package ht.ihsi.rgph.mobile.epc.constant;

import android.view.Gravity;

/**
 * Created by ajordany on 3/23/2016.
 */
 public interface Constant {

    String DATABASE_NAME="epc_data";

    int JOU_RESANSMAN=10;
    int MWA_RESANSMAN=06;
    int ANE_RESANSMAN=2018;
    String DATE_RESANSMAN=""+JOU_RESANSMAN +"/"+MWA_RESANSMAN +"/"+ANE_RESANSMAN;

    //region STATIC DATA FILE NAME
    String TBL_CATEGORIE_QUESTION = "tbl_categorie_question.json";
    String TBL_QUESTION = "tbl_questions.json";
    String TBL_REPONSES = "tbl_reponses.json";
    String TBL_QUESTIONS_REPONSES = "tbl_questions_reponses.json";
    String TBL_MODULE = "tbl_module.json";
    String TBL_QUESTIONS_MODULE = "tbl_questions_module.json";
    String TBL_DEPARTEMENT = "tbl_departement.json";
    String TBL_COMMUNE= "tbl_commune.json";
    String TBL_VQSE= "tbl_vqse.json";
    String TBL_PAYS= "tbl_pays.json";
    String DATA_TBL_PERSONNEL = "data_personnel.json";

    String TBL_DOMAINE_ETUDE= "tbl_domaine_etude.json";

    //region LIST MODULE
    int LIST_MODULE_BATIMENT=1;
    int LIST_MODULE_LOGEMENT_COLLECTIF=2;
    int LIST_MODULE_LOGEMENT_INDIVIDUEL=3;
    int LIST_MODULE_MENAGE=4;
    int LIST_MODULE_EMIGRE=5;
    int LIST_MODULE_DECES=6;
    int LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF=7;
    int LIST_MODULE_INDIVIDU_MENAGE =8;
    int LIST_MODULE_INDIVIDU_LIST_ONLY = 10;
    int LIST_MODULE_BATIMENT_LIST_ONLY = 11;
    int LIST_MODULE_LOGEMENT_LIST_ONLY = 12;
    int LIST_MODULE_MENAGE_LIST_ONLY = 13;
    int LIST_MODULE_EMIGRER_LIST_ONLY = 14;
    int LIST_MODULE_DECES_LIST_ONLY = 15;
    int LIST_MODULE_COMPTE_UTILISATEUR_16 = 16;
    int LIST_MODULE_BATIMANT_POUR_RAPPORT_17 = 17;
    int LIST_MODULE_RAPPORT_PAR_BATIMANT_18 = 18;
    //endregion

    //region LIST MODULE PARAM
    String PARAM_LIST_TYPE ="LIST_TYPE";
    String PARAM_LIST_TITLE ="LIST_TITLE";
    String PARAM_MODULE_STATUT ="MODULE_STATUT";
    String PARAM_MODULE_ID ="MODULE_ID";
    String PARAM_MODEL="MODEL";
    String PARAM_QUESTIONNAIRE_FORMULAIRE="PARAM_QUESTION";
    String PARAM_LISTE_HEADER_ONE="HEADER_LIST_ONE";
    String PARAM_LISTE_HEADER_TWO="HEADER_LIST_TWO";
    String PARAM_ID = "PARAM_ID";

    String PARAM_FORM_HEADER_ONE="HEADER_FORM_ONE";
    String PARAM_FORM_HEADER_TWO="HEADER_FORM_TWO";
    String PARAM_NUMERO_ORDRE_LOG_INDIVIDUEL="PARAM_NUMERO_ORDRE_LOG_INDIVIDUEL";

    //endregion

    //region MODULE NAME
    String MODULE_NAME_BATIMAN="Batiman";
    String MODULE_NAME_LOGMAN_KOLEKTIF="Lojman Kolektif";
    String MODULE_NAME_LOGMAN_ENDIVIDYEL ="Lojman Endividyèl";
    String MODULE_NAME_MENAGE="Menaj";
    String MODULE_NAME_DECES="Desè";
    String MODULE_NAME_EMIGRE="Emigre";
    String MODULE_NAME_INDIVIDU="Moun";

    int ZONE_URBAIN_1 = 1;
    int ZONE_RURAL_2 = 2;
    //endregion





    int WI_LI_TOUJOU_LA_MEN_LI_OKIPE_NAN_ZAFE_MANJE_A_PA_02 = 2;
    int WI_MEN_LI_DEPLASE_POU_PLIS_PASE_6_MWA_04 = 4;
    int LI_FENK_VINI_LI_GEN_LI_GEN_ENTANSYON_RETE_LA_08 = 8;
    int LI_NAN_KAY_LA_POU_MWENS_KE_6_MWA_09 = 9;

    int PRECISEZ_10 = 10;
    int PRECISEZ_14 = 14;
    int PRECISEZ_19 = 19;
    int PRECISEZ_23 = 23;

    //endregion

    //region FORMULAIRE - MODULE
    int FORMULAIRE_BATIMENT = 1;
    int FORMULAIRE_LOGEMENT_COLLECTIF = 2;
    int FORMULAIRE_LOGEMENT_INDIVIDUEL = 3;
    int FORMULAIRE_MENAGE = 4;
    int FORMULAIRE_INDIVIDUS = 5;
    int FORMULAIRE_DECES = 6;
    int FORMULAIRE_EMIGRE = 7;
    int FORMULAIRE_EVALUATION_MENAGE = 8;

    String NON_0 = "false";
    String OUI_1 = "true";

    int Non_0 = 0;
    int Oui_1 = 1;

    int ACTIF = 1;
    int DESACTIVE = 0;
    int PRIVILEGE_DEVELOPPEUR = 99;
    int PRIVILEGE_SUPERVISEUR = 7;
    int PRIVILEGE_AGENT = 8;
    int PRIVILEGE_ASTIC = 9;

    int ACTION_NOUVEAU = 0;
    int ACTION_AFFICHER = 1;
    int ACTION_MOFIDIER = 2;
    int ACTION_KONTINYE = 3;
    int ACTION_LISTER = 4;
    int ACTION_RAPPORT = 5;

    int imeActionId_EtReponse_6 = 6;

    //endregion
    String MODULE_BATIMENT = "MODULE_BATIMENT";
    String CODE_MODULE = "CODE_MODULE";

    //region STATUT MODULE
    int STATUT_MODULE_KI_FINI_1 = 1;
    int STATUT_MODULE_KI_MAL_RANPLI_2 = 2;
    int STATUT_MODULE_KI_PA_FINI_3 = 3;

    int STATUT_RANPLI_NET_11 = 11;
    int STATUT_PA_FIN_RANPLI_22 = 22;
    int STATUT_PA_RANPLI_DITOU_33 = 33;
    //endregion

    //region CONTRAINTE REPONSE
    int CHIFFRE = 1;
    int LETTRE = 2;
    int CHIFFRE_LETTRE = 3;
    //endregion

    //region TYPE QUESTIONS
    int TYPE_QUESTION_CHOIX_1 = 1;
    int TYPE_QUESTION_SAISIE_2 = 2;
    int TYPE_QUESTION_DATE_MM_AAAA_3 = 3;
    int TYPE_QUESTION_DEUX_CHOIX_4 = 4;
    int TYPE_QUESTION_CHOIX_PAYS_5 = 5;
    int TYPE_QUESTION_CHOIX_COMMUNE_6 = 6;
    int TYPE_QUESTION_CHOIX_SECTION_COMMUNALE_7 = 7;
    int TYPE_QUESTION_CHOIX_DOMAINE_ETUDE_8 = 8;
    int TYPE_QUESTION_CHOIX_INDIVIDU_9 = 9;
    int TYPE_QUESTION_CHOIX_ADD_INDIVIDU_10 = 10;
    int TYPE_QUESTION_DATE_JJ_MM_AAAA_11 = 11;
    int TYPE_QUESTION_NBR_APPAREIL_ET_ANIMAUX_12 = 12;
    int TYPE_QUESTION_NBR_GARCON_ET_FILLE_13 = 13;
    int TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_14 = 14;
    int TYPE_QUESTION_CHOIX_COMBO1_ET_COMBO2_LIER_15 = 15;
    int TYPE_QUESTION_CHOIX_AGE_16 = 16;
    int TYPE_QUESTION_CHOIX_LISTE_BOX_17 = 17;
    int TYPE_QUESTION_CHOIX_LISTE_BOX_PAYS_18 = 18;
    int TYPE_QUESTION_CHOIX_LISTE_BOX_AGE_19 = 19;
    int TYPE_QUESTION_CHOIX_LISTE_BOX_DOMAINE_ETUDE_20 = 20;
    int TYPE_QUESTION_CHOIX_COMBO1_ET_LISTE_BOX_LIER_21 = 21;
    int TYPE_QUESTION_CHOIX_NUMBER_LISTE_BOX_22 = 22;
    int TYPE_QUESTION_DATE_SAISIE_JOUR_SELECT_MOIS_SAISIE_ANNEE_23 = 23;
    int TYPE_QUESTION_DATE_SELECT_MOIS_SAISIE_ANNEE_24 = 24;

    //endregion

    //region MANAGER TYPE
        int FORM_DATA_MANAGER =1 ;
        int CU_RECORD_MANAGER =2 ;
        int QUERY_RECORD_MANAGER =3 ;
    //endregion

    int GravityCenter = Gravity.CENTER;
    int GravityTop = Gravity.TOP;
    int GravityBottom = Gravity.BOTTOM;
    int GravityEnd = Gravity.END;

    // KEY DATA FOR PREFERENCE USE FOR LOGIN USER
    String prefKeyUserName = "keyUserName";
    String prefKeyIdGroupeUser = "KeyIdGroupeUser";

    // BATIMANT
    //region "VARIABLES BATIMENT"
    int BATIMAN_Pa_konnen_paske_li_pa_sou_je_5 = 5;
    int PA_GEN_LOT_ITILIZASYON = 4;
    int BATIMAN_OBSERVABLE_NON_5 = 5;
    int BATIMAN_OKIPE_1 = 1;
    int BATIMAN_TOUJOU_VID_2 = 2;
    int BATIMAN_PA_KA_WE_5 = 5;
    //endregion

    int LOJMAN_OKIPE_TOUTAN_MEN_MOUN_YO_LA_1 = 1;
    int LOJMAN_OKIPE_TOUTAN_MEN_MOUN_YO_PA_LA_2 = 2;
    int LOJMAN_OKIPE_YON_LE_KONSA_3 = 3;
    int LOJMAN_PA_OKIPE_4 = 4;
    int LCOL_Pansyon_Fanmi_1 = 1;
    int LCOL_Kouvan_Monaste_2 = 2;
    int LCOL_Presbite_3 = 3;
    int LCOL_Ofelina_4 = 4;
    int LCOL_Lazil_oswa_kote_granmoun_rete_oswa_kay_retrèt_6 = 6;

   // LOGEMENT
    int LOJ_KOLEKTIF = 0;
    int LOJ_ENDIVIDYEL = 1;

    // FORMULAIRE
    String STATUT_MODULE = "STATUT_MODULE";


    String DEBUT = "DEBUT";
    String FIN = "FIN";
    String STRING_VIDE = "";

    int LOAD_ALL_PARENT = 0;
    int LOAD_ALL_ENFANT = 1;



    // AGE
    // int AnneeRecensement = DateTime.Now.Year;// 2015;
    int AGE_00ANS = 0;
    int AGE_03ANS = 3;
    int AGE_04ANS = 4;
    int AGE_05ANS = 5;
    int AGE_06ANS = 6;
    int AGE_07ANS = 7;
    int AGE_08ANS = 8;
    int AGE_10ANS = 10;
    int AGE_11ANS = 11;
    int AGE_12ANS = 12;
    int AGE_13ANS = 13;
    int AGE_14ANS = 14;
    int AGE_15ANS = 15;
    int AGE_17ANS = 17;
    int AGE_18ANS = 18;
    int AGE_19ANS = 19;
    int AGE_20ANS = 20;
    int AGE_23ANS = 23;
    int AGE_24ANS = 24;
    int AGE_25ANS = 25;
    int AGE_26ANS = 26;
    int AGE_28ANS = 28;
    int AGE_29ANS = 29;
    int AGE_30ANS = 30;
    int AGE_32ANS = 32;
    int AGE_33ANS = 33;
    int AGE_34ANS = 34;
    int AGE_35ANS = 35;
    int AGE_39ANS = 39;
    int AGE_40ANS = 40;
    int AGE_44ANS = 44;
    int AGE_45ANS = 45;
    int AGE_49ANS = 49;
    int AGE_50ANS = 50;
    int AGE_60ANS = 60;
    int AGE_120ANS = 120;
    int JOUR_PA_KONNEN_99ANS = 99;
    int MOIS_PA_KONNEN_99ANS = 99;
    int ANNEE_PA_KONNEN_9999ANS = 9999;
    int AGE_PA_KONNEN_999ANS = 999;

    int MOIS_FEVRIER_02 = 2;

    int NBR_3_ENFANT = 3;
    int NBR_6_ENFANT = 6;
    int NBR_8_ENFANT = 8;
    int NBR_10_ENFANT = 10;
    int NBR_12_ENFANT = 12;
    int NBR_14_ENFANT = 14;
    int NBR_16_ENFANT = 16;
    int NBR_18_ENFANT = 18;

    int HOMME_1 = 1;
    int FEMME_2 = 2;

    //------ REPONSE / QUESTIONS -----//
    int REPONS_WI_1 = 1;
    int REPONS_NON_2 = 2;
    int REPONS_NON_0 = 0;
    int REPONS_PA_KONNEN_3 = 3;
    int REPONS_PA_KONNEN_99 = 99;

    int LAJ_GRANMOUN_6 = 6;

    int NAN_PEYI_LETRANJE_3 = 3;
    int NIVO_INIVESITE = 7;

    int BATIMAN_SAN_ETAJ_AK_APATMAN_04 = 4;
    int BATIMAN_AK_ETAJ_AK_APATMAN_05 = 5;
    int BATIMAN_CHELTE_10 = 10;

    String Msg_Ou_Dwe_Ekri_Yon_Repons = "Ou dwe ekri yon repons";
    String Msg_Ou_Dwe_Chwazi_Yon_Repons = "Ou dwe chwazi yon repons";

    //region "VARIABLE LOGEMENT COLLECTIF"
    int SetValueTempInfoQuestion_Batiment = 1;
    int SetValueTempInfoQuestion_Logement = 2;
    int SetValueTempInfoQuestion_Individu = 3;
    int SetValueTempInfoQuestion_Menage = 4;
    int SetValueTempInfoQuestion_Emigre = 5;
    int SetValueTempInfoQuestion_Deces = 6;
    String CallFormulaireLogementCollectif = "CallFormulaireLogementCollectif";

    int Sant_detansyon_Prizon_6 = 6;
    //endregion

    //region "VARIABLE LOGEMENT INDIVIDUEL"
    String Qlin2StatutOccupation = "Qlin2StatutOccupation";
    String Qp5JourMoisAnneeDateNaissance = "Qp5JourMoisAnneeDateNaissance";
    int LOJMAN_APATMAN_01 = 1;
    int LOJMAN_PYES_KAY_02 = 2;
    int LOJMAN_CHELTE_05 = 5;
    int LOJMAN_TANT_06 = 6;
    int LOJMAN_Lot_3 = 3;
    //endregion

    //region "VARIABLE "
    String Qlc1bTotalGarconEtFille = "Qlc1bTotalGarconEtFille";
    String Qlin6NombrePieceETChambreACoucher = "Qlin6NombrePieceETChambreACoucher";

    String Qe4ANiveauEtudeETClasse = "Qe4ANiveauEtudeETClasse";
    String Qm4ModeAprobEauBoireEtUsageCourant = "Qm4ModeAprobEauBoireEtUsageCourant";
    String Qm5SrcEnergieCuison1Et2 = "Qm5SrcEnergieCuison1Et2";
    String Qm10TotalDomestiqueGaconEtFille = "Qm10TotalDomestiqueGaconEtFille";
    String Qm11CallFormListeIndividu = "Qm11CallFormListeIndividu";
    String CallFormListeAncienMembreMenage = "CallFormListeAncienMembreMenage";

    String Qf1NbrEnfantGarconEtFillessssssss = "Qf1NbrEnfantGarconEtFille";
    String Qf1NbreEnfantNeVivantGarconEtFille = "Qf1NbreEnfantNeVivantGarconEtFille";
    String Qf2NbrEnfantVivantGarconEtFille = "Qf2NbrEnfantVivantGarconEtFille";

    String CallFormulaireLogementEndividyel = "CallFormulaireLogementEndividyel";
    String CallFormulaireIndividuLojCol = "CallFormulaireIndividuLojCol";
    String CallFormulaireMenage = "CallFormulaireMenage";
    String CallFormulaireEmigre = "CallFormulaireEmigre";
    String CallFormulaireDeces = "CallFormulaireDeces";
    String CallFormulaireIndividuMenage = "CallFormulaireIndividuMenage";
    //endregion

    //region "VARIABLE DECES"
    String Qd2bAgeDecede = "Qd2bAgeDecede";
    //endregion

    //region "VARIABLE INDIVIDU"
    String Qp5bAge = "Qp5bAge";


    int NiveauEtude_Preskole_3 = 3;
    int NiveauEtude_Prime_Fondamantal_1e_sik_4 = 4;
    int NiveauEtude_Prime_Fondamantal_2e_sik_5 = 5;
    int NiveauEtude_Segonde_Fondamantal_3e_sik_6 = 6;
    int NiveauEtude_Segonde_3e_Filo_7 = 7;
    int NiveauEtude_Inivesite_8 = 8;
    int Isit_la_nan_menm_vil_oswa_menm_katye_1 = 1;
    int Pa_Aplikab_4 = 4;
    int R05_Non_Aplikab_05 = 5;
    int Pa_Aplikab_7 = 7;
    int Non_5 = 5;
    int Non_okenn_difikilte_1 = 1;

    int Chef_menaj_la_01 = 1;
    int Mari_Madanm_02 = 2;
    int Pitit_gason_Ou_Piti_fi_03 = 3;
    int Papa_Manman_06 = 6;
    int Pitit_Pitit_Gason_Ou_Pitit_Pitit_fi_07 = 7;

    int R01_Wi_Preskole_1 = 1;
    int R02_Wi_Prime_Fondamantal_1e_sik_2 = 2;
    int R03_Wi_Prime_Fondamantal_2e_sik_3 = 3;
    int R04_Wi_Segonde_Fondamantal_3e_sik_4 = 4;
    int R05_Wi_Segonde_3e_Filo_5 = 5;
    int R06_Wi_Inivesite_6 = 6;
    int R07_Wi_Lekol_Pwofesyonel_7 = 7;
    int R08_Non_Sant_Alfabetizasyon_8 = 8;
    int R09_Non_Okenn_9 = 9;
    int R10_Pa_Konnen_10 = 10;

    int Selibate_01 = 1;
    int Marye_02 = 2;
    int Plase_03 = 3;
    int Viv_avek_04 = 4;
    int Veuf_05 = 5;

    //endregion

    //region VARIABLES PREFERENCE
    String prefPersId = "prefPersId";
    String prefSdeId = "prefSdeId";
    String prefPrenomEtNom = "prefPrenomEtNom";
    String prefNom = "prefNom";
    String prefPrenom = "prefPrenom";
    String prefSexe = "prefSexe";
    String prefNomUtilisateur = "prefNomUtilisateur";
    String prefEmail = "prefEmail";
    String prefDeptId = "prefDeptId";
    String prefComId = "prefComId";
    String prefVqseId = "prefVqseId";
    String prefEstActif = "prefEstActif";
    String prefProfileId = "prefProfileId";
    String prefIsConnected = "prefIsConnected";
    String prefLastLogin = "prefLastLogin";
    int CLASSE_REPONSE_MODEL = 1;
    int CLASSE_KEY_VALUE_MODEL = 2;

    //endregion

}
