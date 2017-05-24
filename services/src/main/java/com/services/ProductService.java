package com.services;

import com.entity.Product;
import com.model.PaginationResult;
import com.model.ProductInfo;
import com.services.exceptions.ServiceException;

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
    Product findProduct(String code) throws ServiceException;

    /**
     * This method find product info by id
     *
     * @param code
     * @return ProductInfo
     */
    ProductInfo findProductInfo(String code) throws ServiceException;

    /**
     * @param page
     * @param maxResult
     * @param maxNavigationPage
     * @return Pagination Result
     */
    PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) throws ServiceException;

    /**
     * @param page
     * @param maxResult
     * @param maxNavigationPage
     * @param likeName
     * @return Pagination Result
     */
    PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName) throws ServiceException;

    /**
     * This method save product info
     *
     * @param productInfo
     */
    void save(ProductInfo productInfo) throws ServiceException;
}
