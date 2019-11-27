package jp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;

public class JPTest {
    public static void main(String[] args) {
        String jsonSegment = "[{\r\n" +
                "	\"名称\": \"脆皮青豆\",\r\n" +
                "	\"配料\": [\"豌豆\", \"棕榈油\", \"白砂糖\", \"食用盐\", \"玉米淀粉\"]\r\n" +
                "}, {\r\n" +
                "	\"名称\": \"绿葡萄干\",\r\n" +
                "	\"配料\": [\"无核葡萄干\"]\r\n" +
                "}, {\r\n" +
                "	\"名称\": \"素肉片\",\r\n" +
                "	\"配料\": [\"大豆分离蛋白\", \"水\", \"植物油\", \"酿造酱油\", \"白砂糖\", \"食用盐\", \"孜然\", \"香辛料\"]\r\n" +
                "}]";
        JSONArray segment = (JSONArray) JSONArray.parseArray(jsonSegment);
        System.out.println("数组IN：" + JSONPath.eval(segment, "$[名称 in ('素肉片', '脆皮青豆')]")); //脆皮青豆
    }
}
