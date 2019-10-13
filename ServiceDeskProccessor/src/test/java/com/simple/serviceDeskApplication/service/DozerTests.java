package com.simple.serviceDeskApplication.service;

import com.github.dozermapper.core.Mapper;
import com.simple.serviceDeskApplication.model.TicketBO;
import com.simple.serviceDeskApplication.model.TicketDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static com.simple.serviceDeskApplication.util.TestUtil.prepareTicketBO;
import static com.simple.serviceDeskApplication.util.TestUtil.prepareTicketDTO;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.simple.serviceDeskApplication")
@EntityScan(basePackages = "com.simple.serviceDeskApplication")
@SpringBootTest
@ActiveProfiles("test")
public class DozerTests {

    @Autowired
    private Mapper mapper;

    @Test
    public void convertTicketBO_withDozer() {
        TicketBO ticketBO = prepareTicketBO().setUuid(UUID.randomUUID().toString());
        TicketDTO ticketDTO = mapper.map(ticketBO, TicketDTO.class);
        assertThat(ticketBO.getName()).isEqualTo(ticketDTO.getName());
        assertThat(ticketBO.getDescription()).isEqualTo(ticketDTO.getDescription());
        assertThat(ticketBO.getUuid()).isEqualTo(ticketDTO.getTicketNumber());
        assertThat(ticketBO.getStatus()).isEqualTo(ticketDTO.getStatus());
        assertThat(ticketBO.getEmail()).isEqualTo(ticketDTO.getEmail());
        assertThat(ticketBO.getPriority()).isEqualTo(ticketDTO.getPriority());
    }


    @Test
    public void convertTicketDTO_withDozer() {
        TicketDTO ticketDTO = prepareTicketDTO();
        TicketBO ticketBO = mapper.map(ticketDTO,TicketBO.class);
        assertThat(ticketBO.getName()).isEqualTo(ticketDTO.getName());
        assertThat(ticketBO.getDescription()).isEqualTo(ticketDTO.getDescription());
        assertThat(ticketBO.getUuid()).isEqualTo(ticketDTO.getTicketNumber());
        assertThat(ticketBO.getStatus()).isEqualTo(ticketDTO.getStatus());
        assertThat(ticketBO.getEmail()).isEqualTo(ticketDTO.getEmail());
        assertThat(ticketBO.getPriority()).isEqualTo(ticketDTO.getPriority());
    }
}
