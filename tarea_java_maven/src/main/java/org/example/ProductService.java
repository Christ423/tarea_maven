import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private List<ProductEntity> products;

    public ProductService() {
        products = new ArrayList<>();
        // Inicializar algunos productos de ejemplo
        products.add(new ProductEntity("Laptop", "Electrónica", 1000.00, 50));
        products.add(new ProductEntity("Silla", "Muebles", 150.00, 30));
        products.add(new ProductEntity("Cámara", "Fotografía", 500.00, 15));
    }

    // Crear un producto
    public ProductEntity createProduct(ProductEntity product) {
        products.add(product);
        return product;
    }

    // Leer todos los productos
    public List<ProductEntity> getAllProducts() {
        return products;
    }

    // Leer un producto por UUID
    public Optional<ProductEntity> getProductById(UUID id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    // Actualizar un producto
    public Optional<ProductEntity> updateProduct(UUID id, ProductEntity updatedProduct) {
        Optional<ProductEntity> existingProduct = getProductById(id);
        existingProduct.ifPresent(product -> {
            product.setName(updatedProduct.getName());
            product.setCategory(updatedProduct.getCategory());
            product.setPrice(updatedProduct.getPrice());
            product.setStock(updatedProduct.getStock());
        });
        return existingProduct;
    }

    // Eliminar un producto
    public boolean deleteProduct(UUID id) {
        return products.removeIf(product -> product.getId().equals(id));
    }
}
