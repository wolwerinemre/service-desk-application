package com.simple.serviceDeskApplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "ticket")
@NoArgsConstructor
public class TicketBO implements Serializable {
    private static final long serialVersionUID = -4468144567896715235L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE , generator = "ticket_id_seq")
    @SequenceGenerator(name="ticket_id_seq", sequenceName = "ticket_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "ticket_number")
    private String uuid;
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 2000)
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    @Convert(converter = ProcessStatus.EnumConverter.class)
    private ProcessStatus status;
    @Column(name = "priority")
    private Integer priority;
    @Column(name = "active")
    private boolean active = true;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

}


