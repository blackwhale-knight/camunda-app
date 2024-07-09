package com.mangoket.camunda.storage.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "process")
public class Process {
    @Id
    private Long id;
    private String name;
    private String sourceService;
    private String requester;
    private String status;
    private Date startDate;
    private Date endDate;
}
