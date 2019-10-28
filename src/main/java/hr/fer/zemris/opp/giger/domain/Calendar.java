package hr.fer.zemris.opp.giger.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Calendar {

    @Id
    private Long id;

    @ElementCollection
    @CollectionTable(name = "Calendar_gig", joinColumns = @JoinColumn(name = "calendar_id"))
    @Column(name = "gigDate")
    private List<LocalDate> gigDates;

    @ElementCollection
    @CollectionTable(name = "Calendar_private", joinColumns = @JoinColumn(name = "calendar_id"))
    @Column(name = "privateDate")
    private List<LocalDate> privateDates;

    public Calendar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LocalDate> getGigDates() {
        return gigDates;
    }

    public void setGigDates(List<LocalDate> gigDates) {
        this.gigDates = gigDates;
    }

//    public List<LocalDate> getPrivateDates() {
//        return privateDates;
//    }
//
//    public void setPrivateDates(List<LocalDate> privateDates) {
//        this.privateDates = privateDates;
//    }
}
