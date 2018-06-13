package ht.ihsi.rgph.mobile.backend.entities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import ht.ihsi.rgph.mobile.backend.entities.Vqse;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "tbl_vqse".
*/
public class VqseDao extends AbstractDao<Vqse, Void> {

    public static final String TABLENAME = "tbl_vqse";

    /**
     * Properties of entity Vqse.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property VqseId = new Property(0, String.class, "VqseId", false, "VqseId");
        public final static Property VqseNom = new Property(1, String.class, "VqseNom", false, "VqseNom");
        public final static Property ComId = new Property(2, String.class, "ComId", false, "ComId");
    };


    public VqseDao(DaoConfig config) {
        super(config);
    }
    
    public VqseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"tbl_vqse\" (" + //
                "\"VqseId\" TEXT NOT NULL UNIQUE ," + // 0: VqseId
                "\"VqseNom\" TEXT," + // 1: VqseNom
                "\"ComId\" TEXT NOT NULL );"); // 2: ComId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"tbl_vqse\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Vqse entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getVqseId());
 
        String VqseNom = entity.getVqseNom();
        if (VqseNom != null) {
            stmt.bindString(2, VqseNom);
        }
        stmt.bindString(3, entity.getComId());
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public Vqse readEntity(Cursor cursor, int offset) {
        Vqse entity = new Vqse( //
            cursor.getString(offset + 0), // VqseId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // VqseNom
            cursor.getString(offset + 2) // ComId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Vqse entity, int offset) {
        entity.setVqseId(cursor.getString(offset + 0));
        entity.setVqseNom(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setComId(cursor.getString(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(Vqse entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(Vqse entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}