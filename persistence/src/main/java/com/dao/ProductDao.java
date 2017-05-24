package com.dao;

import com.dao.exceptions.DaoException;
import com.entity.Product;
import com.model.PaginationResult;
import com.model.ProductInfo;

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
    ProductInfo findProductInfo(String code) throws DaoException;

    /**
     * @param page
     * @param maxResult
     * @param maxNavigationPage
     * @return Pagination result
     */
    PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) throws DaoException;

    /**
     * @param page
     * @param maxResult
     * @param maxNavigationPage
     * @param likeName
     * @return Pagination result
     */
    PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName) throws DaoException;

    /**
     * This method save product info
     *
     * @param productInfo
     */
    void save(ProductInfo productInfo) throws DaoException;
}
