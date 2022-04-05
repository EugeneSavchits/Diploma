package models;


import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Case {
    private String title;
    @SerializedName(value = "priority_id")
    private int priorityId;

}
