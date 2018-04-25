package com.sv.honda.repository;

import com.sv.honda.entity.SystemLogEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemLogRepository extends BaseRepository<SystemLogEntity, String> {
}
