package com.softuni.usersystem.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "countries")
public class Country extends BaseEntity {
    @Basic
    private String name;

    @OneToMany(targetEntity = Town.class, mappedBy = "country")
    private List<Town> towns;
}
