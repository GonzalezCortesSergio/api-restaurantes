package com.salesianostriana.dam.bares.service;

import com.salesianostriana.dam.bares.model.Tag;
import com.salesianostriana.dam.bares.repository.TagRepository;
import com.salesianostriana.dam.bares.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TagService extends BaseServiceImpl<Tag, Long, TagRepository> {

    public Tag edit (Long id, Tag tag) {

        Optional<Tag> optionalAntiguo = repository.findById(id);

        if(optionalAntiguo.isEmpty())
            return null;

        Tag antiguo = optionalAntiguo.get();

        antiguo.setNombre(tag.getNombre());

        return repository.save(antiguo);
    }
}
