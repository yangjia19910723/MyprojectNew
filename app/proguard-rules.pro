# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\pangjia\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-optimizationpasses 5  #指定代码的压缩级别 0 - 7，一般都是5，无需改变
-dontusemixedcaseclassnames #不使用大小写混合
#告诉Proguard 不要跳过对非公开类的处理，默认是跳过
-dontskipnonpubliclibraryclasses #如果应用程序引入的有jar包，并且混淆jar包里面的class
-verbose #混淆时记录日志（混淆后生产映射文件 map 类名 -> 转化后类名的映射
#指定混淆时的算法，后面的参数是一个过滤器
#这个过滤器是谷歌推荐的算法，一般也不会改变
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#类型转换错误 添加如下代码以便过滤泛型（不写可能会出现类型转换错误，一般情况把这个加上就是了）,即避免泛型被混淆
-keepattributes Signature
#假如项目中有用到注解，应加入这行配置,对JSON实体映射也很重要,eg:fastjson
-keepattributes *Annotation*
#抛出异常时保留代码行数
-keepattributes SourceFile,LineNumberTable
#保持 native 的方法不去混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#继承activity,application,service,broadcastReceiver,contentprovider....不进行混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.support.multidex.MultiDexApplication
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# universal-image-loader 混淆
-dontwarn com.nostra13.universalimageloader.**
-keep class com.nostra13.universalimageloader.** { *; }


#-ButterKnife 7.0
 -keep class butterknife.** { *; }
 -dontwarn butterknife.internal.**
 -keep class **$$ViewBinder { *; }
 -keepclasseswithmembernames class * {
  @butterknife.* <fields>;
 }
 -keepclasseswithmembernames class * {
 @butterknife.* <methods>;
 }

#友盟统计
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}


# support-v7
-dontwarn android.support.v7.**
-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }
-keep class android.support.v7.** { *; }

# support design
#@link http://stackoverflow.com/a/31028536
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }
#-------------------------------------------------------------------------

# picasso
-keep class com.squareup.picasso.** {*; }
-dontwarn com.squareup.picasso.**

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


#v4包下的文件都不要混淆 -dontwarn   如果有警告也不终止
-dontwarn android.support.v4.**
-keep class android.support.v4.app.**{*;}
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment  #所有fragment的子类不要去混淆
-keep public class * extends android.app.Activity  #所有activity的子类不要去混淆
-keep public class * extends android.app.Application #用法同上
-keep public class * extends android.app.Service #用法同上
-keep public class * extends android.content.BroadcastReceiver #用法同上
-keep public class * extends android.content.ContentProvider #用法同上
-keep public class * extends android.app.backup.BackupAgentHelper #用法同上
-keep public class * extends android.preference.Preference #用法同上
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
#保持指定规则的方法不被混淆（Android layout 布局文件中为控件配置的onClick方法不能混淆）
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}
#保持自定义控件指定规则的方法不被混淆
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
#保持枚举 enum 不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#保持 Parcelable 不被混淆（aidl文件不能去混淆）
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
#需要序列化和反序列化的类不能被混淆（注：Java反射用到的类也不能被混淆）
-keepnames class * implements java.io.Serializable
#保护实现接口Serializable的类中，指定规则的类成员不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
#保持R文件不被混淆，否则，你的反射是获取不到资源id的
-keep class **.R$* { *; }
#以下针对App本身设置
#保护WebView对HTML页面的API不被混淆
-keep class **.Webview2JsInterface { *; }
#如果你的项目中用到了webview的复杂操作,最好加入
-keepclassmembers class * extends android.webkit.WebViewClient {
  public void *(android.webkit.WebView,java.lang.String,android.graphics.Bitmap);
  public boolean *(android.webkit.WebView,java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebChromeClient {
  public void *(android.webkit.WebView,java.lang.String);
}
#对WebView的简单说明下：经过实战检验,做腾讯QQ登录，如果引用他们提供的jar，
#若不加防止WebChromeClient混淆的代码，oauth认证无法回调，反编译基代码后可看到他们有用到WebChromeClient，加入此代码即可。
#转换JSON的JavaBean，类成员名称保护，使其不被混淆
-keepclassmembernames class com.cgv.cn.movie.common.bean.** { *; }
#保证自定义类不被混淆 XXX换成你自己的包名
-keep class com.meifaxuetang.view.** {*;}
# 保持实体数据结构接口不被混淆(也就是被GSON注解的实体结构)此处是自己接口的包名 XXX换成你自己的包名
-keep class com.meifaxuetang.entity.** { *; }
#使用gson包解析数据时，出现 missing type parameter 异常，添加如下代码
-dontobfuscate #不混淆输入的类文件
-dontoptimize  #不优化输入的类文件
# 不混淆 GSON
-keep class com.google.gson.** { *; }
-keep class com.google.gson.JsonObject {*;}
-keep class org.json.** {*;}
-keep class com.badlogic.** { *;}
-keep class * extends com.badlogic.gdx.utils.Json*
-keep class com.google.** {*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.futurice.project.models.pojo.** { *; }
#fresco 防止该包下native类别优化时处理掉,运行奔溃
-keep class com.facebook.imagepipeline.nativecode.**{*;}
-keep class android.os.**{*;}
#2.0.9后加入语音通话功能，如需使用此功能的api，加入以下keep
-dontwarn ch.imvs.**
-dontwarn org.slf4j.**
-keep class org.ice4j.** {*;}
-keep class net.java.sip.** {*;}
-keep class org.webrtc.voiceengine.** {*;}
-keep class org.bitlet.** {*;}
-keep class org.slf4j.** {*;}
-keep class ch.imvs.** {*;}
