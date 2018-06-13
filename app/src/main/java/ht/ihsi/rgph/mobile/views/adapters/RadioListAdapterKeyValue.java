package ht.ihsi.rgph.mobile.views.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.constant.Constant;
import ht.ihsi.rgph.mobile.models.KeyValueModel;
import ht.ihsi.rgph.mobile.utilities.ToastUtility;

/**
 * Created by JFDuverseau on 5/26/2017.
 */
public class RadioListAdapterKeyValue extends RecyclerView.Adapter<RadioListAdapterKeyValue.DetailRowViewHolder> {

    public int mSelectedItem = -1;
    private List<KeyValueModel> keyValueModelList;
    private Context context;
    private int typeEvenement;
    private int align;
    private LayoutInflater mInflater;
    private View.OnClickListener onClickListener;
    private OnItemClickListenerKeyValue onItemClickListenerKeyValue;
    private OnBindViewHolderListener onBindViewHolderListener;
    public DetailRowViewHolder viewHolder;

    public RadioListAdapterKeyValue(Context context, List<KeyValueModel> questionReponseModelList){
        this.keyValueModelList = questionReponseModelList;
        this.context = context;
        this.align = 0;
        this.mInflater = LayoutInflater.from(context);
    }
    public RadioListAdapterKeyValue(Context context, int typeEvenement, List<KeyValueModel> questionReponseModelList){
        this.keyValueModelList = questionReponseModelList;
        this.context = context;
        this.typeEvenement = typeEvenement;
        this.align = 0;
        this.mInflater = LayoutInflater.from(context);
    }
    public RadioListAdapterKeyValue(Context context, List<KeyValueModel> questionReponseModelList, int align){
        this.keyValueModelList = questionReponseModelList;
        this.context = context;
        this.align = align;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public DetailRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_questionnaire, null);
        DetailRowViewHolder detailRowViewHolder= new DetailRowViewHolder(view);
        return detailRowViewHolder;
    }

    @Override
    public void onBindViewHolder(DetailRowViewHolder viewHolder, int position) {
        viewHolder.bind(keyValueModelList.get(position));
        viewHolder.radioButton.setChecked(position == mSelectedItem);
        this.viewHolder = viewHolder;

        if(mSelectedItem == position) {
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }
    }

    public void setOnItemClickListener(OnItemClickListenerKeyValue listener) {
        if( this.typeEvenement != Constant.ACTION_AFFICHER ) {
            this.onItemClickListenerKeyValue = listener;
        }
    }

    //region GETTER / SETTER
    public List<KeyValueModel> getKeyValueModelList() {
        return keyValueModelList;
    }
    //endregion

    @Override
    public int getItemCount() {
        return (null!= keyValueModelList ? keyValueModelList.size(): 0);
    }

    public void setFilter(List<KeyValueModel> filteredList){
        keyValueModelList =new ArrayList<KeyValueModel>();
        keyValueModelList.addAll(filteredList);
        notifyDataSetChanged();
    }

    public boolean tryMoveSelection(RecyclerView.LayoutManager layoutManager, int direction) {
        try {
            int nextSelectItem = mSelectedItem + direction;
            int itemCount = getItemCount();
             // If still within valid bounds, move the selection, notify to redraw, and scroll
            if (nextSelectItem >= 0 && nextSelectItem < itemCount) {
               //notifyItemChanged(mSelectedItem);
                mSelectedItem = nextSelectItem;
                //notifyItemChanged(mSelectedItem);
                layoutManager.scrollToPosition(mSelectedItem);
                return true;
            } else {
                //ToastUtility.LogCat("W", "tryMoveSelection() else (nextSelectItem >= 0 && nextSelectItem < itemCount) \n nextSelectItem : " + nextSelectItem + " |  getItemCount : " + itemCount);
            }
        }catch (Exception ex) {
            ToastUtility.LogCat( "Exception: tryMoveSelection(): " + ex.getMessage() + " / toString: " + ex.toString());
            ex.printStackTrace();
        }
        return false;
    }

    public class DetailRowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public RadioButton radioButton;
        public TextView tReponse;
        private KeyValueModel keyValueModel;

        public DetailRowViewHolder(View view){
            super(view);

            radioButton = (RadioButton) view.findViewById(R.id.radio);
            tReponse = (TextView) view.findViewById(R.id.text);
            view.setClickable(true);
            //view.animate();
            view.setOnClickListener(this);
            /*View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyItemRangeChanged(0, keyValueModelList.size());
                }
            };
            itemView.setOnClickListener(clickListener);*/
            radioButton.setOnClickListener(this);
        }

        //region GETTER SETTER
        public RadioButton getRadioButton() {
            return radioButton;
        }

        public void setRadioButton(RadioButton radioButton) {
            this.radioButton = radioButton;
        }

        public TextView gettReponse() {
            return tReponse;
        }

        public void settReponse(TextView tReponse) {
            this.tReponse = tReponse;
        }

        public KeyValueModel getKeyValueModel() {
            //keyValueModel.radioButton = getRadioButton();
            //keyValueModel.tReponse = gettReponse();
            return keyValueModel;
        }

        public void setKeyValueModel(KeyValueModel keyValueModel) {
            //keyValueModel.radioButton = getRadioButton();
            //keyValueModel.tReponse = gettReponse();
            this.keyValueModel = keyValueModel;
        }
        //endregion

        @Override
        public void onClick(View v) {
            //ToastUtility.ToastMessage(context, "Event onClick IN RadioListAdapter: " + keyValueModel.getCodeQuestion()+ " :-: " + keyValueModel.getLibelleReponse() );
            if (onItemClickListenerKeyValue != null) {
                onItemClickListenerKeyValue.onItemClick(keyValueModel);
            }
            mSelectedItem = getAdapterPosition();
            notifyItemRangeChanged(0, keyValueModelList.size());

            v.setBackgroundColor(Color.RED);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        public void bind(KeyValueModel row){
            //row.radioButton = getRadioButton();
            //row.tReponse = gettReponse();
            this.keyValueModel = row;
            this.tReponse.setText(row.getValue());

            if (align==View.TEXT_ALIGNMENT_CENTER) {
                this.tReponse.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
            //this.radioButton.setText(row.getLibelleReponse());
        }//
    }


    //Interface on keyValueModel click listener
    public interface OnBindViewHolderListener {
        void onBindViewHolder(List<KeyValueModel> questionReponseModelList);
    }

    public interface OnItemClickListenerKeyValue {
        void onItemClick(KeyValueModel entity);
    }
}
