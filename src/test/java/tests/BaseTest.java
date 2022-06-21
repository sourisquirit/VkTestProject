package tests;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.UserAuthResponse;
import helpers.ApiHelper;
import helpers.LikesHelper;
import helpers.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class BaseTest {
    public ResourceBundle rb = getBundle("application");
    public LoginPage loginPage = new LoginPage();
    public ApiHelper apiHelper = new ApiHelper();
    public VkApiClient vk;
    public UserActor userActor;
    SoftAssert softAssert = new SoftAssert();
    public LikesHelper likesHelper = new LikesHelper();

    @BeforeClass
    public void init() {
        String code;
        try {
            loginPage.init();
            //get code
            code = loginPage.openUrl(rb.getString("GET_CODE_URI"))
                    .allowAccess()
                    .getCode();
        } finally {
            loginPage.quit();
        }
        // get api client
        vk = apiHelper.getApiClient();
        // get access token
        UserAuthResponse userAuthResponse = apiHelper.getUserResponseData(vk, code);
        userActor = new UserActor(userAuthResponse.getUserId(), userAuthResponse.getAccessToken());
    }

}
