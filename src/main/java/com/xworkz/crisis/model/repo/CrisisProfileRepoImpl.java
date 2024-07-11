package com.xworkz.crisis.model.repo;

import com.xworkz.crisis.dto.CrisisProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

@Repository
public class CrisisProfileRepoImpl implements CrisisProfileRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean save(CrisisProfileDto crisisProfileDto) {
        if (crisisProfileDto == null) {
            System.out.println("Error: CrisisProfileDto is null");
            return false;
        }

        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            entityManager.persist(crisisProfileDto);
            tx.commit();
            System.out.println("Profile stored successfully");
            return true;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }
}
