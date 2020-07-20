package com.aaa.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/16 19:14
 * @description:
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BaseModel implements Serializable {
    @Id
    @NotNull
    private Long id;

    @Column(name = "CREATE_TIME")
    @Max(value = 100, message = "时间长度最长不能超过100")
    private String createTime;

    @Column(name = "MODIFY_TIME")
    @Max(value = 100, message = "时间长度最长不能超过100")
    private String modifyTime;
}
