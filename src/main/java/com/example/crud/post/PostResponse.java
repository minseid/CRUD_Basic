package com.example.crud.post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;        // 타입을 Post 엔티티와 맞춰주는 것이 좋습니다 (Long)
    private String author;
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Long viewCount;

    // 생성자 내부에서 엔티티의 값을 DTO로 옮겨주어야 합니다.
    public PostResponse(Post post) {
        this.id = post.getId();
        this.author = post.getAuthor();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.created = post.getCreated();
        this.updated = post.getUpdated();
        this.viewCount = post.getViewCount();
    }
}