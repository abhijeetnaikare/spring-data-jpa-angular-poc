package com.cailam.springdatajpaangularpoc.service;


import com.cailam.springdatajpaangularpoc.model.Cloth;
import com.cailam.springdatajpaangularpoc.repositories.ClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("clothService")
@Transactional
public class ClothServiceImpl implements  ClothService{

    @Autowired
    private ClothRepository clothRepository;


    @Override
    public Cloth findById(Long id) {
        return clothRepository.findOne(id);
    }

    @Override
    public Cloth findByName(String name) {
        return clothRepository.findByName(name);
    }

    @Override
    public Cloth saveCloth(Cloth cloth) {
        return  clothRepository.save(cloth);
    }

    @Override
    public void updateCloth(Cloth cloth) {
        saveCloth(cloth);
    }

    @Override
    public void deleteClothById(Long id) {
        clothRepository.delete(id);
    }

    @Override
    public void deleteAllClothes() {
        clothRepository.deleteAll();
    }

    @Override
    public List<Cloth> findAllClothes() {
        return clothRepository.findAll();
    }

    @Override
    public boolean isClothExist(Cloth cloth) {
        return findByName(cloth.getName()) != null;
    }
}
