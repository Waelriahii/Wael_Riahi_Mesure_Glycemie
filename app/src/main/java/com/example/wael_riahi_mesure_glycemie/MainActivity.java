package com.example.wael_riahi_mesure_glycemie;

import static android.app.ProgressDialog.show;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button button;

    private SeekBar sbAge;

    private RadioButton rbtoui, rbtnon;

    private EditText etValeur;

    private TextView textAge;

    private TextView msg;

    private int age=0;

    private void init(){
        button = findViewById(R.id.button);
        sbAge = findViewById(R.id.sbAge);
        rbtoui = findViewById(R.id.rbtoui);
        etValeur = findViewById(R.id.etValeur);
        msg = findViewById(R.id.msg);
        textAge = findViewById(R.id.textAge);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        msg = findViewById(R.id.msg);
        init();

        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("information", "onProgressChanged" + progress);
                textAge.setText("Votre age:" + progress + "ans");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                consulterGlycemie();
            }
        });
    }
        private void consulterGlycemie(){
            String valeurString = etValeur.getText().toString().trim();
            if (valeurString.isEmpty()) {
                Toast.makeText(this,  "Veillez entrer le valeur mesuré" , Toast.LENGTH_SHORT).show();
                return;
            }
            double valeur = Double.parseDouble(valeurString);
            boolean aJeune = rbtoui.isChecked();
            String resultat;

            double min,max;

            //determination des intervalles normaux

            if (aJeune){
                if (age > 13){
                    min = 5.0;
                    max = 7.2;
                } else if (age >= 6) {
                    min = 5.0;
                    max = 10.0;
                } else {
                    min = 5.5;
                    max = 10.0;
                }
            }else {
                min = 0.0;
                max = 10.5;
            }
            //verification du niveau de glycémie

            if (valeur < min) {
                resultat = "le niveau de glycémie est tros bas";
            } else if (valeur > max) {
                resultat ="le niveau de glycémie est trop élevé";
            }else {
                resultat ="le niveau de glycémie est normal";
            }
            msg.setText(resultat);
        }

}
