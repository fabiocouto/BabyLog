package model;

/**
 * Created by fabiodocoutooliveira on 9/20/17.
 */

import android.content.ContentUris;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

/**
 * Created by fabiodocoutooliveira on 9/14/17.
 */

public class ActivityLog implements Parcelable, Comparable<ActivityLog> {

    private Integer idActivity;
    private Long timeSpent;
    private String title;
    private String description;
    private String username;
    private String category;
    private String platform;
    private String localDate;
    private String localHour;

    public static final String AUTHORITY = "com.babylog";
    public static String[] columns = new String[]{ActivityLogEntityColumns._ID,
            ActivityLogEntityColumns.ID_ACTIVITY, ActivityLogEntityColumns.TITLE,
            ActivityLogEntityColumns.DESCRIPTION, ActivityLogEntityColumns.USERNAME,ActivityLogEntityColumns.LOCAL_DATE,
            ActivityLogEntityColumns.PLATFORM, ActivityLogEntityColumns.CATEGORY,
            ActivityLogEntityColumns.LOCAL_HOUR, ActivityLogEntityColumns.TIME_SPENT};

    public ActivityLog() {
    }

    // Parcelable start
    public ActivityLog(Parcel source) {
        idActivity = source.readInt();
        timeSpent = source.readLong();
        title = source.readString();
        description = source.readString();
        username = source.readString();
        category = source.readString();
        platform = source.readString();
        localDate = source.readString();
        localHour = source.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idActivity);
        dest.writeLong(timeSpent);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(username);
        dest.writeString(category);
        dest.writeString(platform);
        dest.writeString(localDate);
        dest.writeString(localHour);
    }

    public static final Creator<ActivityLog> CREATOR = new Parcelable.Creator<ActivityLog>() {
        @Override
        public ActivityLog[] newArray(int size) {
            return new ActivityLog[size];
        }

        @Override
        public ActivityLog createFromParcel(Parcel source) {
            return new ActivityLog(source);
        }
    };
    // Parcelable end

    public Integer getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(Integer idActivity) {
        this.idActivity = idActivity;
    }

    public Long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Long timeSpent) {
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

    public void setCategory(String cathegory) {
        this.category = cathegory;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityLog)) return false;

        ActivityLog activity = (ActivityLog) o;

        if (getIdActivity() != null ? !getIdActivity().equals(activity.getIdActivity()) : activity.getIdActivity() != null)
            return false;
        if (getTimeSpent() != null ? !getTimeSpent().equals(activity.getTimeSpent()) : activity.getTimeSpent() != null)
            return false;
        if (getTitle() != null ? !getTitle().equals(activity.getTitle()) : activity.getTitle() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(activity.getDescription()) : activity.getDescription() != null)
            return false;
        if (getUsername() != null ? !getUsername().equals(activity.getUsername()) : activity.getUsername() != null)
            return false;
        if (getCategory() != null ? !getCategory().equals(activity.getCategory()) : activity.getCategory() != null)
            return false;
        if (getPlatform() != null ? !getPlatform().equals(activity.getPlatform()) : activity.getPlatform() != null)
            return false;
        if (getLocalDate() != null ? !getLocalDate().equals(activity.getLocalDate()) : activity.getLocalDate() != null)
            return false;
        return getLocalHour() != null ? getLocalHour().equals(activity.getLocalHour()) : activity.getLocalHour() == null;

    }


    @Override
    public int hashCode() {
        int result = getIdActivity() != null ? getIdActivity().hashCode() : 0;
        result = 31 * result + (getTimeSpent() != null ? getTimeSpent().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getPlatform() != null ? getPlatform().hashCode() : 0);
        result = 31 * result + (getLocalDate() != null ? getLocalDate().hashCode() : 0);
        result = 31 * result + (getLocalHour() != null ? getLocalHour().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Activity{" +
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


    @Override
    public int compareTo(@NonNull ActivityLog activityLog) {
        return getIdActivity().compareTo(activityLog.getIdActivity());
    }


    public static final class ActivityLogEntityColumns implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY + "/babylog");
        // Mime Type para todos os Logs
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.babylogs";
        // Mime Type para uma unico BabyLog
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.babylog";
        // OrdenaÁ„o default para inserir no order by
        public static final String DEFAULT_SORT_ORDER = "_id ASC";
        public static final String _ID = "_id";
        public static final String ID_ACTIVITY = "idactivity";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String USERNAME = "username";
        public static final String PLATFORM = "platform";
        public static final String CATEGORY = "category";
        public static final String LOCAL_DATE = "local_date";
        public static final String LOCAL_HOUR = "local_hour";
        public static final String TIME_SPENT = "time_spent";

        private ActivityLogEntityColumns() {
        }

        // Metodo que constroi uma Uri para um BIKESPOT especifico, com o seu id
        public static Uri getUriId(long id) {

            Uri uriBikeSpots = ContentUris.withAppendedId(ActivityLog.ActivityLogEntityColumns.CONTENT_URI, id);
            return uriBikeSpots;
        }
    }
}
