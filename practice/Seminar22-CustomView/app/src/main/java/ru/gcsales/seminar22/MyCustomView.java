package ru.gcsales.seminar22;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * @author Maxim Surovtsev
 * Created on 7/27/18
 */
public class MyCustomView extends View {

    private Random rnd = new Random();
    private Paint mPaint = new Paint();
    private float mRectSizeX;
    private float mRectSizeY;
    private float mXLeft;
    private float mXRight;
    private float mYTop;
    private float mYBottom;
    private float mXDiff;
    private float mYDiff;
    private boolean mIsMoved = false;

    private MainActivity.Callback mCallback;

    public MyCustomView(Context context) {
        super(context);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MyCustomView, 0, 0);
        int color = a.getColor(R.styleable.MyCustomView_paint_color, Color.BLACK);
        mRectSizeX = a.getFloat(R.styleable.MyCustomView_rect_size_x, 100);
        mRectSizeY = a.getFloat(R.styleable.MyCustomView_rect_size_y, 100);
        a.recycle();
        // Инициализируем прямоугольник
        mPaint.setColor(color);
        mXLeft = 0;
        mXRight = mXLeft + mRectSizeX;
        mYTop = 0;
        mYBottom = mYTop + mRectSizeY;
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(mXLeft, mYTop, mXRight, mYBottom, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Чтобы можно было двигать только за сам прямоугольник
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            boolean isInsideRectX = (event.getX() > mXLeft && event.getX() < mXRight);
            boolean isInsideRectY = (event.getY() > mYTop && event.getY() < mYBottom);
            if (isInsideRectX && isInsideRectY) {
                // Запоминаем точку, за которую нужно двигать прямоугольник
                mXDiff = event.getX() - mXLeft;
                mYDiff = event.getY() - mYTop;
                return true;
            }
        }

        // Меняем координаты во время передвижения
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            mXLeft = event.getX() - mXDiff;
            mXRight = mXLeft + mRectSizeX;
            mYTop = event.getY() - mYDiff;
            mYBottom = mYTop + mRectSizeY;
            mIsMoved = true;
            invalidate();
            mCallback.onPositionChanged(mXLeft, mYTop);
            return true;
        }

        // Красим прямоугольник при изменеии положения
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mIsMoved) {
                mPaint.setARGB(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                invalidate();
            }
            mIsMoved = false;
            return true;
        }

        return false;
    }

    public void setCallback(MainActivity.Callback callback) {
        mCallback = callback;
    }
}
