package com.eticaret.converter;

import com.eticaret.entity.OrderStatus;
import com.eticaret.state.OrderState;
import com.eticaret.state.impl.CanceledState;
import com.eticaret.state.impl.DeliveredState;
import com.eticaret.state.impl.PendingState;
import com.eticaret.state.impl.ProcessingState;
import com.eticaret.state.impl.ShippedState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStateConverter implements AttributeConverter<OrderState, String> {
	@Override
    public String convertToDatabaseColumn(OrderState orderState) {
        if (orderState instanceof PendingState) return "PENDING";
        if (orderState instanceof ProcessingState) return "PROCESSING";
        if (orderState instanceof ShippedState) return "SHIPPED";
        if (orderState instanceof DeliveredState) return "DELIVERED";
        if (orderState instanceof CanceledState) return "CANCELED";
        throw new IllegalArgumentException("Bilinmeyen OrderState: " + orderState);
    }

    @Override
    public OrderState convertToEntityAttribute(String status) {
        return switch (OrderStatus.valueOf(status)) {
            case PENDING -> new PendingState();
            case PROCESSING -> new ProcessingState();
            case SHIPPED -> new ShippedState();
            case DELIVERED -> new DeliveredState();
            case CANCELED -> new CanceledState();
        };
    }

}
