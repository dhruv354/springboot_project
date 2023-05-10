package com.example.demo.service;
import com.example.demo.MyExceptions.CartExceptions.CartAlreadyExistsException;
import com.example.demo.MyExceptions.CustomerExceptions.CustomerNotFoundException;
import com.example.demo.MyExceptions.ProductExceptions.ProductNotFoundException;
import com.example.demo.dao.CartDAO;
import com.example.demo.dao.CustomerDAO;
import com.example.demo.dao.ProductCartDAO;
import com.example.demo.dao.ProductDAO;
import com.example.demo.dto.CartDTO;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.ProductCart;
import com.example.demo.model.Products;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    Logger logger = LoggerFactory.getLogger(CartService.class);
    @Autowired
    private CartDAO cartDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductCartDAO productCartDAO;

    public Cart addProduct(CartDTO cartDTO) throws CustomerNotFoundException, ProductNotFoundException, CartAlreadyExistsException {
        Cart newCart = new Cart();
        logger.info("inside addProduct function");

        logger.info("checking customer with id");
        int user_id = cartDTO.getUser_id();
        Optional<Customer> optionalCustomer = customerDAO.findById(user_id);
        if(!optionalCustomer.isPresent()) {
            throw new CustomerNotFoundException("User with this id does not exist");
        }

        //check if cart with this user already exists

        logger.info("checking product with id");
        Optional<Products> optionalProduct = productDAO.findById(cartDTO.getProduct_id());
        if(!optionalProduct.isPresent()) {
            throw new ProductNotFoundException("Product with this id does not exist");
        }

        List<ProductCart> productCartArr = new ArrayList<>();
        Cart optionalCart = cartDAO.findByCustomer(optionalCustomer.get());
        ProductCart productCart = new ProductCart();
        productCart.setProduct(optionalProduct.get());
        productCart.setQuantity(cartDTO.getQuantity());
        productCartArr.add(productCart);

        if(optionalCart != null){
            //check if product cart exists
            List<ProductCart> tmp = optionalCart.getProduct();

            for(int i = 0;i < tmp.size();i++){
                Products product = tmp.get(i).getProduct();
                if(product.getId() == cartDTO.getProduct_id()){
                    throw new CartAlreadyExistsException("cart with this user and product already exists instead do a put request");
                }
            }
            tmp.add(productCart);
            optionalCart.setProduct(tmp);
            return cartDAO.save(optionalCart);
        }

        //check if any cart with this User and Product
        //then just update the quantity of that cart
        logger.info("setting fields in Cart Entity");

        List<Cart> cartArr = cartDAO.findAll();
        newCart.setProduct(productCartArr);
        newCart.setCustomer(optionalCustomer.get());
        return cartDAO.save(newCart);
    }

    public Cart getCart(int userId) throws CustomerNotFoundException {
        List<Cart> output = new ArrayList<>();
        Optional<Customer> optionalCustomer = customerDAO.findById(userId);
        if(!optionalCustomer.isPresent()){
            throw new CustomerNotFoundException("user with id does not exist");
        }
        logger.info("user with id exists");
        List<Cart> customerCart = cartDAO.findAll();
        return cartDAO.findByCustomer(optionalCustomer.get());
    }

    public Cart updateCart(int cartId, int quantity, int product_id) throws CustomerNotFoundException, ProductNotFoundException {
        Optional<Cart> optionalCart = cartDAO.findById(cartId);
        if(!optionalCart.isPresent()){
            throw new CustomerNotFoundException("cart with this id does not exist");
        }
        List<ProductCart> tmp =  optionalCart.get().getProduct();
        ProductCart updatedProductCart = new ProductCart();
        Boolean flag = false;
        for(int i = 0;i < tmp.size();i++){
            if(tmp.get(i).getProduct().getId() == product_id){
                updatedProductCart.setQuantity(quantity);
                updatedProductCart.setProduct(tmp.get(i).getProduct());
                flag = true;
                tmp.set(i, updatedProductCart);
                break;
            }
        }
        if(!flag) throw new ProductNotFoundException("product with this id does not exist");

        optionalCart.get().setProduct(tmp);
        return cartDAO.save(optionalCart.get());
    }

    public void deleteCart(int cart_id){
        cartDAO.deleteById(cart_id);
    }
}
