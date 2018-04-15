package data;

public class Message {
    public String Text;
    public FeedResponse feedResponse= new FeedResponse();
    public Sender sender;
    public enum Sender {USER,BOT};

}
