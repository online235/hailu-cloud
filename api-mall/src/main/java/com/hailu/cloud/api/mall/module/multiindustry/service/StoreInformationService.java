package com.hailu.cloud.api.mall.module.multiindustry.service;

import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
import com.hailu.cloud.api.mall.module.multiindustry.model.StoreInformationListResult;
import com.hailu.cloud.api.mall.module.multiindustry.model.StoreInformationResultModel;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.apache.ibatis.annotations.Param;

import java.text.ParseException;
import java.util.List;

public interface StoreInformationService {

    /**
     * 分类查询商铺
     * @param storeTotalType
     * @param storeSonType
     * @return
     */
    PageInfoModel<List<StoreInformationListResult>> findStoreInformationList(Long storeTotalType, Long storeSonType, String cityCode, Integer size, Integer page) throws ParseException;

    /**
     * 店铺详细信息
     * @param id
     * @return
     */
    StoreInformation findStoreInformation(Long id) throws BusinessException;



    StoreInformationResultModel findStoreInformationLeftAlbum(Long id) throws BusinessException;


}
