package com.csi0n.zhihudaily;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.csi0n.zhihudaily.utils.FileUtils;
import com.csi0n.zhihudaily.utils.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Created by chqss on 2016/2/27 0027.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private final Context mContext;
    // log文件的后缀名
    public static final String FILE_NAME_SUFFIX = "zhihudaily.log";
    private static CrashHandler sInstance = null;
    //private SendEmail sendEmail;
    private CrashHandler(Context cxt) {
        // 将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        // 获取Context，方便内部使用
        mContext = cxt;
    }
    public synchronized static CrashHandler create(Context cxt) {
        if (sInstance == null) {
            sInstance = new CrashHandler(cxt);
        }
        return sInstance;
    }
    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个ex，我们就可以得到异常信息。
     */
    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        // 导出异常信息到SD卡中
        try {
            saveToSDCard(ex);
        } catch (Exception e) {
        } finally {
            ex.printStackTrace();// 调试时打印日志信息
            System.exit(0);
            //sendMail();
        }
    }

    // public static void sendAppCrashReport(final Context context) {
    // ViewInject.create().getExitDialog(context,
    // "对不起，小屁孩发脾气了，我们会替你好好教训一下他的。", new OnClickListener() {
    // @Override
    // public void onClick(DialogInterface dialog, int which) {
    // System.exit(-1);
    // }
    // });
    // }

    private void saveToSDCard(Throwable ex) throws Exception {
        File file = FileUtils.getSaveFile(Config.saveFolder, FILE_NAME_SUFFIX);
        boolean append = false;
        if (System.currentTimeMillis() - file.lastModified() > 5000) {
            append = true;
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
                file, append)));
        // 导出发生异常的时间
        pw.println(StringUtils.getDataTime("yyyy-MM-dd-HH-mm-ss"));
        // 导出手机信息
        dumpPhoneInfo(pw);
        pw.println();
        // 导出异常的调用栈信息
        ex.printStackTrace(pw);
        pw.println();
        pw.close();
    }

    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        // 应用的版本名称和版本号
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
                PackageManager.GET_ACTIVITIES);
        pw.print("App Version: ");
        pw.print(pi.versionName);
        pw.print('_');
        pw.println(pi.versionCode);
        pw.println();

        // android版本号
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);
        pw.println();

        // 手机制造商
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);
        pw.println();

        // 手机型号
        pw.print("Model: ");
        pw.println(Build.MODEL);
        pw.println();

        // cpu架构
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
        pw.println();
    }

/*    private void sendMail() {
        sendEmail = new SendEmail();
    }*/

    private static final String EMAIL_REXP = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)" + "*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private String[] extractEmails(final String all) {
        Pattern pattern = Pattern.compile(EMAIL_REXP);
        StringTokenizer tokenizer = new StringTokenizer(all, ";");
        List<String> mailList = new ArrayList<String>();

        String currentMail;
        while (tokenizer.hasMoreElements()) {
            currentMail = (String) tokenizer.nextElement();
            if (pattern.matcher(currentMail.trim()).matches()) {
                mailList.add(currentMail);
            }
        }
        return mailList.toArray(new String[0]);
    }

/*    private class SendEmail extends AsyncTask<Void, Void, Void> {
        public SendEmail() {
            this.execute(null, null, null, null, null);
        }

        Exception error = null;

        @Override
        protected void onPreExecute() {
            KJLoger.debug("开始发送");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String[] tos = extractEmails("841506740@qq.com");
                MailSender m = new MailSender();
                m.setCredentials("csi0n_zhihuijia@126.com", "unmangetswvfdrck").setToAddresses(tos);
                m.setSubject("智慧家管理员BUG报告").setMailText(FILE_NAME_SUFFIX);
                m.attachFile(FILE_NAME_SUFFIX, (FileUtils.getSaveFile(Config.saveFolder, FILE_NAME_SUFFIX)).getAbsolutePath());
                m.useMailPropertiesSNMP("smtp.126.com", 25, 465, true);
                m.send();
            } catch (Exception e) {
                error = e;
            } finally {
                FileUtils.getSaveFile(Config.saveFolder, FILE_NAME_SUFFIX).delete();
                System.exit(0);
            }
            return null;
        }
    }*/
}
