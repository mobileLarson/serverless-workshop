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
package de.openknowledge.workshop.serverless.oms.lambda.delete;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import de.openknowledge.workshop.serverless.oms.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteOrderHandler implements RequestHandler<DeleteOrderRequest, DeleteOrderResponse> {

    private static final Logger logger = LoggerFactory.getLogger(DeleteOrderHandler.class);


    /**
     * Deletes an order with the help of a given <code>DeleteOrderRequest</code>
     * representing the ordering user and the drink to create.
     *
     * @param deleteOrderRequest order event (userId, drink)
     * @param context            aws lambda context
     * @return order response wrapping the created order
     */
    public DeleteOrderResponse handleRequest(DeleteOrderRequest deleteOrderRequest, Context context) {

        DeleteOrderResponse response;

        try {
            String orderId = deleteOrderRequest.getOrderId();
            OrderService.deleteOrder(orderId);
            return new DeleteOrderResponse("OK", "Order successfully deleted.");
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage());
            return DeleteOrderResponse.emptyResponse("ERROR", String.format("Error during order deletion: %s.", ex.getMessage()));
        }
    }
}