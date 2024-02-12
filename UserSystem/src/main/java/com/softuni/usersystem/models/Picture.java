package com.softuni.usersystem.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {
    @Basic
    private String title;

    @Basic
    private String caption;

    @Basic
    private String path;

    @ManyToMany(mappedBy = "pictures")
    private List<Album> albums;
}
