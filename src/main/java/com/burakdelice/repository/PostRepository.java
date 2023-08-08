package com.burakdelice.repository;

import com.burakdelice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long id);
    List<Post> findPostsByCategoryId(Long id);
    @Query("select p from Post p ORDER BY p.publishedAt desc")
    List<Post> postOrderByPublish();
    @Query("select p from Post p where p.categoryId=:id")
    List<Post> postOrderByCategoryId(Long id);
    @Query("select p from Post p where p.userId=:id")
    List<Post> postOrderByUserId(Long id);
}
