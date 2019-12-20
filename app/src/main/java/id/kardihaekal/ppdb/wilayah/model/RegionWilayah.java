package id.kardihaekal.ppdb.wilayah.model;

import com.google.gson.annotations.SerializedName;

public class RegionWilayah {

    @SerializedName("id")
    public long id;

    @SerializedName("name")
    public String name;

    public RegionWilayah(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
