package com.aiocloud.onetable.console.nlp;

import com.aiocloud.onetable.console.constant.DeleteConstat;
import com.aiocloud.onetable.console.nlp.cache.TableInfoCache;
import com.aiocloud.onetable.mysql.table.mapper.ColumnInfoMapper;
import com.aiocloud.onetable.mysql.table.mapper.DictionaryConMapper;
import com.aiocloud.onetable.mysql.table.po.ColumnInfoPO;
import com.aiocloud.onetable.mysql.table.po.DictionaryConfPO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Service
public class NlpLoader {
    private static Logger logger = LoggerFactory.getLogger(NlpLoader.class);

    @Resource
    private ColumnInfoMapper columnInfoMapper;

    @Resource
    private DictionaryConMapper dictionaryConMapper;

    @PostConstruct
    public void init(){
        logger.info("加载nlp语料...");
        try {
            List<ColumnInfoPO> columnInfoPOList = columnInfoMapper.selectList(new LambdaQueryWrapper<ColumnInfoPO>().eq(ColumnInfoPO::getDeleteFlag, DeleteConstat.NO_DELETE));
            List<DictionaryConfPO> dictionaryConfPOList = dictionaryConMapper.selectList(new LambdaQueryWrapper<DictionaryConfPO>().eq(DictionaryConfPO::getDeleteFlag, DeleteConstat.NO_DELETE));
            if (dictionaryConfPOList != null && dictionaryConfPOList.size() > 0){
                for (DictionaryConfPO dictionaryConfPO : dictionaryConfPOList) {
                    CustomDictionary.add(dictionaryConfPO.getWordName(), dictionaryConfPO.getWordNature());
                }
            }
            if (columnInfoPOList != null && columnInfoPOList.size() > 0){
                for (ColumnInfoPO columnInfoPO : columnInfoPOList) {
                    TableInfoCache.put(columnInfoPO.getTableName(), columnInfoPO.getColumnComment(), columnInfoPO.getColumnName());
                    CustomDictionary.add(columnInfoPO.getColumnComment(), "column");
                }
            }
        } catch (Exception e) {
            logger.info("加载nlp语料异常", e);
        }
    }
}
