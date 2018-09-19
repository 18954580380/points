package cn.koboro.points.conterver;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xdw
 */
public class TextMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public TextMappingJackson2HttpMessageConverter() {
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        setSupportedMediaTypes(mediaTypes);// tag6
    }
}