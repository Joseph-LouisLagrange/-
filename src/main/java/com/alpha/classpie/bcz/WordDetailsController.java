package com.alpha.classpie.bcz;

import cn.hutool.core.io.FileUtil;
import com.alpha.classpie.bcz.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨能
 * @create 2020/12/2
 */
@RestController
public class WordDetailsController {
    String base="C:\\Users\\ASUS\\Desktop\\百词斩";
    @Autowired
    WordDetailsMapper wordDetailsMapper;

    @Autowired
    GraphicExampleSentencesMapper graphicExampleSentencesMapper;
    @Autowired
    WordEnglishInterpretationMapper wordEnglishInterpretationMapper;

    @Autowired
    TranslationMapper translationMapper;

    @Autowired
    WordPhoneticTranscriptionMapper wordPhoneticTranscriptionMapper;

    @RequestMapping("/getWordDetails")
    public List<WordDetails> getWordDetails(@RequestParam("count") int count){
        PageHelper.startPage(0,count);
        List<WordDetails> wordDetails = wordDetailsMapper.selectByExample(new WordDetailsExample());
        return PageInfo.of(wordDetails).getList().stream().map(this::doWrap).collect(Collectors.toList());
    }

    @RequestMapping("/getGraphicExampleSentencesImage")
    public void getGraphicExampleSentencesImage(String imagePath, HttpServletResponse response) throws IOException {
        FileInputStream fileInputStream=new FileInputStream(base+"\\图文例句\\"+imagePath);
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes=new byte[1024];
        int length=-1;
        while ((length=fileInputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,length);
        }
        response.flushBuffer();
        fileInputStream.close();
        outputStream.close();
    }

    @RequestMapping("/getPictogramImage")
    public void getPictogramImagePath(String imagePath,HttpServletResponse response) throws IOException {
        FileInputStream fileInputStream=new FileInputStream(base+"\\象形文字\\"+imagePath);
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes=new byte[1024];
        int length=-1;
        while ((length=fileInputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,length);
        }
        response.flushBuffer();
        fileInputStream.close();
        outputStream.close();
    }

    private WordDetails doWrap(WordDetails wordDetails){
        WordDetailsWrapper wordDetailsWrapper = new WordDetailsWrapper();
        BeanUtils.copyProperties(wordDetails,wordDetailsWrapper);

        GraphicExampleSentencesExample graphicExampleSentencesExample = new GraphicExampleSentencesExample();
        graphicExampleSentencesExample.createCriteria().andWordDetailsIdEqualTo(wordDetails.getId());
        GraphicExampleSentences graphicExampleSentences = graphicExampleSentencesMapper.selectByExample(graphicExampleSentencesExample).get(0);
        wordDetailsWrapper.setGraphicExampleSentences(graphicExampleSentences);//图文例句

        WordEnglishInterpretationExample wordEnglishInterpretationExample = new WordEnglishInterpretationExample();
        wordEnglishInterpretationExample.createCriteria().andWordIdEqualTo(wordDetails.getId());
        List<WordEnglishInterpretationKey> wordEnglishInterpretationKeys = wordEnglishInterpretationMapper.selectByExample(wordEnglishInterpretationExample);
        List<String> englishInterpretation = wordEnglishInterpretationKeys.stream().map(WordEnglishInterpretationKey::getEnglishInterpretation).collect(Collectors.toList());
        wordDetailsWrapper.setEnglishInterpretation(englishInterpretation);//英文释义

        TranslationExample translationExample = new TranslationExample();
        translationExample.createCriteria().andWordDetailsIdEqualTo(wordDetails.getId());
        List<Translation> translations = translationMapper.selectByExample(translationExample);
        List<String> translationsString = translations.stream().map(Translation::getTranslation).collect(Collectors.toList());
        wordDetailsWrapper.setTranslations(translationsString);//设置翻译

        WordPhoneticTranscriptionExample wordPhoneticTranscriptionExample = new WordPhoneticTranscriptionExample();
        wordPhoneticTranscriptionExample.createCriteria().andWordDetailsIdEqualTo(wordDetails.getId());
        List<WordPhoneticTranscription> wordPhoneticTranscriptions = wordPhoneticTranscriptionMapper.selectByExample(wordPhoneticTranscriptionExample);
        List<String> phoneticTranscriptions = wordPhoneticTranscriptions.stream().map(WordPhoneticTranscription::getPhoneticTranscription).collect(Collectors.toList());
        wordDetailsWrapper.setPhoneticTranscriptions(phoneticTranscriptions);//设置音标
        return wordDetailsWrapper;
    }
}
