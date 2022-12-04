package vn.openshift;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SpringBootApplication
@RestController
public class Test1Application {

	@GetMapping("/{input}")
	public String getAmount(@PathVariable String input) throws IOException {
		String amount = input;
		String url_str = "https://api.exchangerate.host/convert?from=USD&to=VND&amount=" + amount;

		URL url = new URL(url_str);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();

		JsonObject jsonobj = JsonParser.parseReader(new InputStreamReader(request.getInputStream()))
										.getAsJsonObject();

		String req_result = jsonobj.get("result").getAsString();
		return req_result;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Test1Application.class, args);
	}

}
