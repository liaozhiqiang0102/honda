package com.sv.honda.util;

import com.sv.honda.entity.SystemLogEntity;
import com.sv.honda.repository.SystemLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.UUID;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private SystemLogRepository systemLogRepository;

    /**
     * 使用AOP方式将所有有关事务类的操作记录log
     */
    @After("execution(public * com.sv.honda.service..*(..)) && @annotation(org.springframework.transaction.annotation.Transactional)")
    public void saveLog(JoinPoint joinPoint){
        System.out.println(joinPoint);
        SystemLogEntity systemLogEntity = new SystemLogEntity();
        systemLogEntity.setActivityModuleName("产品");
        systemLogEntity.setActivityType(4);
        systemLogEntity.setLogMessage("新建产品");
        systemLogEntity.setLogTimestamp(new Timestamp(System.currentTimeMillis()));
        systemLogEntity.setUserId(UUID.randomUUID().toString());
        systemLogEntity.setActivityClass(joinPoint.getSignature().toString());
        this.systemLogRepository.save(systemLogEntity);
    }
}
