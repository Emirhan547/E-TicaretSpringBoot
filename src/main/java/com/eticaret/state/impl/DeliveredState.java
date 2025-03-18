package com.eticaret.state.impl;

import com.eticaret.entity.Order;
import com.eticaret.state.OrderState;

public class DeliveredState implements OrderState {
	@Override
    public void next(Order order) {
        throw new UnsupportedOperationException("Sipariş zaten teslim edildi.");
    }

    @Override
    public void cancel(Order order) {
        throw new UnsupportedOperationException("Teslim edilmiş sipariş iptal edilemez.");
    }

}
