package com.example.crud.post;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public Long postCreate(@RequestBody PostRequest postRequest) {
        return postService.postCreate(postRequest);
    }


    @PutMapping("/update{id}")
    public Long postUpdate(@RequestParam Long id,@RequestBody PostRequest postRequest) {
        return postService.postUpdate(id, postRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@RequestParam Long id) {
        postService.postDelete(id);
    }

    @GetMapping("/all")
    public Page<PostResponse> postFindAll(Pageable pageable) {
        return postService.postFindAll(pageable);

    }

    @GetMapping("/{id}")
    public PostResponse postFindOne(@RequestParam Long id) {
        return postService.postFindById(id);
    }


}
/**
 * 어노테이션	데이터 위치	주로 사용하는 경우	예시 URL
 * @PathVariable URL 경로 자체	리소스의 ID를 지정할 때	/api/posts/10
 * @RequestParam URL 뒤의 쿼리 스트링	검색, 필터링, 페이지 번호	/api/posts?page=1&sort=title
 * @RequestBody HTTP 본문 (Body)	JSON 객체 전체를 보낼 때	(URL에는 안 보임)
 */