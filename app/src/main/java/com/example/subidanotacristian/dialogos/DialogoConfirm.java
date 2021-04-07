package com.example.subidanotacristian.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.subidanotacristian.R;
import com.example.subidanotacristian.fragments.FragmentUno;
import com.example.subidanotacristian.utils.Persona;

public class DialogoConfirm extends DialogFragment {

    Button btn_confirm;
    View view;
    deleteListener listener;
    Persona persona;

    public static DialogoConfirm newInstance(Persona persona) {

        Bundle args = new Bundle();
        args.putSerializable("key", persona);

        DialogoConfirm fragment = new DialogoConfirm();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (deleteListener) context;
        persona = (Persona) this.getArguments().getSerializable("key");

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        view = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialogo,null);
        alertDialog.setView(view);
        return alertDialog.create();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn_confirm = view.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSelectedRemove(persona);

            }
        });

    }

    public interface deleteListener{
         void onSelectedRemove(Persona persona);
    }

}
