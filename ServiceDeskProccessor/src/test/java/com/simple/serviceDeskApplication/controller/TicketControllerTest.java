package com.simple.serviceDeskApplication.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dozermapper.core.Mapper;
import com.simple.serviceDeskApplication.controller.ticket.TicketController;
import com.simple.serviceDeskApplication.model.TicketBO;
import com.simple.serviceDeskApplication.model.TicketDTO;
import com.simple.serviceDeskApplication.service.TicketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.simple.serviceDeskApplication.util.TestUtil.prepareTicketBO;
import static com.simple.serviceDeskApplication.util.TestUtil.prepareTicketDTO;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TicketController.class)
@ActiveProfiles("test")
public class TicketControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Mapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TicketService ticketService;

    @Before
    public void init() {
        TicketDTO ticketDTO = prepareTicketDTO();
        TicketBO ticketBO = mapper.map(ticketDTO,TicketBO.class);
        List<TicketBO> ticketBOList = asList(ticketBO);
        when(ticketService.getAll()).thenReturn(ticketBOList);
        when(ticketService.save(any(TicketBO.class))).thenReturn(ticketBO);
        when(ticketService.update(any(TicketBO.class))).thenReturn(ticketBO);
        when(ticketService.findByTicketNumber(any())).thenReturn(ticketBO);
        when(ticketService.delete(any())).thenReturn(true);
    }

    @Test
    public void givenTickets_whenGetAllTickets_thenReturnJsonArray() throws Exception {
        mvc.perform(get("/api/ticket")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").isNotEmpty());
    }

    @Test
    public void givenTicket_whenGetOneTicket_thenReturnJsonArray() throws Exception {
        TicketDTO ticketDTO = prepareTicketDTO();
        mvc.perform(get("/api/ticket/" + ticketDTO.getTicketNumber())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").isNotEmpty());
    }

    @Test
    public void givenTicket_whenSaveTicket_thenReturnJsonArray() throws Exception {

        TicketBO ticketBO = prepareTicketBO();
        TicketDTO ticketDTO = mapper.map(ticketBO,TicketDTO.class);

        mvc.perform(post("/api/ticket")
                .content(objectMapper.writeValueAsString(ticketDTO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").isNotEmpty());
    }

    @Test
    public void givenTicket_whenUpdateTicket_thenReturnJsonArray() throws Exception {

        TicketDTO ticketDTO = prepareTicketDTO();

        mvc.perform(put("/api/ticket")
                .content(objectMapper.writeValueAsString(ticketDTO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").isNotEmpty());
    }

    @Test
    public void givenTicket_whenDeleteTicket_thenReturnJsonArray() throws Exception {

        TicketDTO ticketDTO = prepareTicketDTO();

        mvc.perform(delete("/api/ticket")
                .content(objectMapper.writeValueAsString(ticketDTO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isBoolean());
    }
}

