package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by fabiodocoutooliveira on 29/05/16.
 */
public class LocalStorageDAO {

    public static final String TABLE_TIP = "bikespottip";
    public static final String TABLE_BIKELANE = "bikelane";
    public static final String TABLE_BIKESPOT = "bikespot";
    public static final String TABLE_SYNC_STATUS = "syncstatus";
    public static final String TABLE_SESSION = "session";
    public static final String DATABASE_PATH = "/data/data/com.bikespotapp/databases/";
    private static final String TAG_DAO = "LOCAL_DAO: ";
    private static final String DATABASE_NAME = "bikespot_db";
    private final Integer KILOMETER = 1000;
    public SQLiteDatabase db;

    public LocalStorageDAO() {

    }

    public LocalStorageDAO(Context ctx) {
        db = ctx.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

/*
    public void insertBikeSpot(BikeSpotEntity bikeSpotEntity) {

        ContentValues values = new ContentValues();
        values.put(BikeSpotEntity.BikeSpotEntityColumns.ID_BIKESPOT, bikeSpotEntity.getIdBikeSpot());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.USERNAME, bikeSpotEntity.getUsername());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.NAME, bikeSpotEntity.getName());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.ADDRESS_NUMBER, bikeSpotEntity.getAddressNumber());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.POSTAL_CODE, bikeSpotEntity.getPostalCode());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.STATUS, bikeSpotEntity.getStatus());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.FULL_ADDRESS, bikeSpotEntity.getFullAddress());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.LOCATION, String.valueOf(bikeSpotEntity.getLocation()));
        values.put(BikeSpotEntity.BikeSpotEntityColumns.DESCRIPTION, bikeSpotEntity.getDescription());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.PICTURE, (byte[]) null);
        values.put(BikeSpotEntity.BikeSpotEntityColumns.RATE, bikeSpotEntity.getRate());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.CAPACITY, bikeSpotEntity.getCapacity());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.LOCAL_DATE, bikeSpotEntity.getLocalDate());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.PLATTAFORM, bikeSpotEntity.getPlattaform());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.CATHEGORY, bikeSpotEntity.getCathegory());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.BIKE_SHARING, bikeSpotEntity.getBikeSharing());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.REVIEW_COUNT, bikeSpotEntity.getReviewCount());
        values.put(BikeSpotEntity.BikeSpotEntityColumns.COMMENT_COUNT, bikeSpotEntity.getCommentCount());

        try {
            int result = (int) db.insert(TABLE_BIKESPOT, null, values);


        } catch (Exception e) {

        } finally {

        }

    }


    public void insertSyncStatus(SyncStatus syncStatus) {

        ContentValues values = new ContentValues();
        values.put(SyncStatus.SyncStatusColumns.LAST_SYNC_DATE, syncStatus.getLastSyncDate());
        values.put(SyncStatus.SyncStatusColumns.LAST_SYNC_HOUR, syncStatus.getLastSyncHour());
        values.put(SyncStatus.SyncStatusColumns.BIKESPOT_COUNT, syncStatus.getBikeSpotCount());
        values.put(SyncStatus.SyncStatusColumns.BIKELANE_COUNT, syncStatus.getBikeLaneCount());

        try {
            db.insert(TABLE_SYNC_STATUS, "", values);

        } catch (Exception e) {

        } finally {

        }

    }


    public void insertSession(SessionUser sessionUser) {

        ContentValues values = new ContentValues();
        values.put(SessionUser.SessionColumns.NAME, sessionUser.getName());
        values.put(SessionUser.SessionColumns.EMAIL, sessionUser.getEmail());
        values.put(SessionUser.SessionColumns.PASSWORD, sessionUser.getPassword());

        try {
            db.insert(TABLE_SESSION, "", values);

        } catch (Exception e) {

        } finally {

        }

    }

    public int updateSyncStatus(Integer id, String syncDate, String syncHour, Integer bikeSpotCount, Integer bikeLaneCount){

        ContentValues values = new ContentValues();
        values.put(SyncStatus.SyncStatusColumns.LAST_SYNC_DATE, syncDate);
        values.put(SyncStatus.SyncStatusColumns.LAST_SYNC_HOUR, syncHour);
        values.put(SyncStatus.SyncStatusColumns.BIKESPOT_COUNT, bikeSpotCount);
        values.put(SyncStatus.SyncStatusColumns.BIKELANE_COUNT, bikeLaneCount);
        return db.update(TABLE_SYNC_STATUS, values, SyncStatus.SyncStatusColumns._ID+ " = " + id, null);

    }


    public int updateSyncStatus(SyncStatus syncStatus){

        ContentValues values = new ContentValues();
        values.put(SyncStatus.SyncStatusColumns.LAST_SYNC_DATE, syncStatus.getLastSyncDate());
        values.put(SyncStatus.SyncStatusColumns.LAST_SYNC_HOUR, syncStatus.getLastSyncHour());
        values.put(SyncStatus.SyncStatusColumns.BIKESPOT_COUNT, syncStatus.getBikeSpotCount());
        values.put(SyncStatus.SyncStatusColumns.BIKELANE_COUNT, syncStatus.getBikeSpotCount());
        return db.update(TABLE_SYNC_STATUS, values, null, null);

    }


    public ArrayList<BikeSpotEntity> getAllLocalBikeSpots() {

        ArrayList<BikeSpotEntity> bikeSpotEntityArrayList  = new ArrayList<>();

        Cursor cur = db.rawQuery("SELECT * FROM bikespot;", null);
        if (cur.moveToFirst()) {
            do {
                BikeSpotEntity bikeSpotEntity = new BikeSpotEntity();

                bikeSpotEntity.setIdBikeSpot(cur.getString(1));
                bikeSpotEntity.setUsername(cur.getString(2));
                bikeSpotEntity.setName(cur.getString(3));
                bikeSpotEntity.setAddressNumber(cur.getString(4));
                bikeSpotEntity.setPostalCode(cur.getString(5));
                bikeSpotEntity.setStatus(Boolean.TRUE);
                bikeSpotEntity.setFullAddress(cur.getString(7));
                bikeSpotEntity.setLocation(cur.getString(8));
                bikeSpotEntity.setDescription(cur.getString(9));
                //bikeSpotEntity.setPicture(cur.getBlob(10));
                bikeSpotEntity.setRate(cur.getInt(11));
                bikeSpotEntity.setCapacity(cur.getInt(12));
                bikeSpotEntity.setLocalDate(cur.getString(13));
                bikeSpotEntity.setPlattaform(cur.getString(14));
                bikeSpotEntity.setCathegory(cur.getString(15));
                bikeSpotEntity.setBikeSharing(cur.getString(16));
                bikeSpotEntity.setReviewCount(cur.getInt(17));
                bikeSpotEntity.setCommentCount(cur.getString(18));
                bikeSpotEntityArrayList.add(bikeSpotEntity);

            } while (cur.moveToNext());
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();

        }
        return bikeSpotEntityArrayList;

    }

    public ArrayList<SyncStatus> getAllSyncStatus() {

        ArrayList<SyncStatus> statusArrayList  = new ArrayList<>();

        Cursor cur = db.rawQuery("SELECT * FROM syncstatus;", null);
        if (cur.moveToFirst()) {
            do {
                SyncStatus syncStatus = new SyncStatus();
                syncStatus.setLastSyncDate(cur.getString(1));
                syncStatus.setLastSyncHour(cur.getString(2));
                syncStatus.setBikeSpotCount(cur.getInt(3));
                syncStatus.setBikeLaneCount(cur.getInt(4));
                statusArrayList.add(syncStatus);

            } while (cur.moveToNext());
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
        }
        return statusArrayList;

    }

    public ArrayList<SessionUser> getAllSession() {

        ArrayList<SessionUser> sessionUsersArrayList  = new ArrayList<>();

        Cursor cur = db.rawQuery("SELECT * FROM session;", null);
        if (cur.moveToFirst()) {
            do {
                SessionUser sessionUser = new SessionUser();
                sessionUser.setName(cur.getString(1));
                sessionUser.setEmail(cur.getString(2));
                sessionUser.setPassword(cur.getString(3));
                sessionUsersArrayList.add(sessionUser);

            } while (cur.moveToNext());
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
        }
        return sessionUsersArrayList;

    }

    public void deleteAllBikeSpots(){

        if(getAllLocalBikeSpots().size() > 1) {

            int doneDelete = 0;
            doneDelete = db.delete(TABLE_BIKESPOT, null, null);

        }
    }


    public void deleteAllSyncStatus(){

        int doneDelete = 0;
        doneDelete = db.delete(TABLE_SYNC_STATUS, null, null);

    }

    public void deleteAllSessions(){

        int doneDelete = 0;
        doneDelete = db.delete(TABLE_SESSION, null, null);

    }



    public SessionUser searchSessionByName(String name) {

        SessionUser sessionUser = new SessionUser();

        try {
            Cursor cur = db.query(TABLE_SESSION, SessionUser.columns, SessionUser.SessionColumns.NAME + "='"
                    + name + "'", null, null, null, null);

            if (cur.moveToNext()) {
                sessionUser.setName(cur.getString(1));
                sessionUser.setEmail(cur.getString(2));
                sessionUser.setPassword(cur.getString(3));
            }
        } catch (SQLException e) {
            return null;
        }

        return sessionUser;
    }


    public List<BikeSpotEntity> getAllBikeSpotListPerKilometer(Double kilometers, LatLng latLng) {

        List<BikeSpotEntity> list = new ArrayList<>();
        try {
              ArrayList<BikeSpotEntity> listExtra = getAllLocalBikeSpots();
              for(BikeSpotEntity bikeSpotEntity: listExtra){
                  if(getDistanceBetweenPoints(bikeSpotEntity,latLng) <= kilometers){
                      list.add(bikeSpotEntity);
                  }
              }

        } catch (Exception e) {

        }

        return list;
    }


    public Float getDistanceBetweenPoints(BikeSpotEntity bikeSpotEntity, LatLng latLng){
        Float result = Calculator.distanceFrom(latLng.latitude, latLng.longitude, transformStringInLatitude(bikeSpotEntity.getLocation()).latitude, transformStringInLatitude(bikeSpotEntity.getLocation()).longitude) / KILOMETER;
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        Float formatedFloat = new Float(formatter.format(result));
        return formatedFloat;
    }



    public LatLng transformStringInLatitude(String coordinates){
        StringBuilder stringBuilder = new StringBuilder();
        coordinates = stringBuilder.removeReplaceParseGeoPointCoordinates(coordinates);
        String latitude[] = coordinates.split(",");
        double lat = Double.parseDouble(latitude[0]);
        double lng = Double.parseDouble(latitude[1]);
        LatLng latLng = new LatLng(lat,lng);
        return latLng;
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
*/
}
