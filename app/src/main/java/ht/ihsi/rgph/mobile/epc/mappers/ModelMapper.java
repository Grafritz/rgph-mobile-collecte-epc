package ht.ihsi.rgph.mobile.epc.mappers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ht.ihsi.rgph.mobile.epc.backend.entities.BatimentDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.DecesDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.EmigreDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.IndividuDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.LogementDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.MenageDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.PersonnelDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.RapportRARDao;
import ht.ihsi.rgph.mobile.epc.backend.entities.Batiment;
import ht.ihsi.rgph.mobile.epc.backend.entities.CategorieQuestion;
import ht.ihsi.rgph.mobile.epc.backend.entities.Commune;
import ht.ihsi.rgph.mobile.epc.backend.entities.Deces;
import ht.ihsi.rgph.mobile.epc.backend.entities.Departement;
import ht.ihsi.rgph.mobile.epc.backend.entities.Emigre;
import ht.ihsi.rgph.mobile.epc.backend.entities.Individu;
import ht.ihsi.rgph.mobile.epc.backend.entities.Logement;
import ht.ihsi.rgph.mobile.epc.backend.entities.Menage;
import ht.ihsi.rgph.mobile.epc.backend.entities.Module;
import ht.ihsi.rgph.mobile.epc.backend.entities.Pays;
import ht.ihsi.rgph.mobile.epc.backend.entities.Personnel;
import ht.ihsi.rgph.mobile.epc.backend.entities.Question;
import ht.ihsi.rgph.mobile.epc.backend.entities.QuestionModule;
import ht.ihsi.rgph.mobile.epc.backend.entities.QuestionReponse;
import ht.ihsi.rgph.mobile.epc.backend.entities.RapportFinal;
import ht.ihsi.rgph.mobile.epc.backend.entities.RapportRAR;
import ht.ihsi.rgph.mobile.epc.backend.entities.Vqse;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.managers.CURecordMngr;
import ht.ihsi.rgph.mobile.epc.managers.QueryRecordMngr;
import ht.ihsi.rgph.mobile.epc.managers.QueryRecordMngrImpl;
import ht.ihsi.rgph.mobile.epc.models.BatimentModel;
import ht.ihsi.rgph.mobile.epc.models.CategorieQuestionModel;
import ht.ihsi.rgph.mobile.epc.models.CommuneModel;
import ht.ihsi.rgph.mobile.epc.models.DecesModel;
import ht.ihsi.rgph.mobile.epc.models.DepartementModel;
import ht.ihsi.rgph.mobile.epc.models.EmigreModel;
import ht.ihsi.rgph.mobile.epc.models.IndividuModel;
import ht.ihsi.rgph.mobile.epc.models.KeyValueModel;
import ht.ihsi.rgph.mobile.epc.models.LogementModel;
import ht.ihsi.rgph.mobile.epc.models.MenageModel;
import ht.ihsi.rgph.mobile.epc.models.ModuleModel;
import ht.ihsi.rgph.mobile.epc.models.PaysModel;
import ht.ihsi.rgph.mobile.epc.models.PersonnelModel;
import ht.ihsi.rgph.mobile.epc.models.QuestionModuleModel;
import ht.ihsi.rgph.mobile.epc.models.QuestionReponseModel;
import ht.ihsi.rgph.mobile.epc.models.QuestionsModel;
import ht.ihsi.rgph.mobile.epc.models.RapportFinalModel;
import ht.ihsi.rgph.mobile.epc.models.RapportRARModel;
import ht.ihsi.rgph.mobile.epc.models.RowDataListModel;
import ht.ihsi.rgph.mobile.epc.models.VqseModel;
import ht.ihsi.rgph.mobile.epc.utilities.Shared_Preferences;
import ht.ihsi.rgph.mobile.epc.utilities.ToastUtility;
import ht.ihsi.rgph.mobile.epc.utilities.Tools;

/**
 * Created by jadme on 3/23/2016.
 */
public class ModelMapper {
    //region Map To BaseModel

    public static KeyValueModel MapTo(String key, String value) {
        KeyValueModel kvm = new KeyValueModel(key, value);
        return kvm;
    }

    public static List<RowDataListModel> MapToRowsIndividuOnly(List<Individu> inds){
        List<RowDataListModel> result=new ArrayList<>() ;
        if(inds!=null && inds.size()>0) {
            for (Individu ind: inds) {
                RowDataListModel r = new RowDataListModel();
                // BatimentModel bat =
                r.setTitle("#" + ind.getQ1NoOrdre() +" : " + ind.getQp2APrenom()+"  " + ind.getQp2BNom() );
                r.setDesc("<b>Relasyon:</b>" + Tools.getString_Reponse(""+ind.getQp3LienDeParente(), IndividuDao.Properties.Qp3LienDeParente.columnName)
                        + " | <b>Sèks:</b>" + Tools.getString_Reponse(""+ind.getQp4Sexe(), IndividuDao.Properties.Qp4Sexe.columnName)
                        + " | <b>Fòm ranpli nèt:</b>" + Tools.getString_Reponse(""+ind.getIsFieldAllFilled(), IndividuDao.Properties.IsFieldAllFilled.columnName));
                r.setIsComplete(false);
                r.setIsModuleMenu(false);
                r.setModel(MapToIndividuModel(ind));
                result.add(r);
            }
        }else{
            /*RowDataListModel r = new RowDataListModel();
            r.setTitle("AUCUN ELELEMENT TROUVE!!!");
            r.setDesc("");
            result.add(r);*/
            result = null;
        }
        return result;
    }

    public static List<RowDataListModel> MapToRowsIndividu(List<Individu> inds){
        List<RowDataListModel> result=new ArrayList<>() ;
        if(inds!=null && inds.size()>0) {
            for (Individu ind: inds) {
                RowDataListModel r = new RowDataListModel();
                // BatimentModel bat =
                r.setTitle("#" + ind.getQ1NoOrdre() +" : " + ind.getQp2APrenom()+"  " + ind.getQp2BNom() );
                r.setDesc("<b>Relasyon:</b>" + Tools.getString_Reponse(""+ind.getQp3LienDeParente(), IndividuDao.Properties.Qp3LienDeParente.columnName)
                        + " | <b>Sèks:</b>" + Tools.getString_Reponse(""+ind.getQp4Sexe(), IndividuDao.Properties.Qp4Sexe.columnName)
                        + " | <b>Fòm ranpli nèt:</b>" + Tools.getString_Reponse(""+ind.getIsFieldAllFilled(), IndividuDao.Properties.IsFieldAllFilled.columnName));
                        //+ "<br />Sde:" + ind.getSdeId() + "/Batiman-" + ind.getBatimentId() + "/Lojman Endividyèl-" + ind.getLogeId() + "/Menaj-" + ind.getMenageId());
                r.setIsComplete(ind.getIsFieldAllFilled());
                r.setIsModuleMenu(false);
                r.setModel(MapToIndividuModel(ind));
                result.add(r);
            }
        }else{
            /*RowDataListModel r = new RowDataListModel();
            r.setTitle("AUCUN ELELEMENT TROUVE!!!");
            r.setDesc("");
            result.add(r);*/
            result = null;
        }
        return result;
    }

    public static List<RowDataListModel> MapToRowsDeces(List<Deces> deces){

        List<RowDataListModel> result=new ArrayList<>() ;
        if(deces!=null && deces.size()>0) {
            for (Deces d: deces) {
                RowDataListModel r = new RowDataListModel();
                r.setTitle("#" + d.getQd2NoOrdre() +" Moun Mouri ");
                r.setDesc(""
                        + " <b>Sèks:</b>" + Tools.getString_Reponse(""+d.getQd2aSexe(), DecesDao.Properties.Qd2aSexe.columnName)
                        + " | <b>laj li mouri:</b> " +  d.getQd2bAgeDecede()
                        + " <br /> <b>Sikonstans:</b> " +  Tools.getString_Reponse(""+ d.getQd2c1CirconstanceDeces(), DecesDao.Properties.Qd2c1CirconstanceDeces.columnName)
                        + " <br /> <b>Kauz:</b> " +  Tools.getString_Reponse(""+ d.getQd2c2CauseDeces(), DecesDao.Properties.Qd2c2CauseDeces.columnName)
                        + " | <b>Fòm ranpli nèt:</b>" + Tools.getString_Reponse(""+ d.getIsFieldAllFilled(), DecesDao.Properties.IsFieldAllFilled.columnName));
                r.setIsComplete(d.getIsFieldAllFilled());
                r.setIsModuleMenu(false);
                r.setModel(MapToDecesModel(d));
                result.add(r);
            }
        }else{
           /* RowDataListModel r = new RowDataListModel();
            r.setTitle("AUCUN ELELEMENT TROUVE!!!");
            r.setDesc("");
            result.add(r);*/
            result = null;
        }
        return result;
    }

    public static List<RowDataListModel> MapToRowsEmigre(List<Emigre> emigres){

        List<RowDataListModel> result=new ArrayList<>() ;
        if(emigres!=null && emigres.size()>0) {
            for (Emigre e: emigres) {
                RowDataListModel r = new RowDataListModel();
                r.setTitle("#" + e.getQn1numeroOrdre() +" : " + e.getQn2aNomComplet() );
                //r.setTitle("Emigre-" + e.getQn1numeroOrdre());
                r.setDesc(""
                        + " <b>Sèks:</b>" + Tools.getString_Reponse(""+e.getQn2bSexe(), EmigreDao.Properties.Qn2bSexe.columnName)
                        + " | <b>laj li pati:</b> " +  e.getQn2cAgeAuMomentDepart()
                        + " <br /> Rezidans " +  Tools.getString_Reponse(""+ e.getQn2eDernierPaysResidence(), EmigreDao.Properties.Qn2eDernierPaysResidence.columnName)
                        + " | <b>Fòm ranpli nèt:</b>" + Tools.getString_Reponse(""+e.getIsFieldAllFilled(), EmigreDao.Properties.IsFieldAllFilled.columnName));
                r.setIsComplete(e.getIsFieldAllFilled());
                r.setIsModuleMenu(false);
                r.setModel(MapToEmigreModel(e));
                result.add(r);
            }
        }else{
           /* RowDataListModel r = new RowDataListModel();
            r.setTitle("AUCUN ELELEMENT TROUVE!!!");
            r.setDesc("");
            result.add(r);*/
            result = null;
        }
        return result;
    }

    public static List<RowDataListModel> MapToRowsMenage(Context context, List<Menage> menages){
        int statutFormulaire = Constant.STATUT_MODULE_KI_FINI_1;
        boolean isFillAllField = true;
        List<RowDataListModel> result=new ArrayList<>() ;
        if(menages!=null && menages.size()>0) {
            long NbreEmigre = 0;
            long NbreEmigreSave = 0;
            long NbreEmigreF = 0;
            long NbreEmigreG = 0;

            long NbreDeces = 0;
            long NbreDecesSave = 0;
            long NbreDecesF = 0;
            long NbreDecesG = 0;
            long TotalIndividuVivant = 0;
            QueryRecordMngr queryRecordMngr = new QueryRecordMngrImpl(context);
            for (Menage m : menages) {

                if( m.getQn1NbreEmigre() != null && m.getQn1NbreEmigre() > 0 ){
                    NbreEmigre = m.getQn1NbreEmigre();
                }
                if( NbreEmigre > 0 ){
                    NbreEmigreSave = queryRecordMngr.countEmigrer_AllFilled_ByMenage_ByStatus(m.getMenageId(), statutFormulaire, isFillAllField);
                }
                if( m.getQd1NbreDecede() != null &&  m.getQd1NbreDecede() > 0 ) {
                    NbreDeces = m.getQd1NbreDecede();
                }
                if( NbreDeces > 0 ){
                    NbreDecesSave = queryRecordMngr.countDeces_AllFilled_ByMenage_ByStatus(m.getMenageId(), statutFormulaire, isFillAllField);
                }

                if( m.getQm11TotalIndividuVivant() != null &&  m.getQm11TotalIndividuVivant() > 0 ){
                    TotalIndividuVivant = queryRecordMngr.countIndividus_AllFilled_ByMenage_ByStatus(m.getMenageId(), statutFormulaire, isFillAllField);
                }

                RowDataListModel r = new RowDataListModel();
                r.setTitle("#"+ m.getQm1NoOrdre()+" Menaj");
                r.setDesc("<b> Emigre:</b> "+ NbreEmigreSave +"/" + NbreEmigre
                        +"<b> | Moun mouri:</b> "+ NbreDecesSave +"/" + NbreDeces
                        + " | <b> Moun Vivan:</b>"+ TotalIndividuVivant +"/"+m.getQm11TotalIndividuVivant()
                        + " | <b>Fòm ranpli nèt:</b>" + Tools.getString_Reponse(""+m.getIsFieldAllFilled(), MenageDao.Properties.IsFieldAllFilled.columnName));
                r.setIsComplete(m.getIsFieldAllFilled());
                r.setIsModuleMenu(true);
                r.setModel(MapToMenageModel(m));
                result.add(r);
            }
        }else{
            result = null;
        }
        return result;
    }

    public static List<CommuneModel> MapToCommune(List<Commune> coms) {
        List<CommuneModel> result = new ArrayList<>() ;
        if(coms!=null && coms.size()>0) {
            for (Commune com : coms) {
                CommuneModel r = new CommuneModel();
                r.setComId(com.getComId());
                r.setComNom(com.getComNom());
                r.setDeptId(com.getDeptId());
                result.add(r);
            }
        }
        return result;
    }

    public static List<VqseModel> MapToVqse(List<Vqse> vqses) {
        List<VqseModel> result = new ArrayList<>() ;
        if(vqses!=null && vqses.size()>0) {
            for (Vqse com : vqses) {
                VqseModel r = new VqseModel();
                r.setVqseId(com.getVqseId());
                r.setVqseNom(com.getVqseNom());
                r.setComId(com.getComId());
                result.add(r);
            }
        }
        return result;
    }


    public void SetStatutLogement(QueryRecordMngr queryRecordMngr, CURecordMngr cuRecordMngr,LogementModel logementModel, int typeLogement) {
        try{
            boolean IsStatutLogementFini = true;
            int statutFormulaire = Constant.STATUT_MODULE_KI_FINI_1;
            boolean isFillAllField = true;
            //****************************************************//
            //********** CALCUL POUR LOGEMENT COLLECTIF **********//
            //****************************************************//
            if(typeLogement == Constant.LOJ_KOLEKTIF){
                //*******  CALCUL POUR INDIVIDUS DANS LE LOGEMENT COLLECTIF  ********//
                int Nbre_TotalIndividu = 0;//this.getLogementModel().getQlcTotalIndividus();
                if( logementModel.getQlc1TypeLogement() != null && logementModel.getQlc1TypeLogement() <= 5){
                    if ( logementModel.getQlcTotalIndividus() != null && logementModel.getQlcTotalIndividus() > 0) {
                        Nbre_TotalIndividu = logementModel.getQlcTotalIndividus();
                    }
                }
                if (Nbre_TotalIndividu > 0) {
                    // On Calcul le nombre d'individu fini et bien remplit
                    long NbreTotalIndividu_FiniEtBienRemplit = queryRecordMngr.countIndividus_AllFilled_ByLogement_ByStatus( logementModel.getLogeId(), statutFormulaire, isFillAllField);
                    if ( Nbre_TotalIndividu != NbreTotalIndividu_FiniEtBienRemplit ){
                        IsStatutLogementFini=false;
                    }
                }
            }else if(typeLogement == Constant.LOJ_ENDIVIDYEL){
                //********* CALCUL POUR LOGEMENT INDIVIDUEL *********//
                //*******  CALCUL POUR MENAGE DANS LE LOGEMENT  ********//
                int nbre_TotalMenage = 0;
                if( logementModel.getQlin9NbreTotalMenage() != null ){
                    nbre_TotalMenage = logementModel.getQlin9NbreTotalMenage();
                }
                if ( logementModel.getQlin8NbreIndividuDepense() != null &&
                        logementModel.getQlin8NbreIndividuDepense() == Constant.REPONS_NON_2 ){
                    nbre_TotalMenage = 1;
                }
                // On verifie s'il existe de menage dans le logement Individuel
                if( nbre_TotalMenage > 0 ) {
                    long nbreTotalMenage_FiniEtBienRemplit = queryRecordMngr.countMenage_AllFilled_ByLogement_ByStatus(logementModel.getLogeId(), statutFormulaire, isFillAllField);
                    if ( nbre_TotalMenage != nbreTotalMenage_FiniEtBienRemplit ){
                        IsStatutLogementFini=false;
                    }
                }
            }

            // SI TOUT CE PASSE TRES BIEN ON PASSE LE STATUT DU LOGEMENT A FINI\
            if ( IsStatutLogementFini ) {
                LogementModel logModel = logementModel;
                // SI tous les champs du logement sont bien remplit
                if ( logModel.getLogeId() != null &&  logModel.getLogeId() > 0 &&
                        logModel.getIsFieldAllFilled() != null && logModel.getIsFieldAllFilled() ) {
                    logModel.setStatut((short) statutFormulaire);
                    logModel = cuRecordMngr.SaveLogement(logModel.getLogeId(), logementModel, Constant.ACTION_MOFIDIER,"");
                }
            }
        }catch (Exception ex) {
            Log.e(ToastUtility.TAG, "Exception: SetStatutLogement(): " + ex.getMessage() + " / toString: " + ex.toString());
            ex.printStackTrace();
        }
    }

    public static List<RowDataListModel> MapToRowsLogement(Context context, List<Logement> loges){
        int statutFormulaire = Constant.STATUT_MODULE_KI_FINI_1;
        boolean isFillAllField = true;
        List<RowDataListModel> result=new ArrayList<>() ;
        if(loges!=null && loges.size()>0) {
            boolean IsStatutLogementFini = true;
            long NbreTotalIndividu_FiniEtBienRemplit = 0;
            long NbreTotalMenage = 0;
            QueryRecordMngr queryRecordMngr = new QueryRecordMngrImpl(context);

            for (Logement log : loges) {
                RowDataListModel r = new RowDataListModel();
                r.setId(log.getLogeId());
                if(log.getQlCategLogement() == Constant.LOJ_KOLEKTIF){

                    r.setTitle("#"+ log.getQlin1NumeroOrdre()+" Lojman Kolektif" );
                    String garconEtFille = "";
                    if(log.getQlc1TypeLogement()!= null && log.getQlc1TypeLogement() > 6){
                        garconEtFille = "<br /><b>Kte Gason:</b>" + log.getQlc2bTotalGarcon() + " | <b>Kte Fi:</b>" + log.getQlc2bTotalGarcon();
                    }else{
                        if( log.getQlcTotalIndividus() != null && log.getQlcTotalIndividus() > 0 ){
                            NbreTotalIndividu_FiniEtBienRemplit = queryRecordMngr.countIndividus_AllFilled_ByLogement_ByStatus(log.getLogeId(), statutFormulaire, isFillAllField);
                            if ( log.getQlcTotalIndividus() != NbreTotalIndividu_FiniEtBienRemplit ){
                                IsStatutLogementFini=false;
                            }

                        }
                        garconEtFille = "<br /><b>Kte Moun:</b> "+ NbreTotalIndividu_FiniEtBienRemplit +"/" + log.getQlcTotalIndividus();
                    }
                    r.setDesc("<b>Tip Lojman:</b>" + Tools.getString_Reponse("" + log.getQlc1TypeLogement(), LogementDao.Properties.Qlc1TypeLogement.columnName)
                            + garconEtFille
                            + " | <b>Fòm ranpli nèt:</b>" + Tools.getString_Reponse(""+log.getIsFieldAllFilled(), LogementDao.Properties.IsFieldAllFilled.columnName));
                }else if(log.getQlCategLogement() == Constant.LOJ_ENDIVIDYEL){
                    r.setTitle("#"+ log.getQlin1NumeroOrdre()+" Lojman Endividyèl" );
                    String kteMenaj = "", nbrTentative = "";
                    NbreTotalMenage = queryRecordMngr.countMenage_AllFilled_ByLogement_ByStatus(log.getLogeId(), statutFormulaire, isFillAllField);
                    if( log.getQlin2StatutOccupation() != null && log.getQlin2StatutOccupation() == Constant.LOJMAN_OKIPE_TOUTAN_MEN_MOUN_YO_PA_LA_2   ){
                       if( log.getNbrTentative() != null ) {
                           nbrTentative = " <br /> <b>Nbr Tantativ:</b> " + (log.getNbrTentative()-1);
                       }
                    }
                    if( log.getQlin8NbreIndividuDepense() != null && log.getQlin8NbreIndividuDepense()== Constant.REPONS_WI_1){
                        kteMenaj = "<br /><b>Kte Menaj:</b> "+ NbreTotalMenage +"/"  + log.getQlin9NbreTotalMenage();
                    }else{
                        if( log.getQlin2StatutOccupation() != null && log.getQlin2StatutOccupation() != Constant.LOJMAN_OKIPE_TOUTAN_MEN_MOUN_YO_PA_LA_2   ) {
                            kteMenaj = "<br /><b>Kte Menaj:</b> " + NbreTotalMenage + "/1";
                        }
                    }
                    String typeLogement = "";
                    if( log.getQlin4TypeLogement() != null && log.getQlin4TypeLogement()>0){
                        typeLogement = "<br /><b>Tip Lojman:</b>" + Tools.getString_Reponse("" + log.getQlin4TypeLogement(), LogementDao.Properties.Qlin4TypeLogement.columnName);
                    }
                    r.setDesc("<b>Lojman sa:</b>" + Tools.getString_Reponse("" + log.getQlin2StatutOccupation(), LogementDao.Properties.Qlin2StatutOccupation.columnName)
                            + typeLogement + kteMenaj + nbrTentative
                            + " | <b>Fòm ranpli nèt:</b>" + Tools.getString_Reponse(""+log.getIsFieldAllFilled(), LogementDao.Properties.IsFieldAllFilled.columnName));
                }

                r.setIsComplete(log.getIsFieldAllFilled());
                if( log.getQlin2StatutOccupation() != null && log.getQlin2StatutOccupation() == Constant.LOJMAN_OKIPE_TOUTAN_MEN_MOUN_YO_PA_LA_2
                        && log.getNbrTentative() != null && log.getNbrTentative() < 4 ){
                    r.setIsModuleMenu(false);
                    r.setIsComplete(false);
                }else{
                    r.setIsModuleMenu(log.getIsFieldAllFilled());
                }
                r.setModel(MapToLogementModel(log));
                result.add(r);
            }
        }else{
            result = null;
        }
        return result;
    }

    public static List<RowDataListModel> MapToRows(Context context, List<Batiment> bats){
        int statutFormulaire = Constant.STATUT_MODULE_KI_FINI_1;
        boolean isFillAllField = true;
        List<RowDataListModel> result=new ArrayList<>() ;
        if(bats!=null && bats.size()>0) {
            QueryRecordMngr queryRecordMngr = new QueryRecordMngrImpl(context);
            for (Batiment bat : bats) {
                long LogCol = 0;
                long LogInd = 0;
                if( bat.getQb8NbreLogeCollectif()!=null && bat.getQb8NbreLogeCollectif() > 0 ){
                    LogCol = queryRecordMngr.countLogement_AllFilled_ByBatiment_byTypeLog_ByStatus(bat.getBatimentId(), Constant.LOJ_KOLEKTIF, statutFormulaire, isFillAllField);
                }
                if( bat.getQb8NbreLogeIndividuel()!=null && bat.getQb8NbreLogeIndividuel() > 0 ){
                    LogInd = queryRecordMngr.countLogement_AllFilled_ByBatiment_byTypeLog_ByStatus(bat.getBatimentId(), Constant.LOJ_ENDIVIDYEL, statutFormulaire, isFillAllField);
                }
                RowDataListModel r = new RowDataListModel();
                r.setId(bat.getBatimentId());

                r.setTitle("<b>#"+ bat.getBatimentId() +" Batiman</b> "+ ( bat.getQrgph()!= null ? " | RGPH: " + bat.getQrgph():"")
                        + ""+ ( bat.getQadresse()!= null ? " | <i> " + bat.getQadresse() + "</i>" :"")
                        + ""+ ( bat.getQhabitation()!= null ? " | <b>Bit:</b> " + bat.getQhabitation() :"")
                        + ""+ ( bat.getQlocalite()!= null ? " | <b>Lok:</b> " + bat.getQlocalite() :""));
                r.setDesc( ( bat.getQb1Etat()!= null ? "<b>Eta:</b> " + Tools.getString_Reponse("" + bat.getQb1Etat(), BatimentDao.Properties.Qb1Etat.columnName):"")
                        + ( bat.getQb2Type()!= null ? "<br /><b>Kalite:</b> " + Tools.getString_Reponse("" + bat.getQb2Type(), BatimentDao.Properties.Qb2Type.columnName):"")
                        + ""+ ( bat.getQb8NbreLogeCollectif()!= null ? "<br /><b>Loj kol:</b> " + LogCol + "/" + bat.getQb8NbreLogeCollectif():"")
                        + ""+ ( bat.getQb8NbreLogeIndividuel()!= null ? " | <b>Loj End:</b> " + LogInd + "/" + bat.getQb8NbreLogeIndividuel():"")
                        + ""+ ( bat.getQb6StatutOccupation()!= null ? "<br /><b>Estati:</b> " + Tools.getString_Reponse("" + bat.getQb6StatutOccupation(), BatimentDao.Properties.Qb6StatutOccupation.columnName):"")
                        + " | <b>Fòm ranpli nèt:</b>" + Tools.getString_Reponse(""+bat.getIsFieldAllFilled(), BatimentDao.Properties.IsFieldAllFilled.columnName)
                );
                r.setIsComplete(bat.getIsFieldAllFilled());
                r.setIsModuleMenu(bat.getIsFieldAllFilled());
                r.setModel(MapToBatimentModel(bat));
                result.add(r);
            }
        }else{
            result = null;
        }
        return result;
    }

    public static List<RowDataListModel> MapToRowsForRapport(Context context, List<Batiment> bats){
        int statutFormulaire = Constant.STATUT_MODULE_KI_FINI_1;
        boolean isFillAllField = true;
        List<RowDataListModel> result=new ArrayList<>() ;
        if(bats!=null && bats.size()>0) {
            QueryRecordMngr queryRecordMngr = new QueryRecordMngrImpl(context);
            for (Batiment bat : bats) {
                long nbrRap=0;
                if( bat.getQb8NbreLogeCollectif()!=null && bat.getQb8NbreLogeCollectif() > 0 ){
                    nbrRap = queryRecordMngr.countRapport_ByBatiment(bat.getBatimentId());
                }
                RowDataListModel r = new RowDataListModel();
                r.setId(bat.getBatimentId());

                r.setTitle("<b>Rapò</b>  Batiman "+ bat.getBatimentId()
                        + ""+ ( bat.getQadresse()!= null ? " | <i> " + bat.getQadresse() + "</i>" :"")
                        + ""+ ( bat.getQhabitation()!= null ? " | <b>Bit:</b> " + bat.getQhabitation() :"")
                        + ""+ ( bat.getQlocalite()!= null ? " | <b>Lok:</b> " + bat.getQlocalite() :""));
                r.setDesc( //( bat.getQb1Etat()!= null ? "<b>Eta:</b> " + Tools.getString_Reponse("" + bat.getQb1Etat(), BatimentDao.Properties.Qb1Etat.columnName):"") +
                        // ( bat.getQb2Type()!= null ? "<br /><b>Kalite:</b> " + Tools.getString_Reponse("" + bat.getQb2Type(), BatimentDao.Properties.Qb2Type.columnName):"") +
                        "<b>Kte Rap:</b> " + nbrRap
                );
                r.setIsComplete(true);
                r.setIsModuleMenu(false);
                r.setModel(MapToBatimentModel(bat));
                result.add(r);
            }
        }else{
            result = null;
        }
        return result;
    }

    public static List<RowDataListModel> MapToRowsRapport(Context context, List<RapportRAR> rapList){
        //int statutFormulaire = Constant.STATUT_MODULE_KI_FINI_1;
        //boolean isFillAllField = true;
        List<RowDataListModel> result=new ArrayList<>() ;
        if(rapList!=null && rapList.size()>0) {
            QueryRecordMngr queryRecordMngr = new QueryRecordMngrImpl(context);
            for (RapportRAR rar : rapList) {
                String raison="";
                RowDataListModel r = new RowDataListModel();
                r.setId(rar.getBatimentId());

                r.setTitle("<b>Rapò sou "+ rar.getRapportModuleName() +" </b> ");
                if( rar.getRaisonActionId() == Constant.PRECISEZ_10
                        || rar.getRaisonActionId() == Constant.PRECISEZ_14
                        || rar.getRaisonActionId() == Constant.PRECISEZ_19
                        || rar.getRaisonActionId()== Constant.PRECISEZ_23 ){
                    raison = ( rar.getAutreRaisonAction()!= null || !rar.getAutreRaisonAction().equalsIgnoreCase("") ? " " + rar.getAutreRaisonAction() :"");
                }else{
                    raison ="<b>Rezon:</b> " + Tools.getString_Reponse("" + rar.getRaisonActionId(), RapportRARDao.Properties.RaisonActionId.columnName);
                }

                r.setDesc(  raison + "<br /> li te rive an varyab: <b>" + rar.getCodeQuestionStop() +"</b>");
                r.setIsComplete(false);
                r.setIsModuleMenu(false);
                r.setModel(MapToRapportRAR(rar));
                result.add(r);
            }
        }else{
            result = null;
        }
        return result;
    }

    public static PersonnelModel MapTo(Personnel entity) {
        PersonnelModel m = new PersonnelModel();
        m.setPersId(entity.getPersId());
        m.setSdeId(entity.getSdeId());
        m.setNom(entity.getNom());
        m.setPrenom(entity.getPrenom());
        m.setSexe(entity.getSexe());
        m.setNomUtilisateur(entity.getNomUtilisateur());
        m.setMotDePasse(entity.getMotDePasse());
        m.setEmail(entity.getEmail());
        m.setDeptId(entity.getDeptId());
        m.setComId(entity.getComId());
        m.setVqseId(entity.getVqseId());
        m.setZone(entity.getZone());
        m.setCodeDistrict(entity.getCodeDistrict());
        m.setEstActif(entity.getEstActif());
        m.setProfileId(entity.getProfileId());
        return m;
    }

    public static Personnel MapTo(PersonnelModel entity) {
        Personnel m = new Personnel();
        //m.setPersId(entity.getPersId());
        m.setSdeId(entity.getSdeId());
        m.setNom(entity.getNom());
        m.setPrenom(entity.getPrenom());
        m.setSexe(entity.getSexe());
        m.setNomUtilisateur(entity.getNomUtilisateur());
        m.setMotDePasse(entity.getMotDePasse());
        m.setEmail(entity.getEmail());
        m.setDeptId(entity.getDeptId());
        m.setComId(entity.getComId());
        m.setVqseId(entity.getVqseId());
        m.setZone(entity.getZone());
        m.setCodeDistrict(entity.getCodeDistrict());
        m.setEstActif(entity.getEstActif());
        m.setProfileId(entity.getProfileId());
        return m;
    }

    public static CategorieQuestionModel MapTo(CategorieQuestion entity) {
        CategorieQuestionModel m = new CategorieQuestionModel();
        m.setCodeCategorie(entity.getCodeCategorie());
        m.setCategorieQuestion(entity.getCategorieQuestion());
        m.setDetailsCategorie(entity.getDetailsCategorie());
        m.setSousDetailsCategorie(entity.getSousDetailsCategorie());
        return m;
    }

    public static QuestionsModel MapTo(Question entity) {
        QuestionsModel m = new QuestionsModel();
        m.setCodeQuestion(entity.getCodeQuestion());
        m.setLibelle(entity.getLibelle());
        m.setDetailsQuestion(entity.getDetailsQuestion());
        m.setIndicationsQuestion(entity.getIndicationsQuestion());
        m.setCodeCategorie(entity.getCodeCategorie());
        m.setNomChamps(entity.getNomChamps());
        m.setTypeQuestion(entity.getTypeQuestion());
        m.setCaratereAccepte(entity.getCaratereAccepte());
        m.setNbreValeurMinimal(entity.getNbreValeurMinimal());
        m.setNbreCaratereMaximal(entity.getNbreCaratereMaximal());
        m.setContrainteSautChampsValeur(entity.getContrainteSautChampsValeur());
        m.setEstSautReponse(entity.getEstSautReponse());
        m.setqPrecedent(entity.getQPrecedent());
        m.setqSuivant(entity.getQSuivant());
        return m;
    }

   /* public static ReponseModel MapTo(Reponse entity) {
        ReponseModel m = new ReponseModel();
        m.setCodeUniqueReponse(entity.getCodeUniqueReponse());
        m.setCodeReponse(entity.getCodeReponse());
        m.setLibelleReponse(entity.getLibelleReponse());
        return m;
    }*/

    public static QuestionReponseModel MapTo(QuestionReponse entity) {
        QuestionReponseModel m = new QuestionReponseModel();
        m.setCodeQuestion(entity.getCodeQuestion());
        m.setCodeUniqueReponse(entity.getCodeUniqueReponse());
        m.setCodeReponse(entity.getCodeReponse());
        m.setLibelleReponse(entity.getLibelleReponse());
        m.setEstEnfant(entity.getEstEnfant());
        m.setAvoirEnfant(entity.getAvoirEnfant());
        m.setCodeParent(entity.getCodeParent());
        m.setQPrecedent(entity.getQPrecedent());
        m.setQSuivant(entity.getQSuivant());
        return m;
    }

    public static ModuleModel MapTo(Module entity) {
        ModuleModel m = new ModuleModel();
        m.setCodeModule(entity.getCodeModule());
        m.setNomModule(entity.getNomModule());
        m.setTypeModule(entity.getTypeModule());
        m.setDescription(entity.getDescription());
        m.setEstActif(entity.getEstActif());
        return m;
    }

    public static QuestionModuleModel MapTo(QuestionModule entity) {
        QuestionModuleModel m = new QuestionModuleModel();
        m.setOrdre(entity.getOrdre());
        m.setCodeModule(entity.getCodeModule());
        m.setCodeQuestion(entity.getCodeQuestion());
        m.setEstDebut(entity.getEstDebut());
        return m;
    }

    public static List<QuestionReponseModel> MapTo(List<QuestionReponse> questionReponse) {
        List<QuestionReponseModel> result = new ArrayList<QuestionReponseModel>();
        for (QuestionReponse qReponseModel : questionReponse) {
            result.add(MapTo(qReponseModel));
        }
        return result;
    }

   /**/
   public static DepartementModel MapTo(Departement entity){
       DepartementModel m = new DepartementModel();
       m.setDeptId(entity.getDeptId());
       m.setDeptNom(entity.getDeptNom());
       return m;
    }

    public static CommuneModel MapTo(Commune entity) {
        CommuneModel m = new CommuneModel();
        m.setComId(entity.getComId());
        m.setComNom(entity.getComNom());
        m.setDeptId(entity.getDeptId());
        return m;
    }

     public static VqseModel MapTo(Vqse entity) {
         VqseModel m = new VqseModel();
         m.setVqseId(entity.getVqseId());
         m.setVqseNom(entity.getVqseNom());
         m.setComId(entity.getComId());
         return m;
     }

    /*public static DomainEtudeModel MapTo(DomainEtude entitiy){
        m.setcode(entity.getcode());
        m.setnomDomaine(entity.getnomDomaine());
    }

    public static PaysModel MapTo(Pays entitiy){
        m.setcodePays(entity.getcodePays());
        m.setnomPays(entity.getnomPays());
    }*/

    //endregion

    //region Map List<Entity>
    public static List<BatimentModel> MapToBatimentModel(List<Batiment> bats) {
        List<BatimentModel> result = new ArrayList<BatimentModel>();
        for (Batiment bat : bats) {
            result.add(MapToBatimentModel(bat));
        }
        return result;
    }

    public static List<LogementModel> MapToLogementModel(List<Logement> loges) {
        List<LogementModel> result = new ArrayList<LogementModel>();
        for (Logement log : loges) {
            result.add(MapToLogementModel(log));
        }
        return result;
    }

    public static List<MenageModel> MapToMenageModel(List<Menage> menages) {
        List<MenageModel> result = new ArrayList<MenageModel>();
        for (Menage m : menages) {
            result.add(ModelMapper.MapToMenageModel(m));
        }
        return result;
    }

    public static List<EmigreModel> MapToEmigreModel(List<Emigre> emigres) {
        List<EmigreModel> result = new ArrayList<EmigreModel>();
        for (Emigre emigre : emigres) {
            result.add(MapToEmigreModel(emigre));
        }
        return result;
    }

    public static List<DecesModel> MapToDecesModel(List<Deces> deces) {
        List<DecesModel> result = new ArrayList<DecesModel>();
        for (Deces d : deces) {
            result.add(MapToDecesModel(d));
        }
        return result;
    }

    public static List<IndividuModel> MapToIndividuModel(List<Individu> inds) {
        List<IndividuModel> result = new ArrayList<IndividuModel>();
        for (Individu ind : inds) {
            result.add(MapToIndividuModel(ind));
        }
        return result;
    }

    public static List<PaysModel> MapToPays(List<Pays> payses) {
        List<PaysModel> result = new ArrayList<>() ;
        if(payses!=null && payses.size()>0) {
            for (Pays pays : payses) {
                PaysModel r = new PaysModel();
                r.setCodePays(pays.getCodePays());
                r.setNomPays(pays.getNomPays());
                result.add(r);
            }
        }
        return result;
    }

    public static List<RowDataListModel> MapToRows(Shared_Preferences SPref, List<Personnel> personnelList) {
        List<RowDataListModel> result=new ArrayList<>() ;
        if(personnelList != null && personnelList.size() > 0) {
            for (Personnel personnel : personnelList) {
                RowDataListModel r = new RowDataListModel();
                r.setId(personnel.getPersId());
                r.setTitle("" + personnel.getPrenom() + " " + personnel.getNom().toUpperCase());
                String desc = " <b>SDE :</b> " + personnel.getSdeId()
                        + "<br /><b>Kont:</b> " + personnel.getNomUtilisateur()
                        + " | <b>Pòs:</b> " + Tools.getString_Reponse("" + personnel.getProfileId(), PersonnelDao.Properties.ProfileId.columnName)
                        + " | <b>Estati :</b> " + Tools.getString_Reponse((personnel.getEstActif() ? "1" : "0"), PersonnelDao.Properties.EstActif.columnName);
                r.setDesc(desc);

                r.setIsComplete(true);
                r.setIsModuleMenu(false);
                r.setModel(ModelMapper.MapTo(personnel));

                if (SPref != null) {
                    if (SPref.getProfileId() == Constant.PRIVILEGE_DEVELOPPEUR) {
                        result.add(r);
                    } else {
                        if (SPref.getPersId() == personnel.getPersId()) {
                            result.add(r);
                        } else if (personnel.getProfileId() != 201) {
                            result.add(r);
                        }
                    }
                }
            }
        }else{
        }
        return result;
    }
    //endregion

    //region "Map To EntityModel"
    public static BatimentModel MapToBatimentModel(Batiment batiment) {
        BatimentModel bat = new BatimentModel();
        bat.setBatimentId(batiment.getBatimentId());
        bat.setDeptId(batiment.getDeptId());
        bat.setComId(batiment.getComId());
        bat.setVqseId(batiment.getVqseId());
        bat.setSdeId(batiment.getSdeId());
        bat.setZone(batiment.getZone());
        bat.setDisctrictId(batiment.getDisctrictId());
        bat.setQhabitation(batiment.getQhabitation());
        bat.setQlocalite(batiment.getQlocalite());
        bat.setQadresse(batiment.getQadresse());
        bat.setQrec(batiment.getQrec());
        bat.setQrgph(batiment.getQrgph());
        bat.setQb1Etat(batiment.getQb1Etat());
        bat.setQb2Type(batiment.getQb2Type());
        bat.setQb3NombreEtage(batiment.getQb3NombreEtage());
        bat.setQb4MateriauMur(batiment.getQb4MateriauMur());
        bat.setQb5MateriauToit(batiment.getQb5MateriauToit());
        bat.setQb6StatutOccupation(batiment.getQb6StatutOccupation());
        bat.setQb7Utilisation1(batiment.getQb7Utilisation1());
        bat.setQb7Utilisation2(batiment.getQb7Utilisation2());
        bat.setQb8NbreLogeCollectif(batiment.getQb8NbreLogeCollectif());
        bat.setQb8NbreLogeIndividuel(batiment.getQb8NbreLogeIndividuel());
        bat.setStatut(batiment.getStatut());
        bat.setDateEnvoi(batiment.getDateEnvoi());
        bat.setIsValidated(batiment.getIsValidated());
        bat.setIsSynchroToAppSup(batiment.getIsSynchroToAppSup());
        bat.setIsSynchroToCentrale(batiment.getIsSynchroToCentrale());
        bat.setDateDebutCollecte(batiment.getDateDebutCollecte());
        bat.setDateFinCollecte(batiment.getDateFinCollecte());
        bat.setDureeSaisie(batiment.getDureeSaisie());
        bat.setIsFieldAllFilled(batiment.getIsFieldAllFilled());
        bat.setIsContreEnqueteMade(batiment.getIsContreEnqueteMade());
        bat.setLatitude(batiment.getLatitude());
        bat.setLongitude(batiment.getLongitude());
        bat.setCodeAgentRecenceur(batiment.getCodeAgentRecenceur());
        return bat;
    }

    public static LogementModel MapToLogementModel(Logement logement) {
        LogementModel log = new LogementModel();
        log.setLogeId(logement.getLogeId());
        log.setBatimentId(logement.getBatimentId());
        log.setSdeId(logement.getSdeId());
        log.setQlCategLogement(logement.getQlCategLogement());
        log.setQlin1NumeroOrdre(logement.getQlin1NumeroOrdre());
        log.setQlc1TypeLogement(logement.getQlc1TypeLogement());
        log.setQlc2bTotalGarcon(logement.getQlc2bTotalGarcon());
        log.setQlc2bTotalFille(logement.getQlc2bTotalFille());
        log.setQlcTotalIndividus(logement.getQlcTotalIndividus());
        log.setQlin2StatutOccupation(logement.getQlin2StatutOccupation());
        log.setQlin3ExistenceLogement(logement.getQlin3ExistenceLogement());
        log.setQlin4TypeLogement(logement.getQlin4TypeLogement());
        log.setQlin5MateriauSol(logement.getQlin5MateriauSol());
        log.setQlin6NombrePiece(logement.getQlin6NombrePiece());
        log.setQlin7NbreChambreACoucher(logement.getQlin7NbreChambreACoucher());
        log.setQlin8NbreIndividuDepense(logement.getQlin8NbreIndividuDepense());
        log.setQlin9NbreTotalMenage(logement.getQlin9NbreTotalMenage());
        log.setStatut(logement.getStatut());
        log.setIsValidated(logement.getIsValidated());
        log.setDateDebutCollecte(logement.getDateDebutCollecte());
        log.setDateFinCollecte(logement.getDateFinCollecte());
        log.setDureeSaisie(logement.getDureeSaisie());
        log.setIsFieldAllFilled(logement.getIsFieldAllFilled());
        log.setIsContreEnqueteMade(logement.getIsContreEnqueteMade());
        log.setNbrTentative(logement.getNbrTentative());
        log.setCodeAgentRecenceur(logement.getCodeAgentRecenceur());
        return log;
    }

    public static MenageModel MapToMenageModel(Menage menage) {
        MenageModel men = new MenageModel();
        men.setMenageId(menage.getMenageId());
        men.setLogeId(menage.getLogeId());
        men.setBatimentId(menage.getBatimentId());
        men.setSdeId(menage.getSdeId());
        men.setQm1NoOrdre(menage.getQm1NoOrdre());
        men.setQm2ModeJouissance(menage.getQm2ModeJouissance());
        men.setQm3ModeObtentionLoge(menage.getQm3ModeObtentionLoge());
        men.setQm4_1ModeAprovEauABoire(menage.getQm4_1ModeAprovEauABoire());
        men.setQm4_2ModeAprovEauAUsageCourant(menage.getQm4_2ModeAprovEauAUsageCourant());
        men.setQm5SrcEnergieCuisson1(menage.getQm5SrcEnergieCuisson1());
        men.setQm5SrcEnergieCuisson2(menage.getQm5SrcEnergieCuisson2());
        men.setQm6TypeEclairage(menage.getQm6TypeEclairage());
        men.setQm7ModeEvacDechet(menage.getQm7ModeEvacDechet());
        men.setQm8EndroitBesoinPhysiologique(menage.getQm8EndroitBesoinPhysiologique());
        men.setQm9NbreRadio1(menage.getQm9NbreRadio1());
        men.setQm9NbreTelevision2(menage.getQm9NbreTelevision2());
        men.setQm9NbreRefrigerateur3(menage.getQm9NbreRefrigerateur3());
        men.setQm9NbreFouElectrique4(menage.getQm9NbreFouElectrique4());
        men.setQm9NbreOrdinateur5(menage.getQm9NbreOrdinateur5());
        men.setQm9NbreMotoBicyclette6(menage.getQm9NbreMotoBicyclette6());
        men.setQm9NbreVoitureMachine7(menage.getQm9NbreVoitureMachine7());
        men.setQm9NbreBateau8(menage.getQm9NbreBateau8());
        men.setQm9NbrePanneauGeneratrice9(menage.getQm9NbrePanneauGeneratrice9());
        men.setQm9NbreMilletChevalBourique10(menage.getQm9NbreMilletChevalBourique10());
        men.setQm9NbreBoeufVache11(menage.getQm9NbreBoeufVache11());
        men.setQm9NbreCochonCabrit12(menage.getQm9NbreCochonCabrit12());
        men.setQm9NbreBeteVolaille13(menage.getQm9NbreBeteVolaille13());
        men.setQm10AvoirPersDomestique(menage.getQm10AvoirPersDomestique());
        men.setQm10TotalDomestiqueFille(menage.getQm10TotalDomestiqueFille());
        men.setQm10TotalDomestiqueGarcon(menage.getQm10TotalDomestiqueGarcon());
        men.setQm11TotalIndividuVivant(menage.getQm11TotalIndividuVivant());
        men.setQn1Emigration(menage.getQn1Emigration());
        men.setQn1NbreEmigre(menage.getQn1NbreEmigre());
        men.setQd1Deces(menage.getQd1Deces());
        men.setQd1NbreDecede(menage.getQd1NbreDecede());
        men.setStatut(menage.getStatut());
        men.setIsValidated(menage.getIsValidated());
        men.setDateDebutCollecte(menage.getDateDebutCollecte());
        men.setDateFinCollecte(menage.getDateFinCollecte());
        men.setDureeSaisie(menage.getDureeSaisie());
        men.setIsFieldAllFilled(menage.getIsFieldAllFilled());
        men.setIsContreEnqueteMade(menage.getIsContreEnqueteMade());
        men.setCodeAgentRecenceur(menage.getCodeAgentRecenceur());
        return men;
    }

    public static EmigreModel MapToEmigreModel(Emigre emigre) {
        EmigreModel em = new EmigreModel();
        em.setEmigreId(emigre.getEmigreId());
        em.setMenageId(emigre.getMenageId());
        em.setLogeId(emigre.getLogeId());
        em.setBatimentId(emigre.getBatimentId());
        em.setSdeId(emigre.getSdeId());
        em.setQn1numeroOrdre(emigre.getQn1numeroOrdre());
        em.setQn2aNomComplet(emigre.getQn2aNomComplet());
        em.setQn2bSexe(emigre.getQn2bSexe());
        em.setQn2cAgeAuMomentDepart(emigre.getQn2cAgeAuMomentDepart());
        em.setQn2dVivantToujours(emigre.getQn2dVivantToujours());
        em.setQn2eDernierPaysResidence(emigre.getQn2eDernierPaysResidence());
        em.setStatut(emigre.getStatut());
        em.setIsFieldAllFilled(emigre.getIsFieldAllFilled());
        em.setDateDebutCollecte(emigre.getDateDebutCollecte());
        em.setDateFinCollecte(emigre.getDateFinCollecte());
        em.setDureeSaisie(emigre.getDureeSaisie());
        em.setCodeAgentRecenceur(emigre.getCodeAgentRecenceur());
        return em;
    }

    public static DecesModel MapToDecesModel(Deces deces) {
        DecesModel dec = new DecesModel();
        dec.setDecesId(deces.getDecesId());
        dec.setMenageId(deces.getMenageId());
        dec.setLogeId(deces.getLogeId());
        dec.setBatimentId(deces.getBatimentId());
        dec.setSdeId(deces.getSdeId());
        dec.setQd2NoOrdre(deces.getQd2NoOrdre());
        dec.setQd2aSexe(deces.getQd2aSexe());
        dec.setQd2bAgeDecede(deces.getQd2bAgeDecede());
        dec.setQd2c1CirconstanceDeces(deces.getQd2c1CirconstanceDeces());
        dec.setQd2c2CauseDeces(deces.getQd2c2CauseDeces());
        dec.setStatut(deces.getStatut());
        dec.setIsFieldAllFilled(deces.getIsFieldAllFilled());
        dec.setDateDebutCollecte(deces.getDateDebutCollecte());
        dec.setDateFinCollecte(deces.getDateFinCollecte());
        dec.setDureeSaisie(deces.getDureeSaisie());
        dec.setIsContreEnqueteMade(deces.getIsContreEnqueteMade());
        dec.setCodeAgentRecenceur(deces.getCodeAgentRecenceur());
        return dec;
    }

    public static IndividuModel MapToIndividuModel(Individu individu) {
        IndividuModel ind = new IndividuModel();
        //region "KARAKTERISTIK MOUN NAN -|- POU TOUT MANM MENAJ LA -|- KARAKTERISTIK DEMOGRAFIK"
        ind.setIndividuId(individu.getIndividuId());
        ind.setMenageId(individu.getMenageId());
        ind.setLogeId(individu.getLogeId());
        ind.setBatimentId(individu.getBatimentId());
        ind.setSdeId(individu.getSdeId());
        ind.setQ1NoOrdre(individu.getQ1NoOrdre());
        ind.setQp2APrenom(individu.getQp2APrenom());
        ind.setQp2BNom(individu.getQp2BNom());
        ind.setQp3LienDeParente(individu.getQp3LienDeParente());
        ind.setQp3HabiteDansMenage(individu.getQp3HabiteDansMenage());
        ind.setQp4Sexe(individu.getQp4Sexe());
        ind.setQp5DateNaissanceJour(individu.getQp5DateNaissanceJour());
        ind.setQp5DateNaissanceMois(individu.getQp5DateNaissanceMois());
        ind.setQp5DateNaissanceAnnee(individu.getQp5DateNaissanceAnnee());
        ind.setQp5bAge(individu.getQp5bAge());
        ind.setQp6religion(individu.getQp6religion());
        ind.setQp6AutreReligion(individu.getQp6AutreReligion());
        ind.setQp7Nationalite(individu.getQp7Nationalite());
        ind.setQp7PaysNationalite(individu.getQp7PaysNationalite());
        ind.setQp8MereEncoreVivante(individu.getQp8MereEncoreVivante());
        ind.setQp9EstPlusAge(individu.getQp9EstPlusAge());
        ind.setQp10LieuNaissance(individu.getQp10LieuNaissance());
        ind.setQp10CommuneNaissance(individu.getQp10CommuneNaissance());
        ind.setQp10VqseNaissance(individu.getQp10VqseNaissance());
        ind.setQp10PaysNaissance(individu.getQp10PaysNaissance());
        ind.setQp11PeriodeResidence(individu.getQp11PeriodeResidence());
        ind.setQp12DomicileAvantRecensement(individu.getQp12DomicileAvantRecensement());
        ind.setQp12CommuneDomicileAvantRecensement(individu.getQp12CommuneDomicileAvantRecensement());
        ind.setQp12VqseDomicileAvantRecensement(individu.getQp12VqseDomicileAvantRecensement());
        ind.setQp12PaysDomicileAvantRecensement(individu.getQp12PaysDomicileAvantRecensement());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- EDIKASYON -|- POU MOUN KI GEN 3 LANE OSWA PLIS"
        ind.setQe1EstAlphabetise(individu.getQe1EstAlphabetise());
        ind.setQe2FreqentationScolaireOuUniv(individu.getQe2FreqentationScolaireOuUniv());
        ind.setQe3typeEcoleOuUniv(individu.getQe3typeEcoleOuUniv());
        ind.setQe4aNiveauEtude(individu.getQe4aNiveauEtude());
        ind.setQe4bDerniereClasseOUAneEtude(individu.getQe4bDerniereClasseOUAneEtude());
        ind.setQe5DiplomeUniversitaire(individu.getQe5DiplomeUniversitaire());
        ind.setQe6DomaineEtudeUniversitaire(individu.getQe6DomaineEtudeUniversitaire());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON NAN ZAFE WE"
        ind.setQaf1HandicapVoir(individu.getQaf1HandicapVoir());

        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON NAN ZAFE TANDE"
        ind.setQaf2HandicapEntendre(individu.getQaf2HandicapEntendre());

        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON NAN ZAFE MACHE (BOUJE)"
        ind.setQaf3HandicapMarcher(individu.getQaf3HandicapMarcher());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON KOYITIV"
        ind.setQaf4HandicapSouvenir(individu.getQaf4HandicapSouvenir());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- OKIPE TET LI"
        ind.setQaf5HandicapPourSeSoigner(individu.getQaf5HandicapPourSeSoigner());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON KOMINIKATIF"
        ind.setQaf6HandicapCommuniquer(individu.getQaf6HandicapCommuniquer());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- NOUVEL TEKNOLOJI NAN KOMINIKASYON(POU MOUN KI GEN 10 LANE OSWA PLIS) -|- POSESYON TELEFON SELILE"
        ind.setQt1PossessionTelCellulaire(individu.getQt1PossessionTelCellulaire());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- NOUVEL TEKNOLOJI NAN KOMINIKASYON(POU MOUN KI GEN 10 LANE OSWA PLIS) -|- ITILIZASYON  ENTENET AK AKSE "
        ind.setQt2UtilisationInternet(individu.getQt2UtilisationInternet());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- MIGRASYON : RETOUNEN VIN VIV AN AYITI(POU MOUN KI GEN 10 LANE OSWA PLIS) -|- TAN LI FE LI TAP VIV NAN LOT PEYI A"
        ind.setQem1DejaVivreAutrePays(individu.getQem1DejaVivreAutrePays());
        ind.setQem1AutrePays(individu.getQem1AutrePays());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- MIGRASYON : RETOUNEN VIN VIV AN AYITI(POU MOUN KI GEN 10 LANE OSWA PLIS) -|- DAT RETOU A"
        ind.setQem2MoisRetour(individu.getQem2MoisRetour());
        ind.setQem2AnneeRetour(individu.getQem2AnneeRetour());
        //endregion
        //region "KARAKTERISTIK MOUN NAN  -|- Pou moun ki genyen dis (10) lane epi plis -|- ESTATI MATRIMONYAL"
        ind.setQsm1StatutMatrimonial(individu.getQsm1StatutMatrimonial());
        //endregion
        //region "KARAKTERISTIK MOUN NAN  -|- Pou moun ki genyen dis (10) lane epi plis -|- Aktivite Ekonomik"
        ind.setQa1ActEconomiqueDerniereSemaine(individu.getQa1ActEconomiqueDerniereSemaine());
        ind.setQa2ActAvoirDemele1(individu.getQa2ActAvoirDemele1());
        ind.setQa2ActDomestique2(individu.getQa2ActDomestique2());
        ind.setQa2ActCultivateur3(individu.getQa2ActCultivateur3());
        ind.setQa2ActAiderParent4(individu.getQa2ActAiderParent4());
        ind.setQa2ActAutre5(individu.getQa2ActAutre5());
        ind.setQa3StatutEmploie(individu.getQa3StatutEmploie());
        ind.setQa4SecteurInstitutionnel(individu.getQa4SecteurInstitutionnel());
        ind.setQa5TypeBienProduitParEntreprise(individu.getQa5TypeBienProduitParEntreprise());
        ind.setQa5PreciserTypeBienProduitParEntreprise(individu.getQa5PreciserTypeBienProduitParEntreprise());
        ind.setQa6LieuActDerniereSemaine(individu.getQa6LieuActDerniereSemaine());
        ind.setQa7FoncTravail(individu.getQa7FoncTravail());
        ind.setQa8EntreprendreDemarcheTravail(individu.getQa8EntreprendreDemarcheTravail());
        ind.setQa9VouloirTravailler(individu.getQa9VouloirTravailler());
        ind.setQa10DisponibilitePourTravail(individu.getQa10DisponibilitePourTravail());
        ind.setQa11RecevoirTransfertArgent(individu.getQa11RecevoirTransfertArgent());
        //endregion
        //region "FEGONDITE -|- POU FANM KI GEN 13 LANE OSWA PLIS -|- "
        ind.setQf1aNbreEnfantNeVivantM(individu.getQf1aNbreEnfantNeVivantM());
        ind.setQf1bNbreEnfantNeVivantF(individu.getQf1bNbreEnfantNeVivantF());
        ind.setQf2aNbreEnfantVivantM(individu.getQf2aNbreEnfantVivantM());
        ind.setQf2bNbreEnfantVivantF(individu.getQf2bNbreEnfantVivantF());
        ind.setQf3DernierEnfantJour(individu.getQf3DernierEnfantJour());
        ind.setQf3DernierEnfantMois(individu.getQf3DernierEnfantMois());
        ind.setQf3DernierEnfantAnnee(individu.getQf3DernierEnfantAnnee());
        ind.setQf4DENeVivantVit(individu.getQf4DENeVivantVit());
        ind.setStatut(individu.getStatut());
        ind.setIsFieldAllFilled(individu.getIsFieldAllFilled());
        ind.setDateDebutCollecte(individu.getDateDebutCollecte());
        ind.setDateFinCollecte(individu.getDateFinCollecte());
        ind.setDureeSaisie(individu.getDureeSaisie());
        ind.setIsContreEnqueteMade(individu.getIsContreEnqueteMade());
        ind.setCodeAgentRecenceur(individu.getCodeAgentRecenceur());
        //endregion

        return ind;
    }
    //endregion

    //region Map To Entity
    public static Batiment MapToBatiment(BatimentModel batiment) {
        Batiment bat = new Batiment();
        //bat.setBatimentId(batiment.getBatimentId());
        bat.setDeptId(batiment.getDeptId());
        bat.setComId(batiment.getComId());
        bat.setVqseId(batiment.getVqseId());
        bat.setSdeId(batiment.getSdeId());
        bat.setZone(batiment.getZone());
        bat.setDisctrictId(batiment.getDisctrictId());
        bat.setQhabitation(batiment.getQhabitation());
        bat.setQlocalite(batiment.getQlocalite());
        bat.setQadresse(batiment.getQadresse());
        bat.setQrec(batiment.getQrec());
        bat.setQrgph(batiment.getQrgph());
        bat.setQb1Etat(batiment.getQb1Etat());
        bat.setQb2Type(batiment.getQb2Type());
        bat.setQb3NombreEtage(batiment.getQb3NombreEtage());
        bat.setQb4MateriauMur(batiment.getQb4MateriauMur());
        bat.setQb5MateriauToit(batiment.getQb5MateriauToit());
        bat.setQb6StatutOccupation(batiment.getQb6StatutOccupation());
        bat.setQb7Utilisation1(batiment.getQb7Utilisation1());
        bat.setQb7Utilisation2(batiment.getQb7Utilisation2());
        bat.setQb8NbreLogeCollectif(batiment.getQb8NbreLogeCollectif());
        bat.setQb8NbreLogeIndividuel(batiment.getQb8NbreLogeIndividuel());
        bat.setStatut(batiment.getStatut());
        bat.setDateEnvoi(batiment.getDateEnvoi());
        bat.setIsValidated(batiment.getIsValidated());
        bat.setIsSynchroToAppSup(batiment.getIsSynchroToAppSup());
        bat.setIsSynchroToCentrale(batiment.getIsSynchroToCentrale());
        bat.setDateDebutCollecte(batiment.getDateDebutCollecte());
        bat.setDateFinCollecte(batiment.getDateFinCollecte());
        bat.setDureeSaisie(batiment.getDureeSaisie());
        bat.setIsFieldAllFilled(batiment.getIsFieldAllFilled());
        bat.setIsContreEnqueteMade(batiment.getIsContreEnqueteMade());
        bat.setLatitude(batiment.getLatitude());
        bat.setLongitude(batiment.getLongitude());
        bat.setCodeAgentRecenceur(batiment.getCodeAgentRecenceur());
        return bat;
    }

    public static Logement MapToLogement(LogementModel logement) {
        Logement log = new Logement();
        //log.setLogeId(logement.getLogeId());
        log.setBatimentId(logement.getBatimentId());
        log.setSdeId(logement.getSdeId());
        log.setQlCategLogement(logement.getQlCategLogement());
        log.setQlin1NumeroOrdre(logement.getQlin1NumeroOrdre());
        log.setQlc1TypeLogement(logement.getQlc1TypeLogement());
        log.setQlc2bTotalGarcon(logement.getQlc2bTotalGarcon());
        log.setQlc2bTotalFille(logement.getQlc2bTotalFille());
        log.setQlcTotalIndividus(logement.getQlcTotalIndividus());
        log.setQlin2StatutOccupation(logement.getQlin2StatutOccupation());
        log.setQlin3ExistenceLogement(logement.getQlin3ExistenceLogement());
        log.setQlin4TypeLogement(logement.getQlin4TypeLogement());
        log.setQlin5MateriauSol(logement.getQlin5MateriauSol());
        log.setQlin6NombrePiece(logement.getQlin6NombrePiece());
        log.setQlin7NbreChambreACoucher(logement.getQlin7NbreChambreACoucher());
        log.setQlin8NbreIndividuDepense(logement.getQlin8NbreIndividuDepense());
        log.setQlin9NbreTotalMenage(logement.getQlin9NbreTotalMenage());
        log.setStatut(logement.getStatut());
        log.setIsValidated(logement.getIsValidated());
        log.setDateDebutCollecte(logement.getDateDebutCollecte());
        log.setDateFinCollecte(logement.getDateFinCollecte());
        log.setDureeSaisie(logement.getDureeSaisie());
        log.setIsFieldAllFilled(logement.getIsFieldAllFilled());
        log.setIsContreEnqueteMade(logement.getIsContreEnqueteMade());
        log.setNbrTentative(logement.getNbrTentative());
        log.setCodeAgentRecenceur(logement.getCodeAgentRecenceur());
        return log;
    }

    public static Menage MapToMenage(MenageModel menage) {
        Menage men = new Menage();
        //men.setMenageId(menage.getMenageId());
        men.setLogeId(menage.getLogeId());
        men.setBatimentId(menage.getBatimentId());
        men.setSdeId(menage.getSdeId());
        men.setQm1NoOrdre(menage.getQm1NoOrdre());
        men.setQm2ModeJouissance(menage.getQm2ModeJouissance());
        men.setQm3ModeObtentionLoge(menage.getQm3ModeObtentionLoge());
        men.setQm4_1ModeAprovEauABoire(menage.getQm4_1ModeAprovEauABoire());
        men.setQm4_2ModeAprovEauAUsageCourant(menage.getQm4_2ModeAprovEauAUsageCourant());
        men.setQm5SrcEnergieCuisson1(menage.getQm5SrcEnergieCuisson1());
        men.setQm5SrcEnergieCuisson2(menage.getQm5SrcEnergieCuisson2());
        men.setQm6TypeEclairage(menage.getQm6TypeEclairage());
        men.setQm7ModeEvacDechet(menage.getQm7ModeEvacDechet());
        men.setQm8EndroitBesoinPhysiologique(menage.getQm8EndroitBesoinPhysiologique());
        men.setQm9NbreRadio1(menage.getQm9NbreRadio1());
        men.setQm9NbreTelevision2(menage.getQm9NbreTelevision2());
        men.setQm9NbreRefrigerateur3(menage.getQm9NbreRefrigerateur3());
        men.setQm9NbreFouElectrique4(menage.getQm9NbreFouElectrique4());
        men.setQm9NbreOrdinateur5(menage.getQm9NbreOrdinateur5());
        men.setQm9NbreMotoBicyclette6(menage.getQm9NbreMotoBicyclette6());
        men.setQm9NbreVoitureMachine7(menage.getQm9NbreVoitureMachine7());
        men.setQm9NbreBateau8(menage.getQm9NbreBateau8());
        men.setQm9NbrePanneauGeneratrice9(menage.getQm9NbrePanneauGeneratrice9());
        men.setQm9NbreMilletChevalBourique10(menage.getQm9NbreMilletChevalBourique10());
        men.setQm9NbreBoeufVache11(menage.getQm9NbreBoeufVache11());
        men.setQm9NbreCochonCabrit12(menage.getQm9NbreCochonCabrit12());
        men.setQm9NbreBeteVolaille13(menage.getQm9NbreBeteVolaille13());
        men.setQm10AvoirPersDomestique(menage.getQm10AvoirPersDomestique());
        men.setQm10TotalDomestiqueFille(menage.getQm10TotalDomestiqueFille());
        men.setQm10TotalDomestiqueGarcon(menage.getQm10TotalDomestiqueGarcon());
        men.setQm11TotalIndividuVivant(menage.getQm11TotalIndividuVivant());
        men.setQn1Emigration(menage.getQn1Emigration());
        men.setQn1NbreEmigre(menage.getQn1NbreEmigre());
        men.setQd1Deces(menage.getQd1Deces());
        men.setQd1NbreDecede(menage.getQd1NbreDecede());
        men.setStatut(menage.getStatut());
        men.setIsValidated(menage.getIsValidated());
        men.setDateDebutCollecte(menage.getDateDebutCollecte());
        men.setDateFinCollecte(menage.getDateFinCollecte());
        men.setDureeSaisie(menage.getDureeSaisie());
        men.setIsFieldAllFilled(menage.getIsFieldAllFilled());
        men.setIsContreEnqueteMade(menage.getIsContreEnqueteMade());
        men.setCodeAgentRecenceur(menage.getCodeAgentRecenceur());
        return men;
    }

    public static Emigre MapToEmigre(EmigreModel emigre) {
        Emigre em = new Emigre();
        //em.setEmigreId(emigre.getEmigreId());
        em.setMenageId(emigre.getMenageId());
        em.setLogeId(emigre.getLogeId());
        em.setBatimentId(emigre.getBatimentId());
        em.setSdeId(emigre.getSdeId());
        em.setQn1numeroOrdre(emigre.getQn1numeroOrdre());
        em.setQn2aNomComplet(emigre.getQn2aNomComplet());
        em.setQn2bSexe(emigre.getQn2bSexe());
        em.setQn2cAgeAuMomentDepart(emigre.getQn2cAgeAuMomentDepart());
        em.setQn2dVivantToujours(emigre.getQn2dVivantToujours());
        em.setQn2eDernierPaysResidence(emigre.getQn2eDernierPaysResidence());
        em.setStatut(emigre.getStatut());
        em.setIsFieldAllFilled(emigre.getIsFieldAllFilled());
        em.setDateDebutCollecte(emigre.getDateDebutCollecte());
        em.setDateFinCollecte(emigre.getDateFinCollecte());
        em.setDureeSaisie(emigre.getDureeSaisie());
        em.setCodeAgentRecenceur(emigre.getCodeAgentRecenceur());
        return em;
    }

    public static Deces MapToDeces(DecesModel deces) {
        Deces dec = new Deces();
        //dec.setDecesId(deces.getDecesId());
        dec.setMenageId(deces.getMenageId());
        dec.setLogeId(deces.getLogeId());
        dec.setBatimentId(deces.getBatimentId());
        dec.setSdeId(deces.getSdeId());
        dec.setQd2NoOrdre(deces.getQd2NoOrdre());
        dec.setQd2aSexe(deces.getQd2aSexe());
        dec.setQd2bAgeDecede(deces.getQd2bAgeDecede());
        dec.setQd2c1CirconstanceDeces(deces.getQd2c1CirconstanceDeces());
        dec.setQd2c2CauseDeces(deces.getQd2c2CauseDeces());
        dec.setStatut(deces.getStatut());
        dec.setIsFieldAllFilled(deces.getIsFieldAllFilled());
        dec.setDateDebutCollecte(deces.getDateDebutCollecte());
        dec.setDateFinCollecte(deces.getDateFinCollecte());
        dec.setDureeSaisie(deces.getDureeSaisie());
        dec.setIsContreEnqueteMade(deces.getIsContreEnqueteMade());
        dec.setCodeAgentRecenceur(deces.getCodeAgentRecenceur());
        return dec;
    }

    public static Individu MapToIndividu(IndividuModel individu) {
        Individu ind = new Individu();
        //region "KARAKTERISTIK MOUN NAN -|- POU TOUT MANM MENAJ LA -|- KARAKTERISTIK DEMOGRAFIK"
        //ind.setIndividuId(individu.getIndividuId());
        ind.setMenageId(individu.getMenageId());
        ind.setLogeId(individu.getLogeId());
        ind.setBatimentId(individu.getBatimentId());
        ind.setSdeId(individu.getSdeId());
        ind.setQ1NoOrdre(individu.getQ1NoOrdre());
        ind.setQp2APrenom(individu.getQp2APrenom());
        ind.setQp2BNom(individu.getQp2BNom());
        ind.setQp3LienDeParente(individu.getQp3LienDeParente());
        ind.setQp3HabiteDansMenage(individu.getQp3HabiteDansMenage());
        ind.setQp4Sexe(individu.getQp4Sexe());
        ind.setQp5DateNaissanceJour(individu.getQp5DateNaissanceJour());
        ind.setQp5DateNaissanceMois(individu.getQp5DateNaissanceMois());
        ind.setQp5DateNaissanceAnnee(individu.getQp5DateNaissanceAnnee());
        ind.setQp5bAge(individu.getQp5bAge());
        ind.setQp6religion(individu.getQp6religion());
        ind.setQp6AutreReligion(individu.getQp6AutreReligion());
        ind.setQp7Nationalite(individu.getQp7Nationalite());
        ind.setQp7PaysNationalite(individu.getQp7PaysNationalite());
        ind.setQp8MereEncoreVivante(individu.getQp8MereEncoreVivante());
        ind.setQp9EstPlusAge(individu.getQp9EstPlusAge());
        ind.setQp10LieuNaissance(individu.getQp10LieuNaissance());
        ind.setQp10CommuneNaissance(individu.getQp10CommuneNaissance());
        ind.setQp10VqseNaissance(individu.getQp10VqseNaissance());
        ind.setQp10PaysNaissance(individu.getQp10PaysNaissance());
        ind.setQp11PeriodeResidence(individu.getQp11PeriodeResidence());
        ind.setQp12DomicileAvantRecensement(individu.getQp12DomicileAvantRecensement());
        ind.setQp12CommuneDomicileAvantRecensement(individu.getQp12CommuneDomicileAvantRecensement());
        ind.setQp12VqseDomicileAvantRecensement(individu.getQp12VqseDomicileAvantRecensement());
        ind.setQp12PaysDomicileAvantRecensement(individu.getQp12PaysDomicileAvantRecensement());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- EDIKASYON -|- POU MOUN KI GEN 3 LANE OSWA PLIS"
        ind.setQe1EstAlphabetise(individu.getQe1EstAlphabetise());
        ind.setQe2FreqentationScolaireOuUniv(individu.getQe2FreqentationScolaireOuUniv());
        ind.setQe3typeEcoleOuUniv(individu.getQe3typeEcoleOuUniv());
        ind.setQe4aNiveauEtude(individu.getQe4aNiveauEtude());
        ind.setQe4bDerniereClasseOUAneEtude(individu.getQe4bDerniereClasseOUAneEtude());
        ind.setQe5DiplomeUniversitaire(individu.getQe5DiplomeUniversitaire());
        ind.setQe6DomaineEtudeUniversitaire(individu.getQe6DomaineEtudeUniversitaire());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON NAN ZAFE WE"
        ind.setQaf1HandicapVoir(individu.getQaf1HandicapVoir());

        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON NAN ZAFE TANDE"
        ind.setQaf2HandicapEntendre(individu.getQaf2HandicapEntendre());

        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON NAN ZAFE MACHE (BOUJE)"
        ind.setQaf3HandicapMarcher(individu.getQaf3HandicapMarcher());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON KOYITIV"
        ind.setQaf4HandicapSouvenir(individu.getQaf4HandicapSouvenir());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- OKIPE TET LI"
        ind.setQaf5HandicapPourSeSoigner(individu.getQaf5HandicapPourSeSoigner());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- AKTIVITE AK FONKSYÒNMAN MOUN NAN(POU MOUN KI GEN 5 LANE OSWA PLIS) -|- LIMITASYON KOMINIKATIF"
        ind.setQaf6HandicapCommuniquer(individu.getQaf6HandicapCommuniquer());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- NOUVEL TEKNOLOJI NAN KOMINIKASYON(POU MOUN KI GEN 10 LANE OSWA PLIS) -|- POSESYON TELEFON SELILE"
        ind.setQt1PossessionTelCellulaire(individu.getQt1PossessionTelCellulaire());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- NOUVEL TEKNOLOJI NAN KOMINIKASYON(POU MOUN KI GEN 10 LANE OSWA PLIS) -|- ITILIZASYON  ENTENET AK AKSE "
        ind.setQt2UtilisationInternet(individu.getQt2UtilisationInternet());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- MIGRASYON : RETOUNEN VIN VIV AN AYITI(POU MOUN KI GEN 10 LANE OSWA PLIS) -|- TAN LI FE LI TAP VIV NAN LOT PEYI A"
        ind.setQem1DejaVivreAutrePays(individu.getQem1DejaVivreAutrePays());
        ind.setQem1AutrePays(individu.getQem1AutrePays());
        //endregion
        //region "KARAKTERISTIK MOUN NAN -|- MIGRASYON : RETOUNEN VIN VIV AN AYITI(POU MOUN KI GEN 10 LANE OSWA PLIS) -|- DAT RETOU A"
        ind.setQem2MoisRetour(individu.getQem2MoisRetour());
        ind.setQem2AnneeRetour(individu.getQem2AnneeRetour());
        //endregion
        //region "KARAKTERISTIK MOUN NAN  -|- Pou moun ki genyen dis (10) lane epi plis -|- ESTATI MATRIMONYAL"
        ind.setQsm1StatutMatrimonial(individu.getQsm1StatutMatrimonial());
        //endregion
        //region "KARAKTERISTIK MOUN NAN  -|- Pou moun ki genyen dis (10) lane epi plis -|- Aktivite Ekonomik"
        ind.setQa1ActEconomiqueDerniereSemaine(individu.getQa1ActEconomiqueDerniereSemaine());
        ind.setQa2ActAvoirDemele1(individu.getQa2ActAvoirDemele1());
        ind.setQa2ActDomestique2(individu.getQa2ActDomestique2());
        ind.setQa2ActCultivateur3(individu.getQa2ActCultivateur3());
        ind.setQa2ActAiderParent4(individu.getQa2ActAiderParent4());
        ind.setQa2ActAutre5(individu.getQa2ActAutre5());
        ind.setQa3StatutEmploie(individu.getQa3StatutEmploie());
        ind.setQa4SecteurInstitutionnel(individu.getQa4SecteurInstitutionnel());
        ind.setQa5TypeBienProduitParEntreprise(individu.getQa5TypeBienProduitParEntreprise());
        ind.setQa5PreciserTypeBienProduitParEntreprise(individu.getQa5PreciserTypeBienProduitParEntreprise());
        ind.setQa6LieuActDerniereSemaine(individu.getQa6LieuActDerniereSemaine());
        ind.setQa7FoncTravail(individu.getQa7FoncTravail());
        ind.setQa8EntreprendreDemarcheTravail(individu.getQa8EntreprendreDemarcheTravail());
        ind.setQa9VouloirTravailler(individu.getQa9VouloirTravailler());
        ind.setQa10DisponibilitePourTravail(individu.getQa10DisponibilitePourTravail());
        ind.setQa11RecevoirTransfertArgent(individu.getQa11RecevoirTransfertArgent());
        //endregion
        //region "FEGONDITE -|- POU FANM KI GEN 13 LANE OSWA PLIS -|- "
        ind.setQf1aNbreEnfantNeVivantM(individu.getQf1aNbreEnfantNeVivantM());
        ind.setQf1bNbreEnfantNeVivantF(individu.getQf1bNbreEnfantNeVivantF());
        ind.setQf2aNbreEnfantVivantM(individu.getQf2aNbreEnfantVivantM());
        ind.setQf2bNbreEnfantVivantF(individu.getQf2bNbreEnfantVivantF());
        ind.setQf3DernierEnfantJour(individu.getQf3DernierEnfantJour());
        ind.setQf3DernierEnfantMois(individu.getQf3DernierEnfantMois());
        ind.setQf3DernierEnfantAnnee(individu.getQf3DernierEnfantAnnee());
        ind.setQf4DENeVivantVit(individu.getQf4DENeVivantVit());
        ind.setStatut(individu.getStatut());
        ind.setIsFieldAllFilled(individu.getIsFieldAllFilled());
        ind.setDateDebutCollecte(individu.getDateDebutCollecte());
        ind.setDateFinCollecte(individu.getDateFinCollecte());
        ind.setDureeSaisie(individu.getDureeSaisie());
        ind.setIsContreEnqueteMade(individu.getIsContreEnqueteMade());
        ind.setCodeAgentRecenceur(individu.getCodeAgentRecenceur());
        return ind;
    }
    //endregion

    public static Shared_Preferences MapToPreferences(Context context, PersonnelModel entity) {
        Shared_Preferences sharedPreferences = new Shared_Preferences(context);
        sharedPreferences.setPersId(entity.getPersId());
        sharedPreferences.setSdeId(entity.getSdeId());
        sharedPreferences.setNom(entity.getNom());
        sharedPreferences.setPrenom(entity.getPrenom());
        sharedPreferences.setPrenomEtNom();
        sharedPreferences.setSexe(entity.getSexe());
        sharedPreferences.setNomUtilisateur(entity.getNomUtilisateur());
        sharedPreferences.setMotDePasse(entity.getMotDePasse());
        sharedPreferences.setEmail(entity.getEmail());
        sharedPreferences.setEstActif(entity.getEstActif());
        sharedPreferences.setProfileId(entity.getProfileId());
        sharedPreferences.setDeptId(entity.getDeptId());
        sharedPreferences.setComId(entity.getComId());
        sharedPreferences.setVqseId(entity.getVqseId());

        sharedPreferences.setZone(entity.getZone());
        sharedPreferences.setCodeDsitri(entity.getCodeDistrict());

        sharedPreferences.setIsConnected(entity.getIsConnected());
        sharedPreferences.setprefLastLogin("");
        return sharedPreferences;
    }//

    public static RapportRAR MapToRapportRAR(RapportRARModel rarModel) {
        RapportRAR rap = new RapportRAR();
        //rap.setRapportId(rarModel.getRapportId());
        rap.setBatimentId(rarModel.getBatimentId());
        rap.setLogeId(rarModel.getLogeId());
        rap.setMenageId(rarModel.getMenageId());
        rap.setIndividuId(rarModel.getEmigreId());
        rap.setIndividuId(rarModel.getDecesId());
        rap.setIndividuId(rarModel.getIndividuId());

        rap.setRapportModuleName(rarModel.getRapportModuleName());
        rap.setCodeQuestionStop(rarModel.getCodeQuestionStop());
        rap.setVisiteNumber(rarModel.getVisiteNumber());
        rap.setRaisonActionId(rarModel.getRaisonActionId());
        rap.setAutreRaisonAction(rarModel.getAutreRaisonAction());
        rap.setIsFieldAllFilled(rarModel.getIsFieldAllFilled());
        rap.setDateDebutCollecte(rarModel.getDateDebutCollecte());
        rap.setDateFinCollecte(rarModel.getDateFinCollecte());
        rap.setDureeSaisie(rarModel.getDureeSaisie());
        rap.setCodeAgentRecenceur(rarModel.getCodeAgentRecenceur());
        rap.setIsContreEnqueteMade(rarModel.getIsContreEnqueteMade());
        return rap;
    }
    public static RapportRAR MapToRapportRAR(RapportRAR rarModel) {
        RapportRAR rap = new RapportRAR();
        rap.setRapportId(rarModel.getRapportId());
        rap.setBatimentId(rarModel.getBatimentId());
        rap.setLogeId(rarModel.getLogeId());
        rap.setMenageId(rarModel.getMenageId());
        rap.setIndividuId(rarModel.getEmigreId());
        rap.setIndividuId(rarModel.getDecesId());
        rap.setIndividuId(rarModel.getIndividuId());

        rap.setRapportModuleName(rarModel.getRapportModuleName());
        rap.setCodeQuestionStop(rarModel.getCodeQuestionStop());
        rap.setVisiteNumber(rarModel.getVisiteNumber());
        rap.setRaisonActionId(rarModel.getRaisonActionId());
        rap.setAutreRaisonAction(rarModel.getAutreRaisonAction());
        rap.setIsFieldAllFilled(rarModel.getIsFieldAllFilled());
        rap.setDateDebutCollecte(rarModel.getDateDebutCollecte());
        rap.setDateFinCollecte(rarModel.getDateFinCollecte());
        rap.setDureeSaisie(rarModel.getDureeSaisie());
        rap.setCodeAgentRecenceur(rarModel.getCodeAgentRecenceur());
        rap.setIsContreEnqueteMade(rarModel.getIsContreEnqueteMade());
        return rap;
    }

    public static RapportFinal MapToRapportFinal(RapportFinalModel rarModel) {
        RapportFinal rap = new RapportFinal();
        //rap.setRapportId(rarModel.getRapportId());
        rap.setBatimentId(rarModel.getBatimentId());
        rap.setLogeId(rarModel.getLogeId());
        rap.setMenageId(rarModel.getMenageId());
        rap.setRepondantPrincipalId(rarModel.getRepondantPrincipalId());

        rap.setAE_EsKeGenMounKiEde(rarModel.getAE_EsKeGenMounKiEde());
        rap.setAE_IsVivreDansMenage(rarModel.getAE_IsVivreDansMenage());
        rap.setAE_RepondantQuiAideId(rarModel.getAE_RepondantQuiAideId());

        rap.setF_EsKeGenMounKiEde(rarModel.getF_EsKeGenMounKiEde());
        rap.setF_IsVivreDansMenage(rarModel.getF_IsVivreDansMenage());
        rap.setF_RepondantQuiAideId(rarModel.getF_RepondantQuiAideId());

        rap.setDateDebutCollecte(rarModel.getDateDebutCollecte());
        rap.setDateFinCollecte(rarModel.getDateFinCollecte());
        rap.setDureeSaisie(rarModel.getDureeSaisie());
        rap.setCodeAgentRecenceur(rarModel.getCodeAgentRecenceur());
        rap.setIsContreEnqueteMade(rarModel.getIsContreEnqueteMade());
        return rap;
    }

    //
}// END CLASS
//
