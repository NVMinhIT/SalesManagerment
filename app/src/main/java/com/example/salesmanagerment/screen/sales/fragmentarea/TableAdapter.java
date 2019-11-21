package com.example.salesmanagerment.screen.sales.fragmentarea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.adapters.ListAdapter;
import com.example.salesmanagerment.data.model.entity.TableMappingCustom;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Constants;

import java.util.List;

public class TableAdapter extends ListAdapter<TableMappingCustom, TableMappingCustom> {

    private int WrongIndex = 10000;
    private int mLastSelectedPosition = WrongIndex;
    public String idTableMapping;

    public void setIdTableMapping(String idTableMapping) {
        this.idTableMapping = idTableMapping;
    }

    public TableAdapter(Context context, List<TableMappingCustom> tableMappingCustoms, String id) {
        super(context);
        mListData = tableMappingCustoms;
        idTableMapping = id;
        setSelectedIndex();

    }

//    String getIdTableMappingId() {
//        return mListData.get(mLastSelectedPosition).TableID;
//    }
//
//    String getNamTable() {
//        return mListData.get(mLastSelectedPosition).TableName;
//    }

    private void setSelectedIndex() {
//        if (idTableMapping != null) {
//            int size = mListData.size();
//            for (int i = 0; i < size; i++) {
//                if (idTableMapping.equals(mListData.get(i).AreaID)) {
//                    mLastSelectedPosition = i;
//                }
//            }
//        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //thay lay out
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_table_status, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        itemViewHolder.bind(mListData.get(i));
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameTable;
        ImageView imvIconTable;
        ImageView mImageButtonClick;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        /**
         * Phương thức tham chiếu, khởi tạo view
         *
         */
        private void initViews(View view) {
            //tìm view theo layout
            mImageButtonClick = view.findViewById(R.id.imv_click);
            tvNameTable = view.findViewById(R.id.tvNameTable);
            imvIconTable = view.findViewById(R.id.imvIconTable);


        }

        void bind(final TableMappingCustom tableMappingCustom) {
            //status 0 là màu xanh, 1 là ...
            if (tableMappingCustom != null) {
                    if (tableMappingCustom.isSelected == null){
                        tableMappingCustom.isSelected = false;
                    }
                if (tableMappingCustom.isSelected) {
                    mImageButtonClick.setImageResource(R.drawable.ic_tick);
                } else {
                    mImageButtonClick.setImageResource(0);
                }
                tvNameTable.setText(tableMappingCustom.TableName);
                if (tableMappingCustom.TableStatus == 0) {
                    imvIconTable.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_table));
                } else if (tableMappingCustom.TableStatus == 1) {
                    imvIconTable.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_table_ordering));

                } else if (tableMappingCustom.TableStatus == 2) {
                    imvIconTable.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_table_ordered));
                }

                this.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tableMappingCustom.TableStatus == Constants.TABLE_ARE_SERVING) {
                            CommonFunc.showToastWarning(R.string.table_are_serving);
                        } else if (tableMappingCustom.TableStatus == Constants.TABLE_RESERVE) {
                            CommonFunc.showToastWarning(R.string.table_reserve);
                        } else {
                            int newPosition = getAdapterPosition();
                            mListData.get(newPosition).isSelected = true;
                            notifyItemChanged(newPosition);
                            if (mLastSelectedPosition != WrongIndex) {
                                mListData.get(mLastSelectedPosition).isSelected = false;
                                notifyItemChanged(mLastSelectedPosition);
                            }
                            mLastSelectedPosition = newPosition;
                            mOnClickListener.onItemClick(tableMappingCustom);

                        }
                    }
                });
            }

        }
    }
}
