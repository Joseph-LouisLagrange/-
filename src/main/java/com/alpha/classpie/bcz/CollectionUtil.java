package com.alpha.classpie.bcz;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionUtil {
    public static  String  toString(Collection<String> collection,String delimiter){
        StringBuilder stringBuffer=new StringBuilder();
        return String.join(delimiter, collection);
    }

    public static void mixedUpset(List<?> list){
        Collections.shuffle(list);
    }
}
