package ua.com.footballgamble.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "testProductRepository")

public class TestProductRepository {
	public static final Logger logger = LoggerFactory.getLogger(TestProductRepository.class);
	/*
	 * private List<TestProduct> products = new ArrayList<>();
	 * 
	 * public TestProductRepository() { TestProduct product = new TestProduct(1l,
	 * "TestProduct 1", new BigDecimal(0.25));
	 * 
	 * products.add(product); }
	 * 
	 * public List<TestProduct> getProducts() { return products; }
	 * 
	 * public void setProducts(List<TestProduct> products) { this.products =
	 * products; }
	 * 
	 * public void save(TestProduct product) { products.add(product); }
	 */

}
