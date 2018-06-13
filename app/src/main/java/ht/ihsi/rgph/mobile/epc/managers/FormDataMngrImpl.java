package ht.ihsi.rgph.mobile.epc.managers;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import ht.ihsi.rgph.mobile.epc.backend.entities.CategorieQuestionDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.CommuneDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.DepartementDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.ModuleDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.PaysDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.PersonnelDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.QuestionDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.QuestionModuleDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.QuestionReponseDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.VqseDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.CategorieQuestion;
import ht.ihsi.rgph.mobile.epc.backend.entities.Commune;
import ht.ihsi.rgph.mobile.epc.backend.entities.Departement;
import ht.ihsi.rgph.mobile.epc.backend.entities.DomaineEtude;
import ht.ihsi.rgph.mobile.epc.backend.entities.Module;
import ht.ihsi.rgph.mobile.epc.backend.entities.Pays;
import ht.ihsi.rgph.mobile.epc.backend.entities.Personnel;
import ht.ihsi.rgph.mobile.epc.backend.entities.Question;
import ht.ihsi.rgph.mobile.epc.backend.entities.QuestionModule;
import ht.ihsi.rgph.mobile.epc.backend.entities.QuestionReponse;
import ht.ihsi.rgph.mobile.epc.backend.entities.Vqse;
import ht.ihsi.rgph.mobile.epc.exceptions.ManagerException;
import ht.ihsi.rgph.mobile.epc.mappers.ModelMapper;
import ht.ihsi.rgph.mobile.epc.models.CategorieQuestionModel;
import ht.ihsi.rgph.mobile.epc.models.CommuneModel;
import ht.ihsi.rgph.mobile.epc.models.DepartementModel;
import ht.ihsi.rgph.mobile.epc.models.KeyValueModel;
import ht.ihsi.rgph.mobile.epc.models.ModuleModel;
import ht.ihsi.rgph.mobile.epc.models.PersonnelModel;
import ht.ihsi.rgph.mobile.epc.models.QuestionReponseModel;
import ht.ihsi.rgph.mobile.epc.models.QuestionsModel;
import ht.ihsi.rgph.mobile.epc.models.VqseModel;

/**
 * Created by ajordany on 3/21/2016.
 */
public class FormDataMngrImpl extends AbstractDatabaseManager implements FormDataMngr, Serializable {
    /**
     * Return the categorie of a question
     *
     * @param categorie the categorie of question
     * @return CategorieQuestionModel
     */
    @Override
    public synchronized CategorieQuestionModel getCategorieQuestion(String categorie)throws ManagerException {
        //Log.i(MANAGERS, "Inside of getCategorieQuestion!");
        if(!categorie.equalsIgnoreCase("")){
            try{
                openReadableDb();
                CategorieQuestionDao categorieQuestionDao=daoSession.getCategorieQuestionDao();
                CategorieQuestion categorieQuestion=categorieQuestionDao.queryBuilder()
                        .where(CategorieQuestionDao.Properties.CodeCategorie.eq(categorie)).unique();
                if(categorieQuestion!=null){
                    return ModelMapper.MapTo(categorieQuestion);
                }
                daoSession.clear();
            }
            catch(Exception ex){
                Log.e(MANAGERS, "Exception <> unable to check this categorie" + ex.getMessage());
                throw new ManagerException("unable to check this module",ex);
            }
        }
        return null;
    }

    private static FormDataMngrImpl instance;

    public FormDataMngrImpl(final Context context) {
        super(context);
    }

    //region required methods
    public static FormDataMngrImpl getInstance(Context context){

        if (instance == null) {
            instance = new FormDataMngrImpl(context);
        }

        return instance;
    }

    @Override
    public void closeDbConnections(){
      closeConnections();
        if (instance != null) {
            instance = null;
        }
    }

    //endregion

    //region additional database managers
    @Override
    public synchronized ModuleModel checkModule(int type, int status) throws ManagerException {
        //Log.i(MANAGERS, "Inside of checkModule!");
        ModuleModel result=null;
        try {
            openReadableDb();
           // daoSession.getModuleDao().queryBuilder().count();
            Module m=daoSession.getModuleDao().queryBuilder()
                    .where(ModuleDao.Properties.TypeModule.eq(type))
                    .where(ModuleDao.Properties.EstActif.eq(true)).unique();
            if(m!=null){
                result = ModelMapper.MapTo(m);
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to check this module: " + ex.getMessage());
            throw new ManagerException("unable to check this module",ex);
        }
        return result;
    }

    @Override
    public ModuleModel checkListModuleByType(int type) throws ManagerException {
        return null;
    }

    @Override
    public synchronized QuestionsModel getFirstQuestionOfModule(String moduleId)
            throws ManagerException {
        //Log.i(MANAGERS, "Inside of getFirstQuestionOfModule! : moduleId:"+moduleId);
        QuestionsModel result=null;
        try {
            openReadableDb();
            QueryBuilder<Question> qb=daoSession.getQuestionDao().queryBuilder();
            qb.join(QuestionDao.Properties.CodeQuestion,QuestionModule.class, QuestionModuleDao.Properties.CodeQuestion)
                                .where(QuestionModuleDao.Properties.CodeModule.eq(moduleId))
                                .where(QuestionModuleDao.Properties.EstDebut.eq(true));
            Question q = qb.distinct().unique();
            if(q!=null){
                result= ModelMapper.MapTo(q);
                Log.i(MANAGERS, "getFirstQuestionOfModule - NOT NULL:");
            }else{
                Log.i(MANAGERS, "getFirstQuestionOfModule - NULL:");
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to get the first question of this module" + ex.getMessage());
            throw new ManagerException("unable to get the first question of this module",ex);
        }
        return result;
    }

    @Override
    public synchronized QuestionsModel getNextQuestionByCode(String id)
            throws ManagerException {
        //Log.i(MANAGERS, "Inside of getNextQuestionByCode! id:" + id);
        QuestionsModel result=null;
        try {
            openReadableDb();
            QueryBuilder<Question> qb=daoSession.getQuestionDao().queryBuilder();
            qb.where(QuestionDao.Properties.CodeQuestion.eq(id));
            Question q=qb.unique();
            if(q!=null){
                result= ModelMapper.MapTo(q);
                Log.i(MANAGERS, "getNextQuestionByCode - NOT NULL:");
            }else{
                Log.i(MANAGERS, "getNextQuestionByCode - NULL:");
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to get the next question of this module : " + ex.getMessage());
            throw new ManagerException("unable to get the first question of this module : ",ex);
        }
        return result;
    }

   /* @Override
    public synchronized QuestionReponseModel getResponsesByQuestionByReponse(String questionId, String reponseId)
            throws ManagerException {
        //Log.i(MANAGERS, "Inside of getResponsesByQuestionByReponse!" + " questionId:" + questionId + " / reponseId:" + reponseId);
        try {
            openReadableDb();
            QuestionReponse q = daoSession.getQuestionReponseDao().queryBuilder()
                    .where(QuestionReponseDao.Properties.CodeQuestion.eq(questionId))
                    .where(
                            new WhereCondition.StringCondition("codeUniqueReponse=" + "(Select codeUniqueReponse from tbl_reponse Where codeReponse=" + reponseId + ")")
                    )
                    .build().unique();
            //.where(QuestionReponseDao.Properties.CodeQuestion.eq(questionId))
            // .where(QuestionReponseDao.Properties.CodeUniqueReponse.eq(daoSession.re);
            // QuestionReponse q =query.unique();

            daoSession.clear();
            QuestionReponseModel reponseModel = ModelMapper.MapTo(q);
            Log.i(MANAGERS, "Inside of getResponsesByQuestionByReponse!" + " getCodeParent:" + reponseModel.getCodeParent() + " / getCodeQuestion:" + reponseModel.getCodeQuestion() + " / getCodeUniqueReponse:" + reponseModel.getCodeUniqueReponse());
            return reponseModel;

        } catch (Exception ex) {
            Log.e(MANAGERS, "Exception <> unable search reponse by question : toString:" + ex.toString());
            ex.printStackTrace();
            throw new ManagerException("unable search reponse by question " + ex.toString(), ex);
        }
    }*/

    public synchronized QuestionReponseModel getResponsesByQuestionByReponse(String questionId, String reponseId)
            throws ManagerException {
        //Log.i(MANAGERS, "Inside of getResponsesByQuestionByReponse!" + " questionId:" + questionId + " / reponseId:" + reponseId);
        try {
            openReadableDb();
            QueryBuilder<QuestionReponse> qr = daoSession.getQuestionReponseDao().queryBuilder()
                    .where(QuestionReponseDao.Properties.CodeQuestion.eq(questionId))
                    .where(QuestionReponseDao.Properties.CodeUniqueReponse.eq(questionId+"-"+reponseId));
          //  Join join=qr.join(QuestionReponseDao.Properties.CodeUniqueReponse, Reponse.class, ReponseDao.Properties.CodeUniqueReponse);
           // join;
            QuestionReponse q =qr.unique();

            daoSession.clear();
            return ModelMapper.MapTo(q);

        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable search reponse by question : " + ex.getMessage());
            ex.printStackTrace();
            throw new ManagerException("unable search reponse by question"+ex.getMessage(),ex);
        }
    }

    /*private  ReponseModel MapToQr(QuestionReponse qr) {
        ReponseModel result = null;
        if (qr != null) {
            //result = new ReponseModel();
            //ReponseModel result = new ReponseModel();
            result.setCodeParent(qr.getCodeParent());
            result.setCodeQuestion(qr.getCodeQuestion());
            result.setCodeUniqueReponse(qr.getCodeUniqueReponse());
            result.setEstEnfant(qr.getEstEnfant());
            result.setqPrecedent(qr.getQPrecedent());
            result.setqSuivant(qr.getQSuivant());
            result.setAvoirEnfant(qr.getAvoirEnfant());
            Reponse reponse = getReponseById(qr.getCodeUniqueReponse());
            if (reponse != null) {
                result.setLibelleReponse(reponse.getLibelleReponse());
                result.setCodeReponse(reponse.getCodeReponse());
            }
        }
        return result;
    }*/

    /*private  List<ReponseModel> MapTo(List<QuestionReponse> questionReponses){
        List<ReponseModel> result=null;
        if (questionReponses != null && questionReponses.size() > 0) {
            result=new ArrayList<ReponseModel>();
            for(QuestionReponse questionReponse : questionReponses) {
                ReponseModel r=new ReponseModel();
                r.setAvoirEnfant(questionReponse.getAvoirEnfant());
                r.setCodeParent(questionReponse.getCodeParent());
                r.setCodeQuestion(questionReponse.getCodeQuestion());
                r.setCodeUniqueReponse(questionReponse.getCodeUniqueReponse());
                r.setEstEnfant(questionReponse.getEstEnfant());
                r.setqPrecedent(questionReponse.getQPrecedent());
                r.setqSuivant(questionReponse.getQSuivant());
                Reponse reponse= getReponseById(questionReponse.getCodeUniqueReponse());
                if(reponse!=null){
                    r.setLibelleReponse(reponse.getLibelleReponse());
                    r.setCodeReponse(reponse.getCodeReponse());
                }
                result.add(r);
            }
        }
        return result;
    }*/

    /*public synchronized  Reponse getReponseById(String id){
        Reponse r=null;
            openReadableDb();
            r = daoSession.getReponseDao().queryBuilder().where(ReponseDao.Properties.CodeUniqueReponse.eq(id)).distinct().unique();
        //Log.e(MANAGERS, "Exception <>=========Response Id: "+r.getCodeReponse()+"/"+r.getLibelleReponse());
            daoSession.clear();
        return r;
    }*/

    @Override
    public synchronized List<QuestionReponseModel> searchQuestionReponseByQuestion(String codeQuestion)
            throws ManagerException {
        //Log.i(MANAGERS, "Inside of searchQuestionReponseByQuestion! / FOR codeQuestion:" + codeQuestion );
        List<QuestionReponseModel> result=null;
        try {
            openReadableDb();
            QueryBuilder<QuestionReponse> qr = daoSession.getQuestionReponseDao().queryBuilder()
                    .where(QuestionReponseDao.Properties.EstEnfant.eq(false))
                    .where(QuestionReponseDao.Properties.CodeQuestion.eq(codeQuestion));

            List<QuestionReponse> questionReponses = qr.list();
            if(questionReponses!=null && questionReponses.size()>0){
                Log.i(MANAGERS, "searchQuestionReponseByQuestion () questionReponses.size():" + questionReponses.size() + " / FOR codeQuestion:" + codeQuestion);
            }else{
                Log.e(MANAGERS, "searchQuestionReponseByQuestion () QuestionReponses IS NULL / FOR codeQuestion:" + codeQuestion);
            }
            daoSession.clear();
            result = ModelMapper.MapTo(questionReponses);
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable search reponse by question" + ex.toString());
            ex.printStackTrace();
            throw new ManagerException("unable search reponse by question",ex);
        }
        return result;
    }

    @Override
    public synchronized List<QuestionReponseModel> searchResponsesByQuestion(String questionId, String codeParent)
            throws ManagerException {
        //Log.i(MANAGERS, "Inside of searchResponsesByQuestion Child!  / FOR questionId:" + questionId +" / codeParent:" + codeParent);
        List<QuestionReponseModel> result=null;
        try {
            openReadableDb();
            QueryBuilder<QuestionReponse> qr = daoSession.getQuestionReponseDao().queryBuilder()
                    .where(QuestionReponseDao.Properties.EstEnfant.eq(true))
                    .where(QuestionReponseDao.Properties.CodeQuestion.eq(questionId))
                    .where(QuestionReponseDao.Properties.CodeParent.eq(codeParent));

            List<QuestionReponse> questionReponses = qr.list();
            if(questionReponses!=null && questionReponses.size()>0){
                Log.w(MANAGERS, "searchResponsesByQuestion() : size():" + questionReponses.size()+ " / FOR questionId:" + questionId +" / codeParent:" + codeParent);
            }else{
                Log.e(MANAGERS, "searchResponsesByQuestion() : QuestionReponses IS NULL / FOR questionId:" + questionId +" / codeParent:" + codeParent);
            }
            daoSession.clear();
            result= ModelMapper.MapTo(questionReponses);
        }catch(Exception ex){
            Log.e(MANAGERS, " Exception <> unable search reponse by question: " + ex.getMessage());
            ex.printStackTrace();
            throw new ManagerException("Exception unable search reponse by question: ",ex);
        }
        return result;
    }

    @Override
    public synchronized List<KeyValueModel> getAllPays() throws ManagerException {
        //Log.i(MANAGERS, "Inside of getAllPays!");
        List<KeyValueModel> result=null;
        try {
            openReadableDb();
            //List<Pays> payses=daoSession.getPaysDao().loadAll();
            List<Pays> payses=daoSession.getPaysDao().queryBuilder().orderAsc(PaysDao.Properties.NomPays).list();//.loadAll();

            if(payses!=null && payses.size()>0){
                Log.i(MANAGERS, "getAllPays () payses.size():" + payses.size());
                result=new ArrayList<KeyValueModel>();
                for(Pays p: payses){
                    result.add(new KeyValueModel(p.getCodePays(),p.getNomPays()));
                }
            }else{
                Log.e(MANAGERS, "getAllPays () pays IS NULL");
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to load pays data" + ex.getMessage());
            throw new ManagerException("unable to load pays data",ex);
        }
        return result;
    }

    @Override
    public synchronized List<KeyValueModel> getAllDepartement()
            throws ManagerException {
        //Log.i(MANAGERS, "Inside of getAllDepartement!");
        List<KeyValueModel> result=null;
        try {
            openReadableDb();
            List<Departement> depts=daoSession.getDepartementDao().queryBuilder().orderAsc(DepartementDao.Properties.DeptNom).list();//.loadAll();
            //List<Departement> depts=daoSession.getDepartementDao().loadAll();
            if(depts!=null && depts.size()>0){
                result=new ArrayList<KeyValueModel>();
                for(Departement dept: depts){
                    result.add(new KeyValueModel(dept.getDeptId(),dept.getDeptNom()));
                }
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to load pays data" + ex.getMessage());
            throw new ManagerException("unable to load pays data",ex);
        }
        return result;
    }

    @Override
    public synchronized  DepartementModel getDepartementById(String departementId) throws ManagerException {
        //Log.i(MANAGERS, "Inside of getDepartementById! departementId: "+ departementId);
        DepartementModel result = null;
        try {
            openReadableDb();
            Departement dept = daoSession.getDepartementDao().queryBuilder().where(DepartementDao.Properties.DeptId.eq(departementId)).unique();
            if( dept != null ){
                result= ModelMapper.MapTo(dept);
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to get data from the database : " + ex.getMessage());
            throw  new ManagerException("<> unable to get data from the database : ",ex);
        }
        return result;
    }

    @Override
    public synchronized List<CommuneModel> getAllCommuneByIdDept(String deptId)
            throws ManagerException {
        //Log.i(MANAGERS, "Inside of getAllCommuneByIdDept!");
        List<CommuneModel> result=null;
        try {
            openReadableDb();
            List<Commune> coms = daoSession.getCommuneDao().queryBuilder().where( CommuneDao.Properties.DeptId.eq(deptId)).orderAsc(CommuneDao.Properties.ComNom).list();
            //List<Commune> coms = daoSession.getCommuneDao().queryBuilder().where( CommuneDao.Properties.DeptId.eq(deptId)).list();
            result = ModelMapper.MapToCommune(coms);
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to load commune data" + ex.getMessage());
            throw new ManagerException("unable to load commune data",ex);
        }
        return result;
    }

    @Override
    public synchronized CommuneModel getCommuneById(String comId) throws ManagerException {
        //Log.i(MANAGERS, "Inside of getCommuneById!");
        CommuneModel result = null;
        try {
            openReadableDb();
            Commune com = daoSession.getCommuneDao().queryBuilder().where(
                    CommuneDao.Properties.ComId.eq(comId)).unique();
            if(com!=null){
                result= ModelMapper.MapTo(com);
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to get data from the database : " + ex.getMessage());
            throw  new ManagerException("<> unable to get data from the database : ",ex);
        }
        return result;
    }

    @Override
    public synchronized List<VqseModel> getAllVqseByIdCom(String comId)
            throws ManagerException {
        //Log.i(MANAGERS, "Inside of getAllVqseByIdCom!");
        List<VqseModel> result=null;
        try {
            openReadableDb();
            List<Vqse> vqses = daoSession.getVqseDao().queryBuilder().where( VqseDao.Properties.ComId.eq(comId)).orderAsc(VqseDao.Properties.VqseNom).list();
            result = ModelMapper.MapToVqse(vqses);
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to load vqse data" + ex.getMessage());
            throw new ManagerException("unable to load vqse data",ex);
        }
        return result;
    }

    @Override
    public synchronized VqseModel getVqseById(String vqseId) throws ManagerException {
        //Log.i(MANAGERS, "Inside of getVqseById!");
        VqseModel result = null;
        try {
            openReadableDb();
            Vqse vqse = daoSession.getVqseDao().queryBuilder().where(
                    VqseDao.Properties.VqseId.eq(vqseId)).unique();
            if(vqse!=null){
                result= ModelMapper.MapTo(vqse);
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to get data from the database : " + ex.getMessage());
            throw  new ManagerException("<> unable to get data from the database : ",ex);
        }
        return result;
    }

    @Override
    public synchronized List<KeyValueModel> getAllDomaineEtude()
            throws ManagerException {
        //Log.i(MANAGERS, "Inside of getAllDomaineEtude!");
        List<KeyValueModel> result=null;
        try {
            openReadableDb();
            List<DomaineEtude> dommaines=daoSession.getDomaineEtudeDao().queryBuilder().list();
            if(dommaines!=null && dommaines.size()>0){
                result=new ArrayList<KeyValueModel>();
                for(DomaineEtude domaine: dommaines){
                    result.add(new KeyValueModel(domaine.getCode(),domaine.getNomDomaine()));
                }
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to load domaine etude data" + ex.getMessage());
            throw new ManagerException("unable to load domaine etude data",ex);
        }
        return result;
    }

    @Override
    public QuestionReponseModel getQuestionResponsesByCodeQuestionByCodeReponse(String codeQuestion, String codeReponse) throws ManagerException {
        //Log.i(MANAGERS, "Inside of getQuestionResponsesByCodeQuestionByCodeReponse! : codeQuestion>>:"+codeQuestion + " / codeReponse>>:"+codeReponse);
        QuestionReponseModel result = null;
        try {
            openReadableDb();
            QuestionReponse qr = daoSession.getQuestionReponseDao().queryBuilder()
                    .where(QuestionReponseDao.Properties.CodeQuestion.eq(codeQuestion))
                    .where(QuestionReponseDao.Properties.CodeReponse.eq(codeReponse)).unique();
            if(qr != null){
                result = ModelMapper.MapTo(qr);
                Log.i(MANAGERS, "getQuestionResponsesByCodeQuestionByCodeReponse - NOT NULL:");
            }else{
                Log.e(MANAGERS, "getQuestionResponsesByCodeQuestionByCodeReponse - NULL:");
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to get the Question Responses By CodeQuestion By CodeUniqueReponse : " + ex.getMessage());
            throw new ManagerException("unable to get the Question Responses By CodeQuestion By CodeUniqueReponse : ",ex);
        }
        return result;
    }

    @Override
    public QuestionReponseModel getQuestionResponsesByCodeQuestionByCodeReponseIsParent(String codeQuestion, String codeReponse) throws ManagerException {
        //
        // Log.i(MANAGERS, "Inside of getQuestionResponsesByCodeQuestionByCodeReponseIsParent! : codeQuestion>>:"+codeQuestion + " / codeReponse>>:"+codeReponse);
        QuestionReponseModel result = null;
        try {
            openReadableDb();
            QuestionReponse qr = daoSession.getQuestionReponseDao().queryBuilder()
                    .where(QuestionReponseDao.Properties.CodeQuestion.eq(codeQuestion))
                    .where(QuestionReponseDao.Properties.EstEnfant.eq(false))
                    .where(QuestionReponseDao.Properties.CodeReponse.eq(codeReponse)).unique();
            if(qr != null){
                result = ModelMapper.MapTo(qr);
                //Log.i(MANAGERS, "getQuestionResponsesByCodeQuestionByCodeReponseIsParent - NOT NULL:");
            }else{
                //Log.e(MANAGERS, "getQuestionResponsesByCodeQuestionByCodeReponseIsParent - NULL:");
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to get the Question Responses By CodeQuestion By CodeUniqueReponse : " + ex.getMessage());
            throw new ManagerException("unable to get the Question Responses By CodeQuestion By CodeUniqueReponse : ",ex);
        }
        return result;
    }

    /**
     * @param NomUtilisateur
     * @param MotDePasse
     * @return PersonnelModel
     * @throws ManagerException
     */
    @Override
    public PersonnelModel getPersonnelInfo(String NomUtilisateur, String MotDePasse) throws ManagerException {
        //Log.i(MANAGERS, "Inside of getPersonnelInfo!");
        PersonnelModel result = null;
        try {
            openReadableDb();
            Personnel personnel = daoSession.getPersonnelDao().queryBuilder()
                    .where(PersonnelDao.Properties.NomUtilisateur.eq(NomUtilisateur))
                    .where(PersonnelDao.Properties.MotDePasse.eq(MotDePasse)).unique();
            if( personnel != null ){
                result = ModelMapper.MapTo(personnel);
            }
            daoSession.clear();
        }catch(Exception ex){
            Log.e(MANAGERS, "Exception <> unable to get Personne lInfo : " + ex.getMessage());
            throw  new ManagerException("<> unable to get Personnel Info : ",ex);
        }
        return result;
    }

    //endregion
}
