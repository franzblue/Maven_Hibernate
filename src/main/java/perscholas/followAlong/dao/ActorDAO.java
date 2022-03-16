package perscholas.followAlong.dao;

import perscholas.followAlong.entity.Actor;

import javax.persistence.*;
import java.util.List;

public class ActorDAO {

    private static final String PERSISTENCE_UNIT_NAME = "moviesdb";

    private  EntityManagerFactory emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);


    public Actor findById(Integer id) {
        EntityManager em = emFactoryObj.createEntityManager();

        // select * from actors where id = 1

        String sql = "SELECT a FROM Actor a WHERE a.id = :actorId";
        TypedQuery<Actor> query = em.createQuery(sql, Actor.class);
        query.setParameter("actorId", id);

        Actor result = query.getSingleResult();

        return result;
    }

    public List<Actor> findByFirstName(String firstName) {
        EntityManager em = emFactoryObj.createEntityManager();

        // select * from actors where first_name = "Franz";

        String sql = "SELECT a FROM Actor a WHERE a.firstName = :firstName";
        TypedQuery<Actor> query = em.createQuery(sql, Actor.class);
        query.setParameter("firstName", firstName);

        List<Actor> result = query.getResultList();

        return result;
    }

    public Actor save(Actor actor) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        // save the actor to the database
        em.persist(actor);

        // commit the transaction
        em.getTransaction().commit();
        em.clear();

        return actor;
    }

    public void delete(Actor actor) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        // delete the actor to the database
        em.remove(em.contains(actor) ? actor : em.merge(actor));

        // commit the transaction
        em.getTransaction().commit();
        em.clear();
    }

    public void deleteById(Integer id) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        // delete from actors where id = 1

        String sql = "DELETE FROM Actor a WHERE a.id = :actorId";
        Query query = em.createQuery(sql);
        query.setParameter("actorId", id);

        query.executeUpdate();
        em.getTransaction().commit();
    }

    public Actor update(Actor actor) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        // update the actor to the database
        em.merge(actor);

        // commit the transaction
        em.getTransaction().commit();
        em.clear();

        return actor;
    }

    public List<Actor> findByFirstNameAndLastName(String firstName, String lastName) {
        EntityManager em = emFactoryObj.createEntityManager();

        // select * from actors where first_name = "Franz";

        String sql = "SELECT a FROM Actor a WHERE a.firstName = :firstName AND a.lastName = :lastName";
        TypedQuery<Actor> query = em.createQuery(sql, Actor.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);

        List<Actor> result = query.getResultList();

        return result;
    }

}
