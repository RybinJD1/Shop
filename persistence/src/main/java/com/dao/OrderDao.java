package com.dao;

import com.entity.Order;
import com.entity.OrderDetail;
import com.vo.OrderDetailInfo;
import com.vo.OrderInfo;
import com.vo.PaginationResult;

import java.util.List;

/**
 * Interface methods Orders
 */
public interface OrderDao {

    /**
     * This method save order
     *
     * @param order
     */
    void saveOrder(Order order);


    /**
     * This method save OrderDetail
     * @param detail
     */
    void saveOrderDetail(OrderDetail detail);

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
     * @return Order info
     */
    OrderInfo getOrderInfo(String orderId);

    /**
     * @param orderId
     * @return List OrderDetailInfo
     */
    List<OrderDetailInfo> listOrderDetailInfos(String orderId);

    int getMaxOrderNum();
}
