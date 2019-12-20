package id.kardihaekal.ppdb.wilayah.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DataWilayah {
    @SerializedName("data")
    public List<RegionWilayah> data;

    public DataWilayah(List<RegionWilayah> data) {
        this.data = data;
    }

    public List<RegionWilayah> getData() {
        return data;
    }

    public void setData(List<RegionWilayah> data) {
        this.data = data;
    }
}
