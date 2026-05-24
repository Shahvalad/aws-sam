package com.serverless.pirates.getbyid;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.serverless.pirates.repository.PiratesRepository;
import com.serverless.pirates.util.LambdaUtil;

import java.util.Map;

public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final PiratesRepository piratesRepository = new PiratesRepository();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        try {
            String pirateId = input.getPathParameters() != null
                    ? input.getPathParameters().get("pirateId")
                    : null;

            if (pirateId == null || pirateId.isBlank()) {
                return new APIGatewayProxyResponseEvent()
                        .withStatusCode(400)
                        .withHeaders(Map.of("Content-Type", "application/json"))
                        .withBody(LambdaUtil.buildFailedResponse("pirateId is required"));
            }

            var pirateOpt = piratesRepository.findById(pirateId);

            if (pirateOpt.isEmpty()) {
                return new APIGatewayProxyResponseEvent()
                        .withStatusCode(404)
                        .withHeaders(Map.of("Content-Type", "application/json"))
                        .withBody(LambdaUtil.buildFailedResponse("Pirate not found"));
            }

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withHeaders(Map.of("Content-Type", "application/json"))
                    .withBody(LambdaUtil.buildSuccessfulResponse(
                            pirateOpt.get(),
                            "Pirate fetched successfully"
                    ));

        } catch (Exception e) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withHeaders(Map.of("Content-Type", "application/json"))
                    .withBody(LambdaUtil.buildFailedResponse(
                            "Internal server error: " + e.getMessage()
                    ));
        }
    }
}
