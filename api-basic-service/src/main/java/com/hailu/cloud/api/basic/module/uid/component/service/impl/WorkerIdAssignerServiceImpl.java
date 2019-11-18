package com.hailu.cloud.api.basic.module.uid.component.service.impl;

import com.hailu.cloud.api.basic.module.uid.component.service.IWorkerIdAssignerService;
import com.hailu.cloud.api.basic.module.uid.component.utils.NetUtils;
import com.hailu.cloud.api.basic.module.uid.dao.GlobalWorkerNodeMapper;
import com.hailu.cloud.api.basic.module.uid.entity.GlobalWorkerNodeEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Represents an implementation of {@link IWorkerIdAssignerService},
 * the worker id will be discarded after assigned to the UidGenerator
 *
 * @author yutianbao
 */
@Slf4j
@Service
public class WorkerIdAssignerServiceImpl implements IWorkerIdAssignerService {

    @Resource
    private GlobalWorkerNodeMapper workerNodeDAO;

    /**
     * Assign worker id base on database.<p>
     * If there is host name & port in the environment, we considered that the node runs in Docker container<br>
     * Otherwise, the node runs on an actual machine.
     *
     * @return assigned worker id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public long assignWorkerId() {
        // build worker node entity
        GlobalWorkerNodeEntity workerNodeEntity = buildWorkerNode();

        // add worker node for new (ignore the same IP + PORT)
        workerNodeDAO.addWorkerNode(workerNodeEntity);
        log.info("Add worker node:" + workerNodeEntity);

        return workerNodeEntity.getId();
    }

    /**
     * Build worker node entity by IP and PORT
     */
    private GlobalWorkerNodeEntity buildWorkerNode() {
        GlobalWorkerNodeEntity workerNodeEntity = new GlobalWorkerNodeEntity();
        workerNodeEntity.setType(2);
        workerNodeEntity.setHostname(NetUtils.getLocalAddress());
        workerNodeEntity.setPort(System.currentTimeMillis() + "-" + RandomUtils.nextInt(100000, 100000));

        return workerNodeEntity;
    }

}
