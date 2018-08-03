package ru.gcsales.seminar23;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Maxim Surovtsev
 * Created on 8/1/18
 */
public class MyViewGroup extends ViewGroup {

    private static final String TAG = "MyViewGroup";


    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        String log1 = String.format("w: %s, h: %s", MeasureSpec.toString(widthMeasureSpec), MeasureSpec.toString(heightMeasureSpec));
        Log.d(TAG, log1);

        int desiredWidth = 0;
        // Always match_parent
        final int height = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();
        // Height of a single column. This should be
        // always less than height of this ViewGroup.
        // Initial value is equal to height to generate
        // the first column.
        int columnHeight = height;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // If a column can fit one more child
            if (columnHeight + child.getMeasuredHeight() < height) {
                columnHeight += child.getMeasuredHeight();
            } else { // Else add one more column
                columnHeight = 0;
                desiredWidth += child.getMeasuredWidth();
            }
        }

        setMeasuredDimension(resolveSize(desiredWidth, widthMeasureSpec), heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();

        final int height = getMeasuredHeight();

        // Starting points
        int x = 0;
        int y = 0;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            if (y + child.getMeasuredHeight() < height) {
                child.layout(x, y, x + child.getMeasuredWidth(), y + child.getMeasuredHeight());
                y += child.getMeasuredHeight();
            } else {
                x += child.getMeasuredWidth();
                y = 0;
                child.layout(x, y, x + child.getMeasuredWidth(), y + child.getMeasuredHeight());
            }
        }
    }
}
