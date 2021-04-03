package com.cuit.homeiot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiClients {


    /**
     * meta
     */ /**
     * meta : {"page":1,"limit":10000,"hasnext":false,"count":-1}
     * data : [{"recv_cnt":12,"recv_msg":0,"mqueue_dropped":0,"mqueue_len":0,"clean_start":true,"clientid":"test","subscriptions_cnt":0,"send_pkt":12,"recv_oct":52,"max_inflight":32,"proto_name":"MQTT","mountpoint":"undefined","connected":true,"ip_address":"192.168.220.1","heap_size":610,"is_bridge":false,"expiry_interval":0,"proto_ver":4,"max_mqueue":1000,"username":"mqtt","inflight":0,"created_at":"2021-03-24 17:00:37","node":"emqx@127.0.0.1","max_awaiting_rel":100,"awaiting_rel":0,"send_msg":0,"connected_at":"2021-03-24 17:00:37","keepalive":60,"reductions":9350,"zone":"external","recv_pkt":1,"send_cnt":12,"max_subscriptions":0,"mailbox_len":0,"send_oct":26,"port":64830}]
     * code : 0
     */

    private MetaDTO meta;
    /**
     * data
     */
    private List<DataDTO> data;
    /**
     * code
     */
    private Integer code;

    /**
     * MetaDTO
     */
    @NoArgsConstructor
    @Data
    public static class MetaDTO {
        /**
         * page
         */ /**
         * page : 1
         * limit : 10000
         * hasnext : false
         * count : -1
         */

        private Integer page;
        /**
         * limit
         */
        private Integer limit;
        /**
         * hasnext
         */
        private Boolean hasnext;
        /**
         * count
         */
        private Integer count;
    }

    /**
     * DataDTO
     */
    @NoArgsConstructor
    @Data
    public static class DataDTO {
        /**
         * recv_cnt
         */ /**
         * recv_cnt : 12
         * recv_msg : 0
         * mqueue_dropped : 0
         * mqueue_len : 0
         * clean_start : true
         * clientid : test
         * subscriptions_cnt : 0
         * send_pkt : 12
         * recv_oct : 52
         * max_inflight : 32
         * proto_name : MQTT
         * mountpoint : undefined
         * connected : true
         * ip_address : 192.168.220.1
         * heap_size : 610
         * is_bridge : false
         * expiry_interval : 0
         * proto_ver : 4
         * max_mqueue : 1000
         * username : mqtt
         * inflight : 0
         * created_at : 2021-03-24 17:00:37
         * node : emqx@127.0.0.1
         * max_awaiting_rel : 100
         * awaiting_rel : 0
         * send_msg : 0
         * connected_at : 2021-03-24 17:00:37
         * keepalive : 60
         * reductions : 9350
         * zone : external
         * recv_pkt : 1
         * send_cnt : 12
         * max_subscriptions : 0
         * mailbox_len : 0
         * send_oct : 26
         * port : 64830
         */

        private Integer recv_cnt;
        /**
         * recv_msg
         */
        private Integer recv_msg;
        /**
         * mqueue_dropped
         */
        private Integer mqueue_dropped;
        /**
         * mqueue_len
         */
        private Integer mqueue_len;
        /**
         * clean_start
         */
        private Boolean clean_start;
        /**
         * clientid
         */
        private String clientid;
        /**
         * subscriptions_cnt
         */
        private Integer subscriptions_cnt;
        /**
         * send_pkt
         */
        private Integer send_pkt;
        /**
         * recv_oct
         */
        private Integer recv_oct;
        /**
         * max_inflight
         */
        private Integer max_inflight;
        /**
         * proto_name
         */
        private String proto_name;
        /**
         * mountpoint
         */
        private String mountpoint;
        /**
         * connected
         */
        private Boolean connected;
        /**
         * ip_address
         */
        private String ip_address;
        /**
         * heap_size
         */
        private Integer heap_size;
        /**
         * is_bridge
         */
        private Boolean is_bridge;
        /**
         * expiry_interval
         */
        private Integer expiry_interval;
        /**
         * proto_ver
         */
        private Integer proto_ver;
        /**
         * max_mqueue
         */
        private Integer max_mqueue;
        /**
         * username
         */
        private String username;
        /**
         * inflight
         */
        private Integer inflight;
        /**
         * created_at
         */
        private String created_at;
        /**
         * node
         */
        private String node;
        /**
         * max_awaiting_rel
         */
        private Integer max_awaiting_rel;
        /**
         * awaiting_rel
         */
        private Integer awaiting_rel;
        /**
         * send_msg
         */
        private Integer send_msg;
        /**
         * connected_at
         */
        private String connected_at;
        /**
         * keepalive
         */
        private Integer keepalive;
        /**
         * reductions
         */
        private Integer reductions;
        /**
         * zone
         */
        private String zone;
        /**
         * recv_pkt
         */
        private Integer recv_pkt;
        /**
         * send_cnt
         */
        private Integer send_cnt;
        /**
         * max_subscriptions
         */
        private Integer max_subscriptions;
        /**
         * mailbox_len
         */
        private Integer mailbox_len;
        /**
         * send_oct
         */
        private Integer send_oct;
        /**
         * port
         */
        private Integer port;
    }
}
