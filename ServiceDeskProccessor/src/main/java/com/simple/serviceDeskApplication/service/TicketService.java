package com.simple.serviceDeskApplication.service;

import com.simple.serviceDeskApplication.model.ProcessStatus;
import com.simple.serviceDeskApplication.model.TicketBO;
import com.simple.serviceDeskApplication.repository.TicketRepository;
import com.simple.serviceDeskApplication.util.exception.BadRequestException;
import com.simple.serviceDeskApplication.util.exception.EntityNotFoundException;
import com.simple.serviceDeskApplication.util.exception.PersistencyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.StringUtils.isEmpty;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<TicketBO> getAll() {
        return ticketRepository.findAllByActiveIsTrue();
    }

    public TicketBO findByTicketNumber(String uuid) {
        return ticketRepository.findOne(Example.of(new TicketBO().setUuid(uuid))).orElseThrow(EntityNotFoundException::new);
    }

    public TicketBO save(TicketBO bo) {
        if(ObjectUtils.isEmpty(bo)) {
            throw new BadRequestException("Object is empty");
        }
        return saveEntity(
                bo.setUuid(UUID.randomUUID().toString())
                .setStatus(ProcessStatus.OPEN));
    }

    public TicketBO update(TicketBO bo) {
        if(isEmpty(bo.getUuid())) {
            throw new BadRequestException("Ticket Number is empty");
        }
        bo.setActive(isActive(bo.getStatus()));
        TicketBO dbTicket = findByTicketNumber(bo.getUuid());
        BeanUtils.copyProperties(bo,dbTicket,"id","createDate","updateDate");
        return saveEntity(dbTicket);
    }

    public Boolean delete(String ticketNumber) {
        if(isEmpty(ticketNumber)) {
            throw new BadRequestException("Ticket Number is empty");
        }
        TicketBO ticketBO = findByTicketNumber(ticketNumber);
        ticketRepository.delete(ticketBO);
        return !ticketRepository.existsById(ticketBO.getId());
    }

    private TicketBO saveEntity(TicketBO entity) {
        if(ObjectUtils.isEmpty(entity)){
            throw new PersistencyException("Entity object is empty");
        }
        return ticketRepository.saveAndFlush(entity);
    }

    private boolean isActive(ProcessStatus status) {
        return !ProcessStatus.CLOSE.equals(status);
    }
}
