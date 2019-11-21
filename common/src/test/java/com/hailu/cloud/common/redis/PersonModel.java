package com.hailu.cloud.common.redis;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@InjectDict
@Getter
@Setter
public class PersonModel {

    private String categoryId;

    private int a;

    private Integer b;

    private long c;

    private Long d;

    private Boolean e;

    private boolean f;

    private Date g;

    @DictName(code = "CATEGORY", joinField = "categoryId")
    private String categoryName;

    @DictName(code = "CATEGORY", joinField = "categoryId")
    private String statusDisplay;

    private String statusDisplay2;

    private PersonModel next;

    List<PersonModel> personModels = new ArrayList<>();

}
