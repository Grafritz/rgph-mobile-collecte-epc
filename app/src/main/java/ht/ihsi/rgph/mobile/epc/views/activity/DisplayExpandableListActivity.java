package ht.ihsi.rgph.mobile.epc.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.models.RowDataListModel;
import ht.ihsi.rgph.mobile.epc.utilities.Privilege;
import ht.ihsi.rgph.mobile.epc.utilities.Shared_Preferences;
import ht.ihsi.rgph.mobile.epc.utilities.ToastUtility;
import ht.ihsi.rgph.mobile.epc.utilities.Tools;
import ht.ihsi.rgph.mobile.epc.views.adapters.DisplayListAdapter;
import ht.ihsi.rgph.mobile.epc.views.adapters.ExpandableListAdapter;

/**
 * Created by JFDuverseau on 12/8/2016.
 */
public class DisplayExpandableListActivity extends BaseActivity  {

    private ProgressBar progressBar;
    private MaterialSearchView searchView;
    private Toolbar toolbar;
    private String title;
    private int listType=0;
    private short moduleStatut=0;
    private long id=0;
    Shared_Preferences infoUser = null;
    int profilId = 0;
    private TextView list_header_1;
    private TextView list_header_2;
    String StringHeaderTwo;

    ExpandableListAdapter expandableListAdapter ;
    ExpandableListView expandable_ListView;
    List<String> listDataHeader;
    HashMap<String, List<RowDataListModel>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_expandable_list );

        try {
            infoUser = Tools.SharedPreferences(this);
            if (infoUser != null && infoUser.getProfileId() != null) {
                profilId = infoUser.getProfileId();
            }
            Intent intent = getIntent();
            title = intent.getStringExtra(Constant.PARAM_LIST_TITLE);

            StringHeaderTwo = intent.getStringExtra(Constant.PARAM_LISTE_HEADER_TWO).toString();
            listType = Integer.valueOf(intent.getStringExtra(Constant.PARAM_LIST_TYPE)).intValue();
            moduleStatut = Short.valueOf(intent.getStringExtra(Constant.PARAM_MODULE_STATUT));
            if (listType != Constant.LIST_MODULE_BATIMENT) {
                id = Long.valueOf(intent.getStringExtra(Constant.PARAM_MODULE_ID)).longValue();
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

            // get the listview
            expandable_ListView = (ExpandableListView) findViewById(R.id.expandable_ListView);
            expandableListAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, listType);
            // setting list adapter
            expandable_ListView.setAdapter(expandableListAdapter);

            //initialize the progress bar
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);

            // preparing list data
            prepareListData();
        } catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception:onCreate() - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }

    //region SearchView

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

    public boolean onTextSearchEvent(String newText) {
        if( listDataChild!=null && listDataChild.size()>0) {
            final HashMap<String, List<RowDataListModel>> filteredList = filter(listDataChild, newText);
            expandableListAdapter.setFilter(filteredList);
        }
        return true;
    }
    private HashMap<String, List<RowDataListModel>> filter( HashMap<String, List<RowDataListModel>> rows, String query ){
        query=query.toLowerCase();
        final HashMap<String, List<RowDataListModel>> filteredList = new HashMap<String, List<RowDataListModel>>();
        if(rows!=null) {
            /*for (RowDataListModel row : rows.get(RowDataListModel.class)) {
                final String text = row.getTitle().toLowerCase();
                if (text.contains(query)) {
                    filteredList.putAll(row);
                }
            }*/
        }
        return filteredList;
    }
    //endregion

    //region EVENTS

    public DisplayListAdapter.OnMenuItemClickListener getMenuItemListener(){
        return new DisplayListAdapter.OnMenuItemClickListener() {

            @Override
            public void onMenuItemAfficher(RowDataListModel row) {

            }

            @Override
            public void onMenuItemModifye(RowDataListModel row) {
                boolean privilege = Privilege.getAccessControl(profilId, Constant.ACTION_MOFIDIER, moduleStatut);
                if( privilege ){
                    //goToForm(row, Constant.ACTION_MOFIDIER);
                }else{
                    Tools.AlertDialogMsg(DisplayExpandableListActivity.this, "Opsyon [ Modifye ] sa pa fèt pou ou, \nWè avèk sipèvisè ou...");
                }
            }

            @Override
            public void onMenuItemKontinye(RowDataListModel row) {
                boolean privilege = Privilege.getAccessControl(profilId, Constant.ACTION_KONTINYE, moduleStatut);
                if( privilege ){
                    //OptionKontinye(row, Constant.ACTION_KONTINYE);
                }else{
                    Tools.AlertDialogMsg(DisplayExpandableListActivity.this, "Opsyon [ Modifye ] sa pa fèt pou ou, \nWè avèk sipèvisè ou...");
                }
            }

            @Override
            public void onMenuItemRapport(RowDataListModel row) {

            }

            @Override
            public void onMenuItemRetounenMalRanpli(RowDataListModel row) {

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
    //endregion

    //region DATA

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<RowDataListModel>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");
/*
        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);*/
    }
    //endregion
}
