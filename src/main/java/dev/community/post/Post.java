package dev.community.post;

import dev.community.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

	@Column(name = "title")
	@NotEmpty
	private String title;

	@Column(name = "content")
	@NotEmpty
	private String content;

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

}
