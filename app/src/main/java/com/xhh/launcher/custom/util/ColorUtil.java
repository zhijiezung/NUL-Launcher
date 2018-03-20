package com.xhh.launcher.custom.util;

import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.graphics.Palette;

import java.util.List;

/**
 * <p>颜色处理工具类.</p>
 * <p>创建时间: 2018/3/20 0020</p>
 * <br/><p>颜色处理工具类</p>
 * @author nameh
 */
public class ColorUtil {
    
    private static final float MIN_CONTRAST_RATIO = 2f;

    public static boolean isShouldLight(Palette palette){
        return isLegibleOnWallpaper(Color.WHITE,palette.getSwatches());
    }

    /**
     * <p>判断颜色.</p>
     * <p>创建时间: 2018/3/20 0020</p>
     * <br/><p></p>
     * <br/><p>
     *       使用来自Lawnchair的ch.deletescape.lawnchair.dynamicui.ExtractionUtils类的
     *       <a href="https://github.com/LawnchairLauncher/Lawnchair/blob/beta/app/src/main/java/ch/deletescape/lawnchair/dynamicui/ExtractionUtils.java#L94" target="blank">
     *         isLegibleOnWallpaper
     *       </a>
     *       方法
     *     </p>
     * @param color 比较的颜色
     * @param wallpaperSwatches Palette的swatches
     * @return boolean 是否清晰
     */

    private static boolean isLegibleOnWallpaper(int color, List<Palette.Swatch> wallpaperSwatches) {
        int legiblePopulation = 0;
        int illegiblePopulation = 0;
        for (Palette.Swatch swatch : wallpaperSwatches) {
            if (isLegible(color, swatch.getRgb())) {
                legiblePopulation += swatch.getPopulation();
            } else {
                illegiblePopulation += swatch.getPopulation();
            }
        }
        return legiblePopulation > illegiblePopulation;
    }

    /**
     * <p>判断颜色.</p>
     * <p>创建时间: 2018/3/20 0020</p>
     * <br/><p></p>
     * <br/><p>
     *       使用来自Lawnchair的ch.deletescape.lawnchair.dynamicui.ExtractionUtils类的
     *       <a href="https://github.com/LawnchairLauncher/Lawnchair/blob/beta/app/src/main/java/ch/deletescape/lawnchair/dynamicui/ExtractionUtils.java#L110" target="blank">
     *         isLegible
     *       </a>
     *       方法
     *     </p>
     * @param foreground 前景色
     * @param background 背景色
     * @return boolean 是否清晰
     */

    private static boolean isLegible(int foreground, int background) {
        background = ColorUtils.setAlphaComponent(background, 255);
        return ColorUtils.calculateContrast(foreground, background) >= MIN_CONTRAST_RATIO;
    }

}
