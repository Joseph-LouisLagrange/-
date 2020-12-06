package com.alpha.classpie.serviceTest;

import com.alpha.classpie.service.inf.FilePlanService;
import com.alpha.classpie.util.Office2PDF;
import org.jodconverter.core.office.OfficeException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

/**
 * @author 杨能
 * @create 2020/12/1
 */
@SpringBootTest
public class BigFileTest {

    @Autowired
    Office2PDF office2PDF;

    @Autowired
    FilePlanService filePlanService;

    @Test
    public void test() throws OfficeException {
        office2PDF.toPdf(filePlanService.getFile(99),"text/plain",new File("dd.pdf"));
    }
}
