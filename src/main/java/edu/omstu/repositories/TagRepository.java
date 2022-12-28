package edu.omstu.repositories;

import java.util.Optional;

import org.hibernate.HibernateException;

import edu.omstu.entities.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TagRepository {
    private EntityManager entityManager;

    public TagRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Tag tag) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(tag);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Optional<Tag> findById(int tagId) {
        Tag tag = entityManager.find(Tag.class, tagId);
        return tag != null ? Optional.of(tag) : Optional.empty();
    }

    public Optional<Tag> findByName(String tagName) {
        Tag tag = entityManager.createQuery("SELECT t FROM Tag t WHERE t.name = :name", Tag.class)
        .setParameter("name", tagName).getSingleResult();
        return tag != null ? Optional.of(tag) : Optional.empty();
    }
}
