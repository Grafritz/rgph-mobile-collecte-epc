package ht.ihsi.rgph.mobile.epc.models;

import ht.ihsi.rgph.mobile.epc.backend.entities.LogementDao;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.epc.managers.CURecordMngr;
import ht.ihsi.rgph.mobile.epc.managers.FormDataMngr;
import ht.ihsi.rgph.mobile.epc.managers.QueryRecordMngr;
import ht.ihsi.rgph.mobile.epc.utilities.Tools;

/**
 * Created by ajordany on 3/21/2016.
 */
public class LogementModel extends BaseModel{

    //region ATTRIBUTS
    private Long logeId;
    private Long batimentId;
    private String sdeId;
    private Short qlCategLogement;
    private Short qlin1NumeroOrdre;
    private Short qlc1TypeLogement;
    private Short qlc2bTotalGarcon;
    private Short qlc2bTotalFille;
    private Short qlcTotalIndividus;
    private Short qlin2StatutOccupation;
    private Short qlin3ExistenceLogement;
    private Short qlin4TypeLogement;
    private Short qlin5MateriauSol;
    private Short qlin6NombrePiece;
    private Short qlin7NbreChambreACoucher;
    private Short qlin8NbreIndividuDepense;
    private Short qlin9NbreTotalMenage;
    private Short statut;
    private Boolean isValidated;
    private String dateDebutCollecte;
    private String dateFinCollecte;
    private Integer dureeSaisie;
    private Boolean isFieldAllFilled;
    private Boolean isContreEnqueteMade;
    private Short nbrTentative;
    private String codeAgentRecenceur;
    private Boolean isVerified=false;

    //endregion

    //region VARIABLES SYSTEME
    public String qlc1bTotalGarconEtFille;
    public String qlin6NombrePieceETChambreACoucher;
    private String callFormulaireIndividuLojCol;
    private String callFormulaireMenage;
    private BatimentModel objBatiment;
    public static QueryRecordMngr queryRecordMngr;
    public static FormDataMngr formDataMngr;
    public static CURecordMngr cuRecordMngr;
    //endregion

    //region CONSTRUCTEURS
    public LogementModel() {
        BlankData();
    }

    private void BlankData() {
        this.logeId = Long.valueOf(0);
        this.batimentId = Long.valueOf(0);
        this.qlc2bTotalGarcon = 0;
        this.qlc2bTotalGarcon = 0;
        objBatiment = null;
    }
    //endregion

    // region |-| GETTER SETTER SYSTEME |-|
    public String getQlc1bTotalGarconEtFille() {// "00-00"
        return qlc2bTotalGarcon + "-" + qlc2bTotalFille;
    }

    public void setQlc1bTotalGarconEtFille(String value) {
        try {
            String[] TotalGarconEtFille = value.split("-"); // 00-00
            String TotalGarcon  = TotalGarconEtFille[0]; // Garcon
            String TotalFille = TotalGarconEtFille[1];  // Fille
            qlc2bTotalGarcon = Short.valueOf(TotalGarcon);
            qlc2bTotalFille = Short.valueOf(TotalFille);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getQlin6NombrePieceETChambreACoucher() {// "00-00"
        return qlin6NombrePiece + "-" + qlin7NbreChambreACoucher;
    }

    public void setQlin6NombrePieceETChambreACoucher(String value) {
        try {
            String[] Items = value.split("-"); // 00-00
            qlin6NombrePiece = Short.valueOf(Items[0]);
            qlin7NbreChambreACoucher = Short.valueOf(Items[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//endregion///////////////////////////////////////////////

    //region LogementModel getters and setters
    public Long getLogeId() {
        return logeId;
    }

    public void setLogeId(Long logeId) {
        this.logeId = logeId;
    }

    public Long getBatimentId() {
        return batimentId;
    }

    public void setBatimentId(Long batimentId) {
        this.batimentId = batimentId;
    }

    public String getSdeId() {
        return sdeId;
    }

    public void setSdeId(String sdeId) {
        this.sdeId = sdeId;
    }

    public Short getQlCategLogement() {
        return qlCategLogement;
    }

    public void setQlCategLogement(Short qlCategLogement) {
        this.qlCategLogement = qlCategLogement;
    }

    public Short getQlin1NumeroOrdre() {
        return qlin1NumeroOrdre;
    }

    public void setQlin1NumeroOrdre(Short qlin1NumeroOrdre) {
        this.qlin1NumeroOrdre = qlin1NumeroOrdre;
    }

    public Short getQlc1TypeLogement() {
        return qlc1TypeLogement;
    }

    public void setQlc1TypeLogement(Short qlc1TypeLogement) {
        this.qlc1TypeLogement = qlc1TypeLogement;
    }

    public Short getQlc2bTotalGarcon() {
        return qlc2bTotalGarcon;
    }

    public void setQlc2bTotalGarcon(Short qlc2bTotalGarcon) {
        this.qlc2bTotalGarcon = qlc2bTotalGarcon;
    }

    public Short getQlc2bTotalFille() {
        return qlc2bTotalFille;
    }

    public void setQlc2bTotalFille(Short qlc2bTotalFille) {
        this.qlc2bTotalFille = qlc2bTotalFille;
    }

    public Short getQlcTotalIndividus() {
        return qlcTotalIndividus;
    }

    public void setQlcTotalIndividus(Short qlcTotalIndividus) {
        this.qlcTotalIndividus = qlcTotalIndividus;
    }

    public Short getQlin2StatutOccupation() {
        return qlin2StatutOccupation;
    }

    public void setQlin2StatutOccupation(Short qlin2StatutOccupation) {
        this.qlin2StatutOccupation = qlin2StatutOccupation;
    }

    public Short getQlin3ExistenceLogement() {
        return qlin3ExistenceLogement;
    }

    public void setQlin3ExistenceLogement(Short qlin3ExistenceLogement) {
        this.qlin3ExistenceLogement = qlin3ExistenceLogement;
    }

    public Short getQlin4TypeLogement() {
        return qlin4TypeLogement;
    }

    public void setQlin4TypeLogement(Short qlin4TypeLogement) {
        this.qlin4TypeLogement = qlin4TypeLogement;
    }

    public Short getQlin5MateriauSol() {
        return qlin5MateriauSol;
    }

    public void setQlin5MateriauSol(Short qlin5MateriauSol) {
        this.qlin5MateriauSol = qlin5MateriauSol;
    }

    public Short getQlin6NombrePiece() {
        return qlin6NombrePiece;
    }

    public void setQlin6NombrePiece(Short qlin6NombrePiece) {
        this.qlin6NombrePiece = qlin6NombrePiece;
    }

    public Short getQlin7NbreChambreACoucher() {
        return qlin7NbreChambreACoucher;
    }

    public void setQlin7NbreChambreACoucher(Short qlin7NbreChambreACoucher) {
        this.qlin7NbreChambreACoucher = qlin7NbreChambreACoucher;
    }

    public Short getQlin8NbreIndividuDepense() {
        return qlin8NbreIndividuDepense;
    }

    public void setQlin8NbreIndividuDepense(Short qlin8NbreIndividuDepense) {
        this.qlin8NbreIndividuDepense = qlin8NbreIndividuDepense;
    }

    public Short getQlin9NbreTotalMenage() {
        return qlin9NbreTotalMenage;
    }

    public void setQlin9NbreTotalMenage(Short qlin9NbreTotalMenage) {
        this.qlin9NbreTotalMenage = qlin9NbreTotalMenage;
    }

    public Short getStatut() {
        return statut;
    }

    public void setStatut(Short statut) {
        this.statut = statut;
    }

    public Boolean getIsValidated() {
        return isValidated;
    }

    public void setIsValidated(Boolean isValidated) {
        this.isValidated = isValidated;
    }

    public String getDateDebutCollecte() {
        return dateDebutCollecte;
    }

    public void setDateDebutCollecte(String dateDebutCollecte) {
        this.dateDebutCollecte = dateDebutCollecte;
    }

    public String getDateFinCollecte() {
        return dateFinCollecte;
    }

    public void setDateFinCollecte(String dateFinCollecte) {
        this.dateFinCollecte = dateFinCollecte;
    }

    public Integer getDureeSaisie() {
        return dureeSaisie;
    }

    public void setDureeSaisie(Integer dureeSaisie) {
        this.dureeSaisie = dureeSaisie;
    }

    public Boolean getIsFieldAllFilled() {
        return isFieldAllFilled;
    }

    public void setIsFieldAllFilled(Boolean isFieldAllFilled) {
        this.isFieldAllFilled = isFieldAllFilled;
    }

    public Boolean getIsContreEnqueteMade() {
        return isContreEnqueteMade;
    }

    public void setIsContreEnqueteMade(Boolean isContreEnqueteMade) {
        this.isContreEnqueteMade = isContreEnqueteMade;
    }

    public Short getNbrTentative() {
        return nbrTentative;
    }

    public void setNbrTentative(Short nbrTentative) {
        this.nbrTentative = nbrTentative;
    }

    public String getCodeAgentRecenceur() {
        return codeAgentRecenceur;
    }

    public void setCodeAgentRecenceur(String codeAgentRecenceur) {
        this.codeAgentRecenceur = codeAgentRecenceur;
    }
    //endregion

    //region Model getters and setters

    public String getCallFormulaireIndividuLojCol() {
        return callFormulaireIndividuLojCol;
    }
    public void setCallFormulaireIndividuLojCol(String callFormulaireIndividuLojCol) {
        this.callFormulaireIndividuLojCol = callFormulaireIndividuLojCol;
    }
    public String getCallFormulaireMenage() {
        return callFormulaireMenage;
    }
    public void setCallFormulaireMenage(String callFormulaireMenage) {
        this.callFormulaireMenage = callFormulaireMenage;
    }

    public BatimentModel getObjBatiment() {
        return objBatiment;
    }

    public void setObjBatiment(BatimentModel objBatiment) {
        this.objBatiment = objBatiment;
    }
    //endregion

    //region METHODES Contrainte Saut Champs Valeur
    public static String Check_ContrainteSautChampsValeur( String nomChamps, LogementModel logementModel, Long iDKeys, Object dataBase)  throws TextEmptyException {
        try{
            String QSuivant = "";
            //region LOGEMENT COLLECTIF
            // LC1A
            /* Etablir la relation entre B.8, LC, M10, M11 et CP
                Si B.8.1 = 1 et B.8.2 = 0, après avoir choisi le type de logement collectif
                - si LC < 6, introduire un contrôle de saut permettant de passer automatiquement à la question M10 puis M11 et après,
                permettre d’aller automatiquement au module CP plus particulièrement à la question P6.
                - sinon (LC > 5), on est alors en LC.1.B, permettre d’inscrire d’abord le nombre d’hommes et ensuite le nombre femmes
                puis d’aller au rapport de l’Agent afin de remplir le statut de remplissage du bâtiment.
                 */
            //
            if (nomChamps.equalsIgnoreCase(LogementDao.Properties.Qlc1TypeLogement.columnName)){
                if ( logementModel.getQlc1TypeLogement()!= null && logementModel.getQlc1TypeLogement() < Constant.Sant_detansyon_Prizon_6  ){
                    //throw new TextEmptyException("Si Batiman sa okipe li dwe genyen yon itilizasyon.");
                }
            }
            if ( nomChamps.equalsIgnoreCase( Constant.Qlc1bTotalGarconEtFille ) ){
                if( logementModel.getQlc2bTotalFille() !=null
                        && logementModel.getQlc2bTotalGarcon() !=null
                        && logementModel.getQlc2bTotalFille() == 0
                        && logementModel.getQlc2bTotalGarcon() == 0 ){
                    throw new TextEmptyException("Kantite Fi ak Kantite gason pa dwe zewo.");
                }
            }
            if (nomChamps.equalsIgnoreCase(LogementDao.Properties.QlcTotalIndividus.columnName)){
                long Nbre_Individu_DejaSave=0;
                if ( logementModel.getLogeId() != null && logementModel.getLogeId() > 0 ) {
                    Nbre_Individu_DejaSave = queryRecordMngr.countIndByLog( logementModel.getLogeId() );
                }
                if( logementModel.getQlcTotalIndividus()!=null &&  logementModel.getQlcTotalIndividus() < Nbre_Individu_DejaSave ){
                    throw new TextEmptyException("Ou paka retire nan kantite ke ou te mete a. "
                            + "\n  paske ou gentan anregistre [" + Nbre_Individu_DejaSave + "] moun deja pou lojman kolektif sa.");
                }
                if ( logementModel.getQlcTotalIndividus()!=null &&  logementModel.getQlcTotalIndividus() <= 0  ){
                    QSuivant = Constant.FIN;
                }
            }
            if (nomChamps.equalsIgnoreCase(LogementDao.Properties.Qlin8NbreIndividuDepense.columnName)){
                if ( logementModel.getQlin8NbreIndividuDepense()!=null && logementModel.getQlin8NbreIndividuDepense() == Constant.REPONS_NON_2  ){
                    logementModel.setQlin9NbreTotalMenage((short) 1);
                    //QSuivant = "LIN13.A"; //QlIN13ACallFormulaireIndividu /* Antre enfòmasyon sou menaj yo ki nan lojman endividyèl sa */
                }
            }
            //endregion

            //region LOGEMENT INDIVIDUEL

            // LIN4 - KI TIP LOJMAN SA A YE?
            //if (nomChamps.equalsIgnoreCase(LogementDao.Properties.Qlin4TypeLogement.columnName)) {
            /* Etablir un contrôle entre B.2 et LIN.4
                Si LIN4 = 5 ou LIN4 = 6 alors, B.2 = 9
                Vérifier que si LIN.4 ≤ 4 ou LIN.4 = 7 et que B.2 ≤8 ou B.2 = 10, Si non afficher un message d’erreur permettant à l’Agent Recenseur de vérifier de son côté et de corriger. */
                /*if ( logementModel.getQlin4TypeLogement() == Constant.LOJMAN_CHELTE_05
                     ||        logementModel.getQlin4TypeLogement() == Constant.LOJMAN_TANT_06) {
                    if (logementModel.getObjBatiment().getQb2Type() != Constant.BATIMAN_ABRI_PWOVIZWA_09) {
                        throw new TextEmptyException("Repons ou mete a pa bon, verifye B3 avan ou kontinye. Gade si batiman an se yon abri pwoviswa (tant, sheltè, etc...)."
                                + "\n \n Pou batiman an ou te chwazi : " + Tools.getString_Reponse(""+logementModel.getObjBatiment().getQb2Type(), BatimentDao.Properties.Qb2Type.columnName));
                    }
                }
                if ( logementModel.getQlin4TypeLogement() <= Constant.LOJMAN_PYES_KAY_02 ||
                        logementModel.getQlin4TypeLogement() == Constant.LOJMAN_Lot_7){
                    if (logementModel.getObjBatiment().getQb2Type() <= Constant.BATIMAN_ANGA_08 ){
                    }else if(logementModel.getObjBatiment().getQb2Type() == Constant.BATIMAN_Lot_10) {
                    }else{
                        throw new TextEmptyException("Repons ou mete a pa bon, verifye B3 avan ou kontinye. Gade si batiman an se yon abri pwoviswa (tant, sheltè, etc.)."
                                + "\n \n Pou batiman an ou te chwazi : " + Tools.getString_Reponse(""+logementModel.getObjBatiment().getQb2Type(), BatimentDao.Properties.Qb2Type.columnName));
                    }
                }*/
            //}
            //LIN9	KONBYEN PYÈS LOJMAN SA A GENYEN?  (Watè, koulwa ak galeri pa ladann)
            if (nomChamps.equalsIgnoreCase(LogementDao.Properties.Qlin6NombrePiece.columnName)) {
            // Etablir un contrôle entre LIN.4 et LIN.6, Si LIN.4 = 2 alors en LIN.6 on doit avoir « 01 » pour le nombre de pièces. /
                if ( logementModel.getQlin6NombrePiece()!=null && logementModel.getQlin6NombrePiece() <= 0){
                    throw new TextEmptyException("Paka pa gen pyès nan lojman an. Ekri kantite pyès lojman an genyen.");
                }
                if ( logementModel.getQlin4TypeLogement()!=null && logementModel.getQlin4TypeLogement() == Constant.LOJMAN_PYES_KAY_02
                        && logementModel.getQlin6NombrePiece()!=null && logementModel.getQlin6NombrePiece() > 1 ){
                    throw new TextEmptyException("Paka genyen plis ke yon (1) pyès nan tip lojman sa a."
                            + "\n \n Ou te chwazi : " + Tools.getString_Reponse(""+logementModel.getQlin4TypeLogement(), LogementDao.Properties.Qlin4TypeLogement.columnName));
                }
            }/**/
            //LIN7 - NAN PYÈS SA YO NAN KONBYEN KI SE CHANM POU DOMI SÈLMAN ? ( Kantite chanm pou kouche  (Si pa gen okenn chanm pou moun domi ekri 00) )
            if (nomChamps.equalsIgnoreCase( LogementDao.Properties.Qlin7NbreChambreACoucher.columnName ) ) {
                // Etablir un contrôle entre LIN.6 et LIN.7, LIN.7 ≤ LIN.6
                //Si LIN.7 > LIN.6, afficher un message d’erreur /
                if (  logementModel.getQlin7NbreChambreACoucher()!=null && logementModel.getQlin6NombrePiece()!=null
                        && logementModel.getQlin7NbreChambreACoucher() > logementModel.getQlin6NombrePiece() ){
                    throw new TextEmptyException("Paka gen plis chanm ke pyès nan lojaman an."
                            + "\n \n Ou te mete : " + logementModel.getQlin6NombrePiece() + " Pyès.");
                }
            }

            //LIN9 - KONBYEN MENAJ* ANTOU KAP VIV NAN LOJMAN SA A SI W METE MENAJ PAW LA LADAN N?
            if (nomChamps.equalsIgnoreCase(LogementDao.Properties.Qlin9NbreTotalMenage.columnName)) {
            /* Si LIN.8 = 1, alors LIN9 ≥ 1 Dans ce cas, si LIN.9 = 0, afficher un message d’erreur
            Si LIN9>1, Introduire un contrôle permettant de remplir la section logement (LIN1 à LIN9) une seule fois.  */
                if (  logementModel.getQlin9NbreTotalMenage()!=null && logementModel.getQlin8NbreIndividuDepense()!=null
                        && logementModel.getQlin8NbreIndividuDepense() == Constant.REPONS_WI_1 && logementModel.getQlin9NbreTotalMenage() <= 1 ){
                    throw new TextEmptyException("Ou paka mete "+logementModel.getQlin9NbreTotalMenage()+" Menaj nan ka sa a pou kantite menaj yo ki genyen antou."
                            + "\n\nPaske lèw fe sòm pa ou a ak lòt yo wap jwenn plis ke  " + +logementModel.getQlin9NbreTotalMenage() + " Menaj.");
                }
            }
            //endregion
            return QSuivant;
        } catch (Exception ex){
            throw ex;
        }
    }
    //endregion


}

