package com.example.salesmanagerment.screen.sales.listorder.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.salesmanagerment.R;

import java.util.Objects;
import java.util.UUID;

public class ConfirmCancelOrderDialog extends DialogFragment implements View.OnClickListener {
    private Button btnOk, btnCancel;
    private EditText edtReasonCancelOrder;
    private ConfirmCancelOrderCallBack mCallBack;

    public static ConfirmCancelOrderDialog newInstance(String orderID) {
        ConfirmCancelOrderDialog colorSelectorDialog = new ConfirmCancelOrderDialog();
        Bundle bundle = new Bundle();
//        colorSelectorDialog.setArguments(bundle);
        return colorSelectorDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_confirm_cancel_order, container, false);
        initViews(view);
        initEvents();
        return view;
    }

    /**
     * Phương thức gắn sự kiện cho view
     * Created_by Nguyễn Bá Linh on 10/04/2019
     */
    private void initEvents() {
        try {
            btnCancel.setOnClickListener(this);
            btnOk.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Phương thức tham chiếu, khởi tạo view
     * Created_by Nguyễn Bá Linh on 10/04/2019
     */
    private void initViews(View view) {
        btnOk = view.findViewById(R.id.btnOk);
        btnCancel = view.findViewById(R.id.btnCancel);
        edtReasonCancelOrder = view.findViewById(R.id.edtReasonCancelOrder);
        Bundle bundle = getArguments();
//        if (bundle != null) {
//            mUnit = bundle.getParcelable(AppConstants.ARG_UNIT);
//            if (mUnit != null) {
//                isEdit = true;
//                tvTitle.setText(R.string.edit_unit);
//                etUnitName.setText(mUnit.getUnitName());
//                etUnitName.setSelection(mUnit.getUnitName().length());
//            } else {
//                isEdit = false;
//                tvTitle.setText(R.string.add_new_unit);
//            }
//        }

    }

    /**
     * Chỉnh lại kích thước dialog khi hiển thị trên thiết bị
     * Created_by Nguyễn Bá Linh on 09/04/2019
     */
    @Override
    public void onResume() {
        super.onResume();
        try {
            Window window = getDialog().getWindow();
            int width = getResources().getDimensionPixelSize(R.dimen.color_dialog_width);
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            assert window != null;
            window.setLayout(width, height);
            window.setGravity(Gravity.CENTER);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Ghi đè hàm khởi tạo dialog, thiết lập dialog
     * Created_by Nguyễn Bá Linh on 10/04/2019
     *
     * @param savedInstanceState - gói dữ liệu
     * @return - dialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        try {
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            Objects.requireNonNull(dialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    // Disable Back key and Search key
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_SEARCH:
                            return true;
                        case KeyEvent.KEYCODE_BACK:
                            return true;
                        default:
                            return false;
                    }
                }
            });
            return dialog;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnOk:
//                String unitName = etUnitName.getText().toString().trim();
//                if (isEdit) {
//                    mCallBack.onUpdateUnit(new Unit(mUnit.getUnitId(), unitName));
//                } else {
//                    mCallBack.onAddUnit(new Unit(UUID.randomUUID().toString(), unitName));
//                }
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
            default:
                break;
        }
    }


    /**
     * Phương
     *
     * @param callBack
     */
    public void setCallBack(ConfirmCancelOrderCallBack callBack) {
        mCallBack = callBack;
    }

    /**
     * Call back được gọi để cập nhật/thêm đơn vị
     * Created_by Nguyễn Bá Linh on 09/04/2019
     */
    public interface ConfirmCancelOrderCallBack {
        void onCancelSuccess();
    }

}
