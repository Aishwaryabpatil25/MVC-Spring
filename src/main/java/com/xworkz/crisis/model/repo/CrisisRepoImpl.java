package com.xworkz.crisis.model.repo;


import com.xworkz.crisis.dto.CrisisDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class CrisisRepoImpl implements CrisisRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean save(CrisisDto crisisDto) {
        System.out.println("Crisis stored successfully");
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {
            tx.begin();
            entityManager.persist(crisisDto);
            tx.commit();
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            tx.rollback();
        } finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public CrisisDto findByEmailAndPassword(String email, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("select c from CrisisDto c where email=:email and password=:pass");
            query.setParameter("email", email);
            query.setParameter("pass", password);

            CrisisDto crisisDto = (CrisisDto) query.getSingleResult();
            return crisisDto;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public CrisisDto findByEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("select c from CrisisDto c where c.email=:email ");
            query.setParameter("email", email);
            CrisisDto crisisDto = (CrisisDto) query.getSingleResult();
            return crisisDto;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public boolean updateFailedLoginAttempts(CrisisDto crisisDto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("UPDATE CrisisDto c SET c.failedLoginAttempts = :attempts WHERE c.email = :email");
            query.setParameter("attempts", crisisDto.getFailedLoginAttempts());
            query.setParameter("email", crisisDto.getEmail());
            int updatedCount = query.executeUpdate();
            entityManager.getTransaction().commit();
            return updatedCount > 0;
        } catch (PersistenceException persistenceException) {
            entityManager.getTransaction();
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }


    @Override
    public boolean updateAccountLock(CrisisDto crisisDto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("UPDATE CrisisDto c SET c.accountLocked = :locked WHERE c.email = :email");
            query.setParameter("locked", crisisDto.getAccountLocked());
            query.setParameter("email", crisisDto.getEmail());
            int updatedCount = query.executeUpdate();
            entityManager.getTransaction().commit();
            return updatedCount > 0;
        } catch (PersistenceException persistenceException) {
            entityManager.getTransaction().rollback();
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public CrisisDto findByContactNumber(Long contactNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT c FROM CrisisDto c WHERE c.contactNumber = :contactNumber");
            query.setParameter("contactNumber", contactNumber);
            CrisisDto crisisDto = (CrisisDto) query.getSingleResult();
            return crisisDto;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Query query = entityManager.createQuery("UPDATE CrisisDto c SET c.newPassword = :password WHERE c.email = :email");
            query.setParameter("password", newPassword);
            query.setParameter("email", email);
            int updatedCount = query.executeUpdate();
            tx.commit();
            return true;
        } catch (PersistenceException persistenceException) {
            tx.rollback();
            persistenceException.printStackTrace();

        } finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public boolean forgotPassword(CrisisDto crisisDto) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();

            Query query = entityManager.createQuery("UPDATE CrisisDto c SET c.password = :password WHERE c.email = :email");
            query.setParameter("password",crisisDto.getPassword());
            query.setParameter("email",crisisDto.getEmail());

            int updatedCount = query.executeUpdate();
            tx.commit();
            return true;
        } catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }

}


