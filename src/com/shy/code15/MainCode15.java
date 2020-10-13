package com.shy.code15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 石皓岩
 * @date 2020/10/13 9:51
 * 描述:携程笔试
 */
public class MainCode15 {

    public static void main(String[] args) {
        String[] lines = new String[]{
                "5",
                "abc[10]",
                "b[20]",
                "a[30]",
                "a[5]=10",
                "b[a[abc[9]]]=12"
        };
        System.out.println(validateArrayUsages(lines));
    }

    static int validateArrayUsages(String[] lines) {
        if (lines.length < 1) {
            return 0;
        }
        Integer n = Integer.valueOf(lines[0]);
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>(n);
        String reg = "[^0-9]";
        for (int i = 1; i <= n; i++) {
            if (lines[i].contains("[") && lines[i].contains("]") && !lines[i].contains("]=")) {
                // 这样就表示这行是定义了 ，需要把数组大小拿出来
                String[] split = lines[i].split("\\[");
                list.add(split[0]);
                Pattern compile = Pattern.compile(reg);
                Matcher matcher = compile.matcher(split[1]);
                String[] s1 = matcher.replaceAll("").split("\\s+");
                map.put(split[0], Integer.valueOf(s1[0]));
            } else {
                String[] split = lines[i].split("=");
                for (String s : split) {

                    String[] split1 = s.split("\\[");
                    if(split1.length < 2){
                        continue;
                    }
                    Pattern compile = Pattern.compile(reg);
                    Matcher matcher = compile.matcher(s);
                    String[] s1 = matcher.replaceAll("").split("\\s+");
                    if (map.containsKey( split1[split1.length-2])) {
                        Integer integer = map.get( split1[split1.length-2]);
                        if (integer <= Integer.valueOf(s1[0])) {
                            return i;
                        }
                    }
                }
            }
        }


        return 0;
    }

}
