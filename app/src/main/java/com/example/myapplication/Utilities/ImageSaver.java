package com.example.myapplication.Utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageSaver {

    public static void main(String[] args) {

    }

    public static void saveImageToInternalStorage(Context context, Bitmap bitmap, String imageName) {
        File directory = context.getDir("images", Context.MODE_PRIVATE);
        File imageFile = new File(directory, imageName + ".png");

        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap loadImageFromInternalStorage(Context context, String imageName) {
        if (context != null) {
            if (context.getDir("images", Context.MODE_PRIVATE) != null) {
                //System.out.println("\nLoading image\n");
                File directory = context.getDir("images", Context.MODE_PRIVATE);
                File imageFile = new File(directory, imageName + ".png");
                //System.out.println(imageFile);

                if (imageFile.exists()) {
                    System.out.println("\nImagefile exists.\n");
                    return BitmapFactory.decodeFile(imageFile.getAbsolutePath());

                }
                return null;
            }else{
                System.out.println("\nGetdir is null\n");
                return  null;
            }

        }else{
            System.out.println("\nContext is null\n");
            return  null;
        }
    }
}