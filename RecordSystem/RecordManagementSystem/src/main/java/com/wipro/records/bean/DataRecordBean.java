package com.wipro.records.bean;

import java.util.Date;

public class DataRecordBean {

    private String recordId;
    private String recordName;
    private String category;
    private Date createdDate;
    private String description;
    private String remarks;

    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }

    public String getRecordName() { return recordName; }
    public void setRecordName(String recordName) { this.recordName = recordName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
