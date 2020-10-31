package com.guru.ticketcollector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Ticketgenrate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] Stops = { "Hebbal", "Tinfactory", "HSR layour", "Silkbord", "BTM layout" , "Banashankri"};

    Button paynow;

    String source= "";
    String Destination= "";
    String Total = "";

    LinearLayout ticketgen,ticketdisplay;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_genrator);

        ticketgen = (LinearLayout)findViewById(R.id.liner_layout_ticket_gen);
                ticketdisplay = (LinearLayout)findViewById(R.id.liner_layout_ticket_Display);
        ticketdisplay.setVisibility(View.GONE);
        Spinner spinstart = findViewById(R.id.spinner_starting);
        Spinner spinend = findViewById(R.id.spinner_ending);

        spinstart.setOnItemSelectedListener(this);

        spinend.setOnItemSelectedListener(this);

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Stops);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinstart.setAdapter(ad);
        spinend.setAdapter(ad);

        EditText Et = (EditText)findViewById(R.id.editText_number);
        Et.setText("1");
        Total = Et.getText().toString();

        paynow = (Button)findViewById(R.id.button_pay);
        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialogButtonClicked(view,source,Destination,Total);
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getId()==R.id.spinner_starting){
            System.out.println(" WOW that was selected ended " +adapterView.getItemAtPosition(i).toString());

            source = adapterView.getItemAtPosition(i).toString();
        }else if (adapterView.getId()==R.id.spinner_ending) {
            Destination = adapterView.getItemAtPosition(i).toString();
            System.out.println(" WOW that was selected starting " + adapterView.getItemAtPosition(i).toString());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void showAlertDialogButtonClicked(View view,String source, String Destination, String Total)
    {

        // Create an alert builder
        AlertDialog.Builder builder
                = new AlertDialog.Builder(this);
        builder.setTitle("Name");

        // set the custom layout
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.payment_alert,
                        null);
        builder.setView(customLayout);

       final AlertDialog dialog
                = builder.create();
        // add a button
        CardView googlepay = customLayout.findViewById(R.id.cardview1);
        CardView phonepay = customLayout.findViewById(R.id.cardview2);
        CardView paytm = customLayout.findViewById(R.id.cardview3);

        googlepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ticketgen.setVisibility(view.GONE);
                ticketdisplay.setVisibility(view.VISIBLE);
            }
        });

        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ticketgen.setVisibility(view.GONE);
                ticketdisplay.setVisibility(view.VISIBLE);
            }
        });
        phonepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ticketgen.setVisibility(view.GONE);
                ticketdisplay.setVisibility(view.VISIBLE);
            }
        });


        // create and show
        // the alert dialog

        dialog.show();
    }

}
