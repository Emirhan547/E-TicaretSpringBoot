package com.eticaret.state.impl;

import com.eticaret.entity.Order;
import com.eticaret.entity.OrderStatus;
import com.eticaret.state.OrderState;

public class PendingState implements OrderState {
	@Override
    public void next(Order order) {
        order.setStatus(OrderStatus.PROCESSING);
        order.setOrderState(new ProcessingState());
    }

    @Override
    public void cancel(Order order) {
        order.setStatus(OrderStatus.CANCELED);
        order.setOrderState(new CanceledState());
    }

}
