package com.wipro.records.service;

import java.text.SimpleDateFormat;

import java.util.List;

import com.wipro.records.bean.DataRecordBean;
import com.wipro.records.dao.DataRecordDAO;

public class Administrator {

    DataRecordDAO dao = new DataRecordDAO();
    public String addRecord(String recordName,
                            String category,
                            String createdDate,
                            String description,
                            String remarks) {

        try {
            java.util.Date utilDate =
                    new SimpleDateFormat("yyyy-MM-dd").parse(createdDate);
            java.sql.Date sqlDate =
                    new java.sql.Date(utilDate.getTime());
            if (dao.recordExists(recordName, sqlDate)) {
                return "Record Already Exists";
            }
            String recordId =
                    dao.generateRecordID(recordName, sqlDate);

            DataRecordBean bean = new DataRecordBean();
            bean.setRecordId(recordId);
            bean.setRecordName(recordName);
            bean.setCategory(category);
            bean.setCreatedDate(sqlDate);
            bean.setDescription(description);
            bean.setRemarks(remarks);

            return dao.createRecord(bean);

        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }
    public DataRecordBean getRecord(String recordName,
                                    String createdDate) {

        try {

            java.util.Date utilDate =
                    new SimpleDateFormat("yyyy-MM-dd").parse(createdDate);

            java.sql.Date sqlDate =
                    new java.sql.Date(utilDate.getTime());

            return dao.fetchRecord(recordName, sqlDate);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<DataRecordBean> getAllRecords() {
        return dao.fetchAllRecords();
    }
}
