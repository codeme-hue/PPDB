package id.kardihaekal.ppdb;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import id.kardihaekal.ppdb.account.login.LoginActivity;

/** Class Untuk Splash Screen**/

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
      }
    },3000);
  }
}
