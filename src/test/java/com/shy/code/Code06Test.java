package com.shy.code;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Code06Test {


    @Test
    public void test1() {

        // [{"uid":909, "status": 1},{"uid":909, "status": w},{"uid":908, "status": 1}]


    }

    public void process(List<Map<String, Integer>> list) {

        // 获取核心线程数


        List<Map<Integer, List<Map<String, Integer>>>> res = new ArrayList<>();

        // 1. 对list进行分组
        Map<Integer, List<Map<String, Integer>>> group = list.stream().collect(Collectors.groupingBy(map -> map.get("uid")));
        // 2. 对每个分组按照大小排序
        List<Map<String, Integer>> collect = group.entrySet().stream().sorted((o1, o2) -> o2.getValue().size() - o1.getValue().size()).flatMap(o -> o.getValue().stream()).collect(Collectors.toList());
        // 3. 拿到最大的uid
        Integer uid = collect.get(0).get("uid");
        // 生成分区 添加倒res
        // 拿到最大的所有数据量
        List<Map<String, Integer>> maps = group.get(uid);
        Map<Integer, List<Map<String, Integer>>> o1 = new HashMap<>();
        o1.put(uid, maps);
        res.add(o1);
        // 接下俩生成剩下的分区
        for (int i = 1; i < collect.size(); i++) {
            Map<Integer, List<Map<String, Integer>>> o2 = new HashMap<>();
            // 拿到第二大的uid


        }

    }

}
