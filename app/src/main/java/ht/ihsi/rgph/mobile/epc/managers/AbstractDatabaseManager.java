package ht.ihsi.rgph.mobile.epc.managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import de.greenrobot.dao.async.AsyncOperation;
import de.greenrobot.dao.async.AsyncOperationListener;
import de.greenrobot.dao.async.AsyncSession;
import ht.ihsi.rgph.mobile.epc.backend.entities.DaoMaster;
import ht.ihsi.rgph.mobile.epc.backend.entities.DaoSession;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.utilities.ToastUtility;

/**
 * Created by ajordany on 3/22/2016.
 */
public abstract  class AbstractDatabaseManager implements DatabaseManager, AsyncOperationListener {

    /**
     * Class tag. Used for debug.
     */
    protected static final String TAG = DatabaseManager.class.getCanonicalName();

    protected static final String MANAGERS = ToastUtility.TAG + "-:MANAGERS";

    /**
     * The Android Activity reference for access to DatabaseManager.
     */
    protected Context context;
    protected DaoMaster.DevOpenHelper mHelper;
    protected SQLiteDatabase database;
    protected DaoMaster daoMaster;
    protected DaoSession daoSession;
    protected AsyncSession asyncSession;
    protected List<AsyncOperation> completedOperations;

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    /**
     * Constructs a new DatabaseManager with the specified arguments.
     *
     * @param context The Android {@link Context}.
     */
    public AbstractDatabaseManager(final Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(this.context, Constant.DATABASE_NAME, null);
        completedOperations = new CopyOnWriteArrayList<AsyncOperation>();
    }


    @Override
    public void onAsyncOperationCompleted(AsyncOperation operation) {
        completedOperations.add(operation);

    }

    public abstract void closeDbConnections();

    /**
     * Query for readable DB
     */
    public void openReadableDb() throws SQLiteException {
        database = mHelper.getReadableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
        asyncSession.setListener(this);
    }

    protected void assertWaitForCompletion1Sec() {
        asyncSession.waitForCompletion(1000);
        asyncSession.isCompleted();
    }

    /**
     * Query for writable DB
     */
    public void openWritableDb() throws SQLiteException {
        database = mHelper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
        asyncSession = daoSession.startAsyncSession();
        asyncSession.setListener(this);
    }

    @Override
    public void closeConnections() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
        if (database != null && database.isOpen()) {
            database.close();
        }
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }

    }

    @Override
    public synchronized void dropDatabase() {
        try {
            openWritableDb();
            DaoMaster.dropAllTables(database, true); // drops all tables
            mHelper.onCreate(database);              // creates the tables

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
