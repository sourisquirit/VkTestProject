package helpers;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import io.qameta.allure.Step;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import static java.util.ResourceBundle.getBundle;

public class ApiHelper {
    private static final Logger logger = Logger.getLogger(ApiHelper.class.getName());
    private final ResourceBundle rb = getBundle("application");

    @Step("Get Api client")
    public VkApiClient getApiClient() {
        logger.info("Get api client");
        TransportClient transportClient = new HttpTransportClient();
        return new VkApiClient(transportClient);
    }

    @Step("Get User response data")
    public UserAuthResponse getUserResponseData(VkApiClient vk, String code) {
        logger.info("Get user response data");
        UserAuthResponse authResponse = null;
        try {
            authResponse = vk.oAuth()
                    .userAuthorizationCodeFlow(
                            Integer.valueOf(rb.getString("API_ID")),
                            System.getProperty("client_secret"),
                            rb.getString("REDIRECT_URI"),
                            code)
                    .execute();
        } catch (ApiException | ClientException e) {
            logger.warning("Error by getting User response data");
            throw new RuntimeException(e);
        }

        return authResponse;
    }
}
