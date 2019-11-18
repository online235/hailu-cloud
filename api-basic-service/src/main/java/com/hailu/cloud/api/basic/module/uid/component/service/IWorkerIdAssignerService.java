package com.hailu.cloud.api.basic.module.uid.component.service;

/**
 * Represents a worker id assigner for {@link com.hailu.cloud.api.basic.module.uid.component.impl.DefaultUidGenerator}
 * 
 * @author yutianbao
 */
public interface IWorkerIdAssignerService {

    /**
     * Assign worker id for {@link com.hailu.cloud.api.basic.module.uid.component.impl.DefaultUidGenerator}
     * 
     * @return assigned worker id
     */
    long assignWorkerId();

}
