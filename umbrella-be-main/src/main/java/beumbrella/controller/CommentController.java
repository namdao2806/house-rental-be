package beumbrella.controller;

import beumbrella.model.Comment;
import beumbrella.service.CommentService;
import beumbrella.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/comments")
public class    CommentController {
    @Autowired
    CommentServiceImpl commentService;

    @GetMapping
    public ResponseEntity<Iterable<Comment>> findAll() {
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Comment>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity add(@RequestBody Comment comment) {
        final Comment add = commentService.add(comment);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> edit(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> comment1 = commentService.findById(id);
        if (!comment1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comment.setId(comment1.get().getId());
        commentService.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id) {
        commentService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-new-comment")
    public ResponseEntity<Iterable<Comment>> findNewComment() {
        commentService.findNewComment();
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/find-comment-by-product-id/{id}")
    public ResponseEntity<Iterable<Comment>> findCommentByProductId(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.findCommentByProductId(id), HttpStatus.OK);
    }
}
