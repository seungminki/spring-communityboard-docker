package dev.community.post;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class Posts {

	private List<Post> posts;

	@XmlElement
	public List<Post> getPostList() {
		if (posts == null) {
			posts = new ArrayList<>();
		}
		return posts;
	}

}
