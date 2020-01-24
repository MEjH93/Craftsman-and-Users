package com.example.mua.mobilekupacmajstor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

public class GuestMajstorActivity extends AppCompatActivity {

    private TableLayout TabelaMajstoraGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_majstor);

        TabelaMajstoraGuest = (TableLayout) findViewById(R.id.TabelaMajstoraGuest);

        TabelaMajstoraGuest.addView(GuestSearchActivity.tl, new TableLayout.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));

        Button b = new Button(this);
        b.setText("Nazad");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nazad_redirect();
            }
        });

        TabelaMajstoraGuest.addView(b, new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
    }

    public void nazad_redirect(){
        Intent i = new Intent(this,GuestSearchActivity.class);
        startActivity(i);
    }
}
