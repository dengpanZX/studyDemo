package com.zxxk.kg.listview_test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitValue {
    /**
     * ģ�����ݷ�ҳ���أ�
     * @param pageStart  ��ʼ��
     * @param pageSize   ÿҳ��ʾ��Ŀ
     * @return
     */
    public static List<Map<String,Object>> initValue(int pageStart,int pageSize){
        Map<String,Object> map;
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        for(int i=0;i<pageSize;i++){
            map=new HashMap<String,Object>();
            map.put("text", "zxxk_demo�޸�");
            map.put("title", pageStart+"_ListView��ҳ��ʾ");
            ++pageStart;
            list.add(map);
        }
        return list;
    }
    
    
    /**
     * ˢ�µ�����
     * @param pageStart
     * @param pageSize
     * @param a ��ʾ��ˢ�µ�����
     * @return
     */
    public static List<Map<String,Object>> initValue(int pageStart,int pageSize , List<Map<String,Object>> data){
    	Map<String,Object> map;
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        for(int i=0;i<pageSize;i++){
            map=new HashMap<String,Object>();
            map.put("text", "zxxk_demo");
            map.put("title", pageStart +"_ListViewˢ��֮�������");
            ++pageStart;
            list.add(map);
        }
        return list;
    }
}