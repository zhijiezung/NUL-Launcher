package com.xhh.launcher.custom.util;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import com.xhh.launcher.custom.R;

import static android.text.Html.FROM_HTML_MODE_COMPACT;

/**
 * <p>获取异常信息以及手机信息.</p>
 * <p>创建时间: 18-1-25</p>
 *
 * @author xhh
 */
public class ExceptionUtil {

    /**
     * 传入的Context
     */
    private Context mContext;

    public ExceptionUtil(Context context) {
        this.mContext = context;
    }

    /**
     * <p>打印手机信息.</p>
     * <p>创建时间: 18-1-25</p>
     *
     * @param textView 被打印的TextView
     */
    public void printPhoneInfo(TextView textView) {
        textView.append(mContext.getString(R.string.log_message_phone_brand));
        textView.append(Html.fromHtml("<font color=\"#E51C23\">" + PhoneUtil.getBrand() +
                "</font>", FROM_HTML_MODE_COMPACT));
        textView.append("\n");
        textView.append(mContext.getString(R.string.log_message_phone_model));
        textView.append(Html.fromHtml("<font color=\"#E51C23\">" + PhoneUtil.getModel() + "</font>", FROM_HTML_MODE_COMPACT));
        textView.append("\n");
        textView.append(mContext.getString(R.string.log_message_phone_product));
        textView.append(Html.fromHtml("<font color=\"#E51C23\">" + PhoneUtil.getProduct() + "</font>", FROM_HTML_MODE_COMPACT));
        textView.append("\n");
        textView.append(mContext.getString(R.string.log_message_phone_ver));
        textView.append(Html.fromHtml("<font color=\"#E51C23\">" + PhoneUtil.getAndroidVersion() + "</font>", FROM_HTML_MODE_COMPACT));
        textView.append("\n");
        textView.append(mContext.getString(R.string.log_message_phone_appver));
        textView.append(Html.fromHtml("<font color=\"#E51C23\">" + PhoneUtil.getAppVersion(mContext) + "</font>", FROM_HTML_MODE_COMPACT));

        textView.append("\n");
    }

    /**
     * <p>打印错误信息.</p>
     * <p>创建时间: 18-1-25</p>
     *
     * @param textView  被打印的TextView
     * @param throwable 抛出的异常
     */
    public void printError(TextView textView, Throwable throwable) {
        textView.append(mContext.getString(R.string.log_message_error));
        textView.append(Html.fromHtml("<font color=\"#E51C23\">" + throwable.getMessage() + "</font>", FROM_HTML_MODE_COMPACT));
        textView.append("\n");
        for (StackTraceElement stackTraceElement : throwable.getStackTrace()) {
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            String fileName = stackTraceElement.getFileName();
            String line = stackTraceElement.getLineNumber() + "";
            textView.append("\t\t\t\t\t\t");
            textView.append(Html.fromHtml("<font  color=\"#E51C23\">at</font>", FROM_HTML_MODE_COMPACT));
            textView.append("\t" + className);
            textView.append("." + methodName);
            textView.append("(");
            textView.append(Html.fromHtml("<font color=\"#E51C23\">" + fileName + "</font>", FROM_HTML_MODE_COMPACT));
            textView.append(":");
            textView.append(Html.fromHtml("<u><font color=\"#5677FC\">" + line + "</font></u>", FROM_HTML_MODE_COMPACT));
            textView.append(")");
            textView.append("\n");
        }
    }

}
