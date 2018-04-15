package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.FeedResponse;
import data.Message;
import data.NewsAgentResponse;
import services.FeedService;
import services.NewsAgentService;

import java.util.UUID;

public class MessageActor extends UntypedActor {
    // Self - Reference the Actor
    private final ActorRef out;

    public MessageActor(ActorRef out) {
        this.out = out;
    }

    // PROPS
    public static Props props(ActorRef out) {
        return Props.create(MessageActor.class, out);
    }

    // Object of Feed Service
    // Object of NewsAgentService
    private FeedService feedService = new FeedService();
    private NewsAgentService newsAgentService = new NewsAgentService();
    private NewsAgentResponse newsAgentResponse;
    private FeedResponse feedResponse;

    @Override
    public void onReceive(Object message) throws Throwable {
        // Send back the response
        ObjectMapper mapper = new ObjectMapper();
        if (message instanceof String) {

            Message messageObject = new Message();
            messageObject.Text=(String)message;
            messageObject.sender=Message.Sender.USER;
            out.tell(mapper.writeValueAsString(messageObject), self());
            String query= newsAgentService.getNewsAgentResponse( "Find " + messageObject.Text,UUID.randomUUID()).query;
            newsAgentResponse = newsAgentService.getNewsAgentResponse(messageObject.Text,UUID.randomUUID());
            FeedResponse feedResponse=feedService.getFeedByQuery(newsAgentResponse.query);
            messageObject.Text=(feedResponse.title == null) ? "No results found" : "Showing results for: " + query;
            messageObject.feedResponse=feedResponse;
            messageObject.sender = Message.Sender.BOT;
            out.tell(mapper.writeValueAsString(messageObject),self());
        }
    }
}