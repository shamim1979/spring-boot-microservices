package bd.gov.lims.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static java.nio.charset.StandardCharsets.UTF_8;


public class ResourceUtil {

    private final ResourceLoader resourceLoader;

    @Autowired
    public ResourceUtil(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String keyAsString(String path, String keySpec) throws IOException {
        Resource resource = resourceLoader.getResource(path);
        Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8);
        String key = FileCopyUtils.copyToString(reader);
        return cleanKey(key, keySpec);
    }

    private String cleanKey(String key, String keySpec) {
        return key
                    .replace("-----BEGIN " + keySpec + " KEY-----", "")
                    .replace("-----END " + keySpec + " KEY-----", "")
                    .replaceAll("\\s+", "");
    }
}
