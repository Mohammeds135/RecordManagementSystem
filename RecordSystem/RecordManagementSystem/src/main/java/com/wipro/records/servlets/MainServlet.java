package com.wipro.records.servlets;

import java.io.IOException;


import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.wipro.records.bean.DataRecordBean;
import com.wipro.records.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        Administrator admin = new Administrator();

        try {
            if ("add".equals(action)) {

                String result = admin.addRecord(
                        request.getParameter("recordName"),
                        request.getParameter("category"),
                        request.getParameter("createdDate"),
                        request.getParameter("description"),
                        request.getParameter("remarks")
                );

                if (!"FAIL".equals(result)
                        && !"Record Already Exists".equals(result)) {

                    request.setAttribute("recordId", result);
                    RequestDispatcher rd =
                            request.getRequestDispatcher("success.jsp");
                    rd.forward(request, response);

                } else {

                    request.setAttribute("errorMsg", result);
                    RequestDispatcher rd =
                            request.getRequestDispatcher("error.jsp");
                    rd.forward(request, response);
                }
            }
            else if ("view".equals(action)) {

                DataRecordBean bean = admin.getRecord(
                        request.getParameter("recordName"),
                        request.getParameter("createdDate")
                );

                if (bean != null) {

                    request.setAttribute("record", bean);
                    RequestDispatcher rd =
                            request.getRequestDispatcher("displayRecord.jsp");
                    rd.forward(request, response);

                } else {

                    request.setAttribute("errorMsg", "Record Not Found");
                    RequestDispatcher rd =
                            request.getRequestDispatcher("error.jsp");
                    rd.forward(request, response);
                }
            }
            else if ("viewAll".equals(action)) {

                List<DataRecordBean> list = admin.getAllRecords();

                request.setAttribute("records", list);
                RequestDispatcher rd =
                        request.getRequestDispatcher("displayAllRecords.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }
}
