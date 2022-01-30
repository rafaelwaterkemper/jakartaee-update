package com.waterkemper.repository;

import com.waterkemper.model.ScheduleEmail;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ScheduleEmailDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ScheduleEmail> list() {
        return entityManager.createQuery("Select se from ScheduleEmail se", ScheduleEmail.class).getResultList();
    }

    public void insert(ScheduleEmail scheduleEmail) {
        entityManager.persist(scheduleEmail);
    }

    public List<ScheduleEmail> getNotScheduled() {
        return entityManager
                .createQuery("""
                        SELECT se
                          FROM ScheduleEmail se 
                         WHERE se.scheduled = false
                        """, ScheduleEmail.class).getResultList();
    }

    public void update(ScheduleEmail scheduleEmail) {
        entityManager.merge(scheduleEmail);
    }
}
