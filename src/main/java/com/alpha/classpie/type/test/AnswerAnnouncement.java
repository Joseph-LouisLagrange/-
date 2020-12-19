package com.alpha.classpie.type.test;


public enum AnswerAnnouncement implements BaseEnumInf<Integer> {
    NO_ANNOUNCE(0),ANNOUNCE_AFTER_SUBMIT(1),ANNOUNCE_AFTER_DEADLINE(2);
    int i;
    AnswerAnnouncement(int i){
        this.i=i;
    }
    @Override
    public Integer getValue() {
        return this.i;
    }
    @Override
    public BaseEnumInf<Integer> fromValue(Integer value) {
        AnswerAnnouncement[] values = values();
        for(AnswerAnnouncement answerAnnouncement:values){
            if(value.equals(answerAnnouncement.getValue())){
                return answerAnnouncement;
            }
        }
        return null;
    }
}
