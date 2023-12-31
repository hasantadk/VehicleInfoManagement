package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Ozellik;
import com.example.sahibinden.model.entity.OzellikEntity;
import com.example.sahibinden.repository.OzellikRepository;
import com.example.sahibinden.service.OzellikService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OzellikServiceImpl implements OzellikService {
    private final OzellikRepository ozellikRepository;

    public Ozellik getOzellikById(Long id) {
        OzellikEntity ozellikEntity = ozellikRepository.findById(id).orElseThrow();
        return Ozellik.fromEntity(ozellikEntity);
    }

    public List<Ozellik> getAllOzellik() {
        List<OzellikEntity> ozellikEntities = ozellikRepository.findAll();
        return ozellikEntities.stream()
                .map(Ozellik::fromEntity)
                .collect(Collectors.toList());
    }

    public Ozellik addOzellik(Ozellik ozellik) {
        OzellikEntity ozellikEntity = OzellikEntity.fromModel(ozellik);
        OzellikEntity addedOzellikEntity = ozellikRepository.save(ozellikEntity);
        return Ozellik.fromEntity(addedOzellikEntity);
    }


    public Ozellik updateOzellik(Ozellik ozellik) {
        if (ozellikRepository.existsById(ozellik.getId())) {
            OzellikEntity updatedOzellikEntity = ozellikRepository.save(OzellikEntity.fromModel(ozellik));
            return Ozellik.fromEntity(updatedOzellikEntity);
        }
        return null;
    }

    public boolean deleteOzellikById(Long id) {
        ozellikRepository.deleteById(id);
        return !ozellikRepository.existsById(id);
    }
}
