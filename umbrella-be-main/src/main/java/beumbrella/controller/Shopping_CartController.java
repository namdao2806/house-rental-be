package beumbrella.controller;

import beumbrella.model.CartItem;
import beumbrella.model.Product;
import beumbrella.model.User;
import beumbrella.service.impl.CartServiceImpl;
import beumbrella.service.impl.ProductServiceImpl;
import beumbrella.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@CrossOrigin("*")
@RequestMapping("/shopping_carts")

public class Shopping_CartController {
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<Iterable<CartItem>> findAll() {
        Iterable<CartItem> result = cartService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping({"/cart/{id}"})
    public ResponseEntity<Optional<CartItem>> findCartItem(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CartItem> addToShoppingCart(@RequestBody CartItem cartItem) {
        UUID uuid = UUID.randomUUID();
        String billId = uuid.toString();
        User currentUser = userService.getCurrentUser();
        cartItem.setUser(currentUser);
        Optional<Product> product = productService.findById(cartItem.getProduct().getId());
        Optional<User> shopId = userService.findById(cartItem.getProduct().getUser().getId());
        cartItem.setShopId(shopId.get());
        List<CartItem> items = cartService.findAllCartByShopIdAndCustomerId(cartItem.getShopId().getId(), cartItem.getUser().getId());
        if (items != null && items.size() > 0) {
            billId = items.get(0).getBillId();
            cartItem.setBillId(billId);
            for (CartItem item : items) {
                if (item.getProduct().getId().equals(cartItem.getProduct().getId())) {
                    if (item.getQuantity() < item.getProduct().getQuantity()) {
                        item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                        item.getProduct().setQuantity(item.getProduct().getQuantity() - cartItem.getQuantity());
                        cartItem.setDate(LocalDateTime.now());
                        cartItem.setStatus(0);
                        cartService.save(item);
                        return new ResponseEntity<>(item, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                }
            }
        } else {
            cartItem.setBillId(billId);
            for (CartItem item : items) {
                if (item.getProduct().getId().equals(cartItem.getProduct().getId())) {
                    if (item.getQuantity() < item.getProduct().getQuantity()) {
                        item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                        item.getProduct().setQuantity(item.getProduct().getQuantity() - cartItem.getQuantity());
                        cartItem.setDate(LocalDateTime.now());
                        cartItem.setStatus(0);
                        cartService.save(item);
                        return new ResponseEntity<>(item, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                }
            }
        }
        product.get().setQuantity(product.get().getQuantity() - cartItem.getQuantity());
        cartItem.setDate(LocalDateTime.now());
        cartItem.setStatus(0);
        cartService.save(cartItem);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }



    @PutMapping({"/checkout/{userId}"})
    public ResponseEntity<Boolean> checkout(@PathVariable Long userId) {
        return new ResponseEntity<>(cartService.checkout(userId), HttpStatus.OK);
    }

    @PutMapping({"/cart/{id}"})
    public ResponseEntity<CartItem> updateShoppingCart(@PathVariable Long id, @RequestBody CartItem cartItem) {
        Optional<CartItem> cartItemOptional = cartService.findById(id);
        if (!cartItemOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartItem.setId(cartItemOptional.get().getId());
        cartItemOptional.get().getProduct().setQuantity(cartItemOptional.get().getProduct().getQuantity() - (cartItem.getQuantity() - cartItemOptional.get().getQuantity()));

        cartService.save(cartItem);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping({"/cart/{id}"})
    public ResponseEntity<CartItem> deleteShoppingCart(@PathVariable Long id) {
        Optional<CartItem> cartItemOptional = cartService.findById(id);
        cartItemOptional.get().getProduct().setQuantity(cartItemOptional.get().getProduct().getQuantity() + cartItemOptional.get().getQuantity());
        cartService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-all-cart-by-customer-id/{userId}")
    public ResponseEntity<Iterable<CartItem>> findAllCartByCustomerId(@PathVariable Long userId) {
        return new ResponseEntity<>(cartService.findAllCartByCustomerId(userId), HttpStatus.OK);
    }

    @GetMapping("/find-bill-by-status-equals-zero/{userId}")
    public ResponseEntity<Iterable<CartItem>> findBillsByStatusEqualsZero(@PathVariable Long userId) {
        return new ResponseEntity<>(cartService.findBillStatusEqualsZero(userId), HttpStatus.OK);
    }

    @GetMapping("/find-bill-by-status-equals-one/{userId}")
    public ResponseEntity<Iterable<CartItem>> findBillsByStatusEqualsOne(@PathVariable Long userId) {
        return new ResponseEntity<>(cartService.findBillStatusEqualsOne(userId), HttpStatus.OK);
    }

    @GetMapping("/find-bill-by-status-equals-two/{userId}")
    public ResponseEntity<Iterable<CartItem>> findBillsByStatusEqualsTwo(@PathVariable Long userId) {
        return new ResponseEntity<>(cartService.findBillStatusEqualsTwo(userId), HttpStatus.OK);
    }

    @GetMapping("/find-bill-by-status-equals-three/{userId}")
    public ResponseEntity<Iterable<CartItem>> findBillsByStatusEqualsThree(@PathVariable Long userId) {
        return new ResponseEntity<>(cartService.findBillStatusEqualsThree(userId), HttpStatus.OK);
    }

    @GetMapping("/find-all-carts-by-userId/{id}")
    public ResponseEntity<Iterable<CartItem>> findAllCartItem(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.findAllCartByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/find-all-carts-by-ownerId/{id}")
    public ResponseEntity<Iterable<CartItem>> findAllCartItemByOwnerId(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.findAllCartByProductAndUserId(id), HttpStatus.OK);
    }

    @GetMapping("/find-cart-by-billId")
    public ResponseEntity<Iterable<CartItem>> findCartItemByCartId(@RequestParam(value = "billId") String billId) {
        return new ResponseEntity<>(cartService.findDetailCart(billId), HttpStatus.OK);
    }

    @PutMapping("/accept-bill-by-shop/{id}")
    public ResponseEntity<CartItem> acceptBill(@PathVariable Long id,@RequestBody CartItem cartItem) {
        Optional<CartItem> cartItemOptional = cartService.findById(id);
        if (!cartItemOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartItem.setStatus(2);
        cartService.save(cartItem);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/delete-bill-by-shop/{id}")
    public ResponseEntity<CartItem> deleteBill(@PathVariable Long id,@RequestBody CartItem cartItem) {
        Optional<CartItem> cartItemOptional = cartService.findById(id);
        if (!cartItemOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cartItemOptional.get().getProduct().setQuantity(cartItemOptional.get().getProduct().getQuantity() + cartItemOptional.get().getQuantity());
        cartItem.setStatus(3);
        cartService.save(cartItem);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }
}
