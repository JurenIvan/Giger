package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.MusicianPreviewDto;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianPreviewPictureDto;
import hr.fer.zemris.opp.giger.web.rest.dto.MusicianProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Musician {

	@Id
	private Long id;
	private String bio;
	private Boolean publicCalendar;

	@ManyToMany
	private List<Instrument> instruments;

	@ManyToMany
	private List<Gig> pastGigs;

	@OneToMany
	private List<Post> posts;

	@ManyToMany
	private List<Occasion> occasions;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Musician)) return false;
		Musician musician = (Musician) o;
		return Objects.equals(id, musician.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public MusicianPreviewDto toDto() {
		return new MusicianPreviewDto(id, bio, publicCalendar, instruments);
	}

	public List<Occasion> getOccasionsWithoutDescriptions() {
		return occasions.stream().map(Occasion::getOccasionWithoutDescription).collect(toList());
	}

	public List<Occasion> getPublicOccasios() {
		return occasions.stream().filter(occasion -> !occasion.getPersonalOccasion()).collect(toList());
	}

	public Musician update(MusicianProfileDto musicianProfileDto, List<Instrument> instruments) {
		if (musicianProfileDto.getInstrumentList() != null) {
			this.instruments = instruments;
		}
		return this;
	}

	public void addPost(Post post) {
		posts.add(post);
	}

	public MusicianPreviewPictureDto createMusicianPreviewPicture(String username, @Length(max = 10000) String pictureUrl) {
		return new MusicianPreviewPictureDto(id, bio, publicCalendar, instruments, username, pictureUrl);
	}
}