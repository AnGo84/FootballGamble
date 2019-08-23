package ua.com.footballgamble.contloller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named(value = "testProductListController")
@ViewScoped
/*
 * @Scope(value = "session")
 * 
 * @Component(value = "testProductListController")
 * 
 * @ELBeanName(value = "testProductListController")
 * 
 * @Join(path = "/", to = "/pages/test_product_list_form.jsf")
 */
public class TestProductListController {
	public static final Logger logger = LoggerFactory.getLogger(TestProductListController.class);
	/*
	 * private TestProduct product = new TestProduct();
	 * 
	 * @Autowired private TestProductRepository productRepository;
	 * 
	 * private List<TestProduct> products;
	 */

	/*
	 * @Deferred
	 * 
	 * @RequestAction
	 * 
	 * @IgnorePostback
	 */
	/*
	 * @PostConstruct public void loadData() { // products =
	 * productRepository.findAll(); logger.info("Get products list size: " +
	 * productRepository.getProducts().size()); products =
	 * productRepository.getProducts(); }
	 * 
	 * public List<TestProduct> getProducts() { return products; }
	 */
}
