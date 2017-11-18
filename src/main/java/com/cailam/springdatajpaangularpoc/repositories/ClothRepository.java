package com.cailam.springdatajpaangularpoc.repositories;

import com.cailam.springdatajpaangularpoc.model.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {

    Cloth findByName(String name);

}