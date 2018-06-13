package ht.ihsi.rgph.mobile.epc.managers;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.greenrobot.dao.async.AsyncOperation;
import de.greenrobot.dao.async.AsyncOperationListener;
import de.greenrobot.dao.async.AsyncSession;
import ht.ihsi.rgph.mobile.epc.backend.entities.DaoMaster;
import ht.ihsi.rgph.mobile.epc.backend.entities.DaoSession;
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
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.utilities.ToastUtility;

/**
 * Created by ajordany on 3/23/2016.
 */
public class LoadStaticDataMngr extends Activity implements AsyncOperationListener {

    protected SQLiteDatabase database;
    protected DaoMaster daoMaster;
    protected DaoSession daoSession;
    protected AsyncSession asyncSession;
    protected List<AsyncOperation> completedOperations;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAsyncOperationCompleted(AsyncOperation operation) {
        completedOperations.add(operation);

    }

    public  void loadData(Context context, SQLiteDatabase db) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();


            Log.w(ToastUtility.TAG + "MainActivity", "loadData method is called!");

            //region Insert DATA FACK
            /*  insert BATIMENT
            is = assetManager.open("data_batiment.json");
            collectionType = new TypeToken<List<Batiment>>(){}.getType();
            List<Batiment> batiments = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Batiment.class, batiments);
            Log.w(ToastUtility.TAG + "MainActivity", "BATIMENT Data inserted");*/

            /*  insert LOGEMENT
            is = assetManager.open("data_logement.json");
            collectionType = new TypeToken<List<Logement>>(){}.getType();
            List<Logement> logements = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Logement.class, logements);
            Log.w(ToastUtility.TAG + "MainActivity", "LOGEMENT Data inserted");*/

            /*  insert MENAGE
            is = assetManager.open("data_menage.json");
            collectionType = new TypeToken<List<Menage>>(){}.getType();
            List<Menage> menages = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Menage.class, menages);
            Log.w(ToastUtility.TAG + "MainActivity", "MENAGE Data inserted");*/

            /*  insert EMIGRER
            is = assetManager.open("data_emigrer.json");
            collectionType = new TypeToken<List<Emigre>>(){}.getType();
            List<Emigre> emigres = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Emigre.class, emigres);
            Log.w(ToastUtility.TAG + "MainActivity", "EMIGRER Data inserted");*/

            /*  insert DECES
            is = assetManager.open("data_deces.json");
            collectionType = new TypeToken<List<Deces>>(){}.getType();
            List<Deces> deces = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Deces.class, deces);
            Log.w(ToastUtility.TAG + "MainActivity", "DECES Data inserted");*/

            /*  insert INDIVIDUS
            is = assetManager.open("data_individus.json");
            collectionType = new TypeToken<List<Individu>>(){}.getType();
            List<Individu> individus = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Individu.class, individus);
            Log.w(ToastUtility.TAG + "MainActivity", "INDIVIDUS Data inserted");*/
            //endregion

            /*  insert COMPTE */
            is = assetManager.open(Constant.DATA_TBL_PERSONNEL);
            String result = "Conpte Utilisateur: \n";
            collectionType = new TypeToken<List<Personnel>>(){}.getType();
            List<Personnel> personnels = gson.fromJson(getStringJson(is), collectionType);
            result += "\t -Lecture du fichier JSON";
            result += "\n \t -Données Utilisateur" + ((personnels != null) ? " ["+personnels.size() +"]":"") +"\n";
            bulkInsertDaTa(Personnel.class, personnels);
            Log.w(ToastUtility.TAG + "MainActivity", result);

            /*  insert TBL_CATEGORIE_QUESTION */
            is = assetManager.open(Constant.TBL_CATEGORIE_QUESTION);
            collectionType = new TypeToken<List<CategorieQuestion>>(){}.getType();
            List<CategorieQuestion> categorieQuestions = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(CategorieQuestion.class,categorieQuestions);
            Log.w(ToastUtility.TAG + "MainActivity", "TBL_CATEGORIE_QUESTION Data inserted");

            /*  insert TBL_QUESTION */
            is = assetManager.open(Constant.TBL_QUESTION);
            collectionType = new TypeToken<List<Question>>(){}.getType();
            List<Question> questions = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Question.class,questions);
            Log.w(ToastUtility.TAG + "MainActivity", "TBL_QUESTION Data inserted");
/**/
            /*  insert TBL_REPONSES */
           /* is = assetManager.open(Constant.TBL_REPONSES);
            collectionType = new TypeToken<List<Reponse>>(){}.getType();
            List<Reponse> reponses = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Reponse.class, reponses);
            Log.w(ToastUtility.TAG + "MainActivity", "TBL_REPONSES Data inserted");*/

             /*  insert TBL_QUESTIONS_REPONSES */
            is = assetManager.open(Constant.TBL_QUESTIONS_REPONSES);
            collectionType = new TypeToken<List<QuestionReponse>>(){}.getType();
            List<QuestionReponse> questionReponses = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(QuestionReponse.class,questionReponses);
            Log.w(ToastUtility.TAG + "MainActivity", "TBL_QUESTIONS_REPONSES Data inserted");

             /*  insert TBL_MODULE */
            is = assetManager.open(Constant.TBL_MODULE);
            collectionType = new TypeToken<List<Module>>(){}.getType();
            List<Module> modules = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Module.class,modules);
            Log.w(ToastUtility.TAG + "MainActivity", "TBL_MODULE Data inserted");

             /*  insert TBL_QUESTIONS_MODULE */
            is = assetManager.open(Constant.TBL_QUESTIONS_MODULE);
            collectionType = new TypeToken<List<QuestionModule>>(){}.getType();
            List<QuestionModule> questionModules = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(QuestionModule.class,questionModules);
            Log.w(ToastUtility.TAG + "MainActivity", "TBL_QUESTIONS_MODULE Data inserted");

            /*  insert TBL_PAYS */
            is = assetManager.open(Constant.TBL_PAYS);
            collectionType = new TypeToken<List<Pays>>(){}.getType();
            List<Pays> payses = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Pays.class, payses);
            Log.w(ToastUtility.TAG + "MainActivity", "TBL_PAYS Data inserted");

             /*  insert TBL_DEPARTEMENT */
            is = assetManager.open(Constant.TBL_DEPARTEMENT);
            collectionType = new TypeToken<List<Departement>>(){}.getType();
            List<Departement> departements = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Departement.class,departements);
            Log.w(ToastUtility.TAG + "MainActivity", "TBL_DEPARTEMENT Data inserted");

            /*  insert TBL_COMMUNE */
            is = assetManager.open(Constant.TBL_COMMUNE);
            collectionType = new TypeToken<List<Commune>>(){}.getType();
            List<Commune> communes = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Commune.class,communes);
            Log.w(ToastUtility.TAG + "MainActivity", "TBL_COMMUNE Data inserted");

            /*  insert TBL_VQSE */
            is = assetManager.open(Constant.TBL_VQSE);
            collectionType = new TypeToken<List<Vqse>>(){}.getType();
            List<Vqse> vqses = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(Vqse.class,vqses);
            Log.w(ToastUtility.TAG + "MainActivity", "TBL_VQSE Data inserted");

            /*  insert TBL_DOMAINE_ETUDE */
            is = assetManager.open(Constant.TBL_DOMAINE_ETUDE);
            collectionType = new TypeToken<List<DomaineEtude>>(){}.getType();
            List<DomaineEtude> domaineEtudes = gson.fromJson(getStringJson(is), collectionType);
            bulkInsertDaTa(DomaineEtude.class,domaineEtudes);
            Log.w(ToastUtility.TAG + "MainActivity", "TBL_DOMAINE_ETUDE Data inserted");

            //Log.w(ToastUtility.TAG + "MainActivity", "Data Module loaded");

        } catch (Exception ex) {
            Log.e(ToastUtility.TAG + "MainActivity", "error loade data: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }

    //region LOAD DATA ALL
    String result = "";
    public synchronized String loadData_PERSONNEL(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(ToastUtility.TAG + "MainActivity", "loadData_PERSONNEL method is called!");

             /*  insert COMPTE */
            is = assetManager.open(Constant.DATA_TBL_PERSONNEL);
            result = "Compte Utilisateur: \n";
            textView.setText(result);
            collectionType = new TypeToken<List<Personnel>>(){}.getType();
            List<Personnel> personnels = gson.fromJson(getStringJson(is), collectionType);
            result += "\t -Lecture du fichier JSON";
            textView.setText(result);
            result += "\n \t -Données Utilisateur" + ((personnels != null) ? " ["+personnels.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Personnel.class, personnels);
            Log.w(ToastUtility.TAG + "DATA_TBL_PERSONNEL", result);
        } catch (Exception ex) {
            result += "Erreur:"+ ex.toString();
            textView.setText(result);
            Log.e(ToastUtility.TAG + " : DATA_TBL_PERSONNEL", "error load data: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_DEPARTEMENT(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(ToastUtility.TAG + "MainActivity", "loadData_DEPARTEMENT method is called!");

             /*  insert TBL_DEPARTEMENT */
            is = assetManager.open(Constant.TBL_DEPARTEMENT);
            result = " \n Département:";
            textView.setText(result);
            collectionType = new TypeToken<List<Departement>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<Departement> departements = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((departements != null) ? " ["+departements.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Departement.class, departements);
            Log.w(ToastUtility.TAG + "MainActivity", result);
        } catch (Exception ex) {
            result += " \n \t * Erreur:"+ ex.toString();
            textView.setText(result);
            Log.e(ToastUtility.TAG + "MainActivity", "error load data: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_COMMUNE(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(ToastUtility.TAG + "MainActivity", "loadData_COMMUNE method is called!");

            /*  insert TBL_COMMUNE */
            is = assetManager.open(Constant.TBL_COMMUNE);
            result = " \n Commune:";
            textView.setText(result);
            collectionType = new TypeToken<List<Commune>>(){}.getType();
            List<Commune> communes = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            result += " \n \t -Données " + ((communes != null) ? " ["+communes.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Commune.class, communes);
            Log.w(ToastUtility.TAG + "MainActivity", result);
        } catch (Exception ex) {
            result += " \n \t * Erreur:"+ ex.toString();
            Log.e(ToastUtility.TAG + "MainActivity", "error loade data: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_VQSE(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(ToastUtility.TAG + "MainActivity", "loadData_VQSE method is called!");

            /*  insert TBL_VQSE */
            is = assetManager.open(Constant.TBL_VQSE);
            result = " \n VQSE:";
            textView.setText(result);
            collectionType = new TypeToken<List<Vqse>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<Vqse> vqses = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((vqses != null) ? " ["+vqses.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Vqse.class, vqses);
            Log.w(ToastUtility.TAG + "MainActivity", result);
        } catch (Exception ex) {
            Log.e(ToastUtility.TAG + "MainActivity", "error loade data: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_PAYS(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(ToastUtility.TAG + "MainActivity", "loadData_VQSE method is called!");

            /*  insert TBL_VQSE */
            is = assetManager.open(Constant.TBL_PAYS);
            result = " \n PAYS:";
            textView.setText(result);
            collectionType = new TypeToken<List<Pays>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<Pays> listData = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((listData != null) ? " ["+listData.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Pays.class, listData);
            Log.w(ToastUtility.TAG + "Pays", result);
        } catch (Exception ex) {
            Log.e(ToastUtility.TAG + "MainActivity", "error loadData: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_CATEGORIE_QUESTION(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(ToastUtility.TAG + "MainActivity", "loadData_VQSE method is called!");

            /*  insert TBL_VQSE */
            is = assetManager.open(Constant.TBL_CATEGORIE_QUESTION);
            result = " \n PAYS:";
            textView.setText(result);
            collectionType = new TypeToken<List<CategorieQuestion>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<CategorieQuestion> listData = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((listData != null) ? " ["+listData.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(CategorieQuestion.class, listData);
            Log.w(ToastUtility.TAG + "Pays", result);
        } catch (Exception ex) {
            Log.e(ToastUtility.TAG + "MainActivity", "error loadData: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_QUESTION(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(ToastUtility.TAG + "MainActivity", "TBL_QUESTION method is called!");

            /*  insert TBL_VQSE */
            is = assetManager.open(Constant.TBL_QUESTION);
            result = " \n Question:";
            textView.setText(result);
            collectionType = new TypeToken<List<Question>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<Question> listData = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((listData != null) ? " ["+listData.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Question.class, listData);
            Log.w(ToastUtility.TAG + "MainActivity", result);
        } catch (Exception ex) {
            Log.e(ToastUtility.TAG + "MainActivity", "error loadData: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_QUESTIONS_REPONSES(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(ToastUtility.TAG + "MainActivity", "TBL_QUESTIONS_REPONSES method is called!");

            /*  insert TBL_VQSE */
            is = assetManager.open(Constant.TBL_QUESTIONS_REPONSES);
            result = " \n QuestionReponse:";
            textView.setText(result);
            collectionType = new TypeToken<List<QuestionReponse>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<QuestionReponse> listData = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((listData != null) ? " ["+listData.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(QuestionReponse.class, listData);
            Log.w(ToastUtility.TAG + "Pays", result);
        } catch (Exception ex) {
            Log.e(ToastUtility.TAG + "MainActivity", "error loadData: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_MODULE(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(ToastUtility.TAG + "MainActivity", "TBL_MODULE method is called!");

            is = assetManager.open(Constant.TBL_MODULE);
            result = " \n PAYS:";
            textView.setText(result);
            collectionType = new TypeToken<List<Module>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<Module> listData = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((listData != null) ? " ["+listData.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(Module.class, listData);
            Log.w(ToastUtility.TAG + "MainActivity", result);
        } catch (Exception ex) {
            Log.e(ToastUtility.TAG + "MainActivity", "error loadData: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_QUESTIONS_MODULE(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(ToastUtility.TAG + "MainActivity", "TBL_QUESTIONS_MODULE method is called!");

            is = assetManager.open(Constant.TBL_QUESTIONS_MODULE);
            result = " \n PAYS:";
            textView.setText(result);
            collectionType = new TypeToken<List<QuestionModule>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<QuestionModule> listData = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((listData != null) ? " ["+listData.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(QuestionModule.class, listData);
            Log.w(ToastUtility.TAG + "MainActivity", result);
        } catch (Exception ex) {
            Log.e(ToastUtility.TAG + "MainActivity", "error loadData: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    public synchronized String loadData_DOMAINE_ETUDE(Context context, SQLiteDatabase db, TextView textView) {
        this.database = db;
        InputStream is =null;
        Gson gson = new Gson();
        Type collectionType =null;
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
        try {
            AssetManager assetManager = context.getAssets();
            Log.i(ToastUtility.TAG + "MainActivity", "TBL_DOMAINE_ETUDE method is called!");

            /*  insert TBL_VQSE */
            is = assetManager.open(Constant.TBL_DOMAINE_ETUDE);
            result = " \n PAYS:";
            textView.setText(result);
            collectionType = new TypeToken<List<DomaineEtude>>(){}.getType();
            result += " \n \t -Lecture du fichier JSON";
            textView.setText(result);
            List<DomaineEtude> listData = gson.fromJson(getStringJson(is), collectionType);
            result += " \n \t -Données " + ((listData != null) ? " ["+listData.size() +"]":"") +"\n";
            textView.setText(result);
            bulkInsertDaTa(DomaineEtude.class, listData);
            Log.w(ToastUtility.TAG + "MainActivity", result);
        } catch (Exception ex) {
            Log.e(ToastUtility.TAG + "MainActivity", "error loadData: " + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }
    //endregion

    private  void bulkInsertDaTa(Class cl, List<?> entities){
        try {
            if (entities != null && entities.size() > 0) {

                Log.w(ToastUtility.TAG + "MainActivity", " Classe:" + cl.getSimpleName().toUpperCase() + " / size(): " + entities.size());
                openWritableDb();
                asyncSession.insertOrReplaceInTx(cl, entities);
                assertWaitForCompletion1Sec();
                daoSession.clear();
                //Log.e(ToastUtility.TAG + "MainActivity", "bulkInsertDaTa : DATA INSERT = " + entities.size());
            }else{
                Log.e(ToastUtility.TAG + "MainActivity", "bulkInsertDaTa : entities.size() = 0 " );
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(ToastUtility.TAG + "MainActivity", "Exception - bulkInsertDaTa error insertind data: " + e.getMessage());
        }
    }

    private void assertWaitForCompletion1Sec() {
        asyncSession.waitForCompletion(2000);
        asyncSession.isCompleted();
    }

    /**
     * Query for writable DB
     */
    public void openWritableDb() throws SQLiteException {
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
        asyncSession.setListener(this);
    }

    public static String getStringJson(InputStream is) {
        String json = "";
        try {
            //InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            //Log.w(ToastUtility.TAG + "MainActivity", "Load Json data json :" + json);
        } catch (Exception ex) {
            Log.e(ToastUtility.TAG + "MainActivity", "Load Json data failed!!" + ex.getMessage());
        }
        //Log.i(ToastUtility.TAG + "MainActivity", "getStringJson(): json=" + json);
        return json;
    }
}
