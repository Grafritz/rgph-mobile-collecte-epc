package ht.ihsi.rgph.mobile.epc.views.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.models.RowDataListModel;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private LayoutInflater mInflater;
	private OnItemClickListener onItemClickListener;
	private OnMenuItemClickListener onMenuItemClickListener;
	private int listType;

	private Context context;
	private List<String> targetlistDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<RowDataListModel>> targetlistDataChild;


	public ExpandableListAdapter(Context context, List<String> targetlistDataHeader,
								 HashMap<String, List<RowDataListModel>> targetlistChildData, int listType) {
		this.context = context;
		this.targetlistDataHeader = targetlistDataHeader;
		this.targetlistDataChild = targetlistChildData;
		this.mInflater = LayoutInflater.from(context);
		this.listType = listType;
	}

	public void setOnMenuItemClickListener(OnMenuItemClickListener listener){
		this.onMenuItemClickListener=listener;
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.onItemClickListener = listener;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.targetlistDataChild.get(this.targetlistDataHeader.get(groupPosition)).get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final String childText = (String) getChild(groupPosition, childPosition);

		if (convertView == null) {

			//LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			//convertView = infalInflater.inflate(R.layout.list_item, null);

			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null);
			DetailRowViewHolder detailRowViewHolder= new DetailRowViewHolder(convertView);
			//return detailRowViewHolder;
		}

		//TextView txtListChild = (TextView) convertView .findViewById(R.id.lblListItem);
		//txtListChild.setText(childText);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.targetlistDataChild.get(this.targetlistDataHeader.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.targetlistDataHeader.get(groupPosition);
	}


	@Override
	public int getGroupCount() {
		return this.targetlistDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	//region OTHERS

	//region FILTER
	public void setFilter(HashMap<String, List<RowDataListModel>> filteredList){
		//targetlistDataChild = new ArrayList<RowDataListModel>();
		targetlistDataChild = new HashMap<String, List<RowDataListModel>>();
		targetlistDataChild.putAll(filteredList);
		notifyDataSetChanged();
	}
	//endregion

	public class DetailRowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
		public ImageView imageView;
		public TextView title;
		public TextView desc;
		public Button btnModule;
		public Button btnMenu;
		private ImageView overflowIcon;
		private RowDataListModel row;

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
				if( listType != Constant.LIST_MODULE_INDIVIDU_LIST_ONLY
						&& listType != Constant.LIST_MODULE_LOGEMENT_LIST_ONLY
						&& listType != Constant.LIST_MODULE_MENAGE_LIST_ONLY
						&& listType != Constant.LIST_MODULE_BATIMENT_LIST_ONLY
						&& listType != Constant.LIST_MODULE_EMIGRER_LIST_ONLY
						&& listType != Constant.LIST_MODULE_DECES_LIST_ONLY ) {
					showContextMenu(v); // call the contextual menu
				}
			}

		}

		public void showContextMenu(View v){
			PopupMenu popup = new PopupMenu(v.getContext(), v);
			popup.inflate(R.menu.recycle_view_actions);

			if(listType==Constant.LIST_MODULE_INDIVIDU_MENAGE
					|| listType==Constant.LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF
					|| listType==Constant.LIST_MODULE_DECES
					|| listType==Constant.LIST_MODULE_EMIGRE) {
				//popup.getMenu().removeItem(R.id.item_module_menu);
			}else if( listType==Constant.LIST_MODULE_INDIVIDU_LIST_ONLY
					|| listType==Constant.LIST_MODULE_LOGEMENT_LIST_ONLY
					|| listType==Constant.LIST_MODULE_MENAGE_LIST_ONLY
					|| listType==Constant.LIST_MODULE_BATIMENT_LIST_ONLY
					|| listType==Constant.LIST_MODULE_EMIGRER_LIST_ONLY
					|| listType==Constant.LIST_MODULE_DECES_LIST_ONLY ){
				popup.getMenu().removeItem(R.id.item_modifier);
				popup.getMenu().removeItem(R.id.item_Kontinye);
				//popup.getMenu().removeItem(R.id.item_module_menu);
			}else{
				//popup.getMenu().findItem(R.id.item_module_menu).setEnabled(row.isModuleMenu());

				//MenuItem item=popup.getMenu().findItem(R.id.item_module_menu);
				//item.setTitle(item.getTitle()+" "+getModuleName());
			}

			popup.getMenu().findItem(R.id.item_Kontinye).setEnabled(row.isComplete());


			popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					switch (item.getItemId()) {
						case R.id.item_modifier:
							onMenuItemClickListener.onMenuItemRekomanse(row);
							break;
						case R.id.item_Kontinye:
							onMenuItemClickListener.onMenuItemKontinye(row);
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
			popup.setGravity(Gravity.RIGHT);

			popup.show();

		}

		public String getModuleName(){
			if(listType==Constant.LIST_MODULE_BATIMENT){
				return Constant.MODULE_NAME_LOGMAN_ENDIVIDYEL;
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

		private View.OnClickListener getClickMenuListener(){
			return new View.OnClickListener(){
				@Override
				public void onClick(View v) {
					if (onItemClickListener != null) {
						onItemClickListener.onItemMenuClick(row);
					}
				}
			};
		}

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

		}

		public int getIcon(){
			if(listType== Constant.LIST_MODULE_BATIMENT || listType==Constant.LIST_MODULE_BATIMENT_LIST_ONLY ){
				return R.drawable.ic_batiment;
			}else if(listType==Constant.LIST_MODULE_LOGEMENT_COLLECTIF
					|| listType==Constant.LIST_MODULE_LOGEMENT_INDIVIDUEL || listType==Constant.LIST_MODULE_LOGEMENT_LIST_ONLY ){
				return R.drawable.ic_batiment;
			}else if(listType==Constant.LIST_MODULE_MENAGE || listType==Constant.LIST_MODULE_MENAGE_LIST_ONLY ){
				return R.drawable.ic_menage;
			}else if(listType==Constant.LIST_MODULE_EMIGRE || listType==Constant.LIST_MODULE_EMIGRER_LIST_ONLY ){
				return R.drawable.ic_emigre;
			}else if(listType==Constant.LIST_MODULE_DECES || listType==Constant.LIST_MODULE_DECES_LIST_ONLY ){
				return R.drawable.ic_deces;
			}else if(listType==Constant.LIST_MODULE_INDIVIDU_MENAGE || listType==Constant.LIST_MODULE_INDIVIDU_LIST_ONLY
					|| listType==Constant.LIST_MODULE_INDIVIDU_LOGEMENT_COLLECTIF){
				return R.drawable.ic_individu;
			}else{
				return R.drawable.placeholder;
			}
		}

	}
	//endregion

	//Interface on row click listener
	public interface OnItemClickListener {
		void onItemClick(RowDataListModel entity);
		void onItemModuleClick(RowDataListModel entity);
		void onItemMenuClick(RowDataListModel entity);
	}

	public interface OnMenuItemClickListener{
		void onMenuItemRekomanse(RowDataListModel row);
		void onMenuItemKontinye(RowDataListModel row);
		//void onMenuItemModuleMenu(RowDataListModel row);
	}
}
