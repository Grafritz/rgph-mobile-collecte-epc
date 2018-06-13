package ht.ihsi.rgph.mobile.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import de.greenrobot.dao.query.QueryBuilder;
import ht.ihsi.rgph.mobile.backend.entities.BatimentDao;
import ht.ihsi.rgph.mobile.backend.entities.DaoSession;
import ht.ihsi.rgph.mobile.backend.entities.DecesDao;
import ht.ihsi.rgph.mobile.backend.entities.EmigreDao;
import ht.ihsi.rgph.mobile.backend.entities.IndividuDao;
import ht.ihsi.rgph.mobile.backend.entities.LogementDao;
import ht.ihsi.rgph.mobile.backend.entities.MenageDao;
import ht.ihsi.rgph.mobile.backend.entities.PersonnelDao;
import ht.ihsi.rgph.mobile.backend.entities.RapportFinalDao;
import ht.ihsi.rgph.mobile.backend.entities.RapportRARDao;
import ht.ihsi.rgph.mobile.backend.entities.Batiment;
import ht.ihsi.rgph.mobile.backend.entities.Deces;
import ht.ihsi.rgph.mobile.backend.entities.Emigre;
import ht.ihsi.rgph.mobile.backend.entities.Individu;
import ht.ihsi.rgph.mobile.backend.entities.Logement;
import ht.ihsi.rgph.mobile.backend.entities.Menage;
import ht.ihsi.rgph.mobile.backend.entities.Personnel;
import ht.ihsi.rgph.mobile.constant.Constant;
import ht.ihsi.rgph.mobile.exceptions.ManagerException;
import ht.ihsi.rgph.mobile.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.mappers.ModelMapper;
import ht.ihsi.rgph.mobile.models.BatimentModel;
import ht.ihsi.rgph.mobile.models.DecesModel;
import ht.ihsi.rgph.mobile.models.EmigreModel;
import ht.ihsi.rgph.mobile.models.IndividuModel;
import ht.ihsi.rgph.mobile.models.LogementModel;
import ht.ihsi.rgph.mobile.models.MenageModel;
import ht.ihsi.rgph.mobile.models.PersonnelModel;
import ht.ihsi.rgph.mobile.models.RapportFinalModel;
import ht.ihsi.rgph.mobile.models.RapportRARModel;
import ht.ihsi.rgph.mobile.utilities.ToastUtility;

/**
 * Created by jadme on 3/22/2016.
 */
public class CURecordMngrImpl extends AbstractDatabaseManager implements CURecordMngr {

    private static CURecordMngrImpl instance;

    public CURecordMngrImpl(final Context content) {
        super(content);
    }

    //region required methods
    public static CURecordMngrImpl getInstance(Context context) {

        if (instance == null) {
            instance = new CURecordMngrImpl(context);
        }

        return instance;
    }

    public DaoSession getDaoSession() {
        DaoSession session = daoSession;
        return session;
    }

    @Override
    public void closeDbConnections() {
        closeConnections();
        if (instance != null) {
            instance = null;
        }
    }
    //endregion

    //region database managers

    /**
     * Save a new Batiment
     *
     * @param batiment the object batiment
     * @return BatimentModel
     * @throws ManagerException
     */
    @Override
    public synchronized BatimentModel saveBatiment(BatimentModel batiment, String userCode) throws ManagerException {
        if (batiment != null) {
            openWritableDb();
            BatimentDao batimentDao = daoSession.getBatimentDao();
            long batimentId = batimentDao.insert(ModelMapper.MapToBatiment(batiment));
            if (batimentId != 0) {
                Batiment bat = batimentDao.load(batimentId);
                //Batiment bat = batimentDao.loadByRowId(batimentId);
                Log.d(ToastUtility.TAG, "saveBatiment / Batiment Insert ID:" + bat.getBatimentId() + "/SDE: " + bat.getSdeId() + "REC:" + bat.getQrec() + "RGPH: " + bat.getQrgph());
                batiment.setBatimentId(batimentId);
                daoSession.clear();
                return batiment;
            } else {
                throw new ManagerException("Error while Inserting the batiment");
            }

        } else {
            Log.d("saveBatiment", "Batiment is null ");
        }
        return null;
    }

    @Override
    public synchronized BatimentModel SaveBatiment(Long id, BatimentModel batiment, int typeEvenement, String userCode) throws ManagerException {
        if( id <= 0 ){
            return saveBatiment(batiment, userCode);
        }else{
            if( typeEvenement == Constant.ACTION_AFFICHER ) {
                return batiment;
            }else{
                batiment.setBatimentId(id);
                return updateBatiment(batiment, userCode);
            }
        }
    }

    /**
     * save a new logement
     *
     * @param logement the object logement
     * @return LogementModel
     * @throws ManagerException
     */
    @Override
    public synchronized LogementModel saveLogement(LogementModel logement, String userCode) throws ManagerException {
        if (logement != null) {
            openWritableDb();
            LogementDao logementDao = daoSession.getLogementDao();
            long logementId = logementDao.insert(ModelMapper.MapToLogement(logement));
            if (logementId != 0) {
                //Logement log = logementDao.load(logementId);
                logement.setLogeId(logementId);
                Log.d(ToastUtility.TAG, "saveLogement / Logement Insert ID:" + logementId + " Batiment ID:" + logement.getBatimentId() + " SDE:" + logement.getSdeId());
                daoSession.clear();
                return logement;
            } else {
                throw new ManagerException("Error while Inserting the logement");
            }
        }
        return null;
    }

    @Override
    public LogementModel SaveLogement(Long id, LogementModel logementModel, int typeEvenement, String userCode) throws ManagerException {
        if( id <= 0){
            logementModel = saveLogement(logementModel, userCode);
        } else{
            if( typeEvenement == Constant.ACTION_AFFICHER ) {
                return logementModel;
            }else {
                logementModel.setLogeId(id);
                logementModel = updateLogement(logementModel, userCode);
            }
        }
        return logementModel;
    }

    /**
     * Save a new menage
     *
     * @param menage the object menage
     * @return MenageModel
     * @throws ManagerException
     */
    @Override
    public synchronized MenageModel saveMenage(MenageModel menage, String userCode) throws ManagerException {
        if (menage != null) {
            MenageModel menageModel = new MenageModel();
            openWritableDb();
            MenageDao menageDao = daoSession.getMenageDao();
            long menageId = menageDao.insert(ModelMapper.MapToMenage(menage));
            if (menageId != 0) {
                Log.d(ToastUtility.TAG, "saveMenage / Menage Inserted Id:" + menageId + " Batiment ID:" + menage.getBatimentId() + " Logement:" + menage.getLogeId() + " SDE:" + menage.getSdeId());
                menageModel = menage;
                menageModel.setMenageId(menageId);
                daoSession.clear();
                return menageModel;
            } else {
                throw new ManagerException("Error while Inserting the menage");
            }
        }
        return null;
    }

    @Override
    public MenageModel SaveMenage(Long id, MenageModel menage, int typeEvenement, String userCode) throws ManagerException {
        if( id <= 0 ){
            return saveMenage(menage, userCode);
        }else{
            if( typeEvenement == Constant.ACTION_AFFICHER ) {
                return menage;
            }else {
                menage.setMenageId(id);
                return updateMenage(menage, userCode);
            }
        }
    }

    /**
     * Save a new deces
     *
     * @param deces the object deces
     * @return DecesModel
     * @throws ManagerException
     */
    @Override
    public synchronized DecesModel saveDeces(DecesModel deces, String userCode) throws ManagerException {
        if (deces != null) {
            DecesModel decesModel = new DecesModel();
            openWritableDb();
            DecesDao decesDao = daoSession.getDecesDao();
            long decesId = decesDao.insert(ModelMapper.MapToDeces(deces));
            if (decesId != 0) {
                Log.d(ToastUtility.TAG, "saveDeces / Deces Insert ID:" + decesId + " Batiment ID:" + deces.getBatimentId() + " Logement:" + deces.getLogeId() + " SDE:" + deces.getSdeId());
                decesModel = deces;
                decesModel.setDecesId(decesId);
                daoSession.clear();
                return decesModel;
            } else {
                throw new ManagerException("Error while Inserting the deces");
            }
        }
        return null;
    }

    @Override
    public DecesModel SaveDeces(Long id, DecesModel deces, int typeEvenement, String userCode) throws ManagerException {
        if( id <= 0 ){
            return saveDeces(deces, userCode);
        }else{
            if( typeEvenement == Constant.ACTION_AFFICHER ) {
                return deces;
            }else {
                deces.setDecesId(id);
                return updateDeces(deces, userCode);
            }
        }
    }

    /**
     * Save a new emigre.
     *
     * @param emigre
     * @return EmigreModel
     * @throws ManagerException
     */
    @Override
    public synchronized EmigreModel saveEmigre(EmigreModel emigre, String userCode) throws ManagerException {
        if (emigre != null) {
            EmigreModel emigreModel = new EmigreModel();
            openWritableDb();
            EmigreDao emigreDao = daoSession.getEmigreDao();
            long emigreId = emigreDao.insert(ModelMapper.MapToEmigre(emigre));
            if (emigreId != 0) {
                Log.d(ToastUtility.TAG, "saveEmigre / Emigre Inserted ID:" + emigreId + " Batiment ID:" + emigre.getBatimentId() + " Logement:" + emigre.getLogeId() + " SDE:" + emigre.getSdeId());
                emigreModel = emigre;
                emigreModel.setEmigreId(emigreId);
                daoSession.clear();
                return emigreModel;
            } else {
                throw new ManagerException("Error while inserting the emigre");
            }
        }
        return null;
    }

    @Override
    public EmigreModel SaveEmigre(Long id, EmigreModel emigre, int typeEvenement, String userCode) throws ManagerException {
        if( id <= 0 ){
            return saveEmigre(emigre, userCode);
        }else{
            if( typeEvenement == Constant.ACTION_AFFICHER ) {
                return emigre;
            }else {
                emigre.setEmigreId(id);
                return updateEmigre(emigre, userCode);
            }
        }
    }

    /**
     * Save a new individu.
     *
     * @param individu
     * @return IndividuModel
     * @throws ManagerException
     */
    @Override
    public synchronized IndividuModel saveIndividu(IndividuModel individu, String userCode) throws ManagerException {
        if (individu != null) {
            IndividuModel individuModel = new IndividuModel();
            openWritableDb();
            IndividuDao individuDao = daoSession.getIndividuDao();
            //Individu ind = ModelMapper.MapToIndividu(individu);
            long individuId = individuDao.insert(ModelMapper.MapToIndividu(individu));
            if (individuId != 0) {
                Log.d(ToastUtility.TAG, "saveIndividu / Save Individu Insert Id:" + individuId + " Batiment ID:" + individu.getBatimentId() + " Logement:" + individu.getLogeId() + " SDE:" + individu.getSdeId()+" NOM:"+individu.getQp2BNom());
                individuModel = individu;
                individuModel.setIndividuId(individuId);
                daoSession.clear();
                return individuModel;
            } else {
                throw new ManagerException("Error while inserting the individu");
            }
        }
        return null;
    }

    @Override
    public IndividuModel SaveIndividu(Long id, IndividuModel individu, int typeEvenement, String userCode) throws ManagerException {
        if( id <= 0 ){
            return saveIndividu(individu, userCode);
        }else{
            if( typeEvenement == Constant.ACTION_AFFICHER ) {
                return individu;
            }else {
                individu.setIndividuId(id);
                return updateIndividu(individu, userCode);
            }
        }
    }

    /**
     * Save a new rapportRAR
     *
     * @param rapportRARModel the object menage
     * @return RapportRARModel
     * @throws ManagerException
     */
    @Override
    public synchronized RapportRARModel saveRapportRAR(RapportRARModel rapportRARModel) throws ManagerException {
        if (rapportRARModel != null) {
            RapportRARModel rapport = new RapportRARModel();
            openWritableDb();
            RapportRARDao rapportRARDao = daoSession.getRapportRARDao();
            long rapportId = rapportRARDao.insert(ModelMapper.MapToRapportRAR(rapportRARModel));
            if (rapportId != 0) {
                Log.d(ToastUtility.TAG, "saveRapportRAR Insert Id:" + rapportId );
                rapport = rapportRARModel;
                rapport.setIndividuId(rapportId);
                daoSession.clear();
                return rapport;
            } else {
                throw new ManagerException("Error while inserting the Rapport RAR");
            }
        }
        return null;
    }

    @Override
    public RapportFinalModel saveRapportFinal(RapportFinalModel rapportFinalModel) throws ManagerException {
        if (rapportFinalModel != null) {
            RapportFinalModel rapport = new RapportFinalModel();
            openWritableDb();
            RapportFinalDao rapportFinalDao = daoSession.getRapportFinalDao();
            long rapportId = rapportFinalDao.insert(ModelMapper.MapToRapportFinal(rapportFinalModel));
            if (rapportId != 0) {
                Log.d(ToastUtility.TAG, "saveRapportFinal Insert Id:" + rapportId );
                rapport = rapportFinalModel;
                //rapport.setIndividuId(rapportId);
                daoSession.clear();
                return rapport;
            } else {
                throw new ManagerException("Error while inserting the Rapport Final");
            }
        }
        return null;
    }

    @Override
    public PersonnelModel savePersonnel(PersonnelModel personnelModel, String userCode) throws ManagerException {
        if ( personnelModel != null ) {
            openWritableDb();
            PersonnelDao personnelDao = daoSession.getPersonnelDao();
            long idpersonne = personnelDao.insert(ModelMapper.MapTo(personnelModel));
            if ( idpersonne != 0 ) {
                Personnel bat = personnelDao.load(idpersonne);
                Log.d(ToastUtility.TAG, "savePersonnelModel / Insert ID:" + bat.getPersId() );
                personnelModel.setPersId(idpersonne);
                daoSession.clear();
                return personnelModel;
            } else {
                throw new ManagerException("Error while Inserting the personnelModel ");
            }
        } else {
            Log.d(ToastUtility.TAG + "savePersonnel", "personnel is null ");
        }
        return null;
    }

    @Override
    public PersonnelModel SavePersonnel(long id, PersonnelModel personnelModel, String userCode) throws ManagerException, TextEmptyException {
        try {
            if (id <= 0) {
                Validation(personnelModel);
                personnelModel = savePersonnel(personnelModel, userCode);
            } else if (id > 0) {
                personnelModel.setPersId(id);
                personnelModel = updatePersonnel(personnelModel, userCode);
            }
        }catch (TextEmptyException ex){
            throw ex;
        }
        return personnelModel;
    }
    private void Validation(PersonnelModel personnelModel) throws TextEmptyException {
        if( GetPersonnelByCompteUtilisateur(personnelModel) ){
            throw new TextEmptyException("Ce Compte Utilisateur [ "+ personnelModel.getNomUtilisateur() +" ] est déjà enregistré");
        }
    }
    private boolean GetPersonnelByCompteUtilisateur(PersonnelModel personnelModel) {
        //Log.i(MANAGERS, "Inside of GetPersonnelByCompteUtilisateur! / codeSDE :"+personnelModel.getPersId());
        boolean result = false;
        //try {
        openReadableDb();
        Personnel loges = daoSession.getPersonnelDao().queryBuilder()
                .where(PersonnelDao.Properties.NomUtilisateur.eq(personnelModel.getNomUtilisateur())).unique();
        //result= MapToRowsBatiment(loges);
        daoSession.clear();
        if (loges == null) {
            result = false;
        }else if (loges != null) {
            long id = 0;
            if ( personnelModel.getPersId() > 0 && personnelModel != null ){
                id = personnelModel.getPersId();
            }
            if( id == 0 ){
                result = true;
            }else if (loges.getPersId() != id ){
                result = true;
            }else{
                result = false;
            }
        }
        //} catch (Exception ex) {
        //    throw ex;
        //}
        return result;
    }

    /**
     * Save a new entity
     *
     * @param entite
     * @return
     */
    @Override
    public synchronized <T> T saveEntity(T entite) throws ManagerException {
        if (entite.getClass() == BatimentModel.class) {
            saveBatiment((BatimentModel) entite,"");
            return entite;
        }
        if (entite.getClass() == LogementModel.class) {
            saveLogement((LogementModel) entite,"");
            return entite;
        }
        if (entite.getClass() == MenageModel.class) {
            saveMenage((MenageModel) entite,"");
            return entite;
        }
        if (entite.getClass() == EmigreModel.class) {
            saveEmigre((EmigreModel) entite,"");
            return entite;
        }
        if (entite.getClass() == DecesModel.class) {
            saveDeces((DecesModel) entite,"");
            return entite;
        }
        if (entite.getClass() == IndividuModel.class) {
            saveIndividu((IndividuModel) entite,"");
            return entite;
        }
        if (entite.getClass() == RapportRARModel.class) {
            saveRapportRAR((RapportRARModel) entite);
            return entite;
        }
        return null;
    }


    /**
     * Update a batiment
     *
     * @param batiment
     * @return BatimentModel
     * @throws ManagerException
     */
    @Override
    public synchronized BatimentModel updateBatiment(BatimentModel batiment, String userCode) throws ManagerException {
        if (batiment != null) {
            openReadableDb();
            BatimentDao batimentDao = daoSession.getBatimentDao();
            Log.d(ToastUtility.TAG, "BATIMENT UPDATING / Batiment Insert ID:" + batiment.getBatimentId() + "/SDE: " + batiment.getSdeId() + "REC:" + batiment.getQrec() + "RGPH: " + batiment.getQrgph());
            Batiment bat = batimentDao.load(batiment.getBatimentId());
            //Batiment bat = batimentDao.loadByRowId(batiment.getBatimentId());
            if (bat != null) {
                try {
                    bat = ModelMapper.MapToBatiment(batiment);
                    bat.setBatimentId(batiment.getBatimentId());

                    batimentDao.update(bat);
                    Log.d(ToastUtility.TAG, "BATIMENT UPDATED / Batiment Insert ID:" + bat.getBatimentId() + "/SDE: " + bat.getSdeId() + "REC:" + bat.getQrec() + "RGPH: " + bat.getQrgph());
                    daoSession.clear();
                } catch (Exception ex) {
                    throw new ManagerException("" + ex.getMessage());
                }
                daoSession.clear();
                return batiment;
            }
        }
        return null;
    }

    @Override
    public int updateStatutBatiment(long IdBatiment, short Statut, boolean isFieldAllFilled, String userCode) throws ManagerException {
        if (IdBatiment > 0 ) {
            try {
                openReadableDb();
                BatimentDao batimentDao = daoSession.getBatimentDao();
                Batiment bat = batimentDao.load(IdBatiment);
                if (bat != null) {
                    bat.setStatut(Statut);
                    bat.setIsFieldAllFilled(isFieldAllFilled);
                    bat.setIsSynchroToAppSup(false);
                    bat.setIsSynchroToCentrale(false);
                    batimentDao.update(bat);
                    return 1;
                }
            } catch (Exception ex) {
                throw new ManagerException("" + ex.getMessage());
            } finally {
                daoSession.clear();
            }
        }
        return 0;
    }

    /**
     * Update a logement.
     *
     * @param logement
     * @return LogementModel
     * @throws ManagerException
     */
    @Override
    public synchronized LogementModel updateLogement(LogementModel logement, String userCode) throws ManagerException {
        if (logement != null) {
            openReadableDb();
            LogementDao logementDao = daoSession.getLogementDao();
            Logement log=logementDao.load(logement.getLogeId());
            //Logement log=logementDao.loadByRowId(logement.getLogeId());
            Log.d(ToastUtility.TAG, "LOGEMENT B. UPDATING / BID:"+logement.getBatimentId()+" LOG:"+logement.getLogeId()+" SDE:"+logement.getSdeId());
            if (log != null) {
                log = ModelMapper.MapToLogement(logement);
                log.setLogeId(logement.getLogeId());
                try {
                    logementDao.update(log);
                    Log.d(ToastUtility.TAG, "LOGEMENT B. UPDATED / BID:" + log.getBatimentId() + " LOG:" + log.getLogeId() + " SDE:" + log.getSdeId());
                    daoSession.clear();
                    return ModelMapper.MapToLogementModel(log);
                } catch (Exception ex) {
                    throw new ManagerException("Manager Exception: " + ex.getMessage());
                }

            }
        }
        return null;
    }

    @Override
    public int updateStatutLogement(long idLogement, short Statut, boolean isFieldAllFilled, String userCode) throws ManagerException {
        if (idLogement > 0 ) {
            try {
                openReadableDb();
                LogementDao logementDao = daoSession.getLogementDao();
                Logement obj = logementDao.load(idLogement);
                if (obj != null) {
                    obj.setStatut(Statut);
                    obj.setIsFieldAllFilled(isFieldAllFilled);
                    //obj.setIsSynchroToAppSup(false);
                    //obj.setIsSynchroToCentrale(false);
                    logementDao.update(obj);
                    return 1;
                }
            } catch (Exception ex) {
                throw new ManagerException("" + ex.getMessage());
            } finally {
                daoSession.clear();
            }
        }
        return 0;
    }

    /**
     * Update a menage.
     *
     * @param menage
     * @return MenageModel
     * @throws ManagerException
     */
    @Override
    public synchronized MenageModel updateMenage(MenageModel menage, String userCode) throws ManagerException {
        if (menage != null) {
            openReadableDb();
            MenageDao menageDao = daoSession.getMenageDao();
            Menage men = menageDao.load(menage.getMenageId());
            //QueryBuilder qb = menageDao.queryBuilder();
            //qb.where(MenageDao.Properties.BatimentId.eq(menage.getBatimentId())).and(MenageDao.Properties.LogeId.eq(menage.getLogeId()), MenageDao.Properties.MenageId.eq(menage.getMenageId()));
            //Menage men = (Menage) qb.unique();
            if (men != null) {
                try {
                    men = ModelMapper.MapToMenage(menage);
                    men.setMenageId(menage.getMenageId());
                    menageDao.update(men);
                    Log.d(ToastUtility.TAG, "updateMenage / Found Update" + men.getMenageId());
                    daoSession.clear();
                    return ModelMapper.MapToMenageModel(men);
                } catch (Exception ex) {
                    throw new ManagerException("" + ex.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public int updateStatutMenage(long idMenage, short Statut, boolean isFieldAllFilled, String userCode) throws ManagerException {
        if (idMenage > 0 ) {
            try {
                openReadableDb();
                MenageDao menageDao = daoSession.getMenageDao();
                Menage obj = menageDao.load(idMenage);
                if (obj != null) {
                    obj.setStatut(Statut);
                    obj.setIsFieldAllFilled(isFieldAllFilled);
                    menageDao.update(obj);
                    return 1;
                }
            } catch (Exception ex) {
                throw new ManagerException("" + ex.getMessage());
            } finally {
                daoSession.clear();
            }
        }
        return 0;
    }

    /**
     * Update an Emigre
     *
     * @param emigre the object emigre
     * @return EmigreModel
     * @throws ManagerException
     */
    @Override
    public synchronized EmigreModel updateEmigre(EmigreModel emigre, String userCode) throws ManagerException {
        if (emigre != null) {
            openReadableDb();
            EmigreDao emigreDao = daoSession.getEmigreDao();
            Emigre em = emigreDao.load(emigre.getEmigreId());
            //QueryBuilder qb = emigreDao.queryBuilder();
            //qb.where(EmigreDao.Properties.BatimentId.eq(emigre.getBatimentId())).and(EmigreDao.Properties.LogeId.eq(emigre.getLogeId()), EmigreDao.Properties.MenageId.eq(emigre.getMenageId()), EmigreDao.Properties.EmigreId.eq(emigre.getEmigreId()));
            //Emigre em = (Emigre) qb.unique();
            if (em != null) {
                try {
                    em = ModelMapper.MapToEmigre(emigre);
                    em.setEmigreId(emigre.getEmigreId());
                    emigreDao.update(em);
                    Log.d(ToastUtility.TAG, "updateEmigre / Emigre Update:" + em.getEmigreId());
                    daoSession.clear();
                    return ModelMapper.MapToEmigreModel(em);
                } catch (Exception ex) {
                    throw new ManagerException("" + ex.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public int updateStatutEmigre(long idEmigre, short Statut, boolean isFieldAllFilled, String userCode) throws ManagerException {
        if (idEmigre > 0 ) {
            try {
                openReadableDb();
                EmigreDao emigreDao = daoSession.getEmigreDao();
                Emigre obj = emigreDao.load(idEmigre);
                if (obj != null) {
                    obj.setStatut(Statut);
                    obj.setIsFieldAllFilled(isFieldAllFilled);
                    emigreDao.update(obj);
                    return 1;
                }
            } catch (Exception ex) {
                throw new ManagerException("" + ex.getMessage());
            } finally {
                daoSession.clear();
            }
        }
        return 0;
    }

    /**
     * Update a deces.
     *
     * @param deces
     * @return DecesModel
     * @throws ManagerException
     */
    @Override
    public synchronized DecesModel updateDeces(DecesModel deces, String userCode) throws ManagerException {
        if (deces != null) {
            openReadableDb();
            DecesDao decesDao = daoSession.getDecesDao();
            Deces dec = decesDao.load(deces.getDecesId());
            //QueryBuilder qb = decesDao.queryBuilder();
            //qb.where(DecesDao.Properties.BatimentId.eq(deces.getBatimentId())).and(DecesDao.Properties.LogeId.eq(deces.getLogeId()),
            //        DecesDao.Properties.MenageId.eq(deces.getMenageId()), DecesDao.Properties.DecesId.eq(deces.getDecesId()));
            //Deces dec = (Deces) qb.unique();
            if (dec != null) {
                try {
                    dec = ModelMapper.MapToDeces(deces);
                    dec.setDecesId(deces.getDecesId());
                    decesDao.update(dec);
                    Log.d(ToastUtility.TAG, "updateDeces / Update Found:" + dec.getDecesId());
                    daoSession.clear();
                    return ModelMapper.MapToDecesModel(dec);
                } catch (Exception ex) {
                    throw new ManagerException("" + ex.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public int updateStatutDeces(long idDeces, short Statut, boolean isFieldAllFilled, String userCode) throws ManagerException {
        if (idDeces > 0 ) {
            try {
                openReadableDb();
                DecesDao decesDao = daoSession.getDecesDao();
                Deces obj = decesDao.load(idDeces);
                if (obj != null) {
                    obj.setStatut(Statut);
                    obj.setIsFieldAllFilled(isFieldAllFilled);
                    decesDao.update(obj);
                    return 1;
                }
            } catch (Exception ex) {
                throw new ManagerException("" + ex.getMessage());
            } finally {
                daoSession.clear();
            }
        }
        return 0;
    }

    /**
     * Update an individu.
     *
     * @param individu
     * @return IndividuModel
     * @throws ManagerException
     */
    @Override
    public synchronized IndividuModel updateIndividu(IndividuModel individu, String userCode) throws ManagerException {
        if (individu != null) {
            //QueryBuilder qb = individuDao.queryBuilder();
            //qb.where(IndividuDao.Properties.BatimentId.eq(individu.getBatimentId())).and(IndividuDao.Properties.LogeId.eq(individu.getLogeId()),
            //        IndividuDao.Properties.MenageId.eq(individu.getMenageId()), IndividuDao.Properties.IndividuId.eq(individu.getIndividuId()));
            //Individu ind = (Individu) qb.unique();
            openReadableDb();
            IndividuDao individuDao = daoSession.getIndividuDao();
            Log.d(ToastUtility.TAG, "INDIVIDU UPDATING  ID:" + individu.getIndividuId() );
            Individu ind  = individuDao.load(individu.getIndividuId());
            if (ind.getIndividuId() != 0) {
                try {
                    ind = ModelMapper.MapToIndividu(individu);
                    ind.setIndividuId(individu.getIndividuId());
                    individuDao.update(ind);
                    Log.d(ToastUtility.TAG, "updateIndividu / Individu Update:" + ind.getIndividuId());
                    daoSession.clear();
                    return ModelMapper.MapToIndividuModel(ind);
                } catch (Exception ex) {
                    throw new ManagerException("Impossible De faire la mise à jour" );
                }
            }
        }
        return null;
    }

    @Override
    public int updateStatutIndividu(long idIndividu, short Statut, boolean isFieldAllFilled, String userCode) throws ManagerException {
        if (idIndividu > 0 ) {
            try {
                openReadableDb();
                IndividuDao individuDao = daoSession.getIndividuDao();
                Individu obj = individuDao.load(idIndividu);
                if (obj != null) {
                    obj.setStatut(Statut);
                    obj.setIsFieldAllFilled(isFieldAllFilled);
                    individuDao.update(obj);
                    return 1;
                }
            } catch (Exception ex) {
                throw new ManagerException("" + ex.getMessage());
            } finally {
                daoSession.clear();
            }
        }
        return 0;
    }

    @Override
    public PersonnelModel updatePersonnel(PersonnelModel personnelModel, String userCode) throws ManagerException {
        if (personnelModel != null) {
            openReadableDb();
            PersonnelDao personnelDao = daoSession.getPersonnelDao();
            Personnel personnel = personnelDao.load(personnelModel.getPersId());
            //Log.d(ToastUtility.TAG, " B. UPDATING / BID:"+personnelModel.getPersId()  );
            if (personnel != null) {
                personnel = ModelMapper.MapTo(personnelModel);
                personnel.setPersId(personnelModel.getPersId());
                try {
                    personnelDao.update(personnel);
                    //Log.d(ToastUtility.TAG, "PERSONNEL UPDATING / BID:"+personnelModel.getPersId()  );
                    daoSession.clear();
                    return ModelMapper.MapTo(personnel);
                } catch (Exception ex) {
                    throw new ManagerException("Manager Exception: " + ex.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public PersonnelModel updateAllPersonnel(long persId, String userCode) throws ManagerException {
        try {
            openReadableDb();
            SQLiteDatabase db = getDatabase();

            ContentValues value = new ContentValues();
            value.put(PersonnelDao.Properties.EstActif.columnName, 0);

            db.update(PersonnelDao.TABLENAME, value, "" + PersonnelDao.Properties.PersId.columnName + " <> ?"
                            +" AND " + PersonnelDao.Properties.PersId.columnName + " <> 1"
                            +" AND " + PersonnelDao.Properties.PersId.columnName + " <> 2"
                            +" AND " + PersonnelDao.Properties.ProfileId.columnName + " = " + Constant.PRIVILEGE_AGENT,
                    new String[] { String.valueOf(persId) });

            db.close();
            daoSession.clear();
        } catch (Exception ex) {
            Log.i(MANAGERS, "<> unable to get data from the database " + ex.getMessage());
            throw new ManagerException("<> unable to get data from the database ", ex);
        }
        return null;
    }

    /**
     * Update an entity
     *
     * @param entite
     * @return T entity.
     */
    @Override
    public synchronized <T> T updateEntity(T entite) throws ManagerException {
        try {
            if (entite.getClass() == BatimentModel.class) {
                updateBatiment((BatimentModel) entite, "");
                return entite;
            }
            if (entite.getClass() == LogementModel.class) {
                updateLogement((LogementModel) entite, "");
                return entite;
            }
            if (entite.getClass() == MenageModel.class) {
                updateMenage((MenageModel) entite, "");
                return entite;
            }
            if (entite.getClass() == EmigreModel.class) {
                updateEmigre((EmigreModel) entite, "");
                return entite;
            }
            if (entite.getClass() == DecesModel.class) {
                updateDeces((DecesModel) entite, "");
                return entite;
            }
            if (entite.getClass() == IndividuModel.class) {
                updateIndividu((IndividuModel) entite, "");
                return entite;
            }
        } catch (Exception ex) {
            throw new ManagerException("" + ex.getMessage());
        }
        return null;
    }

    /**
     * Increment the number of Logement Collectif in a batiment and change the status
     *
     * @param batId  the id of a batiment
     * @param status the status of the batiment
     * @return BatimentModel
     * @throws ManagerException
     */
    @Override
    public synchronized BatimentModel incNbLogCAndStatInBat(long batId, int status) throws ManagerException {
        if (batId != 0) {
            BatimentModel batimentModel = new BatimentModel();
            openReadableDb();
            BatimentDao batimentDao = daoSession.getBatimentDao();
            Batiment bat = batimentDao.load(batId);
            //Batiment bat = batimentDao.loadByRowId(batId);
            if (bat.getBatimentId() != 0) {
                short inc = (short) (bat.getQb8NbreLogeCollectif() + 1);
                bat.setQb8NbreLogeCollectif(inc);
                bat.setStatut((short) status);
                openWritableDb();
                batimentDao.update(bat);
                Log.d(ToastUtility.TAG, "incNbLogIAndStatInBat / LG Ind Inc");
                daoSession.clear();
                return ModelMapper.MapToBatimentModel(bat);

            }

        }
        return null;

    }

    /**
     * Increment  the number of Logement Individuel in a batiment and change the status
     *
     * @param batId  the id of a batiment
     * @param status the status
     * @return BatimentModel
     * @throws ManagerException
     */
    @Override
    public synchronized BatimentModel incNbLogIAndStatInBat(long batId, int status) throws ManagerException {
        if (batId != 0) {
            openReadableDb();
            BatimentDao batimentDao = daoSession.getBatimentDao();
            Batiment bat = batimentDao.load(batId);
            //Batiment bat = batimentDao.loadByRowId(batId);
            if (bat.getBatimentId() != 0) {
                int inc = bat.getQb8NbreLogeIndividuel() + 1;
                bat.setQb8NbreLogeIndividuel((short) inc);
                bat.setStatut((short) status);
                openWritableDb();
                batimentDao.update(bat);
                Log.d(ToastUtility.TAG, "CRUD-UPDATE / BATIMENT UPDATE");
                daoSession.clear();
                return ModelMapper.MapToBatimentModel(bat);
            }
        }
        return null;
    }

    /**
     * Increment the number of Individu in a Logement Collectif and change the status
     *
     * @param logId  the id of a logement
     * @param status the status
     * @return LogementModel
     * @throws ManagerException
     */
    @Override
    public synchronized LogementModel incNbIndAndStatInLC(long logId, int status) throws ManagerException {
        if (logId != 0) {
            openReadableDb();
            LogementDao logementDao = daoSession.getLogementDao();
           Logement logement = logementDao.load(logId);
            //Logement logement = logementDao.loadByRowId(logId);
            //short inc = (short) (logement.getQlin8NbreIndividuVivant() + 1);
            //logement.setQlin11NbreIndividuVivant(inc);
            logement.setStatut((short) status);
            openWritableDb();
            logementDao.update(logement);
            Log.d(ToastUtility.TAG, "CRUD-UPDATE / LOGEMENT UPDATE");
            daoSession.clear();
            return ModelMapper.MapToLogementModel(logement);
        }
        return null;
    }

    /**
     * Increment the number of Individus in a Logement Individuel and change the status
     *
     * @param logId  the id of logement
     * @param status the status
     * @return LogementModel
     * @throws ManagerException
     */
    @Override
    public synchronized LogementModel incNbMenAndStatInLI(long logId, int status) throws ManagerException {
        if (logId != 0) {
            openReadableDb();
            LogementDao logementDao = daoSession.getLogementDao();
            Logement logement = logementDao.load(logId);
            //Logement logement = logementDao.loadByRowId(logId);
            int inc = logement.getQlin9NbreTotalMenage() + 1;
            logement.setQlin9NbreTotalMenage((short) inc);
            logement.setStatut((short) status);
            openWritableDb();
            logementDao.update(logement);
            Log.d(ToastUtility.TAG, "CRUD-UPDATE / LOGEMENT UPDATE");
            daoSession.clear();
            return ModelMapper.MapToLogementModel(logement);
        }
        return null;
    }

    /**
     * Increment  the number of Individus in a Menage and change the status
     *
     * @param menId  the id of a menage
     * @param status the status of the menage
     * @return MenageModel
     * @throws ManagerException
     */
    @Override
    public synchronized MenageModel incNbIndAndStatInMen(long menId, int status) throws ManagerException {
        if (menId != 0) {
            openReadableDb();
            MenageDao menageDao = daoSession.getMenageDao();
            Menage menage = menageDao.load(menId);
            //Menage menage = menageDao.loadByRowId(menId);
            menage.setQm11TotalIndividuVivant(menage.getQm11TotalIndividuVivant() + 1);
            menage.setStatut((short) status);
            openWritableDb();
            menageDao.update(menage);
            Log.d(ToastUtility.TAG, "CRUD-UPDATE / MENAGE UPDATE");
            daoSession.clear();
            return ModelMapper.MapToMenageModel(menage);

        }
        return null;
    }

    /**
     * Increment  the number of Deces in a Menage and change the status
     *
     * @param menId  the id of a menage
     * @param status the status of the menage
     * @return ManagerException
     * @throws ManagerException
     */
    @Override
    public synchronized MenageModel incNbDecesAndStatInMen(long menId, int status) throws ManagerException {
        if (menId != 0) {
            openReadableDb();
            MenageDao menageDao = daoSession.getMenageDao();
            Menage menage = menageDao.load(menId);
            //Menage menage = menageDao.loadByRowId(menId);
            //int count =  menage.getQm12NbreDecede() + 1;
            //menage.setQm12NbreDecede((short) count);
            menage.setStatut((short) status);
            openWritableDb();
            menageDao.update(menage);
            Log.d(ToastUtility.TAG, "CRUD-UPDATE / MENAGE UPDATE");
            daoSession.clear();
            return ModelMapper.MapToMenageModel(menage);

        }
        return null;
    }

    /**
     * Increment the number of Emigre in a Menage and change the status
     *
     * @param menId  the id of a menage
     * @param status the status of the menage
     * @return MenageModel
     * @throws ManagerException
     */
    @Override
    public synchronized MenageModel incNbEmAndStatInMen(long menId, int status) throws ManagerException {
        if (menId != 0) {
            openReadableDb();
            MenageDao menageDao = daoSession.getMenageDao();
            Menage menage = menageDao.load(menId);
            //Menage menage = menageDao.loadByRowId(menId);
            //menage.setQm11NbreEmigre((short) (menage.getQm11NbreEmigre() + 1));
            menage.setStatut((short) status);
            openWritableDb();
            menageDao.update(menage);
            Log.d(ToastUtility.TAG, "CRUD-UPDATE / MENAGE UPDATE");
            daoSession.clear();
            return ModelMapper.MapToMenageModel(menage);

        }
        return null;
    }

    //endregion
}
