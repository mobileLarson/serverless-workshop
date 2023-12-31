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
package de.openknowledge.workshop.serverless.repository;

import de.openknowledge.workshop.serverless.model.Order;
import de.openknowledge.workshop.serverless.util.AwsClientProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;

public class DynamoDbOrderRepository  {

    private static final Logger logger = LoggerFactory.getLogger(DynamoDbOrderRepository.class);

    // order table name
    public static final String ORDER_TABLE_NAME = "sw-order-table";

    // dynamo db client (expensive operation)
    public static final DynamoDbClient DDB_CLIENT = AwsClientProvider.getDynamoDbClient();

    private static final String COL_ORDER_ID = "PK";               // partition (orderId)

    private static final String COL_ORDER_NO = "orderNo";          // column orderNo
    private static final String COL_ORDER_STATE = "state";         // column state
    private static final String COL_USER_ID = "userId";            // column userId
    private static final String COL_DRINK = "drink";               // column drink

    /**
     * Stores a given order in dynamoDB.
     *
     * @param order given order
     * @return order created
     */
    public void storeOrder(Order order) {

        // initialize return object
        Order orderCreated = new Order();

        // map for handling key / attribute mappings, where
        //   - key = column name and
        //   - attribute value = corresponding value of order object
        Map<String, AttributeValue> itemValues = new HashMap<String,AttributeValue>();

        // mapping of pk and sk
        itemValues.put(COL_ORDER_ID, AttributeValue.builder().s(order.getOrderId()).build());

        // TODO
        // - add map entries for remaining order attributes
        // - CAUTION: order no is a LONG value (use n for number)



        // TODO
        // build PutItemRequest object with the help of builder()-pattern
        // DON'T FORGET to call build() ;-)
        // - table name
        // - item withe key / attribute map (itemValues)
        PutItemRequest request = PutItemRequest.builder()
                .returnValues(ReturnValue.ALL_OLD)   // return all key/values of inserted object
                .build();

        // FYI:
        // PutItemRequest allows only ReturnValue.ALL_OLD or ReturnValue.ALL_NONE.
        // So there is no way to reproduce the inserted object from the ReturnValue map.
        // If you want to do so, use UpdateItemRequest instead.

        try {
            PutItemResponse putItemResponse = DDB_CLIENT.putItem(request);
            logger.info(String.format("%s: item put (%s | %d)", ORDER_TABLE_NAME, order.getOrderId(), order.getOrderNo()));

            if (putItemResponse.hasAttributes()) {
                for (var attribute : putItemResponse.attributes().entrySet()) {
                    logger.debug(String.format("Attribute $s has value $s", attribute.getKey()), attribute.getValue().s());
                }
            }

        } catch (ResourceNotFoundException e) {
            logger.error(String.format("Error: The Amazon DynamoDB table \"%s\" can't be found.", ORDER_TABLE_NAME));
            logger.error("Be sure that it exists and that you've typed its name correctly!");
            // WOULD BE NICE to have some useful ex handling ;-)
        } catch (DynamoDbException e) {
            logger.error(String.format("Error: %s", e.getMessage()));
            // WOULD BE NICE to have some useful ex handling ;-)
        }
    }

}
