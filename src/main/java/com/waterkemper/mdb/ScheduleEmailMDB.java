package com.waterkemper.mdb;

import com.waterkemper.model.ScheduleEmail;
import com.waterkemper.service.ScheduleEmailService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/Queue/EmailQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ScheduleEmailMDB implements MessageListener {

    @Inject
    private ScheduleEmailService scheduleEmailService;

    @Override
    public void onMessage(Message message) {
        try {
            ScheduleEmail scheduleEmail = message.getBody(ScheduleEmail.class);
            scheduleEmailService.sendMail(scheduleEmail);
        } catch (JMSException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
