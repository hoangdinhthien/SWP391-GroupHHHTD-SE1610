/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Thien's
 */
public class CandidateDTO {
    private String can_id;
    private String job_id;
    private int majorId;
    private String email;
    private String name;
    private String can_cv;
    private String phone;
    private int isStatus;

    public CandidateDTO() {
    }

    public CandidateDTO(String can_id, String job_id, int majorId, String email, String name, String can_cv, String phone, int isStatus) {
        this.can_id = can_id;
        this.job_id = job_id;
        this.majorId = majorId;
        this.email = email;
        this.name = name;
        this.can_cv = can_cv;
        this.phone = phone;
        this.isStatus = isStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getId() {
        return can_id;
    }

    public void setId(String can_id) {
        this.can_id = can_id;
    }

    public String getJobId() {
        return job_id;
    }

    public void setJobId(String job_id) {
        this.job_id = job_id;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCanCv() {
        return can_cv;
    }

    public void setCanCv(String can_cv) {
        this.can_cv = can_cv;
    }

    public int getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(int isStatus) {
        this.isStatus = isStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
