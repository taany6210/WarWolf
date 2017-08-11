package com.ty.warwolf.util;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.ty.warwolf.R;
import com.ty.warwolf.glide.BlurTransformation;
import com.ty.warwolf.glide.RoundTransformation;

import java.io.File;


/**
 * @ 文件名:   GlideUtil
 * @ 创建者:   ty
 * @ 时间:    2017/8/2 下午2:37
 * @ 描述:
 */

public class GlideUtil {

    public static int mImageId = -1;//默认图片id

    public static void initImageIcon(int id) {
        mImageId = id;
    }

    @BindingAdapter("loadFromScreenShots")
    public static void intoScreenShots(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .crossFade()
                .centerCrop()
                .into(view);
    }

    @BindingAdapter("loadFromRound")
    public static void intRoundImage(ImageView view, String url) {
        intoRound(url, 100, view);
    }

    /**
     * 默认glide，不做任何处理
     *
     * @param url
     * @param view
     */
    public static void intoDefault(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }

    /**
     * 默认glide，不做任何处理
     */
    public static void intoDefault(int id, ImageView view) {
        Glide.with(view.getContext())
                .load(id)
                .into(view);
    }

    /**
     * glide 从字符串中加载图片（网络地址或者本地地址）
     */
    @BindingAdapter("loadFromUrlNormal")
    public static void into(ImageView view, String url) {
        DrawableTypeRequest<String> request = Glide.with(view.getContext()).load(url);
        if (mImageId != -1) {
            request.placeholder(mImageId);
        }
        request.crossFade()//淡入淡出
                .centerCrop()
                .placeholder(R.drawable.img_default)
                .error(R.drawable.img_default)
                .into(view);

    }

    /**
     * glide 从资源ID中加载图片
     */
    public static void into(int resourceId, ImageView view) {
        DrawableTypeRequest<Integer> request = Glide.with(view.getContext()).load(resourceId);
        if (mImageId != -1) {
            request.placeholder(mImageId);
        }
        request.crossFade()//淡入淡出
                .centerCrop() // 缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分
                .into(view);
    }

    /**
     * glide 从文件中加载图片
     */
    public static void into(File file, ImageView view) {
        DrawableTypeRequest<File> request = Glide.with(view.getContext()).load(file);
        if (mImageId != -1) {
            request.placeholder(mImageId);
        }
        request.crossFade()//淡入淡出
                .centerCrop() // 缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分
                .into(view);
    }

    /**
     * glide 从URI中加载图片
     */
    public static void into(Uri uri, ImageView view) {
        DrawableTypeRequest<Uri> request = Glide.with(view.getContext()).load(uri);
        if (mImageId != -1) {
            request.placeholder(mImageId);
        }
        request.crossFade()//淡入淡出
                .centerCrop() // 缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分
                .into(view);
    }

    /**
     * glide 通过指定的大小从字符串中加载图片（网络地址或者本地地址）
     */
    public static void into(ImageView view, String url, int width, int height) {
        DrawableTypeRequest<String> request = Glide.with(view.getContext()).load(url);
        if (width > 0 && height > 0) {
            request.override(width, height);
        }
        if (mImageId != -1) {
            request.placeholder(mImageId);
        }
        request.crossFade()//淡入淡出
                .centerCrop() // 缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分
                .into(view);
    }

    /**
     * glide 通过指定的大小从资源ID中加载图片
     */
    public static void into(int resourceId, ImageView view, int width, int height) {
        DrawableTypeRequest<Integer> request = Glide.with(view.getContext()).load(resourceId);
        if (width > 0 && height > 0) {
            request.override(width, height);
        }
        if (mImageId != -1) {
            request.placeholder(mImageId);
        }
        request.crossFade()//淡入淡出
                .centerCrop() // 缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分
                .into(view);
    }

    /**
     * glide 通过指定的大小从文件中加载图片
     */
    public static void into(File file, ImageView view, int width, int height) {
        DrawableTypeRequest<File> request = Glide.with(view.getContext()).load(file);
        if (width > 0 && height > 0) {
            request.override(width, height);
        }
        if (mImageId != -1) {
            request.placeholder(mImageId);
        }
        request.crossFade()//淡入淡出
                .centerCrop() // 缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分
                .into(view);
    }

    /**
     * glide 通过指定的大小从Uri中加载图片
     */
    public static void into(Uri uri, ImageView view, int width, int height) {
        DrawableTypeRequest<Uri> request = Glide.with(view.getContext()).load(uri);
        if (width > 0 && height > 0) {
            request.override(width, height);
        }
        if (mImageId != -1) {
            request.placeholder(mImageId);
        }
        request.crossFade()//淡入淡出
                .centerCrop() // 缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分
                .into(view);
    }


    /**
     * 高斯模糊图片处理
     */
    public static void intoBlur(String url, ImageView view) {
        DrawableTypeRequest<String> request = Glide.with(view.getContext()).load(url);
        if (mImageId != -1) {
            request.placeholder(mImageId);
        }
        request.crossFade()
                .transform(new BlurTransformation(view.getContext()))
                .centerCrop() // 缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分
                .into(view);
    }

    /**
     * 圆或者圆角图片处理
     */
    public static void intoRound(String url, int radius, ImageView view) {
        DrawableTypeRequest<String> request = Glide.with(view.getContext()).load(url);
        if (mImageId != -1) {
            request.placeholder(mImageId);
        }
        request.crossFade()
                .centerCrop()
                .transform(new RoundTransformation(view.getContext(), radius))
                .into(view);
    }

    /**
     * 圆或者圆角图片处理
     */
    public static void intoRound(int id, int radius, ImageView view) {
        DrawableTypeRequest<Integer> request = Glide.with(view.getContext()).load(id);
        if (mImageId != -1) {
            request.placeholder(mImageId);
        }
        request.crossFade()//淡入淡出
                .centerCrop() // 缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分
                .transform(new RoundTransformation(view.getContext(), radius))
                .into(view);
    }


    /**
     * glide 从字符串中加载图片（网络地址或者本地地址）,
     */
    public static void into(String url, ImageView view, int defaultId) {
        Glide.with(view.getContext()).load(url)
                .placeholder(defaultId)
                .crossFade()//淡入淡出
                .centerCrop() // 缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分
                .into(view);
    }

    /**
     * 从字符串中加载圆形图片（网络地址或者本地地址）,
     */
    public static void intoRound(String url, int radius, ImageView view, int defaultId) {
        Glide.with(view.getContext()).load(url)
                .placeholder(defaultId)
                .crossFade()
                .transform(new RoundTransformation(view.getContext(), radius))
                .into(view);
    }
}
