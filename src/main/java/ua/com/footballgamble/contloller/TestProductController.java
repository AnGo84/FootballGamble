package ua.com.footballgamble.contloller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Scope(value = "session")
//@Component(value = "testProductController")
@Named(value = "testProductController")
@ViewScoped
//@Join(path = "/product", to = "/pages/test_product_form.jsf")
public class TestProductController {
	public static final Logger logger = LoggerFactory.getLogger(TestProductController.class);
	/*
	 * private TestProduct product = new TestProduct();
	 * 
	 * @Autowired private TestProductRepository productRepository;
	 * 
	 * public String save() { logger.info("Save product: " + product);
	 * productRepository.save(product); product = new TestProduct(); return
	 * "/pages/test_product_list_form.xhtml?faces-redirect=true"; }
	 * 
	 * public TestProduct getProduct() { logger.info("Get Product: " + product);
	 * return product; }
	 * 
	 * public void setProduct(TestProduct product) { logger.info("Set Product: " +
	 * product); this.product = product; }
	 */

}
