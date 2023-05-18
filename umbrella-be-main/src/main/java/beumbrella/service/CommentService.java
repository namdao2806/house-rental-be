package beumbrella.service;
import beumbrella.model.Comment;
import beumbrella.model.Product;
import org.springframework.data.repository.query.Param;

public interface CommentService extends GeneralService<Comment>{

    Iterable<Product> findNewProduct();
    Iterable<Comment> findNewComment();

    Iterable<Comment> findCommentByProductId( Long id);

}
