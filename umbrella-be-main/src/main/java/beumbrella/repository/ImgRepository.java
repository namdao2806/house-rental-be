package beumbrella.repository;

import beumbrella.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepository extends JpaRepository<Image,Long> {
    @Query(value = "select * from image i join products p on p.id = i.product_id\n" +
            "where p.id = :id", nativeQuery = true)
    Iterable<Image> findAllByProductId(@Param("id") Long id);
}
