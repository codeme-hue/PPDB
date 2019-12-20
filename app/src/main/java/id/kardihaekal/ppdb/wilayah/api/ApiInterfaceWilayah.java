package id.kardihaekal.ppdb.wilayah.api;

import id.kardihaekal.ppdb.wilayah.model.DataWilayah;
import id.kardihaekal.ppdb.wilayah.model.UniqueCodeWilayah;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterfaceWilayah {

    @GET("poe")
    Call<UniqueCodeWilayah> getUniqueCode();

    @GET("{code}/m/wilayah/provinsi")
    Call<DataWilayah> getProvinceList(@Path("code") String code);

    @GET("{code}/m/wilayah/kabupaten")
    Call<DataWilayah> getKabupatenList(@Path("code") String code, @Query("idpropinsi") long idProv);

    @GET("{code}/m/wilayah/kecamatan")
    Call<DataWilayah> getKecamatanList(@Path("code") String code, @Query("idkabupaten") long idKab);

    @GET("{code}/m/wilayah/kelurahan")
    Call<DataWilayah> getKelurahanList(@Path("code") String code, @Query("idkecamatan") long idKec);
}
