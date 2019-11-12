package com.example.salesmanagerment.screen.sales.createorder;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.salesmanagerment.R;

import java.util.Objects;

public class AddPersonDialogFragment extends DialogFragment implements View.OnClickListener {
    private SetPerson setPerson;
    private EditText editTextPerson;
    private Button btAddPerson, btCancel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SetPerson) {
            this.setPerson = (SetPerson) context;
        }

    }


    public static AddPersonDialogFragment getInstance() {
        AddPersonDialogFragment addPersonDialogFragment = new AddPersonDialogFragment();
        return addPersonDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_person, container, false);
        initView(view);
        return view.getRootView();
    }

    private void initView(View view) {
        editTextPerson = view.findViewById(R.id.et_Account_Person);
        btAddPerson = view.findViewById(R.id.btn_Yes_Person);
        btCancel = view.findViewById(R.id.btn_No_Person);
        btAddPerson.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Yes_Person:
                String account = editTextPerson.getText().toString();
                setPerson.setMyName(account);
                dismiss();
                break;
            case R.id.btn_No_Person:
                dismiss();
                break;
        }
    }

    public interface SetPerson {

        void setMyName(String string);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        try {
            WindowManager.LayoutParams params = Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).getAttributes();
            params.width = getResources().getDimensionPixelSize(R.dimen.dialog_with);
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            Objects.requireNonNull(getDialog().getWindow()).setAttributes(params);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }
}