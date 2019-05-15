package edu.uniandes.isis2503.diegodanieldanielajuan.ATpos.document;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.hamcrest.number.OrderingComparison;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.ATposApplication;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Product;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.ProductPromotion;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.ProductPromotionRepository;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {ATposApplication.class})
public class ProductPromotionTest {
	
	@Autowired
	private ProductPromotionRepository productPromotionRepositoryTest;
	
	@Autowired
	private ProductRepository productRepositoryTest;

	@Before
	public void setUp() throws Exception {
		Product product1 = new Product(1, 10.50, null);
		productRepositoryTest.insert(product1);
		ProductPromotion productPromotion = new ProductPromotion(1, 1.00);
		List<Product> products = new LinkedList<Product>();
		products.add(product1);
		productPromotion.setProducts(products);
		productPromotionRepositoryTest.insert(productPromotion);
		Product product = productRepositoryTest.findByProductId(1);
		product.setProductPromotion(productPromotion);
		productRepositoryTest.save(product);
	}

	@After
	public void tearDown() throws Exception {
		productPromotionRepositoryTest.deleteAll();
		productRepositoryTest.deleteAll();
	}
	
	@Test
	public void setUpVerification() {
		ProductPromotion productPromotion = productPromotionRepositoryTest.findByProductPromotionId(1);
		assertNotNull(productPromotion);
		assertEquals(1.00, productPromotion.getPromotion(), 0.00);
		List<Product> productTest = productPromotion.getProducts();
		Product product = productRepositoryTest.findByProductId(productTest.get(0).getProductId());
		assertNotNull(product);
		assertEquals(productTest.get(0).getPrice(), product.getPrice(), 0.00);
	}
	
	
	@Test(timeout=100)
	public void cache() {
		long startFirstTime = System.currentTimeMillis();
		productPromotionRepositoryTest.findByProductPromotionId(1);
		long endFirstTime = System.currentTimeMillis();
		long timeFirstTime = endFirstTime  - startFirstTime;
		
		productPromotionRepositoryTest.findByProductPromotionId(1);
		productPromotionRepositoryTest.findByProductPromotionId(1);
		productPromotionRepositoryTest.findByProductPromotionId(1);
		
		long startTime = System.currentTimeMillis();
		productPromotionRepositoryTest.findByProductPromotionId(1);
		long endTime = System.currentTimeMillis();
		long timeTime = startTime - endTime;
		
		assertThat(timeFirstTime, OrderingComparison.greaterThan(timeTime));
		
	}
	
	@Test
	public void productEmbbed() {
		Product product = productRepositoryTest.findByProductId(1);
		ProductPromotion productPromotion = product.getProductPromotion();;
		assertEquals(1, productPromotion.getProductPromotionId());
		assertEquals(1.00, productPromotion.getPromotion(), 0.00);
		assertEquals(10.50, product.getPrice(), 0.00);
	}
	
	@Test
	public void update() {
		Product product2 = new Product(2, 1000, null);
		productRepositoryTest.insert(product2);
		List<Product> products = new LinkedList<Product>();
		products.add(product2);

		ProductPromotion productPromotion2 = new ProductPromotion(2, 10.00);
		productPromotion2.setProducts(products);
		productPromotionRepositoryTest.insert(productPromotion2);
		
		Product product3= new Product(3, 90.50, null);
		productRepositoryTest.insert(product3);
		List<Product> productsT = new LinkedList<Product>();
		productsT.add(product3);

		ProductPromotion productPromotion = productPromotionRepositoryTest.findByProductPromotionId(2);
		assertEquals(1, productPromotion.getProducts().size());
		productPromotion.setPromotion(50.00);
		productPromotion.setProducts(productsT);
		productPromotionRepositoryTest.save(productPromotion);
		
		ProductPromotion productPromotionVerify = productPromotionRepositoryTest.findByProductPromotionId(2);
		assertEquals(50.00, productPromotionVerify.getPromotion(), 0.00);
		
		Product productVerify = productPromotionVerify.getProducts().get(0);
		assertEquals(90.50, productVerify.getPrice(), 0.00);
	}
	
	@Test
	public void delete() {
		ProductPromotion productPromotion = productPromotionRepositoryTest.findByProductPromotionId(1);
		productPromotionRepositoryTest.delete(productPromotion);
		assertNull(productPromotionRepositoryTest.findByProductPromotionId(1));
	}
	
}
