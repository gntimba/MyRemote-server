package ClickAtell;


import com.clickatell.platform.data.Message;
import com.clickatell.platform.data.MessageRequest;
import com.clickatell.platform.util.MessageUtil;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class ClickatellRest {
    private static final String POST = "POST";
    private String apiKey;
    private String token;

    public ClickatellRest(String apiKey) {
        this.apiKey = apiKey;
    }

    public Message[] sendMessage(String message, String... numbers) throws Exception {
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setMessage(message);
        messageRequest.setToNumbers(numbers);
        return this.sendMessage(messageRequest);
    }

    public Message[] sendMessage(MessageRequest messageRequest) throws Exception {
        String number = messageRequest.getToNumbers()[0];

        for(int x = 1; x < messageRequest.getToNumbers().length; ++x) {
            number = number + "\",\"" + messageRequest.getToNumbers()[x];
        }

        String fromNumber = messageRequest.getFromNumber() != null ? messageRequest.getFromNumber() : "";
        String response = this.execute("messages", "POST", "{\"to\":[\"" + number + "\"],\"content\":\"" + messageRequest.getMessage() + "\", \"from\":\"" + fromNumber + "\"}");
        return MessageUtil.stringToMessage(response);
    }

    private String execute(String resource, String method, String dataPacket) throws UnknownHostException {
        HttpURLConnection connection = null;

        String var7;
        try {
            URL url = new URL("https://platform.clickatell.com/" + resource);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("X-Version", "1");
            connection.setRequestProperty("Authorization", this.apiKey);
            String l = "0";
            if (dataPacket != null) {
                l = Integer.toString(dataPacket.getBytes().length);
            }

            connection.setRequestProperty("Content-Length", "" + l);
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(dataPacket != null);
            if (dataPacket != null) {
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(dataPacket);
                wr.flush();
                wr.close();
            }

            connection.getResponseCode();
            InputStream stream = connection.getErrorStream();
            if (stream == null) {
                stream = connection.getInputStream();
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
            StringBuffer response = new StringBuffer();

            String line;
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\n');
            }

            rd.close();
            String var11 = response.toString().trim();
            return var11;
        } catch (UnknownHostException var16) {
            throw var16;
        } catch (Exception var17) {
            var7 = "";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

        }

        return var7;
    }
}
