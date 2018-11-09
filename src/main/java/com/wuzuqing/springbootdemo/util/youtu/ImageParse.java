package com.wuzuqing.springbootdemo.util.youtu;


import com.alibaba.fastjson.JSON;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/17 21:35
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/17$
 * @updateDes ${TODO}
 */

public class ImageParse {


    public static List<Result.ItemsBean> getSyncData(Object data) {
        JSONObject respose = null;
        try {
            Image image;
            if (data instanceof String) {
                respose = StaticVal.getYoutu().GeneralOcr(data.toString());
            } else {
                respose = StaticVal.getYoutu().GeneralOcr((byte[]) data);
            }
            Result result = JSON.parseObject(respose.toString(), Result.class);
            return result.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void getSyncData(String file, Call call) {
        call.call(getSyncData(file));
    }

    public static void getSyncData(byte[] data, Call call) {
        call.call(getSyncData(data));
    }

    public interface Call {
        void call(List<Result.ItemsBean> result);
    }

}
