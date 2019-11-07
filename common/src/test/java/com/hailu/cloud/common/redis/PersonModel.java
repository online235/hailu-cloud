package com.hailu.cloud.common.redis;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import lombok.Getter;
import lombok.Setter;

@InjectDict
@Getter
@Setter
public class PersonModel {

    private String categoryId;

    @DictName(code = "CATEGORY", joinField = "categoryId")
    private String categoryName;

    @DictName(code = "CATEGORY", joinField = "categoryId")
    private String statusDisplay;

    private String statusDisplay2;

}
