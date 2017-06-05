package com.services.impl;

import com.dao.OrderDao;
import com.dao.ProductDao;
import com.entity.Order;
import com.entity.OrderDetail;
import com.entity.Product;
import com.services.OrderService;
import com.services.exceptions.ServiceException;
import com.vo.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger log = Logger.getLogger(ProductServiceImpl.class);

    @Autowired
    @Qualifier("orderDaoImpl")
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDAO;

    @Override
    public void saveOrder(CartInfo cartInfo) throws ServiceException {
        int orderNum = orderDao.getMaxOrderNum() + 1;
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setOrderNum(orderNum);
        order.setOrderDate(new Date());
        order.setAmount(cartInfo.getAmountTotal());
        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        order.setCustomerName(customerInfo.getName());
        order.setCustomerEmail(customerInfo.getEmail());
        order.setCustomerPhone(customerInfo.getPhone());
        order.setCustomerAddress(customerInfo.getAddress());
        orderDao.saveOrder(order);
        List<CartLineInfo> lines = cartInfo.getCartLines();
        for (CartLineInfo line : lines) {
            OrderDetail detail = new OrderDetail();
            detail.setId(UUID.randomUUID().toString());
            detail.setOrder(order);
            detail.setAmount(line.getAmount());
            detail.setPrice(line.getProductInfo().getPrice());
            detail.setQuanity(line.getQuantity());
            String code = line.getProductInfo().getCode();
            Product product = this.productDAO.findProduct(code);
            detail.setProduct(product);
            orderDao.saveOrderDetail(detail);
        }
        cartInfo.setOrderNum(orderNum);

    }

    @Override
    public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) throws ServiceException {

        return orderDao.listOrderInfo(page, maxResult, maxNavigationPage);

    }

    @Override
    public OrderInfo getOrderInfo(String orderId) throws ServiceException {

        return orderDao.getOrderInfo(orderId);

    }

    @Override
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) throws ServiceException {
        return orderDao.listOrderDetailInfos(orderId);
    }
}
