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
package de.openknowledge.workshop.serverless.oms.service;


import de.openknowledge.workshop.serverless.oms.model.Order;
import de.openknowledge.workshop.serverless.oms.model.OrderStatus;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.condition.DisabledIf;
import software.amazon.awssdk.services.dynamodb.endpoints.internal.Value;

import java.util.UUID;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Tests for Order Service CRUD methods.")
public class TestOrderService {

    private final static Boolean MOCK = Boolean.FALSE;

    private final static String USER_ID = "user-id-100";
    private final static String BARISTA_ID = "barista-id-100";
    private final static String DRINK = "Americano grande";
    private final static Long ORDER_NO = 12345L ;

    private final static String ORDER_STATUS_CONFIRMED = OrderStatus.CONFIRMED.getStatus();
    private final static String ORDER_STATUS_CANCELED = OrderStatus.CANCELED.getStatus();
    private final static String ORDER_STATUS_PICKED_UP = OrderStatus.PICKED_UP.getStatus();

    private static String testOrderId = "order-id-0000";

    @BeforeAll
    @DisplayName("Test initialisation (ALL).")
    static void initAll() {
        System.out.println("Los gehts ... ");
    }

    @AfterAll
    @DisplayName("Test tear down (ALL).")
    static void tearDownAll() {
        System.out.println("Los gehts ... fertig!");
    }

    @Test
    @DisplayName("CREATE a new order.")
    @org.junit.jupiter.api.Order(1)
    void createOrder() {

        // create order
        Order orderCreated = OrderService.createOrder(USER_ID, DRINK);

        // check created order
        assumeTrue(orderCreated != null);

        // set orderNo and orderId for following test
        // THIS IS INTEGRATION BUT NOT UNIT TESTING!
        this.testOrderId = orderCreated.getOrderId();

        assumeTrue(USER_ID.equals(orderCreated.getUserId()));
        assumeTrue(DRINK.equals(orderCreated.getDrink()));
        assumeTrue(ORDER_STATUS_CONFIRMED.equals(orderCreated.getOrderStatus()));
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    @DisplayName("READ an existing order.")
    void readOrder() {

        // use orderNo and orderId from previous test
        // THIS IS INTEGRATION BUT NOT UNIT TESTING!
        Order orderRead = OrderService.readOrder(testOrderId);

        // check order read
        assumeTrue(orderRead != null);
        assumeTrue(testOrderId.equals(orderRead.getOrderId()));
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    @DisplayName("UPDATE an existing order.")
    void updateOrder() {

        // update order to simulate order pick-up by user.

        // use orderNo and orderId from previous test
        // THIS IS INTEGRATION BUT NOT UNIT TESTING!
        Order orderToUpdate = new Order(testOrderId, ORDER_NO, ORDER_STATUS_PICKED_UP, USER_ID, BARISTA_ID, DRINK);
        Order orderUpdated = OrderService.updateOrder(orderToUpdate);

        assumeTrue(orderUpdated != null);
        assumeTrue(testOrderId.equals(orderUpdated.getOrderId()));
        assumeTrue(ORDER_NO.equals(orderUpdated.getOrderNo()));
        assumeTrue(ORDER_STATUS_PICKED_UP.equals(orderUpdated.getOrderStatus()));
        assumeTrue(USER_ID.equals(orderUpdated.getUserId()));
        assumeTrue(BARISTA_ID.equals(orderUpdated.getBaristaId()));
        assumeTrue(DRINK.equals(orderUpdated.getDrink()));
    }


    @Test
    @org.junit.jupiter.api.Order(4)
    @DisplayName("CANCEL an existing order.")
    void cancelOrder() {

        // update order to simulate order pick-up by user.

        // use orderNo and orderId from previous test
        // THIS IS INTEGRATION BUT NOT UNIT TESTING!
        Order orderCanceled = OrderService.cancelOrder(testOrderId);

        assumeTrue(orderCanceled != null);
        assumeTrue(testOrderId.equals(orderCanceled.getOrderId()));
        assumeTrue(ORDER_STATUS_CANCELED.equals(orderCanceled.getOrderStatus()));
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    @DisplayName("DELETE an existing order.")
    void deleteOrder() {

        // use orderNo and orderId from previous test
        // THIS IS INTEGRATION BUT NOT UNIT TESTING!
        Order orderToUpdate = new Order();
        OrderService.deleteOrder(testOrderId);

        // nothing explicitly to test cause there is no result
    }


    @Test
    @org.junit.jupiter.api.Order(6)
    @DisplayName("READ an unknown order.")
    @DisabledIf("isMockUsed")
    void readUnknownOrder() {

        // use orderNo and orderId from previous test
        // THIS IS INTEGRATION BUT NOT UNIT TESTING!
        Order orderRead = OrderService.readOrder(testOrderId);

        // check order read
        assumeTrue(orderRead == null);
    }

    boolean isMockUsed() {
        return MOCK.booleanValue();
    }

}
