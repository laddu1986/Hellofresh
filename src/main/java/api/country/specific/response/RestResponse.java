package api.country.specific.response;

import java.util.ArrayList;

public class RestResponse {

	private ArrayList<String> messages;

	public ArrayList<String> getMessages() {
		return this.messages;
	}

	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}

	private Result result;

	public Result getResult() {
		return this.result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
