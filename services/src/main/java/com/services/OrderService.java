package com.services;

import com.model.CartInfo;
import com.model.OrderDetailInfo;
import com.model.OrderInfo;
import com.model.PaginationResult;

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
    void saveOrder(CartInfo cartInfo);

    /**
     * @param page
     * @param maxResult
     * @param maxNavigationPage
     * @return Pagination result
     */
    PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage);

    /**
     * This method of finding an order by id
     *
     * @param orderId
     * @return OrderInfo
     */
    OrderInfo getOrderInfo(String orderId);

    /**
     * @param orderId
     * @return List OrderDetailInfo
     */
    List<OrderDetailInfo> listOrderDetailInfos(String orderId);
}
