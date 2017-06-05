package com.util;

import com.vo.CartInfo;

import javax.servlet.http.HttpServletRequest;

public class Utils {

    /**
     * Products in Cart, stored in Session.
     *
     * @param request
     * @return cartInfo
     */
    public static CartInfo getCartInSession(HttpServletRequest request) {
        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");
        if (cartInfo == null) {
            cartInfo = new CartInfo();
            request.getSession().setAttribute("myCart", cartInfo);
        }
        return cartInfo;
    }

    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("myCart");
    }

    public static void storeLastOrderedCartInSession(HttpServletRequest request, CartInfo cartInfo) {
        request.getSession().setAttribute("lastOrderedCart", cartInfo);
    }

    public static CartInfo getLastOrderedCartInSession(HttpServletRequest request) {
        return (CartInfo) request.getSession().getAttribute("lastOrderedCart");
    }
}
