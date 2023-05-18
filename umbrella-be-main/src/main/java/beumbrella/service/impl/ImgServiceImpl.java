package beumbrella.service.impl;
import beumbrella.model.Image;
import beumbrella.repository.ImgRepository;
import beumbrella.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ImgServiceImpl implements ImgService {
    @Autowired
    ImgRepository imgRepository;


    @Override
    public Iterable<Image> findAll() {
        return imgRepository.findAll();
    }

    @Override
    public Optional<Image> findById(Long id) {
        return imgRepository.findById(id);
    }

    @Override
    public void save(Image image) {
        imgRepository.save(image);

    }

    @Override
    public void remove(Long id) {
        imgRepository.deleteById(id);

    }

    @Override
    public Iterable<Image> findAllImg() {
        return imgRepository.findAll();
    }

    @Override
    public Page<Image> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Iterable<Image> findAllByProductId(Long id) {
        return imgRepository.findAllByProductId(id);
    }
}
