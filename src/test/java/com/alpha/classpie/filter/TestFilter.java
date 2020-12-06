package com.alpha.classpie.filter;

import java.util.Arrays;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/29
 */
public class TestFilter {
    public static void main(String[] args) {
        Filter filter = new Filter();
        MoreThan100Condition moreThan100Condition = new MoreThan100Condition();
        moreThan100Condition.setActive(true);
        OddNumberCondition oddNumberCondition = new OddNumberCondition();
        oddNumberCondition.setActive(true);
        filter.addCondition(moreThan100Condition);
        filter.addCondition(oddNumberCondition);
        List<Integer> result=filter.getResult(Arrays.asList(1,2,6,3,5,200,69,560,501,851));
        result.forEach(System.out::println);
    }
}
