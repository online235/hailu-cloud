package com.hailu.cloud.api.mall.module.multiindustry.model;


import com.hailu.cloud.api.mall.module.multiindustry.entity.McStoreAlbum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("店铺相册")
public class StoreAlbumListModel {


    @ApiModelProperty("相册总图片")
    private List<McStoreAlbum> mcStoreAlbumList;


    @ApiModelProperty("1-环境总图片")
    private List<McStoreAlbum> EnvironmentalStoreAlbumList;


    @ApiModelProperty("2-其他总图片")
    private List<McStoreAlbum> OtherStoreAlbumList;



}
