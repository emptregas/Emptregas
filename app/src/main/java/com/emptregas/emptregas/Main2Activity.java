package com.emptregas.emptregas;

import android.content.DialogInterface;

import com.emptregas.emptregas.fragment.FormFragment;
import com.emptregas.emptregas.fragment.Instruction;
import com.emptregas.emptregas.fragment.TextFragment;

import java.util.ArrayList;
import java.util.List;

import ivb.com.materialstepper.progressMobileStepper;


public class Main2Activity extends progressMobileStepper {

    List<Class> stepperFragmentList = new ArrayList<>();


    @Override
    public List<Class> init() {
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(FormFragment.class);
        stepperFragmentList.add(Instruction.class);
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(FormFragment.class);
        stepperFragmentList.add(Instruction.class);
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(FormFragment.class);
        stepperFragmentList.add(Instruction.class);
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(FormFragment.class);
        stepperFragmentList.add(Instruction.class);
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(FormFragment.class);
        stepperFragmentList.add(Instruction.class);
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(FormFragment.class);
        stepperFragmentList.add(Instruction.class);
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(TextFragment.class);
        stepperFragmentList.add(FormFragment.class);
        stepperFragmentList.add(Instruction.class);
        stepperFragmentList.add(TextFragment.class);

        return stepperFragmentList;
    }


    @Override
    public void onStepperCompleted() {
        showCompletedDialog();
    }

    protected void showCompletedDialog(){
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(
                Main2Activity.this);

        // set title
        alertDialogBuilder.setTitle("Hooray");
        alertDialogBuilder
                .setMessage("We've completed the stepper")
                .setCancelable(true)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                    }
                });

        // create alert dialog
        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }
}