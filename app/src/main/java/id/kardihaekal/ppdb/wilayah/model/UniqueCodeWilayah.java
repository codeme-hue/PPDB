package id.kardihaekal.ppdb.wilayah.model;

import com.google.gson.annotations.SerializedName;

public class UniqueCodeWilayah {
    @SerializedName("token")
    public String uniqueCode;

    @SerializedName("code")
    public String requestCode;

    @SerializedName("success")
    public String successSign;

    public UniqueCodeWilayah(String uniqueCode, String requestCode, String successSign) {
        this.uniqueCode = uniqueCode;
        this.requestCode = requestCode;
        this.successSign = successSign;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getSuccessSign() {
        return successSign;
    }

    public void setSuccessSign(String successSign) {
        this.successSign = successSign;
    }
}
