package id.kardihaekal.ppdb.util;

public class UtilsApi {

  //isi base url api nya disini!
  public static final String BASE_URL_API = "https://iibf.web.com/api/";

  // Mendeklarasikan Interface BaseApiService
  public static BaseApiService getAPIService(){
    return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
  }
}
