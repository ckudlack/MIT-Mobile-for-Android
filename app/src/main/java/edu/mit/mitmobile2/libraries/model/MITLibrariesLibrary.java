package edu.mit.mitmobile2.libraries.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serg on 5/20/15.
 */
public class MITLibrariesLibrary implements Parcelable {

    @SerializedName("id")
    private String identifier;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    @SerializedName("location")
    private String location;

    @SerializedName("phone")
    private String phoneNumber;

    @SerializedName("terms")
    private List<MITLibrariesTerm> terms;

    @SerializedName("coordinates")
    private List<Object> coordinateArray;           // TODO: clarify proper type

    public MITLibrariesLibrary() {
        // empty constructor
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<MITLibrariesTerm> getTerms() {
        return terms;
    }

    public void setTerms(List<MITLibrariesTerm> terms) {
        this.terms = terms;
    }

    public List<Object> getCoordinateArray() {
        return coordinateArray;
    }

    public void setCoordinateArray(List<Object> coordinateArray) {
        this.coordinateArray = coordinateArray;
    }

    protected MITLibrariesLibrary(Parcel in) {
        identifier = in.readString();
        name = in.readString();
        url = in.readString();
        location = in.readString();
        phoneNumber = in.readString();
        if (in.readByte() == 0x01) {
            terms = new ArrayList<MITLibrariesTerm>();
            in.readList(terms, MITLibrariesTerm.class.getClassLoader());
        } else {
            terms = null;
        }
        if (in.readByte() == 0x01) {
            coordinateArray = new ArrayList<Object>();
            in.readList(coordinateArray, Object.class.getClassLoader());
        } else {
            coordinateArray = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(identifier);
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(location);
        dest.writeString(phoneNumber);
        if (terms == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(terms);
        }
        if (coordinateArray == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(coordinateArray);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MITLibrariesLibrary> CREATOR = new Parcelable.Creator<MITLibrariesLibrary>() {
        @Override
        public MITLibrariesLibrary createFromParcel(Parcel in) {
            return new MITLibrariesLibrary(in);
        }

        @Override
        public MITLibrariesLibrary[] newArray(int size) {
            return new MITLibrariesLibrary[size];
        }
    };
}