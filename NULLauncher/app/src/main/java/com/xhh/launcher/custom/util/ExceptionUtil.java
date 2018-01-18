package com.xhh.launcher.custom.util;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by nameh on 2018/1/18 0018.
 */

public class ExceptionUtil {

    private Context context;
    private PhoneUtil phoneInfo;

    public ExceptionUtil(Context context) {
        this.context = context;
        this.phoneInfo = new PhoneUtil(context);
    }

    public void printPhoneInfo(TextView textView) {
        textView.append("手机品牌:");
        textView.append(Html.fromHtml("<font color=\"#E51C23\">" + phoneInfo.getBrand() + "</font>"));
        textView.append("\n");
        textView.append("手机型号:");
        textView.append(Html.fromHtml("<font color=\"#E51C23\">" + phoneInfo.getModel() + "</font>"));
        textView.append("\n");
        textView.append("名称:");
        textView.append(Html.fromHtml("<font color=\"#E51C23\">" + phoneInfo.getProduct() + "</font>"));
        textView.append("\n");
        textView.append("安卓版本:");
        textView.append(Html.fromHtml("<font color=\"#E51C23\">" + phoneInfo.getAndroidVersion() + "</font>"));
        textView.append("\n");
        textView.append("软件版本:");
        textView.append(Html.fromHtml("<font color=\"#E51C23\">" + phoneInfo.getAppVersion() + "</font>"));

        textView.append("\n");
    }

    public void printError(TextView textView, Throwable throwable) {
        textView.append("错误信息: ");
        textView.append(Html.fromHtml("<font color=\"#E51C23\">" + throwable.getMessage() + "</font>"));
        textView.append("\n");
        for (StackTraceElement stackTraceElement : throwable.getStackTrace()) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            String fileName = stackTraceElement.getFileName();
            String line = stackTraceElement.getLineNumber() + "";
            textView.append("\t\t\t\t\t\t");
            textView.append(Html.fromHtml("<font  color=\"#E51C23\">at</font>"));
            textView.append("\t" + className);
            textView.append("." + methodName);
            textView.append("(");
            textView.append(Html.fromHtml("<font color=\"#E51C23\">" + fileName + "</font>"));
            textView.append(":");
            textView.append(Html.fromHtml("<u><font color=\"#5677FC\">" + line + "</font></u>"));
            textView.append(")");
            textView.append("\n");
        }
    }

}
