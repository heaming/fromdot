package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostReadService implements PostReadUsecase {

    private final PostResolvingHelpService postResolvingHelpService;

    @Override
    public ResolvedPost getById(Long id) {
        return postResolvingHelpService.resolvedPostById(id);
    }
}
