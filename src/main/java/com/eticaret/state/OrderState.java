package com.eticaret.state;

import com.eticaret.entity.Order;

public interface OrderState {
	void next(Order order);
    void cancel(Order order);

}
