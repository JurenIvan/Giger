package hr.fer.zemris.opp.giger.domain;

import hr.fer.zemris.opp.giger.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SystemPerson implements UserDetails {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	@Email
	@NotNull
	@Column(unique = true)
	private String email;
	private Boolean verified;
	private Boolean locked;
	@NotNull
	private String passwordHash;

	@ElementCollection(fetch = EAGER)
	private List<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(Role::getGrantedAuthority).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return passwordHash;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void addRole(Role role) {
		if (this.roles == null) this.roles = new ArrayList<>();
		this.roles.add(role);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SystemPerson)) return false;
		SystemPerson systemPerson = (SystemPerson) o;
		return Objects.equals(id, systemPerson.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
