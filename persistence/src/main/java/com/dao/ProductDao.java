package com.dao;

import com.entity.Product;
import com.vo.PaginationResult;
import com.vo.ProductInfo;

/**
 * Interface methods Products
 */
public interface ProductDao {

    /**
     * This method find product by id
     *
     * @param code
     * @return Product
     */
    Product findProduct(String code);

    /**
     * This method find product info by id
     *
     * @param code
     * @return ProductInfo
     */
    ProductInfo findProductInfo(String code);

    /**
     * @param page
     * @param maxResult
     * @param maxNavigationPage
     * @return Pagination result
     */
    PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage);

    /**
     * @param page
     * @param maxResult
     * @param maxNavigationPage
     * @param likeName
     * @return Pagination result
     */
    PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName);

    /**
     * This method save product info
     *
     * @param product
     */
    void save(Product product);
}
