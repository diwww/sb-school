package ru.gcsales.seminar7.model;

import android.os.Parcel;
import android.os.Parcelable;

import ru.gcsales.seminar7.Config;

/**
 * Model containing data for the first ViewHolder
 */
public class FirstItemModel implements BaseModel {

    private String imageUrl;
    private String text;

    public FirstItemModel(String imageUrl, String text) {
        this.imageUrl = imageUrl;
        this.text = text;
    }

    protected FirstItemModel(Parcel in) {
        imageUrl = in.readString();
        text = in.readString();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(text);
    }

    public static final Creator<FirstItemModel> CREATOR = new Creator<FirstItemModel>() {
        @Override
        public FirstItemModel createFromParcel(Parcel in) {
            return new FirstItemModel(in);
        }

        @Override
        public FirstItemModel[] newArray(int size) {
            return new FirstItemModel[size];
        }
    };

    @Override
    public int getType() {
        return Config.Extras.FIRST.getType();
    }
}
