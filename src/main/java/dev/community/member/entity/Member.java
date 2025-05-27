package dev.community.member.entity;

import dev.community.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
@Entity
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false)
	private String password;


	@Builder
	public Member(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public void updateName(String newName) {
		this.name = newName;
	}

	public boolean isPasswordMatch(String inputPassword, PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(inputPassword, this.password);
	}

	public void encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(this.password);
	}


}
