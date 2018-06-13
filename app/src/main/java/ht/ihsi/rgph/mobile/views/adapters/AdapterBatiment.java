package ht.ihsi.rgph.mobile.views.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.models.BatimentModel;
import ht.ihsi.rgph.mobile.utilities.ToastUtility;

/**
 * Created by JFDuverseau on 3/30/2016.
 */
public class AdapterBatiment extends BaseAdapter
{
    private Context context;
    private final List<BatimentModel> batimentList;

    public AdapterBatiment(Activity context, List<BatimentModel> batimentList)
    {
        this.context = context;
        this.batimentList = batimentList;
    }

    @Override
    public int getCount() {
        return batimentList.size();
    }

    @Override
    public Object getItem(int position) {
        return batimentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View view = layoutInflater.inflate(R.layout.list_item_for_all, parent, false);
        TextView tv_Description = (TextView) view.findViewById(R.id.tv_Description);
        TextView tv_Titre = (TextView) view.findViewById(R.id.tv_Titre);

        final BatimentModel bat = batimentList.get(position);
        final String batStr = "BATIMAN-" + bat.getBatimentId();
        String val = "<span style='color:red;'>REC:" + bat.getQrec() + "</span>";
        tv_Titre.setText(Html.fromHtml(batStr + " / <span style='color:red;'>REC:"+ bat.getQrec() +"</span>" ));
        tv_Description.setText(Html.fromHtml("" + "<span style='color:red;'>SDE:</span>" + bat.getSdeId() + " / Adrès:" + bat.getQadresse() + " / Lokalite:" + bat.getQlocalite()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rowView.setBackgroundColor(Color.rgb(95, 194, 192));
//                Session.setCurrentChant(f);
//                Intent intent = new Intent(context, ChantView.class);
                ShowMenuAction(batStr);
            }
        });
        return view;
    }

    private void ShowMenuAction(String batStr) {
        Intent intent;
        AlertDialog.Builder ad = new AlertDialog.Builder(context);

        ad.setTitle(batStr);
        //ad.setMessage(context.getResources().getString(R.string.label_MessageMenuPopUp));
		ad.setIcon(android.R.drawable.ic_menu_info_details);
        //ad.setIcon(R.mipmap.ic_launcher);

        String label_GoToModuleFille = context.getResources().getString(R.string.label_GoToModuleFille);
        String label_OuvrirFormulaireModule = context.getResources().getString(R.string.label_OuvrirFormulaireModule).replace("{0}", batStr.toLowerCase());
        String label_UpdateFormulaireModule = context.getResources().getString(R.string.label_UpdateFormulaireModule).replace("{0}",  batStr.toLowerCase());

        String menu[] = new String[]{label_GoToModuleFille, label_OuvrirFormulaireModule, label_UpdateFormulaireModule};

        ad.setItems(menu, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:// label_GoToModuleFille
                        //intent = new Intent(context.getApplicationContext(), ChantList.class);
                        //intent.putExtra(Constant.TYPE_CHANT, "HT");
                        ToastUtility.LogCat(context.getApplicationContext(), "onClick - label_GoToModuleFille");
                        ToastUtility.ToastMessage(context.getApplicationContext(), "onClick - label_GoToModuleFille");
                        //startActivity(intent);
                        break;
                    case 1:// label_OuvrirFormulaireModule
                        ToastUtility.LogCat(context.getApplicationContext(), "onClick - label_OuvrirFormulaireModule");
                        ToastUtility.ToastMessage(context.getApplicationContext(), "onClick - label_OuvrirFormulaireModule");
                        break;
                    case 3:// label_UpdateFormulaireModule
                        ToastUtility.LogCat(context.getApplicationContext(), "onClick - label_UpdateFormulaireModule");
                        ToastUtility.ToastMessage(context.getApplicationContext(), "onClick - label_UpdateFormulaireModule");
                        break;
                    default:
                        break;
                }
            }
        });
        ad.show();
    }


}
