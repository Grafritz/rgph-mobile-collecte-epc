package ht.ihsi.rgph.mobile.epc.backend.entities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import ht.ihsi.rgph.mobile.epc.backend.entities.DomaineEtude;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "tbl_domaine_etude".
*/
public class DomaineEtudeDao extends AbstractDao<DomaineEtude, Void> {

    public static final String TABLENAME = "tbl_domaine_etude";

    /**
     * Properties of entity DomaineEtude.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Code = new Property(0, String.class, "Code", false, "Code");
        public final static Property NomDomaine = new Property(1, String.class, "NomDomaine", false, "NomDomaine");
    };


    public DomaineEtudeDao(DaoConfig config) {
        super(config);
    }
    
    public DomaineEtudeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"tbl_domaine_etude\" (" + //
                "\"Code\" TEXT NOT NULL UNIQUE ," + // 0: Code
                "\"NomDomaine\" TEXT);"); // 1: NomDomaine
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"tbl_domaine_etude\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DomaineEtude entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getCode());
 
        String NomDomaine = entity.getNomDomaine();
        if (NomDomaine != null) {
            stmt.bindString(2, NomDomaine);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public DomaineEtude readEntity(Cursor cursor, int offset) {
        DomaineEtude entity = new DomaineEtude( //
            cursor.getString(offset + 0), // Code
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // NomDomaine
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DomaineEtude entity, int offset) {
        entity.setCode(cursor.getString(offset + 0));
        entity.setNomDomaine(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(DomaineEtude entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(DomaineEtude entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
