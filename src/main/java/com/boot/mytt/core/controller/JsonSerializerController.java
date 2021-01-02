package com.boot.mytt.core.controller;

import com.boot.mytt.core.json.jsonserializer.PledgeDetailItemVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author renwq
 * @date 2021/1/2 18:27
 */
@RestController
public class JsonSerializerController {

    @RequestMapping("jsonSerializer")
    public PledgeDetailItemVO fn() {
        PledgeDetailItemVO res = new PledgeDetailItemVO();
        res.setAddTime(new Date());
        return res;
    }
}
