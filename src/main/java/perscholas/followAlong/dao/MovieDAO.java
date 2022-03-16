package perscholas.followAlong.dao;

import perscholas.followAlong.entity.Actor;
import perscholas.followAlong.entity.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovieDAO {

    private static final String PERSISTENCE_UNIT_NAME = "moviesdb";

    private EntityManagerFactory emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);


    public Movie findById(Integer id) {
        EntityManager em = emFactoryObj.createEntityManager();

        // select * from movies where id = 1

        String sql = "SELECT m FROM Movie m WHERE m.id = :movieId";
        TypedQuery<Movie> query = em.createQuery(sql, Movie.class);
        query.setParameter("movieId", id);

        Movie result = query.getSingleResult();

        return result;
    }

    public List<Movie> findTitle(String title) {
        EntityManager em = emFactoryObj.createEntityManager();

        // select * from movies where title = "Star Wars";

        String sql = "SELECT m FROM Movie m WHERE m.title = :title";
        TypedQuery<Movie> query = em.createQuery(sql, Movie.class);
        query.setParameter("title", title);

        List<Movie> result = query.getResultList();

        return result;
    }

    public Movie save(Movie movie) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        // save the movie to the database
        em.persist(movie);

        // commit the transaction
        em.getTransaction().commit();
        em.clear();

        return movie;
    }

    public Movie update(Movie movie) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        // update the movie to the database
        em.merge(movie);

        // commit the transaction
        em.getTransaction().commit();
        em.clear();

        return movie;
    }

}