package com.serverless.pirates.getall;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.serverless.pirates.repository.PiratesRepository;
import com.serverless.pirates.util.LambdaUtil;

import java.util.Map;

public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{

    private static final PiratesRepository piratesRepository = new PiratesRepository();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        try {
            var pirates = piratesRepository.findAll();
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withHeaders(Map.of("Content-Type", "application/json"))
                    .withBody(LambdaUtil
                            .buildSuccessfulResponse(pirates,
                                    "Pirates fetched successfully."));
        } catch (Exception e) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withHeaders(Map.of("Content-Type", "application/json"))
                    .withBody(LambdaUtil
                            .buildFailedResponse("Something went wrong while fetching pirates. Error: "
                                    + e.getMessage()));
        }
    }
}
