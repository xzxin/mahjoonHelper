package com.example.myactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MjView extends View {
    List<Bitmap> _bitMaps;
    public MjView(Context context) {
        super(context);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        _bitMaps = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            _bitMaps.add(MjBitMap.getMjBitMap("wan", i, this));
        }
        for (int i = 1; i <= 5; i++) {
            _bitMaps.add(MjBitMap.getMjBitMap("tong", i, this));
        }
        Bitmap bitmap = drawBitMap(_bitMaps);
        canvas.drawBitmap(bitmap, 0, 0, null);
        bitmap.recycle();

    }

    private Bitmap drawBitMap(List<Bitmap> bitMaps) {
        int width = 0;
        int height = 0;

        //获取最大宽度
        for (Bitmap bitmap : bitMaps) {
            width = width + bitmap.getWidth();
            if (height < bitmap.getHeight()) {
                height = bitmap.getHeight();
            }
        }

        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        int tempWidths = 0;
        //画图
        for (int i = 0; i < bitMaps.size(); i++) {
            canvas.drawBitmap(bitMaps.get(i), tempWidths, 0, null);
            tempWidths = bitMaps.get(i).getWidth() + tempWidths;
        }
        int destWidth = newBitmap.getWidth()/5;
        int destHeight = newBitmap.getHeight()/5;
        Bitmap bmp = Bitmap.createScaledBitmap(newBitmap, destWidth, destHeight, true);
        newBitmap.recycle();
        return bmp;
    }

    public MjView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

}
