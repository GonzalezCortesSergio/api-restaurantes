package com.salesianostriana.dam.bares.service;

import com.salesianostriana.dam.bares.model.Bar;
import com.salesianostriana.dam.bares.model.Tag;
import com.salesianostriana.dam.bares.repository.BarRepository;
import com.salesianostriana.dam.bares.repository.TagRepository;
import com.salesianostriana.dam.bares.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BarService extends BaseServiceImpl<Bar, Long, BarRepository> {

    private final TagService tagService;
    //Métodos

    public Bar edit(Long id, Bar b) {

        Optional<Bar> optionalAntiguo = repository.findById(id);

        if(optionalAntiguo.isEmpty())
            return null;

        Bar antiguo = optionalAntiguo.get();

        antiguo.setNombre(b.getNombre());
        antiguo.setDireccion(b.getDireccion());
        antiguo.setLatitud(b.getLatitud());
        antiguo.setDescripcion(b.getDescripcion());
        antiguo.setPhotoUrl(b.getPhotoUrl());

        return repository.save(antiguo);
    }

    public Bar agregarTag(Long id, Tag tag) {

        Optional<Bar> optionalBar = repository.findById(id);

        if(optionalBar.isEmpty())
            return null;

        Bar bar = optionalBar.get();

        bar.addTag(tag);

        tagService.save(tag);

        return repository.save(bar);
    }

    public Bar eliminarTag(Long id, Long tagId) {

        Optional<Bar> optionalBar = repository.findById(id);
        Optional<Tag> optionalTag = tagService.findById(tagId);

        if (optionalBar.isEmpty())
            return null;

        if(optionalTag.isEmpty())
            return null;


        Bar bar = optionalBar.get();
        Tag tag = optionalTag.get();

        bar.removeTag(tag);

        return repository.save(bar);
    }

}
