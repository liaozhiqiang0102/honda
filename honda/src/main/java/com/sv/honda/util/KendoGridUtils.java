package com.sv.honda.util;

import com.sv.honda.entity.KendoGridEntiy;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.lang.reflect.Field;
import java.util.*;

/***
 * kendoGrid封装
 */
public class KendoGridUtils {
    private String dataType = "json";
    private String type = "post";
    private String requestEnd = "onRequestEnd";
    private String editor = "customBoolEditor";
    private String data = "function (response) {JSON.parse(response).content;}";
    private String total = "function (response) {JSON.parse(response).total;}";
    private String toolbar = "[{name: \"create\", text: \"新增\"}],";
    private String pageable = "{pageSizes: true,buttonCount: 5,totalPages: true,refresh: true,pageSize: 5,pageSizes: [5, 10, 15, 20],numeric: true,input: true,messages: {display: \"显示 {0}-{1} 条，共 {2} 条\", empty: \"没有数据\", itemsPerPage: \"每页显示数量\", first: \"第一页\", last: \"最后一页\", next: \"下一页\", previous: \"上一页\", page: \"页\", of: \"共 {0}页\"}}";
    private String command = "[{name: \"edit\", text: {edit: \"编辑\", cancel: \"取消\", update: \"更新\"}},{name: \"destroy\", text: \"删除\"}],title: \"操作\",width: \"250px\"}]";
    private String mode = "\"popup\"";


    /**
     * 根据List获取到对应的JSONArray
     *
     * @param list
     * @return
     */
    public static JSONArray getJSONArrayByList(List<?> list) {
        JSONArray jsonArray = new JSONArray();
        if (list == null || list.isEmpty()) {
            return jsonArray;//nerver return null
        }

        for (Object object : list) {
            jsonArray.add(object);
        }
        return jsonArray;
    }


    /**
     * 单个对象的所有属性名
     *
     * @param obj
     * @return
     */
    public static List<Map<String, Object>> getKey(Object obj) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            String name = f.getName().toString(); //得到此属性的名字
            if (name.endsWith("id") || name.endsWith("Id") || name.equals("id") || name.equals("Id")) {
                continue;
            }
            map.put("id", i);
            map.put("name", name);
            list.add(map);
        }
        return list;
    }


    /**
     * 单个对象的id属性名
     *
     * @param obj
     * @return
     */
    public static String getKeyId(Object obj) {
        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        String name = fs[0].getName();

        if (fs[0].getName().endsWith("id") || fs[0].getName().endsWith("Id") || fs[0].getName().equals("id") || fs[0].getName().equals("Id")) {
            fs[0].getName();
        }

        return name;
    }


    /**
     * 得到对象的所有Fields
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> getKeys(Object obj, String[] userFieldValues) {
        Map<String, Object> nameMap = new HashMap<String, Object>();

        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            if (userFieldValues != null) {
                if (Arrays.asList(userFieldValues).contains(String.valueOf(i))) {
                    Map<String, Object> typeMap = new HashMap<String, Object>();
                    Field f = fs[i];
                    f.setAccessible(true); // 设置些属性是可以访问的
                    String type = f.getType().toString();//得到此属性的类型
                    String name = f.getName().toString(); //得到此属性的名字

                    if (type.endsWith("String")) {
                        type = "string";
                    } else if (type.endsWith("int") || type.endsWith("Integer")) {
                        type = "int";
                    } else if (type.endsWith("Date")) {
                        type = "date";
                    } else if (type.endsWith("Boolean")) {
                        type = "boolean";
                    }

                    if (name.endsWith("id") || name.endsWith("Id") || name.endsWith("name") || name.endsWith("Name")) {
                        typeMap.put("nullable", true);
                        Map<String, Object> valMap = new HashMap<String, Object>();

                        Map<String, Object> msgMap = new HashMap<String, Object>();
                        msgMap.put("message", "请输入" + name + "：");
                        valMap.put("required", msgMap);
                        typeMap.put("validation", valMap);
                    }
                    typeMap.put("type", type);
                    nameMap.put(name.replace("\"",""), typeMap);
                } else {
                    continue;
                }
            }
        }

        return nameMap;
    }


    /**
     * 得到对象的所有Columns
     *
     * @param obj
     * @return
     */
    public List<Map<String, Object>> getColumnKeys(Object obj, String[] userFieldValues) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        // 得到类对象
        Class userCla = (Class) obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            if (userFieldValues != null) {
                if (Arrays.asList(userFieldValues).contains(String.valueOf(i))) {
                    Map<String, Object> typeMap = new HashMap<String, Object>();
                    Field f = fs[i];
                    f.setAccessible(true); // 设置些属性是可以访问的
                    String name = f.getName().toString(); //得到此属性的名字

                    String type = f.getType().toString();//得到此属性的类型
                    Map<String, Object> nameMap = new HashMap<String, Object>();
                    if (name.endsWith("id") || name.endsWith("Id") || name.equals("id") || name.equals("Id")) {
                        continue;
                    } else if (type.endsWith("Date")) {
                        nameMap.put("format", "{0:yyyy-MM-dd}");
                    } else if (type.endsWith("Boolean")) {
                        nameMap.put("editor", editor.replace("\"", ""));
                    }

                    nameMap.put("field", name);
                    list.add(nameMap);

                } else {
                    continue;
                }
            }
        }

        return list;
    }

    /**
     * list转json
     *
     * @param list
     * @return
     */
    public static JSONArray ListJson(List<String> list) {
        JSONArray json = JSONArray.fromObject(list);
        return json;
    }


    /**
     * kendoGrid初始化表格数据
     *
     * @return
     */
    public StringBuffer kendoGridInit(String[] fieldValues, KendoGridEntiy kendoGridEntiy, String entityList, String add, String delete, String update) {

        Map<String, Object> mapFields = getKeys(kendoGridEntiy.getObject(), fieldValues);   //初始化时的fields
        List<Map<String, Object>> listColumns = getColumnKeys(kendoGridEntiy.getObject(), fieldValues);    //dataSources的columns
        JSONObject jsonFields = JSONObject.fromObject(mapFields);
        JSONArray jsonColumnsArray = JSONArray.fromObject(listColumns);
        String columnId = getKeyId(kendoGridEntiy.getObject());         //获取datagrid的id项

        String jsonColumns = jsonColumnsArray.toString().replace("[", "").replace("]", "");

        StringBuffer sb = new StringBuffer();
        sb.append("$(function () {");
        sb.append("loadList();});");
//        sb.append("var url = \"" + kendoGridEntiy.getUrl() + "\";");
        sb.append("function loadList() {");   //初始化表格数据
        sb.append("kendo.culture(\"zh-CN\");");
        sb.append("var dataSource = new kendo.data.DataSource({");
        sb.append("transport: {");
        sb.append("read: {url: \"" + entityList + "\"},");
        sb.append("create: {type: \"" + type + "\",url: \"" + add + "\",dataType: \"" + dataType + "\"},");
        sb.append("destroy: {type: \"" + type + "\",url:\"" + delete + "\",dataType: \"" + dataType + "\"},");
        sb.append("update: {type: \"" + type + "\",url: \"" + update + "\",dataType: \"" + dataType + "\"}},");
        sb.append("serverPaging: true,");   //设定服务器来实现分页功能
        sb.append("requestEnd: " + requestEnd + ",");
        sb.append("pageSize: " + kendoGridEntiy.getUserPageSize() + ",");
        sb.append("schema: {");
        sb.append("data: " + data + ","); //响应到页面的数据
        sb.append("total: " + total + ",");//总记录条数
        sb.append("model: {");
        sb.append("id: \"" + columnId + "\",");
        sb.append("fields: " + jsonFields + "");
        sb.append("}}});");
        sb.append("$(\"#grid\").kendoGrid({");
        sb.append("dataSource: dataSource,");
        sb.append("height: " + kendoGridEntiy.getUserHeight() + ",");
        sb.append("sortable: " + kendoGridEntiy.getSortable() + ",");//表示是否支持排序， boolean型
        sb.append("filterable: " + kendoGridEntiy.getFilterable() + ",");//filterable表示过滤，即能进行等于，不等于，包含，已开头等过滤操作，boolean型
        sb.append("toolbar: " + toolbar + "");
        sb.append("pageable: " + pageable + ",");
        sb.append("columns: [" + jsonColumns + ",");
        sb.append("{command: " + command + ",");
        sb.append("editable: {mode: " + mode + ", window: {title: \"" + kendoGridEntiy.getWindowTitle() + "\"}}});}");
        return sb;
    }
}
