package com.eticaret.state.impl;

import com.eticaret.entity.Order;
import com.eticaret.state.OrderState;

public class CanceledState implements OrderState {
	@Override
    public void next(Order order) {
        throw new UnsupportedOperationException("İptal edilen sipariş ilerletilemez.");
    }

    @Override
    public void cancel(Order order) {
        throw new UnsupportedOperationException("Sipariş zaten iptal edildi.");
    }

}
