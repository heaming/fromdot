package com.fromdot.kafkahandson.ugc.controller;

import com.fromdot.kafkahandson.ugc.PostCreateUsecase;
import com.fromdot.kafkahandson.ugc.PostDeleteUsecase;
import com.fromdot.kafkahandson.ugc.PostReadUsecase;
import com.fromdot.kafkahandson.ugc.PostUpdateUsecase;
import com.fromdot.kafkahandson.ugc.model.PostCreateRequest;
import com.fromdot.kafkahandson.ugc.model.PostDetailDto;
import com.fromdot.kafkahandson.ugc.model.PostDto;
import com.fromdot.kafkahandson.ugc.model.PostUpdateRequest;
import com.fromdot.kafkahandson.ugc.post.model.Post;
import com.fromdot.kafkahandson.ugc.post.model.ResolvedPost;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostCreateUsecase postCreateUsecase;
    private final PostUpdateUsecase postUpdateUsecase;
    private final PostReadUsecase postReadUsecase;
    private final PostDeleteUsecase postDeleteUsecase;

    @PostMapping
    ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequest request) {

        Post post = postCreateUsecase.create(
                new PostCreateUsecase.Request(
                        request.getUserId(),
                        request.getTitle(),
                        request.getContent(),
                        request.getCategoryId()
                )
        );

        return ResponseEntity.ok().body(toDto(post));
    }

    @PutMapping("/{postId}")
    ResponseEntity<PostDto> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest request) {

        Post post = postUpdateUsecase.update(
                new PostUpdateUsecase.Request(
                        postId,
                        request.getTitle(),
                        request.getContent(),
                        request.getCategoryId()
                )
        );

        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(toDto(post));
    }

    @GetMapping("/{postId}/detail")
    ResponseEntity<PostDetailDto> readPostDetail(@PathVariable Long postId) {
        ResolvedPost resolvedPost = postReadUsecase.getById(postId);

        if(resolvedPost == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(toDto(resolvedPost));
    }

    @DeleteMapping("/{postId}")
    ResponseEntity<PostDto> deletePost(@PathVariable Long postId) {
        Post post = postDeleteUsecase.delete(new PostDeleteUsecase.Request(postId));

        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(toDto(post));
    }


    private PostDto toDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUserId(),
                post.getCategoryId(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getDeletedAt()
        );
    }

    private PostDetailDto toDto(ResolvedPost resolvedPost) {
        return new PostDetailDto(
                resolvedPost.getId(),
                resolvedPost.getTitle(),
                resolvedPost.getContent(),
                resolvedPost.getUserName(),
                resolvedPost.getCategoryName(),
                resolvedPost.getCreatedAt(),
                resolvedPost.isUpdated()
        );
    }


}
