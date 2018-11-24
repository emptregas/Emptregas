package com.emptregas.emptregas.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.emptregas.emptregas.R;
import com.emptregas.emptregas.adapter.ViewPagerAdapter;
import com.emptregas.emptregas.interactor.AuthInnerInteractor;
import com.emptregas.emptregas.interactor.AuthMainInteractor;
import com.emptregas.emptregas.model.TokenManager;

public class AuthFragment extends Fragment {
    private String FRAG_TAG_FORGET_PASSWORD = "ForgetPassword";
    TokenManager tokenManager;
    private AuthMainInteractor mListener;
    private AuthInnerInteractor innerInteractor;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public AuthFragment() {
        // Required empty public constructor
    }


    public static AuthFragment newInstance(AuthMainInteractor mListener) {
        AuthFragment fragment = new AuthFragment();
        fragment.mListener = mListener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        innerInteractor = new AuthInnerInteractor() {
            @Override
            public void switchToSignIn() {
                 mListener.moveToMain();
            }

            @Override
            public void switchToSignUp() {

            }

            @Override
            public void switchToForgetPassword() {
                getChildFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.bottom_up, R.anim.bottom_down, R.anim.bottom_up, R.anim.bottom_down)
                        .add(R.id.authFrame, ForgetPasswordFragment.newInstance(innerInteractor), FRAG_TAG_FORGET_PASSWORD)
                        .addToBackStack(FRAG_TAG_FORGET_PASSWORD)
                        .commit();
            }

            @Override
            public void switchToMain() {
                mListener.moveToMain();
            }

            @Override
            public void popForgetPassword() {
                getChildFragmentManager().popBackStackImmediate();
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(SignInFragment.newInstance(innerInteractor), "Iniciar sesión");
        adapter.addFragment(SignUpFragment.newInstance(innerInteractor), "Registrar");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
