/*
 * Copyright (C) open knowledge GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package de.openknowledge.workshop.serverless.oms.lambda.update;

import de.openknowledge.workshop.serverless.oms.lambda.OrderAction;
import de.openknowledge.workshop.serverless.oms.lambda.OrderRequest;
import de.openknowledge.workshop.serverless.oms.model.Order;

import java.util.Optional;

public class UpdateOrderRequest {

    Order order;

    public UpdateOrderRequest() {
    }

    public UpdateOrderRequest(OrderRequest orderRequest) {
        if (isValidUpdateOrderRequest(orderRequest)) {
            this.order = orderRequest.getOrder();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static boolean isValidUpdateOrderRequest(OrderRequest request) {

        // check for action
        Optional<OrderAction> action = OrderAction.get(request.getAction());
        if (action.isEmpty() || !action.get().equals(OrderAction.UPDATE)) {
            return false;
        }

        // check for userId and drink
        Order order = request.getOrder();
        return (order != null && order.getOrderId() != null);
    }
}
