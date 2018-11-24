package com.emptregas.emptregas.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Selection;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.emptregas.emptregas.R;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConfirmOrder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConfirmOrder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmOrder extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "ConfirmOrder";
    private   String distancia,dirRec,dirEnt = "";
    private Integer precio;

private RadioButton efectivo, transferencia, datafono, alEntrega, alRecoger;


    private TextInputLayout lytNomRec,lytNomEnt, lytTelRec, lytTelEnt;
    private EditText  nomEnt,nomRec,telEnt,telRec;
    private TextView crearOrden;


    private OnFragmentInteractionListener mListener;

    public ConfirmOrder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfirmOrder.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfirmOrder newInstance(String param1, String param2) {
        ConfirmOrder fragment = new ConfirmOrder();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm_order, container, false);

        //aca mapeamos la variables con los elementos de la vista
        efectivo = (RadioButton) view.findViewById(R.id.rbtEfectivo);
        transferencia = (RadioButton) view.findViewById(R.id.rbtTransferencia);
        datafono = (RadioButton) view.findViewById(R.id.rbtDatafono);
        alRecoger = (RadioButton) view.findViewById(R.id.radioButton4);
        alEntrega = (RadioButton) view.findViewById(R.id.radioButton5);
        TextView tvDirRec = (TextView) view.findViewById(R.id.txtDIrRec);
        TextView tvDirEnt = (TextView) view.findViewById(R.id.txtDIrEnt);
        TextView tvDistancia = (TextView) view.findViewById(R.id.txtDistancia);
        TextView tvPrecio = (TextView) view.findViewById(R.id.txtPrecio);
        crearOrden = (TextView) view.findViewById(R.id.txtCrearOrden);
        lytNomRec = (TextInputLayout) view.findViewById(R.id.lytNomRec);
        lytNomEnt = (TextInputLayout) view.findViewById(R.id.lytNomEnt);
        lytTelEnt = (TextInputLayout) view.findViewById(R.id.lytTelEnt);
        lytTelRec = (TextInputLayout) view.findViewById(R.id.lytTelRec);
        nomRec = (EditText) view.findViewById(R.id.edtNomRec);
        nomEnt = (EditText) view.findViewById(R.id.edtNomEnt);
        telRec = (EditText) view.findViewById(R.id.edtTelRec);
        telEnt = (EditText) view.findViewById(R.id.edtTelEnt);

        // Si se reciben datos del fragment anterior setea en los elementos
        if (getArguments() != null) {
            tvDirEnt.setText(getArguments().getString("bundleDestino"));
            tvDirRec.setText(getArguments().getString("bundleOrigen"));
            tvDistancia.setText(getArguments().getString("bundleDistancia"));
            tvPrecio.setText(Integer.toString(getArguments().getInt("bundlePrecio")));
        }else{
            Log.d("Bundle","getArguments es Null");
        }

        crearOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lytNomRec.setError(null);
                lytNomEnt.setError(null);
                lytTelRec.setError(null);
                lytTelEnt.setError(null);

                 int cursor = 0;
                if (TextUtils.isEmpty(nomRec.getText().toString())){
                    lytNomRec.setError("A quien contactamos para RECOGER?");
                    nomRec.requestFocus();
                    cursor = 1;
                }

                if (TextUtils.isEmpty(nomEnt.getText().toString())){
                    lytNomEnt.setError("A quien Contactamos para ENTREGAR");
                    if(cursor==0){
                        nomEnt.requestFocus();
                        cursor = 1;
                    }

                }

            }
        });
        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
