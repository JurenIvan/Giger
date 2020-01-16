package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.web.rest.dto.MusicianProfileDto;
import hr.fer.zemris.opp.giger.web.rest.dto.PersonPreviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	@Id
	private Long id;

	@Column(unique = true)
	private String username;
	private String phoneNumber;
	@Length(max = 10000)
	private String pictureUrl;

	public PersonPreviewDto toDto() {
		return new PersonPreviewDto(id, username, pictureUrl);
	}

	public Person updatePerson(MusicianProfileDto musicianProfileDto) {
		if (musicianProfileDto.getContactNumber() != null)
			this.phoneNumber = musicianProfileDto.getContactNumber();

		if (musicianProfileDto.getPictureUrl() != null)
			this.pictureUrl = musicianProfileDto.getPictureUrl();

		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Person)) return false;
		Person person = (Person) o;
		return Objects.equals(id, person.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}