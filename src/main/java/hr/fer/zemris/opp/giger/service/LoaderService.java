package hr.fer.zemris.opp.giger.service;

import hr.fer.zemris.opp.giger.domain.*;
import hr.fer.zemris.opp.giger.domain.enums.GigType;
import hr.fer.zemris.opp.giger.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static hr.fer.zemris.opp.giger.domain.enums.GigType.*;
import static hr.fer.zemris.opp.giger.domain.enums.InstrumentType.*;
import static hr.fer.zemris.opp.giger.domain.enums.Role.*;
import static java.time.LocalDateTime.of;

@Service
@AllArgsConstructor
public class LoaderService implements ApplicationRunner {

	private SystemPersonRepository systemPersonRepository;
	private InstrumentRepository instrumentRepository;
	private OrganizerRepository organizerRepository;
	private PersonRepository personRepository;
	private ReviewRepository reviewRepository;
	private GigRepository gigRepository;
	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private OccasionRepository occasionRepository;
	private MusicianRepository musicianRepository;
	private BandRepository bandRepository;
	private MessageRepository messageRepository;
	private ConversationRepository conversationRepository;
	private List<SystemPerson> systemPeople;
	private List<Instrument> instruments;
	private List<Organizer> organizers;
	private List<Location> locations;
	private List<Person> people;
	private List<Review> reviews;
	private List<Gig> gigs;
	private List<Comment> comments;
	private List<Post> posts;
	private List<Occasion> occasions;
	private List<Musician> musicians;
	private List<Band> bands;
	private List<Message> messages;
	private List<Conversation> conversations;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		createSystemPeople();
		createInstruments();
		createOrganizers();
		createLocations();
		createPeople();
		createReviews();
		createGigs();
		createComments();
		createPosts();
		createOccasions();
		createMusicians();
		createBands();
		createMessages();
		createConversations();
	}

	private void createSystemPeople() {
		systemPeople.add(new SystemPerson(null, "john.doe@giger.com", true, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(ORGANIZER, PERSON)));
		systemPeople.add(new SystemPerson(null, "james.doe@giger.com", true, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(ORGANIZER, PERSON)));
		systemPeople.add(new SystemPerson(null, "robert.doe@giger.com", false, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(ORGANIZER, PERSON)));
		systemPeople.add(new SystemPerson(null, "admin@giger.com", true, false, BCrypt.hashpw("adminadmin", BCrypt.gensalt(10)), List.of(MUSICIAN, PERSON, ORGANIZER)));
		systemPeople.add(new SystemPerson(null, "michael.doe@giger.com", true, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(MUSICIAN, PERSON)));
		systemPeople.add(new SystemPerson(null, "william.doe@giger.com", true, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(MUSICIAN, PERSON)));
		systemPeople.add(new SystemPerson(null, "david.doe@giger.com", false, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(PERSON)));
		systemPeople.add(new SystemPerson(null, "richard.doe@giger.com", true, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(PERSON)));
		systemPeople.add(new SystemPerson(null, "joseph.doe@giger.com", true, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(PERSON)));
		systemPeople.add(new SystemPerson(null, "thomas.doe@giger.com", false, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(PERSON)));
		systemPeople.add(new SystemPerson(null, "george.doe@giger.com", true, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(ORGANIZER, PERSON)));
		systemPeople.add(new SystemPerson(null, "don.doe@giger.com", true, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(ORGANIZER, PERSON)));
		systemPeople.add(new SystemPerson(null, "luke.doe@giger.com", false, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(ORGANIZER, PERSON)));
		systemPeople.add(new SystemPerson(null, "charles@giger.com", true, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(MUSICIAN, PERSON)));
		systemPeople.add(new SystemPerson(null, "daniel.doe@giger.com", true, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(MUSICIAN, PERSON)));
		systemPeople.add(new SystemPerson(null, "mark.doe@giger.com", true, false, BCrypt.hashpw("12345678", BCrypt.gensalt(10)), List.of(MUSICIAN, PERSON)));

		this.systemPeople = systemPersonRepository.saveAll(systemPeople);
	}

	private void createInstruments() {
		instruments.add(new Instrument(null, "Marimba", PERCUSSION));
		instruments.add(new Instrument(null, "Piano", KEYBOARD));
		instruments.add(new Instrument(null, "Vibraphone", PERCUSSION));
		instruments.add(new Instrument(null, "Trumpet", BRASS_INSTRUMENT));
		instruments.add(new Instrument(null, "Xylophone", PERCUSSION));
		instruments.add(new Instrument(null, "Guitar", STRING_INSTRUMENT));
		instruments.add(new Instrument(null, "Clarinet", WOODWIND_INSTRUMENT));
		instruments.add(new Instrument(null, "Oboe", WOODWIND_INSTRUMENT));
		instruments.add(new Instrument(null, "Violine", STRING_INSTRUMENT));
		instruments.add(new Instrument(null, "Flute", WOODWIND_INSTRUMENT));

		this.instruments = instrumentRepository.saveAll(instruments);
	}

	private void createOrganizers() {
		organizers.add(new Organizer(systemPeople.get(0).getId(), "John"));
		organizers.add(new Organizer(systemPeople.get(1).getId(), "James"));
		organizers.add(new Organizer(systemPeople.get(2).getId(), "Robert"));
		organizers.add(new Organizer(systemPeople.get(3).getId(), "Admin"));
		organizers.add(new Organizer(systemPeople.get(10).getId(), "George"));
		organizers.add(new Organizer(systemPeople.get(11).getId(), "Michael"));
		organizers.add(new Organizer(systemPeople.get(12).getId(), "William"));

		this.organizers = organizerRepository.saveAll(organizers);
	}

	private void createLocations() {
		locations.add(new Location(45.0, 45.0, "Odravska 8", "Drugi kat"));
		locations.add(new Location(35.0, 45.0, "Bašćanske ploče 33", "Prvi kat"));
		locations.add(new Location(45.0, 35.0, "Kneza Višeslava 10", "Zadnja vrata"));
		locations.add(new Location(25.0, 25.0, "Kneza Branimira 68", "Prvi ulaz"));
		locations.add(new Location(45.0, 35.0, "Kralja Tomislava 78", "Zabranjene životinje"));
		locations.add(new Location(35.0, 45.0, "Šegrta Hlapića 63", "Dopušteno pušenje"));
		locations.add(new Location(45.0, 35.0, "Ivice Kičmanovića 50", "Zabranjeno pušenje"));
		locations.add(new Location(25.0, 25.0, "Šenoine Branke 55", "Prvi ulaz"));
	}

	private void createPeople() {
		people.add(new Person(systemPeople.get(0).getId(), "john.doe", "091536780", "https://lumiere-a.akamaihd.net/v1/images/ct_mickeymouseandfriends_mickey_ddt-16970_4e99445d.jpeg"));
		people.add(new Person(systemPeople.get(1).getId(), "james.doe", "091536781", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Snoopy_Peanuts.png/200px-Snoopy_Peanuts.png"));
		people.add(new Person(systemPeople.get(2).getId(), "robert.doe", "091536782", "https://cdn.shopify.com/s/files/1/0456/3093/products/Peanuts-Astronaut_Snoopy_Standing_Pin_82eb7563-d533-4ede-91b2-7dd9267d0651_x800.jpg?v=1562088968"));
		people.add(new Person(systemPeople.get(3).getId(), "admin.doe", "091536783", "https://lumiere-a.akamaihd.net/v1/images/ct_mickeymouseandfriends_mickey_ddt-16970_4e99445d.jpeg"));
		people.add(new Person(systemPeople.get(4).getId(), "michael.doe", "091536784", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Snoopy_Peanuts.png/200px-Snoopy_Peanuts.png"));
		people.add(new Person(systemPeople.get(5).getId(), "william.doe", "091536785", "https://cdn.shopify.com/s/files/1/0456/3093/products/Peanuts-Astronaut_Snoopy_Standing_Pin_82eb7563-d533-4ede-91b2-7dd9267d0651_x800.jpg?v=1562088968"));
		people.add(new Person(systemPeople.get(6).getId(), "david.doe", "091536786", "https://lumiere-a.akamaihd.net/v1/images/ct_mickeymouseandfriends_mickey_ddt-16970_4e99445d.jpeg"));
		people.add(new Person(systemPeople.get(7).getId(), "richard.doe", "091536787", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Snoopy_Peanuts.png/200px-Snoopy_Peanuts.png"));
		people.add(new Person(systemPeople.get(8).getId(), "joseph.doe", "091536788", "https://cdn.shopify.com/s/files/1/0456/3093/products/Peanuts-Astronaut_Snoopy_Standing_Pin_82eb7563-d533-4ede-91b2-7dd9267d0651_x800.jpg?v=1562088968"));
		people.add(new Person(systemPeople.get(9).getId(), "thomas.doe", "091536789", "https://lumiere-a.akamaihd.net/v1/images/ct_mickeymouseandfriends_mickey_ddt-16970_4e99445d.jpeg"));
		people.add(new Person(systemPeople.get(10).getId(), "george.doe", "091536710", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Snoopy_Peanuts.png/200px-Snoopy_Peanuts.png"));
		people.add(new Person(systemPeople.get(11).getId(), "don.doe", "091536711", "https://cdn.shopify.com/s/files/1/0456/3093/products/Peanuts-Astronaut_Snoopy_Standing_Pin_82eb7563-d533-4ede-91b2-7dd9267d0651_x800.jpg?v=1562088968"));
		people.add(new Person(systemPeople.get(12).getId(), "luke.doe", "091536712", "https://lumiere-a.akamaihd.net/v1/images/ct_mickeymouseandfriends_mickey_ddt-16970_4e99445d.jpeg"));
		people.add(new Person(systemPeople.get(13).getId(), "charles.doe", "091536713", "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/Snoopy_Peanuts.png/200px-Snoopy_Peanuts.png"));
		people.add(new Person(systemPeople.get(14).getId(), "daniel.doe", "091536714", "https://cdn.shopify.com/s/files/1/0456/3093/products/Peanuts-Astronaut_Snoopy_Standing_Pin_82eb7563-d533-4ede-91b2-7dd9267d0651_x800.jpg?v=1562088968"));
		people.add(new Person(systemPeople.get(15).getId(), "mark.doe", "091536715", "https://lumiere-a.akamaihd.net/v1/images/ct_mickeymouseandfriends_mickey_ddt-16970_4e99445d.jpeg"));


		this.people = personRepository.saveAll(people);
	}

	private void createReviews() {
		reviews.add(new Review(null, "great", "ok", 5, 3, of(2019, 12, 1, 18, 0, 0), people.get(0)));
		reviews.add(new Review(null, "good", "good", 3, 3, of(2019, 12, 1, 16, 0, 0), people.get(1)));
		reviews.add(new Review(null, "great", "great", 5, 5, of(2019, 11, 1, 18, 0, 0), people.get(2)));
		reviews.add(new Review(null, "bad", "good", 1, 3, of(2019, 10, 1, 18, 0, 0), people.get(3)));
		reviews.add(new Review(null, "bad", "bad", 1, 1, of(2019, 9, 1, 18, 0, 0), people.get(4)));
		reviews.add(new Review(null, "great", "good", 5, 3, of(2019, 8, 1, 18, 0, 0), people.get(5)));
		reviews.add(new Review(null, "great", "bad", 5, 1, of(2019, 7, 1, 18, 0, 0), people.get(6)));
		reviews.add(new Review(null, "ok", "ok", 2, 2, of(2019, 12, 2, 10, 0, 0), people.get(7)));
		reviews.add(new Review(null, "very good", "ok", 4, 3, of(2019, 12, 5, 18, 0, 0), people.get(8)));
		reviews.add(new Review(null, "very good", "very good", 4, 4, of(2019, 12, 7, 18, 0, 0), people.get(9)));

		this.reviews = reviewRepository.saveAll(reviews);
	}

	private void createGigs() {
		gigs.add(new Gig(null, organizers.get(0), of(2020, 6, 23, 20, 0, 0), locations.get(0), "Rock concert", "2h", 1500, "Rock concert", CONCERT, true, false, List.of(reviews.get(0), reviews.get(1))));
		gigs.add(new Gig(null, organizers.get(1), of(2020, 5, 4, 10, 0, 0), locations.get(1), "Bachelors party", "4h", 500, "Bachelors", BACHELORS_PARTY, true, false, List.of(reviews.get(2), reviews.get(3))));
		gigs.add(new Gig(null, organizers.get(2), of(2020, 6, 6, 13, 0, 0), locations.get(2), "Birthday", "4h", 200, "Birthday", GigType.BIRTHDAY, true, false, List.of(reviews.get(4), reviews.get(5))));
		gigs.add(new Gig(null, organizers.get(3), of(2020, 8, 12, 15, 0, 0), locations.get(3), "Wedding", "6h", 500, "Wedding", WEDDING, true, false, List.of(reviews.get(6), reviews.get(7))));
		gigs.add(new Gig(null, organizers.get(4), of(2020, 4, 10, 22, 0, 0), locations.get(4), "Jazz concert", "Nastup 1", 1200, "Jazz concert", CONCERT, true, false, List.of(reviews.get(8), reviews.get(9))));
		gigs.add(new Gig(null, organizers.get(4), of(2020, 4, 15, 22, 0, 0), locations.get(4), "Jazz concert", "Nastup 1", 1200, "Jazz concert", CONCERT, false, false, List.of()));
		gigs.add(new Gig(null, organizers.get(3), of(2020, 4, 16, 22, 0, 0), locations.get(4), "Jazz concert", "Nastup 1", 1200, "Jazz concert", CONCERT, false, false, List.of()));


		this.gigs = gigRepository.saveAll(gigs);
	}

	private void createComments() {
		comments.add(new Comment(null, "Yeah", of(2019, 12, 8, 20, 0), people.get(0)));
		comments.add(new Comment(null, "Good luck", of(2019, 12, 9, 20, 0), people.get(1)));
		comments.add(new Comment(null, "Nice", of(2019, 12, 7, 20, 0), people.get(2)));
		comments.add(new Comment(null, "Best wishes!", of(2019, 12, 3, 5, 0), people.get(3)));
		comments.add(new Comment(null, "Nice job", of(2019, 11, 5, 12, 0), people.get(4)));
		comments.add(new Comment(null, "What instrument is that?", of(2019, 11, 8, 15, 0), people.get(5)));
		comments.add(new Comment(null, "You rock, man", of(2019, 11, 6, 14, 0), people.get(6)));
		comments.add(new Comment(null, "Where can I get this?", of(2019, 6, 8, 20, 0), people.get(7)));
		comments.add(new Comment(null, "Haha", of(2019, 8, 8, 8, 0), people.get(8)));
		comments.add(new Comment(null, "xd", of(2019, 2, 7, 14, 0), people.get(9)));
		comments.add(new Comment(null, "No", of(2019, 12, 1, 20, 0), people.get(2)));
		comments.add(new Comment(null, "Congrats!", of(2019, 12, 2, 20, 0), people.get(3)));
		comments.add(new Comment(null, "When?", of(2019, 12, 3, 20, 0), people.get(4)));
		comments.add(new Comment(null, "Can I buy it online?", of(2019, 12, 4, 20, 0), people.get(5)));
		comments.add(new Comment(null, "Yes, ofcourse!", of(2019, 12, 5, 20, 0), people.get(6)));
		comments.add(new Comment(null, "Where was it?", of(2019, 12, 6, 20, 0), people.get(7)));
		comments.add(new Comment(null, "Good for you.", of(2019, 12, 7, 20, 0), people.get(8)));
		comments.add(new Comment(null, "Woohoo", of(2019, 12, 8, 20, 0), people.get(9)));

		this.comments = commentRepository.saveAll(comments);
	}

	private void createPosts() {
		posts.add(new Post(null, "What a beautiful day for music", of(2019, 12, 1, 19, 20, 0), List.of(comments.get(0), comments.get(1))));
		posts.add(new Post(null, "Having my first concert tonight", of(2019, 12, 8, 19, 0), List.of()));
		posts.add(new Post(null, "First post", of(2019, 12, 7, 19, 0), List.of(comments.get(2))));
		posts.add(new Post(null, "Just became a band member hehe", of(2019, 12, 1, 9, 0), List.of(comments.get(3))));
		posts.add(new Post(null, "Tonight is the first gig in my cafe", of(2019, 11, 5, 11, 0), List.of(comments.get(4))));
		posts.add(new Post(null, "Playing xyz", of(2019, 11, 8, 14, 0), List.of(comments.get(5))));
		posts.add(new Post(null, "Got a big chance.", of(2019, 11, 6, 13, 0), List.of(comments.get(6))));
		posts.add(new Post(null, "Found the best shop for guitars.", of(2019, 6, 8, 19, 0), List.of(comments.get(7))));
		posts.add(new Post(null, "Hahahah", of(2019, 8, 8, 7, 0), List.of(comments.get(8))));
		posts.add(new Post(null, "lol", of(2019, 2, 7, 13, 0), List.of(comments.get(9))));
		posts.add(new Post(null, "My post", of(2019, 12, 1, 19, 0), List.of()));
		posts.add(new Post(null, "Having a big concert tonight.", of(2019, 12, 2, 19, 0), List.of()));
		posts.add(new Post(null, "New album coming soon.", of(2019, 12, 3, 19, 0), List.of()));
		posts.add(new Post(null, "New song coming soon.", of(2019, 12, 4, 19, 0), List.of()));
		posts.add(new Post(null, "Did you listen my xyz song?", of(2019, 12, 5, 19, 0), List.of()));
		posts.add(new Post(null, "Loved the concert tonight.", of(2019, 12, 6, 19, 0), List.of()));
		posts.add(new Post(null, "Working on something big.", of(2019, 12, 7, 19, 0), List.of()));
		posts.add(new Post(null, "Got promoted to a band leader!!", of(2019, 12, 8, 19, 0), List.of()));

		this.posts = postRepository.saveAll(posts);
	}

	private void createOccasions() {
		occasions.add(new Occasion(null, of(2020, 1, 1, 0, 0, 0), "My birthday", true));
		occasions.add(new Occasion(null, of(2020, 1, 2, 0, 0, 0), "John's birthday", true));
		occasions.add(new Occasion(null, of(2020, 2, 3, 0, 0, 0), "Emma's wedding", true));
		occasions.add(new Occasion(null, of(2020, 3, 4, 0, 0, 0), "My wedding", true));
		occasions.add(new Occasion(null, of(2020, 1, 5, 0, 0, 0), "Doctor's appointment", true));
		occasions.add(new Occasion(null, of(2020, 5, 6, 0, 0, 0), "Doctor's appointment", false));
		occasions.add(new Occasion(null, of(2020, 7, 7, 0, 0, 0), "Cindy's birthday", false));
		occasions.add(new Occasion(null, of(2020, 9, 8, 0, 0, 0), "Luke's bachelors party", false));
		occasions.add(new Occasion(null, of(2020, 1, 9, 0, 0, 0), "Meeting", false));
		occasions.add(new Occasion(null, of(2020, 4, 10, 0, 0, 0), "Teambuilding", false));

		this.occasions = occasionRepository.saveAll(occasions);
	}

	private void createMusicians() {
		musicians.add(new Musician(systemPeople.get(3).getId(), "bio1", true, List.of(instruments.get(0), instruments.get(1), instruments.get(2)), List.of(gigs.get(0), gigs.get(1)), List.of(posts.get(0), posts.get(1), posts.get(2)), List.of(occasions.get(0))));
		musicians.add(new Musician(systemPeople.get(4).getId(), "bio2", true, List.of(instruments.get(3), instruments.get(4), instruments.get(5)), List.of(gigs.get(2), gigs.get(3)), List.of(posts.get(10), posts.get(11)), List.of(occasions.get(2), occasions.get(3))));
		musicians.add(new Musician(systemPeople.get(5).getId(), "bio3", true, List.of(instruments.get(0), instruments.get(2), instruments.get(5)), List.of(gigs.get(0), gigs.get(4)), List.of(posts.get(3)), List.of(occasions.get(4), occasions.get(5))));
		musicians.add(new Musician(systemPeople.get(13).getId(), "bio4", true, List.of(instruments.get(6), instruments.get(7), instruments.get(8)), List.of(gigs.get(0), gigs.get(2)), List.of(posts.get(4), posts.get(7)), List.of(occasions.get(6), occasions.get(7))));
		musicians.add(new Musician(systemPeople.get(14).getId(), "bio5", false, List.of(instruments.get(1), instruments.get(8), instruments.get(9)), List.of(gigs.get(1), gigs.get(4)), List.of(posts.get(5), posts.get(8)), List.of(occasions.get(8), occasions.get(9))));
		musicians.add(new Musician(systemPeople.get(15).getId(), "bio6", false, List.of(instruments.get(0), instruments.get(3), instruments.get(7)), List.of(gigs.get(2), gigs.get(4)), List.of(posts.get(6), posts.get(9)), List.of(occasions.get(1))));

		this.musicians = musicianRepository.saveAll(musicians);
	}

	private void createBands() {
		bands.add(new Band(null, "The Beatles", "bio1", LocalDateTime.of(1957, 6, 5, 0, 0, 0), "https://upload.wikimedia.org/wikipedia/commons/d/df/The_Fabs.JPG", locations.get(0), 100.0, musicians.get(0), List.of(musicians.get(0), musicians.get(1)), List.of(musicians.get(2)), List.of(musicians.get(3)), List.of(musicians.get(4)), List.of(posts.get(12), posts.get(13)), List.of(CONCERT), List.of(occasions.get(0), occasions.get(1)), List.of(gigs.get(0), gigs.get(1)), List.of(gigs.get(5), gigs.get(6))));
		bands.add(new Band(null, "Pink Floyd", "bio2", LocalDateTime.of(1965, 6, 5, 0, 0, 0), "https://upload.wikimedia.org/wikipedia/en/thumb/d/d6/Pink_Floyd_-_all_members.jpg/250px-Pink_Floyd_-_all_members.jpg", locations.get(1), 150.0, musicians.get(2), List.of(musicians.get(2), musicians.get(3)), List.of(musicians.get(4)), List.of(musicians.get(5)), List.of(musicians.get(5)), List.of(posts.get(14), posts.get(15)), List.of(WEDDING), List.of(occasions.get(2), occasions.get(3)), List.of(gigs.get(2), gigs.get(3)), List.of()));
		bands.add(new Band(null, "AC/DC", "bio3", LocalDateTime.of(1973, 6, 5, 0, 0, 0), "http://radiolabin.hr/portal/vijesti/1563455875ac-dc.jpg", locations.get(2), 200.0, musicians.get(4), List.of(musicians.get(4), musicians.get(5)), List.of(musicians.get(0)), List.of(musicians.get(1)), List.of(musicians.get(5)), List.of(posts.get(16), posts.get(17)), List.of(BACHELORS_PARTY), List.of(occasions.get(4), occasions.get(5)), List.of(gigs.get(4)), List.of()));

		this.bands = bandRepository.saveAll(bands);
	}

	private void createMessages() {
		messages.add(new Message(null, "Hello", of(2019, 12, 1, 12, 0, 0), people.get(0), null));
		messages.add(new Message(null, "Hi", of(2019, 12, 2, 10, 0, 0), people.get(1), null));
		messages.add(new Message(null, "How are you?", of(2019, 12, 3, 10, 0, 0), people.get(0), null));
		messages.add(new Message(null, "Good", of(2019, 12, 4, 10, 0, 0), people.get(1), null));
		messages.add(new Message(null, "How is your day?", of(2019, 12, 5, 10, 0, 0), people.get(0), null));
		messages.add(new Message(null, "Nice", of(2019, 12, 6, 10, 0, 0), people.get(1), null));
		messages.add(new Message(null, "OK", of(2019, 12, 7, 10, 0, 0), people.get(0), null));
		messages.add(new Message(null, "Haha", of(2019, 12, 8, 10, 0, 0), people.get(1), null));

		this.messages = messageRepository.saveAll(messages);
	}

	private void createConversations() {
		conversations.add(new Conversation(null, "title1", people.get(0).getPictureUrl(), List.of(people.get(0), people.get(1)), bands.get(0), List.of(messages.get(0), messages.get(1), messages.get(2), messages.get(3), messages.get(4), messages.get(5), messages.get(6), messages.get(7))));

		this.conversations = conversationRepository.saveAll(conversations);
	}
}
