package com.ctbu.fastec.example.generators;

import com.ctbu.latte.wechat.templates.WXEntryTemplate;
import com.ctbu.latte_annotations.annotations.EntryGenerator;

/**
 * Created by CaiPengFei on 2017/11/15.
 */
@EntryGenerator(
        packageName = "com.ctbu.fastec.example",
        entryTemplate= WXEntryTemplate.class
)
public interface WeChatEntry {
}
