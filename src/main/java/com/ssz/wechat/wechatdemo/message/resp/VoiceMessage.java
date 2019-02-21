package com.ssz.wechat.wechatdemo.message.resp;

/**
 * ClassName: VoiceMessage
 *
 * @author dapengniao
 * @Description: 语音消息
 * @date 2016年3月8日 下午6:02:13
 */
public class VoiceMessage extends BaseMessage {

    private Voice Voice;

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        Voice = voice;
    }


}
