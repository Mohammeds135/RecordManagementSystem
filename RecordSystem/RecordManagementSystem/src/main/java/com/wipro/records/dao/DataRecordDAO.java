package com.wipro.records.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import com.wipro.records.bean.DataRecordBean;
import com.wipro.records.util.DBUtil;

public class DataRecordDAO {

    public String createRecord(DataRecordBean bean) {

        String status = "FAIL";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO DATA_RECORD_TB "
              + "(RECORDID, RECORDNAME, CATEGORY, CREATED_DATE, DESCRIPTION, REMARKS) "
              + "VALUES (?,?,?,?,?,?)")) {

            ps.setString(1, bean.getRecordId());
            ps.setString(2, bean.getRecordName());
            ps.setString(3, bean.getCategory());

            Date date = (bean.getCreatedDate() != null)
                    ? new Date(bean.getCreatedDate().getTime())
                    : new Date(System.currentTimeMillis());

            ps.setDate(4, date);
            ps.setString(5, bean.getDescription());
            ps.setString(6, bean.getRemarks());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                status = bean.getRecordId();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public boolean recordExists(String recordName, Date createdDate) {

        boolean exists = false;

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT RECORDID FROM DATA_RECORD_TB WHERE RECORDNAME=? AND CREATED_DATE=?")) {

            ps.setString(1, recordName);
            ps.setDate(2, new Date(createdDate.getTime()));

            try (ResultSet rs = ps.executeQuery()) {
                exists = rs.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }

    public String generateRecordID(String recordName, Date createdDate) {

        String id = "";

        try (Connection con = DBUtil.getDBConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT DATA_RECORD_SEQ.NEXTVAL FROM dual")) {

            int seq = rs.next() ? rs.getInt(1) : 0;

            String datePart = new SimpleDateFormat("yyyyMMdd").format(createdDate);

            String namePart = (recordName.length() >= 2)
                    ? recordName.substring(0, 2).toUpperCase()
                    : (recordName.length() == 1
                        ? recordName.toUpperCase() + "X"
                        : "XX");

            id = datePart + namePart + seq;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public DataRecordBean fetchRecord(String recordName, Date createdDate) {

        DataRecordBean bean = null;

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM DATA_RECORD_TB WHERE RECORDNAME=? AND CREATED_DATE=?")) {

            ps.setString(1, recordName);
            ps.setDate(2, new Date(createdDate.getTime()));

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    bean = new DataRecordBean();
                    bean.setRecordId(rs.getString("RECORDID"));
                    bean.setRecordName(rs.getString("RECORDNAME"));
                    bean.setCategory(rs.getString("CATEGORY"));
                    bean.setCreatedDate(rs.getDate("CREATED_DATE"));
                    bean.setDescription(rs.getString("DESCRIPTION"));
                    bean.setRemarks(rs.getString("REMARKS"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    public List<DataRecordBean> fetchAllRecords() {

        List<DataRecordBean> list = new ArrayList<>();

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM DATA_RECORD_TB ORDER BY CREATED_DATE DESC");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DataRecordBean bean = new DataRecordBean();
                bean.setRecordId(rs.getString("RECORDID"));
                bean.setRecordName(rs.getString("RECORDNAME"));
                bean.setCategory(rs.getString("CATEGORY"));
                bean.setCreatedDate(rs.getDate("CREATED_DATE"));
                bean.setDescription(rs.getString("DESCRIPTION"));
                bean.setRemarks(rs.getString("REMARKS"));
                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
