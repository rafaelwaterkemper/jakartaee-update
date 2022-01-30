package com.waterkemper.controller;

import com.waterkemper.model.ScheduleEmail;
import com.waterkemper.service.ScheduleEmailService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("emails")
public class SchedulerEmailController {

    @Inject
    private ScheduleEmailService scheduleEmailService;

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(scheduleEmailService.list()).build();
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response save(ScheduleEmail scheduleEmail) {
        scheduleEmailService.save(scheduleEmail);
        return Response.status(201).build();
    }
}
