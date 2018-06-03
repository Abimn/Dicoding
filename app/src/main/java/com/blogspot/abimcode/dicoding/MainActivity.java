package com.blogspot.abimcode.dicoding;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /* Deklarasi semua komponen yang akan dimanipulasi */
    EditText edtLength, edtWidth, edtHeight;
    Button btnCalculate;
    TextView tvResult;

    /* Casting semua komponen yang telah kita deklarasi sebelumnya agar dapat dimanipulasi */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // menampilkan tampilan yang berasal dari layout

        /* Inisialisasi berdasarkan id pada lasyout */
        /* Objek EditText disesuaikan (Cast) dengan komponen EditText ber-ID di layout xml melalui method findbyid */
        edtLength = (EditText) findViewById(R.id.edtLength);
        edtWidth = (EditText) findViewById(R.id.edtWidth);
        edtHeight = (EditText) findViewById(R.id.edtHeight);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        tvResult = (TextView) findViewById(R.id.tvResult);

        /* Membuat event klik sehingga dapat memlakukan aksi ketika pengguna meakukan sebuah klik */
        btnCalculate.setOnClickListener(this);

        /* Ini lanjutan dari method onSaveInstance */
        /* Pada onCreate inilah kita menggunakan nilai bundle yang telah kita simpan tadi
           pada onSaveInstanceState kemudian kita isikan kembali pada tvResult */
        if (savedInstanceState != null) {
            String hasil = savedInstanceState.getString(STATE_HASIL);
            tvResult.setText(hasil);
        }
    }

    private static final String STATE_HASIL = "state_hasil";

    /* Membuat method onSaveInstanceState, yang bertujuan kektika terjadi perganrian orientasi
       tidak hilang apa yang sudah kita inputkan pada EditText
       Perhitungan yang di tampilkan pada tvResult dimasukkam pada bundle dan di save isinya */
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_HASIL, tvResult.getText().toString());
        super.onSaveInstanceState(outState);
    }

    /* Membuat method onClik */
    public void onClick(View v) {
        if (v.getId() == R.id.btnCalculate) {
            String length = edtLength.getText().toString().trim();
            String width = edtWidth.getText().toString().trim();
            String height = edtHeight.getText().toString().trim();
            boolean isEmptyFields = false;

            // Kondisi jika editText kosong
            if (TextUtils.isEmpty(length)) {
                isEmptyFields = true;
                edtLength.setError("Data Tidak Boleh Kosong");
            }

            if (TextUtils.isEmpty(width)) {
                isEmptyFields = true;
                edtWidth.setError("Data Tidak Boleh Kosong");
            }

            if (TextUtils.isEmpty(height)) {
                isEmptyFields = true;
                edtHeight.setError("Data Tidak Boleh Kosong");
            }

            // Kondisi jika tidak kosong, maka akan melakukan perhitungan
            if (!isEmptyFields) {
                Double l = Double.parseDouble(length);
                Double w = Double.parseDouble(width);
                Double h = Double.parseDouble(height);
                Double Volume = l * w * h;
                // Menampilkan hasil pada TextView
                tvResult.setText(String.valueOf(Volume));
            }
        }
    }
}
