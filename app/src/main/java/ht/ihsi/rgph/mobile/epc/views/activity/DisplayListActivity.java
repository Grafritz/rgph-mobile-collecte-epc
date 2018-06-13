package ht.ihsi.rgph.mobile.epc.views.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.epc.backend.entities.BatimentDao;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.exceptions.ManagerException;
import ht.ihsi.rgph.mobile.epc.exceptions.TextEmptyException;
import ht.ihsi.rgph.mobile.epc.models.BatimentModel;
import ht.ihsi.rgph.mobile.epc.models.DecesModel;
import ht.ihsi.rgph.mobile.epc.models.EmigreModel;
import ht.ihsi.rgph.mobile.epc.models.IndividuModel;
import ht.ihsi.rgph.mobile.epc.models.KeyValueModel;
import ht.ihsi.rgph.mobile.epc.models.LogementModel;
import ht.ihsi.rgph.mobile.epc.models.MenageModel;
import ht.ihsi.rgph.mobile.epc.models.ModuleModel;
import ht.ihsi.rgph.mobile.epc.models.RowDataListModel;
import ht.ihsi.rgph.mobile.epc.utilities.Privilege;
import ht.ihsi.rgph.mobile.epc.utilities.QuestionnaireFormulaireUtility;
import ht.ihsi.rgph.mobile.epc.utilities.Shared_Preferences;
import ht.ihsi.rgph.mobile.epc.utilities.ToastUtility;
import ht.ihsi.rgph.mobile.epc.utilities.Tools;
import ht.ihsi.rgph.mobile.epc.views.adapters.DisplayListAdapter;
import ht.ihsi.rgph.mobile.epc.views.decorator.SimpleDividerItemDecoration;
import ht.ihsi.rgph.mobile.epc.views.security.FormulaireUtilisateur;

/**
 * Created by ajordany on 4/7/2016.
 */
public class DisplayListActivity extends BaseActivity {

    private static final String TAG = "DisplayListActivity";
    private List<RowDataListModel> targetList=new ArrayList<RowDataListModel>();
    private RecyclerView recyclerView;
    private DisplayListAdapter mAdapter;
    private ProgressBar progressBar;
    private MaterialSearchView searchView;
    private Toolbar toolbar;
    private String title;
    private int listType=0,listTypeUse=0;
    private short moduleStatut=0;
    private String moduleStatutString="";
    private long id=0;
    int profilId = 0;
    private String nomUtilisateur="";
    private TextView list_header_1;
    private TextView list_header_2;
    String StringHeaderTwo;
    private RowDataListModel rowDataListModel = null;
    private BatimentModel batimentModel = null;
    private LogementModel logementModel = null;
    private MenageModel menageModel = null;
    private EmigreModel emigreModel = null;
    private DecesModel decesModel = null;
    private IndividuModel individuModel = null;
    public Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);
        try {
            infoUser = Tools.SharedPreferences(this);
            if (infoUser != null && infoUser.getProfileId() != null) {
                profilId = infoUser.getProfileId();
                nomUtilisateur = infoUser.getNomUtilisateur();
            }
            Intent intent = getIntent();
            title = intent.getStringExtra(Constant.PARAM_LIST_TITLE);

            StringHeaderTwo = intent.getStringExtra(Constant.PARAM_LISTE_HEADER_TWO).toString();
            listType = listTypeUse = Integer.valueOf(intent.getStringExtra(Constant.PARAM_LIST_TYPE)).intValue();

            moduleStatut = Short.valueOf(intent.getStringExtra(Constant.PARAM_MODULE_STATUT));

            moduleStatutString = Tools.getStringStatut(moduleStatut);

            rowDataListModel = (RowDataListModel) intent.getSerializableExtra(Constant.PARAM_MODEL);
            if (listType != Constant.LIST_MODULE_BATIMENT
                    || listType != Constant.LIST_MODULE_COMPTE_UTILISATEUR_16) {
                id = Long.valueOf(intent.getStringExtra(Constant.PARAM_MODULE_ID)).longValue();
            }
            if( listType == Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL || listType == Constant.LIST_MODULE_LOGEMENT_COLLECTIF ){
                batimentModel = (BatimentModel) rowDataListModel.getModel();
            }else if( listType == Constant.LIST_MODULE_MENAGE  || listType == Constant.LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF ){
                logementModel = (LogementModel)  rowDataListModel.getModel();
            }else if( listType == Constant.LIST_MODULE_EMIGRE || listType == Constant.LIST_MODULE_DECES || listType == Constant.LIST_MODULE_INDIVIDU_MENAGE ){
                menageModel = (MenageModel)  rowDataListModel.getModel();
            }


            // Toolbar creation
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(Html.fromHtml("<b>" + getString(R.string.app_name) + "</b>"));
            list_header_1 = (TextView) findViewById(R.id.list_header_1);
            list_header_1.setText(title);
            list_header_2 = (TextView) findViewById(R.id.list_header_2);
            list_header_2.setText(StringHeaderTwo);

            //toolbar.setLogo(R.drawable.logo_rgph);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            createSearchView(); //create the search view
            init(Constant.FORM_DATA_MANAGER);
            init(Constant.QUERY_RECORD_MANAGER);
            init(Constant.CU_RECORD_MANAGER);

            //initialize the recycle view
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            // First param is number of columns and second param is orientation i.e Vertical or Horizontal
            StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            // Attach the layout manager to the recycler view
            gridLayoutManager.setAutoMeasureEnabled(true);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
            //recyclerView.setItemAnimator(new SlideInUpAnimator());
            recyclerView.setHasFixedSize(true);
       /* recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                //Toast.makeText(getApplicationContext(), "endless scrooll page="+page+" totalItemsCount"+totalItemsCount, Toast.LENGTH_LONG).show();
                new AsynDisplayDataListTask().execute();
            }
        });*/
            //  initialize the adapter
            mAdapter = new DisplayListAdapter(DisplayListActivity.this, targetList, listType, profilId, moduleStatut );
            mAdapter.setOnMenuItemClickListener(getMenuItemListener());
            //inject the adapter into the recycle view
            recyclerView.setAdapter(mAdapter);
            //initialize the progress bar
            //initialize the progress bar
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            //load asynchronously the list of the data
            //AsynDisplayDataListTask task= new AsynDisplayDataListTask();
            //task.execute();
        } catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception:onCreate() - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }

    }

    //region "EVENTS"
    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    public void createSearchView(){
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                onTextSearchEvent(newText);
                return true;
            }


        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        //ToastUtility.LogCat("W", "onResume");
        new AsynDisplayDataListTask().execute();
        //ReloadDataListTask();

        super.onResume();
    }

    private void ReloadDataListTask() {
        try {
            if (targetList != null && targetList.size() <= 0) {
                // VERIFIKASYON KI AP FET POU LOJMAN, EMIGRE, DECES, MOUN NAN MENAJ.
                if (listType == Constant.LIST_MODULE_LOGEMENT_COLLECTIF) {
                    // POU LOJMAN KI NAN BATIMAN // SI PA GEN LOJMAN KOL NAP GADE TOU SI PAGEN LOJ END. POU NOU KA AFICHE LIST YO
                    if (batimentModel != null && batimentModel.getQb8NbreLogeIndividuel() != null && batimentModel.getQb8NbreLogeIndividuel() > 0) {
                        long NbreTotalLogeIndividuel_KiPaFini = queryRecordMngr.countLogement_ByBatiment_byTypeLog_ByStatus(batimentModel.getBatimentId(), Constant.LOJ_ENDIVIDYEL, moduleStatut);
                        if (NbreTotalLogeIndividuel_KiPaFini > 0) {
                            listType = Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL;
                            list_header_1.setText(getString(R.string.label_lojmanEndividyel) + " "+ moduleStatutString);
                            new AsynDisplayDataListTask().execute();
                        }
                    }
                } else if (listType == Constant.LIST_MODULE_EMIGRE) {//|| listType == Constant.LIST_MODULE_DECES || listType == Constant.LIST_MODULE_INDIVIDU_MENAGE ){
                    if (menageModel.getQd1Deces() != null && menageModel.getQd1Deces() == Constant.REPONS_WI_1 ){//&& menageModel.getQd1NbreDecede() != null && menageModel.getQm12NbreDecede() > 0)
                        long NbreDeces = queryRecordMngr.countDecesByMenageByStatus(menageModel.getMenageId(), moduleStatut);
                        if (NbreDeces > 0) {
                            listType = Constant.LIST_MODULE_DECES;
                            list_header_1.setText(getString(R.string.label_mounMouri) + " "+ moduleStatutString);
                            new AsynDisplayDataListTask().execute();
                        }
                    }
                } else if (listType == Constant.LIST_MODULE_DECES) {//|| listType == Constant.LIST_MODULE_INDIVIDU_MENAGE ){
                    if ( menageModel.getQm11TotalIndividuVivant() != null && menageModel.getQm11TotalIndividuVivant() > 0 ) {
                        long totalIndividuVivant = queryRecordMngr.countIndByMenageByStatus(menageModel.getMenageId(), moduleStatut);
                        if (totalIndividuVivant > 0) {
                            listType = Constant.LIST_MODULE_INDIVIDU_MENAGE;
                            list_header_1.setText(getString(R.string.label_moun) + " "+ moduleStatutString);
                            new AsynDisplayDataListTask().execute();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception: ReloadDataListTask - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }

    public boolean onTextSearchEvent(String newText) {
        if(targetList!=null && targetList.size()>0) {
            final List<RowDataListModel> filteredList = filter(targetList, newText);
            mAdapter.setFilter(filteredList);
        }
        return true;
    }

    private List<RowDataListModel> filter(List<RowDataListModel> rows, String query){
        query=query.toLowerCase();
        final List<RowDataListModel> filteredList=new ArrayList<>();
        if(rows!=null) {
            for (RowDataListModel row : rows) {
                final String text = row.getTitle().toLowerCase();
                if (text.contains(query)) {
                    filteredList.add(row);
                }
            }
        }
        return filteredList;
    }

    public DisplayListAdapter.OnMenuItemClickListener getMenuItemListener(){
        return new DisplayListAdapter.OnMenuItemClickListener() {

            @Override
            public void onMenuItemAfficher(RowDataListModel row) {
                boolean privilege = Privilege.getAccessControl(profilId, Constant.ACTION_AFFICHER, moduleStatut);
                if( privilege ){
                    goToForm(row, Constant.ACTION_AFFICHER);
                }else{
                    Tools.AlertDialogMsg(DisplayListActivity.this, "Opsyon [ Modifye ] sa pa fèt pou ou, \nWè avèk sipèvisè ou...");
                }
            }

            @Override
            public void onMenuItemModifye(RowDataListModel row) {
                boolean privilege = Privilege.getAccessControl(profilId, Constant.ACTION_MOFIDIER, moduleStatut);
                if( privilege ){
                    goToForm(row, Constant.ACTION_MOFIDIER);
                }else{
                    Tools.AlertDialogMsg(DisplayListActivity.this, "Opsyon [ Modifye ] sa pa fèt pou ou, \nWè avèk sipèvisè ou...");
                }
            }

            @Override
            public void onMenuItemKontinye(RowDataListModel row) {
                boolean privilege = Privilege.getAccessControl(profilId, Constant.ACTION_KONTINYE, moduleStatut);
                if( privilege ){
                    OptionKontinye(row, Constant.ACTION_KONTINYE);
                }else{
                    Tools.AlertDialogMsg(DisplayListActivity.this, "Opsyon [ Modifye ] sa pa fèt pou ou, \nWè avèk sipèvisè ou...");
                }
            }

            @Override
            public void onMenuItemRapport(RowDataListModel row) {
                boolean privilege = Privilege.getAccessControl(profilId, Constant.ACTION_RAPPORT, moduleStatut);
                if( privilege ){
                    //goToForm(row, Constant.ACTION_MOFIDIER);
                }else{
                    Tools.AlertDialogMsg(DisplayListActivity.this, "Li pa posib pouw gade rapport yo...");
                }
            }

            @Override
            public void onMenuItemRetounenMalRanpli(RowDataListModel row) {
                 ShowAlertDialog( row);
            }

            //@Override
            //public void onMenuItemModuleMenu(RowDataListModel row) {
            //    boolean privilege = Privilege.getAccessControl(profilId, Constant.ACTION_LISTER, moduleStatut);
            //    if( privilege ){
            //    goToMenu(row);
            //    }else{
            //        Tools.AlertDialogMsg(DisplayListActivity.this, "Opsyon sa pa fèt pou ou, \nWè avèk sipèvisè ou...");
            //    }
            //}
        };
    }

    private void ShowAlertDialog(final RowDataListModel row) {
        try {
            message = "Eske ou vle RETOUNEN \n<br />"+ row.getTitle() +"  \n<br />" +  row.getDesc();//Html.fromHtml(message))
            AlertDialog.Builder builder = new AlertDialog.Builder(DisplayListActivity.this);
            builder.setMessage(Html.fromHtml(message))
                    .setCancelable(false)
                    .setPositiveButton(""+ getString(R.string.label_Non), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
                    .setNegativeButton("" + getString(R.string.label_WI), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                short statutMod = (short) Constant.STATUT_MODULE_KI_MAL_RANPLI_2;
                                if ( listType == Constant.LIST_MODULE_BATIMENT ){
                                    batimentModel = (BatimentModel) row.getModel();
                                    if( batimentModel !=null ) {
                                        // Set Statut Batiment
                                        cuRecordMngr.updateStatutBatiment(batimentModel.getBatimentId(), statutMod, true, nomUtilisateur  );
                                    }
                                }else if ( listType == Constant.LIST_MODULE_LOGEMENT_COLLECTIF ||  listType == Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL ){
                                    logementModel = (LogementModel) row.getModel();
                                    if( logementModel !=null ) {
                                        // Set Statut Batiment
                                        cuRecordMngr.updateStatutBatiment(logementModel.getBatimentId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Logement
                                        cuRecordMngr.updateStatutLogement(logementModel.getLogeId(), statutMod, false, nomUtilisateur  );
                                    }
                                }else if ( listType == Constant.LIST_MODULE_MENAGE ){
                                    menageModel = (MenageModel) row.getModel();
                                    if( menageModel !=null ) {
                                        // Set Statut Batiment
                                        cuRecordMngr.updateStatutBatiment(menageModel.getBatimentId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Logement
                                        cuRecordMngr.updateStatutLogement(menageModel.getLogeId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Menage
                                        cuRecordMngr.updateStatutMenage(menageModel.getMenageId(), statutMod, false, nomUtilisateur  );
                                    }
                                }else if ( listType == Constant.LIST_MODULE_EMIGRE ){
                                    emigreModel = (EmigreModel) row.getModel();
                                    if( emigreModel !=null ) {
                                        // Set Statut Batiment
                                        cuRecordMngr.updateStatutBatiment(emigreModel.getBatimentId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Logement
                                        cuRecordMngr.updateStatutLogement(emigreModel.getLogeId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Menage
                                        cuRecordMngr.updateStatutMenage(emigreModel.getMenageId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Emigrer
                                        cuRecordMngr.updateStatutEmigre(emigreModel.getEmigreId(), statutMod, false, nomUtilisateur );
                                    }
                                }else if ( listType == Constant.LIST_MODULE_DECES ){
                                    decesModel = (DecesModel) row.getModel();
                                    if( decesModel !=null ) {
                                        // Set Statut Batiment
                                        cuRecordMngr.updateStatutBatiment(decesModel.getBatimentId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Logement
                                        cuRecordMngr.updateStatutLogement(decesModel.getLogeId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Menage
                                        cuRecordMngr.updateStatutMenage(decesModel.getMenageId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Deces
                                        cuRecordMngr.updateStatutDeces(decesModel.getDecesId(), statutMod, false, nomUtilisateur  );
                                    }
                                }else if ( listType == Constant.LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF ){
                                    individuModel = (IndividuModel) row.getModel();
                                    if( individuModel !=null ) {
                                        // Set Statut Batiment
                                        cuRecordMngr.updateStatutBatiment(individuModel.getBatimentId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Logement
                                        cuRecordMngr.updateStatutLogement(individuModel.getLogeId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Individu
                                        cuRecordMngr.updateStatutIndividu(individuModel.getIndividuId(), statutMod, false, nomUtilisateur  );
                                    }
                                }else if ( listType == Constant.LIST_MODULE_INDIVIDU_MENAGE ){
                                    individuModel = (IndividuModel) row.getModel();
                                    if( individuModel !=null ) {
                                        // Set Statut Batiment
                                        cuRecordMngr.updateStatutBatiment(individuModel.getBatimentId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Logement
                                        cuRecordMngr.updateStatutLogement(individuModel.getLogeId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Menage
                                        cuRecordMngr.updateStatutMenage(individuModel.getMenageId(), statutMod, true, nomUtilisateur  );
                                        // Set Statut Individu
                                        cuRecordMngr.updateStatutIndividu(individuModel.getIndividuId(), statutMod, false, nomUtilisateur  );
                                    }
                                }
                                new AsynDisplayDataListTask().execute();
                            } catch (Exception ex) {
                                ToastUtility.LogCat( "Exception: ShowAlertDialog : setNegativeButton - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
                                ex.printStackTrace();
                            }

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception:ShowAlertDialog() - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }
    //endregion

    //region "OPTIONS METHODES"
    public void goToForm(RowDataListModel row, int typeEvenement){
        try {
            ModuleModel moduleModel = null;
            QuestionnaireFormulaireUtility QF = null;
            short numOrdre =1;
            //String dateString = Tools.getDateString_MMddyyyy_HHmmss_a();
            if(listType == Constant.LIST_MODULE_BATIMENT){
                moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_BATIMENT, Constant.ACTIF);
                if( row != null && moduleModel != null ){
                    BatimentModel batimentModel = (BatimentModel) row.getModel();
                    QF = new QuestionnaireFormulaireUtility( moduleModel, batimentModel, Constant.FORMULAIRE_BATIMENT, formDataMngr);
                    QF.TypeEvenement = typeEvenement;

                    if(batimentModel!=null && batimentModel.getDateDebutCollecte()!=null && !batimentModel.getDateDebutCollecte().equalsIgnoreCase("") ) {
                        QF.setDateDebutCollecte(batimentModel.getDateDebutCollecte());
                    }else{
                        QF.setDateDebutCollecte(dateString);
                    }

                    intent = new Intent(this, QuestionnaireBatimentActivity.class);
                    intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QF);
                    intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, "MODIFYE BATIMAN " + batimentModel.getBatimentId());
                    intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, "SDE: " + batimentModel.getSdeId());
                    startActivity(intent);
                }
            }else if(listType == Constant.LIST_MODULE_LOGEMENT_COLLECTIF){
                moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_LOGEMENT_COLLECTIF, Constant.ACTIF);
                if( row != null && moduleModel != null ){
                    LogementModel logementModel = (LogementModel) row.getModel();
                    BatimentModel batimentModel = queryRecordMngr.getBatById(logementModel.getBatimentId());
                    logementModel.setObjBatiment(batimentModel);
                    numOrdre += (int)logementModel.getQlin1NumeroOrdre();
                    QuestionnaireBatimentActivity.CounterForLogeCollectif = numOrdre;//

                    QF = new QuestionnaireFormulaireUtility( moduleModel, logementModel, Constant.FORMULAIRE_LOGEMENT_COLLECTIF, formDataMngr);
                    QF.TypeEvenement = typeEvenement;
                    if(logementModel!=null && logementModel.getDateDebutCollecte()!=null && !logementModel.getDateDebutCollecte().equalsIgnoreCase("") ) {
                       QF.setDateDebutCollecte(logementModel.getDateDebutCollecte());
                    }else{
                        QF.setDateDebutCollecte(dateString);
                    }
                    intent = new Intent(this, QuestionnaireLogementActivity.class);
                    intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QF);
                    intent.putExtra(Constant.PARAM_NUMERO_ORDRE_LOG_INDIVIDUEL, "" + numOrdre );
                    intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, "MODIFYE LOJMAN KOLEKTIF " + logementModel.getQlin1NumeroOrdre() );
                    intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, "Batiman " + batimentModel.getBatimentId() + " | REC: " +batimentModel.getQrec());
                    startActivity(intent);
                }
            }else if(listType == Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL){
                moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL, Constant.ACTIF);
                if( row != null && moduleModel != null ){
                    LogementModel logementModel = (LogementModel) row.getModel();
                    BatimentModel batimentModel = queryRecordMngr.getBatById(logementModel.getBatimentId());
                    logementModel.setObjBatiment(batimentModel);
                    numOrdre += (int)logementModel.getQlin1NumeroOrdre();
                    QuestionnaireBatimentActivity.CounterForLogeEndividyel = numOrdre;

                    QF = new QuestionnaireFormulaireUtility( moduleModel, logementModel, Constant.FORMULAIRE_LOGEMENT_INDIVIDUEL, formDataMngr);
                    QF.TypeEvenement = typeEvenement;
                    if(logementModel!=null && logementModel.getDateDebutCollecte()!=null && !logementModel.getDateDebutCollecte().equalsIgnoreCase("") ) {
                        QF.setDateDebutCollecte(logementModel.getDateDebutCollecte());
                    }else{
                        QF.setDateDebutCollecte(dateString);
                    }
                    intent = new Intent(this, QuestionnaireLogementActivity.class);
                    intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QF);
                    intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, "MODIFYE LOJMAN ENDIVIDYEL " + logementModel.getQlin1NumeroOrdre() );
                    intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, "Batiman " + batimentModel.getBatimentId() + " | REC: " +batimentModel.getQrec() );
                    startActivity(intent);
                }
            }else if(listType == Constant.LIST_MODULE_MENAGE){
                moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_MENAGE, Constant.ACTIF);
                if( row != null && moduleModel != null ){
                    MenageModel menageModel = (MenageModel) row.getModel();
                    BatimentModel batimentModel = queryRecordMngr.getBatById(menageModel.getBatimentId());
                    LogementModel logementModel = queryRecordMngr.getLogById(menageModel.getLogeId());
                    menageModel.setObjBatiment(batimentModel);
                    menageModel.setObjLogement(logementModel);
                    QF = new QuestionnaireFormulaireUtility( moduleModel, menageModel, Constant.FORMULAIRE_MENAGE, formDataMngr);
                    QF.TypeEvenement = typeEvenement;
                    if(menageModel!=null && menageModel.getDateDebutCollecte()!=null && !menageModel.getDateDebutCollecte().equalsIgnoreCase("") ) {
                        QF.setDateDebutCollecte(menageModel.getDateDebutCollecte());
                    }else{
                        QF.setDateDebutCollecte(dateString);
                    }
                    numOrdre += (int)menageModel.getQm1NoOrdre();
                    QuestionnaireLogementActivity.CounterForMenage_LogInd = numOrdre;

                    intent = new Intent(this, QuestionnaireMenageActivity.class);
                    intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QF);
                    intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, "MODIFYE MENAJ " + menageModel.getQm1NoOrdre());
                    intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, " Lojman Endividèl " +  logementModel.getQlin1NumeroOrdre()
                            + " | Batiman " +  batimentModel.getBatimentId() + " | REC: " +  batimentModel.getQrec()  );
                    startActivity(intent);
                }
            }else if(listType == Constant.LIST_MODULE_EMIGRE){
                moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_EMIGRE, Constant.ACTIF);
                if( row != null && moduleModel != null ){
                    EmigreModel emigreModel = (EmigreModel) row.getModel();
                    BatimentModel batimentModel = queryRecordMngr.getBatById(emigreModel.getBatimentId());
                    LogementModel logementModel = queryRecordMngr.getLogById(emigreModel.getLogeId());
                    MenageModel menageModel = queryRecordMngr.getMenageById(emigreModel.getMenageId());
                    emigreModel.setObjBatiment(batimentModel);
                    emigreModel.setObjLogement(logementModel);
                    emigreModel.setObjMenage(menageModel);

                    QF = new QuestionnaireFormulaireUtility( moduleModel, emigreModel, Constant.FORMULAIRE_EMIGRE, formDataMngr);
                    QF.TypeEvenement = typeEvenement;
                    if(emigreModel!=null && emigreModel.getDateDebutCollecte()!=null && !emigreModel.getDateDebutCollecte().equalsIgnoreCase("") ) {
                        QF.setDateDebutCollecte(emigreModel.getDateDebutCollecte());
                    }else{
                        QF.setDateDebutCollecte(dateString);
                    }

                    intent = new Intent(this, QuestionnaireEmigreActivity.class);
                    intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QF);

                    intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, "MODIFYE EMIGRE " + emigreModel.getQn1numeroOrdre());
                    intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, " Menaj " +  menageModel.getQm1NoOrdre()
                            + " | Lojman Endividyèl " +  logementModel.getQlin1NumeroOrdre()
                            + " | Batiman " +  batimentModel.getBatimentId());
                    startActivity(intent);
                }
            }else if(listType == Constant.LIST_MODULE_DECES){
                moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_DECES, Constant.ACTIF);
                if( row != null && moduleModel != null ){
                    DecesModel decesModel = (DecesModel) row.getModel();
                    BatimentModel batimentModel = queryRecordMngr.getBatById(decesModel.getBatimentId());
                    LogementModel logementModel = queryRecordMngr.getLogById(decesModel.getLogeId());
                    MenageModel menageModel = queryRecordMngr.getMenageById(decesModel.getMenageId());
                    decesModel.setObjBatiment(batimentModel);
                    decesModel.setObjLogement(logementModel);
                    decesModel.setObjMenage(menageModel);
                    QF = new QuestionnaireFormulaireUtility( moduleModel, decesModel, Constant.FORMULAIRE_DECES, formDataMngr);
                    QF.TypeEvenement = typeEvenement;
                    if(decesModel!=null && decesModel.getDateDebutCollecte()!=null && !decesModel.getDateDebutCollecte().equalsIgnoreCase("") ) {
                        QF.setDateDebutCollecte(decesModel.getDateDebutCollecte());
                    }else{
                        QF.setDateDebutCollecte(dateString);
                    }

                    intent = new Intent(this, QuestionnaireDecesActivity.class);
                    intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QF);
                    intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, "MODIFYE MOUN MOURI " + decesModel.getQd2NoOrdre());
                    intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, " Menaj " +  menageModel.getQm1NoOrdre()
                            + " | Lojman Endividyèl " +  logementModel.getQlin1NumeroOrdre()
                            + " | Batiman " +  batimentModel.getBatimentId());
                    startActivity(intent);
                }
            }else if( listType == Constant.LIST_MODULE_INDIVIDU_MENAGE ||
                    listType == Constant.LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF ){

                moduleModel = formDataMngr.checkModule(Constant.FORMULAIRE_INDIVIDUS, Constant.ACTIF);
                if( row != null && moduleModel != null ){
                    IndividuModel individuModel = (IndividuModel) row.getModel();
                    BatimentModel batimentModel = queryRecordMngr.getBatById(individuModel.getBatimentId());
                    LogementModel logementModel = queryRecordMngr.getLogById(individuModel.getLogeId());
                    MenageModel menageModel = null;//queryRecordMngr.getMenageById(individuModel.getMenageId());
                    if( logementModel != null && logementModel.getQlCategLogement() != null && logementModel.getQlCategLogement() == Constant.LOJ_ENDIVIDYEL ) {
                        if( individuModel.getMenageId() != null && individuModel.getMenageId() > 0 ) {
                            menageModel = queryRecordMngr.getMenageById(individuModel.getMenageId());
                            individuModel.setMenageId(individuModel.getMenageId());
                        }
                    }else{
                        individuModel.setMenageId((long)0);
                        individuModel.setObjMenage(menageModel);
                    }
                    individuModel.setObjBatiment(batimentModel);
                    individuModel.setObjLogement(logementModel);
                    individuModel.setObjMenage(menageModel);
                    QF = new QuestionnaireFormulaireUtility( moduleModel, individuModel, Constant.FORMULAIRE_INDIVIDUS, formDataMngr);
                    QF.TypeEvenement = typeEvenement;
                    if(individuModel!=null && individuModel.getDateDebutCollecte()!=null && !individuModel.getDateDebutCollecte().equalsIgnoreCase("") ) {
                        QF.setDateDebutCollecte(individuModel.getDateDebutCollecte());
                    }else{
                        QF.setDateDebutCollecte(dateString);
                    }

                    intent = new Intent(this, QuestionnaireIndividuActivity.class);
                    intent.putExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE, QF);
                    intent.putExtra(Constant.PARAM_FORM_HEADER_ONE, "MODIFYE MOUN " + individuModel.getQ1NoOrdre());
                    if( logementModel != null && logementModel.getQlCategLogement() != null && logementModel.getQlCategLogement() == Constant.LOJ_ENDIVIDYEL ) {
                        intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, "Menaj " + menageModel.getQm1NoOrdre()
                                + " | Loj Endividèl " + logementModel.getQlin1NumeroOrdre()
                                + " | Batiman " + batimentModel.getBatimentId());
                    }else if( logementModel != null && logementModel.getQlCategLogement() != null && logementModel.getQlCategLogement() == Constant.LOJ_KOLEKTIF ) {
                        intent.putExtra(Constant.PARAM_FORM_HEADER_TWO, "Loj Kolektif " + logementModel.getQlin1NumeroOrdre()
                                + " | Batiman " + batimentModel.getBatimentId() + " | RGPH: " + batimentModel.getQrgph());
                    }
                    startActivity(intent);
                }
            }else if( listType == Constant.LIST_MODULE_COMPTE_UTILISATEUR_16){
                intent = new Intent(this, FormulaireUtilisateur.class);
                intent.putExtra(Constant.PARAM_MODEL, row);
                intent.putExtra(Constant.PARAM_ID, row.getId());
                startActivity(intent);
            }else if( listType == Constant.LIST_MODULE_BATIMANT_POUR_RAPPORT_17){
                if( row != null ) {
                    BatimentModel batimentModel = (BatimentModel) row.getModel();

                    String InfoHeader = "Batiman " + batimentModel.getBatimentId();
                    showListView(getString(R.string.label_Rapo_Sou_Batiman_Yo) + " "+ moduleStatutString, Constant.LIST_MODULE_RAPPORT_PAR_BATIMANT_18, moduleStatut, batimentModel.getBatimentId(), InfoHeader, row );
                }
            }else if( listType == Constant.LIST_MODULE_RAPPORT_PAR_BATIMANT_18){
                if( row != null ) {
                    //BatimentModel batimentModel = (BatimentModel) row.getModel();

                    //String InfoHeader = "Rapò Batiman-" + batimentModel.getBatimentId();
                    ToastUtility.LogCat(this, "LIST_MODULE_RAPPORT_PAR_BATIMANT_18");


                }
            }
        }catch (ManagerException e) {
            ToastUtility.LogCat(this, "ManagerException:goToForm() - getMessage:" + e.getMessage() + " / toString:" + e.toString());
            e.printStackTrace();
        }catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception:goToForm() - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }

    public void OptionKontinye(RowDataListModel row, int typeEvenement) {
        try {
            if (listType == Constant.LIST_MODULE_BATIMENT) {
               GetFormBatiment_Or_ListeLogment(row);
            }else  if (listType == Constant.LIST_MODULE_LOGEMENT_COLLECTIF) {
                GetFormLogementCollectif_Or_ListeIndividu(row);
            }else  if (listType == Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL) {
                GetFormLogementIndividuel_Or_ListeMenage(row);
            }else  if (listType == Constant.LIST_MODULE_MENAGE) {
                GetFormMenage_OrListeEmigrer_OrListeDeces_OrListeIndividu(row);
            }else  if (listType == Constant.LIST_MODULE_EMIGRE) {

            }else  if (listType == Constant.LIST_MODULE_DECES) {

            }else  if (listType == Constant.LIST_MODULE_INDIVIDU_MENAGE) {

            }else  if (listType == Constant.LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF) {

            }
        } catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception:OptionKontinye - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }
    //endregion

    //region "METHODES GET FORM OU LISTE"
    private void GetFormMenage_OrListeEmigrer_OrListeDeces_OrListeIndividu(RowDataListModel row) {
        try {
            MenageModel menModel = (MenageModel) row.getModel();
            //int statutFormulaire = moduleStatut;
            boolean IsShowEmigrer_KiPaFini = false;
            boolean IsShowDeces_KiPaFini = false;
            boolean IsShowIndividu_KiPaFini = false;
            long NbreEmigre = 0;
            long NbreDeces = 0;
            long TotalIndividuVivant = 0;
            if ( menModel != null ) {
                // Verification si le menage est remplit totalement
                if ( menModel.getIsFieldAllFilled() != null && !menModel.getIsFieldAllFilled() ) {
                    // Si tous les champs du menage ne sont pas totalement remplit
                    goToForm( row, Constant.ACTION_MOFIDIER );
                } else { // Sinon si tous les champs du Menage sont totalement remplit
                    if( menModel.getQn1Emigration() != null && menModel.getQn1Emigration() == Constant.REPONS_WI_1 ){// && menModel.getQm11NbreEmigre() != null && menModel.getQm11NbreEmigre() > 0 ){
                        NbreEmigre = queryRecordMngr.countEmigrerByMenageByStatus( menModel.getMenageId(), moduleStatut );
                        if ( NbreEmigre > 0) {
                            IsShowEmigrer_KiPaFini = true;
                        }
                    }
                    if( menModel.getQd1Deces() != null && menModel.getQd1Deces() == Constant.REPONS_WI_1 ){// && menModel.getQm12NbreDecede() != null &&  menModel.getQm12NbreDecede() > 0 ) {
                        NbreDeces = queryRecordMngr.countDecesByMenageByStatus( menModel.getMenageId(), moduleStatut );
                        if ( NbreDeces > 0) {
                            IsShowDeces_KiPaFini = true;
                        }
                    }
                    if( menModel.getQm11TotalIndividuVivant() != null &&  menModel.getQm11TotalIndividuVivant() > 0 ){
                        TotalIndividuVivant = queryRecordMngr.countIndByMenageByStatus( menModel.getMenageId(), moduleStatut );
                        if ( TotalIndividuVivant > 0) {
                            IsShowIndividu_KiPaFini = true;
                        }
                    }

                    if( IsShowEmigrer_KiPaFini ) {
                        BatimentModel batimentModel = queryRecordMngr.getBatById(menModel.getBatimentId());
                        menModel.setObjBatiment(batimentModel);
                        LogementModel logementModel = queryRecordMngr.getLogById(menModel.getLogeId());
                        menModel.setObjLogement(logementModel);

                        String InfoHeader =" Menaj " +  menModel.getQm1NoOrdre() + " | Lojman End. " +  logementModel.getQlin1NumeroOrdre()
                                + " | " + "Batiman-" + menModel.getBatimentId()+ " | REC: " + batimentModel.getQrec();

                        showListView(getString(R.string.label_emigre) + " "+ moduleStatutString, Constant.LIST_MODULE_EMIGRE,
                                moduleStatut, menModel.getMenageId(), InfoHeader, row );

                    }else if( IsShowDeces_KiPaFini ) {
                        BatimentModel batimentModel = queryRecordMngr.getBatById(menModel.getBatimentId());
                        menModel.setObjBatiment(batimentModel);
                        LogementModel logementModel = queryRecordMngr.getLogById(menModel.getLogeId());
                        menModel.setObjLogement(logementModel);

                        String InfoHeader =" Menaj " +  menModel.getQm1NoOrdre() + " | Lojman End. " +  logementModel.getQlin1NumeroOrdre()
                                + " | " + "Batiman-" + menModel.getBatimentId()+ " | REC: " + batimentModel.getQrec();

                        showListView(getString(R.string.label_mounMouri) + " "+ moduleStatutString, Constant.LIST_MODULE_DECES,
                                moduleStatut, menModel.getMenageId(), InfoHeader, row  );

                    }else if( IsShowIndividu_KiPaFini ) {
                        BatimentModel batimentModel = queryRecordMngr.getBatById(menModel.getBatimentId());
                        menModel.setObjBatiment(batimentModel);
                        LogementModel logementModel = queryRecordMngr.getLogById(menModel.getLogeId());
                        menModel.setObjLogement(logementModel);

                        String InfoHeader =" Menaj " +  menModel.getQm1NoOrdre() + " | Lojman End. " +  logementModel.getQlin1NumeroOrdre()
                                + " | " + "Batiman-" + menModel.getBatimentId()+ " | REC: " + batimentModel.getQrec();

                        showListView(getString(R.string.label_moun) + " "+ moduleStatutString, Constant.LIST_MODULE_INDIVIDU_MENAGE,
                                moduleStatut, menModel.getMenageId(), InfoHeader, row  );

                    }else{
                        // SI tous les champs du Batiment sont bien remplit
                        if (menModel.getIsFieldAllFilled() != null && menModel.getIsFieldAllFilled()) {
                            menModel.setStatut((short) Constant.STATUT_MODULE_KI_FINI_1);
                            menModel = cuRecordMngr.SaveMenage(menModel.getMenageId(), menModel, Constant.ACTION_MOFIDIER, nomUtilisateur);
                            ToastUtility.ToastMessage(this, "Menaj " + menModel.getQm1NoOrdre() + " sa Fini deja.", Constant.GravityCenter);
                            //ToastUtility.LogCat(this, "AFTER Save Menage ID:" + menModel.getMenageId());
                            Tools.AlertDialogMsg(this, "Menaj " + menModel.getQm1NoOrdre() + " sa fini deja.");

                            new AsynDisplayDataListTask().execute();
                            ReloadDataListTask();
                        }
                    }
                }
            }
        }catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception:GetFormMenage_OrListeEmigrer_OrListeDeces_OrListeIndividu - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }

    private void GetFormLogementIndividuel_Or_ListeMenage(RowDataListModel row) {
        try {
            LogementModel logModel = (LogementModel) row.getModel();
            //int statutFormulaire = moduleStatut;
            boolean IsShowMenage_KiPaFini = false;
            if (logModel != null) {
                // Verification si le logement est remplit totalement
                if (logModel.getIsFieldAllFilled() != null && !logModel.getIsFieldAllFilled()) {
                    // Si tous les champs du logement ne sont pas totalement remplit
                    goToForm(row, Constant.ACTION_MOFIDIER);
                } else { // Sinon si tous les champs du logement sont totalement remplit
                    if (logModel.getQlCategLogement() != null && logModel.getQlCategLogement() == Constant.LOJ_ENDIVIDYEL) {
                        // Si c'est un logement Individuel ou aura la liste des Menages si possible
                        long getQlin13NbreTotalMenage = 0;
                        if (logModel.getQlin8NbreIndividuDepense() != null && logModel.getQlin8NbreIndividuDepense() == Constant.REPONS_WI_1) {
                            if (logModel.getQlin9NbreTotalMenage() != null) {
                                getQlin13NbreTotalMenage = logModel.getQlin9NbreTotalMenage();
                            }
                        } else {
                            getQlin13NbreTotalMenage = 1;
                        }
                        if (getQlin13NbreTotalMenage > 0) {
                            long NbreTotalMenage = queryRecordMngr.countMenageByStatusAndLog(moduleStatut, logModel.getLogeId());
                            if (NbreTotalMenage > 0) {
                                IsShowMenage_KiPaFini = true;
                            }
                        }

                        if (IsShowMenage_KiPaFini) {
                            BatimentModel batimentModel = queryRecordMngr.getBatById(logModel.getBatimentId());
                            logModel.setObjBatiment(batimentModel);
                            String InfoHeader = " Lojman Endividèl " + logModel.getQlin1NumeroOrdre() + " | " + "Batiman-" + batimentModel.getBatimentId() + " | REC: " + batimentModel.getQrec();

                            showListView(getString(R.string.label_menaj) + " " + moduleStatutString, Constant.LIST_MODULE_MENAGE,
                                    moduleStatut, logModel.getLogeId(), InfoHeader, row);
                        } else {
                            // SI tous les champs du logement sont bien remplit
                            if (logModel.getIsFieldAllFilled() != null && logModel.getIsFieldAllFilled()) {
                                if (logModel.getQlin2StatutOccupation() != null && logModel.getQlin2StatutOccupation() != Constant.LOJMAN_OKIPE_TOUTAN_MEN_MOUN_YO_PA_LA_2) {
                                    logModel.setStatut((short) Constant.STATUT_MODULE_KI_FINI_1);
                                    logModel = cuRecordMngr.SaveLogement(logModel.getLogeId(), logModel, Constant.ACTION_MOFIDIER, nomUtilisateur);
                                    ToastUtility.LogCat(this, "AFTER Save Logement ID:" + logModel.getLogeId());
                                    Tools.AlertDialogMsg(this, "Lojman Endividyèl " + logModel.getQlin1NumeroOrdre() + " sa fini deja.");

                                    new AsynDisplayDataListTask().execute();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception:GetFormLogementIndividuel_Or_ListeMenage - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }

    private void GetFormLogementCollectif_Or_ListeIndividu(RowDataListModel row) {
        try {
            LogementModel logModel = (LogementModel) row.getModel();
            int statutFormulaire = moduleStatut;
            boolean IsShowIndividus_KiPaFini = false;
            boolean IsShowMenage_KiPaFini = false;
            if (logModel != null) {
                // Verification si le logement est remplit totalement
                if (logModel.getIsFieldAllFilled() != null && !logModel.getIsFieldAllFilled()) {
                    // Si tous les champs du logement ne sont pas totalement remplit
                    goToForm(row, Constant.ACTION_MOFIDIER);
                } else { // Sinon si tous les champs du logement sont totalement remplit
                    // Liste Individu "KI PA FINI"
                    if ( logModel.getQlCategLogement() != null && logModel.getQlCategLogement() == Constant.LOJ_KOLEKTIF ) {
                        if ( logModel.getQlc1TypeLogement() <= Constant.LCOL_Lazil_oswa_kote_granmoun_rete_oswa_kay_retrèt_6) {
                            if (logModel.getQlcTotalIndividus() != null && logModel.getQlcTotalIndividus() > 0) {
                                long TotalIndividus = queryRecordMngr.countIndByLogByStatus(logModel.getLogeId(), statutFormulaire);
                                if (TotalIndividus > 0) {
                                    IsShowIndividus_KiPaFini = true;
                                }
                            }
                        }

                        if (IsShowIndividus_KiPaFini) {
                            BatimentModel batimentModel = queryRecordMngr.getBatById(logModel.getBatimentId());
                            logModel.setObjBatiment(batimentModel);
                            String InfoHeader = "Lojman Kolektif " + logModel.getQlin1NumeroOrdre() + " | Batiman-" + batimentModel.getBatimentId()+ " | Rec: " + batimentModel.getQrec();

                            showListView(getString(R.string.label_moun) + " "+ moduleStatutString,
                                    Constant.LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF, moduleStatut, logModel.getLogeId(), InfoHeader, row);
                        }else{
                            // SI tous les champs du logement sont bien remplit
                            if ( logModel.getIsFieldAllFilled() != null && logModel.getIsFieldAllFilled() ) {

                                logModel.setStatut((short) Constant.STATUT_MODULE_KI_FINI_1);
                                logModel = cuRecordMngr.SaveLogement(logModel.getLogeId(), logModel, Constant.ACTION_MOFIDIER, nomUtilisateur);
                                ToastUtility.LogCat(this, "AFTER Save Logement ID:" + logModel.getLogeId());
                                Tools.AlertDialogMsg(this, "Lojman Kolektif " + logModel.getQlin1NumeroOrdre() + " sa fini deja.");

                                new AsynDisplayDataListTask().execute();
                            }
                        }

                    }
                }
            }
        }catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception:GetFormLogementCollectif_Or_ListeIndividu - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }

    private void GetFormBatiment_Or_ListeLogment(RowDataListModel row) {
        try {
            BatimentModel batModel = (BatimentModel) row.getModel();
            //int statutFormulaire = moduleStatut;
            boolean IsShowLogeCollectif_KiPaFini = false;
            boolean IsShowLogeIndividuel_KiPaFini = false;

            if (batModel != null) {
                String InfoHeader = "" + "Batiman " + batModel.getBatimentId() + " | RGPH: " + batModel.getQrgph();
                // Verification si le batiment est remplit totalement
                if (batModel.getIsFieldAllFilled() != null && !batModel.getIsFieldAllFilled()) {
                    // Si tous les champs du Batiment ne sont pas totalement remplit
                    goToForm(row, Constant.ACTION_MOFIDIER);
                } else { // Sinon si tous les champs du Batiment sont totalement remplit
                    // Liste Logement Collectif "KI PA FINI"
                    if (batModel.getQb8NbreLogeCollectif() != null && batModel.getQb8NbreLogeCollectif() > 0) {
                        long NbreTotalLogeCollectif_KiPaFini = queryRecordMngr.countLogement_ByBatiment_byTypeLog_ByStatus(batModel.getBatimentId(), Constant.LOJ_KOLEKTIF, moduleStatut);
                        if (NbreTotalLogeCollectif_KiPaFini > 0) {
                            IsShowLogeCollectif_KiPaFini = true;
                        }
                    }
                    if (batModel.getQb8NbreLogeIndividuel() != null && batModel.getQb8NbreLogeIndividuel() > 0) {
                        // Liste Logement Individuel "KI PA FINI"
                        long NbreTotalLogeIndividuel_KiPaFini = queryRecordMngr.countLogement_ByBatiment_byTypeLog_ByStatus(batModel.getBatimentId(), Constant.LOJ_ENDIVIDYEL, moduleStatut);
                        if (NbreTotalLogeIndividuel_KiPaFini > 0) {
                            IsShowLogeIndividuel_KiPaFini = true;
                        }
                    }

                    if (IsShowLogeCollectif_KiPaFini) {

                        showListView(getString(R.string.label_lojmanKolektif) + " "+ moduleStatutString,
                                Constant.LIST_MODULE_LOGEMENT_COLLECTIF,
                                moduleStatut,
                                batModel.getBatimentId(), InfoHeader, row);

                    } else if (IsShowLogeIndividuel_KiPaFini) {
                        showListView(getString(R.string.label_lojmanEndividyel) + " "+ moduleStatutString,
                                Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL,
                                moduleStatut,
                                batModel.getBatimentId(), InfoHeader, row);
                    } else {
                        // SI tous les champs du Batiment sont bien remplit
                        if (batModel.getIsFieldAllFilled() != null && batModel.getIsFieldAllFilled()) {
                            if (batModel.getQb1Etat() != null && batModel.getQb1Etat() != Constant.BATIMAN_Pa_konnen_paske_li_pa_sou_je_5) {
                                batModel.setStatut((short) Constant.STATUT_MODULE_KI_FINI_1);
                                batModel = cuRecordMngr.SaveBatiment(batModel.getBatimentId(), batModel, Constant.ACTION_MOFIDIER, nomUtilisateur);
                                ToastUtility.LogCat(this, "AFTER Save Batiment ID:" + batModel.getBatimentId());
                                Tools.AlertDialogMsg(this, "Batiman " + batModel.getBatimentId() + " sa Fini deja.");

                                // On fait appel au Rapport de l'agent recenseur
                                QuestionnaireFormulaireUtility QF = new QuestionnaireFormulaireUtility();
                                QF.setTbl_TableName( Constant.FORMULAIRE_BATIMENT );
                                QF.setBatimentModel( batModel );
                                QF.setNomChamps( BatimentDao.Properties.Qb1Etat.columnName );

                                message = "" + "<b>Rapò sou </b>";
                                if( batModel.getBatimentId() != null && batModel.getQrec() != null ) {
                                    message += "" + "<b> Batiman " + batModel.getBatimentId() + " </b>";
                                }
                                //this.ShowRapport_RAR(batModel.getStatut(), QF, message);

                                new AsynDisplayDataListTask().execute();
                            }
                        }
                    }
                }
            }
        }catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception:GetFormBatiment_Or_ListeLogment - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }
    //endregion

    //region PopUp ShowRapport_RAR
    public void ShowRapport_RAR(int statut, final QuestionnaireFormulaireUtility QF, String title) {
        try {
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.rapport_rar);
            dialog.setCancelable(false);
            ScrollView scrollView2 = (ScrollView) dialog.findViewById(R.id.scrollView2);
            //QuestionnaireFormulaireUtility QF = new QuestionnaireFormulaireUtility();

            //TextView tv_GrandTitre2 = (TextView) dialog.findViewById(R.id.tv_GrandTitre);
            //tv_GrandTitre2.setVisibility(View.GONE);

            sp_Rezon = (Spinner) dialog.findViewById(R.id.sp_Rezon);

            LL_LotRezon = (LinearLayout) dialog.findViewById(R.id.LL_LotRezon);
            tv_LotRezon = (TextView) dialog.findViewById(R.id.tv_LotRezon);
            et_LotRezon = (EditText) dialog.findViewById(R.id.et_LotRezon);

            dialog.setTitle("" + this.getString(R.string.Rappot_Agent_Resenceur) );

            Shared_Preferences sharedPreferences = null;
            sharedPreferences = Tools.SharedPreferences(this);
            if ( sharedPreferences != null ){
                //if (Constant.FORMULAIRE_BATIMENT == QF.getTbl_TableName()){}
            }

            TextView tv_GrandTitreRap = (TextView) dialog.findViewById(R.id.tv_GrandTitre);
            tv_GrandTitreRap.setText(Html.fromHtml("" + title));

            //region sp_Rezon
            QF.Load_RaisonRAR(this, QF, sp_Rezon, statut);

            sp_Rezon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try{
                        // TEST POUR LE CHARGEMENT DES COMMUNES
                        KeyValueModel keyValueRezon = null;
                        if (parent.getId() == R.id.sp_Rezon) {
                            keyValueRezon = ((KeyValueModel) sp_Rezon.getSelectedItem());
                        }
                        LL_LotRezon.setVisibility(View.GONE);
                        if (keyValueRezon != null){
                            if( !keyValueRezon.getKey().trim().equalsIgnoreCase("")) {
                                if (parent.getId() == R.id.sp_Rezon) {
                                    if( keyValueRezon.getKey().equalsIgnoreCase(""+ Constant.PRECISEZ_10) ||
                                            keyValueRezon.getKey().equalsIgnoreCase(""+ Constant.PRECISEZ_14) ||
                                            keyValueRezon.getKey().equalsIgnoreCase(""+ Constant.PRECISEZ_19) ||
                                            keyValueRezon.getKey().equalsIgnoreCase(""+ Constant.PRECISEZ_23) ){
                                        LL_LotRezon.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        }
                    }catch (Exception ex)        {
                        ToastUtility.LogCat("Exception :-: sp_Rezon:onItemSelected(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
                        ex.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //endregion

            //region Buttons btnQuitter
            Button btnQuitter = (Button) dialog.findViewById(R.id.btnQuitter);
            btnQuitter.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    // On va a la question suivante
                    //Precedent_Click(QF);
                }
            });
            //endregion

            //region Buttons btnContinuer
            Button btnContinuer = (Button) dialog.findViewById(R.id.btnContinuer);
            btnContinuer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Shared_Preferences sharedPreferences = Tools.SharedPreferences(DisplayListActivity.this);
                        QF.CheckValueBefore_RapportRAR( sp_Rezon, et_LotRezon, sharedPreferences);

                        //QF.SaveInfoDefinitivement(cuRecordMngr, false);
                        if (dialog != null) {
                            dialog.dismiss();
                        }

                        new AsynDisplayDataListTask().execute();//finish();//Suivant_Click();
                    }catch (TextEmptyException ex) {
                        Tools.AlertDialogMsg( DisplayListActivity.this, ex.getMessage());
                        ToastUtility.LogCat("TextEmptyException: ShowRapport_RAR() :" + ex.getMessage());
                    }catch (Exception ex) {
                        ToastUtility.LogCat("Exception: ShowRapport_RAR() :" + ex.getMessage() + " / toString: " + ex.toString());
                        ex.printStackTrace();
                    }
                }
            });
            //endregion

            dialog.show();
        } catch (Exception ex) {
            String message = "Erreur :";
            ToastUtility.LogCat("Exception: ShowRapport_RAR :" + message +" / " + ex.toString());
            //Tools.AlertDialogMsg(context, message +"\n"+ ex.getMessage());
            ex.printStackTrace();
        }
    }
    // endregion

    //region "Asyn Display Data List Task"
    public class AsynDisplayDataListTask extends AsyncTask<String, Void, Integer>{
        List<RowDataListModel> rowDataList = null;
        Shared_Preferences SPref = Tools.SharedPreferences(DisplayListActivity.this);

        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            try {
                if( targetList != null ) {
                    targetList.clear();
                }
                if ( listType == Constant.LIST_MODULE_BATIMENT ) {
                    //targetList.addAll(FakeData.prepareBatimentMalRempliData());
                    rowDataList = queryRecordMngr.searchListBatByStatus(moduleStatut);
                    message = "Pa gen Batiman nan sistèm nan.";
                } else if (listType == Constant.LIST_MODULE_LOGEMENT_COLLECTIF) {
                    //targetList.addAll(FakeData.prepareLogementCollectif());
                    rowDataList = queryRecordMngr.searchListLogByTypeAndBatAndStat(Constant.LOJ_KOLEKTIF, id, moduleStatut);
                    message = "Pa gen Lojman kolektif nan sistèm nan.";
                } else if (listType == Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL) {
                    //targetList.addAll(FakeData.prepareLogementInd());
                    rowDataList = queryRecordMngr.searchListLogByTypeAndBatAndStat(Constant.LOJ_ENDIVIDYEL, id, moduleStatut);
                    message = "Pa gen Lojman endividyèl nan sistèm nan.";
                } else if (listType == Constant.LIST_MODULE_MENAGE) {
                    //targetList.addAll(FakeData.prepareMenage());
                    rowDataList = queryRecordMngr.searchListMenageByLog(id, moduleStatut);
                    message = "Pa gen menaj nan sistèm nan.";
                } else if (listType == Constant.LIST_MODULE_EMIGRE) {
                    //targetList.addAll(FakeData.prepareEmigre());
                    rowDataList = queryRecordMngr.searchListEmigreByMenage(id, moduleStatut);
                    message = "Pa gen emigre nan sistèm nan.";
                } else if (listType == Constant.LIST_MODULE_DECES) {
                    //targetList.addAll(FakeData.prepareDeces());
                    rowDataList = queryRecordMngr.searchListDecesByMenage(id, moduleStatut);
                    message = "Pa gen Moun mouri nan sistèm nan.";
                } else if (listType == Constant.LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF) {
                    //targetList.addAll(FakeData.prepareIndividu());
                    rowDataList = queryRecordMngr.searchListIndByStaAndLog(moduleStatut, id);
                    message = "Pa gen moun pou lojman kolektif sa nan sistèm nan .";
                }else if(listType == Constant.LIST_MODULE_INDIVIDU_MENAGE){
                    //targetList.addAll(FakeData.prepareIndividu());
                    rowDataList = queryRecordMngr.searchListIndByMenageAndStatut(moduleStatut,id);
                    message = "Pa gen moun pou menaj sa nan sistèm nan .";
                }else if(listType == Constant.LIST_MODULE_COMPTE_UTILISATEUR_16){
                    rowDataList = queryRecordMngr.searchListProfilUser(SPref);
                    message = "Pa gen kont itilizatè nan sistèm nan .";
                }else if(listType == Constant.LIST_MODULE_BATIMANT_POUR_RAPPORT_17){
                    rowDataList = queryRecordMngr.searchListBatimentByStatus_ForRapport( moduleStatut);
                    message = "Pa gen Batiman nan sistèm nan.";
                }else if(listType == Constant.LIST_MODULE_RAPPORT_PAR_BATIMANT_18){
                    rowDataList = queryRecordMngr.searchListRapportByIdBatiment(id);
                    message = "Pa gen Rapò nan sistèm nan.";
                }
                if( rowDataList != null ){
                    targetList.addAll(rowDataList);
                }else{
                    //ToastUtility.ToastMessage(DisplayListActivity.this, message, Constant.GravityCenter);
                    ToastUtility.LogCat(message);
                }
            }catch (Exception ex) {
                ToastUtility.LogCat( "Exception: doInBackground - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
                ex.printStackTrace();
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer result) {
            try {
                progressBar.setVisibility(View.GONE);
                if (result == 1) {
                    //toolbar.setTitle(title+"("+targetList.size()+")");
                    mAdapter.setFilter(targetList);

                    ReloadDataListTask();
                    setTitleHeader();
                } else {
                    Log.e(TAG, "Failed to fetch data!");
                }
            }catch (Exception ex) {
                ToastUtility.LogCat( "Exception: onPostExecute - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
                ex.printStackTrace();
            }
        }
    }

    private void setTitleHeader() {
        int nbrsave=0,nbrTotal=0;
        if( targetList != null )
            nbrsave = targetList.size();

        if (listType == Constant.LIST_MODULE_BATIMENT) {
            list_header_1.setText(getString(R.string.label_batiman) + " "+ moduleStatutString + " [" + nbrsave + "]");

        } else if (listType == Constant.LIST_MODULE_LOGEMENT_COLLECTIF) {
            nbrTotal = (batimentModel != null && batimentModel.getQb8NbreLogeCollectif() != null)? batimentModel.getQb8NbreLogeCollectif():0;
            list_header_1.setText(getString(R.string.label_lojmanKolektif) + " "+ moduleStatutString + " [" + nbrsave + "/" + nbrTotal + "]");

        } else if (listType == Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL) {
            nbrTotal = (batimentModel != null && batimentModel.getQb8NbreLogeIndividuel() != null)? batimentModel.getQb8NbreLogeIndividuel():0;
            list_header_1.setText(getString(R.string.label_lojmanEndividyel) + " "+ moduleStatutString + " [" + nbrsave + "/" + nbrTotal + "]");

        } else if (listType == Constant.LIST_MODULE_MENAGE) {
            nbrTotal = (logementModel != null && logementModel.getQlin8NbreIndividuDepense() != null && logementModel.getQlin8NbreIndividuDepense() == Constant.REPONS_WI_1
                    && logementModel.getQlin9NbreTotalMenage() != null )? logementModel.getQlin9NbreTotalMenage():0;
            list_header_1.setText(getString(R.string.label_menaj) + " "+ moduleStatutString + " [" + nbrsave + "/" + nbrTotal + "]");

        } else if (listType == Constant.LIST_MODULE_EMIGRE) {
            if (menageModel.getQn1Emigration() != null && menageModel.getQn1Emigration() == Constant.REPONS_WI_1) {
                if ( menageModel.getQn1NbreEmigre() != null && menageModel.getQn1NbreEmigre() > 0) {
                    nbrTotal = menageModel.getQn1NbreEmigre();
                }
            }
            list_header_1.setText(getString(R.string.label_emigre) + " "+ moduleStatutString + " [" + nbrsave + "/" + nbrTotal + "]");

        } else if (listType == Constant.LIST_MODULE_DECES) {
            if ( menageModel.getQd1Deces() != null && menageModel.getQd1Deces() == Constant.REPONS_WI_1) {
                if ( menageModel.getQd1NbreDecede() != null && menageModel.getQd1NbreDecede() > 0) {
                    nbrTotal = menageModel.getQd1NbreDecede();
                }
            }
            list_header_1.setText(getString(R.string.label_mounMouri) + " "+ moduleStatutString + " [" + nbrsave + "/" + nbrTotal + "]");

        } else if (listType == Constant.LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF) {
            nbrTotal = (logementModel != null && logementModel.getQlcTotalIndividus() != null )? logementModel.getQlcTotalIndividus():0;
            list_header_1.setText(getString(R.string.label_moun) + " "+ moduleStatutString + " [" + nbrsave + "/" + nbrTotal + "]");

        }else if(listType == Constant.LIST_MODULE_INDIVIDU_MENAGE){
            nbrTotal = (menageModel != null && menageModel.getQm11TotalIndividuVivant() != null)? menageModel.getQm11TotalIndividuVivant():0;
            list_header_1.setText(getString(R.string.label_moun) + " "+ moduleStatutString + " [" + nbrsave + "/" + nbrTotal + "]");

        }else if(listType == Constant.LIST_MODULE_COMPTE_UTILISATEUR_16){
            list_header_1.setText(getString(R.string.label_Kont_Itilizate) + " "+ moduleStatutString + " [" + nbrsave + "]");
        }else if(listType == Constant.LIST_MODULE_BATIMANT_POUR_RAPPORT_17){
            list_header_1.setText(getString(R.string.label_Rapo_Sou_Batiman_Yo) + " "+ moduleStatutString + " [" + nbrsave + "]");
        }else if(listType == Constant.LIST_MODULE_RAPPORT_PAR_BATIMANT_18){
            list_header_1.setText(getString(R.string.label_Rapo) + " "+ moduleStatutString + " [" + nbrsave + "]");
        }
    }
    //endregion
}
