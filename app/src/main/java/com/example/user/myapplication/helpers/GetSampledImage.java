package com.example.user.myapplication.helpers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by user on 24/7/17.
 */

public class GetSampledImage extends AsyncTask<String,Void,File> {

    private Activity activity;
    OnSampledImageAsyncTaskPostExecute asyncTaskPostExecute;
    public GetSampledImage(Activity activity) {
        this.activity=activity;
        asyncTaskPostExecute= (OnSampledImageAsyncTaskPostExecute) activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected File doInBackground(String... params) {


        try {
            String picturePath = params[0];
            String imageDirectory = params[1];
            boolean isGalleryImage = Boolean.valueOf(params[2]);
            int reqImageWidth = Integer.valueOf(params[3]);
            if (null != picturePath) {
                ExifInterface exif = new ExifInterface(picturePath);
                int orientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION, 1);
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(picturePath, options);
                options.inSampleSize = calculateInSampleSize(options, reqImageWidth, reqImageWidth);
                options.inJustDecodeBounds = false;
                Bitmap imageBitmap = BitmapFactory.decodeFile(picturePath, options);
                if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);
                    imageBitmap = Bitmap.createBitmap(imageBitmap, 0, 0,
                            imageBitmap.getWidth(), imageBitmap.getHeight(),
                            matrix, true);
                } else if (orientation == 8) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(270);
                    imageBitmap = Bitmap.createBitmap(imageBitmap, 0, 0,
                            imageBitmap.getWidth(), imageBitmap.getHeight(),
                            matrix, true);
                } else if (orientation == 3) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(180);
                    imageBitmap = Bitmap.createBitmap(imageBitmap, 0, 0,
                            imageBitmap.getWidth(), imageBitmap.getHeight(),
                            matrix, true);
                }
                if (null != imageBitmap) {
                    return getImageFile(imageBitmap, picturePath, imageDirectory, isGalleryImage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        asyncTaskPostExecute.onSamplingPostExecute(file);

    }

    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private File getImageFile(Bitmap bmp, String picturePath, String imageDirectory,
                              boolean isGalleryImage) {
        try {
            OutputStream fOut = null;
            File file;
            if (isGalleryImage) {
                file = GeneralFunctions.setUpImageFile(imageDirectory);
            } else {
                file = new File(picturePath);
            }
            fOut = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
            MediaStore.Images.Media.insertImage(activity.getContentResolver(),
                    file.getAbsolutePath(), file.getName(),
                    file.getName());
            return file;
        } catch (OutOfMemoryError | Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public interface OnSampledImageAsyncTaskPostExecute{
        void onSamplingPostExecute(File file);
    }
}
