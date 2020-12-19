package com.alpha.classpie.filter;

import java.util.ArrayList;
import java.util.List;


public class Filter {
    List<Condition> conditionList=new ArrayList<>();
    public void addCondition(Condition condition){
        conditionList.add(condition);
    }

    public List<Integer> getResult(List<Integer> list){
        List<Integer> result=new ArrayList<>();
        for(Integer i:list){
            boolean f=true;
            for(Condition condition:conditionList){
                f= condition.isActive() && condition.doTest(i);
                if(!f) break;
            }
            if(f){
                result.add(i);
            }
        }
        return result;
    }
}
