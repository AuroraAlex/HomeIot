package com.cuit.homeiot.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class RulePara {

    /**
     * rawsql
     */ /**
     * rawsql : SELECT clientid FROM "testRule/62cdabc4695d497e9a46a9801af69fd6" WHERE payload.aaa > 1
     * actions : [{"params":{"target_topic":"cmd/62cdabc4695d497e9a46a9801af69fd6","target_qos":0,"payload_tmpl":"{\"aaa\":2}"},"name":"republish"}]
     * description : test-rule
     */

    private String rawsql;
    /**
     * actions
     */
    private List<ActionsDTO> actions;
    /**
     * description
     */
    private String description;

    /**
     * ActionsDTO
     */
    @NoArgsConstructor
    @Data
    public static class ActionsDTO {
        /**
         * params
         */ /**
         * params : {"target_topic":"cmd/62cdabc4695d497e9a46a9801af69fd6","target_qos":0,"payload_tmpl":"{\"aaa\":2}"}
         * name : republish
         */

        private ParamsDTO params;
        /**
         * name
         */
        private String name;

        /**
         * ParamsDTO
         */
        @NoArgsConstructor
        @Data
        public static class ParamsDTO {
            /**
             * target_topic
             */ /**
             * target_topic : cmd/62cdabc4695d497e9a46a9801af69fd6
             * target_qos : 0
             * payload_tmpl : {"aaa":2}
             */

            private String target_topic;
            /**
             * target_qos
             */
            private Integer target_qos;
            /**
             * payload_tmpl
             */
            private String payload_tmpl;
        }
    }
}
