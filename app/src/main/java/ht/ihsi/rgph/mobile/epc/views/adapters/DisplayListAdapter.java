package ht.ihsi.rgph.mobile.epc.views.adapters;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.models.BatimentModel;
//import ht.ihsi.rgph.mobile.epc.models.DecesModel;
//import ht.ihsi.rgph.mobile.epc.models.EmigreModel;
import ht.ihsi.rgph.mobile.epc.models.IndividuModel;
import ht.ihsi.rgph.mobile.epc.models.LogementModel;
import ht.ihsi.rgph.mobile.epc.models.MenageModel;
import ht.ihsi.rgph.mobile.epc.models.RowDataListModel;

/**
 * Created by ajordany on 4/7/2016.
 */
public class DisplayListAdapter extends RecyclerView.Adapter<DisplayListAdapter.DetailRowViewHolder> {

    private List<RowDataListModel> targetList;
    private Context context;
    private LayoutInflater mInflater;
    private OnItemClickListener onItemClickListener;
    private OnMenuItemClickListener onMenuItemClickListener;
    private int listType;
    private int profilId;
    private int statutModule;

    public DisplayListAdapter(Context context, List<RowDataListModel> targetList, int listType){
        this.targetList=targetList;
        this.context=context;
        this.mInflater=LayoutInflater.from(context);
        this.listType=listType;
        this.profilId=0;
        this.statutModule=Constant.STATUT_MODULE_KI_FINI_1;
    }

    public DisplayListAdapter(Context context, List<RowDataListModel> targetList, int listType, int profilId, int statutModule){
        this.targetList=targetList;
        this.context=context;
        this.mInflater=LayoutInflater.from(context);
        this.listType=listType;
        this.profilId=profilId;
        this.statutModule=statutModule;
    }

    @Override
    public DetailRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,null);
        DetailRowViewHolder detailRowViewHolder= new DetailRowViewHolder(view);
        return detailRowViewHolder;
    }

    @Override
    public void onBindViewHolder(DetailRowViewHolder holder, int position) {
        holder.bind(targetList.get(position));
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener listener){
        this.onMenuItemClickListener=listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
       return (null!= targetList ? targetList.size(): 0);
    }

    public void setFilter(List<RowDataListModel> filteredList){
        targetList=new ArrayList<RowDataListModel>();
        targetList.addAll(filteredList);
        notifyDataSetChanged();
    }

    public class DetailRowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;
        public TextView title;
        public TextView desc;
        public Button btnModule;
        public Button btnMenu;
        private ImageView overflowIcon;
        private RowDataListModel row;

        private BatimentModel batimentModel=null;
        private LogementModel logementModel=null;
        private MenageModel menageModel=null;
        //private EmigreModel emigreModel=null;
        //private DecesModel decesModel=null;
        private IndividuModel individuModel=null;

        public DetailRowViewHolder(View view){
            super(view);

            imageView= (ImageView) view.findViewById(R.id.imageView);
            title= (TextView) view.findViewById(R.id.title);
            desc= (TextView) view.findViewById(R.id.desc);
            overflowIcon= (ImageView) view.findViewById(R.id.overflowIcon);
            overflowIcon.setOnClickListener(this);
            view.setClickable(true);
            // view.animate();
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(!row.isEmpty()){
                if( listType == Constant.LIST_MODULE_LOGEMENT_NOT_FINISH_LIST_ONLY
                        || listType == Constant.LIST_MODULE_MENAGE_NOT_FINISH_LIST_ONLY ){
                    if ( onItemClickListener != null ) {
                        onItemClickListener.onItemModuleClick(row);
                    }
                    overflowIcon.setVisibility(View.GONE);
                }else if( listType != Constant.LIST_MODULE_INDIVIDU_LIST_ONLY
                        && listType != Constant.LIST_MODULE_LOGEMENT_LIST_ONLY
                        && listType != Constant.LIST_MODULE_MENAGE_LIST_ONLY
                        && listType != Constant.LIST_MODULE_BATIMENT_LIST_ONLY
                        && listType != Constant.LIST_MODULE_EMIGRER_LIST_ONLY
                        && listType != Constant.LIST_MODULE_DECES_LIST_ONLY ) {
                    overflowIcon.setVisibility(View.GONE);

                    if( profilId == Constant.PRIVILEGE_AGENT && statutModule == Constant.STATUT_MODULE_KI_FINI_1 ){
                        // Pas de Menu
                    }else{
                        showContextMenu(v); // call the contextual menu
                    }
                }
            }
        }

        public void showContextMenu(View v){
            PopupMenu popup = new PopupMenu(v.getContext(), v);
            popup.getMenuInflater().inflate(R.menu.recycle_view_actions, popup.getMenu());
            //popup.inflate(R.menu.recycle_view_actions);

            MenuItem item_afficher = popup.getMenu().findItem(R.id.item_afficher);
            MenuItem item_modifier = popup.getMenu().findItem(R.id.item_modifier);
            MenuItem item_Kontinye = popup.getMenu().findItem(R.id.item_Kontinye);
            MenuItem item_Rapport = popup.getMenu().findItem(R.id.item_Rapport);
            MenuItem item_RetounenMalRanpli = popup.getMenu().findItem(R.id.item_RetounenMalRanpli);

            /*if(listType==Constant.LIST_MODULE_INDIVIDU_MENAGE
                    || listType==Constant.LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF
                    || listType==Constant.LIST_MODULE_DECES
                    || listType==Constant.LIST_MODULE_EMIGRE) {
                //popup.getMenu().removeItem(R.id.item_module_menu);
            }else */

            if( listType==Constant.LIST_MODULE_INDIVIDU_LIST_ONLY
                    || listType==Constant.LIST_MODULE_LOGEMENT_LIST_ONLY
                    || listType==Constant.LIST_MODULE_MENAGE_LIST_ONLY
                    || listType==Constant.LIST_MODULE_BATIMENT_LIST_ONLY
                    || listType==Constant.LIST_MODULE_EMIGRER_LIST_ONLY
                    || listType==Constant.LIST_MODULE_DECES_LIST_ONLY ){

                item_afficher.setVisible(false);
                popup.getMenu().removeItem(R.id.item_modifier);
                popup.getMenu().removeItem(R.id.item_Kontinye);
                popup.getMenu().removeItem(R.id.item_Rapport);
                popup.getMenu().removeItem(R.id.item_RetounenMalRanpli);
                overflowIcon.setVisibility(View.GONE);
                //popup.getMenu().removeItem(R.id.item_module_menu);
            }else{
                //popup.getMenu().findItem(R.id.item_module_menu).setEnabled(row.isModuleMenu());
                //MenuItem item=popup.getMenu().findItem(R.id.item_Rapport);
                item_Rapport.setTitle(" Rapò "+getModuleName());
            }

            if(  profilId == Constant.PRIVILEGE_AGENT
                    || profilId == Constant.PRIVILEGE_SUPERVISEUR
                    || profilId == Constant.PRIVILEGE_ASTIC ) {
                item_Rapport.setVisible(false);
                item_RetounenMalRanpli.setVisible(false);
            }
            if( profilId == Constant.PRIVILEGE_SUPERVISEUR
                    || profilId == Constant.PRIVILEGE_ASTIC ) {
                item_modifier.setVisible(false);
            }

            //popup.getMenu().findItem(R.id.item_Kontinye).setVisible(row.isComplete());
            item_Kontinye.setVisible(row.isComplete());

            if(listType == Constant.LIST_MODULE_BATIMENT) {
                BatimentModel batModel = (BatimentModel) row.getModel();
                if (batModel.getQb1Etat() != null && batModel.getQb1Etat() == Constant.BATIMAN_Pa_konnen_paske_li_pa_sou_je_5) {
                    item_Kontinye.setVisible(false);
                }
            }

            if(listType==Constant.LIST_MODULE_COMPTE_UTILISATEUR_16){
                item_Kontinye.setVisible(false);
                item_Rapport.setVisible(false);
                item_RetounenMalRanpli.setVisible(false);

            }else  if( listType==Constant.LIST_MODULE_BATIMANT_POUR_RAPPORT_17 ){
                item_modifier.setTitle("Ouvè rapò "+getModuleName()+" ");
                item_modifier.setVisible(true);
                item_Kontinye.setVisible(false);
                item_Rapport.setVisible(false);
                item_RetounenMalRanpli.setVisible(false);
            }

            item_RetounenMalRanpli.setVisible(false);
            if( statutModule==Constant.STATUT_MODULE_KI_FINI_1 ){
                item_RetounenMalRanpli.setVisible(true);
                if(  profilId == Constant.PRIVILEGE_AGENT ) {
                    item_RetounenMalRanpli.setVisible(false);
                }
            }

            if( listType==Constant.LIST_MODULE_BATIMANT_POUR_RAPPORT_17
                    || listType==Constant.LIST_MODULE_RAPPORT_PAR_BATIMANT_18
                    || listType==Constant.LIST_MODULE_COMPTE_UTILISATEUR_16 ){
                item_RetounenMalRanpli.setVisible(false);
            }
            if( listType==Constant.LIST_MODULE_RAPPORT_PAR_BATIMANT_18 ){
                item_modifier.setVisible(false);
                item_Kontinye.setVisible(false);
                item_Rapport.setVisible(false);
                item_RetounenMalRanpli.setVisible(false);
            }

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.item_afficher:
                            onMenuItemClickListener.onMenuItemAfficher(row);
                            break;
                        case R.id.item_modifier:
                            onMenuItemClickListener.onMenuItemModifye(row);
                            break;
                        case R.id.item_Kontinye:
                            onMenuItemClickListener.onMenuItemKontinye(row);
                            break;
                        case R.id.item_Rapport:
                            onMenuItemClickListener.onMenuItemRapport(row);
                            break;
                        case R.id.item_RetounenMalRanpli:
                            onMenuItemClickListener.onMenuItemRetounenMalRanpli(row);
                            break;
                        //case R.id.item_module_menu:
                        //    onMenuItemClickListener.onMenuItemModuleMenu(row);
                        //    break;
                        default:
                            break;
                    }
                    return true;
                }
            });

            // CHECK PRIVILEGE POUR AFFICHER LE MENU

            item_afficher.setVisible(false);

            if( profilId == Constant.PRIVILEGE_AGENT && statutModule == Constant.STATUT_MODULE_KI_FINI_1 ){
                //popup.getMenu().removeItem(R.id.item_modifier);
                //popup.getMenu().removeItem(R.id.item_Kontinye);
                //popup.getMenu().removeItem(R.id.item_Rapport);
                item_modifier.setVisible(false);
                item_Kontinye.setVisible(false);
                item_Rapport.setVisible(false);
                item_RetounenMalRanpli.setVisible(false);
            }else{
                MenuPopupHelper menuPopupHelper = new MenuPopupHelper(context, (MenuBuilder)popup.getMenu(), v);
                menuPopupHelper.setForceShowIcon(true);
                menuPopupHelper.setGravity(Gravity.RIGHT);
                menuPopupHelper.show();
            }
            //popup.show();
        }

        public String getModuleName(){
            long numeroOrdre = 0;
            if(listType==Constant.LIST_MODULE_BATIMENT || listType==Constant.LIST_MODULE_BATIMANT_POUR_RAPPORT_17 ){
                if(!row.isEmpty()){
                    batimentModel = (BatimentModel) row.getModel();
                    numeroOrdre = (this.batimentModel.getBatimentId() != null ? this.batimentModel.getBatimentId() : Long.valueOf(0));
                }
                return Constant.MODULE_NAME_BATIMAN + " " + numeroOrdre;

            }else if(listType==Constant.LIST_MODULE_LOGEMENT_COLLECTIF){
                if(!row.isEmpty()){
                    logementModel = (LogementModel) row.getModel();
                    numeroOrdre = (this.logementModel.getQlin1NumeroOrdre() != null ? this.logementModel.getQlin1NumeroOrdre() : Long.valueOf(0));
                }
                return Constant.MODULE_NAME_LOGMAN_KOLEKTIF + " " + numeroOrdre;

            }else if(listType==Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL){
                if(!row.isEmpty()){
                    logementModel = (LogementModel) row.getModel();
                    numeroOrdre = (this.logementModel.getQlin1NumeroOrdre() != null ? this.logementModel.getQlin1NumeroOrdre() : Long.valueOf(0));
                }
                return Constant.MODULE_NAME_LOGMAN_ENDIVIDYEL + " " + numeroOrdre;

            }else if(listType==Constant.LIST_MODULE_MENAGE){
                if(!row.isEmpty()){
                    menageModel = (MenageModel) row.getModel();
                    numeroOrdre = (this.menageModel.getQm1NoOrdre() != null ? this.menageModel.getQm1NoOrdre() : Long.valueOf(0));
                }
                return Constant.MODULE_NAME_MENAGE + " " + numeroOrdre;

            }else if( listType==Constant.LIST_MODULE_INDIVIDU_MENAGE || listType==Constant.LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF){
                if(!row.isEmpty()){
                    individuModel = (IndividuModel) row.getModel();
                    numeroOrdre = (this.individuModel.getQ1NoOrdre() != null ? this.individuModel.getQ1NoOrdre() : Long.valueOf(0));
                }
                return Constant.MODULE_NAME_INDIVIDU + " " + numeroOrdre;

            /*}else if(listType==Constant.LIST_MODULE_EMIGRE){
                if(!row.isEmpty()){
                    emigreModel = (EmigreModel) row.getModel();
                    numeroOrdre = (this.emigreModel.getQn1numeroOrdre() != null ? this.emigreModel.getQn1numeroOrdre() : Long.valueOf(0));
                }
                return Constant.MODULE_NAME_EMIGRE + " " + numeroOrdre;

            }else if(listType==Constant.LIST_MODULE_DECES){
                if(!row.isEmpty()){
                    decesModel = (DecesModel) row.getModel();
                    numeroOrdre = (this.decesModel.getQd2NoOrdre() != null ? this.decesModel.getQd2NoOrdre() : Long.valueOf(0));
                }
                return Constant.MODULE_NAME_DECES + " " + numeroOrdre;*/
            }else{
                return "";
            }
        }

        public String getModuleName_Fille(){
            if(listType==Constant.LIST_MODULE_BATIMENT){
                return Constant.MODULE_NAME_BATIMAN;
            }else if(listType==Constant.LIST_MODULE_LOGEMENT_COLLECTIF){
                return Constant.MODULE_NAME_INDIVIDU;
            }else if(listType==Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL){
                return Constant.MODULE_NAME_MENAGE;
            }else if(listType==Constant.LIST_MODULE_MENAGE){
                return Constant.MODULE_NAME_INDIVIDU;
            }else{
                return "";
            }
        }

        private View.OnClickListener getClickModuleListener(){
            return new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemModuleClick(row);
                    }
                }
            };
        }

        //private View.OnClickListener getClickMenuListener(){
        //    return new View.OnClickListener(){
        //        @Override
        //        public void onClick(View v) {
        //            if (onItemClickListener != null) {
        //                onItemClickListener.onItemMenuClick(row);
        //            }
        //        }
        //    };
        //}

        public void bind(RowDataListModel row){
            this.row=row;
            int icon=getIcon();
            Picasso.with(context).load(icon).placeholder(icon)
                    .into(this.imageView);

            Picasso.with(context).load(R.drawable.dots_vertical).placeholder(R.drawable.dots_vertical)
                    .into(this.overflowIcon);

            this.title.setText(Html.fromHtml((row.getTitle())));
            this.desc.setText(Html.fromHtml(row.getDesc()));
            if(row.isEmpty()){
                overflowIcon.setVisibility(View.INVISIBLE);
            }

            if( listType == Constant.LIST_MODULE_LOGEMENT_NOT_FINISH_LIST_ONLY
                    || listType == Constant.LIST_MODULE_MENAGE_NOT_FINISH_LIST_ONLY ){
                overflowIcon.setVisibility(View.GONE);
            }
            if( listType==Constant.LIST_MODULE_INDIVIDU_LIST_ONLY
                    || listType==Constant.LIST_MODULE_LOGEMENT_LIST_ONLY
                    || listType==Constant.LIST_MODULE_MENAGE_LIST_ONLY
                    || listType==Constant.LIST_MODULE_BATIMENT_LIST_ONLY
                    || listType==Constant.LIST_MODULE_EMIGRER_LIST_ONLY
                    || listType==Constant.LIST_MODULE_DECES_LIST_ONLY ){
                overflowIcon.setVisibility(View.GONE);
            }
        }

        public int getIcon(){
            if( listType== Constant.LIST_MODULE_LOGEMENT_NOT_FINISH_LIST_ONLY
                    || listType == Constant.LIST_MODULE_MENAGE_NOT_FINISH_LIST_ONLY ){
                return R.drawable.ic_logement_notfinish;
            }else if(listType== Constant.LIST_MODULE_BATIMENT || listType==Constant.LIST_MODULE_BATIMENT_LIST_ONLY ){
                return R.drawable.ic_batiment;
            }else if(listType==Constant.LIST_MODULE_LOGEMENT_COLLECTIF
                    || listType==Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL || listType==Constant.LIST_MODULE_LOGEMENT_LIST_ONLY ){
                return R.drawable.ic_logement;
            }else if(listType==Constant.LIST_MODULE_MENAGE || listType==Constant.LIST_MODULE_MENAGE_LIST_ONLY ){
                return R.drawable.ic_menage;
            }else if(listType==Constant.LIST_MODULE_EMIGRE || listType==Constant.LIST_MODULE_EMIGRER_LIST_ONLY ){
                return R.drawable.ic_emigre;
            }else if(listType==Constant.LIST_MODULE_DECES || listType==Constant.LIST_MODULE_DECES_LIST_ONLY ){
                return R.drawable.ic_deces;
            }else if(listType==Constant.LIST_MODULE_INDIVIDU_MENAGE || listType==Constant.LIST_MODULE_INDIVIDU_LIST_ONLY
                    || listType==Constant.LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF){
                return R.drawable.ic_individu;
            }else if(listType==Constant.LIST_MODULE_COMPTE_UTILISATEUR_16 ){
                return R.drawable.group_user;
            }else if(listType==Constant.LIST_MODULE_BATIMANT_POUR_RAPPORT_17 || listType==Constant.LIST_MODULE_RAPPORT_PAR_BATIMANT_18 ){
                return R.drawable.report;
            }else{
                return R.drawable.placeholder;
            }
        }

    }

    //Interface on row click listener
    public interface OnItemClickListener {
        //void onItemClick(RowDataListModel entity);
        void onItemModuleClick(RowDataListModel entity);
        //void onItemMenuClick(RowDataListModel entity);
    }

    public interface OnMenuItemClickListener{
        void onMenuItemAfficher(RowDataListModel row);
        void onMenuItemModifye(RowDataListModel row);
        void onMenuItemKontinye(RowDataListModel row);
        void onMenuItemRapport(RowDataListModel row);
        void onMenuItemRetounenMalRanpli(RowDataListModel row);
        //void onMenuItemModuleMenu(RowDataListModel row);
    }

}
