package com.qlcx.web.controller.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.qlcx.common.core.domain.AjaxResult;
import com.qlcx.common.core.domain.CxSelect;

@Controller
@RequestMapping("/demo/form")
public class DemoFormController
{
    private String prefix = "demo/form";

    private final static List<UserFormModel> users = new ArrayList<UserFormModel>();
    {
        users.add(new UserFormModel(1, "1000001", "测试1", "15888888888"));
        users.add(new UserFormModel(2, "1000002", "测试2", "15666666666"));
        users.add(new UserFormModel(3, "1000003", "测试3", "15666666666"));
        users.add(new UserFormModel(4, "1000004", "测试4", "15666666666"));
        users.add(new UserFormModel(5, "1000005", "测试5", "15666666666"));
    }

    /**
     * Button page
    */
    @GetMapping("/button")
    public String button()
    {
        return prefix + "/button";
    }

    /**
     * Drop-down box
     */
    @GetMapping("/select")
    public String select()
    {
        return prefix + "/select";
    }

    /**
     * Timeline
    */
    @GetMapping("/timeline")
    public String timeline()
    {
        return prefix + "/timeline";
    }

    /**
     * Form verification
     */
    @GetMapping("/validate")
    public String validate()
    {
        return prefix + "/validate";
    }

    /**
     * Function extension (including file upload)
     */
    @GetMapping("/jasny")
    public String jasny()
    {
        return prefix + "/jasny";
    }

    /**
     * Drag sort
     */
    @GetMapping("/sortable")
    public String sortable()
    {
        return prefix + "/sortable";
    }

    /**
     * Tabs & panels
    */
    @GetMapping("/tabs_panels")
    public String tabs_panels()
    {
        return prefix + "/tabs_panels";
    }

    /**
     * Grid
     */
    @GetMapping("/grid")
    public String grid()
    {
        return prefix + "/grid";
    }

    /**
     * Form Wizard
     */
    @GetMapping("/wizard")
    public String wizard()
    {
        return prefix + "/wizard";
    }

    /**
     * File Upload
     */
    @GetMapping("/upload")
    public String upload()
    {
        return prefix + "/upload";
    }

    /**
     * Date and time page
     */
    @GetMapping("/datetime")
    public String datetime()
    {
        return prefix + "/datetime";
    }

    /**
     * Left and right components
    */
    @GetMapping("/duallistbox")
    public String duallistbox()
    {
        return prefix + "/duallistbox";
    }

    /**
     * Basic form
     */
    @GetMapping("/basic")
    public String basic()
    {
        return prefix + "/basic";
    }

    /**
     * Card list
     */
    @GetMapping("/cards")
    public String cards()
    {
        return prefix + "/cards";
    }

    /**
     * summernote rich text editor
     */
    @GetMapping("/summernote")
     public String summernote()
     {
         return prefix + "/summernote";
     }

     /**
     * Search autocomplete
    */
    @GetMapping("/autocomplete")
    public String autocomplete()
    {
        return prefix + "/autocomplete";
    }

    /**
     * Multi-level linkage pull-down
    */
    @GetMapping("/cxselect")
    public String cxselect(ModelMap mmap)
    {
        CxSelect cxSelectTB = new CxSelect();
        cxSelectTB.setN("Taobao");
        cxSelectTB.setV("taobao");
        CxSelect cxSelectTm = new CxSelect();
        cxSelectTm.setN("Tmall");
        cxSelectTm.setV("tm");
        CxSelect cxSelectJhs = new CxSelect();
        cxSelectJhs.setN("poly cost-effective");
        cxSelectJhs.setV("jhs");
        List<CxSelect> tmList = new ArrayList<CxSelect>();
        tmList.add(cxSelectTm);
        tmList.add(cxSelectJhs);
        cxSelectTB.setS(tmList);

        CxSelect cxSelectJD = new CxSelect();
        cxSelectJD.setN("Jingdong");
        cxSelectJD.setV("jd");
        CxSelect cxSelectCs = new CxSelect();
        cxSelectCs.setN("Jingdong Supermarket");
        cxSelectCs.setV("jdcs");
        CxSelect cxSelectSx = new CxSelect();
        cxSelectSx.setN("Jingdong Fresh");
        cxSelectSx.setV("jdsx");
        List<CxSelect> jdList = new ArrayList<CxSelect>();
        jdList.add(cxSelectCs);
        jdList.add(cxSelectSx);
        cxSelectJD.setS(jdList);

        List<CxSelect> cxList = new ArrayList<CxSelect>();
        cxList.add(cxSelectTB);
        cxList.add(cxSelectJD);

        mmap.put("data", JSON.toJSON(cxList));
        return prefix + "/cxselect";
    }

    /**
     * Simulation data
     */
    @GetMapping("/cityData")
    @ResponseBody
    public String cityData()
    {
        String data = "[{\"n\":\"Hunan Province\",\"s\":[{\"n\":\"Changsha City\",\"s\":[{\" n\":\"Furong District\"},{\"n\":\"Tianxin District\"},{\"n\":\"Yuelu District\"},{\"n\":\"Kaifu District\"},{\"n\":\"Yuhua District\"},{\"n\":\"Wangcheng District\"},{\"n\":\"Changsha County \"},{\"n\":\"Ningxiang County\"},{\"n\":\"Liuyang City\"}]},{\"n\":\"Zhuzhou City\" ,\"s\":[{\"n\":\"Hetang District\"},{\"n\":\"Lusong District\"},{\"n\":\"Shifeng District\"},{\"n\":\"Tianyuan District\"},{\"n\":\"Zhuzhou County\"},{\"n\":\"尤县\"},{ \"n\":\"Chaling County\"},{\"n\":\"Yanling County\"},{\"n\":\"Liling City\"}]},{\"n\":\"Xiangtan City\",\"s\":[{\"n\":\"Yuhu District\"},{\"n\":\"Yuetang District\"}, {\"n\":\"Xiangtan County\"},{\"n\":\"Xiangxiang City\"},{\"n\":\"Shaoshan City\"}]},{\"n\":\"Hengyang City\",\"s\":[{\"n\":\"Zhuhui District\"},{\"n\":\"Yanfeng District\"},{\"n\":\"Shigu District\"},{\"n\":\"Steamed Xiang District\"},{\"n\":\"Nanyue District\"},{\"n\":\"Hengyang County\"},{\"n\":\"Hengnan County\"},{\"n\":\"Hengshan County\"},{\"n\":\"Hengdong County\"},{\"n\":\"Qidong County\"},{\"n\":\"Leiyang\"},{\"n\":\"Changning City\"}]},{\"n\":\"Shaoyang City\",\"s\":[{\"n\":\"Shuangqing District\"},{\"n \":\"Daxiang District\"},{\"n\":\"Beita District\"},{\"n\":\"Shaodong County\"},{\"n\": \"Xinshao County\"},{\"n\":\"Shaoyang County\"},{\"n\":\"Longhui County\"},{\"n\":\"Dongkou County\"},{\"n\":\"Suining County\"},{\"n\":\"Xinning County\"},{\"n\":\"Chengbu Miao Autonomous County\"},{\"n\":\"Wugang City\"}]},{\"n\":\"Yueyang City\",\"s\":[{\"n\":\" Yueyang Tower\"},{\"n\":\"Yunxi District\"},{\"n\":\"Junshan District\"},{\"n\":\"Yueyang County\"},{\"n\":\"Huarong County\"},{\"n\":\"Xiangyin County\"},{\"n\":\"Pingjiang County\"},{\"n \":\"Miluo City\"},{\"n\":\"Linxiang City\"}]},{\"n\":\"Changde City\",\"s\":[ {\"n\":\"Wuling District\"},{\"n\":\"Dingcheng District\"},{\"n\":\"Anxiang County\"},{\"n \":\"Hanshou County\"},{\"n\":\"Li County\"},{\"n\":\"Linli County\"},{\"n\":\"Taoyuan County\"},{\"n\":\"Shimen County\"},{\"n\":\"Jinshi\"}]},{\"n\":\"Zhangjiajie City\",\"s\":[{\"n\":\"Yongding District\"},{\"n\":\"Wulingyuan District\"},{\"n\":\"Cili County\"},{\"n\":\"Sangzhi County\"}]},{\"n\":\"Yiyang City\",\"s\":[{\"n \":\"Ziyang District\"},{\"n\":\"Heshan District\"},{\"n\":\"South County\"},{\"n\":\" Taojiang County\"},{\"n\":\"Anhua County\"},{\"n\":\"Yuanjiang City\"}]},{\"n\":\"Chenzhou City \",\"s\":[{\"n\":\"Beihu District\"},{\"n\":\"Suxian District\"},{\"n\":\" Guiyang County\"},{\"n\":\"Yizhang County\"},{\"n\":\"Yongxing County\"},{\"n\":\"Jiahe County \"},{\"n\":\"Linwu County\"},{\"n\":\"Rucheng County\"},{\"n\":\"Guidong County\" },{\"n\":\"Anren County\"},{\"n\":\"Zixing City\"}]},{\"n\":\"Yongzhou\",\"s\":[{\"n\":\"Lingling District\"},{\"n\":\"Lengshuitan District\"},{\"n\":\"Qiyang County\"},{\"n\":\"Dongan County\"},{\"n\":\"Shuangpai County\"},{\"n\":\"Daoxian\"},{\"n\":\"Jiangyong County\"},{\"n\":\"Ningyuan County\"},{\"n\":\"Lanshan County\"},{\"n \":\"Xintian County\"},{\"n\":\"Jianghua Yao Autonomous County\"}]},{\"n\":\"Huaihua City\",\"s\": [{\"n\":\"Hecheng District\"},{\"n\":\"Zhongfang County\"},{\"n\":\"Yuanling County\"},{\" n\":\"Chenxi County\"},{\"n\":\"Xupu County\"},{\"n\":\"Huitong County\"},{\"n\": \"Mayang Miao Autonomous County\"}, {\"n\":\"Xinhuang Dong Autonomous County\"},{\"n\":\"Zhijiang Dong Autonomous County\"},{\"n\":\"Jingzhou Miao and Dong Autonomous County\" },{\"n\":\"Channel Dong Autonomous County\"},{\"n\":\"Hongjiang City\"}]},{\"n\":\"Loudi City\",\"s\":[{\"n\":\"Louxing District\"},{\"n\":\"Shuangfeng County\"},{\"n\":\"Xinhua County \"},{\"n\":\"Lengshuijiang City\"},{\"n\":\"Lianyuan City\"}]},{\"n\":\"Xiangxi Tujia Miao Autonomous Prefecture\",\"s\":[{\"n\":\"Jishou City\"},{\"n\":\"Luxi County\"},{\"n\": \"Phoenix County\"},{\"n\":\"Huayuan County\"},{\"n\":\"Baojing County\"},{\"n\":\"Gu Zhang County\"},{\"n\":\"Yongshun County\"},{\"n\":\"Longshan County\"}]}]},{\"n\":\"Guangdong Province\",\"s\":[{\"n\":\"Guangzhou\",\"s\":[{\"n\":\"Liwan District\"},{\" n\":\"Yuexiu District\"},{\"n\":\"Haizhu District\"},{\"n\":\"Tianhe District\"},{\"n\":\"Baiyun District\"},{\"n\":\"Huangpu District\"},{\"n\":\"Panyu District\"},{\"n\":\"Huadu District\"},{\"n\":\"Nansha District\"},{\"n\":\"Luogang District\"},{\"n\":\"Zengcheng\"},{ \"n\":\"Conghua City\"}]},{\"n\":\"Shaoguan City\",\"s\":[{\"n\":\"Wujiang District\" },{\"n\":\"Jianjiang District\"},{\"n\":\"Qujiang District\"},{\"n\":\"Shixing County\"}, {\"n\":\"Renhua County\"},{\"n\":\"Wengyuan County\"},{\"n\":\"Ruyuan Yao Autonomous County\"},{ \"n\":\"Xinfeng County\"},{\"n\":\"Lechang City\"},{\"n\":\"Nanxiong City\"}]},{\"n\":\"Shenzhen\",\"s\":[{\"n\":\"Luohu District\"},{\"n\":\"Futian District\"},{ \"n\":\"Nanshan District\"},{\"n\":\"Baoan District\"},{\"n\":\"Longgang District\"},{\"n\" :\"Yantian District\"}]},{\"n\":\"Zhuhai City\",\"s\":[{\"n\":\"Xiangzhou District\"},{\" n\":\"Doumen District\"},{\"n\":\"Jinwan District\"}]},{\"n\":\"Shantou City\",\"s\":[{\"n\":\"Longhu District\"},{\"n\":\"Jinping District\"},{\"n\":\"Haojiang District\" },{\"n\":\"Chaoyang District\"},{\"n\":\"Chaonan District\"},{\"n\":\"Chenghai District\"},{\"n\":\"Nan'ao County\"}]},{\"n\":\"Foshan\",\"s\":[{\"n\":\"Chancheng District\" },{\"n\":\"Nanhai District\"},{\"n\":\"Shunde District\"},{\"n\":\"Sanshui District\"},{\"n\":\"Gaoming District\"}]},{\"n\":\"Jiangmen\",\"s\":[{\"n\":\"Pengjiang District\" },{\"n\":\"Jianghai District\"},{\"n\":\"Xinhui District\"},{\"n\":\"Taishan City\"},{\"n\":\"Kaiping City\"},{\"n\":\"Heshan City\"},{\"n\":\"Enping City\"}]},{\"n\":\"Zhanjiang\",\"s\":[{\"n\":\"Chikan District\"},{\"n\":\"Xiashan District\"},{\" n\":\"Potou District\"},{\"n\":\"Mazhang District\"},{\"n\":\"Suixi County\"},{\"n\":\"Xuwen County\"},{\"n\":\"Lianjiang City\"},{\"n\":\"Leizhou\"},{\"n\":\" Wuchuan City\"}]},{\"n\":\"Maoming City\",\"s\":[{\"n\":\"Maonan District\"},{\"n\":\"Maogang District\"},{\"n\":\"Dianbai County\"},{\"n\":\"Gaozhou\"},{\"n\":\"Huazhou\"},{\"n\":\"Xinyi City\"}]},{\"n\":\"Zhaoqing City\",\"s\":[{\" n\":\"Duanzhou District\"},{\"n\":\"Dinghu District\"},{\"n\":\"Guangning County\"},{\"n\": \"Huaiji County\"},{\"n\":\"Fengkai County\"},{\"n\":\"Deqing County\"},{\"n\":\"High Main City\"},{\"n\":\"Sihui City\"}]},{\"n\":\"Huizhou\",\"s\":[{\"n\":\"Huicheng District\"},{\"n\":\"Huiyang District\"},{\"n\":\"Boluo County\"},{\"n\":\" Huidong County\"},{\"n\":\"Longmen County\"}]},{\"n\":\"Meizhou\",\"s\":[{\"n\":\"Meijiang District\"},{\"n\":\"Meixian\"},{\"n\":\"Dapu County\"},{\"n\":\" Fengshun County\"},{\"n\":\"Wuhua County\"},{\"n\":\"Pingyuan County\"},{\"n\":\"Jiaoling County\"},{\"n\":\"Xingning City\"}]},{\"n\":\"Shanwei\",\"s\":[{\"n\":\"City\"},{\"n\":\"Sea Feng County\"},{\"n\":\"Luhe County\"},{\"n\":\"Lufeng City\"}]},{\"n\":\"Heyuan City\",\"s\":[{\"n\":\"Yuancheng\"},{\"n\":\"Zijin County\"},{\"n\":\"Longchuan County\"},{\"n\":\"Lianping County\"},{\"n\":\"Heping County\"},{\"n\":\"Dongyuan County\" }]},{\"n\":\"Yangjiang City\",\"s\":[{\"n\":\"Jiangcheng District\"},{\"n\":\"Yang Xi County\"},{\"n\":\"Yangdong County\"},{\"n\":\"Yangchun City\"}]},{\"n\":\"Qingyuan City \",\"s\":[{\"n\":\"Qingcheng District\"},{\"n\":\"Fresh District\"},{\"n\":\"Buddha Gang County\"},{\"n\":\"Yangshan County\"},{\"n\":\"Lianshan Zhuang and Yao Autonomous County\"},{\"n\":\"Nanyao Autonomous County\"},{\"n\":\"Yingde City\"},{\"n\":\"Lianzhou\"}]},{\"n\":\" Dongguan\"},{\"n\":\"Zhongshan\"},{\"n\":\"Chaozhou\",\"s\":[{\"n\":\"Xiangqiao District\"},{\"n\":\"Chaoan District\"},{\"n\":\"Raoping County\"}]},{\"n\":\"Jieyang City\",\"s\":[{\"n\":\"Rongcheng District\"},{\"n\":\"Jiedong District\"},{\"n\": \"Jiexi County\"},{\"n\":\"Huilai County\"},{\"n\":\"Puning City\"}]},{\"n\":\"Yunfu City\",\"s\":[{\"n\":\"Yuncheng District\"},{\"n\":\"Xingxing County\"},{\"n\":\"Yunan County\"},{\"n\":\"Yunan County\"},{\"n\":\"Luoding City\"}]}]}]";
        return data;
    }

    /**
     * Get user data
    */
    @GetMapping("/userModel")
    @ResponseBody
    public AjaxResult userModel()
    {
        AjaxResult ajax = new AjaxResult();

        ajax.put("code", 200);
        ajax.put("value", users);
        return ajax;
    }

    /**
      * Get data collection
    */
    @GetMapping("/collection")
    @ResponseBody
    public AjaxResult collection()
    {
        String[] array = { "qlcx 1", "qlcx 2", "qlcx 3", "qlcx 4", "qlcx 5" };
        AjaxResult ajax = new AjaxResult();
        ajax.put("value", array);
        return ajax;
    }
}

class UserFormModel
{
    /** User ID */
    private int userId;

    /** user ID */
    private String userCode;

    /** username */
    private String userName;

    /** User phone */
    private String userPhone;

    public UserFormModel()
    {

    }

    public UserFormModel(int userId, String userCode, String userName, String userPhone)
    {
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.userPhone = userPhone;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

}
