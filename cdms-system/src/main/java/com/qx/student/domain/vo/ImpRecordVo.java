package com.qx.student.domain.vo;

import com.qx.student.domain.*;

import java.io.Serializable;
import java.util.List;

public class ImpRecordVo implements Serializable{

    private static final long serialVersionUID = 1L;

    private ImpRecord impRecord;

    private List<HistorySupportRecord> historySupportRecordList;

    private List<TgcheckSupportRecord> tgcheckSupportRecordList;

    private List<JscheckSupportRecord> jscheckSupportRecordList;

    private List<FzcheckSupportRecord> fzcheckSupportRecordList;

    private Long studentTrainId;

    private String supports;

    public String getSupports() {
        return supports;
    }

    public void setSupports(String supports) {
        this.supports = supports;
    }

    public ImpRecord getImpRecord() {
        return impRecord;
    }

    public void setImpRecord(ImpRecord impRecord) {
        this.impRecord = impRecord;
    }

    public List<HistorySupportRecord> getHistorySupportRecordList() {
        return historySupportRecordList;
    }

    public void setHistorySupportRecordList(List<HistorySupportRecord> historySupportRecordList) {
        this.historySupportRecordList = historySupportRecordList;
    }

    public List<TgcheckSupportRecord> getTgcheckSupportRecordList() {
        return tgcheckSupportRecordList;
    }

    public void setTgcheckSupportRecordList(List<TgcheckSupportRecord> tgcheckSupportRecordList) {
        this.tgcheckSupportRecordList = tgcheckSupportRecordList;
    }

    public List<JscheckSupportRecord> getJscheckSupportRecordList() {
        return jscheckSupportRecordList;
    }

    public void setJscheckSupportRecordList(List<JscheckSupportRecord> jscheckSupportRecordList) {
        this.jscheckSupportRecordList = jscheckSupportRecordList;
    }

    public List<FzcheckSupportRecord> getFzcheckSupportRecordList() {
        return fzcheckSupportRecordList;
    }

    public void setFzcheckSupportRecordList(List<FzcheckSupportRecord> fzcheckSupportRecordList) {
        this.fzcheckSupportRecordList = fzcheckSupportRecordList;
    }

    public Long getStudentTrainId() {
        return studentTrainId;
    }

    public void setStudentTrainId(Long studentTrainId) {
        this.studentTrainId = studentTrainId;
    }
}
