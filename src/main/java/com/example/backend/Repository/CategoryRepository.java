package com.example.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Model.Category;
import com.example.backend.Model.Product;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
}
