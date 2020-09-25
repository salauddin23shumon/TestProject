
package com.sss.testproject.listData.listDataModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sss.testproject.listData.adapter.RecyclerViewItem;

public class Results  extends RecyclerViewItem {

    @SerializedName("resources")
    @Expose
    private List<Resource> resources = null;

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

}
