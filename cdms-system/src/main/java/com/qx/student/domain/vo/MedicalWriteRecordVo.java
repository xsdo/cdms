package com.qx.student.domain.vo;

import com.qx.student.domain.MedicalWriteRecord;

/**
 * 病历书写扩展类
 */
public class MedicalWriteRecordVo {

    private MedicalWriteRecord medicalWriteRecord;

    private Long studentTrainId;

    public MedicalWriteRecord getMedicalWriteRecord() {
        return medicalWriteRecord;
    }

    public void setMedicalWriteRecord(MedicalWriteRecord medicalWriteRecord) {
        this.medicalWriteRecord = medicalWriteRecord;
    }

    public Long getStudentTrainId() {
        return studentTrainId;
    }

    public void setStudentTrainId(Long studentTrainId) {
        this.studentTrainId = studentTrainId;
    }
}
