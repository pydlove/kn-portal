package com.aiocloud.onetable.console.web.table.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PieVO
 * @Description 饼图数据对象
 * @Author shux
 * @Date 2025/2/22 23:02
 */
@Data
public class PieVO {

      /**
       * 饼图的标签列表
       */
      private List<String> labels;

      /**
       * 饼图的值列表
       */
      private List<String> values;

      /**
       * 饼图的键值对映射
       */
      private Map<String, String> dataPairs;

      public PieVO() {
            this.labels = new ArrayList<>();
            this.values = new ArrayList<>();
            this.dataPairs = new HashMap<>();
      }
}