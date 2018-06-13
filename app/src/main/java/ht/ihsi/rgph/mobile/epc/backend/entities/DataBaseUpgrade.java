package ht.ihsi.rgph.mobile.epc.backend.entities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import ht.ihsi.rgph.mobile.epc.managers.LoadStaticDataMngr;

import static ht.ihsi.rgph.mobile.epc.backend.entities.DaoMaster.createAllTables;
import static ht.ihsi.rgph.mobile.epc.backend.entities.DaoMaster.dropAllTables;

/**
 * Created by JFDuverseau on 5/23/2018.
 */

public class DataBaseUpgrade {
    public final static int UPGRADE_TO_VERSION_3 = 3; //[ MISE A JOUR 23-Mai-2018 ]

    //region UPDATA
    public static void onUpgrade(Context context, SQLiteDatabase db, int oldVersion, int newVersion) {
        while (oldVersion < newVersion) {
            oldVersion++;
            switch (oldVersion) {//switch (newVersion) {
                case UPGRADE_TO_VERSION_3:
                    MiseAJour_AllDataBase(context, db, true);
                    break;
            }
        }
    }

    //endregion

    //region VERSION 3 - [ MISE A JOUR 23-Mai-2018 ]
    public static void MiseAJour_AllDataBase(Context context, SQLiteDatabase db, boolean ifNotExists){
        dropAllTables(db, true);
        createAllTables(db, true);
    }
    //endregion

    public static void MiseAjour_Version2(Context context, SQLiteDatabase db, boolean ifNotExists) {
        //db.execSQL("ALTER TABLE  CHANT ADD COLUMN  CODE_CHANT  TEXT"); // 2: CodeChant "
        //Log.i(Tools.TAG + "MiseAjour_Version2", "ADD COLUMN CODE_CHANT");

        //db.execSQL("ALTER TABLE  CHANT ADD COLUMN  IS_UPDATE   INTEGER"); // 3: IsUpdate
        //Log.i(Tools.TAG + "MiseAjour_Version2", "ADD COLUMN IS_UPDATE");

        dropAllTables_Localisation(db, ifNotExists);
        createAllTables_Version2(db, ifNotExists);
        LoadAll_DataLocalisation(context, db);
    }

    //region VERSION --- - test
    public static void LoadAll_DataLocalisation(Context context, SQLiteDatabase db ) {
        LoadStaticDataMngr loadStaticDataMngr = new LoadStaticDataMngr();
        loadStaticDataMngr.loadData(context, db);
    }

    public static void createAllTables_Version2(SQLiteDatabase db, boolean ifNotExists) {
       /* PaysDao.createTable(db, ifNotExists);
        DepartementDao.createTable(db, ifNotExists);
        CommuneDao.createTable(db, ifNotExists);
        // CHANT Favorite
        ChantFavoriteDao.createTable(db, ifNotExists);*/
    }

    public static void dropAllTables_Localisation(SQLiteDatabase db, boolean ifExists) {
       /* PaysDao.dropTable(db, ifExists);
        Log.i(Tools.TAG + "Version2", "drop Table Pays");
        DepartementDao.dropTable(db, ifExists);
        Log.i(Tools.TAG + "Version2", "drop Table Departement");
        CommuneDao.dropTable(db, ifExists);
        Log.i(Tools.TAG + "Version2", "drop Table Commune");*/
    }
    //endregion

//endregion
}
