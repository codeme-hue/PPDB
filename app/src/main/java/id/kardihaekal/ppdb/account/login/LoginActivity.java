package id.kardihaekal.ppdb.account.login;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.JsonObject;
import id.kardihaekal.ppdb.ui.FormActivity;
import id.kardihaekal.ppdb.MainActivity;
import id.kardihaekal.ppdb.R;
import id.kardihaekal.ppdb.account.login.model.Data;
import id.kardihaekal.ppdb.account.login.model.Login;
import id.kardihaekal.ppdb.account.login.model.User;
import id.kardihaekal.ppdb.account.register.RegisterActivity;
import id.kardihaekal.ppdb.util.BaseApiService;
import id.kardihaekal.ppdb.util.PrefManager;
import id.kardihaekal.ppdb.util.UtilsApi;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

  private ImageButton buttonRegister;
  private Button buttonLogin;
  private EditText editTextEmail;
  private EditText editTextPassword;
  private TextView textViewForgot;
  PrefManager prefManager;
  private ProgressDialog pDialog;
  private Dialog dialog;
  private String TAG = LoginActivity.class.getSimpleName();
  Context mContext;


  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    initialized();
    onClick();
    other();
  }

  private void other() {

    //skip Login Activity if DataWilayah PrefManager not empty
    prefManager = new PrefManager(this);
    if (!prefManager.getEmail().isEmpty()) {
      startActivity(new Intent(getApplicationContext(), MainActivity.class));
      finish();
    }
    pDialog = new ProgressDialog(this);

  }

  private void onClick() {
    buttonRegister.setOnClickListener(new OnClickListener() {

      @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
      @Override
      public void onClick(View v) {
        Intent intent   = new Intent(LoginActivity.this, RegisterActivity.class);
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
        startActivity(intent,activityOptions.toBundle());
      }
    });
    buttonLogin.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
          signIn(email, password);
        } else {
          Toast.makeText(LoginActivity.this, "Email atau Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        }

      }

    });
  }

  private void signIn(final String email, final String password) {
    pDialog.setMessage("Mohon tunggu sebentar...");
    pDialog.setCanceledOnTouchOutside(false);
    pDialog.show();

    Login login = new Login(
        email,
        password
    );

    Retrofit.Builder builder = new Retrofit.Builder()
        .baseUrl(UtilsApi.BASE_URL_API)
        .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    BaseApiService baseApiService = retrofit.create(BaseApiService.class);
    Call<JsonObject> call = baseApiService.loginAccount(login);

    call.enqueue(new Callback<JsonObject>() {
      @Override
      public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

        if (response.isSuccessful()) {
          Toast.makeText(LoginActivity.this, "Login berhasil!", Toast.LENGTH_SHORT).show();
          pDialog.dismiss();
          startActivity(new Intent(getApplicationContext(), FormActivity.class));

          try {

            Log.e(TAG, "onResponse: " + response.body().toString());
            JSONObject jsonObject = new JSONObject(response.body().toString());

            JSONObject data = jsonObject.getJSONObject("data");

            Data dataJson = new Data();

            //set Token
            dataJson.setToken(data.getString("token"));

            User userJson = new User();

            //set User DataWilayah
           // userJson.setId(data.getInt("id"));
            //userJson.setName(data.getString("name"));
            //userJson.setEmail(data.getString("email"));

            //save DataWilayah to Shared Preferences (PrefManager)
            new PrefManager(getApplicationContext()).setEmail(data.getString("email"));
            new PrefManager(getApplicationContext()).setName(data.getString("name"));

          } catch (JSONException e) {
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            pDialog.dismiss();
          }
        } else {
          try {
            JSONObject jObjError = new JSONObject(response.errorBody().string());
            Log.e(TAG, "message : "+ jObjError.getString("message"));

            Toast.makeText(LoginActivity.this, "Login gagal, harap cek email & password!", Toast.LENGTH_SHORT).show();
            pDialog.dismiss();
          } catch (Exception e) {
            Log.e(TAG, "message : "+ e.getMessage());
            pDialog.dismiss();
          }
        }
      }

      @Override
      public void onFailure(Call<JsonObject> call, Throwable t) {
        Toast.makeText(LoginActivity.this, "Maaf, terjadi kesalahan!", Toast.LENGTH_SHORT).show();
        pDialog.dismiss();
      }
    });
  }

  private void initialized() {
    buttonRegister = findViewById(R.id.btRegister);
    buttonLogin = findViewById(R.id.btnLogin);
    editTextEmail = findViewById(R.id.edtLoginEmail);
    editTextPassword = findViewById(R.id.edtLoginPassword);
    textViewForgot = findViewById(R.id.tvForgotPassword);
  }
}
