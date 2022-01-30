package com.waterkemper.servlet;

import com.waterkemper.model.ScheduleEmail;
import com.waterkemper.service.ScheduleEmailService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("emails-servlet")
public class ScheduleEmailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private ScheduleEmailService scheduleEmailService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        scheduleEmailService.list().forEach(email -> pw.print("Os emails são: " + email.getEmail()));
    }

    @Override
    //email, subject, message
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String[] email = reader.readLine().split(",");
        ScheduleEmail scheduleEmail = new ScheduleEmail();
        scheduleEmail.setEmail(email[0]);
        scheduleEmail.setSubject(email[1]);
        scheduleEmail.setMessage(email[2]);
        scheduleEmailService.save(scheduleEmail);
    }
}
