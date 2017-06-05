package com.services;

import com.vo.CartInfo;
import com.vo.OrderDetailInfo;
import com.vo.OrderInfo;
import com.vo.PaginationResult;
import com.services.exceptions.ServiceException;

import java.util.List;

/**
 * Interface OrderService
 */
public interface OrderService {

    /**
     * This method save order
     *
     * @param cartInfo
     */
    void saveOrder(CartInfo cartInfo) throws ServiceException;

    /**
     * @param page
     * @param maxResult
     * @param maxNavigationPage
     * @return Pagination result
     */
    PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) throws ServiceException;

    /**
     * This method of finding an order by id
     *
     * @param orderId
     * @return OrderInfo
     */
    OrderInfo getOrderInfo(String orderId) throws ServiceException;

    /**
     * @param orderId
     * @return List OrderDetailInfo
     */
    List<OrderDetailInfo> listOrderDetailInfos(String orderId) throws ServiceException;
}
