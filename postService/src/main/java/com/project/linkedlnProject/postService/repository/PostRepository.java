package com.project.linkedlnProject.postService.repository;

import com.project.linkedlnProject.postService.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
