package com.cailam.springdatajpaangularpoc.service;

import com.cailam.springdatajpaangularpoc.model.Cloth;

import java.util.List;

public interface ClothService {

    Cloth findById(Long id);

    Cloth findByName(String name);

    void saveCloth(Cloth cloth);

    void updateCloth(Cloth cloth);

    void deleteClothById(Long id);

    void deleteAllClothes();

    List<Cloth> findAllClothes();

    boolean isClothExist(Cloth cloth);
}
