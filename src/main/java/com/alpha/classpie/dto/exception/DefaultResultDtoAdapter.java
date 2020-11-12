package com.alpha.classpie.dto.exception;

import com.alpha.classpie.dto.ResultDtoAdapter;
import org.springframework.stereotype.Component;

/**
 * @author 杨能
 * @create 2020/10/15
 */
@Component("defaultResultDtoAdapter")
public class DefaultResultDtoAdapter implements ResultDtoAdapter<Object> {

    private static final String classCastException="类型Dto不匹配";

    @Override
    public Object toData(ResultDto resultDto) {
        if(resultDto.isSuccess && resultDto instanceof SuccessResultDto){
               return ((SuccessResultDto) resultDto).getData();
        }
        throw new ClassCastException(classCastException);
    }

    @Override
    public ExceptionDto toExceptionDto(ResultDto resultDto) {
        if(!resultDto.isSuccess && resultDto instanceof FailResultDto){
            return ((FailResultDto) resultDto).getException();
        }
        throw new ClassCastException(classCastException);
    }

    @Override
    public ResultDto toResultDto(Object data) {
        ResultDto resultDto=new SuccessResultDto(data);
        resultDto.setSuccess(true);
        return resultDto;
    }

    @Override
    public ResultDto toResultDto(ExceptionDto exceptionDto) {
        ResultDto resultDto=new FailResultDto(exceptionDto);
        resultDto.setSuccess(false);
        return resultDto;
    }

    @Override
    public ResultDto toResultDto(Exception e) {
        if(e instanceof ExceptionDto){
           return toResultDto((ExceptionDto)e);
        }
        //默认的处理
        ExceptionDto exceptionDto=new ExceptionDto(e);
        return toResultDto(exceptionDto);
    }
}
