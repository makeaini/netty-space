import com.googlecode.protobuf.format.JsonFormat;
import com.pulsar.netty.domain.MsgBody;

public class ApiTest {
	public static void main(String[] args) throws JsonFormat.ParseException {
		MsgBody.Builder msg = MsgBody.newBuilder();
		msg.setChannelId("abD01223");
		msg.setMsgInfo("hi helloworld");
		MsgBody msgBody = msg.build();
		JsonFormat format = new JsonFormat();
		// protobuf转Json 需要引入protobuf-java-format
		String msgBodyStr = format.printToString(msgBody);
		System.out.println(msgBodyStr);
	}

}
