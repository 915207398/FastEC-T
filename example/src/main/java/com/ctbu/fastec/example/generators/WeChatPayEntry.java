package com.ctbu.fastec.example.generators;

import com.ctbu.latte.wechat.templates.WXPayEntryTemplate;
import com.ctbu.latte_annotations.annotations.PayEntryGenerator;

/**
 * Created by CaiPengFei on 2017/11/15.
 */
@PayEntryGenerator(
        packageName = "com.ctbu.fastec.example",
        PayEntryTemplate= WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
