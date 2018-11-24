package com.emptregas.emptregas.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.emptregas.emptregas.R;
import com.emptregas.emptregas.MainActivity;
import com.emptregas.emptregas.entities.AccessToken;
import com.emptregas.emptregas.interactor.AuthInnerInteractor;
import com.emptregas.emptregas.interfaces.ApiService;
import com.emptregas.emptregas.model.FacebookManager;
import com.emptregas.emptregas.model.TokenManager;
import com.emptregas.emptregas.network.RetrofitBuilder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;


public class SignInFragment extends Fragment {
    private static final String TAG = "LoginSplashActivity";
    private AuthInnerInteractor innerInteractor;
    TextView texto;
    TextInputLayout email, password;
    Button signIn;
    ApiService service;
    TokenManager tokenManager;
    AwesomeValidation validator;
    Call<AccessToken> call;
    FirebaseUser user;
    FacebookManager facebookManager;
    private FirebaseAuth firebaseAuth;


    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance(AuthInnerInteractor innerInteractor) {
        SignInFragment fragment = new SignInFragment();
        fragment.innerInteractor = innerInteractor;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = RetrofitBuilder.createService(ApiService.class);
        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
    signIn = (Button) view.findViewById(R.id.signIn);
        email = (TextInputLayout) view.findViewById(R.id.email);
        password = (TextInputLayout) view.findViewById(R.id.password);
        tokenManager = com.emptregas.emptregas.TokenManager.getInstance(getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE));


       signIn.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                         loguearUsuario();
                                     }
                                 });
               view.findViewById(R.id.forgetPassword).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       innerInteractor.switchToForgetPassword();

                   }
               });
        view.findViewById(R.id.switchSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                innerInteractor.switchToSignUp();

            }
        });
               return view;
           }


    private void loguearUsuario(){
        String email1 = email.getEditText().getText().toString();
        String passw = password.getEditText().getText().toString();

        if(TextUtils.isEmpty(email1)){
            email.requestFocus();
            email.setError("Por favor ingrese su Email!");
          //  Toast.makeText(getContext(),"Por favor ingrese un Email Valido",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            email.requestFocus();
            email.setError("Por favor ingrese un Email Valido!");
            //Toast.makeText(getContext(), "Email Invlido por favor verifique", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(passw)){
            password.requestFocus();
            password.setError("Por favor ingrese su Contraseña!");
           // Toast.makeText(getContext(),"Por favor ingrese una Contraseña",Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email1, passw)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "BIENVENIDO!");
                            Toast.makeText(getContext(), "Informacion correcta, Espere un momento.",
                                    Toast.LENGTH_SHORT).show();
                            user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().finish();
                            //   updateUI(user);
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getContext(), "Usuario ya Existe!",
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getContext(), "Ocurrio un Error, por favor verifique sus datos e intentelo de nuevo",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


}
