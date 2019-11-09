package hr.fer.zemris.opp.giger.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicianDto {

    public String bio;
    private List<Long> instrumentIdList = new ArrayList<>();
    private boolean publicCalendar;
}
