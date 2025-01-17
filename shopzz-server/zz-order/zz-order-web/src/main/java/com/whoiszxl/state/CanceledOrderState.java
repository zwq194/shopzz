package com.whoiszxl.state;

import com.whoiszxl.constants.OrderStatusConstants;
import com.whoiszxl.entity.Order;
import com.whoiszxl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 已取消状态
 * @author whoiszxl
 *
 */
@Component
public class CanceledOrderState extends AbstractOrderState {

	@Autowired
	public CanceledOrderState(OrderService orderService) {
		super(orderService);
	}

	@Override
	protected Integer getOrderStatus(Order order) {
		return OrderStatusConstants.CANCELED;
	}
}
