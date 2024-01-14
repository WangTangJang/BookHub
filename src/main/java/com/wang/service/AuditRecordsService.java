package com.wang.service;

import com.wang.model.AuditRecords;

public interface AuditRecordsService {

    void insert(AuditRecords auditRecords);

    void deleteById(int id);

    AuditRecords selectBySubmitterId(int id);
}
