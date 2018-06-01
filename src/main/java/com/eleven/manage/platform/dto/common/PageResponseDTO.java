package com.eleven.manage.platform.dto.common;

/**
 * @author ywl
 * @date 2018/5/25
 **/
public class PageResponseDTO extends ResponseDTO {

    private PaginationDTO paginationDTO;

    public PaginationDTO getPaginationDTO() {
        return paginationDTO;
    }

    public void setPaginationDTO(PaginationDTO paginationDTO) {
        this.paginationDTO = paginationDTO;
    }
}
