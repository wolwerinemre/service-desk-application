package com.simple.serviceDeskApplication.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class TicketDTO implements Serializable {
    private static final long serialVersionUID = -4468144567536715231L;

    private String ticketNumber;
    private String name;
    private String email;
    private String description;
    @JsonSerialize
    private ProcessStatus status;
    private Integer priority;
    private Date createDate;
    private Date updateDate;
}
