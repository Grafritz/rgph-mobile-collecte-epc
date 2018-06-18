package ht.ihsi.rgph.mobile.epc.backend.entities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import ht.ihsi.rgph.mobile.epc.backend.entities.Menage;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "tbl_menage".
*/
public class MenageDao extends AbstractDao<Menage, Long> {

    public static final String TABLENAME = "tbl_menage";

    /**
     * Properties of entity Menage.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property MenageId = new Property(0, Long.class, "menageId", true, "menageId");
        public final static Property LogeId = new Property(1, Long.class, "logeId", false, "logeId");
        public final static Property BatimentId = new Property(2, Long.class, "batimentId", false, "batimentId");
        public final static Property SdeId = new Property(3, String.class, "sdeId", false, "sdeId");
        public final static Property Qm1NoOrdre = new Property(4, Short.class, "qm1NoOrdre", false, "qm1NoOrdre");
        public final static Property Qm2TotalIndividuVivant = new Property(5, Short.class, "qm2TotalIndividuVivant", false, "qm2TotalIndividuVivant");
        public final static Property Qm22IsHaveAncienMembre = new Property(6, Short.class, "qm22IsHaveAncienMembre", false, "qm22IsHaveAncienMembre");
        public final static Property Qm22TotalAncienMembre = new Property(7, Short.class, "qm22TotalAncienMembre", false, "qm22TotalAncienMembre");
        public final static Property Statut = new Property(8, Short.class, "statut", false, "statut");
        public final static Property IsValidated = new Property(9, Boolean.class, "isValidated", false, "isValidated");
        public final static Property IsFieldAllFilled = new Property(10, Boolean.class, "isFieldAllFilled", false, "isFieldAllFilled");
        public final static Property DateDebutCollecte = new Property(11, String.class, "dateDebutCollecte", false, "dateDebutCollecte");
        public final static Property DateFinCollecte = new Property(12, String.class, "dateFinCollecte", false, "dateFinCollecte");
        public final static Property DureeSaisie = new Property(13, Integer.class, "dureeSaisie", false, "dureeSaisie");
        public final static Property IsContreEnqueteMade = new Property(14, Boolean.class, "isContreEnqueteMade", false, "isContreEnqueteMade");
        public final static Property CodeAgentRecenceur = new Property(15, String.class, "codeAgentRecenceur", false, "codeAgentRecenceur");
        public final static Property IsVerified = new Property(16, Boolean.class, "isVerified", false, "isVerified");
    };


    public MenageDao(DaoConfig config) {
        super(config);
    }
    
    public MenageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"tbl_menage\" (" + //
                "\"menageId\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: menageId
                "\"logeId\" INTEGER," + // 1: logeId
                "\"batimentId\" INTEGER," + // 2: batimentId
                "\"sdeId\" TEXT," + // 3: sdeId
                "\"qm1NoOrdre\" INTEGER," + // 4: qm1NoOrdre
                "\"qm2TotalIndividuVivant\" INTEGER," + // 5: qm2TotalIndividuVivant
                "\"qm22IsHaveAncienMembre\" INTEGER," + // 6: qm22IsHaveAncienMembre
                "\"qm22TotalAncienMembre\" INTEGER," + // 7: qm22TotalAncienMembre
                "\"statut\" INTEGER," + // 8: statut
                "\"isValidated\" INTEGER," + // 9: isValidated
                "\"isFieldAllFilled\" INTEGER," + // 10: isFieldAllFilled
                "\"dateDebutCollecte\" TEXT," + // 11: dateDebutCollecte
                "\"dateFinCollecte\" TEXT," + // 12: dateFinCollecte
                "\"dureeSaisie\" INTEGER," + // 13: dureeSaisie
                "\"isContreEnqueteMade\" INTEGER," + // 14: isContreEnqueteMade
                "\"codeAgentRecenceur\" TEXT," + // 15: codeAgentRecenceur
                "\"isVerified\" INTEGER);"); // 16: isVerified
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"tbl_menage\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Menage entity) {
        stmt.clearBindings();
 
        Long menageId = entity.getMenageId();
        if (menageId != null) {
            stmt.bindLong(1, menageId);
        }
 
        Long logeId = entity.getLogeId();
        if (logeId != null) {
            stmt.bindLong(2, logeId);
        }
 
        Long batimentId = entity.getBatimentId();
        if (batimentId != null) {
            stmt.bindLong(3, batimentId);
        }
 
        String sdeId = entity.getSdeId();
        if (sdeId != null) {
            stmt.bindString(4, sdeId);
        }
 
        Short qm1NoOrdre = entity.getQm1NoOrdre();
        if (qm1NoOrdre != null) {
            stmt.bindLong(5, qm1NoOrdre);
        }
 
        Short qm2TotalIndividuVivant = entity.getQm2TotalIndividuVivant();
        if (qm2TotalIndividuVivant != null) {
            stmt.bindLong(6, qm2TotalIndividuVivant);
        }
 
        Short qm22IsHaveAncienMembre = entity.getQm22IsHaveAncienMembre();
        if (qm22IsHaveAncienMembre != null) {
            stmt.bindLong(7, qm22IsHaveAncienMembre);
        }
 
        Short qm22TotalAncienMembre = entity.getQm22TotalAncienMembre();
        if (qm22TotalAncienMembre != null) {
            stmt.bindLong(8, qm22TotalAncienMembre);
        }
 
        Short statut = entity.getStatut();
        if (statut != null) {
            stmt.bindLong(9, statut);
        }
 
        Boolean isValidated = entity.getIsValidated();
        if (isValidated != null) {
            stmt.bindLong(10, isValidated ? 1L: 0L);
        }
 
        Boolean isFieldAllFilled = entity.getIsFieldAllFilled();
        if (isFieldAllFilled != null) {
            stmt.bindLong(11, isFieldAllFilled ? 1L: 0L);
        }
 
        String dateDebutCollecte = entity.getDateDebutCollecte();
        if (dateDebutCollecte != null) {
            stmt.bindString(12, dateDebutCollecte);
        }
 
        String dateFinCollecte = entity.getDateFinCollecte();
        if (dateFinCollecte != null) {
            stmt.bindString(13, dateFinCollecte);
        }
 
        Integer dureeSaisie = entity.getDureeSaisie();
        if (dureeSaisie != null) {
            stmt.bindLong(14, dureeSaisie);
        }
 
        Boolean isContreEnqueteMade = entity.getIsContreEnqueteMade();
        if (isContreEnqueteMade != null) {
            stmt.bindLong(15, isContreEnqueteMade ? 1L: 0L);
        }
 
        String codeAgentRecenceur = entity.getCodeAgentRecenceur();
        if (codeAgentRecenceur != null) {
            stmt.bindString(16, codeAgentRecenceur);
        }
 
        Boolean isVerified = entity.getIsVerified();
        if (isVerified != null) {
            stmt.bindLong(17, isVerified ? 1L: 0L);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Menage readEntity(Cursor cursor, int offset) {
        Menage entity = new Menage( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // menageId
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // logeId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // batimentId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // sdeId
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4), // qm1NoOrdre
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5), // qm2TotalIndividuVivant
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6), // qm22IsHaveAncienMembre
            cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7), // qm22TotalAncienMembre
            cursor.isNull(offset + 8) ? null : cursor.getShort(offset + 8), // statut
            cursor.isNull(offset + 9) ? null : cursor.getShort(offset + 9) != 0, // isValidated
            cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0, // isFieldAllFilled
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // dateDebutCollecte
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // dateFinCollecte
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13), // dureeSaisie
            cursor.isNull(offset + 14) ? null : cursor.getShort(offset + 14) != 0, // isContreEnqueteMade
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // codeAgentRecenceur
            cursor.isNull(offset + 16) ? null : cursor.getShort(offset + 16) != 0 // isVerified
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Menage entity, int offset) {
        entity.setMenageId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLogeId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setBatimentId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setSdeId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setQm1NoOrdre(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4));
        entity.setQm2TotalIndividuVivant(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5));
        entity.setQm22IsHaveAncienMembre(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6));
        entity.setQm22TotalAncienMembre(cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7));
        entity.setStatut(cursor.isNull(offset + 8) ? null : cursor.getShort(offset + 8));
        entity.setIsValidated(cursor.isNull(offset + 9) ? null : cursor.getShort(offset + 9) != 0);
        entity.setIsFieldAllFilled(cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0);
        entity.setDateDebutCollecte(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setDateFinCollecte(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setDureeSaisie(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
        entity.setIsContreEnqueteMade(cursor.isNull(offset + 14) ? null : cursor.getShort(offset + 14) != 0);
        entity.setCodeAgentRecenceur(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setIsVerified(cursor.isNull(offset + 16) ? null : cursor.getShort(offset + 16) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Menage entity, long rowId) {
        entity.setMenageId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Menage entity) {
        if(entity != null) {
            return entity.getMenageId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
