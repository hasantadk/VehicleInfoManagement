package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Kasa;
import com.example.sahibinden.model.entity.KasaEntity;
import com.example.sahibinden.model.entity.ModelEntity;
import com.example.sahibinden.repository.KasaRepository;
import com.example.sahibinden.repository.MarkaRepository;
import com.example.sahibinden.repository.ModelRepository;
import com.example.sahibinden.service.KasaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KasaServiceImpl implements KasaService {
    private final KasaRepository kasaRepository;
    private final MarkaRepository markaRepository;
    private final ModelRepository modelRepository;


    public Kasa getKasaById(Long id) {
        KasaEntity kasaEntity = kasaRepository.findById(id).orElseThrow();
        return Kasa.fromEntity(kasaEntity);
    }

    public Kasa getKasaByShortName(String shortName) {
        KasaEntity kasaEntity = kasaRepository.findKasaEntityByShortName(shortName).orElseThrow();
        return Kasa.fromEntity(kasaEntity);
    }

    public List<Kasa> getAllKasa() {
        List<KasaEntity> kasaEntities = kasaRepository.findAll();
        return kasaEntities.stream()
                .map(Kasa::fromEntity)
                .collect(Collectors.toList());
    }

    public Kasa addKasa(Kasa kasa) {

        ModelEntity model = modelRepository.findById(kasa.getModel().getId()).get();
        KasaEntity kasaEntity = KasaEntity.fromModel(kasa);
        kasaEntity.setModel(model);
        KasaEntity addedKasaEntity = kasaRepository.save(kasaEntity);
        return Kasa.fromEntity(addedKasaEntity);

    }


    public List<Kasa> addKasas(List<Kasa> kasaList) {
        List<KasaEntity> modelEntityList = kasaList.stream().map(KasaEntity::fromModel).toList();
        List<KasaEntity> addedModelEntityList = kasaRepository.saveAll(modelEntityList);
        return addedModelEntityList.stream().map(Kasa::fromEntity).toList();
    }


    public Kasa updateKasa(Kasa kasa) {
        if (kasaRepository.existsById(kasa.getId())) {
            KasaEntity updatedKasaEntity = kasaRepository.save(KasaEntity.fromModel(kasa));
            return Kasa.fromEntity(updatedKasaEntity);
        }
        return null;
    }

    public boolean deleteKasaById(Long id) {
        kasaRepository.deleteById(id);
        return !kasaRepository.existsById(id);
    }
}
