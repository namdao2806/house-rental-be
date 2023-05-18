package beumbrella.service.impl;

import beumbrella.model.Comment;
import beumbrella.model.Product;
import beumbrella.repository.CommentRepository;
import beumbrella.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository ;
    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);

    }
    public Comment add(Comment comment) {
        return commentRepository.save(comment);

    }



    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);

    }

    @Override
    public Iterable<Product> findNewProduct() {
        return commentRepository.findNewProduct();
    }

    @Override
    public Iterable<Comment> findNewComment() {
        return commentRepository.findNewComment();
    }

    @Override
    public Iterable<Comment> findCommentByProductId(Long id) {
        return commentRepository.findCommentByProductId(id);
    }
}
