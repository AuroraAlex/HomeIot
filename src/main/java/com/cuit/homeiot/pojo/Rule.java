package com.cuit.homeiot.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Rule {

    /**
     * data
     */ /**
     * data : {"rawsql":"SELECT clientid FROM \"testRule/62cdabc4695d497e9a46a9801af69fd6\" WHERE payload.aaa > 1","on_action_failed":"continue","metrics":[{"speed_max":0,"speed_last5m":0,"speed":0,"node":"emqx@127.0.0.1","matched":0}],"id":"rule:7a57f2e7","for":["testRule/62cdabc4695d497e9a46a9801af69fd6"],"enabled":true,"description":"test-rule","actions":[{"params":{"target_topic":"cmd/62cdabc4695d497e9a46a9801af69fd6","target_qos":0,"payload_tmpl":"{\"aaa\":2}"},"name":"republish","metrics":[{"success":0,"node":"emqx@127.0.0.1","failed":0}],"id":"republish_1618489951686110028","fallbacks":[]}]}
     * code : 0
     */

    private DataDTO data;
    /**
     * code
     */
    private Integer code;

    /**
     * DataDTO
     */
    @NoArgsConstructor
    @Data
    public static class DataDTO {
        /**
         * rawsql
         */ /**
         * rawsql : SELECT clientid FROM "testRule/62cdabc4695d497e9a46a9801af69fd6" WHERE payload.aaa > 1
         * on_action_failed : continue
         * metrics : [{"speed_max":0,"speed_last5m":0,"speed":0,"node":"emqx@127.0.0.1","matched":0}]
         * id : rule:7a57f2e7
         * for : ["testRule/62cdabc4695d497e9a46a9801af69fd6"]
         * enabled : true
         * description : test-rule
         * actions : [{"params":{"target_topic":"cmd/62cdabc4695d497e9a46a9801af69fd6","target_qos":0,"payload_tmpl":"{\"aaa\":2}"},"name":"republish","metrics":[{"success":0,"node":"emqx@127.0.0.1","failed":0}],"id":"republish_1618489951686110028","fallbacks":[]}]
         */

        private String rawsql;
        /**
         * on_action_failed
         */
        private String on_action_failed;
        /**
         * metrics
         */
        private List<MetricsDTO> metrics;
        /**
         * id
         */
        private String id;
        /**
         * forX
         */
        private List<String> forX;
        /**
         * enabled
         */
        private Boolean enabled;
        /**
         * description
         */
        private String description;
        /**
         * actions
         */
        private List<ActionsDTO> actions;

        /**
         * MetricsDTO
         */
        @NoArgsConstructor
        @Data
        public static class MetricsDTO {
            /**
             * speed_max
             */ /**
             * speed_max : 0
             * speed_last5m : 0
             * speed : 0
             * node : emqx@127.0.0.1
             * matched : 0
             */

            private Integer speed_max;
            /**
             * speed_last5m
             */
            private Integer speed_last5m;
            /**
             * speed
             */
            private Integer speed;
            /**
             * node
             */
            private String node;
            /**
             * matched
             */
            private Integer matched;
        }

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
             * metrics : [{"success":0,"node":"emqx@127.0.0.1","failed":0}]
             * id : republish_1618489951686110028
             * fallbacks : []
             */

            private ParamsDTO params;
            /**
             * name
             */
            private String name;
            /**
             * metrics
             */
            private List<MetricsDTO> metrics;
            /**
             * id
             */
            private String id;
            /**
             * fallbacks
             */
            private List<?> fallbacks;

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

            /**
             * MetricsDTO
             */
            @NoArgsConstructor
            @Data
            public static class MetricsDTO {
                /**
                 * success
                 */ /**
                 * success : 0
                 * node : emqx@127.0.0.1
                 * failed : 0
                 */

                private Integer success;
                /**
                 * node
                 */
                private String node;
                /**
                 * failed
                 */
                private Integer failed;
            }
        }
    }
}
