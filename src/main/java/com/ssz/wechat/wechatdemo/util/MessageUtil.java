package com.ssz.wechat.wechatdemo.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ssz.wechat.wechatdemo.message.resp.BaseMessage;
import com.ssz.wechat.wechatdemo.message.resp.Article;
import com.ssz.wechat.wechatdemo.message.resp.ImageMessage;
import com.ssz.wechat.wechatdemo.message.resp.MusicMessage;
import com.ssz.wechat.wechatdemo.message.resp.NewsMessage;
import com.ssz.wechat.wechatdemo.message.resp.TextMessage;
import com.ssz.wechat.wechatdemo.message.resp.VideoMessage;
import com.ssz.wechat.wechatdemo.message.resp.VoiceMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * ClassName: MessageUtil
 *
 * @author dapengniao
 * @Description: 消息工具类
 * @date 2016年3月7日 上午10:05:04
 */
public class MessageUtil {
    /**
     * @param @param  request
     * @param @return
     * @param @throws Exception
     * @Description: 解析微信发来的请求（XML）
     * @author dapengniao
     * @date 2016年3月7日 上午10:04:02
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }

    public static String messageToXml(BaseMessage message) {
        if (message instanceof TextMessage) {
            return textMessageToXml((TextMessage) message);
        }
        if (message instanceof NewsMessage) {
            return newsMessageToXml((NewsMessage) message);
        }
        if (message instanceof ImageMessage) {
            return imageMessageToXml((ImageMessage) message);
        }
        if (message instanceof VoiceMessage) {
            return voiceMessageToXml((VoiceMessage) message);
        }
        if (message instanceof VideoMessage) {
            return videoMessageToXml((VideoMessage) message);
        }
        if (message instanceof MusicMessage) {
            return musicMessageToXml((MusicMessage) message);
        }
        return textMessageToXml((TextMessage) message);
    }

    /**
     * @param @param  textMessage
     * @param @return
     * @Description: 文本消息对象转换成xml
     * @author dapengniao
     * @date 2016年3月8日 下午4:13:22
     */
    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);
    }

    /**
     * @param @param  newsMessage
     * @param @return
     * @Description: 图文消息对象转换成xml
     * @author dapengniao
     * @date 2016年3月8日 下午4:14:09
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }

    /**
     * @param @param  imageMessage
     * @param @return
     * @Description: 图片消息对象转换成xml
     * @author dapengniao
     * @date 2016年3月9日 上午9:25:51
     */
    public static String imageMessageToXml(ImageMessage imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    /**
     * @param @param  voiceMessage
     * @param @return
     * @Description: 语音消息对象转换成xml
     * @author dapengniao
     * @date 2016年3月9日 上午9:27:26
     */
    public static String voiceMessageToXml(VoiceMessage voiceMessage) {
        xstream.alias("xml", voiceMessage.getClass());
        return xstream.toXML(voiceMessage);
    }

    /**
     * @param @param  videoMessage
     * @param @return
     * @Description: 视频消息对象转换成xml
     * @author dapengniao
     * @date 2016年3月9日 上午9:31:09
     */
    public static String videoMessageToXml(VideoMessage videoMessage) {
        xstream.alias("xml", videoMessage.getClass());
        return xstream.toXML(videoMessage);
    }

    /**
     * @param @param  musicMessage
     * @param @return
     * @Description: 音乐消息对象转换成xml
     * @author dapengniao
     * @date 2016年3月8日 下午4:13:36
     */
    public static String musicMessageToXml(MusicMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 对象到xml的处理
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}