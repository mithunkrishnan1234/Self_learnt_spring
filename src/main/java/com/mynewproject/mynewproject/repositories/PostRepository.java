package com.mynewproject.mynewproject.repositories;

import com.mynewproject.mynewproject.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {

}
