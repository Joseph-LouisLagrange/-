package com.alpha.classpie.util;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharSet;
import org.apache.http.util.CharsetUtils;
import org.apache.tomcat.util.buf.CharsetUtil;
import org.apache.tomcat.util.digester.DocumentProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author 杨能
 * @create 2020/11/15
 * 计算工具
 */
public class ComputeUtil {
    public static String encodeString(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        bin.close();
        String code = null;
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }
        return code;
    }

    public static long computeWordCount(String fileName) throws Exception {
        String encodeString = encodeString(fileName);
        long size = FileUtils.sizeOf(new File(fileName));
        switch (encodeString){
            case "UTF-8":return size / 3;
            case "Unicode":return size/4;
            case "UTF-16BE":
            case "GBK":
                return size/2;
            default:return size;
        }
    }

    public static long computeWordCount(long size){
        return size/3;
    }

    public static long computeWordCount(MultipartFile...multipartFiles){
        Long size = Arrays.stream(multipartFiles).map(MultipartFile::getSize).reduce(Long::sum).orElse(0L);
        return computeWordCount(size);
    }


    //计算相似率
    public static int computeSimilarityRate(List<String> filenames){
        return 0;
    }
}
