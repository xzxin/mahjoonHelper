package com.example.myactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MjView extends View {
    List<Bitmap> _bitMaps;

    public Map<String, List<Integer>> getToDrawMjs() {
        return toDrawMjs;
    }

    public void setToDrawMjs(Map<String, List<Integer>> toDrawMjs) {
        this.toDrawMjs = toDrawMjs;
    }

    Map<String, List<Integer>> toDrawMjs;
    public MjView(Context context) {
        super(context);
        toDrawMjs = new HashMap<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (toDrawMjs == null || toDrawMjs.size() == 0) {
            return;
        }
        _bitMaps = new ArrayList<>();
        for (String type : toDrawMjs.keySet()) {
            Collections.sort(toDrawMjs.get(type));
            for (Integer mj :toDrawMjs.get(type)) {
                _bitMaps.add(MjBitMap.getMjBitMap(type, mj, this));
            }
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
