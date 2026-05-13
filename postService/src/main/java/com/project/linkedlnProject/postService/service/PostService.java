package com.project.linkedlnProject.postService.service;

import com.project.linkedlnProject.postService.dto.PostCreateRequestDto;
import com.project.linkedlnProject.postService.dto.PostDto;
import com.project.linkedlnProject.postService.entity.Post;
import com.project.linkedlnProject.postService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }
}
