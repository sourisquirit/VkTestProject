package tests;

import com.vk.api.sdk.exceptions.ApiAccessException;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiParamException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.likes.Type;
import com.vk.api.sdk.objects.likes.responses.AddResponse;
import com.vk.api.sdk.objects.likes.responses.DeleteResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddDeleteLikesTest extends BaseTest {

    @DataProvider
    public Object[][] addLikesTestData() {
        return new Object[][]{
                new Object[]{Type.PHOTO, 457273810, 288302965},
                new Object[]{Type.POST, 1591, 10771},
                new Object[]{Type.VIDEO, 456239105, 5801031},
                new Object[]{Type.PHOTO_COMMENT, 131, 288302965}
        };
    }

    @Test(groups = {"positive"}, dataProvider = "addLikesTestData", priority = 2)
    @Description("Add Likes Test")
    public void addLikesTest(Type type, int itemId, int ownerId) throws ClientException, ApiException {
        AddResponse addResponse = likesHelper.addLikes(vk, userActor, type, itemId, ownerId);
        Assert.assertTrue(addResponse.getLikes() >= 1, "Number of likes after adding likes differs from expected");
    }

    @Test(groups = {"negative"}, expectedExceptions = ApiParamException.class)
    @Description("Add Likes Api Parameters Exception Test")
    public void addLikesApiParamExceptionTest() throws ClientException, ApiException {
        likesHelper.addLikes(vk, userActor, Type.PHOTO, 1111, 288302965);
    }

    @Test(groups = {"positive"}, priority = 3)
    @Description("Delete Likes Test")
    public void deleteLikesTest() throws ClientException, ApiException {
        DeleteResponse deleteResponse = likesHelper.deleteLikes(vk, userActor, Type.PHOTO, 457273810, 288302965);
        Assert.assertNotEquals(deleteResponse.getLikes(), 3, "Number of likes after deleting likes differs from expected");
    }

    @Test(groups = {"negative"}, priority = 1, expectedExceptions = ApiAccessException.class)
    @Description("Delete Likes Access Denied Test")
    public void deleteLikesAccessDeniedTest() throws ClientException, ApiException {
        likesHelper.deleteLikes(vk, userActor, Type.PHOTO, 457273810, 288302965);
    }
}