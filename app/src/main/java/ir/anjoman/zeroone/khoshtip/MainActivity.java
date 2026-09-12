package ir.anjoman.zeroone.khoshtip;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnkhoshtip, btndoshman,btnbist6,danosh,golzar,moshali;
    PrefManager pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = new PrefManager(this);
        if (pref.getStart().equals("")) pref.start("00");
        TextView txtt = findViewById(R.id.textt);
        txtt.setText("SCORE :"+pref.getStart());
        Intent i = new Intent(MainActivity.this, SinglePlayerActivity.class);
        btnkhoshtip = findViewById(R.id.btnSingle);
        btndoshman = findViewById(R.id.btnMulti);
        btnbist6 = findViewById(R.id.bist6);
        danosh = findViewById(R.id.danosh);
        golzar = findViewById(R.id.golzar);
        moshali = findViewById(R.id.moshali);
        // تک نفره
        btnkhoshtip.setOnClickListener(v -> {
            pref.save("khoshtip");
            startActivity(i);
            finish();
        });
        // چند نفره
        btndoshman.setOnClickListener(v -> {
            pref.save("doshman");
            startActivity(i);
            finish();
        });
        btnbist6.setOnClickListener(v -> {
            pref.save("bist6");
            startActivity(i);
            finish();
        });
        danosh.setOnClickListener(v -> {
            pref.save("danosh");
            startActivity(i);
            finish();
        });
        golzar.setOnClickListener(v -> {
            pref.save("golzar");
            startActivity(i);
            finish();
        });
        moshali.setOnClickListener(v -> {
            pref.save("moshali");
            startActivity(i);
            finish();
        });
    }
}
