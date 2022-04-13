package com.app.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.pojo.Categories;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Integer> {
	Categories findByCategoryName(String categoryName);
}
