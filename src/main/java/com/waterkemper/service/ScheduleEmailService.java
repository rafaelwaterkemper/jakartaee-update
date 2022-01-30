package com.waterkemper.service;

import com.waterkemper.model.ScheduleEmail;
import com.waterkemper.repository.ScheduleEmailDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ScheduleEmailService {

    private static final Logger LOGGER = Logger.getLogger("ScheduleEmailService");

    @Inject
    private ScheduleEmailDAO scheduleEmailDAO;

    public List<ScheduleEmail> list() {
        return scheduleEmailDAO.list();
    }

    public List<ScheduleEmail> getNotScheduled() { return scheduleEmailDAO.getNotScheduled(); }

    public void save(ScheduleEmail scheduleEmail) {
        scheduleEmail.setScheduled(false);
        scheduleEmailDAO.insert(scheduleEmail);
    }

    public void updateStatus(ScheduleEmail scheduleEmail) {
        scheduleEmail.setScheduled(true);
        scheduleEmailDAO.update(scheduleEmail);
    }

    public void sendMail(ScheduleEmail scheduleEmail) {
        try {
            Thread.sleep(5000);
            LOGGER.info("The email was sent to user: " + scheduleEmail.getEmail());
        } catch (InterruptedException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
