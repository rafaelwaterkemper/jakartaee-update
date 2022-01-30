package com.waterkemper.scheduler;

import com.waterkemper.model.ScheduleEmail;
import com.waterkemper.service.ScheduleEmailService;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.List;

@Singleton
public class SchedulerEmailJob {

    @Inject
    private ScheduleEmailService scheduleEmailService;

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jms/Queue/EmailQueue")
    private Queue queue;

    @Schedule(hour = "*", minute = "*", second = "*/10")
    public void send() {
        List<ScheduleEmail> notScheduled = scheduleEmailService.getNotScheduled();
        notScheduled.forEach(toSchedule -> {
            context.createProducer().send(queue, toSchedule);
            scheduleEmailService.updateStatus(toSchedule);
        });
    }
}
