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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	@Autowired
	private EpisodeRepository episodeRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private FavoriteRepository favoriteRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Test
	void saved_Movie() {
		Faker faker=new Faker();
		Slugify slugify= Slugify.builder().build();
		Boolean status=faker.bool().bool();
		Random rd=new Random();

		List<Country>countries=countryRepository.findAll();
		List<Genre>genres=genreRepository.findAll();
		List<Actor>actors=actorRepository.findAll();
		List<Director>directors=directorRepository.findAll();

		for (int i=0;i<100;i++){

			Country rdCountry=countries.get(rd.nextInt(countries.size()));

			List<Genre> rdGenres=new ArrayList<>();
			for (int j=0;j<rd.nextInt(2)+1;j++){
				Genre rdGenre=genres.get(rd.nextInt(genres.size()));
				if (!rdGenres.contains((rdGenre))){
					rdGenres.add(rdGenre);
				}
			}
			List<Actor> rdActors=new ArrayList<>();
			for (int j=0;j<rd.nextInt(3)+5;j++){
				Actor rdActor=actors.get(rd.nextInt(actors.size()));
				if (!rdActors.contains((rdActor))){
					rdActors.add(rdActor);
				}
			}
			List<Director> rdDirectors=new ArrayList<>();
			for (int j=0;j<rd.nextInt(2)+1;j++){
				Director rdDirector=directors.get(rd.nextInt(directors.size()));
				if (!rdDirectors.contains((rdDirector))){
					rdDirectors.add(rdDirector);
				}
			}

			String name=faker.book().title();
			Movie movie= Movie.builder()
					.name(name)
					.slug(slugify.slugify(name))
					.description(faker.lorem().paragraph())
					.poster("https://placehold.co/600x400?text="+name.substring(0,1).toLowerCase())
					.releaseYear(faker.number().numberBetween(2000,2024))
					.rating(faker.number().randomDouble(1,1,5))
					.trailerURL("src=\"https://www.youtube.com/embed/hY1nE5vAriQ?si=vKKw9LOj0MFiBsQT\"")
					.type(MovieType.values()[faker.number().numberBetween(0,MovieType.values().length)])
					.status(status)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.publishedAt(status?LocalDateTime.now():null)
					.country(rdCountry)
					.genres(rdGenres)
					.actors(rdActors)
					.directors(rdDirectors)
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
					.password("123")
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
	void save_episode(){
		Random rd=new Random();
		List<Movie>movies=movieRepository.findAll();

		for (Movie movie:movies){
			if (movie.getType().equals(MovieType.PHIM_BO)){
				for (int i=0;i<rd.nextInt(6)+5;i++){
					Episode episode=Episode.builder()
							.name("Tập"+(i+1))
							.duration(50)
							.videoUrl("https://videos.pexels.com/video-files/3209828/3209828-hd_1280_720_25fps.mp4")
							.displayOrder(i+1)
							.status(true)
							.createdAt(LocalDateTime.now())
							.updatedAt(LocalDateTime.now())
							.publishedAt(true ? LocalDateTime.now():null)
							.movie(movie)
							.build();
					episodeRepository.save(episode);
				}
			}else {
				Episode episode=Episode.builder()
						.name("Xem full")
						.duration(150)
						.videoUrl("https://videos.pexels.com/video-files/3209828/3209828-hd_1280_720_25fps.mp4")
						.displayOrder(1)
						.status(true)
						.createdAt(LocalDateTime.now())
						.updatedAt(LocalDateTime.now())
						.publishedAt(LocalDateTime.now())
						.movie(movie)
						.build();
				episodeRepository.save(episode);
			}
		}
	}
	@Test
	void save_reviews(){
		Random rd=new Random();
		Faker faker=new Faker();
		List<Movie>movies=movieRepository.findAll();
		List<User>users=userRepository.findAll();

		for (Movie movie:movies){
			for (int i=0;i< rd.nextInt(6)+5;i++){
				User rdUser=users.get(rd.nextInt(users.size()));
				Review review=Review.builder()
						.content(faker.lorem().paragraph())
						.rating(Double.valueOf(rd.nextInt(6)))
						.createdAt(LocalDateTime.now())
						.updatedAt(LocalDateTime.now())
						.movie(movie)
						.user(rdUser)
						.build();
				reviewRepository.save(review);
			}
		}
	}
	@Test
	void save_histories(){
		Random rd=new Random();
		List<Movie>movies=movieRepository.findAll();
		List<User>users=userRepository.findAll();
		List<Episode>episodes=episodeRepository.findAll();

		for (User user:users){
			for (int i=0;i< rd.nextInt(6)+5;i++){
				Movie rdMovie=movies.get(rd.nextInt(movies.size()));
				Episode rdEpisode=episodes.get(rd.nextInt(episodes.size()));
				History history=History.builder()
						.duration(Double.valueOf(rd.nextInt(150,300)))
						.createdAt(LocalDateTime.now())
						.updatedAt(LocalDateTime.now())
						.user(user)
						.movie(rdMovie)
						.episode(rdEpisode)
						.build();
				historyRepository.save(history);
			}
		}
	}
	@Test
	void save_favorite(){
		Random rd=new Random();
		List<Movie>movies=movieRepository.findAll();
		List<User>users=userRepository.findAll();

		for (User user:users){
			for (int i=0;i< rd.nextInt(6)+5;i++){
				Movie rdMovie=movies.get(rd.nextInt(movies.size()));
				Favorite favorite=Favorite.builder()
						.createdAt(LocalDateTime.now())
						.updatedAt(LocalDateTime.now())
						.user(user)
						.movie(rdMovie)
						.build();
				favoriteRepository.save(favorite);
			}
		}
	}
	@Test
	void save_admin() {
		Faker faker = new Faker();
			String name = faker.name().fullName();
			User user = User.builder()
					.name(name)
					.email(faker.internet().emailAddress())
					.avatar("https://placehold.co/600x400?text=" + name)
					.password("123")
					.role(Role.Admin)
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.build();
			userRepository.save(user);
	}
	@Test
	void encode_password_user(){
		List<User>users=userRepository.findAll();
		for (User user:users){
			user.setPassword((passwordEncoder).encode("123"));
			userRepository.save(user);
		}
	}
	@Test
	void movie_method_test(){
//		List<Movie>movies=movieRepository.findAll();//select*from movie
//		System.out.println("movie size:"+movies.size());
//
//		List<Movie>moviesByIds=movieRepository.findAllById(List.of(1,2,3));
//		System.out.println("movie size:"+moviesByIds.size());
//
//		Movie movie=movieRepository.findById(1).orElse(null);
//		movie.setName("hiihihii");
//		movieRepository.save(movie);

//		movieRepository.deleteById(2);
//		actorRepository.deleteAll();
//		movieRepository.deleteAll(moviesByIds);
//		movieRepository.deleteAll();
	}
}