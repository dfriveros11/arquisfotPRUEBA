package edu.uniandes.isis2503.diegodanieldanielajuan.ATpos.document;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.ATposApplication;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Product;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {ATposApplication.class})
public class ProductTest {

	@Autowired
	private ProductRepository productRepositoryTest;
	
	@Before
	public void setUp() throws Exception {
		Product product1 = new Product(1, 10.50, null);
		productRepositoryTest.insert(product1);
	}

	@After
	public void tearDown() throws Exception {
		productRepositoryTest.deleteAll();
	}
	
	@Test
	public void find() {
		Product product = productRepositoryTest.findByProductId(1);
		assertNotNull(product);
		assertEquals(1, product.getProductId());
		assertEquals(new Double(10.50), product.getPrice(), 0.00);
		assertNull(productRepositoryTest.findByProductId(2));
	}
	
	@Test
	public void update() {
		Product product = productRepositoryTest.findByProductId(1);
		product.setPrice(9.00);
		productRepositoryTest.save(product);
		Product productVerify = productRepositoryTest.findByProductId(1);
		assertEquals(new Double(9.00), productVerify.getPrice(), 0.00);
	}
	
	@Test
	public void delete() {
		Product product = productRepositoryTest.findByProductId(1);
		productRepositoryTest.delete(product);
		assertNull(productRepositoryTest.findByProductId(1));
	}

}
