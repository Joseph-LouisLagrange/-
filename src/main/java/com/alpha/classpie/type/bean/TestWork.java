package com.alpha.classpie.type.bean;

import com.alpha.classpie.pojo.file.FilePlan;

import java.util.Date;
import java.util.List;


public class TestWork {
    String title;
    String resume;
    List<FilePlan> filePlans;
    Date createdDatetime;
    List<TestWorkElement> testWorkElements;
    //测试设置
    TestMainConfig testMainConfig;
    //时间设置
    TestTimeConfig testTimeConfig;

}
