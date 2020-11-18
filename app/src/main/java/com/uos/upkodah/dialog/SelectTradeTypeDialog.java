package com.uos.upkodah.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class SelectTradeTypeDialog extends DialogFragment {
    private static String[] tradeList = new String[]{"전세","월세"};
    private DialogInterface.OnClickListener listener;

    /**
     * @param listener : 이 Dialog의 결과를 반영할 View나 기타 위치를 설정하는 리스너 장착
     */
    public SelectTradeTypeDialog(DialogInterface.OnClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("매물 타입 선택")
                .setItems(tradeList, listener);

        return builder.create();
    }

    public static String indexToResult(int i){
        return tradeList[i];
    }
}
