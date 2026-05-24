package com.serverless.pirates.save;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.serverless.pirates.model.Pirate;
import com.serverless.pirates.repository.PiratesRepository;
import com.serverless.pirates.util.LambdaUtil;

import java.util.Map;
import java.util.UUID;

import static com.serverless.pirates.util.LambdaUtil.validateField;

public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final PiratesRepository piratesRepository = new PiratesRepository();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        try {
            if (input.getBody() == null || input.getBody().isBlank()) {
                return new APIGatewayProxyResponseEvent()
                        .withStatusCode(400)
                        .withHeaders(Map.of("Content-Type", "application/json"))
                        .withBody(LambdaUtil.buildFailedResponse("Request body is required"));
            }

            Pirate pirate = LambdaUtil.parseRequestBodyToPirate(input.getBody());

            StringBuilder validationMessage = getValidationErrors(pirate);
            if (validationMessage != null) {
                return new APIGatewayProxyResponseEvent()
                        .withStatusCode(400)
                        .withHeaders(Map.of("Content-Type", "application/json"))
                        .withBody(LambdaUtil.buildFailedResponse(validationMessage.toString()));
            }

            pirate.setPirateId(UUID.randomUUID().toString());
            piratesRepository.save(pirate);

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(201)
                    .withHeaders(Map.of("Content-Type", "application/json"))
                    .withBody(LambdaUtil.buildSuccessfulResponse(
                            pirate,
                            "Pirate created successfully"
                    ));


        } catch (Exception e) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(500)
                    .withHeaders(Map.of("Content-Type", "application/json"))
                    .withBody(LambdaUtil.buildFailedResponse(
                            "Error while saving pirate: " + e.getMessage()
                    ));
        }
    }

    private StringBuilder getValidationErrors(Pirate pirate) {
        StringBuilder failedValidationMessage = new StringBuilder();

        appendIfNotNull(failedValidationMessage, validateField("name", pirate.getName()));
        appendIfNotNull(failedValidationMessage, validateField("crew", pirate.getCrew()));
        appendIfNotNull(failedValidationMessage, validateField("age", pirate.getAge()));

        if (failedValidationMessage.length() == 0) {
            return null;
        }

        return failedValidationMessage;
    }

    private void appendIfNotNull(StringBuilder sb, String msg) {
        if (msg != null) {
            sb.append(msg).append(" ");
        }
    }
}
