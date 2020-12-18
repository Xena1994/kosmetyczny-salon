package com.example.salon_kosmetyczny.models.commands;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class SimpleFilter implements SearchFilter{
    private String phrase;

    @Override
    public boolean isEmpty() {
        return StringUtils.isEmpty(phrase);
    }

    @Override
    public void clear() {
        phrase = "";
    }

    public String getPhraseLIKE(){
        if(StringUtils.isEmpty(phrase)) {
            return null;
        }else{
            return "%"+phrase+"%";
        }
    }
}
