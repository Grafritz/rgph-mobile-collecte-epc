package ht.ihsi.rgph.mobile.epc.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.epc.constant.Constant;
import ht.ihsi.rgph.mobile.epc.models.QuestionReponseModel;
import ht.ihsi.rgph.mobile.epc.utilities.ToastUtility;

/**
 * Created by JFDuverseau on 5/15/2017.
 */
public class RadioListAdapter extends RecyclerView.Adapter<RadioListAdapter.DetailRowViewHolder> {

    public int mSelectedItem = -1;
    private List<QuestionReponseModel> reponseModelList;
    private QuestionReponseModel reponseModelSelectionner=null;
    private Context context;
    private int typeEvenement;
    private LayoutInflater mInflater;
    private View.OnClickListener onClickListener;
    private OnItemClickListener onItemClickListener;
    private OnItemCheckedChangedListener onItemCheckedChangedListener;
    private OnBindViewHolderListener onBindViewHolderListener;
    //private OnMenuItemClickListener onMenuItemClickListener;
    public DetailRowViewHolder viewHolder;

    public RadioListAdapter(Context context, int typeEvenement, List<QuestionReponseModel> questionReponseModelList){
        this.reponseModelSelectionner=null;
        this.reponseModelList = questionReponseModelList;
        this.context = context;
        this.typeEvenement = typeEvenement;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public DetailRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_questionnaire, null);
        DetailRowViewHolder detailRowViewHolder= new DetailRowViewHolder(view, this.typeEvenement);
        return detailRowViewHolder;
    }

    @Override
    public void onBindViewHolder(DetailRowViewHolder viewHolder, int position) {
        QuestionReponseModel row = reponseModelList.get(position);
        viewHolder.bind(row);
        viewHolder.radioButton.setChecked(position == mSelectedItem);
        this.viewHolder = viewHolder;

        if(mSelectedItem == position) {
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
            reponseModelSelectionner = viewHolder.getReponseModelSelectionner();
            reponseModelSelectionner = row;

        }//else
        //    viewHolder.itemView.setBackgroundColor(Color.RED);

        //if (onBindViewHolderListener != null) {
        //    onBindViewHolderListener.onBindViewHolder(reponseModelList);
        //}
        //if( viewHolder.radioButton.isChecked() ){
        //    reponseModelSelectionner = reponseModelList.get(position);
        //}
        //reponseModelSelectionner = viewHolder.getReponseModelSelectionner();
    }

    //public void setOnBindViewHolderListener(OnItemClickListener listener) {
    //    this.onItemClickListener = listener;
    //}

    public void setOnItemClickListener(OnItemClickListener listener) {
        if( this.typeEvenement != Constant.ACTION_AFFICHER ) {
            this.onItemClickListener = listener;
        }
    }

    public void setOnItemCheckedChangedListener(OnItemCheckedChangedListener listener) {
        this.onItemCheckedChangedListener = listener;
    }

    public QuestionReponseModel getReponseModelSelectionner() {
        return reponseModelSelectionner;
    }
    public void setReponseModelSelectionner(QuestionReponseModel reponseModelSelectionner){
        this.reponseModelSelectionner = reponseModelSelectionner;
    }
    //region GETTER / SETTER
    public List<QuestionReponseModel> getReponseModelList() {
        return reponseModelList;
    }

    //endregion

    @Override
    public int getItemCount() {
        return (null!= reponseModelList ? reponseModelList.size(): 0);
    }

    public void setFilter(List<QuestionReponseModel> filteredList){
        reponseModelList =new ArrayList<QuestionReponseModel>();
        reponseModelList.addAll(filteredList);
        notifyDataSetChanged();
    }

    public boolean tryMoveSelection(RecyclerView.LayoutManager layoutManager, int direction) {
        try {
            int nextSelectItem = mSelectedItem + direction;
            int itemCount = getItemCount();
            //ToastUtility.LogCat("E", "tryMoveSelection() nextSelectItem : " + nextSelectItem + " \n|  direction : " + direction
            //        + " \n|  mSelectedItem : " + mSelectedItem
            //        + " \n|  itemCount : " + itemCount);
            // If still within valid bounds, move the selection, notify to redraw, and scroll
            if (nextSelectItem >= 0 && nextSelectItem < itemCount) {
                //ToastUtility.LogCat("W", "tryMoveSelection() nextSelectItem : " + nextSelectItem + " |  getItemCount : " + itemCount);
                //ToastUtility.LogCat("E", "tryMoveSelection() if (nextSelectItem >= 0 && nextSelectItem < itemCount) {");
                //notifyItemChanged(mSelectedItem);
                mSelectedItem = nextSelectItem;
                //notifyItemChanged(mSelectedItem);
                layoutManager.scrollToPosition(mSelectedItem);
                //ToastUtility.LogCat("W", "2 tryMoveSelection() nextSelectItem : " + nextSelectItem + " |  getItemCount : " + itemCount);
                return true;
            } else {
                //ToastUtility.LogCat("W", "tryMoveSelection() else (nextSelectItem >= 0 && nextSelectItem < itemCount) \n nextSelectItem : " + nextSelectItem + " |  getItemCount : " + itemCount);
                //return false;
            }
        }catch (Exception ex) {
            ToastUtility.LogCat( "Exception: tryMoveSelection(): " + ex.getMessage() + " / toString: " + ex.toString());
            ex.printStackTrace();
        }
        return false;
    }

    public int getItemSelected(){

        return 1;
    }

    public class DetailRowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        public RadioButton radioButton;
        public TextView tReponse;
        public int typeEvenement;
        private QuestionReponseModel questionReponseModel;
        private QuestionReponseModel reponseModelSelectionner;

        public DetailRowViewHolder(View view, int typeEvenement){
            super(view);

            this.typeEvenement=typeEvenement;

            radioButton = (RadioButton) view.findViewById(R.id.radio);
            tReponse = (TextView) view.findViewById(R.id.text);
            view.setClickable(true);
            //view.animate();
            view.setOnClickListener(this);
            /*View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyItemRangeChanged(0, reponseModelList.size());
                }
            };
            itemView.setOnClickListener(clickListener);*/
            radioButton.setOnClickListener(this);
            //radioButton.setOnCheckedChangeListener(this);
        }

        //region GETTER SETTER
        public QuestionReponseModel getReponseModelSelectionner() {
            return reponseModelSelectionner;
        }

        public RadioButton getRadioButton() {
            return radioButton;
        }

        public void setRadioButton(RadioButton radioButton) {
            this.radioButton = radioButton;
        }

        public TextView gettReponse() {
            return tReponse;
        }

        public void setReponse(TextView tReponse) {
            this.tReponse = tReponse;
        }

        public QuestionReponseModel getQuestionReponseModel() {
            questionReponseModel.radioButton = getRadioButton();
            questionReponseModel.tReponse = gettReponse();
            return questionReponseModel;
        }

        public void setQuestionReponseModel(QuestionReponseModel questionReponseModel) {
            questionReponseModel.radioButton = getRadioButton();
            questionReponseModel.tReponse = gettReponse();
            this.questionReponseModel = questionReponseModel;
        }

        //endregion

        @Override
        public void onClick(View v) {
            //ToastUtility.ToastMessage(context, "Event onClick IN RadioListAdapter: " + questionReponseModel.getCodeQuestion()+ " :-: " + questionReponseModel.getLibelleReponse() );
            if( this.typeEvenement != Constant.ACTION_AFFICHER ) {
                reponseModelSelectionner = questionReponseModel;
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(questionReponseModel);
                }
                mSelectedItem = getAdapterPosition();
                notifyItemRangeChanged(0, reponseModelList.size());
                v.setBackgroundColor(Color.RED);
            }
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (isChecked) {
                //selectedStrings.add(tv.getText().toString());
                reponseModelSelectionner = questionReponseModel;
                if (onItemCheckedChangedListener != null) {
                    onItemCheckedChangedListener.OnItemCheckedChangedListener(questionReponseModel);
                }
                ToastUtility.LogCat("onCheckedChanged | " + questionReponseModel.toString());
            }else{
                //selectedStrings.remove(tv.getText().toString());
            }
            //mSelectedItem = getAdapterPosition();
        }

        public void bind(QuestionReponseModel row){
            row.radioButton = getRadioButton();
            row.tReponse = gettReponse();
            this.questionReponseModel = row;
            this.tReponse.setText(row.getLibelleReponse());
            //this.radioButton.setText(row.getLibelleReponse());
            if ( row.radioButton.isChecked() ) {
                reponseModelSelectionner = row;
            }
        }//
    }


    //Interface on questionReponseModel click listener
    public interface OnBindViewHolderListener {
        void onBindViewHolder(List<QuestionReponseModel> questionReponseModelList);
    }

    public interface OnItemClickListener {
        void onItemClick(QuestionReponseModel entity);
        //void onItemClick_2(QuestionReponseModel entity  );
        //void onItemMenuClick(QuestionReponseModel entity);
    }

    public interface OnItemCheckedChangedListener {
        void OnItemCheckedChangedListener(QuestionReponseModel entity);
    }

    /*public interface OnMenuItemClickListener{
        void onMenuItemModifye(QuestionReponseModel row);
        void onMenuItemKontinye(QuestionReponseModel row);
        void onMenuItemRapport(QuestionReponseModel row);
    }*/
}
