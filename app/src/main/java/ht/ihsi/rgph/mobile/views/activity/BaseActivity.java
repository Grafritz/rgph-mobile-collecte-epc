package ht.ihsi.rgph.mobile.views.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import ht.ihsi.rgph.mobile.constant.Constant;
import ht.ihsi.rgph.mobile.managers.CURecordMngr;
import ht.ihsi.rgph.mobile.managers.CURecordMngrImpl;
import ht.ihsi.rgph.mobile.managers.FormDataMngr;
import ht.ihsi.rgph.mobile.managers.FormDataMngrImpl;
import ht.ihsi.rgph.mobile.managers.QueryRecordMngr;
import ht.ihsi.rgph.mobile.managers.QueryRecordMngrImpl;
import ht.ihsi.rgph.mobile.models.KeyValueModel;
import ht.ihsi.rgph.mobile.models.QuestionReponseModel;
import ht.ihsi.rgph.mobile.models.RowDataListModel;
import ht.ihsi.rgph.mobile.utilities.Shared_Preferences;
import ht.ihsi.rgph.mobile.utilities.ToastUtility;
import ht.ihsi.rgph.mobile.utilities.Tools;
import ht.ihsi.rgph.mobile.views.adapters.RadioListAdapter;
import ht.ihsi.rgph.mobile.views.adapters.RadioListAdapterKeyValue;

/**
 * Created by ajordany on 3/22/2016.
 */
public class BaseActivity extends AppCompatActivity {

    protected FormDataMngr formDataMngr;
    protected CURecordMngr cuRecordMngr;
    protected QueryRecordMngr queryRecordMngr;
    protected Intent intent;
    public Boolean cancel = true;
    public String message = "";
    public Toolbar toolbar;
    public RecyclerView recyclerViewReponse;
    public LinearLayout LL_RecyclerView;
    public RadioListAdapter mAdapter;
    public RadioListAdapter radioListAdapter;
    public RadioListAdapterKeyValue radioListAdapterKeyValue;
    public QuestionReponseModel codeReponseRecyclerView = null;
    public KeyValueModel codeReponseKeyValueModel = null;
    public Boolean isRetour = false;
    public Shared_Preferences infoUser = null;
    public int profilId = 0;
    public short moduleStatut=Constant.STATUT_MODULE_KI_FINI_1;


    //region Rapport RAR
    public Spinner sp_Rezon;
    public LinearLayout LL_LotRezon;
    public TextView tv_LotRezon;
    public EditText et_LotRezon;
    public boolean finishAfter=false;
    public LinearLayout LinearLayout_messageChangerdeModule;
    public TextView tv_messageChangerdeModule;
    public Button btnContinuerEtChangerdeModule;
    //endregion

    //region JODA TIME// Format for input
    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("MM/dd/YYYY HH:mm:ss");
    //DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("EEE, dd MMM YYYY HH:mm:ss zzz");
    //public DateTime getDateDebutCollecte;
    public String dateString ="";// Tools.getDateString_MMddyyyy_HHmmss_a();
    //endregion

    @Override
    protected void onDestroy() {
       /* if (formDataMngr != null)
            formDataMngr.closeDbConnections();

        if(cuRecordMngr!=null){
            cuRecordMngr.closeDbConnections();
        }

        if(queryRecordMngr!=null){
            queryRecordMngr.closeDbConnections();
        }*/

        ToastUtility.LogCat(this, "onDestroy : No closeDbConnections()");
        super.onDestroy();
    }

    public void init(int type){
        if(Constant.FORM_DATA_MANAGER==type){
            formDataMngr = new FormDataMngrImpl(this);
        }else if(Constant.CU_RECORD_MANAGER==type){
            cuRecordMngr=new CURecordMngrImpl(this);
        }else if(Constant.QUERY_RECORD_MANAGER==type){
            queryRecordMngr=new QueryRecordMngrImpl(this);
        }
    }

    public void CheckPrefIsUseConnected() {
        Boolean checkPrefIsUseConnected =  Tools.CheckPrefIsUseConnected(this);
        if(!checkPrefIsUseConnected){
            cancel=true;
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else {
            cancel=false;
        }
    }//
    //

    public void showListView(String title,int listType,int statut){
        Intent intent= new Intent(this, DisplayListActivity.class);
        intent.putExtra(Constant.PARAM_LIST_TITLE, title);
        intent.putExtra(Constant.PARAM_LIST_TYPE, ""+listType);
        intent.putExtra(Constant.PARAM_MODULE_STATUT, ""+statut);
        startActivity(intent);
    }

    public void showListView(String title,int listType,int statut,long id,String txtHeaderTwo){
        Intent intent= new Intent(this, DisplayListActivity.class);
        intent.putExtra(Constant.PARAM_LIST_TITLE, title);
        intent.putExtra(Constant.PARAM_LIST_TYPE, ""+listType);
        intent.putExtra(Constant.PARAM_MODULE_STATUT, "" + statut);
        intent.putExtra(Constant.PARAM_LISTE_HEADER_TWO, txtHeaderTwo);
        intent.putExtra(Constant.PARAM_MODULE_ID, "" + id);

        startActivity(intent);
    }

    /* RowDataListModel row = new RowDataListModel(); */
    public void showListView(String title, int listType, int statut, long id, String txtHeaderTwo, RowDataListModel rowObject){
        Intent intent= new Intent(this, DisplayListActivity.class);
        intent.putExtra(Constant.PARAM_LIST_TITLE, title);
        intent.putExtra(Constant.PARAM_LISTE_HEADER_TWO, txtHeaderTwo);
        intent.putExtra(Constant.PARAM_LIST_TYPE, ""+listType);
        intent.putExtra(Constant.PARAM_MODULE_STATUT, "" + statut);
        intent.putExtra(Constant.PARAM_MODULE_ID, "" + id);
        intent.putExtra(Constant.PARAM_MODEL, rowObject);

        startActivity(intent);
    }
}
