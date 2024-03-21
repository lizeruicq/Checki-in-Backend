package com.tianji.ai;
import com.aliyun.bailian20230601.Client;
import com.aliyun.bailian20230601.models.CreateTokenRequest;
import com.aliyun.bailian20230601.models.CreateTokenResponse;
import com.aliyun.bailian20230601.models.CreateTokenResponseBody;
import com.aliyun.teaopenapi.models.Config;
import org.apache.commons.lang3.StringUtils;


public class testToken {

    public void createToken() {
        String accessKeyId = "";
        String accessKeySecret = "";
        String agentKey = "";
        String endpoint = "bailian.cn-beijing.aliyuncs.com";

        Config config = new Config().setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret)
                .setEndpoint(endpoint);

        try {
            Client client = new Client(config);
            CreateTokenRequest request = new CreateTokenRequest().setAgentKey(agentKey);
            CreateTokenResponse response = client.createToken(request);
            CreateTokenResponseBody body = response.getBody();
            if (body == null) {
                String error = "create token error";
                throw new RuntimeException(error);
            }

            if (!body.success) {
                String requestId = body.requestId;
                if (StringUtils.isBlank(requestId)) {
                    requestId = response.getHeaders().get("x-acs-request-id");
                }

                String error = "failed to create token, reason: " + body.message + " RequestId: " + requestId;
                throw new RuntimeException(error);
            }

            CreateTokenResponseBody.CreateTokenResponseBodyData data = body.getData();
            if (data == null) {
                throw new RuntimeException("create token error, data is null");
            }

            System.out.printf("token: %s, expiredTime : %d", data.getToken(), data.getExpiredTime());
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
