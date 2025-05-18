package dev.community;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Board extends BaseEntity {

	@Column(nullable = false)
	private String title;

	@Column(columnDefinition = "Text", nullable = false)
	private String content;


	@Builder
	public Board(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
