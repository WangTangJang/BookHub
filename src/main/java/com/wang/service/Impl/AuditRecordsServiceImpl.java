package com.wang.service.Impl;

import com.wang.mapper.AuditRecordsMapper;
import com.wang.model.AuditRecords;
import com.wang.service.AuditRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditRecordsServiceImpl implements AuditRecordsService {

    @Autowired
    AuditRecordsMapper mapper;

    @Override
    public void insert(AuditRecords auditRecords) {
        mapper.insert(auditRecords);
    }

    @Override
    public void deleteById(int id) {
        mapper.delete(id);
    }

    @Override
    public AuditRecords selectBySubmitterId(int id) {
        AuditRecords auditRecords = new AuditRecords();
        auditRecords.setSubmitterId(id);
        return mapper.select(auditRecords);
    }

}
