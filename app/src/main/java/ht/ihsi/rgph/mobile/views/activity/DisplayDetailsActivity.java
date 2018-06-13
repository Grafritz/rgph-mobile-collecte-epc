package ht.ihsi.rgph.mobile.views.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.constant.Constant;
import ht.ihsi.rgph.mobile.models.BatimentModel;
import ht.ihsi.rgph.mobile.utilities.QuestionnaireFormulaireUtility;
import ht.ihsi.rgph.mobile.utilities.ToastUtility;

public class DisplayDetailsActivity extends BaseActivity {

    //region ATTRIBUTS
    public LinearLayout LL_DisplayQuestionnaire;
    private String title;
    private int listType=0;
    private short moduleStatut=0;
    private long id=0;
    private BatimentModel batimentModel=null;
    private QuestionnaireFormulaireUtility QF = null;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_details);

        init(Constant.FORM_DATA_MANAGER);
        init(Constant.QUERY_RECORD_MANAGER);
        //init(Constant.CU_RECORD_MANAGER);


        try {
            LL_DisplayQuestionnaire = (LinearLayout) findViewById(R.id.LL_DisplayQuestionnaire);

            Intent intent= getIntent();
            title= intent.getStringExtra(Constant.PARAM_LIST_TITLE);
            listType= Integer.valueOf(intent.getStringExtra(Constant.PARAM_LIST_TYPE)).intValue();
            moduleStatut=Short.valueOf(intent.getStringExtra(Constant.PARAM_MODULE_STATUT));
            if(listType != Constant.LIST_MODULE_BATIMENT){
                id= Long.valueOf(intent.getStringExtra(Constant.PARAM_MODULE_ID)).longValue();
            }
            if(listType == Constant.LIST_MODULE_BATIMENT){
                batimentModel = (BatimentModel) intent.getSerializableExtra(Constant.PARAM_MODEL);
                QF = (QuestionnaireFormulaireUtility) intent.getSerializableExtra(Constant.PARAM_QUESTIONNAIRE_FORMULAIRE);
            }
            for (int i = 1; i <= 20; i++) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                Button btn = new Button(this);
                //btn.setId(i);
                final int id_ = btn.getId();
                btn.setText("button " + i);
                //btn.setBackgroundColor(Color.rgb(70, 80, 90));
                LL_DisplayQuestionnaire.addView(btn, params);

                /*btn = ((Button) findViewById(id_));*/
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(),
                                "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                                .show();
                    }
                });
            }
        }catch (Exception ex) {
            ToastUtility.LogCat(this, "Exception:onCreate() - getMessage:" + ex.getMessage() + " / toString:" + ex.toString());
            ex.printStackTrace();
        }
    }
}
