package com.icer.huobitrade.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.icer.huobitrade.R;

/**
 * Created by cljlo on 2018/1/21.
 */

public class InputDialog extends Dialog {

    TextView mTvTitle;
    EditText mEtInput;
    Button mBtnOk;
    Button mBtnCancel;

    private Callback mCallback;

    public InputDialog(@NonNull Context context) {
        super(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        setContentView(R.layout.dialog_input);
        mTvTitle = findViewById(R.id.tv_title);
        mEtInput = findViewById(R.id.et_input);
        mBtnOk = findViewById(R.id.btn_ok);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) {
                    mCallback.onOk(mEtInput.getText().toString());
                }
                dismiss();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show(String title, String hint, Callback callback) {
        mTvTitle.setText(title);
        mEtInput.setHint(hint);
        setCallback(callback);
        mEtInput.setText("");
        super.show();
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onOk(String input);
    }
}
