package dev.community.member;

import dev.community.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseEntity {


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


}
