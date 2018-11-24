package com.emptregas.emptregas.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.emptregas.emptregas.R;
import com.emptregas.emptregas.interactor.AuthInnerInteractor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordFragment extends Fragment {
    private static final String TAG = "ForgetPassword";
    private AuthInnerInteractor innerInteractor;
    private Button btnRcovery;
    private EditText edtEmail;



    public ForgetPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        btnRcovery = (Button) view.findViewById(R.id.forgot_pass_send_mail_btn);
        edtEmail = (EditText) view.findViewById(R.id.forgot_pass_email_tv);

        btnRcovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(edtEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(),"Hemos enviado un email a tu correo, revisa y recupera tu contraseña pronto!",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getContext(),"Error con su email, por favor verfique e intente de Nuevo",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });



        view.findViewById(R.id.forgot_pass_back_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                innerInteractor.popForgetPassword();
            }
        });
        view.findViewById(R.id.forgot_pass_login_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                innerInteractor.popForgetPassword();
            }
        });
        return view;
    }

    public static ForgetPasswordFragment newInstance(AuthInnerInteractor innerInteractor) {
        ForgetPasswordFragment fragment = new ForgetPasswordFragment();
        fragment.innerInteractor = innerInteractor;
        return fragment;
    }
}
