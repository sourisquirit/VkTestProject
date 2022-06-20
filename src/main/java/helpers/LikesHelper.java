package helpers;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.likes.Type;
import com.vk.api.sdk.objects.likes.responses.AddResponse;
import com.vk.api.sdk.objects.likes.responses.DeleteResponse;
import com.vk.api.sdk.objects.likes.responses.GetListResponse;
import io.qameta.allure.Step;

import java.util.logging.Logger;

public class LikesHelper {
    private static final Logger logger = Logger.getLogger(LikesHelper.class.getName());

    @Step("Add like with type {type}, itemId {itemId} and ownerId {ownerId}")
    public AddResponse addLikes(VkApiClient vk, UserActor userActor, Type type, int itemId, int ownerId) throws ClientException, ApiException {
        logger.info("add like: " + type + ", " + itemId + ", " + ownerId);
        return vk.likes().add(userActor, type, itemId)
                .ownerId(ownerId)
                .execute();
    }

    @Step("Delete like with type {type}, itemId {itemId} and ownerId {ownerId}")
    public DeleteResponse deleteLikes(VkApiClient vk, UserActor userActor, Type type, int itemId, int ownerId) throws ClientException, ApiException {
        logger.info("delete like: " + type + ", " + itemId + ", " + ownerId);
        return vk.likes().delete(userActor, type, itemId)
                .ownerId(ownerId)
                .execute();
    }

    @Step("Get list of likes with type {type}, itemId {itemId} and ownerId {ownerId}")
    public GetListResponse getListLikes(VkApiClient vk, UserActor userActor, Type type, int itemId, int ownerId) throws ClientException, ApiException {
        logger.info("get list of likes: " + type + ", " + itemId + ", " + ownerId);
        return vk.likes().getList(userActor, type)
                .ownerId(ownerId)
                .itemId(itemId)
//                    .friendsOnly(GetListFriendsOnly._1)
                .skipOwn(true)
//                    .extended(true) //  throws java.lang.IllegalStateException: Expected NUMBER but was BEGIN_OBJECT at path $.items[0] on execute
                .execute();
    }
}
