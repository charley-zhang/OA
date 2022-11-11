package com.homework.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.homework.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


@ApiModel(description = "职位列表ResponseDTO")
public class PositionListResponseDTO implements Serializable {


    private static final long serialVersionUID = -1852006557920872081L;
    /**
     * 职位id
     */
    @ApiModelProperty(value = "职位id")
    private Integer positionId;
    /**
     * 职位名称
     */
    @ApiModelProperty(value = "职位名称")
    private String positionName;
    /**
     * 办公地点
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    /**
     * 1代表有效,0代表无效
     */
    @ApiModelProperty(value = "1代表有效,0代表无效")
    private Integer status;

//    private String statusName = StatusEnum.statusOf(getStatus()).getStatusName();

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PositionListResponseDTO{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", address='" + createTime + '\'' +
                ", status=" + status +
                '}';
    }
}
