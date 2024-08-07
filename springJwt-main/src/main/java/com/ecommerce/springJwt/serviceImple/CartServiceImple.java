package com.ecommerce.springJwt.serviceImple;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.springJwt.dto.AddProductInDto;
import com.ecommerce.springJwt.dto.CartItemDto;
import com.ecommerce.springJwt.dto.OrderDto;
import com.ecommerce.springJwt.dto.PlacedOrderDto;
import com.ecommerce.springJwt.enums.OrderStatus;
import com.ecommerce.springJwt.model.CartItem;
import com.ecommerce.springJwt.model.Order;
import com.ecommerce.springJwt.model.Product;
import com.ecommerce.springJwt.model.User;
import com.ecommerce.springJwt.repository.CartItemRepository;
import com.ecommerce.springJwt.repository.OrderRepository;
import com.ecommerce.springJwt.repository.ProductRepo;
import com.ecommerce.springJwt.repository.UserRepository;
import com.ecommerce.springJwt.service.CartService;

@Service
public class CartServiceImple implements CartService {

    private CartItemRepository cartItemRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private ProductRepo productRepo;

    public CartServiceImple(CartItemRepository cartItemRepository, OrderRepository orderRepository,
            UserRepository userRepository, ProductRepo productRepo) {
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepo = productRepo;
    }

    @Override
    public ResponseEntity<?> addProductToCart(AddProductInDto addProductInDto) {
        Order activOrder = orderRepository.findByUserIdAndOrderStatus(addProductInDto.getUserId(),
                OrderStatus.BEKLEMEDE);
        if (activOrder == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Active order not found");
        }

        Optional<CartItem> optionalCartItem = cartItemRepository.findByOrderIdAndProductIdAndUserId(activOrder.getId(),
                addProductInDto.getProductId(), addProductInDto.getUserId());

        if (optionalCartItem.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product already in cart");
        } else {
            Optional<Product> optionalProduct = productRepo.findById(addProductInDto.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInDto.getUserId());

            if (optionalUser.isPresent() && optionalProduct.isPresent()) {
                CartItem cartItem = new CartItem();
                cartItem.setProduct(optionalProduct.get());
                cartItem.setPrice(optionalProduct.get().getPrice());
                cartItem.setQuantity(1L);
                Long discount = optionalProduct.get().getDiscount(); // Ürüne özgü indirim miktarı
                Long discountedPrice = optionalProduct.get().getPrice() - discount; // İndirimli fiyat
                cartItem.setDiscount(discount * cartItem.getQuantity());
                cartItem.setDiscountedPrice(discountedPrice * cartItem.getQuantity());
                cartItem.setUser(optionalUser.get());
                cartItem.setOrder(activOrder);

                CartItem updatedCartItem = cartItemRepository.save(cartItem);

                activOrder.setTotalAmount(activOrder.getTotalAmount() + discountedPrice);
                activOrder.setAmount(activOrder.getAmount() + cartItem.getPrice());
                activOrder.setDiscount(discount);

                activOrder.getCartItems().add(cartItem);
                orderRepository.save(activOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(updatedCartItem);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product Not Found");
            }
        }
    }

    @Override
    public OrderDto getCartByUserId(Long userId) {
        Order activOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.BEKLEMEDE);
        List<CartItemDto> cartItemDtoList = activOrder.getCartItems().stream().map(CartItem::getCartItemDto)
                .collect(Collectors.toList());
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activOrder.getAmount());
        orderDto.setId(activOrder.getId());
        orderDto.setOrderStatus(activOrder.getOrderStatus());
        orderDto.setDiscount(activOrder.getDiscount());
        orderDto.setTotalAmount(activOrder.getTotalAmount());
        orderDto.setCartItems(cartItemDtoList);

        return orderDto;
    }

    @Override
    public OrderDto increasedProductQuantity(AddProductInDto addProductInDto) {
        Order activOrder = orderRepository.findByUserIdAndOrderStatus(addProductInDto.getUserId(),
                OrderStatus.BEKLEMEDE);

        Optional<Product> optionalProduct = productRepo.findById(addProductInDto.getProductId());

        Optional<CartItem> optionalCartItem = cartItemRepository.findByOrderIdAndProductIdAndUserId(activOrder.getId(),
                addProductInDto.getProductId(), addProductInDto.getUserId());

        if (optionalProduct.isPresent() && optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            Product product = optionalProduct.get();
            Long discount = optionalProduct.get().getDiscount(); // Ürüne özgü indirim miktarı
            Long discountedPrice = optionalProduct.get().getPrice() - discount; // İndirimli fiyat
            cartItem.setDiscount(discount + discount * cartItem.getQuantity());
            cartItem.setDiscountedPrice(discountedPrice + discountedPrice * cartItem.getQuantity());

            activOrder.setAmount(activOrder.getAmount() + cartItem.getPrice());
            activOrder.setTotalAmount(activOrder.getTotalAmount() + discountedPrice);
            activOrder.setDiscount(discount + discount * cartItem.getQuantity());

            cartItem.setQuantity(cartItem.getQuantity() + 1);

            cartItemRepository.save(cartItem);
            orderRepository.save(activOrder);
            return activOrder.getOrderDto();

        }
        return null;

    }

    @Override
    public OrderDto decreasedProductQuantity(AddProductInDto addProductInDto) {
        try {
            // Kullanıcının aktif siparişini bul
            Order activOrder = orderRepository.findByUserIdAndOrderStatus(addProductInDto.getUserId(),
                    OrderStatus.BEKLEMEDE);
            if (activOrder == null) {
                throw new RuntimeException("Active order not found");
            }

            // Ürünü ve sepetteki ürünü bul
            Optional<Product> optionalProduct = productRepo.findById(addProductInDto.getProductId());
            Optional<CartItem> optionalCartItem = cartItemRepository.findByOrderIdAndProductIdAndUserId(
                    activOrder.getId(),
                    addProductInDto.getProductId(), addProductInDto.getUserId());

            if (optionalProduct.isPresent() && optionalCartItem.isPresent()) {
                CartItem cartItem = optionalCartItem.get();
                Product product = optionalProduct.get();

                // Quantity kontrolü
                if (cartItem.getQuantity() <= 1) {
                    return activOrder.getOrderDto(); // Minimum quantity'ye ulaşıldı, azaltma işlemi yapma
                }

                Long discount = product.getDiscount(); // Ürüne özgü indirim miktarı
                Long discountedPrice = product.getPrice() - discount; // İndirimli fiyat

                // Güncellenmiş değerler
                Long newDiscount = cartItem.getDiscount() - discount;
                Long newDiscountedPrice = cartItem.getDiscountedPrice() - discountedPrice;

                // Bilgi güncellemeleri
                cartItem.setDiscount(newDiscount);
                cartItem.setDiscountedPrice(newDiscountedPrice);
                cartItem.setQuantity(cartItem.getQuantity() - 1);

                activOrder.setAmount(activOrder.getAmount() - product.getPrice());
                activOrder.setTotalAmount(activOrder.getTotalAmount() - discountedPrice);
                activOrder.setDiscount(activOrder.getDiscount() - discount);

                // Değişiklikleri kaydet
                cartItemRepository.save(cartItem);
                orderRepository.save(activOrder);
                return activOrder.getOrderDto();
            } else {
                throw new RuntimeException("Product or CartItem not found");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Hatanın detaylarını konsola yazdır
            throw new RuntimeException("Error decreasing product quantity", e); // Hata mesajını daha detaylı hale getir
        }
    }

    public OrderDto placedOrder(PlacedOrderDto placedOrderDto) {
        Order activOrder = orderRepository.findByUserIdAndOrderStatus(placedOrderDto.getUserId(),
                OrderStatus.BEKLEMEDE);

        if (activOrder == null) {
            // Aktiv sipariş bulunamazsa uygun bir yanıt döndürün
            return null; // Veya uygun bir hata yanıtı döndürün
        }

        Optional<User> optionalUser = userRepository.findById(placedOrderDto.getUserId());
        if (optionalUser.isPresent()) {
            activOrder.setOrderDescription(placedOrderDto.getOrderDescription());
            activOrder.setAddress(placedOrderDto.getAddress());
            activOrder.setDate(new Date());
            activOrder.setOrderStatus(OrderStatus.SIPARIS_VERILDI);
            activOrder.setTrackingId(UUID.randomUUID());

            orderRepository.save(activOrder);

            return activOrder.getOrderDto();
        } else {
            // Kullanıcı bulunamazsa uygun bir yanıt döndürün
            return null; // Veya uygun bir hata yanıtı döndürün
        }

    }

    @Override
    public List<OrderDto> getMyPlacedOrder(Long UserId) {
        {
            List<OrderStatus> orderStatusList = List.of(OrderStatus.BEKLEMEDE, OrderStatus.SIPARIS_VERILDI,
                    OrderStatus.KARGOLANDI,
                    OrderStatus.TESLIM_EDILDI, OrderStatus.ONAYLANDI, OrderStatus.IPTAL_EDILDI);

            return orderRepository.findAllByUserIdAndOrderStatusIn(UserId, orderStatusList).stream()
                    .map(Order::getOrderDto)
                    .collect(Collectors.toList());
        }
    }
}