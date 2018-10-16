package com.yueworld.baseproject.utils;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.yueworld.baseproject.MyApplication;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;

/**
 *
 *
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CommonUtils {

    public static int dip(Context context, int pxValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue,
                context.getResources().getDisplayMetrics());
    }

    public static int px(Context context, int dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static ValueAnimator createHeightAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        // animator.setDuration(DURATION);
        return animator;
    }

    /**
     * 收缩动画
     */
    @SuppressLint("NewApi")
    public static void animateCollapsing(final View view) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createHeightAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    public static ValueAnimator createTopMarginAnimatorForLl(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                ((LinearLayout.LayoutParams) layoutParams).setMargins(0, value, 0, 0);
                // layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
//		animator.setDuration(500000);
        animator.setDuration(500);
        return animator;
    }


    public static boolean animateExpandingWidth(final View view, int width) {
        if (view.getVisibility() == View.VISIBLE) {
            return false;
        }
        view.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthSpec, heightSpec);

        ValueAnimator animator = createWidthAnimator(view, 0, width);
        animator.start();
        return true;
    }

    public static void animateCollapsingWidth(final View view, int width) {
        int origHeight = view.getWidth();

        ValueAnimator animator = createWidthAnimator(view, width, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

            ;
        });
        animator.start();
    }


    public static ValueAnimator createWidthAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = value;
                view.setLayoutParams(layoutParams);
            }
        });
        // animator.setDuration(DURATION);
        return animator;
    }

    /**
     * 扩展动画
     */
    public static boolean animateExpanding(final View view) {
        if (view.getVisibility() == View.VISIBLE) {
            return false;
        }
        view.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthSpec, heightSpec);

        ValueAnimator animator = createHeightAnimator(view, 0, view.getMeasuredHeight());
        animator.start();
        return true;
    }

    public static boolean animateExpanding(final View view, final Handler handler) {
        if (view.getVisibility() == View.VISIBLE) {
            return false;
        }
        view.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthSpec, heightSpec);

        ValueAnimator animator = createHeightAnimator(view, 0, view.getMeasuredHeight());

        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);//监听动画结束
                handler.sendEmptyMessage(Constant.ANIMATION_END_FLAG);
            }
        });
//        handler.sendMessageDelayed(new Message(), animator.getDuration() + 80);
        return true;
    }

    public static boolean animateExpanding(final View view, int height,final Handler handler) {
        if (view.getVisibility() == View.VISIBLE) {
            return false;
        }
        view.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthSpec, heightSpec);

        ValueAnimator animator = createHeightAnimator(view, 0, height);

        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);//监听动画结束
                handler.sendEmptyMessage(Constant.ANIMATION_END_FLAG);
            }
        });
//        handler.sendMessageDelayed(new Message(), animator.getDuration() + 80);
        return true;
    }

    public static float getTextWidth(Context Context, String text, int textSize, TextPaint paint) {
        float scaledDensity = Context.getResources().getDisplayMetrics().scaledDensity;
        paint.setTextSize(scaledDensity * textSize);
        return paint.measureText(text);
    }



    /**
     * 隐藏 软键盘
     *
     * @param event     touch event
     * @param mActivity who's pager
     */
    public static void hideSoft(MotionEvent event, Activity mActivity) {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (mActivity.getCurrentFocus() != null) {
                if (mActivity.getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
    }

    /**
     * 隐藏 软键盘
     *
     * @param mActivity who's pager
     */
    public static void hideSoft(Activity mActivity) {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (mActivity.getCurrentFocus() != null) {
            if (mActivity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    public static void showImm(Activity mActivity, View view) {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }


    /**
     * 获取屏幕宽度
     *
     * @param mContext
     */
    public static int getWidth(Context mContext) {
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        return width;
    }

    public static int getheight(Context mContext) {
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        return height;
    }




    /**
     * saveImageForShare(保存分享的图片)
     * (这里描述这个方法适用条件 – 可选)
     * void
     *
     * @throws
     * @since 1.0.0
     */
    public static void saveImageForShare(final String imageUrl, final Handler mShareHandler) {
        new Thread() {
            public void run() {
                boolean isSend = false;
                InputStream is = null;
                URL url = null;
                HttpURLConnection urlConnection = null;
                Looper.prepare();
                try {
                    url = new URL(imageUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        is = urlConnection.getInputStream();
                    } else {
                        mShareHandler.sendEmptyMessage(1);
                        isSend = true;
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    mShareHandler.sendEmptyMessage(1);
                    isSend = true;
                }
                Bitmap bm = BitmapFactory.decodeStream(is);
//				Bitmap mShareImage=changeColor(bm);
                CommonUtils.saveBitmapFile(bm, "/sdcard/wjl/share.jpg");
                if (!isSend) {
                    mShareHandler.sendEmptyMessage(0);
                }

            }

            ;
        }.start();
    }

    public static void saveBitmapFile(Bitmap bitmap, String path) {
        File file = new File(path);// 将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加遮罩阴影
     *
     * @param t 透明度
     */
    public static void setWindow(float t, Activity activity) {
        WindowManager.LayoutParams wl = activity.getWindow().getAttributes();
        wl.alpha = t;
        activity.getWindow().setAttributes(wl);
    }

    /**
     * 真是路径转 文件路径
     *
     * @param context
     * @param uri
     * @return
     */
    public static String filePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String filePath = null;
        if (scheme == null)
            filePath = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            filePath = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        filePath = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return filePath;
    }


    /**
     * double型数据保留两位小数
     * @param data
     * @return
     */
    public static String doubleIn2Num(double data){
        NumberFormat nbf=NumberFormat.getInstance();
        nbf.setMinimumFractionDigits(2);
        return nbf.format(data);
    }

    public static int ScreenHeight(){
        DisplayMetrics dm = new DisplayMetrics();
       return dm.heightPixels;
    }

    public static int ScreenWidth(){
        DisplayMetrics dm = new DisplayMetrics();
        return dm.widthPixels;
    }


    /**
     * 手机唯一标识
     * @return
     */
    public static String getUniqueId(){
        String androidID = Settings.Secure.getString(MyApplication.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;
        try {
            return toMD5(id);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return id;
        }
    }


    private static String toMD5(String text) throws NoSuchAlgorithmException {
        //获取摘要器 MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //通过摘要器对字符串的二进制字节数组进行hash计算
        byte[] digest = messageDigest.digest(text.getBytes());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            //循环每个字符 将计算结果转化为正整数;
            int digestInt = digest[i] & 0xff;
            //将10进制转化为较短的16进制
            String hexString = Integer.toHexString(digestInt);
            //转化结果如果是个位数会省略0,因此判断并补0
            if (hexString.length() < 2) {
                sb.append(0);
            }
            //将循环结果添加到缓冲区
            sb.append(hexString);
        }
        //返回整个结果
        return sb.toString();
    }


    /**
     * h获取版本号
     * @return
     */
    public static int packageCode() {
        PackageManager manager = MyApplication.getContext().getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(MyApplication.getContext().getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     *
     * @return
     */
    public static String getVersionName() {
        PackageManager manager = MyApplication.getContext().getPackageManager();
        String name ="";
        try {
            PackageInfo info = manager.getPackageInfo(MyApplication.getContext().getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }



}
