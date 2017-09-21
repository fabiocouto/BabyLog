package dto;

/**
 * Created by fabiodocoutooliveira on 9/14/17.
 */

public class ActivityDTO {

    private Integer idActivity;
    private Integer timeSpent;
    private String title;
    private String description;
    private String username;
    private String category;
    private String platform;
    private String localDate;
    private String localHour;


    public Integer getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String getLocalHour() {
        return localHour;
    }

    public void setLocalHour(String localHour) {
        this.localHour = localHour;
    }

    @Override
    public String toString() {
        return "ActivityDTO{" +
                "idActivity=" + idActivity +
                ", timeSpent=" + timeSpent +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                ", category='" + category + '\'' +
                ", platform='" + platform + '\'' +
                ", localDate='" + localDate + '\'' +
                ", localHour='" + localHour + '\'' +
                '}';
    }
}
