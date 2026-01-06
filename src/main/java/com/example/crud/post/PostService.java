package com.example.crud.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PostService {
    private final ObjectMapper objectMapper;
    private final PostRepository postRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public PostService(ObjectMapper objectMapper, PostRepository postRepository, RedisTemplate<String, Object> redisTemplate) {
        this.objectMapper = objectMapper;
        this.postRepository = postRepository;
        this.redisTemplate = redisTemplate;
    }
    // builder쓴이유 : 보기 직관적이기 때문
    @Transactional
    public Long postCreate(PostRequest request){
        Post  newPost = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(request.getAuthor())
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .viewCount(0L)
                .build();
        return  postRepository.save(newPost).getId();
    }


    @Transactional
    public Long postUpdate(Long id , PostRequest request){
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다."));
        post.update(request.getTitle(),request.getContent());
        return id;

    }

    @Transactional(readOnly = true)
    public Page<PostResponse> postFindAll(Pageable pageable) {
        // 1. DB에서 페이징된 엔티티들을 가져옴
        Page<Post> posts = postRepository.findAll(pageable);
        // 2. Page 안의 엔티티들을 DTO로 변환
        return posts.map(PostResponse::new);
    }

    @Transactional
    public PostResponse postFindById(Long id) {
        // Redis 조회수 증가
        String viewKey = "post:" + id;
        Long viewCount = redisTemplate.opsForValue().increment(viewKey, 1);

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        PostResponse response = new PostResponse(post);
        response.setViewCount(viewCount);

        return response;
    }

    @Transactional
    public void  postDelete(Long id){
        postRepository.deleteById(id);
    }

    //수동 JSON 변환(게시글 저장)
    public void savePost(Post post) throws JsonProcessingException {
        String jsonPost = objectMapper.writeValueAsString(post); // 객체를 담아서
        redisTemplate.opsForValue().set("posts"+ post. getId(), jsonPost); //ID N의 post를 redisTemplate로 보냄
    }
}
