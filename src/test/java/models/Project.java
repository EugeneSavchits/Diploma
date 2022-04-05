package models;

import com.google.gson.annotations.SerializedName;
import enums.ProjectType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    private String name;
    private String announcement;
    @SerializedName(value = "show_announcement")
    private boolean isShowAnnouncement;
    @SerializedName(value = "suite_mode")
    private ProjectType typeOfProject;
    @SerializedName(value = "is_completed")
    private boolean isCompleted;

//    public Project() {
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAnnouncement() {
//        return announcement;
//    }
//
//    public void setAnnouncement(String announcement) {
//        this.announcement = announcement;
//    }
//
//    public boolean isShowAnnouncement() {
//        return isShowAnnouncement;
//    }
//
//    public void setShowAnnouncement(boolean showAnnouncement) {
//        isShowAnnouncement = showAnnouncement;
//    }
//
//    public ProjectType getTypeOfProject() {
//        return typeOfProject;
//    }
//
//    public void setTypeOfProject(ProjectType typeOfProject) {
//        this.typeOfProject = typeOfProject;
//    }
//
//    public boolean isCompleted() {
//        return isCompleted;
//    }
//
//    public void setCompleted(boolean completed) {
//        isCompleted = completed;
//    }
}
