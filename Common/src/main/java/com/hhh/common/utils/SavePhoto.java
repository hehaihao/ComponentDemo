package com.hhh.common.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import com.hhh.common.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目：ChatfengIM
 * 文件描述：保存图片到手机相册
 * 作者：ljj
 * 创建时间：2019/8/28
 */

public class SavePhoto {
    //存调用该类的活动
    Context context;

    public SavePhoto(Context context) {
        this.context = context;
    }

    //保存文件的方法：
    public String SaveBitmapFromView(View view) {
        int w = view.getWidth();
        int h = view.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        // 保存完图片后放置的位置
/*        int screenHeight = ScreenUtils.getScreenHeight(context);
        int screenWidth = ScreenUtils.getScreenWidth(context);
        view.layout((screenWidth-w)/2, (screenHeight-h)/2, w, h);*/
        view.draw(c);
        // 压缩图片
//        Matrix matrix = new Matrix();
//        matrix.postScale(0.5f,0.5f); //长和宽放大缩小的比例
//        bmp = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String filePath = saveBitmap(bmp, format.format(new Date()) + ".JPEG");
        return filePath;
    }

    /*
     * 保存文件，文件名为当前日期
     */
    public String saveBitmap(Bitmap bitmap, String bitName) {
        String fileName;
        File file;
        if (Build.BRAND.equals("Xiaomi")) { // 小米手机
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
        } else {  // Meizu 、Oppo
//                Log.v("qwe","002");
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + bitName;
        }
        file = new File(fileName);

        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            // 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
                out.flush();
                out.close();
                // 插入图库
                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), bitName, null);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        // 提示保存成功
        ToastUtil.showCenterToast(context.getResources().getString(R.string.qrcode_save_successfully));
        // 发送广播，通知刷新图库的显示
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));
        return fileName;
    }
}
