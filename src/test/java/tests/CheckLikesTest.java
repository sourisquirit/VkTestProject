package tests;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.likes.Type;
import com.vk.api.sdk.objects.likes.responses.GetListResponse;
import com.vk.api.sdk.objects.likes.responses.IsLikedResponse;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckLikesTest extends BaseTest {

    @Test(groups = {"positive"})
    @Description("Get List Likes Test")
    public void getListLikesTest() throws ClientException, ApiException {
        GetListResponse getListResponse = likesHelper.getListLikes(vk, userActor, Type.POST, 2641, 1572254);
        softAssert.assertNotNull(getListResponse.getItems(), "Items are not present");
        softAssert.assertTrue(getListResponse.getCount() > 5, "Number of users who liked this post is less or equals 5");
        softAssert.assertTrue(getListResponse.getItems().contains(519904), "List doesnt contain expected item");
        softAssert.assertAll();
    }

    @Test(groups = {"negative"})
    @Description("Get List Likes with Invalid Parameters Test")
    public void getListLikesInvalidParamsTest() {
        Assert.assertThrows(() -> likesHelper.getListLikes(vk, userActor, Type.TEXTPOST, 2641, 1572254));
        Assert.assertThrows(() -> likesHelper.getListLikes(vk, userActor, Type.POST, 0, 1572254));
    }

    @Test(groups = {"positive"})
    @Description("Get Is Liked Test")
    public void isLikedTest() throws ClientException, ApiException {
        IsLikedResponse isLikedResponse = vk.likes().isLiked(userActor, Type.POST, 13361)
                .ownerId(-142178011)
                .userId(10771)
                .execute();
        softAssert.assertTrue(isLikedResponse.isLiked(), "Post is not liked by user");
        softAssert.assertFalse(isLikedResponse.isCopied(), "Post is reposted by user");
        softAssert.assertAll();
    }
}
