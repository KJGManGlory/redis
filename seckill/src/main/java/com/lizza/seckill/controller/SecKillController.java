package com.lizza.seckill.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc:
 * @author: lizza1643@gmail.com
 * @date: 2021-01-09
 */
@RestController
public class SecKillController {

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }
}
