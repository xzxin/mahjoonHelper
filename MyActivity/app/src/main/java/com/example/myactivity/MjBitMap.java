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
        bitMap.put("wan", new HashMap<>());
        bitMap.put("tiao", new HashMap<>());
        bitMap.put("tong", new HashMap<>());
        bitMap.get("wan").put(1, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan1));
        bitMap.get("wan").put(2, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan2));
        bitMap.get("wan").put(3, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan3));
        bitMap.get("wan").put(4, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan4));
        bitMap.get("wan").put(5, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan5));
        bitMap.get("wan").put(6, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan6));
        bitMap.get("wan").put(7, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan7));
        bitMap.get("wan").put(8, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan8));
        bitMap.get("wan").put(9, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.wan9));
        bitMap.get("tiao").put(1, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao1));
        bitMap.get("tiao").put(2, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao2));
        bitMap.get("tiao").put(3, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao3));
        bitMap.get("tiao").put(4, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao4));
        bitMap.get("tiao").put(5, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao5));
        bitMap.get("tiao").put(6, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao6));
        bitMap.get("tiao").put(7, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao7));
        bitMap.get("tiao").put(8, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao8));
        bitMap.get("tiao").put(9, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tiao9));
        bitMap.get("tong").put(1, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong1));
        bitMap.get("tong").put(2, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong2));
        bitMap.get("tong").put(3, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong3));
        bitMap.get("tong").put(4, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong4));
        bitMap.get("tong").put(5, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong5));
        bitMap.get("tong").put(6, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong6));
        bitMap.get("tong").put(7, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong7));
        bitMap.get("tong").put(8, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong8));
        bitMap.get("tong").put(9, BitmapFactory.decodeResource(view.getContext().getResources(), R.drawable.tong9));

    }

    public static Bitmap getMjBitMap(String type, Integer num, View view) {
        if (bitMap.get(type) == null) {
            loadBitMaps(view);
        }
        return bitMap.get(type).get(num);
    }
}
