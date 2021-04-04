package litecoin_parser.service;

import litecoin_parser.database.Block;
import litecoin_parser.database.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseService {

    private final EntityManager entityManager;

    public DatabaseService() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("litecoin_parser");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void saveBlock(Block block) {
        entityManager.getTransaction().begin();
        entityManager.persist(block);
        entityManager.getTransaction().commit();
    }

    public void saveTransaction(Transaction transaction) {
        entityManager.getTransaction().begin();
        entityManager.persist(transaction);
        entityManager.getTransaction().commit();
    }
}
