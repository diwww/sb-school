package ru.gcsales.seminar8;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.Random;

public class FirstFragmentLoader extends AsyncTaskLoader<Integer> {

    private static final int[] colors = {
            Color.RED,
            Color.GREEN,
            Color.BLUE
    };

    private static final Random random = new Random();

    public FirstFragmentLoader(@NonNull Context context, Bundle args) {
        super(context);
    }

    @Nullable
    @Override
    public Integer loadInBackground() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return colors[random.nextInt(colors.length)];
    }
}
