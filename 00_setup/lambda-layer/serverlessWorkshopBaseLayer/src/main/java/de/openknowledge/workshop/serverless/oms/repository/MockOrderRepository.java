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
package de.openknowledge.workshop.serverless.oms.repository;

import de.openknowledge.workshop.serverless.oms.model.Order;
import de.openknowledge.workshop.serverless.oms.model.OrderStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MockOrderRepository implements OrderRepository {

    @Override
    public void storeOrder(Order order) {
        // no implementation by default
    }

    @Override
    public Order updateOrder(Order order) {
        return order;
    }

    @Override
    public Order readOrder(String orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderNo(12345L);
        order.setOrderStatus("mock-status");
        order.setDrink("mock-drink");
        order.setUserId("mock-user");
        order.setBaristaId("mock-barista");
        return order;
    }

    public List<Order> readOrders(OrderRepositoryFilter filter, Map<String, String> filterAttributes){
        Order order = readOrder("");
        return Arrays.asList(order);
    }


    @Override
    public Order cancelOrder(String orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderNo(12345L);
        order.setOrderStatus(OrderStatus.CANCELED.getStatus());
        order.setDrink("mock-drink");
        order.setUserId("mock-user");
        order.setBaristaId("mock-barista");
        return order;
    }

    @Override
    public void deleteOrder(String orderId) {
        // no implementation by default
    }
}
