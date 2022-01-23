package vip.sgtv.lecast;

import android.net.Uri;
import android.util.Log;

import com.hpplay.sdk.source.api.IConnectListener;
import com.hpplay.sdk.source.api.ILelinkPlayerListener;
import com.hpplay.sdk.source.api.LelinkPlayerInfo;
import com.hpplay.sdk.source.api.LelinkSourceSDK;
import com.hpplay.sdk.source.browse.api.IBrowseListener;
import com.hpplay.sdk.source.browse.api.LelinkServiceInfo;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LecastUtil {
    public static boolean isInitialized = false;

    public static void init(final String appId, final String apiKey) {
        LelinkSourceSDK.getInstance().bindSdk(LecastPlugin.activity.getApplicationContext(), appId, apiKey, (boolean success) -> isInitialized = success);
        LelinkSourceSDK.getInstance().setBrowseResultListener((resultCode, list) -> {
            Log.d("Lecast",resultCode+"\t"+list);
            if (resultCode == IBrowseListener.BROWSE_TIMEOUT)
                LecastPlugin.send("searchTimeout", null);
            else if (resultCode == IBrowseListener.BROWSE_STOP)
                LecastPlugin.send("searchStop", null);
            else if (resultCode == IBrowseListener.BROWSE_SUCCESS)
                LecastPlugin.send("searchSuccess", JsonUtil.from(list));
            else if (resultCode == IBrowseListener.BROWSE_ERROR_AUTH)
                LecastPlugin.send("searchAuthError", null);

        });
        LelinkSourceSDK.getInstance().setConnectListener(new IConnectListener() {

            @Override
            public void onConnect(LelinkServiceInfo serviceInfo, int connectType) {
                LecastPlugin.send("onConnect", JsonUtil.from(serviceInfo));
            }

            @Override
            public void onDisconnect(LelinkServiceInfo lelinkServiceInfo, int i, int i1) {

            }
        });
        LelinkSourceSDK.getInstance().setPlayListener(new ILelinkPlayerListener() {
            @Override
            public void onLoading() {
                LecastPlugin.send("onLoading", null);
            }

            @Override
            public void onStart() {
                LecastPlugin.send("onStart", null);
            }

            @Override
            public void onPause() {
                LecastPlugin.send("onPause", null);
            }

            @Override
            public void onCompletion() {
                LecastPlugin.send("onCompletion", null);
            }

            @Override
            public void onStop() {
                LecastPlugin.send("onStop", null);
            }

            @Override
            public void onSeekComplete(int pPosition) {
            }

            @Override
            public void onInfo(int what, int extra) {
            }

            @Override
            public void onInfo(int i, String s) {

            }

            @Override
            public void onError(int what, int extra) {
                LecastPlugin.send("onError", null);
            }

            @Override
            public void onVolumeChanged(float percent) {
            }

            @Override
            public void onPositionUpdate(long duration, long position) {
                LecastPlugin.send("onPositionUpdate", new HashMap() {{
                    put("duration", duration);
                    put("position", position);
                }});
            }
        });
    }

    public static void startSearch() {
        LelinkSourceSDK.getInstance().startBrowse();
    }

    public static void stopSearch() {
        LelinkSourceSDK.getInstance().stopBrowse();
    }

    public static void connect(String uid,String ip,String name) {
        LelinkServiceInfo info = new LelinkServiceInfo();
        info.setUid(uid);
        info.setIp(ip);
        info.setName(name);
        LelinkSourceSDK.getInstance().connect(info);
    }

    public static void disconnect(String uid,String ip,String name) {
        LelinkServiceInfo info = new LelinkServiceInfo();
        info.setUid(uid);
        info.setIp(ip);
        info.setName(name);
        LelinkSourceSDK.getInstance().disconnect(info);
    }

    public static List<Map> getConnectedDevices() {
        List<LelinkServiceInfo> infos = LelinkSourceSDK.getInstance().getConnectInfos();
        return JsonUtil.from(infos);
    }

    public static void play(String url) {
        LelinkPlayerInfo playerInfo = new LelinkPlayerInfo();
        playerInfo.setType(LelinkSourceSDK.MEDIA_TYPE_VIDEO);
        playerInfo.setRetryDLNAHttp(true);
        if (url.toLowerCase().startsWith("http"))
            playerInfo.setUrl(url);
        else
            playerInfo.setLocalUri(Uri.fromFile(new File(url)));
        LelinkSourceSDK.getInstance().startPlayMedia(playerInfo);
    }

    public static void pausePlay() {
        LelinkSourceSDK.getInstance().pause();
    }

    public static void resumePlay() {
        LelinkSourceSDK.getInstance().resume();
    }

    public static void stopPlay() {
        LelinkSourceSDK.getInstance().stopPlay();
    }

    public static void seekTo(int seconds) {
        LelinkSourceSDK.getInstance().seekTo(seconds);
    }

    public static void addVolume() {
        LelinkSourceSDK.getInstance().addVolume();
    }

    public static void subVolume() {
        LelinkSourceSDK.getInstance().subVolume();
    }
}
