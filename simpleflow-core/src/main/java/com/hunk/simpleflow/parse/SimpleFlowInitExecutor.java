package com.hunk.simpleflow.parse;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ArrayUtil;

import com.hunk.simpleflow.error.FileNotFoundException;
import com.hunk.simpleflow.error.FileParseException;
import com.hunk.simpleflow.property.SimpleFlowConfig;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

/**
 * Created on 2023/5/18.
 *
 * @author norbit
 *     <p>初始化flow流程
 */
@Slf4j
public class SimpleFlowInitExecutor {

    private SimpleFlowConfig flowConfig;

    public void setFlowConfig(SimpleFlowConfig flowConfig) {
        this.flowConfig = flowConfig;
    }

    public void init() {
        Resource[] classPath = getClassPath();
        if (ArrayUtil.isEmpty(classPath)) {
            log.info("未发现配置文件");
            throw new FileNotFoundException("No process documentation found !");
        }
        for (Resource resource : classPath) {
            try {
                String content = IoUtil.readUtf8(resource.getInputStream());
                FlowDispatchInit.of(content, flowConfig.getFileType()).init();
            } catch (Exception e) {
                log.error("流程文件加载错误", e);
                throw new FileParseException("Process file load exception !");
            }
        }
    }

    public Resource[] getClassPath() {
        try {
            return new PathMatchingResourcePatternResolver().getResources(locationPattern());
        } catch (IOException e) {
            log.error("加载配置文件异常", e);
            return null;
        }
    }

    private String locationPattern() {
        return String.format(
                "%s%s/*.%s",
                ResourceUtils.CLASSPATH_URL_PREFIX,
                flowConfig.getRuleSource(),
                flowConfig.getFileTypeToStr());
    }
}
