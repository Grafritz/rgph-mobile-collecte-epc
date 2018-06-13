package ht.ihsi.rgph.dao.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class Main {

    //region DYNAMIC QUESTION
    public static final String DESTINATION_PACKAGE_NAME="ht.ihsi.rgph.mobile.backend.entities";
    public static final String PERSONNEL_OBJECT="Personnel";
    public static final String CATEGORIE_QUESTION_OBJECT="CategorieQuestion";
    public static final String QUESTION_OBJECT="Question";
    public static final String REPONSE_OBJECT="Reponse";
    public static final String QUESTION_REPONSE_OBJECT="QuestionReponse";
    public static final String MODULE_OBJECT="Module";
    public static final String QUESTION_MODULE_OBJECT="QuestionModule";
    public static final String DEPARTEMENT_OBJECT ="Departement";
    public static final String COMMUNE_OBJECT ="Commune";
    public static final String VQSE_OBJECT ="Vqse";
    public static final String DOMAINE_ETUDE_OBJECT ="DomaineEtude";
    public static final String PAYS_OBJECT ="Pays";

    public static final String TBL_PERSONNEL ="tbl_personnel";
    public static final String TBL_CATEGORIE_QUESTION ="tbl_categorie_question";
    public static final String TBL_QUESTION ="tbl_question";
    public static final String TBL_REPONSE ="tbl_reponse";
    public static final String TBL_QUESTION_REPONSE ="tbl_question_reponse";
    public static final String TBL_MODULE ="tbl_module";
    public static final String TBL_QUESTION_MODULE ="tbl_question_module";

    public static final String TBL_DEPARTEMENT ="tbl_departement";
    public static final String TBL_COMMUNE ="tbl_commune";
    public static final String TBL_VQSE ="tbl_vqse";
    public static final String TBL_DOMAINE_ETUDE ="tbl_domaine_etude";
    public static final String TBL_PAYS ="tbl_pays";

    //endregion

    //region COLLECT OBJECT
    public static final String BATIMENT_OBJECT="Batiment";
    public static final String LOGEMENT_OBJECT="Logement";
    public static final String MENAGE_OBJECT="Menage";
    public static final String EMIGRE_OBJECT="Emigre";
    public static final String DECES_OBJECT="Deces";
    public static final String INDIVIDU_OBJECT="Individu";
    public static final String RapportRAR_OBJECT="RapportRAR";
    public static final String RapportRAR_FINAL_OBJECT="RapportFinal";

    public static final String TBL_BATIMENT_OBJECT="tbl_batiment";
    public static final String TBL_LOGEMENT_OBJECT="tbl_logement";
    public static final String TBL_MENAGE_OBJECT="tbl_menage";
    public static final String TBL_EMIGRE_OBJECT="tbl_emigre";
    public static final String TBL_DECES_OBJECT="tbl_deces";
    public static final String TBL_INDIVIDU_OBJECT="tbl_individu";
    public static final String TBL_RapportRAR_OBJECT="tbl_rapportrar";
    public static final String TBL_RapportRAR_FINAL_OBJECT="tbl_rapportfinal";
    //endregion

    public static final int DATABASE_VERSION = 3; // Ajout Champs [ isVerified ]

    public static void main(String args[]) throws Exception {

        Schema schema= new Schema(DATABASE_VERSION, DESTINATION_PACKAGE_NAME);

        //region DYNAMIC QUESTION
        Entity personnel= schema.addEntity(PERSONNEL_OBJECT);
        personnel.setTableName(TBL_PERSONNEL);
        QuestionsGen.createPersonnelEntity(personnel);

        Entity categorieQuestion =schema.addEntity(CATEGORIE_QUESTION_OBJECT);
        QuestionsGen.createCategorieQuestionEntity(categorieQuestion);
        categorieQuestion.setTableName(TBL_CATEGORIE_QUESTION);

        Entity question =schema.addEntity(QUESTION_OBJECT);
        QuestionsGen.createQuestionsEntity(question);
        question.setTableName(TBL_QUESTION);

        /*Entity reponse =schema.addEntity(REPONSE_OBJECT);
        QuestionsGen.createReponseEntity(reponse);
        reponse.setTableName(TBL_REPONSE);*/

        Entity questionReponse =schema.addEntity(QUESTION_REPONSE_OBJECT);
        QuestionsGen.createQuestionReponseEntity(questionReponse);
        questionReponse.setTableName(TBL_QUESTION_REPONSE);

        Entity module =schema.addEntity(MODULE_OBJECT);
        QuestionsGen.createModuleEntity(module);
        module.setTableName(TBL_MODULE);

        Entity questionModule =schema.addEntity(QUESTION_MODULE_OBJECT);
        QuestionsGen.createQuestionModuleEntity(questionModule);
        questionModule.setTableName(TBL_QUESTION_MODULE);

        Entity departement=schema.addEntity(DEPARTEMENT_OBJECT);
        QuestionsGen.createDepartementEntity(departement);
        departement.setTableName(TBL_DEPARTEMENT);

        Entity commune=schema.addEntity(COMMUNE_OBJECT);
        QuestionsGen.createCommuneEntity(commune);
        commune.setTableName(TBL_COMMUNE);

        Entity vqse=schema.addEntity(VQSE_OBJECT);
        QuestionsGen.createVqseEntity(vqse);
        vqse.setTableName(TBL_VQSE);

        Entity domaineEtude=schema.addEntity(DOMAINE_ETUDE_OBJECT);
        QuestionsGen.createDomainEtudeEntity(domaineEtude);
        domaineEtude.setTableName(TBL_DOMAINE_ETUDE);

        Entity pays=schema.addEntity(PAYS_OBJECT);
        QuestionsGen.createPaysEntity(pays);
        pays.setTableName(TBL_PAYS);

        //endregion

        //region COLLECT OBJECT
        Entity batiment= schema.addEntity(BATIMENT_OBJECT);
        batiment.setTableName(TBL_BATIMENT_OBJECT);
        QuestionsGen.createBatimentEntity(batiment);

        Entity logement= schema.addEntity(LOGEMENT_OBJECT);
        logement.setTableName(TBL_LOGEMENT_OBJECT);
        QuestionsGen.createLogementEntity(logement);

        Entity menage= schema.addEntity(MENAGE_OBJECT);
        menage.setTableName(TBL_MENAGE_OBJECT);
        QuestionsGen.createMenageEntity(menage);

        Entity emigre =schema.addEntity(EMIGRE_OBJECT);
        emigre.setTableName(TBL_EMIGRE_OBJECT);
        QuestionsGen.createEmigreEntity(emigre);

        Entity deces=schema.addEntity(DECES_OBJECT);
        deces.setTableName(TBL_DECES_OBJECT);
        QuestionsGen.createDecesEntity(deces);

        Entity rapportRAR = schema.addEntity(RapportRAR_OBJECT);
        rapportRAR.setTableName(TBL_RapportRAR_OBJECT);
        QuestionsGen.createRapportRAREntity(rapportRAR);

        Entity rapport_FINAL_RAR = schema.addEntity(RapportRAR_FINAL_OBJECT);
        rapport_FINAL_RAR.setTableName(TBL_RapportRAR_FINAL_OBJECT);
        QuestionsGen.createRapportFinalRAREntity(rapport_FINAL_RAR);

        Entity individu=schema.addEntity(INDIVIDU_OBJECT);
        individu.setTableName(TBL_INDIVIDU_OBJECT);
        QuestionsGen.createIndividuEntity(individu);


        //region Relationships
        //Property batimentId= logement.addLongProperty("batimentId").columnName("batimentId").notNull().getProperty();
        //logement.addToOne(batiment, batimentId, "batiment");
        //batiment.addToMany(logement, batimentId, "logements");

        //Property logeId= menage.addLongProperty("logeId").columnName("logeId").notNull().getProperty();
        //menage.addToOne(logement, logeId, "logement");
        //logement.addToMany(menage, logeId, "menages");

        //Property menageId_emigre= emigre.addLongProperty("menageId").columnName("menageId").notNull().getProperty();
        //emigre.addToOne(menage, menageId_emigre, "menage");
        //menage.addToMany(emigre, menageId_emigre, "emigres");

        //Property menageId_deces= deces.addLongProperty("menageId").columnName("menageId").notNull().getProperty();
        //deces.addToOne(menage, menageId_deces, "menage");
        //menage.addToMany(deces, menageId_deces, "deces");

        //Property menageId_ind= individu.addLongProperty("menageId").columnName("menageId").getProperty();
        //Property logeId_ind=individu.addLongProperty("logeId").columnName("logeId").getProperty();

        //individu.addToOne(menage, menageId_ind, "menage");
        //menage.addToMany(individu, menageId_ind, "individus");

        //individu.addToOne(logement,logeId_ind,"logement");
        //logement.addToMany(individu,logeId_ind,"individus");
        //endregion

        new DaoGenerator().generateAll(schema, "../app/src/main/java");

    }

}

