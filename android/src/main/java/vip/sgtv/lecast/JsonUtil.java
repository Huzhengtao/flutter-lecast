package vip.sgtv.lecast;

import com.hpplay.sdk.source.browse.api.LelinkServiceInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    static public Map from(final LelinkServiceInfo obj) {
        return new HashMap() {{
            put("uid", obj.getUid());
            put("name", obj.getName());
            put("ip", obj.getIp());
            put("port", obj.getPort());
            put("type", obj.getTypes());
            put("isOnLine", obj.isOnLine());
        }};
    }

    static public List<Map> from(final List<LelinkServiceInfo> objs) {
        List<Map> list = new ArrayList();
        if (objs != null)
            for (LelinkServiceInfo obj : objs) {
                list.add(from(obj));
            }
        return list;
    }
}
