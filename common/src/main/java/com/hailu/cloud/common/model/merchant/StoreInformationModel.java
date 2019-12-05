package com.hailu.cloud.common.model.merchant;

import com.hailu.cloud.common.fill.annotation.DictName;
import com.hailu.cloud.common.fill.annotation.InjectDict;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xuzhijie
 */
@Data
@InjectDict
public class StoreInformationModel {

    private Long storeId;

    private Integer toExamine;

    @DictName(code = "TO_EXAMINE", joinField = "toExamine")
    @ApiModelProperty(value = "审核")
    private String toExamineDisPlay;

}
