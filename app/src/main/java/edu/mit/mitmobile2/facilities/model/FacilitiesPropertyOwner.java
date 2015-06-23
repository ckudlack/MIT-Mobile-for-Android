package edu.mit.mitmobile2.facilities.model;


import android.os.Parcel;
import android.os.Parcelable;

public class FacilitiesPropertyOwner implements Parcelable {

    private String id;
    private String name;
    private String phone;
    private String email;
    private String isHidden;
    private String isLeased;

    public FacilitiesPropertyOwner() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHidden(String isHidden) {
        this.isHidden = isHidden;
    }

    public String getHidden() {
        return isHidden;
    }

    public void setLeased(String isLeased) {
        this.isLeased = isLeased;
    }

    public String getLeased() {
        return isLeased;
    }

    @Override
    public String toString() {
        return "FacilitiesPropertyOwner{" +
                "id='" + id + '\'' +
                ", isHidden='" + isHidden + '\'' +
                ", isLeased='" + isLeased + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email=" + email +
                '}';
    }

    protected FacilitiesPropertyOwner(Parcel in) {
        id = in.readString();
        isHidden = in.readString();
        isLeased = in.readString();
        name = in.readString();
        phone = in.readString();
        email = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(isHidden);
        dest.writeString(isLeased);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(email);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FacilitiesPropertyOwner> CREATOR = new Parcelable.Creator<FacilitiesPropertyOwner>() {
        @Override
        public FacilitiesPropertyOwner createFromParcel(Parcel in) {
            return new FacilitiesPropertyOwner(in);
        }

        @Override
        public FacilitiesPropertyOwner[] newArray(int size) {
            return new FacilitiesPropertyOwner[size];
        }
    };
}
