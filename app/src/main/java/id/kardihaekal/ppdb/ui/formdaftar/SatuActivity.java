package id.kardihaekal.ppdb.ui.formdaftar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build.VERSION_CODES;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.reginald.editspinner.EditSpinner;
import id.kardihaekal.ppdb.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SatuActivity extends AppCompatActivity {

  private EditSpinner edtKelamin;
  Calendar myCalendar = Calendar.getInstance();
  private int Gender = 0;
  public static final int GENDER_MALE = 0;
  public static final int GENDER_FEMALE = 1;
  private EditText edtTangggaLahir, edtNamaLengkap, edtNamaPanggilan;
  private EditText edtAgama, edtTempatLahir;
  private Button btnSelanjutnya;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_satu);

    initialized();
    OnClick();
    other();
  }

  private void other() {
    //Adapter Spinner Kelamin
    ListAdapter adapter = new ArrayAdapter<String>(this,
        R.layout.support_simple_spinner_dropdown_item,
        getResources().getStringArray(R.array.array_gender_options));
    edtKelamin.setAdapter(adapter);
    edtKelamin.setEditable(false);

  }

  private void OnClick() {
    edtTangggaLahir.setFocusableInTouchMode(false);
    edtTangggaLahir.setFocusable(false);
    edtTangggaLahir.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new DatePickerDialog(SatuActivity.this, date, myCalendar
            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
      }
    });

    btnSelanjutnya.setOnClickListener(new OnClickListener() {
      @RequiresApi(api = VERSION_CODES.LOLLIPOP)
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(SatuActivity.this, DuaActivity.class);
        startActivity(intent);
      }
    });
  }

  private void initialized() {
    edtTangggaLahir = findViewById(R.id.input_tanggal_lahir);
    edtKelamin = findViewById(R.id.input_kelamin);
    edtNamaLengkap = findViewById(R.id.input_nama_lengkap);
    edtNamaPanggilan = findViewById(R.id.input_nama_panggilan);
    edtAgama = findViewById(R.id.input_agama);
    edtTempatLahir = findViewById(R.id.input_tempat_lahir);
    btnSelanjutnya = findViewById(R.id.button_save1);
  }


  DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,
        int dayOfMonth) {
      // TODO Auto-generated method stub
      myCalendar.set(Calendar.YEAR, year);
      myCalendar.set(Calendar.MONTH, monthOfYear);
      myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
      setBirth();
    }

  };

  private void setBirth() {
    String myFormat = "dd MMMM yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

    edtTangggaLahir.setText(sdf.format(myCalendar.getTime()));
  }
}
