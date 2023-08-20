package org.zerock.b01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadResultDTO {

    private String uuid;

    private String fileName;

    private boolean img;

    // 나중에 JSON으로 처리될 때는 link라는 속성으로 자동 처리
    public String getLink() {
        if (img) {
            return "s_" + uuid + "_" + fileName;
        }

        return uuid + "_" + fileName;
    }

}
