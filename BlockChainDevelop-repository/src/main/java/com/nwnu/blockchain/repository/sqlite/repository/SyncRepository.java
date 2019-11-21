package com.nwnu.blockchain.repository.sqlite.repository;

import com.nwnu.blockchain.core.model.SyncEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <pre>
 *  Version         Date            Author          Description
 * ------------------------------------------------------------
 *  1.0.0           2019/11/18     red        -
 * </pre>
 *
 * @author red
 * @version 1.0.0 2019/11/18 8:35 AM
 * @since 1.0.0
 */
public interface SyncRepository extends JpaRepository<SyncEntity, Long> {
	SyncEntity findTopByOrderByIdDesc();
}
