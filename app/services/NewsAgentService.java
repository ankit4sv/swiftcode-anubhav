package services;

import com.fasterxml.jackson.databind.JsonNode;
import data.NewsAgentResponse;
import play.libs.ws.WS;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

import java.util.UUID;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class NewsAgentService {
    public NewsAgentResponse getNewsAgentResponse(String query,UUID sessionId) throws ExecutionException, InterruptedException {
        NewsAgentResponse newsAgentResponse = new NewsAgentResponse();
        try {
            WSRequest queryRequest = WS.url("https://api.api.api/api/query");
            CompletionStage<WSResponse> responsePromise = queryRequest
                    .setQueryParameter("V", "20150910")
                    .setQueryParameter("query", query)
                    .setQueryParameter("lang", "en")
                    .setQueryParameter("sessionId", sessionId.toString())
                    .setQueryParameter("timezone", "2017-08-09T03:25:23+0530")
                    .setHeader("Authorization", "Bearer 054a388ef08e46c3beb61cd9a12dd13f")
                    .get();
            JsonNode response = responsePromise.thenApply(WSResponse::asJson).toCompletableFuture().get();
            newsAgentResponse.query = response.get("result").get("parameters").get("keyword").asText().isEmpty() ?
                    (response.get("result").get("parameters").get("source").asText().isEmpty()
                            ? response.get("result").get("parameters").get("category").asText()
                            : response.get("result").get("parameters").get("source").asText() )
                    : response.get("result").get("parameters").get("keyword").asText() ;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return newsAgentResponse;

    }
}
