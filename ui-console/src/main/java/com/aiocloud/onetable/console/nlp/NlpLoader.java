package com.aiocloud.onetable.console.nlp;

import com.aiocloud.onetable.console.nlp.cache.Dictionary;
import com.aiocloud.onetable.console.nlp.cache.TableMap;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class NlpLoader {
    private static Logger logger = LoggerFactory.getLogger(NlpLoader.class);

    @PostConstruct
    public void init(){
        logger.info("加载nlp语料...");
        try {
            Dictionary.init();
            TableMap.map.forEach((key, value)->{
                CustomDictionary.add(key, "column");
            });
        } catch (Exception e) {
            logger.info("加载nlp语料异常", e);
        }
    }
}
