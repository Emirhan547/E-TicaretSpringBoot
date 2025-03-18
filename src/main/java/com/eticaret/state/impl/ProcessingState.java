package com.eticaret.state.impl;

import com.eticaret.entity.Order;
import com.eticaret.entity.OrderStatus;
import com.eticaret.state.OrderState;

public class ProcessingState implements OrderState {
	@Override
    public void next(Order order) {
        order.setStatus(OrderStatus.SHIPPED);
        order.setOrderState(new ShippedState());
    }

    @Override
    public void cancel(Order order) {
        order.setStatus(OrderStatus.CANCELED);
        order.setOrderState(new CanceledState());
    }

}
