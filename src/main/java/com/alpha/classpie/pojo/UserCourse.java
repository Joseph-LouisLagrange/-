package com.alpha.classpie.pojo;

import com.alpha.classpie.pojo.user.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserCourse extends UserCourseKey {
    private Role role;

    private Boolean isArchive;

    private Integer courseOrder;

    private String businessCard;

    public UserCourse(Integer userId, Integer courseId, List<Role> roles, Boolean isArchive, Integer courseOrder, String businessCard) {
        super(userId, courseId);
        this.role = roles.get(0);
        this.isArchive = isArchive;
        this.courseOrder = courseOrder;
        this.businessCard = businessCard;

    }

    public UserCourse(Integer userId, Integer courseId, Role role, Boolean isArchive, Integer courseOrder, String businessCard) {
        super(userId, courseId);
        this.role = role;
        this.isArchive = isArchive;
        this.courseOrder = courseOrder;
        this.businessCard = businessCard;

    }

    public UserCourse() {
        super();
    }


    public Boolean getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Boolean isArchive) {
        this.isArchive = isArchive;
    }

    public Integer getCourseOrder() {
        return courseOrder;
    }

    public void setCourseOrder(Integer courseOrder) {
        this.courseOrder = courseOrder;
    }

    public String getBusinessCard() {
        return businessCard;
    }

    public void setBusinessCard(String businessCard) {
        this.businessCard = businessCard;
    }

    public Integer getRoleId(){
        return role.getId();
    }
}
