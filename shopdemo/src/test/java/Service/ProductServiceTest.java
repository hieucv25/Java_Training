package Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.shopdemo.model.Product;
import com.example.shopdemo.repository.ProductRepository;
import com.example.shopdemo.service.impl.ServiceProductImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ServiceProductImpl serviceProductImpl;


    @Test
    void whenGetAll() {
        List<Product> mockList = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            mockList.add(new Product("product_" + i++, new BigDecimal("5000"), 20, LocalDateTime.now()));
        }
        when(productRepository.findAll()).thenReturn(mockList);

        List<Product> listProduct = serviceProductImpl.findALl();

        assertThat(listProduct.size()).isEqualTo(mockList.size());

        verify(productRepository).findAll();

    }
}
