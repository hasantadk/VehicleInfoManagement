package com.example.sahibinden.service.impl;

import com.example.sahibinden.model.Motor;
import com.example.sahibinden.model.Paket;
import com.example.sahibinden.model.entity.MotorEntity;
import com.example.sahibinden.model.entity.PaketEntity;
import com.example.sahibinden.repository.PaketRepository;
import com.example.sahibinden.exception.model.CustomException;
import com.example.sahibinden.service.PaketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaketServiceImpl implements PaketService {
    private final PaketRepository paketRepository;

    public Paket getPaketById(Long id) {
        PaketEntity paketEntity = paketRepository.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Girdiğiniz id bulunamadı: " + id));
        return Paket.fromEntity(paketEntity);
    }

    public List<Paket> getAllPaket() {
        List<PaketEntity> paketEntities = paketRepository.findAll();
        return paketEntities.stream()
                .map(Paket::fromEntity)
                .collect(Collectors.toList());
    }

    public Paket addPaket(Paket paket) {
        PaketEntity paketEntity = PaketEntity.fromModel(paket);
        PaketEntity addedPaketEntity = paketRepository.save(paketEntity);
        return Paket.fromEntity(addedPaketEntity);
    }

    public Paket updatePaket(Paket paket) {
        if (paketRepository.existsById(paket.getId())) {
            PaketEntity updatedPaketEntity = paketRepository.save(PaketEntity.fromModel(paket));
            return Paket.fromEntity(updatedPaketEntity);
        }
        return null;
    }

    public boolean deletePaketById(Long id) {
        paketRepository.deleteById(id);
        return !paketRepository.existsById(id);
    }
}
