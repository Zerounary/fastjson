package jp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 {
 "名称": "杂牌书包",
 "价格": 10,
 "购买日期": 20150101,
 "口袋": {
 "正面第一个口袋": [""],
 "正面第二个口袋": [""],
 "正面第三个口袋": [""],
 "主口袋": [""],
 "主口内袋": [""],
 "左侧口袋": [""],
 "右侧口袋": [""]
 }
 }
 */
public class JPTest {
    public static void main(String[] args) {
        String jsonSegment = "[{\r\n" +
                "	\"名称\": \"脆皮青豆\",\r\n" +
                "	\"价格\": 10,\r\n" +
                "	\"配料\": [\"豌豆\", \"棕榈油\", \"白砂糖\", \"食用盐\", \"玉米淀粉\"],\r\n" +
                "	\"数字\": [1,-1,3]\r\n" +
                "}, {\r\n" +
                "	\"名称\": \"绿葡萄干\",\r\n" +
                "	\"价格\": 20,\r\n" +
                "	\"配料\": [\"无核葡萄干\"],\r\n" +
                "	\"数字\": [1,6,3]\r\n" +
                "}, {\r\n" +
                "	\"名称\": \"素肉片\",\r\n" +
                "	\"价格\": 30,\r\n" +
                "	\"配料\": [\"大豆分离蛋白\", \"水\", \"植物油\", \"酿造酱油\", \"白砂糖\", \"食用盐\", \"孜然\", \"香辛料\"],\r\n" +
                "	\"数字\": [1,2,3]\r\n" +
                "}]";
        System.out.println(jsonSegment);
        JSONArray segment = (JSONArray) JSONArray.parseArray(jsonSegment);
        System.out.println("数组 - 序号：" + JSONPath.eval(segment, "$[-1]['名称','价格']")); //脆皮青豆
        System.out.println("数组 - like：" + JSONPath.eval(segment, "$[名称 like '素%片']['名称','价格']")); //脆皮青豆
        System.out.println("数组 - IN：" + JSONPath.eval(segment, "$[名称 in ('脆皮青豆')]['名称','价格']")); //脆皮青豆
        System.out.println("数组 - BETWEEN：" + JSONPath.eval(segment, "$[价格 between 10 and 25]")); //脆皮青豆  ['名称','价格']

        System.out.println("数组 - LENGTH - 无过滤：" + JSONPath.eval(segment, "$.length()")); //无过滤
        System.out.println("数组 - MAX - 无过滤：" + JSONPath.eval(segment, "$[-1].数字.max()")); //无过滤
        System.out.println("数组 - MIN - 无过滤：" + JSONPath.eval(segment, "$[-1].数字.min()")); //无过滤
        System.out.println("数组 - LENGTH - 有过滤：" + JSONPath.eval(segment, "$[价格 > 1].数字.length()"));
        System.out.println("数组 - MAX - 有过滤：" + JSONPath.eval(segment, "$[价格 > 1].数字.max()"));
        System.out.println("数组 - MIN - 有过滤：" + JSONPath.eval(segment, "$[价格 > 1].数字.min()"));


        System.out.println("数组 - 存在 - 有过滤：" + JSONPath.containsValue(segment, "$[名称 = '素肉片'].配料","玉米淀粉"));


//        System.out.println("数组 - MIN - 有过滤：" + JSONPath.arrayAdd(segment, "$[价格 > 1].数字.min()"));

        //多属性提取
        jsonSegment = "{\n" +
                "\t\"姓名\": \"aaa\",\n" +
                "\t\"test\": \"test\"\n" +
                "}";
        JSONObject object = (JSONObject) JSONObject.parseObject(jsonSegment);
        System.out.println("多属性访问：" + JSONPath.eval(jsonSegment, "$['姓名','test']"));

        //多属性提取
        jsonSegment = "[{\n" +
                "\t\"评委\": \"小青豆\",\n" +
                "\t\"年龄\": 31,\n" +
                "\t\"打分\": [10,3,5]\n" +
                "}, {\n" +
                "\t\"评委\": \"葡萄干\",\n" +
                "\t\"年龄\": 21,\n" +
                "\t\"打分\": [7,2,13]\n" +
                "}, {\n" +
                "\t\"评委\": \"小米辣\",\n" +
                "\t\"年龄\": 41,\n" +
                "\t\"打分\": [11,1,4]\n" +
                "}]";
        JSONArray array = (JSONArray) JSONObject.parse(jsonSegment);
        System.out.println("数组 - MIN - 有过滤：" + JSONPath.eval(array, "$[年龄 > 25].打分.min()"));
        System.out.println("数组 - MAX - 有过滤：" + JSONPath.eval(array, "$[年龄 > 25].打分.max()"));
        System.out.println("数组 - LENGTH - 有过滤：" + JSONPath.eval(array, "$[年龄 > 25].size()"));
    }
}
