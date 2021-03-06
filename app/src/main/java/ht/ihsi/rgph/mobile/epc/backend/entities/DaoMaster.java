package ht.ihsi.rgph.mobile.epc.backend.entities;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import ht.ihsi.rgph.mobile.epc.utilities.ToastUtility;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 1): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        PersonnelDao.createTable(db, ifNotExists);
        CategorieQuestionDao.createTable(db, ifNotExists);
        QuestionDao.createTable(db, ifNotExists);
        QuestionReponseDao.createTable(db, ifNotExists);
        ModuleDao.createTable(db, ifNotExists);
        QuestionModuleDao.createTable(db, ifNotExists);
        DepartementDao.createTable(db, ifNotExists);
        CommuneDao.createTable(db, ifNotExists);
        VqseDao.createTable(db, ifNotExists);
        DomaineEtudeDao.createTable(db, ifNotExists);
        PaysDao.createTable(db, ifNotExists);
        BatimentDao.createTable(db, ifNotExists);
        LogementDao.createTable(db, ifNotExists);
        MenageDao.createTable(db, ifNotExists);
        AncienMembreDao.createTable(db, ifNotExists);
        IndividuDao.createTable(db, ifNotExists);
        RapportRARDao.createTable(db, ifNotExists);
        RapportFinalDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        PersonnelDao.dropTable(db, ifExists);
        CategorieQuestionDao.dropTable(db, ifExists);
        QuestionDao.dropTable(db, ifExists);
        QuestionReponseDao.dropTable(db, ifExists);
        ModuleDao.dropTable(db, ifExists);
        QuestionModuleDao.dropTable(db, ifExists);
        DepartementDao.dropTable(db, ifExists);
        CommuneDao.dropTable(db, ifExists);
        VqseDao.dropTable(db, ifExists);
        DomaineEtudeDao.dropTable(db, ifExists);
        PaysDao.dropTable(db, ifExists);
        BatimentDao.dropTable(db, ifExists);
        LogementDao.dropTable(db, ifExists);
        MenageDao.dropTable(db, ifExists);
        AncienMembreDao.dropTable(db, ifExists);
        IndividuDao.dropTable(db, ifExists);
        RapportRARDao.dropTable(db, ifExists);
        RapportFinalDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        private Context context;
        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
            this.context=context;
            //Log.i("rgphdb", "DevOpenHelper : "+context.getDatabasePath(name).getAbsolutePath());
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(ToastUtility.TAG + "greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
            //LoadStaticDataMngr loadStaticDataMngr=new LoadStaticDataMngr();
            //loadStaticDataMngr.loadData(this.context, db);
        }


    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        private Context context;
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(new DatabaseContext(context), name, factory);
            //super(context, name, factory);
            this.context = context;
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i(ToastUtility.TAG + "greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " ");

            //dropAllTables(db, true);
            //onCreate(db);
            DataBaseUpgrade.onUpgrade(context, db, oldVersion, newVersion);
        }
    }

    public static class DatabaseContext extends ContextWrapper {

        private static final String DIRECTORY_DATA="Data";
        private static final String DATABASE_DIR="rgph_epc_db";

        public DatabaseContext(Context base) {
            super(base);
        }

        @Override
        public File getDatabasePath(String name) {

            File dir = new File(Environment.getExternalStoragePublicDirectory(
                    DIRECTORY_DATA), DATABASE_DIR);
            if (!dir.mkdirs()) {}
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    DIRECTORY_DATA), DATABASE_DIR + File.separator + name+".db");
            return file;
        }

        @Override
        public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory) {
            SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(this.getDatabasePath(name), null);
            return  result;
        }

        @Override
        public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory, DatabaseErrorHandler errorHandler) {
            return openOrCreateDatabase(name, mode, factory);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(PersonnelDao.class);
        registerDaoClass(CategorieQuestionDao.class);
        registerDaoClass(QuestionDao.class);
        registerDaoClass(QuestionReponseDao.class);
        registerDaoClass(ModuleDao.class);
        registerDaoClass(QuestionModuleDao.class);
        registerDaoClass(DepartementDao.class);
        registerDaoClass(CommuneDao.class);
        registerDaoClass(VqseDao.class);
        registerDaoClass(DomaineEtudeDao.class);
        registerDaoClass(PaysDao.class);
        registerDaoClass(BatimentDao.class);
        registerDaoClass(LogementDao.class);
        registerDaoClass(MenageDao.class);
        registerDaoClass(AncienMembreDao.class);
        registerDaoClass(IndividuDao.class);
        registerDaoClass(RapportRARDao.class);
        registerDaoClass(RapportFinalDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
