package com.fromdot.kafkahandson.ugc.controller;

import com.fromdot.kafkahandson.ugc.PostSearchUsecase;
import com.fromdot.kafkahandson.ugc.SubscribingPostListUsecase;
import com.fromdot.kafkahandson.ugc.model.PostInListDto;
import com.fromdot.kafkahandson.ugc.post.model.ResolvedPost;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/list")
public class PostListController {

    private final SubscribingPostListUsecase subscribingPostListUsercase;
    private final PostSearchUsecase postSearchUsecase;

    @GetMapping("/inbox/{userId}")
    ResponseEntity<List<PostInListDto>> listSubscribingPosts(@PathVariable Long userId,
                                                             @RequestParam(name = "page", defaultValue = "0", required = false) int page) {

        List<ResolvedPost> subscribingInboxPosts = subscribingPostListUsercase.listSubscribingInboxPosts(
                new SubscribingPostListUsecase.Request(page, userId)
        );

        return ResponseEntity.ok().body(subscribingInboxPosts.stream().map(this::toDto).toList());
    }

    @GetMapping("/search")
    ResponseEntity<List<PostInListDto>> searchPosts(
            @RequestParam("keyword") String keyword,
            @RequestParam("page") int page
            ) {

        List<ResolvedPost> searchedPosts = postSearchUsecase.getSearchResultByKeyword(keyword, page);
        return ResponseEntity.ok().body(searchedPosts.stream().map(this::toDto).toList());
    }

    private PostInListDto toDto(ResolvedPost resolvedPost) {
        return new PostInListDto(
                resolvedPost.getId(),
                resolvedPost.getTitle(),
                resolvedPost.getUserName(),
                resolvedPost.getCreatedAt()
        );
    }

}
