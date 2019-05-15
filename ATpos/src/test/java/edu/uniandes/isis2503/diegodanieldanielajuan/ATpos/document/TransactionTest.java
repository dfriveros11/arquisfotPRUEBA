package edu.uniandes.isis2503.diegodanieldanielajuan.ATpos.document;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

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
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Transaction;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.TransactionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {ATposApplication.class})
public class TransactionTest {
	
	@Autowired
	private TransactionRepository transactionRepositoryTest;
	

	@Before
	public void setUp() throws Exception {
		Transaction transaction= new Transaction(1, 44000);
		transactionRepositoryTest.insert(transaction);
		List<Product> products = new LinkedList<Product>();
		ProductPromotion productPromotion = new ProductPromotion(25, 0.2);
		Product productY = new Product(27, 35000, productPromotion);
		Product productX = new Product(12, 20000, productPromotion);
		products.add(productX);
		products.add(productY);
		transaction.setProducts(products);
		
		transactionRepositoryTest.insert(transaction);
		Transaction transactionTest = transactionRepositoryTest.findByTransactionId(1);
		transactionRepositoryTest.save(transactionTest);
		
	}

	@After
	public void tearDown() throws Exception {
		transactionRepositoryTest.deleteAll();
	}
	
	@Test
	public void setUpVerification() {
		
		Transaction transaction = transactionRepositoryTest.findByTransactionId(1);
		
		assertNotNull(transaction);
		

		assertEquals(1, transaction.getTransactionId(), 0.00);
		assertEquals(44000, transaction.getValue(), 0.00);
		
		List<Product> productTest = transaction.getProducts();
		Product productX = transactionRepositoryTest.findByTransactionId(1).getProducts().get(0);
		Product productY = transactionRepositoryTest.findByTransactionId(1).getProducts().get(1);
		
		assertNotNull(productX);
		assertNotNull(productY);
		
		assertEquals(productTest.get(0).getProductId(), productX.getProductId(), 0.00);
		assertEquals(productTest.get(0).getPrice(), productX.getPrice(), 0.00);
		
		assertEquals(productTest.get(1).getProductId(), productY.getProductId(), 0.00);
		assertEquals(productTest.get(1).getProductId(), productY.getProductId(), 0.00);
		
	}
	

	@Test
	public void delete() {
		Transaction transaction = transactionRepositoryTest.findByTransactionId(1);
		transactionRepositoryTest.delete(transaction);
		assertNull(transactionRepositoryTest.findByTransactionId(1));
	}
	
}
