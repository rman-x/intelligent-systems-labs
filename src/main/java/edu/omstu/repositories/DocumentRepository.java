package edu.omstu.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;

import edu.omstu.entities.Document;
import edu.omstu.entities.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DocumentRepository {
    private EntityManager entityManager;
    private TagRepository tagRepository;

    public DocumentRepository(EntityManager entityManager, TagRepository tagRepository) {
        this.entityManager = entityManager;
        this.tagRepository = tagRepository;
    }

    private long getBitSetByTags(List<String> tagNames) {
        long bitset = 0;
        for (String tagName : tagNames) {
            Optional<Tag> tag = tagRepository.findByName(tagName);
            if (!tag.isEmpty()) {
                int i = tag.get().getId();
                bitset |= 1 << i;
            }
        }
        return bitset;
    }

    public void save(Document doc, List<String> tagNames) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            long bitset = getBitSetByTags(tagNames);
            doc.setTags(bitset);
            entityManager.persist(doc);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Document> findByTags(List<String> tagNames, int page, int size) {
        long bitset = getBitSetByTags(tagNames);
        List<Document> taggedDocs = entityManager
                .createNativeQuery("SELECT * FROM Document WHERE (document.tags & :tags) != 0", Document.class)
                .setParameter("tags", bitset).getResultList();
        return taggedDocs.subList(page, size);
    }


    public List<Document> findByNotTags(List<String> tagNames, int page, int size) {
        long bitset = getBitSetByTags(tagNames);
        List<Document> taggedDocs = entityManager
                .createNativeQuery("SELECT * FROM Document WHERE (document.tags & :tags) = 0", Document.class)
                .setParameter("tags", bitset).getResultList();
        return taggedDocs.subList(page, size);
    }

    public Optional<Document> findById(int docId) {
        Document tag = entityManager.find(Document.class, docId);
        return tag != null ? Optional.of(tag) : Optional.empty();
    }
}
