package com.aiocloud.ruankao.portal.web.ruankao.controller;

import cn.hutool.core.util.StrUtil;
import com.aiocloud.ruankao.portal.web.ruankao.service.UserService;
import com.aiocloud.ruankao.portal.web.ruankao.vo.SendCodeVO;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @description: WechatController.java
 * @copyright: @copyright (c) 2022
 * @company: aiocloud
 * @author: panyong
 * @version: 1.0.0
 * @createTime: 2025-09-27 19:06
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/wechat")
public class WechatController {

    private final WxMpService wxMpService;
    private final UserService userService;


    /**
     * 验证微信服务器（GET请求）
     */
    @GetMapping("/handler")
    public String authGet(
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam("echostr") String echostr) {

        if (wxMpService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }
        return "error";
    }

    /**
     * 处理微信消息（POST请求）
     */
    @PostMapping(value = "/handler", produces = "application/xml; charset=UTF-8")
    public String post(@RequestBody String requestBody,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce) {

        // 验证签名
        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            return "error";
        }

        try {
            // 解析XML消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);

            String replyText;
            String content = inMessage.getContent();
            if (content.startsWith("验证码")) {
                String key = content.substring(2);
                String verificationCode = userService.getAndCheckVerificationCode(new SendCodeVO(key));
                if (StrUtil.isEmpty(verificationCode)) {
                    replyText = "验证码不存在或者已经失效，请在注册页面点击刷新";
                } else {
                    replyText = "您查询的验证码（编号：" + key + "）是：\n" +
                            "🔐 " + verificationCode + "\n\n" +
                            "*请妥善保管，勿泄露给他人*";
                }

            } else {
                // 构建回复消息
                replyText = "收到消息：" + content;
            }

            WxMpXmlOutMessage outMessage = WxMpXmlOutMessage.TEXT()
                    .content(replyText)
                    .fromUser(inMessage.getToUser())
                    .toUser(inMessage.getFromUser())
                    .build();

            return outMessage.toXml();

        } catch (Exception e) {
            return "error";
        }
    }
}