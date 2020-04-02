package stg.template.template.payloads;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/2/19
 * @Todo:
 */
@Data
@AllArgsConstructor
@ApiModel(value = "ListData", description = "ListData")
public class ListData<T> {
    @ApiModelProperty(value = "items in one page", required = true, dataType = "List<T>")
    private List<T> items;

    @ApiModelProperty(value = "the number of all items", required = true, dataType = "Integer")
    private int total;
}
