package com.example.crud.config;

import com.example.crud.post.Post;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<Post, String> {
}
