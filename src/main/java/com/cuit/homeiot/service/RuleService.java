package com.cuit.homeiot.service;

import com.cuit.homeiot.mapper.RuleListMapper;
import com.cuit.homeiot.pojo.Rule;
import com.cuit.homeiot.pojo.RuleList;
import com.cuit.homeiot.pojo.RulePara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RuleService {
    private final RestTemplate restTemplate;
    private final RuleListMapper ruleListMapper;
    @Value("${EMQ.URL}")
    public String url;


    @Autowired
    public RuleService(RestTemplate restTemplate, RuleListMapper ruleListMapper) {
        this.restTemplate = restTemplate;
        this.ruleListMapper = ruleListMapper;
    }

    public Boolean addRule(String sdid,
                    String tdid,
                    String cmd,
                    String condition,
                    String description){
        Map<String,Object> map = new HashMap<>();
        RulePara rulePara = new RulePara();
        RulePara.ActionsDTO actionsDTO = new RulePara.ActionsDTO();
        RulePara.ActionsDTO.ParamsDTO paramsDTO= new RulePara.ActionsDTO.ParamsDTO();
        String sql = "SELECT clientid FROM \"$PERSISTENCE/"+sdid+"\" WHERE payload."+condition;
        rulePara.setRawsql(sql);
        rulePara.setDescription(description);
        actionsDTO.setName("republish");
        paramsDTO.setPayload_tmpl(cmd);
        paramsDTO.setTarget_qos(0);
        paramsDTO.setTarget_topic("cmd/"+tdid);
        actionsDTO.setParams(paramsDTO);
        List<RulePara.ActionsDTO> actions = new ArrayList<>();
        actions.add(actionsDTO);
        rulePara.setActions(actions);


        System.out.println(rulePara);

        Rule res =  restTemplate.postForObject(url+"/api/v4/rules",rulePara,Rule.class);
        if (res == null){
            return false;
        }
        RuleList ruleList = new RuleList();
        ruleList.setRid(res.getData().getId());
        ruleList.setDes(res.getData().getDescription());
        ruleList.setSdid(sdid);
        ruleList.setTdid(tdid);
        ruleListMapper.insert(ruleList);
        return true;
    }

    public List<RuleList> getAll(){
        List<RuleList> rules = ruleListMapper.selectList(null);
        return rules;
    }

    public Boolean delRule(String rid){
        Map<String, Object> col  = new HashMap<>();
        col.put("rid",rid);
        restTemplate.delete(url+"/api/v4/rules/{1}",rid);
        return ruleListMapper.deleteByMap(col)>0;
    }


    public boolean batchDelDevice(String ids) {
        String[] rids = ids.split(",");
        for(String rid:rids){
            if (!delRule(rid)){
                return false;
            }
        }
        return true;
    }
}
