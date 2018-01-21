package com.icer.huobitrade.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.icer.huobitrade.R;


public class Input2Dialog extends Dialog {

    TextView mTvTitle;
    EditText mEtInput1;
    EditText mEtInput2;
    Button mBtnOk;
    Button mBtnCancel;

    private Callback mCallback;

    public Input2Dialog(@NonNull Context context) {
        super(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        setContentView(R.layout.dialog_input_2);
        mTvTitle = findViewById(R.id.tv_title);
        mEtInput1 = findViewById(R.id.et_input_1);
        mEtInput2 = findViewById(R.id.et_input_2);
        mBtnOk = findViewById(R.id.btn_ok);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) {
                    mCallback.onOk(mEtInput1.getText().toString(), mEtInput2.getText().toString());
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

    public void show(String title, String hint1, String hint2, Callback callback) {
        mTvTitle.setText(title);
        mEtInput1.setHint(hint1);
        mEtInput2.setHint(hint2);
        setCallback(callback);
        mEtInput1.setText("");
        mEtInput2.setText("");
        super.show();
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onOk(String input1, String input2);
    }
}
