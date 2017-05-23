package com.services;

import com.entity.Product;
import com.model.PaginationResult;
import com.model.ProductInfo;

/**
 * Interface ProductService
 */
public interface ProductService {

    /**
     * This method find product by id
     *
     * @param code
     * @return
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
     * @return Pagination Result
     */
    PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage);

    /**
     * @param page
     * @param maxResult
     * @param maxNavigationPage
     * @param likeName
     * @return Pagination Result
     */
    PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName);

    /**
     * This method save product info
     *
     * @param productInfo
     */
    void save(ProductInfo productInfo);
}
