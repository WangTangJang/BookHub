package com.wang.model.result;

import lombok.Data;

@Data
public class LoginResult {

    private Meta meta;
    private Data data;

    @lombok.Data
    public static class Meta{
        private int status;
        private String massage;
        public Meta(int c ,String m){
            this.status = c;
            this.massage = m;
        }
    }

    @lombok.Data
    public static class Data {
        private int uid;
        private String token;

        public Data(int i, String s) {
            this.uid = i;
            this.token = s;
        }
    }
}
