package com.boot.mytt.core.json.jsonserializer;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class PledgeDetailItemVO implements Serializable {

    private static final long serialVersionUID = 2308418084162500432L;

    private String remark;

    @JsonSerialize(using = Date2LongSerialize.class)
    private Date addTime;
}
