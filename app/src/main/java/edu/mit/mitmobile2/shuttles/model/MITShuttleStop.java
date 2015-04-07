package edu.mit.mitmobile2.shuttles.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import edu.mit.mitmobile2.DBAdapter;
import edu.mit.mitmobile2.Schema;
import edu.mit.mitmobile2.maps.MapItem;

public class MITShuttleStop extends MapItem implements Parcelable {

    @Expose
    private String id;
    @Expose
    private String url;
    @SerializedName("route_id")
    @Expose
    private String routeId;
    @SerializedName("route_url")
    @Expose
    private String routeUrl;
    @Expose
    private String title;
    @SerializedName("stop_number")
    @Expose
    private String stopNumber;
    @Expose
    private Double lat;
    @Expose
    private Double lon;
    @Expose
    private List<MITShuttlePrediction> predictions = new ArrayList<>();
    @SerializedName("predictions_url")
    @Expose
    private String predictionsUrl;
    private float distance;
    private long timestamp;

    public MITShuttleStop() {
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getRouteId() {
        return routeId;
    }


    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }


    public String getRouteUrl() {
        return routeUrl;
    }


    public void setRouteUrl(String routeUrl) {
        this.routeUrl = routeUrl;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getStopNumber() {
        return stopNumber;
    }


    public void setStopNumber(String stopNumber) {
        this.stopNumber = stopNumber;
    }


    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public List<MITShuttlePrediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<MITShuttlePrediction> predictions) {
        this.predictions = predictions;
    }

    public String getPredictionsUrl() {
        return predictionsUrl;
    }

    public void setPredictionsUrl(String predictionsUrl) {
        this.predictionsUrl = predictionsUrl;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int getMapItemType() {
        return MARKERTYPE;
    }

    @Override
    public MarkerOptions getMarkerOptions() {
        MarkerOptions m = new MarkerOptions();
        m.title(this.title);
        m.snippet(this.id);
        LatLng position = new LatLng(this.lat, this.lon);
        m.position(position);
        return m;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.url);
        dest.writeString(this.routeId);
        dest.writeString(this.routeUrl);
        dest.writeString(this.title);
        dest.writeString(this.stopNumber);
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lon);
        dest.writeList(this.predictions);
        dest.writeString(this.predictionsUrl);
    }

    private MITShuttleStop(Parcel p) {
        this.id = p.readString();
        this.url = p.readString();
        this.routeId = p.readString();
        this.routeUrl = p.readString();
        this.title = p.readString();
        this.stopNumber = p.readString();
        this.lat = p.readDouble();
        this.lon = p.readDouble();
        this.predictions = (List<MITShuttlePrediction>) p.readArrayList(MITShuttlePrediction.class.getClassLoader());
        this.predictionsUrl = p.readString();
    }

    public static final Parcelable.Creator<MITShuttleStop> CREATOR = new Parcelable.Creator<MITShuttleStop>() {
        public MITShuttleStop createFromParcel(Parcel source) {
            return new MITShuttleStop(source);
        }

        public MITShuttleStop[] newArray(int size) {
            return new MITShuttleStop[size];
        }
    };

    @Override
    protected String getTableName() {
        return Schema.Stop.TABLE_NAME;
    }

    @Override
    protected void buildSubclassFromCursor(Cursor cursor, DBAdapter dbAdapter) {
        setId(cursor.getString(cursor.getColumnIndex(Schema.Stop.STOP_ID)));
        setUrl(cursor.getString(cursor.getColumnIndex(Schema.Stop.STOP_URL)));
        setRouteId(cursor.getString(cursor.getColumnIndex(Schema.Stop.ROUTE_ID)));
        setRouteUrl(cursor.getString(cursor.getColumnIndex(Schema.Stop.ROUTE_URL)));
        setTitle(cursor.getString(cursor.getColumnIndex(Schema.Stop.STOP_TITLE)));
        setStopNumber(cursor.getString(cursor.getColumnIndex(Schema.Stop.STOP_NUMBER)));
        setLat(cursor.getDouble(cursor.getColumnIndex(Schema.Stop.STOP_LAT)));
        setLon(cursor.getDouble(cursor.getColumnIndex(Schema.Stop.STOP_LON)));
        setPredictionsUrl(cursor.getString(cursor.getColumnIndex(Schema.Stop.PREDICTIONS_URL)));
        setDistance(cursor.getFloat(cursor.getColumnIndex(Schema.Stop.DISTANCE)));
        setTimestamp(cursor.getLong(cursor.getColumnIndex(Schema.Stop.TIMESTAMP)));

        buildSubclassFromCursor(cursor, dbAdapter, "");
    }

    @Override
    protected void buildSubclassFromCursor(Cursor cursor, DBAdapter dbAdapter, String prefix) {
        String segmentString = cursor.getString(cursor.getColumnIndex(Schema.Stop.PREDICTIONS));
        if (!TextUtils.isEmpty(segmentString)) {
            Gson gson = new Gson();
            Type nestedListType = new TypeToken<List<MITShuttlePrediction>>() {
            }.getType();
            List<MITShuttlePrediction> predictions = gson.fromJson(segmentString, nestedListType);

            // check timestamp of closest prediction to see it is outdated
            if (predictions.size() > 0) {
                long diff = System.currentTimeMillis() - timestamp;
//                Timber.d("DIFF: " + diff);
                if (diff < 60000) {
                    setPredictions(predictions);
                } else {
                    setPredictions(new ArrayList<MITShuttlePrediction>());
                }
            } else {
                setPredictions(new ArrayList<MITShuttlePrediction>());
            }
        } else {
            setPredictions(new ArrayList<MITShuttlePrediction>());
        }
    }

    @Override
    public void fillInContentValues(ContentValues values, DBAdapter dbAdapter) {
        long timestampVal = 0;

        if (predictions != null) {
            /*for (MITShuttlePrediction p : predictions) {
                Cursor cursor = dbAdapter.db.query(Schema.Alerts.TABLE_NAME, Schema.Alerts.ALL_COLUMNS, Schema.Alerts.STOP_ID + "=\'" + this.id + "\' AND " + Schema.Alerts.VEHICLE_ID + "=\'" + p.getVehicleId() + "\'", null, null, null, null);
                int diff = Integer.MAX_VALUE;
                long id = -1;
                ContentValues vals = new ContentValues();
                if (cursor.moveToFirst()) {
                    int timestamp = cursor.getInt(cursor.getColumnIndex(Schema.Alerts.TIMESTAMP));
                    id = cursor.getLong(cursor.getColumnIndex(Schema.Alerts.ID_COL));
                    diff = Math.abs(p.getTimestamp() - timestamp);
                    vals.put(Schema.Alerts.TIMESTAMP, p.getTimestamp());
                }

                cursor.close();

                if (diff < 50) {
                    dbAdapter.db.update(Schema.Alerts.TABLE_NAME, vals, Schema.Alerts.ID_COL + "=" + id, null);
                }
            }*/

            String preds = predictions.toString();
            values.put(Schema.Stop.PREDICTIONS, preds);
            timestampVal = System.currentTimeMillis();
        }

        values.put(Schema.Stop.STOP_ID, this.id);
        values.put(Schema.Stop.STOP_URL, this.url);
        values.put(Schema.Stop.ROUTE_ID, this.routeId);
        values.put(Schema.Stop.ROUTE_URL, this.routeUrl);
        values.put(Schema.Stop.STOP_TITLE, this.title);
        values.put(Schema.Stop.STOP_NUMBER, this.stopNumber);
        values.put(Schema.Stop.STOP_LAT, this.lat);
        values.put(Schema.Stop.STOP_LON, this.lon);
        values.put(Schema.Stop.PREDICTIONS_URL, this.predictionsUrl);
        values.put(Schema.Stop.TIMESTAMP, timestampVal);
    }
}