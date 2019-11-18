package com.hailu.cloud.api.basic.module.uid.dao;

import com.hailu.cloud.api.basic.module.uid.entity.GlobalWorkerNodeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yutianbao
 */
@Mapper
public interface GlobalWorkerNodeMapper {

    /**
     * 根据ip,端口查找主机
     *
     * @param host 主机
     * @param port 端口
     * @return
     */
    GlobalWorkerNodeEntity getWorkerNodeByHostPort(@Param("host") String host, @Param("port") String port);

    /**
     * 添加主机
     *
     * @param workerNodeEntity
     */
    void addWorkerNode(GlobalWorkerNodeEntity workerNodeEntity);

}
