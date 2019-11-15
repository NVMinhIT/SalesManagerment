package com.example.salesmanagerment.base.adapters;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmanagerment.base.listeners.IOnItemClickListener;
import com.example.salesmanagerment.base.listeners.IOnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Class base cho các adapter dành cho recyclerView
 *
 *
 * @param <T>
 */
public abstract class ListAdapter<T,G> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected List<T> mListData;
    protected IOnItemClickListener<G> mOnClickListener;
    protected IOnItemLongClickListener<G> mOnLongClickListener;

    public ListAdapter<T, G> setOnClickListener(IOnItemClickListener<G> onClickListener) {
        mOnClickListener = onClickListener;
        return this;
    }

    public ListAdapter<T, G> setOnLongClickListener(IOnItemLongClickListener<G> onLongClickListener) {
        mOnLongClickListener = onLongClickListener;
        return this;
    }

    /**
     * Là phương thức khởi tạo cho ListAdapter
     *
     *
     * @param context là được truyền tới từ context nơi khởi tạo thể hiện của lớp
     */
    public ListAdapter(Context context) {
        mContext = context;
        mListData = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mListData != null ? mListData.size() : 0;
    }

    /**
     * Phương thức lấy danh sách dữ liệu hiện tại
     *
     *
     * @return danh sách dữ liệu
     */
    public List<T> getListData() {
        return mListData;
    }

    /**
     * Phương thức truyền dữ liệu cho danh sách
     *
     *
     * @param listData danh sách dữ liệu được truyền vào
     */
    public void setListData(List<T> listData) {
        mListData.clear();
        mListData.addAll(listData);
        notifyDataSetChanged();
    }

    /**
     * Phương thức thêm dữ liệu cho danh sách dữ liệu đã có
     *
     *
     * @param listData danh sách dữ liệu được thêm vào
     */
    public void addData(List<T> listData) {
        mListData.addAll(listData);
        notifyDataSetChanged();
    }

    /**
     * Phương thức xóa dữ liệu của danh sách
     *
     */
    public void clearData() {
        if (mListData != null) {
            mListData.clear();
            mListData = new ArrayList<>();
            notifyDataSetChanged();
        }
    }

    /**
     * Phương thức lấy item tại vị trí position
     *
     *
     * @param position là vị trí muốn lấy
     * @return item của list
     */
    public T getItem(int position) {
        return mListData.get(position);
    }

}
