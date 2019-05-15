package edu.uniandes.isis2503.diegodanieldanielajuan.atpos.service;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Product;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.document.Transaction;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.ProductRepository;
import edu.uniandes.isis2503.diegodanieldanielajuan.atpos.repository.TransactionRepository;

@Service
public class TransactionService {

	/**
	 * Connection to the repository of transactions
	 */
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ProductRepository productRepository;

	private KeyGeneratorService keyGenerator = KeyGeneratorService.getInstance();

	private KeyPair keyPair = keyGenerator.getKeyPair();

	public String createTransaction(@RequestBody @Valid Transaction transaction) {
		try {
			Transaction transactionTest = transactionRepository.findByTransactionId(transaction.getTransactionId());
			if (transactionTest != null) {
				throw new DuplicateKeyException("The transaction's id exist");
			}
			transactionRepository.insert(transaction);
		} catch (Exception ex) {
			return "Error crseating the product: " + ex.toString();
		}

		try {
			String message = transaction.toString();
			String signature = this.createSignature("HMACSHA256", message.getBytes());
			message = message + ":SIGNATURE:" + signature;
			return message;
		} catch (Exception ex) {
			return "Error creating the message: " + ex.toString();
		}
	}

	private String createSignature(String algorithm, byte[] message) throws InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

		byte[] messageHash = getDigest("HMACSHA256", message);
		byte[] signature = asimetricCipher(messageHash, keyPair.getPrivate(), "RSA");
		return printByteArrayHexa(signature);

	}

	public static byte[] asimetricCipher(byte[] msg, Key key, String algoritmo) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher decifrador = Cipher.getInstance(algoritmo);
		decifrador.init(Cipher.ENCRYPT_MODE, key);
		return decifrador.doFinal(msg);
	}

	public static byte[] getDigest(String algorithm, byte[] buffer) {
		try {
			String alg = "";
			if (algorithm.equals("HMACMD5")) {
				alg = "MD5";
			} else if (algorithm.equals("HMACSHA1")) {
				alg = "SHA-1";
			} else {
				alg = "SHA-256";
			}
			MessageDigest digest = MessageDigest.getInstance(alg);
			digest.update(buffer);
			return digest.digest();
		} catch (Exception e) {
			return null;
		}
	}

	public static String printByteArrayHexa(byte[] byteArray) {
		String out = "";
		for (int i = 0; i < byteArray.length; i++) {
			if ((byteArray[i] & 0xff) <= 0xf) {
				out += "0";
			}
			out += Integer.toHexString(byteArray[i] & 0xff).toUpperCase();
		}

		return (out);
	}

	public Transaction updateTransaction(@RequestBody @Valid Transaction transaction) {
		try {

			Transaction oldtransaction = transactionRepository.findByTransactionId(transaction.getTransactionId());
			if (oldtransaction == null) {
				throw new Exception("The Transaction with id: " + transaction.getTransactionId()
						+ " doesn´t exist in our database");
			}
			oldtransaction.setValue(transaction.getValue());
			transactionRepository.save(oldtransaction);
			return oldtransaction;
		} catch (Exception ex) {
			ex.toString();
			return null;
		}
	}

	public Transaction getTransactionID(@PathVariable("id") int id) {
		try {
			Transaction transaction = transactionRepository.findByTransactionId(id);
			if (transaction == null) {
				throw new Exception("The Product with id: " + id + " doesn´t exist in our database");
			}
			return transaction;
		} catch (Exception ex) {
			return null;
		}
	}

	public List<Transaction> getTransactions() {
		try {
			List<Transaction> transactions = transactionRepository.findAll();
			return transactions;
		} catch (Exception ex) {
			return null;
		}
	}

	public String deleteTransaction(@PathVariable("id") int id) {
		try {
			Transaction transaction = transactionRepository.findByTransactionId(id);
			if (transaction == null) {
				throw new Exception("The Product with id: " + id + " doesn´t exist in our database");
			}
			transactionRepository.delete(transaction);
		} catch (Exception ex) {
			return "Error deleting the Product:" + ex.toString();
		}
		return "Transaction succesfully deleted with id : " + id;
	}

	// Relation with product

	public String createTransactionProduct(@PathVariable("id") int id, @RequestBody @Valid Product product) {
		try {
			Transaction transaction = null;
			Product newProduct = null;
			try {
				transaction = transactionRepository.findByTransactionId(id);

			} catch (Exception e) {
				// TODO: handle exception
				return "There is not a transaction with id : " + id;
			}

			if (product == null) {

				throw new Exception("The product is null");
			}
			try {

				newProduct = productRepository.findByProductId(product.getProductId());

			} catch (Exception e) {
				// TODO: handle exception
				return "The product with id: " + product.getProductId() + " doesn't exist";
			}

			List<Product> products = null;
			try {
				products = transaction.getProducts();

			} catch (Exception e) {
				// TODO: handle exception
				return "It is not possible to get products from transaction with id: " + id;
			}

			try {

				if (products == null || products.get(0) == null) {

					LinkedList<Product> newList = new LinkedList<>();
					newList.add(newProduct);
					productRepository.save(newProduct);
					transaction.setProducts(newList);
					transactionRepository.save(transaction);
				}
			} catch (Exception e) {

				for (int i = 0; i < products.size(); i++) {
					if (products.get(i).getProductId() == newProduct.getProductId()) {
						throw new Exception("The Transaction with id: " + id + " has already a product with id "
								+ product.getProductId());
					}
				}

				transaction.addProduct(newProduct);
				productRepository.save(newProduct);
				transactionRepository.save(transaction);
			}

		} catch (Exception ex) {
			return "Error creating Product related with the Transaction with id: " + id;
		}
		return "Product  with id " + product.getProductId() + " succesfully created linked to Transaction  with id: "
				+ id;

	}

	public List<Product> findTransactionProducts(@PathVariable("id") int id) {
		try {
			Transaction transaction = transactionRepository.findByTransactionId(id);
			if (transaction == null) {
				throw new Exception("The Product  with id:  " + id + " doesn't exist");
			}
			List<Product> lista = transaction.getProducts();
			return lista;
		} catch (Exception e) {
			return null;
		}

	}

	public Product updateTransactionProduct(@PathVariable("id") int id, @PathVariable("idP") int idP,
			@Valid @RequestBody Product newProduct) {
		try {
			Transaction transaction = transactionRepository.findByTransactionId(id);
			if (transaction == null) {
				throw new Exception("The transaction with id:  " + id + " doesn't exist");
			}

			Product product = productRepository.findByProductId(idP);

			if (product == null) {
				throw new Exception("The Product  with id:  " + idP + " doesn't exist");
			}

			List<Product> products = transaction.getProducts();

			for (int i = 0; i < products.size(); i++) {
				if (products.get(i).getProductId() == product.getProductId()) {
					product.setPrice(newProduct.getPrice());
					product.setProductPromotion(newProduct.getProductPromotion());
					product.setTransactions(newProduct.getTransactions());
					productRepository.save(product);
					return product;
				}
			}

		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public String deleteTransactionProduct(@PathVariable("id") int id, @PathVariable("idP") int idP) {
		try {
			Transaction transaction = transactionRepository.findByTransactionId(id);
			Product product = productRepository.findByProductId(idP);

			if (transaction == null) {
				throw new Exception("The Transaction  with id:  " + id + " doesn't exist");
			}
			Product foundProduct = productRepository.findByProductId(idP);

			if (product == null) {
				throw new Exception("The Product with id:  " + idP + " doesn't exist");
			}

			productRepository.delete(foundProduct);
		} catch (Exception e) {
			return "Error deleting the Product :" + e.toString();
		}
		return "Product  with id " + idP + " has been succesfully deleted!";
	}

}
