package com.ctbu.fastec.example.generators;

import com.ctbu.latte.wechat.templates.AppRegisterTemplate;
import com.ctbu.latte_annotations.annotations.AppRegisterGenerator;

/**
 * Created by CaiPengFei on 2017/11/15.
 */
@AppRegisterGenerator(
        packageName = "com.ctbu.fastec.example",
        registerTemplate= AppRegisterTemplate.class
)
public interface AppRegister {
}
