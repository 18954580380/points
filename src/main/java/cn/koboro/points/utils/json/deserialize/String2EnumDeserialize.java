package cn.koboro.points.utils.json.deserialize;

import cn.koboro.points.enums.ResultEnum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import cn.koboro.points.enums.ResultEnum;
import cn.koboro.points.utils.EnumUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author xdw
 */
public class String2EnumDeserialize extends JsonDeserializer<ResultEnum> {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public ResultEnum deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return EnumUtil.getByCode(jp.getIntValue(), ResultEnum.class);
    }

}
