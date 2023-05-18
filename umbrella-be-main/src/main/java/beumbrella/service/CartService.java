package beumbrella.service;

import beumbrella.model.CartItem;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartService extends GeneralService<CartItem> {
    List<CartItem> findAllCartByUserId(Long id);

    boolean checkout(Long userId);

    Iterable<CartItem> findAllCartByProductAndUserId(Long id);

    Iterable<CartItem> findDetailCart(String billId);

    List<CartItem> findAllCartByShopIdAndCustomerId(Long shopId, Long customerId);

    Iterable<CartItem> findBillStatusEqualsZero(Long userId);

    Iterable<CartItem> findBillStatusEqualsOne(Long userId);

    Iterable<CartItem> findBillStatusEqualsTwo(Long userId);

    Iterable<CartItem> findBillStatusEqualsThree(Long userId);

    Iterable<CartItem> findAllCartByCustomerId(Long userId);

}
