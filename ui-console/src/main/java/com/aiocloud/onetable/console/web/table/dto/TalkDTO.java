package com.aiocloud.onetable.console.web.table.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: yangbin
 */
@Data
public class TalkDTO {

    private List<String> columnList;
    private List<List> dataList;

}
