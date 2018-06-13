package ht.ihsi.rgph.mobile.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.constant.Constant;
import ht.ihsi.rgph.mobile.exceptions.ManagerException;
import ht.ihsi.rgph.mobile.models.BatimentModel;
import ht.ihsi.rgph.mobile.models.LogementModel;
import ht.ihsi.rgph.mobile.models.MenageModel;
import ht.ihsi.rgph.mobile.models.ModuleModel;
import ht.ihsi.rgph.mobile.models.RowDataListModel;
import ht.ihsi.rgph.mobile.utilities.QuestionnaireFormulaireUtility;
import ht.ihsi.rgph.mobile.utilities.ToastUtility;

public class MenageActivity extends BaseActivity implements View.OnClickListener {

    private static final String ADD_MEANGE_DESC="<i>Siw vle ajoute yon nouvo menaj, klike sou boutan sa</i>";
    private static final String MEANGE_KI_PA_FINI_DESC="<i>Siw vle ale nan menaj ki poko fini yo, klike sou boutan sa...</i>";
    private static final String MEANGE_KI_MAL_RAMPLI_DESC="<i>Siw vle ale nan menaj ki mal ranpli yo, klike sou boutan sa...<i>";
    private static final String MEANGE_KI_FINI_DESC="<i>Siw vle gade menaj ki fini yo, klike sou boutan sa...</i>";


    private Intent intent;
    public LogementModel logementModel = null;

    private RowDataListModel rowDada;
    private BatimentModel model;
    String title = "";

    private RelativeLayout btn_AjouteMenage;
    private RelativeLayout btn_menage_ki_pa_fini;
    private RelativeLayout btn_menage_ki_mal_ranpli;
    private RelativeLayout tv_menage_ki_fini;

    private TextView txtHeaderOne;
    private TextView txtHeaderTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menage);
        try {

            Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
            setSupportActionBar(toolbar);

            init(Constant.FORM_DATA_MANAGER);
            init(Constant.QUERY_RECORD_MANAGER);

            txtHeaderOne=(TextView) findViewById(R.id.list_header_1);
            txtHeaderTwo=(TextView) findViewById(R.id.list_header_2);

            Intent intent = getIntent();
            title = intent.getStringExtra(Constant.PARAM_LIST_TITLE);
            rowDada = (RowDataListModel) intent.getSerializableExtra(Constant.PARAM_MODEL);
            logementModel = (LogementModel) rowDada.getModel();
            title = rowDada.getTitle();

            txtHeaderOne.setText("JERE MENAJ");
            txtHeaderTwo.setText(title);

            CheckPrefIsUseConnected();
            setStringControlButton();
        } catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception: onCreate(): getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }

    protected String getStringRessource(int id){
        return this.getResources().getString(id).toString();
    }

    protected void setText(TextView textView,String text){
        textView.setText(text);
    }


    private void setStringControlButton(){
        try {
            //logementModel = SessionUtility.getLogementModel();
            this.setTitle(title);

            btn_AjouteMenage=(RelativeLayout)findViewById(R.id.btn_AjouteMenage);
            setText((TextView) btn_AjouteMenage.findViewById(R.id.test_button_text2), getStringRessource(R.string.label_ajoute_menage).toUpperCase());
            setText((TextView) btn_AjouteMenage.findViewById(R.id.test_button_text1), Html.fromHtml(ADD_MEANGE_DESC).toString());
            btn_AjouteMenage.setOnClickListener(this);

            long total = queryRecordMngr.countMenageByStatusAndLog(Constant.STATUT_MODULE_KI_PA_FINI_3, logementModel.getLogeId());
            btn_menage_ki_pa_fini=(RelativeLayout)findViewById(R.id.btn_menage_ki_pa_fini);
            setText((TextView) btn_menage_ki_pa_fini.findViewById(R.id.test_button_text2), (this.getResources().getString(R.string.label_menaj_ki_pa_fini).toString() + "(" + total + ")").toUpperCase());
            setText((TextView) btn_menage_ki_pa_fini.findViewById(R.id.test_button_text1), Html.fromHtml(MEANGE_KI_PA_FINI_DESC).toString());
            btn_menage_ki_pa_fini.setVisibility(View.GONE);
            if (total > 0) {
                btn_menage_ki_pa_fini.setVisibility(View.VISIBLE);
                btn_menage_ki_pa_fini.setOnClickListener(this);
            }

            total = queryRecordMngr.countMenageByStatusAndLog(Constant.STATUT_MODULE_KI_MAL_RANPLI_2, logementModel.getLogeId());
            btn_menage_ki_mal_ranpli=(RelativeLayout)findViewById(R.id.btn_menage_ki_mal_ranpli);
            setText((TextView) btn_menage_ki_mal_ranpli.findViewById(R.id.test_button_text2), (this.getResources().getString(R.string.label_menaj_ki_mal_ranpli).toString() + "(" + total + ")").toUpperCase());
            setText((TextView) btn_menage_ki_mal_ranpli.findViewById(R.id.test_button_text1), Html.fromHtml(MEANGE_KI_MAL_RAMPLI_DESC).toString());
            btn_menage_ki_mal_ranpli.setVisibility(View.GONE);
            if (total > 0) {
                btn_menage_ki_mal_ranpli.setVisibility(View.VISIBLE);
                btn_menage_ki_mal_ranpli.setOnClickListener(this);
            }

            total = queryRecordMngr.countMenageByStatusAndLog(Constant.STATUT_MODULE_KI_FINI_1, logementModel.getLogeId());
            tv_menage_ki_fini=(RelativeLayout)findViewById(R.id.btn_menage_ki_fini);
            setText((TextView)tv_menage_ki_fini.findViewById(R.id.test_button_text2),(this.getResources().getString(R.string.label_menaj_ki_fini).toString() + "(" + total + ")").toUpperCase());
            setText((TextView)tv_menage_ki_fini.findViewById(R.id.test_button_text1),Html.fromHtml(MEANGE_KI_FINI_DESC).toString());
            tv_menage_ki_fini.setVisibility(View.GONE);
            if (total > 0) {
                tv_menage_ki_fini.setVisibility(View.VISIBLE);
                tv_menage_ki_fini.setOnClickListener(this);
            }

        } catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception: setStringControlButton(): getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }//

    @Override
    public void onClick(View v) {
        try {
            CheckPrefIsUseConnected();
            switch (v.getId()) {
                case R.id.btn_AjouteMenage:
                    if (!cancel) {
                        Menage_Click();
                    }
                    break;
                case R.id.btn_menage_ki_pa_fini:
                    showListView(getString(R.string.label_menaj_ki_pa_fini), Constant.LIST_MODULE_MENAGE, Constant.STATUT_MODULE_KI_PA_FINI_3,rowDada.getId(),txtHeaderTwo.getText().toString());
                    ToastUtility.LogCat(this, "Constant.STATUT_MODULE: " + Constant.STATUT_MODULE_KI_PA_FINI_3);
                    break;
                case R.id.btn_menage_ki_mal_ranpli:
                    showListView(getString(R.string.label_menaj_ki_mal_ranpli), Constant.LIST_MODULE_MENAGE, Constant.STATUT_MODULE_KI_MAL_RANPLI_2,logementModel.getLogeId(),txtHeaderTwo.getText().toString());
                    ToastUtility.LogCat(this, "Constant.STATUT MODULE: " + Constant.STATUT_MODULE_KI_MAL_RANPLI_2);
                    break;
                case R.id.btn_menage_ki_fini:
                    showListView(getString(R.string.label_menaj_ki_fini), Constant.LIST_MODULE_MENAGE, Constant.STATUT_MODULE_KI_FINI_1,logementModel.getLogeId(),txtHeaderTwo.getText().toString());
                    ToastUtility.LogCat(this, "Constant.STATUT_MODULE: " + Constant.STATUT_MODULE_KI_FINI_1);
                    break;
                default:
            }
        }catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception: onClick(): getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }//

    @Override
    public void onResume() {
        super.onResume();
        setStringControlButton();
    }

    public void Menage_Click() {
        try {
           /* long NbreMenageSave = queryRecordMngr.countMenageByLog(logementModel.getLogeId());
            final int numeroOrdre = (short) (NbreMenageSave + 1);
            //Boolean isNbreIndividuDepense = logementModel.getQlin12NbreIndividuDepense();
            int nbre_IndividuVivant = 0;
            int nbre_TotalMenage = 0;
            if( logementModel.getQlin11NbreIndividuVivant() != null ){
                nbre_IndividuVivant = logementModel.getQlin11NbreIndividuVivant();
            }
            if( logementModel.getQlin13NbreTotalMenage() != null ){
                nbre_TotalMenage = logementModel.getQlin13NbreTotalMenage();
            }
            boolean IsNbreIndividuDepense_NON = false;

            if ( logementModel.getQlin12NbreIndividuDepense() != null
                    && logementModel.getQlin12NbreIndividuDepense() == Constant.REPONS_NON_2 ){
                IsNbreIndividuDepense_NON = true;
                nbre_TotalMenage = 1;
            }

            if ( nbre_TotalMenage <= 0 ) {
                message = "Ou pa te di gen menaj nan lojman sa.";
                message += "\nOu pap kapab ajoute menaj ? \n - Tounen nan lojman an pouw ka di konbyen menaj ki genyen !";
                //Tools.AlertDialogMsg(this, message, "E");
                //ToastUtility.ToastMessage(this, message);
                throw new TextEmptyException(message);
            }else {
                if (nbre_TotalMenage == NbreMenageSave) {
                    if (IsNbreIndividuDepense_NON) {
                        message = "Ou pa kapab ajoute plis ke yon menaj nan lojman sa.";
                        message += "\nPaske pou kesyon LIN12 la ou te chwazi : " + Tools.getString_Reponse("" + logementModel.getQlin12NbreIndividuDepense(), Constant.Qlin12NbreIndividuDepense);
                        throw new TextEmptyException(message);
                    } else {
                        message = "Ou te di gen [ " + nbre_TotalMenage + " ] menaj nan lojman sa.";
                        message += "\nEske ou vle ajoute yon lòt menaj ankò ?";
                        //throw new TextEmptyException(message);
                    }
                    AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
                    String titleMsg = getString(R.string.msg_title_succesInformation);
                    aBuilder.setTitle(titleMsg);
                    aBuilder.setMessage(message);
                    aBuilder.setCancelable(true);

                    aBuilder.setPositiveButton(getString(R.string.label_Oui),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Incemente le Qb9NbreLogeCollectif dans BATIMENT
                                    //Cls_Batiment b = new Cls_Batiment(BatimentId, " Qb9NbreLogeCollectif = Qb9NbreLogeCollectif + 1 ,Statut=" + ContrainteReponse.FOMILE_KI_PA_FIN_RANPLI_3 + "");
                                    //batiment = b;
                                    //nbreLogeCollectif = b.Qb9NbreLogeCollectif;
                                    //nbreLogeIndividuel = b.Qb9NbreLogeIndividuel;
                                    AjouterMenage(numeroOrdre);
                                }
                            }
                    );
                    aBuilder.setNegativeButton(getString(R.string.label_Non),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //AjouterMenage(numeroOrdre);
                                }
                            }

                    );
                    aBuilder.show();
                } else {
                    AjouterMenage(numeroOrdre);
                }
            }*/
        //} catch (TextEmptyException ex) {
        //    Tools.AlertDialogMsg(this, ex.getMessage());
        } catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception: Menage_Click(): getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }//

    private void ShowDialogBox(String message, final short numeroOrdre) {

    }//

    private void AjouterMenage(int numeroOrdre) {
        try {
            MenageModel menageModel = new MenageModel();
            menageModel.setSdeId(logementModel.getSdeId());
            menageModel.setQm1NoOrdre((short) numeroOrdre);
            // POUR BATIMENT
            menageModel.setBatimentId(logementModel.getBatimentId());
            menageModel.setObjBatiment(logementModel.getObjBatiment());
            // POUR LOGEMENT
            menageModel.setLogeId(logementModel.getLogeId());
            menageModel.setObjLogement(logementModel);

            ModuleModel moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_MENAGE, Constant.ACTIF);
            QuestionnaireFormulaireUtility QF = new QuestionnaireFormulaireUtility( moduleModel, menageModel, Constant.FORMULAIRE_MENAGE, formDataMngr);
            //SessionUtility.setCurrentQuestionnaireFormulaireUtility(QF);

            intent = new Intent(this, QuestionnaireBatimentActivity.class);
            intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, "AJOUTE YON MENAJ");
            intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, " Lojman Endividèl " + logementModel.getQlin1NumeroOrdre()
                    + " | Batiman " + logementModel.getBatimentId() + " | REC: " + logementModel.getObjBatiment().getQrec()  );
            intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QF);
            //intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, txtHeaderTwo.getText().toString());
            startActivity(intent);

        } catch (ManagerException ex) {
            ex.printStackTrace();
            ToastUtility.LogCat(this, "ManagerException: AjouterMenage(): getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
        } catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception: AjouterMenage(): getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
            throw ex;
        }
    }//
}
