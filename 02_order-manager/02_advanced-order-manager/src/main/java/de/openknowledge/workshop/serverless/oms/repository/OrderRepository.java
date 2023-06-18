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

import java.util.List;
import java.util.Map;

public interface OrderRepository {

    void storeOrder(Order order);
    Order updateOrder(Order order);
    Order readOrder(String orderId);
    List<Order> readOrders(OrderRepositoryFilter filter, Map<String, String> filterAttributes);
    Order cancelOrder(String orderId);
    void deleteOrder(String orderId);
}
