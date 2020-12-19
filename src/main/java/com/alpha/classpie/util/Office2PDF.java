package com.alpha.classpie.util;


import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.document.DefaultDocumentFormatRegistry;

import org.jodconverter.core.document.DocumentFormat;
import org.jodconverter.core.document.DocumentFormatRegistry;
import org.jodconverter.core.document.SimpleDocumentFormatRegistry;
import org.jodconverter.core.office.OfficeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;


@Component
public class Office2PDF {

    @Autowired
    private DocumentConverter converter;

    public void toPdf(File officeFile,String mimeType , File dest) throws OfficeException {
        converter.convert(officeFile).as(Objects.requireNonNull(DefaultDocumentFormatRegistry.getFormatByMediaType(mimeType)))
                .to(dest).as(DefaultDocumentFormatRegistry.PDF).execute();
    }

    public void toPdf(InputStream inputStream,String mimeType,OutputStream outputStream) throws OfficeException {
        converter.convert(inputStream,true)
                .as(Objects.requireNonNull(DefaultDocumentFormatRegistry.getFormatByMediaType(mimeType)))
                .to(outputStream).as(DefaultDocumentFormatRegistry.PDF).execute();
    }

    public void toPdf(File file,String mimeType,OutputStream outputStream) throws OfficeException {
        //SimpleDocumentFormatRegistry simpleDocumentFormatRegistry = new SimpleDocumentFormatRegistry();
        DocumentFormat formatByMediaType = DefaultDocumentFormatRegistry.getFormatByMediaType(mimeType);
        if(formatByMediaType==null){
            formatByMediaType=DefaultDocumentFormatRegistry.TXT;
        }
        converter.convert(file)
                .as(formatByMediaType)
                .to(outputStream,true)
                .as(DefaultDocumentFormatRegistry.PDF).execute();
    }
}
