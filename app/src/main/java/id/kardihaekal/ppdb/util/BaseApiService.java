package id.kardihaekal.ppdb.util;

import com.google.gson.JsonObject;
import id.kardihaekal.ppdb.account.login.model.Login;
import id.kardihaekal.ppdb.account.register.model.Register;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BaseApiService {

  /*Register*/
  @Headers("Content-Type: application/json")
  @POST("register")
  Call<Register> createAccount(@Body Register register);

  /*Login*/
  @Headers("Content-Type: application/json")
  @POST("login")
  Call<JsonObject> loginAccount(@Body Login login);
}