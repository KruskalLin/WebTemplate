package stg.template.template.payloads;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Sort;
/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/2/19
 * @Todo:
 */
@Data
@AllArgsConstructor
@ApiModel(value = "FilterRequest", description = "Page filter request")
public class FilterRequest {
    @NotNull
    @ApiModelProperty(value = "page number", required = true, dataType = "Integer")
    private int page;

    @NotNull
    @ApiModelProperty(value = "page size", required = true, dataType = "Integer")
    private int size;

    @ApiModelProperty(value = "item sort indicator: ASC or DESC", dataType = "String")
    private String sort;

    @ApiModelProperty(value = "sort or search title in this property", dataType = "String")
    private String property;

    @ApiModelProperty(value = "search by title", dataType = "String")
    private String title;

}
