package dev.community.user;

import dev.community.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Column(name = "name")
	@NotEmpty(message = "이름은 필수 항목입니다.")
	private String name;

	@Column(name = "email")
	@NotEmpty(message = "이메일은 필수 항목입니다.")
	@Email
	private String email;

	@Column(name = "password")
	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	private String password;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

}
