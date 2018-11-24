package com.emptregas.emptregas.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.emptregas.emptregas.MainActivity;
import com.emptregas.emptregas.R;
import com.emptregas.emptregas.entities.AccessToken;
import com.emptregas.emptregas.entities.ApiError;
import com.emptregas.emptregas.interactor.AuthInnerInteractor;
import com.emptregas.emptregas.interfaces.ApiService;
import com.emptregas.emptregas.model.TokenManager;
import com.emptregas.emptregas.network.RetrofitBuilder;
import com.emptregas.emptregas.util.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {
    private AuthInnerInteractor innerInteractor;
    private EditText email, password, passwordConfirm,phone,name;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private final String TAG="SignUpFragment";
    ApiService service;
    Call<AccessToken> call;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance(AuthInnerInteractor innerInteractor) {
        SignUpFragment fragment = new SignUpFragment();
        fragment.innerInteractor = innerInteractor;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = RetrofitBuilder.createService(ApiService.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) view.findViewById((R.id.email2));
        password = (EditText) view.findViewById((R.id.password2));
        passwordConfirm = (EditText) view.findViewById((R.id.passwordConfirm2));
        phone = (EditText) view.findViewById((R.id.phone));
        name = (EditText) view.findViewById((R.id.name));

        progressDialog = new ProgressDialog(getContext());

        view.findViewById(R.id.signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"aqui deberia ejecutarse el codigo",Toast.LENGTH_LONG).show();
                registrarUsuario();

            }
        });


        return view;
    }
    private void registrarUsuario(){
     final    String email1 = email.getText().toString();
      final  String pass = password.getText().toString();
      final   String namer = name.getText().toString();
        final   String passConfirm = passwordConfirm.getText().toString();
        final   String telefono = phone.getText().toString();

        if(TextUtils.isEmpty(namer)||TextUtils.isEmpty(email1)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(telefono)){

            Toast.makeText(getContext(),"Faltan Algunos datos por ingresar, por favor verifique",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
            Toast.makeText(getContext(),"Email Invlido por favor verifique",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.equals(passConfirm)){
            Toast.makeText(getContext(),"Las Contraseñas no coinciden por favor verifique",Toast.LENGTH_SHORT).show();
            return;
        }

                 firebaseAuth.createUserWithEmailAndPassword(email1, pass)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        String uid = user.getUid();

                                        call = service.register(namer, email1, pass,uid);
                                        call.enqueue(new Callback<AccessToken>() {
                                            @Override
                                            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                                                Log.w(TAG, "onResponse: " + response);

                                                if (response.isSuccessful()) {
                                                    Log.d(TAG, "Cuenta creada satisfactoriamente!");
                                                    Log.w(TAG, "onResponse: " + response.body() );
                                                    startActivity(new Intent(getActivity(), MainActivity.class));
                                                    getActivity().finish();


                                                } else {
                                                    // If sign in fails, display a message to the user.
                                                     Toast.makeText(getContext(), "Authentication failed. en Juliapp ",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<AccessToken> call, Throwable t) {
                                                Log.w(TAG, "onFailure: " + t.getMessage());
                                            }
                                        });

                                        //   updateUI(user);
                                    } else {
                                        if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(getContext(), "Usuario ya Existe!",
                                                    Toast.LENGTH_SHORT).show();
                                        }else{
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(getContext(), "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                        //   updateUI(null);
                                    }
                                }
                            });

    }


}
