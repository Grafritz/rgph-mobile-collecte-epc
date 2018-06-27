package ht.ihsi.rgph.mobile.epc.managers;

import ht.ihsi.rgph.mobile.epc.exceptions.ManagerException;
import ht.ihsi.rgph.mobile.epc.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.epc.models.AncienMembreModel;
import ht.ihsi.rgph.mobile.epc.models.BatimentModel;
//import ht.ihsi.rgph.mobile.epc.models.DecesModel;
//import ht.ihsi.rgph.mobile.epc.models.EmigreModel;
import ht.ihsi.rgph.mobile.epc.models.IndividuModel;
import ht.ihsi.rgph.mobile.epc.models.LogementModel;
import ht.ihsi.rgph.mobile.epc.models.MenageModel;
import ht.ihsi.rgph.mobile.epc.models.PersonnelModel;
import ht.ihsi.rgph.mobile.epc.models.RapportFinalModel;
import ht.ihsi.rgph.mobile.epc.models.RapportRARModel;

/**
 * Created by Jfduvers on 3/22/2016.
 */
public interface CURecordMngr {

    /**
     * Save a new Batiment
     *
     * @param batiment the object batiment
     * @return BatimentModel
     * @throws ManagerException
     */
    BatimentModel saveBatiment(BatimentModel batiment, String userCode) throws ManagerException;
    BatimentModel SaveBatiment(Long id, BatimentModel batiment, int typeEvenement, String userCode) throws ManagerException;

    /**
     * save a new logement
     *
     * @param logement the object logement
     * @return LogementModel
     * @throws ManagerException
     */
    LogementModel saveLogement(LogementModel logement, String userCode) throws ManagerException;
    LogementModel SaveLogement(Long id, LogementModel logement, int typeEvenement, String userCode) throws ManagerException;

    /**
     * Save a new menage
     *
     * @param menage the object menage
     * @return MenageModel
     * @throws ManagerException
     */
    MenageModel saveMenage(MenageModel menage, String userCode) throws ManagerException;
    MenageModel SaveMenage(Long id, MenageModel menage, int typeEvenement, String userCode) throws ManagerException;

    //DecesModel saveDeces(DecesModel deces, String userCode) throws ManagerException;
    //DecesModel SaveDeces(Long id, DecesModel deces, int typeEvenement, String userCode) throws ManagerException;

    //EmigreModel saveEmigre(EmigreModel emigre, String userCode) throws ManagerException;
    //EmigreModel SaveEmigre(Long id, EmigreModel emigre, int typeEvenement, String userCode) throws ManagerException;

    /**
     * Save a new individu.
     *
     * @param individu
     * @return IndividuModel
     * @throws ManagerException
     */
    IndividuModel InsertIndividu(IndividuModel individu, String userCode) throws ManagerException;
    IndividuModel SaveIndividu(Long id, IndividuModel individu, int typeEvenement, String userCode) throws ManagerException;

    AncienMembreModel SaveAncienMembre(Long id, AncienMembreModel ancienMembre, int typeEvenement, String userCode) throws ManagerException;
    AncienMembreModel InsertAncienMembre(AncienMembreModel individu, String userCode) throws ManagerException;

    /**
     * Save a new rapportRAR
     *
     * @param rapportRARModel the object menage
     * @return RapportRARModel
     * @throws ManagerException
     */
    RapportRARModel saveRapportRAR(RapportRARModel rapportRARModel) throws ManagerException;

    RapportFinalModel saveRapportFinal(RapportFinalModel rapportFinalModel) throws ManagerException;

    PersonnelModel savePersonnel(PersonnelModel personnelModel, String userCode) throws ManagerException;

    PersonnelModel SavePersonnel(long id, PersonnelModel personnelModel, String userCode) throws ManagerException, TextEmptyException;
    /**
     * Save a new entity
     *
     * @param entite
     * @param <T>    the type of the entity
     * @return
     */
    <T> T saveEntity(T entite) throws ManagerException;

    /**
     * Update a batiment
     *
     * @param batiment
     * @return BatimentModel
     * @throws ManagerException
     */
    BatimentModel updateBatiment(BatimentModel batiment, String userCode) throws ManagerException;
    int updateStatutBatiment(long IdBatiment, short Statut, boolean isFieldAllFilled, String userCode) throws ManagerException;

    /**
     * Update a logement.
     *
     * @param logement
     * @return LogementModel
     * @throws ManagerException
     */
    LogementModel updateLogement(LogementModel logement, String userCode) throws ManagerException;
    int updateStatutLogement(long idLogement, short Statut, boolean isFieldAllFilled, String userCode) throws ManagerException;

    /**
     * Update a menage.
     *
     * @param menage
     * @return MenageModel
     * @throws ManagerException
     */
    MenageModel updateMenage(MenageModel menage, String userCode) throws ManagerException;
    int updateStatutMenage(long idMenage, short Statut, boolean isFieldAllFilled, String userCode) throws ManagerException;

    //EmigreModel updateEmigre(EmigreModel emigre, String userCode) throws ManagerException;
    //int updateStatutEmigre(long idEmigre, short Statut, boolean isFieldAllFilled, String userCode) throws ManagerException;

    //DecesModel updateDeces(DecesModel deces, String userCode) throws ManagerException;
    //int updateStatutDeces(long idDeces, short Statut, boolean isFieldAllFilled, String userCode) throws ManagerException;

    /**
     * Update an individu.
     *
     * @param individu
     * @return IndividuModel
     * @throws ManagerException
     */
    IndividuModel updateIndividu(IndividuModel individu, String userCode) throws ManagerException;
    AncienMembreModel updateAncienMembre(AncienMembreModel individu, String userCode) throws ManagerException;
    int updateStatutIndividu(long idIndividu, short Statut, boolean isFieldAllFilled, String userCode) throws ManagerException;


    PersonnelModel updatePersonnel(PersonnelModel personnelModel, String userCode) throws ManagerException;

    PersonnelModel updateAllPersonnel(long persId, String userCode) throws ManagerException;
    /**
     * Update an entity
     *
     * @param entite
     * @param <T>    the entity type.
     * @return T entity.
     */
    <T> T updateEntity(T entite) throws ManagerException;

    //BatimentModel incNbLogCAndStatInBat(long batId, int status) throws ManagerException;

    /**
     * Increment or decrement the number of Logement Individuel in a batiment and change the status
     * @param batId the id of a batiment
     * @param status the status
     * @return BatimentModel
     * @throws ManagerException
     */

    BatimentModel incNbLogIAndStatInBat(long batId, int status) throws ManagerException;

    /**
     * Increment or decrement the number of Individu in a Logement Collectif and change the status
     * @param logId the id of a logement
     * @param status the status
     * @return LogementModel
     * @throws ManagerException
     */

    LogementModel incNbIndAndStatInLC(long logId, int status) throws ManagerException;

    /**
     * Increment or decrement the number of Individus in a Logement Individuel and change the status
     * @param logId  the id of logement
     * @param status the status
     * @return LogementModel
     * @throws ManagerException
     */

    LogementModel incNbMenAndStatInLI(long logId, int status) throws ManagerException;

    /**
     * Increment or decrement the number of Individus in a Menage and change the status
     * @param menId the id of a menage
     * @param status the status of the menage
     * @return MenageModel
     * @throws ManagerException
     */

    MenageModel incNbIndAndStatInMen(long menId, int status) throws ManagerException;

    /**
     * Increment or decrement the number of Deces in a Menage and change the status
     * @param menId the id of a menage
     * @param status the status of the menage
     * @return  ManagerException
     * @throws ManagerException
     */

    MenageModel incNbDecesAndStatInMen(long menId, int status) throws ManagerException;

    /**
     * Increment or decrement the number of Emigre in a Menage and change the status
     * @param menId the id of a menage
     * @param status the status of the menage
     * @return MenageModel
     * @throws ManagerException
     */

    MenageModel incNbEmAndStatInMen(long menId, int status) throws ManagerException;

    void closeDbConnections();




}
