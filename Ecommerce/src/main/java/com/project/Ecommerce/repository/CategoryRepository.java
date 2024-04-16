package com.project.Ecommerce.repository;

import com.project.Ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    public Category findByName(String name);

    @Query("select c from Category c where c.name=:name and c.parentCategory.name=:parentCategoryName")
    public Category findByNameAndParent(@Param("name")String name,@Param("parentCategoryName")String parentCategory);
}
