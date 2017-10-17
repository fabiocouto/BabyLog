package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import model.ActivityLog;

/**
 * Created by fabiodocoutooliveira on 29/05/16.
 */



public class ActivityLogDAO {

    public static final String TABLE_BABY = "baby";
    public static final String TABLE_ACTIVITY = "activity";
    public static final String TABLE_SYNC_STATUS = "syncstatus";
    public static final String TABLE_SESSION = "session";
    public static final String DATABASE_PATH = "/data/data/com.babylog/databases/";
    private static final String TAG_DAO = "LOCAL_DAO: ";
    private static final String DATABASE_NAME = "babylog_db";
    private final Integer ONE = 1;

    public SQLiteDatabase db;

    public ActivityLogDAO() {

    }

    public ActivityLogDAO(Context ctx) {
        db = ctx.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
    }


    public void insertActivityLog(ActivityLog activityLog) {

        ContentValues values = new ContentValues();
        values.put(ActivityLog.ActivityLogEntityColumns.ID_ACTIVITY, activityLog.getIdActivity());
        values.put(ActivityLog.ActivityLogEntityColumns.TITLE, activityLog.getTitle());
        values.put(ActivityLog.ActivityLogEntityColumns.DESCRIPTION, activityLog.getDescription());
        values.put(ActivityLog.ActivityLogEntityColumns.USERNAME, activityLog.getUsername());
        values.put(ActivityLog.ActivityLogEntityColumns.CATEGORY, activityLog.getCategory());
        values.put(ActivityLog.ActivityLogEntityColumns.PLATFORM, activityLog.getPlatform());
        values.put(ActivityLog.ActivityLogEntityColumns.LOCAL_DATE, activityLog.getLocalDate());
        values.put(ActivityLog.ActivityLogEntityColumns.LOCAL_HOUR, activityLog.getLocalHour());
        values.put(ActivityLog.ActivityLogEntityColumns.TIME_SPENT, String.valueOf(activityLog.getTimeSpent()));

        try {

            int result = (int) db.insert(TABLE_ACTIVITY, null, values);
            Log.d("RESULT_DAO ", String.valueOf(result) + " - ");

        } catch (Exception e) {
            Log.d("RESULT_EXCEPTION ", e.getMessage() + " - ");

        } finally {

        }
    }

    public ArrayList<ActivityLog> getAllLocalActivityLogs() {

        ArrayList<ActivityLog> activityLogArrayList  = new ArrayList<>();
// _id , idactivity, title, description, username, category, platform, local_date, local_hour, time_spent
        Cursor cur = db.rawQuery("SELECT * FROM activity;", null);
        if (cur.moveToFirst()) {
            do {
                ActivityLog activityLog = new ActivityLog();

                activityLog.setIdActivity(cur.getInt(1));
                activityLog.setTitle(cur.getString(2));
                activityLog.setDescription(cur.getString(3));
                activityLog.setUsername(cur.getString(4));
                activityLog.setCategory(cur.getString(5));
                activityLog.setPlatform(cur.getString(6));
                activityLog.setLocalDate(cur.getString(7));
                activityLog.setLocalHour(cur.getString(8));
                activityLog.setTimeSpent(cur.getLong(9));
                activityLogArrayList.add(activityLog);

            } while (cur.moveToNext());
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();

        }
        Collections.sort(activityLogArrayList, Collections.<ActivityLog>reverseOrder());
        return activityLogArrayList;

    }

    public ArrayList<ActivityLog> getAllLocalBreastFeedingActivityLogs() {

        ArrayList<ActivityLog> activityLogArrayList  = new ArrayList<>();
// _id , idactivity, title, description, username, category, platform, local_date, local_hour, time_spent
        Cursor cur = db.rawQuery("SELECT * FROM activity WHERE category = 'M';", null);
        if (cur.moveToFirst()) {
            do {
                ActivityLog activityLog = new ActivityLog();

                activityLog.setIdActivity(cur.getInt(1));
                activityLog.setTitle(cur.getString(2));
                activityLog.setDescription(cur.getString(3));
                activityLog.setUsername(cur.getString(4));
                activityLog.setCategory(cur.getString(5));
                activityLog.setPlatform(cur.getString(6));
                activityLog.setLocalDate(cur.getString(7));
                activityLog.setLocalHour(cur.getString(8));
                activityLog.setTimeSpent(cur.getLong(9));
                activityLogArrayList.add(activityLog);

            } while (cur.moveToNext());
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();

        }
        Collections.sort(activityLogArrayList, Collections.<ActivityLog>reverseOrder());
        return activityLogArrayList;

    }

    public ArrayList<ActivityLog> getAllLocalFormulaFeedingActivityLogs() {

        ArrayList<ActivityLog> activityLogArrayList  = new ArrayList<>();
// _id , idactivity, title, description, username, category, platform, local_date, local_hour, time_spent
        Cursor cur = db.rawQuery("SELECT * FROM activity WHERE category = 'F';", null);
        if (cur.moveToFirst()) {
            do {
                ActivityLog activityLog = new ActivityLog();

                activityLog.setIdActivity(cur.getInt(1));
                activityLog.setTitle(cur.getString(2));
                activityLog.setDescription(cur.getString(3));
                activityLog.setUsername(cur.getString(4));
                activityLog.setCategory(cur.getString(5));
                activityLog.setPlatform(cur.getString(6));
                activityLog.setLocalDate(cur.getString(7));
                activityLog.setLocalHour(cur.getString(8));
                activityLog.setTimeSpent(cur.getLong(9));
                activityLogArrayList.add(activityLog);

            } while (cur.moveToNext());
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();

        }
        Collections.sort(activityLogArrayList, Collections.<ActivityLog>reverseOrder());
        return activityLogArrayList;

    }

    public int updateActivityLog(ActivityLog activityLog){

        Log.d("DAO3: ", activityLog.getIdActivity().toString() + activityLog.getDescription().toString() + activityLog.getTimeSpent().toString());

        ContentValues values = new ContentValues();
        values.put(ActivityLog.ActivityLogEntityColumns.DESCRIPTION, activityLog.getDescription());
        values.put(ActivityLog.ActivityLogEntityColumns.LOCAL_DATE, activityLog.getLocalDate());
        values.put(ActivityLog.ActivityLogEntityColumns.LOCAL_HOUR, activityLog.getLocalHour());
        values.put(ActivityLog.ActivityLogEntityColumns.TIME_SPENT, String.valueOf(activityLog.getTimeSpent()));
        String selection = ActivityLog.ActivityLogEntityColumns.ID_ACTIVITY + " = ?";
        String[] selectionArgs = {String.valueOf(activityLog.getIdActivity())};
        int count = db.update(TABLE_ACTIVITY, values, selection, selectionArgs);
        return count;

    }


    public int updateActivityLogWithParams(Integer id, String description, String timeSpent){

        Log.d("DAO2: ", id.toString() + description.toString() + timeSpent.toString());

        ContentValues values = new ContentValues();
        values.put("description", description);
        values.put("time_spent", String.valueOf(timeSpent));
        return db.update(TABLE_ACTIVITY, values, ActivityLog.ActivityLogEntityColumns.ID_ACTIVITY+ " = " + id, null);

    }

    public int updateActivityLogWithParams(Integer id, String description){

        Log.d("DAO4: ", id.toString() + description.toString());

        ContentValues values = new ContentValues();
        values.put("description", description);
        return db.update(TABLE_ACTIVITY, values, ActivityLog.ActivityLogEntityColumns.ID_ACTIVITY+ " = " + id, null);

    }

    public int deleteActivityLog(ActivityLog activityLog){

        String selection = ActivityLog.ActivityLogEntityColumns.ID_ACTIVITY + " = ?";
        String[] selectionArgs = {String.valueOf(activityLog.getIdActivity())};
        int count = db.delete(TABLE_ACTIVITY, selection, selectionArgs);
        return count;

    }

    public void deleteAllActivityLogs(){
        if(getAllLocalActivityLogs().size() > 1) {
            int doneDelete = 0;
            doneDelete = db.delete(TABLE_ACTIVITY, null, null);
        }
    }

    public Integer generateActivityLogId(){
        if(!getAllLocalActivityLogs().isEmpty()){
            return getAllLocalActivityLogs().size() + ONE;
        }
        return ONE;
    }

    public Integer generateFormulaActivityLogId(){
        if(!getAllLocalActivityLogs().isEmpty()){
            return getAllLocalFormulaFeedingActivityLogs().size() + ONE;
        }
        return ONE;
    }


    public Integer generateBreastFeedingActivityLogId(){
        if(!getAllLocalBreastFeedingActivityLogs().isEmpty()){
            return getAllLocalActivityLogs().size() + ONE;
        }
        return ONE;
    }


    public Cursor query(SQLiteQueryBuilder queryBuilder, String[] projection,
                        String selection, String[] selectionArgs, String groupBy,
                        String having, String orderBy) {
        Cursor c = queryBuilder.query(this.db, projection, selection,
                selectionArgs, groupBy, having, orderBy);
        return c;
    }

    public void closeDataBase() {
        // fecha o banco de dados
        if (db != null) {
            db.close();
        }
    }

}
