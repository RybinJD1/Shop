package com.services.servicesImpl;

import com.dao.OrderDao;
import com.model.CartInfo;
import com.model.OrderDetailInfo;
import com.model.OrderInfo;
import com.model.PaginationResult;
import com.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    @Qualifier("orderDaoImpl")
    private OrderDao orderDao;

    @Override
    public void saveOrder(CartInfo cartInfo) {
        orderDao.saveOrder(cartInfo);
    }

    @Override
    public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
        return orderDao.listOrderInfo(page, maxResult, maxNavigationPage);
    }

    @Override
    public OrderInfo getOrderInfo(String orderId) {
        return orderDao.getOrderInfo(orderId);
    }

    @Override
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
        return orderDao.listOrderDetailInfos(orderId);
    }
}
