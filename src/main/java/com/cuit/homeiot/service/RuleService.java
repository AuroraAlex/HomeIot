package com.cuit.homeiot.service;

import com.cuit.homeiot.pojo.Rule;
import com.cuit.homeiot.pojo.RulePara;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RuleService {
    private final RestTemplate restTemplate;
    @Value("${EMQ.URL}")
    public String url;


    @Autowired
    public RuleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
        return res != null;
    }



}
