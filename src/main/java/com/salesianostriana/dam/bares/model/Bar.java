package com.salesianostriana.dam.bares.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String direccion;

    private double latitud;

    private double longitud;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToMany
    @JoinTable(name = "bar_tag",
    joinColumns = @JoinColumn(name = "bar_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ToString.Exclude
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties("bares")
    private List<Tag> tags = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String photoUrl;


    public void addTag(Tag tag) {

        this.tags.add(tag);

        tag.getBares().add(this);
    }

    public void removeTag(Tag tag) {

        tag.getBares().remove(this);

        this.tags.remove(tag);
    }



}
