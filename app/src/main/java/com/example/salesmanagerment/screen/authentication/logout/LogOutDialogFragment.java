package com.example.salesmanagerment.screen.authentication.logout;

import android.app.Dialog;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.screen.authentication.login.LoginActivity;
import com.example.salesmanagerment.utils.Navigator;

import java.util.Objects;

public class LogOutDialogFragment extends DialogFragment implements View.OnClickListener {

    private Button btnNo, btnYes;
    private Navigator navigator;

    public static LogOutDialogFragment newInstance() {
        return new LogOutDialogFragment();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_logout, container, false);
        initView(view);
        return view.getRootView();
    }

    private void initView(View view) {
        navigator = new Navigator(this);
        btnNo = view.findViewById(R.id.btn_No_LogOut);
        btnYes = view.findViewById(R.id.btn_Yes_LogOut);
        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_No_LogOut:
                dismiss();
                break;
            case R.id.btn_Yes_LogOut:
                navigator.startActivity(LoginActivity.class);
                dismiss();
                break;
            default:
                break;
        }
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
