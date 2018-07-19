package ru.gcsales.seminar7.model;

import android.os.Parcel;
import android.os.Parcelable;

import ru.gcsales.seminar7.Config;

public class SecondItemModel implements BaseModel{
    private String imageUrl;
    private String text1;
    private String text2;

    public SecondItemModel(String imageUrl, String text1, String text2) {
        this.imageUrl = imageUrl;
        this.text1 = text1;
        this.text2 = text2;
    }

    protected SecondItemModel(Parcel in) {
        imageUrl = in.readString();
        text1 = in.readString();
        text2 = in.readString();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTextFirst() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getTextSecond() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(text1);
        dest.writeString(text2);
    }

    public static final Creator<SecondItemModel> CREATOR = new Creator<SecondItemModel>() {
        @Override
        public SecondItemModel createFromParcel(Parcel in) {
            return new SecondItemModel(in);
        }

        @Override
        public SecondItemModel[] newArray(int size) {
            return new SecondItemModel[size];
        }
    };

    @Override
    public int getType() {
        return Config.Extras.SECOND.getType();
    }
}
