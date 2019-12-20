package id.kardihaekal.ppdb.ui.formdaftar;

import android.content.Intent;
import android.os.Build.VERSION_CODES;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import id.kardihaekal.ppdb.R;
import id.kardihaekal.ppdb.wilayah.api.ApiClientWilayah;
import id.kardihaekal.ppdb.wilayah.api.ApiInterfaceWilayah;
import id.kardihaekal.ppdb.wilayah.model.DataWilayah;
import id.kardihaekal.ppdb.wilayah.model.RegionWilayah;
import id.kardihaekal.ppdb.wilayah.model.UniqueCodeWilayah;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DuaActivity extends AppCompatActivity {

  private EditText edtAlamatLengkap, edtKodePos;
  private Spinner edtPropinsi, edtKabupaten, edtKecamatan, edtKelurahan;
  private Button btnSelanjutnya;
  Animation animation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dua);

    initialized();
    OnClick();
    other();
    loadUniqueCode();
  }

  private void other() {

  }

  private void OnClick() {
    btnSelanjutnya.setOnClickListener(new OnClickListener() {
      @RequiresApi(api = VERSION_CODES.LOLLIPOP)
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(DuaActivity.this, TigaActivity.class);
        startActivity(intent);
      }
    });
  }

  private void initialized() {
    edtAlamatLengkap = findViewById(R.id.input_alamat_lengkap);
    edtPropinsi = findViewById(R.id.input_propinsi);
    edtKabupaten = findViewById(R.id.input_kota);
    edtKecamatan = findViewById(R.id.input_kecamatan);
    edtKelurahan = findViewById(R.id.input_kelurahan);
    edtKodePos = findViewById(R.id.input_kodepos);
    btnSelanjutnya = findViewById(R.id.button_save2);
  }

  public void loadUniqueCode() {
    ApiInterfaceWilayah apiService = ApiClientWilayah.getClient().create(ApiInterfaceWilayah.class);
    Call<UniqueCodeWilayah> call = apiService.getUniqueCode();

    call.enqueue(new Callback<UniqueCodeWilayah>() {
      @Override
      public void onResponse(Call<UniqueCodeWilayah> call, Response<UniqueCodeWilayah> response) {
        String code = "MeP7c5ne" + response.body().getUniqueCode();
        loadProvinceList(code);
      }

      @Override
      public void onFailure(Call<UniqueCodeWilayah> call, Throwable t) {

      }
    });
  }

  public void loadProvinceList(final String code) {
    ApiInterfaceWilayah apiService = ApiClientWilayah.getClient().create(ApiInterfaceWilayah.class);

    Call<DataWilayah> call = apiService.getProvinceList(code);

    call.enqueue(new Callback<DataWilayah>() {
      @Override
      public void onResponse(Call<DataWilayah> call, Response<DataWilayah> response) {
        final List<RegionWilayah> daftarProvinsi = response.body().getData();

        // masukkan daftar provinsi ke list string
        List<String> provs = new ArrayList<>();
        // isi data pertama dengan string 'Silakan Pilih!'
        provs.add(0, getString(R.string.txt_please_slct));
        for (int i = 0; i < daftarProvinsi.size(); i++) {
          provs.add(daftarProvinsi.get(i).getName());
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(DuaActivity.this,
            android.R.layout.simple_spinner_item, provs);
        edtPropinsi.setAdapter(adapter);

        edtPropinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (!edtPropinsi.getSelectedItem().toString()
                .equals(getString(R.string.txt_please_slct))) {
              long idProv = daftarProvinsi.get(position - 1).getId();
              loadKabupatenList(code, idProv);
            }
          }


          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
        });
      }

      @Override
      public void onFailure(Call<DataWilayah> call, Throwable t) {

      }
    });
  }

  public void loadKabupatenList(final String code, final long idProv) {
    edtKecamatan.setAdapter(null);
    edtKelurahan.setAdapter(null);
    ApiInterfaceWilayah apiService = ApiClientWilayah.getClient().create(ApiInterfaceWilayah.class);
    Call<DataWilayah> call = apiService.getKabupatenList(code, idProv);

    call.enqueue(new Callback<DataWilayah>() {
      @Override
      public void onResponse(Call<DataWilayah> call, Response<DataWilayah> response) {
        final List<RegionWilayah> daftarKabupaten = response.body().getData();

        // masukkan daftar kabupaten ke list string
        List<String> kabs = new ArrayList<>();
        // isi data pertama dengan string 'Silakan Pilih!'
        kabs.add(0, getString(R.string.txt_please_slct));
        for (int i = 0; i < daftarKabupaten.size(); i++) {
          kabs.add(daftarKabupaten.get(i).getName());
        }

        // masukkan daftar kabupaten ke spinner
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(DuaActivity.this,
            android.R.layout.simple_spinner_item, kabs);
        edtKabupaten.setAdapter(adapter);

        edtKabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (!edtKabupaten.getSelectedItem().toString()
                .equals(getString(R.string.txt_please_slct))) {
              long idKab = daftarKabupaten.get(position - 1).getId();
              loadKecamatanList(code, idKab);
            }
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
        });
      }

      @Override
      public void onFailure(Call<DataWilayah> call, Throwable t) {

      }
    });
  }

  public void loadKecamatanList(final String code, long idKab) {
    edtKelurahan.setAdapter(null);
    ApiInterfaceWilayah apiService = ApiClientWilayah.getClient().create(ApiInterfaceWilayah.class);
    Call<DataWilayah> call = apiService.getKecamatanList(code, idKab);

    call.enqueue(new Callback<DataWilayah>() {
      @Override
      public void onResponse(Call<DataWilayah> call, Response<DataWilayah> response) {
        final List<RegionWilayah> daftarKecamatan = response.body().getData();

        // masukkan daftar kecamatan ke list string
        List<String> kecs = new ArrayList<>();
        // isi data pertama dengan string 'Silakan Pilih!'
        kecs.add(0, getString(R.string.txt_please_slct));
        for (int i = 0; i < daftarKecamatan.size(); i++) {
          kecs.add(daftarKecamatan.get(i).getName());
        }

        // masukkan daftar kecamatan ke spinner
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(DuaActivity.this,
            android.R.layout.simple_spinner_item, kecs);
        edtKecamatan.setAdapter(adapter);

        edtKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (!edtKecamatan.getSelectedItem().toString()
                .equals(getString(R.string.txt_please_slct))) {
              long idKec = daftarKecamatan.get(position - 1).getId();
              loadKelurahanList(code, idKec);
            }
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
        });
      }

      @Override
      public void onFailure(Call<DataWilayah> call, Throwable t) {

      }
    });
  }

  public void loadKelurahanList(final String code, final long idKec) {
    ApiInterfaceWilayah apiService = ApiClientWilayah.getClient().create(ApiInterfaceWilayah.class);
    Call<DataWilayah> call = apiService.getKelurahanList(code, idKec);

    call.enqueue(new Callback<DataWilayah>() {
      @Override
      public void onResponse(Call<DataWilayah> call, Response<DataWilayah> response) {
        final List<RegionWilayah> daftarKelurahan = response.body().getData();

        // masukkan daftar kelurahan ke list string
        List<String> kels = new ArrayList<>();
        // isi data pertama dengan string 'Silakan Pilih!'
        kels.add(0, getString(R.string.txt_please_slct));
        for (int i = 0; i < daftarKelurahan.size(); i++) {
          kels.add(daftarKelurahan.get(i).getName());
        }

        // masukkan daftar kelurahan ke spinner
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(DuaActivity.this,
            android.R.layout.simple_spinner_item, kels);
        edtKelurahan.setAdapter(adapter);
      }

      @Override
      public void onFailure(Call<DataWilayah> call, Throwable t) {

      }
    });
  }
}
