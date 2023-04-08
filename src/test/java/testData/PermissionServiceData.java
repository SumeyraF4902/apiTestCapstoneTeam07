package testData;

import java.util.HashMap;

public class PermissionServiceData {


    //   public HashMap permessionPostData()
    public HashMap<String, Object> expecdetData(Integer id, String resource, String  action, Integer app_id )   {

    /*{

         "id": 719,
        "resource": "book",
            "action": "deneme, write etc.",
            "app_id": 2
    }*/

        HashMap<String,Object>expecdetData=new HashMap<>();

        expecdetData.put("id",id);
        expecdetData.put("resource",resource);
        expecdetData.put("action",action);
        expecdetData.put("app_id",app_id);


        return expecdetData;
    }








}
