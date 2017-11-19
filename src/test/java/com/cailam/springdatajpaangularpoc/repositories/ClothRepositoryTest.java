package com.cailam.springdatajpaangularpoc.repositories;

import com.cailam.springdatajpaangularpoc.model.Cloth;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Ignore
public class ClothRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private  ClothRepository clothRepository;

    @Test
    public void whenFindByName_thenReturnCloth() {
        // given
        Cloth tshirt = new Cloth("nike","cotton",10.0);
        entityManager.persist(tshirt);
        entityManager.flush();

        // when
        Cloth found = clothRepository.findByName(tshirt.getName());

        // then
        Assert.assertEquals(found.getName(),tshirt.getName());
    }
}
