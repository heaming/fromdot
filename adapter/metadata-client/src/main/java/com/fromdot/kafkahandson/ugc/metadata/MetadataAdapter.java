package com.fromdot.kafkahandson.ugc.metadata;

import com.fromdot.kafkahandson.ugc.port.MetadataPort;
import com.fromdot.kafkahandson.ugc.metadata.MetadataClient.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MetadataAdapter implements MetadataPort {

    private final MetadataClient metadataClient;

    @Override
    public String getCategoryNameByCategoryId(Long categoryId) {
        CategoryResponse response = metadataClient.getCategoryById(categoryId);

        if(response == null) return null;
        return response.getName();
    }

    @Override
    public String getUserNameByUserId(Long userId) {
        MetadataClient.UserResponse response = metadataClient.getUserById(userId);

        if(response == null) return null;
        return response.getName();
    }

    @Override
    public List<Long> listFollowerIdsByUserId(Long userId) {
        return metadataClient.getFollowerIdsByUserId(userId);
    }
}
