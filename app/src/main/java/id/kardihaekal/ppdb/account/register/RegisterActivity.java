package id.kardihaekal.ppdb.account.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import id.kardihaekal.ppdb.ui.FormActivity;
import id.kardihaekal.ppdb.R;
import id.kardihaekal.ppdb.util.PrefManager;


public class RegisterActivity extends AppCompatActivity {

  private Button buttonSignUp;
  private EditText editTextEmail;
  private EditText editTextPassword;
  private Toolbar toolbarReg;
  private Animation animation;
  private RelativeLayout relativeLayoutReg;
  private ProgressDialog pDialog;
  private String TAG = RegisterActivity.class.getSimpleName();

  //Belum di lanjutkan


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    initialized();
    onClick();
    actionBar();
    other();
  }

  private void other() {
    animation = AnimationUtils.loadAnimation(this,R.anim.lefttoright);
    relativeLayoutReg.setAnimation(animation);
    pDialog = new ProgressDialog(this);

  }

  private void actionBar() {
    setSupportActionBar(toolbarReg);
    getSupportActionBar().setTitle("");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void onClick() {
    buttonSignUp.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

          //save DataWilayah to Shared Preferences (PrefManager)
          new PrefManager(getApplicationContext()).setEmail(email);
          new PrefManager(getApplicationContext()).setName(password);
          Intent intent = new Intent(RegisterActivity.this, FormActivity.class);
          startActivity(intent);
          finish();
        } else {
          Toast.makeText(RegisterActivity.this, "Email atau Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        }

      }
    });
  }

  private void initialized() {
    buttonSignUp = findViewById(R.id.btnSignUp);
    editTextEmail = findViewById(R.id.edtRegisterEmail);
    editTextPassword = findViewById(R.id.edtRegisterPassword);
    toolbarReg = findViewById(R.id.regHeader);
    relativeLayoutReg = findViewById(R.id.rLayoutReg);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case android.R.id.home :
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
