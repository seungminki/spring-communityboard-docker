package dev.community;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;


	@Builder
	public Board(Long id, String title, String content) {
		this.title = title;
		this.content = content;
	}
}
