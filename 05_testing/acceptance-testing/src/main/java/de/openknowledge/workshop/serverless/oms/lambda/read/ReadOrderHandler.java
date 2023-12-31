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

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import de.openknowledge.workshop.serverless.oms.model.Order;
import de.openknowledge.workshop.serverless.oms.service.OrderService;

public class ReadOrderHandler implements RequestHandler<ReadOrderRequest, ReadOrderResponse> {

    /**
     * Creates an order response with the help of a given <code>OrderRequest</code>
     * representing the ordering user and the drink to create.
     *
     * @param readOrderRequest order event (userId, drink)
     * @param context          aws lambda context
     * @return order response wrapping the created order
     */
    public ReadOrderResponse handleRequest(ReadOrderRequest readOrderRequest, Context context) {

        ReadOrderResponse response;

        try {
            String orderId = readOrderRequest.getOrderId();
            Order order = OrderService.readOrder(orderId);
            return new ReadOrderResponse(order, "OK", "Order successfully read.");
        } catch (Exception ex) {
            // TODO should be lambda exception
            // response is already set to LAMBDA_EXCEPTION_GREET
            // any additional action required?
            // WHAT TO RETURN?
            return ReadOrderResponse.emptyResponse("ERROR", String.format("Error during order retrieval: %s.", ex.getMessage()));
        }
    }

}
