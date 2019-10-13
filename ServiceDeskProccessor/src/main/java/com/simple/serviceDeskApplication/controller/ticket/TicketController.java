package com.simple.serviceDeskApplication.controller.ticket;

import com.github.dozermapper.core.Mapper;
import com.simple.serviceDeskApplication.controller.TicketApi;
import com.simple.serviceDeskApplication.model.TicketBO;
import com.simple.serviceDeskApplication.model.TicketDTO;
import com.simple.serviceDeskApplication.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class TicketController implements TicketApi {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private Mapper mapper;

    @Override
    public ResponseEntity<List<TicketDTO>> getAllList(){
        return new ResponseEntity<>(
                ticketService.getAll()
                        .stream()
                        .map(bo -> mapper.map(bo,TicketDTO.class))
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketDTO> getByTicketNumber(@PathVariable @Valid @Min(1) @NotEmpty String ticketNumber){
        return new ResponseEntity<>(
                mapper.map(ticketService.findByTicketNumber(ticketNumber),TicketDTO.class),
                HttpStatus.OK);
    }

    @Override
    public @ResponseBody ResponseEntity<TicketDTO> save(@RequestBody @Valid @NotEmpty TicketDTO dto){
        return new ResponseEntity<>(mapper.map(ticketService.save(mapper.map(dto,TicketBO.class)),TicketDTO.class),
                HttpStatus.OK);
    }

    @Override
    public @ResponseBody ResponseEntity<TicketDTO> update(@RequestBody @Valid @NotEmpty TicketDTO dto){
        return new ResponseEntity<>(mapper.map(ticketService.update(mapper.map(dto,TicketBO.class)),TicketDTO.class),
                HttpStatus.OK);
    }

    @Override
    public @ResponseBody ResponseEntity<Boolean> delete(@RequestBody @Valid @NotEmpty TicketDTO dto){
        return new ResponseEntity<>(ticketService.delete(dto.getTicketNumber()),
                HttpStatus.OK);
    }
}
