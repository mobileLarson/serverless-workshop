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
package de.openknowledge.workshop.serverless.oms.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import de.openknowledge.workshop.serverless.oms.lambda.create.CreateOrderHandler;
import de.openknowledge.workshop.serverless.oms.lambda.create.CreateOrderRequest;
import de.openknowledge.workshop.serverless.oms.lambda.delete.DeleteOrderHandler;
import de.openknowledge.workshop.serverless.oms.lambda.delete.DeleteOrderRequest;
import de.openknowledge.workshop.serverless.oms.lambda.read.ReadOrderHandler;
import de.openknowledge.workshop.serverless.oms.lambda.read.ReadOrderRequest;
import de.openknowledge.workshop.serverless.oms.lambda.update.UpdateOrderHandler;
import de.openknowledge.workshop.serverless.oms.lambda.update.UpdateOrderRequest;
import de.openknowledge.workshop.serverless.oms.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Order Management Request Dispatcher:
 * - extracts order action
 * - delegates call to related order handler (create, read, update, delete)
 * - collects and transforms result
 */
// de.openknowledge.workshop.serverless.oms.lambda.OrderHandler::handleRequest
public class OrderHandler implements RequestHandler<OrderRequest, OrderResponse> {

    private static final Logger logger = LoggerFactory.getLogger(OrderHandler.class);

    @Override
    public OrderResponse handleRequest(OrderRequest orderRequest, Context context) {

        // check for valid order action
        Order orderToHandle = orderRequest.getOrder();
        OrderAction action = getOrderAction(orderRequest.getAction());

        logger.info(String.format("Action = %s", action));
        logger.info(String.format("Detail = %s", orderToHandle));

        switch (action) {
            case CREATE:
                CreateOrderRequest createOrderRequest = new CreateOrderRequest(orderRequest);
                CreateOrderHandler createOrderHandler = new CreateOrderHandler();
                return new OrderResponse(createOrderHandler.handleRequest(createOrderRequest, context));
            case READ:
                ReadOrderRequest readOrderRequest = new ReadOrderRequest(orderRequest);
                ReadOrderHandler readOrderHandler = new ReadOrderHandler();
                return new OrderResponse(readOrderHandler.handleRequest(readOrderRequest, context));
            case UPDATE:
                // TODO
                // - "convert" OrderRequest to UpdateOrderRequest
                // - handle "converted" request with corresponding Request Handler for update request
                // - "convert" specific order response for update request to neutral response
                // - return "neutral" OrderResponse corresponding to method signature
                return null;
            case DELETE:
                DeleteOrderRequest deleteOrderRequest = new DeleteOrderRequest(orderRequest);
                DeleteOrderHandler deleteOrderHandler = new DeleteOrderHandler();
                return new OrderResponse(deleteOrderHandler.handleRequest(deleteOrderRequest, context));
            default:
                return OrderResponse.emptyResponse("WARNING", String.format("Unknown action: %s.", orderRequest.getAction()));
        }
    }

    /**
     * Converts string based order action to order action object.
     *
     * @param action String presentation of order action
     * @return OrderAction object
     */
    private static OrderAction getOrderAction(String action) {
        Optional<OrderAction> orderAction = OrderAction.get(action);
        return orderAction.orElse(OrderAction.UNKNOWN);
    }
}
