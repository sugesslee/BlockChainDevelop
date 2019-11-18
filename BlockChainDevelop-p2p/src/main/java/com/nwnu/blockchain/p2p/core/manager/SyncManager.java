package com.nwnu.blockchain.p2p.core.manager;

import com.nwnu.blockchain.p2p.core.model.SyncEntity;
import com.nwnu.blockchain.p2p.core.repository.SyncRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 2:39 PM
 * @since 1.0.0
 */
@Service
public class SyncManager {
    @Resource
    private SyncRepository syncRepository;

    public SyncEntity findLastOne() {
        return syncRepository.findTopByOrderByIdDesc();
    }

    public SyncEntity save(SyncEntity syncEntity) {
        return syncRepository.save(syncEntity);
    }

    public Object findAll(Pageable pageable) {
        return syncRepository.findAll(pageable);
    }

    public void deleteAll() {
        syncRepository.deleteAll();
    }
}
