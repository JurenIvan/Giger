package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.enums.GigType;
import hr.fer.zemris.opp.giger.web.rest.dto.BandDto;
import hr.fer.zemris.opp.giger.web.rest.dto.GigCreationDto;
import hr.fer.zemris.opp.giger.web.rest.dto.GigPreviewDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.EnumType.ORDINAL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gig {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY)
	@NotNull
	private Organizer organizer;
	@NotNull
	private LocalDateTime dateTime;
	@Embedded
	private Location location;
	private String description;
	private String expectedDuration;
	private Integer proposedPrice;
	private String name;

	@Enumerated(ORDINAL)
	private GigType gigType;
	private boolean finalDealAchieved;
	private boolean privateGig;

	@ManyToMany(cascade = MERGE)
	private List<Review> reviews;

	public GigPreviewDto toDto() {
		return new GigPreviewDto(id, organizer.getId(), dateTime, location, name, description, expectedDuration, proposedPrice, gigType, finalDealAchieved, privateGig, null);
	}

	public GigPreviewDto toDto(BandDto bandDto) {
		return new GigPreviewDto(id, organizer.getId(), dateTime, location, name, description, expectedDuration, proposedPrice, gigType, finalDealAchieved, privateGig, bandDto);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Gig)) return false;
		Gig gig = (Gig) o;
		return Objects.equals(id, gig.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public static Gig createGig(GigCreationDto gigCreationDto, Organizer organizer) {
		Gig gig = new Gig();
		gig.dateTime = gigCreationDto.getDateTime();
		gig.location = gigCreationDto.getLocation();
		gig.description = gigCreationDto.getDescription();
		gig.expectedDuration = gigCreationDto.getExpectedDuration();
		gig.proposedPrice = gigCreationDto.getProposedPrice();
		gig.gigType = gigCreationDto.getGigType();
		gig.privateGig = gigCreationDto.getPrivateGig();
		gig.name = gigCreationDto.getGigName();
		gig.organizer = organizer;

		return gig;
	}

	public Gig editGig(GigCreationDto gigCreationDto) {
		if (gigCreationDto.getDateTime() != null)
			dateTime = gigCreationDto.getDateTime();

		if (gigCreationDto.getLocation() != null)
			location = gigCreationDto.getLocation();

		if (gigCreationDto.getDescription() != null)
			description = gigCreationDto.getDescription();

		if (gigCreationDto.getExpectedDuration() != null)
			expectedDuration = gigCreationDto.getExpectedDuration();

		if (gigCreationDto.getProposedPrice() != null)
			proposedPrice = gigCreationDto.getProposedPrice();

		if (gigCreationDto.getGigType() != null)
			gigType = gigCreationDto.getGigType();

		if (gigCreationDto.getPrivateGig() != null)
			privateGig = gigCreationDto.getPrivateGig();

		if (gigCreationDto.getGigName() != null)
			name = gigCreationDto.getGigName();

		return this;
	}
}
