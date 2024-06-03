package com.example.javaspringbootb17;

import com.example.javaspringbootb17.entity.*;
import com.example.javaspringbootb17.model.enums.MovieType;
import com.example.javaspringbootb17.model.enums.Role;
import com.example.javaspringbootb17.repsitory.*;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class JavaspringbootB17ApplicationTests {
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private DirectorRepository directorRepository;
	@Autowired
	private ActorRepository actorRepository;
	@Test
	void savedMovie() {
		Faker faker=new Faker();
		Slugify slugify= Slugify.builder().build();
		Boolean status=faker.bool().bool();
		for (int i=0;i<100;i++){
			String name=faker.book().title();
			Movie movie= Movie.builder()
					.name(name)
					.slug(slugify.slugify(name))
					.description(faker.lorem().paragraph())
					.poster("https://placehold.co/600x400?text="+name.substring(0,1).toLowerCase())
					.releaseYear(faker.number().numberBetween(2000,2024))
					.rating(faker.number().randomDouble(1,1,5))
					.trailerURL("src=\"https://www.youtube.com/embed/hY1nE5vAriQ?si=vKKw9LOj0MFiBsQT\"")
					.type(MovieType.values()[faker.number().numberBetween(0,MovieType.values().length-1)])
					.status(status)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.publishedAt(status?LocalDateTime.now():null)
					.build();
			movieRepository.save(movie);
		}
	}
	@Test
	void save_users() {
		Faker faker = new Faker();

		for (int i = 0; i < 30; i++) {
			String name = faker.name().fullName();
			String password = faker.internet().password();
			User user = User.builder()
					.name(name)                                                        
					.email(faker.internet().emailAddress())
					.avatar("https://placehold.co/600x400?text=" + name)
					.password(password)
					.role(Role.values()[faker.number().numberBetween(0, Role.values().length - 1)])
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();
			userRepository.save(user);
		}
	}
	@Test
	void save_countries() {
		Faker faker = new Faker();
		Slugify slugify = Slugify.builder().build();
		for (int i = 0; i < 20; i++) {
			String name = faker.country().name();
			Country country = Country.builder()
					.name(name)
					.slug(slugify.slugify(name))
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();
			countryRepository.save(country);
		}
	}

	@Test
	void save_genres() {
		Faker faker = new Faker();
		Slugify slugify = Slugify.builder().build();
		for (int i = 0; i < 20; i++) {
			String name = faker.book().genre();
			Genre genre = Genre.builder()
					.name(name)
					.slug(slugify.slugify(name))
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();
			genreRepository.save(genre);
		}
	}

	@Test
	void save_directors() {
		Faker faker = new Faker();
		Slugify slugify = Slugify.builder().build();

		for (int i = 0; i < 30; i++) {
			String name = faker.name().fullName();
			Director director = Director.builder()
					.name(name)
					.slug(slugify.slugify(name))
					.avatar("https://placehold.co/600x400?text=" + name)
					.bio(faker.lorem().paragraph())
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();
			directorRepository.save(director);
		}
	}

	@Test
	void save_actors() {
		Faker faker = new Faker();
		Slugify slugify = Slugify.builder().build();

		for (int i = 0; i < 100; i++) {
			String name = faker.name().fullName();
			Actor actor = Actor.builder()
					.name(name)
					.slug(slugify.slugify(name))
					.avatar("https://placehold.co/600x400?text=" + name)
					.bio(faker.lorem().paragraph())
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();
			actorRepository.save(actor);
		}
	}
	@Test
	void movie_method_test(){
		List<Movie>movies=movieRepository.findAll();//select*from movie
		System.out.println("movie size:"+movies.size());

		List<Movie>moviesByIds=movieRepository.findAllById(List.of(1,2,3));
		System.out.println("movie size:"+moviesByIds.size());

		Movie movie=movieRepository.findById(1).orElse(null);
		movie.setName("hiihihii");
		movieRepository.save(movie);

//		movieRepository.deleteById(2);
//		movieRepository.delete(movie);
//		movieRepository.deleteAll(moviesByIds);
//		movieRepository.deleteAll();
	}
}