package id.kardihaekal.ppdb.ui.formdaftar;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import id.kardihaekal.ppdb.R;

public class TigaActivity extends AppCompatActivity {

  private EditText edtNamaOrangTua, edtPekerjaanOrgTua;
  private EditText edtNoTelepon, edtnoHandphone, edtEmail;
  private Button btnSelanjutnya;
  Animation animation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tiga);

    initialized();
    OnClick();
    other();
  }

  private void other() {
  }

  private void OnClick() {
    btnSelanjutnya.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
  }

  private void initialized() {
    edtNamaOrangTua = findViewById(R.id.input_nama_orang_tua);
    edtPekerjaanOrgTua = findViewById(R.id.input_pekerjaan_orang_tua);
    edtNoTelepon = findViewById(R.id.input_telepon_rumah);
    edtnoHandphone = findViewById(R.id.input_handphone);
    edtEmail = findViewById(R.id.input_email);
    btnSelanjutnya = findViewById(R.id.button_save3);
  }
}
