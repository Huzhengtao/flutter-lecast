package vip.sgtv.lecast;

import android.app.Activity;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * LecastPlugin
 */
public class LecastPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    public static MethodChannel channel;
    public static Activity activity;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "lecast");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("init")) {
            ArrayList<String> args = (ArrayList<String>) call.arguments;
            LecastUtil.init(args.get(0), args.get(1));
        } else if (call.method.equals("startSearch")) {
            LecastUtil.startSearch();
        } else if (call.method.equals("stopSearch")) {
            LecastUtil.stopSearch();
        } else if (call.method.equals("connect")) {
            ArrayList<String> args = (ArrayList<String>) call.arguments;
            LecastUtil.connect(args.get(0), args.get(1), args.get(2));
        } else if (call.method.equals("disconnect")) {
            ArrayList<String> args = (ArrayList<String>) call.arguments;
            LecastUtil.disconnect(args.get(0), args.get(1), args.get(2));
        } else if (call.method.equals("getConnectedDevices")) {
            result.success(LecastUtil.getConnectedDevices());
        } else if (call.method.equals("play")) {
            LecastUtil.play((String) call.arguments);
        } else if (call.method.equals("pausePlay")) {
            LecastUtil.pausePlay();
        } else if (call.method.equals("resumePlay")) {
            LecastUtil.resumePlay();
        } else if (call.method.equals("stopPlay")) {
            LecastUtil.stopPlay();
        } else if (call.method.equals("seekTo")) {
            LecastUtil.seekTo((Integer) call.arguments);
        } else if (call.method.equals("addVolume")) {
            LecastUtil.addVolume();
        } else if (call.method.equals("subVolume")) {
            LecastUtil.subVolume();
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        activity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {

    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {

    }

    @Override
    public void onDetachedFromActivity() {

    }

    public static void send(String method, Object data) {
        activity.runOnUiThread(() -> channel.invokeMethod(method, data));
    }
}
