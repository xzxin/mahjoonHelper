package com.example.myactivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class MjBitMap {
    private static Map<String, Map<Integer, Bitmap>> bitMap = new HashMap<>();
    private static void loadBitMaps(View view) {
        bitMap.put("万", new HashMap<>());
        bitMap.put("条", new HashMap<>());
        bitMap.put("筒", new HashMap<>());
        bitMap.get("万").put(1, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan1));
        bitMap.get("万").put(2, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan2));
        bitMap.get("万").put(3, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan3));
        bitMap.get("万").put(4, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan4));
        bitMap.get("万").put(5, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan5));
        bitMap.get("万").put(6, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan6));
        bitMap.get("万").put(7, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan7));
        bitMap.get("万").put(8, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan8));
        bitMap.get("万").put(9, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan9));
        bitMap.get("条").put(1, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao1));
        bitMap.get("条").put(2, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao2));
        bitMap.get("条").put(3, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao3));
        bitMap.get("条").put(4, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao4));
        bitMap.get("条").put(5, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao5));
        bitMap.get("条").put(6, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao6));
        bitMap.get("条").put(7, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao7));
        bitMap.get("条").put(8, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao8));
        bitMap.get("条").put(9, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao9));
        bitMap.get("筒").put(1, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong1));
        bitMap.get("筒").put(2, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong2));
        bitMap.get("筒").put(3, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong3));
        bitMap.get("筒").put(4, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong4));
        bitMap.get("筒").put(5, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong5));
        bitMap.get("筒").put(6, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong6));
        bitMap.get("筒").put(7, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong7));
        bitMap.get("筒").put(8, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong8));
        bitMap.get("筒").put(9, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong9));

    }

    public static Bitmap getMjBitMap(String type, Integer num, View view) {
        if (bitMap.get(type) == null) {
            loadBitMaps(view);
        }
        return bitMap.get(type).get(num);
    }
}
