package com.eticaret.state.impl;

import com.eticaret.entity.Order;
import com.eticaret.entity.OrderStatus;
import com.eticaret.state.OrderState;

public class ShippedState implements OrderState {
	@Override
    public void next(Order order) {
        order.setStatus(OrderStatus.DELIVERED);
        order.setOrderState(new DeliveredState());
    }

    @Override
    public void cancel(Order order) {
        throw new UnsupportedOperationException("Kargoya verilmiş sipariş iptal edilemez.");
    }

}
