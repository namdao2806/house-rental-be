package beumbrella.repository;

import beumbrella.model.Category;
import beumbrella.model.Comment;
import beumbrella.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select * from comments order by id desc limit 4", nativeQuery = true)
    Iterable<Product> findNewProduct();

    @Query(value = "select * from comments order by CURRENT_TIME() desc limit 3;", nativeQuery = true)
    Iterable<Comment> findNewComment();

    @Query(value= "select * from comments c join products p on c.product_id = p.id\n" +
            "join user_table u on c.user_id = u.id\n" +
            "where p.id = :id", nativeQuery = true)
    Iterable<Comment> findCommentByProductId(@Param("id") Long id);
}
