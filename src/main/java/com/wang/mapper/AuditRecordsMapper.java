package com.wang.mapper;

import com.wang.model.AuditRecords;

public interface AuditRecordsMapper {

    /**
     * 插入
     * @param auditRecords 审核记录实体
     */
    void insert(AuditRecords auditRecords);

    /**
     * 删除
     * @param id 审核记录id
     */
    void delete(int id);

    /**
     * 查找
     * @param auditRecords 审核记录
     * @return 找到的审核记录
     */
    AuditRecords select(AuditRecords auditRecords);
}
