package de.openknowledge.workshop.serverless.util;


import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class AwsClientProvider {

    // region to use: e.g. Frankfurt == EU_CENTRAL_1, LONDON == EU_WEST_2
    // for "your" region" see https://docs.aws.amazon.com/de_de/AWSEC2/latest/UserGuide/using-regions-availability-zones.html

    // TODO: adjust region to "your" default region if necessary
    private static final Region DEFAULT_REGION = Region.EU_CENTRAL_1;


    /**
     * Builds an AWS dynamoDb client using the "default" region and
     * "default" profile credential provider
     *
     * @return AWS dynamoDb client
     */
    public static DynamoDbClient getDynamoDbClient() {
        return DynamoDbClient.builder()
                .region(DEFAULT_REGION)
                .build();
    }

    /**
     * Builds an AWS dynamoDb client using the "default" region
     * and given access key and secret key
     *
     * @param accessKey access key to use to build aws credentials
     * @param secretKey secret key to use to build aws credentials
     * @return AWS dynamoDb client
     */
    public static DynamoDbClient getDynamoDbClientFor(String accessKey, String secretKey) {

        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

        return DynamoDbClient
                .builder()
                .region(DEFAULT_REGION)
                .credentialsProvider(credentialsProvider)
                .build();
    }


}