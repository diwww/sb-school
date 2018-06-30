package ru.gcsales.seminar7.model;

import android.os.Parcel;
import android.os.Parcelable;

import ru.gcsales.seminar7.Config;

public class ThirdItemModel implements BaseModel {

    private String text;

    public ThirdItemModel(String text) {
        this.text = text;
    }

    protected ThirdItemModel(Parcel in) {
        text = in.readString();
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
        dest.writeString(text);
    }

    public static final Creator<ThirdItemModel> CREATOR = new Creator<ThirdItemModel>() {
        @Override
        public ThirdItemModel createFromParcel(Parcel in) {
            return new ThirdItemModel(in);
        }

        @Override
        public ThirdItemModel[] newArray(int size) {
            return new ThirdItemModel[size];
        }
    };

    @Override
    public int getType() {
        return Config.Extras.THIRD.getType();
    }
}
