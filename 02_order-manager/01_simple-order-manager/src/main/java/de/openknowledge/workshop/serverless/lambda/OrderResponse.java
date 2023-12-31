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
package de.openknowledge.workshop.serverless.lambda;

import de.openknowledge.workshop.serverless.model.Order;

/**
 * Wrapper object for order
 */
public class OrderResponse {

    private Order order;

    public OrderResponse() {
    }

    public OrderResponse(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return this.order;
    }
    public void setOrderId(Order order) {
        this.order = order;
    }

    public static OrderResponse emptyResponse() {
        return new OrderResponse();
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "order='" + order + '\'' +
                '}';
    }
}
