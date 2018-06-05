package com.xhh.launcher.custom.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;

/**
 * <p>图像处理工具类.</p>
 * <p>创建时间: 2018/3/20 0020</p>
 * <br/><p>图像处理工具类</p>
 * @author nameh
 */

public class ImageUtil {

    /**
     * <p>drawable转Bitmap.</p>
     * <p>创建时间: 2018/3/20 0020</p>
     * <br/><p>drawable为空时返回空</p>
     * @param drawable 需要转换的drawable
     * @return Bitmap 返回Bitmap对象
     */

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return ((BitmapDrawable) drawable).getBitmap();
    }

    /**
     * <p>获取Palette.</p>
     * <p>创建时间: 2018/3/20 0020</p>
     * <br/><p>获取Palette</p>
     * @param drawable 需要获取的对象
     * @return Palette 返回Palette对象
     */
     
    public static synchronized Palette getImagePalette(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return getImagePalette(drawable2Bitmap(drawable));
    }

    /**
     * <p>获取Palette.</p>
     * <p>创建时间: 2018/3/20 0020</p>
     * <br/><p>获取Palette</p>
     * @param drawable 需要获取的对象
     * @param left 图片的左起点
     * @param top 图片的上起点
     * @param right 图片的右起点
     * @param bottom 图片的下起点
     * @return Palette 返回Palette对象
     */

    public static synchronized Palette getImagePalette(Drawable drawable, int left, int top, int right, int bottom) {
        if (drawable == null) {
            return null;
        }
        return getImagePalette(drawable2Bitmap(drawable), left, top, right, bottom);
    }

    /**
     * <p>获取Palette.</p>
     * <p>创建时间: 2018/3/20 0020</p>
     * <br/><p>获取Palette</p>
     * @param bitmap 需要获取的Bitmap对象
     * @return Palette 返回Palette对象
     */

    public static synchronized Palette getImagePalette(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return getImagePalette(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
    }
    /**
     * <p>获取Palette.</p>
     * <p>创建时间: 2018/3/20 0020</p>
     * <br/><p>获取Palette</p>
     * @param bitmap 需要获取的对象
     * @param left 图片的左起点
     * @param top 图片的上起点
     * @param right 图片的右起点
     * @param bottom 图片的下起点
     * @return Palette 返回Palette对象
     */

    public static synchronized Palette getImagePalette(Bitmap bitmap, int left, int top, int right, int bottom) {
        if (bitmap == null) {
            return null;
        }
        return Palette.from(bitmap)
                .setRegion(left, top, right, bottom)
                .clearFilters()
                .generate();
    }

}
