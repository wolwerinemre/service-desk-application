package com.simple.serviceDeskApplication.service;

import com.simple.serviceDeskApplication.config.JpaConfig;
import com.simple.serviceDeskApplication.config.MainConfig;
import com.simple.serviceDeskApplication.model.TicketBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.simple.serviceDeskApplication.util.TestUtil.prepareTicketBO;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = {
        JpaConfig.class,
        MainConfig.class})
@ComponentScan(basePackages = "com.simple.serviceDeskApplication")
@ActiveProfiles("test")
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Test
    public void getAllTicketBO_ShouldBeList() {
        // given
        List<TicketBO> ticketBOList = ticketService.getAll();
        // then
        assertThat(ticketBOList.isEmpty()).as("Ticket list is empty").isFalse();
    }

    @Test
    public void addTicketBO_ShouldBeCreated() {

        TicketBO ticketBOSave = prepareTicketBO();
        ticketBOSave = ticketService.save(ticketBOSave);
        TicketBO ticketBOFound = ticketService.findByTicketNumber(ticketBOSave.getUuid());

        assertThat(ticketBOSave.getName()).isEqualTo(ticketBOFound.getName());
        assertThat(ticketBOFound.getId()).isEqualTo(ticketBOSave.getId());
    }

    @Test
    public void updateTicketBO_ShouldBeCreated() {

        TicketBO ticketBOSave = prepareTicketBO();
        ticketService.save(ticketBOSave);
        TicketBO ticketBOFound = ticketService.findByTicketNumber(ticketBOSave.getUuid());
        ticketBOFound.setDescription("Updated");
        ticketBOFound = ticketService.save(ticketBOFound);
        assertThat(ticketBOFound.getUpdateDate()).isNotNull();
    }

    @Test
    public void deleteFrtinvBO_ShouldBeDeleted() {

        TicketBO ticketBO = prepareTicketBO();
        ticketBO = ticketService.save(ticketBO);

        TicketBO ticketBOFound = ticketService.findByTicketNumber(ticketBO.getUuid());
        assertThat(ticketBOFound.getId()).isEqualTo(ticketBO.getId());

        assertThat(ticketService.delete(ticketBO.getUuid())).isTrue();
    }


}
