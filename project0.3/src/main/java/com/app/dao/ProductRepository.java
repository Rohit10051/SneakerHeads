package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.pojo.Categories;
import com.app.pojo.Product;
import com.app.pojo.Type;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("select p from Product p join fetch p.categories where p.categories.id=:cId")
	List<Product> getAllProduct(@Param("cId")int cId);
	
	@Query("select p from Product p join fetch p.categories where p.categories.id=:cId order by p.price")
	List<Product> getAllProductlowtohigh(@Param("cId")int cId);
	
	@Query("select p from Product p join fetch p.categories where p.categories.id=:cId order by p.price desc")
	List<Product> getAllProducthightolow(@Param("cId")int cId);

	@Query("select p from Product p join fetch p.categories where p.productName LIKE :pName%")
	List<Product> getProductListByName(@Param("pName")String pName);
	
	Product findByProductNameAndSize(String productName, int size);
	
	List<Product> findByCategories(Categories c);
	
	List<Product> findByType(Type type);

}
