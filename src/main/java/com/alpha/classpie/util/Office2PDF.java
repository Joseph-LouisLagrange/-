package com.alpha.classpie.util;


import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.document.DefaultDocumentFormatRegistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @author 杨能
 * @create 2020/11/1
 */
@Component
@Async
public class Office2PDF {

    @Autowired
    private DocumentConverter converter;

    public void toPdf(File officeFile,String mimeType , File dest){
        converter.convert(officeFile).as(Objects.requireNonNull(DefaultDocumentFormatRegistry.getFormatByExtension(mimeType)))
                .to(dest).as(DefaultDocumentFormatRegistry.PDF);
    }

    public void toPdf(InputStream inputStream,String mimeType,OutputStream outputStream){
        converter.convert(inputStream,true)
                .as(Objects.requireNonNull(DefaultDocumentFormatRegistry.getFormatByMediaType(mimeType)))
                .to(outputStream).as(DefaultDocumentFormatRegistry.PDF);
    }
}
