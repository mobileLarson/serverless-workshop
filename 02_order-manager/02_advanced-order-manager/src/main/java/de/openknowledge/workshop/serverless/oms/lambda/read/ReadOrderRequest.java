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
package de.openknowledge.workshop.serverless.oms.lambda.read;

import de.openknowledge.workshop.serverless.oms.lambda.OrderAction;
import de.openknowledge.workshop.serverless.oms.lambda.OrderRequest;
import de.openknowledge.workshop.serverless.oms.model.Order;

import java.util.Optional;

public class ReadOrderRequest {

    private String orderId;

    public ReadOrderRequest() {
    }

    public ReadOrderRequest(OrderRequest orderRequest) {
        if (isValidReadOrderRequest(orderRequest)) {
            this.orderId = orderRequest.getOrder().getOrderId();
        } else {
            throw new IllegalArgumentException();
        }
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isValid() {
        return orderId != null;
    }


    public static boolean isValidReadOrderRequest(OrderRequest request) {

        // check for action
        Optional<OrderAction> action = OrderAction.get(request.getAction());
        if (action.isEmpty() || !action.get().equals(OrderAction.DELETE)) {
            return false;
        }

        // check for userId and drink
        Order order = request.getOrder();
        return (order != null && order.getOrderId() != null);
    }

}