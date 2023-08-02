package com.example.labux.ticket;
import android.os.Parcel;
import android.os.Parcelable;

public class TicketData implements Parcelable {
    private int id;
    private String name;
    private int price;
    private String date;
    private int image;

    private String description;

    private String experience;

    private String detail_name;

    private String detail_time;

    public TicketData(){

    }

    public TicketData(int id, String name, int price, String date, int image, String detail_time, String detail_name, String description, String experience                                                                                                         ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.image = image;
        this.detail_time = detail_time;
        this.detail_name = detail_name;
        this.description = description;
        this.experience = experience;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDetail_time() {
        return detail_time;
    }

    public void setDetail_time(String detail_time) {
        this.detail_time = detail_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDetail_name() {
        return detail_name;
    }

    public void setDetail_name(String detail_name) {
        this.detail_name = detail_name;
    }

    protected TicketData(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readInt();
        date = in.readString();
        image = in.readInt();
        detail_time = in.readString();
        detail_name = in.readString();
        description = in.readString();
        experience = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeString(date);
        dest.writeInt(image);
        dest.writeString(detail_time);
        dest.writeString(detail_name);
        dest.writeString(description);
        dest.writeString(experience);
    }

    public static final Creator<TicketData> CREATOR = new Creator<TicketData>() {
        @Override
        public TicketData createFromParcel(Parcel in) {
            return new TicketData(in);
        }

        @Override
        public TicketData[] newArray(int size) {
            return new TicketData[size];
        }
    };
}

