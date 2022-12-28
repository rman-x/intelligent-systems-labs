package edu.omstu;

import java.util.List;

import edu.omstu.entities.Document;
import edu.omstu.entities.Tag;
import edu.omstu.repositories.DocumentRepository;
import edu.omstu.repositories.TagRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TagRepository tagRepository = new TagRepository(entityManager);
        DocumentRepository documentRepository = new DocumentRepository(entityManager, tagRepository);
        for (int i = 0; i < 10; i++) {
            Tag tag = new Tag();
            tag.setName(String.format("tag00%d", i));
            tagRepository.save(tag);
        }

        Document doc1 = new Document();
        doc1.setFilepath("/home/user/doc1.docx");
        documentRepository.save(doc1, List.of("tag001", "tag003", "tag007"));
        Document doc2 = new Document();
        doc2.setFilepath("/home/user/doc2.docx");
        documentRepository.save(doc2, List.of("tag001", "tag002", "tag005"));
        Document doc3 = new Document();
        doc3.setFilepath("/home/user/doc3.docx");
        documentRepository.save(doc3, List.of("tag004", "tag003", "tag007"));
        Document doc4 = new Document();
        doc4.setFilepath("/home/user/doc4.docx");
        documentRepository.save(doc4, List.of("tag005", "tag008", "tag009"));


        System.out.println("Documents that are tagged by tag001:");
        for (Document doc: documentRepository.findByTags(List.of("tag001"))) {
            System.out.println(doc);
        }
        System.out.println("Documents that are tagged by tag003 and tag007:");
        for (Document doc: documentRepository.findByTags(List.of("tag003", "tag007"))) {
            System.out.println(doc);

        }
        System.out.println("Documents that aren't tagged by tag007:");
        for (Document doc: documentRepository.findByNotTags(List.of("tag007"))) {
            System.out.println(doc);

        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
