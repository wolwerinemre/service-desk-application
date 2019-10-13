package com.simple.serviceDeskApplication.util;

import com.simple.serviceDeskApplication.model.ProcessStatus;
import com.simple.serviceDeskApplication.model.TicketBO;
import com.simple.serviceDeskApplication.model.TicketDTO;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@UtilityClass
public class TestUtil {

    private static final String EMAIL = "test@test.com";
    private static final String NAME = "Test_Ticket_" + new Date().getTime();

    public static TicketBO prepareTicketBO() {
        return new TicketBO()
                .setName(NAME)
                .setStatus(ProcessStatus.OPEN)
                .setDescription("Test BO Description")
                .setEmail(EMAIL)
                .setPriority(1)
                .setCreateDate(Date.from(Instant.now()))
                .setActive(true);
    }

    public static TicketDTO prepareTicketDTO() {
        return new TicketDTO()
                .setName(NAME)
                .setTicketNumber(UUID.randomUUID().toString())
                .setStatus(ProcessStatus.OPEN)
                .setDescription("Test DTO Description")
                .setEmail(EMAIL)
                .setPriority(1)
                .setCreateDate(Date.from(Instant.now()));
    }

}
