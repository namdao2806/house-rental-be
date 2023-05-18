package beumbrella.service;


import beumbrella.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ImgService extends GeneralService<Image> {
    Iterable<Image> findAllImg();
    Page<Image> findAll(Pageable pageable);
    Iterable<Image> findAllByProductId(Long id);
}
