package com.blazemeter;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class SharedfolderUtilTest {

    final static String apiKey = "your-bzm-api-key";
    final static String apiSecret = "your-bzm-api-secret";
    final static String apiURL = "your.domain.blazemeter.net/api/v4/folders/{folder_id}/files";
    final static String filePath = "/path/to/your/file";

    @Test
    public final void whenExecutingFileUploadWithBasicAuthenticationEnabled_thenSuccess() throws IOException {
        SharedfolderUtil sharedfolderUtil = new SharedfolderUtil(apiKey, apiSecret);
        String response = sharedfolderUtil.uploadFile(apiURL, filePath);
        Assert.assertNotNull(response);
    }

    @Test
    public final void whenExecutingListFilesInFolderWithBasicAuthenticationEnabled_thenSuccess() throws IOException {
        SharedfolderUtil sharedfolderUtil = new SharedfolderUtil(apiKey, apiSecret);
        String response = sharedfolderUtil.uploadFile(apiURL, filePath);
        Assert.assertNotNull(response);
    }
}
