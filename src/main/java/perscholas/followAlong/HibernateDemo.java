package perscholas.followAlong;

import perscholas.followAlong.dao.ActorDAO;
import perscholas.followAlong.dao.MovieDAO;
import perscholas.followAlong.entity.Actor;
import perscholas.followAlong.entity.Movie;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class HibernateDemo {

    private ActorDAO actorDAO = new ActorDAO();
    private MovieDAO movieDAO = new MovieDAO();

    public void work() {
        read();
//        createActor();
//        createMovie();
//        deleteWithEntityManager();
//        deleteWithQuery();
//        update();
//        addMovieToActor();
//        addActorToMovie();
    }

    private void addActorToMovie() {
        Movie movie = movieDAO.findById(1);

        Actor actor = actorDAO.findById(3);
        actor.getMovies().add(movie);
        actorDAO.update(actor);

        Actor actor2 = actorDAO.findById(4);
        actor2.getMovies().add(movie);
        actorDAO.update(actor2);

        movie.getActors().add(actor);
        movie.getActors().add(actor2);

        movieDAO.update(movie);
    }

    private void addMovieToActor() {
        Movie movie = movieDAO.findById(1);

        System.out.println(movie);

        for ( Actor actor : movie.getActors() ) {
            System.out.println(actor);
        }

        System.out.println("+_+_+_+_+_+_+_");

        Actor actor = actorDAO.findById(4);

        for (Movie m : actor.getMovies()) {
            System.out.println(m);
        }

//        Actor george = actorDAO.findById(3);
//
//        movie.getActors().add(franz);
//        movie.getActors().add(george);
//
//        movieDAO.update(movie);
    }

    private void update() {
        Actor actor = actorDAO.findById(3);
        System.out.println("Before update : " + actor);

        actor.setFirstName("George");
        actor.setLastName("Lucas");
        actor.setAge(55);

        actorDAO.update(actor);

        System.out.println("After update : " + actor);
    }

    private void deleteWithEntityManager() {
        Actor actor = actorDAO.findById(1);
        actorDAO.delete(actor);
    }

    private void deleteWithQuery() {
        actorDAO.deleteById(2);
    }

    private void createActor() {
        Actor actor = new Actor();

        actor.setFirstName("FirstName");
        actor.setLastName("LastName");
        actor.setAge(100);

        System.out.println("Before save : " + actor);

        actorDAO.save(actor);

        System.out.println("After save :" + actor);
    }

    private void createMovie() {
        Movie movie = new Movie();

        movie.setTitle("FirstName");
        movie.setDescription("LastName");
//        movie.setReleaseDate();

        System.out.println("Before save : " + movie);

        movieDAO.save(movie);

        System.out.println("After save :" + movie);
    }


    private void read() {
        Actor actor = actorDAO.findById(5);
        if(actor == null) {
            System.out.println("Unable to find actor by ID");
        }

        List<Actor> actors = actorDAO.findByFirstName("George");
        for (Actor a : actors ) {
            System.out.println("Find by first name : " + a);
        }

        List<Actor> actors2 = actorDAO.findByFirstNameAndLastName("Franz", "Cordes");
        for (Actor a : actors2 ) {
            System.out.println("Find by bothNames : " + a);
        }

        System.out.println(actor.toString());
    }


    public static void main (String[] args) {

        new HibernateDemo().work();
    }
}
