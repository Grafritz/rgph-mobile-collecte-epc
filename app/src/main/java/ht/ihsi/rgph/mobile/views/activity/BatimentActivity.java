package ht.ihsi.rgph.mobile.views.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;*/

import com.squareup.picasso.Picasso;

import ht.ihsi.rgph.mobile.BuildConfig;
import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.constant.Constant;
import ht.ihsi.rgph.mobile.models.BatimentModel;
import ht.ihsi.rgph.mobile.models.CommuneModel;
import ht.ihsi.rgph.mobile.models.DepartementModel;
import ht.ihsi.rgph.mobile.models.ModuleModel;
import ht.ihsi.rgph.mobile.models.PersonnelModel;
import ht.ihsi.rgph.mobile.models.VqseModel;
import ht.ihsi.rgph.mobile.utilities.QuestionnaireFormulaireUtility;
import ht.ihsi.rgph.mobile.utilities.Shared_Preferences;
import ht.ihsi.rgph.mobile.utilities.ToastUtility;
import ht.ihsi.rgph.mobile.utilities.Tools;
import ht.ihsi.rgph.mobile.views.activity.Settings.SettingsActivity;

/**
 * Created by ajordany on 3/31/2016.
 */
public class BatimentActivity extends BaseActivity  implements  View.OnClickListener {

    private static final String ADD_BATIMAN_DESC="<i>Siw vle ajoute yon nouvo Batiman, klike sou bouton sa</i>";
    private static final String BATIMAN_KI_PA_FINI_DESC="<i>Siw vle ale nan batiman ki poko fini yo, klike sou bouton sa...</i>";
    private static final String BATIMAN_KI_MAL_RAMPLI_DESC="<i>Siw vle ale nan batiman ki mal ranpli yo, klike sou bouton sa...<i>";
    private static final String BATIMAN_KI_FINI_DESC="<i>Siw vle gade batiman ki fini yo, klike sou bouton sa...</i>";

    private Intent intent;
    public Boolean cancel = true;
    public Button btn_QuitterApplication;
    public Button btn_Connexion;
    public TextView tv_batiman_ki_fini;
    private TextView txtHeaderOne;
    private TextView txtHeaderTwo, tv_CompteUtilisateur;

    private RelativeLayout btnAdd;
    private RelativeLayout btn_AjouteBatiman;
    private RelativeLayout btnBatimentPaFini;
    private RelativeLayout btnBatimanMalRampli;
    private RelativeLayout btnBatimentFini;
    private LinearLayout LL_ActionButton;

    private LinearLayout LLGestionSecurity;
    private LinearLayout RL_btn_CompteUtilisateur, RL_btn_Rapport;
    private ImageView btn_ImageCompteUser, btn_ImageRapport;
    private TextView btn_TextCompteUser, btn_TextRapport;

    Shared_Preferences sharedPreferences = null;
    String StringHeaderTwo = "";
    public Dialog dialog;
    public TextView tv_CopyRight, tv_titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batiment);
        try {
            if(savedInstanceState==null) {
                startActivity(new Intent(this, SplashScreen.class));
            }

            boolean result = Tools.CheckPermission_Location(this);
            if(!result) {
                message="Fòk Aplikasyon [ " + getString(R.string.app_name) + "] gen privilèj pou li lokalizew!";
                //ToastUtility.LogCat(this, message);
                ToastUtility.ToastMessage(this, message);
            }
            tv_CopyRight = (TextView)this.findViewById(R.id.tv_CopyRight);
            tv_CopyRight.setText(getString(R.string.msg_Developpeur) + "  -|- Ver. " + BuildConfig.VERSION_NAME);

            tv_titre = (TextView)this.findViewById(R.id.list_header_1);
            tv_titre.setText(getString(R.string.app_name) + "\nVer." + BuildConfig.VERSION_NAME);

            tv_CompteUtilisateur = (TextView) this.findViewById(R.id.tv_CompteUtilisateur);

            btn_QuitterApplication = (Button) this.findViewById(R.id.btn_QuitterApplication);
            btn_QuitterApplication.setOnClickListener(this);

            init(Constant.FORM_DATA_MANAGER);
            init(Constant.QUERY_RECORD_MANAGER);

            txtHeaderOne = (TextView) findViewById(R.id.list_header_1);
            txtHeaderTwo = (TextView) findViewById(R.id.list_header_2);

            btn_AjouteBatiman = (RelativeLayout) findViewById(R.id.btn_AjouteBatiman);
            setText((TextView) btn_AjouteBatiman.findViewById(R.id.test_button_text2), getStringRessource(R.string.label_ajoute_batiman).toUpperCase());
            setText((TextView) btn_AjouteBatiman.findViewById(R.id.test_button_text1), Html.fromHtml(ADD_BATIMAN_DESC).toString());
            btn_AjouteBatiman.setOnClickListener(this);

            LL_ActionButton = (LinearLayout) findViewById(R.id.LL_ActionButton);

            //region SECURITY
            LLGestionSecurity = (LinearLayout) findViewById(R.id.LLGestionSecurity);
            //region COMPTE USERS
            RL_btn_CompteUtilisateur = (LinearLayout) findViewById(R.id.RL_btn_CompteUtilisateur);
            btn_ImageCompteUser = (ImageView) RL_btn_CompteUtilisateur.findViewById(R.id.test_button_image);
            Picasso.with(this).load(R.drawable.group_user).placeholder(R.drawable.group_user).into(btn_ImageCompteUser);
            btn_TextCompteUser = (TextView) RL_btn_CompteUtilisateur.findViewById(R.id.textView4);
            btn_TextCompteUser.setText(R.string.label_KontItilizate);
            RL_btn_CompteUtilisateur.setOnClickListener(this);
            //endregion

            //region RAPPORTS
            RL_btn_Rapport = (LinearLayout) findViewById(R.id.RL_btn_Rapport);
            btn_ImageRapport = (ImageView) RL_btn_Rapport.findViewById(R.id.test_button_image);
            Picasso.with(this).load(R.drawable.report).placeholder(R.drawable.report).into(btn_ImageRapport);
            btn_TextRapport = (TextView) RL_btn_Rapport.findViewById(R.id.textView4);
            btn_TextRapport.setText(R.string.label_Rapo_Sou_Batiman_Yo);
            RL_btn_Rapport.setOnClickListener(this);
            //endregion

            //endregion

            CheckPrefIsUseConnected(false);
        }catch (Exception ex) {
            ToastUtility.LogCat(this, ex);
        }

    }

    protected void setText(TextView textView,String text){
        textView.setText(Html.fromHtml(text));
    }

    protected String getStringRessource(int id){
        return this.getResources().getString(id).toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //ToastUtility.LogCat("I", "onResume() - setStringControlButton() ");
        CheckPrefIsUseConnected(false);
        setStringControlButton();
        //QuestionnaireBatimentActivity.CounterForLogeCollectif=0;

        // Joda Time
        /*DateTime start = new DateTime(2017, 8, 8, 13, 40, 0, 0);
        DateTime end = new DateTime();
        // duration in ms between two instants
        Duration dur = new Duration(start, end);

        ToastUtility.LogCat("W","getStandardSeconds():" + dur.getStandardSeconds()
                +"\ngetStandardMinutes:" + dur.getStandardMinutes());
        // calc will be the same as end
        DateTime calc = start.plus(dur);
        ToastUtility.LogCat("W","secondOfMinute:" + calc.secondOfMinute());

        Tools.getDateInfo(this);*/
    }

    public void CheckPrefIsUseConnected(boolean forConnexion) {
        try {
            Boolean checkPrefIsUseConnected = Tools.CheckPrefIsUseConnected(this);
            if (!checkPrefIsUseConnected) {
                cancel = true;
                btn_AjouteBatiman.setVisibility(View.GONE);
                tv_CompteUtilisateur.setVisibility(View.GONE);
                txtHeaderTwo.setVisibility(View.GONE);
                LLGestionSecurity.setVisibility(View.GONE);

                btn_QuitterApplication.setText(getString(R.string.label_Quit));

                if (forConnexion) {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    btn_QuitterApplication.setText(getString(R.string.label_Konekte));
                    LL_ActionButton.setVisibility(View.GONE);
                }
            } else {
                cancel = false;
                btn_QuitterApplication.setText(getString(R.string.label_Deconnecter));
                btn_AjouteBatiman.setVisibility(View.VISIBLE);
                LL_ActionButton.setVisibility(View.VISIBLE);
                tv_CompteUtilisateur.setVisibility(View.VISIBLE);
                txtHeaderTwo.setVisibility(View.VISIBLE);

                loadDataUser();
            }
        }catch (Exception ex) {
            message = "Erreur :";
            ToastUtility.LogCat("Exception: CheckPrefIsUseConnected :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }//

    private void loadDataUser() {
        try{
            sharedPreferences = Tools.SharedPreferences(this);
            Boolean checkPrefIsUseConnected = Tools.CheckPrefIsUseConnected(this);
            if (checkPrefIsUseConnected) {
                if (sharedPreferences != null) {
                    String departementStr = "", communeStr = "", vqseStr = "", nomComplet = "";
                    if (sharedPreferences.getDeptId() != null
                            && sharedPreferences.getComId() != null
                            && sharedPreferences.getVqseId() != null
                            && sharedPreferences.getPrenomEtNom() != null) {
                        DepartementModel departementModel = formDataMngr.getDepartementById(sharedPreferences.getDeptId());
                        departementStr = departementModel.getDeptNom();

                        CommuneModel communeModel = formDataMngr.getCommuneById(sharedPreferences.getComId());
                        communeStr = communeModel.getComNom();
                        VqseModel vqseModel = formDataMngr.getVqseById(sharedPreferences.getVqseId());
                        vqseStr = vqseModel.getVqseNom();

                        nomComplet = "Byenvini <b>" + sharedPreferences.getPrenomEtNom() + "</b>";
                    }

                    tv_CompteUtilisateur.setText(Html.fromHtml("" + nomComplet));

                    final String localisationString =  departementStr + " <b>|</b> " + communeStr + " <b>|</b> " + vqseStr
                            + "<br />" + "<b>SDE:</b>" + sharedPreferences.getSdeId()
                            + " | <b>" + (sharedPreferences.getZone().equalsIgnoreCase("" + Constant.ZONE_URBAIN_1) ? " Zòn Iben" : " Zòn Riral") + "</b>";
                    StringHeaderTwo = localisationString;

                    txtHeaderTwo.setText(Html.fromHtml("" + localisationString));

                    LLGestionSecurity.setVisibility(View.GONE);
                    btn_AjouteBatiman.setVisibility(View.GONE);
                    RL_btn_CompteUtilisateur.setVisibility(View.GONE);
                    RL_btn_Rapport.setVisibility(View.GONE);

                    if( sharedPreferences.getProfileId()==Constant.PRIVILEGE_DEVELOPPEUR){
                        LLGestionSecurity.setVisibility(View.VISIBLE);
                        RL_btn_CompteUtilisateur.setVisibility(View.VISIBLE);
                        RL_btn_Rapport.setVisibility(View.VISIBLE);
                        btn_AjouteBatiman.setVisibility(View.VISIBLE);

                    } else if( sharedPreferences.getProfileId()==Constant.PRIVILEGE_ASTIC
                            || sharedPreferences.getProfileId()==Constant.PRIVILEGE_SUPERVISEUR  ){
                        RL_btn_Rapport.setVisibility(View.VISIBLE);

                    } else if( sharedPreferences.getProfileId()==Constant.PRIVILEGE_AGENT  ){
                        btn_AjouteBatiman.setVisibility(View.VISIBLE);
                    }
                }
            }
        }catch (Exception ex) {
            message = "Erreur :";
            ToastUtility.LogCat("Exception: CheckPrefIsUseConnected :" + message +" / " + ex.toString());
            Tools.AlertDialogMsg(this, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }

    //
    private void setStringControlButton() {
        try {
            CheckPrefIsUseConnected(false);
            this.setTitle(this.getResources().getString(R.string.label_titre_batiman));
            Boolean checkPrefIsUseConnected = Tools.CheckPrefIsUseConnected(this);
            if (checkPrefIsUseConnected) {
                loadDataUser();

                long total = queryRecordMngr.countBatimentByStatus(Constant.STATUT_MODULE_KI_PA_FINI_3);
                btnBatimentPaFini = (RelativeLayout) findViewById(R.id.btn_batiman_ki_pa_fini);
                setText((TextView) btnBatimentPaFini.findViewById(R.id.test_button_text2), (this.getResources().getString(R.string.label_batiman_ki_pa_fini).toString() + "(" + total + ")").toUpperCase());
                setText((TextView) btnBatimentPaFini.findViewById(R.id.test_button_text1), Html.fromHtml(BATIMAN_KI_PA_FINI_DESC).toString());
                btnBatimentPaFini.setVisibility(View.GONE);
                if (total > 0) {
                    btnBatimentPaFini.setVisibility(View.VISIBLE);
                    btnBatimentPaFini.setOnClickListener(this);
                }

                total = queryRecordMngr.countBatimentByStatus(Constant.STATUT_MODULE_KI_MAL_RANPLI_2);
                btnBatimanMalRampli = (RelativeLayout) findViewById(R.id.btn_batiman_ki_mal_ranpli);
                setText((TextView) btnBatimanMalRampli.findViewById(R.id.test_button_text2), (this.getResources().getString(R.string.label_batiman_ki_mal_ranpli).toString() + "(" + total + ")").toUpperCase());
                setText((TextView) btnBatimanMalRampli.findViewById(R.id.test_button_text1), Html.fromHtml(BATIMAN_KI_MAL_RAMPLI_DESC).toString());
                btnBatimanMalRampli.setVisibility(View.GONE);
                if (total > 0) {
                    btnBatimanMalRampli.setVisibility(View.VISIBLE);
                    btnBatimanMalRampli.setOnClickListener(this);
                }

                total = queryRecordMngr.countBatimentByStatus(Constant.STATUT_MODULE_KI_FINI_1);
                btnBatimentFini = (RelativeLayout) findViewById(R.id.btn_batiman_ki_fini);
                setText((TextView) btnBatimentFini.findViewById(R.id.test_button_text2), (this.getResources().getString(R.string.label_batiman_ki_fini).toString() + "(" + total + ")").toUpperCase());
                setText((TextView) btnBatimentFini.findViewById(R.id.test_button_text1), Html.fromHtml(BATIMAN_KI_FINI_DESC).toString());
                btnBatimentFini.setVisibility(View.GONE);
                if (total > 0) {
                    btnBatimentFini.setVisibility(View.VISIBLE);
                    btnBatimentFini.setOnClickListener(this);
                }
            }
        } catch (Exception ex) {
            ToastUtility.LogCat("ERROR-Exception:setStringControlButton" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.btn_actionbar_Settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }//

    public void showListView(String title, int listType, int statut) {
        intent = new Intent(this, DisplayListActivity.class);
        intent.putExtra(Constant.PARAM_LIST_TITLE, title);
        intent.putExtra(Constant.PARAM_LIST_TYPE, "" + listType);
        intent.putExtra(Constant.PARAM_LISTE_HEADER_ONE, "" + title);
        intent.putExtra(Constant.PARAM_LISTE_HEADER_TWO, "" + txtHeaderTwo.getText());
        intent.putExtra(Constant.PARAM_MODULE_STATUT, "" + statut);
        intent.putExtra(Constant.PARAM_MODULE_ID, "" + 0);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        //CheckPrefIsUseConnected();
        switch (v.getId()) {
            case R.id.btn_AjouteBatiman:
                ToastUtility.LogCat("onClick :-: btn_AjouteBatiman");
                CheckPrefIsUseConnected(true);
                if (!cancel) {
                    //ShowFormulaireBatiment();
                    // On effectue la rechercher du formulaire actif
                    GetQuestion();
                }
                break;
            case R.id.btn_batiman_ki_pa_fini:
                showListView(getString(R.string.label_batiman_ki_pa_fini), Constant.LIST_MODULE_BATIMENT, Constant.STATUT_MODULE_KI_PA_FINI_3);
                //ToastUtility.LogCat(this, "Constant.STATUT_MODULE: " + Constant.STATUT_MODULE_KI_PA_FINI_3);
                break;
            case R.id.btn_batiman_ki_mal_ranpli:
                showListView(getString(R.string.label_batiman_ki_mal_ranpli), Constant.LIST_MODULE_BATIMENT, Constant.STATUT_MODULE_KI_MAL_RANPLI_2);
                //ToastUtility.LogCat(this, "Constant.STATUT MODULE: " + Constant.STATUT_MODULE_KI_MAL_RANPLI_2);
                break;
            case R.id.btn_batiman_ki_fini:
                CheckPrefIsUseConnected(true);
                if (!cancel)
                showListView(getString(R.string.label_batiman_ki_fini), Constant.LIST_MODULE_BATIMENT, Constant.STATUT_MODULE_KI_FINI_1);
                //ToastUtility.LogCat(this, "Constant.STATUT_MODULE: " + Constant.STATUT_MODULE_KI_FINI_1);
                break;
            //region SECURITY
            case R.id.RL_btn_CompteUtilisateur:
                CheckPrefIsUseConnected(true);
                if (!cancel)
                showListView(getString(R.string.label_Kont_Itilizate), Constant.LIST_MODULE_COMPTE_UTILISATEUR_16, 0);
                break;
            case R.id.RL_btn_Rapport:
                CheckPrefIsUseConnected(true);
                if (!cancel)
                    showListView(getString(R.string.label_Rapo_Sou_Batiman_Yo), Constant.LIST_MODULE_BATIMANT_POUR_RAPPORT_17,  Constant.STATUT_MODULE_KI_FINI_1);
                break;
            //endregion
            case R.id.btn_QuitterApplication:
                if (!Tools.CheckPrefIsUseConnected(this)) {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Disconnected();
                }
                break;
            default:
        }
    }//

    private void Disconnected() {
        PersonnelModel personnelModel = new PersonnelModel();
        personnelModel.setIsConnected(false);

        Tools.StoreInfoPresonnel_PreferenceManager(this, personnelModel);

        CheckPrefIsUseConnected(false);
    }

    public void GetQuestion() {
        try {
            //ToastUtility.LogCat("I", "Inside of GetQuestion");

            ModuleModel moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_BATIMENT, Constant.ACTIF);
            if (moduleModel != null) {
                //Tools.getDateInfo(this);
                dateString = Tools.getDateString_MMddyyyy_HHmmss_a();
                //getDateDebutCollecte = dateTimeFormatter.parseDateTime(dateString);
                //ToastUtility.LogCat(this,"dateTimeFormatter.print(getDateDebutCollecte):" + dateTimeFormatter.print(getDateDebutCollecte));

                //ToastUtility.LogCat(this,"dateDebutCollect:" + dateDebutCollect.toString());
                //ToastUtility.ToastMessage(this,"dateDebutCollect:" + dateDebutCollect.toString());
                //ToastUtility.LogCat(this, "getNomModule>> " + moduleModel.getNomModule() +  " / getNomModule>> " + moduleModel.getCodeModule());
                BatimentModel batimentModel =  new BatimentModel();
                batimentModel.setSdeId(Tools.SharedPreferences(this).getSdeId());
                batimentModel.setCodeAgentRecenceur(Tools.SharedPreferences(this).getNomUtilisateur());
                //getDateDebutCollecte = dateTimeFormatter.parseDateTime(dateString);
                batimentModel.setDateDebutCollecte(dateString) ;

                ToastUtility.LogCat(this,"dateTimeFormatter.print(dateDebutCollect):" + dateString);

                QuestionnaireFormulaireUtility QF = new QuestionnaireFormulaireUtility( moduleModel, batimentModel, Constant.FORMULAIRE_BATIMENT, formDataMngr);
                QF.TypeEvenement = Constant.ACTION_NOUVEAU;
                QF.setDateDebutCollecte(dateString);
                ToastUtility.LogCat(this,"getDateDebutCollecte:" + dateString);


                intent = new Intent(this, QuestionnaireBatimentActivity.class);
                //if ( sharedPreferences != null ){
                //    StringHeaderTwo = "SDE: " + sharedPreferences.getSdeId();
                //}
                //StringHeaderTwo = txtHeaderTwo.getText().toString();
                intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, "AJOUTE YON NOUVO BATIMAN");
                intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, StringHeaderTwo );
                intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QF);
                startActivity(intent);
                //intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, txtHeaderTwo.getText().toString());
                //QuestionsModel questionsModel = formDataMngr.getFirstQuestionOfModule(moduleModel.getCodeModule());

                //QuestionnaireModel questionnaireModel =  new QuestionnaireModel();
                //questionnaireModel.setTbl_TableName(Constant.FORMULAIRE_BATIMENT);
                //questionnaireModel.setTbl_TableName(Constant.FORMULAIRE_BATIMENT);
                //questionnaireModel.setModel(questionsModel);
                //SessionUtility.setCurrentQuestionnaireFormulaireUtility(QF);
            } else {
                ToastUtility.LogCat("moduleModel NULL");
            }
        } catch (Exception ex) {
            ToastUtility.ToastMessage(this, "Exception:GetQuestion()  getMessage:" + ex.getMessage() + "\n toString:" + ex.toString());
            ToastUtility.LogCat(this, "Exception:GetQuestion()  getMessage:", ex);
            ex.printStackTrace();
        }
    }//

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    //

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            final int _keyCode=keyCode;
            final KeyEvent _event=event;
            //e = event;
            //k = keyCode;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("" + getString(R.string.msg_Eske_Ou_Vle_Kite_Vre))
                    .setCancelable(false)
                    .setPositiveButton(""+ getString(R.string.label_Non), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
                    .setNegativeButton("" + getString(R.string.label_WI), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            BatimentActivity.super.onKeyDown(_keyCode, _event);
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        }else  if (keyCode == KeyEvent.KEYCODE_HOME){
            return true;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }//
}// End Class Batiment
