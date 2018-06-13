package ht.ihsi.rgph.mobile.backend.entities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import ht.ihsi.rgph.mobile.backend.entities.Menage;

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
        public final static Property Qm2ModeJouissance = new Property(5, Short.class, "qm2ModeJouissance", false, "qm2ModeJouissance");
        public final static Property Qm3ModeObtentionLoge = new Property(6, Short.class, "qm3ModeObtentionLoge", false, "qm3ModeObtentionLoge");
        public final static Property Qm4_1ModeAprovEauABoire = new Property(7, Short.class, "qm4_1ModeAprovEauABoire", false, "qm4_1ModeAprovEauABoire");
        public final static Property Qm4_2ModeAprovEauAUsageCourant = new Property(8, Short.class, "qm4_2ModeAprovEauAUsageCourant", false, "qm4_2ModeAprovEauAUsageCourant");
        public final static Property Qm5SrcEnergieCuisson1 = new Property(9, Short.class, "qm5SrcEnergieCuisson1", false, "qm5SrcEnergieCuisson1");
        public final static Property Qm5SrcEnergieCuisson2 = new Property(10, Short.class, "qm5SrcEnergieCuisson2", false, "qm5SrcEnergieCuisson2");
        public final static Property Qm6TypeEclairage = new Property(11, Short.class, "qm6TypeEclairage", false, "qm6TypeEclairage");
        public final static Property Qm7ModeEvacDechet = new Property(12, Short.class, "qm7ModeEvacDechet", false, "qm7ModeEvacDechet");
        public final static Property Qm8EndroitBesoinPhysiologique = new Property(13, Short.class, "qm8EndroitBesoinPhysiologique", false, "qm8EndroitBesoinPhysiologique");
        public final static Property Qm9NbreRadio1 = new Property(14, Integer.class, "qm9NbreRadio1", false, "qm9NbreRadio1");
        public final static Property Qm9NbreTelevision2 = new Property(15, Integer.class, "qm9NbreTelevision2", false, "qm9NbreTelevision2");
        public final static Property Qm9NbreRefrigerateur3 = new Property(16, Integer.class, "qm9NbreRefrigerateur3", false, "qm9NbreRefrigerateur3");
        public final static Property Qm9NbreFouElectrique4 = new Property(17, Integer.class, "qm9NbreFouElectrique4", false, "qm9NbreFouElectrique4");
        public final static Property Qm9NbreOrdinateur5 = new Property(18, Integer.class, "qm9NbreOrdinateur5", false, "qm9NbreOrdinateur5");
        public final static Property Qm9NbreMotoBicyclette6 = new Property(19, Integer.class, "qm9NbreMotoBicyclette6", false, "qm9NbreMotoBicyclette6");
        public final static Property Qm9NbreVoitureMachine7 = new Property(20, Integer.class, "qm9NbreVoitureMachine7", false, "qm9NbreVoitureMachine7");
        public final static Property Qm9NbreBateau8 = new Property(21, Integer.class, "qm9NbreBateau8", false, "qm9NbreBateau8");
        public final static Property Qm9NbrePanneauGeneratrice9 = new Property(22, Integer.class, "qm9NbrePanneauGeneratrice9", false, "qm9NbrePanneauGeneratrice9");
        public final static Property Qm9NbreMilletChevalBourique10 = new Property(23, Integer.class, "qm9NbreMilletChevalBourique10", false, "qm9NbreMilletChevalBourique10");
        public final static Property Qm9NbreBoeufVache11 = new Property(24, Integer.class, "qm9NbreBoeufVache11", false, "qm9NbreBoeufVache11");
        public final static Property Qm9NbreCochonCabrit12 = new Property(25, Integer.class, "qm9NbreCochonCabrit12", false, "qm9NbreCochonCabrit12");
        public final static Property Qm9NbreBeteVolaille13 = new Property(26, Integer.class, "qm9NbreBeteVolaille13", false, "qm9NbreBeteVolaille13");
        public final static Property Qm10AvoirPersDomestique = new Property(27, Short.class, "qm10AvoirPersDomestique", false, "qm10AvoirPersDomestique");
        public final static Property Qm10TotalDomestiqueFille = new Property(28, Short.class, "qm10TotalDomestiqueFille", false, "qm10TotalDomestiqueFille");
        public final static Property Qm10TotalDomestiqueGarcon = new Property(29, Short.class, "qm10TotalDomestiqueGarcon", false, "qm10TotalDomestiqueGarcon");
        public final static Property Qm11TotalIndividuVivant = new Property(30, Integer.class, "qm11TotalIndividuVivant", false, "qm11TotalIndividuVivant");
        public final static Property Qn1Emigration = new Property(31, Short.class, "qn1Emigration", false, "qn1Emigration");
        public final static Property Qn1NbreEmigre = new Property(32, Short.class, "qn1NbreEmigre", false, "qn1NbreEmigre");
        public final static Property Qd1Deces = new Property(33, Short.class, "qd1Deces", false, "qd1Deces");
        public final static Property Qd1NbreDecede = new Property(34, Short.class, "qd1NbreDecede", false, "qd1NbreDecede");
        public final static Property Statut = new Property(35, Short.class, "statut", false, "statut");
        public final static Property IsValidated = new Property(36, Boolean.class, "isValidated", false, "isValidated");
        public final static Property DateDebutCollecte = new Property(37, String.class, "dateDebutCollecte", false, "dateDebutCollecte");
        public final static Property DateFinCollecte = new Property(38, String.class, "dateFinCollecte", false, "dateFinCollecte");
        public final static Property DureeSaisie = new Property(39, Integer.class, "dureeSaisie", false, "dureeSaisie");
        public final static Property IsFieldAllFilled = new Property(40, Boolean.class, "isFieldAllFilled", false, "isFieldAllFilled");
        public final static Property IsContreEnqueteMade = new Property(41, Boolean.class, "isContreEnqueteMade", false, "isContreEnqueteMade");
        public final static Property CodeAgentRecenceur = new Property(42, String.class, "codeAgentRecenceur", false, "codeAgentRecenceur");
        public final static Property IsVerified = new Property(43, Boolean.class, "isVerified", false, "isVerified");
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
                "\"qm2ModeJouissance\" INTEGER," + // 5: qm2ModeJouissance
                "\"qm3ModeObtentionLoge\" INTEGER," + // 6: qm3ModeObtentionLoge
                "\"qm4_1ModeAprovEauABoire\" INTEGER," + // 7: qm4_1ModeAprovEauABoire
                "\"qm4_2ModeAprovEauAUsageCourant\" INTEGER," + // 8: qm4_2ModeAprovEauAUsageCourant
                "\"qm5SrcEnergieCuisson1\" INTEGER," + // 9: qm5SrcEnergieCuisson1
                "\"qm5SrcEnergieCuisson2\" INTEGER," + // 10: qm5SrcEnergieCuisson2
                "\"qm6TypeEclairage\" INTEGER," + // 11: qm6TypeEclairage
                "\"qm7ModeEvacDechet\" INTEGER," + // 12: qm7ModeEvacDechet
                "\"qm8EndroitBesoinPhysiologique\" INTEGER," + // 13: qm8EndroitBesoinPhysiologique
                "\"qm9NbreRadio1\" INTEGER," + // 14: qm9NbreRadio1
                "\"qm9NbreTelevision2\" INTEGER," + // 15: qm9NbreTelevision2
                "\"qm9NbreRefrigerateur3\" INTEGER," + // 16: qm9NbreRefrigerateur3
                "\"qm9NbreFouElectrique4\" INTEGER," + // 17: qm9NbreFouElectrique4
                "\"qm9NbreOrdinateur5\" INTEGER," + // 18: qm9NbreOrdinateur5
                "\"qm9NbreMotoBicyclette6\" INTEGER," + // 19: qm9NbreMotoBicyclette6
                "\"qm9NbreVoitureMachine7\" INTEGER," + // 20: qm9NbreVoitureMachine7
                "\"qm9NbreBateau8\" INTEGER," + // 21: qm9NbreBateau8
                "\"qm9NbrePanneauGeneratrice9\" INTEGER," + // 22: qm9NbrePanneauGeneratrice9
                "\"qm9NbreMilletChevalBourique10\" INTEGER," + // 23: qm9NbreMilletChevalBourique10
                "\"qm9NbreBoeufVache11\" INTEGER," + // 24: qm9NbreBoeufVache11
                "\"qm9NbreCochonCabrit12\" INTEGER," + // 25: qm9NbreCochonCabrit12
                "\"qm9NbreBeteVolaille13\" INTEGER," + // 26: qm9NbreBeteVolaille13
                "\"qm10AvoirPersDomestique\" INTEGER," + // 27: qm10AvoirPersDomestique
                "\"qm10TotalDomestiqueFille\" INTEGER," + // 28: qm10TotalDomestiqueFille
                "\"qm10TotalDomestiqueGarcon\" INTEGER," + // 29: qm10TotalDomestiqueGarcon
                "\"qm11TotalIndividuVivant\" INTEGER," + // 30: qm11TotalIndividuVivant
                "\"qn1Emigration\" INTEGER," + // 31: qn1Emigration
                "\"qn1NbreEmigre\" INTEGER," + // 32: qn1NbreEmigre
                "\"qd1Deces\" INTEGER," + // 33: qd1Deces
                "\"qd1NbreDecede\" INTEGER," + // 34: qd1NbreDecede
                "\"statut\" INTEGER," + // 35: statut
                "\"isValidated\" INTEGER," + // 36: isValidated
                "\"dateDebutCollecte\" TEXT," + // 37: dateDebutCollecte
                "\"dateFinCollecte\" TEXT," + // 38: dateFinCollecte
                "\"dureeSaisie\" INTEGER," + // 39: dureeSaisie
                "\"isFieldAllFilled\" INTEGER," + // 40: isFieldAllFilled
                "\"isContreEnqueteMade\" INTEGER," + // 41: isContreEnqueteMade
                "\"codeAgentRecenceur\" TEXT," + // 42: codeAgentRecenceur
                "\"isVerified\" INTEGER);"); // 43: isVerified
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
 
        Short qm2ModeJouissance = entity.getQm2ModeJouissance();
        if (qm2ModeJouissance != null) {
            stmt.bindLong(6, qm2ModeJouissance);
        }
 
        Short qm3ModeObtentionLoge = entity.getQm3ModeObtentionLoge();
        if (qm3ModeObtentionLoge != null) {
            stmt.bindLong(7, qm3ModeObtentionLoge);
        }
 
        Short qm4_1ModeAprovEauABoire = entity.getQm4_1ModeAprovEauABoire();
        if (qm4_1ModeAprovEauABoire != null) {
            stmt.bindLong(8, qm4_1ModeAprovEauABoire);
        }
 
        Short qm4_2ModeAprovEauAUsageCourant = entity.getQm4_2ModeAprovEauAUsageCourant();
        if (qm4_2ModeAprovEauAUsageCourant != null) {
            stmt.bindLong(9, qm4_2ModeAprovEauAUsageCourant);
        }
 
        Short qm5SrcEnergieCuisson1 = entity.getQm5SrcEnergieCuisson1();
        if (qm5SrcEnergieCuisson1 != null) {
            stmt.bindLong(10, qm5SrcEnergieCuisson1);
        }
 
        Short qm5SrcEnergieCuisson2 = entity.getQm5SrcEnergieCuisson2();
        if (qm5SrcEnergieCuisson2 != null) {
            stmt.bindLong(11, qm5SrcEnergieCuisson2);
        }
 
        Short qm6TypeEclairage = entity.getQm6TypeEclairage();
        if (qm6TypeEclairage != null) {
            stmt.bindLong(12, qm6TypeEclairage);
        }
 
        Short qm7ModeEvacDechet = entity.getQm7ModeEvacDechet();
        if (qm7ModeEvacDechet != null) {
            stmt.bindLong(13, qm7ModeEvacDechet);
        }
 
        Short qm8EndroitBesoinPhysiologique = entity.getQm8EndroitBesoinPhysiologique();
        if (qm8EndroitBesoinPhysiologique != null) {
            stmt.bindLong(14, qm8EndroitBesoinPhysiologique);
        }
 
        Integer qm9NbreRadio1 = entity.getQm9NbreRadio1();
        if (qm9NbreRadio1 != null) {
            stmt.bindLong(15, qm9NbreRadio1);
        }
 
        Integer qm9NbreTelevision2 = entity.getQm9NbreTelevision2();
        if (qm9NbreTelevision2 != null) {
            stmt.bindLong(16, qm9NbreTelevision2);
        }
 
        Integer qm9NbreRefrigerateur3 = entity.getQm9NbreRefrigerateur3();
        if (qm9NbreRefrigerateur3 != null) {
            stmt.bindLong(17, qm9NbreRefrigerateur3);
        }
 
        Integer qm9NbreFouElectrique4 = entity.getQm9NbreFouElectrique4();
        if (qm9NbreFouElectrique4 != null) {
            stmt.bindLong(18, qm9NbreFouElectrique4);
        }
 
        Integer qm9NbreOrdinateur5 = entity.getQm9NbreOrdinateur5();
        if (qm9NbreOrdinateur5 != null) {
            stmt.bindLong(19, qm9NbreOrdinateur5);
        }
 
        Integer qm9NbreMotoBicyclette6 = entity.getQm9NbreMotoBicyclette6();
        if (qm9NbreMotoBicyclette6 != null) {
            stmt.bindLong(20, qm9NbreMotoBicyclette6);
        }
 
        Integer qm9NbreVoitureMachine7 = entity.getQm9NbreVoitureMachine7();
        if (qm9NbreVoitureMachine7 != null) {
            stmt.bindLong(21, qm9NbreVoitureMachine7);
        }
 
        Integer qm9NbreBateau8 = entity.getQm9NbreBateau8();
        if (qm9NbreBateau8 != null) {
            stmt.bindLong(22, qm9NbreBateau8);
        }
 
        Integer qm9NbrePanneauGeneratrice9 = entity.getQm9NbrePanneauGeneratrice9();
        if (qm9NbrePanneauGeneratrice9 != null) {
            stmt.bindLong(23, qm9NbrePanneauGeneratrice9);
        }
 
        Integer qm9NbreMilletChevalBourique10 = entity.getQm9NbreMilletChevalBourique10();
        if (qm9NbreMilletChevalBourique10 != null) {
            stmt.bindLong(24, qm9NbreMilletChevalBourique10);
        }
 
        Integer qm9NbreBoeufVache11 = entity.getQm9NbreBoeufVache11();
        if (qm9NbreBoeufVache11 != null) {
            stmt.bindLong(25, qm9NbreBoeufVache11);
        }
 
        Integer qm9NbreCochonCabrit12 = entity.getQm9NbreCochonCabrit12();
        if (qm9NbreCochonCabrit12 != null) {
            stmt.bindLong(26, qm9NbreCochonCabrit12);
        }
 
        Integer qm9NbreBeteVolaille13 = entity.getQm9NbreBeteVolaille13();
        if (qm9NbreBeteVolaille13 != null) {
            stmt.bindLong(27, qm9NbreBeteVolaille13);
        }
 
        Short qm10AvoirPersDomestique = entity.getQm10AvoirPersDomestique();
        if (qm10AvoirPersDomestique != null) {
            stmt.bindLong(28, qm10AvoirPersDomestique);
        }
 
        Short qm10TotalDomestiqueFille = entity.getQm10TotalDomestiqueFille();
        if (qm10TotalDomestiqueFille != null) {
            stmt.bindLong(29, qm10TotalDomestiqueFille);
        }
 
        Short qm10TotalDomestiqueGarcon = entity.getQm10TotalDomestiqueGarcon();
        if (qm10TotalDomestiqueGarcon != null) {
            stmt.bindLong(30, qm10TotalDomestiqueGarcon);
        }
 
        Integer qm11TotalIndividuVivant = entity.getQm11TotalIndividuVivant();
        if (qm11TotalIndividuVivant != null) {
            stmt.bindLong(31, qm11TotalIndividuVivant);
        }
 
        Short qn1Emigration = entity.getQn1Emigration();
        if (qn1Emigration != null) {
            stmt.bindLong(32, qn1Emigration);
        }
 
        Short qn1NbreEmigre = entity.getQn1NbreEmigre();
        if (qn1NbreEmigre != null) {
            stmt.bindLong(33, qn1NbreEmigre);
        }
 
        Short qd1Deces = entity.getQd1Deces();
        if (qd1Deces != null) {
            stmt.bindLong(34, qd1Deces);
        }
 
        Short qd1NbreDecede = entity.getQd1NbreDecede();
        if (qd1NbreDecede != null) {
            stmt.bindLong(35, qd1NbreDecede);
        }
 
        Short statut = entity.getStatut();
        if (statut != null) {
            stmt.bindLong(36, statut);
        }
 
        Boolean isValidated = entity.getIsValidated();
        if (isValidated != null) {
            stmt.bindLong(37, isValidated ? 1L: 0L);
        }
 
        String dateDebutCollecte = entity.getDateDebutCollecte();
        if (dateDebutCollecte != null) {
            stmt.bindString(38, dateDebutCollecte);
        }
 
        String dateFinCollecte = entity.getDateFinCollecte();
        if (dateFinCollecte != null) {
            stmt.bindString(39, dateFinCollecte);
        }
 
        Integer dureeSaisie = entity.getDureeSaisie();
        if (dureeSaisie != null) {
            stmt.bindLong(40, dureeSaisie);
        }
 
        Boolean isFieldAllFilled = entity.getIsFieldAllFilled();
        if (isFieldAllFilled != null) {
            stmt.bindLong(41, isFieldAllFilled ? 1L: 0L);
        }
 
        Boolean isContreEnqueteMade = entity.getIsContreEnqueteMade();
        if (isContreEnqueteMade != null) {
            stmt.bindLong(42, isContreEnqueteMade ? 1L: 0L);
        }
 
        String codeAgentRecenceur = entity.getCodeAgentRecenceur();
        if (codeAgentRecenceur != null) {
            stmt.bindString(43, codeAgentRecenceur);
        }
 
        Boolean isVerified = entity.getIsVerified();
        if (isVerified != null) {
            stmt.bindLong(44, isVerified ? 1L: 0L);
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
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5), // qm2ModeJouissance
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6), // qm3ModeObtentionLoge
            cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7), // qm4_1ModeAprovEauABoire
            cursor.isNull(offset + 8) ? null : cursor.getShort(offset + 8), // qm4_2ModeAprovEauAUsageCourant
            cursor.isNull(offset + 9) ? null : cursor.getShort(offset + 9), // qm5SrcEnergieCuisson1
            cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10), // qm5SrcEnergieCuisson2
            cursor.isNull(offset + 11) ? null : cursor.getShort(offset + 11), // qm6TypeEclairage
            cursor.isNull(offset + 12) ? null : cursor.getShort(offset + 12), // qm7ModeEvacDechet
            cursor.isNull(offset + 13) ? null : cursor.getShort(offset + 13), // qm8EndroitBesoinPhysiologique
            cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14), // qm9NbreRadio1
            cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15), // qm9NbreTelevision2
            cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16), // qm9NbreRefrigerateur3
            cursor.isNull(offset + 17) ? null : cursor.getInt(offset + 17), // qm9NbreFouElectrique4
            cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18), // qm9NbreOrdinateur5
            cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19), // qm9NbreMotoBicyclette6
            cursor.isNull(offset + 20) ? null : cursor.getInt(offset + 20), // qm9NbreVoitureMachine7
            cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21), // qm9NbreBateau8
            cursor.isNull(offset + 22) ? null : cursor.getInt(offset + 22), // qm9NbrePanneauGeneratrice9
            cursor.isNull(offset + 23) ? null : cursor.getInt(offset + 23), // qm9NbreMilletChevalBourique10
            cursor.isNull(offset + 24) ? null : cursor.getInt(offset + 24), // qm9NbreBoeufVache11
            cursor.isNull(offset + 25) ? null : cursor.getInt(offset + 25), // qm9NbreCochonCabrit12
            cursor.isNull(offset + 26) ? null : cursor.getInt(offset + 26), // qm9NbreBeteVolaille13
            cursor.isNull(offset + 27) ? null : cursor.getShort(offset + 27), // qm10AvoirPersDomestique
            cursor.isNull(offset + 28) ? null : cursor.getShort(offset + 28), // qm10TotalDomestiqueFille
            cursor.isNull(offset + 29) ? null : cursor.getShort(offset + 29), // qm10TotalDomestiqueGarcon
            cursor.isNull(offset + 30) ? null : cursor.getInt(offset + 30), // qm11TotalIndividuVivant
            cursor.isNull(offset + 31) ? null : cursor.getShort(offset + 31), // qn1Emigration
            cursor.isNull(offset + 32) ? null : cursor.getShort(offset + 32), // qn1NbreEmigre
            cursor.isNull(offset + 33) ? null : cursor.getShort(offset + 33), // qd1Deces
            cursor.isNull(offset + 34) ? null : cursor.getShort(offset + 34), // qd1NbreDecede
            cursor.isNull(offset + 35) ? null : cursor.getShort(offset + 35), // statut
            cursor.isNull(offset + 36) ? null : cursor.getShort(offset + 36) != 0, // isValidated
            cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37), // dateDebutCollecte
            cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38), // dateFinCollecte
            cursor.isNull(offset + 39) ? null : cursor.getInt(offset + 39), // dureeSaisie
            cursor.isNull(offset + 40) ? null : cursor.getShort(offset + 40) != 0, // isFieldAllFilled
            cursor.isNull(offset + 41) ? null : cursor.getShort(offset + 41) != 0, // isContreEnqueteMade
            cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42), // codeAgentRecenceur
            cursor.isNull(offset + 43) ? null : cursor.getShort(offset + 43) != 0 // isVerified
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
        entity.setQm2ModeJouissance(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5));
        entity.setQm3ModeObtentionLoge(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6));
        entity.setQm4_1ModeAprovEauABoire(cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7));
        entity.setQm4_2ModeAprovEauAUsageCourant(cursor.isNull(offset + 8) ? null : cursor.getShort(offset + 8));
        entity.setQm5SrcEnergieCuisson1(cursor.isNull(offset + 9) ? null : cursor.getShort(offset + 9));
        entity.setQm5SrcEnergieCuisson2(cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10));
        entity.setQm6TypeEclairage(cursor.isNull(offset + 11) ? null : cursor.getShort(offset + 11));
        entity.setQm7ModeEvacDechet(cursor.isNull(offset + 12) ? null : cursor.getShort(offset + 12));
        entity.setQm8EndroitBesoinPhysiologique(cursor.isNull(offset + 13) ? null : cursor.getShort(offset + 13));
        entity.setQm9NbreRadio1(cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14));
        entity.setQm9NbreTelevision2(cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15));
        entity.setQm9NbreRefrigerateur3(cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16));
        entity.setQm9NbreFouElectrique4(cursor.isNull(offset + 17) ? null : cursor.getInt(offset + 17));
        entity.setQm9NbreOrdinateur5(cursor.isNull(offset + 18) ? null : cursor.getInt(offset + 18));
        entity.setQm9NbreMotoBicyclette6(cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19));
        entity.setQm9NbreVoitureMachine7(cursor.isNull(offset + 20) ? null : cursor.getInt(offset + 20));
        entity.setQm9NbreBateau8(cursor.isNull(offset + 21) ? null : cursor.getInt(offset + 21));
        entity.setQm9NbrePanneauGeneratrice9(cursor.isNull(offset + 22) ? null : cursor.getInt(offset + 22));
        entity.setQm9NbreMilletChevalBourique10(cursor.isNull(offset + 23) ? null : cursor.getInt(offset + 23));
        entity.setQm9NbreBoeufVache11(cursor.isNull(offset + 24) ? null : cursor.getInt(offset + 24));
        entity.setQm9NbreCochonCabrit12(cursor.isNull(offset + 25) ? null : cursor.getInt(offset + 25));
        entity.setQm9NbreBeteVolaille13(cursor.isNull(offset + 26) ? null : cursor.getInt(offset + 26));
        entity.setQm10AvoirPersDomestique(cursor.isNull(offset + 27) ? null : cursor.getShort(offset + 27));
        entity.setQm10TotalDomestiqueFille(cursor.isNull(offset + 28) ? null : cursor.getShort(offset + 28));
        entity.setQm10TotalDomestiqueGarcon(cursor.isNull(offset + 29) ? null : cursor.getShort(offset + 29));
        entity.setQm11TotalIndividuVivant(cursor.isNull(offset + 30) ? null : cursor.getInt(offset + 30));
        entity.setQn1Emigration(cursor.isNull(offset + 31) ? null : cursor.getShort(offset + 31));
        entity.setQn1NbreEmigre(cursor.isNull(offset + 32) ? null : cursor.getShort(offset + 32));
        entity.setQd1Deces(cursor.isNull(offset + 33) ? null : cursor.getShort(offset + 33));
        entity.setQd1NbreDecede(cursor.isNull(offset + 34) ? null : cursor.getShort(offset + 34));
        entity.setStatut(cursor.isNull(offset + 35) ? null : cursor.getShort(offset + 35));
        entity.setIsValidated(cursor.isNull(offset + 36) ? null : cursor.getShort(offset + 36) != 0);
        entity.setDateDebutCollecte(cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37));
        entity.setDateFinCollecte(cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38));
        entity.setDureeSaisie(cursor.isNull(offset + 39) ? null : cursor.getInt(offset + 39));
        entity.setIsFieldAllFilled(cursor.isNull(offset + 40) ? null : cursor.getShort(offset + 40) != 0);
        entity.setIsContreEnqueteMade(cursor.isNull(offset + 41) ? null : cursor.getShort(offset + 41) != 0);
        entity.setCodeAgentRecenceur(cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42));
        entity.setIsVerified(cursor.isNull(offset + 43) ? null : cursor.getShort(offset + 43) != 0);
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