package com.ssz.wechat.wechatdemo.commom;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssz.wechat.wechatdemo.util.HttpUtils;
import com.ssz.wechat.wechatdemo.web.util.GlobalConstants;

/**
 * ClassName: GetUseInfo
 *
 * @author dapengniao
 * @Description: 获取微信用户信息
 * @date 2016年3月18日 下午2:00:52
 */
public class GetUseInfo {
    /**
     * @param @param  openid
     * @param @return
     * @param @throws Exception
     * @Description: 通过openid获取用户微信信息
     * @author dapengniao
     * @date 2016年3月18日 下午2:01:30
     */
    public static HashMap<String, String> Openid_userinfo(String openid) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("access_token", GlobalConstants.getInterfaceUrl("access_token")); // 定时器中获取到的token
        params.put("openid", openid); // 需要获取的用户的openid
        params.put("lang", "zh_CN");
        String subscribers = HttpUtils.sendGet(GlobalConstants.getInterfaceUrl("OpenidUserinfoUrl"), params);
        System.out.println(subscribers);
        params.clear();
        // 这里返回参数只取了昵称、头像、和性别

        JSONObject object = (JSONObject) JSON.parse(subscribers);

        params.put("nickname", object.getString("nickname")); // 昵称
        params.put("headimgurl", object.getString("headimgurl")); // 图像
        params.put("sex", object.getString("sex")); // 性别
        return params;
    }

}
