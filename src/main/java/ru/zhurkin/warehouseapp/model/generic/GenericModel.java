package ru.zhurkin.warehouseapp.model.generic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class GenericModel {

    @Id
    @Column(name = "id",
            unique = true,
            nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(columnDefinition = "varchar(150) default 'Nikita Zhurkin'")
    protected String createdBy = "Nikita Zhurkin";

    @Column(columnDefinition = "timestamp default now()")
    protected LocalDateTime createdWhen = LocalDateTime.now();

    @Column(columnDefinition = "bool default '0'")
    protected Boolean isDeleted = false;

    @Column(columnDefinition = "varchar(150) default null")
    protected String deletedBy = null;

    @Column(columnDefinition = "timestamp default null")
    protected LocalDateTime deletedWhen = null;
}


